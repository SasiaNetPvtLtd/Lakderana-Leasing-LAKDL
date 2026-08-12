//Created by Minal for #15195 on 05-01-2014


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_CR_PRO_Document_sinhala_letter_7 extends javax.servlet.http.HttpServlet { 
	
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
			
			String part1 = ""; // 004
			String part2 = ""; 
			String m_full_name="";
			String m_add1="";
			String m_add2="";
			String m_city_name="";
			String m_vehicle_num="";
			String arreas_period="";
			
			String finance_no = "";
						double tot_arrears =0.00;
		    double m_rental_amt  =0.00;
			double m_rental_amt_future  =0.00;
			double m_bal_arrears  =0.00;
			double m_odi_amt     =0.00;
			double m_other_amt   =0.00;
			double m_other_amt_future   =0.00;
			double m_total_amt   =0.00;
			double m_total_amt_future =0.00;
			double m_odi_amt_future=0.00;
			double m_excess_receipt_amount =0.00; 

			
			
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
				
				
				//=====================================================================
				
				out.println("<html><head>"); 
			//	out.println("<meta http-equiv=\"content-type\" content=\"text-html; charset=utf-8\">");
				out.println("<title>Reminder </title></head>");
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
				out.println("<tr><td width=\"100%\" class='rep-body1'><b></b></td></tr>");
				out.println("</table>");
				out.println("</font></p></blockquote>");	
				//out.println("<br><br><br><br><br>");
				//out.println("<br><br><br><br><br><br><br><br>");
				
				out.println("<blockquote><blockquote><font size=2><p style='text-align:justify' class='rep-body1'>");	
				/*
				out.println("<table border='0' width='80%' class='table'>"); 		
				out.println("<tr><td width='*%' class='rep-body1' ><font size=2><b>"+m_Letter_date+"</td></tr>");
				out.println("</table>");
				*/
				//================================================================================
				
				
				//================================================================================
				
				out.println("<br><br>");

				
				out.println("<br>");
				
									
				out.println("<table width='100%' class='table'>"); 
				out.println("<tr><td width=\"100%\" class='rep-body1' ><font size='5' color='blue'> Dear Sir / Madam </font></td>");
				out.println("<td width=\"100%\" class='rep-body1'><font size='5' color='blue'>Date</font></td></tr>");
				out.println("</table>");
	
								
				out.println("<table  width='100%' class='table'>"); 
				out.println("<tr><td width='100%' class='rep-body1' size='15'><font face=\"sandaru-n\" size=\"5\" color='blue'>ys;j;a uy;auhdfKks $ uy;añhks</font><font size='5' color='blue'>,</font></td>"); 
				out.println("<td width='100%' class='rep-body1' size='15'><font face=\"sandaru-n\" size=\"5\" color='blue'>Èkh</font></td></tr>");
				out.println("</table>");

				 
				
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr><td width=\"100%\" class='rep-body1' style='text-align:center' ><font size='15' color='blue'><b>Reminder</b></font></td></tr>");
				out.println("<tr><td width=\"100%\" class='rep-body1' style='text-align:center' ><font face=\"sandaru-n\" size=\"5\" color='blue'><b>isysle|ùuhs</b></font></td></tr>");
				out.println("</table>");
			
				out.println("<br>");
				
				out.println("<table width='100%'  class='table'>"); 
				out.println("<tr><td width=\"100%\" class='rep-body1' style='text-align:justify' > <font size='5' color='blue'> We refer to the hire purchase agreement you have signed up with Lakderana Investments Ltd. You have  failed to settle the due amount on the scheduled date. The details of your arrears is listed below. </font> </td></tr>");
				out.println("<tr><td width=\"100%\" class='rep-body1' style='text-align:justify' ><font face=\"sandaru-n\" size=\"5\" color='blue'>wm wdh;kh iu. Tn we;slr.;a l=,S iskaklalr .súiqï m%ldrj kshñ; jdßl uqo, kshñ; Èkg f.ùug<br> wfmdfydi;a ù we;' ysÕ uqo,g wod, úia;rhka my; mßÈh</font><font size=\"5\" color='blue'>.</font></td></tr>");
				out.println("</table>");
				
				out.println("<br>");
				
				double installment_amount = 0;
				double arrears_amount = 0;
				
				rs = stmt.executeQuery (" SELECT "+m_schema_name+".AF_CO_GET_FINANCE_NO('"+m_application_no+"'), "+
											" NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO('"+m_application_no+"'),'-'), "+
											" NVL("+m_schema_name+".AF_CO_GET_INSTALMENT_AMT('"+m_application_no+"'),0), "+
											" NVL("+m_schema_name+".AF_CO_GET_ARREAS_3("+m_schema_name+".AF_CO_GET_FINANCE_NO('"+m_application_no+"')),0) "+
										" FROM DUAL");
				
				boolean more4 = rs.next();
				if(more4){
					finance_no=rs.getString(1);
					m_vehicle_num=rs.getString(2);
					installment_amount=rs.getDouble(3);
					arrears_amount=rs.getDouble(4);
				}
				
				String mm_RENTAL_DATE = "";
				rs=stmt.executeQuery( " "+
					" SELECT TO_CHAR(MAX(A.RENTAL_DATE),'DD') DUE_DATE "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A "+
					" WHERE A.APPLICATION_NO = "+m_schema_name+".AF_CO_GET_APPLICATION_NO('"+finance_no+"') "+	
					" ");
				
				if(rs.next()){
					mm_RENTAL_DATE            = rs.getString("DUE_DATE");
				}
		
				out.println("<table align='left' width='100%'  class='table'>"); 
				out.println("<tr><td></td></tr>");
				
				/*
				out.println("<tr><td width=\"100%\" class='rep-body1' style='text-align:left' > <font size='5' color='blue'>Contract Number : </font> </td></tr>");
				out.println("<tr><td width=\"100%\" class='rep-body1' style='text-align:left' ><font face=\"sandaru-n\" size=\"5\" color=\"blue\">.súiqï wxlh #</font></td></tr>");
				out.println("<tr><td></td></tr>");
				out.println("<tr><td></td></tr>");
				out.println("<tr><td width=\"100%\" class='rep-body1' style='text-align:left' > <font size='5' color='blue'>Vehicle No : </font> </td></tr>");
				out.println("<tr><td width=\"100%\" class='rep-body1' style='text-align:left' ><font face=\"sandaru-n\" size=\"5\" color=\"blue\">jdyk wxlh #</font></td></tr>");
				out.println("<tr><td></td></tr>");
				out.println("<tr><td></td></tr>");
				out.println("<tr><td width=\"100%\" class='rep-body1' style='text-align:left' > <font size='5' color='blue'>Rental (Value) Rs.  </font> </td></tr>");
				out.println("<tr><td width=\"100%\" class='rep-body1' style='text-align:left' ><font face=\"sandaru-n\" size=\"5\" color=\"blue\">jdßlhl jákdlu re' </font></td></tr>");
				out.println("<tr><td></td></tr>");
				out.println("<tr><td></td></tr>");
				out.println("<tr><td width=\"100%\" class='rep-body1' style='text-align:left' > <font size='5' color='blue'>Arrears Rs.  </font> </td></tr>");
				out.println("<tr><td width=\"100%\" class='rep-body1' style='text-align:left' ><font face=\"sandaru-n\" size=\"5\" color=\"blue\">ysÕ uqo, re' </font></td></tr>");
				out.println("<tr><td></td></tr>");
				out.println("<tr><td></td></tr>");
				out.println("<tr><td width=\"100%\" class='rep-body1' style='text-align:left' > <font size='5' color='blue'>Monthly Rental Due Date : </font> </td></tr>");
				out.println("<tr><td width=\"100%\" class='rep-body1' style='text-align:left' ><font face=\"sandaru-n\" size=\"5\" color=\"blue\">udislj jdßl f.úh hq;= Èkh #</font></td></tr>");
				*/
				
				out.println("<tr><td width=\"100%\" class='rep-body1' style='text-align:left' > <font size='5' color='blue'>Contract Number : </font> <font size='5' color='blue'>"+finance_no+"</font> </td></tr>");
				out.println("<tr><td width=\"100%\" class='rep-body1' style='text-align:left' ><font face=\"sandaru-n\" size=\"5\" color=\"blue\">.súiqï wxlh #</font> <font size='5' color='blue'>"+finance_no+"</font> </td></tr>");
				out.println("<tr><td></td></tr>");
				out.println("<tr><td></td></tr>");
				out.println("<tr><td width=\"100%\" class='rep-body1' style='text-align:left' > <font size='5' color='blue'>Vehicle No : </font> <font size='5' color='blue'>"+m_vehicle_num+"</font> </td></tr>");
				out.println("<tr><td width=\"100%\" class='rep-body1' style='text-align:left' ><font face=\"sandaru-n\" size=\"5\" color=\"blue\">jdyk wxlh #</font> <font size='5' color='blue'>"+m_vehicle_num+"</font> </td></tr>");
				out.println("<tr><td></td></tr>");
				out.println("<tr><td></td></tr>");
				out.println("<tr><td width=\"100%\" class='rep-body1' style='text-align:left' > <font size='5' color='blue'>Rental (Value) Rs.  </font> <font size='5' color='blue'>"+nf.format(installment_amount)+"</font> </td></tr>");
				out.println("<tr><td width=\"100%\" class='rep-body1' style='text-align:left' ><font face=\"sandaru-n\" size=\"5\" color=\"blue\">jdßlhl jákdlu re'</font> <font size='5' color='blue'>"+nf.format(installment_amount)+"</font> </td></tr>");
				out.println("<tr><td></td></tr>");
				out.println("<tr><td></td></tr>");
				out.println("<tr><td width=\"100%\" class='rep-body1' style='text-align:left' > <font size='5' color='blue'>Arrears Rs.  </font> <font size='5' color='blue'>"+nf.format(arrears_amount)+"</font> </td></tr>");
				out.println("<tr><td width=\"100%\" class='rep-body1' style='text-align:left' ><font face=\"sandaru-n\" size=\"5\" color=\"blue\">ysÕ uqo, re' </font> <font size='5' color='blue'>"+nf.format(arrears_amount)+"</font> </td></tr>");
				out.println("<tr><td></td></tr>");
				out.println("<tr><td></td></tr>");
				out.println("<tr><td width=\"100%\" class='rep-body1' style='text-align:left' > <font size='5' color='blue'>Monthly Rental Due Date : </font> <font size='5' color='blue'>"+mm_RENTAL_DATE+"</font> </td></tr>");
				out.println("<tr><td width=\"100%\" class='rep-body1' style='text-align:left' ><font face=\"sandaru-n\" size=\"5\" color=\"blue\">udislj jdßl f.úh hq;= Èkh #</font> <font size='5' color='blue'>"+mm_RENTAL_DATE+"</font> </td></tr>");
				
				out.println("<tr><td></td></tr>");
				out.println("<tr><td></td></tr>");
				out.println("<tr><td width=\"100%\" class='rep-body1' style='text-align:justify' > <font size='5' color='blue'>However we believe that you will pay the arrears and the default interest immediately. We hope that you will"+
				 			"avoid the future default interest as agreed by paying this total amount at your earliest and the future rentals"+
							"on or before the due date.</font> </td></tr>");
				out.println("<tr><td width=\"100%\" class='rep-body1' style='text-align:justify' ><font face=\"sandaru-n\" size=\"5\" color=\"blue\">flfia fj;;a yels blaukska fuu ysÕ uqo, f.ùug Tn lghq;= lrk nj wmf.a úYajdihhs' tfia by; uq¿ uqo, läkñka"+
 							"f.ùfuka yd bÈß jdßl kshñ; Èk fyda Bg fmr f.ùfuka tlÕ jQ udisl ov fmd&#3525;sh Tn úiska u.yrjd .kq we;"+
							"ehs wm n,dfmdfrd;a;= fjuq'</font></td></tr>");
				out.println("<tr><td></td></tr>");
				out.println("<tr><td></td></tr>");
				out.println("<tr><td width=\"100%\" class='rep-body1'  > <font size=\"5\" color=\"blue\"> In the event you have paid the above mentioned amount please ignore this notice. </font> </td></tr>");
				out.println("<tr><td width=\"100%\" class='rep-body1'  ><font face=\"sandaru-n\" size=\"5\" color=\"blue\">fuys i|yka ysÕ uqo, ±kgu;a mshjd we;akï lreKdlr fuh fkdi,ld yßkak'</font></td></tr>");
				out.println("<tr><td></td></tr>");
				out.println("<tr><td width=\"100%\" class='rep-body1'  > <font size=\"5\" color=\"blue\"> Yours Faithfully </font> </td></tr>");
				out.println("<tr><td width=\"100%\" class='rep-body1'  ><font face=\"sandaru-n\" size=\"5\" color=\"blue\">fuhg úYajdiS</font></td></tr>");
				out.println("<tr><td></td></tr>");
				out.println("<tr><td></td></tr>");
				out.println("<tr><td></td></tr>");
				out.println("<tr><td></td></tr>");
				out.println("<tr><td width=\"100%\" class='rep-body1'  > <font size=\"5\" color=\"blue\"> Manager </font> </td></tr>");
				out.println("<tr><td width=\"100%\" class='rep-body1'  ><font face=\"sandaru-n\" size=\"5\" color=\"blue\">l,uKdlre</font></td></tr>");
				out.println("<tr><td width=\"100%\" class='rep-body1'  > <font size=\"5\" color=\"blue\"> Lakderana Investments Limited </font> </td></tr>");
				out.println("<tr><td width=\"100%\" class='rep-body1'  ><font face=\"sandaru-n\" size=\"5\" color=\"blue\">,laforK bkafjiaÜukaÜia ,sñgâ</font></td></tr>");
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