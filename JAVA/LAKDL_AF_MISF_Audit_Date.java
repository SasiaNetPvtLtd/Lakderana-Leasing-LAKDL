// DEVELOP BY : SANDUN FOR OFSCL LEASING-CREDIT  
// DATE:25-08-2008

import java.io.*; 
import javax.servlet.*;   
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 
public class LAKDL_AF_MISF_Audit_Date extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	Statement stmt1,stmt;
	CallableStatement callstmt1 =null;

	java.text.NumberFormat nf;
	public ResultSet rs1,rs;

	public String m_chksql;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
		
			//******************************************************************************** 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			LAKDL_AF_MISF_Audit_Date_SQL CO_methods = new LAKDL_AF_MISF_Audit_Date_SQL();
			LAKDL_AF_MISF_Audit_Date_Drill_SQL CO_drill_methods = new LAKDL_AF_MISF_Audit_Date_Drill_SQL();//mk
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_schema_name = m_sn_methods.schema_name;
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			String m_username = m_sn_methods.username;
			//**********************************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);      
			res.setStatus(HttpServletResponse.SC_OK); 
			
			res.setContentType("text/html"); 
			m_chksql=req.getParameter("chksql");
			//Added by Dineth on 28-04-2009
			String m_sort_column   = "A.FINANCE_NO";	
			String m_order_by_type = "ASC";
			
			//End by Dineth on 28-04-2009
			out = res.getOutputStream(); 
			conn = m_sn_methods.met_user_validate(req); 
			
			stmt1=conn.createStatement();
			
		  if(m_chksql.equals("run_report")){		
		
					String m_from_date=req.getParameter("from_date");
					String m_to_date  =req.getParameter("to_date");
					String m_user     =req.getParameter("user");
					String m_rpt_type =req.getParameter("rpt_type");
					String m_rpt_name =req.getParameter("rpt_name");
					
					//Added by Dineth on 28-04-2009
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
			    //End by Dineth on 28-04-2009
					
				  String m_date="";
				  String m_facility_code="";
				  String m_officer="";
					String m_officer_name="";
					String m_location_desc="";
					String m_start_date="";
					String m_end_date="";
					
					stmt = conn.createStatement ();
					//stmt2 = conn.createStatement ();
						
										
								
					  out.println("<HTML>"); 
						out.println("<HEAD>"); 
						out.println("<TITLE>Audit Reports </TITLE>"); 
						out.println("</HEAD>"); 
						out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
						out.println("<SCRIPT language=\"JavaScript\">"); 
						//Added by Dineth on 28-04-2009
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
							//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_legal_provision_report?chksql=run_report&asat_date="+m_asat_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type+\" \";");
						  out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Agreement_Register?chksql=load_agreement_regi&from_date="+m_from_date+"&to_date="+m_to_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type+\" \";");
							out.println(" window.location.href=m_url;");
							
							out.println("}");
						  
							out.println("function call_data(m_data) {");
							out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Audit_Date?chksql=drill_report&m_data=\"+m_data+\"&rpt_type="+m_rpt_type+"&user="+m_user+"&from_date="+m_from_date+"&to_date="+m_to_date+"&rpt_name="+m_rpt_name+"\";"); 
			        out.println("		window.open(m_url,'displayWindow4','left=50,top=60,width=850,height=490,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=0');");
			        //out.println(" window.location.href=m_url;");
							
							out.println("}");
							
							
						
						out.println("</script>"); 
						out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
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
						out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Finance - Audit Reports</td>"); 
						out.println("</tr>"); 
						out.println("<tr>"); 
						out.println("<td  height='10px' class='pdn_txtpos'>"); 
						out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
						out.println("<tr><td width='13%' align='center'></td>");  
						out.println("<td width='12%' align='center'></td>");  
						out.println("<td width='12%' align='center'></td>");  
						//out.println("<td width='10%' align='center'></td>");
						//out.println("<td width='10%' align='center'></td>");
						out.println("<td width='12%'></td>");  
						//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
						//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
						out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout=''   onclick='close_window()' value=\"Close\"></td>"); //load_roll_out_value()
						out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
						out.println("</table>");  
						out.println("</td></tr><tr>");  
						out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
						out.println("</tr>");  
			      out.println("<tr><td>");  
			      //out.println("</table>");
			      //out.println("</table>");
			      //out.println("</table>");
			 
						String sysdate = "";
						rs=stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY HH24:MI:SS') FROM DUAL");
						
						boolean more=rs.next();
						if(more){
						  sysdate = rs.getString(1);
						}
			      out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
						out.println("<tr><td>&nbsp;</td></tr>");
						out.println("<tr class=pdn_txtpos2 >");
						out.println("<td width=\"*%\" align=\"center\"><b>"+m_rpt_name+" </b></td>"); 
						out.println("</tr >");
						out.println("<tr >");
						out.println("<td width=\"*%\" align=\"center\">"+sysdate+" - "+m_username+"</td>"); 
						out.println("</tr >");
						out.println("</table >");
						out.println("<br>");
						out.println("<br>");			
						
			 			String Sql_data=CO_methods.getQuery(m_schema_name,m_from_date,m_to_date,m_rpt_type);
						//out.println("Sql_data="+Sql_data);
						rs=stmt.executeQuery(Sql_data);
						
						ResultSetMetaData rsMetaData = rs.getMetaData();
						
			      int numberOfColumns = rsMetaData.getColumnCount();
						//out.println("numberOfColumns="+numberOfColumns);
						
						more=rs.next();
						int count=0;
						
						
						
						
						
						if(!more){
						out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
						out.println("<tr class=pdn_txtpos2  >");
						out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   >No Records To Display</td>"); 
						out.println("</tr >");
						out.println("</table >");
						}
									
						//=================================================
						out.println("<table align=\"center\" width=\"95%\" border='1' class=\"table\" cellspacing=\"1\" >"); //bordercolor='black' cellspacing=0
						if(more){
						 
			    
						out.println("<tr bgcolor=\"#C0C0C0\"  >");
						out.println("<td width=\"5%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >No</td>"); //1
						for (int i=1;i<=numberOfColumns;i++){
						   out.println("<td STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >"+rsMetaData.getColumnLabel(i)+"</td>"); //1
							
						}
						out.println("</tr >");
						
						}
						int j=1;
						//int count=0;
						double m_total_due=0,total_mon_rental=0,total_curr_due=0;
						String m_application_no="";
						
						while(more){
						
						out.println("<tr  bgcolor=\"#FFFFFF\"  >");
						out.println("<td width=\"5%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+j+"</td>");  //1
						for (int i=1;i<=numberOfColumns;i++){
						  if(i==1){
						    out.println("<td width=\"15%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand;}'  onclick=\"call_data('"+rs.getString(i)+"')\"  >"+rs.getString(i)+"</td>");  //6
							}else{
							  out.println("<td width=\"15%\"  STYLE='{font: 8pt arial; text-align:center; }'    >"+rs.getString(i)+"</td>");  //6
							}
						}
								
						out.println("</tr >");			
						more=rs.next();
						count+=1;
						j+=1;
						if(more){
						 out.println("<tr  bgcolor=\"#C0C0C0\"  >");
						 out.println("<td width=\"5%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+j+"</td>");  //1
						 for (int i=1;i<=numberOfColumns;i++){
						  if(i==1){
						    out.println("<td width=\"15%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand;}'  onclick=\"call_data('"+rs.getString(i)+"')\"  >"+rs.getString(i)+"</td>");  //6
							}else{
							  out.println("<td width=\"15%\"  STYLE='{font: 8pt arial; text-align:center; }'    >"+rs.getString(i)+"</td>");  //6
							}
						 }
								
						 out.println("</tr >");
						}
						
						count+=1;
						j+=1;
						more=rs.next();
						if(!more){break;}
						}
						
						
						
						
						out.println("</table>");		 
						
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			    out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			    out.println("</body>");
					out.println("</html>");
					
					
     }else if(m_chksql.equals("drill_report")){		
		
					String m_from_date=req.getParameter("from_date");
					String m_to_date  =req.getParameter("to_date");
					String m_user     =req.getParameter("user");
					String m_rpt_type =req.getParameter("rpt_type");
					String m_rpt_name =req.getParameter("rpt_name");
					String m_data     =req.getParameter("m_data");
					
					//Added by Dineth on 28-04-2009
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
			    //End by Dineth on 28-04-2009
					
				  String m_date="";
				  String m_facility_code="";
				  String m_officer="";
					String m_officer_name="";
					String m_location_desc="";
					String m_start_date="";
					String m_end_date="";
					
					stmt = conn.createStatement ();
					//stmt2 = conn.createStatement ();
						
										
								
					  out.println("<HTML>"); 
						out.println("<HEAD>"); 
						out.println("<TITLE>Audit Reports </TITLE>"); 
						out.println("</HEAD>"); 
						out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
						out.println("<SCRIPT language=\"JavaScript\">"); 
						//Added by Dineth on 28-04-2009
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
							//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_legal_provision_report?chksql=run_report&asat_date="+m_asat_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type+\" \";");
						  out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Agreement_Register?chksql=load_agreement_regi&from_date="+m_from_date+"&to_date="+m_to_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type+\" \";");
							out.println(" window.location.href=m_url;");
							
							out.println("}");
						  
							out.println("function call_data(m_data) {");
							out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Audit_Date?chksql=drill_report&m_data=\"+m_data+\"&rpt_type="+m_rpt_type+"&user="+m_user+"&from_date="+m_from_date+"&to_date="+m_to_date+"&rpt_name="+m_rpt_name+"\";"); 
			        out.println(" window.location.href=m_url;");
							
							out.println("}");
							
						
						//end by Dineth on 28-04-2009	
						out.println("</script>"); 
						out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
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
						out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Finance - Audit Reports</td>"); 
						out.println("</tr>"); 
						out.println("<tr>"); 
						out.println("<td  height='10px' class='pdn_txtpos'>"); 
						out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
						out.println("<tr><td width='13%' align='center'></td>");  
						out.println("<td width='12%' align='center'></td>");  
						out.println("<td width='12%' align='center'></td>");  
						//out.println("<td width='10%' align='center'></td>");
						//out.println("<td width='10%' align='center'></td>");
						out.println("<td width='12%'></td>");  
						//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
						//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
						out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout=''   onclick='close_window()' value=\"Close\"></td>"); //load_roll_out_value();
						out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
						out.println("</table>");  
						out.println("</td></tr><tr>");  
						out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
						out.println("</tr>");  
			      out.println("<tr><td>");  
			      //out.println("</table>");
			      //out.println("</table>");
			      //out.println("</table>");
			 
						String sysdate = "";
						rs=stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY HH24:MI:SS') FROM DUAL");
						
						boolean more=rs.next();
						if(more){
						  sysdate = rs.getString(1);
						}
			      out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
						out.println("<tr><td>&nbsp;</td></tr>");
						out.println("<tr class=pdn_txtpos2 >");
						out.println("<td width=\"*%\" align=\"center\"><b>"+m_rpt_name+" </b></td>"); 
						out.println("</tr >");
						out.println("<tr >");
						out.println("<td width=\"*%\" align=\"center\">"+sysdate+" - "+m_username+"</td>"); 
						out.println("</tr >");
						out.println("</table >");
						out.println("<br>");
						out.println("<br>");			
				
						String Sql_data=CO_drill_methods.getQuery_drill(m_schema_name,m_from_date,m_to_date,m_rpt_type,m_data);
						//out.println("Sql_data="+Sql_data);
						rs=stmt.executeQuery(Sql_data);
						
						ResultSetMetaData rsMetaData = rs.getMetaData();
						
			      int numberOfColumns = rsMetaData.getColumnCount();
						//out.println("numberOfColumns="+numberOfColumns);
						
						more=rs.next();
						int count=0;
						
						
						/*out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
						out.println("<tr >");
						out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>"+m_rpt_name+"</u></td>"); 
						out.println("</tr >");
						out.println("</table >");
						out.println("<br>");
						out.println("<br>");
						*/
						
						if(!more){
						out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
						out.println("<tr class=pdn_txtpos2  >");
						out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>No Records To Display</u></td>"); 
						out.println("</tr >");
						out.println("</table >");
						}
									
						//=================================================
						out.println("<table align=\"center\" width=\"95%\"  class=\"table\" border=\"1\" cellspacing=\"1\">"); //bordercolor='black' cellspacing=0
						if(more){
						 
			    
						out.println("<tr bgcolor=\"#C0C0C0\"  >");
						out.println("<td width=\"5%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >No</td>"); //1
						for (int i=1;i<=numberOfColumns;i++){
						   out.println("<td STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >"+rsMetaData.getColumnLabel(i)+"</td>"); //1
							
						}
						out.println("</tr >");
						
						}
						int j=1;
						//int count=0;
						double m_total_due=0,total_mon_rental=0,total_curr_due=0;
						String m_application_no="";
						
						while(more){
						
						out.println("<tr  bgcolor=\"#FFFFFF\"  >");
						out.println("<td width=\"5%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+j+"</td>");  //1
						for (int i=1;i<=numberOfColumns;i++){
						  if(i==1){
						    out.println("<td width=\"15%\"  STYLE='{font: 8pt arial; text-align:center; }'  onclick=\"call_data('"+rs.getString(i)+"');\"  >"+rs.getString(i)+"</td>");  //6
							}else{
							  out.println("<td width=\"15%\"  STYLE='{font: 8pt arial; text-align:center; }'    >"+rs.getString(i)+"</td>");  //6
							}
						}
								
						out.println("</tr >");			
						more=rs.next();
						count+=1;
						j+=1;
						if(more){
						 out.println("<tr  bgcolor=\"#C0C0C0\"  >");
						 out.println("<td width=\"5%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+j+"</td>");  //1
						 for (int i=1;i<=numberOfColumns;i++){
						  out.println("<td width=\"15%\"  STYLE='{font: 8pt arial; text-align:center; }'    >"+rs.getString(i)+"</td>");  //6
						 }
								
						 out.println("</tr >");
						}
						
						count+=1;
						j+=1;
						more=rs.next();
						if(!more){break;}
						}
						
						
						
						
						out.println("</table>");		 
						
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			    out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			    out.println("</body>");
					out.println("</html>");
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
