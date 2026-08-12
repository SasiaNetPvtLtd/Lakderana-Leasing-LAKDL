// DEVELOP BY : Ishani 2013.07.11
// DATE:25-08-2008

import java.io.*; 
import javax.servlet.*;   
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

public class LAKDL_AF_PRO_Cr_book_pledge_upload_excel_report extends javax.servlet.http.HttpServlet { 
	
	public void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		ServletOutputStream out = null;
		Connection conn;
		Statement stmt1,stmt2,stmt3,stmt4,stmt;
		stmt1=stmt2=stmt3=stmt4=stmt=null;
		CallableStatement callstmt1 =null;
		
		java.text.NumberFormat nf;
		nf=null;
		ResultSet rs1,rs,rs2,rs3,rs4;
		rs1=rs=rs2=rs3=rs4=null;
		
		String m_chksql;
		
		
		try { 
			
			//******************************************************************************** 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
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
			String m_status="";
			
			if(req.getParameter("status")!=null){
			 m_status=req.getParameter("status");
			}
			out = res.getOutputStream(); 
			conn = m_sn_methods.met_user_validate(req); 
			
			stmt1=conn.createStatement();
			stmt2=conn.createStatement();
			stmt3=conn.createStatement();
			stmt4=conn.createStatement();
			
			
			if(m_chksql.equals("upload_exception_list")){		
				
				
				stmt = conn.createStatement ();
				
				out.println("<br>");	
				out.println("<br>");
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>CR Books - Excel Upload Exceptions</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function add_button(){");
				
				if(m_status.equals("autho")){
					out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"> <input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" style=\"width:40px;\" onClick=\"cr_print()\"></td> </tr>';"); //onClick=\"print_data()\"
				}else{
			     out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_AUTH\" value=\"Authorize\" style=\"width:80px;\" onClick=\"save_window()\"></td> </tr>';"); //onClick=\"print_data()\"
				}
			    out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
				out.println("m_writedata+'</table>';");
				
				out.println("}");	
			
			    out.println("function save_window(){	");  
			
				out.println("   	document.Form1.BUT_AUTH.disabled=true;");
			    out.println("   	document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_PRO_Save_Cr_book_pledge_upload_excel';");
			    out.println("		document.Form1.submit();");
				out.println("}"); 
				
				
			
				
				out.println("function cr_print(){"); 
			    out.println("m_table.innerHTML=''");
				out.println("window.print();");
				out.println("window.close();");
				out.println("}"); 
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' onLoad=\"add_button()\">");	
				out.println("<form name='Form1'>");
				
				out.println("<table width='100%' class='table' cellspacing='0'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>");	
				
				out.println("<br>");	
				out.println("<br>");
			
			  
			   	out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>CR Books - Excel Upload List</u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				
				
				String Sql_data="";
				boolean more;

				
				    Sql_data=" SELECT NVL(A.FINANCE_NO,'-') , "+
							 "  A.VEHICLE_NO,  "+
							 "  A.BANK_CODE, "+
							 "  A.BRANCH_CODE, "+	
							 "  A.EXCEPTION_REMARK "+		
							 "  FROM "+m_schema_name+".AF_PRO_PLEDGE_CR_BOOK_TMP A "+
							 "  WHERE "+
							 "  A.ENT_USER = '"+m_username+"' AND A.EXCEPTION_REMARK IS NULL " ;
					
					
				
					
				out.println	("<!--Upload Exception  "+Sql_data+" -->");
				
				rs=stmt.executeQuery(Sql_data);
				more=rs.next();
				int i=1;
				
				if(!more){
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
					out.println("<tr class=pdn_txtpos2  >");
					out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>No Records To Display</u></td>"); 
					out.println("</tr >");
					out.println("</table >");
				}
				else
				{
				double m_tot = 0.00;
				String m_td_color=null;
				int num_row=0;
				int count=1;
				out.println("<table id=mytable align=\"center\" width=\"90%\" border=\"1\" class=\"table\"  cellspacing=0 > ");
				out.println("<tr> ");
				out.println("<td width='10%' class=div_input align='center' bgcolor='lightblue' ><B> No. </B></td>");
				out.println("<td width='20%' class=div_input align='center' bgcolor='lightblue' ><B> Finance No </B></td>");
				out.println("<td width='20%' class=div_input align='center' bgcolor='lightblue' ><B> Vehicle No</B></td>");
				out.println("<td width='20%' class=div_input align='center' bgcolor='lightblue' ><B> Bank Code</B></td>");
				out.println("<td width='20%' class=div_input align='center' bgcolor='lightblue' ><B> Branch Code</B></td>");
				
				out.println("</tr >");
				
				String mm_bg_color = "";
				
				while(more)
					
					{
						
						mm_bg_color = "BGCOLOR='white'";
						out.println("<TR "+mm_bg_color+" >");
						out.println("<TD STYLE='{ font: 9pt arial; text-align:left;}'  ><B>"+i+"</B> </TD>");
						out.println("<TD STYLE='{ font: 9pt arial; text-align:left;}'  ><B>"+rs.getString(1)+"</B> </TD>");
						out.println("<TD STYLE='{ font: 9pt arial; text-align:left;}'  ><B>"+rs.getString(2)+"</B> </TD>");
						out.println("<TD STYLE='{ font: 9pt arial; text-align:left;}'  ><B>"+rs.getString("BANK_CODE")+"</B> </TD>");
						out.println("<TD STYLE='{ font: 9pt arial; text-align:left;}'  ><B>"+rs.getString("BRANCH_CODE")+"</B> </TD>");
						
						out.println("</TR>");
						
						i++;
						
						more = rs.next();				
					}
					
				
				out.println("</table>");
				
			}
				
				out.println("<br>");	
				out.println("<br>");
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>CR Books - Excel Upload Exception List</u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				
				
				String Sql_CR_data="";
				boolean more1;

				
				    Sql_CR_data=" SELECT NVL(A.FINANCE_NO,'-') , "+
							 "  A.VEHICLE_NO,  "+
							// "  A.BANK_CODE, "+
							 //"  A.BRANCH_CODE, "+	
							 "  A.EXCEPTION_REMARK "+		
							 "  FROM "+m_schema_name+".AF_PRO_PLEDGE_CR_BOOK_TMP A "+
							 "  WHERE "+
							 "  A.ENT_USER = '"+m_username+"' AND A.EXCEPTION_REMARK IS NOT NULL " ;
					
					
				
					
				out.println	("<!--Upload Exception  "+Sql_CR_data+" -->");
				
				rs=stmt.executeQuery(Sql_CR_data);
				more1=rs.next();
				int x=1;
				
				if(!more1){
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
					out.println("<tr class=pdn_txtpos2  >");
					out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>No Records To Display</u></td>"); 
					out.println("</tr >");
					out.println("</table >");
				}
				else
				{
				double m_tot = 0.00;
				String m_td_color=null;
				int num_row=0;
				int count=1;
				out.println("<table id=mytable align=\"center\" width=\"90%\" border=\"1\" class=\"table\"  cellspacing=0 > ");
				out.println("<tr> ");
				out.println("<td width='10%' class=div_input align='center' bgcolor='lightblue' ><B> No. </B></td>");
				out.println("<td width='20%' class=div_input align='center' bgcolor='lightblue' ><B> Finance No </B></td>");
				out.println("<td width='20%' class=div_input align='center' bgcolor='lightblue' ><B> Vehicle No</B></td>");
				out.println("<td width='40%' class=div_input align='center' bgcolor='lightblue' ><B> Exception Remark</B></td>");
				
				out.println("</tr >");
				
				String mm_bg_color = "";
				
				while(more1)
					
					{
						
						mm_bg_color = "BGCOLOR='white'";
						out.println("<TR "+mm_bg_color+" >");
						out.println("<TD STYLE='{ font: 9pt arial; text-align:left;}'  ><B>"+x+"</B> </TD>");
						out.println("<TD STYLE='{ font: 9pt arial; text-align:left;}'  ><B>"+rs.getString(1)+"</B> </TD>");
						out.println("<TD STYLE='{ font: 9pt arial; text-align:left;}'  ><B>"+rs.getString(2)+"</B> </TD>");
						out.println("<TD STYLE='{ font: 9pt arial; text-align:left;}'  ><B>"+rs.getString(3)+"</B> </TD>");
						
						out.println("</TR>");
						
						x++;
						
						more1 = rs.next();				
					}
					
				
				out.println("</table>");
				
			}
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}
			
			
			// added by udara 18-05-2017
			if(rs!=null){try{rs.close();  }catch(Exception e){}}
			if(rs1!=null){try{rs1.close();  }catch(Exception e){}}
			if(rs2!=null){try{rs2.close();  }catch(Exception e){}}
			if(rs3!=null){try{rs3.close();  }catch(Exception e){}}
			if(rs4!=null){try{rs4.close();  }catch(Exception e){}}
			if(stmt!=null){try{stmt.close();  }catch(Exception e){}}
			if(stmt1!=null){try{stmt1.close();  }catch(Exception e){}}
			if(stmt2!=null){try{stmt2.close();  }catch(Exception e){}}
			if(stmt3!=null){try{stmt3.close();  }catch(Exception e){}}
			if(stmt4!=null){try{stmt4.close();  }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
			// end by udara 18-05-2017
			
			
			
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
