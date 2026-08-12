//ID         :
//SCREEN NAME: Collection first reminder for the pre printed documents.
//CREATED BY :Kanchana Karunarathna
//DATE/TIME  : 09-04-2014
//NOTES:

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_RE_PRO_Collection_NOT_Reminder extends javax.servlet.http.HttpServlet {
	
	public /*synchronized*/ void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		ServletOutputStream out = null;
		Connection conn = null;
		try {
			
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req);
			String m_html_client_url = m_sn_methods.html_client_url.trim();
			String m_class_url = m_sn_methods.servlet_client_url.trim() + ":" + m_sn_methods.client_t3_port.trim();
			String m_fschema_name = m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			out = res.getOutputStream();
			
			java.text.NumberFormat nf;
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			Statement stmt   = conn.createStatement();
			Statement stmt1  = conn.createStatement();
			Statement stmt2  = conn.createStatement();
			Statement stmt3  = conn.createStatement();
			Statement stmt4  = conn.createStatement();
			Statement stmt5  = conn.createStatement();
			Statement stmt6  = conn.createStatement();
			Statement stmt7  = conn.createStatement();
			Statement stmt8  = conn.createStatement();
			Statement stmt9  = conn.createStatement();
			Statement stmt10 = conn.createStatement();
			ResultSet rs,rs1,rs2,rs3,rs4,rs5,rs6,rs7,rs8,rs9,rs10;
			//Decaring variables
			
			String m_chksql = req.getParameter("chksql");
			
			String m_debtor_code="-",m_client_name="-",m_invoice_seq_no="-",m_batch_no="-",m_return_no="-"; //m_application_no
			String m_chq_no="-",m_chq_date="-",m_bank="-",m_branch="-",m_deadline_date="-",m_invoice_no="-",m_Letter_date="-";
			String m_contact_person="-",m_contact_desig="-",m_app_no="-",m_lease_date="-",m_client_no="-";
			String m_d_contact_person="-",m_d_contact_desig="-";
			String m_master_lease_no="-";
			String m_contract_client_code="-";
			String m_date ="";
			double m_chq_amount=0,m_tot_arrears=0,m_odi_arrears=0,m_pen_tot=0;
			
			String m_lakderana_name="";
			String m_lakderana_add1="";
			String m_lakderana_add2="";
			String m_lakderana_city_name="";
			String m_lakderana_tel_no="";
			String m_lakderana_fax_no="";
			String m_LAKDL_vat_no="",m_LAKDL_reg_no="";
			String m_vat_precentage="";
			
			String m_full_name="**";
			String m_add1="*";
			String m_add2="**";
			String m_city_name="**";
			String m_title="**";
			String m_client_type="**"; 
			String m_nic_no="**";
			
			String m_vehicle_no="-";
			String m_Ins_count="";
			String m_ent_date="";
			
			double mm_NET_RENTAL_AMOUNT=0.00;
			String mm_RENTAL_DATE="";
			String mm_VEHICLE_NO="";
			String mm_MATURITY_DATE="";
			String mm_NEXT_RENTAL_DATE="";
			String m_contact_full_name="";
			String m_guarantor_full_name="";
			String m_guarantor_full_name1="";
			String m_guarantor_full_name2="";
			String m_guarantor_full_name3="";
			String m_guarantor_full_name4="";
			String mm_GUARANTOR_CODE="";
			String mm_GUAR_ID="";
			String m_dummy_border = "0";//Make This Zero To Hide Borders which are not required
			
			String m_guranter0="",m_guranter1="",m_guranter2="",m_guranter3="",m_guranter4="";
			String m_gurantertitle0="",m_gurantertitle1="",m_gurantertitle2="",m_gurantertitle3="",m_gurantertitle4="";
			String m_guranteradd0="",m_guranteradd1="",m_guranteradd2="",m_guranteradd3="",m_guranteradd4="";
			String name_gurant="";
			String title_gurant="";
			String add1_gurant="";
			String add2_gurant="";
			String city_gurant="";
			String m_guranteradd0_2="",m_guranteradd1_2="",m_guranteradd2_2="",m_guranteradd3_2="",m_guranteradd4_2="";
			String m_guranter_city0="",m_guranter_city1="",m_guranter_city2="",m_guranter_city3="",m_guranter_city4="";
			
			String m_company_name="";
			String m_reg_no="";
			String m_company_det="";
			String m_com_contact="";

			
			if (m_chksql.trim().equals("print_not_final_letter")) {
				
				String m_application_no = req.getParameter("application_no");
				String m_client_code = req.getParameter("client_code");
				String m_document_code = req.getParameter("document_code");
				String vehicles_no="";
				String m_print=req.getParameter("print");
				String m_total_arrears=req.getParameter("total_arrears");
				
			//	out.println("m_finance_no="+m_finance_no);
			
			rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL ");								
			boolean more = rs.next();
			
			if(more){
				m_Letter_date=rs.getString(1);
			}
			
			rs = stmt.executeQuery(" SELECT "+
				" COMPANY_NAME, "+
				" ADDRESS1, "+
				" ADDRESS2, "+
				" CITY, "+
				" TEL_NO, "+
				" FAX_NO,  "+
				" VAT_RATE, "+
				" VAT_REG_NO "+
				" ,REG_NO"+
				" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ");
			
			more = rs.next();		
			
			if(more)
			{
				m_lakderana_name=rs.getString(1);
				m_lakderana_add1=rs.getString(2);
				m_lakderana_add2=rs.getString(3);
				m_lakderana_city_name=rs.getString(4);
				m_lakderana_tel_no=rs.getString(5);
				m_lakderana_fax_no=rs.getString(6);
				m_vat_precentage=rs.getString(7);			
				m_LAKDL_vat_no=rs.getString(8);	
				m_LAKDL_reg_no=rs.getString(9);	
			}
			
				//// -----Contract Client Details ------//
			
			String Contract_Client_Data =" SELECT CLIENT_CODE"+
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
				" WHERE FINANCE_NO='"+m_application_no+"' ";
			
		//	System.out.println("sql Contract_Client_Data ==> "+Contract_Client_Data);
			rs7 = stmt7.executeQuery(Contract_Client_Data);
			
			more = rs7.next();	
			
			if(more){	
				m_contract_client_code=rs7.getString(1);
				
			}
			
			
			//----- Client Details ------//
			
			String Client_Data=" SELECT  "+
				" 'CLIENT', "+ //1
				" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(TITLE) || '. ' || UPPER(INITIALS)|| ' ' || INITCAP(SURNAME),'C',/*'MESS' || '. '  || */INITCAP(FULL_NAME)),' ') ,   "+ //2
				" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(ADDRESS1),'C',INITCAP(REGISTERED_ADDRESS1)),' '),  "+ //3
				" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(ADDRESS2),'C',INITCAP(REGISTERED_ADDRESS2)),' ') , "+ //4
				" NVL(INITCAP("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),' '),  "+ //5
				" NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,  'C',BUSINESS_CERTIFICATE_NO),'-'),   "+ //6
				" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(FULL_NAME),'C',INITCAP(FULL_NAME)),' ')    "+ //7
				" FROM "+m_schema_name+".AF_CO_MAS_CLIENT  "+
				" WHERE   CLIENT_CODE =UPPER('"+m_contract_client_code+"') ";
			
			//System.out.println("sql Client_Data ==> "+Client_Data);
			out.println	("<!sql Client_Data -- "+Client_Data+" -->");
			
			rs2 = stmt2.executeQuery(Client_Data);
			
			more = rs2.next();		
			
			if(more){	
				m_full_name=rs2.getString(2);
				m_add1=rs2.getString(3);
				m_add2=rs2.getString(4);
				m_city_name=rs2.getString(5);
				m_nic_no=rs2.getString(6);
			}
			
			
			
			
			String m_designation = "";
			String m_tel_no= "";
			
			String Client_Name=" SELECT  "+
				" 'CLIENT', "+ //1
				" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(TITLE) || '. ' || UPPER(INITIALS)|| ' ' || INITCAP(SURNAME),'C',/*'MESS' || '. '  || */INITCAP(FULL_NAME)),' ') ,   "+ //2
				" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(ADDRESS1),'C',INITCAP(REGISTERED_ADDRESS1)),' '),  "+ //3
				" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(ADDRESS2),'C',INITCAP(REGISTERED_ADDRESS2)),' ') , "+ //4
				" NVL(INITCAP("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),' '),  "+ //5
				" NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,  'C',BUSINESS_CERTIFICATE_NO),'-'),   "+ //6
				" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(FULL_NAME),'C',INITCAP(FULL_NAME)),' ') ,   "+ //7
				" DESIGNATION,  "+ // 8
				" NVL(MOBILE_NO,TEL_NO) "+
				" FROM "+m_schema_name+".AF_CO_MAS_CLIENT  "+ // 9
				" WHERE   CLIENT_CODE =UPPER('"+m_contract_client_code+"') ";
			
			System.out.println("sql Client_Data ==> "+Client_Name);
			rs8 = stmt8.executeQuery(Client_Name);
			
			more = rs8.next();		
			
			if(more){	
				m_contact_full_name=rs8.getString(2);
				m_designation=rs8.getString(8);
				m_tel_no=rs8.getString(9);
				
			}
			
			
			//// ---- End Contact Client Detail-----///
			
			
			
			String Agree_Data =" SELECT "+
				" A.APPLICATION_NO, "+
				"  NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO(A.APPLICATION_NO),'-') "+
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A"+
				" WHERE A.FINANCE_NO='"+m_application_no+"' ";
			
			
			//System.out.println("sql Agree_Data ==> "+Agree_Data);
			rs3 = stmt3.executeQuery(Agree_Data);
			
			more = rs3.next();		
			
			if(more){	
				m_app_no=rs3.getString(1);
				m_vehicle_no=rs3.getString(2);
				
			}
			
			
			rs3.close();
			
			//--------------------------------------------
			
			
			// -- Arrears Details - Start --
			
			double arr_amount = 0;
			
			
			String arrearsDetails =" SELECT "+
				" A.ARR_AMOUNT, "+
				" TO_CHAR(A.ENT_DATE,'DD-MM-YYYY') "+
				" FROM "+m_schema_name+".AF_CO_BULK_LETTER_CONTRACTS A"+
				" WHERE A.FINANCE_NO='"+m_application_no+"' ";
			
			rs3 = stmt3.executeQuery(arrearsDetails);
			//out.println("arrearsDetails="+arrearsDetails);
			more = rs3.next();
			
			if(more){	
				arr_amount=rs3.getDouble(1);
				m_ent_date=rs3.getString(2);
			}
			//out.println("m_ent_date="+m_ent_date);
			// -- Arrears Details - End --
			

				
			String Company_Details=" SELECT  "+
				"  UPPER(COMPANY_NAME), "+
				"  'Registration No : ' || REG_NO, "+
				"  INITCAP(NVL(UPPER(COMPANY_NAME),' '))||','||' No.'|| NVL(UPPER(ADDRESS1),' ')||', '||INITCAP(NVL(UPPER(ADDRESS2),' '))||', '||INITCAP(NVL(UPPER(CITY),' ')), 	"+
				" 'Tel : ' ||NVL(TEL_NO,' ')||' | Fax : '||NVL(FAX_NO,' ')||' | Email :'||NVL(EMAIL,' ')||' | Web :'||NVL(WEB,' ') "+
				" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ";
			
			//out.println("Company_Details="+Company_Details);
			rs10 = stmt10.executeQuery(Company_Details);
			
				more = rs10.next();
				
				if(more){	
					m_company_name=rs10.getString(1);
					m_reg_no=rs10.getString(2);
					m_company_det=rs10.getString(3);	
					m_com_contact=rs10.getString(4);
				}
			   
			    out.println("<blockquote><font size=2><p style='text-align:left'>");										
				out.println("<br>");
			
			    out.println("<HTML>");
				out.println("<HEAD>");
				out.println("<TITLE>Notice of Termination Final</TITLE>");
				out.println("</HEAD>");
				out.println("<link REL='STYLESHEET' HREF='" + m_html_client_url + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language1.2='JavaScript' src='" + m_html_client_url + "/validate.js'></SCRIPT>");
				out.println("<SCRIPT language1.2='JavaScript' src='" + m_html_client_url + "/leasing_drill_down.js'></SCRIPT>");
				out.println("<SCRIPT language1.2='JavaScript' src='" + m_html_client_url + "/ajax_data_gateway.js'></SCRIPT>");
				out.println("<link REL='STYLESHEET' HREF='" + m_html_client_url + "/css/le_final_reminder_alignment_1.css' TYPE=\"text/css\">");
				
				out.println("<SCRIPT language=\"JavaScript\">");					
		/*		out.println("function add_button(){");
				out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_window()\"></td></tr>';"); //onClick=\"print_data()\"
				out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
				out.println("m_writedata+'</table>';");
				out.println("}");*/
				
				out.println("function add_button(){");
				
				if (m_print.trim().equals("FALSE")) {
				out.println("m_table.innerHTML=\"\" ");
				}else if(m_print.trim().equals("TRUE")) {
				out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_window()\"></td></tr>';"); //onClick=\"print_data()\"
				out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
				out.println("m_writedata+'</table>';");
				}
				out.println("}");
				
			/*	out.println("function print_data(){");
				out.println("m_table.innerHTML=\"\" ");
		//		out.println("   m_url='" + m_class_url + "/" + m_fschema_name + "AF_RE_PRO_Collection_Final_Reminder?m_finance_no="+req.getParameter("finance_no")+"&application_no=" + req.getParameter("application_no") + "&document_code="+req.getParameter("document_code")+"&print=FALSE&client_code=" + req.getParameter("client_code") + "&client_type=" + req.getParameter("client_type") + "';");
				out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_Collection_First_Reminder?chksql=print_first_letter&finance_no="+m_finance_no+" \";");
				out.println("window.location.href=m_url;"); 
				out.println("window.print();");
				out.println("}"); */
				
				out.println("function save_window(){	");  
			//	out.println("before_submit();"); 
			    out.println(" m_table.innerHTML=\"\" ");
				out.println("window.print();");
				out.println("}"); 
				
				out.println("function before_submit(){ "); 
				out.println(" m_table.innerHTML=\"\" ");
				out.println(" var finance_no=document.getElementById('fin_no').value;	");				
			//	out.println("		if(confirm(\"Are you sure you want to print the first reminder letter for \"+m_finance_no+\"? \")){ "); 
				out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_Collection_First_Reminder_SAVE?finance_no=\"+finance_no+\" \";");
				out.println(" window.location.href=m_url;"); 
				out.println(" window.print();");
		    //	out.println("		}");
				out.println("		}");
				
				out.println("</SCRIPT>");	
				
				out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");	
				out.println("<body bgcolor='white'>");	
				out.println("<form name='Form1'>");
				
				out.println("<table width='100%' class='table' cellspacing='0'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>");	
				
				//out.println("<DIV class=\"not_reminder_div_1\" >"); 

				out.println("<table  width='90%' class='table' cellspacing='0'>"); 
				out.println("<tr> "); 
				out.println("<td><img src=\""+m_html_client_url+"/images/LAKDL.gif\" align='right'></td>"); //width=\"8\" height=\"8\"
				out.println("</tr> "); 
				out.println("</table>");
				
									
				out.println("<table border='0' width='80%' class='table'>"); 		
				out.println("<tr><td width='*%' class='rep-body' align='center' ><B>BY REGISTERED POST</B></tr>");
				out.println("</table>");
				
				out.println("<table border='0' width='90%' class='table'>"); 	
			    out.println("<font face=\"Times New Roman\">");
				out.println("<tr><td width='*%' class='rep-body' style='{font:13px;text-align:right;}'>"+m_Letter_date+"</td></tr>");
				out.println("</font>");
				out.println("</table><br>");
				
				out.println("<table border='0' width='80%' class='table'>"); 	
		        out.println("<font face=\"Times New Roman\">");
				out.println("<tr><td width='*%' class='rep-body' style='{font:13px;text-align:left;}'>"+m_full_name+"</td></tr>");
				out.println("<tr><td width='*%' class='rep-body' style='{font:13px;text-align:left;}'>"+m_add1+"</td></tr>");
				out.println("<tr><td width='*%' class='rep-body' style='{font:13px;text-align:left;}'>"+m_add2+"</td></tr>");
				out.println("<tr><td width='*%' class='rep-body' style='{font:13px;text-align:left;}'>"+m_city_name+"</td></tr>");
				out.println("</font>");
				out.println("</table>");
				out.println("<br>");
			//	out.println("<br>");
			//	out.println("<br>");
				
			/*	out.println("<table border='1' width='60%' class='table'> ");	
			    out.println("<tr ><th align='left'><font face=\"FMBindumathi\">Arrears of Installments </font>/<font face=\"Times New Roman\">ys. jdrsl</font></th><th align='left'>"+m_contact_full_name+"</th></tr>");
			    out.println("<tr ><th align='left'><font face=\"FMBindumathi\">Default amount </font>/<font face=\"Times New Roman\">m%udo fmd,sh ^re'&</font></th><th align='left'>"+m_application_no+"</th></tr>");
				out.println("<tr ><th align='left'><font face=\"FMBindumathi\">Total payable as at above date</font>/<font face=\"Times New Roman\">toskg f.jSug we;s uq,q ys. uqo, ^re'&</font></th><th align='left'>"+m_vehicle_no+"</th></tr>");
				out.println("</table><br>");
			*/
			    out.println("<table border='0' width='80%' class='table'> ");	
				
				out.println("<tr ><th align='left'><font face=\"Times New Roman\">HIRER'S NAME</font></th>");        
				out.println("<th align='left'><font face=\"Times New Roman\">AGREEMENT NO.</font></th>");
				out.println("<th align='left'><font face=\"Times New Roman\">VEHICLE NO.</font></th>");
				out.println("<th align='left'><font face=\"Times New Roman\">STATEMENT DATE</font></th>");
				out.println("<th align='left'><font face=\"Times New Roman\">TOTAL OUTSTANDING</font></th></tr>"); 
				
				out.println("<tr ><th align='left'><font face=\"FMBindumathi\">l=,S .eKqïlref.a ku</font></th>");
				out.println("<th align='left'><font face=\"FMBindumathi\">.súiqï wxlh</font></th>");
				out.println("<th align='left'><font face=\"FMBindumathi\">jdyk wxlh</font></th>");
				out.println("<th align='left'><font face=\"FMBindumathi\">m%ldYkfha Èkh</font></th>");
				out.println("<th align='left'><font face=\"FMBindumathi\">ysÕ uq¿ uqo,</font></th></tr>");
				
				out.println("<tr ><th align='left'><font face=\"Kalaham\">thlif nfhs;tdthsupd; ngau</font></th>");
				out.println("<th align='left'><font face=\"Kalaham\">cld;gbf;if ,y</font></th>");
				out.println("<th align='left'><font face=\"Kalaham\">thfdj;jpd; ,y</font></th>");
				out.println("<th align='left'><font face=\"Kalaham\">$w;W jpfjp</font></th>");
				out.println("<th align='left'><font face=\"Kalaham\">epYitapYs;s nkhj;j njhif</font></th></tr>");
				
				out.println("<tr ><th align='left'><font face=\"Times New Roman\">---------------------------------------------</font></th>");
				out.println("<th align='left'><font face=\"Times New Roman\">-------------------------</font></th>");
				out.println("<th align='left'><font face=\"Times New Roman\">-----------------------</font></th>");
				out.println("<th align='left'><font face=\"Times New Roman\">----------------------------</font></th>");
				out.println("<th align='left'><font face=\"Times New Roman\">--------------------------------------------</font></th></tr>");
				
				out.println("<tr ><th align='left'><font face=\"Times New Roman\">"+m_contact_full_name+"</font></th>");
				out.println("<th align='left'><font face=\"Times New Roman\">"+m_application_no+"</font></th>");
				out.println("<th align='left'><font face=\"Times New Roman\">"+m_vehicle_no+"</font></th>");
				//out.println("<th align='left'><font face=\"Times New Roman\">"+m_ent_date+"</font></th>");
				out.println("<th align='left'><font face=\"Times New Roman\">"+m_Letter_date+"</font></th>");
				//out.println("<th align='left'><font face=\"Times New Roman\">"+nf.format(arr_amount)+"</font></th></tr>");
				//out.println("<th align='left'><font face=\"Times New Roman\">"+nf.format(m_total_arrears)+"</font></th></tr>");
				out.println("<th align='left'><font face=\"Times New Roman\">"+m_total_arrears+"</font></th></tr>");
				out.println("</table><br>");
				
				out.println("<table border='0' width='90%' class='table'> ");
				out.println("<tr ><td width='*%' class='rep-body' style='{font:13px;text-align:justify;}'>");
				out.println("<p>");
				out.println("<font face=\"FMBindumathi\">");
				out.println("m%sh uy;auhdKks$uy;añhks</font></b>");
				out.println("</p>");	
				out.println("</table>");
				
				out.println("<table border='0' width='90%' class='table'> ");
				out.println("<tr ><td width='*%' class='rep-body' style='{font:13px;text-align:justify;}'>");
				out.println("<p>");
				out.println("<font face=\"FMBindumathi\">");
                out.println("<b><u>l=,S iskaklalr .súiqfï kshufhka$fldkafoais lv lsÍu fya;=fjka ixfYdaê; 1982 wxl 29 orK mdßfNda.sl Kh mkf;a"+ 
					        "18^2& j.ka;sh hgf;a l=,sh wjika lrkq ,nk njg jq ±kaùuhs'</u></b>");
				out.println("</p></font>");	
				out.println("</table>");
				
				out.println("<br>");
				out.println("<br>");
				
				out.println("<table border='0' width='90%' class='table'> ");
				out.println("<tr ><td width='*%' class='rep-body' style='{font:13px;text-align:justify;}'>");
				out.println("<p>");
				out.println("<font face=\"FMBindumathi\">");
                out.println("wms Tn fj; fuu.ska okajd isákafka Tn úiska '''''''''''''''''''''''''''''''''''''''''''''''' isÿ"+ 
					        "lsÍu u.ska by; lS l=,S iskaklalr .súiqfï úêúOdkhkag mgyeks jk whqßka l%shdlr we;s nj"+ 
							"iy$fyda .súiqfï m%ldYs; fldkafoais lv lrkq ,en we;s njhs' wms fuu.ska Tn fj; okajd isákafka fuh"+
							"l=,S iskaklalr .súiqfï jeo.;a fldkafoaishla lv lsßula jk njhs' tneúka tlS jdykh$WmlrKh"+
							"iïnkaOfhka isÿlr we;s jro ksjerÈ lsÍug jydu l%shd lrk f,i;a\" tlS ksjerÈ lsÍu iïnkaOfhka"+
						    "fuls ,smsh ,eî Èk 30 la we;=<; wm fj; ±kqï fok f,i;a fuhska oekqï fokq ,efí'");
				out.println("</p></font>");	
				out.println("</table>");
				
				out.println("<br>");
				out.println("<br>");
				
				out.println("<table border='0' width='90%' class='table'> ");
				out.println("<tr ><td width='*%' class='rep-body' style='{font:13px;text-align:justify;}'>");
				out.println("<p>");
				out.println("<font face=\"FMBindumathi\">");
                out.println("ta wkqj\" wm fuu.ska Tn fj; m%ldY lr isákafka fuu ,smsh ,eî Èk 30 la blau ùug fmr by; lreK  "+
					        "iïnkaOfhka m%;sl¾u ie,iSug Tn l%shd fkdlrkafka fyda fkdi,ldyßkafka kï\" tlS l=,S"+
					        "iskaklalr .súiqfï by; lS úêúOdkhkag wkqj l=,sh wjika lsÍu i|yd mshjr .ekSug wmg isÿjkq we;s "+
					        "njhs'  tneúka\"  jeäÿr  m%udohlska f;drj tlS lvlsßu h:dj;a lsßu i|yd lghq;= i,ik fuka wms   "+
					        "fuu.ska Tn fj; oekqï fouq'");
				out.println("</p></font>");	
				out.println("</table>");
				
				out.println("<br>");
				out.println("<br>");
				
				out.println("<table border='0' width='90%' class='table'> ");
				out.println("<tr ><td width='*%' class='rep-body' style='{font:13px;text-align:justify;}'>");
				out.println("<p>");
				out.println("<font face=\"FMBindumathi\">");
                out.println("wemlrejka jk Tn úiska tlS ld, iSudj ;= wod< l%shdj ksis mßos ksjerÈ lsÍug l=,S .eKqïlrej "+
					        "fmUùug lghq;= lrk f,io th tmßÈ isÿjk njg úuis,su;a ùuo Tn i;= j.lSula jk nj fuhska oekqï fouq'");
				out.println("</p></font>");	
				out.println("</table>");
				
				out.println("<br>");
				out.println("<br>");
				
				out.println("<table border='0' width='90%' class='table'> ");
				out.println("<tr ><td width='*%' class='rep-body' style='{font:13px;text-align:justify;}'>");
				out.println("<p>");
				out.println("<font face=\"FMBindumathi\">");
                out.println("fuhg úYajdiS\"");
				out.println("</p></font>");	
				out.println("</table>");
				
				out.println("<br>");
				out.println("<br>");
				
				out.println("<table border='0' width='90%' class='table'> ");
				out.println("<tr ><td width='*%' class='rep-body' style='{font:13px;text-align:justify;}'>");
				out.println("<p>");
				out.println("<font face=\"Times New Roman\">");
                out.println(".................................");
				out.println("</p></font>");	
				out.println("</table>");
				
                out.println("<table border='0' width='90%' class='table'> ");
				out.println("<tr ><td width='*%' class='rep-body' style='{font:13px;text-align:justify; font-weight: bold;}'>");
				out.println("<p>");
				out.println("<font face=\"FMBindumathi\">");
                out.println("l,ukdlre whlsßï"); //l<ukdlre 
				out.println("</p></font>");	
				out.println("</table>");
				
				//rs4=stmt4.executeQuery
				String Guranter_count=" SELECT "+
					"  INITCAP(UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(GUARANTOR_CODE))),  "+
					"  INITCAP(NVL("+m_schema_name+".AF_CO_GET_CLIENT_ADD1(GUARANTOR_CODE),' ')),"+
					"  INITCAP(NVL("+m_schema_name+".AF_CO_GET_CLIENT_ADD2(GUARANTOR_CODE),' ')),"+
					"  INITCAP(NVL("+m_schema_name+".AF_CO_GET_CLI_CITY_NAME(GUARANTOR_CODE),' ')), "+
					"  INITCAP(NVL("+m_schema_name+".AF_CO_GET_CLIENT_TITLE(GUARANTOR_CODE),' ')) "+ 
					" FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR  "+
					" WHERE UPPER(APPLICATION_NO)=UPPER('"+m_app_no+"')  "+
					" ORDER BY GUAR_ID,GUARANTOR_CODE "+	
					" ";
				
				rs4=stmt4.executeQuery(Guranter_count);	
				//out.println("Guranter_coun="+Guranter_count);
				boolean more_gurant =rs4.next();
				
				int count_gurant=0;
				while (more_gurant){
					if(count_gurant==0){
						m_guranter0=rs4.getString(1);
						m_guranteradd0=rs4.getString(2);
						m_guranteradd0_2=rs4.getString(3);
						m_guranter_city0=rs4.getString(4);
						m_gurantertitle0=rs4.getString(5);
					}
					else if(count_gurant==1){   
						m_guranter1=rs4.getString(1);
						m_guranteradd1=rs4.getString(2);
						m_guranteradd1_2=rs4.getString(3);
						m_guranter_city1=rs4.getString(4);
						m_gurantertitle1=rs4.getString(5);
					}
					else if(count_gurant==2){   
						m_guranter2=rs4.getString(1);
						m_guranteradd2=rs4.getString(2);
						m_guranteradd2_2=rs4.getString(3);
						m_guranter_city2=rs4.getString(4);
						m_gurantertitle2=rs4.getString(5);
					}
					else if(count_gurant==3){   
						m_guranter3=rs4.getString(1);
						m_guranteradd3=rs4.getString(2);
						m_guranteradd3_2=rs4.getString(3);
						m_guranter_city3=rs4.getString(4);
						m_gurantertitle3=rs4.getString(5);
					}
					else if(count_gurant==4){   
						m_guranter4=rs4.getString(1);
						m_guranteradd4=rs4.getString(2);
						m_guranteradd4_2=rs4.getString(3);
						m_guranter_city4=rs4.getString(4);
						m_gurantertitle4=rs4.getString(5);
					}
					more_gurant=rs4.next();
					count_gurant=count_gurant+1;
					
				}
				//out.println("1111111111count_gurant="+count_gurant);
				
				rs4.close();
				stmt4.close();
				
			/*	out.println("<table border='0' width='90%' class='table'> ");
				out.println("<tr ><td width='*%' class='rep-body' style='{font:13px;text-align:justify;}'>");
				out.println("<p>");
				out.println("<font face=\"FMBindumathi\">");
                out.println("l<ukdlre"); //l<ukdlre 
				out.println("</p></font>");	
				out.println("</table>");
				
            */
				
				    // Guaranter
				
				  String sql_gur=" SELECT "+
					"  A.APPLICATION_NO, "+
					"  A.GUARANTOR_CODE, "+
					"  INITCAP("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.GUARANTOR_CODE)), "+//INITCAP by Lalanka on 05-6-2009
					"  INITCAP(NVL(B.ADDRESS1,'-')), "+//INITCAP by Lalanka on 05-6-2009
					"  INITCAP(NVL(B.ADDRESS2,'-')), "+//INITCAP by Lalanka on 05-6-2009
					"  INITCAP(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(B.CITY_CODE),'-')), "+
					"  NVL(DECODE(B.CLIENT_TYPE,'I',INITCAP(B.TITLE)||'.','C',''),' ') "+	//added by madhawa 2012-01-12
					" FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
					" WHERE A.APPLICATION_NO='"+m_app_no+"' AND A.GUARANTOR_CODE=B.CLIENT_CODE ORDER BY A.GUAR_ID,A.GUARANTOR_CODE ";
				//out.println(sql_gur);
				  out.println	("<!--Guaranter "+sql_gur+" -->");
				
				Vector gur_name=new Vector();
				Vector gur_add1=new Vector();
				Vector gur_add2=new Vector();
				Vector gur_city=new Vector();//added 2012-03-19
				
				rs1 = stmt1.executeQuery (sql_gur);
				
				out.println("<table border='0' width='90%' class='table'> ");
				out.println("<tr ><td width='*%' class='rep-body' style='{font:13px;text-align:justify;}'>");
				out.println("<p>");
				out.println("<font face=\"FMBindumathi\">");
                out.println("msgm;a - wemlrejka");
				out.println("</p></font>");	
				out.println("</table>");
				
				
			//	int g_count=0;
			//	int i=0,j=1;
				
				int count=0;
				int i=0,j=1,x=2;
				int m=2,n=3;
				int k=2,l=3;
				
				while(rs1.next())
				{
					gur_name.addElement(rs1.getString(7)+rs1.getString(3));
					gur_add1.addElement(rs1.getString(4));
					gur_add2.addElement(rs1.getString(5));
					gur_city.addElement(rs1.getString(6));//2012-03-15
					count=count+1;
				}
				
				if(count==1){
					out.println("<br>");						
					out.println("<table border='"+m_dummy_border+"' width='90%' class='table'> ");	
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'> 1). "+gur_name.get(i).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'></td></tr>");
				//	out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'>"+gur_add1.get(i).toString()+"</td>");
				//	out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'></td></tr>");
				//	out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'>"+gur_add2.get(i).toString()+"</td>");
				//	out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'></td></tr>");
				//	out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'>"+gur_city.get(i).toString()+"</td>");
				//	out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'></td></tr>");
				//	out.println("</table>");
				}
				else if(count==2){
					out.println("<br>");						
                    out.println("<table border='"+m_dummy_border+"' width='90%' class='table'> ");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:11ptpt;text-align:justify;}'>1). "+gur_name.get(i).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:11pt;text-align:justify;}'></td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:11pt;text-align:justify;}'>2). "+gur_name.get(j).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:11pt;text-align:justify;}'></td></tr>");


					
					out.println("</table>"); 
					
				}
						else if(count==3){
						
				/*	out.println("<table border='"+m_dummy_border+"' width='90%' class='table'> ");
					
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>1). "+gur_name.get(i).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'></td></tr>");
					//out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'>"+gur_name.get(j).toString()+"</td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'>2). "+gur_name.get(j).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'></td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'>3). "+gur_name.get(k).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'></td></tr>"); */
					
					/*out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_name.get(j).toString()+"</td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_add1.get(i).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_add1.get(j).toString()+"</td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_add2.get(i).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_add2.get(j).toString()+"</td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_city.get(i).toString()+"</td>");  
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_city.get(j).toString()+"</td></tr>"); 
					
					out.println("<tr><td> &nbsp</td> </tr>"); 
					
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_name.get(k).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'></td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_add1.get(k).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'></td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_add2.get(k).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'></td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_city.get(k).toString()+"</td>");
			   	    out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'></td></tr>");
					
					
					out.println("</table>");	*/
					
			/*		out.println("<table border='"+m_dummy_border+"' width='90%' class='table'> ");	
					out.println("<tr><td width='30%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_name.get(i).toString()+"</td>");
					out.println("     <td width='30%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_name.get(j).toString()+"</td>");
					out.println("     <td width='30%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_name.get(x).toString()+"</td></tr>");
					out.println("<tr> <td width='30%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_add1.get(i).toString()+"</td>");
					out.println("     <td width='30%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_add1.get(j).toString()+"</td>");
					out.println("     <td width='30%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_add1.get(x).toString()+"</td></tr>");
					out.println("<tr> <td width='30%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_add2.get(i).toString()+"</td>");
					out.println("     <td width='30%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_add2.get(j).toString()+"</td>");
					out.println("     <td width='30%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_add2.get(x).toString()+"</td></tr>");
					out.println("<tr><td width='30%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_city.get(i).toString()+"</td>");  
					out.println("    <td width='30%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_city.get(j).toString()+"</td>"); 
					out.println("    <td width='30%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_city.get(x).toString()+"</td></tr>"); 
					out.println("</table>");
			*/		
				    out.println("<table border='"+m_dummy_border+"' width='90%' class='table'> ");
					
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:11ptpt;text-align:justify;}'>1). "+gur_name.get(i).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:11pt;text-align:justify;}'></td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:11pt;text-align:justify;}'>2). "+gur_name.get(j).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:11pt;text-align:justify;}'></td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:11pt;text-align:justify;}'>3). "+gur_name.get(m).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:11pt;text-align:justify;}'></td></tr>");

					
					out.println("</table>"); 
					
						
					}	
					else if(count!=0){
						
					out.println("<table border='"+m_dummy_border+"' width='90%' class='table'> ");
					
			/*		out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>1). "+gur_name.get(i).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'></td></tr>");
					//out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'>"+gur_name.get(j).toString()+"</td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'>2). "+gur_name.get(j).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'></td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'>3). "+gur_name.get(k).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'></td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'>4). "+gur_name.get(l).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'></td></tr>");
					/*out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_name.get(i).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_name.get(j).toString()+"</td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_add1.get(i).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_add1.get(j).toString()+"</td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_add2.get(i).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_add2.get(j).toString()+"</td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_city.get(i).toString()+"</td>");  
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_city.get(j).toString()+"</td></tr>"); 
					
					out.println("<tr><td> &nbsp</td> </tr>"); 
					
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_name.get(k).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_name.get(l).toString()+"</td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_add1.get(k).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_add1.get(l).toString()+"</td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_add2.get(k).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_add2.get(l).toString()+"</td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_city.get(k).toString()+"</td>");  
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_city.get(l).toString()+"</td></tr>");					
				*/
				//	out.println("</table>");
				
				    out.println("<table border='"+m_dummy_border+"' width='90%' class='table'> ");
					
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:11ptpt;text-align:justify;}'>1). "+gur_name.get(i).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:11pt;text-align:justify;}'></td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:11pt;text-align:justify;}'>2). "+gur_name.get(j).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:11pt;text-align:justify;}'></td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:11pt;text-align:justify;}'>3). "+gur_name.get(m).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:11pt;text-align:justify;}'></td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:11pt;text-align:justify;}'>4). "+gur_name.get(n).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:11pt;text-align:justify;}'></td></tr>");
					
					out.println("</table>"); 
					

					
					
			
				}
					
				//	out.println("</br>");		
				//	out.println("</br>");	
				//	out.println("</br>");	
				//	out.println("</br>");
				
					out.println("<table border='0' width='90%' class='table'>"); 	
			        out.println("<font face=\"Times New Roman\">");
				    out.println("<tr><td width='*%' class='rep-body' style='{font:13px;text-align:center;}'><u>"+m_company_name+"</u></td></tr>");
				    out.println("</font>");
				    out.println("</table>");
					
					out.println("<table border='0' width='90%' class='table'>"); 	
			        out.println("<font face=\"Times New Roman\">");
				    out.println("<tr><td width='*%' class='rep-body' style='{font:13px;text-align:center;}'><u>"+m_reg_no+"</u></td></tr>");
				    out.println("</font>");
				    out.println("</table>");
					
					out.println("<table border='0' width='90%' class='table'>"); 	
			        out.println("<font face=\"Times New Roman\">");
				    out.println("<tr><td width='*%' class='rep-body' style='{font:13px;text-align:center;}'><u>"+m_company_det+"</u></td></tr>");
				    out.println("</font>");
				    out.println("</table>");
					
					out.println("<table border='0' width='90%' class='table'>"); 	
			        out.println("<font face=\"Times New Roman\">");
				    out.println("<tr><td width='*%' class='rep-body' style='{font:13px;text-align:center;}'><u>"+m_com_contact+"</u></td></tr>");
				    out.println("</font>");
				    out.println("</table>");
			
				//out.println("count_gurant="+count_gurant);	
				if(count_gurant!=0){
					
					for(int a=0; a<count_gurant; a++ ){
						//out.println("<div class=\"break\">"); 
						//out.println("<div class=\"pagebreak\"> </div> ");
						//out.println("<div class=\"page-break\"></div>");
						// out.println("<div class=\"pagebreak\">"); 
						
						//out.println("<br><br><br>");
						out.println("<p style=\"page-break-before:always\"></p>");	//&nbsp
						
						out.println("<table  width='90%' class='table' cellspacing='0'>"); 
						out.println("<tr> "); 
						out.println("<td><img src=\""+m_html_client_url+"/images/LAKDL.gif\" align='right'></td>"); //width=\"8\" height=\"8\"
						out.println("</tr> "); 
						out.println("</table>");
					
						out.println("<table border='0' width='80%' class='table'>"); 		
						out.println("<tr><td width='*%' class='rep-body' align='center' ><B>BY REGISTERED POST</B></tr>");
						out.println("</table>");
						
						out.println("<table border='0' width='90%' class='table'>"); 	
					    out.println("<font face=\"Times New Roman\">");
						out.println("<tr><td width='*%' class='rep-body' style='{font:13px;text-align:right;}'>"+m_Letter_date+"</td></tr>");
						out.println("</font>");
						out.println("</table><br>");
				
					//================================================================================
						
						if(a==0){
							name_gurant=m_gurantertitle0+". "+m_guranter0;
							add1_gurant=m_guranteradd0;
							add2_gurant=m_guranteradd0_2;
							city_gurant=m_guranter_city0;
							title_gurant=m_gurantertitle0;
						}
						if(a==1){
							name_gurant=m_gurantertitle1+". "+m_guranter1;
							add1_gurant=m_guranteradd1;
							add2_gurant=m_guranteradd1_2;
							city_gurant=m_guranter_city1;
							title_gurant=m_gurantertitle1;
						}
						if(a==2){
							name_gurant=m_gurantertitle2+". "+m_guranter2;
							add1_gurant=m_guranteradd2;
							add2_gurant=m_guranteradd2_2;
							city_gurant=m_guranter_city2;
							title_gurant=m_gurantertitle2;
						}
						if(a==3){
							name_gurant=m_gurantertitle3+". "+m_guranter3;
							add1_gurant=m_guranteradd3;
							add2_gurant=m_guranteradd3_2;
							city_gurant=m_guranter_city3;
							title_gurant=m_gurantertitle3;
						}
						if(a==4){
							name_gurant=m_gurantertitle4+". "+m_guranter4;
							add1_gurant=m_guranteradd4;
							add2_gurant=m_guranteradd4_2;
							city_gurant=m_guranter_city4;
							title_gurant=m_gurantertitle4;
						}
						
						if(!name_gurant.equals(" "))
						{
							name_gurant=name_gurant+" ";
						}
						
						if(!add1_gurant.equals(" "))
						{
							add1_gurant=add1_gurant+" ";
						}
						if(!add2_gurant.equals(" "))
						{
							add2_gurant=add2_gurant+" "; 					
						}
				
				out.println("<table border='0' width='80%' class='table'>"); 	
		        out.println("<font face=\"Times New Roman\">");
		/*		out.println("<tr><td width='*%' class='rep-body' style='{font:13px;text-align:left;}'>"+m_full_name+"</td></tr>");
				out.println("<tr><td width='*%' class='rep-body' style='{font:13px;text-align:left;}'>"+m_add1+"</td></tr>");
				out.println("<tr><td width='*%' class='rep-body' style='{font:13px;text-align:left;}'>"+m_add2+"</td></tr>");
				out.println("<tr><td width='*%' class='rep-body' style='{font:13px;text-align:left;}'>"+m_city_name+"</td></tr>");
		*/		
		        out.println("<tr><td width='*%' class='rep-body' style='{font:13px;text-align:left;}'>"+name_gurant+"</td></tr>");
				out.println("<tr><td width='*%' class='rep-body' style='{font:13px;text-align:left;}'>"+add1_gurant+"</td></tr>");
				out.println("<tr><td width='*%' class='rep-body' style='{font:13px;text-align:left;}'>"+add2_gurant+"</td></tr>");
				out.println("<tr><td width='*%' class='rep-body' style='{font:13px;text-align:left;}'>"+city_gurant+"</td></tr>");
				out.println("</font>");
				out.println("</table>");
				out.println("<br>");
			//	out.println("<br>");
			//	out.println("<br>");
				
			/*	out.println("<table border='1' width='60%' class='table'> ");	
			    out.println("<tr ><th align='left'><font face=\"FMBindumathi\">Arrears of Installments </font>/<font face=\"Times New Roman\">ys. jdrsl</font></th><th align='left'>"+m_contact_full_name+"</th></tr>");
			    out.println("<tr ><th align='left'><font face=\"FMBindumathi\">Default amount </font>/<font face=\"Times New Roman\">m%udo fmd,sh ^re'&</font></th><th align='left'>"+m_application_no+"</th></tr>");
				out.println("<tr ><th align='left'><font face=\"FMBindumathi\">Total payable as at above date</font>/<font face=\"Times New Roman\">toskg f.jSug we;s uq,q ys. uqo, ^re'&</font></th><th align='left'>"+m_vehicle_no+"</th></tr>");
				out.println("</table><br>");
			*/
			    out.println("<table border='0' width='80%' class='table'> ");	
				
				out.println("<tr ><th align='left'><font face=\"Times New Roman\">HIRER'S NAME</font></th>");        
				out.println("<th align='left'><font face=\"Times New Roman\">AGREEMENT NO.</font></th>");
				out.println("<th align='left'><font face=\"Times New Roman\">VEHICLE NO.</font></th>");
				out.println("<th align='left'><font face=\"Times New Roman\">STATEMENT DATE</font></th>");
				out.println("<th align='left'><font face=\"Times New Roman\">TOTAL OUTSTANDING</font></th></tr>"); 
				
				out.println("<tr ><th align='left'><font face=\"FMBindumathi\">l=,S .eKqïlref.a ku</font></th>");
				out.println("<th align='left'><font face=\"FMBindumathi\">.súiqï wxlh</font></th>");
				out.println("<th align='left'><font face=\"FMBindumathi\">jdyk wxlh</font></th>");
				out.println("<th align='left'><font face=\"FMBindumathi\">m%ldYkfha Èkh</font></th>");
				out.println("<th align='left'><font face=\"FMBindumathi\">ysÕ uq¿ uqo,</font></th></tr>");
				
				out.println("<tr ><th align='left'><font face=\"Kalaham\">thlif nfhs;tdthsupd; ngau</font></th>");
				out.println("<th align='left'><font face=\"Kalaham\">cld;gbf;if ,y</font></th>");
				out.println("<th align='left'><font face=\"Kalaham\">thfdj;jpd; ,y</font></th>");
				out.println("<th align='left'><font face=\"Kalaham\">$w;W jpfjp</font></th>");
				out.println("<th align='left'><font face=\"Kalaham\">epYitapYs;s nkhj;j njhif</font></th></tr>");
				
				out.println("<tr ><th align='left'><font face=\"Times New Roman\">---------------------------------------------</font></th>");
				out.println("<th align='left'><font face=\"Times New Roman\">-------------------------</font></th>");
				out.println("<th align='left'><font face=\"Times New Roman\">-----------------------</font></th>");
				out.println("<th align='left'><font face=\"Times New Roman\">----------------------------</font></th>");
				out.println("<th align='left'><font face=\"Times New Roman\">--------------------------------------------</font></th></tr>");
				
				out.println("<tr ><th align='left'><font face=\"Times New Roman\">"+m_contact_full_name+"</font></th>");
				out.println("<th align='left'><font face=\"Times New Roman\">"+m_application_no+"</font></th>");
				out.println("<th align='left'><font face=\"Times New Roman\">"+m_vehicle_no+"</font></th>");
				//out.println("<th align='left'><font face=\"Times New Roman\">"+m_ent_date+"</font></th>");
				out.println("<th align='left'><font face=\"Times New Roman\">"+m_Letter_date+"</font></th>");
				//out.println("<th align='left'><font face=\"Times New Roman\">"+nf.format(arr_amount)+"</font></th></tr>");
				//out.println("<th align='left'><font face=\"Times New Roman\">"+nf.format(m_total_arrears)+"</font></th></tr>");
				out.println("<th align='left'><font face=\"Times New Roman\">"+m_total_arrears+"</font></th></tr>");
				out.println("</table><br>"); 
				
				out.println("<table border='0' width='90%' class='table'> ");
				out.println("<tr ><td width='*%' class='rep-body' style='{font:13px;text-align:justify;}'>");
				out.println("<p>");
				out.println("<font face=\"FMBindumathi\">");
				out.println("m%sh uy;auhdKks$uy;añhks</font></b>");
				out.println("</p>");	
				out.println("</table>");
				
				out.println("<table border='0' width='90%' class='table'> ");
				out.println("<tr ><td width='*%' class='rep-body' style='{font:13px;text-align:justify;}'>");
				out.println("<p>");
				out.println("<font face=\"FMBindumathi\">");
                out.println("<b><u>l=,S iskaklalr .súiqfï kshufhka$fldkafoais lv lsÍu fya;=fjka ixfYdaê; 1982 wxl 29 orK mdßfNda.sl Kh mkf;a"+ 
					        "18^2& j.ka;sh hgf;a l=,sh wjika lrkq ,nk njg jq ±kaùuhs'</u></b>");
				out.println("</p></font>");	
				out.println("</table>");
				
				out.println("<br>");
				out.println("<br>");
				
				out.println("<table border='0' width='90%' class='table'> ");
				out.println("<tr ><td width='*%' class='rep-body' style='{font:13px;text-align:justify;}'>");
				out.println("<p>");
				out.println("<font face=\"FMBindumathi\">");
                out.println("wms Tn fj; fuu.ska okajd isákafka Tn úiska '''''''''''''''''''''''''''''''''''''''''''''''' isÿ"+ 
					        "lsÍu u.ska by; lS l=,S iskaklalr .súiqfï úêúOdkhkag mgyeks jk whqßka l%shdlr we;s nj"+ 
							"iy$fyda .súiqfï m%ldYs; fldkafoais lv lrkq ,en we;s njhs' wms fuu.ska Tn fj; okajd isákafka fuh"+
							"l=,S iskaklalr .súiqfï jeo.;a fldkafoaishla lv lsßula jk njhs' tneúka tlS jdykh$WmlrKh"+
							"iïnkaOfhka isÿlr we;s jro ksjerÈ lsÍug jydu l%shd lrk f,i;a\" tlS ksjerÈ lsÍu iïnkaOfhka"+
						    "fuls ,smsh ,eî Èk 30 la we;=<; wm fj; ±kqï fok f,i;a fuhska oekqï fokq ,efí'");
				out.println("</p></font>");	
				out.println("</table>");
				
				out.println("<br>");
				out.println("<br>");
				
				out.println("<table border='0' width='90%' class='table'> ");
				out.println("<tr ><td width='*%' class='rep-body' style='{font:13px;text-align:justify;}'>");
				out.println("<p>");
				out.println("<font face=\"FMBindumathi\">");
                out.println("ta wkqj\" wm fuu.ska Tn fj; m%ldY lr isákafka fuu ,smsh ,eî Èk 30 la blau ùug fmr by; lreK  "+
					        "iïnkaOfhka m%;sl¾u ie,iSug Tn l%shd fkdlrkafka fyda fkdi,ldyßkafka kï\" tlS l=,S"+
					        "iskaklalr .súiqfï by; lS úêúOdkhkag wkqj l=,sh wjika lsÍu i|yd mshjr .ekSug wmg isÿjkq we;s "+
					        "njhs'  tneúka\"  jeäÿr  m%udohlska f;drj tlS lvlsßu h:dj;a lsßu i|yd lghq;= i,ik fuka wms   "+
					        "fuu.ska Tn fj; oekqï fouq'");
				out.println("</p></font>");	
				out.println("</table>");
				
		/*	    out.println("<font face=\"FMAbhaya\">");
                out.println("ta wkqj wm fuu.ska Tn fj; m%ldY lr isákafka fuu ,smsh ,eî Èk 30 la blau ùug fmr by; lreK iïnkaOfhka    "+
					        "m%;sl¾u ie,iSug Tn l%shd fkdlrkafka fyda fkdi,ldyßkafka kï tlS l=,Siskaklalr .súiqfï by; lS úêúOdkhkag "+
					        "wkqj l=,sh wjika lsÍu i|yd mshjr .ekSug wmg isÿjkq we;snjhs' tneúka jeäÿr m%udohlska f;drj tlS lvlsßu  "+
					        "njhs'  tneúka\"  jeäÿr  m%udohlska f;drj tlS lvlsßu h:dj;a lsßu i|yd lghq;= i,ik fuka wms              "+
					        "h:dj;a lsßu i|yd lghq;= i,ik fuka wms fuu.ska Tn fj; oekqï fouq' ");
				out.println("</p></font>");	
				out.println("</table>");
		*/		
				out.println("<br>");
				out.println("<br>");
				
				out.println("<table border='0' width='90%' class='table'> ");
				out.println("<tr ><td width='*%' class='rep-body' style='{font:13px;text-align:justify;}'>");
				out.println("<p>");
				out.println("<font face=\"FMBindumathi\">");
                out.println("wemlrejka jk Tn úiska tlS ld, iSudj ;= wod< l%shdj ksis mßos ksjerÈ lsÍug l=,S .eKqïlrej "+
					        "fmUùug lghq;= lrk f,io th tmßÈ isÿjk njg úuis,su;a ùuo Tn i;= j.lSula jk nj fuhska oekqï fouq'");
				out.println("</p></font>");	
				out.println("</table>");
				
				out.println("<br>");
				out.println("<br>");
				
				out.println("<table border='0' width='90%' class='table'> ");
				out.println("<tr ><td width='*%' class='rep-body' style='{font:13px;text-align:justify;}'>");
				out.println("<p>");
				out.println("<font face=\"FMBindumathi\">");
                out.println("fuhg úYajdiS\"");
				out.println("</p></font>");	
				out.println("</table>");
				
				out.println("<br>");
				out.println("<br>");
				
				out.println("<table border='0' width='90%' class='table'> ");
				out.println("<tr ><td width='*%' class='rep-body' style='{font:13px;text-align:justify;}'>");
				out.println("<p>");
				out.println("<font face=\"Times New Roman\">");
                out.println(".................................");
				out.println("</p></font>");	
				out.println("</table>");
				
                out.println("<table border='0' width='90%' class='table'> ");
				out.println("<tr ><td width='*%' class='rep-body' style='{font:13px;text-align:justify; font-weight: bold;}'>");
				out.println("<p>");
				out.println("<font face=\"FMBindumathi\">");
                out.println("l,ukdlre whlsßï"); //l<ukdlre 
				out.println("</p></font>");	
				out.println("</table>");
				
		/*		rs5=stmt5.executeQuery(" SELECT "+
					"  INITCAP(UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(GUARANTOR_CODE))),  "+
					"  INITCAP(NVL("+m_schema_name+".AF_CO_GET_CLIENT_ADD1(GUARANTOR_CODE),' ')),"+
					"  INITCAP(NVL("+m_schema_name+".AF_CO_GET_CLIENT_ADD2(GUARANTOR_CODE),' ')),"+
					"  INITCAP(NVL("+m_schema_name+".AF_CO_GET_CLI_CITY_NAME(GUARANTOR_CODE),' ')), "+
					"  INITCAP(NVL("+m_schema_name+".AF_CO_GET_CLIENT_TITLE(GUARANTOR_CODE),' ')) "+ 
					" FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR  "+
					" WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"')  "+
					" ORDER BY GUAR_ID "+	
					"  ");
				boolean more_gurant_1 =rs5.next();
				
				 count_gurant=0;
				while (more_gurant_1){
					if(count_gurant==0){
						m_guranter0=rs5.getString(1);
						m_guranteradd0=rs5.getString(2);
						m_guranteradd0_2=rs5.getString(3);
						m_guranter_city0=rs5.getString(4);
						m_gurantertitle0=rs5.getString(5);
					}
					else if(count_gurant==1){   
						m_guranter1=rs5.getString(1);
						m_guranteradd1=rs5.getString(2);
						m_guranteradd1_2=rs5.getString(3);
						m_guranter_city1=rs5.getString(4);
						m_gurantertitle1=rs5.getString(5);
					}
					else if(count_gurant==2){   
						m_guranter2=rs5.getString(1);
						m_guranteradd2=rs5.getString(2);
						m_guranteradd2_2=rs5.getString(3);
						m_guranter_city2=rs5.getString(4);
						m_gurantertitle2=rs5.getString(5);
					}
					else if(count_gurant==3){   
						m_guranter3=rs5.getString(1);
						m_guranteradd3=rs5.getString(2);
						m_guranteradd3_2=rs5.getString(3);
						m_guranter_city3=rs5.getString(4);
						m_gurantertitle3=rs5.getString(5);
					}
					else if(count_gurant==4){   
						m_guranter4=rs5.getString(1);
						m_guranteradd4=rs5.getString(2);
						m_guranteradd4_2=rs5.getString(3);
						m_guranter_city4=rs5.getString(4);
						m_gurantertitle4=rs5.getString(5);
					}
					more_gurant=rs5.next();
					count_gurant=count_gurant+1;
				}
				
				
				rs4.close();
				stmt4.close();
			*/	
				
			/*	out.println("<table border='0' width='90%' class='table'> ");
				out.println("<tr ><td width='*%' class='rep-body' style='{font:13px;text-align:justify;}'>");
				out.println("<p>");
				out.println("<font face=\"FMBindumathi\">");
                out.println("l<ukdlre"); //l<ukdlre 
				out.println("</p></font>");	
				out.println("</table>");
				
            */
				
				    // Guaranter
					
						  String sql_gur_1=" SELECT "+
					"  A.APPLICATION_NO, "+
					"  A.GUARANTOR_CODE, "+
					"  INITCAP("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.GUARANTOR_CODE)), "+//INITCAP by Lalanka on 05-6-2009
					"  INITCAP(NVL(B.ADDRESS1,'-')), "+//INITCAP by Lalanka on 05-6-2009
					"  INITCAP(NVL(B.ADDRESS2,'-')), "+//INITCAP by Lalanka on 05-6-2009
					"  INITCAP(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(B.CITY_CODE),'-')), "+
					"  NVL(DECODE(B.CLIENT_TYPE,'I',INITCAP(B.TITLE)||'.','C',''),' ') "+	//added by madhawa 2012-01-12
					" FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
					" WHERE A.APPLICATION_NO='"+m_app_no+"' AND A.GUARANTOR_CODE=B.CLIENT_CODE ORDER BY A.GUAR_ID,A.GUARANTOR_CODE ";
				//out.println(sql_gur_1);
				  out.println	("<!--Guaranter "+sql_gur_1+" -->");
				
				Vector cop_gur_name=new Vector();
				Vector cop_gur_add1=new Vector();
				Vector cop_gur_add2=new Vector();
				Vector cop_gur_city=new Vector();//added 2012-03-19
				
				rs1 = stmt1.executeQuery (sql_gur_1);
				
				out.println("<table border='0' width='90%' class='table'> ");
				out.println("<tr ><td width='*%' class='rep-body' style='{font:13px;text-align:justify;}'>");
				out.println("<p>");
				out.println("<font face=\"FMBindumathi\">");
                out.println("msgm;a - wemlrejka");
				out.println("</p></font>");	
				out.println("</table>");
				
				
				int g_count=0;
			//	int o=0,j=1;
				
				
				/*int count=0;
				int i=0,j=1,x=2;
				int m=2,n=3;
				int k=2,l=3; */
				
				while(rs1.next())
				{
					cop_gur_name.addElement(rs1.getString(7)+rs1.getString(3));
					cop_gur_add1.addElement(rs1.getString(4));
					cop_gur_add2.addElement(rs1.getString(5));
					gur_city.addElement(rs1.getString(6));//2012-03-15
					g_count=g_count+1;
				}
				
				//out.println("count="+count);
				//out.println("g_count="+g_count);
				if(g_count==1){
					out.println("<br>");						
					out.println("<table border='"+m_dummy_border+"' width='90%' class='table'> ");	
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'> 1). "+cop_gur_name.get(i).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'></td></tr>");
				//	out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'>"+cop_gur_add1.get(i).toString()+"</td>");
				//	out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'></td></tr>");
				//	out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'>"+cop_gur_add2.get(i).toString()+"</td>");
				//	out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'></td></tr>");
				//	out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'>"+gur_city.get(i).toString()+"</td>");
				//	out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'></td></tr>");
					out.println("</table>");
				}
				else if(g_count==2){
					out.println("<br>");						
                    out.println("<table border='"+m_dummy_border+"' width='90%' class='table'> ");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:11ptpt;text-align:justify;}'>1). "+cop_gur_name.get(i).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:11pt;text-align:justify;}'></td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:11pt;text-align:justify;}'>2). "+cop_gur_name.get(j).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:11pt;text-align:justify;}'></td></tr>");


					
					out.println("</table>"); 
					
				}
						else if(g_count==3){
						
				/*	out.println("<table border='"+m_dummy_border+"' width='90%' class='table'> ");
					
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>1). "+cop_gur_name.get(i).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'></td></tr>");
					//out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'>"+cop_gur_name.get(j).toString()+"</td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'>2). "+cop_gur_name.get(j).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'></td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'>3). "+cop_gur_name.get(k).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'></td></tr>"); */
					
					/*out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+cop_gur_name.get(j).toString()+"</td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+cop_gur_add1.get(i).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+cop_gur_add1.get(j).toString()+"</td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+cop_gur_add2.get(i).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+cop_gur_add2.get(j).toString()+"</td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_city.get(i).toString()+"</td>");  
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_city.get(j).toString()+"</td></tr>"); 
					
					out.println("<tr><td> &nbsp</td> </tr>"); 
					
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+cop_gur_name.get(k).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'></td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+cop_gur_add1.get(k).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'></td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+cop_gur_add2.get(k).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'></td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_city.get(k).toString()+"</td>");
			   	    out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'></td></tr>");
					
					
					out.println("</table>");	*/
					
			/*		out.println("<table border='"+m_dummy_border+"' width='90%' class='table'> ");	
					out.println("<tr><td width='30%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+cop_gur_name.get(i).toString()+"</td>");
					out.println("     <td width='30%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+cop_gur_name.get(j).toString()+"</td>");
					out.println("     <td width='30%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+cop_gur_name.get(x).toString()+"</td></tr>");
					out.println("<tr> <td width='30%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+cop_gur_add1.get(i).toString()+"</td>");
					out.println("     <td width='30%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+cop_gur_add1.get(j).toString()+"</td>");
					out.println("     <td width='30%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+cop_gur_add1.get(x).toString()+"</td></tr>");
					out.println("<tr> <td width='30%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+cop_gur_add2.get(i).toString()+"</td>");
					out.println("     <td width='30%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+cop_gur_add2.get(j).toString()+"</td>");
					out.println("     <td width='30%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+cop_gur_add2.get(x).toString()+"</td></tr>");
					out.println("<tr><td width='30%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_city.get(i).toString()+"</td>");  
					out.println("    <td width='30%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_city.get(j).toString()+"</td>"); 
					out.println("    <td width='30%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_city.get(x).toString()+"</td></tr>"); 
					out.println("</table>");
			*/		
				    out.println("<table border='"+m_dummy_border+"' width='90%' class='table'> ");
					
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:11ptpt;text-align:justify;}'>1). "+cop_gur_name.get(i).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:11pt;text-align:justify;}'></td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:11pt;text-align:justify;}'>2). "+cop_gur_name.get(j).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:11pt;text-align:justify;}'></td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:11pt;text-align:justify;}'>3). "+cop_gur_name.get(m).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:11pt;text-align:justify;}'></td></tr>");

					
					out.println("</table>"); 
					
						
					}	
					else if(g_count!=0){
						
				//	out.println("<table border='"+m_dummy_border+"' width='90%' class='table'> ");
					
			/*		out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>1). "+cop_gur_name.get(i).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'></td></tr>");
					//out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'>"+cop_gur_name.get(j).toString()+"</td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'>2). "+cop_gur_name.get(j).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'></td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'>3). "+cop_gur_name.get(k).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'></td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'>4). "+cop_gur_name.get(l).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10pt;text-align:justify;}'></td></tr>");
					/*out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+cop_gur_name.get(i).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+cop_gur_name.get(j).toString()+"</td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+cop_gur_add1.get(i).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+cop_gur_add1.get(j).toString()+"</td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+cop_gur_add2.get(i).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+cop_gur_add2.get(j).toString()+"</td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_city.get(i).toString()+"</td>");  
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_city.get(j).toString()+"</td></tr>"); 
					
					out.println("<tr><td> &nbsp</td> </tr>"); 
					
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+cop_gur_name.get(k).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+cop_gur_name.get(l).toString()+"</td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+cop_gur_add1.get(k).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+cop_gur_add1.get(l).toString()+"</td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+cop_gur_add2.get(k).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+cop_gur_add2.get(l).toString()+"</td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_city.get(k).toString()+"</td>");  
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:10ptpt;text-align:justify;}'>"+gur_city.get(l).toString()+"</td></tr>");					
				*/
				//	out.println("</table>");
				
				    out.println("<table border='"+m_dummy_border+"' width='90%' class='table'> ");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:11ptpt;text-align:justify;}'>1). "+cop_gur_name.get(i).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:11pt;text-align:justify;}'></td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:11pt;text-align:justify;}'>2). "+cop_gur_name.get(j).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:11pt;text-align:justify;}'></td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:11pt;text-align:justify;}'>3). "+cop_gur_name.get(m).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:11pt;text-align:justify;}'></td></tr>");
					out.println("<tr><td width='45%' class='rep-body-rl_letters' style='{font:11pt;text-align:justify;}'>4). "+cop_gur_name.get(n).toString()+"</td>");
					out.println("<td width='45%' class='rep-body-rl_letters' style='{font:11pt;text-align:justify;}'></td></tr>");
					out.println("</table>"); 
					
			        
					
				  }
					
				//  	out.println("</br>");		
				//	out.println("</br>");	
				//	out.println("</br>");	
				//	out.println("</br>");	
				//	out.println("</br>");		
						
					out.println("<table border='0' width='90%' class='table'>"); 	
			        out.println("<font face=\"Times New Roman\">");
				    out.println("<tr><td width='*%' class='rep-body' style='{font:13px;text-align:center;}'><u>"+m_company_name+"</u></td></tr>");
				    out.println("</font>");
				    out.println("</table>");
					
					out.println("<table border='0' width='90%' class='table'>"); 	
			        out.println("<font face=\"Times New Roman\">");
				    out.println("<tr><td width='*%' class='rep-body' style='{font:13px;text-align:center;}'><u>"+m_reg_no+"</u></td></tr>");
				    out.println("</font>");
				    out.println("</table>");
					
					out.println("<table border='0' width='90%' class='table'>"); 	
			        out.println("<font face=\"Times New Roman\">");
				    out.println("<tr><td width='*%' class='rep-body' style='{font:13px;text-align:center;}'><u>"+m_company_det+"</u></td></tr>");
				    out.println("</font>");
				    out.println("</table>");
					
					out.println("<table border='0' width='90%' class='table'>"); 	
			        out.println("<font face=\"Times New Roman\">");
				    out.println("<tr><td width='*%' class='rep-body' style='{font:13px;text-align:center;}'><u>"+m_com_contact+"</u></td></tr>");
				    out.println("</font>");
				    out.println("</table>");
					
			      // out.println("</div>");
					// out.println("<p style=\"page-break-after:always\">&nbsp;</p>");		
				   }
				}	
				
				out.println("<SCRIPT language1.2='JavaScript' src='" + m_html_client_url + "/validate_v1.js'></SCRIPT>");
				//out.println("</BODY>");
				//out.println("</html>");
				
				out.println("</font></p></blockquote>");	
			    out.println("</font></p>");	
				out.println("</form></body></html>");
			
				
			}
			} catch (Exception ex) {
			try {
				out.println("Error:" + ex.toString());
			} catch (Exception e) {
			}
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
				}
			}
		}
	}
}