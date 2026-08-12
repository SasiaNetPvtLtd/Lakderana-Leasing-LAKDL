//--
//SCREEN NAME	:MIS - Reserve Accounts Summery report
//CREATED BY	:LALANKA
//DATE/TIME		:22-10-2009
//NOTES	

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_FA_MISF_Client_Facility_Status_Report1 extends javax.servlet.http.HttpServlet { 

		Connection conn;
		Statement stmt;
		public ResultSet rs;
		PreparedStatement pstmt;
		java.text.NumberFormat nf;

public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 

		try { 

			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);

			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 

			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			conn = m_sn_methods.met_user_validate(req); 
			stmt = conn.createStatement();

			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 

			ServletOutputStream out = res.getOutputStream(); 
			String m_schema_name = m_sn_methods.schema_name;


			String m_screen_type= req.getParameter("chksql");

			String m_fschema_name=m_sn_methods.client_name.trim();
			
			//String m_receipt_no=req.getParameter("receipt_no");
			String m_from_date = req.getParameter("from_date");
			String m_to_date = req.getParameter("to_date"); 
      //String m_rel_type = req.getParameter("realisation");
			String m_branch  = req.getParameter("branch");
			String facility_status = req.getParameter("FACILITY_STATUS");
						
			String m_order_by   = "CLIENT_CODE";	
			String m_sort_by = "ASC";
							
			if(req.getParameter("order_by")!=null && req.getParameter("sort_by")!=null){
			    m_order_by = req.getParameter("order_by");
			    m_sort_by = req.getParameter("sort_by");

			}


			if(m_screen_type.equals("MAIN")){

			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE> Client Facility Status Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("function befor_end(m_obj) {");
      		out.println("   m_obj.focus();");
      		out.println("}");
							
			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Payment Details Report - \"+m_val;"); 
			out.println("}");
			out.println("function load_roll_out_value(){"); 
			out.println("help_box.innerHTML=\" Payment Details Report  \";"); 
			out.println("}");
			
			out.println("function sort_data(m_sort_col) {");
			out.println("	 m_order_by_type = 'ASC'; ");  
			out.println("	 if(m_sort_col=='"+m_order_by+"'){");
			out.println("	   if('"+m_sort_by+"'=='DESC'){");
			out.println("	      m_order_by_type = 'ASC'; ");  
			out.println("    }else{");
			out.println("       m_order_by_type = 'DESC'; ");
			out.println("    }");
			out.println("  }else{");
			out.println("    m_order_by_type = 'ASC'; ");
			out.println("  }");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"FA_MISF_Client_Facility_Status_Report1?chksql=MAIN&from_date="+m_from_date+"&branch="+m_branch+"&to_date="+m_to_date+"&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\" \";");
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
			out.println("<tr class='pdn_txtpos2'><td width='60%' align='left' style='height: 18px'>  Client Facility Status Report</td>");
			out.println("<td width='40%' align='right'><input type=\"button\" class='mainbut'  onclick='close_window()' value=\"Close\"></td></tr>"); 
			out.println("</table>");  
			out.println("</table>");  
			out.println("</BR>");
			out.println("</BR>");

			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr class='pdn_txtpos2'>"); 
			out.println("<td width='6%' class='txt_report_column'>SE/NO</td>");
			out.println("<td width='6%' class='txt_report_column'>Facility No</td>"); 
			out.println("<td width='5%' class='txt_report_column' style='{cursor:hand}' onClick=\"sort_data('CLIENT_CODE')\">Client Code</td>");
			out.println("<td width='6%' class='txt_report_column'>Client Name</td>"); 
			out.println("<td width='6%' class='txt_report_column'>Facility Crerated Date</td>"); 
			//out.println("<td width='5%' class='txt_report_column'>SETTLE MODE</td>"); 
			out.println("<td width='6%' class='txt_report_column'>Facility Activated Date</td>");
			out.println("<td width='8%' class='txt_report_column'>Facility Terminated / Disapproved  Date</td>"); 
			out.println("<td width='6%' class='txt_report_column' align='right'>Credit Limit</td>"); 
			 
			out.println("<td width='6%' class='txt_report_column' >Facility Status</td>"); 
			out.println("</tr >"); 
			
			 String query ="";
			if(facility_status.equals("ALL")){//ALL FACILITIES
				
			    query= " SELECT ROWNUM,FACILITY_NO,CLIENT_CODE, "+
					   " NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-'), "+
				       " TO_CHAR(FACILITY_START_DATE,'DD-MM-YYYY'), "+
				       " NVL(TO_CHAR(ACTIVATE_DATE,'DD-MM-YYYY'),'-'), "+
				       " DECODE(FACILITY_STATUS,'C',TO_CHAR(APP_DATE,'DD-MM-YYYY'),'T',TO_CHAR(MOD_DATE,'DD-MM-YYYY'),'-') ,"+
				       " CREDIT_LIMIT,DECODE(FACILITY_STATUS,'Y','Activated','N','Wait for Credit Approval','A','Approval Level 1','A2','Approval Confirmation','C','Disapproved','Terminated') "+
				       " FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
				       " WHERE "+	
					   " FACILITY_STATUS IN ('T') AND "+ //,TERMINATED
					   " TO_DATE(TO_CHAR(MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
        		       " TO_DATE(TO_CHAR(MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					   " UNION "+
					   " SELECT ROWNUM,FACILITY_NO,CLIENT_CODE, "+
					   " NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-'), "+
				       " TO_CHAR(FACILITY_START_DATE,'DD-MM-YYYY'), "+
				       " NVL(TO_CHAR(ACTIVATE_DATE,'DD-MM-YYYY'),'-'), "+
				       " DECODE(FACILITY_STATUS,'C',TO_CHAR(APP_DATE,'DD-MM-YYYY'),'T',TO_CHAR(MOD_DATE,'DD-MM-YYYY'),'Y',TO_CHAR(ACTIVATE_DATE,'DD-MM-YYYY'),'-') ,"+
				       " CREDIT_LIMIT,DECODE(FACILITY_STATUS,'Y','Activated','N','Wait for Credit Approval','A','Approval Level 1','A2','Approval Confirmation','C','Disapproved','Terminated') "+
				       " FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
				       " WHERE "+	
					   " FACILITY_STATUS = 'Y' AND "+ //ACTIVATED
					   " TO_DATE(TO_CHAR(ACTIVATE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
        		       " TO_DATE(TO_CHAR(ACTIVATE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					   " UNION "+ 
					   " SELECT ROWNUM,FACILITY_NO,CLIENT_CODE, "+
					   " NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-'), "+
				       " TO_CHAR(FACILITY_START_DATE,'DD-MM-YYYY'), "+
				       " NVL(TO_CHAR(ACTIVATE_DATE,'DD-MM-YYYY'),'-'), "+
				       " DECODE(FACILITY_STATUS,'C',TO_CHAR(APP_DATE,'DD-MM-YYYY'),'T',TO_CHAR(MOD_DATE,'DD-MM-YYYY'),'-') ,"+
				       " CREDIT_LIMIT,DECODE(FACILITY_STATUS,'Y','Activated','N','Wait for Credit Approval','A','Approval Level 1','A2','Approval Confirmation','C','Disapproved','Terminated') "+
				       " FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
				       " WHERE "+	
					   " FACILITY_STATUS ='C' AND "+ // disaaproved
					   " TO_DATE(TO_CHAR(APP_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
        		       " TO_DATE(TO_CHAR(APP_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') ";//DISAPPROVED	
						
			} 	
			else if(facility_status.equals("ACTIVATED")) // activated facilities //Y
			{
			query=  " SELECT ROWNUM,FACILITY_NO,CLIENT_CODE, "+
					" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-'), "+
				    " TO_CHAR(FACILITY_START_DATE,'DD-MM-YYYY'), "+
				    " NVL(TO_CHAR(ACTIVATE_DATE,'DD-MM-YYYY'),'-'), "+
				    " DECODE(FACILITY_STATUS,'C',TO_CHAR(APP_DATE,'DD-MM-YYYY'),'T',TO_CHAR(MOD_DATE,'DD-MM-YYYY'),'Y',TO_CHAR('ACTIVATE_DATE','DD-MM-YYYY'),'-') ,"+
				    " CREDIT_LIMIT,DECODE(FACILITY_STATUS,'Y','Activated','N','Wait for Credit Approval','A','Approval Level 1','A2','Approval Confirmation','C','Disapproved','Terminated') "+
				    " FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
				    " WHERE "+	
					" FACILITY_STATUS ='Y' AND "+
					" TO_DATE(TO_CHAR(ACTIVATE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
        		    " TO_DATE(TO_CHAR(ACTIVATE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') ";

				   // " TO_DATE(TO_CHAR(FACILITY_START_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
        		   // " TO_DATE(TO_CHAR(FACILITY_START_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') ";
			}
			else if(facility_status.equals("TERMINATED")) // terminated facilities // T
			{
			query=  " SELECT ROWNUM,FACILITY_NO,CLIENT_CODE, "+
					" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-'), "+
				    " TO_CHAR(FACILITY_START_DATE,'DD-MM-YYYY'), "+
				    " NVL(TO_CHAR(ACTIVATE_DATE,'DD-MM-YYYY'),'-'), "+
				    " DECODE(FACILITY_STATUS,'C',TO_CHAR(APP_DATE,'DD-MM-YYYY'),'T',TO_CHAR(MOD_DATE,'DD-MM-YYYY'),'-') ,"+
				    " CREDIT_LIMIT,DECODE(FACILITY_STATUS,'Y','Activated','N','Wait for Credit Approval','A','Approval Level 1','A2','Approval Confirmation','C','Disapproved','Terminated') "+
				    " FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
				    " WHERE "+	
					" FACILITY_STATUS ='T' AND "+
					" TO_DATE(TO_CHAR(MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
        		    " TO_DATE(TO_CHAR(MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') ";
				
			}
			else if(facility_status.equals("DISAPPROVED")) // disapproved //C
			{
				query= " SELECT ROWNUM,FACILITY_NO,CLIENT_CODE, "+
					   " NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-'), "+
				       " TO_CHAR(FACILITY_START_DATE,'DD-MM-YYYY'), "+
				       " NVL(TO_CHAR(ACTIVATE_DATE,'DD-MM-YYYY'),'-'), "+
				       " DECODE(FACILITY_STATUS,'C',TO_CHAR(APP_DATE,'DD-MM-YYYY'),'T',TO_CHAR(MOD_DATE,'DD-MM-YYYY'),'-') ,"+
				       " CREDIT_LIMIT,DECODE(FACILITY_STATUS,'Y','Activated','N','Wait for Credit Approval','A','Approval Level 1','A2','Approval Confirmation','C','Disapproved','Terminated') "+
				       " FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
				       " WHERE "+	
					   " FACILITY_STATUS ='C' AND "+
					   " TO_DATE(TO_CHAR(APP_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
        		       " TO_DATE(TO_CHAR(APP_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') ";

			}			
			
			//out.println(query);
			pstmt = conn.prepareStatement(query);
				
		    /*query=" SELECT ROWNUM,FACILITY_NO,CLIENT_CODE, "+
				" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-'), "+
				" TO_CHAR(FACILITY_START_DATE,'DD-MM-YYYY'), "+
				" NVL(TO_CHAR(ACTIVATE_DATE,'DD-MM-YYYY'),'-'), "+
				" DECODE(FACILITY_STATUS,'C',TO_CHAR(APP_DATE,'DD-MM-YYYY'),'T',TO_CHAR(MOD_DATE,'DD-MM-YYYY'),'-') ,"+
				" CREDIT_LIMIT,DECODE(FACILITY_STATUS,'Y','Activated','N','Wait for Credit Approval','A','Approval Level 1','A2','Approval Confirmation','C','Disapproved','Terminated') "+
				" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
				" WHERE "+	
				" TO_DATE(TO_CHAR(FACILITY_START_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
        		" TO_DATE(TO_CHAR(FACILITY_START_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') ");
			
			*/
										 
			rs=pstmt.executeQuery(); 
			int j=0;

			boolean more=rs.next();
			while(more){
								if(j>0 && j%2==1){
			      out.println("<tr class=tr_input1 >");
						}
						else{
						
			      out.println("<tr class=tr_input >");
						}

						out.println("<TD class='txt_report_data'  >"+rs.getString(1)+"</TD>");
						out.println("<TD class='txt_report_data' style= cursor:hand;cursor-color:blue onclick=show_facility('"+rs.getString(2)+"') >"+rs.getString(2)+"</TD>");
						out.println("<TD class='txt_report_data'  >"+rs.getString(3)+"</TD>");
						out.println("<TD class='txt_report_data' >"+rs.getString(4)+"</TD>");
						out.println("<TD class='txt_report_data' >"+rs.getString(5)+"</TD>");
						out.println("<TD class='txt_report_data' >"+rs.getString(6)+"</TD>");
						out.println("<TD class='txt_report_data' > "+rs.getString(7)+"</TD>");
						
						out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(8))+"</TD>");
						out.println("<TD class='txt_report_data' >"+rs.getString(9)+"</TD>");
						
					
						more=rs.next(); 
						j=j+1;
						
			} 
			
			
			

			out.println("</table>"); 
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr><td width='100%' class='note'></td></tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
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


