//ID         :
//SCREEN NAME:Collection - Portfolio Quality Statement
//CREATED BY :Nuwan De Silva	
//DATE/TIME  : 26-02-2006
//NOTES:

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_RE_Portfolio_Quality_Statement_New extends javax.servlet.http.HttpServlet { 

 ServletOutputStream out = null;
	
	
	Connection conn;
	Statement stmt,stmt_partner,stmt2;
	CallableStatement callstmt1 =null;

	java.text.NumberFormat nf;
	
  public ResultSet rs,rs_partner,rs2;
 	

	public String m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_due_date,m_no_of_due_date,m_finance_no,m_print;
	public double m_amount_due;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String header_name = m_sn_methods.header_name.trim();
			String m_username=m_sn_methods.username;
      //m_username="OFSCLALL";

      res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
		  nf.setMinimumFractionDigits(0);
		  nf.setMaximumFractionDigits(0);
			
		// out.println("conn"+conn);
		
			
			//Orient Data -------------------------------------------------------------------------
			String m_orient_name="";
			String m_orient_add1="";
			String m_orient_add2="";
			String m_orient_city_name="";
			String m_orient_tel_no="";
			String m_orient_fax_no="";
			String m_orient_vat_rate="";
			//--------------------------------------------------------------------------------------
			
			String data="";
			String m_status="";
			int m_cum_value=0;
			double m_precentage=0;
			double m_tot_nil=0;
			double m_tot_arreas=0;
			//double to_million=1000000;
			double to_million=1;
		 String m_chksql = req.getParameter("chksql");
  	 //if(m_chksql.trim().equals("main_page")){
		 //String m_generate = req.getParameter("generate");
		 //String m_print=req.getParameter("print");
		 stmt = conn.createStatement ();
	   stmt2 = conn.createStatement ();
		
		String m_month_2="";
		String m_Letter_date_2="";
		String m_Letter_date_3="";
		String m_Letter_date_month="";
		String m_Letter_date_month_2="";
		String m_Letter_date_month_3="";
		double m_sub_total_arrears=0;
		double arr_precentage=0;
		double total_nil=0;
		double total_one_month_arr=0;
		double total_two_month_arr=0;
		double total_three_month_arr=0;
		double total_four_month_arr=0;
		double total_six_month_arr=0;
		int total_contracts=0;
		double total=0;
		double m_cum=0;

				rs = stmt.executeQuery(" SELECT "+
					    " COMPANY_NAME, "+
					    " ADDRESS1, "+
					    " ADDRESS2, "+
					    " CITY, "+
					    " TEL_NO, "+
					    " FAX_NO,  "+
							" VAT_RATE "+
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
											m_orient_vat_rate=rs.getString(7);			
											}
											
					rs.close();
					
					
			if(m_chksql.equals("run_report")){ 
			
			String m_date=req.getParameter("date");
			String m_division=req.getParameter("division_code");
			String m_report_type = req.getParameter("report_type");
			try{
			callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_PORTFOLIO(:1,:2,:3,:4);END;");
			callstmt1.setString(1,m_date);
			callstmt1.setString(2,m_division);
			callstmt1.setString(3,m_username);
			callstmt1.setString(4,m_report_type);
			callstmt1.execute();
			out.print("OK"); 
			
			}
			catch(Exception ex){
			out.println("ERROR"+ex.toString()); 
			}
			
			}
			else if(m_chksql.trim().equals("print_report")){	
			
		  String m_date="";
			String m_division="";
			String m_report_type="",m_report_desc="",m_division_desc="";
		
		 if(req.getParameter("date")!=null ){
		 m_date=req.getParameter("date").trim();
		 }
			
		 if(req.getParameter("division_code")!=null ){
		 m_division=req.getParameter("division_code").trim();
		 }	
			
			
		 if(req.getParameter("report_type")!=null ){
		 m_report_type=req.getParameter("report_type").trim();
		 }	
     
			if(m_report_type.equals("N_LEG")){ 
			m_report_desc="Live Contracts";
			}else{
			m_report_desc="Legal Contracts";
			}
			
			if(m_division.equals("AF")){ 
			m_division_desc="Asset Finance";
			}else{
			m_division_desc="Bike Division";
			}
		
			out.println("<html><head>"); 
			out.println("<title>Portfolio Quality Statement</title></head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("function show_movement_drill(m_age){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Portfolio_Quality_Statement_New?chksql=drill_down_details&report_type="+m_report_type+"&division="+m_division+"&age=\"+m_age;"); 
			out.println("window.open(m_url);");	
			out.println("}");	
			/*
			out.println("function show_movement_drill_old(m_user_id,m_age){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age_New?chksql=drill_down_details_old&age=\"+m_age+\"&user_id=\"+m_user_id;"); 
			out.println("window.open(m_url);");	
			out.println("}");	
		 */			
			out.println("</script>"); 
			
			out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"\">");//add_button()
			out.println("<body bgcolor='white'><br>");
			
			out.println("<form name='Form1'>");
			String m_date_1="",m_date_2="",m_date_3="";
			rs = stmt.executeQuery ("SELECT TO_CHAR(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),'fmddth Month yyyy') LETTER_DATE , "+
			" TO_CHAR(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),'MONTH')   ,"+
			" TO_CHAR(ADD_MONTHS(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),-1),'fmddth Month yyyy') LETTER_DATE, "+
			" TO_CHAR(ADD_MONTHS(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),-1),'MONTH') ,"+
			" TO_CHAR(ADD_MONTHS(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),-2),'fmddth Month yyyy') LETTER_DATE, "+
			" TO_CHAR(ADD_MONTHS(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),-2),'MONTH'), "+
			" TO_CHAR(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),'DD-MM-YYYY')   ,"+
			" TO_CHAR(ADD_MONTHS(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),-1),'DD-MM-YYYY') ,"+
			" TO_CHAR(ADD_MONTHS(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),-2),'DD-MM-YYYY') "+
			" FROM DUAL ");          
			more = rs.next();
			if(more){
			m_Letter_date=rs.getString(1);
			m_Letter_date_month=rs.getString(2);	
			m_Letter_date_2=rs.getString(3);
			m_Letter_date_month_2=rs.getString(4);
			m_Letter_date_3=rs.getString(5);
			m_Letter_date_month_3=rs.getString(6);
			m_date_1=rs.getString(7);
			m_date_2=rs.getString(8);
			m_date_3=rs.getString(9);
			}
		//	rs.close();
			
			if(m_report_type.equals("N_LEG")){
									
			rs=stmt.executeQuery(
			" SELECT   "+ 
			" a.finance_no, "+ //1
			" a.client_code, "+ //2
			" a.client_full_name,  "+ //3
			//" ceil((NVL(a.total_rentals,0)- NVL(a.rentals_paid,0) )) age ,"+ //4
			" ROUND(a.age) ,"+ //4
			" a.total_amount-(nvl(a.settled_amount,0) + nvl(a.adjusted_amount,0)) arrears, "+ //5
			" (nvl(a.future_receivable,0) + nvl(a.arr_capital_portion,0) ) cap_out,  "+ //6
			//" ceil((NVL(a.total_rentals_prv_month,0)- NVL(a.rentals_paid_prv_month,0) )) age  ,"+ //7
			" ROUND(a.age_prv_month) ,"+ //7
			" a.total_amount_prv_month-(nvl(a.settled_amount_prv_month,0) + nvl(a.adjusted_amount_prv_month,0)) arrears_old, "+ //8
			" (nvl(a.future_receivable_prv_month,0) + nvl(a.arr_capital_portion_prv_month,0) ) cap_out_old  ,"+ //9
			" nvl(a.arrears_age_0,0) ,"+ //10
      " nvl(a.arrears_age_1,0) , "+ //11
			" nvl(a.arrears_age_2,0) , "+ //12
			" nvl(a.arrears_age_3,0) , "+ //13
      " nvl(a.arrears_age_4_5,0) , "+ //14
			" nvl(a.arrears_age_6,0) , "+ //15
			" nvl(a.arrears_age_0_old,0) , "+ //16
      " nvl(a.arrears_age_1_old,0) , "+ //17
			" nvl(a.arrears_age_2_old,0) ,  "+ //18
			" nvl(a.arrears_age_3_old,0) , "+ //19
      " nvl(a.arrears_age_4_5_old,0) ,  "+ //20
			" nvl(a.arrears_age_6_old,0) ,"+ //21
			//" ceil((NVL(a.total_rentals_prv_month2,0)- NVL(a.rentals_paid_prv_month2,0) )) age  ,"+ //22
			" ROUND(a.age_prv_month2) ,"+ //22
			" a.total_amount_prv_month2-(nvl(a.settled_amount_prv_month2,0) + nvl(a.adjusted_amount_prv_month2,0)) arrears_old, "+ //23
			" (nvl(a.future_receivable_prv_month2,0) + nvl(a.arr_capital_portion_prv_month2,0) ) cap_out_old  ,"+ //24
			" nvl(a.arrears_age_0_old_2,0) , "+ //25
      " nvl(a.arrears_age_1_old_2,0) , "+ //26
			" nvl(a.arrears_age_2_old_2,0) ,  "+ //27
			" nvl(a.arrears_age_3_old_2,0) , "+ //28
      " nvl(a.arrears_age_4_5_old_2,0) ,  "+ //29
			" nvl(a.arrears_age_6_old_2,0) "+ //30
			" FROM "+m_schema_name+".af_re_tbd_portfolio a "+
			" where a.ent_user='"+m_username+"' "+
			" and   a.DIVISION_CODE='"+m_division+"'  "+
			" and NVL(a.COLLECTION_OFFICER,'-') <>'L001' "+//L001
			" and a.activate_date <=to_date('"+m_date_1+"','DD-MM-YYYY') " );
			}
			
			else if(m_report_type.equals("LEG")){
			
			rs=stmt.executeQuery(
			" SELECT   "+ 
			" a.finance_no, "+ //1
			" a.client_code, "+ //2
			" a.client_full_name,  "+ //3
			//" ceil((NVL(a.total_rentals,0)- NVL(a.rentals_paid,0) )) age ,"+ //4
			" ROUND(a.age) ,"+ //4
			" nvl(a.total_amount,0) -(nvl(a.settled_amount,0) + nvl(a.adjusted_amount,0)) arrears, "+ //5
			" (nvl(a.future_receivable,0) + nvl(a.arr_capital_portion,0) ) cap_out,  "+ //6
			//" ceil((NVL(a.total_rentals_prv_month,0)- NVL(a.rentals_paid_prv_month,0) )) age  ,"+ //7
			" ROUND(a.age_prv_month) ,"+ //7
			" nvl(a.total_amount_prv_month,0) - (nvl(a.settled_amount_prv_month,0) + nvl(a.adjusted_amount_prv_month,0)) arrears_old, "+ //8
			" (nvl(a.future_receivable_prv_month,0) + nvl(a.arr_capital_portion_prv_month,0) ) cap_out_old  ,"+ //9
			" nvl(a.arrears_age_0,0) ,"+ //10
      " nvl(a.arrears_age_1,0) , "+ //11
			" nvl(a.arrears_age_2,0) , "+ //12
			" nvl(a.arrears_age_3,0) , "+ //13
      " nvl(a.arrears_age_4_5,0) , "+ //14
			" nvl(a.arrears_age_6,0) , "+ //15
			" nvl(a.arrears_age_0_old,0) , "+ //16
      " nvl(a.arrears_age_1_old,0) , "+ //17
			" nvl(a.arrears_age_2_old,0) ,  "+ //18
			" nvl(a.arrears_age_3_old,0) , "+ //19
      " nvl(a.arrears_age_4_5_old,0) ,  "+ //20
			" nvl(a.arrears_age_6_old,0) ,"+ //21
			//" ceil((NVL(a.total_rentals_prv_month2,0)- NVL(a.rentals_paid_prv_month2,0) )) age  ,"+ //22
			" ROUND(a.age_prv_month2) ,"+ //22
			" a.total_amount_prv_month2-(nvl(a.settled_amount_prv_month2,0) + nvl(a.adjusted_amount_prv_month2,0)) arrears_old, "+ //23
			" (nvl(a.future_receivable_prv_month2,0) + nvl(a.arr_capital_portion_prv_month2,0) ) cap_out_old  ,"+ //24
			" nvl(a.arrears_age_0_old_2,0) , "+ //25
      " nvl(a.arrears_age_1_old_2,0) , "+ //26
			" nvl(a.arrears_age_2_old_2,0) ,  "+ //27
			" nvl(a.arrears_age_3_old_2,0) , "+ //28
      " nvl(a.arrears_age_4_5_old_2,0) ,  "+ //29
			" nvl(a.arrears_age_6_old_2,0) "+ //30
			" FROM "+m_schema_name+".af_re_tbd_portfolio a "+
			" where a.ent_user='"+m_username+"' "+
			" and   a.DIVISION_CODE='"+m_division+"'  "+
			" and NVL(a.COLLECTION_OFFICER,'-') ='L001' "+//L001
			" and a.activate_date <=to_date('"+m_date_1+"','DD-MM-YYYY') " );
				
							
				
				}
			
		  boolean more2=rs.next();
			int m_age=0,m_age_old=0,m_age_old_2=0;
			int m_age_0_count=0,m_age_1_count=0,m_age_2_count=0,m_age_3_count=0,m_age_4_count=0,m_age_6_count=0,m_age_4_5_count=0;
			int m_age_0_count_old=0,m_age_1_count_old=0,m_age_2_count_old=0,m_age_3_count_old=0,m_age_4_count_old=0,m_age_6_count_old=0,m_age_4_5_count_old=0;
			int m_age_0_count_old_2=0,m_age_1_count_old_2=0,m_age_2_count_old_2=0,m_age_3_count_old_2=0,m_age_4_count_old_2=0,m_age_6_count_old_2=0,m_age_4_5_count_old_2=0;
			
			double m_amount=0,m_age_0_m_amount=0,m_age_1_m_amount=0,m_age_2_m_amount=0,m_age_3_m_amount=0,m_age_6_m_amount=0;
			double m_amount_old=0,m_age_0_m_amount_old=0,m_age_1_m_amount_old=0,m_age_2_m_amount_old=0,m_age_3_m_amount_old=0,m_age_6_m_amount_old=0;
			double m_amount_old_2=0,m_age_0_m_amount_old_2=0,m_age_1_m_amount_old_2=0,m_age_2_m_amount_old_2=0,m_age_3_m_amount_old_2=0,m_age_6_m_amount_old_2=0;
			double m_amount_cap=0;
			double m_amount_cap_old=0;
			double m_amount_cap_old_2=0;
			double m_age_0_m_amount_cap=0,m_age_1_m_amount_cap=0,m_age_2_m_amount_cap=0,m_age_3_m_amount_cap=0,m_age_6_m_amount_cap=0;
			double m_age_0_m_amount_cap_old=0,m_age_1_m_amount_cap_old=0,m_age_2_m_amount_cap_old=0,m_age_3_m_amount_cap_old=0,m_age_6_m_amount_cap_old=0;
			double m_age_0_m_amount_cap_old_2=0,m_age_1_m_amount_cap_old_2=0,m_age_2_m_amount_cap_old_2=0,m_age_3_m_amount_cap_old_2=0,m_age_6_m_amount_cap_old_2=0;
			double m_age_4_5_m_amount=0,m_age_4_5_m_amount_cap=0;
			double m_age_4_5_m_amount_old=0,m_age_4_5_m_amount_cap_old=0;
			double m_age_4_5_m_amount_old_2=0,m_age_4_5_m_amount_cap_old_2=0;
			
			double m_age_6_1=0,m_age_6_2=0,m_age_6_3=0,m_age_6_4_5=0,m_age_6_6=0;
			double m_age_4_5_1=0,m_age_4_5_2=0,m_age_4_5_3=0,m_age_4_5_4_5=0;
      double m_age_3_1=0,m_age_3_2=0,m_age_3_3=0;
			double m_age_2_1=0,m_age_2_2=0;
			
			double m_age_old_6_1=0,m_age_old_6_2=0,m_age_old_6_3=0,m_age_old_6_4_5=0,m_age_old_6_6=0;
			double m_age_old_4_5_1=0,m_age_old_4_5_2=0,m_age_old_4_5_3=0,m_age_old_4_5_4_5=0;
      double m_age_old_3_1=0,m_age_old_3_2=0,m_age_old_3_3=0;
			double m_age_old_2_1=0,m_age_old_2_2=0;
			
			double m_age_old_2_6_1=0,m_age_old_2_6_2=0,m_age_old_2_6_3=0,m_age_old_2_6_4_5=0,m_age_old_2_6_6=0;
			double m_age_old_2_4_5_1=0,m_age_old_2_4_5_2=0,m_age_old_2_4_5_3=0,m_age_old_2_4_5_4_5=0;
      double m_age_old_2_3_1=0,m_age_old_2_3_2=0,m_age_old_2_3_3=0;
			double m_age_old_2_2_1=0,m_age_old_2_2_2=0;
			
			double total_arrears=0;
			//double total_nil=0;
			
			if(more2){
			while(more2){
			m_age=rs.getInt(4);
			m_amount=rs.getDouble(5);
			m_amount_cap=rs.getDouble(6);
			
			if ( m_age <=0 ){
			m_age_0_count=m_age_0_count+1;
			m_age_0_m_amount_cap=m_age_0_m_amount_cap+m_amount_cap;
			}
			else if (m_age ==1){
			m_age_1_count=m_age_1_count+1;
			m_age_1_m_amount+=m_amount;
			m_age_1_m_amount_cap=m_age_1_m_amount_cap+m_amount_cap;
			}
			else if (m_age == 2){
			m_age_2_count=m_age_2_count+1;
			m_age_2_m_amount+=m_amount;
			m_age_2_m_amount_cap=m_age_2_m_amount_cap+m_amount_cap;
			m_age_2_1+=rs.getDouble(11)+rs.getDouble(10);
			m_age_2_2+=rs.getDouble(12)+rs.getDouble(13)+rs.getDouble(14)+rs.getDouble(15);
			}
			else if (m_age ==3){
			m_age_3_count=m_age_3_count+1;
			m_age_3_m_amount+=m_amount;
			m_age_3_m_amount_cap=m_age_3_m_amount_cap+m_amount_cap;
			m_age_3_1+=rs.getDouble(11)+rs.getDouble(10);
			m_age_3_2+=rs.getDouble(12);
			m_age_3_3+=rs.getDouble(13)+rs.getDouble(14)+rs.getDouble(15);
			}
			else if (m_age >3 && m_age <=5){
			m_age_4_5_count=m_age_4_5_count+1;
			m_age_4_5_m_amount+=m_amount;
			m_age_4_5_m_amount_cap=m_age_4_5_m_amount_cap+m_amount_cap;
			m_age_4_5_1+=rs.getDouble(11)+rs.getDouble(10);
			m_age_4_5_2+=rs.getDouble(12);
			m_age_4_5_3+=rs.getDouble(13);
			m_age_4_5_4_5+=rs.getDouble(14)+rs.getDouble(15);
			}
			else if(m_age >5 ){
		  m_age_6_count=m_age_6_count+1;
			m_age_6_m_amount+=m_amount;
			m_age_6_m_amount_cap=m_age_6_m_amount_cap+m_amount_cap;
			m_age_6_1+=rs.getDouble(11)+rs.getDouble(10);
			m_age_6_2+=rs.getDouble(12);
			m_age_6_3+=rs.getDouble(13);
			m_age_6_4_5+=rs.getDouble(14);
			m_age_6_6+=rs.getDouble(15);
			}
			
		  more2=rs.next();
			}
			}
						
			if(m_report_type.equals("N_LEG")){
			
			rs=stmt.executeQuery(
			" SELECT   "+ 
			" a.finance_no, "+ //1
			" a.client_code, "+ //2
			" a.client_full_name,  "+ //3
			//" ceil((NVL(a.total_rentals,0)- NVL(a.rentals_paid,0) )) age ,"+ //4
			" ROUND(a.age) ,"+ //4
			" a.total_amount-(nvl(a.settled_amount,0) + nvl(a.adjusted_amount,0)) arrears, "+ //5
			" (nvl(a.future_receivable,0) + nvl(a.arr_capital_portion,0) ) cap_out,  "+ //6
			//" ceil((NVL(a.total_rentals_prv_month,0)- NVL(a.rentals_paid_prv_month,0) )) age  ,"+ //7
			" ROUND(a.age_prv_month) ,"+ //4
			" a.total_amount_prv_month-(nvl(a.settled_amount_prv_month,0) + nvl(a.adjusted_amount_prv_month,0)) arrears_old, "+ //8
			" (nvl(a.future_receivable_prv_month,0) + nvl(a.arr_capital_portion_prv_month,0) ) cap_out_old  ,"+ //9
			" nvl(a.arrears_age_0,0) ,"+ //10
      " nvl(a.arrears_age_1,0) , "+ //11
			" nvl(a.arrears_age_2,0) , "+ //12
			" nvl(a.arrears_age_3,0) , "+ //13
      " nvl(a.arrears_age_4_5,0) , "+ //14
			" nvl(a.arrears_age_6,0) , "+ //15
			" nvl(a.arrears_age_0_old,0) , "+ //16
      " nvl(a.arrears_age_1_old,0) , "+ //17
			" nvl(a.arrears_age_2_old,0) ,  "+ //18
			" nvl(a.arrears_age_3_old,0) , "+ //19
      " nvl(a.arrears_age_4_5_old,0) ,  "+ //20
			" nvl(a.arrears_age_6_old,0) ,"+ //21
			//" ceil((NVL(a.total_rentals_prv_month2,0)- NVL(a.rentals_paid_prv_month2,0) )) age  ,"+ //22
			" ROUND(a.age_prv_month2) ,"+ //22
			" a.total_amount_prv_month2-(nvl(a.settled_amount_prv_month2,0) + nvl(a.adjusted_amount_prv_month2,0)) arrears_old, "+ //23
			" (nvl(a.future_receivable_prv_month2,0) + nvl(a.arr_capital_portion_prv_month2,0) ) cap_out_old  ,"+ //24
			" nvl(a.arrears_age_0_old_2,0) , "+ //25
      " nvl(a.arrears_age_1_old_2,0) , "+ //26
			" nvl(a.arrears_age_2_old_2,0) ,  "+ //27
			" nvl(a.arrears_age_3_old_2,0) , "+ //28
      " nvl(a.arrears_age_4_5_old_2,0) ,  "+ //29
			" nvl(a.arrears_age_6_old_2,0) "+ //30
			" FROM "+m_schema_name+".af_re_tbd_portfolio a "+
			" where a.ent_user='"+m_username+"' "+
			" and   DIVISION_CODE='"+m_division+"'  "+
			" and NVL(a.COLLECTION_OFFICER,'-') <>'L001' "+//L001
			" and a.activate_date <=to_date('"+m_date_2+"','DD-MM-YYYY') " );
			
			}else if(m_report_type.equals("LEG")){
			
			rs=stmt.executeQuery(
			" SELECT   "+ 
			" a.finance_no, "+ //1
			" a.client_code, "+ //2
			" a.client_full_name,  "+ //3
			//" ceil((NVL(a.total_rentals,0)- NVL(a.rentals_paid,0) )) age ,"+ //4
			" ROUND(a.age) ,"+ //4
			" a.total_amount-(nvl(a.settled_amount,0) + nvl(a.adjusted_amount,0)) arrears, "+ //5
			" (nvl(a.future_receivable,0) + nvl(a.arr_capital_portion,0) ) cap_out,  "+ //6
			//" ceil((NVL(a.total_rentals_prv_month,0)- NVL(a.rentals_paid_prv_month,0) )) age  ,"+ //7
			" ROUND(a.age_prv_month) ,"+ //7
			" a.total_amount_prv_month-(nvl(a.settled_amount_prv_month,0) + nvl(a.adjusted_amount_prv_month,0)) arrears_old, "+ //8
			" (nvl(a.future_receivable_prv_month,0) + nvl(a.arr_capital_portion_prv_month,0) ) cap_out_old  ,"+ //9
			" nvl(a.arrears_age_0,0) ,"+ //10
      " nvl(a.arrears_age_1,0) , "+ //11
			" nvl(a.arrears_age_2,0) , "+ //12
			" nvl(a.arrears_age_3,0) , "+ //13
      " nvl(a.arrears_age_4_5,0) , "+ //14
			" nvl(a.arrears_age_6,0) , "+ //15
			" nvl(a.arrears_age_0_old,0) , "+ //16
      " nvl(a.arrears_age_1_old,0) , "+ //17
			" nvl(a.arrears_age_2_old,0) ,  "+ //18
			" nvl(a.arrears_age_3_old,0) , "+ //19
      " nvl(a.arrears_age_4_5_old,0) ,  "+ //20
			" nvl(a.arrears_age_6_old,0) ,"+ //21
			//" ceil((NVL(a.total_rentals_prv_month2,0)- NVL(a.rentals_paid_prv_month2,0) )) age  ,"+ //22
			" ROUND(a.age_prv_month2) ,"+ //22
			" a.total_amount_prv_month2-(nvl(a.settled_amount_prv_month2,0) + nvl(a.adjusted_amount_prv_month2,0)) arrears_old, "+ //23
			" (nvl(a.future_receivable_prv_month2,0) + nvl(a.arr_capital_portion_prv_month2,0) ) cap_out_old  ,"+ //24
			" nvl(a.arrears_age_0_old_2,0) , "+ //25
      " nvl(a.arrears_age_1_old_2,0) , "+ //26
			" nvl(a.arrears_age_2_old_2,0) ,  "+ //27
			" nvl(a.arrears_age_3_old_2,0) , "+ //28
      " nvl(a.arrears_age_4_5_old_2,0) ,  "+ //29
			" nvl(a.arrears_age_6_old_2,0) "+ //30
			" FROM "+m_schema_name+".af_re_tbd_portfolio a "+
			" where a.ent_user='"+m_username+"' "+
			" and   DIVISION_CODE='"+m_division+"'  "+
			" and NVL(a.COLLECTION_OFFICER,'-') ='L001' "+//L001
			" and a.activate_date <=to_date('"+m_date_2+"','DD-MM-YYYY') " );
			
			}

			
			more2=rs.next();

			if(more2){
			while(more2){
			m_age_old=rs.getInt(7);
			m_amount_old=rs.getDouble(8);
			m_amount_cap_old=rs.getDouble(9);
			
			if ( m_age_old <=0 ){
			m_age_0_count_old=m_age_0_count_old+1;
			m_age_0_m_amount_cap_old=m_age_0_m_amount_cap_old+m_amount_cap_old;
			}
			else if (m_age_old ==1){
			m_age_1_count_old=m_age_1_count_old+1;
			m_age_1_m_amount_old+=m_amount_old;
			m_age_1_m_amount_cap_old=m_age_1_m_amount_cap_old+m_amount_cap_old;
			}
			else if (m_age_old ==2){
			m_age_2_count_old=m_age_2_count_old+1;
			m_age_2_m_amount_old+=m_amount_old;
			m_age_2_m_amount_cap_old=m_age_2_m_amount_cap_old+m_amount_cap_old;
			m_age_old_2_1+=rs.getDouble(17)+rs.getDouble(16);
			m_age_old_2_2+=rs.getDouble(18)+rs.getDouble(19)+rs.getDouble(20)+rs.getDouble(21);
		  }
			else if (m_age_old ==3){
			m_age_3_count_old=m_age_3_count_old+1;
			m_age_3_m_amount_old+=m_amount_old;
			m_age_3_m_amount_cap_old=m_age_3_m_amount_cap_old+m_amount_cap_old;
			m_age_old_3_1+=rs.getDouble(17)+rs.getDouble(16);
			m_age_old_3_2+=rs.getDouble(18);
			m_age_old_3_3+=rs.getDouble(19)+rs.getDouble(20)+rs.getDouble(21);
			}
			else if (m_age_old >3 && m_age_old <=5){
			m_age_4_5_count_old=m_age_4_5_count_old+1;
			m_age_4_5_m_amount_old+=m_amount_old;
			m_age_4_5_m_amount_cap_old=m_age_4_5_m_amount_cap_old+m_amount_cap_old;
			m_age_old_4_5_1+=rs.getDouble(17)+rs.getDouble(16);
			m_age_old_4_5_2+=rs.getDouble(18);
			m_age_old_4_5_3+=rs.getDouble(19);
			m_age_old_4_5_4_5+=rs.getDouble(20)+rs.getDouble(21);
			
			}
			else if(m_age_old > 5 ){
		  m_age_6_count_old=m_age_6_count_old+1;
			m_age_6_m_amount_old+=m_amount_old;
			m_age_6_m_amount_cap_old=m_age_6_m_amount_cap_old+m_amount_cap_old;
		  m_age_old_6_1+=rs.getDouble(17)+rs.getDouble(16);
			m_age_old_6_2+=rs.getDouble(18);
			m_age_old_6_3+=rs.getDouble(19);
			m_age_old_6_4_5+=rs.getDouble(20);
			m_age_old_6_6+=rs.getDouble(21);
			}
		  more2=rs.next();
			}
			}
			
		/*	
		 
			if(m_report_type.equals("N_LEG")){

			rs=stmt.executeQuery(
			" SELECT   "+ 
			" a.finance_no, "+ //1
			" a.client_code, "+ //2
			" a.client_full_name,  "+ //3
			//" ceil((NVL(a.total_rentals,0)- NVL(a.rentals_paid,0) )) age ,"+ //4
			" ROUND(a.age) ,"+ //4
			" a.total_amount-(nvl(a.settled_amount,0) + nvl(a.adjusted_amount,0)) arrears, "+ //5
			" (nvl(a.future_receivable,0) + nvl(a.arr_capital_portion,0) ) cap_out,  "+ //6
			//" ceil((NVL(a.total_rentals_prv_month,0)- NVL(a.rentals_paid_prv_month,0) )) age  ,"+ //7
			" ROUND(a.age_prv_month) ,"+ //7
			" a.total_amount_prv_month-(nvl(a.settled_amount_prv_month,0) + nvl(a.adjusted_amount_prv_month,0)) arrears_old, "+ //8
			" (nvl(a.future_receivable_prv_month,0) + nvl(a.arr_capital_portion_prv_month,0) ) cap_out_old  ,"+ //9
			" nvl(a.arrears_age_0,0) ,"+ //10
      " nvl(a.arrears_age_1,0) , "+ //11
			" nvl(a.arrears_age_2,0) , "+ //12
			" nvl(a.arrears_age_3,0) , "+ //13
      " nvl(a.arrears_age_4_5,0) , "+ //14
			" nvl(a.arrears_age_6,0) , "+ //15
			" nvl(a.arrears_age_0_old,0) , "+ //16
      " nvl(a.arrears_age_1_old,0) , "+ //17
			" nvl(a.arrears_age_2_old,0) ,  "+ //18
			" nvl(a.arrears_age_3_old,0) , "+ //19
      " nvl(a.arrears_age_4_5_old,0) ,  "+ //20
			" nvl(a.arrears_age_6_old,0) ,"+ //21
			//" ceil((NVL(a.total_rentals_prv_month2,0)- NVL(a.rentals_paid_prv_month2,0) )) age  ,"+ //22
			" ROUND(a.age_prv_month2) ,"+ //22
			" a.total_amount_prv_month2-(nvl(a.settled_amount_prv_month2,0) + nvl(a.adjusted_amount_prv_month2,0)) arrears_old, "+ //23
			" (nvl(a.future_receivable_prv_month2,0) + nvl(a.arr_capital_portion_prv_month2,0) ) cap_out_old  ,"+ //24
			" nvl(a.arrears_age_0_old_2,0) , "+ //25
      " nvl(a.arrears_age_1_old_2,0) , "+ //26
			" nvl(a.arrears_age_2_old_2,0) ,  "+ //27
			" nvl(a.arrears_age_3_old_2,0) , "+ //28
      " nvl(a.arrears_age_4_5_old_2,0) ,  "+ //29
			" nvl(a.arrears_age_6_old_2,0) "+ //30
			" FROM "+m_schema_name+".af_re_tbd_portfolio a "+
			" where a.ent_user='"+m_username+"' "+
			" and   DIVISION_CODE='"+m_division+"'  "+
			" and NVL(a.COLLECTION_OFFICER,'-')  <>'L001' "+//L001
			" and a.activate_date <=to_date('"+m_date_3+"','DD-MM-YYYY') " );
			
			}else if(m_report_type.equals("LEG")){
			
			rs=stmt.executeQuery(
			" SELECT   "+ 
			" a.finance_no, "+ //1
			" a.client_code, "+ //2
			" a.client_full_name,  "+ //3
			//" ceil((NVL(a.total_rentals,0)- NVL(a.rentals_paid,0) )) age ,"+ //4
			" ROUND(a.age) ,"+ //4
			" a.total_amount-(nvl(a.settled_amount,0) + nvl(a.adjusted_amount,0)) arrears, "+ //5
			" (nvl(a.future_receivable,0) + nvl(a.arr_capital_portion,0) ) cap_out,  "+ //6
			//" ceil((NVL(a.total_rentals_prv_month,0)- NVL(a.rentals_paid_prv_month,0) )) age  ,"+ //7
			" ROUND(a.age_prv_month) ,"+ //7
			" a.total_amount_prv_month-(nvl(a.settled_amount_prv_month,0) + nvl(a.adjusted_amount_prv_month,0)) arrears_old, "+ //8
			" (nvl(a.future_receivable_prv_month,0) + nvl(a.arr_capital_portion_prv_month,0) ) cap_out_old  ,"+ //9
			" nvl(a.arrears_age_0,0) ,"+ //10
      " nvl(a.arrears_age_1,0) , "+ //11
			" nvl(a.arrears_age_2,0) , "+ //12
			" nvl(a.arrears_age_3,0) , "+ //13
      " nvl(a.arrears_age_4_5,0) , "+ //14
			" nvl(a.arrears_age_6,0) , "+ //15
			" nvl(a.arrears_age_0_old,0) , "+ //16
      " nvl(a.arrears_age_1_old,0) , "+ //17
			" nvl(a.arrears_age_2_old,0) ,  "+ //18
			" nvl(a.arrears_age_3_old,0) , "+ //19
      " nvl(a.arrears_age_4_5_old,0) ,  "+ //20
			" nvl(a.arrears_age_6_old,0) ,"+ //21
			//" ceil((NVL(a.total_rentals_prv_month2,0)- NVL(a.rentals_paid_prv_month2,0) )) age  ,"+ //22
			" ROUND(a.age_prv_month2) ,"+ //22
			" a.total_amount_prv_month2-(nvl(a.settled_amount_prv_month2,0) + nvl(a.adjusted_amount_prv_month2,0)) arrears_old, "+ //23
			" (nvl(a.future_receivable_prv_month2,0) + nvl(a.arr_capital_portion_prv_month2,0) ) cap_out_old  ,"+ //24
			" nvl(a.arrears_age_0_old_2,0) , "+ //25
      " nvl(a.arrears_age_1_old_2,0) , "+ //26
			" nvl(a.arrears_age_2_old_2,0) ,  "+ //27
			" nvl(a.arrears_age_3_old_2,0) , "+ //28
      " nvl(a.arrears_age_4_5_old_2,0) ,  "+ //29
			" nvl(a.arrears_age_6_old_2,0) "+ //30
			" FROM "+m_schema_name+".af_re_tbd_portfolio a "+
			" where a.ent_user='"+m_username+"' "+
			" and   DIVISION_CODE='"+m_division+"'  "+
			" and NVL(a.COLLECTION_OFFICER,'-') ='L001' "+//L001
			" and a.activate_date <=to_date('"+m_date_3+"','DD-MM-YYYY') " );
			
			}
			more2=rs.next();

			if(more2){
			while(more2){
			m_age_old_2=rs.getInt(22);
			m_amount_old_2=rs.getDouble(23);
			m_amount_cap_old_2=rs.getDouble(24);
			
			if ( m_age_old_2 <=0 ){
			m_age_0_count_old_2+=1;
			m_age_0_m_amount_cap_old_2+=+m_amount_cap_old_2;
			}
			else if (m_age_old_2 ==1){
			m_age_1_count_old_2+=1;
			m_age_1_m_amount_old_2+=m_amount_old_2;
			m_age_1_m_amount_cap_old_2+=m_amount_cap_old_2;
			}
			else if (m_age_old_2 ==2){
			m_age_2_count_old_2+=1;
			m_age_2_m_amount_old_2+=m_amount_old_2;
			m_age_2_m_amount_cap_old_2+=m_amount_cap_old_2;
			m_age_old_2_2_1+=rs.getDouble(26)+rs.getDouble(25);
			m_age_old_2_2_2+=rs.getDouble(27)+rs.getDouble(28)+rs.getDouble(29)+rs.getDouble(30);
		  }
			else if (m_age_old_2 ==3){
			m_age_3_count_old_2+=1;
			m_age_3_m_amount_old_2+=m_amount_old_2;
			m_age_3_m_amount_cap_old_2+=m_amount_cap_old_2;
			m_age_old_2_3_1+=rs.getDouble(26)+rs.getDouble(25);
			m_age_old_2_3_2+=rs.getDouble(27);
			m_age_old_2_3_3+=rs.getDouble(28)+rs.getDouble(29)+rs.getDouble(30);
			}
			else if (m_age_old_2  > 3 && m_age_old_2 <=5){
			m_age_4_5_count_old_2+=1;
			m_age_4_5_m_amount_old_2+=m_amount_old_2;
			m_age_4_5_m_amount_cap_old_2+=m_amount_cap_old_2;
			m_age_old_2_4_5_1+=rs.getDouble(26)+rs.getDouble(25);
			m_age_old_2_4_5_2+=rs.getDouble(27);
			m_age_old_2_4_5_3+=rs.getDouble(28);
			m_age_old_2_4_5_4_5+=rs.getDouble(29)+rs.getDouble(30);
			}
			else if(m_age_old_2 >5 ){
		  m_age_6_count_old_2+=1;
			m_age_6_m_amount_old_2+=m_amount_old_2;
			m_age_6_m_amount_cap_old_2+=m_amount_cap_old_2;
		  m_age_old_2_6_1+=rs.getDouble(26)+rs.getDouble(25);
			m_age_old_2_6_2+=rs.getDouble(27);
			m_age_old_2_6_3+=rs.getDouble(28);
			m_age_old_2_6_4_5+=rs.getDouble(29);
			m_age_old_2_6_6+=rs.getDouble(30);
			}
		  more2=rs.next();
			}
			}
      
			*/
			
			double total_con=0;
			double sum_1_month=0,sum_2_month=0,sum_3_month=0,sum_4_5_month=0,sum_6_month=0;
			total_nil            =m_age_0_m_amount_cap+m_age_1_m_amount_cap+m_age_2_m_amount_cap+m_age_3_m_amount_cap+m_age_4_5_m_amount_cap+m_age_6_m_amount_cap;
			total_contracts      =m_age_0_count+m_age_1_count+m_age_2_count+m_age_3_count+m_age_4_5_count+m_age_6_count;
			total_con=total_contracts;
			out.println("<table border='0' width='95%' class='table' align='center' >"); 		
			out.println("<tr ><td width='20%' class='rep-body' align='left' >To</td>");
			out.println("<td width='*%' class='rep-body' align='left' >The Board of Directors</td></tr>");
			
			out.println("<tr ><td width='20%' class='rep-body' align='left' >Through</td>");
			out.println("<td width='*%' class='rep-body' align='left' >Director/CEO</td></tr>");
			
			out.println("<tr ><td width='20%' class='rep-body' align='left' >From</td>");
			out.println("<td width='*%' class='rep-body' align='left' >AGM - Recoveries</td></tr>");
			
			out.println("<tr ><td width='20%' class='rep-body' align='left' >Subject</td>");
			out.println("<td width='*%' class='rep-body' align='left' >Portfolio Quality</td></tr>");
			
			out.println("</table>");	
			
			out.println("<br><br>");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr ><td width='*%' class='rep-body' align='center' ><b>PORTFOLIO QUALITY STATEMENT AS AT "+m_Letter_date+"</td></tr>");
			out.println("<tr ><td width='*%' class='rep-body' align='center' >Division - "+m_division_desc+"</td></tr>");
			out.println("<tr ><td width='*%' class='rep-body' align='center' >Report Type  - "+m_report_desc+"</td></tr>");
			out.println("</table>");	
			
      out.println("<br><br>");
      out.println("<table width=\"95%\"  border=\"1\" align='center' cellspacing=\"0\" > "); 
			out.println("<tr bgcolor='lightblue' > "); //class=pdn_txtpos2
			out.println("<td width=\"16%\" STYLE='{font:  8pt bold arial; text-align:center;}'>&nbsp;</td> ");
			out.println("<td colspan=\"2\" STYLE='{font:  8pt bold arial; text-align:center;}'><b>No Arrears </td> ");
			out.println("<td colspan=\"2\" STYLE='{font:  8pt bold arial; text-align:center;}'><b>1 Months Arrears </td> ");
			out.println("<td colspan=\"2\" STYLE='{font:  8pt bold arial; text-align:center;}'><b>2 Months Arrears </td> ");
			out.println("<td colspan=\"2\" STYLE='{font:  8pt bold arial; text-align:center;}'><b>3 Months Arrears </td> ");
			out.println("<td colspan=\"2\" STYLE='{font:  8pt bold arial; text-align:center;}'><b>4-5 Months Arrears </td> ");
			out.println("<td colspan=\"2\" STYLE='{font:  8pt bold arial; text-align:center;}'> <b>>=6 Months Arrears </td> ");
			out.println(" </tr>  ");
			out.println(" <tr class=pdn_txtpos2> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' >&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' width=\"6%\"><b>No</td> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' width=\"8%\"><b>Capital O/S</td> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' width=\"6%\"><b>No</td> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' width=\"8%\"><b>Capital O/S</td> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' width=\"6%\"><b>No</td> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' width=\"8%\"><b>Capital O/S</td> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' width=\"6%\"><b>No</td> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' width=\"8%\"><b>Capital O/S</td> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' width=\"6%\"><b>No</td> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' width=\"8%\"><b>Capital O/S</td> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' width=\"6%\"><b>No</td> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' width=\"8%\"><b>Capital O/S</td> ");
			out.println(" </tr> ");
			
			
			out.println(" <tr> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:left;}'>Balance B/F</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+m_age_0_count_old+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_0_m_amount_cap_old/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+m_age_1_count_old+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_1_m_amount_cap_old/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+m_age_2_count_old+"</td> "); 
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_2_m_amount_cap_old/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+m_age_3_count_old+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_3_m_amount_cap_old/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+m_age_4_5_count_old+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_4_5_m_amount_cap_old/to_million)+"</td> ");  
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+m_age_6_count_old+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_6_m_amount_cap_old/to_million)+"</td> ");
			out.println(" </tr> ");
			
			out.println(" <tr> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:left;}'>Net Movement</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+(m_age_0_count - m_age_0_count_old)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_age_0_m_amount_cap-m_age_0_m_amount_cap_old)/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+(m_age_1_count - m_age_1_count_old)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_age_1_m_amount_cap-m_age_1_m_amount_cap_old)/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+(m_age_2_count - m_age_2_count_old)+"</td> "); 
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_age_2_m_amount_cap-m_age_2_m_amount_cap_old)/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+(m_age_3_count - m_age_3_count_old)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_age_3_m_amount_cap-m_age_3_m_amount_cap_old)/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+(m_age_4_5_count - m_age_4_5_count_old)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_age_4_5_m_amount_cap-m_age_4_5_m_amount_cap_old)/to_million)+"</td> ");  
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+(m_age_6_count - m_age_6_count_old)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_age_6_m_amount_cap-m_age_6_m_amount_cap_old)/to_million)+"</td> ");
			out.println(" </tr> ");
			
			out.println(" <tr> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:left;}'>Balance C/F</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+m_age_0_count+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_0_m_amount_cap/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+m_age_1_count+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_1_m_amount_cap/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+m_age_2_count+"</td> "); 
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_2_m_amount_cap/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+m_age_3_count+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_3_m_amount_cap/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+m_age_4_5_count+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_4_5_m_amount_cap/to_million)+"</td> ");  
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+m_age_6_count+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_6_m_amount_cap/to_million)+"</td> ");
			out.println(" </tr> ");
			
			out.println(" <tr> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:left;}'>Arrears contracts as % of total reveivalbe</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_nil/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_age_0_m_amount_cap/total_nil)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_age_1_m_amount_cap/total_nil)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> "); 
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_age_2_m_amount_cap/total_nil)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_age_3_m_amount_cap/total_nil)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_age_4_5_m_amount_cap/total_nil)*100)+"%</td> ");  
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_age_6_m_amount_cap/total_nil)*100)+"%</td> ");
			out.println(" </tr> ");
			
			
			out.println(" <tr> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:left;}'>Arrears as % of total contracts</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+total_contracts+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_age_0_count/total_con)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_age_1_count/total_con)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> "); 
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_age_2_count/total_con)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_age_3_count/total_con)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_age_4_5_count/total_con)*100)+"%</td> ");  
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_age_6_count/total_con)*100)+"%</td> ");
			out.println(" </tr> ");
			
			out.println(" <tr> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:left;}'><B>CUMULATIVE VALUE % - "+m_Letter_date_month+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'><B>"+nf.format((m_age_0_m_amount_cap/total_nil)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'><B>"+nf.format(((m_age_1_m_amount_cap+m_age_0_m_amount_cap)/total_nil)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> "); 
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'><B>"+nf.format(((m_age_1_m_amount_cap+m_age_0_m_amount_cap+m_age_2_m_amount_cap)/total_nil)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'><B>"+nf.format(((m_age_1_m_amount_cap+m_age_0_m_amount_cap+m_age_2_m_amount_cap+m_age_3_m_amount_cap)/total_nil)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'><B>"+nf.format(((m_age_1_m_amount_cap+m_age_0_m_amount_cap+m_age_2_m_amount_cap+m_age_3_m_amount_cap+m_age_4_5_m_amount_cap)/total_nil)*100)+"%</td> ");  
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'><B>"+nf.format(((m_age_1_m_amount_cap+m_age_0_m_amount_cap+m_age_2_m_amount_cap+m_age_3_m_amount_cap+m_age_4_5_m_amount_cap+m_age_6_m_amount_cap)/total_nil)*100)+"%</td> ");
			out.println(" </tr> ");
			
			double total_nil_old=0,total_contracts_old=0,total_con_old=0;
			total_nil_old            =m_age_0_m_amount_cap_old+m_age_1_m_amount_cap_old+m_age_2_m_amount_cap_old+m_age_3_m_amount_cap_old+m_age_4_5_m_amount_cap_old+m_age_6_m_amount_cap_old;
			total_contracts_old      =m_age_0_count_old+m_age_1_count_old+m_age_2_count_old+m_age_3_count_old+m_age_4_5_count_old+m_age_6_count_old;
			total_con_old=total_contracts_old;
			
			out.println(" <tr> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:left;}'>CUMULATIVE VALUE % - "+m_Letter_date_month_2+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_age_0_m_amount_cap_old/total_nil)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(((m_age_1_m_amount_cap_old+m_age_0_m_amount_cap_old)/total_nil)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> "); 
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(((m_age_1_m_amount_cap_old+m_age_0_m_amount_cap_old+m_age_2_m_amount_cap_old)/total_nil)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(((m_age_1_m_amount_cap_old+m_age_0_m_amount_cap+m_age_2_m_amount_cap+m_age_3_m_amount_cap_old)/total_nil)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(((m_age_1_m_amount_cap_old+m_age_0_m_amount_cap_old+m_age_2_m_amount_cap_old+m_age_3_m_amount_cap_old+m_age_4_5_m_amount_cap_old)/total_nil)*100)+"%</td> ");  
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(((m_age_1_m_amount_cap_old+m_age_0_m_amount_cap_old+m_age_2_m_amount_cap_old+m_age_3_m_amount_cap_old+m_age_4_5_m_amount_cap_old+m_age_6_m_amount_cap_old)/total_nil)*100)+"%</td> ");
			out.println(" </tr> ");
			
			
			out.println(" <tr> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:left;}'><b>CUMULATIVE CONTRACT % - "+m_Letter_date_month+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'><b>"+nf.format((m_age_0_count/total_con)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'><b>"+nf.format(((m_age_0_count+m_age_1_count)/total_con)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> "); 
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'><b>"+nf.format(((m_age_0_count+m_age_1_count+m_age_2_count)/total_con)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'><b>"+nf.format(((m_age_0_count+m_age_1_count+m_age_2_count+m_age_3_count)/total_con)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'><b>"+nf.format(((m_age_0_count+m_age_1_count+m_age_2_count+m_age_3_count+m_age_4_5_count)/total_con)*100)+"%</td> ");  
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'><b>"+nf.format(((m_age_0_count+m_age_1_count+m_age_2_count+m_age_3_count+m_age_4_5_count+m_age_6_count)/total_con)*100)+"%</td> ");
			out.println(" </tr> ");


			
			out.println(" <tr> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:left;}'>CUMULATIVE CONTRACT % - "+m_Letter_date_month_2+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_age_0_count_old/total_con_old)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(((m_age_0_count_old+m_age_1_count_old)/total_con_old)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> "); 
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(((m_age_0_count_old+m_age_1_count_old+m_age_2_count_old)/total_con_old)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(((m_age_0_count_old+m_age_1_count_old+m_age_2_count_old+m_age_3_count_old)/total_con_old)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(((m_age_0_count_old+m_age_1_count_old+m_age_2_count_old+m_age_3_count_old+m_age_4_5_count_old)/total_con_old)*100)+"%</td> ");  
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(((m_age_0_count_old+m_age_1_count_old+m_age_2_count_old+m_age_3_count_old+m_age_4_5_count_old+m_age_6_count_old)/total_con_old)*100)+"%</td> ");
			out.println(" </tr> ");

			
			out.println(" <tr> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:left;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> "); 
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");  
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println(" </tr> ");
			
			out.println(" <tr> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:left;}'>NO OF CONTRACTS</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+total_contracts+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> "); 
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");  
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println(" </tr> ");
			
			out.println(" <tr> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:left;}'>NET INVESTMENT IN LEASE</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_nil/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> "); 
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");  
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println(" </tr> ");
			out.println("</table>");
			
			out.flush();
						
			out.println(" <br><br><br> ");
			
			out.println("<table border='0' width='100%' align='center' class='table'>"); 		
			out.println("<tr ><td width='*%' class='rep-body' align='center' ><b>"+m_orient_name.toUpperCase()+"</td></tr>");
		  out.println("<tr ><td width='*%' class='rep-body' align='center' ><b>PORTFOLIO QUALITY STATEMENT AS AT "+m_Letter_date+"</td></tr>");
		  out.println("</table>");	
			
      out.println("<br>");
			out.println("<table border='1' width='95%' align='center' class='table' cellspacing='0' >"); 		
			out.println("<tr bgcolor='lightblue'  ><td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Contracts</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Cum Contracts</td>");
			out.println("<td width='9%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Category</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Nil</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Arrears</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>1 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>2 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>3 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>4-5 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>>6 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Demand</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Months</td>");
			out.println("</tr>");
			
			m_cum_value+=m_age_0_count;
			total_arrears=m_age_0_m_amount+m_age_1_m_amount+m_age_2_m_amount+m_age_3_m_amount+m_age_4_5_m_amount+m_age_6_m_amount;
			total_nil=m_age_0_m_amount_cap+m_age_1_m_amount_cap+m_age_2_m_amount_cap+m_age_3_m_amount_cap+m_age_4_5_m_amount_cap+m_age_6_m_amount_cap;
	    m_precentage=(m_age_0_m_amount/total_arrears)*100;
			out.println("<tr onClick=\"show_movement_drill(0)\" style=\"{cursor:hand;}\" >");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_age_0_count+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>Zero contracts</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_0_m_amount_cap/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");//"+nf.format(m_age_0_m_amount/to_million)+"
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>0%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("</tr>");
			
			m_cum_value+=m_age_1_count;
			m_precentage=(m_age_1_m_amount/total_arrears)*100;
			out.println("<tr onClick=\"show_movement_drill(1)\" style=\"{cursor:hand;}\" >");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_age_1_count+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>One Month contracts</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_1_m_amount_cap/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_1_m_amount/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_1_m_amount)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("</tr>");
			m_cum_value+=m_age_2_count;
			m_precentage=(m_age_2_m_amount/total_arrears)*100;
			out.println("<tr onClick=\"show_movement_drill(2)\" style=\"{cursor:hand;}\" >");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_age_2_count+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>Two Month contracts</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_2_m_amount_cap/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_2_m_amount/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_2_1)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_2_2)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("</tr>");
			m_cum_value+=m_age_3_count;
			m_precentage=(m_age_3_m_amount/total_arrears)*100;
			out.println("<tr onClick=\"show_movement_drill(3)\" style=\"{cursor:hand;}\" >");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_age_3_count+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>Three Month contracts</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_3_m_amount_cap/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_3_m_amount/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_3_1)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_3_2)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_3_3)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("</tr>");
			m_cum_value+=m_age_4_5_count;
			m_precentage=(m_age_4_5_m_amount/total_arrears)*100;
			out.println("<tr onClick=\"show_movement_drill(5)\" style=\"{cursor:hand;}\" >");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_age_4_5_count+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>4 - 5 Month contracts</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_4_5_m_amount_cap/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_4_5_m_amount/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_4_5_1)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_4_5_2)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_4_5_3)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_4_5_4_5)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("</tr>");
			m_cum_value+=m_age_6_count;
			m_precentage=(m_age_6_m_amount/total_arrears)*100;
			out.println("<tr onClick=\"show_movement_drill(6)\" style=\"{cursor:hand;}\" >");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_age_6_count+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>Above Six contracts</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_6_m_amount_cap/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_6_m_amount/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_6_1)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_6_2)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_6_3)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_6_4_5)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_6_6)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("</tr>");
			sum_1_month=m_age_6_1+m_age_4_5_1+m_age_3_1+m_age_2_1+m_age_1_m_amount;
			sum_2_month=m_age_6_2+m_age_4_5_2+m_age_3_2+m_age_2_2;
			sum_3_month=m_age_6_3+m_age_4_5_3+m_age_3_3;
			//sum_3_month=m_age_6_3+m_age_old_4_5_3+m_age_3_3;
			sum_4_5_month=m_age_6_4_5+m_age_4_5_4_5;
			//sum_4_5_month=m_age_6_6+m_age_4_5_4_5;
			sum_6_month=m_age_6_6;
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_nil)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_arrears)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>100%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(sum_1_month)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(sum_2_month)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(sum_3_month)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(sum_4_5_month)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(sum_6_month)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("</tr>");
			out.println("</table>");
			out.flush();
			
			m_cum_value=0;
			m_precentage=0;
			total_arrears=0;
			total_nil=0;
			total_arrears=m_age_0_m_amount_old+m_age_1_m_amount_old+m_age_2_m_amount_old+m_age_3_m_amount_old+m_age_4_5_m_amount_old+m_age_6_m_amount_old;
			total_nil=m_age_0_m_amount_cap_old+m_age_1_m_amount_cap_old+m_age_2_m_amount_cap_old+m_age_3_m_amount_cap_old+m_age_4_5_m_amount_cap_old+m_age_6_m_amount_cap_old;

	    
			
			out.println(" <br><br><br> ");
			
			out.println("<table border='0' width='100%' align='center' class='table'>"); 		
			out.println("<tr ><td width='*%' class='rep-body' align='center' ><b>"+m_orient_name.toUpperCase()+"</td></tr>");
			out.println("<tr ><td width='*%' class='rep-body' align='center' ><b>PORTFOLIO QUALITY STATEMENT AS AT "+m_Letter_date_2+"</td></tr>");
			out.println("</table>");	
      out.println("<br>");
						
			out.println("<table border='1' width='95%' align='center' class='table' cellspacing='0' >"); 		
			out.println("<tr bgcolor='lightblue'  ><td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Contracts</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Cum Contracts</td>");
			out.println("<td width='9%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Category</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Nil</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Arrears</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>1 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>2 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>3 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>4-5 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>>6 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Demand</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Months</td>");
			out.println("</tr>");
			
			m_cum_value+=m_age_0_count_old;
	    
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_age_0_count_old+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>Zero contracts</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_0_m_amount_cap_old/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>0%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("</tr>");
			m_precentage=(m_age_1_m_amount_old/total_arrears)*100;
			m_cum_value+=m_age_1_count_old;
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_age_1_count_old+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>One Month contracts</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_1_m_amount_cap_old/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_1_m_amount_old/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_1_m_amount_old)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("</tr>");
			m_cum_value+=m_age_2_count_old;
			m_precentage=(m_age_2_m_amount_old/total_arrears)*100;

			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_age_2_count_old+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>Two Month contracts</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_2_m_amount_cap_old/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_2_m_amount_old/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_old_2_1)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_old_2_2)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("</tr>");
			m_cum_value+=m_age_3_count_old;
			m_precentage=(m_age_3_m_amount_old/total_arrears)*100;
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_age_3_count_old+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>Three Month contracts</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_3_m_amount_cap_old/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_3_m_amount_old/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_old_3_1)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_old_3_2)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_old_3_3)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("</tr>");
			m_cum_value+=m_age_4_5_count_old;
			m_precentage=(m_age_4_5_m_amount_old/total_arrears)*100;
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_age_4_5_count_old+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>4 - 5 Month contracts</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_4_5_m_amount_cap_old/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_4_5_m_amount_old/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_old_4_5_1)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_old_4_5_2)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_old_4_5_3)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_old_4_5_4_5)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("</tr>");
			m_cum_value+=m_age_6_count_old;
			m_precentage=(m_age_6_m_amount_old/total_arrears)*100;
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_age_6_count_old+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>Above Six contracts</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_6_m_amount_cap_old/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_6_m_amount_old/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_old_6_1)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_old_6_2)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_old_6_3)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_old_6_4_5)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_old_6_6)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("</tr>");
			
			sum_1_month=0;
			sum_2_month=0;
			sum_3_month=0;
			sum_4_5_month=0;
			sum_6_month=0;

			sum_1_month=m_age_old_6_1+m_age_old_4_5_1+m_age_old_3_1+m_age_old_2_1+m_age_1_m_amount_old;
			sum_2_month=m_age_old_6_2+m_age_old_4_5_2+m_age_old_3_2+m_age_old_2_2;
			sum_3_month=m_age_old_6_3+m_age_old_4_5_3+m_age_old_3_3;
			sum_4_5_month=m_age_old_6_4_5+m_age_old_4_5_4_5;
			//sum_4_5_month=m_age_old_6_6+m_age_old_4_5_4_5;
			sum_6_month=m_age_old_6_6;
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_nil)+"</td>"); 
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_arrears)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>100%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(sum_1_month)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(sum_2_month)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(sum_3_month)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(sum_4_5_month)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(sum_6_month)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("</tr>");
			
			out.println("</table>");
			
			rs.close();				
			out.flush();
			
			/*
		 
			if(m_report_type.equals("N_LEG")){

			rs=stmt.executeQuery(
			" SELECT   "+ 
			" a.finance_no, "+ //1
			" a.client_code, "+ //2
			" a.client_full_name,  "+ //3
			//" ceil((NVL(a.total_rentals,0)- NVL(a.rentals_paid,0) )) age ,"+ //4
			" ROUND(a.age) ,"+ //4
			" a.total_amount-(nvl(a.settled_amount,0) + nvl(a.adjusted_amount,0)) arrears, "+ //5
			" (nvl(a.future_receivable,0) + nvl(a.arr_capital_portion,0) ) cap_out,  "+ //6
			//" ceil((NVL(a.total_rentals_prv_month,0)- NVL(a.rentals_paid_prv_month,0) )) age  ,"+ //7
			" ROUND(a.age_prv_month) ,"+ //7
			" a.total_amount_prv_month-(nvl(a.settled_amount_prv_month,0) + nvl(a.adjusted_amount_prv_month,0)) arrears_old, "+ //8
			" (nvl(a.future_receivable_prv_month,0) + nvl(a.arr_capital_portion_prv_month,0) ) cap_out_old  ,"+ //9
			" nvl(a.arrears_age_0,0) ,"+ //10
      " nvl(a.arrears_age_1,0) , "+ //11
			" nvl(a.arrears_age_2,0) , "+ //12
			" nvl(a.arrears_age_3,0) , "+ //13
      " nvl(a.arrears_age_4_5,0) , "+ //14
			" nvl(a.arrears_age_6,0) , "+ //15
			" nvl(a.arrears_age_0_old,0) , "+ //16
      " nvl(a.arrears_age_1_old,0) , "+ //17
			" nvl(a.arrears_age_2_old,0) ,  "+ //18
			" nvl(a.arrears_age_3_old,0) , "+ //19
      " nvl(a.arrears_age_4_5_old,0) ,  "+ //20
			" nvl(a.arrears_age_6_old,0) ,"+ //21
			//" ceil((NVL(a.total_rentals_prv_month2,0)- NVL(a.rentals_paid_prv_month2,0) )) age  ,"+ //22
			" ROUND(a.age_prv_month2) ,"+ //22
			" a.total_amount_prv_month2-(nvl(a.settled_amount_prv_month2,0) + nvl(a.adjusted_amount_prv_month2,0)) arrears_old, "+ //23
			" (nvl(a.future_receivable_prv_month2,0) + nvl(a.arr_capital_portion_prv_month2,0) ) cap_out_old  ,"+ //24
			" nvl(a.arrears_age_0_old_2,0) , "+ //25
      " nvl(a.arrears_age_1_old_2,0) , "+ //26
			" nvl(a.arrears_age_2_old_2,0) ,  "+ //27
			" nvl(a.arrears_age_3_old_2,0) , "+ //28
      " nvl(a.arrears_age_4_5_old_2,0) ,  "+ //29
			" nvl(a.arrears_age_6_old_2,0) "+ //30
			" FROM "+m_schema_name+".af_re_tbd_portfolio a "+
			" where a.ent_user='"+m_username+"' "+
			" and   DIVISION_CODE='"+m_division+"'  "+
			" and NVL(a.COLLECTION_OFFICER,'-')  <>'L001' "+//L001
			" and a.activate_date <=to_date('"+m_date_3+"','DD-MM-YYYY') " );
			
			}else if(m_report_type.equals("LEG")){
			
			rs=stmt.executeQuery(
			" SELECT   "+ 
			" a.finance_no, "+ //1
			" a.client_code, "+ //2
			" a.client_full_name,  "+ //3
			//" ceil((NVL(a.total_rentals,0)- NVL(a.rentals_paid,0) )) age ,"+ //4
			" ROUND(a.age) ,"+ //4
			" a.total_amount-(nvl(a.settled_amount,0) + nvl(a.adjusted_amount,0)) arrears, "+ //5
			" (nvl(a.future_receivable,0) + nvl(a.arr_capital_portion,0) ) cap_out,  "+ //6
			//" ceil((NVL(a.total_rentals_prv_month,0)- NVL(a.rentals_paid_prv_month,0) )) age  ,"+ //7
			" ROUND(a.age_prv_month) ,"+ //7
			" a.total_amount_prv_month-(nvl(a.settled_amount_prv_month,0) + nvl(a.adjusted_amount_prv_month,0)) arrears_old, "+ //8
			" (nvl(a.future_receivable_prv_month,0) + nvl(a.arr_capital_portion_prv_month,0) ) cap_out_old  ,"+ //9
			" nvl(a.arrears_age_0,0) ,"+ //10
      " nvl(a.arrears_age_1,0) , "+ //11
			" nvl(a.arrears_age_2,0) , "+ //12
			" nvl(a.arrears_age_3,0) , "+ //13
      " nvl(a.arrears_age_4_5,0) , "+ //14
			" nvl(a.arrears_age_6,0) , "+ //15
			" nvl(a.arrears_age_0_old,0) , "+ //16
      " nvl(a.arrears_age_1_old,0) , "+ //17
			" nvl(a.arrears_age_2_old,0) ,  "+ //18
			" nvl(a.arrears_age_3_old,0) , "+ //19
      " nvl(a.arrears_age_4_5_old,0) ,  "+ //20
			" nvl(a.arrears_age_6_old,0) ,"+ //21
			//" ceil((NVL(a.total_rentals_prv_month2,0)- NVL(a.rentals_paid_prv_month2,0) )) age  ,"+ //22
			" ROUND(a.age_prv_month2) ,"+ //22
			" a.total_amount_prv_month2-(nvl(a.settled_amount_prv_month2,0) + nvl(a.adjusted_amount_prv_month2,0)) arrears_old, "+ //23
			" (nvl(a.future_receivable_prv_month2,0) + nvl(a.arr_capital_portion_prv_month2,0) ) cap_out_old  ,"+ //24
			" nvl(a.arrears_age_0_old_2,0) , "+ //25
      " nvl(a.arrears_age_1_old_2,0) , "+ //26
			" nvl(a.arrears_age_2_old_2,0) ,  "+ //27
			" nvl(a.arrears_age_3_old_2,0) , "+ //28
      " nvl(a.arrears_age_4_5_old_2,0) ,  "+ //29
			" nvl(a.arrears_age_6_old_2,0) "+ //30
			" FROM "+m_schema_name+".af_re_tbd_portfolio a "+
			" where a.ent_user='"+m_username+"' "+
			" and   DIVISION_CODE='"+m_division+"'  "+
			" and NVL(a.COLLECTION_OFFICER,'-') ='L001' "+//L001
			" and a.activate_date <=to_date('"+m_date_3+"','DD-MM-YYYY') " );
			
			}
			more2=rs.next();

			if(more2){
			while(more2){
			m_age_old_2=rs.getInt(22);
			m_amount_old_2=rs.getDouble(23);
			m_amount_cap_old_2=rs.getDouble(24);
			
			if ( m_age_old_2 <=0 ){
			m_age_0_count_old_2+=1;
			m_age_0_m_amount_cap_old_2+=+m_amount_cap_old_2;
			}
			else if (m_age_old_2 ==1){
			m_age_1_count_old_2+=1;
			m_age_1_m_amount_old_2+=m_amount_old_2;
			m_age_1_m_amount_cap_old_2+=m_amount_cap_old_2;
			}
			else if (m_age_old_2 ==2){
			m_age_2_count_old_2+=1;
			m_age_2_m_amount_old_2+=m_amount_old_2;
			m_age_2_m_amount_cap_old_2+=m_amount_cap_old_2;
			m_age_old_2_2_1+=rs.getDouble(26)+rs.getDouble(25);
			m_age_old_2_2_2+=rs.getDouble(27)+rs.getDouble(28)+rs.getDouble(29)+rs.getDouble(30);
		  }
			else if (m_age_old_2 ==3){
			m_age_3_count_old_2+=1;
			m_age_3_m_amount_old_2+=m_amount_old_2;
			m_age_3_m_amount_cap_old_2+=m_amount_cap_old_2;
			m_age_old_2_3_1+=rs.getDouble(26)+rs.getDouble(25);
			m_age_old_2_3_2+=rs.getDouble(27);
			m_age_old_2_3_3+=rs.getDouble(28)+rs.getDouble(29)+rs.getDouble(30);
			}
			else if (m_age_old_2  > 3 && m_age_old_2 <=5){
			m_age_4_5_count_old_2+=1;
			m_age_4_5_m_amount_old_2+=m_amount_old_2;
			m_age_4_5_m_amount_cap_old_2+=m_amount_cap_old_2;
			m_age_old_2_4_5_1+=rs.getDouble(26)+rs.getDouble(25);
			m_age_old_2_4_5_2+=rs.getDouble(27);
			m_age_old_2_4_5_3+=rs.getDouble(28);
			m_age_old_2_4_5_4_5+=rs.getDouble(29)+rs.getDouble(30);
			}
			else if(m_age_old_2 >5 ){
		  m_age_6_count_old_2+=1;
			m_age_6_m_amount_old_2+=m_amount_old_2;
			m_age_6_m_amount_cap_old_2+=m_amount_cap_old_2;
		  m_age_old_2_6_1+=rs.getDouble(26)+rs.getDouble(25);
			m_age_old_2_6_2+=rs.getDouble(27);
			m_age_old_2_6_3+=rs.getDouble(28);
			m_age_old_2_6_4_5+=rs.getDouble(29);
			m_age_old_2_6_6+=rs.getDouble(30);
			}
		  more2=rs.next();
			}
			}
      
			

			
			
			m_cum_value=0;
			m_precentage=0;
			total_arrears=0;
			total_nil=0;
			total_arrears=m_age_0_m_amount_old_2+m_age_1_m_amount_old_2+m_age_2_m_amount_old_2+m_age_3_m_amount_old_2+m_age_4_5_m_amount_old_2+m_age_6_m_amount_old_2;
			total_nil=m_age_0_m_amount_cap_old_2+m_age_1_m_amount_cap_old_2+m_age_2_m_amount_cap_old_2+m_age_3_m_amount_cap_old_2+m_age_4_5_m_amount_cap_old_2+m_age_6_m_amount_cap_old_2;
			
			out.println(" <br><br><br> ");
			
			out.println("<table border='0' width='100%' align='center' class='table'>"); 		
			out.println("<tr ><td width='*%' class='rep-body' align='center' ><b>"+m_orient_name.toUpperCase()+"</td></tr>");
			out.println("<tr ><td width='*%' class='rep-body' align='center' ><b>PORTFOLIO QUALITY STATEMENT AS AT "+m_Letter_date_3+"</td></tr>");
			out.println("</table>");	
      out.println("<br>");
						
			out.println("<table border='1' width='95%' align='center' class='table' cellspacing='0' >"); 		
			out.println("<tr bgcolor='lightblue'  ><td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Contracts</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Cum Contracts</td>");
			out.println("<td width='9%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Category</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Nil</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Arrears</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>1 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>2 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>3 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>4-5 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>>6 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Demand</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Months</td>");
			out.println("</tr>");
			
			m_cum_value+=m_age_0_count_old_2;
	  	out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_age_0_count_old_2+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>Zero contracts</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_0_m_amount_cap_old_2/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");//"+nf.format(m_age_0_m_amount_old_2/to_million)+"
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>0%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("</tr>");
			
			m_cum_value+=m_age_1_count_old_2;
			m_precentage=(m_age_1_m_amount_old_2/total_arrears)*100;
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_age_1_count_old_2+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>One Month contracts</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_1_m_amount_cap_old_2/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_1_m_amount_old_2/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_1_m_amount_old_2)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("</tr>");
			
			m_cum_value+=m_age_2_count_old_2;
			m_precentage=(m_age_2_m_amount_old_2/total_arrears)*100;
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_age_2_count_old_2+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>Two Month contracts</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_2_m_amount_cap_old_2/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_2_m_amount_old_2/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_old_2_2_1)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_old_2_2_2)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("</tr>");
					
			m_cum_value+=m_age_3_count_old_2;
			m_precentage=(m_age_3_m_amount_old_2/total_arrears)*100;
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_age_3_count_old_2+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>Three Month contracts</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_3_m_amount_cap_old_2/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_3_m_amount_old_2/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_old_2_3_1)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_old_2_3_2)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_old_2_3_3)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("</tr>");
					
			
			m_cum_value+=m_age_4_5_count_old_2;
			m_precentage=(m_age_4_5_m_amount_old_2/total_arrears)*100;
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_age_4_5_count_old_2+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>4 - 5 Month contracts</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_4_5_m_amount_cap_old_2/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_4_5_m_amount_old_2/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_old_2_4_5_1)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_old_2_4_5_2)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_old_2_4_5_3)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_old_2_4_5_4_5)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("</tr>");
			
			
			m_cum_value+=m_age_6_count_old_2;
			m_precentage=(m_age_6_m_amount_old_2/total_arrears)*100;
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_age_6_count_old_2+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>Above Six contracts</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_6_m_amount_cap_old_2/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_6_m_amount_old_2/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_old_2_6_1)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_old_2_6_2)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_old_2_6_3)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_old_2_6_4_5)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_old_2_6_6)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("</tr>");
			
			sum_1_month=0;
			sum_2_month=0;
			sum_3_month=0;
			sum_4_5_month=0;
			sum_6_month=0;

			sum_1_month=m_age_old_2_6_1+m_age_old_2_4_5_1+m_age_old_2_3_1+m_age_old_2_2_1+m_age_1_m_amount_old_2;
			sum_2_month=m_age_old_2_6_2+m_age_old_2_4_5_2+m_age_old_2_3_2+m_age_old_2_2_2;
			sum_3_month=m_age_old_2_6_3+m_age_old_2_4_5_3+m_age_old_2_3_3;
			//sum_4_5_month=m_age_old_2_6_6+m_age_old_2_4_5_4_5;
			sum_4_5_month=m_age_old_2_6_4_5+m_age_old_2_4_5_4_5;
			sum_6_month=m_age_old_2_6_6;
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_nil)+"</td>"); 
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_arrears)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>100%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(sum_1_month)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(sum_2_month)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(sum_3_month)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(sum_4_5_month)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(sum_6_month)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("</tr>");
			
			out.println("</table>");
      
			*/
			

			out.println("</form>");	
			out.println("</body>");	
			out.println("</html>");	

			}
			
	  else if(m_chksql.equals("drill_down_details")){		
	  
		 double m_age=0;
		 String m_division="";
			String m_report_type="";
		 if(req.getParameter("age")!=null ){
	 	 m_age=Double.parseDouble(req.getParameter("age").trim());
		 }
     if(req.getParameter("division")!=null ){
	 	 m_division=req.getParameter("division").trim();
		 }
		 if(req.getParameter("report_type")!=null ){
	 	 m_report_type=req.getParameter("report_type").trim();
		 }
			
		 
			
			if(m_age ==6){
			
			if(m_report_type.equals("N_LEG")){
			
			rs=stmt.executeQuery(
			" SELECT   "+ 
			" a.finance_no, "+ //1
			" a.client_code, "+ //2
			" a.client_full_name,  "+ //3
			//" ceil((NVL(a.total_rentals,0)- NVL(a.rentals_paid,0) )) age ,"+ //4
			" ROUND(a.age) ,"+ //4
			" a.total_amount-(nvl(a.settled_amount,0) + nvl(a.adjusted_amount,0)) arrears, "+ //5
			" (nvl(a.future_receivable,0) + nvl(a.arr_capital_portion,0) ) cap_out,  "+ //6
			//" ceil((NVL(a.total_rentals_prv_month,0)- NVL(a.rentals_paid_prv_month,0) )) age  ,"+ //7
			" ROUND(a.age_prv_month) ,"+ //7
			" a.total_amount_prv_month-(nvl(a.settled_amount_prv_month,0) + nvl(a.adjusted_amount_prv_month,0)) arrears_old, "+ //8
			" (nvl(a.future_receivable_prv_month,0) + nvl(a.arr_capital_portion_prv_month,0) ) cap_out_old  ,"+ //9
			" nvl(a.arrears_age_0,0) ,"+ //10
      " nvl(a.arrears_age_1,0) , "+ //11
			" nvl(a.arrears_age_2,0) , "+ //12
			" nvl(a.arrears_age_3,0) , "+ //13
      " nvl(a.arrears_age_4_5,0) , "+ //14
			" nvl(a.arrears_age_6,0) , "+ //15
			" nvl(a.arrears_age_0_old,0) , "+ //16
      " nvl(a.arrears_age_1_old,0) , "+ //17
			" nvl(a.arrears_age_2_old,0) ,  "+ //18
			" nvl(a.arrears_age_3_old,0) , "+ //19
      " nvl(a.arrears_age_4_5_old,0) ,  "+ //20
			" nvl(a.arrears_age_6_old,0) "+ //21
			" FROM "+m_schema_name+".af_re_tbd_portfolio a "+
			" where a.ent_user='"+m_username+"' "+
			" and a.division_code='"+m_division+"' "+
		 // " AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) > 5 ");
			" and NVL(a.COLLECTION_OFFICER,'-') <>'L001' "+//L001
			" AND ROUND(a.age) >= 6 ");
			}
			else if(m_report_type.equals("LEG")){
			
			rs=stmt.executeQuery(
			" SELECT   "+ 
			" a.finance_no, "+ //1
			" a.client_code, "+ //2
			" a.client_full_name,  "+ //3
			//" ceil((NVL(a.total_rentals,0)- NVL(a.rentals_paid,0) )) age ,"+ //4
			" ROUND(a.age) ,"+ //4
			" a.total_amount-(nvl(a.settled_amount,0) + nvl(a.adjusted_amount,0)) arrears, "+ //5
			" (nvl(a.future_receivable,0) + nvl(a.arr_capital_portion,0) ) cap_out,  "+ //6
			//" ceil((NVL(a.total_rentals_prv_month,0)- NVL(a.rentals_paid_prv_month,0) )) age  ,"+ //7
			" ROUND(a.age_prv_month) ,"+ //7
			" a.total_amount_prv_month-(nvl(a.settled_amount_prv_month,0) + nvl(a.adjusted_amount_prv_month,0)) arrears_old, "+ //8
			" (nvl(a.future_receivable_prv_month,0) + nvl(a.arr_capital_portion_prv_month,0) ) cap_out_old  ,"+ //9
			" nvl(a.arrears_age_0,0) ,"+ //10
      " nvl(a.arrears_age_1,0) , "+ //11
			" nvl(a.arrears_age_2,0) , "+ //12
			" nvl(a.arrears_age_3,0) , "+ //13
      " nvl(a.arrears_age_4_5,0) , "+ //14
			" nvl(a.arrears_age_6,0) , "+ //15
			" nvl(a.arrears_age_0_old,0) , "+ //16
      " nvl(a.arrears_age_1_old,0) , "+ //17
			" nvl(a.arrears_age_2_old,0) ,  "+ //18
			" nvl(a.arrears_age_3_old,0) , "+ //19
      " nvl(a.arrears_age_4_5_old,0) ,  "+ //20
			" nvl(a.arrears_age_6_old,0) "+ //21
			" FROM "+m_schema_name+".af_re_tbd_portfolio a "+
			" where a.ent_user='"+m_username+"' "+
			" and a.division_code='"+m_division+"' "+
		 // " AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) > 5 ");
			" and NVL(a.COLLECTION_OFFICER,'-') ='L001' "+//L001
			" AND ROUND(a.age) >= 6 ");
			
			}
			
			}
			
			else if(m_age ==5){
			if(m_report_type.equals("N_LEG")){

			
			rs=stmt.executeQuery(
			" SELECT   "+ 
			" a.finance_no, "+ //1
			" a.client_code, "+ //2
			" a.client_full_name,  "+ //3
			//" ceil((NVL(a.total_rentals,0)- NVL(a.rentals_paid,0) )) age ,"+ //4
			" ROUND(a.age) ,"+
			" a.total_amount-(nvl(a.settled_amount,0) + nvl(a.adjusted_amount,0)) arrears, "+ //5
			" (nvl(a.future_receivable,0) + nvl(a.arr_capital_portion,0) ) cap_out,  "+ //6
			//" ceil((NVL(a.total_rentals_prv_month,0)- NVL(a.rentals_paid_prv_month,0) )) age  ,"+ //7
			" ROUND(a.age_prv_month) ,"+
			" a.total_amount_prv_month-(nvl(a.settled_amount_prv_month,0) + nvl(a.adjusted_amount_prv_month,0)) arrears_old, "+ //8
			" (nvl(a.future_receivable_prv_month,0) + nvl(a.arr_capital_portion_prv_month,0) ) cap_out_old  ,"+ //9
			" nvl(a.arrears_age_0,0) ,"+ //10
      " nvl(a.arrears_age_1,0) , "+ //11
			" nvl(a.arrears_age_2,0) , "+ //12
			" nvl(a.arrears_age_3,0) , "+ //13
      " nvl(a.arrears_age_4_5,0) , "+ //14
			" nvl(a.arrears_age_6,0) , "+ //15
			" nvl(a.arrears_age_0_old,0) , "+ //16
      " nvl(a.arrears_age_1_old,0) , "+ //17
			" nvl(a.arrears_age_2_old,0) ,  "+ //18
			" nvl(a.arrears_age_3_old,0) , "+ //19
      " nvl(a.arrears_age_4_5_old,0) ,  "+ //20
			" nvl(a.arrears_age_6_old,0) "+ //21
			" FROM "+m_schema_name+".af_re_tbd_portfolio a "+
			" where a.ent_user='"+m_username+"' "+
			//" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) > 3  "+
		  //" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) <= 5 ");
			" and a.division_code='"+m_division+"' "+
			" and NVL(a.COLLECTION_OFFICER,'-') <>'L001' "+//L001
			" AND ROUND(a.age) >= 4 "+
			" AND ROUND(a.age) <= 5 ");
			}
			else if(m_report_type.equals("LEG")){
			
			rs=stmt.executeQuery(
			" SELECT   "+ 
			" a.finance_no, "+ //1
			" a.client_code, "+ //2
			" a.client_full_name,  "+ //3
			//" ceil((NVL(a.total_rentals,0)- NVL(a.rentals_paid,0) )) age ,"+ //4
			" ROUND(a.age) ,"+
			" a.total_amount-(nvl(a.settled_amount,0) + nvl(a.adjusted_amount,0)) arrears, "+ //5
			" (nvl(a.future_receivable,0) + nvl(a.arr_capital_portion,0) ) cap_out,  "+ //6
			//" ceil((NVL(a.total_rentals_prv_month,0)- NVL(a.rentals_paid_prv_month,0) )) age  ,"+ //7
			" ROUND(a.age_prv_month) ,"+
			" a.total_amount_prv_month-(nvl(a.settled_amount_prv_month,0) + nvl(a.adjusted_amount_prv_month,0)) arrears_old, "+ //8
			" (nvl(a.future_receivable_prv_month,0) + nvl(a.arr_capital_portion_prv_month,0) ) cap_out_old  ,"+ //9
			" nvl(a.arrears_age_0,0) ,"+ //10
      " nvl(a.arrears_age_1,0) , "+ //11
			" nvl(a.arrears_age_2,0) , "+ //12
			" nvl(a.arrears_age_3,0) , "+ //13
      " nvl(a.arrears_age_4_5,0) , "+ //14
			" nvl(a.arrears_age_6,0) , "+ //15
			" nvl(a.arrears_age_0_old,0) , "+ //16
      " nvl(a.arrears_age_1_old,0) , "+ //17
			" nvl(a.arrears_age_2_old,0) ,  "+ //18
			" nvl(a.arrears_age_3_old,0) , "+ //19
      " nvl(a.arrears_age_4_5_old,0) ,  "+ //20
			" nvl(a.arrears_age_6_old,0) "+ //21
			" FROM "+m_schema_name+".af_re_tbd_portfolio a "+
			" where a.ent_user='"+m_username+"' "+
			//" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) > 3  "+
		  //" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) <= 5 ");
			" and a.division_code='"+m_division+"' "+
			" and NVL(a.COLLECTION_OFFICER,'-') ='L001' "+//L001
			" AND ROUND(a.age) >= 4 "+
			" AND ROUND(a.age) <= 5 ");
			
			}

			
			}
			
			else if(m_age ==3){
			
			if(m_report_type.equals("N_LEG")){

			
			rs=stmt.executeQuery(
			" SELECT   "+ 
			" a.finance_no, "+ //1
			" a.client_code, "+ //2
			" a.client_full_name,  "+ //3
			//" ceil((NVL(a.total_rentals,0)- NVL(a.rentals_paid,0) )) age ,"+ //4
			" ROUND(a.age) ,"+
			" a.total_amount-(nvl(a.settled_amount,0) + nvl(a.adjusted_amount,0)) arrears, "+ //5
			" (nvl(a.future_receivable,0) + nvl(a.arr_capital_portion,0) ) cap_out,  "+ //6
			//" ceil((NVL(a.total_rentals_prv_month,0)- NVL(a.rentals_paid_prv_month,0) )) age  ,"+ //7
			" ROUND(a.age_prv_month) ,"+
			" a.total_amount_prv_month-(nvl(a.settled_amount_prv_month,0) + nvl(a.adjusted_amount_prv_month,0)) arrears_old, "+ //8
			" (nvl(a.future_receivable_prv_month,0) + nvl(a.arr_capital_portion_prv_month,0) ) cap_out_old  ,"+ //9
			" nvl(a.arrears_age_0,0) ,"+ //10
      " nvl(a.arrears_age_1,0) , "+ //11
			" nvl(a.arrears_age_2,0) , "+ //12
			" nvl(a.arrears_age_3,0) , "+ //13
      " nvl(a.arrears_age_4_5,0) , "+ //14
			" nvl(a.arrears_age_6,0) , "+ //15
			" nvl(a.arrears_age_0_old,0) , "+ //16
      " nvl(a.arrears_age_1_old,0) , "+ //17
			" nvl(a.arrears_age_2_old,0) ,  "+ //18
			" nvl(a.arrears_age_3_old,0) , "+ //19
      " nvl(a.arrears_age_4_5_old,0) ,  "+ //20
			" nvl(a.arrears_age_6_old,0) "+ //21
			" FROM "+m_schema_name+".af_re_tbd_portfolio a "+
			" where a.ent_user='"+m_username+"' "+
			//" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) > 2  "+
		  //" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) <= 3 ");
			//" AND ROUND(a.age) >= 2 "+
			" and a.division_code='"+m_division+"' "+
			" and NVL(a.COLLECTION_OFFICER,'-') <>'L001' "+//L001
			" AND ROUND(a.age) = 3 ");
			}
			else if(m_report_type.equals("LEG")){
			
			rs=stmt.executeQuery(
			" SELECT   "+ 
			" a.finance_no, "+ //1
			" a.client_code, "+ //2
			" a.client_full_name,  "+ //3
			//" ceil((NVL(a.total_rentals,0)- NVL(a.rentals_paid,0) )) age ,"+ //4
			" ROUND(a.age) ,"+
			" a.total_amount-(nvl(a.settled_amount,0) + nvl(a.adjusted_amount,0)) arrears, "+ //5
			" (nvl(a.future_receivable,0) + nvl(a.arr_capital_portion,0) ) cap_out,  "+ //6
			//" ceil((NVL(a.total_rentals_prv_month,0)- NVL(a.rentals_paid_prv_month,0) )) age  ,"+ //7
			" ROUND(a.age_prv_month) ,"+
			" a.total_amount_prv_month-(nvl(a.settled_amount_prv_month,0) + nvl(a.adjusted_amount_prv_month,0)) arrears_old, "+ //8
			" (nvl(a.future_receivable_prv_month,0) + nvl(a.arr_capital_portion_prv_month,0) ) cap_out_old  ,"+ //9
			" nvl(a.arrears_age_0,0) ,"+ //10
      " nvl(a.arrears_age_1,0) , "+ //11
			" nvl(a.arrears_age_2,0) , "+ //12
			" nvl(a.arrears_age_3,0) , "+ //13
      " nvl(a.arrears_age_4_5,0) , "+ //14
			" nvl(a.arrears_age_6,0) , "+ //15
			" nvl(a.arrears_age_0_old,0) , "+ //16
      " nvl(a.arrears_age_1_old,0) , "+ //17
			" nvl(a.arrears_age_2_old,0) ,  "+ //18
			" nvl(a.arrears_age_3_old,0) , "+ //19
      " nvl(a.arrears_age_4_5_old,0) ,  "+ //20
			" nvl(a.arrears_age_6_old,0) "+ //21
			" FROM "+m_schema_name+".af_re_tbd_portfolio a "+
			" where a.ent_user='"+m_username+"' "+
			//" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) > 2  "+
		  //" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) <= 3 ");
			//" AND ROUND(a.age) >= 2 "+
			" and a.division_code='"+m_division+"' "+
			" and NVL(a.COLLECTION_OFFICER,'-') ='L001' "+//L001
			" AND ROUND(a.age) = 3 ");
			
      }
			
			
			
			}
			
			else if(m_age ==2){
			
			if(m_report_type.equals("N_LEG")){

			
			rs=stmt.executeQuery(
			" SELECT   "+ 
			" a.finance_no, "+ //1
			" a.client_code, "+ //2
			" a.client_full_name,  "+ //3
			//" ceil((NVL(a.total_rentals,0)- NVL(a.rentals_paid,0) )) age ,"+ //4
			" ROUND(a.age) ,"+
			" a.total_amount-(nvl(a.settled_amount,0) + nvl(a.adjusted_amount,0)) arrears, "+ //5
			" (nvl(a.future_receivable,0) + nvl(a.arr_capital_portion,0) ) cap_out,  "+ //6
			//" ceil((NVL(a.total_rentals_prv_month,0)- NVL(a.rentals_paid_prv_month,0) )) age  ,"+ //7
			" ROUND(a.age_prv_month) ,"+
			" a.total_amount_prv_month-(nvl(a.settled_amount_prv_month,0) + nvl(a.adjusted_amount_prv_month,0)) arrears_old, "+ //8
			" (nvl(a.future_receivable_prv_month,0) + nvl(a.arr_capital_portion_prv_month,0) ) cap_out_old  ,"+ //9
			" nvl(a.arrears_age_0,0) ,"+ //10
      " nvl(a.arrears_age_1,0) , "+ //11
			" nvl(a.arrears_age_2,0) , "+ //12
			" nvl(a.arrears_age_3,0) , "+ //13
      " nvl(a.arrears_age_4_5,0) , "+ //14
			" nvl(a.arrears_age_6,0) , "+ //15
			" nvl(a.arrears_age_0_old,0) , "+ //16
      " nvl(a.arrears_age_1_old,0) , "+ //17
			" nvl(a.arrears_age_2_old,0) ,  "+ //18
			" nvl(a.arrears_age_3_old,0) , "+ //19
      " nvl(a.arrears_age_4_5_old,0) ,  "+ //20
			" nvl(a.arrears_age_6_old,0) "+ //21
			" FROM "+m_schema_name+".af_re_tbd_portfolio a "+
			" where a.ent_user='"+m_username+"' "+
			//" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) > 1  "+
		  //" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) <= 2 ");
			" and a.division_code='"+m_division+"' "+
			" and NVL(a.COLLECTION_OFFICER,'-') <>'L001' "+//L001
			" AND ROUND(a.age) = 2 ");
			}
			else if(m_report_type.equals("LEG")){
			
			rs=stmt.executeQuery(
			" SELECT   "+ 
			" a.finance_no, "+ //1
			" a.client_code, "+ //2
			" a.client_full_name,  "+ //3
			//" ceil((NVL(a.total_rentals,0)- NVL(a.rentals_paid,0) )) age ,"+ //4
			" ROUND(a.age) ,"+
			" a.total_amount-(nvl(a.settled_amount,0) + nvl(a.adjusted_amount,0)) arrears, "+ //5
			" (nvl(a.future_receivable,0) + nvl(a.arr_capital_portion,0) ) cap_out,  "+ //6
			//" ceil((NVL(a.total_rentals_prv_month,0)- NVL(a.rentals_paid_prv_month,0) )) age  ,"+ //7
			" ROUND(a.age_prv_month) ,"+
			" a.total_amount_prv_month-(nvl(a.settled_amount_prv_month,0) + nvl(a.adjusted_amount_prv_month,0)) arrears_old, "+ //8
			" (nvl(a.future_receivable_prv_month,0) + nvl(a.arr_capital_portion_prv_month,0) ) cap_out_old  ,"+ //9
			" nvl(a.arrears_age_0,0) ,"+ //10
      " nvl(a.arrears_age_1,0) , "+ //11
			" nvl(a.arrears_age_2,0) , "+ //12
			" nvl(a.arrears_age_3,0) , "+ //13
      " nvl(a.arrears_age_4_5,0) , "+ //14
			" nvl(a.arrears_age_6,0) , "+ //15
			" nvl(a.arrears_age_0_old,0) , "+ //16
      " nvl(a.arrears_age_1_old,0) , "+ //17
			" nvl(a.arrears_age_2_old,0) ,  "+ //18
			" nvl(a.arrears_age_3_old,0) , "+ //19
      " nvl(a.arrears_age_4_5_old,0) ,  "+ //20
			" nvl(a.arrears_age_6_old,0) "+ //21
			" FROM "+m_schema_name+".af_re_tbd_portfolio a "+
			" where a.ent_user='"+m_username+"' "+
			//" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) > 1  "+
		  //" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) <= 2 ");
			" and a.division_code='"+m_division+"' "+
			" and NVL(a.COLLECTION_OFFICER,'-') ='L001' "+//L001
			" AND ROUND(a.age) = 2 ");
			}

			
			
			
			}
			else if(m_age ==1){
			
			if(m_report_type.equals("N_LEG")){
			
			rs=stmt.executeQuery(
			" SELECT   "+ 
			" a.finance_no, "+ //1
			" a.client_code, "+ //2
			" a.client_full_name,  "+ //3
			//" ceil((NVL(a.total_rentals,0)- NVL(a.rentals_paid,0) )) age ,"+ //4
			" ROUND(a.age) ,"+
			" a.total_amount-(nvl(a.settled_amount,0) + nvl(a.adjusted_amount,0)) arrears, "+ //5
			" (nvl(a.future_receivable,0) + nvl(a.arr_capital_portion,0) ) cap_out,  "+ //6
			//" ceil((NVL(a.total_rentals_prv_month,0)- NVL(a.rentals_paid_prv_month,0) )) age  ,"+ //7
			" ROUND(a.age_prv_month) ,"+
			" a.total_amount_prv_month-(nvl(a.settled_amount_prv_month,0) + nvl(a.adjusted_amount_prv_month,0)) arrears_old, "+ //8
			" (nvl(a.future_receivable_prv_month,0) + nvl(a.arr_capital_portion_prv_month,0) ) cap_out_old  ,"+ //9
			" nvl(a.arrears_age_0,0) ,"+ //10
      " nvl(a.arrears_age_1,0) , "+ //11
			" nvl(a.arrears_age_2,0) , "+ //12
			" nvl(a.arrears_age_3,0) , "+ //13
      " nvl(a.arrears_age_4_5,0) , "+ //14
			" nvl(a.arrears_age_6,0) , "+ //15
			" nvl(a.arrears_age_0_old,0) , "+ //16
      " nvl(a.arrears_age_1_old,0) , "+ //17
			" nvl(a.arrears_age_2_old,0) ,  "+ //18
			" nvl(a.arrears_age_3_old,0) , "+ //19
      " nvl(a.arrears_age_4_5_old,0) ,  "+ //20
			" nvl(a.arrears_age_6_old,0) "+ //21
			" FROM "+m_schema_name+".af_re_tbd_portfolio a "+
			" where a.ent_user='"+m_username+"' "+
			//" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) > 0  "+
		  //" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) <= 1 ");
			" and a.division_code='"+m_division+"' "+
			" and NVL(a.COLLECTION_OFFICER,'-') <>'L001' "+//L001
			" AND ROUND(a.age) = 1 ");
			}
			else if(m_report_type.equals("LEG")){
			
			rs=stmt.executeQuery(
			" SELECT   "+ 
			" a.finance_no, "+ //1
			" a.client_code, "+ //2
			" a.client_full_name,  "+ //3
			//" ceil((NVL(a.total_rentals,0)- NVL(a.rentals_paid,0) )) age ,"+ //4
			" ROUND(a.age) ,"+
			" a.total_amount-(nvl(a.settled_amount,0) + nvl(a.adjusted_amount,0)) arrears, "+ //5
			" (nvl(a.future_receivable,0) + nvl(a.arr_capital_portion,0) ) cap_out,  "+ //6
			//" ceil((NVL(a.total_rentals_prv_month,0)- NVL(a.rentals_paid_prv_month,0) )) age  ,"+ //7
			" ROUND(a.age_prv_month) ,"+
			" a.total_amount_prv_month-(nvl(a.settled_amount_prv_month,0) + nvl(a.adjusted_amount_prv_month,0)) arrears_old, "+ //8
			" (nvl(a.future_receivable_prv_month,0) + nvl(a.arr_capital_portion_prv_month,0) ) cap_out_old  ,"+ //9
			" nvl(a.arrears_age_0,0) ,"+ //10
      " nvl(a.arrears_age_1,0) , "+ //11
			" nvl(a.arrears_age_2,0) , "+ //12
			" nvl(a.arrears_age_3,0) , "+ //13
      " nvl(a.arrears_age_4_5,0) , "+ //14
			" nvl(a.arrears_age_6,0) , "+ //15
			" nvl(a.arrears_age_0_old,0) , "+ //16
      " nvl(a.arrears_age_1_old,0) , "+ //17
			" nvl(a.arrears_age_2_old,0) ,  "+ //18
			" nvl(a.arrears_age_3_old,0) , "+ //19
      " nvl(a.arrears_age_4_5_old,0) ,  "+ //20
			" nvl(a.arrears_age_6_old,0) "+ //21
			" FROM "+m_schema_name+".af_re_tbd_portfolio a "+
			" where a.ent_user='"+m_username+"' "+
			//" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) > 0  "+
		  //" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) <= 1 ");
			" and a.division_code='"+m_division+"' "+
			" and NVL(a.COLLECTION_OFFICER,'-') ='L001' "+//L001
			" AND ROUND(a.age) = 1 ");
			
			}
			

			}
			
			else if(m_age ==0){
			
			if(m_report_type.equals("N_LEG")){
			
			rs=stmt.executeQuery(
			" SELECT   "+ 
			" a.finance_no, "+ //1
			" a.client_code, "+ //2
			" a.client_full_name,  "+ //3
			//" ceil((NVL(a.total_rentals,0)- NVL(a.rentals_paid,0) )) age ,"+ //4
			" ROUND(a.age) ,"+
			" a.total_amount-(nvl(a.settled_amount,0) + nvl(a.adjusted_amount,0)) arrears, "+ //5
			" (nvl(a.future_receivable,0) + nvl(a.arr_capital_portion,0) ) cap_out,  "+ //6
			//" ceil((NVL(a.total_rentals_prv_month,0)- NVL(a.rentals_paid_prv_month,0) )) age  ,"+ //7
			" ROUND(a.age_prv_month) ,"+
			" a.total_amount_prv_month-(nvl(a.settled_amount_prv_month,0) + nvl(a.adjusted_amount_prv_month,0)) arrears_old, "+ //8
			" (nvl(a.future_receivable_prv_month,0) + nvl(a.arr_capital_portion_prv_month,0) ) cap_out_old  ,"+ //9
			" nvl(a.arrears_age_0,0) ,"+ //10
      " nvl(a.arrears_age_1,0) , "+ //11
			" nvl(a.arrears_age_2,0) , "+ //12
			" nvl(a.arrears_age_3,0) , "+ //13
      " nvl(a.arrears_age_4_5,0) , "+ //14
			" nvl(a.arrears_age_6,0) , "+ //15
			" nvl(a.arrears_age_0_old,0) , "+ //16
      " nvl(a.arrears_age_1_old,0) , "+ //17
			" nvl(a.arrears_age_2_old,0) ,  "+ //18
			" nvl(a.arrears_age_3_old,0) , "+ //19
      " nvl(a.arrears_age_4_5_old,0) ,  "+ //20
			" nvl(a.arrears_age_6_old,0) "+ //21
			" FROM "+m_schema_name+".af_re_tbd_portfolio a "+
			" where a.ent_user='"+m_username+"' "+
			//" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) > 0  "+
		  //" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) <= 1 ");
			" and a.division_code='"+m_division+"' "+
			" and NVL(a.COLLECTION_OFFICER,'-') <>'L001' "+//L001
			" AND ROUND(a.age) = 0 ");
			}
			else if(m_report_type.equals("LEG")){
			
			rs=stmt.executeQuery(
			" SELECT   "+ 
			" a.finance_no, "+ //1
			" a.client_code, "+ //2
			" a.client_full_name,  "+ //3
			//" ceil((NVL(a.total_rentals,0)- NVL(a.rentals_paid,0) )) age ,"+ //4
			" ROUND(a.age) ,"+
			" a.total_amount-(nvl(a.settled_amount,0) + nvl(a.adjusted_amount,0)) arrears, "+ //5
			" (nvl(a.future_receivable,0) + nvl(a.arr_capital_portion,0) ) cap_out,  "+ //6
			//" ceil((NVL(a.total_rentals_prv_month,0)- NVL(a.rentals_paid_prv_month,0) )) age  ,"+ //7
			" ROUND(a.age_prv_month) ,"+
			" a.total_amount_prv_month-(nvl(a.settled_amount_prv_month,0) + nvl(a.adjusted_amount_prv_month,0)) arrears_old, "+ //8
			" (nvl(a.future_receivable_prv_month,0) + nvl(a.arr_capital_portion_prv_month,0) ) cap_out_old  ,"+ //9
			" nvl(a.arrears_age_0,0) ,"+ //10
      " nvl(a.arrears_age_1,0) , "+ //11
			" nvl(a.arrears_age_2,0) , "+ //12
			" nvl(a.arrears_age_3,0) , "+ //13
      " nvl(a.arrears_age_4_5,0) , "+ //14
			" nvl(a.arrears_age_6,0) , "+ //15
			" nvl(a.arrears_age_0_old,0) , "+ //16
      " nvl(a.arrears_age_1_old,0) , "+ //17
			" nvl(a.arrears_age_2_old,0) ,  "+ //18
			" nvl(a.arrears_age_3_old,0) , "+ //19
      " nvl(a.arrears_age_4_5_old,0) ,  "+ //20
			" nvl(a.arrears_age_6_old,0) "+ //21
			" FROM "+m_schema_name+".af_re_tbd_portfolio a "+
			" where a.ent_user='"+m_username+"' "+
			//" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) > 0  "+
		  //" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) <= 1 ");
			" and a.division_code='"+m_division+"' "+
			" and NVL(a.COLLECTION_OFFICER,'-') ='L001' "+//L001
			" AND ROUND(a.age) = 0 ");
			}

			

			}
			
			
			
			
			
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection - PortFolio Quality Statement</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
			out.println("<br>");	
			out.println("<br>");	
			
			out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
			out.println("<tr class=tr_input1>");
			//out.println("<td width=\"*%\" align=center  ><u><b>Daily Collection Movement as at "+report_date+" </u></td>"); 
			out.println("</tr >");
			out.println("</table>");			
			out.println("<br>");		
			
			
			
				out.println("<table width='80%' class='table' border='1'  align='center' cellspacing='0' cellspacing='1' >");
				out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\">");
				out.println("<td width='2%' ><DIV class=factoring-letter-body><b>No<DIV></td>");//2
				out.println("<td width='10%' ><DIV class=factoring-letter-body><b>Finance No<DIV></td>");//2
				out.println("<td width='20%' ><DIV class=factoring-letter-body><b>Client Name</b></DIV></td>");//3
				out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>Total Outstanding</b></DIV></td>");//5
				out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>Arreas Amt</b></DIV></td>");//6
				out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>Arreas Age</b></DIV></td>");//7
				out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>No Arrears</b></DIV></td>");//8
				out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>Age 1</b></DIV></td>");//9
				out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>Age 2</b></DIV></td>");//10
				out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>Age 3</b></DIV></td>");//11
				out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>Age 4 - 5</b></DIV></td>");//12
				out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>Age 6 ></b></DIV></td>");//13
				out.println("</tr>");
			
			more=rs.next();
			
			int row=0;
		  int j=1;
			int i=1;
			while(more){
		  out.println("<tr   >");
			out.println("<td width=\"2%\" class=factoring-letter-body align=left>"+i+"</td>"); 
			out.println("<td width=\"10%\" class=factoring-letter-body align=left>"+rs.getString(1)+"</td>"); 
			out.println("<td width=\"20%\" class=factoring-letter-body align=left>"+rs.getString(3)+"</td>"); 
			out.println("<td width=\"10%\" class=factoring-letter-body align=left>"+rs.getDouble(6)+"</td>"); 
			out.println("<td width=\"10%\" class=factoring-letter-body align=left>"+rs.getDouble(5)+"</td>"); 
			out.println("<td width=\"10%\" class=factoring-letter-body align=left>"+rs.getDouble(4)+"</td>"); 
			out.println("<td width=\"10%\" class=factoring-letter-body align=left>"+rs.getDouble(10)+"</td>"); 
			out.println("<td width=\"10%\" class=factoring-letter-body align=left>"+rs.getDouble(11)+"</td>"); 
			out.println("<td width=\"10%\" class=factoring-letter-body align=left>"+rs.getDouble(12)+"</td>"); 
			out.println("<td width=\"10%\" class=factoring-letter-body align=left>"+rs.getDouble(13)+"</td>"); 
			out.println("<td width=\"10%\" class=factoring-letter-body align=left>"+rs.getDouble(14)+"</td>"); 
			out.println("<td width=\"10%\" class=factoring-letter-body align=left>"+rs.getDouble(15)+"</td>"); 
			out.println("</tr >"); 
			more=rs.next();
			i+=1;
			}
									
		out.println("</table >"); 
    out.println("</table >"); 
		out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
		out.println("</body>");
		out.println("</html>");

			
			

			
			
      }
			
			/*else if(m_chksql.trim().equals("print_report")){	
			
			out.println("<html><head>"); 
			out.println("<title>Portfolio Quality Statement</title></head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			
			out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"\">");//add_button()
			//out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo.gif\"></DIV><br><hr>");
			out.println("<body bgcolor='white'><br>");
			
			out.println("<form name='Form1'>");
       	
			//&&&&&&&&&&&&&&&&&&&&&&new main report &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
			out.println("<font size=2><p style='text-align:justify' class='rep-body'>");	
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr ><td width='*%' class='rep-body' align='center' ><b><u>"+m_orient_name.toUpperCase()+"</u></td></tr>");
			//out.println("<tr ><td width='*%' class='rep-body' align='center' ><b>PORTFOLIO QUALITY STATEMENT AS AT "+m_Letter_date+"</td></tr>");
			out.println("</table>");	
			out.println("<br><br>");
			
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr ><td width='20%' class='rep-body' align='left' >To</td>");
			out.println("<td width='*%' class='rep-body' align='left' >The Board of Directors</td></tr>");
			
			out.println("<tr ><td width='20%' class='rep-body' align='left' >Through</td>");
			out.println("<td width='*%' class='rep-body' align='left' >Director/CEO</td></tr>");
			
			out.println("<tr ><td width='20%' class='rep-body' align='left' >From</td>");
			out.println("<td width='*%' class='rep-body' align='left' >AGM - Recoveries</td></tr>");
			
			out.println("<tr ><td width='20%' class='rep-body' align='left' >Subject</td>");
			out.println("<td width='*%' class='rep-body' align='left' >Portfolio Quality</td></tr>");
			
			out.println("</table>");	
			
			out.println("<br><br>");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr ><td width='*%' class='rep-body' align='center' ><b>PORTFOLIO QUALITY STATEMENT AS AT "+m_Letter_date+"</td></tr>");
			out.println("</table>");	

			
      out.println("<br><br>");
      out.println("<table width=\"100%\"  border=\"1\" align='center' cellspacing=\"0\" > "); 
			out.println("<tr class=pdn_txtpos2> ");
			out.println("<td width=\"16%\" STYLE='{font:  8pt bold arial; text-align:center;}'>&nbsp;</td> ");
			out.println("<td colspan=\"2\" STYLE='{font:  8pt bold arial; text-align:center;}'><b>No Arrears </td> ");
			out.println("<td colspan=\"2\" STYLE='{font:  8pt bold arial; text-align:center;}'><b>1 Months Arrears </td> ");
			out.println("<td colspan=\"2\" STYLE='{font:  8pt bold arial; text-align:center;}'><b>2 Months Arrears </td> ");
			out.println("<td colspan=\"2\" STYLE='{font:  8pt bold arial; text-align:center;}'><b>3 Months Arrears </td> ");
			out.println("<td colspan=\"2\" STYLE='{font:  8pt bold arial; text-align:center;}'><b>4-5 Months Arrears </td> ");
			out.println("<td colspan=\"2\" STYLE='{font:  8pt bold arial; text-align:center;}'> <b>>=6 Months Arrears </td> ");
			out.println(" </tr>  ");
			out.println(" <tr class=pdn_txtpos2> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' >&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' width=\"6%\"><b>No</td> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' width=\"8%\"><b>Capital O/S</td> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' width=\"6%\"><b>No</td> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' width=\"8%\"><b>Capital O/S</td> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' width=\"6%\"><b>No</td> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' width=\"8%\"><b>Capital O/S</td> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' width=\"6%\"><b>No</td> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' width=\"8%\"><b>Capital O/S</td> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' width=\"6%\"><b>No</td> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' width=\"8%\"><b>Capital O/S</td> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' width=\"6%\"><b>No</td> ");
			out.println("  <td STYLE='{font:  8pt bold arial; text-align:center;}' width=\"8%\"><b>Capital O/S</td> ");
			out.println(" </tr> ");
			
			String sql_query=" SELECT a.application_no, "+
											" floor(a.total_rentals-a.rentals_paid), "+
											" a.total_amount- a.settled_amount, "+
											" floor(a.total_rentals_prv_month- a.rentals_paid_prv_month), "+
											" a.total_amount_prv_month- a.settled_amount_prv_month "+
											" FROM af_re_tbd_portfolio a ";
		  
			rs2 = stmt2.executeQuery(sql_query);
		  boolean	more2=rs2.next();
			int m_age=0;
			int m_age_0_count=0,m_age_1_count=0,m_age_2_count=0,m_age_3_count=0,m_age_4_count=0,m_age_5_count=0,m_age_6_count=0;
			double m_amount=0,m_age_0_m_amount=0,m_age_1_m_amount=0,m_age_2_m_amount=0,m_age_3_m_amount=0,m_age_5_m_amount=0,m_age_6_m_amount=0;
			while(more2){
			m_age=rs2.getInt(2);
			m_amount=rs2.getDouble(3);
			if (m_age==0){
			m_age_0_count=m_age_0_count+1;
			m_age_0_m_amount=m_age_0_m_amount+m_amount;
			}
			else if (m_age==1){
			m_age_1_count=m_age_1_count+1;
			m_age_1_m_amount=m_age_1_m_amount+m_amount;
			}
			else if (m_age==2){
			m_age_2_count=m_age_2_count+1;
			m_age_2_m_amount=m_age_2_m_amount+m_amount;
			}
			else if (m_age==3){
			m_age_3_count=m_age_3_count+1;
			m_age_3_m_amount=m_age_3_m_amount+m_amount;
			}
			else if (m_age>3 &&  m_age<=5){
			m_age_5_count=m_age_5_count+1;
			m_age_5_m_amount=m_age_5_m_amount+m_amount;
			}
			else if(m_age>=6 ){
		  m_age_6_count=m_age_6_count+1;
			m_age_6_m_amount=m_age_6_m_amount+m_amount;

			}
			
		  more2=rs2.next();

			}
			
			
			
			out.println(" <tr> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:left;}'>Balance B/F</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+m_age_0_count+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_0_m_amount/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+m_age_1_count+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_1_m_amount/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+m_age_2_count+"</td> "); 
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_2_m_amount/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+m_age_3_count+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_3_m_amount/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+m_age_5_count+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_5_m_amount/to_million)+"</td> ");  
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+m_age_6_count+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_6_m_amount/to_million)+"</td> ");
			out.println(" </tr> ");
			
			
		  
			rs2 = stmt2.executeQuery(sql_query);
		  more2=rs2.next();
			int p_m_age_0_count=0,p_m_age_1_count=0,p_m_age_2_count=0,p_m_age_3_count=0,p_m_age_4_count=0,p_m_age_5_count=0,p_m_age_6_count=0;
			double p_m_age_0_m_amount=0,p_m_age_1_m_amount=0,p_m_age_2_m_amount=0,p_m_age_3_m_amount=0,p_m_age_5_m_amount=0,p_m_age_6_m_amount=0;
			while(more2){
			m_age=rs2.getInt(4);
			m_amount=rs2.getDouble(5);
			if (m_age==0){
			p_m_age_0_count=p_m_age_0_count+1;
			p_m_age_0_m_amount=p_m_age_0_m_amount+m_amount;
			}
			else if (m_age==1){
			p_m_age_1_count=p_m_age_1_count+1;
			p_m_age_1_m_amount=p_m_age_1_m_amount+m_amount;
			}
			else if (m_age==2){
			p_m_age_2_count=p_m_age_2_count+1;
			p_m_age_2_m_amount=p_m_age_2_m_amount+m_amount;
			}
			else if (m_age==3){
			p_m_age_3_count=p_m_age_3_count+1;
			p_m_age_3_m_amount=p_m_age_3_m_amount+m_amount;
			}
			else if (m_age>3 &&  m_age<=5){
			p_m_age_5_count=p_m_age_5_count+1;
			p_m_age_5_m_amount=p_m_age_5_m_amount+m_amount;
			}
			else if(m_age>=6 ){
		  p_m_age_6_count=p_m_age_6_count+1;
			p_m_age_6_m_amount=p_m_age_6_m_amount+m_amount;
			}
			
		  more2=rs2.next();

			}
			
			out.println(" <tr> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:left;}'>Balance B/F</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+p_m_age_0_count+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(p_m_age_0_m_amount/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+p_m_age_1_count+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(p_m_age_1_m_amount/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+p_m_age_2_count+"</td> "); 
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(p_m_age_2_m_amount/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+p_m_age_3_count+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(p_m_age_3_m_amount/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+p_m_age_5_count+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(p_m_age_5_m_amount/to_million)+"</td> ");  
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+p_m_age_6_count+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(p_m_age_6_m_amount/to_million)+"</td> ");
			out.println(" </tr> ");
			
			
			out.println(" <tr> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:left;}'>Balance B/F</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+p_m_age_0_count+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(p_m_age_0_m_amount/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+p_m_age_1_count+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(p_m_age_1_m_amount/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+p_m_age_2_count+"</td> "); 
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(p_m_age_2_m_amount/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+p_m_age_3_count+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(p_m_age_3_m_amount/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+p_m_age_5_count+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(p_m_age_5_m_amount/to_million)+"</td> ");  
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+p_m_age_6_count+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(p_m_age_6_m_amount/to_million)+"</td> ");
			out.println(" </tr> ");

			
			out.println(" <tr> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:left;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> "); 
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");  
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println(" </tr> ");
			
			/*
			out.println(" <tr> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:left;}'>Arrears contracts as % of total reveivalbe</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_nil/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((rs.getDouble(31)/total_nil)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((rs.getDouble(2)/total_nil)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> "); 
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((rs.getDouble(6)/total_nil)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((rs.getDouble(11)/total_nil)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((rs.getDouble(17)/total_nil)*100)+"%</td> ");  
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((rs.getDouble(24)/total_nil)*100)+"%</td> ");
			out.println(" </tr> ");
			
			
			out.println(" <tr> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:left;}'>Arrears as % of total contracts</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+total_contracts+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((rs.getDouble(32)/total_contracts)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((rs.getDouble(3)/total_contracts)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> "); 
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((rs.getDouble(7)/total_contracts)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((rs.getDouble(12)/total_contracts)*100)+"%</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((rs.getDouble(18)/total_contracts)*100)+"%</td> ");  
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((rs.getDouble(25)/total_contracts)*100)+"%</td> ");
			out.println(" </tr> ");
			
			//cumulative value
			out.println(" <tr> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:left;}'><B>CUMULATIVE VALUE % - "+m_Letter_date_month+" </td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			total      =rs.getDouble(2)+rs.getDouble(6)+rs.getDouble(11)+rs.getDouble(17)+rs.getDouble(24)+rs.getDouble(31);
			m_cum=rs.getDouble(31);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'><B>"+nf.format((m_cum/total)*100)+"%</td> ");
			m_cum=rs.getDouble(31)+rs.getDouble(2);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'><B>"+nf.format((m_cum/total)*100)+"%</td> ");
			m_cum=rs.getDouble(31)+rs.getDouble(2)+rs.getDouble(6);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> "); 
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'><B>"+nf.format((m_cum/total)*100)+"%</td> ");
			m_cum=rs.getDouble(31)+rs.getDouble(2)+rs.getDouble(6)+rs.getDouble(11);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'><B>"+nf.format((m_cum/total)*100)+"%</td> ");
			m_cum=rs.getDouble(31)+rs.getDouble(2)+rs.getDouble(6)+rs.getDouble(11)+rs.getDouble(17);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'><B>"+nf.format((m_cum/total)*100)+"%</td> ");  
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			m_cum=rs.getDouble(31)+rs.getDouble(2)+rs.getDouble(6)+rs.getDouble(11)+rs.getDouble(17)+rs.getDouble(24);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'><B>"+nf.format((m_cum/total)*100)+"%</td> ");
			out.println(" </tr> ");
			
			
			out.println(" <tr> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:left;}'>CUMULATIVE VALUE % - "+m_Letter_date_month_2+" </td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			total      =rs2.getDouble(2)+rs2.getDouble(6)+rs2.getDouble(11)+rs2.getDouble(17)+rs2.getDouble(24)+rs2.getDouble(31);
			m_cum=rs2.getDouble(31);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_cum/total)*100)+"%</td> ");
			m_cum=rs2.getDouble(31)+rs2.getDouble(2);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_cum/total)*100)+"%</td> ");
			m_cum=rs2.getDouble(31)+rs2.getDouble(2)+rs2.getDouble(6);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> "); 
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_cum/total)*100)+"%</td> ");
			m_cum=rs2.getDouble(31)+rs2.getDouble(2)+rs2.getDouble(6)+rs2.getDouble(11);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_cum/total)*100)+"%</td> ");
			m_cum=rs2.getDouble(31)+rs2.getDouble(2)+rs2.getDouble(6)+rs2.getDouble(11)+rs2.getDouble(17);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_cum/total)*100)+"%</td> ");  
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			m_cum=rs2.getDouble(31)+rs2.getDouble(2)+rs2.getDouble(6)+rs2.getDouble(11)+rs2.getDouble(17)+rs2.getDouble(24);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_cum/total)*100)+"%</td> ");
			out.println(" </tr> ");
			
			
			
			//comulative contract 
						
			out.println(" <tr> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:left;}'><B>CUMULATIVE CONTRACT % - "+m_Letter_date_month+" </td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			total      =rs.getDouble(3)+rs.getDouble(7)+rs.getDouble(12)+rs.getDouble(18)+rs.getDouble(25)+rs.getDouble(32);
			m_cum=rs.getDouble(32);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'><B>"+nf.format((m_cum/total)*100)+"%</td> ");
			m_cum=rs.getDouble(32)+rs.getDouble(3);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'><B>"+nf.format((m_cum/total)*100)+"%</td> ");
			m_cum=rs.getDouble(32)+rs.getDouble(3)+rs.getDouble(7);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> "); 
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'><B>"+nf.format((m_cum/total)*100)+"%</td> ");
			m_cum=rs.getDouble(32)+rs.getDouble(3)+rs.getDouble(7)+rs.getDouble(12);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'><B>"+nf.format((m_cum/total)*100)+"%</td> ");
			m_cum=rs.getDouble(32)+rs.getDouble(3)+rs.getDouble(7)+rs.getDouble(12)+rs.getDouble(18);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'><B>"+nf.format((m_cum/total)*100)+"%</td> ");  
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			m_cum=rs.getDouble(32)+rs.getDouble(3)+rs.getDouble(7)+rs.getDouble(12)+rs.getDouble(18)+rs.getDouble(25);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'><B>"+nf.format((m_cum/total)*100)+"%</td> ");
			out.println(" </tr> ");
			
			
			out.println(" <tr> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:left;}'>CUMULATIVE CONTRACT % - "+m_Letter_date_month_2+" </td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			total      =rs2.getDouble(3)+rs2.getDouble(7)+rs2.getDouble(12)+rs2.getDouble(18)+rs2.getDouble(25)+rs2.getDouble(32);
			m_cum=rs2.getDouble(32);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_cum/total)*100)+"%</td> ");
			m_cum=rs2.getDouble(32)+rs2.getDouble(3);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_cum/total)*100)+"%</td> ");
			m_cum=rs2.getDouble(32)+rs2.getDouble(3)+rs2.getDouble(7);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> "); 
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_cum/total)*100)+"%</td> ");
			m_cum=rs2.getDouble(32)+rs2.getDouble(3)+rs2.getDouble(7)+rs2.getDouble(12);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_cum/total)*100)+"%</td> ");
			m_cum=rs2.getDouble(32)+rs2.getDouble(3)+rs2.getDouble(7)+rs2.getDouble(12)+rs2.getDouble(18);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_cum/total)*100)+"%</td> ");  
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			m_cum=rs2.getDouble(32)+rs2.getDouble(3)+rs2.getDouble(7)+rs2.getDouble(12)+rs2.getDouble(18)+rs2.getInt(25);
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_cum/total)*100)+"%</td> ");
			out.println(" </tr> ");
			
			
			
			out.println(" <tr> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:left;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> "); 
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");  
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println(" </tr> ");
			
			
			out.println(" <tr> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:left;}'>NO OF CONTRACTS</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+total_contracts+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> "); 
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");  
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println(" </tr> ");
			
			out.println(" <tr> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:left;}'>NET INVESTMENT IN LEASE</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_nil/to_million)+"</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> "); 
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");  
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println("  <td STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td> ");
			out.println(" </tr> ");
			
		//	}
			out.println(" </table> ");
			
			rs.close();
			rs2.close();
			*/
			
			/*out.println("<font size=2><p style='text-align:justify' class='rep-body'>");	
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr ><td width='*%' class='rep-body' align='center' ><b>"+m_orient_name.toUpperCase()+"</td></tr>");
			out.println("<tr ><td width='*%' class='rep-body' align='center' ><b>PORTFOLIO QUALITY STATEMENT AS AT "+m_Letter_date+"</td></tr>");
			out.println("</table>");	
      out.println("<br>");
			out.println("<table border='1' width='100%' class='table' cellspacing='0' >"); 		
			//-------report header -------------------------------------------------------
			out.println("<tr class=pdn_txtpos2 ><td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Contracts</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Cum Contracts</td>");
			out.println("<td width='9%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Category</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Nil</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Arrears</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>1 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>2 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>3 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>4-5 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>>6 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Demand</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Months</td>");
			out.println("</tr>");
      //--------------------------------------------------------------------------------------------------
			String Sql_main_query="";
			rs = stmt.executeQuery(Sql_main_query);
			more=rs.next();

     if(more){
			m_cum_value=0;
			m_sub_total_arrears=rs.getDouble(4)+rs.getDouble(8)+rs.getDouble(9)+
			                    rs.getDouble(13)+rs.getDouble(14)+rs.getDouble(15)+
													rs.getDouble(19)+rs.getDouble(20)+rs.getDouble(21)+rs.getDouble(22)+
													rs.getDouble(26)+rs.getDouble(27)+rs.getDouble(28)+rs.getDouble(29)+rs.getDouble(30);
													
			total_nil            =rs.getDouble(2)+rs.getDouble(6)+rs.getDouble(11)+rs.getDouble(17)+rs.getDouble(24)+rs.getDouble(31);
			total_one_month_arr  =rs.getDouble(4)+rs.getDouble(8)+rs.getDouble(13)+rs.getDouble(19)+rs.getDouble(26);
			total_two_month_arr  =rs.getDouble(9)+rs.getDouble(14)+rs.getDouble(20)+rs.getDouble(27);
			total_three_month_arr=rs.getDouble(15)+rs.getDouble(21)+rs.getDouble(28);
			total_four_month_arr =rs.getDouble(22)+rs.getDouble(29);
			total_six_month_arr =rs.getDouble(30);
			
			m_cum_value+=rs.getInt(32);
	
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(32)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>Zero contracts</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(31)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>0%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("</tr>");
			
			m_cum_value+=rs.getInt(3);
			arr_precentage=(rs.getDouble(4)/m_sub_total_arrears)*100;
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(3)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>"+rs.getString(1)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(2)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(4)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(arr_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(4)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("</tr>");
			//two month----------------------------
			m_cum_value+=rs.getInt(7);
			m_tot_arreas=rs.getDouble(8)+rs.getDouble(9);
			arr_precentage=(m_tot_arreas/m_sub_total_arrears)*100;

			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(7)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>"+rs.getString(5)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(6)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_tot_arreas/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(arr_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(8)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(9)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			
			out.println("</tr>");
			
			//three month -----------------------------
			m_cum_value+=rs.getInt(12);
			m_tot_arreas=rs.getDouble(13)+rs.getDouble(14)+rs.getDouble(15);
			arr_precentage=(m_tot_arreas/m_sub_total_arrears)*100;
			
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(12)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>"+rs.getString(10)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(11)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_tot_arreas/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(arr_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(13)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(14)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(15)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			
			out.println("</tr>");
			
			
			
			//four month -----------------------------
			m_cum_value+=rs.getInt(18);
			m_tot_arreas=rs.getDouble(19)+rs.getDouble(20)+rs.getDouble(21)+rs.getDouble(22);
			arr_precentage=(m_tot_arreas/m_sub_total_arrears)*100;

			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(18)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>"+rs.getString(16)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(17)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_tot_arreas/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(arr_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(19)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(20)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(21)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(22)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			
			out.println("</tr>");
			
			
			//six month -----------------------------
			m_cum_value+=rs.getInt(25);
			m_tot_arreas=rs.getDouble(26)+rs.getDouble(27)+rs.getDouble(28)+rs.getDouble(29)+rs.getDouble(30);
			arr_precentage=(m_tot_arreas/m_sub_total_arrears)*100;

			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(25)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>"+rs.getString(23)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(24)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_tot_arreas/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(arr_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(26)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(27)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(28)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(29)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(30)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("</tr>");
			
			
			//total  -----------------------------
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_nil/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_sub_total_arrears/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_sub_total_arrears/m_sub_total_arrears)*100)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_one_month_arr/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_two_month_arr/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_three_month_arr/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_four_month_arr/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_six_month_arr/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td></tr>");
			out.println("</tr>");
			
			}
			out.println("</table>");
			rs.close();
			
			//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			
			
			//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			//New Report  previous month
			
		
													
			
			out.println("<br>");
			out.println("<hr color='black'>");
			out.println("<br>");
			
			out.println("<font size=2><p style='text-align:justify' class='rep-body'>");	
			out.println("<table border='0' width='100%' class='table'>"); 		
			//out.println("<tr ><td width='*%' class='rep-body' align='center' ><b>"+m_orient_name.toUpperCase()+"</td></tr>");
			out.println("<tr ><td width='*%' class='rep-body' align='center' ><b>PORTFOLIO QUALITY STATEMENT AS AT "+m_Letter_date_2+"</td></tr>");
			out.println("</table>");	
      out.println("<br>");
			
			out.println("<table border='1' width='100%' class='table' cellspacing='0' >"); 		
			//-------report header -------------------------------------------------------
			out.println("<tr class=pdn_txtpos2 ><td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Contracts</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Cum Contracts</td>");
			out.println("<td width='9%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Category</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Nil</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Arrears</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>1 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>2 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>3 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>4-5 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>>6 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Demand</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Months</td>");
			out.println("</tr>");
      //--------------------------------------------------------------------------------------------------
			String Sql_main_query_2="";
			rs = stmt.executeQuery(Sql_main_query_2);
			more=rs.next();

    if(more){
			m_cum_value=0;
			m_sub_total_arrears=rs.getDouble(4)+rs.getDouble(8)+rs.getDouble(9)+
			                    rs.getDouble(13)+rs.getDouble(14)+rs.getDouble(15)+
													rs.getDouble(19)+rs.getDouble(20)+rs.getDouble(21)+rs.getDouble(22)+
													rs.getDouble(26)+rs.getDouble(27)+rs.getDouble(28)+rs.getDouble(29)+rs.getDouble(30);
													
			total_nil            =rs.getDouble(2)+rs.getDouble(6)+rs.getDouble(11)+rs.getDouble(17)+rs.getDouble(24)+rs.getDouble(31);
			total_one_month_arr  =rs.getDouble(4)+rs.getDouble(8)+rs.getDouble(13)+rs.getDouble(19)+rs.getDouble(26);
			total_two_month_arr  =rs.getDouble(9)+rs.getDouble(14)+rs.getDouble(20)+rs.getDouble(27);
			total_three_month_arr=rs.getDouble(15)+rs.getDouble(21)+rs.getDouble(28);
			total_four_month_arr =rs.getDouble(22)+rs.getDouble(29);
			total_six_month_arr =rs.getDouble(30);
			
			m_cum_value+=rs.getInt(32);
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(32)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>Zero contracts</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(31)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>0%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("</tr>");
			
			m_cum_value+=rs.getInt(3);
			arr_precentage=(rs.getDouble(4)/m_sub_total_arrears)*100;

			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(3)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>"+rs.getString(1)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(2)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(4)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(arr_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(4)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("</tr>");
			//two month----------------------------
			m_cum_value+=rs.getInt(7);
			m_tot_arreas=rs.getDouble(8)+rs.getDouble(9);
			arr_precentage=(m_tot_arreas/m_sub_total_arrears)*100;

			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(7)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>"+rs.getString(5)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(6)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_tot_arreas/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(arr_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(8)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(9)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			
			out.println("</tr>");
			
			//three month -----------------------------
			m_cum_value+=rs.getInt(12);
			m_tot_arreas=rs.getDouble(13)+rs.getDouble(14)+rs.getDouble(15);
			arr_precentage=(m_tot_arreas/m_sub_total_arrears)*100;
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(12)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>"+rs.getString(10)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(11)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_tot_arreas/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(arr_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(13)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(14)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(15)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			
			out.println("</tr>");
			
			
			
			//four month -----------------------------
			m_cum_value+=rs.getInt(18);
			m_tot_arreas=rs.getDouble(19)+rs.getDouble(20)+rs.getDouble(21)+rs.getDouble(22);
			arr_precentage=(m_tot_arreas/m_sub_total_arrears)*100;
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(18)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>"+rs.getString(16)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(17)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_tot_arreas/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(arr_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(19)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(20)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(21)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(22)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			
			out.println("</tr>");
			
			
			//six month -----------------------------
			m_cum_value+=rs.getInt(25);
			m_tot_arreas=rs.getDouble(26)+rs.getDouble(27)+rs.getDouble(28)+rs.getDouble(29)+rs.getDouble(30);
			arr_precentage=(m_tot_arreas/m_sub_total_arrears)*100;
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(25)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>"+rs.getString(23)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(24)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_tot_arreas/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(arr_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(26)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(27)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(28)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(29)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(30)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			
			out.println("</tr>");
			
			//total  -----------------------------
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_nil/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_sub_total_arrears/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_sub_total_arrears/m_sub_total_arrears)*100)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_one_month_arr/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_two_month_arr/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_three_month_arr/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_four_month_arr/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_six_month_arr/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td></tr>");
			out.println("</tr>");

			}
			out.println("</table>");
			rs.close();
			
			//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			
			
			
			
			
			//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			//New Report  previous month
			String m_month_3="";
			String m_Letter_date_3="";
			
				rs = stmt.executeQuery ("SELECT TO_CHAR(ADD_MONTHS(TO_DATE('"+m_month+"','DD-MM-YYYY'),-2),'DD-MM-YYYY') ,TO_CHAR(ADD_MONTHS(LAST_DAY(TO_DATE('"+m_month+"','DD-MM-YYYY')),-2),'fmddth Month yyyy') LETTER_DATE  FROM DUAL ");          
				 more = rs.next();
				if(more){
				m_month_3=rs.getString(1);
				m_Letter_date_3=rs.getString(2);
				}
				rs.close();									
		String Sql_main_query_3= " SELECT "+
	                        " 'One contracts' ,"+ //1
													" "+m_schema_name+".AF_CO_GET_NIL(1,'"+m_month_3+"','"+m_division_code+"') ,  "+ //2
													" "+m_schema_name+".AF_CO_GET_ONE_MON_ARR(0,'"+m_month_3+"','"+m_division_code+"') ,  "+ //3
													" "+m_schema_name+".AF_CO_GET_ONE_MON_ARR(1,'"+m_month_3+"','"+m_division_code+"') ,  "+ //4
													
													" 'Two contracts' ,"+ //5
													" "+m_schema_name+".AF_CO_GET_NIL(2,'"+m_month_3+"','"+m_division_code+"') ,  "+ //6
													" "+m_schema_name+".AF_CO_GET_TWO_MON_ARR(0,'"+m_month_3+"','"+m_division_code+"')  , "+ //7
													" "+m_schema_name+".AF_CO_GET_TWO_MON_ARR(1,'"+m_month_3+"','"+m_division_code+"')  , "+ //8
													" "+m_schema_name+".AF_CO_GET_TWO_MON_ARR(2,'"+m_month_3+"','"+m_division_code+"')  , "+ //9
													
													" 'Three contracts' ,"+ //10
													" "+m_schema_name+".AF_CO_GET_NIL(3,'"+m_month_3+"','"+m_division_code+"') ,  "+ //11
													" "+m_schema_name+".AF_CO_GET_THREE_MON_ARR(0,'"+m_month_3+"','"+m_division_code+"') ,  "+ //12
													" "+m_schema_name+".AF_CO_GET_THREE_MON_ARR(1,'"+m_month_3+"','"+m_division_code+"') ,  "+ //13
													" "+m_schema_name+".AF_CO_GET_THREE_MON_ARR(2,'"+m_month_3+"','"+m_division_code+"') ,  "+ //14
													" "+m_schema_name+".AF_CO_GET_THREE_MON_ARR(3,'"+m_month_3+"','"+m_division_code+"') ,  "+ //15
													
													" 'Four to Five contracts' ,"+ //16
													" "+m_schema_name+".AF_CO_GET_NIL(4,'"+m_month_3+"','"+m_division_code+"') ,  "+ //17
													" "+m_schema_name+".AF_CO_GET_FOUR_MON_ARR(0,'"+m_month_3+"','"+m_division_code+"') ,  "+ //18
													" "+m_schema_name+".AF_CO_GET_FOUR_MON_ARR(1,'"+m_month_3+"','"+m_division_code+"') ,  "+ //19
													" "+m_schema_name+".AF_CO_GET_FOUR_MON_ARR(2,'"+m_month_3+"','"+m_division_code+"') ,  "+ //20
													" "+m_schema_name+".AF_CO_GET_FOUR_MON_ARR(3,'"+m_month_3+"','"+m_division_code+"') ,  "+ //21
													" "+m_schema_name+".AF_CO_GET_FOUR_MON_ARR(4,'"+m_month_3+"','"+m_division_code+"') ,  "+ //22
													
													" 'Over Six' ,"+ //23
													" "+m_schema_name+".AF_CO_GET_NIL(6,'"+m_month_3+"','"+m_division_code+"') ,  "+ //24
													" "+m_schema_name+".AF_CO_GET_SIX_MON_ARR(0,'"+m_month_3+"','"+m_division_code+"')  , "+ //25
													" "+m_schema_name+".AF_CO_GET_SIX_MON_ARR(1,'"+m_month_3+"','"+m_division_code+"')  , "+ //26
													" "+m_schema_name+".AF_CO_GET_SIX_MON_ARR(2,'"+m_month_3+"','"+m_division_code+"')  , "+ //27
													" "+m_schema_name+".AF_CO_GET_SIX_MON_ARR(3,'"+m_month_3+"','"+m_division_code+"')  , "+ //28
													" "+m_schema_name+".AF_CO_GET_SIX_MON_ARR(4,'"+m_month_3+"','"+m_division_code+"')  , "+ //29
													" "+m_schema_name+".AF_CO_GET_SIX_MON_ARR(6,'"+m_month_3+"','"+m_division_code+"')  , "+ //30
													
													" "+m_schema_name+".AF_CO_GET_NIL(0,'"+m_month_3+"','"+m_division_code+"') ,  "+ //31
													" "+m_schema_name+".AF_CO_GET_ZERO_MON_ARR(0,'"+m_month_3+"','"+m_division_code+"')   "+ //32
													
													"FROM DUAL ";
													
			
			out.println("<br>");
			out.println("<hr color='black'>");
			out.println("<br>");
			
			out.println("<font size=2><p style='text-align:justify' class='rep-body'>");	
			out.println("<table border='0' width='100%' class='table'>"); 		
			//out.println("<tr ><td width='*%' class='rep-body' align='center' ><b>"+m_orient_name.toUpperCase()+"</td></tr>");
			out.println("<tr ><td width='*%' class='rep-body' align='center' ><b>PORTFOLIO QUALITY STATEMENT AS AT "+m_Letter_date_3+"</td></tr>");
			out.println("</table>");	
      out.println("<br>");
			
			out.println("<table border='1' width='100%' class='table' cellspacing='0' >"); 		
			//-------report header -------------------------------------------------------
			out.println("<tr class=pdn_txtpos2 ><td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Contracts</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Cum Contracts</td>");
			out.println("<td width='9%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Category</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Nil</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Arrears</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>1 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>2 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>3 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>4-5 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>>6 month</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Demand</td>");
			out.println("<td width='7%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Months</td>");
			out.println("</tr>");
      //--------------------------------------------------------------------------------------------------
			
			rs = stmt.executeQuery(Sql_main_query_3);
			more=rs.next();

    if(more){
			m_cum_value=0;
			m_sub_total_arrears=rs.getDouble(4)+rs.getDouble(8)+rs.getDouble(9)+
			                    rs.getDouble(13)+rs.getDouble(14)+rs.getDouble(15)+
													rs.getDouble(19)+rs.getDouble(20)+rs.getDouble(21)+rs.getDouble(22)+
													rs.getDouble(26)+rs.getDouble(27)+rs.getDouble(28)+rs.getDouble(29)+rs.getDouble(30);
													
			total_nil            =rs.getDouble(2)+rs.getDouble(6)+rs.getDouble(11)+rs.getDouble(17)+rs.getDouble(24)+rs.getDouble(31);
			total_one_month_arr  =rs.getDouble(4)+rs.getDouble(8)+rs.getDouble(13)+rs.getDouble(19)+rs.getDouble(26);
			total_two_month_arr  =rs.getDouble(9)+rs.getDouble(14)+rs.getDouble(20)+rs.getDouble(27);
			total_three_month_arr=rs.getDouble(15)+rs.getDouble(21)+rs.getDouble(28);
			total_four_month_arr =rs.getDouble(22)+rs.getDouble(29);
			total_six_month_arr =rs.getDouble(30);
			
			m_cum_value=rs.getInt(32);

			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(32)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>Zero contracts</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(31)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>0%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("</tr>");
			
			m_cum_value+=rs.getInt(3);
			arr_precentage=(rs.getDouble(4)/m_sub_total_arrears)*100;
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(3)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>"+rs.getString(1)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(2)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(4)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(arr_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(4)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("</tr>");
			//two month----------------------------
			m_cum_value+=rs.getInt(7);
			m_tot_arreas=rs.getDouble(8)+rs.getDouble(9);
			arr_precentage=(m_tot_arreas/m_sub_total_arrears)*100;
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(7)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>"+rs.getString(5)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(6)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_tot_arreas/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(arr_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(8)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(9)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			
			out.println("</tr>");
			
			//three month -----------------------------
			m_cum_value+=rs.getInt(12);
			m_tot_arreas=rs.getDouble(13)+rs.getDouble(14)+rs.getDouble(15);
			arr_precentage=(m_tot_arreas/m_sub_total_arrears)*100;

			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(12)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>"+rs.getString(10)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(11)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_tot_arreas/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(arr_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(13)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(14)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(15)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			
			out.println("</tr>");
			
			
			
			//four month -----------------------------
			m_cum_value+=rs.getInt(18);
			m_tot_arreas=rs.getDouble(19)+rs.getDouble(20)+rs.getDouble(21)+rs.getDouble(22);
			arr_precentage=(m_tot_arreas/m_sub_total_arrears)*100;

			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(18)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>"+rs.getString(16)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(17)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_tot_arreas/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(arr_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(19)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(20)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(21)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(22)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			
			out.println("</tr>");
			
			
			//six month -----------------------------
			m_cum_value+=rs.getInt(25);
			m_tot_arreas=rs.getDouble(26)+rs.getDouble(27)+rs.getDouble(28)+rs.getDouble(29)+rs.getDouble(30);
			arr_precentage=(m_tot_arreas/m_sub_total_arrears)*100;
			
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+rs.getInt(25)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>"+rs.getString(23)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(24)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_tot_arreas/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(arr_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(26)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(27)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(28)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(29)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(rs.getDouble(30)/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			
			out.println("</tr>");
			
					//total  -----------------------------
			out.println("<tr>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='9%' STYLE='{font:  8pt arial; text-align:left;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_nil/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_sub_total_arrears/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format((m_sub_total_arrears/m_sub_total_arrears)*100)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_one_month_arr/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_two_month_arr/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_three_month_arr/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_four_month_arr/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(total_six_month_arr/to_million)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td></tr>");
			out.println("</tr>");
	
		
		
			}
			out.println("</table>");
			rs.close();
			
			//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
													
		  out.println("</form></body></html>");
			}
			*/
			//view report-----------------------------------------------------------------------------------------------
			else  if(m_chksql.trim().equals("main_page")){	
			
		  out.println("<html><head>"); 
			out.println("<title>Portfolio Quality Statement</title></head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
  		
			out.println("<script>");
			
			out.println("function get_vector(data_vec) {");
			out.println("			if(document.Form1.hid_chk_status.value=='M_SYS_DATE'  ){");
			out.println("			document.Form1.VAL_DAY.value=data_vec[0];");
			out.println("			document.Form1.VAL_MONTH.value=data_vec[1];");
			out.println("			document.Form1.VAL_YEAR.value=data_vec[2];");
			out.println("			}");
			
			out.println("}");

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_AF_RE_Portfolio_Quality_Statement\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Collection Process - Portfoliio Quality Statement - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Collection Process - Portfoliio Quality Statement - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
		
   
			out.println("}"); 
			out.println("else{}");
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("document.Form1.hid_save_status.value=\"Save\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("document.Form1.hid_save_status.value=\"Modify\";"); 
			out.println("}else if(m_val==\"DACT\"){");  
			out.println("document.Form1.hid_status.value=\"Deactivate\";");  
			out.println("document.Form1.hid_save_status.value=\"Deactive\";"); 
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_status.value=\"Reactivate\";");  
			out.println("document.Form1.hid_save_status.value=\"Reactive\";"); 
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}"); 
			
			out.println("function check_Date(objDD,objMM,objYY) {");
			out.println("   if(objDD.value!='' &&  objMM.value!='' && objYY.value!=''){");
			out.println("   checkMonthLength(objDD,objMM,objYY);");
			out.println("   document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");			
			out.println("}");
			out.println("}");
			
			
			out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
			//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
			out.println("}");
			
			out.println("function load_c_date(val) {");
		  out.println("if(document.Form1.SCREEN_NAME.value!=\"DEL\"){");
      out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
			out.println("v_date=val.substr(0,val.indexOf('-'));");
			out.println("if(v_date.length<2)");
			out.println("v_date=0+v_date");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("v_month=val.substr(0,val.indexOf('-'));");
			out.println("if(v_month.length<2)");
			out.println("v_month=0+v_month");
			
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			
			
			out.println("     document.Form1.VAL_DAY.value=v_date;");
			out.println("     document.Form1.VAL_MONTH.value=v_month;");
			out.println("     document.Form1.VAL_YEAR.value=val;");
			out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");			
			out.println("  }");				
			out.println("}");
			out.println("}");
			
			
			out.println("function help_button_View() {");
			out.println("if(document.Form1.VAL_DAY.value==\"\" || document.Form1.VAL_MONTH.value==\"\"  || document.Form1.VAL_YEAR.value==\"\"  ){  "); 
			out.println("VDATE.style.color='red';");
			out.println("}"); 
			out.println("else {"); 
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Portfolio_Quality_Statement?chksql=main_page&generate=letter&print=TRUE&division_code='+document.Form1.TXT_DIVISION_CODE.value+'&month='+document.Form1.hid_date.value+'';"); 
			out.println("window.open(m_url,'displayWindow3','left=30,top=110,width=950,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
		  out.println("}"); 
			out.println("}");
			
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Portfolio_Quality_Statement_New?chksql=main_page&generate=view';"); 
			out.println("		}"); 
			out.println("}"); 
			
			
							out.println("var timerID;");
				out.println("var durationID=0;");
				
				out.println("function set_timer_actions() {");
				out.println("   durationID=durationID+1;");
				out.println("		timerID = setTimeout(\"set_timer_actions()\",1000);");
				out.println("		m_table.innerHTML=\"<p><b>Please Wait...\"+durationID+\" s</b></P>\";");
				out.println("}");

			
			out.println("function run_report() {");
			out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
			out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Portfolio_Quality_Statement_New?chksql=run_report&date=\"+m_date+\"&report_type=\"+document.Form1.TXT_REPORT_TYPE.value+\"&division_code=\"+document.Form1.TXT_DIVISION_CODE.value;"); 
			out.println("   set_timer_actions();");
			out.println("		load_interface(m_url,'NORM');");
			out.println("	}");
			out.println("}");
			
			out.println("function get_vector_normal(m_data){");
			out.println("		if(m_data==\"OK\"){");
			out.println("			print_report();"); 
			out.println("		}");
			out.println("		else{");
			out.println("			alert('Error when generating Report...'+m_data);");
			out.println("		}");
			out.println("}");
			
			out.println("function print_report(){");
			out.println("		clearTimeout(timerID);");
			out.println("		m_table.innerHTML=\"\";");
			out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
			out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
			out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Portfolio_Quality_Statement_New?chksql=print_report&date=\"+m_date+\"&report_type=\"+document.Form1.TXT_REPORT_TYPE.value+\"&division_code=\"+document.Form1.TXT_DIVISION_CODE.value;"); 
			out.println("			window.open(m_url);");
			out.println("	}");
			out.println("}");
			
			out.println("function get_system_date() {");
			out.println("assignState('M_SYS_DATE') ;");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=get_sys_date\";");
			out.println("		load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function assignState(val){");
			out.println("document.Form1.hid_chk_status.value=val");
			out.println("}");


			out.println("</script>");
			
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"get_system_date()\">");  
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input type='hidden' name='Hid_scr_name' value='' > ");
			out.println("<INPUT TYPE='Hidden' NAME='hid_date' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"NEW\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_win_type' VALUE=\"Main\">"); 			
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
						
				
				  out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
					out.println("<tr>"); 
					out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
					out.println("<td class='border_wht' valign='top'> "); 
					out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
					out.println("<tr> "); 
					out.println("<td height='30' class='pdn_mainHD'>"+header_name+"</td>"); 
					out.println("</tr>"); 
					out.println("<tr> "); 
					out.println("<td height='1'><img src='spacer.gif' width='1' height='1'></td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td style='height: 327px'>"); 
					out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' height='100%' width='100%'>   "); 
					out.println("<tr>"); 
					out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process - Portfoliio Quality Statement</td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>"); 
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					out.println("<td width='10%'></td>");  
					out.println("<td width='10%'></td>");  
					out.println("<td width='6%'></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
					out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
					out.println("</table>");  
					out.println("</td></tr><tr>");  
					out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
					out.println("</tr><tr>");  
					out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
					out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%' >");  
					out.println("<tr class='tr_input'>");  
					out.println("<td width='100%'><img height='1' src='spacer.gif' width='100%' /></td>");  
					out.println("</tr>");  
					out.println("</table>");  
					
					
					out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=table>");
					out.println("<tr class=tr_input>");
					out.println("<td width='20%'ID=VDATE>Date as at *</td>"); //Month 
					out.println("<td width='60%'><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input5\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
					out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input5\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
					out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input5\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
					out.println("    <input class='but_input' type='button' name='BUT_RUN_QUALITY' value=\"View Report\" onClick=\"print_report()\" style=\"{width:110px;}\">"); 
					out.println("    <input class='but_input' type='button' name='BUT_RUN_QUALITY' value=\"Run Report\" onClick=\"run_report()\" style=\"{width:110px;}\"></td>"); 
					out.println("<td width='*%'>");
					out.println("</td>");
					out.println("</tr>");
					
					//added by nwuan de silva on 30-10-07-------------------------------------------------------------
					out.println("<tr >"); 
					out.println("<td width='20%' ><DIV id='DIV_TXT_DIVISION_CODE'  class=div_input>Division Code</DIV></td>"); 
					out.println("<td width='60%' ><SELECT onchange=\"\" name=\"TXT_DIVISION_CODE\" class=\"txt_input\" > ");
					out.println("<OPTION value=\"AF\" SELECTED>Other</OPTION>");
					out.println("<OPTION value=\"BD\">Bike</OPTION>");
					out.println("</SELECT></td>");
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					
					
					//Added by Sandun on 04-08-2008*******************************
					
					out.println("<tr>&nbsp;</tr>");
					out.println("<tr >"); 
					out.println("<td width='20%' ><DIV id='DIV_TXT_REPORT_TYPE'  class=div_input>Report Type</DIV></td>"); 
					out.println("<td width='60%' ><SELECT onchange=\"\" name=\"TXT_REPORT_TYPE\" class=\"txt_input\" > ");
					out.println("<OPTION value=\"N_LEG\" SELECTED>Non Legal</OPTION>");
					out.println("<OPTION value=\"LEG\">Legal</OPTION>");
					out.println("</SELECT></td>");
					out.println("<td width='*%'></td>");								
					out.println("</tr>"); 
					
					
		
					
					
					
					
					
				/* out.println("<tr >"); 
				 out.println("<td width='20%' ><DIV id='DIV_TXT_USER'  class=div_input>Collection Officer </DIV></td>"); 
				 out.println("<td width='60%' ><input class='txt_input' type='text' name='TXT_USER' maxlength='10' style='{width=150px}' size='10' onblur=\"assignState('M_USER'),makeRequest(document.Form1.TXT_USER)\">"); 
				 out.println("<input class='but_input' type='button' name='BUT_TXT_USER' value=\"Help\" onClick=\"help_button_user()\"></TD>"); 
				 out.println("</tr>"); 
				*/	
					out.println("</table>");						
					out.println("<hr>");
					out.println("<table align='center' width='100%' class='table'>"); 
					out.println("<tr>");  
					out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
					out.println("</tr>"); 
					out.println("</table>");
					
				
					out.println("</form>"); 
					
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
					
					out.println("</body>"); 
					out.println("</html>"); 
				
			
			}
			
			
			//}
			
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
