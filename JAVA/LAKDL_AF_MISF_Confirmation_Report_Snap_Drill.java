//Created By Minal on 17-06-2015 for #17087
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

public class LAKDL_AF_MISF_Confirmation_Report_Snap_Drill extends javax.servlet.http.HttpServlet { //LAKDL_AF_CR_PRO_Enter_Deletion_letter_details_app

	/*
	ServletOutputStream out = null;
	public String m_chksql;
	Connection conn;
	Statement stmt,stmt1,stmt2;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1,rs2;
	*/
	
	//public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { // commented by udara 18-05-2017
	public void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { // added by udara 18-05-2017
		
		// added by udara 18-05-2017
		ServletOutputStream out = null;
		//String m_chksql = null;
		Connection conn = null;
		Statement stmt= null,stmt1= null,stmt2= null;
		java.text.NumberFormat nf= null,nf1= null;
		ResultSet rs= null,rs1= null,rs2= null;
		// end by udara 18-05-2017
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			conn = m_sn_methods.met_user_validate(req); 
			
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		    nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			String m_schema_name = m_sn_methods.schema_name;
		    String m_chksql=req.getParameter("chksql");
			String m_username = m_sn_methods.username; // added by udara 13-05-2015
			
			if(m_chksql.equals("drill_down")){
				
				
				
					out.println("<HTML>"); 
					out.println("<HEAD>"); 
					out.println("<TITLE>Confirmation Report Approval Level 4</TITLE>"); 
					out.println("</HEAD>"); 
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
					out.println("<SCRIPT language=\"JavaScript\">");
					
					out.println("function load_details_report_new(val,m_id){"); 
					out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Confirmation_Report_Detail_Snap_Viewer?chksql=main_page&snap_view_status=Y&compare_status=N&generate_status=N&view_only_status=Y&snap_position=VIEW&finance_no=\"+val+\"&snap_id=\"+m_id; ");
					out.println("window.open(m_url,'popupwin_conf_rpt_app_1','left=110,top=110,width=750,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1')");
					out.println("} "); 

					out.println("</script>"); 
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' >"); 
					out.println("<FORM NAME='Form1' method='post'>"); 
					String m_contract_no="";
					m_contract_no=req.getParameter("finance_no");
					
					String m_user_branch = "";
			
					rs=stmt.executeQuery(" "+
						" SELECT "+ 
							" "+m_schema_name+".AF_CO_GET_USER_LOCATION('"+m_username+"') "+
								" FROM DUAL ");
					
					if(rs.next()){
						m_user_branch = rs.getString(1);
					}
					
					if(m_user_branch.equals("HO")){
						m_user_branch = "";
					}
					
					out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");	
					
					rs=stmt.executeQuery(" "+ 	
							" SELECT "+
								" SNAP_ID, "+
								//" SNAP_POSITION, "+
								" DECODE(SNAP_POSITION, "+
								" 'GENERATE','Generation', "+
								" 'REGENERATE','Re-Generation', "+
								" 'APPROVE1','Confirmation approval 01 Save', "+
								" 'APPROVED','Confirmation approval 01 Approve', "+
								" 'APPROVE_2','Confirmation approval 02 Approve', "+
								" 'DISAPPROVE','Confirmation approval 02 Disapprove', "+
								" 'APP_3','Confirmation approval 03 Approve', "+
								" 'DISSAP_3','Confirmation approval 03 Disapprove', "+
								" 'APP_4','Confirmation approval 04 Approve', "+
								" 'DISSAP_4','Confirmation approval 04 Disapprove', "+
								" SNAP_POSITION) SNAP_POSITION, "+
								
								" TO_CHAR(SNAP_TIME,'DD-MM-YYYY HH:MI:SS AM') "+
									" FROM "+m_schema_name+".AF_CONFIRMATION_REPORT_SNAP	"+
									" WHERE FINANCE_NO LIKE '%"+m_contract_no+"%'	"+	
									" ORDER BY SNAP_TIME "+
					" ");
					
					// end by udara 03-07-2015
					
					
						
				int j=0;
		    	boolean more = rs.next();
				if(!more){
						out.println("<table align=\"center\" width=\"100%\" class=\"table\"><TR class=\"pdn_txtpos2\" align=\"center\">");
						out.println("<td  width=\"*%\" align=\"center\"><B>No Records Found</td>");
	          			out.println("</TR></table>");
						out.println("<input type='hidden' name='REC_COUNT' value=0>");
				}
				
				int counts = 0; // added by udara 15-10-2015
				
				if(more){	
					
				out.println("<table align=\"center\" width=\"100%\" class=\"table\"><TR class=\"pdn_txtpos2\" align=\"center\">");
				out.println("<td  width=\"*%\" align=\"center\"><B> Confirmation Report - Snap Drilldown - "+m_contract_no+" </td>");
	          	out.println("</TR></table>");	
					
				out.println("<br>");	
				out.println("<b><HR>");	
				out.println("<br>");	
			    out.println("<table align=\"center\" width=\"100%\" border=\"1\" class=\"table\">");
	      		
				out.println("<tr class=pdn_txtpos2 align='left'>");
				//out.println("<td  width='1%' > <b>No.</b> </td>"); 
				out.println("<td  width='20%' align=left> <b>Snap ID</b></td>");
				out.println("<td  width='20%' align=left> <b>Snap Position</b></td>");
				out.println("<td  width='20%' align=left> <b>Snap Time</b></td>");
				out.println("<td  width='20%' align=left> <b>View</b></td>");

				out.println("</tr>");
				while(more){
					
				counts = counts + 1; // added by udara 15-10-2015	
					
			    out.println("<tr>");
				//out.println("<td  width='1%' >"+counts+"</td>"); // added by udara 15-10-2015
				out.println("<td  width='20%' align=left>"+rs.getString(1)+"</td>");
				out.println("<td  width='20%' align=left>"+rs.getString(2)+"</td>");
				out.println("<td  width='20%' align=left>"+rs.getString(3)+"</td>");
				out.println("<td  width='20%' align=left> <input type='button' name='DETA_BUTTON_"+j+"' value='View' class='but_input' onClick=load_details_report_new('"+m_contract_no+"','"+rs.getString(1)+"') > </td>");
				//out.println("<td  width='*%'  > &nbsp; </td>");
				more=rs.next();
				j=j+1;
				}
				out.println("</tr>");
				out.println("</table>");
				out.println("<input type='hidden' name='REC_COUNT' value="+j+">");
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("</body>"); 
				out.println("</html>"); 
				
				
				}		
			}
				
				
				// added by udara 18-05-2017
			if(rs!=null){try{rs.close();  }catch(Exception e){}}
			if(rs1!=null){try{rs1.close();  }catch(Exception e){}}
			if(rs2!=null){try{rs2.close();  }catch(Exception e){}}
			if(stmt!=null){try{stmt.close();  }catch(Exception e){}}
			if(stmt1!=null){try{stmt1.close();  }catch(Exception e){}}
			if(stmt2!=null){try{stmt2.close();  }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
			// end by udara 18-05-2017

			//*****************************************************************************************************************
		}catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
	