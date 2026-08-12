
//--
//SCREEN NAME:DISPLAY FOLLOW UP REPORT
//CREATED BY:CHANDANA
//DATE/TIME:05/04/2007
//NOTES:
//URL:https://dev-lakdl.sasianet.com:/myserver/servlet/LAKDL_AF_CO_REP_display_follow_up_report?chksql=MAIN&

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_CO_REP_display_follow_up_report extends javax.servlet.http.HttpServlet { 

		Connection conn;
		Statement stmt;
		public ResultSet rs;
		PreparedStatement pstmt;
		java.text.NumberFormat nf;

public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 

		try { 

			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);

			//SCREEN_METHODS m_sn_methods = new SCREEN_METHODS(); 
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();

			String m_html_client_url=con_method.html_client_url.trim(); 
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
			conn = con_method.met_user_validate(req); 
			stmt = conn.createStatement();
			
			String header_name=con_method.header_name.trim();
			String m_schema_name = con_method.schema_name;
			String m_fschema_name=con_method.client_name.trim();
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;

			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 

			ServletOutputStream out = res.getOutputStream();  


			String m_screen_type= req.getParameter("chksql");
			
			String m_id_num= req.getParameter("ID_NUM");
			String m_status= req.getParameter("STATUS");
			
			String m_eff_from_date= req.getParameter("EFF_FROM_DATE");
			String m_eff_to_date= req.getParameter("EFF_TO_DATE");
			String m_action_date= req.getParameter("ACT_DATE");			
			
			//Added by Dineth on 2008-10-10
			String m_assigned_by=req.getParameter("ASSIGNED_BY");
			String m_assigned_to=req.getParameter("ASSIGNED_TO");
			String m_division_code=req.getParameter("DIVISION");
			String m_sub_division_code=req.getParameter("SUB_DIVISION");
			//End by Dineth on 2008-10-10
			
			String m_sort_column   = "A.FOLLOW_UP_NO";	
			String m_order_by_type = "ASC";
			
			m_sort_column = req.getParameter("sort_column");
			m_order_by_type = req.getParameter("order_by_type");
			

			if(m_screen_type.equals("MAIN")){
			
			m_action_date = "--";

			//m_chksql = req.getParameter(\"chksql\");
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Display Follow Up Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			
			// Added by Udara Somathilake on 14-10-2010
			out.println("function load_history_report(id_no,cli_no) {");
			//out.println("		alert(id_no +'  ' + cli_no);");
			out.println("m_url='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+id_no+'&client_code='+cli_no;"); 
         out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("}");

			
			out.println("function sort_data(m_sort_col) {");
			out.println("	 m_order_by_type = 'ASC'; ");  
			out.println("	 if(m_sort_col=='"+m_sort_column+"'){");
			out.println("	   if('"+m_order_by_type+"'=='DESC'){");
			out.println("	      m_order_by_type = 'ASC'; ");  
			out.println("    }else{");
			out.println("       m_order_by_type = 'DESC'; ");
			out.println("    }");
			out.println("  }else{");
			out.println("    m_order_by_type = 'ASC'; ");
			out.println("  }");
	  //  out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RPT_Advertisement_Offers_Report?chksql=MAIN&OFFER_NO=&from_date="+m_from_date+"&to_date="+m_to_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
			
						
			/*out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CO_REP_display_follow_up_report?chksql=MAIN&ID_NUM="+m_id_num+"&STATUS="+m_status+""+      
		  "&EFF_FROM_DATE="+m_eff_from_date+""+
			"&EFF_TO_DATE="+m_eff_to_date+""+
			"&ACT_DATE="+m_action_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type; ");*/
			
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CO_REP_display_follow_up_report?chksql=MAIN&ID_NUM="+m_id_num+"&STATUS="+m_status+""+      
		  "&EFF_FROM_DATE="+m_eff_from_date+""+
			"&EFF_TO_DATE="+m_eff_to_date+""+
			"&ACT_DATE="+m_action_date+"&ASSIGNED_BY="+m_assigned_by+"&ASSIGNED_TO="+m_assigned_to+"&DIVISION="+m_division_code+"&SUB_DIVISION="+m_sub_division_code+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type; ");
			
			
			out.println(" window.location.href=m_url;");
				
			out.println("}");

			
			
			
			
			out.println("</script>"); 

			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' >"); 
			out.println("<tr><td height='30' class='pdn_mainHD'>Asset Financing System</td></tr>"); 
			out.println("<tr><td height='1'><img src='spacer.gif' height='1'></td></tr>"); 
			out.println("<tr>"); 
			out.println("<td>"); 
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
			out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
			out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px'>Display Follow Up Report</td></tr>"); 
			out.println("</table>");  
			out.println("</table>");  
			out.println("</BR>");
									
				//out.println("m_id_num==="+m_id_num+"m_status==="+m_status+"m_eff_from_date===="+m_eff_from_date+"m_eff_to_date===="+m_eff_to_date+"m_action_date==="+m_action_date);
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr>");
			out.println("<td width='95%' align='right'><input type=\"button\" class='mainbut'  onclick='close_window()' value=\"Close\"></td>");
			out.println("<td width='5%' align='right'></td>");
			out.println("</tr >"); 
			out.println("</table>");
			out.println("</BR>");
			
			out.println("<table align='center' width='100%' class='table' border=1>"); 
			out.println("<tr class=pdn_txtpos2 >"); 
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; onclick=sort_data('A.FOLLOW_UP_NO')>FOLLOW UP NO</td>"); 
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; onclick=sort_data('A.ID_NO')>ID NO</td>");  
			out.println("<td width='10%' class='txt_report_column'>FOLLOWUP TYPE</td>"); 
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; onclick=sort_data('A.EFF_VAL_DATE')>EFF VAL DATE</td>"); 
			out.println("<td width='10%' class='txt_report_column'>ACTION TAKEN</td>"); 
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; onclick=sort_data('A.ACTION_DATE')>ACTION DATE</td>"); 
			out.println("<td width='10%' class='txt_report_column'>ACTION SET FOR</td>"); 
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; onclick=sort_data('A.ACTION_ENT_DATE')>ACTION ENT DATE</td>");
			out.println("<td width='10%' class='txt_report_column'>REMARKS</td>");//Added by Dineth on 2008-12-16
			out.println("<td width='10%' class='txt_report_column'>FOLLOWUP TIME</td>"); 
			out.println("<td width='10%' class='txt_report_column'>STATUS</td>"); 
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; onclick=sort_data('A.PREV_FOLLOWUP_NO')>PREV FOLLOWUP NO</td>"); 
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; onclick=sort_data('A.ORG_FOLLOWUP_NO')>ORG FOLLOWUP NO</td>"); 
			out.println("</tr >");
			
			//Added by Dineth on 2008-10-10
			
			String q_assigned_by="";
			String q_assigned_to="";
			String q_div_code="";
			String q_div_sub_code="";
			if(!m_assigned_by.equals("")){
			q_assigned_by=" AND A.ENT_USER='"+m_assigned_by+"'";
			}
			if(!m_assigned_to.equals("")){
			q_assigned_to=" AND A.ACTION_SET_FOR='"+m_assigned_to+"'";
			}
			if(!m_division_code.equals("")){
			q_div_code=" AND A.DIVISION_CODE='"+m_division_code+"'";
			}
			if(!m_sub_division_code.equals("")){
			q_div_sub_code=" AND A.SUB_DIVISION_CODE='"+m_sub_division_code+"'";
			}
			
			//End by Dineth on 2008-10-10
			
			if(!m_id_num.equals("") && m_eff_from_date.equals("--") && m_eff_to_date.equals("--") && m_action_date.equals("--")){
			  if(m_status.equals("ALL")){		
				
				//out.println("1"); // udara
				
			//out.println("alert("+m_id_num+");"); 
			
			pstmt = conn.prepareStatement("  SELECT A.FOLLOW_UP_NO,NVL(A.ID_NO,'-'), "+
			" "+m_schema_name+".AF_CO_GET_FOLLOWUP_TYPE(A.ID_NO,A.SCREEN_NAME) FOLLOWUP_TYPE,  "+
			" NVL(TO_CHAR(A.EFF_VAL_DATE,'DD-MON-YYYY'),'-'),  B.CATEGORY_NAME ACTION_TAKEN, "+
			" NVL(TO_CHAR(A.ACTION_DATE,'DD-MON-YYYY'),'-'),  A.ACTION_SET_FOR, "+
			" NVL(TO_CHAR(A.ACTION_ENT_DATE,'DD-MON-YYYY'),'-'),  NVL(A.PREV_FOLLOWUP_NO,'-'), "+
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS "+
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), "+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)  "+ // 14,15 values
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), "+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)  "+ // 14,15 values
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO ("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO) ),A.ID_NO) "+ // 14,15 values
			" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO ("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO) ),A.ID_NO),NVL("+m_schema_name+".FA_CO_GET_LAST_FOLLOW_REMARK(A.ID_NO),A.ENT_REMARKS) "+ // 14,15,16 values
			" FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A, "+
			" "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY B "+
			" WHERE A.ACTION_TOBE_TAKEN=B.CATEGORY_CODE AND "+
			" A.ID_NO='"+m_id_num+"'"+q_assigned_by+q_assigned_to+q_div_code+q_div_sub_code+" "+
			" ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
			
			}else{
			//out.println("2"); // udara
			
			pstmt = conn.prepareStatement("  SELECT A.FOLLOW_UP_NO,NVL(A.ID_NO,'-'), "+
			" "+m_schema_name+".AF_CO_GET_FOLLOWUP_TYPE(A.ID_NO,A.SCREEN_NAME) FOLLOWUP_TYPE,  "+
			" NVL(TO_CHAR(A.EFF_VAL_DATE,'DD-MON-YYYY'),'-'),  B.CATEGORY_NAME ACTION_TAKEN, "+
			" NVL(TO_CHAR(A.ACTION_DATE,'DD-MON-YYYY'),'-'),  A.ACTION_SET_FOR, "+
			" NVL(TO_CHAR(A.ACTION_ENT_DATE,'DD-MON-YYYY'),'-'),  NVL(A.PREV_FOLLOWUP_NO,'-'), "+
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, NVL(A.ENT_REMARKS,'-') "+
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), "+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)  "+ // 14,15 values
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO ("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO) ),A.ID_NO) "+ // 14,15 values
			" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO ("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO) ),A.ID_NO),NVL("+m_schema_name+".FA_CO_GET_LAST_FOLLOW_REMARK(A.ID_NO),A.ENT_REMARKS) "+ // 14,15,16 values
			" FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A, "+
			" "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY B "+
			" WHERE A.ACTION_TOBE_TAKEN=B.CATEGORY_CODE AND "+
			" A.ID_NO='"+m_id_num+"' AND "+
			" A.STATUS='"+m_status+"'"+q_assigned_by+q_assigned_to+q_div_code+q_div_sub_code+" "+
			" ORDER BY "+m_sort_column+" "+m_order_by_type+" ");		
			}
			
			
			
			
       }else
				if(!m_eff_from_date.equals("--") && !m_eff_to_date.equals("--") && m_action_date.equals("--") && m_id_num.equals("")){ // && m_id_num.equals(" ") 
			
			//out.println("m_eff_to_date==="+m_eff_to_date+"m_eff_from_date==="+m_eff_from_date);
			
			 if(m_status.equals("ALL")){	
				
				//out.println("3"); // udara

				
				out.println(q_assigned_by); // udara
				out.println(q_assigned_to); // udara
				out.println(q_div_code); // udara
				out.println(q_div_sub_code); // udara
				
				
				
				pstmt = conn.prepareStatement("  SELECT A.FOLLOW_UP_NO,NVL(A.ID_NO,'-'), "+
			" "+m_schema_name+".AF_CO_GET_FOLLOWUP_TYPE(A.ID_NO,A.SCREEN_NAME) FOLLOWUP_TYPE,  "+
			" NVL(TO_CHAR(A.EFF_VAL_DATE,'DD-MON-YYYY'),'-'),  B.CATEGORY_NAME ACTION_TAKEN, "+
			" NVL(TO_CHAR(A.ACTION_DATE,'DD-MON-YYYY'),'-'),  A.ACTION_SET_FOR, "+
			" NVL(TO_CHAR(A.ACTION_ENT_DATE,'DD-MON-YYYY'),'-'),  NVL(A.PREV_FOLLOWUP_NO,'-'), "+
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, NVL(A.ENT_REMARKS,'-') "+
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), "+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)  "+ // 14,15 values
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO ("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO) ),A.ID_NO) "+ // 14,15 values
			" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO ("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO) ),A.ID_NO),NVL("+m_schema_name+".FA_CO_GET_LAST_FOLLOW_REMARK(A.ID_NO),A.ENT_REMARKS) "+ // 14,15,16 values
			" FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A, "+
			" "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY B "+
			" WHERE A.ACTION_TOBE_TAKEN=B.CATEGORY_CODE AND "+ 
			" TO_DATE(TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YY'),'DD-MM-YY')<TO_DATE('"+m_eff_to_date+"','DD-MM-YY') AND "+
			" TO_DATE(TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YY'),'DD-MM-YY')>TO_DATE('"+m_eff_from_date+"','DD-MM-YY')"+q_assigned_by+q_assigned_to+q_div_code+q_div_sub_code+" "+
			" ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
			}else{
			
			//out.println("4"); // udara
			
				pstmt = conn.prepareStatement("  SELECT A.FOLLOW_UP_NO,NVL(A.ID_NO,'-'), "+
			" "+m_schema_name+".AF_CO_GET_FOLLOWUP_TYPE(A.ID_NO,A.SCREEN_NAME) FOLLOWUP_TYPE,  "+
			" NVL(TO_CHAR(A.EFF_VAL_DATE,'DD-MON-YYYY'),'-'),  B.CATEGORY_NAME ACTION_TAKEN, "+
			" NVL(TO_CHAR(A.ACTION_DATE,'DD-MON-YYYY'),'-'),  A.ACTION_SET_FOR, "+
			" NVL(TO_CHAR(A.ACTION_ENT_DATE,'DD-MON-YYYY'),'-'),  NVL(A.PREV_FOLLOWUP_NO,'-'), "+
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS,NVL(A.ENT_REMARKS,'-') "+
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), "+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)  "+ // 14,15 values
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO ("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO) ),A.ID_NO) "+ // 14,15 values
			" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO ("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO) ),A.ID_NO),NVL("+m_schema_name+".FA_CO_GET_LAST_FOLLOW_REMARK(A.ID_NO),A.ENT_REMARKS) "+ // 14,15,16 values
			" FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A, "+
			" "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY B "+
			" WHERE A.ACTION_TOBE_TAKEN=B.CATEGORY_CODE AND "+ 
			" TO_DATE(TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YY'),'DD-MM-YY')<TO_DATE('"+m_eff_to_date+"','DD-MM-YY') AND "+
			" TO_DATE(TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YY'),'DD-MM-YY')>TO_DATE('"+m_eff_from_date+"','DD-MM-YY') AND "+
			" A.STATUS='"+m_status+"'"+q_assigned_by+q_assigned_to+q_div_code+q_div_sub_code+" "+
			" ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
				}
			
	    }else
			if(!m_action_date.equals("--") && !m_eff_from_date.equals("--") && !m_eff_to_date.equals("--") && m_id_num.equals("")){
			
			if(m_status.equals("ALL")){
			
			//out.println("5"); // udara
			
				pstmt = conn.prepareStatement("  SELECT A.FOLLOW_UP_NO,NVL(A.ID_NO,'-'), "+
			" "+m_schema_name+".AF_CO_GET_FOLLOWUP_TYPE(A.ID_NO,A.SCREEN_NAME) FOLLOWUP_TYPE,  "+
			" NVL(TO_CHAR(A.EFF_VAL_DATE,'DD-MON-YYYY'),'-'),  B.CATEGORY_NAME ACTION_TAKEN, "+
			" NVL(TO_CHAR(A.ACTION_DATE,'DD-MON-YYYY'),'-'),  A.ACTION_SET_FOR, "+
			" NVL(TO_CHAR(A.ACTION_ENT_DATE,'DD-MON-YYYY'),'-'),  NVL(A.PREV_FOLLOWUP_NO,'-'), "+
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS,NVL(A.ENT_REMARKS,'-') "+
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), "+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)  "+ // 14,15 values
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO ("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO) ),A.ID_NO) "+ // 14,15 values
			" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO ("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO) ),A.ID_NO),NVL("+m_schema_name+".FA_CO_GET_LAST_FOLLOW_REMARK(A.ID_NO),A.ENT_REMARKS) "+ // 14,15,16 values
			" FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A, "+
			" "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY B "+
			" WHERE A.ACTION_TOBE_TAKEN=B.CATEGORY_CODE AND "+ 
			" TO_DATE(TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YY'),'DD-MM-YY')<TO_DATE('"+m_eff_to_date+"','DD-MM-YY') AND "+
			" TO_DATE(TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YY'),'DD-MM-YY')>TO_DATE('"+m_eff_from_date+"','DD-MM-YY') AND "+
			" TO_DATE(TO_CHAR(A.ACTION_ENT_DATE,'DD-MM-YY'),'DD-MM-YY')=TO_DATE('"+m_action_date+"','DD-MM-YY')"+q_assigned_by+q_assigned_to+q_div_code+q_div_sub_code+" "+
			" ORDER BY "+m_sort_column+" "+m_order_by_type+" "); 
			}else{
			
			//out.println("6"); // udara
			
				pstmt = conn.prepareStatement("  SELECT A.FOLLOW_UP_NO,NVL(A.ID_NO,'-'), "+
			" "+m_schema_name+".AF_CO_GET_FOLLOWUP_TYPE(A.ID_NO,A.SCREEN_NAME) FOLLOWUP_TYPE,  "+
			" NVL(TO_CHAR(A.EFF_VAL_DATE,'DD-MON-YYYY'),'-'),  B.CATEGORY_NAME ACTION_TAKEN, "+
			" NVL(TO_CHAR(A.ACTION_DATE,'DD-MON-YYYY'),'-'),  A.ACTION_SET_FOR, "+
			" NVL(TO_CHAR(A.ACTION_ENT_DATE,'DD-MON-YYYY'),'-'),  NVL(A.PREV_FOLLOWUP_NO,'-'), "+
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS,NVL(A.ENT_REMARKS,'-') "+
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), "+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)  "+ // 14,15 values
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO ("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO) ),A.ID_NO) "+ // 14,15 values
			" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO ("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO) ),A.ID_NO),NVL("+m_schema_name+".FA_CO_GET_LAST_FOLLOW_REMARK(A.ID_NO),A.ENT_REMARKS) "+ // 14,15,16 values
			" FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A, "+
			" "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY B "+
			" WHERE A.ACTION_TOBE_TAKEN=B.CATEGORY_CODE AND "+ 
			" TO_DATE(TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YY'),'DD-MM-YY')<TO_DATE('"+m_eff_to_date+"','DD-MM-YY') AND "+
			" TO_DATE(TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YY'),'DD-MM-YY')>TO_DATE('"+m_eff_from_date+"','DD-MM-YY') AND "+
			" TO_DATE(TO_CHAR(A.ACTION_ENT_DATE,'DD-MM-YY'),'DD-MM-YY')=TO_DATE('"+m_action_date+"','DD-MM-YY') AND "+
			" A.STATUS='"+m_status+"'"+q_assigned_by+q_assigned_to+q_div_code+q_div_sub_code+" "+
			" ORDER BY "+m_sort_column+" "+m_order_by_type+" "); 
			}
			
			}else
			//if(!m_action_date.equals("--") && !m_eff_from_date.equals("--") && !m_id_num.equals("") && m_action_date.equals("--")){
			if(!m_id_num.equals("") && !m_eff_from_date.equals("--") && !m_eff_to_date.equals("--") && m_action_date.equals("--")){
			
			if(m_status.equals("ALL")){
			
			//out.println("7"); // udara
			
				pstmt = conn.prepareStatement("  SELECT A.FOLLOW_UP_NO,NVL(A.ID_NO,'-'), "+
			" "+m_schema_name+".AF_CO_GET_FOLLOWUP_TYPE(A.ID_NO,A.SCREEN_NAME) FOLLOWUP_TYPE,  "+
			" NVL(TO_CHAR(A.EFF_VAL_DATE,'DD-MON-YYYY'),'-'),  B.CATEGORY_NAME ACTION_TAKEN, "+
			" NVL(TO_CHAR(A.ACTION_DATE,'DD-MON-YYYY'),'-'),  A.ACTION_SET_FOR, "+
			" NVL(TO_CHAR(A.ACTION_ENT_DATE,'DD-MON-YYYY'),'-'),  NVL(A.PREV_FOLLOWUP_NO,'-'), "+
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS,NVL(A.ENT_REMARKS,'-') "+
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), "+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)  "+ // 14,15 values
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO ("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO) ),A.ID_NO) "+ // 14,15 values
			" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO ("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO) ),A.ID_NO),NVL("+m_schema_name+".FA_CO_GET_LAST_FOLLOW_REMARK(A.ID_NO),A.ENT_REMARKS) "+ // 14,15,16 values
			" FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A, "+
			" "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY B "+
			" WHERE A.ACTION_TOBE_TAKEN=B.CATEGORY_CODE AND "+ 
			" TO_DATE(TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YY'),'DD-MM-YY')<TO_DATE('"+m_eff_to_date+"','DD-MM-YY') AND "+
			" TO_DATE(TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YY'),'DD-MM-YY')>TO_DATE('"+m_eff_from_date+"','DD-MM-YY') AND "+
			" A.ID_NO='"+m_id_num+"'"+q_assigned_by+q_assigned_to+q_div_code+q_div_sub_code+" "+
			" ORDER BY "+m_sort_column+" "+m_order_by_type+" "); 
			}else{
			
			//out.println("8"); // udara
			
				pstmt = conn.prepareStatement("  SELECT A.FOLLOW_UP_NO,NVL(A.ID_NO,'-'), "+
			" "+m_schema_name+".AF_CO_GET_FOLLOWUP_TYPE(A.ID_NO,A.SCREEN_NAME) FOLLOWUP_TYPE,  "+
			" NVL(TO_CHAR(A.EFF_VAL_DATE,'DD-MON-YYYY'),'-'),  B.CATEGORY_NAME ACTION_TAKEN, "+
			" NVL(TO_CHAR(A.ACTION_DATE,'DD-MON-YYYY'),'-'),  A.ACTION_SET_FOR, "+
			" NVL(TO_CHAR(A.ACTION_ENT_DATE,'DD-MON-YYYY'),'-'),  NVL(A.PREV_FOLLOWUP_NO,'-'), "+
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS,NVL(A.ENT_REMARKS,'-') "+
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), "+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)  "+ // 14,15 values
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO ("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO) ),A.ID_NO) "+ // 14,15 values
			" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO ("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO) ),A.ID_NO),NVL("+m_schema_name+".FA_CO_GET_LAST_FOLLOW_REMARK(A.ID_NO),A.ENT_REMARKS) "+ // 14,15,16 values
			" FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A, "+
			" "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY B "+
			" WHERE A.ACTION_TOBE_TAKEN=B.CATEGORY_CODE AND "+ 
			" TO_DATE(TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YY'),'DD-MM-YY')<TO_DATE('"+m_eff_to_date+"','DD-MM-YY') AND "+
			" TO_DATE(TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YY'),'DD-MM-YY')>TO_DATE('"+m_eff_from_date+"','DD-MM-YY') AND "+
			" A.ID_NO='"+m_id_num+"' AND "+
			" A.STATUS='"+m_status+"'"+q_assigned_by+q_assigned_to+q_div_code+q_div_sub_code+" "+
			" ORDER BY "+m_sort_column+" "+m_order_by_type+" "); 
						
			}
			
			}else if(!m_id_num.equals("") && !m_eff_from_date.equals("--") && !m_eff_to_date.equals("--") && !m_action_date.equals("--")){
			
			if(m_status.equals("ALL")){
			
			//out.println("9"); // udara
			
				pstmt = conn.prepareStatement("  SELECT A.FOLLOW_UP_NO,NVL(A.ID_NO,'-'), "+
			" "+m_schema_name+".AF_CO_GET_FOLLOWUP_TYPE(A.ID_NO,A.SCREEN_NAME) FOLLOWUP_TYPE,  "+
			" NVL(TO_CHAR(A.EFF_VAL_DATE,'DD-MON-YYYY'),'-'),  B.CATEGORY_NAME ACTION_TAKEN, "+
			" NVL(TO_CHAR(A.ACTION_DATE,'DD-MON-YYYY'),'-'),  A.ACTION_SET_FOR, "+
			" NVL(TO_CHAR(A.ACTION_ENT_DATE,'DD-MON-YYYY'),'-'),  NVL(A.PREV_FOLLOWUP_NO,'-'), "+
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS,NVL(A.ENT_REMARKS,'-') "+
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), "+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)  "+ // 14,15 values
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO ("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO) ),A.ID_NO) "+ // 14,15 values
			" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO ("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO) ),A.ID_NO),NVL("+m_schema_name+".FA_CO_GET_LAST_FOLLOW_REMARK(A.ID_NO),A.ENT_REMARKS) "+ // 14,15,16 values
			" FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A, "+
			" "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY B "+
			" WHERE A.ACTION_TOBE_TAKEN=B.CATEGORY_CODE AND "+ 
			" TO_DATE(TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YY'),'DD-MM-YY')<TO_DATE('"+m_eff_to_date+"','DD-MM-YY') AND "+
			" TO_DATE(TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YY'),'DD-MM-YY')>TO_DATE('"+m_eff_from_date+"','DD-MM-YY') AND "+
			" TO_DATE(TO_CHAR(A.ACTION_ENT_DATE,'DD-MM-YY'),'DD-MM-YY')=TO_DATE('"+m_action_date+"','DD-MM-YY') AND "+
			" A.ID_NO='"+m_id_num+"'"+q_assigned_by+q_assigned_to+q_div_code+q_div_sub_code+" "+
			" ORDER BY "+m_sort_column+" "+m_order_by_type+" "); 
			}else{
			
			
			//out.println("10"); // udara
			
				pstmt = conn.prepareStatement("  SELECT A.FOLLOW_UP_NO,NVL(A.ID_NO,'-'), "+
			" "+m_schema_name+".AF_CO_GET_FOLLOWUP_TYPE(A.ID_NO,A.SCREEN_NAME) FOLLOWUP_TYPE,  "+
			" NVL(TO_CHAR(A.EFF_VAL_DATE,'DD-MON-YYYY'),'-'),  B.CATEGORY_NAME ACTION_TAKEN, "+
			" NVL(TO_CHAR(A.ACTION_DATE,'DD-MON-YYYY'),'-'),  A.ACTION_SET_FOR, "+
			" NVL(TO_CHAR(A.ACTION_ENT_DATE,'DD-MON-YYYY'),'-'),  NVL(A.PREV_FOLLOWUP_NO,'-'), "+
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS,NVL(A.ENT_REMARKS,'-') "+
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), "+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)  "+ // 14,15 values
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO ("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO) ),A.ID_NO) "+ // 14,15 values
			" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO ("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO) ),A.ID_NO),NVL("+m_schema_name+".FA_CO_GET_LAST_FOLLOW_REMARK(A.ID_NO),A.ENT_REMARKS) "+ // 14,15,16 values
			" FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A, "+
			" "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY B "+
			" WHERE A.ACTION_TOBE_TAKEN=B.CATEGORY_CODE AND "+ 
			" TO_DATE(TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YY'),'DD-MM-YY')<TO_DATE('"+m_eff_to_date+"','DD-MM-YY') AND "+
			" TO_DATE(TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YY'),'DD-MM-YY')>TO_DATE('"+m_eff_from_date+"','DD-MM-YY') AND "+
			" TO_DATE(TO_CHAR(A.ACTION_ENT_DATE,'DD-MM-YY'),'DD-MM-YY')=TO_DATE('"+m_action_date+"','DD-MM-YY') AND "+
			" A.ID_NO='"+m_id_num+"' AND "+
			" A.STATUS='"+m_status+"'"+q_assigned_by+q_assigned_to+q_div_code+q_div_sub_code+" "+
			" ORDER BY "+m_sort_column+" "+m_order_by_type+" "); 

			
			
			}
			
			}else if(m_id_num.equals("") && m_eff_from_date.equals("--") && m_eff_to_date.equals("--") && !m_action_date.equals("--")){
			
			if(m_status.equals("ALL")){
			
			//out.println("11"); // udara
			
				pstmt = conn.prepareStatement("  SELECT A.FOLLOW_UP_NO,NVL(A.ID_NO,'-'), "+
			" "+m_schema_name+".AF_CO_GET_FOLLOWUP_TYPE(A.ID_NO,A.SCREEN_NAME) FOLLOWUP_TYPE,  "+
			" NVL(TO_CHAR(A.EFF_VAL_DATE,'DD-MON-YYYY'),'-'),  B.CATEGORY_NAME ACTION_TAKEN, "+
			" NVL(TO_CHAR(A.ACTION_DATE,'DD-MON-YYYY'),'-'),  A.ACTION_SET_FOR, "+
			" NVL(TO_CHAR(A.ACTION_ENT_DATE,'DD-MON-YYYY'),'-'),  NVL(A.PREV_FOLLOWUP_NO,'-'), "+
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS,NVL(A.ENT_REMARKS,'-') "+
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), "+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)  "+ // 14,15 values
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO ("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO) ),A.ID_NO) "+ // 14,15 values
			" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO ("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO) ),A.ID_NO),NVL("+m_schema_name+".FA_CO_GET_LAST_FOLLOW_REMARK(A.ID_NO),A.ENT_REMARKS) "+ // 14,15,16 values
			" FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A, "+
			" "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY B "+
			" WHERE A.ACTION_TOBE_TAKEN=B.CATEGORY_CODE AND "+ 
			" TO_DATE(TO_CHAR(A.ACTION_ENT_DATE,'DD-MM-YY'),'DD-MM-YY')=TO_DATE('"+m_action_date+"','DD-MM-YY')"+q_assigned_by+q_assigned_to+q_div_code+q_div_sub_code+" "+
			" ORDER BY "+m_sort_column+" "+m_order_by_type+" "); 
			}else{
			
			//out.println("12"); // udara
			
				pstmt = conn.prepareStatement("  SELECT A.FOLLOW_UP_NO,NVL(A.ID_NO,'-'), "+
			" "+m_schema_name+".AF_CO_GET_FOLLOWUP_TYPE(A.ID_NO,A.SCREEN_NAME) FOLLOWUP_TYPE,  "+
			" NVL(TO_CHAR(A.EFF_VAL_DATE,'DD-MON-YYYY'),'-'),  B.CATEGORY_NAME ACTION_TAKEN, "+
			" NVL(TO_CHAR(A.ACTION_DATE,'DD-MON-YYYY'),'-'),  A.ACTION_SET_FOR, "+
			" NVL(TO_CHAR(A.ACTION_ENT_DATE,'DD-MON-YYYY'),'-'),  NVL(A.PREV_FOLLOWUP_NO,'-'), "+
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS,NVL(A.ENT_REMARKS,'-') "+
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), "+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)  "+ // 14,15 values
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO ("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO) ),A.ID_NO) "+ // 14,15 values
			" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO ("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO) ),A.ID_NO),NVL("+m_schema_name+".FA_CO_GET_LAST_FOLLOW_REMARK(A.ID_NO),A.ENT_REMARKS) "+ // 14,15,16 values
			" FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A, "+
			" "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY B "+
			" WHERE A.ACTION_TOBE_TAKEN=B.CATEGORY_CODE AND "+
			" TO_DATE(TO_CHAR(A.ACTION_ENT_DATE,'DD-MM-YY'),'DD-MM-YY')=TO_DATE('"+m_action_date+"','DD-MM-YY') AND "+
			" A.STATUS='"+m_status+"'"+q_assigned_by+q_assigned_to+q_div_code+q_div_sub_code+" "+
			" ORDER BY "+m_sort_column+" "+m_order_by_type+" "); 
		
			}
			
			}else if(m_action_date.equals("--") && m_eff_from_date.equals("--") && m_id_num.equals("") && m_action_date.equals("--")){			
			
			if(m_status.equals("ALL")){
			
			//out.println("13"); // udara
			
			pstmt = conn.prepareStatement("  SELECT A.FOLLOW_UP_NO,NVL(A.ID_NO,'-'), "+
			" NVL("+m_schema_name+".AF_CO_GET_FOLLOWUP_TYPE(A.ID_NO,A.SCREEN_NAME),'-') FOLLOWUP_TYPE,  "+
			" NVL(TO_CHAR(A.EFF_VAL_DATE,'DD-MON-YYYY'),'-'),  B.CATEGORY_NAME ACTION_TAKEN, "+
			" NVL(TO_CHAR(A.ACTION_DATE,'DD-MON-YYYY'),'-'),  A.ACTION_SET_FOR, "+
			" NVL(TO_CHAR(A.ACTION_ENT_DATE,'DD-MON-YYYY'),'-'),  NVL(A.PREV_FOLLOWUP_NO,'-'), "+
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS,NVL(A.ENT_REMARKS,'-') "+
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), "+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)  "+ // 14,15 values
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO ("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO) ),A.ID_NO) "+ // 14,15 values
			" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO ("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO) ),A.ID_NO),NVL("+m_schema_name+".FA_CO_GET_LAST_FOLLOW_REMARK(A.ID_NO),A.ENT_REMARKS) "+ // 14,15,16 values
			" FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A, "+
			" "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY B "+
			" WHERE A.ACTION_TOBE_TAKEN=B.CATEGORY_CODE"+q_assigned_by+q_assigned_to+q_div_code+q_div_sub_code+" "+
			" ORDER BY "+m_sort_column+" "+m_order_by_type+" "); 
						
			}else{
			
			//out.println("14"); // udara
			
			pstmt = conn.prepareStatement("  SELECT A.FOLLOW_UP_NO,NVL(A.ID_NO,'-'), "+
			" NVL("+m_schema_name+".AF_CO_GET_FOLLOWUP_TYPE(A.ID_NO,A.SCREEN_NAME),'-') FOLLOWUP_TYPE,  "+
			" NVL(TO_CHAR(A.EFF_VAL_DATE,'DD-MON-YYYY'),'-'),  B.CATEGORY_NAME ACTION_TAKEN, "+
			" NVL(TO_CHAR(A.ACTION_DATE,'DD-MON-YYYY'),'-'),  A.ACTION_SET_FOR, "+
			" NVL(TO_CHAR(A.ACTION_ENT_DATE,'DD-MON-YYYY'),'-'),  NVL(A.PREV_FOLLOWUP_NO,'-'), "+
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS,NVL(A.ENT_REMARKS,'-') "+
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), "+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)  "+ // 14,15 values
			//" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO ("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO) ),A.ID_NO) "+ // 14,15 values
			" A.ORG_FOLLOWUP_NO, NVL(A.FOLLOWUP_TIME,'-'), A.STATUS, A.ENT_REMARKS, "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO ("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO) ),A.ID_NO),NVL("+m_schema_name+".FA_CO_GET_LAST_FOLLOW_REMARK(A.ID_NO),A.ENT_REMARKS) "+ // 14,15,16 values
			" FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A, "+
			" "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY B "+
			" WHERE A.ACTION_TOBE_TAKEN=B.CATEGORY_CODE AND "+
			" A.STATUS='"+m_status+"'"+q_assigned_by+q_assigned_to+q_div_code+q_div_sub_code+" "+
			" ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
		
			}
			
			
			}  /*else{
			
			pstmt = conn.prepareStatement("  SELECT A.FOLLOW_UP_NO,NVL(A.ID_NO,'-'), "+
			" "+m_schema_name+".AF_CO_GET_FOLLOWUP_TYPE(A.ID_NO,A.SCREEN_NAME) FOLLOWUP_TYPE,  "+
			" TO_CHAR(A.EFF_VAL_DATE,'DD-MON-YY'),  B.CATEGORY_NAME ACTION_TAKEN, "+
			" TO_CHAR(A.ACTION_DATE,'DD-MON-YY'),  A.ACTION_SET_FOR, "+
			" TO_CHAR(A.ACTION_ENT_DATE,'DD-MON-YY'),  A.PREV_FOLLOWUP_NO, "+
			" A.ORG_FOLLOWUP_NO, A.FOLLOWUP_TIME, A.STATUS "+
			" FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A, "+
			" "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY B "+
			" WHERE A.ACTION_TOBE_TAKEN=B.CATEGORY_CODE ");
			
			
			
			
			
			} */
			
			
				
			//	}
			rs=pstmt.executeQuery(); 

			int j = 0;

			boolean more=rs.next();
			while(more){
						out.println("<tr >"); 
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(1)+"</TD>");
						out.println("<TD style=\"cursor:hand\"   onclick=\"load_history_report(document.getElementById('ID_NO"+j+"').value, document.getElementById('CLI_NO"+j+"').value);\"  class='txt_report_data' align='right'> "+rs.getString(15)+"</TD> <input type=hidden id=\"ID_NO"+j+"\" name=\"ID_NO"+j+"\" value=\""+rs.getString(15)+"\"> <input type=hidden id=\"CLI_NO"+j+"\" name=\"CLI_NO"+j+"\" value=\""+rs.getString(14)+"\"> ");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(3)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(4)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(5)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(6)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(7)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(8)+"</TD>");
						//out.println("<TD class='txt_report_data' align='right'>"+rs.getString(13)+"</TD>");//Added by Dineth on 2008-12-16
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(16)+"</TD>");//Added by Dineth on 2008-12-16
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(11)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(12)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(9)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(10)+"</TD>");
						out.println("</tr >"); 
						
						j = j+1;
						
						more=rs.next(); 
			} 

			out.println("</table>"); 
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr><td width='100%' class='note'></td></tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			rs.close();
			pstmt.close();
			conn.close();
			out.flush();
			out.close();
			}


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


