// DEVELOP BY : MAHELA FOR OFSCL FACTORING    DATE:04-01-2007
// TRIAL BALANCE REPORT            
      
import java.io.*; 
import javax.servlet.*;   
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
     

public class LAKDL_AF_MISF_lease_maturity_profile extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public ResultSet rs;
	Statement stmt;
	Connection conn;
	java.text.NumberFormat nf1,nf;
  CallableStatement callstmt1 =null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			     
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			String m_schema_name=m_sn_methods.schema_name;
			


			conn = m_sn_methods.met_user_validate(req); 
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			String m_username=m_sn_methods.username; 
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(2);
			nf1.setMaximumFractionDigits(2);	
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(0);
			nf.setMaximumFractionDigits(0);	
			
			stmt = conn.createStatement();
			
			String chksql=req.getParameter("chksql");
			
				if(chksql.equals("run_report")){ 
			
				String m_cut_off_date = req.getParameter("activ_cut_date");
				String m_from_date    = req.getParameter("from_date");
				String m_to_date      = req.getParameter("to_date");
												
				
				try{
				//callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_TBD_MATURITY_RPT(:1,:2,:3,:4);END;");
				callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_TBD_MATURITY_RPT(:1,:2,:3);END;");
				/*
				callstmt1.setString(1,m_cut_off_date);
				callstmt1.setString(2,m_from_date);
				callstmt1.setString(3,m_to_date);
				callstmt1.setString(4,m_username);
				
				*/
				
				callstmt1.setString(1,m_cut_off_date);
				callstmt1.setString(2,m_username);
				callstmt1.setString(3,null);
				
				callstmt1.execute();
				
				out.print("OK"); 
				}
				catch(Exception ex){
				out.println("ERROR"+ex.toString()); 
				}

			}
			
			else if(chksql.trim().equals("main_page")){
			
			String m_date_dd="";
			String m_date_mm="";
			String m_date_yy="";
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Lease Maturity Profile</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			     
			out.println("var m_sav_msg='';");
			
			out.println("var timerID;");
			out.println("var durationID=0;");
			
			out.println("function set_timer_actions() {");
			out.println("   durationID=durationID+1;");
			out.println("		timerID = setTimeout(\"set_timer_actions()\",1000);");
			out.println("		m_table.innerHTML=\"<p><b>Please Wait...\"+durationID+\" s</b></P>\";");
			out.println("}");
				
			
			out.println("function get_vector(data_vec) {");
			out.println("	  if(data_vec.length>0 && document.Form1.hid_option.value==\"1\"){");
			out.println("			document.Form1.TXT_FROM_DATE_DD.value=data_vec[0];");
			out.println("			document.Form1.TXT_FROM_DATE_MM.value=data_vec[1];");
			out.println("			document.Form1.TXT_FROM_DATE_YY.value=data_vec[2];");
			out.println("			document.Form1.TXT_TO_DATE_DD.value=data_vec[0];");
			out.println("			document.Form1.TXT_TO_DATE_MM.value=data_vec[1];");
			out.println("			document.Form1.TXT_TO_DATE_YY.value=data_vec[2];");
	  	out.println("			document.Form1.TXT_ACT_CUT_DATE_DD.value=data_vec[0];");
		  out.println("			document.Form1.TXT_ACT_CUT_DATE_MM.value=data_vec[1];");
			out.println("			document.Form1.TXT_ACT_CUT_DATE_YY.value=data_vec[2];");
			out.println("		}");
			out.println("}");
			
			//To validate from date & to date
			out.println("function validate_date(){");
			out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
			out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
			out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
			out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
			out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
			out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
			out.println(" if(m_from_dd != '' && m_from_mm !='' && m_from_yy !=''  ) { ");
			out.println("    if(!checkMonthLength(document.Form1.TXT_FROM_DATE_DD,document.Form1.TXT_FROM_DATE_MM,document.Form1.TXT_FROM_DATE_YY)){  "); 
			out.println("     return false;"); 
			out.println("     }");
			out.println("    else {");
			out.println(" 	  if(m_to_dd != '' && m_to_mm !='' && m_to_yy !=''  ) { ");
			out.println("  	     if(checkMonthLength(document.Form1.TXT_TO_DATE_DD,document.Form1.TXT_TO_DATE_MM,document.Form1.TXT_TO_DATE_YY)){  "); 
			out.println("      	  return true;"); 
			out.println("  	  	 }");
			out.println("        else "); 
			out.println("         return false; "); 
			out.println("     }");
			out.println("			else {");
			out.println("   		alert('To Date cannot be null ')");
			out.println("   		return false;"); 
			out.println("     }");
			out.println("    }");
			out.println("  }");
			out.println(" else { ");
			out.println("   alert('From Date cannot be null ')");
			out.println("   return false;"); 
			out.println("  }");
			out.println(" }");			
			
			out.println("function show_trail_balance_report() {");
			out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
			out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
			out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
			out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
			out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
			out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
			out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
			out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
			out.println("		if(validate_date()) {");
			out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report?chksql=LOAD_TRAIL_BAL_REPORT_LEVEL_1&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&div_code=\"+document.Form1.TXT_DIVISION_CODE.value+\"&prod_code=\"+document.Form1.TXT_PRODUCT_CODE.value+\"&acc_type_code=\"+document.Form1.TXT_ACC_CODE.value;");
			out.println("    popupwin=window.open(m_url,'displayWindow1','left=70,top=110,width=1000,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("   }");
			out.println("}");
			
			
			out.println("function run_report() {");
			
			out.println("ac_date    = document.Form1.TXT_ACT_CUT_DATE_DD.value+'-'+document.Form1.TXT_ACT_CUT_DATE_MM.value+'-'+document.Form1.TXT_ACT_CUT_DATE_YY.value;");
			out.println("from_date  = document.Form1.TXT_FROM_DATE_DD.value+'-'+document.Form1.TXT_FROM_DATE_MM.value+'-'+document.Form1.TXT_FROM_DATE_YY.value;");
			out.println("to_date    = document.Form1.TXT_TO_DATE_DD.value+'-'+document.Form1.TXT_TO_DATE_MM.value+'-'+document.Form1.TXT_TO_DATE_YY.value;");
			out.println("in_type    = document.Form1.TXT_PRODUCT_LEVEL.value;");
			out.println("client     = document.Form1.TXT_CLIENT_CODE.value;");
			out.println("locat      = document.Form1.TXT_LOCATION.value;");
			out.println("PAY_CAT    = document.Form1.PAY_CAT.value;");
			out.println("m_status   = document.Form1.CONTRACT_STATUS.value;");
			
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_lease_maturity_profile?chksql=run_report&status=\"+m_status+\"&prod_level=\"+in_type+\"&location_code=\"+locat+\"&client_code=\"+client+\"&activ_cut_date=\"+ac_date+\"&from_date=\"+from_date+\"&to_date=\"+to_date+\"\"; ");//&location_code=\"+location+\"
			//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_lease_maturity_profile?chksql=run_report&date=\"+m_date;"); 
			out.println("   set_timer_actions();");
			out.println("		load_interface(m_url,'NORM');");
			//out.println("	}");
			out.println("}");
			
			out.println("function get_vector_normal(m_data){");
			out.println("		if(m_data==\"OK\"){");
			//out.println("			print_report();"); 
			out.println("			alert('Report Generated Successfully');"); 
			out.println("		  m_table.innerHTML='';");
			out.println("		}");
			out.println("		else{");
			out.println("			alert('Error when generating Report...'+m_data);");
			out.println("		}");
			out.println("}");
				
			
			
			out.println("function chkLesseeCode(){");
			out.println("if(document.Form1.TXT_CLIENT_CODE.value==\"\"){");
			out.println("alert('Lessee Code cannot be Empty...!');");
			out.println("return false;");
			out.println("document.Form1.TXT_CLIENT_CODE.focus();");
			out.println("}else{");
      out.println("return true;");
      out.println("}");
			out.println("}");
			
			out.println("function chkLocationCode(){");
			out.println("if(document.Form1.TXT_LOCATION.value==\"\"){");
			out.println("alert('Location cannot be Empty...!');");
			out.println("return false;");
			out.println("document.Form1.TXT_LOCATION.focus();");
			out.println("}else{");
      out.println("return true;");
      out.println("}");
			out.println("}");
			
			out.println("function chkProductLevel(){");
			/*  //Commented By Sandun on 28-01-2009
			out.println("if(document.Form1.TXT_PRODUCT_LEVEL.value==\"\"){");
			out.println("alert('Product Level cannot be Empty...!');");
			out.println("return false;");
			out.println("document.Form1.TXT_PRODUCT_LEVEL.focus();");
			out.println("}else{");*/
      out.println("return true;");
     // out.println("}");
			out.println("}");
			
			out.println("function month_wise_sum_report(){");
			out.println("if(chkProductLevel()){");
			out.println("ac_date    = document.Form1.TXT_ACT_CUT_DATE_DD.value+'-'+document.Form1.TXT_ACT_CUT_DATE_MM.value+'-'+document.Form1.TXT_ACT_CUT_DATE_YY.value;");
			out.println("from_date  = document.Form1.TXT_FROM_DATE_DD.value+'-'+document.Form1.TXT_FROM_DATE_MM.value+'-'+document.Form1.TXT_FROM_DATE_YY.value;");
			out.println("to_date    = document.Form1.TXT_TO_DATE_DD.value+'-'+document.Form1.TXT_TO_DATE_MM.value+'-'+document.Form1.TXT_TO_DATE_YY.value;");
			out.println("in_type    = document.Form1.TXT_PRODUCT_LEVEL.value;");
			out.println("client     = document.Form1.TXT_CLIENT_CODE.value;");
			out.println("locat      = document.Form1.TXT_LOCATION.value;");
			out.println("PAY_CAT    = document.Form1.PAY_CAT.value;");
			out.println("m_status   = document.Form1.CONTRACT_STATUS.value;");
			
			//month_wise_sum_report_AMI
			//out.println("alert('PAY_CAT'+PAY_CAT);");
			out.println("if(PAY_CAT=='AMI'){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_lease_maturity_profile?chksql=month_wise_sum_report_AMI&prod_level=\"+in_type+\"&location_code=\"+locat+\"&client_code=\"+client+\"&activ_cut_date=\"+ac_date+\"&from_date=\"+from_date+\"&to_date=\"+to_date+\"\"; ");//&location_code=\"+location+\"
			out.println("}else if(PAY_CAT=='NIBSM'){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_lease_maturity_profile?chksql=month_wise_sum_report_NIBSM&prod_level=\"+in_type+\"&location_code=\"+locat+\"&client_code=\"+client+\"&activ_cut_date=\"+ac_date+\"&from_date=\"+from_date+\"&to_date=\"+to_date+\"\"; ");//&location_code=\"+location+\"
			out.println("}else if(PAY_CAT=='RENTAL'){");
			
			// comment by nuwan de silva 17-11-2009
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_lease_maturity_profile?chksql=month_wise_sum_report&status=\"+m_status+\"&prod_level=\"+in_type+\"&location_code=\"+locat+\"&client_code=\"+client+\"&activ_cut_date=\"+ac_date+\"&from_date=\"+from_date+\"&to_date=\"+to_date+\"\"; ");//&location_code=\"+location+\"
			
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_lease_maturity_profile?chksql=month_wise_sum_report_new&status=\"+m_status+\"&prod_level=\"+in_type+\"&location_code=\"+locat+\"&client_code=\"+client+\"&activ_cut_date=\"+ac_date+\"&from_date=\"+from_date+\"&to_date=\"+to_date+\"\"; ");//&location_code=\"+location+\"
			
			out.println("}");
			
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_lease_maturity_profile?chksql=month_wise_sum_report&prod_level=\"+in_type+\"&location_code=\"+locat+\"&client_code=\"+client+\"&activ_cut_date=\"+ac_date+\"&from_date=\"+from_date+\"&to_date=\"+to_date+\"\"; ");//&location_code=\"+location+\"
			//out.println("alert(m_url);");
			
			out.println("popupwin=window.open(m_url,'displayWindow1','left=70,top=110,width=1000,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("}");
			out.println("}");
			
			out.println("function lessee_wise_detail_report(){");
			
			//out.println("if(chkLesseeCode() && chkProductLevel()){");
			out.println("ac_date = document.Form1.TXT_ACT_CUT_DATE_DD.value+'-'+document.Form1.TXT_ACT_CUT_DATE_MM.value+'-'+document.Form1.TXT_ACT_CUT_DATE_YY.value;");
			out.println("in_type = document.Form1.TXT_PRODUCT_LEVEL.value;");
			out.println("client  = document.Form1.TXT_CLIENT_CODE.value;");
			out.println("locat   = document.Form1.TXT_LOCATION.value;");
			
			
			out.println("from_date=document.Form1.TXT_FROM_DATE_DD.value+'-'+document.Form1.TXT_FROM_DATE_MM.value+'-'+document.Form1.TXT_FROM_DATE_YY.value;");
			out.println("to_date=document.Form1.TXT_TO_DATE_DD.value+'-'+document.Form1.TXT_TO_DATE_MM.value+'-'+document.Form1.TXT_TO_DATE_YY.value;");

			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_lease_maturity_profile?chksql=lessee_wise_detail_report&prod_level=\"+in_type+\"&from_date=\"+from_date+\"&to_date=\"+to_date+\"&location_code=\"+locat+\"&client_code=\"+client+\"&activ_cut_date=\"+ac_date+\"\"; ");//&location_code=\"+location+\"
			out.println("popupwin=window.open(m_url,'displayWindow1','left=70,top=110,width=1000,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			
			//out.println("}");
			
			out.println("}");
			
			
			
			out.println("function branch_wise_summary_report(){");
			out.println("if(chkProductLevel()){");
			out.println("ac_date = document.Form1.TXT_ACT_CUT_DATE_DD.value+'-'+document.Form1.TXT_ACT_CUT_DATE_MM.value+'-'+document.Form1.TXT_ACT_CUT_DATE_YY.value;");
			out.println("in_type = document.Form1.TXT_PRODUCT_LEVEL.value;");
			out.println("client  = document.Form1.TXT_CLIENT_CODE.value;");
			out.println("locat   = document.Form1.TXT_LOCATION.value;");
			
			out.println("from_date=document.Form1.TXT_FROM_DATE_DD.value+'-'+document.Form1.TXT_FROM_DATE_MM.value+'-'+document.Form1.TXT_FROM_DATE_YY.value;");
			out.println("to_date=document.Form1.TXT_TO_DATE_DD.value+'-'+document.Form1.TXT_TO_DATE_MM.value+'-'+document.Form1.TXT_TO_DATE_YY.value;");
			
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_lease_maturity_profile?chksql=branch_wise_summary_report&prod_level=\"+in_type+\"&from_date=\"+from_date+\"&to_date=\"+to_date+\"&location_code=\"+locat+\"&client_code=\"+client+\"&activ_cut_date=\"+ac_date+\"\"; ");//&location_code=\"+location+\"
			out.println("popupwin=window.open(m_url,'displayWindow1','left=70,top=110,width=1000,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("}");	
			out.println("}");	
			
			
			
			out.println("function branch_wise_detail_report(){");
			//out.println("if(chkLocationCode() && chkProductLevel()){");
			out.println("ac_date = document.Form1.TXT_ACT_CUT_DATE_DD.value+'-'+document.Form1.TXT_ACT_CUT_DATE_MM.value+'-'+document.Form1.TXT_ACT_CUT_DATE_YY.value;");
			out.println("in_type = document.Form1.TXT_PRODUCT_LEVEL.value;");
			out.println("client  = document.Form1.TXT_CLIENT_CODE.value;");
			out.println("locat   = document.Form1.TXT_LOCATION.value;");
			
			out.println("from_date=document.Form1.TXT_FROM_DATE_DD.value+'-'+document.Form1.TXT_FROM_DATE_MM.value+'-'+document.Form1.TXT_FROM_DATE_YY.value;");
			out.println("to_date=document.Form1.TXT_TO_DATE_DD.value+'-'+document.Form1.TXT_TO_DATE_MM.value+'-'+document.Form1.TXT_TO_DATE_YY.value;");


			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_lease_maturity_profile?chksql=branch_wise_detail_report&prod_level=\"+in_type+\"&from_date=\"+from_date+\"&to_date=\"+to_date+\"&location_code=\"+locat+\"&client_code=\"+client+\"&activ_cut_date=\"+ac_date+\"\"; ");//&location_code=\"+location+\"
			out.println("popupwin=window.open(m_url,'displayWindow1','left=70,top=110,width=1000,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			//out.println("}");	
			out.println("}");
			
			
			out.println("function agreement_wise_detail_report(){");
			
			out.println("if(chkProductLevel()){");
			out.println("ac_date = document.Form1.TXT_ACT_CUT_DATE_DD.value+'-'+document.Form1.TXT_ACT_CUT_DATE_MM.value+'-'+document.Form1.TXT_ACT_CUT_DATE_YY.value;");
			out.println("in_type = document.Form1.TXT_PRODUCT_LEVEL.value;");
			out.println("client  = document.Form1.TXT_CLIENT_CODE.value;");
			out.println("locat   = document.Form1.TXT_LOCATION.value;");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_lease_maturity_profile?chksql=agreement_wise_detail_report&prod_level=\"+in_type+\"&location_code=\"+locat+\"&client_code=\"+client+\"&activ_cut_date=\"+ac_date+\"\"; ");//&location_code=\"+location+\"
			out.println("PAY_CAT   = document.Form1.PAY_CAT.value;");
			

			
			
			out.println("from_date=document.Form1.TXT_FROM_DATE_DD.value+'-'+document.Form1.TXT_FROM_DATE_MM.value+'-'+document.Form1.TXT_FROM_DATE_YY.value;");
			out.println("to_date=document.Form1.TXT_TO_DATE_DD.value+'-'+document.Form1.TXT_TO_DATE_MM.value+'-'+document.Form1.TXT_TO_DATE_YY.value;");
			
			out.println("if(PAY_CAT=='AMI'){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_lease_maturity_profile?chksql=agreement_wise_detail_report_AMI&prod_level=\"+in_type+\"&location_code=\"+locat+\"&client_code=\"+client+\"&from_date=\"+from_date+\"&to_date=\"+to_date+\"&activ_cut_date=\"+ac_date+\"\"; ");
			out.println("}else if(PAY_CAT=='NIBSM'){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_lease_maturity_profile?chksql=agreement_wise_detail_report_NIBSM&prod_level=\"+in_type+\"&location_code=\"+locat+\"&client_code=\"+client+\"&from_date=\"+from_date+\"&to_date=\"+to_date+\"&activ_cut_date=\"+ac_date+\"\"; ");
			out.println("}else if(PAY_CAT=='RENTAL'){");
			
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_lease_maturity_profile?chksql=agreement_wise_detail_report&prod_level=\"+in_type+\"&location_code=\"+locat+\"&client_code=\"+client+\"&from_date=\"+from_date+\"&to_date=\"+to_date+\"&activ_cut_date=\"+ac_date+\"\"; ");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_lease_maturity_profile?chksql=agreement_wise_detail_report_new&prod_level=\"+in_type+\"&location_code=\"+locat+\"&client_code=\"+client+\"&from_date=\"+from_date+\"&to_date=\"+to_date+\"&activ_cut_date=\"+ac_date+\"\"; ");
			out.println("}");
			
			
			out.println("popupwin=window.open(m_url,'displayWindow1','left=70,top=110,width=1000,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("}");	
			out.println("}");
			
			
			out.println("function agreement_wise_summary_report(){");
			out.println("if(chkProductLevel()){");
			out.println("ac_date = document.Form1.TXT_ACT_CUT_DATE_DD.value+'-'+document.Form1.TXT_ACT_CUT_DATE_MM.value+'-'+document.Form1.TXT_ACT_CUT_DATE_YY.value;");
			out.println("in_type = document.Form1.TXT_PRODUCT_LEVEL.value;");
			out.println("client  = document.Form1.TXT_CLIENT_CODE.value;");
			out.println("locat   = document.Form1.TXT_LOCATION.value;");
			out.println("PAY_CAT   = document.Form1.PAY_CAT.value;");
			out.println("from_date=document.Form1.TXT_FROM_DATE_DD.value+'-'+document.Form1.TXT_FROM_DATE_MM.value+'-'+document.Form1.TXT_FROM_DATE_YY.value;");
			out.println("to_date=document.Form1.TXT_TO_DATE_DD.value+'-'+document.Form1.TXT_TO_DATE_MM.value+'-'+document.Form1.TXT_TO_DATE_YY.value;");
			out.println("if(PAY_CAT=='AMI'){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_lease_maturity_profile?chksql=agreement_wise_detail_report_AMI&prod_level=\"+in_type+\"&location_code=\"+locat+\"&client_code=\"+client+\"&from_date=\"+from_date+\"&to_date=\"+to_date+\"&activ_cut_date=\"+ac_date+\"\"; ");
			out.println("}else if(PAY_CAT=='NIBSM'){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_lease_maturity_profile?chksql=agreement_wise_detail_report_NIBSM&prod_level=\"+in_type+\"&location_code=\"+locat+\"&client_code=\"+client+\"&from_date=\"+from_date+\"&to_date=\"+to_date+\"&activ_cut_date=\"+ac_date+\"\"; ");
			out.println("}else if(PAY_CAT=='RENTAL'){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_lease_maturity_profile?chksql=agreement_wise_summary_report&prod_level=\"+in_type+\"&location_code=\"+locat+\"&client_code=\"+client+\"&from_date=\"+from_date+\"&to_date=\"+to_date+\"&activ_cut_date=\"+ac_date+\"\"; ");
			out.println("}");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=70,top=110,width=1000,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("}");	
			out.println("}");
			
			
			
			
			out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
			//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
			out.println("}");
							
			out.println("function load_c_date(val) {");
			out.println("var date1='' ");
			out.println("var date2='' ");
		  out.println("  if(document.Form1.hid_cal_date.value=='1'){"); 
			out.println("		v_date=val.substr(0,val.indexOf('-'));");
			out.println("		if(v_date.length<2)");
			out.println("			v_date=0+v_date");
			out.println("			val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("			v_month=val.substr(0,val.indexOf('-'));");
			out.println("		if(v_month.length<2)");
			out.println("			v_month=0+v_month");
			out.println("			val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("     document.Form1.TXT_FROM_DATE_DD.value=v_date;");
			out.println("     document.Form1.TXT_FROM_DATE_MM.value=v_month;");
			out.println("     document.Form1.TXT_FROM_DATE_YY.value=val;");
			out.println("	}");
			
			out.println(" if(document.Form1.hid_cal_date.value=='2'){"); 
			out.println("		v_date=val.substr(0,val.indexOf('-'));");
			out.println("		if(v_date.length<2)");
			out.println("			v_date=0+v_date");
			out.println("			val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("			v_month=val.substr(0,val.indexOf('-'));");
			out.println("		if(v_month.length<2)");
			out.println("			v_month=0+v_month");
			out.println("			val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("     document.Form1.TXT_TO_DATE_DD.value=v_date;");
			out.println("     document.Form1.TXT_TO_DATE_MM.value=v_month;");
			out.println("     document.Form1.TXT_TO_DATE_YY.value=val;");			
			out.println(" }");
			
			out.println(" if(document.Form1.hid_cal_date.value=='3'){"); 
			out.println("		v_date=val.substr(0,val.indexOf('-'));");
			out.println("		if(v_date.length<2)");
			out.println("			v_date=0+v_date");
			out.println("			val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("			v_month=val.substr(0,val.indexOf('-'));");
			out.println("		if(v_month.length<2)");
			out.println("			v_month=0+v_month");
			out.println("			val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("     document.Form1.TXT_ACT_CUT_DATE_DD.value=v_date;");
			out.println("     document.Form1.TXT_ACT_CUT_DATE_MM.value=v_month;");
			out.println("     document.Form1.TXT_ACT_CUT_DATE_YY.value=val;");
			out.println(" }");			
			out.println("}");

			
			/*out.println("function get_vector_normal(m_data){");
			out.println("		invoice_detail_data.innerHTML=m_data;");
			out.println("}");
			*/
			
			out.println("function validate_data(){"); 
			out.println("	return true;"); 
			out.println("}"); 			


			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_lease_maturity_profile?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 
			out.println("function new_window(){	"); 
			
			out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_lease_maturity_profile';"); 
			out.println("}"); 

			out.println("function save_window(){	"); 
			out.println("	before_submit();"); 
			out.println("}"); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_FA_OP_CLIENT_STATEMENT_REPORT\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("		popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"FA_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
 
			out.println("function load_roll_value(m_val){"); 
			out.println("	help_box.innerHTML=\"Lease Maturity Profile - \"+m_val;"); 
			out.println("}"); 

			out.println("function load_roll_out_value(){");
			out.println("	help_box.innerHTML=\"Lease Maturity Profile \";"); 
			out.println("}"); 
			   
			out.println("function get_system_date() {");
			out.println("	  document.Form1.hid_option.value=\"1\";");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=get_sys_date\";");
			out.println("		load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function load_screen_status(m_val){"); 
			out.println("		if(m_val==\"NEW\"){"); 
			out.println("			new_window();"); 
			out.println("		}"); 
			out.println("		else if(m_val==\"HELP\"){"); 
			out.println("			load_help_msg();"); 
			out.println("		}"); 
			out.println("		document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("		if(m_val==\"NEW\"){");
			out.println("			document.Form1.hid_status.value=\"New\";"); 
			out.println("		}");
			out.println("		else if(m_val==\"EDIT\"){");  
			out.println("			document.Form1.hid_status.value=\"Edit\";");  
			out.println("		}");
			out.println("		else if(m_val==\"DACT\"){");  
			out.println("			document.Form1.hid_status.value=\"Deactivate\";");  
			out.println("		}");
			out.println("		else if(m_val==\"RACT\"){");  
			out.println("			document.Form1.hid_status.value=\"Reactivate\";");  
			out.println("		}");
			out.println("		else{");  
			out.println("			document.Form1.hid_status.value=\"\";");  
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function get_display_msg(){"); 
			out.println("	if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("		m_sav_msg=\"Save\";"); 
			out.println("	}");
			out.println("	else if(document.Form1.SCREEN_NAME.value==\"EDIT\"){");  
			out.println("		m_sav_msg=\"Modify\";");  
			out.println("	}");
			out.println("	else if(document.Form1.SCREEN_NAME.value==\"DACT\"){");  
			out.println("		m_sav_msg=\"Deactivate\";");  
			out.println("	}");
			out.println("	else if(document.Form1.SCREEN_NAME.value==\"RACT\"){");  
			out.println("		m_sav_msg=\"Reactivate\";");  
			out.println("	}");
			out.println("	else{");  
			out.println("		m_sav_msg=\"\";");  
			out.println("	}"); 
			out.println("}"); 

			out.println("function MyDialog(){"); 
			out.println("	this.valout   = new Array(10);"); 
			out.println("}"); 
											
			out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Servlet?class_in=\"+client_name+\"AF_MAS_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("		if(oBj.valout[1] !=\"Close\"){"); 
			out.println("			if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("				if(oBj.valout[1]!=\"Next\"){"); 
			out.println("					if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("						help_update_value_assign_1();"); 
	  	out.println("					}"); 
			out.println("					if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("						help_product_assign();"); 
	  	out.println("					}"); 
			out.println("					if(document.Form1.hid_help_type.value==\"3\"){"); 
			out.println("						help_value_assign_loc();"); 
	  	out.println("					}"); 
			out.println("					if(document.Form1.hid_help_type.value==\"4\"){"); 
			out.println("						help_value_assign_cli();"); 
	  	out.println("					}"); 
			out.println("				}"); 
			out.println("				else{"); 
			out.println("					Next(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("					return false;"); 
			out.println("				} "); 
			out.println("			}"); 
			out.println("			else{	"); 
			out.println("				Prev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("			}	"); 
			out.println("	 	}"); 
			out.println("	}"); 
			out.println("}"); 

			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("		HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 

			out.println("function Next (Start,End,Hid_No){"); 
			out.println("		HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			
			out.println("function help_client() {"); 
			out.println("document.Form1.hid_help_type.value=\"4\";"); 
			out.println("m_sql = \"Client_code_help_client_creation\";"); 
			out.println("m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@Y@\";"); //ClientSql1
			out.println("HelpBox('1','10','0');"); 
			out.println("}");
				
			out.println("function help_value_assign_cli() {"); 
			out.println("document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];");
			out.println("}");
			
			out.println("function help_location(){"); 
			out.println("document.Form1.hid_help_type.value=\"3\";"); 
			out.println("m_sql = \"m_help_TXT_LOCATION_CODE_sql\";"); 
			out.println("m_criteria = document.Form1.TXT_LOCATION.value+\"@Y@\";"); 
			out.println("HelpBox('1','10','0');"); 
			out.println("}");
				
			out.println("function help_value_assign_loc(){"); 
			out.println("document.Form1.TXT_LOCATION.value=oBj.valout[2];");
			out.println("}");
		
		   out.println("function help_update_product() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_TRAN_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_PRODUCT_LEVEL.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");   
			  
			out.println("function help_product_assign() {"); 
			out.println("    document.Form1.TXT_PRODUCT_LEVEL.value=oBj.valout[2];"); 
			out.println("}");
		
		  out.println("function check_date(objdd,objmm,objyy) {");						
			out.println("  if((objdd.value !=\"\")&&(objmm.value !=\"\")&&(objyy.value !=\"\")){");
			out.println("  checkMonthLength(objdd,objmm,objyy);");
			//out.println("  validate_date(objdd,objmm,objyy,document.Form1.HID_SYS_VAL_DAY,document.Form1.HID_SYS_VAL_MONTH,document.Form1.HID_SYS_VAL_YEAR);");
      out.println("}");
			out.println("}");
		
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"get_system_date();\">"); //get_system_date();load_lock();
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
			out.println("<tr> "); 
			out.println("<td height='30' class='pdn_mainHD'>"+m_header_name+"</td>"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Lease Maturity Profile</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>"); 
			out.println("<td width='10%' align='center'></td>");
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
			
			
			out.println("<table class='table' width='100%'  border=0>"); 
			
			
			/*out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_DIVISION'  class=div_input>Division </DIV></td>"); 
			out.println("<td width='15%' ><select class='txt_input' name='DIV_CODE' style='width:100'>"); 
			out.println("<option value='AF' selected>Lease</option>");
			out.println("<option value='BD' selected>Bike</option>");
			out.println("<option value='ALL'>All</option>");
			out.println("</td>");
			out.println("<td width='2%' align='left' ><b></b></td>");
			out.println("<td width='10%' align='left' >&nbsp;</td>");	
			out.println("<td width='30%' align='left' >&nbsp;</td>");	
		  out.println("</tr>"); 
			*/
			
		  rs = stmt.executeQuery(
			" SELECT DISTINCT A.branch_code ,"+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.branch_code) LOCATION "+
			" FROM "+m_schema_name+".af_co_pro_application_details a "+
			" where a.division_code='AF' "+
			" AND a.application_status='ACTIVATED' "+
			" AND A.branch_code IS NOT NULL "+
			" AND A.branch_code<>'-' "+
			" UNION ALL "+
			" SELECT 'BIKE','BIKE' LOCATION "+
			" FROM DUAL ");
			
			
			out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_LOCATION'  class=div_input>Location </DIV></td>"); 
			out.println("<td width='20%' ><select class='txt_input' name='TXT_LOCATION' style='width:130'>"); 
			out.println("<option value='ALL' selected>ALL</option>");
			while(rs.next()){
			out.println("<option value='"+rs.getString(1)+"' >"+rs.getString(2)+"</option>");
			}
			
			out.println("</td>");
			out.println("<td width='2%' align='left' ><b></b></td>");
			out.println("<td width='40%' align='left' >&nbsp;</td>");	
			//out.println("<td width='30%' align='left' >&nbsp;</td>");	
			out.println("<td width='*%' align='left'><input type='button' class='but_input1' name='BUT_REUN' value=\"Run Report\" style='width:200' onClick=\"run_report()\"></td>"); 
		  out.println("</tr>"); 
			
			
			/*out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_LOCATION'  class=div_input>Location</DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_LOCATION' maxlength='10' size='70' style='width:100' onblur=\"help_location()\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_location()\" ></td>"); 
			out.println("<td width='2%' align='left' ><b></b></td>");
			out.println("<td width='10%' align='left' >&nbsp;</td>");	
			out.println("<td width='30%' align='left' >&nbsp;</td>");	
		  out.println("</tr>"); 
		  */
			
			out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_PRODUCT_LEVEL'  class=div_input>Product Level</DIV></td>"); 
			out.println("<td width='15%' ><input class='txt_input' type='text' name='TXT_PRODUCT_LEVEL' maxlength='20' size='70' style='width:100' onblur=\"help_update_product()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_1' value=\"Help\" onClick=\"help_update_product()\"></td>"); 
			out.println("<td width='2%' align='left' ><b></b></td>");
			out.println("<td width='40%' align='left' ></td>");
			out.println("<td width='*%' align='left'><input type='button' class='but_input1' name='BUT_MON_SUMMARY' value=\"Month Wise Summary Report\" style='width:200' onClick=\"month_wise_sum_report();\"></td>"); 
			out.println("</tr>"); 
				
			out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_PAYMENT_CAT'  class=div_input>Payment Category </DIV></td>"); 
			out.println("<td width='15%' ><select class='txt_input' name='PAY_CAT' style='width:100'>"); 
			out.println("<option value='RENTAL' selected>Rental</option>");
			out.println("<option value='AMI'>AMI</option>");
			out.println("<option value='NIBSM'>NIBSM</option></select>");
			out.println("</td>");
			out.println("<td width='2%' align='left' ><b></b></td>");
			out.println("<td width='40%' align='left' ></td>");
			out.println("<td width='*%' align='left'>");
			out.println("<input type='button' class='but_input1' name='BUT_AGREE_WISE'         value=\"Agreement Wise Detail Report\" style='width:200' onClick=\"agreement_wise_detail_report()\">"); 
			out.println("<input type='button' class='but_input1' name='BUT_AGREE_WISE_SUMMARY' value=\"Agreement Wise Summary Report\" style='width:200' onClick=\"agreement_wise_summary_report()\"></td>"); 
			out.println("</tr>");
			
			out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Lessee</DIV></td>"); 
			out.println("<td width='15%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='50' style='width:100' onblur=\"help_client()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_client()\" ></td>"); 
			out.println("<td width='2%' align='left' ><b></b></td>");
			out.println("<td width='40%' align='left' ></td>");
			out.println("<td width='*%' align='left'><input type='button' class='but_input1' name='BUT_LESSEE_WISE' value=\"Lessee Wise Detail Report\" style='width:200' onClick=\"lessee_wise_detail_report()\"></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_ACT_CUT_DATE'  class=div_input>Activation Cutoff Date</DIV></td>"); 
			// Modified By Samitha Kulatilaka On 2009-10-14 (Function Call On onBlur() Event)
			out.println("<TD WIDTH=\"15%\"><input class=\"txt_input5\" type=\"text\" name=TXT_ACT_CUT_DATE_DD maxlength=\"2\" size=\"2\" onblur=checkMonthLength(document.Form1.TXT_ACT_CUT_DATE_DD,document.Form1.TXT_ACT_CUT_DATE_MM,document.Form1.TXT_ACT_CUT_DATE_YY) >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_ACT_CUT_DATE_MM  maxlength=\"2\" size=\"2\" onblur=checkMonthLength(document.Form1.TXT_ACT_CUT_DATE_DD,document.Form1.TXT_ACT_CUT_DATE_MM,document.Form1.TXT_ACT_CUT_DATE_YY)>");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_ACT_CUT_DATE_YY maxlength=\"4\" size=\"4\" onblur=checkMonthLength(document.Form1.TXT_ACT_CUT_DATE_DD,document.Form1.TXT_ACT_CUT_DATE_MM,document.Form1.TXT_ACT_CUT_DATE_YY)><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a>  ");	
			out.println("<td width='2%' align='left' ><b></b></td>");
			out.println("<td width='40%' align='left' ></td>");
			out.println("<td width='*%' align='left'><input type='button' class='but_input1' name='BUT_BRANCH_SUMMARY' value=\"Branch Wise Summary Report\" style='width:200' onClick=\"branch_wise_summary_report()\"></td>"); 
			out.println("</td> ");
			out.println("</tr>");
			
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input>Period Range </DIV></td>"); 
			// Modified By Samitha Kulatilaka On 2009-10-14 (Function Call On onBlur() Event)
			out.println("<TD WIDTH=\"15%\"><input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_DD maxlength=\"2\" size=\"2\" onblur=checkMonthLength(document.Form1.TXT_FROM_DATE_DD,document.Form1.TXT_FROM_DATE_MM,document.Form1.TXT_FROM_DATE_YY) >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_MM  maxlength=\"2\" size=\"2\" onblur=checkMonthLength(document.Form1.TXT_FROM_DATE_DD,document.Form1.TXT_FROM_DATE_MM,document.Form1.TXT_FROM_DATE_YY) >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_YY maxlength=\"4\" size=\"4\"  onblur=checkMonthLength(document.Form1.TXT_FROM_DATE_DD,document.Form1.TXT_FROM_DATE_MM,document.Form1.TXT_FROM_DATE_YY) ><a href style='{cursor:hand; }' onclick=load_calendar('1')>   Calendar</a>  ");	
			out.println("</td> ");
			out.println("<td width='2%' align='left' >To</td>");
			out.println("<td>"); 
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_DD maxlength=\"2\" size=\"2\" onblur=checkMonthLength(document.Form1.TXT_TO_DATE_DD,document.Form1.TXT_TO_DATE_MM,document.Form1.TXT_TO_DATE_YY)>");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM  maxlength=\"2\" size=\"2\" onblur=checkMonthLength(document.Form1.TXT_TO_DATE_DD,document.Form1.TXT_TO_DATE_MM,document.Form1.TXT_TO_DATE_YY) >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY maxlength=\"4\" size=\"4\" onblur=checkMonthLength(document.Form1.TXT_TO_DATE_DD,document.Form1.TXT_TO_DATE_MM,document.Form1.TXT_TO_DATE_YY) > <a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a>");	
			out.println("</td> ");
			//out.println("<td width='40%' align='left' ></td>");
			out.println("<td width='*%' align='left'><input type='button' class='but_input1' name='BUT_BRANCH_WISE' value=\"Branch Wise Detail Report\" style='width:200' onClick=\"branch_wise_detail_report()\"></td>"); 
			out.println("</tr>");
						
			out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_CONTACT_STATUS'  class=div_input>Contract Status </DIV></td>"); 
			out.println("<td width='15%' ><select class='txt_input' name='CONTRACT_STATUS' style='width:100'>"); 
			out.println("<option value='LIVE' selected>Live</option>");
			out.println("<option value='LEGAL'>Legal</option>");
			out.println("<option value='ALL'>All</option></select>");
			out.println("</td>");
			out.println("<td width='2%' align='left' ><b></b></td>");
			out.println("<td width='10%' align='left' >&nbsp;</td>");	
			out.println("<td width='30%' align='left' >&nbsp;</td>");	
			out.println("</tr>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
			out.println("</tr>"); 
			out.println("</table>"); 
			
			out.println("</table>"); 
			
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
			//out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			//out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
			//out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			//out.flush();
			}
			
			//NIBSM
			else if(chksql.equals("month_wise_sum_report_NIBSM")){
			
			String m_activ_cut_date = req.getParameter("activ_cut_date");
			String m_prod_level = req.getParameter("prod_level");
			String m_client_code = req.getParameter("client_code");
			String m_location_code =req.getParameter("location_code");
			
						
			String m_from_date = req.getParameter("from_date");
			String m_to_date = req.getParameter("to_date");
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Month Wise Summary Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<body>");
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' align='center'>   "); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td align='left' style='height: 18px' id='help_box' ><b>Month Wise Summary Report NIBMSM</b></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
						
						
		if(m_client_code.equals("")){
		
				rs = stmt.executeQuery(
				" SELECT "+
				" COUNT(DISTINCT B.FINANCE_NO)  , "+
				" TO_CHAR(B.NIBSM_EFF_DATE,'Month-YYYY') , "+
				" SUM(B.NIBSM_AMOUNT)  "+
				" FROM "+m_schema_name+".AF_TBD_MATURITY_DET A , "+m_schema_name+".AF_TBD_MATURITY B "+
				" WHERE A.APPLICATION_NO(+)=B.APPLICATION_NO  "+
				" AND   A.ENT_USER='"+m_username+"' "+
				" AND   B.ENT_USER='"+m_username+"' "+
				" AND   B.TRANSACTION_TYPE  like '%"+m_prod_level+"%' "+
				" AND   A.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')   "+
				" AND   A.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')    "+
				" AND   B.STATUS=1 "+
				" AND   B.NIBSM_AMOUNT > 0 "+
				" GROUP BY TO_CHAR(B.NIBSM_EFF_DATE,'Month-YYYY') ,TO_CHAR(B.NIBSM_EFF_DATE,'MM-YYYY')"+
				" ORDER BY  TO_DATE(TO_CHAR(B.NIBSM_EFF_DATE,'MM-YYYY'),'MM-YYYY')   ");

		
	    	}
				else{
				
				rs = stmt.executeQuery(
				" SELECT "+
				" COUNT(DISTINCT B.FINANCE_NO)  , "+
				" TO_CHAR(B.NIBSM_EFF_DATE,'Month-YYYY') , "+
				" SUM(B.NIBSM_AMOUNT)  "+
				" FROM "+m_schema_name+".AF_TBD_MATURITY_DET A , "+m_schema_name+".AF_TBD_MATURITY B "+
				" WHERE A.APPLICATION_NO(+)=B.APPLICATION_NO  "+
				" AND   A.ENT_USER='"+m_username+"' "+
				" AND   B.ENT_USER='"+m_username+"' "+
				" AND   B.TRANSACTION_TYPE  like '%"+m_prod_level+"%' "+
				" AND   A.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')   "+
				" AND   A.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')    "+
				" AND   B.STATUS=1 "+
				" AND   B.NIBSM_AMOUNT > 0 "+
				" GROUP BY TO_CHAR(B.NIBSM_EFF_DATE,'Month-YYYY') ,TO_CHAR(B.NIBSM_EFF_DATE,'MM-YYYY')"+
				" ORDER BY  TO_DATE(TO_CHAR(B.NIBSM_EFF_DATE,'MM-YYYY'),'MM-YYYY')   ");

				
				}
		
		
			boolean more = rs.next();
			int count = 1;
			
			if(!more){
			out.println("<table class='table' border='0' width=100% align='center'>   "); 
			out.println("<tr align='center' width='100%'><td>&nbsp;</td></tr>");
			out.println("<tr align='center' width='100%'><td>");
			out.println("<font color='red'>No data found....!</font>"); 
			out.println("</td></tr>"); 
			out.println("</table>"); 
			}
			else{
			out.println("<table class='table' border='0' width=100% >   "); 
			out.println("<tr class=pdn_txtpos2 >"); 
			out.println("<td width='5%'  align='left' >No.</td>"); 
			out.println("<td width='10%' align='left' >Month</td>"); 
			out.println("<td width='10%' align='left' >NIBSM Value</td>"); 
			out.println("<td width='8%' align='center' >No.of Agreements</td>"); 
			out.println("</tr>"); 
			}
			
			while(more){	
			if(count%2==1){
			out.println("<tr bgcolor=\"#FFFFFF\" >"); //class=pdn_txtpos2 
			}
			else{
			out.println("<tr bgcolor=\"#C0C0C0\" >");
			}
			out.println("<td width='5%'  align='left' >"+count+"</td>"); 
			out.println("<td width='10%' align='left' >"+rs.getString(2)+"</td>"); 
			out.println("<td width='10%' align='right' >"+nf1.format(rs.getDouble(3))+"</td>");
			out.println("<td width='8%' align='center' >"+nf.format(rs.getInt(1))+"</td>"); 
			out.println("</tr>"); 
			count = count+1;
			more = rs.next();
			}
			
			out.println("</table>"); 
			
			out.println("</body><html>");
			
			}
			
			//AMI ---------------------------------------------
			else if(chksql.equals("month_wise_sum_report_AMI")){
			
			String m_activ_cut_date = req.getParameter("activ_cut_date");
			String m_prod_level = req.getParameter("prod_level");
			String m_client_code = req.getParameter("client_code");
			String m_location_code =req.getParameter("location_code");
			
						
			String m_from_date = req.getParameter("from_date");
			String m_to_date = req.getParameter("to_date");
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Month Wise Summary Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<body>");
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' align='center'>   "); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td align='left' style='height: 18px' id='help_box' ><b>Month Wise Summary Report</b></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
						
						
		if(m_client_code.equals("")){
		
				rs = stmt.executeQuery(
				" SELECT "+
				" COUNT(DISTINCT B.FINANCE_NO)  , "+
				" TO_CHAR(B.AMI_EFF_DATE,'Month-YYYY') , "+
				" SUM(B.AMI_AMOUNT)  "+
				" FROM "+m_schema_name+".AF_TBD_MATURITY_DET A , "+m_schema_name+".AF_TBD_MATURITY B "+
				" WHERE A.APPLICATION_NO(+)=B.APPLICATION_NO  "+
				" AND   A.ENT_USER='"+m_username+"' "+
				" AND   B.ENT_USER='"+m_username+"' "+
				" AND   B.TRANSACTION_TYPE  like '%"+m_prod_level+"%' "+
				" AND   A.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')   "+
				" AND   A.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')    "+
				" AND   B.STATUS=1 "+
				" AND   B.AMI_AMOUNT > 0 "+
				" GROUP BY TO_CHAR(B.AMI_EFF_DATE,'Month-YYYY') ,TO_CHAR(B.AMI_EFF_DATE,'MM-YYYY')"+
				" ORDER BY  TO_DATE(TO_CHAR(B.AMI_EFF_DATE,'MM-YYYY'),'MM-YYYY')   ");

		
	    	}
				else{
				
				rs = stmt.executeQuery(
				" SELECT "+
				" COUNT(DISTINCT B.FINANCE_NO)  , "+
				" TO_CHAR(B.AMI_EFF_DATE,'Month-YYYY') , "+
				" SUM(B.AMI_AMOUNT)  "+
				" FROM "+m_schema_name+".AF_TBD_MATURITY_DET A , "+m_schema_name+".AF_TBD_MATURITY B "+
				" WHERE A.APPLICATION_NO(+)=B.APPLICATION_NO  "+
				" AND   A.ENT_USER='"+m_username+"' "+
				" AND   B.ENT_USER='"+m_username+"' "+
				" AND   B.TRANSACTION_TYPE  like '%"+m_prod_level+"%' "+
				" AND   A.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')   "+
				" AND   A.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')    "+
				" AND   B.STATUS=1 "+
				" AND   B.AMI_AMOUNT > 0 "+
				" GROUP BY TO_CHAR(B.AMI_EFF_DATE,'Month-YYYY') ,TO_CHAR(B.AMI_EFF_DATE,'MM-YYYY')"+
				" ORDER BY  TO_DATE(TO_CHAR(B.AMI_EFF_DATE,'MM-YYYY'),'MM-YYYY')   ");

				
				}
		
		
			boolean more = rs.next();
			int count = 1;
			
			if(!more){
			out.println("<table class='table' border='0' width=100% align='center'>   "); 
			out.println("<tr align='center' width='100%'><td>&nbsp;</td></tr>");
			out.println("<tr align='center' width='100%'><td>");
			out.println("<font color='red'>No data found....!</font>"); 
			out.println("</td></tr>"); 
			out.println("</table>"); 
			}
			else{
			out.println("<table class='table' border='0' width=100% >   "); 
			out.println("<tr class=pdn_txtpos2 >"); 
			out.println("<td width='5%'  align='left' >No.</td>"); 
			out.println("<td width='10%' align='left' >Month</td>"); 
			out.println("<td width='10%' align='left' >AMI Value</td>"); 
			out.println("<td width='8%' align='center' >No.of Agreements</td>"); 
			out.println("</tr>"); 
			}
			
			while(more){	
			if(count%2==1){
			out.println("<tr bgcolor=\"#FFFFFF\" >"); //class=pdn_txtpos2 
			}
			else{
			out.println("<tr bgcolor=\"#C0C0C0\" >");
			}
			out.println("<td width='5%'  align='left' >"+count+"</td>"); 
			out.println("<td width='10%' align='left' >"+rs.getString(2)+"</td>"); 
			out.println("<td width='10%' align='right' >"+nf1.format(rs.getDouble(3))+"</td>");
			out.println("<td width='8%' align='center' >"+nf.format(rs.getInt(1))+"</td>"); 
			out.println("</tr>"); 
			count = count+1;
			more = rs.next();
			}
			
			out.println("</table>"); 
			
			out.println("</body><html>");
			
			}
			else if(chksql.equals("month_wise_sum_report")){
			
			String m_activ_cut_date = req.getParameter("activ_cut_date");
			String m_prod_level = req.getParameter("prod_level");
			String m_client_code = req.getParameter("client_code");
			String m_location_code =req.getParameter("location_code");
			
			String m_status =req.getParameter("status"); 
			String m_from_date = req.getParameter("from_date");
			String m_to_date = req.getParameter("to_date");
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Month Wise Summary Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<body>");
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' align='center'>   "); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td align='left' style='height: 18px' id='help_box' ><b>Month Wise Summary Report</b></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
						
						
		if(m_client_code.equals("")){
		
		if(m_location_code.equals("ALL")){
		
		/*  rs = stmt.executeQuery(
				" SELECT  TO_CHAR(D.RENTAL_DATE,'Month-YYYY') MONTH,"+//1
																"         COUNT(DISTINCT A.FINANCE_NO), "+//2
																"         SUM(D.CAPITAL_AMOUNT),"+//3
																"         SUM(D.NET_RENTAL_AMOUNT) , "+ //4
																"         SUM(D.INTEREST_AMOUNT), "+ //5 
																"         "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE)  "+//6
																" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
																"      "+m_schema_name+".AF_CO_MAS_CLIENT B, "+
																"      "+m_schema_name+".CO_CO_MAS_EMPLOYEE C, "+
																"      "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT D "+
																" WHERE  A.CLIENT_CODE=B.CLIENT_CODE "+
																" AND    A.COLLECTION_OFFICER=C.EMP_CODE "+
	 															" AND    A.APPLICATION_NO = D.APPLICATION_NO "+
	 															" AND    APPLICATION_STATUS='ACTIVATED' "+
																//" AND    A.DIVISION_CODE='AF' "+	
																" AND    A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
																" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
																" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY') ) "+
																" AND    D.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																" AND    D.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																" GROUP BY TO_CHAR(D.RENTAL_DATE,'Month-YYYY'),TO_CHAR(D.RENTAL_DATE,'MM-YYYY'),A.TRANSACTION_TYPE "+
																" ORDER BY TO_DATE(TO_CHAR(D.RENTAL_DATE,'MM-YYYY'),'MM-YYYY') "); //TO_CHAR(D.RENTAL_DATE,'MM-YYYY')
																
*/			

        /**** Comment by ns 15-09-2009  
				rs = stmt.executeQuery(
																	" SELECT  TO_CHAR(B.RENTAL_DATE,'MONTH-YYYY') MONTH,"+ //1
																	" COUNT(DISTINCT A.FINANCE_NO), "+ //2
																	" SUM(B.CAPITAL_AMOUNT),"+ //3
																	" SUM(B.NET_RENTAL_AMOUNT) ,  "+ //4
																	" SUM(B.INTEREST_AMOUNT),  "+ //5 
																	" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE)  "+ //6
																	" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT B  "+
																	"      ,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C,"+m_schema_name+".AF_CO_PRO_INVOICE D "+
																	" WHERE C.APPLICATION_NO = B.APPLICATION_NO "+
																	" AND   A.APPLICATION_NO = B.APPLICATION_NO  "+
																	" AND   C.INVOICE_NO     = B.PRO_INVOICE_NO "+
																	" AND   C.PRICING_NO     = B.PRICING_NO "+
																	" AND   B.INVOICE_NO     = D.INVOICE_NO(+) "+
																	//" AND   D.ACTIVE_STATUS  = 'Y'  "+
																	" AND   C.ACTIVE_STATUS IN ('Y') "+
																	" AND   A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
																	" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS   "+
																	" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY')  "+
																	" AND     APPLICATION_STATUS =  'ACTIVATED' "+
																	" OR    ( APPLICATION_STATUS <> 'ACTIVATED' "+
																	" AND    TO_DATE("+m_schema_name+".AF_CO_GET_TER_DATE(FINANCE_NO, '"+m_activ_cut_date+"'),'DD-MM-YYYY')  > TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY')  )"+
																	" )  "+
																	" AND    B.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
																	" AND    B.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')   "+
														S			" GROUP BY TO_CHAR(B.RENTAL_DATE,'MONTH-YYYY'),TO_CHAR(B.RENTAL_DATE,'MM-YYYY'),A.TRANSACTION_TYPE  "+
																	" ORDER BY TO_DATE(TO_CHAR(B.RENTAL_DATE,'MM-YYYY'),'MM-YYYY') ");
																	
			*******************///////////////			
			
			
																	
																	rs = stmt.executeQuery(
																	" SELECT  TO_CHAR(B.RENTAL_DATE,'MONTH-YYYY') MONTH,"+ //1
																	" COUNT(DISTINCT A.FINANCE_NO), "+ //2
																	" SUM(B.CAPITAL_AMOUNT),"+ //3
																	" SUM(B.NET_RENTAL_AMOUNT) ,  "+ //4
																	" SUM(B.INTEREST_AMOUNT),  "+ //5 
																	" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE)  "+ //6
																	" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT B  "+
																	"      ,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C,"+m_schema_name+".AF_CO_PRO_INVOICE D "+
																	" WHERE C.APPLICATION_NO = B.APPLICATION_NO "+
																	" AND   A.APPLICATION_NO = B.APPLICATION_NO  "+
																	" AND   C.INVOICE_NO     = B.PRO_INVOICE_NO "+
																	" AND   C.PRICING_NO     = B.PRICING_NO "+
																	" AND   B.INVOICE_NO     = D.INVOICE_NO(+) "+
																	" AND   C.ACTIVE_STATUS IN ('Y') "+
																	" AND   A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
																	" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS   "+
																	" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY')  "+
																	" AND UPPER("+m_schema_name+".AF_CO_GET_APP_STATUS(APPLICATION_NO))='ACTIVATED'  "+
																	" )  "+
																	" AND    B.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
																	" AND    B.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')   "+
																	" GROUP BY TO_CHAR(B.RENTAL_DATE,'MONTH-YYYY'),TO_CHAR(B.RENTAL_DATE,'MM-YYYY'),A.TRANSACTION_TYPE  "+
																	" ORDER BY TO_DATE(TO_CHAR(B.RENTAL_DATE,'MM-YYYY'),'MM-YYYY') ");



		}
		  else if(m_location_code.equals("BIKE")){
		
			 /* rs = stmt.executeQuery(
				" SELECT  TO_CHAR(D.RENTAL_DATE,'Month-YYYY') MONTH,"+//1
																"         COUNT(DISTINCT A.FINANCE_NO), "+//2
																"         SUM(D.CAPITAL_AMOUNT),"+//3
																"         SUM(D.NET_RENTAL_AMOUNT) , "+ //4
																"         SUM(D.INTEREST_AMOUNT), "+ //5 
																"         "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE)  "+//6
																" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
																"      "+m_schema_name+".AF_CO_MAS_CLIENT B, "+
																"      "+m_schema_name+".CO_CO_MAS_EMPLOYEE C, "+
																"      "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT D "+
																" WHERE  A.CLIENT_CODE=B.CLIENT_CODE "+
																" AND    A.COLLECTION_OFFICER=C.EMP_CODE "+
	 															" AND    A.APPLICATION_NO = D.APPLICATION_NO "+
	 															" AND    APPLICATION_STATUS='ACTIVATED' "+
																" AND    A.DIVISION_CODE='BD' "+	
																" AND    A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
																" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
																" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY') ) "+
																" AND    D.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																" AND    D.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																" GROUP BY TO_CHAR(D.RENTAL_DATE,'Month-YYYY'),TO_CHAR(D.RENTAL_DATE,'MM-YYYY'),A.TRANSACTION_TYPE "+
																" ORDER BY TO_DATE(TO_CHAR(D.RENTAL_DATE,'MM-YYYY'),'MM-YYYY') "); //TO_CHAR(D.RENTAL_DATE,'MM-YYYY')
         */
					
							/***		rs = stmt.executeQuery(
																	" SELECT  TO_CHAR(B.RENTAL_DATE,'MONTH-YYYY') MONTH,"+ //1
																	" COUNT(DISTINCT A.FINANCE_NO), "+ //2
																	" SUM(B.CAPITAL_AMOUNT),"+ //3
																	" SUM(B.NET_RENTAL_AMOUNT) ,  "+ //4
																	" SUM(B.INTEREST_AMOUNT),  "+ //5 
																	" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE)  "+ //6
																	" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT B  "+
																	"      ,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C,"+m_schema_name+".AF_CO_PRO_INVOICE D "+
																	" WHERE C.APPLICATION_NO = B.APPLICATION_NO "+
																	" AND   A.DIVISION_CODE='BD' "+	
																	" AND   A.APPLICATION_NO = B.APPLICATION_NO  "+
																	" AND   C.INVOICE_NO     = B.PRO_INVOICE_NO "+
																	" AND   C.PRICING_NO     = B.PRICING_NO "+
																	" AND   B.INVOICE_NO     = D.INVOICE_NO(+) "+
																	//" AND   D.ACTIVE_STATUS  = 'Y'  "+
																	" AND   C.ACTIVE_STATUS IN ('Y') "+
																	" AND    A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
																	" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS   "+
																	" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY')  "+
																	" AND     APPLICATION_STATUS =  'ACTIVATED' "+
																	" OR    ( APPLICATION_STATUS <> 'ACTIVATED' "+
																	" AND    TO_DATE("+m_schema_name+".AF_CO_GET_TER_DATE(FINANCE_NO, '"+m_activ_cut_date+"'),'DD-MM-YYYY')  > TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY')  )"+
																	" )  "+
																	" AND    B.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
																	" AND    B.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')   "+
																	" GROUP BY TO_CHAR(B.RENTAL_DATE,'MONTH-YYYY'),TO_CHAR(B.RENTAL_DATE,'MM-YYYY'),A.TRANSACTION_TYPE  "+
																	" ORDER BY TO_DATE(TO_CHAR(B.RENTAL_DATE,'MM-YYYY'),'MM-YYYY') ");
																	
							***/
							
							rs = stmt.executeQuery(
																	" SELECT  TO_CHAR(B.RENTAL_DATE,'MONTH-YYYY') MONTH,"+ //1
																	" COUNT(DISTINCT A.FINANCE_NO), "+ //2
																	" SUM(B.CAPITAL_AMOUNT),"+ //3
																	" SUM(B.NET_RENTAL_AMOUNT) ,  "+ //4
																	" SUM(B.INTEREST_AMOUNT),  "+ //5 
																	" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE)  "+ //6
																	" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT B  "+
																	"      ,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C,"+m_schema_name+".AF_CO_PRO_INVOICE D "+
																	" WHERE C.APPLICATION_NO = B.APPLICATION_NO "+
																	" AND   A.DIVISION_CODE='BD' "+
																	" AND   A.APPLICATION_NO = B.APPLICATION_NO  "+
																	" AND   C.INVOICE_NO     = B.PRO_INVOICE_NO "+
																	" AND   C.PRICING_NO     = B.PRICING_NO "+
																	" AND   B.INVOICE_NO     = D.INVOICE_NO(+) "+
																	" AND   C.ACTIVE_STATUS IN ('Y') "+
																	" AND   A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
																	" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS   "+
																	" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY')  "+
																	" AND UPPER("+m_schema_name+".AF_CO_GET_APP_STATUS(APPLICATION_NO))='ACTIVATED'  "+
																	" )  "+
																	" AND    B.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
																	" AND    B.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')   "+
																	" GROUP BY TO_CHAR(B.RENTAL_DATE,'MONTH-YYYY'),TO_CHAR(B.RENTAL_DATE,'MM-YYYY'),A.TRANSACTION_TYPE  "+
																	" ORDER BY TO_DATE(TO_CHAR(B.RENTAL_DATE,'MM-YYYY'),'MM-YYYY') ");

		
		
		}else{
		
		
		//out.println(
			/***  rs = stmt.executeQuery(
				" SELECT  TO_CHAR(D.RENTAL_DATE,'Month-YYYY') MONTH,"+//1
																"         COUNT(DISTINCT A.FINANCE_NO), "+//2
																"         SUM(D.CAPITAL_AMOUNT),"+//3
																"         SUM(D.NET_RENTAL_AMOUNT) , "+ //4
																"         SUM(D.INTEREST_AMOUNT), "+ //5 
																"         "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE)  "+//6
																" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
																"      "+m_schema_name+".AF_CO_MAS_CLIENT B, "+
																"      "+m_schema_name+".CO_CO_MAS_EMPLOYEE C, "+
																"      "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT D "+
																" WHERE  A.CLIENT_CODE=B.CLIENT_CODE "+
																" AND    A.COLLECTION_OFFICER=C.EMP_CODE "+
	 															" AND    A.APPLICATION_NO = D.APPLICATION_NO "+
	 															" AND    APPLICATION_STATUS='ACTIVATED' "+
																" AND    A.DIVISION_CODE='AF' "+	
				 											  " AND    C.LOCATION_CODE  ='"+m_location_code+"' "+
																" AND    A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
																" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
																" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY') ) "+
																//" AND    TO_CHAR(A.ACTIVATED_DATE,'MM') <= TO_CHAR(TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY'),'MM') "+
																" AND    D.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																" AND    D.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																" GROUP BY TO_CHAR(D.RENTAL_DATE,'Month-YYYY'),TO_CHAR(D.RENTAL_DATE,'MM-YYYY'),A.TRANSACTION_TYPE "+
																" ORDER BY TO_DATE(TO_CHAR(D.RENTAL_DATE,'MM-YYYY'),'MM-YYYY') "); //TO_CHAR(D.RENTAL_DATE,'MM-YYYY')
				*****/
				
													rs = stmt.executeQuery(
																	" SELECT  TO_CHAR(B.RENTAL_DATE,'MONTH-YYYY') MONTH,"+ //1
																	" COUNT(DISTINCT A.FINANCE_NO), "+ //2
																	" SUM(B.CAPITAL_AMOUNT),"+ //3
																	" SUM(B.NET_RENTAL_AMOUNT) ,  "+ //4
																	" SUM(B.INTEREST_AMOUNT),  "+ //5 
																	" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE)  "+ //6
																	" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT B  "+
																	"      ,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C,"+m_schema_name+".AF_CO_PRO_INVOICE D "+
																	" WHERE C.APPLICATION_NO = B.APPLICATION_NO "+
																	" AND   A.DIVISION_CODE='BD' "+	
																	" AND   A.APPLICATION_NO = B.APPLICATION_NO  "+
																	" AND   C.INVOICE_NO     = B.PRO_INVOICE_NO "+
																	" AND   C.PRICING_NO     = B.PRICING_NO "+
																	" AND   B.INVOICE_NO     = D.INVOICE_NO(+) "+
																	//" AND   D.ACTIVE_STATUS  = 'Y'  "+
																	" AND   C.ACTIVE_STATUS IN ('Y') "+
																	//" AND    C.LOCATION_CODE  ='"+m_location_code+"' "+
																	" AND    A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
																	" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS   "+
																	" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY')  "+
																	" )  "+
																	" AND    B.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
																	" AND    B.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')   "+
																	" GROUP BY TO_CHAR(B.RENTAL_DATE,'MONTH-YYYY'),TO_CHAR(B.RENTAL_DATE,'MM-YYYY'),A.TRANSACTION_TYPE  "+
																	" ORDER BY TO_DATE(TO_CHAR(B.RENTAL_DATE,'MM-YYYY'),'MM-YYYY') ");
																
			}													

		
	    	}
				else{
				

					/****  rs = stmt.executeQuery( " SELECT  TO_CHAR(D.RENTAL_DATE,'Month-YYYY') MONTH,"+//1
																"         COUNT(DISTINCT A.FINANCE_NO), "+//2
																"         SUM(D.CAPITAL_AMOUNT),"+//3
																"         SUM(D.NET_RENTAL_AMOUNT) , "+ //4
																"         SUM(D.INTEREST_AMOUNT), "+ //5 
																"         "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE)  "+//6
																" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
																"      "+m_schema_name+".AF_CO_MAS_CLIENT B, "+
																"      "+m_schema_name+".CO_CO_MAS_EMPLOYEE C, "+
																"      "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT D "+
																" WHERE  A.CLIENT_CODE=B.CLIENT_CODE "+
																" AND    A.COLLECTION_OFFICER=C.EMP_CODE "+
	 															" AND    A.APPLICATION_NO = D.APPLICATION_NO "+
	 															" AND    APPLICATION_STATUS='ACTIVATED' "+
																" AND    A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
																" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
																" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY')  "+
																" AND  CLIENT_CODE = '"+m_client_code+"' )  "+
																" AND    D.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																" AND    D.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																" GROUP BY TO_CHAR(D.RENTAL_DATE,'Month-YYYY'),TO_CHAR(D.RENTAL_DATE,'MM-YYYY'),A.TRANSACTION_TYPE "+
																" ORDER BY TO_DATE(TO_CHAR(D.RENTAL_DATE,'MM-YYYY'),'MM-YYYY') "); //TO_CHAR(D.RENTAL_DATE,'MM-YYYY')
          *****/
					
					
																		rs = stmt.executeQuery(
																	" SELECT  TO_CHAR(B.RENTAL_DATE,'MONTH-YYYY') MONTH,"+ //1
																	" COUNT(DISTINCT A.FINANCE_NO), "+ //2
																	" SUM(B.CAPITAL_AMOUNT),"+ //3
																	" SUM(B.NET_RENTAL_AMOUNT) ,  "+ //4
																	" SUM(B.INTEREST_AMOUNT),  "+ //5 
																	" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE)  "+ //6
																	" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT B  "+
																	"      ,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C,"+m_schema_name+".AF_CO_PRO_INVOICE D "+
																	" WHERE C.APPLICATION_NO = B.APPLICATION_NO "+
																	" AND   A.DIVISION_CODE='BD' "+	
																	" AND   A.APPLICATION_NO = B.APPLICATION_NO  "+
																	" AND   C.INVOICE_NO     = B.PRO_INVOICE_NO "+
																	" AND   C.PRICING_NO     = B.PRICING_NO "+
																	" AND   B.INVOICE_NO     = D.INVOICE_NO(+) "+
																	//" AND   D.ACTIVE_STATUS  = 'Y'  "+
																	" AND   C.ACTIVE_STATUS IN ('Y') "+
																	" AND    A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
																	" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS   "+
																	" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY')  "+
																	" AND    A.CLIENT_CODE = '"+m_client_code+"'   "+
																	" )  "+
																	" AND    B.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
																	" AND    B.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')   "+
																	" GROUP BY TO_CHAR(B.RENTAL_DATE,'MONTH-YYYY'),TO_CHAR(B.RENTAL_DATE,'MM-YYYY'),A.TRANSACTION_TYPE  "+
																	" ORDER BY TO_DATE(TO_CHAR(B.RENTAL_DATE,'MM-YYYY'),'MM-YYYY') ");
				
				}
		
		
			boolean more = rs.next();
			int count = 1;
			
			if(!more){
			out.println("<table class='table' border='0' width=100% align='center'>   "); 
			out.println("<tr align='center' width='100%'><td>&nbsp;</td></tr>");
			out.println("<tr align='center' width='100%'><td>");
			out.println("<font color='red'>No data found....!</font>"); 
			out.println("</td></tr>"); 
			out.println("</table>"); 
			}
			else{
			out.println("<table class='table' border='0' width=100% >   "); 
			out.println("<tr class=pdn_txtpos2 >"); 
			out.println("<td width='5%'  align='left' >No.</td>"); 
			out.println("<td width='10%' align='left' >Month</td>"); 
			out.println("<td width='10%' align='left' >Product</td>"); 
			out.println("<td width='15%' align='right' >Rent</td>"); 
			out.println("<td width='15%' align='right' >Principle</td>"); 
			out.println("<td width='15%' align='right' >Income</td>");
			out.println("<td width='8%' align='center' >No.of Agreements</td>"); 
			out.println("</tr>"); 
			}
			
			while(more){	
			if(count%2==1){
			out.println("<tr bgcolor=\"#FFFFFF\" >"); //class=pdn_txtpos2 
			}
			else{
			out.println("<tr bgcolor=\"#C0C0C0\" >");
			}
			out.println("<td width='5%'  align='left' >"+count+"</td>"); 
			out.println("<td width='10%' align='left' >"+rs.getString(1)+"</td>"); 
			out.println("<td width='10%' align='left' >"+rs.getString(6)+"</td>"); 
			out.println("<td width='15%' align='right' >"+nf1.format(rs.getDouble(4))+"</td>"); 
			out.println("<td width='15%' align='right' >"+nf1.format(rs.getDouble(3))+"</td>"); 
			out.println("<td width='15%' align='right' >"+nf1.format(rs.getDouble(5))+"</td>");
			out.println("<td width='8%' align='center' >"+nf.format(rs.getDouble(2))+"</td>"); 
			out.println("</tr>"); 
			count = count+1;
			more = rs.next();
			}
			
			out.println("</table>"); 
			
			out.println("</body><html>");
			
			}
			
			
			
			else if(chksql.equals("month_wise_sum_report_new")){
			
			String m_activ_cut_date = req.getParameter("activ_cut_date");
			String m_prod_level = req.getParameter("prod_level");
			String m_client_code = req.getParameter("client_code");
			String m_location_code =req.getParameter("location_code");
			
			String m_status =req.getParameter("status"); 
			String m_from_date = req.getParameter("from_date");
			String m_to_date = req.getParameter("to_date");
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Month Wise Summary Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<body>");
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' align='center'>   "); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td align='left' style='height: 18px' id='help_box' ><b>Month Wise Summary Report</b></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
						
						
		if(m_client_code.equals("")){
		
		if(m_location_code.equals("ALL")){
		
			
																	
																/*	rs = stmt.executeQuery(
																	" SELECT  TO_CHAR(B.RENTAL_DATE,'MONTH-YYYY') MONTH,"+ //1
																	" COUNT(DISTINCT A.FINANCE_NO), "+ //2
																	" SUM(B.CAPITAL_AMOUNT),"+ //3
																	" SUM(B.NET_RENTAL_AMOUNT) ,  "+ //4
																	" SUM(B.INTEREST_AMOUNT),  "+ //5 
																	" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE)  "+ //6
																	" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT B  "+
																	"      ,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C,"+m_schema_name+".AF_CO_PRO_INVOICE D "+
																	" WHERE C.APPLICATION_NO = B.APPLICATION_NO "+
																	" AND   A.APPLICATION_NO = B.APPLICATION_NO  "+
																	" AND   C.INVOICE_NO     = B.PRO_INVOICE_NO "+
																	" AND   C.PRICING_NO     = B.PRICING_NO "+
																	" AND   B.INVOICE_NO     = D.INVOICE_NO(+) "+
																	" AND   C.ACTIVE_STATUS IN ('Y') "+
																	" AND   A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
																	" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS   "+
																	" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY')  "+
																	" AND UPPER("+m_schema_name+".AF_CO_GET_APP_STATUS(APPLICATION_NO))='ACTIVATED'  "+
																	" )  "+
																	" AND    B.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
																	" AND    B.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')   "+
																	" GROUP BY TO_CHAR(B.RENTAL_DATE,'MONTH-YYYY'),TO_CHAR(B.RENTAL_DATE,'MM-YYYY'),A.TRANSACTION_TYPE  "+
																	" ORDER BY TO_DATE(TO_CHAR(B.RENTAL_DATE,'MM-YYYY'),'MM-YYYY') ");
																	*/
																	
																	rs = stmt.executeQuery(
																	//out.println(
																	" SELECT "+
																	" TO_CHAR(A.RENTAL_DATE,'MONTH-YYYY') MONTH ,"+
																	" COUNT(A.APPLICATION_NO), "+
																	" SUM(A.CAPITAL_AMOUNT) , "+
																	" SUM(A.NET_RENTAL_AMOUNT) , "+
																	" SUM(A.INTEREST_AMOUNT), "+
																	" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(B.TRANSACTION_TYPE)  "+ //6
																	" FROM "+m_schema_name+".AF_TBD_MATURITY_DET A , "+m_schema_name+".AF_TBD_MATURITY B "+
																	" WHERE A.APPLICATION_NO(+)=B.APPLICATION_NO  "+
																	" AND   A.ENT_USER='"+m_username+"' "+
																	" AND   B.ENT_USER='"+m_username+"' "+
																	" AND   B.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+ //added by ns 15-12-2009
																	" AND   A.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')   "+
																	" AND   A.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')    "+
																	" AND   B.STATUS=1 "+
																	" GROUP BY TO_CHAR(A.RENTAL_DATE,'MONTH-YYYY'),TO_CHAR(A.RENTAL_DATE,'MM-YYYY'),B.TRANSACTION_TYPE   "+
																	" ORDER BY TO_DATE(TO_CHAR(A.RENTAL_DATE,'MM-YYYY'),'MM-YYYY')  ");



		}
		  else if(m_location_code.equals("BIKE")){
		
			 /* rs = stmt.executeQuery(
				" SELECT  TO_CHAR(D.RENTAL_DATE,'Month-YYYY') MONTH,"+//1
																"         COUNT(DISTINCT A.FINANCE_NO), "+//2
																"         SUM(D.CAPITAL_AMOUNT),"+//3
																"         SUM(D.NET_RENTAL_AMOUNT) , "+ //4
																"         SUM(D.INTEREST_AMOUNT), "+ //5 
																"         "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE)  "+//6
																" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
																"      "+m_schema_name+".AF_CO_MAS_CLIENT B, "+
																"      "+m_schema_name+".CO_CO_MAS_EMPLOYEE C, "+
																"      "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT D "+
																" WHERE  A.CLIENT_CODE=B.CLIENT_CODE "+
																" AND    A.COLLECTION_OFFICER=C.EMP_CODE "+
	 															" AND    A.APPLICATION_NO = D.APPLICATION_NO "+
	 															" AND    APPLICATION_STATUS='ACTIVATED' "+
																" AND    A.DIVISION_CODE='BD' "+	
																" AND    A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
																" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
																" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY') ) "+
																" AND    D.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																" AND    D.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																" GROUP BY TO_CHAR(D.RENTAL_DATE,'Month-YYYY'),TO_CHAR(D.RENTAL_DATE,'MM-YYYY'),A.TRANSACTION_TYPE "+
																" ORDER BY TO_DATE(TO_CHAR(D.RENTAL_DATE,'MM-YYYY'),'MM-YYYY') "); //TO_CHAR(D.RENTAL_DATE,'MM-YYYY')
         */
					
							/***		rs = stmt.executeQuery(
																	" SELECT  TO_CHAR(B.RENTAL_DATE,'MONTH-YYYY') MONTH,"+ //1
																	" COUNT(DISTINCT A.FINANCE_NO), "+ //2
																	" SUM(B.CAPITAL_AMOUNT),"+ //3
																	" SUM(B.NET_RENTAL_AMOUNT) ,  "+ //4
																	" SUM(B.INTEREST_AMOUNT),  "+ //5 
																	" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE)  "+ //6
																	" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT B  "+
																	"      ,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C,"+m_schema_name+".AF_CO_PRO_INVOICE D "+
																	" WHERE C.APPLICATION_NO = B.APPLICATION_NO "+
																	" AND   A.DIVISION_CODE='BD' "+	
																	" AND   A.APPLICATION_NO = B.APPLICATION_NO  "+
																	" AND   C.INVOICE_NO     = B.PRO_INVOICE_NO "+
																	" AND   C.PRICING_NO     = B.PRICING_NO "+
																	" AND   B.INVOICE_NO     = D.INVOICE_NO(+) "+
																	//" AND   D.ACTIVE_STATUS  = 'Y'  "+
																	" AND   C.ACTIVE_STATUS IN ('Y') "+
																	" AND    A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
																	" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS   "+
																	" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY')  "+
																	" AND     APPLICATION_STATUS =  'ACTIVATED' "+
																	" OR    ( APPLICATION_STATUS <> 'ACTIVATED' "+
																	" AND    TO_DATE("+m_schema_name+".AF_CO_GET_TER_DATE(FINANCE_NO, '"+m_activ_cut_date+"'),'DD-MM-YYYY')  > TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY')  )"+
																	" )  "+
																	" AND    B.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
																	" AND    B.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')   "+
																	" GROUP BY TO_CHAR(B.RENTAL_DATE,'MONTH-YYYY'),TO_CHAR(B.RENTAL_DATE,'MM-YYYY'),A.TRANSACTION_TYPE  "+
																	" ORDER BY TO_DATE(TO_CHAR(B.RENTAL_DATE,'MM-YYYY'),'MM-YYYY') ");
																	
							***/
							
							/************************************* COMMENT BY NS 17-11-2009 
							rs = stmt.executeQuery(
																	" SELECT  TO_CHAR(B.RENTAL_DATE,'MONTH-YYYY') MONTH,"+ //1
																	" COUNT(DISTINCT A.FINANCE_NO), "+ //2
																	" SUM(B.CAPITAL_AMOUNT),"+ //3
																	" SUM(B.NET_RENTAL_AMOUNT) ,  "+ //4
																	" SUM(B.INTEREST_AMOUNT),  "+ //5 
																	" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE)  "+ //6
																	" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT B  "+
																	"      ,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C,"+m_schema_name+".AF_CO_PRO_INVOICE D "+
																	" WHERE C.APPLICATION_NO = B.APPLICATION_NO "+
																	" AND   A.DIVISION_CODE='BD' "+
																	" AND   A.APPLICATION_NO = B.APPLICATION_NO  "+
																	" AND   C.INVOICE_NO     = B.PRO_INVOICE_NO "+
																	" AND   C.PRICING_NO     = B.PRICING_NO "+
																	" AND   B.INVOICE_NO     = D.INVOICE_NO(+) "+
																	" AND   C.ACTIVE_STATUS IN ('Y') "+
																	" AND   A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
																	" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS   "+
																	" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY')  "+
																	" AND UPPER("+m_schema_name+".AF_CO_GET_APP_STATUS(APPLICATION_NO))='ACTIVATED'  "+
																	" )  "+
																	" AND    B.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
																	" AND    B.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')   "+
																	" GROUP BY TO_CHAR(B.RENTAL_DATE,'MONTH-YYYY'),TO_CHAR(B.RENTAL_DATE,'MM-YYYY'),A.TRANSACTION_TYPE  "+
																	" ORDER BY TO_DATE(TO_CHAR(B.RENTAL_DATE,'MM-YYYY'),'MM-YYYY') ");

		       ***********/
						
						
															rs = stmt.executeQuery(
															    " SELECT "+
																	" TO_CHAR(A.RENTAL_DATE,'MONTH-YYYY') MONTH ,"+
																	" COUNT(A.APPLICATION_NO), "+
																	" SUM(A.CAPITAL_AMOUNT) , "+
																	" SUM(A.NET_RENTAL_AMOUNT) , "+
																	" SUM(A.INTEREST_AMOUNT), "+
																	" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE)  "+ //6
																	" FROM "+m_schema_name+".AF_TBD_MATURITY_DET A , "+m_schema_name+".AF_TBD_MATURITY B "+
																	" WHERE A.APPLICATION_NO(+)=B.APPLICATION_NO  "+
																	" AND   B.DIVISION_CODE='BD' "+
																	" AND   A.ENT_USER='"+m_username+"' "+
																	" AND   B.ENT_USER='"+m_username+"' "+
																	" AND   A.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')   "+
																	" AND   A.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')    "+
																	" AND   B.STATUS=1 "+
																	" GROUP BY A.APPLICATION_NO,TO_CHAR(A.RENTAL_DATE,'MONTH-YYYY'),TO_CHAR(A.RENTAL_DATE,'MM-YYYY'),B.TRANSACTION_TYPE   "+
																	" ORDER BY TO_DATE(TO_CHAR(A.RENTAL_DATE,'MM-YYYY'),'MM-YYYY')  ");
		
		}else{
		
		
		//out.println(
			/***  rs = stmt.executeQuery(
				" SELECT  TO_CHAR(D.RENTAL_DATE,'Month-YYYY') MONTH,"+//1
																"         COUNT(DISTINCT A.FINANCE_NO), "+//2
																"         SUM(D.CAPITAL_AMOUNT),"+//3
																"         SUM(D.NET_RENTAL_AMOUNT) , "+ //4
																"         SUM(D.INTEREST_AMOUNT), "+ //5 
																"         "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE)  "+//6
																" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
																"      "+m_schema_name+".AF_CO_MAS_CLIENT B, "+
																"      "+m_schema_name+".CO_CO_MAS_EMPLOYEE C, "+
																"      "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT D "+
																" WHERE  A.CLIENT_CODE=B.CLIENT_CODE "+
																" AND    A.COLLECTION_OFFICER=C.EMP_CODE "+
	 															" AND    A.APPLICATION_NO = D.APPLICATION_NO "+
	 															" AND    APPLICATION_STATUS='ACTIVATED' "+
																" AND    A.DIVISION_CODE='AF' "+	
				 											  " AND    C.LOCATION_CODE  ='"+m_location_code+"' "+
																" AND    A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
																" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
																" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY') ) "+
																//" AND    TO_CHAR(A.ACTIVATED_DATE,'MM') <= TO_CHAR(TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY'),'MM') "+
																" AND    D.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																" AND    D.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																" GROUP BY TO_CHAR(D.RENTAL_DATE,'Month-YYYY'),TO_CHAR(D.RENTAL_DATE,'MM-YYYY'),A.TRANSACTION_TYPE "+
																" ORDER BY TO_DATE(TO_CHAR(D.RENTAL_DATE,'MM-YYYY'),'MM-YYYY') "); //TO_CHAR(D.RENTAL_DATE,'MM-YYYY')
				*****/
				
												/****************************** COMMENT BY NS 17-11-2009 
												rs = stmt.executeQuery(
																	" SELECT  TO_CHAR(B.RENTAL_DATE,'MONTH-YYYY') MONTH,"+ //1
																	" COUNT(DISTINCT A.FINANCE_NO), "+ //2
																	" SUM(B.CAPITAL_AMOUNT),"+ //3
																	" SUM(B.NET_RENTAL_AMOUNT) ,  "+ //4
																	" SUM(B.INTEREST_AMOUNT),  "+ //5 
																	" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE)  "+ //6
																	" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT B  "+
																	"      ,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C,"+m_schema_name+".AF_CO_PRO_INVOICE D "+
																	" WHERE C.APPLICATION_NO = B.APPLICATION_NO "+
																	" AND   A.DIVISION_CODE='BD' "+	
																	" AND   A.APPLICATION_NO = B.APPLICATION_NO  "+
																	" AND   C.INVOICE_NO     = B.PRO_INVOICE_NO "+
																	" AND   C.PRICING_NO     = B.PRICING_NO "+
																	" AND   B.INVOICE_NO     = D.INVOICE_NO(+) "+
																	//" AND   D.ACTIVE_STATUS  = 'Y'  "+
																	" AND   C.ACTIVE_STATUS IN ('Y') "+
																	//" AND    C.LOCATION_CODE  ='"+m_location_code+"' "+
																	" AND    A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
																	" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS   "+
																	" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY')  "+
																	" )  "+
																	" AND    B.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
																	" AND    B.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')   "+
																	" GROUP BY TO_CHAR(B.RENTAL_DATE,'MONTH-YYYY'),TO_CHAR(B.RENTAL_DATE,'MM-YYYY'),A.TRANSACTION_TYPE  "+
																	" ORDER BY TO_DATE(TO_CHAR(B.RENTAL_DATE,'MM-YYYY'),'MM-YYYY') ");
																	
												**********************/
												
													rs = stmt.executeQuery(
																	" TO_CHAR(A.RENTAL_DATE,'MONTH-YYYY') MONTH ,"+
																	" COUNT(A.APPLICATION_NO), "+
																	" SUM(A.CAPITAL_AMOUNT) , "+
																	" SUM(A.NET_RENTAL_AMOUNT) , "+
																	" SUM(A.INTEREST_AMOUNT), "+
																	" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE)  "+ //6
																	" FROM "+m_schema_name+".AF_TBD_MATURITY_DET A , "+m_schema_name+".AF_TBD_MATURITY B "+
																	" WHERE A.APPLICATION_NO(+)=B.APPLICATION_NO  "+
																	//" AND   B.DIVISION_CODE='BD' "+ 
																	" AND   B.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
																	" AND   A.ENT_USER='"+m_username+"' "+
																	" AND   B.ENT_USER='"+m_username+"' "+
																	" AND   A.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')   "+
																	" AND   A.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')    "+
																	" AND   B.STATUS=1 "+
																	" GROUP BY A.APPLICATION_NO,TO_CHAR(A.RENTAL_DATE,'MONTH-YYYY'),TO_CHAR(A.RENTAL_DATE,'MM-YYYY'),B.TRANSACTION_TYPE   "+
																	" ORDER BY TO_DATE(TO_CHAR(A.RENTAL_DATE,'MM-YYYY'),'MM-YYYY')  ");
												
												
																
			}													

		
	    	}
				else{
				

					/****  rs = stmt.executeQuery( " SELECT  TO_CHAR(D.RENTAL_DATE,'Month-YYYY') MONTH,"+//1
																"         COUNT(DISTINCT A.FINANCE_NO), "+//2
																"         SUM(D.CAPITAL_AMOUNT),"+//3
																"         SUM(D.NET_RENTAL_AMOUNT) , "+ //4
																"         SUM(D.INTEREST_AMOUNT), "+ //5 
																"         "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE)  "+//6
																" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
																"      "+m_schema_name+".AF_CO_MAS_CLIENT B, "+
																"      "+m_schema_name+".CO_CO_MAS_EMPLOYEE C, "+
																"      "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT D "+
																" WHERE  A.CLIENT_CODE=B.CLIENT_CODE "+
																" AND    A.COLLECTION_OFFICER=C.EMP_CODE "+
	 															" AND    A.APPLICATION_NO = D.APPLICATION_NO "+
	 															" AND    APPLICATION_STATUS='ACTIVATED' "+
																" AND    A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
																" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
																" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY')  "+
																" AND  CLIENT_CODE = '"+m_client_code+"' )  "+
																" AND    D.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																" AND    D.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																" GROUP BY TO_CHAR(D.RENTAL_DATE,'Month-YYYY'),TO_CHAR(D.RENTAL_DATE,'MM-YYYY'),A.TRANSACTION_TYPE "+
																" ORDER BY TO_DATE(TO_CHAR(D.RENTAL_DATE,'MM-YYYY'),'MM-YYYY') "); //TO_CHAR(D.RENTAL_DATE,'MM-YYYY')
          *****/
					
					
																/***************** COMMENT BY NS 17-11-2009 
																rs = stmt.executeQuery(
																	" SELECT  TO_CHAR(B.RENTAL_DATE,'MONTH-YYYY') MONTH,"+ //1
																	" COUNT(DISTINCT A.FINANCE_NO), "+ //2
																	" SUM(B.CAPITAL_AMOUNT),"+ //3
																	" SUM(B.NET_RENTAL_AMOUNT) ,  "+ //4
																	" SUM(B.INTEREST_AMOUNT),  "+ //5 
																	" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE)  "+ //6
																	" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT B  "+
																	"      ,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C,"+m_schema_name+".AF_CO_PRO_INVOICE D "+
																	" WHERE C.APPLICATION_NO = B.APPLICATION_NO "+
																	" AND   A.DIVISION_CODE='BD' "+	
																	" AND   A.APPLICATION_NO = B.APPLICATION_NO  "+
																	" AND   C.INVOICE_NO     = B.PRO_INVOICE_NO "+
																	" AND   C.PRICING_NO     = B.PRICING_NO "+
																	" AND   B.INVOICE_NO     = D.INVOICE_NO(+) "+
																	//" AND   D.ACTIVE_STATUS  = 'Y'  "+
																	" AND   C.ACTIVE_STATUS IN ('Y') "+
																	" AND    A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
																	" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS   "+
																	" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY')  "+
																	" AND    A.CLIENT_CODE = '"+m_client_code+"'   "+
																	" )  "+
																	" AND    B.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
																	" AND    B.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')   "+
																	" GROUP BY TO_CHAR(B.RENTAL_DATE,'MONTH-YYYY'),TO_CHAR(B.RENTAL_DATE,'MM-YYYY'),A.TRANSACTION_TYPE  "+
																	" ORDER BY TO_DATE(TO_CHAR(B.RENTAL_DATE,'MM-YYYY'),'MM-YYYY') ");
																	
																****************************/
																
																
																	rs = stmt.executeQuery(
																	" TO_CHAR(A.RENTAL_DATE,'MONTH-YYYY') MONTH ,"+
																	" COUNT(A.APPLICATION_NO), "+
																	" SUM(A.CAPITAL_AMOUNT) , "+
																	" SUM(A.NET_RENTAL_AMOUNT) , "+
																	" SUM(A.INTEREST_AMOUNT), "+
																	" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE)  "+ //6
																	" FROM "+m_schema_name+".AF_TBD_MATURITY_DET A , "+m_schema_name+".AF_TBD_MATURITY B "+
																	" WHERE A.APPLICATION_NO(+)=B.APPLICATION_NO  "+
																	//" AND   B.DIVISION_CODE='BD' "+
																	" AND   B.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
																	" AND   B.CLIENT_CODE = '"+m_client_code+"'   "+
																	" AND   A.ENT_USER='"+m_username+"' "+
																	" AND   B.ENT_USER='"+m_username+"' "+
																	" AND   A.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')   "+
																	" AND   A.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')    "+
																	" AND   B.STATUS=1 "+
																	" GROUP BY A.APPLICATION_NO,TO_CHAR(A.RENTAL_DATE,'MONTH-YYYY'),TO_CHAR(A.RENTAL_DATE,'MM-YYYY'),B.TRANSACTION_TYPE   "+
																	" ORDER BY TO_DATE(TO_CHAR(A.RENTAL_DATE,'MM-YYYY'),'MM-YYYY')  ");
												
				
				}
		
		
			boolean more = rs.next();
			int count = 1;
			
			if(!more){
			out.println("<table class='table' border='0' width=100% align='center'>   "); 
			out.println("<tr align='center' width='100%'><td>&nbsp;</td></tr>");
			out.println("<tr align='center' width='100%'><td>");
			out.println("<font color='red'>No data found....!</font>"); 
			out.println("</td></tr>"); 
			out.println("</table>"); 
			}
			else{
			out.println("<table class='table' border='0' width=100% >   "); 
			out.println("<tr class=pdn_txtpos2 >"); 
			out.println("<td width='5%'  align='left' >No.</td>"); 
			out.println("<td width='10%' align='left' >Month</td>"); 
			out.println("<td width='10%' align='left' >Product</td>"); 
			out.println("<td width='15%' align='right' >Rent</td>"); 
			out.println("<td width='15%' align='right' >Principle</td>"); 
			out.println("<td width='15%' align='right' >Income</td>");
			out.println("<td width='8%' align='center' >No.of Agreements</td>"); 
			out.println("</tr>"); 
			}
			
			while(more){	
			if(count%2==1){
			out.println("<tr bgcolor=\"#FFFFFF\" >"); //class=pdn_txtpos2 
			}
			else{
			out.println("<tr bgcolor=\"#C0C0C0\" >");
			}
			out.println("<td width='5%'  align='left' >"+count+"</td>"); 
			out.println("<td width='10%' align='left' >"+rs.getString(1)+"</td>"); 
			out.println("<td width='10%' align='left' >"+rs.getString(6)+"</td>"); 
			out.println("<td width='15%' align='right' >"+nf1.format(rs.getDouble(4))+"</td>"); 
			out.println("<td width='15%' align='right' >"+nf1.format(rs.getDouble(3))+"</td>"); 
			out.println("<td width='15%' align='right' >"+nf1.format(rs.getDouble(5))+"</td>");
			out.println("<td width='8%' align='center' >"+nf.format(rs.getDouble(2))+"</td>"); 
			out.println("</tr>"); 
			count = count+1;
			more = rs.next();
			}
			
			out.println("</table>"); 
			
			out.println("</body><html>");
			
			}

			
			
			else if(chksql.equals("lessee_wise_detail_report")){
			
			String m_activ_cut_date = req.getParameter("activ_cut_date");
			String m_prod_level = req.getParameter("prod_level");
			String m_client_code = req.getParameter("client_code");
			String m_location_code =req.getParameter("location_code");
			
			String m_from_date = req.getParameter("from_date");
			String m_to_date = req.getParameter("to_date");


			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Lessee Wise Detail Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<body>");
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' align='center'>   "); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td align='left' style='height: 18px' id='help_box' ><b>Lessee Wise Detail Report</b></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			
			if(m_location_code.equals("ALL")){
			
				/**** comment by ns 17-11-2009 
				rs = stmt.executeQuery(
				" SELECT  TO_CHAR(D.RENTAL_DATE,'Month-YYYY') MONTH,"+//1
				"         COUNT(DISTINCT A.FINANCE_NO), "+//2
				"         SUM(D.CAPITAL_AMOUNT),"+//3
				"         SUM(D.NET_RENTAL_AMOUNT) , "+ //4
				"         SUM(D.INTEREST_AMOUNT), "+ //5 
				"         "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE) , "+//6
				"         "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) FULL_NAME "+//7
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
				"      "+m_schema_name+".AF_CO_MAS_CLIENT B, "+
				"      "+m_schema_name+".CO_CO_MAS_EMPLOYEE C, "+
				"      "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT D "+
				" WHERE  A.CLIENT_CODE=B.CLIENT_CODE "+
				" AND    A.COLLECTION_OFFICER=C.EMP_CODE "+
				" AND    A.APPLICATION_NO = D.APPLICATION_NO "+
				" AND    APPLICATION_STATUS='ACTIVATED' "+
				" AND    A.CLIENT_CODE  LIKE '%"+m_client_code+"%' "+
				" AND    A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
				" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
				" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY') ) "+
				" AND    D.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND    D.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" GROUP BY TO_CHAR(D.RENTAL_DATE,'Month-YYYY'),TO_CHAR(D.RENTAL_DATE,'MM-YYYY'),A.TRANSACTION_TYPE,A.CLIENT_CODE "+
				" ORDER BY TO_DATE(TO_CHAR(D.RENTAL_DATE,'MM-YYYY'),'MM-YYYY'),FULL_NAME "); //TO_CHAR(D.RENTAL_DATE,'MM-YYYY')
				****/
				
				rs = stmt.executeQuery(
				//out.println(
				" SELECT "+
				" TO_CHAR(A.RENTAL_DATE,'MONTH-YYYY') MONTH ,"+
				" COUNT(DISTINCT A.APPLICATION_NO) , "+
				" SUM(A.CAPITAL_AMOUNT) , "+
				" SUM(A.NET_RENTAL_AMOUNT) , "+
				" SUM(A.INTEREST_AMOUNT), "+
				//" TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY'), "+
				" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(B.TRANSACTION_TYPE) , "+ //6
				" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) FULL_NAME "+//7
				" FROM "+m_schema_name+".AF_TBD_MATURITY_DET A , "+m_schema_name+".AF_TBD_MATURITY B "+
				" WHERE A.APPLICATION_NO(+)=B.APPLICATION_NO  "+
				" AND   A.ENT_USER='"+m_username+"' "+
				" AND   B.ENT_USER='"+m_username+"' "+
				" AND   B.CLIENT_CODE  LIKE '%"+m_client_code+"%' "+
				" AND   B.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
				" AND   A.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')   "+
				" AND   A.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')    "+
				" AND   B.STATUS=1 "+
				" GROUP BY TO_CHAR(A.RENTAL_DATE,'MONTH-YYYY'),TO_CHAR(A.RENTAL_DATE,'MM-YYYY'),B.TRANSACTION_TYPE ,TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY'),B.CLIENT_CODE  "+
				" ORDER BY TO_DATE(TO_CHAR(A.RENTAL_DATE,'MM-YYYY'),'MM-YYYY'),FULL_NAME  ");

			
			}
			else if(m_location_code.equals("BIKE")){
			
				/*rs = stmt.executeQuery(
				" SELECT  TO_CHAR(D.RENTAL_DATE,'Month-YYYY') MONTH,"+//1
				"         COUNT(DISTINCT A.FINANCE_NO), "+//2
				"         SUM(D.CAPITAL_AMOUNT),"+//3
				"         SUM(D.NET_RENTAL_AMOUNT) , "+ //4
				"         SUM(D.INTEREST_AMOUNT), "+ //5 
				"         "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE),  "+//6
				"         "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) FULL_NAME "+//7
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
				"      "+m_schema_name+".AF_CO_MAS_CLIENT B, "+
				"      "+m_schema_name+".CO_CO_MAS_EMPLOYEE C, "+
				"      "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT D "+
				" WHERE  A.CLIENT_CODE=B.CLIENT_CODE "+
				" AND    A.COLLECTION_OFFICER=C.EMP_CODE "+
				" AND    A.APPLICATION_NO = D.APPLICATION_NO "+
				" AND    APPLICATION_STATUS='ACTIVATED' "+
				" AND    A.DIVISION_CODE='BD' "+	
				" AND    A.CLIENT_CODE  LIKE '%"+m_client_code+"%' "+
				" AND    A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
				" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
				" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY') ) "+
				" AND    D.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND    D.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" GROUP BY TO_CHAR(D.RENTAL_DATE,'Month-YYYY'),TO_CHAR(D.RENTAL_DATE,'MM-YYYY'),A.TRANSACTION_TYPE,A.CLIENT_CODE "+
				" ORDER BY TO_DATE(TO_CHAR(D.RENTAL_DATE,'MM-YYYY'),'MM-YYYY'),FULL_NAME "); //TO_CHAR(D.RENTAL_DATE,'MM-YYYY')
				*/
				
					rs = stmt.executeQuery(
				//out.println(
				" SELECT "+
				" TO_CHAR(A.RENTAL_DATE,'MONTH-YYYY') MONTH ,"+
				" COUNT(DISTINCT A.APPLICATION_NO) , "+
				" SUM(A.CAPITAL_AMOUNT) , "+
				" SUM(A.NET_RENTAL_AMOUNT) , "+
				" SUM(A.INTEREST_AMOUNT), "+
				//" TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY'), "+
				" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(B.TRANSACTION_TYPE) , "+ //6
				" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) FULL_NAME "+//7
				" FROM "+m_schema_name+".AF_TBD_MATURITY_DET A , "+m_schema_name+".AF_TBD_MATURITY B "+
				" WHERE A.APPLICATION_NO(+)=B.APPLICATION_NO  "+
				" AND   B.DIVISION_CODE='BD' "+	
				" AND   B.CLIENT_CODE  LIKE '%"+m_client_code+"%' "+
				" AND   B.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
				" AND   A.ENT_USER='"+m_username+"' "+
				" AND   B.ENT_USER='"+m_username+"' "+
				" AND   A.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')   "+
				" AND   A.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')    "+
				" AND   B.STATUS=1 "+
				" GROUP BY TO_CHAR(A.RENTAL_DATE,'MONTH-YYYY'),TO_CHAR(A.RENTAL_DATE,'MM-YYYY'),B.TRANSACTION_TYPE ,TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY') ,B.CLIENT_CODE  "+
				" ORDER BY TO_DATE(TO_CHAR(A.RENTAL_DATE,'MM-YYYY'),'MM-YYYY'),FULL_NAME  ");

			}
			else{
			
				/* comment by ns 17-11-2009 **************************
				rs = stmt.executeQuery(
				" SELECT  TO_CHAR(D.RENTAL_DATE,'Month-YYYY') MONTH,"+//1
				"         COUNT(DISTINCT A.FINANCE_NO), "+//2
				"         SUM(D.CAPITAL_AMOUNT),"+//3
				"         SUM(D.NET_RENTAL_AMOUNT) , "+ //4
				"         SUM(D.INTEREST_AMOUNT), "+ //5 
				"         "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE),  "+//6
				"         "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) FULL_NAME "+//7
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
				"      "+m_schema_name+".AF_CO_MAS_CLIENT B, "+
				"      "+m_schema_name+".CO_CO_MAS_EMPLOYEE C, "+
				"      "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT D "+
				" WHERE  A.CLIENT_CODE=B.CLIENT_CODE "+
				" AND    A.COLLECTION_OFFICER=C.EMP_CODE "+
				" AND    A.APPLICATION_NO = D.APPLICATION_NO "+
				" AND    APPLICATION_STATUS='ACTIVATED' "+
				" AND    A.DIVISION_CODE='AF' "+	
				" AND    A.CLIENT_CODE  LIKE '%"+m_client_code+"%' "+
				" AND    A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
				" AND    C.LOCATION_CODE  LIKE '%"+m_location_code+"%' "+
				" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
				" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY') ) "+
				" AND    D.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND    D.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" GROUP BY TO_CHAR(D.RENTAL_DATE,'Month-YYYY'),TO_CHAR(D.RENTAL_DATE,'MM-YYYY'),A.TRANSACTION_TYPE,A.CLIENT_CODE "+
				" ORDER BY TO_DATE(TO_CHAR(D.RENTAL_DATE,'MM-YYYY'),'MM-YYYY'),FULL_NAME "); //TO_CHAR(D.RENTAL_DATE,'MM-YYYY')
				*/
				
				
									rs = stmt.executeQuery(
				//out.println(
				" SELECT "+
				" TO_CHAR(A.RENTAL_DATE,'MONTH-YYYY') MONTH ,"+
				" COUNT(DISTINCT A.APPLICATION_NO) , "+
				" SUM(A.CAPITAL_AMOUNT) , "+
				" SUM(A.NET_RENTAL_AMOUNT) , "+
				" SUM(A.INTEREST_AMOUNT), "+
				//" TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY'), "+
				" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(B.TRANSACTION_TYPE) , "+ //6
				" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) FULL_NAME "+//7
				" FROM "+m_schema_name+".AF_TBD_MATURITY_DET A , "+m_schema_name+".AF_TBD_MATURITY B "+
				" WHERE A.APPLICATION_NO(+)=B.APPLICATION_NO  "+
				" AND   B.DIVISION_CODE='AF' "+	
				" AND   B.CLIENT_CODE  LIKE '%"+m_client_code+"%' "+
				" AND   B.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
				" AND   B.LOCATION_CODE  LIKE '%"+m_location_code+"%' "+
				" AND   A.ENT_USER='"+m_username+"' "+
				" AND   B.ENT_USER='"+m_username+"' "+
				" AND   A.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')   "+
				" AND   A.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')    "+
				" AND   B.STATUS=1 "+
				" GROUP BY TO_CHAR(A.RENTAL_DATE,'MONTH-YYYY'),TO_CHAR(A.RENTAL_DATE,'MM-YYYY'),B.TRANSACTION_TYPE ,TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY') ,B.CLIENT_CODE  "+
				" ORDER BY TO_DATE(TO_CHAR(A.RENTAL_DATE,'MM-YYYY'),'MM-YYYY'),FULL_NAME  ");
			
			}
			
			/*
			
								
			if(m_location_code.equals("")){
			
			rs = stmt.executeQuery( " SELECT  TO_CHAR(A.ACTIVATED_DATE,'Month') MONTH,"+//1
															"         COUNT(A.FINANCE_NO), "+//2
															"         SUM(D.CAPITAL_AMOUNT),"+//3
															"         SUM(D.GRENTAL_AMOUNT) , "+ //4
															"         SUM(D.INTEREST_AMOUNT), "+ //5 
															"         "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE)  "+//6
															" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
															"      "+m_schema_name+".AF_CO_MAS_CLIENT B, "+
															"      "+m_schema_name+".CO_CO_MAS_EMPLOYEE C, "+
															"      "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT D "+
															" WHERE  A.CLIENT_CODE=B.CLIENT_CODE "+
															" AND    A.COLLECTION_OFFICER=C.EMP_CODE "+
 															" AND    A.APPLICATION_NO = D.APPLICATION_NO "+
 															" AND    APPLICATION_STATUS='ACTIVATED' "+
															" AND    A.CLIENT_CODE = '"+m_client_code+"' "+
															" AND    A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
															" AND    TO_CHAR(A.ACTIVATED_DATE,'MM') <= TO_CHAR(TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY'),'MM') "+
															" GROUP BY TO_CHAR(A.ACTIVATED_DATE,'Month'),A.TRANSACTION_TYPE,TO_CHAR(A.ACTIVATED_DATE,'MM') "+
															" ORDER BY TO_CHAR(A.ACTIVATED_DATE,'MM')");
															
															

			
			}
			else {
			
			rs = stmt.executeQuery( " SELECT  TO_CHAR(A.ACTIVATED_DATE,'Month') MONTH,"+//1
															"         COUNT(A.FINANCE_NO), "+//2
															"         SUM(D.CAPITAL_AMOUNT),"+//3
															"         SUM(D.GRENTAL_AMOUNT) , "+ //4
															"         SUM(D.INTEREST_AMOUNT), "+ //5 
															"         "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE)  "+//6
															" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
															"      "+m_schema_name+".AF_CO_MAS_CLIENT B, "+
															"      "+m_schema_name+".CO_CO_MAS_EMPLOYEE C, "+
															"      "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT D "+
															" WHERE  A.CLIENT_CODE=B.CLIENT_CODE "+
															" AND    A.COLLECTION_OFFICER=C.EMP_CODE "+
 															" AND    A.APPLICATION_NO = D.APPLICATION_NO "+
 															" AND    APPLICATION_STATUS='ACTIVATED' "+
															" AND    A.CLIENT_CODE = '"+m_client_code+"' "+
															" AND    A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
															" AND    C.LOCATION_CODE  ='"+m_location_code+"' "+
															" AND    TO_CHAR(A.ACTIVATED_DATE,'MM') <= TO_CHAR(TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY'),'MM') "+
															" GROUP BY TO_CHAR(A.ACTIVATED_DATE,'Month'),A.TRANSACTION_TYPE,TO_CHAR(A.ACTIVATED_DATE,'MM') "+
															" ORDER BY TO_CHAR(A.ACTIVATED_DATE,'MM')");
			
			}
			
			*/
			
			
			boolean more = rs.next();
			int count = 1;
			if(!more){
			out.println("<table class='table' border='0' width=100% align='center'>   "); 
			out.println("<tr align='center' width='100%'><td>&nbsp;</td></tr>");
			out.println("<tr align='center' width='100%'><td>");
			out.println("<font color='red'>No data found....!</font>"); 
			out.println("</td></tr>"); 
			out.println("</table>"); 
			}
			else{
			out.println("<table class='table' border='0' width=100% >   "); 
			out.println("<tr class=pdn_txtpos2 >"); 
			out.println("<td width='3%'  align='left' >No.</td>"); 
			out.println("<td width='8%' align='left' >Month</td>");
			out.println("<td width='10%' align='left' >Client Name</td>"); 
			out.println("<td width='10%' align='left' >Product</td>"); 
			out.println("<td width='15%' align='right' >Rent</td>"); 
			out.println("<td width='15%' align='right' >Principle</td>"); 
			out.println("<td width='15%' align='right' >Income</td>");
			out.println("<td width='8%' align='center' >No.of Agreements</td>"); 

			}
			while(more){	
			if(count%2==1){
			out.println("<tr bgcolor=\"#FFFFFF\" >"); 
			}
			else{
			out.println("<tr bgcolor=\"#C0C0C0\" >");
			}
			out.println("<td width='5%'  align='left' >"+count+"</td>"); 
			out.println("<td width='10%' align='left' >"+rs.getString(1)+"</td>"); 
			out.println("<td width='10%' align='left' >"+rs.getString(7)+"</td>"); 
			out.println("<td width='10%' align='left' >"+rs.getString(6)+"</td>"); 
			out.println("<td width='15%' align='right' >"+nf1.format(rs.getDouble(4))+"</td>"); 
			out.println("<td width='15%' align='right' >"+nf1.format(rs.getDouble(3))+"</td>"); 
			out.println("<td width='15%' align='right' >"+nf1.format(rs.getDouble(5))+"</td>");
			out.println("<td width='8%' align='center' >"+rs.getString(2)+"</td>"); 
			out.println("</tr>"); 
			count = count+1;
			more = rs.next();
			}
			
			out.println("</table>"); 
			
			out.println("</body><html>");
			
			}
			
				
			else if(chksql.equals("branch_wise_summary_report")){
			
			String m_activ_cut_date = req.getParameter("activ_cut_date");
			String m_prod_level = req.getParameter("prod_level");
			String m_client_code = req.getParameter("client_code");
			String m_location_code =req.getParameter("location_code");
			
			String m_from_date = req.getParameter("from_date");
			String m_to_date = req.getParameter("to_date");

			
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Branch Wise Summary Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<body>");
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' align='center'>   "); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td align='left' style='height: 18px' id='help_box' ><b>Branch Wise Summary Report</b></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			
			
			
				if(m_location_code.equals("ALL")){
			
				/**** Comment by ns 17-11-2009 
				rs = stmt.executeQuery(
				" SELECT  TO_CHAR(D.RENTAL_DATE,'Month-YYYY') MONTH,"+//1
				"         COUNT(DISTINCT A.FINANCE_NO), "+//2
				"         SUM(D.CAPITAL_AMOUNT),"+//3
				"         SUM(D.NET_RENTAL_AMOUNT) , "+ //4
				"         SUM(D.INTEREST_AMOUNT), "+ //5 
				"         "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE),  "+//6
				"         "+m_schema_name+".AF_CO_GET_LOCATION_DESC(C.LOCATION_CODE) LOCATION_DESC "+//7
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
				"      "+m_schema_name+".AF_CO_MAS_CLIENT B, "+
				"      "+m_schema_name+".CO_CO_MAS_EMPLOYEE C, "+
				"      "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT D "+
				" WHERE  A.CLIENT_CODE=B.CLIENT_CODE "+
				" AND    A.COLLECTION_OFFICER=C.EMP_CODE "+
				" AND    A.APPLICATION_NO = D.APPLICATION_NO "+
				" AND    APPLICATION_STATUS='ACTIVATED' "+
				//" AND    A.DIVISION_CODE='AF' "+	
				" AND    A.CLIENT_CODE  LIKE '%"+m_client_code+"%' "+
				" AND    A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
				//" AND    C.LOCATION_CODE  LIKE '%"+m_location_code+"%' "+
				" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
				" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY') ) "+
				" AND    D.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND    D.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" GROUP BY TO_CHAR(D.RENTAL_DATE,'Month-YYYY'),TO_CHAR(D.RENTAL_DATE,'MM-YYYY'),A.TRANSACTION_TYPE,C.LOCATION_CODE "+
				" ORDER BY TO_DATE(TO_CHAR(D.RENTAL_DATE,'MM-YYYY'),'MM-YYYY'),LOCATION_DESC "); //TO_CHAR(D.RENTAL_DATE,'MM-YYYY')
				*/
				
				
				rs = stmt.executeQuery(
				//out.println(
				" SELECT "+
				" TO_CHAR(A.RENTAL_DATE,'MONTH-YYYY') MONTH ,"+
				" COUNT(DISTINCT A.APPLICATION_NO) , "+
				" SUM(A.CAPITAL_AMOUNT) , "+
				" SUM(A.NET_RENTAL_AMOUNT) , "+
				" SUM(A.INTEREST_AMOUNT), "+
				//" TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY'), "+
				" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(B.TRANSACTION_TYPE) , "+ //6
				" "+m_schema_name+".AF_CO_GET_LOCATION_DESC(B.LOCATION_CODE) LOCATION_DESC "+//7
				" FROM "+m_schema_name+".AF_TBD_MATURITY_DET A , "+m_schema_name+".AF_TBD_MATURITY B "+
				" WHERE A.APPLICATION_NO(+)=B.APPLICATION_NO  "+
				//" AND   B.DIVISION_CODE='AF' "+	
				" AND   B.CLIENT_CODE  LIKE '%"+m_client_code+"%' "+
				" AND   B.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
				//" AND   B.LOCATION_CODE  LIKE '%"+m_location_code+"%' "+
				" AND   A.ENT_USER='"+m_username+"' "+
				" AND   B.ENT_USER='"+m_username+"' "+
				" AND   A.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')   "+
				" AND   A.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')    "+
				" AND   B.STATUS=1 "+
				" GROUP BY TO_CHAR(A.RENTAL_DATE,'MONTH-YYYY'),TO_CHAR(A.RENTAL_DATE,'MM-YYYY'),B.TRANSACTION_TYPE ,TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY') ,B.LOCATION_CODE  "+
				" ORDER BY TO_DATE(TO_CHAR(A.RENTAL_DATE,'MM-YYYY'),'MM-YYYY'),LOCATION_DESC  ");
			 
			}
			else if(m_location_code.equals("BIKE")){
			
				/********** Comment by ns 17-11-2009
				rs = stmt.executeQuery(
				" SELECT  TO_CHAR(D.RENTAL_DATE,'Month-YYYY') MONTH,"+//1
				"         COUNT(DISTINCT A.FINANCE_NO), "+//2
				"         SUM(D.CAPITAL_AMOUNT),"+//3
				"         SUM(D.NET_RENTAL_AMOUNT) , "+ //4
				"         SUM(D.INTEREST_AMOUNT), "+ //5 
				"         "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE),  "+//6
				"         "+m_schema_name+".AF_CO_GET_LOCATION_DESC(C.LOCATION_CODE) LOCATION_DESC "+//7
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
				"      "+m_schema_name+".AF_CO_MAS_CLIENT B, "+
				"      "+m_schema_name+".CO_CO_MAS_EMPLOYEE C, "+
				"      "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT D "+
				" WHERE  A.CLIENT_CODE=B.CLIENT_CODE "+
				" AND    A.COLLECTION_OFFICER=C.EMP_CODE "+
				" AND    A.APPLICATION_NO = D.APPLICATION_NO "+
				" AND    APPLICATION_STATUS='ACTIVATED' "+
				" AND    A.DIVISION_CODE='BD' "+	
				" AND    A.CLIENT_CODE  LIKE '%"+m_client_code+"%' "+
				" AND    A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
				//" AND    C.LOCATION_CODE  LIKE '%"+m_location_code+"%' "+
				" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
				" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY') ) "+
				" AND    D.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND    D.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" GROUP BY TO_CHAR(D.RENTAL_DATE,'Month-YYYY'),TO_CHAR(D.RENTAL_DATE,'MM-YYYY'),A.TRANSACTION_TYPE,C.LOCATION_CODE "+
				" ORDER BY TO_DATE(TO_CHAR(D.RENTAL_DATE,'MM-YYYY'),'MM-YYYY'),LOCATION_DESC "); //TO_CHAR(D.RENTAL_DATE,'MM-YYYY')
       */
				
				
				rs = stmt.executeQuery(
				//out.println(
				" SELECT "+
				" TO_CHAR(A.RENTAL_DATE,'MONTH-YYYY') MONTH ,"+
				" COUNT(DISTINCT A.APPLICATION_NO) , "+
				" SUM(A.CAPITAL_AMOUNT) , "+
				" SUM(A.NET_RENTAL_AMOUNT) , "+
				" SUM(A.INTEREST_AMOUNT), "+
				//" TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY'), "+
				" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(B.TRANSACTION_TYPE) , "+ //6
				" "+m_schema_name+".AF_CO_GET_LOCATION_DESC(B.LOCATION_CODE) LOCATION_DESC "+//7
				" FROM "+m_schema_name+".AF_TBD_MATURITY_DET A , "+m_schema_name+".AF_TBD_MATURITY B "+
				" WHERE A.APPLICATION_NO(+)=B.APPLICATION_NO  "+
				" AND   B.DIVISION_CODE='BD' "+	
				" AND   B.CLIENT_CODE  LIKE '%"+m_client_code+"%' "+
				" AND   B.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
				//" AND   B.LOCATION_CODE  LIKE '%"+m_location_code+"%' "+
				" AND   A.ENT_USER='"+m_username+"' "+
				" AND   B.ENT_USER='"+m_username+"' "+
				" AND   A.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')   "+
				" AND   A.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')    "+
				" AND   B.STATUS=1 "+
				" GROUP BY TO_CHAR(A.RENTAL_DATE,'MONTH-YYYY'),TO_CHAR(A.RENTAL_DATE,'MM-YYYY'),B.TRANSACTION_TYPE ,TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY') ,B.LOCATION_CODE  "+
				" ORDER BY TO_DATE(TO_CHAR(A.RENTAL_DATE,'MM-YYYY'),'MM-YYYY'),LOCATION_DESC  ");

			}
			else{
			
				/****** Comment by ns 17-11-2009 
				rs = stmt.executeQuery(
				" SELECT  TO_CHAR(D.RENTAL_DATE,'Month-YYYY') MONTH,"+//1
				"         COUNT(DISTINCT A.FINANCE_NO), "+//2
				"         SUM(D.CAPITAL_AMOUNT),"+//3
				"         SUM(D.NET_RENTAL_AMOUNT) , "+ //4
				"         SUM(D.INTEREST_AMOUNT), "+ //5 
				"         "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE),  "+//6
				"         "+m_schema_name+".AF_CO_GET_LOCATION_DESC(C.LOCATION_CODE) LOCATION_DESC "+//7
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
				"      "+m_schema_name+".AF_CO_MAS_CLIENT B, "+
				"      "+m_schema_name+".CO_CO_MAS_EMPLOYEE C, "+
				"      "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT D "+
				" WHERE  A.CLIENT_CODE=B.CLIENT_CODE "+
				" AND    A.COLLECTION_OFFICER=C.EMP_CODE "+
				" AND    A.APPLICATION_NO = D.APPLICATION_NO "+
				" AND    APPLICATION_STATUS='ACTIVATED' "+
				" AND    A.DIVISION_CODE='AF' "+	
				" AND    A.CLIENT_CODE  LIKE '%"+m_client_code+"%' "+
				" AND    A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
				" AND    C.LOCATION_CODE  LIKE '%"+m_location_code+"%' "+
				" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
				" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY') ) "+
				" AND    D.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND    D.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" GROUP BY TO_CHAR(D.RENTAL_DATE,'Month-YYYY'),TO_CHAR(D.RENTAL_DATE,'MM-YYYY'),A.TRANSACTION_TYPE,C.LOCATION_CODE "+
				" ORDER BY TO_DATE(TO_CHAR(D.RENTAL_DATE,'MM-YYYY'),'MM-YYYY'),LOCATION_DESC "); //TO_CHAR(D.RENTAL_DATE,'MM-YYYY')
				*/
				
				
				rs = stmt.executeQuery(
				//out.println(
				" SELECT "+
				" TO_CHAR(A.RENTAL_DATE,'MONTH-YYYY') MONTH ,"+
				" COUNT(DISTINCT A.APPLICATION_NO) , "+
				" SUM(A.CAPITAL_AMOUNT) , "+
				" SUM(A.NET_RENTAL_AMOUNT) , "+
				" SUM(A.INTEREST_AMOUNT), "+
				//" TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY'), "+
				" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(B.TRANSACTION_TYPE) , "+ //6
				" "+m_schema_name+".AF_CO_GET_LOCATION_DESC(B.LOCATION_CODE) LOCATION_DESC "+//7
				" FROM "+m_schema_name+".AF_TBD_MATURITY_DET A , "+m_schema_name+".AF_TBD_MATURITY B "+
				" WHERE A.APPLICATION_NO(+)=B.APPLICATION_NO  "+
				//" AND   B.DIVISION_CODE='AF' "+	
				" AND   B.CLIENT_CODE  LIKE '%"+m_client_code+"%' "+
				" AND   B.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
				//" AND   B.LOCATION_CODE  LIKE '%"+m_location_code+"%' "+
				" AND   A.ENT_USER='"+m_username+"' "+
				" AND   B.ENT_USER='"+m_username+"' "+
				" AND   A.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')   "+
				" AND   A.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')    "+
				" AND   B.STATUS=1 "+
				" GROUP BY TO_CHAR(A.RENTAL_DATE,'MONTH-YYYY'),TO_CHAR(A.RENTAL_DATE,'MM-YYYY'),B.TRANSACTION_TYPE ,TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY') ,B.LOCATION_CODE  "+
				" ORDER BY TO_DATE(TO_CHAR(A.RENTAL_DATE,'MM-YYYY'),'MM-YYYY'),LOCATION_DESC  ");
			
			}

						
						
		/*	rs = stmt.executeQuery( " SELECT  TO_CHAR(A.ACTIVATED_DATE,'Month') MONTH,"+//1
															"         COUNT(A.FINANCE_NO), "+//2
															"         SUM(D.CAPITAL_AMOUNT),"+//3
															"         SUM(D.GRENTAL_AMOUNT) , "+ //4
															"         SUM(D.INTEREST_AMOUNT), "+ //5 
															"         "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE)  "+//6
															" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
															"      "+m_schema_name+".AF_CO_MAS_CLIENT B, "+
															"      "+m_schema_name+".CO_CO_MAS_EMPLOYEE C, "+
															"      "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT D "+
															" WHERE  A.CLIENT_CODE=B.CLIENT_CODE "+
															" AND    A.COLLECTION_OFFICER=C.EMP_CODE "+
 															" AND    A.APPLICATION_NO = D.APPLICATION_NO "+
 															" AND    APPLICATION_STATUS='ACTIVATED' "+
															" AND    A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
															" AND    TO_CHAR(A.ACTIVATED_DATE,'MM') <= TO_CHAR(TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY'),'MM') "+
															" GROUP BY TO_CHAR(A.ACTIVATED_DATE,'Month'),A.TRANSACTION_TYPE,TO_CHAR(A.ACTIVATED_DATE,'MM') "+
															" ORDER BY TO_CHAR(A.ACTIVATED_DATE,'MM')");
			
		*/				
			boolean more = rs.next();
			int count = 1;
			
			if(!more){
			out.println("<table class='table' border='0' width=100% align='center'>   "); 
			out.println("<tr align='center' width='100%'><td>&nbsp;</td></tr>");
			out.println("<tr align='center' width='100%'><td>");
			out.println("<font color='red'>No data found....!</font>"); 
			out.println("</td></tr>"); 
			out.println("</table>"); 
			}
			else{			
			out.println("<table class='table' border='0' width=100% >   "); 
			out.println("<tr class=pdn_txtpos2 >"); 
			out.println("<td width='3%'  align='left' >No.</td>"); 
			out.println("<td width='8%' align='left' >Month</td>");
			out.println("<td width='10%' align='left' >Branch Name</td>"); 
			out.println("<td width='10%' align='left' >Product</td>"); 
			out.println("<td width='15%' align='right' >Rent</td>"); 
			out.println("<td width='15%' align='right' >Principle</td>"); 
			out.println("<td width='15%' align='right' >Income</td>");
			out.println("<td width='8%' align='center' >No.of Agreements</td>"); 
			out.println("</tr>"); 			
			}
			
			while(more){	
			if(count%2==1){
			out.println("<tr bgcolor=\"#FFFFFF\" >"); 
			}
			else{
			out.println("<tr bgcolor=\"#C0C0C0\" >");
			}
			out.println("<td width='5%'  align='left' >"+count+"</td>"); 
			out.println("<td width='10%' align='left' >"+rs.getString(1)+"</td>"); 
			out.println("<td width='10%' align='left' >"+rs.getString(7)+"</td>"); 
			out.println("<td width='10%' align='left' >"+rs.getString(6)+"</td>"); 
			out.println("<td width='15%' align='right' >"+nf1.format(rs.getDouble(4))+"</td>"); 
			out.println("<td width='15%' align='right' >"+nf1.format(rs.getDouble(3))+"</td>"); 
			out.println("<td width='15%' align='right' >"+nf1.format(rs.getDouble(5))+"</td>");
			out.println("<td width='8%' align='center' >"+rs.getString(2)+"</td>"); 
			out.println("</tr>"); 
			count = count+1;
			more = rs.next();
			}
			
			out.println("</table>"); 
			
			out.println("</body><html>");
			
			}
			
			
			else if(chksql.equals("branch_wise_detail_report")){
			
			String m_activ_cut_date = req.getParameter("activ_cut_date");
			String m_prod_level = req.getParameter("prod_level");
			String m_client_code = req.getParameter("client_code");
			String m_location_code =req.getParameter("location_code");
			
			
			String m_from_date = req.getParameter("from_date");
			String m_to_date = req.getParameter("to_date");

			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Branch Wise Detail Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<body>");
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' align='center'>   "); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td align='left' style='height: 18px' id='help_box' ><b>Branch Wise Detail Report</b></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			
			
				if(m_location_code.equals("ALL")){
			
				/* comment by ns 17-11-2009
				rs = stmt.executeQuery(
				" SELECT  TO_CHAR(D.RENTAL_DATE,'Month-YYYY') MONTH,"+//1
				"         COUNT(DISTINCT A.FINANCE_NO), "+//2
				"         SUM(D.CAPITAL_AMOUNT),"+//3
				"         SUM(D.NET_RENTAL_AMOUNT) , "+ //4
				"         SUM(D.INTEREST_AMOUNT), "+ //5 
				"         "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE),  "+//6
				"         "+m_schema_name+".AF_CO_GET_LOCATION_DESC(C.LOCATION_CODE) LOCATION_DESC ,"+//7
				"         A.FINANCE_NO "+
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
				"      "+m_schema_name+".AF_CO_MAS_CLIENT B, "+
				"      "+m_schema_name+".CO_CO_MAS_EMPLOYEE C, "+
				"      "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT D "+
				" WHERE  A.CLIENT_CODE=B.CLIENT_CODE "+
				" AND    A.COLLECTION_OFFICER=C.EMP_CODE "+
				" AND    A.APPLICATION_NO = D.APPLICATION_NO "+
				" AND    APPLICATION_STATUS='ACTIVATED' "+
				//" AND    A.DIVISION_CODE='AF' "+	
				" AND    A.CLIENT_CODE  LIKE '%"+m_client_code+"%' "+
				" AND    A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
				//" AND    C.LOCATION_CODE  LIKE '%"+m_location_code+"%' "+
				" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
				" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY') ) "+
				" AND    D.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND    D.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" GROUP BY TO_CHAR(D.RENTAL_DATE,'Month-YYYY'),TO_CHAR(D.RENTAL_DATE,'MM-YYYY'),A.TRANSACTION_TYPE,C.LOCATION_CODE,A.FINANCE_NO "+
				" ORDER BY TO_DATE(TO_CHAR(D.RENTAL_DATE,'MM-YYYY'),'MM-YYYY'),LOCATION_DESC "); //TO_CHAR(D.RENTAL_DATE,'MM-YYYY')
				*/
				
				rs = stmt.executeQuery(
				//out.println(
				" SELECT "+
				" TO_CHAR(A.RENTAL_DATE,'MONTH-YYYY') MONTH ,"+
				" COUNT(DISTINCT B.FINANCE_NO) , "+
				" SUM(A.CAPITAL_AMOUNT) , "+
				" SUM(A.NET_RENTAL_AMOUNT) , "+
				" SUM(A.INTEREST_AMOUNT), "+
				//" TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY'), "+
				" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(B.TRANSACTION_TYPE) , "+ //6
				" "+m_schema_name+".AF_CO_GET_LOCATION_DESC(B.LOCATION_CODE) LOCATION_DESC , "+//7
				" B.FINANCE_NO  "+
				" FROM "+m_schema_name+".AF_TBD_MATURITY_DET A , "+m_schema_name+".AF_TBD_MATURITY B "+
				" WHERE A.APPLICATION_NO(+)=B.APPLICATION_NO  "+
				//" AND   B.DIVISION_CODE='AF' "+	
				" AND   B.CLIENT_CODE  LIKE '%"+m_client_code+"%' "+
				" AND   B.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
				//" AND   B.LOCATION_CODE  LIKE '%"+m_location_code+"%' "+
				" AND   A.ENT_USER='"+m_username+"' "+
				" AND   B.ENT_USER='"+m_username+"' "+
				" AND   A.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')   "+
				" AND   A.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')    "+
				" AND   B.STATUS=1 "+
				" GROUP BY B.FINANCE_NO,TO_CHAR(A.RENTAL_DATE,'MONTH-YYYY'),TO_CHAR(A.RENTAL_DATE,'MM-YYYY'),B.TRANSACTION_TYPE ,TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY') ,B.LOCATION_CODE  "+
				" ORDER BY TO_DATE(TO_CHAR(A.RENTAL_DATE,'MM-YYYY'),'MM-YYYY'),LOCATION_DESC  ");
			
			}
			else if(m_location_code.equals("BIKE")){
			
				/* comment by ns 17-11-2009
				rs = stmt.executeQuery(
				" SELECT  TO_CHAR(D.RENTAL_DATE,'Month-YYYY') MONTH,"+//1
				"         COUNT(DISTINCT A.FINANCE_NO), "+//2
				"         SUM(D.CAPITAL_AMOUNT),"+//3
				"         SUM(D.NET_RENTAL_AMOUNT) , "+ //4
				"         SUM(D.INTEREST_AMOUNT), "+ //5 
				"         "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE),  "+//6
				"         "+m_schema_name+".AF_CO_GET_LOCATION_DESC(C.LOCATION_CODE) LOCATION_DESC ,"+//7
				"         A.FINANCE_NO "+ //8
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
				"      "+m_schema_name+".AF_CO_MAS_CLIENT B, "+
				"      "+m_schema_name+".CO_CO_MAS_EMPLOYEE C, "+
				"      "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT D "+
				" WHERE  A.CLIENT_CODE=B.CLIENT_CODE "+
				" AND    A.COLLECTION_OFFICER=C.EMP_CODE "+
				" AND    A.APPLICATION_NO = D.APPLICATION_NO "+
				" AND    APPLICATION_STATUS='ACTIVATED' "+
				" AND    A.DIVISION_CODE='BD' "+	
				" AND    A.CLIENT_CODE  LIKE '%"+m_client_code+"%' "+
				" AND    A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
				//" AND    C.LOCATION_CODE  LIKE '%"+m_location_code+"%' "+
				" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
				" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY') ) "+
				" AND    D.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND    D.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" GROUP BY TO_CHAR(D.RENTAL_DATE,'Month-YYYY'),TO_CHAR(D.RENTAL_DATE,'MM-YYYY'),A.TRANSACTION_TYPE,C.LOCATION_CODE ,A.FINANCE_NO "+
				" ORDER BY TO_DATE(TO_CHAR(D.RENTAL_DATE,'MM-YYYY'),'MM-YYYY'),LOCATION_DESC "); //TO_CHAR(D.RENTAL_DATE,'MM-YYYY')
        */
				
				
							rs = stmt.executeQuery(
				//out.println(
				" SELECT "+
				" TO_CHAR(A.RENTAL_DATE,'MONTH-YYYY') MONTH ,"+
				" COUNT(DISTINCT B.FINANCE_NO) , "+
				" SUM(A.CAPITAL_AMOUNT) , "+
				" SUM(A.NET_RENTAL_AMOUNT) , "+
				" SUM(A.INTEREST_AMOUNT), "+
				//" TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY'), "+
				" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(B.TRANSACTION_TYPE) , "+ //6
				" "+m_schema_name+".AF_CO_GET_LOCATION_DESC(B.LOCATION_CODE) LOCATION_DESC , "+//7
				" B.FINANCE_NO  "+
				" FROM "+m_schema_name+".AF_TBD_MATURITY_DET A , "+m_schema_name+".AF_TBD_MATURITY B "+
				" WHERE A.APPLICATION_NO(+)=B.APPLICATION_NO  "+
				" AND   B.DIVISION_CODE='BD' "+	
				" AND   B.CLIENT_CODE  LIKE '%"+m_client_code+"%' "+
				" AND   B.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
				//" AND   B.LOCATION_CODE  LIKE '%"+m_location_code+"%' "+
				" AND   A.ENT_USER='"+m_username+"' "+
				" AND   B.ENT_USER='"+m_username+"' "+
				" AND   A.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')   "+
				" AND   A.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')    "+
				" AND   B.STATUS=1 "+
				" GROUP BY B.FINANCE_NO,TO_CHAR(A.RENTAL_DATE,'MONTH-YYYY'),TO_CHAR(A.RENTAL_DATE,'MM-YYYY'),B.TRANSACTION_TYPE ,TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY') ,B.LOCATION_CODE  "+
				" ORDER BY TO_DATE(TO_CHAR(A.RENTAL_DATE,'MM-YYYY'),'MM-YYYY'),LOCATION_DESC  ");

				

			}
			else{
			
				/**** Commnet by ns 17-11-2009
				rs = stmt.executeQuery(
				" SELECT  TO_CHAR(D.RENTAL_DATE,'Month-YYYY') MONTH,"+//1
				"         COUNT(DISTINCT A.FINANCE_NO), "+//2
				"         SUM(D.CAPITAL_AMOUNT),"+//3
				"         SUM(D.NET_RENTAL_AMOUNT) , "+ //4
				"         SUM(D.INTEREST_AMOUNT), "+ //5 
				"         "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE),  "+//6
				"         "+m_schema_name+".AF_CO_GET_LOCATION_DESC(C.LOCATION_CODE) LOCATION_DESC ,"+//7
				"         A.FINANCE_NO "+ //8
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
				"      "+m_schema_name+".AF_CO_MAS_CLIENT B, "+
				"      "+m_schema_name+".CO_CO_MAS_EMPLOYEE C, "+
				"      "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT D "+
				" WHERE  A.CLIENT_CODE=B.CLIENT_CODE "+
				" AND    A.COLLECTION_OFFICER=C.EMP_CODE "+
				" AND    A.APPLICATION_NO = D.APPLICATION_NO "+
				" AND    APPLICATION_STATUS='ACTIVATED' "+
				" AND    A.DIVISION_CODE='AF' "+	
				" AND    A.CLIENT_CODE  LIKE '%"+m_client_code+"%' "+
				" AND    A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
				" AND    C.LOCATION_CODE  LIKE '%"+m_location_code+"%' "+
				" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
				" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY') ) "+
				" AND    D.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND    D.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" GROUP BY TO_CHAR(D.RENTAL_DATE,'Month-YYYY'),TO_CHAR(D.RENTAL_DATE,'MM-YYYY'),A.TRANSACTION_TYPE,C.LOCATION_CODE , A.FINANCE_NO "+
				" ORDER BY TO_DATE(TO_CHAR(D.RENTAL_DATE,'MM-YYYY'),'MM-YYYY'),LOCATION_DESC "); //TO_CHAR(D.RENTAL_DATE,'MM-YYYY')
			  */
				
				
				rs = stmt.executeQuery(
				//out.println(
				" SELECT "+
				" TO_CHAR(A.RENTAL_DATE,'MONTH-YYYY') MONTH ,"+
				" COUNT(DISTINCT B.FINANCE_NO) , "+
				" SUM(A.CAPITAL_AMOUNT) , "+
				" SUM(A.NET_RENTAL_AMOUNT) , "+
				" SUM(A.INTEREST_AMOUNT), "+
				//" TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY'), "+
				" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(B.TRANSACTION_TYPE) , "+ //6
				" "+m_schema_name+".AF_CO_GET_LOCATION_DESC(B.LOCATION_CODE) LOCATION_DESC , "+//7
				" B.FINANCE_NO  "+
				" FROM "+m_schema_name+".AF_TBD_MATURITY_DET A , "+m_schema_name+".AF_TBD_MATURITY B "+
				" WHERE A.APPLICATION_NO(+)=B.APPLICATION_NO  "+
				" AND   B.DIVISION_CODE='AF' "+	
				" AND   B.CLIENT_CODE  LIKE '%"+m_client_code+"%' "+
				" AND   B.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
				//" AND   B.LOCATION_CODE  LIKE '%"+m_location_code+"%' "+
				" AND   A.ENT_USER='"+m_username+"' "+
				" AND   B.ENT_USER='"+m_username+"' "+
				" AND   A.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')   "+
				" AND   A.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')    "+
				" AND   B.STATUS=1 "+
				" GROUP BY B.FINANCE_NO,TO_CHAR(A.RENTAL_DATE,'MONTH-YYYY'),TO_CHAR(A.RENTAL_DATE,'MM-YYYY'),B.TRANSACTION_TYPE ,TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY') ,B.LOCATION_CODE  "+
				" ORDER BY TO_DATE(TO_CHAR(A.RENTAL_DATE,'MM-YYYY'),'MM-YYYY'),LOCATION_DESC  ");

			}
						
						
			/*rs = stmt.executeQuery( " SELECT  TO_CHAR(A.ACTIVATED_DATE,'Month') MONTH,"+//1
															"         COUNT(A.FINANCE_NO), "+//2
															"         SUM(D.CAPITAL_AMOUNT),"+//3
															"         SUM(D.GRENTAL_AMOUNT) , "+ //4
															"         SUM(D.INTEREST_AMOUNT), "+ //5 
															"         "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE)  "+//6
															" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
															"      "+m_schema_name+".AF_CO_MAS_CLIENT B, "+
															"      "+m_schema_name+".CO_CO_MAS_EMPLOYEE C, "+
															"      "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT D "+
															" WHERE  A.CLIENT_CODE=B.CLIENT_CODE "+
															" AND    A.COLLECTION_OFFICER=C.EMP_CODE "+
 															" AND    A.APPLICATION_NO = D.APPLICATION_NO "+
 															" AND    APPLICATION_STATUS='ACTIVATED' "+
														  " AND    C.LOCATION_CODE  ='"+m_location_code+"' "+
															" AND    A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
															" AND    TO_CHAR(A.ACTIVATED_DATE,'MM') <= TO_CHAR(TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY'),'MM') "+
															" GROUP BY TO_CHAR(A.ACTIVATED_DATE,'Month'),A.TRANSACTION_TYPE,TO_CHAR(A.ACTIVATED_DATE,'MM') "+
															" ORDER BY TO_CHAR(A.ACTIVATED_DATE,'MM')");
															
				*/
				
			
			
			boolean more = rs.next();
			int count = 1;
			
			if(!more){
			out.println("<table class='table' border='0' width=100% align='center'>   "); 
			out.println("<tr align='center' width='100%'><td>&nbsp;</td></tr>");
			out.println("<tr align='center' width='100%'><td>");
			out.println("<font color='red'>No data found....!</font>"); 
			out.println("</td></tr>"); 
			out.println("</table>"); 
			}
			else{		
			out.println("<table class='table' border='0' width=100% >"); 
			out.println("<tr class=pdn_txtpos2 >"); 
			out.println("<td width='3%'  align='left' >No.</td>"); 
			out.println("<td width='8%' align='left' >Month</td>");
			out.println("<td width='10%' align='left' >Branch Name</td>"); 
			out.println("<td width='10%' align='left' >Finance No</td>"); 
			out.println("<td width='10%' align='left' >Product</td>"); 
			out.println("<td width='15%' align='right' >Rent</td>"); 
			out.println("<td width='15%' align='right' >Principle</td>"); 
			out.println("<td width='15%' align='right' >Income</td>");
			out.println("<td width='8%' align='center' >No.of Agreements</td>"); 

			out.println("</tr>"); 			
			}
			
			while(more){	
			if(count%2==1){
			out.println("<tr bgcolor=\"#FFFFFF\" >"); 
			}
			else{
			out.println("<tr bgcolor=\"#C0C0C0\" >");
			}
			/*out.println("<td width='5%'  align='left' >"+count+"</td>"); 
			out.println("<td width='10%' align='left' >"+rs.getString(1)+"</td>"); 
			out.println("<td width='10%' align='left' >"+rs.getString(6)+"</td>"); 
			out.println("<td width='15%' align='right' >"+nf1.format(rs.getDouble(4))+"</td>"); 
			out.println("<td width='15%' align='right' >"+nf1.format(rs.getDouble(3))+"</td>"); 
			out.println("<td width='15%' align='right' >"+nf1.format(rs.getDouble(5))+"</td>");
			out.println("<td width='8%' align='center' >"+rs.getString(2)+"</td>"); 
			*/
			out.println("<td width='5%'  align='left' >"+count+"</td>"); 
			out.println("<td width='10%' align='left' >"+rs.getString(1)+"</td>"); 
			out.println("<td width='10%' align='left' >"+rs.getString(7)+"</td>"); 
			out.println("<td width='10%' align='left' >"+rs.getString(8)+"</td>"); 
			out.println("<td width='10%' align='left' >"+rs.getString(6)+"</td>"); 
			out.println("<td width='15%' align='right' >"+nf1.format(rs.getDouble(4))+"</td>"); 
			out.println("<td width='15%' align='right' >"+nf1.format(rs.getDouble(3))+"</td>"); 
			out.println("<td width='15%' align='right' >"+nf1.format(rs.getDouble(5))+"</td>");
			out.println("<td width='8%' align='center' >"+rs.getString(2)+"</td>"); 
			
			out.println("</tr>"); 
			count = count+1;
			more = rs.next();
			}
			
			out.println("</table>"); 
			
			out.println("</body><html>");
			
			}
			
			else if(chksql.equals("agreement_wise_detail_report_NIBSM")){
			
			String m_activ_cut_date = req.getParameter("activ_cut_date");
			String m_prod_level = req.getParameter("prod_level");
			String m_client_code = req.getParameter("client_code");
			String m_location_code =req.getParameter("location_code");
			
			String m_from_date = req.getParameter("from_date");
			String m_to_date = req.getParameter("to_date");
			
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Agreement Wise Detail Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<body>");
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' align='center'>   "); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td align='left' style='height: 18px' id='help_box' ><b>Agreement Wise Detail Report - NIBSM </b></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
						
			
			
			if(m_location_code.equals("")){
		
				/*** COMMENT BY NS 17-11-2009 
				    rs = stmt.executeQuery( 
				    " SELECT A.FINANCE_NO, "+
						" "+m_schema_name+".AF_CO_GET_APP_NIBSM_DATE(A.APPLICATION_NO) M_DATE, "+
						" "+m_schema_name+".AF_CO_GET_APP_NIBSM(A.APPLICATION_NO) NIBSM "+
						" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,  "+ 
						"      "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT D , "+
						"      "+m_schema_name+".AF_CO_PRO_APP_PRICING E "+
						" WHERE  A.APPLICATION_NO = D.APPLICATION_NO  "+
						" AND    A.APPLICATION_NO = E.APPLICATION_NO  "+
						" AND    APPLICATION_STATUS='ACTIVATED'  "+
						" AND    A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
						" AND    E.NIBSM > 0  "+
						" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS   "+
						" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY') )  "+
						" AND    D.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND    D.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" GROUP BY A.FINANCE_NO,A.APPLICATION_NO ");
			 *****/
				
				
						
				rs = stmt.executeQuery(
				" SELECT "+
				" DISTINCT B.FINANCE_NO , "+
				" TO_CHAR(B.NIBSM_EFF_DATE,'DD-MM-YYYY') , "+
				" B.NIBSM_AMOUNT  "+
				" FROM "+m_schema_name+".AF_TBD_MATURITY_DET A , "+m_schema_name+".AF_TBD_MATURITY B "+
				" WHERE A.APPLICATION_NO(+)=B.APPLICATION_NO  "+
				" AND   A.ENT_USER='"+m_username+"' "+
				" AND   B.ENT_USER='"+m_username+"' "+
				" AND   B.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+ //added by ns 15-12-2009
				" AND   A.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')   "+
				" AND   A.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')    "+
				" AND   B.STATUS=1 "+
				" AND   B.NIBSM_AMOUNT > 0 "+
				" ORDER BY B.FINANCE_NO  ");
						
				
			
						}
						else{ 
						
				
						/*** COMMENT BY NS 17-11-2009
						rs = stmt.executeQuery( 
				    " SELECT A.FINANCE_NO, "+
						" "+m_schema_name+".AF_CO_GET_APP_NIBSM_DATE(A.APPLICATION_NO) M_DATE, "+
						" "+m_schema_name+".AF_CO_GET_APP_NIBSM(A.APPLICATION_NO) NIBSM "+
						" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,  "+ 
						"      "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT D , "+
						"      "+m_schema_name+".AF_CO_PRO_APP_PRICING E "+
						" WHERE  A.APPLICATION_NO = D.APPLICATION_NO  "+
						" AND    A.APPLICATION_NO = E.APPLICATION_NO  "+
						" AND    APPLICATION_STATUS='ACTIVATED'  "+
						" AND    A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
						" AND    E.NIBSM > 0  "+
						" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS   "+
						" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY') )  "+
						" AND    D.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND    D.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" GROUP BY A.FINANCE_NO ,A.APPLICATION_NO");
						**/
						
							rs = stmt.executeQuery(
							" SELECT "+
							" DISTINCT B.FINANCE_NO , "+
							" TO_CHAR(B.NIBSM_EFF_DATE,'DD-MM-YYYY') , "+
							" B.NIBSM_AMOUNT  "+
							" FROM "+m_schema_name+".AF_TBD_MATURITY_DET A , "+m_schema_name+".AF_TBD_MATURITY B "+
							" WHERE A.APPLICATION_NO(+)=B.APPLICATION_NO  "+
							" AND   A.ENT_USER='"+m_username+"' "+
							" AND   B.ENT_USER='"+m_username+"' "+
							" AND   B.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+ //added by ns 15-12-2009
							" AND   B.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
							" AND   A.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')   "+
							" AND   A.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')    "+
							" AND   B.STATUS=1 "+
							" AND   B.NIBSM_AMOUNT > 0 "+
							" ORDER BY B.FINANCE_NO  ");

				
						
						}
			
			boolean more = rs.next();
			int count = 1;
			
			if(!more){
			out.println("<table class='table' border='0' width=100% align='center'>   "); 
			out.println("<tr align='center' width='100%'><td>&nbsp;</td></tr>");
			out.println("<tr align='center' width='100%'><td>");
			out.println("<font color='red'>No data found....!</font>"); 
			out.println("</td></tr>"); 
			out.println("</table>"); 
			}
			else{
			out.println("<table class='table' border='0' width=50% align='center' >   "); 
			out.println("<tr class=pdn_txtpos2 >"); 
			out.println("<td width='2%'  align='left' >No.</td>"); 
			out.println("<td width='15%' align='left' >Agreement No</td>"); 
			out.println("<td width='15%' align='left' >Effective Date</td>"); 
			out.println("<td width='15%' align='right' >NIBSM</td>"); 
		//	out.println("<td width='*%' align='left' >&nbsp;</td>"); 
			
			out.println("</tr>"); 
			}	
			
			while(more){	
			if(count%2==1){
			out.println("<tr bgcolor=\"#FCEBC5\" >"); 
			}
			else{
			out.println("<tr bgcolor=\"#FCEBC5\" >");
			}
			out.println("<td width='5%'  align='left' >"+count+"</td>"); 
			out.println("<td width='15%' align='left' >"+rs.getString(1)+"</td>"); 
			out.println("<td width='15%' align='left' >"+rs.getString(2)+"</td>"); 
			out.println("<td width='15%' align='right' >"+nf1.format(rs.getDouble(3))+"</td>"); 
			//out.println("<td width='*%' align='left' >&nbsp;</td>"); 
			
			out.println("</tr>"); 
			count = count+1;
			more = rs.next();
			}
			
			out.println("</table>"); 
			
			out.println("</body><html>");
			
			}
			else if(chksql.equals("agreement_wise_detail_report_AMI")){
			
			String m_activ_cut_date = req.getParameter("activ_cut_date");
			String m_prod_level = req.getParameter("prod_level");
			String m_client_code = req.getParameter("client_code");
			String m_location_code =req.getParameter("location_code");
			
			String m_from_date = req.getParameter("from_date");
			String m_to_date = req.getParameter("to_date");
			
						
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Agreement Wise Detail Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<body>");
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' align='center'>   "); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td align='left' style='height: 18px' id='help_box' ><b>Agreement Wise Detail Report - AMI</b></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
						
			
			
			if(m_location_code.equals("")){
		

				
				   /* rs = stmt.executeQuery( 
				    " SELECT A.FINANCE_NO, "+
						" "+m_schema_name+".AF_CO_GET_AMI_EFF_DATE(A.APPLICATION_NO) M_DATE, "+
						" "+m_schema_name+".AF_CO_GET_AMI_AMOUNT_2(A.APPLICATION_NO) AMI "+
						" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,  "+ 
						"      "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT D , "+
						"      "+m_schema_name+".AF_CO_PRO_APP_PRICING E "+
						" WHERE  A.APPLICATION_NO = D.APPLICATION_NO  "+
						" AND    A.APPLICATION_NO = E.APPLICATION_NO  "+
						" AND    APPLICATION_STATUS='ACTIVATED'  "+
						" AND    A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
						" AND    E.AMI > 0  "+
						" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS   "+
						" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY') )  "+
						" AND    D.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND    D.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" GROUP BY A.FINANCE_NO,A.APPLICATION_NO ");
						*/
						
						rs = stmt.executeQuery(
							" SELECT "+
							" DISTINCT B.FINANCE_NO , "+
							" TO_CHAR(B.AMI_EFF_DATE,'DD-MM-YYYY') , "+
							" B.AMI_AMOUNT  "+
							" FROM "+m_schema_name+".AF_TBD_MATURITY_DET A , "+m_schema_name+".AF_TBD_MATURITY B "+
							" WHERE A.APPLICATION_NO(+)=B.APPLICATION_NO  "+
							" AND   A.ENT_USER='"+m_username+"' "+
							" AND   B.ENT_USER='"+m_username+"' "+
							" AND   A.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')   "+
							" AND   A.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')    "+
							" AND   B.STATUS=1 "+
							" AND   B.AMI_AMOUNT > 0 "+
							" ORDER BY B.FINANCE_NO  ");
				
			
						}
						else{ 
						

					/*	rs = stmt.executeQuery( 
				    " SELECT A.FINANCE_NO, "+
						" "+m_schema_name+".AF_CO_GET_AMI_EFF_DATE(A.APPLICATION_NO) M_DATE, "+
						" "+m_schema_name+".AF_CO_GET_AMI_AMOUNT_2(A.APPLICATION_NO) NIBSM "+
						" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,  "+ 
						"      "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT D , "+
						"      "+m_schema_name+".AF_CO_PRO_APP_PRICING E "+
						" WHERE  A.APPLICATION_NO = D.APPLICATION_NO  "+
						" AND    A.APPLICATION_NO = E.APPLICATION_NO  "+
						" AND    APPLICATION_STATUS='ACTIVATED'  "+
						" AND    A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
						" AND    E.AMI > 0  "+
						" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS   "+
						" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY') )  "+
						" AND    D.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND    D.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" GROUP BY A.FINANCE_NO ,A.APPLICATION_NO");
           */
						
							rs = stmt.executeQuery(
							" SELECT "+
							" DISTINCT B.FINANCE_NO , "+
							" TO_CHAR(B.AMI_EFF_DATE,'DD-MM-YYYY') , "+
							" B.AMI_AMOUNT  "+
							" FROM "+m_schema_name+".AF_TBD_MATURITY_DET A , "+m_schema_name+".AF_TBD_MATURITY B "+
							" WHERE A.APPLICATION_NO(+)=B.APPLICATION_NO  "+
							" AND   A.ENT_USER='"+m_username+"' "+
							" AND   B.ENT_USER='"+m_username+"' "+
							" AND   B.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
							" AND   A.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')   "+
							" AND   A.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')    "+
							" AND   B.STATUS=1 "+
							" AND   B.AMI_AMOUNT > 0 "+
							" ORDER BY B.FINANCE_NO  ");
				
						
						}
			
			boolean more = rs.next();
			int count = 1;
			
			if(!more){
			out.println("<table class='table' border='0' width=100% align='center'>   "); 
			out.println("<tr align='center' width='100%'><td>&nbsp;</td></tr>");
			out.println("<tr align='center' width='100%'><td>");
			out.println("<font color='red'>No data found....!</font>"); 
			out.println("</td></tr>"); 
			out.println("</table>"); 
			}
			else{
			out.println("<table class='table' border='0' width=50% align='center' >   "); 
			out.println("<tr class=pdn_txtpos2 >"); 
			out.println("<td width='2%'  align='left' >No.</td>"); 
			out.println("<td width='15%' align='left' >Agreement No</td>"); 
			out.println("<td width='15%' align='left' >Effective Date</td>"); 
			out.println("<td width='15%' align='right' >AMI</td>"); 
		//	out.println("<td width='*%' align='left' >&nbsp;</td>"); 
			
			out.println("</tr>"); 
			}	
			
			while(more){	
			if(count%2==1){
			out.println("<tr bgcolor=\"#FCEBC5\" >"); 
			}
			else{
			out.println("<tr bgcolor=\"#FCEBC5\" >");
			}
			out.println("<td width='5%'  align='left' >"+count+"</td>"); 
			out.println("<td width='15%' align='left' >"+rs.getString(1)+"</td>"); 
			out.println("<td width='15%' align='left' >"+rs.getString(2)+"</td>"); 
			out.println("<td width='15%' align='right' >"+nf1.format(rs.getDouble(3))+"</td>"); 
			//out.println("<td width='*%' align='left' >&nbsp;</td>"); 
			
			out.println("</tr>"); 
			count = count+1;
			more = rs.next();
			}
			
			out.println("</table>"); 
			
			out.println("</body><html>");
			
			}
			
			
			else if(chksql.equals("agreement_wise_detail_report_new")){
			
			String m_activ_cut_date = req.getParameter("activ_cut_date");
			String m_prod_level = req.getParameter("prod_level");
			String m_client_code = req.getParameter("client_code");
			String m_location_code =req.getParameter("location_code");
			
			String m_from_date = req.getParameter("from_date");
			String m_to_date = req.getParameter("to_date");
			
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Agreement Wise Detail Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<body>");
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' align='center'>   "); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td align='left' style='height: 18px' id='help_box' ><b>Agreement Wise Detail Report</b></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
						
			
			if(m_location_code.equals("ALL")){
			
			
					/***********rs = stmt.executeQuery( 
					"SELECT  DISTINCT A.FINANCE_NO,  "+
					" TO_CHAR(D.RENTAL_DATE,'Month-YYYY') MONTH, "+
					" D.CAPITAL_AMOUNT, "+
					" D.NET_RENTAL_AMOUNT , "+  
					" D.INTEREST_AMOUNT  "+
					" ,TO_CHAR(RENTAL_DATE,'DD-MM-YYYY'), "+
					"  "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE)  "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,  "+ 
					" "+m_schema_name+".AF_CO_MAS_CLIENT B,  "+
					" "+m_schema_name+".CO_CO_MAS_EMPLOYEE C,  "+ 
					" "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT D  "+
					" WHERE  A.CLIENT_CODE=B.CLIENT_CODE  "+
					" AND    A.COLLECTION_OFFICER=C.EMP_CODE  "+
					" AND    A.APPLICATION_NO = D.APPLICATION_NO  "+ 
					" AND    APPLICATION_STATUS='ACTIVATED'  "+
					//" AND    A.DIVISION_CODE='BD' "+	
					" AND    A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
					" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS   "+
					" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY')   "+
					" )   "+
					" AND    D.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
					" AND    D.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
					" ORDER BY  TO_CHAR(RENTAL_DATE,'DD-MM-YYYY') ");
					***********************/
					
					/********************* Comment by ns 17-11-2009 *************
					//added by ns 07-09-2009 			
							rs = stmt.executeQuery(
																	" SELECT  DISTINCT A.FINANCE_NO, "+
																	" TO_CHAR(B.RENTAL_DATE,'MONTH-YYYY') MONTH,"+ //1
																	" SUM(B.CAPITAL_AMOUNT),"+ //3
																	" SUM(B.NET_RENTAL_AMOUNT) ,  "+ //4
																	" SUM(B.INTEREST_AMOUNT),  "+ //5 
																	" TO_CHAR(B.RENTAL_DATE,'DD-MM-YYYY'), "+
																	" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE)  "+ //6
																	" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT B  "+
																	"      ,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C,"+m_schema_name+".AF_CO_PRO_INVOICE D "+
																	" WHERE C.APPLICATION_NO = B.APPLICATION_NO "+
																	" AND   A.APPLICATION_NO = B.APPLICATION_NO  "+
																	" AND   C.INVOICE_NO     = B.PRO_INVOICE_NO "+
																	" AND   C.PRICING_NO     = B.PRICING_NO "+
																	" AND   B.INVOICE_NO     = D.INVOICE_NO(+) "+
																	//" AND   D.ACTIVE_STATUS  = 'Y'  "+
																	" AND   C.ACTIVE_STATUS IN ('Y') "+
																	" AND   A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
																	" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS   "+
																	" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY')  "+
																	" AND     APPLICATION_STATUS =  'ACTIVATED' "+
																	" OR    ( APPLICATION_STATUS <> 'ACTIVATED' "+
																	" AND    TO_DATE("+m_schema_name+".AF_CO_GET_TER_DATE(FINANCE_NO, '"+m_activ_cut_date+"'),'DD-MM-YYYY')  > TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY')  )"+
																	" )  "+
																	" AND    B.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
																	" AND    B.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')   "+
																	" GROUP BY A.FINANCE_NO,TO_CHAR(B.RENTAL_DATE,'MONTH-YYYY'),TO_CHAR(B.RENTAL_DATE,'MM-YYYY'),A.TRANSACTION_TYPE ,TO_CHAR(B.RENTAL_DATE,'DD-MM-YYYY')  "+
																	" ORDER BY TO_CHAR(RENTAL_DATE,'DD-MM-YYYY') ");
																	
				 **********************/
					
					
																	rs = stmt.executeQuery(
																	//out.println(
																	" SELECT "+
																	" DISTINCT B.FINANCE_NO, "+
																	" TO_CHAR(A.RENTAL_DATE,'MONTH-YYYY') MONTH ,"+
																	" SUM(A.CAPITAL_AMOUNT) , "+
																	" SUM(A.NET_RENTAL_AMOUNT) , "+
																	" SUM(A.INTEREST_AMOUNT), "+
																	" TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY'), "+
																	" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(B.TRANSACTION_TYPE)  "+ //6
																	" FROM "+m_schema_name+".AF_TBD_MATURITY_DET A , "+m_schema_name+".AF_TBD_MATURITY B "+
																	" WHERE A.APPLICATION_NO(+)=B.APPLICATION_NO  "+
																	" AND   A.ENT_USER='"+m_username+"' "+
																	" AND   B.ENT_USER='"+m_username+"' "+
																	" AND   B.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
																	" AND   A.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')   "+
																	" AND   A.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')    "+
																	" AND   B.STATUS=1 "+
																	" GROUP BY B.FINANCE_NO,TO_CHAR(A.RENTAL_DATE,'MONTH-YYYY'),TO_CHAR(A.RENTAL_DATE,'MM-YYYY'),B.TRANSACTION_TYPE ,TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY')  "+
																	" ORDER BY TO_CHAR(RENTAL_DATE,'DD-MM-YYYY')  ");

					
					


			
			}
			else if(m_location_code.equals("BIKE")){
			
			/*rs = stmt.executeQuery( " SELECT  TO_CHAR(A.ACTIVATED_DATE,'Month-YYYY') MONTH, "+//1
															" COUNT(A.FINANCE_NO), "+//2
															" A.FINANCE_NO, "+//3
														  " SUM(D.CAPITAL_AMOUNT), "+//4
															" SUM(D.GRENTAL_AMOUNT) , "+//5
															" SUM(D.INTEREST_AMOUNT), "+  //6
															" A.TRANSACTION_TYPE "+//7
														  " FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
															"        "+m_schema_name+".AF_CO_MAS_CLIENT B, "+
															"        "+m_schema_name+".CO_CO_MAS_EMPLOYEE C, "+
														  "        "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT D "+
														  " WHERE  A.CLIENT_CODE=B.CLIENT_CODE "+
														  " AND    A.COLLECTION_OFFICER=C.EMP_CODE "+
														  " AND    A.APPLICATION_NO = D.APPLICATION_NO "+
														  " AND    APPLICATION_STATUS='ACTIVATED' "+
														  " AND    A.TRANSACTION_TYPE  ='"+m_prod_level+"' "+ 
														  " AND    TO_CHAR(A.activated_date,'MM') <= TO_CHAR(TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY'),'MM') "+
														  " GROUP BY A.ACTIVATED_DATE,A.FINANCE_NO ,A.TRANSACTION_TYPE "+
														  " ORDER BY TO_CHAR(A.ACTIVATED_DATE,'MM') ");
															
				*/
				
				/**************
				//out.println(
					rs = stmt.executeQuery( 
					"SELECT  DISTINCT A.FINANCE_NO,  "+
					" TO_CHAR(D.RENTAL_DATE,'Month-YYYY') MONTH, "+
					" D.CAPITAL_AMOUNT, "+
					" D.NET_RENTAL_AMOUNT , "+  
					" D.INTEREST_AMOUNT  "+
					" ,TO_CHAR(RENTAL_DATE,'DD-MM-YYYY'), "+
					"  "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE)  "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,  "+ 
					" "+m_schema_name+".AF_CO_MAS_CLIENT B,  "+
					" "+m_schema_name+".CO_CO_MAS_EMPLOYEE C,  "+ 
					" "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT D  "+
					" WHERE  A.CLIENT_CODE=B.CLIENT_CODE  "+
					" AND    A.COLLECTION_OFFICER=C.EMP_CODE  "+
					" AND    A.APPLICATION_NO = D.APPLICATION_NO  "+ 
					" AND    APPLICATION_STATUS='ACTIVATED'  "+
					" AND    A.DIVISION_CODE='BD' "+	
					" AND    A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
					" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS   "+
					" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY')   "+
					" )   "+
					" AND    D.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
					" AND    D.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
					" ORDER BY  TO_CHAR(RENTAL_DATE,'DD-MM-YYYY') ");
					**************/
					
					/***** Comment By ns 17-11-2009
					rs = stmt.executeQuery(
																	" SELECT  DISTINCT A.FINANCE_NO, "+
																	" TO_CHAR(B.RENTAL_DATE,'MONTH-YYYY') MONTH,"+ //1
																	" SUM(B.CAPITAL_AMOUNT),"+ //3
																	" SUM(B.NET_RENTAL_AMOUNT) ,  "+ //4
																	" SUM(B.INTEREST_AMOUNT),  "+ //5 
																	" TO_CHAR(B.RENTAL_DATE,'DD-MM-YYYY'), "+
																	" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE)  "+ //6
																	" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT B  "+
																	"      ,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C,"+m_schema_name+".AF_CO_PRO_INVOICE D "+
																	" WHERE C.APPLICATION_NO = B.APPLICATION_NO "+
																	" AND   A.APPLICATION_NO = B.APPLICATION_NO  "+
																	" AND   C.INVOICE_NO     = B.PRO_INVOICE_NO "+
																	" AND   C.PRICING_NO     = B.PRICING_NO "+
																	" AND   B.INVOICE_NO     = D.INVOICE_NO(+) "+
																	//" AND   D.ACTIVE_STATUS  = 'Y'  "+
																	" AND   C.ACTIVE_STATUS IN ('Y') "+
																	" AND   A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
																	" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS   "+
																	" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY')  "+
																	" AND     APPLICATION_STATUS =  'ACTIVATED' "+
																	" OR    ( APPLICATION_STATUS <> 'ACTIVATED' "+
																	" AND    TO_DATE("+m_schema_name+".AF_CO_GET_TER_DATE(FINANCE_NO, '"+m_activ_cut_date+"'),'DD-MM-YYYY')  > TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY')  )"+
																	" )  "+
																	" AND    A.DIVISION_CODE='BD' "+	
																	" AND    B.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
																	" AND    B.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')   "+
																	" GROUP BY A.FINANCE_NO,TO_CHAR(B.RENTAL_DATE,'MONTH-YYYY'),TO_CHAR(B.RENTAL_DATE,'MM-YYYY'),A.TRANSACTION_TYPE ,TO_CHAR(B.RENTAL_DATE,'DD-MM-YYYY')  "+
																	" ORDER BY TO_CHAR(RENTAL_DATE,'DD-MM-YYYY') ");
				*****/
				
				
																					rs = stmt.executeQuery(
																	//out.println(
																	" SELECT "+
																	" DISTINCT B.FINANCE_NO, "+
																	" TO_CHAR(A.RENTAL_DATE,'MONTH-YYYY') MONTH ,"+
																	" SUM(A.CAPITAL_AMOUNT) , "+
																	" SUM(A.NET_RENTAL_AMOUNT) , "+
																	" SUM(A.INTEREST_AMOUNT), "+
																	" TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY'), "+
																	" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(B.TRANSACTION_TYPE)  "+ //6
																	" FROM "+m_schema_name+".AF_TBD_MATURITY_DET A , "+m_schema_name+".AF_TBD_MATURITY B "+
																	" WHERE A.APPLICATION_NO(+)=B.APPLICATION_NO  "+
																	" AND   A.ENT_USER='"+m_username+"' "+
																	" AND   B.DIVISION_CODE='BD' "+	
																	" AND   B.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
																	" AND   B.ENT_USER='"+m_username+"' "+
																	" AND   A.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')   "+
																	" AND   A.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')    "+
																	" AND   B.STATUS=1 "+
																	" GROUP BY B.FINANCE_NO,TO_CHAR(A.RENTAL_DATE,'MONTH-YYYY'),TO_CHAR(A.RENTAL_DATE,'MM-YYYY'),B.TRANSACTION_TYPE ,TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY')  "+
																	" ORDER BY TO_CHAR(RENTAL_DATE,'DD-MM-YYYY')  ");

					
					
						}
						else{ 
						
		//							out.println(

				/**************
				
				  rs = stmt.executeQuery(
					"SELECT  DISTINCT A.FINANCE_NO,  "+
					" TO_CHAR(D.RENTAL_DATE,'Month-YYYY') MONTH, "+
					" D.CAPITAL_AMOUNT, "+
					" D.NET_RENTAL_AMOUNT , "+  
					" D.INTEREST_AMOUNT , "+
					" A.TRANSACTION_TYPE "+
					" ,TO_CHAR(RENTAL_DATE,'DD-MM-YYYY'), "+
					" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE)  "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,  "+ 
					" "+m_schema_name+".AF_CO_MAS_CLIENT B,  "+
					" "+m_schema_name+".CO_CO_MAS_EMPLOYEE C,  "+ 
					" "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT D  "+
					" WHERE  A.CLIENT_CODE=B.CLIENT_CODE  "+
					" AND    A.COLLECTION_OFFICER=C.EMP_CODE  "+
					" AND    A.APPLICATION_NO = D.APPLICATION_NO  "+ 
					" AND    APPLICATION_STATUS='ACTIVATED'  "+
					" AND    A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
				  " AND    C.LOCATION_CODE  ='"+m_location_code+"' "+
					" AND    A.DIVISION_CODE='AF' "+	
					" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS   "+
					" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY')   "+
					" )   "+
					" AND    D.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
					" AND    D.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
					" ORDER BY  TO_CHAR(RENTAL_DATE,'DD-MM-YYYY')  ");
				****************/
				
	       
					/*    COMMENT BY NS 17-11-2009
					//added by ns 07-09-2009 			
							rs = stmt.executeQuery(
																	" SELECT  DISTINCT A.FINANCE_NO, "+
																	" TO_CHAR(B.RENTAL_DATE,'MONTH-YYYY') MONTH,"+ //1
																	" SUM(B.CAPITAL_AMOUNT),"+ //3
																	" SUM(B.NET_RENTAL_AMOUNT) ,  "+ //4
																	" SUM(B.INTEREST_AMOUNT),  "+ //5 
																	" TO_CHAR(B.RENTAL_DATE,'DD-MM-YYYY'), "+
																	" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE)  "+ //6
																	" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT B  "+
																	"      ,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C,"+m_schema_name+".AF_CO_PRO_INVOICE D "+
																	" WHERE C.APPLICATION_NO = B.APPLICATION_NO "+
																	" AND   A.APPLICATION_NO = B.APPLICATION_NO  "+
																	" AND   C.INVOICE_NO     = B.PRO_INVOICE_NO "+
																	" AND   C.PRICING_NO     = B.PRICING_NO "+
																	" AND   B.INVOICE_NO     = D.INVOICE_NO "+
																	" AND   D.ACTIVE_STATUS  = 'Y'  "+
																	" AND   C.ACTIVE_STATUS IN ('Y') "+
																	" AND   A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
																	" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS   "+
																	" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY')  "+
																	" AND     APPLICATION_STATUS =  'ACTIVATED' "+
																	" OR    ( APPLICATION_STATUS <> 'ACTIVATED' "+
																	" AND    TO_DATE("+m_schema_name+".AF_CO_GET_TER_DATE(FINANCE_NO, '"+m_activ_cut_date+"'),'DD-MM-YYYY')  > TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY')  )"+
																	
																	" )  "+
																	" AND    A.DIVISION_CODE='AF' "+	
																	" AND    B.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
																	" AND    B.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')   "+
																	" GROUP BY A.FINANCE_NO,TO_CHAR(B.RENTAL_DATE,'MONTH-YYYY'),TO_CHAR(B.RENTAL_DATE,'MM-YYYY'),A.TRANSACTION_TYPE ,TO_CHAR(B.RENTAL_DATE,'DD-MM-YYYY')  "+
																	" ORDER BY TO_CHAR(RENTAL_DATE,'DD-MM-YYYY') ");
						*/
						
						
						
												rs = stmt.executeQuery(
																	//out.println(
																	" SELECT "+
																	" DISTINCT B.FINANCE_NO, "+
																	" TO_CHAR(A.RENTAL_DATE,'MONTH-YYYY') MONTH ,"+
																	" SUM(A.CAPITAL_AMOUNT) , "+
																	" SUM(A.NET_RENTAL_AMOUNT) , "+
																	" SUM(A.INTEREST_AMOUNT), "+
																	" TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY'), "+
																	" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(B.TRANSACTION_TYPE)  "+ //6
																	" FROM "+m_schema_name+".AF_TBD_MATURITY_DET A , "+m_schema_name+".AF_TBD_MATURITY B "+
																	" WHERE A.APPLICATION_NO(+)=B.APPLICATION_NO  "+
																	" AND   A.ENT_USER='"+m_username+"' "+
																	" AND   B.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
				                          " AND   B.LOCATION_CODE  ='"+m_location_code+"' "+
																	" AND   B.ENT_USER='"+m_username+"' "+
																	" AND   A.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')   "+
																	" AND   A.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')    "+
																	" AND   B.STATUS=1 "+
																	" GROUP BY B.FINANCE_NO,TO_CHAR(A.RENTAL_DATE,'MONTH-YYYY'),TO_CHAR(A.RENTAL_DATE,'MM-YYYY'),B.TRANSACTION_TYPE ,TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY')  "+
																	" ORDER BY TO_CHAR(RENTAL_DATE,'DD-MM-YYYY')  ");

								

					
									
						}
			
			boolean more = rs.next();
			int count = 1;
			
			if(!more){
			out.println("<table class='table' border='0' width=100% align='center'>   "); 
			out.println("<tr align='center' width='100%'><td>&nbsp;</td></tr>");
			out.println("<tr align='center' width='100%'><td>");
			out.println("<font color='red'>No data found....!</font>"); 
			out.println("</td></tr>"); 
			out.println("</table>"); 
			}
			else{
			out.println("<table class='table' border='0' width=100% >   "); 
			out.println("<tr class=pdn_txtpos2 >"); 
			out.println("<td width='3%'  align='left' >No.</td>"); 
			out.println("<td width='8%' align='left' >Month</td>");
			out.println("<td width='10%' align='left' >Agreement No</td>"); 
			out.println("<td width='10%' align='left' >Product</td>"); 
			out.println("<td width='15%' align='right' >Rent</td>"); 
			out.println("<td width='15%' align='right' >Principle</td>"); 
			out.println("<td width='15%' align='right' >Income</td>");
			//out.println("<td width='8%' align='center' >No.of Agreements</td>"); 
			out.println("</tr>"); 
			}	
			
			while(more){	
			if(count%2==1){
			out.println("<tr bgcolor=\"#FFFFFF\" >"); 
			}
			else{
			out.println("<tr bgcolor=\"#C0C0C0\" >");
			}
			out.println("<td width='5%'  align='left' >"+count+"</td>"); 
			out.println("<td width='10%' align='left' >"+rs.getString(2)+"</td>"); 
			out.println("<td width='10%' align='left' >"+rs.getString(1)+"</td>"); 
			out.println("<td width='10%' align='left' >"+rs.getString(7)+"</td>"); 
			out.println("<td width='15%' align='right' >"+nf1.format(rs.getDouble(4))+"</td>"); 
			out.println("<td width='15%' align='right' >"+nf1.format(rs.getDouble(3))+"</td>"); 
			out.println("<td width='15%' align='right' >"+nf1.format(rs.getDouble(5))+"</td>");
			
			out.println("</tr>"); 
			count = count+1;
			more = rs.next();
			}
			
			out.println("</table>"); 
			
			out.println("</body><html>");
			
			}
			
			else if(chksql.equals("agreement_wise_detail_report")){
			
			String m_activ_cut_date = req.getParameter("activ_cut_date");
			String m_prod_level = req.getParameter("prod_level");
			String m_client_code = req.getParameter("client_code");
			String m_location_code =req.getParameter("location_code");
			
			String m_from_date = req.getParameter("from_date");
			String m_to_date = req.getParameter("to_date");
			

			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Agreement Wise Detail Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<body>");
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' align='center'>   "); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td align='left' style='height: 18px' id='help_box' ><b>Agreement Wise Detail Report</b></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
						
			
			if(m_location_code.equals("ALL")){
			
			
					/***********rs = stmt.executeQuery( 
					"SELECT  DISTINCT A.FINANCE_NO,  "+
					" TO_CHAR(D.RENTAL_DATE,'Month-YYYY') MONTH, "+
					" D.CAPITAL_AMOUNT, "+
					" D.NET_RENTAL_AMOUNT , "+  
					" D.INTEREST_AMOUNT  "+
					" ,TO_CHAR(RENTAL_DATE,'DD-MM-YYYY'), "+
					"  "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE)  "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,  "+ 
					" "+m_schema_name+".AF_CO_MAS_CLIENT B,  "+
					" "+m_schema_name+".CO_CO_MAS_EMPLOYEE C,  "+ 
					" "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT D  "+
					" WHERE  A.CLIENT_CODE=B.CLIENT_CODE  "+
					" AND    A.COLLECTION_OFFICER=C.EMP_CODE  "+
					" AND    A.APPLICATION_NO = D.APPLICATION_NO  "+ 
					" AND    APPLICATION_STATUS='ACTIVATED'  "+
					//" AND    A.DIVISION_CODE='BD' "+	
					" AND    A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
					" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS   "+
					" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY')   "+
					" )   "+
					" AND    D.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
					" AND    D.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
					" ORDER BY  TO_CHAR(RENTAL_DATE,'DD-MM-YYYY') ");
					***********************/
					
					//added by ns 07-09-2009 			
							rs = stmt.executeQuery(
																	" SELECT  DISTINCT A.FINANCE_NO, "+
																	" TO_CHAR(B.RENTAL_DATE,'MONTH-YYYY') MONTH,"+ //1
																	" SUM(B.CAPITAL_AMOUNT),"+ //3
																	" SUM(B.NET_RENTAL_AMOUNT) ,  "+ //4
																	" SUM(B.INTEREST_AMOUNT),  "+ //5 
																	" TO_CHAR(B.RENTAL_DATE,'DD-MM-YYYY'), "+
																	" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE)  "+ //6
																	" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT B  "+
																	"      ,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C,"+m_schema_name+".AF_CO_PRO_INVOICE D "+
																	" WHERE C.APPLICATION_NO = B.APPLICATION_NO "+
																	" AND   A.APPLICATION_NO = B.APPLICATION_NO  "+
																	" AND   C.INVOICE_NO     = B.PRO_INVOICE_NO "+
																	" AND   C.PRICING_NO     = B.PRICING_NO "+
																	" AND   B.INVOICE_NO     = D.INVOICE_NO(+) "+
																	//" AND   D.ACTIVE_STATUS  = 'Y'  "+
																	" AND   C.ACTIVE_STATUS IN ('Y') "+
																	" AND   A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
																	" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS   "+
																	" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY')  "+
																	" AND     APPLICATION_STATUS =  'ACTIVATED' "+
																	" OR    ( APPLICATION_STATUS <> 'ACTIVATED' "+
																	" AND    TO_DATE("+m_schema_name+".AF_CO_GET_TER_DATE(FINANCE_NO, '"+m_activ_cut_date+"'),'DD-MM-YYYY')  > TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY')  )"+
																	" )  "+
																	" AND    B.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
																	" AND    B.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')   "+
																	" GROUP BY A.FINANCE_NO,TO_CHAR(B.RENTAL_DATE,'MONTH-YYYY'),TO_CHAR(B.RENTAL_DATE,'MM-YYYY'),A.TRANSACTION_TYPE ,TO_CHAR(B.RENTAL_DATE,'DD-MM-YYYY')  "+
																	" ORDER BY TO_CHAR(RENTAL_DATE,'DD-MM-YYYY') ");


			
			}
			else if(m_location_code.equals("BIKE")){
			
			/*rs = stmt.executeQuery( " SELECT  TO_CHAR(A.ACTIVATED_DATE,'Month-YYYY') MONTH, "+//1
															" COUNT(A.FINANCE_NO), "+//2
															" A.FINANCE_NO, "+//3
														  " SUM(D.CAPITAL_AMOUNT), "+//4
															" SUM(D.GRENTAL_AMOUNT) , "+//5
															" SUM(D.INTEREST_AMOUNT), "+  //6
															" A.TRANSACTION_TYPE "+//7
														  " FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
															"        "+m_schema_name+".AF_CO_MAS_CLIENT B, "+
															"        "+m_schema_name+".CO_CO_MAS_EMPLOYEE C, "+
														  "        "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT D "+
														  " WHERE  A.CLIENT_CODE=B.CLIENT_CODE "+
														  " AND    A.COLLECTION_OFFICER=C.EMP_CODE "+
														  " AND    A.APPLICATION_NO = D.APPLICATION_NO "+
														  " AND    APPLICATION_STATUS='ACTIVATED' "+
														  " AND    A.TRANSACTION_TYPE  ='"+m_prod_level+"' "+ 
														  " AND    TO_CHAR(A.activated_date,'MM') <= TO_CHAR(TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY'),'MM') "+
														  " GROUP BY A.ACTIVATED_DATE,A.FINANCE_NO ,A.TRANSACTION_TYPE "+
														  " ORDER BY TO_CHAR(A.ACTIVATED_DATE,'MM') ");
															
				*/
				
				/**************
				//out.println(
					rs = stmt.executeQuery( 
					"SELECT  DISTINCT A.FINANCE_NO,  "+
					" TO_CHAR(D.RENTAL_DATE,'Month-YYYY') MONTH, "+
					" D.CAPITAL_AMOUNT, "+
					" D.NET_RENTAL_AMOUNT , "+  
					" D.INTEREST_AMOUNT  "+
					" ,TO_CHAR(RENTAL_DATE,'DD-MM-YYYY'), "+
					"  "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE)  "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,  "+ 
					" "+m_schema_name+".AF_CO_MAS_CLIENT B,  "+
					" "+m_schema_name+".CO_CO_MAS_EMPLOYEE C,  "+ 
					" "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT D  "+
					" WHERE  A.CLIENT_CODE=B.CLIENT_CODE  "+
					" AND    A.COLLECTION_OFFICER=C.EMP_CODE  "+
					" AND    A.APPLICATION_NO = D.APPLICATION_NO  "+ 
					" AND    APPLICATION_STATUS='ACTIVATED'  "+
					" AND    A.DIVISION_CODE='BD' "+	
					" AND    A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
					" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS   "+
					" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY')   "+
					" )   "+
					" AND    D.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
					" AND    D.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
					" ORDER BY  TO_CHAR(RENTAL_DATE,'DD-MM-YYYY') ");
					**************/
					
							rs = stmt.executeQuery(
																	" SELECT  DISTINCT A.FINANCE_NO, "+
																	" TO_CHAR(B.RENTAL_DATE,'MONTH-YYYY') MONTH,"+ //1
																	" SUM(B.CAPITAL_AMOUNT),"+ //3
																	" SUM(B.NET_RENTAL_AMOUNT) ,  "+ //4
																	" SUM(B.INTEREST_AMOUNT),  "+ //5 
																	" TO_CHAR(B.RENTAL_DATE,'DD-MM-YYYY'), "+
																	" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE)  "+ //6
																	" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT B  "+
																	"      ,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C,"+m_schema_name+".AF_CO_PRO_INVOICE D "+
																	" WHERE C.APPLICATION_NO = B.APPLICATION_NO "+
																	" AND   A.APPLICATION_NO = B.APPLICATION_NO  "+
																	" AND   C.INVOICE_NO     = B.PRO_INVOICE_NO "+
																	" AND   C.PRICING_NO     = B.PRICING_NO "+
																	" AND   B.INVOICE_NO     = D.INVOICE_NO(+) "+
																	//" AND   D.ACTIVE_STATUS  = 'Y'  "+
																	" AND   C.ACTIVE_STATUS IN ('Y') "+
																	" AND   A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
																	" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS   "+
																	" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY')  "+
																	" AND     APPLICATION_STATUS =  'ACTIVATED' "+
																	" OR    ( APPLICATION_STATUS <> 'ACTIVATED' "+
																	" AND    TO_DATE("+m_schema_name+".AF_CO_GET_TER_DATE(FINANCE_NO, '"+m_activ_cut_date+"'),'DD-MM-YYYY')  > TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY')  )"+
																	" )  "+
																	" AND    A.DIVISION_CODE='BD' "+	
																	" AND    B.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
																	" AND    B.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')   "+
																	" GROUP BY A.FINANCE_NO,TO_CHAR(B.RENTAL_DATE,'MONTH-YYYY'),TO_CHAR(B.RENTAL_DATE,'MM-YYYY'),A.TRANSACTION_TYPE ,TO_CHAR(B.RENTAL_DATE,'DD-MM-YYYY')  "+
																	" ORDER BY TO_CHAR(RENTAL_DATE,'DD-MM-YYYY') ");

					
					
						}
						else{ 
						
		//							out.println(

				/**************
				
				  rs = stmt.executeQuery(
					"SELECT  DISTINCT A.FINANCE_NO,  "+
					" TO_CHAR(D.RENTAL_DATE,'Month-YYYY') MONTH, "+
					" D.CAPITAL_AMOUNT, "+
					" D.NET_RENTAL_AMOUNT , "+  
					" D.INTEREST_AMOUNT , "+
					" A.TRANSACTION_TYPE "+
					" ,TO_CHAR(RENTAL_DATE,'DD-MM-YYYY'), "+
					" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE)  "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,  "+ 
					" "+m_schema_name+".AF_CO_MAS_CLIENT B,  "+
					" "+m_schema_name+".CO_CO_MAS_EMPLOYEE C,  "+ 
					" "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT D  "+
					" WHERE  A.CLIENT_CODE=B.CLIENT_CODE  "+
					" AND    A.COLLECTION_OFFICER=C.EMP_CODE  "+
					" AND    A.APPLICATION_NO = D.APPLICATION_NO  "+ 
					" AND    APPLICATION_STATUS='ACTIVATED'  "+
					" AND    A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
				  " AND    C.LOCATION_CODE  ='"+m_location_code+"' "+
					" AND    A.DIVISION_CODE='AF' "+	
					" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS   "+
					" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY')   "+
					" )   "+
					" AND    D.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
					" AND    D.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
					" ORDER BY  TO_CHAR(RENTAL_DATE,'DD-MM-YYYY')  ");
				****************/
				
	        //added by ns 07-09-2009 			
							rs = stmt.executeQuery(
																	" SELECT  DISTINCT A.FINANCE_NO, "+
																	" TO_CHAR(B.RENTAL_DATE,'MONTH-YYYY') MONTH,"+ //1
																	" SUM(B.CAPITAL_AMOUNT),"+ //3
																	" SUM(B.NET_RENTAL_AMOUNT) ,  "+ //4
																	" SUM(B.INTEREST_AMOUNT),  "+ //5 
																	" TO_CHAR(B.RENTAL_DATE,'DD-MM-YYYY'), "+
																	" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE)  "+ //6
																	" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT B  "+
																	"      ,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C,"+m_schema_name+".AF_CO_PRO_INVOICE D "+
																	" WHERE C.APPLICATION_NO = B.APPLICATION_NO "+
																	" AND   A.APPLICATION_NO = B.APPLICATION_NO  "+
																	" AND   C.INVOICE_NO     = B.PRO_INVOICE_NO "+
																	" AND   C.PRICING_NO     = B.PRICING_NO "+
																	" AND   B.INVOICE_NO     = D.INVOICE_NO "+
																	" AND   D.ACTIVE_STATUS  = 'Y'  "+
																	" AND   C.ACTIVE_STATUS IN ('Y') "+
																	" AND   A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
																	" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS   "+
																	" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY')  "+
																	" AND     APPLICATION_STATUS =  'ACTIVATED' "+
																	" OR    ( APPLICATION_STATUS <> 'ACTIVATED' "+
																	" AND    TO_DATE("+m_schema_name+".AF_CO_GET_TER_DATE(FINANCE_NO, '"+m_activ_cut_date+"'),'DD-MM-YYYY')  > TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY')  )"+
																	
																	" )  "+
																	" AND    A.DIVISION_CODE='AF' "+	
																	" AND    B.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
																	" AND    B.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')   "+
																	" GROUP BY A.FINANCE_NO,TO_CHAR(B.RENTAL_DATE,'MONTH-YYYY'),TO_CHAR(B.RENTAL_DATE,'MM-YYYY'),A.TRANSACTION_TYPE ,TO_CHAR(B.RENTAL_DATE,'DD-MM-YYYY')  "+
																	" ORDER BY TO_CHAR(RENTAL_DATE,'DD-MM-YYYY') ");
								

					
				/*	rs = stmt.executeQuery( "SELECT  DISTINCT A.FINANCE_NO,  "+
					" TO_CHAR(D.RENTAL_DATE,'Month-YYYY') MONTH, "+
					" D.CAPITAL_AMOUNT, "+
					" D.NET_RENTAL_AMOUNT , "+  
					" D.INTEREST_AMOUNT , "+
					" A.TRANSACTION_TYPE "+
					" ,TO_CHAR(RENTAL_DATE,'DD-MM-YYYY'), "+
					" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.TRANSACTION_TYPE)  "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,  "+ 
					" "+m_schema_name+".AF_CO_MAS_CLIENT B,  "+
					" "+m_schema_name+".CO_CO_MAS_EMPLOYEE C,  "+ 
					" "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT D  "+
					" WHERE  A.CLIENT_CODE=B.CLIENT_CODE  "+
					" AND    A.COLLECTION_OFFICER=C.EMP_CODE  "+
					" AND    A.APPLICATION_NO = D.APPLICATION_NO  "+ 
					" AND    APPLICATION_STATUS='ACTIVATED'  "+
					" AND    A.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
				  " AND    C.LOCATION_CODE  ='"+m_location_code+"' "+
					" AND    A.FINANCE_NO IN ( SELECT FINANCE_NO  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS   "+
					" WHERE  ACTIVATED_DATE <= TO_DATE('"+m_activ_cut_date+"','DD-MM-YYYY')   "+
					" )   "+
					" AND    D.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
					" AND    D.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
					" ORDER BY  TO_CHAR(RENTAL_DATE,'DD-MM-YYYY')  ");
         */
						
						}
			
			boolean more = rs.next();
			int count = 1;
			
			if(!more){
			out.println("<table class='table' border='0' width=100% align='center'>   "); 
			out.println("<tr align='center' width='100%'><td>&nbsp;</td></tr>");
			out.println("<tr align='center' width='100%'><td>");
			out.println("<font color='red'>No data found....!</font>"); 
			out.println("</td></tr>"); 
			out.println("</table>"); 
			}
			else{
			out.println("<table class='table' border='0' width=100% >   "); 
			out.println("<tr class=pdn_txtpos2 >"); 
			out.println("<td width='3%'  align='left' >No.</td>"); 
			out.println("<td width='8%' align='left' >Month</td>");
			out.println("<td width='10%' align='left' >Agreement No</td>"); 
			out.println("<td width='10%' align='left' >Product</td>"); 
			out.println("<td width='15%' align='right' >Rent</td>"); 
			out.println("<td width='15%' align='right' >Principle</td>"); 
			out.println("<td width='15%' align='right' >Income</td>");
			//out.println("<td width='8%' align='center' >No.of Agreements</td>"); 
			out.println("</tr>"); 
			}	
			
			while(more){	
			if(count%2==1){
			out.println("<tr bgcolor=\"#FFFFFF\" >"); 
			}
			else{
			out.println("<tr bgcolor=\"#C0C0C0\" >");
			}
			out.println("<td width='5%'  align='left' >"+count+"</td>"); 
			out.println("<td width='10%' align='left' >"+rs.getString(2)+"</td>"); 
			out.println("<td width='10%' align='left' >"+rs.getString(1)+"</td>"); 
			out.println("<td width='10%' align='left' >"+rs.getString(7)+"</td>"); 
			out.println("<td width='15%' align='right' >"+nf1.format(rs.getDouble(4))+"</td>"); 
			out.println("<td width='15%' align='right' >"+nf1.format(rs.getDouble(3))+"</td>"); 
			out.println("<td width='15%' align='right' >"+nf1.format(rs.getDouble(5))+"</td>");
			
			out.println("</tr>"); 
			count = count+1;
			more = rs.next();
			}
			
			out.println("</table>"); 
			
			out.println("</body><html>");
			
			}
			
			
			else if(chksql.equals("agreement_wise_summary_report")){
			
			String m_activ_cut_date = req.getParameter("activ_cut_date");
			String m_prod_level = req.getParameter("prod_level");
			String m_client_code = req.getParameter("client_code");
			String m_location_code =req.getParameter("location_code");
			
			String m_from_date = req.getParameter("from_date");
			String m_to_date = req.getParameter("to_date");
			String m_rpt_generated_date="";
			
																	rs = stmt.executeQuery(
																	" SELECT "+
																	" MIN(ENT_DATE) "+
																	" FROM "+m_schema_name+".AF_TBD_MATURITY  "+
																	" WHERE ENT_USER='"+m_username+"' ");
																	if(rs.next()){
																	m_rpt_generated_date=rs.getString(1);
																	}
			
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Agreement Wise Detail Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<body>");
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' align='center'>   "); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td align='left' style='height: 18px' id='help_box' ><b>Report Generated User : "+m_username+"</b> &nbsp;&nbsp; <b>Date/Time : "+m_rpt_generated_date+"</b></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td align='left' style='height: 18px' id='help_box' ><b>Agreement Wise Detail Report</b></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
						
			
			if(m_location_code.equals("ALL")){
			
																	rs = stmt.executeQuery(
																	//out.println(
																	" SELECT "+
																	" DISTINCT B.FINANCE_NO, "+
																	//" TO_CHAR(A.RENTAL_DATE,'MONTH-YYYY') MONTH ,"+
																	" SUM(A.CAPITAL_AMOUNT) , "+
																	" SUM(A.NET_RENTAL_AMOUNT) , "+
																	" SUM(A.INTEREST_AMOUNT), "+
																	//" TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY'), "+
																	" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(B.TRANSACTION_TYPE)  "+ //6
																	" FROM "+m_schema_name+".AF_TBD_MATURITY_DET A , "+m_schema_name+".AF_TBD_MATURITY B "+
																	" WHERE A.APPLICATION_NO(+)=B.APPLICATION_NO  "+
																	" AND   A.ENT_USER='"+m_username+"' "+
																	" AND   B.ENT_USER='"+m_username+"' "+
																	" AND   B.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
																	" AND   A.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')   "+
																	" AND   A.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')    "+
																	" AND   B.STATUS=1 "+
																	//" GROUP BY B.FINANCE_NO,TO_CHAR(A.RENTAL_DATE,'MONTH-YYYY'),TO_CHAR(A.RENTAL_DATE,'MM-YYYY'),B.TRANSACTION_TYPE ,TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY')  "+
																	" GROUP BY B.FINANCE_NO,B.TRANSACTION_TYPE"+
																	" ORDER BY B.FINANCE_NO");

			
			}
			else if(m_location_code.equals("BIKE")){
			
				
																					rs = stmt.executeQuery(
																	//out.println(
																	" SELECT "+
																	" DISTINCT B.FINANCE_NO, "+
																	//" TO_CHAR(A.RENTAL_DATE,'MONTH-YYYY') MONTH ,"+
																	" SUM(A.CAPITAL_AMOUNT) , "+
																	" SUM(A.NET_RENTAL_AMOUNT) , "+
																	" SUM(A.INTEREST_AMOUNT), "+
																	//" TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY'), "+
																	" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(B.TRANSACTION_TYPE)  "+ //6
																	" FROM "+m_schema_name+".AF_TBD_MATURITY_DET A , "+m_schema_name+".AF_TBD_MATURITY B "+
																	" WHERE A.APPLICATION_NO(+)=B.APPLICATION_NO  "+
																	" AND   A.ENT_USER='"+m_username+"' "+
																	" AND   B.DIVISION_CODE='BD' "+	
																	" AND   B.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
																	" AND   B.ENT_USER='"+m_username+"' "+
																	" AND   A.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')   "+
																	" AND   A.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')    "+
																	" AND   B.STATUS=1 "+
																	//" GROUP BY B.FINANCE_NO,TO_CHAR(A.RENTAL_DATE,'MONTH-YYYY'),TO_CHAR(A.RENTAL_DATE,'MM-YYYY'),B.TRANSACTION_TYPE ,TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY')  "+
																	" GROUP BY B.FINANCE_NO,B.TRANSACTION_TYPE"+
																	" ORDER BY B.FINANCE_NO  ");

					
					
						}
						else{ 
						
						
						
												rs = stmt.executeQuery(
																	//out.println(
																	" SELECT "+
																	" DISTINCT B.FINANCE_NO, "+
																	//" TO_CHAR(A.RENTAL_DATE,'MONTH-YYYY') MONTH ,"+
																	" SUM(A.CAPITAL_AMOUNT) , "+
																	" SUM(A.NET_RENTAL_AMOUNT) , "+
																	" SUM(A.INTEREST_AMOUNT), "+
																	//" TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY'), "+
																	" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(B.TRANSACTION_TYPE)  "+ //6
																	" FROM "+m_schema_name+".AF_TBD_MATURITY_DET A , "+m_schema_name+".AF_TBD_MATURITY B "+
																	" WHERE A.APPLICATION_NO(+)=B.APPLICATION_NO  "+
																	" AND   A.ENT_USER='"+m_username+"' "+
																	" AND   B.TRANSACTION_TYPE  LIKE '%"+m_prod_level+"%' "+
				                          " AND   B.LOCATION_CODE  ='"+m_location_code+"' "+
																	" AND   B.ENT_USER='"+m_username+"' "+
																	" AND   A.RENTAL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')   "+
																	" AND   A.RENTAL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')    "+
																	" AND   B.STATUS=1 "+
																	//" GROUP BY B.FINANCE_NO,TO_CHAR(A.RENTAL_DATE,'MONTH-YYYY'),TO_CHAR(A.RENTAL_DATE,'MM-YYYY'),B.TRANSACTION_TYPE ,TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY')  "+
																	" GROUP BY B.FINANCE_NO,B.TRANSACTION_TYPE"+
																	" ORDER BY B.FINANCE_NO  ");

								

					
									
						}
			
			boolean more = rs.next();
			int count = 1;
			
			if(!more){
			out.println("<table class='table' border='0' width=100% align='center'>   "); 
			out.println("<tr align='center' width='100%'><td>&nbsp;</td></tr>");
			out.println("<tr align='center' width='100%'><td>");
			out.println("<font color='red'>No data found....!</font>"); 
			out.println("</td></tr>"); 
			out.println("</table>"); 
			}
			else{
			out.println("<table class='table' border='0' width=100% >   "); 
			out.println("<tr class=pdn_txtpos2 >"); 
			out.println("<td width='3%'  align='left' >No.</td>"); 
			//out.println("<td width='8%' align='left' >Month</td>");
			out.println("<td width='10%' align='left' >Agreement No</td>"); 
			out.println("<td width='10%' align='left' >Product</td>"); 
			out.println("<td width='15%' align='right' >Rent</td>"); 
			out.println("<td width='15%' align='right' >Principle</td>"); 
			out.println("<td width='15%' align='right' >Income</td>");
			//out.println("<td width='8%' align='center' >No.of Agreements</td>"); 
			out.println("</tr>"); 
			}	
			
			while(more){	
			if(count%2==1){
			out.println("<tr bgcolor=\"#FFFFFF\" >"); 
			}
			else{
			out.println("<tr bgcolor=\"#C0C0C0\" >");
			}
			out.println("<td width='5%'  align='left' >"+count+"</td>"); 
			//out.println("<td width='10%' align='left' >"+rs.getString(2)+"</td>"); 
			out.println("<td width='10%' align='left' >"+rs.getString(1)+"</td>"); 
			out.println("<td width='10%' align='left' >"+rs.getString(5)+"</td>"); 
			out.println("<td width='15%' align='right' >"+nf1.format(rs.getDouble(3))+"</td>"); 
			out.println("<td width='15%' align='right' >"+nf1.format(rs.getDouble(2))+"</td>"); 
			out.println("<td width='15%' align='right' >"+nf1.format(rs.getDouble(4))+"</td>");
			
			out.println("</tr>"); 
			count = count+1;
			more = rs.next();
			}
			
			out.println("</table>"); 
			
			out.println("</body><html>");
			
			}
			
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());
			}catch(Exception e){}
		}
		finally{
				if(out!=null){
				try{out.close();  
				}catch(Exception e){}
				}
		}
	}
}
