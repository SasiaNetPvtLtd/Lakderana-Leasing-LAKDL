
	// CREATED BY Minal ON 06-01-2015 for #15195

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_CR_PRO_Document_sinhala_letter_4 extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out = null;
	
	Connection conn;
	Statement stmt,stmt2,stmt_doc_charges,stmt_make,stmt_rental;
	//	CallableStatement callstmt1;
	java.text.NumberFormat nf;
	
	public ResultSet rs,rs2,rs_doc_charges,rs_make,rs_anx_status,rs_rental;
	
	
	public String m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_due_date,m_no_of_due_date,m_finance_no,m_print;
	public double m_amount_due;
	String rec_count="";
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_username = m_sn_methods.username;
			//	String m_chksql;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			// out.println("conn"+conn);
			int m_data_count=0;
			String m_status ="";
			
			
			//Decaring variables
			
			String m_full_name="";
			String m_add1="";
			String m_add2="";
			String m_city_name="";
			String m_vehicle_num="";
			String finance_no = "";
						double tot_arrears =0.00;
		    double m_rental_amt  =0.00;
			double m_rental_amt_future  =0.00;
			double m_odi_amt     =0.00;
			double m_other_amt   =0.00;
			double m_other_amt_future   =0.00;
			double m_total_amt   =0.00;
			double m_total_amt_future =0.00;
			double m_odi_amt_future=0.00;
			double m_excess_receipt_amount =0.00; 
			double m_bal_arrears =0.00; 

			String overdue_date = "";
			
			String m_chksql = req.getParameter("chksql");
			String m_application_no = req.getParameter("application_no");
			stmt = conn.createStatement ();
			stmt2 = conn.createStatement ();
			if(m_chksql.trim().equals("main_page")){
				//stmt = conn.createStatement ();
				stmt_doc_charges = conn.createStatement ();
				stmt_make = conn.createStatement ();
				stmt_rental= conn.createStatement (); //added by nuwan de silva on 10-09-07
				
				//String m_application_no = req.getParameter("application_no");
				//String m_client_code	  =req.getParameter("client_code");		
				String m_client_code="";
				String m_document_code="";
				String m_print="";
				if(req.getParameter("client_code")!=null){
					m_client_code	  =req.getParameter("client_code");		
				}
				if(req.getParameter("document_code")!=null){
					m_document_code	=req.getParameter("document_code");	
				}
				if(req.getParameter("print")!=null){
					m_print=req.getParameter("print");
				}
				
				//String m_document_code	=req.getParameter("document_code");	
				//String m_print=req.getParameter("print");
				
				//rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MON-YYYY') FROM DUAL ");
				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'YYYY-MM-DD') FROM DUAL ");
				
				boolean more = rs.next();
				if(more){
					m_Letter_date=rs.getString(1);
				}
			
				//--Close the Result Set And Stateement--------			
				//rs.close();
				//stmt.close();
				
				
				
				out.println("<html><head>"); 
				//out.println("<meta http-equiv=\"content-type\" content=\"text-html; charset=utf-8\">");
				out.println("<title>Final Letter </title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				
				
				out.println("<script>");
				
				out.println("function get_annexure(m_application_no){");
				

				out.println("}"); 
				
				out.println("function get_vector_normal(http_response) {");
				//added by nuwan de silva on 05-09-07
				
				out.println("m_table.innerHTML=\"\" ");
				out.println("window.print();");
				
				out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Documents_Status?chksql=save_page&scr_name=AF_MK_APP_STATUS_APPROVE_3&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&document_code="+m_document_code+"\";"); 
				out.println(" window.location.href=m_url;"); 
				
				out.println("}");
				
				
				
				
				out.println("function save_data(){");
				//out.println("get_annexure('"+m_application_no+"')");
				out.println("m_table.innerHTML=\"\" ");
				out.println("window.print();");
				
				out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Documents_Status?chksql=save_page&scr_name=AF_MK_APP_STATUS_APPROVE_3&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&document_code="+m_document_code+"\";"); 
				out.println(" window.location.href=m_url;"); 
				
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
				
				out.println("</script>");
				
				out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");//add_button()
				out.println("<body bgcolor='white'><br>");
				out.println("<form name='Form1'>");
				
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>");
				
				out.println("<blockquote><font size=3><p style='text-align:left'>");					
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr><td width=\"100%\" class='rep-body1'></td></tr>");
				out.println("</table>");
				out.println("</font></p></blockquote>");	
			
				out.println("<blockquote><blockquote><font size=2><p style='text-align:justify' class='rep-body1'>");	
		
				out.println("<br><br>");

				
				out.println("<br>");
				

				out.println("<table border='0' width='80%' class='table'>"); 
				out.println("<tr><td width='95%' colspan='2' class='rep-body1' >");
			 	out.println("<font face='sandaru-n' size=5 color='blue'>");
				//out.println("<font face='FMBindumathi-x' size=5 color='blue'>");
		        out.println("<center><b>,shdmÈxÑ ;emEf,ks</b></center></font>"); 
	            out.println("</td></tr>"); 
				out.println("</table>");
				
				out.println("<br>");
				
				
				rs = stmt.executeQuery (" SELECT  "+
											"NVL(CLIENT_CODE,'-'),NVL(FULL_NAME,'-'), NVL(address1,'-'), NVL(address2,'-'),NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(city_code),'-'), NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO('"+m_application_no+"'),'-')   "+
											"FROM   "+
											""+m_schema_name+".AF_CO_MAS_CLIENT	  "+
										  "WHERE CLIENT_CODE = '"+m_client_code+"'  ");
				
				boolean more1 = rs.next();
				if(more1){
					m_full_name=rs.getString(2);
					m_add1=rs.getString(3);
					m_add2=rs.getString(4);
					m_city_name=rs.getString(5);
					m_vehicle_num = rs.getString(6);
				}
				//rs.close();
				//stmt.close();
				
				
				
				out.println("<table border='0' width='80%' class='table'>"); 
				 
				out.println("<tr><td width='95%' colspan='2' class='rep-body1' >");
		        out.println("<font size=5 face='sandaru-n' color='blue'>Èkh (  </font>");
				//out.println("<font size=5 face='sandaru-n' color='blue'> '''''''''''''''''''''''''''''''' </font></td><tr>");
				out.println("<font size=5 color='blue' > "+m_Letter_date+" </font></td><tr>"); 
				out.println("<tr><td></td></tr>");
				out.println("<tr><td></td></tr>");
				out.println("<tr><td></td></tr>");
				//out.println("<tr><td><font size=5 face='sandaru-n' color='blue'> ''''''''''''''''''''''''''''''''''''''''''' </font></td><tr>");
				//out.println("<tr><td><font size=5 face='sandaru-n' color='blue'> ''''''''''''''''''''''''''''''''''''''''''' </font></td><tr>");
				//out.println("<tr><td><font size=5 face='sandaru-n' color='blue'> ''''''''''''''''''''''''''''''''''''''''''' </font></td><tr>");
				//out.println("<tr><td><font size=5 face='sandaru-n' color='blue'> ''''''''''''''''''''''''''''''''''''''''''' </font></td><tr>");
				
				out.println("<tr><td><font size=5  color='blue'> "+m_full_name+" </font></td><tr>");
				out.println("<tr><td><font size=5  color='blue'> "+m_add1+" </font></td><tr>");
				out.println("<tr><td><font size=5  color='blue'> "+m_add2+" </font></td><tr>");
				out.println("<tr><td><font size=5  color='blue'> "+m_city_name+" </font></td><tr>");
				
				out.println("</table>");
				out.println("<br>");
				out.println("<br>");	
				
				out.println("<font size=5 face='sandaru-n' color='blue'>uy;auhdfKks $ uy;añhks</font>,");
				out.println("<br>");
				out.println("<br>");				
			
		        out.println("<u><center><font size=6 face='sandaru-n' color='blue'><b>.súiqu wjika lsÍfï ±kaùu</b></font></center></u>"); 
				
				rs = stmt.executeQuery (" SELECT "+m_schema_name+".AF_CO_GET_FINANCE_NO('"+m_application_no+"') FROM DUAL");
				
				boolean more4 = rs.next();
				if(more4){
					finance_no=rs.getString(1);
				}
				
				
				out.println("<br>");
				out.println("<font size=5 face='sandaru-n' color='blue'><b>l=,S iskaklalr .súiqï wxlh </b></font> <font size=5 color='blue'><b> : "+finance_no+" </b></font>"); // out.println("<font size=5 face='sandaru-n' color='blue'><b>l=,S iskaklalr .súiqï wxlh '''''''''''''''''''''''''''''''</b></font>");
				out.println("<br>");
				out.println("<font size=5 face='sandaru-n' color='blue'><b>jdyk wxlh </b></font><font size=5 color='blue'><b> : "+m_vehicle_num+" </b></font>");  // out.println("<font size=5 face='sandaru-n' color='blue'><b>jdyk wxlh ''''''''''''''''''''''''''''''''''''''''''''''''''''</b></font>");
				
				out.println("<table border='0' width='80%' class='table'>"); 
				out.println("<tr><td style='{font:11px;text-align:justify;}'><font size=5 face='sandaru-n' color='blue'> ñka fmr Tn fj; wm úiska ,sÅ; isys le|ùï iy b,a,Sï .Kkdjla uÕska okajd isáh uq;a fï olajd .súiqï");
				//out.println("m%ldrj l=,S jdßl f.jd mshùu Tn úiska meyer yer we;' j¾I '''''''''''''''''' jk Èkg Tn úiska wm iud.u");
				
				String last_due_date = "";
				
				rs=stmt.executeQuery( " "+
					" SELECT TO_CHAR(MAX(A.RENTAL_DATE),'YYYY-MM-DD') DUE_DATE "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A "+
					" WHERE A.APPLICATION_NO = "+m_schema_name+".AF_CO_GET_APPLICATION_NO('"+finance_no+"') "+	
					" ");
				
				if(rs.next()){
					last_due_date = rs.getString("DUE_DATE");
				}
				
				out.println("m%ldrj l=,S jdßl f.jd mshùu Tn úiska meyer yer we;' j¾I </font> <font size=5 color='blue'> "+m_Letter_date+" </font> <font size=5 face='sandaru-n' color='blue'> jk Èkg Tn úiska wm iud.u");
				
				//out.println("fj; whúh hq;= ysÕ l=,S uqo, re' ''''''''''''''''''''''''''''''''''''''''' la jk w;r by; i|yka lrk ,o .súiqï m%ldrj");
				
				rs = stmt.executeQuery (" "+
					" SELECT "+
					//" "+m_schema_name+".AF_GET_TOTAL_ARREARS('"+finance_no+"',TO_CHAR(SYSDATE,'DD-MM-YYYY'),'"+m_username+"') "+	
					" NVL("+m_schema_name+".AF_CO_GET_ARREAS_3("+m_schema_name+".AF_CO_GET_FINANCE_NO('"+m_application_no+"')),0) "+
					" FROM   DUAL ");
				
				if(rs.next()){
					m_bal_arrears = rs.getDouble(1);			
				}
				
				double m_tot_rent = 0;
				rs = stmt.executeQuery("  SELECT  "+
                    " SUM(A.GRENTAL_AMOUNT)  "+
                    " FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
                    " WHERE A.APPLICATION_NO=UPPER('"+m_application_no+"')   "+
                    " AND   A.APPLICATION_NO=B.APPLICATION_NO "+
                    " AND   A.PRO_INVOICE_NO=B.INVOICE_NO "+
                    " AND   A.PRICING_NO=B.PRICING_NO "+
                    " AND   B.ACTIVE_STATUS='Y' ");
                
                
                
                if(rs.next()){
                    m_tot_rent = rs.getDouble(1);
                }
				
				
				out.println("fj; whúh hq;= ysÕ l=,S uqo, re' </font> <font size=5 color='blue'> "+nf.format(m_bal_arrears)+" </font> <font size=5 face='sandaru-n' color='blue'> la jk w;r by; i|yka lrk ,o .súiqï m%ldrj");
				//out.println("j¾I '''''''''''''''''''''''''''''''''''''''' jk Èkg whùug we;s iïmQ¾K uqo,a m%udKh re' '''''''''''''''''''''''''''''''''' la fõ'</font>");
				out.println("j¾I </font> <font size=5 color='blue'> "+last_due_date+" </font> <font size=5 face='sandaru-n' color='blue'> jk Èkg whùug we;s iïmQ¾K uqo,a m%udKh re' </font> <font size=5 color='blue'> "+nf.format(m_tot_rent)+" </font> <font size=5 face='sandaru-n' color='blue'> la fõ'</font>");
				out.println("</td></tr>");
				
				//out.println("<tr><td style='{font:11px;text-align:justify;}'><font size=5 face='sandaru-n' color='blue'>ta wkqj tlS re' '''''''''''''''''''''''''''''''' la jQ ysÕ l=,S uqo, wo Èk isg Èk 14 la we;=,; fyda Bg fmr f.jd mshjk");
				out.println("<tr><td style='{font:11px;text-align:justify;}'><font size=5 face='sandaru-n' color='blue'>ta wkqj tlS re' </font> <font size=5 color='blue'> "+nf.format(m_bal_arrears)+" </font> <font size=5 face='sandaru-n' color='blue'> la jQ ysÕ l=,S uqo, wo Èk isg Èk 14 la we;=,; fyda Bg fmr f.jd mshjk");
				out.println("f,ig fuhska Tn fj;ska b,a,d isákq ,efí'</font>");
				out.println("</td></tr>");

				out.println("<tr><td style='{font:11px;text-align:justify;}'><font size=5 face='sandaru-n' color='blue'>hï fyhlska Tn úiska tu ysÕ uqo, f.ùu meyer yeßhfyd;a wm úiska fuu l=,S iskaklalr .súiqu wjika"+
							"lsÍug lghq;= lrkq ,nk w;r by; i|yka lrk ,o wm fj;ska Tn fj; l=,shg ,nd fok ,o jdykh jydu"+
							"ls%hd;aul jk mßÈ rdc.sßh</font>,<font size=5 face='sandaru-n' color='blue'> nq;a.uqj mdr</font>,<font size=5 face='sandaru-n' color='blue'> fkd' 100 orK ia:dfkhys msysá wmf.a m%Odk ld¾hd,h fj; fyda"+
							"YdLd ld¾hd,hla fj; idudkH ld¾hd, fõ,djka ;=,§ Ndr fok f,i fuhska b,a,d isákq ,efí'</td></tr>");

	
				out.println("<tr><td style='{font:11px;text-align:justify;}'><font size=5 face='sandaru-n' color='blue'>hï fyhlska Tn by;ska lrk ,o b,a,Sug tlÕ ùug wmfydi;a jqj fyd;a wmf.a whs;sjdislï wdrlaCId lr");
				out.println(".ekSu i|yd wjYH kS;suh mshjr .ekSug isÿjkq we;s njg Tng ;j ÿrg;a fuhska okajd isákq ,efí'");
				out.println("</td></tr>");
				
				
				out.println("<tr><td><font size=5 face='sandaru-n' color='blue'>fuhg úYajdiS<font>,</td></tr>");

	
				out.println("<tr><td></td></tr>");
				out.println("<tr><td></td></tr>");
				out.println("<tr><td></td></tr>");
		
				out.println("<tr><td><font size=5 face='sandaru-n' color='blue'>l,uKdlre</font></td></tr>");
				out.println("<tr><td><b><font size=5 face='sandaru-n' color='blue'>,laforK bkafjiaÜukaÜia ,sñgâ</font></td></tr>");
				out.println("<tr><td></td></tr>");
				out.println("<tr><td><font size=5 face='sandaru-n' color='blue'>msgm;a ( m,uq wemlre </font></td></tr><tr><td><font size=5 face='sandaru-n' color='blue'> fojk wemlre</font></td></tr>");
				//out.println("<tr><td><font size=5 face='sandaru-n'></td></font></td></tr>");
				out.println("</table>");
				
	
	
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				
			}
			
			
			
			out.flush();
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
			if(rs!=null){try{rs.close();  }catch(Exception e){}}
			if(stmt!=null){try{stmt.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}