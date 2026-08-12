
	// CREATED BY Minal ON 06-01-2015 for #15195

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_CR_PRO_Document_sinhala_letter_5 extends javax.servlet.http.HttpServlet { 
	
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
				
				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MON-YYYY') FROM DUAL ");
				
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
			 	out.println("<font face='sandaru-n' size=5 color='red'>");
		        out.println("<center><b>,shdmÈxÑ ;emEf,ks</b></center></font>"); 
	            out.println("</td></tr>"); 
				out.println("</table>");
				
				out.println("<br>");
				
				out.println("<table border='0' width='80%' class='table'>"); 
				 
				out.println("<tr><td width='95%' colspan='2' class='rep-body1' >");
		        out.println("<font size=5 face='sandaru-n' color='red'>Èkh (  </font>");
				
				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'YYYY-MM-DD') FROM DUAL ");

				if(rs.next()){
					m_Letter_date=rs.getString(1);
				}
				
				out.println("<font size=5 color='red' > "+m_Letter_date+" </font></td><tr>");  //out.println("<font size=5 face='sandaru-n' color='red'> '''''''''''''''''''''''''''''''' </font></td><tr>");
				out.println("<tr><td></td></tr>");
				out.println("<tr><td></td></tr>");
				out.println("<tr><td></td></tr>");
				
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
				
				out.println("<tr><td><font size=5  color='red'> "+m_full_name+" </font></td><tr>");
				out.println("<tr><td><font size=5  color='red'> "+m_add1+" </font></td><tr>");
				out.println("<tr><td><font size=5  color='red'> "+m_add2+" </font></td><tr>");
				out.println("<tr><td><font size=5  color='red'> "+m_city_name+" </font></td><tr>");
				
				/*
				out.println("<tr><td><font size=5 face='sandaru-n' color='red'> ''''''''''''''''''''''''''''''''''''''''''' </font></td><tr>");
				out.println("<tr><td><font size=5 face='sandaru-n' color='red'> ''''''''''''''''''''''''''''''''''''''''''' </font></td><tr>");
				out.println("<tr><td><font size=5 face='sandaru-n' color='red'> ''''''''''''''''''''''''''''''''''''''''''' </font></td><tr>");
				out.println("<tr><td><font size=5 face='sandaru-n' color='red'> ''''''''''''''''''''''''''''''''''''''''''' </font></td><tr>");
				*/
				out.println("</table>");
				out.println("<br>");

				out.println("<br>");	
				
				out.println("<font size=5 face='sandaru-n' color='red'>uy;auhdfKks $ uy;añhk</font><font size=5  color='red'>,</font>");
				out.println("<br>");
				out.println("<br>");				
			
		        out.println("<u><center><font size=6 face='sandaru-n' color='red'><b>.súiqu wjika lsÍu </b></font></center></u>"); 
				
				rs = stmt.executeQuery (" SELECT "+m_schema_name+".AF_CO_GET_FINANCE_NO('"+m_application_no+"') FROM DUAL");
				
				boolean more4 = rs.next();
				if(more4){
					finance_no=rs.getString(1);
				}
				
				out.println("<br>");
				out.println("<font size=5 face='sandaru-n' color='red'><b>l=,S iskaklalr .súiqï wxlh </b></font> <font size=5 color='red'><b> : "+finance_no+" </b></font>"); // out.println("<font size=5 face='sandaru-n' color='red'><b>l=,S iskaklalr .súiqï wxlh '''''''''''''''''''''''''''''''</b></font>");
				out.println("<br>");
				out.println("<font size=5 face='sandaru-n' color='red'><b>jdyk wxlh </b></font><font size=5 color='red'><b> : "+m_vehicle_num+" </b></font>"); // out.println("<font size=5 face='sandaru-n' color='red'><b>jdyk wxlh ''''''''''''''''''''''''''''''''''''''''''''''''''''</b></font>");
				
				out.println("<table border='0' width='80%' class='table'>"); 
				out.println("<tr><td style='{font:11px;text-align:justify;}'><font size=5 face='sandaru-n' color='red'> wm úiska Tn fj; lrk ,o isys le|ùï ,sms iy '''''''''''''''''''''''' Èke;s 1982 wxl 29 orK mdßfNda.sl Kh"+
							"mkf;a úê úOdk m%ldrj Tn fj; tjk ,o .súiqu wjika lrkq ,nk njg lrk ,o ±kaùu yd nef|a' ta wkqj"+
							//"Tn fj; tlS ±kaùu tjd ;sìh§;a ta ms#3525;sn|j i,ld ne,Sulska f;drj wod< uqo,a f.ùug Tn fï olajd"+
							"Tn fj; tlS ±kaùu tjd ;sìh§;a ta ms #3525; sn|j i,ld ne,Sulska f;drj wod< uqo,a f.ùug Tn fï olajd"+
							"wfmdfydi;aù we;s nj lK.dgqfjka i|yka lruq'</font></td></tr>");

				
				rs = stmt.executeQuery (" "+
					" SELECT "+
					//" "+m_schema_name+".AF_GET_TOTAL_ARREARS('"+finance_no+"',TO_CHAR(SYSDATE,'DD-MM-YYYY'),'"+m_username+"') "+
					" NVL("+m_schema_name+".AF_CO_GET_ARREAS_3("+m_schema_name+".AF_CO_GET_FINANCE_NO('"+m_application_no+"')),0) "+
					" FROM   DUAL ");
				
				if(rs.next()){
					m_bal_arrears = rs.getDouble(1);			
				}
				
				out.println("<tr><td style='{font:11px;text-align:justify;}'><font size=5 face='sandaru-n' color='red'>ta wkqj by; lS 1982 wxl 29 orK mdßfNda.sl Kh mkf;a 12 jk j.ka;sfha úê úOdkhka W,a,x>kh lr we;s"+
							"neúka tu mkf;a 18 jk j.ka;sfha i|yka úê úOdkhka m%ldrj tlS .súiqu fuhska wjika lrk ,o nj Tn fj;"+
							"okajd isáuq' ;jo wm úiska Tn fj; l=,shg ,ndfok ,o jdykh ;j ÿrg;a wmf.a wkque;shlska iy"+
							"wjirhlska f;drj Tn ika;lfha mj;akd neúka lreKdlr wod, jdykh jydu ls%hd;aul jk mßÈ rdc.sßh"+
							"nq;a.uqj mdr</font>,<font size=5 face='sandaru-n' color='red'> fkd' 100 orK ia:dkfha msysá wmf.a m%Odk ld¾hd,h fj; fyda YdLd ld¾hd,hla fj; idudkH"+
							//"ld¾hd, fõ,djka ;=,§ Ndr fok f,i fyda whùug we;s iïmQ¾K uqo,a m%udKh jk re' ''''''''''''''''''''''''''''' l"+
							"ld¾hd, fõ,djka ;=,§ Ndr fok f,i fyda whùug we;s iïmQ¾K uqo,a m%udKh jk re' </font> <font size=5 color='red'> "+nf.format(m_bal_arrears)+" </font> <font size=5 face='sandaru-n' color='red'> l"+
							"uqo, iy Tn fj;ska wm fj; wh úh hq;=j we;s wfkl=;a .dia;= o we;=¿j tljr f.jd ksu lrk f,i fuhska"+
							"Tn fj;ska b,a,d isákq ,efí'</font></td></tr>");

	
				out.println("<tr><td style='{font:11px;text-align:justify;}'><font size=5 face='sandaru-n' color='red'>hï fyhlska Tn úiska tfia lghq;= lsÍug wfmdfydi;a jqjfyd;a wm f.a"+
							"whs;sjdislï wdrCId lr .ekSu"+
							"fjkqfjka Tng tfysj kvq mjrk f,ig wm f.a kS;s{hka fj; Wmfoia §ug isÿjk nj lreKdfjka i,lkak'</font></td></tr>");
				
				
				
				out.println("<tr><td><font size=5 face='sandaru-n' color='red'>fuhg úYajdiS<font>,</td></tr>");
				
				out.println("<tr><td></td></tr>");
				out.println("<tr><td></td></tr>");
				out.println("<tr><td></td></tr>");
		
				out.println("<tr><td><font size=5 face='sandaru-n' color='red'>l,uKdlre</font></td></tr>");
				out.println("<tr><td><b><font size=5 face='sandaru-n' color='red'>,laforK bkafjiaÜukaÜia ,sñgâ</font></td></tr>");
				out.println("<tr><td></td></tr>");
				out.println("<tr><td><font size=5 face='sandaru-n' color='red'>msgm;a ( m,uq wemlre </font></td></tr>");
				out.println("<tr><td></td></tr>");
				out.println("<tr><td><font size=5 face='sandaru-n' color='red'>fojk wemlre</font></td></tr>");
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