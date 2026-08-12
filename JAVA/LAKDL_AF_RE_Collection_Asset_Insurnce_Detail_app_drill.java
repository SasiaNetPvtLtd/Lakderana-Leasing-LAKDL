
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

public class LAKDL_AF_RE_Collection_Asset_Insurnce_Detail_app_drill extends javax.servlet.http.HttpServlet { 

	public void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 

		ServletOutputStream out = null;
		Connection conn = null;
		Statement stmt= null,stmt1= null,stmt2= null;
		java.text.NumberFormat nf= null,nf1= null;
		ResultSet rs= null,rs1= null,rs2= null;
		 
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
			String m_username = m_sn_methods.username; 

			
			if(m_chksql.equals("insurance_details_app_drill")){
					
					String	m_app_no  = req.getParameter("applicaton_no");

					out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");	

					rs=stmt.executeQuery(" "+ 
					//out.println(" "+
										/*
										" SELECT "+
											" FINANCE_NO, "+
											" MOD_USER, "+
											" TO_CHAR(MOD_DATE,'DD-MM-YYYY HH:MI:SS') "+
											" FROM "+m_schema_name+".AF_INIT_INSURANCE_DATA_LOG "+
											" WHERE FINANCE_NO = '"+m_app_no+"'  "+
											" AND   CURR_STATUS <> 'DISSAPROVE'   "+
											" "+
											*/
										
					" SELECT "+ 
								 " FINANCE_NO, "+
								 " MOD_USER, "+
								 " TO_CHAR(MOD_DATE,'DD-MM-YYYY HH:MI:SS'), "+
								 " CURR_STATUS "+
								 " FROM "+m_schema_name+".AF_INIT_INSURANCE_DATA_LOG "+
								 " WHERE FINANCE_NO = '"+m_app_no+"' "+ 
								 " AND   CURR_STATUS <> 'DISSAPROVE' "+
					       
					" UNION  "+    
					       
					" SELECT "+
								 " FINANCE_NO, "+
								 " ENT_USER, "+
								 " TO_CHAR(ENT_DATE,'DD-MM-YYYY HH:MI:SS'), "+
								 " 'PENDING' "+
								 " FROM "+m_schema_name+".AF_INIT_INSURANCE_DATA "+
								 " WHERE FINANCE_NO = '"+m_app_no+"' "+	
									
					" ");
						
						
				int j=0;
		    	boolean more = rs.next();
				if(!more){
						out.println("<table align=\"center\" width=\"100%\" class=\"table\"><TR class=\"pdn_txtpos2\" align=\"center\">");
						out.println("<td  width=\"*%\" align=\"center\"><B>No Records Found</td>");
	          			out.println("</TR></table>");
						out.println("<input type='hidden' name='REC_COUNT' value=0>");
				}
				
				int counts = 0; 
				
				if(more){		

				out.println("<br>");
				
				out.println("<table align=\"center\" width=\"100%\" class=\"table\"><TR class=\"pdn_txtpos2\" align=\"center\">");
				out.println("<td  width=\"*%\" align=\"center\"><B><U>Insurance Approval Details</U></B></td>");
	          	out.println("</TR></table>");
					
				out.println("<br>");
				
				out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
	      		out.println("<tr class=pdn_txtpos2 align='left'>");

					out.println("<td  width='10%' ><b> Finance No. </b></td>"); 
					out.println("<td  width='10%' ><b> Approved User </b></td>");
					out.println("<td  width='10%' ><b> Approved Date </b></td>");
					out.println("<td  width='10%' ><b> Status </b></td>");
					
				out.println("</tr>");
				while(more){
					 
				counts = counts + 1; 

			    out.println("<tr>"); 
			    out.println("<td  width='10%'  > "+rs.getString(1)+" </td>");
				out.println("<td  width='10%'  > "+rs.getString(2)+" </td>");
				out.println("<td  width='10%'  > "+rs.getString(3)+" </td>");
				out.println("<td  width='10%'  > "+rs.getString(4)+" </td>");
				
				more=rs.next();
				j=j+1;
				}
				out.println("</tr>");
				out.println("</table>");
				out.println("<input type='hidden' name='REC_COUNT' value="+j+">");
				}
			} // end if details

				
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
	