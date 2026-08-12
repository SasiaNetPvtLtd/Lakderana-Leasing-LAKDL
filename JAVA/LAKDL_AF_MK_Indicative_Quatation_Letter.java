import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
//import CLAMF_sn_methods;
import java.util.*;
import oracle.jdbc.driver.*;

//Modified by Mahela on 11-04-2007

public class LAKDL_AF_MK_Indicative_Quatation_Letter extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt3,stmt4,stmt5;
	CallableStatement callstmt;
	java.text.NumberFormat nf;
	java.text.NumberFormat nf1;
	
	public ResultSet rs,rs1,rs_ind,rs2,rs3,rs4,rs5;
	public String m_chksql,m_qua,m_price,m_opt,m_url;
	public String m_st="";
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		try {
			
			//************************************************************	
			//SCREEN_METHODS m_sn_methods = new SCREEN_METHODS();
			LAKDL_AF_CO_conn_methods m_sn_methods=new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url = m_sn_methods.html_client_url;
			String m_class_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			
			//**************************************************************					
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			res.setHeader("Cache-Control", "No-Cache");
			res.setDateHeader("Expires", 0);
			
			ServletOutputStream out = res.getOutputStream();
			String m_sp_name="";
			String m_pricing="";
			m_chksql=req.getParameter("chksql");
			stmt=conn.createStatement();
			stmt2=conn.createStatement();
			stmt1=conn.createStatement();
			stmt3=conn.createStatement();
			stmt4=conn.createStatement();
			stmt5=conn.createStatement();
			
			m_url = m_class_url;
			String m_fschema_name=m_sn_methods.client_name.trim();
			
			
			String m_st="0";
			if (m_chksql.trim().equals("idle")) 
			{
				out.println("idle");
			}	
			else if (m_chksql.trim().equals("view_letter"))
			{		
				try
				{
					
				
					
					
				String m_qutation = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				String m_model="";
				String m_make="";
				String m_item="";
				String m_un="";
				String m_client_name="";
				String m_mk="";
				String m_inq = "";
				String m_title="";
				String m_sub_type="";
				String m_tran_type="";
				double m_vat = 0.0;
				double m_net_amt =0.0;
				double m_initial_amt=0;
				double m_vat_amt=0;
				boolean more4 = false;
				String m_prcing_no = "";
				int m_ami_value = 0;
				String m_ami ="";
				String m_new_modle = "";
				
				String m_orient_name="";
				String m_orient_add1="";
				String m_orient_add2="";
				String m_orient_city_name="";
				String m_orient_tel_no="";
				String m_orient_fax_no="";
				String m_orient_vat_rate="";
				//-----------------------------------------------------------------------------------------
				//rs = stmt.executeQuery("SELECT DISTINCT A.QUOTATION_NO,A.INQUIRY_NO,initcap(CLIENT_NAME),REPLACE(REPLACE(NVL(ADDRESS,' '),'-',' '),'null',' '),REPLACE(REPLACE(NVL(ADDRESS2,' '),'-',' '),'null',' '),initcap(REPLACE(initcap(F.DESCRIPTION),'Not Applicable','')),CITY_DESC/*initcap(REPLACE(CITY_DESC,'--',' '))*/,A.STATUS,TO_CHAR(A.ENT_DATE,'fmddth Month YYYY,'),B.PRICING_NO,OPTION_ID,QTY,B.GROSS_AMOUNT,B.VAT_AMOUNT,B.GROSS_RENTAL,B.VAT_RENTAL,NVL(H.RESIDUAL_VALUE,0),B.PERIOD,REPLACE(initcap(MAKE_DESC),'Not Applicable',''),REPLACE(initcap(G.DESCRIPTION),'Not Applicable',''),NVL(H.AMI,0),H.NIBSM,nvl(B.RESIDUAL_AMOUNT,0),initcap(CLIENT_LAST_NAME),H.GROSS_AMOUNT,'',REPLACE(initcap(h.item_category),'N/A',''),MK_SUPERVISOR,TITLE,JD.DESCRIPTION,h.pricing_no,NVL("+m_schema_name+".AF_CO_GET_ITEM_SUB_DESC(B.model_code),'-'),NVL("+m_schema_name+".AF_CO_GET_SUB_MODEL_DESC(B.model_code),'-'),NVL(H.VAT_AMOUNT,0),C.SUB_PRODUCT_CODE,NVL(H.NET_AMOUNT,0) FROM "+m_schema_name+".AF_MK_PRO_QUOTATION A,"+m_schema_name+".AF_MK_PRO_QUOTATION_DET B,"+m_schema_name+".AF_MK_PRO_INQUIRY C,"+m_schema_name+".AF_CO_MAS_CITY D,"+m_schema_name+".AF_CO_MAS_MAKE E,"+m_schema_name+".AF_CO_MAS_CONDITION_OF_ASSET F,"+m_schema_name+".AF_CO_MAS_MODEL G,"+m_schema_name+".AF_MK_PRO_PRICING H, "+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE JD WHERE UPPER(B.MAKE_CODE)=UPPER(E.MAKE_CODE) AND UPPER(B.MODEL_CODE)=UPPER(G.MODEL_CODE) AND UPPER(A.INQUIRY_NO)=UPPER(C.INQUIRY_CODE) AND UPPER(B.CONDITION_OF_ASSET)=UPPER(F.CODE) AND UPPER(C.CITY_CODE)=UPPER(D.CITY_CODE) AND A.STATUS='Y' AND UPPER(A.QUOTATION_NO) =UPPER(B.QUOTATION_NO) AND UPPER(A.QUOTATION_NO) = UPPER('"+m_qutation+"') AND UPPER(H.PRICING_NO) =UPPER(B.PRICING_NO) AND TRAN_CODE=SUB_PRODUCT_CODE ORDER BY OPTION_ID ASC ");
				
				
				rs1 = stmt1.executeQuery(" SELECT "+ //Added By SJ on 20-11-2008
					" NVL(UPPER(COMPANY_NAME),' '), "+
					" NVL(UPPER(ADDRESS1),' '), "+
					" NVL(UPPER(ADDRESS2),' '), "+
					" NVL(UPPER(CITY),' '), "+
					" NVL(TEL_NO,' '), "+
					" NVL(FAX_NO,' '),  "+
					" NVL(VAT_RATE,0) "+
					" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ");
				
				boolean more_orient = rs1.next();		
				
				if(more_orient)
				{
					m_orient_name=rs1.getString(1);
					m_orient_add1=rs1.getString(2);
					m_orient_add2=rs1.getString(3);
					m_orient_city_name=rs1.getString(4);
					m_orient_tel_no=rs1.getString(5);
					m_orient_fax_no=rs1.getString(6);
					m_orient_vat_rate=rs1.getString(7);			
				}
				
				
				
				
				
				
				rs = stmt.executeQuery
				    ("SELECT DISTINCT A.QUOTATION_NO,A.INQUIRY_NO,initcap(CLIENT_NAME), "+
					" REPLACE(REPLACE(NVL(ADDRESS,' '),'-',' '),'null',' '),REPLACE(REPLACE(NVL(ADDRESS2,' '),'-',' '),'null',' '), "+
					" initcap(REPLACE(initcap(F.DESCRIPTION),'Not Applicable','')),CITY_DESC "+
					" /*initcap(REPLACE(CITY_DESC,'--',' '))*/,A.STATUS,TO_CHAR(A.ENT_DATE,'fmddth Month YYYY,'), "+
					" B.PRICING_NO,OPTION_ID,QTY,B.GROSS_AMOUNT,B.VAT_AMOUNT,B.GROSS_RENTAL,B.VAT_RENTAL, "+
					" NVL(H.RESIDUAL_VALUE,0),B.PERIOD,REPLACE(initcap(MAKE_DESC),'Not Applicable',''), "+
					" REPLACE(initcap(G.DESCRIPTION),'Not Applicable',''),NVL(H.AMI,0),H.NIBSM,nvl(B.RESIDUAL_AMOUNT,0), "+
					" initcap(CLIENT_LAST_NAME),H.GROSS_AMOUNT,'',REPLACE(initcap(h.item_category),'N/A',''),MK_SUPERVISOR, "+
					" TITLE,JD.DESCRIPTION,h.pricing_no,NVL("+m_schema_name+".AF_CO_GET_ITEM_SUB_DESC(B.model_code),'-'), "+
					" NVL("+m_schema_name+".AF_CO_GET_SUB_MODEL_DESC(B.model_code),'-'),NVL(H.VAT_AMOUNT,0), "+
					" C.SUB_PRODUCT_CODE,NVL(H.NET_AMOUNT,0)  "+
					" FROM "+m_schema_name+".AF_MK_PRO_QUOTATION A,"+m_schema_name+".AF_MK_PRO_QUOTATION_DET B, "+
					" "+m_schema_name+".AF_MK_PRO_INQUIRY C,"+m_schema_name+".AF_CO_MAS_CITY D,"+m_schema_name+".AF_CO_MAS_MAKE E, "+
					" "+m_schema_name+".AF_CO_MAS_CONDITION_OF_ASSET F,"+m_schema_name+".AF_CO_MAS_MODEL G, "+
					" "+m_schema_name+".AF_MK_PRO_PRICING H, "+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE JD "+
					" WHERE UPPER(B.MAKE_CODE)=UPPER(E.MAKE_CODE)  "+
					" AND UPPER(B.MODEL_CODE)=UPPER(G.MODEL_CODE)  "+
					" AND UPPER(A.INQUIRY_NO)=UPPER(C.INQUIRY_CODE)  "+
					" AND UPPER(B.CONDITION_OF_ASSET)=UPPER(F.CODE)  "+
					" AND C.CITY_CODE=D.CITY_CODE(+)  "+
					" AND A.STATUS='Y' AND UPPER(A.QUOTATION_NO) =UPPER(B.QUOTATION_NO)  "+
					" AND UPPER(A.QUOTATION_NO) = UPPER('"+m_qutation+"')  "+
					" AND UPPER(H.PRICING_NO) =UPPER(B.PRICING_NO)  "+
					" AND TRAN_CODE=SUB_PRODUCT_CODE ORDER BY OPTION_ID ASC ");
				boolean more=rs.next();	
				
				while(more)
				{ //start 1
					
					m_qua=rs.getString(1);
					m_vat= rs.getDouble(34);
					m_tran_type = rs.getString(35);
					m_net_amt = rs.getDouble(36);
					m_inq     = rs.getString(2); 
					
					
					/*
					rs3 = stmt3.executeQuery("SELECT initcap(NAME) "+
					"FROM "+m_schema_name+".CO_CO_MAS_USER   "+
					"WHERE USER_ID='"+rs.getString(28)+"' ");
			*/
					
					/*
					rs4 = stmt4.executeQuery  // Commented By Sandun on 20-11-2008
					//out.println
					("SELECT nvl(NET_RENTAL_AMOUNT,0),nvl((GRENTAL_AMOUNT-NET_RENTAL_AMOUNT),0) "+
					"FROM "+m_schema_name+".AF_MK_PRO_PRICING_INSTALLMENT  "+
					"WHERE UPPER(PRICING_NO)=UPPER('"+m_prcing_no+"') "+
					"AND INSTALLMENT_NO='0' ");
					//double m_initial_amt=0;
					//double m_vat_amt=0;
					more4  = rs4.next();
					while(more4){
					out.println(m_prcing_no);
					m_initial_amt=rs4.getDouble(1);
					m_vat_amt=rs4.getDouble(2);
					more4  = rs4.next();
					}
					
					*/
					
					m_st=rs.getString(8);
					//if(rs3.next()){
					//	m_sp_name=rs3.getString(1);
					
					//	}
					
					m_client_name=rs.getString(24);
					
					
					if(rs.getString(24)==null){
						m_client_name="";
					}
					
					else if(rs.getString(24).equals("-")){
						m_client_name="";
					}
					
					m_title=rs.getString(29);
					
					
					if(m_title==null){
						m_title="";
					}
					else if(m_title.equals("-") || m_title.equals("")){
						m_title="";
					}
					
					
					m_sub_type=rs.getString(30);
					if(m_sub_type.equals("-") || m_sub_type.equals("")){
						m_sub_type="";
					}
					
					
					if(!m_st.equals("P")){
						
						
						out.println("<html><head><font 10pt arial><title>INDICATIVE QUOTATION</title></head>");
						out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
						out.println("<body leftmargin='0' topmargin='0' class=body>");
						
						//out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo.gif\"></DIV><br><hr>");
						out.println("<br>");
						out.println("<br>");
						out.println("<br>");
						out.println("<br>");
						out.println("<br>");
						out.println("<br>");
						
						out.println("<div align='center' width='162%' class=div_input><b>INDICATIVE QUOTATION NO ("+rs.getString(1)+") </div>");
						if(!m_st.equals("0")){
							
							out.println("<HTML><HEAD>");
							out.println("<SCRIPT language='JavaScript'>");
							out.println("function add_button(){");
							out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
							out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
							out.println("m_writedata+'</table>';");
							out.println("}");
							
							out.println("function save_data(){");			
							//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_Indicative_Quatation_Letter?chksql=view_letter&data_val="+m_qutation+"&ac_status=Y\";");
							//out.println(" window.location.href=m_url;");		
							out.println("m_table.innerHTML=\"\" ");		
							out.println("window.print();");		
							out.println("}");
							
							out.println("function displaymsg() {");
							out.println("}</SCRIPT></HEAD>");
							out.println("<body onload='displaymsg(),add_button();'>");
							
							out.println("<table align='center' width='100%' class='table'>"); 
							out.println("<tr>");  
							out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
							out.println("</tr>"); 
							out.println("</table>");			
							out.println("</body>");
							out.println("</html>");
						}				
						
						out.println("<form name='form1'>");
						out.println("<table border='0' width='100%' class=table>");
						out.println("</table>");
						out.println("<blockquote><font size=2><p style='text-align:right'>");		
						
						
						out.println("<table border='0' width='100%' class=table >");
						out.println("<tr><td width='*%' class='rep-body'>"+rs.getString(9)+"</td></tr>");
						out.println("<tr><td width='*%' class='rep-body' style='{ font:bold;}'><br>"+m_title+" "+rs.getString(3)+" "+m_client_name+"</td></tr>");
						out.println("<tr><td width='*%' class='rep-body' >"+rs.getString(4)+"</td></tr>");
						out.println("<tr><td width='*%' class='rep-body' >"+rs.getString(5)+"</td></tr>");
						out.println("<tr><td width='*%' class='rep-body' >"+rs.getString(7)+"</td></tr>");
						out.println("<tr ><td width='*%' class='rep-body' style='{text-align:left;}'><br>Dear Sir/Madam, </td>");
						out.println("<tr><td width='*%' class='rep-body' style='{text-align:left;}'><br>We thank you for your inquiry and are pleased to forward the following Indicative Quotation for your<br> perusal.</td></tr>");
						out.println("<tr ><td width='*%' class='rep-body' style='{text-align:left;}'><br>* "+rs.getString(30)+"</td></tr>");
						out.println("</table>");
						
						out.println("</font></p></blockquote>");
						
						while(rs.getString(1).trim().equals(m_qua)){//start 2
							
							m_opt=rs.getString(11);
							out.println("<blockquote><font size=2><p style='text-align:right'>");	
							
							out.println("<table border='0' width='100%' class=table>");
							out.println("<tr>");
							out.println("<td width='36%'><b>Option  ("+rs.getString(11)+")</td>");
							out.println("</tr>");
							out.println("</table>");
							
							while(rs.getString(11).trim().equals(m_opt)){//----start 3
								m_prcing_no = rs.getString(31);
								m_model=rs.getString(6);
								if(rs.getString(6)==null) {
									m_model="";
								}
								
								m_make=rs.getString(19);
								if(rs.getString(19)==null) {
									m_make="";
								}
								m_un=rs.getString(20);
								
								if(rs.getString(20)==null) {
									m_un="";
								}
								
								m_item=rs.getString(27);
								
								if(rs.getString(27)==null) {
									m_item="";
								}
								
								m_mk=rs.getString(28);
								m_ami  = rs.getString(21);//
								m_ami_value = Integer.parseInt(m_ami)+1;//Added By Saandun on 20-11-2008
								
								out.println("<table border='0' width='100%' class='table' >");
								if(m_tran_type.equals("FINLEASE")){
									out.println("<tr style='{ font:bold; text-align:left;}'><td><u>"+m_model+"  "+m_make+" "+m_un+ "  Costing "+nf.format(rs.getDouble(25))+"/- ("+nf.format(m_net_amt)+" + VAT of "+nf.format(m_vat)+" )</td></tr>");//"+rs.getString(33)+"  "+rs.getString(32)+" 
								}
								else{
									out.println("<tr style='{ font:bold; text-align:left;}'><td><u>"+m_model+"   "+m_make+" "+m_un+ "  Costing "+nf.format(rs.getDouble(25))+"/-</td></tr>"); //"+rs.getString(33)+"  "+rs.getString(32)+"
								}
								
								rs4 = stmt4.executeQuery( " SELECT nvl(NET_RENTAL_AMOUNT,0),nvl((GRENTAL_AMOUNT-NET_RENTAL_AMOUNT),0) "+
									" FROM "+m_schema_name+".AF_MK_PRO_PRICING_INSTALLMENT  "+
									" WHERE UPPER(PRICING_NO)=UPPER('"+m_prcing_no+"') "+
									" AND INSTALLMENT_NO='0' ");		
								
								more4  = rs4.next();
								
								while(more4){	  
									m_initial_amt=rs4.getDouble(1);
									m_vat_amt=rs4.getDouble(2);
									more4  = rs4.next();		
								}	
								
								out.println("</table>");
								out.println("<table border='0' width='100%' class='table'>");
								out.println("<tr><td style='{ font:bold; text-align:left;}'>Monthly Rental</td><td width='1'>  - </td><td width='10%' style='{ font:bold; text-align:left;}'>"+nf.format(rs.getDouble(15))+"</td><td width='*%' style='{ font:bold; text-align:left;}'> + VAT of  "+nf.format(rs.getDouble(16))+" "+"(0+"+m_ami_value+")"+" </td></tr>");//rs.getString(21)
								out.println("<tr><td style='{ font:bold; text-align:left;}'>Period        </td><td width='1'>  - </td><td width='15%' style='{ font:bold; text-align:left;}'>"+rs.getString(18)+" months</td></tr>");
								out.println("<tr><td style='{ font:bold; text-align:left;}'>Initial Payment</td><td width='1'> - </td><td width='10%' style='{ font:bold; text-align:left;}'>"+nf.format(m_initial_amt)+"</td><td width='*%' style='{ font:bold; text-align:left;}'> + VAT of  "+nf.format(m_vat_amt)+" "+"(0+"+m_ami_value+")"+"</td></tr>");//rs.getString(21)
								
								/*if(!rs.getString(22).equals("0")){
								out.println("<tr><td style='{ font:bold; text-align:left;}'>Residual Amount</td><td width='1'>  - </td><td width='10%' style='{ font:bold; text-align:left;}'>0.00</td></tr>");
								
								}
								else*/
								if(rs.getString(22).equals("0")){
									out.println("<tr><td style='{ font:bold; text-align:left;}'>Residual Amount</td><td width='1'>  - </td><td width='10%' style='{ font:bold; text-align:left;}'>"+nf.format(rs.getDouble(17))+"</td></tr>");
									
								}
								
								//out.println("<tr><td style='{ font:bold; text-align:left;}'>NIBSM</td><td width='1'>  - </td><td width='10%' style='{ font:bold; text-align:left;}'>"+nf.format(rs.getDouble(22))+"</td></tr>");
								out.println("</table>");
								more=rs.next();
								
								if (!more)
								{
									break;
								}
								
							}//---------end 3
							out.println("</font></p></blockquote>");			
							if (more)
							{
								m_opt=rs.getString(11);
							}	
							
							
							if (!more)
							{
								break;
							}		
							
							if (more)
							{
								m_qua=rs.getString(1);
							}
							
							
						}//end 2
						
						
					}
					//	out.println(rs.next());
				}//end 1
				
				
				rs2 = stmt2.executeQuery("SELECT MAIN_CODE,CODE,initcap(DESCRIPTION),ACTIVE_STATUS "+
					"FROM "+m_schema_name+".AF_MK_CONDITIONS_MAIN "+
					"WHERE UPPER(MAIN_CODE)=UPPER('"+m_qua+"')");
				
				
				int f=0;
				
				boolean more2=rs2.next();	
				out.println("<blockquote><font size=2><p style='text-align:right'>");
				
				
				
				while(more2) //Modified by Mahela on 17-05-2007 ( Alignments )
				{
					if(f==0){
						out.println("<table border='0' width='100%' class=\"table\" >");
						out.println("<tr><td width='30%' style='{ font:bold; text-align:left;}'><b><u>Subject to Following Conditions </td></tr>");
						out.println("</table>");
					}
					out.println("<table border='0' width='100%' class=\"table\" >");//<td width='10%' style='{ font:bold; text-align:left;}'><b> "+rs2.getString(2)+"</td>
					out.println("<tr><td width='20%' style='{ font:bold; text-align:left;}'><b> "+rs2.getString(3)+"</td></tr>");
					out.println("</table>");
					more2=rs2.next();
					f=f+1;
					if (!more2)
					{
						break;
					}
					
					
				}
				
				out.println("</font></p></blockquote>");
				
				
				if(m_st.equals("0")){
					
					out.println("<HTML><HEAD>");
					out.println("<SCRIPT language='JavaScript'>");
					out.println("function displaymsg() {");
					out.println("window.close()");
					out.println("}</SCRIPT></HEAD>");
					out.println("<body onload='displaymsg();'></body>");
					out.println("</html>");
					
				}				
				if(!m_st.equals("0")){
					
					//Added by Mahela on 11-04-2007
					rs1 = stmt1.executeQuery(" SELECT "+
						" QUOTATION_NO, "+
						" NVL((SELECT CONTACT_NO FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE WHERE EMP_CODE=(SELECT EMP_ID FROM "+m_schema_name+".CO_CO_MAS_USER WHERE USER_ID=AUTH_SIGNATORY)),'-') "+
						" FROM "+m_schema_name+".AF_MK_PRO_QUOTATION "+
						" WHERE QUOTATION_NO='"+m_qutation+"' ");
					
					boolean more_con=rs1.next();	
					String m_contact_personal="";
					
					if(more_con)
					{ 
						m_contact_personal=rs1.getString(2);
					}
					
					//Added by Mahela on 25-04-2007
					
					rs1 = stmt1.executeQuery(" SELECT "+
						" QUOTATION_NO, "+
						" NVL("+m_schema_name+".AF_CO_GET_USER_NAME(MKT_OFFICER),'-'),"+	
						" NVL(TEL_NO,'-'), "	+
						" MKT_OFFICER "+
						" FROM "+m_schema_name+".AF_MK_PRO_QUOTATION "+
						" WHERE QUOTATION_NO='"+m_qutation+"' ");
					
					boolean more_off=rs1.next();	
					String m_mk_officer="",m_tel_no="";
					
					if(more_off)
					{ 
						m_mk_officer=rs1.getString(2);
						if(rs1.getString(2).equals("-")){
							m_mk_officer=rs1.getString(4);
							
						}
						m_tel_no=rs1.getString(3);
					}
					String m_personal_contact = ""; 
					String m_designation      = "";
					String m_emp_name         = "";
					
					rs5 = stmt5.executeQuery(" SELECT A.INQUIRY_CODE, "+//1    //----------Added By Sandun 05-11-2008
						" C.DESIGNATION_CODE,    "+//2
						" "+m_schema_name+".AF_CO_GET_DESIGNATION_DESC("+m_schema_name+".AF_CO_GET_DESIGNATION_CODE(C.EMP_CODE)),"+//3
						" NVL(C.CONTACT_NO,'-'),  "+//4
						" C.FIRST_NAME ||' '|| C.LAST_NAME EMP_NAME "+//5
						" FROM  "+m_schema_name+".AF_MK_PRO_INQUIRY A,   "+
						"       "+m_schema_name+".AF_MK_PRO_QUOTATION B, "+
						"       "+m_schema_name+".CO_CO_MAS_EMPLOYEE C   "+
						" WHERE A.INQUIRY_CODE  = B.INQUIRY_NO "+
						" AND   A.MK_OFFICER    = C.EMP_CODE   "+
						" AND   A.INQUIRY_CODE  = '"+m_inq+"' ");
					
					
					if(rs5.next()){
						m_personal_contact = rs5.getString(4);
						m_designation      = rs5.getString(3);
						m_emp_name         = rs5.getString(5);
					}
					
					
					out.println("<blockquote><font size=2><p style='text-align:right'>");
					
					out.println("<table border='0' width='100%' class=table >");
					
					out.println("<tr ><td width='*%' class='rep-body' style='{text-align:left;}'>The stated monthly rental excludes Insurance, which needs to be placed through our Broker with an <br>insurer of your choice. </td>");
					out.println("</tr>");
					out.println("<tr><td width='*%' class='rep-body' style='{text-align:left;}'></td></tr>");
					out.println("<tr><td width='*%' class='rep-body' style='{text-align:left;}'>This quotation is valid for 14 days from today and will be subject to the approval of our credit committee.</td></tr>");
					out.println("<tr><td width='*%' class='rep-body' style='{text-align:left;}'>Should you need further clarification or a facility under the option shown in this quotation, please do not</td></tr>");
					
					//out.println("<tr><td width='*%' class='rep-body' style='{text-align:left;}'>hesitate to contact "+m_sp_name+" on 5-577577(Direct),"+m_contact_personal+"(Personal)</td></tr>");
					//out.println("<tr><td width='*%' class='rep-body' style='{text-align:left;}'>hesitate to contact "+m_mk_officer+" on "+m_tel_no+"(Direct),"+m_contact_personal+"(Personal)</td></tr>");// Commented By Sandun on 06-11-2008
					out.println("<tr><td width='*%' class='rep-body' style='{text-align:left;}'>hesitate to contact "+m_mk_officer+" on (Direct) "+m_personal_contact+",(Mobile) "+m_tel_no+"</td></tr>");
					
					out.println("<tr><td width='*%' class='rep-body' style='{text-align:left;}'>We look forward to meeting you shortly to complete the necessary documentation.</td></tr>");
					out.println("<tr><td width='*%' class='rep-body' style='{text-align:left;}'><br>Yours faithfully,</td></tr>");
					//out.println("<tr><td width='*%' class='rep-body' style='{font:bold; text-align:left;}'>ORIENT FINANCIAL SERVICES CORPORATION LTD.</td></tr>");
					out.println("<tr><td width='*%' class='rep-body' style='{font:bold; text-align:left;}'>"+m_orient_name+"</td></tr>");
					out.println("</table>");
					out.println("</font></p></blockquote>");
					
					
					//Added by Mahela on 11-04-2007
					rs1 = stmt1.executeQuery(" SELECT "+
						" QUOTATION_NO, "+
						" NVL(AUTH_SIGNATORY,'-'), "+
						" NVL((SELECT FIRST_NAME ||' '|| LAST_NAME EMP_NAME FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE WHERE EMP_CODE=AUTH_SIGNATORY),'-')"+	//CO_CO_MAS_USER // Mod By Sandun on 20-11-2008
						" FROM "+m_schema_name+".AF_MK_PRO_QUOTATION "+
						" WHERE QUOTATION_NO='"+m_qutation+"' ");
					
					
					out.println("<blockquote><font size=2><p style='text-align:right'>");
					
					out.println("<table border='0' width='100%' class=table >");
					out.println("<tr><td width='*%' class=txt-body style='{font:12px;text-align:left;}'>.....................................</td></tr>");
					out.println("<tr><td >");  
					
					boolean more1=rs1.next();	
					if(more1)
					{
						if(!rs1.getString(3).equals("-")){
							out.println(" "+rs1.getString(3)+" ");   
						}
						if(rs1.getString(3).equals("-")){
							out.println(" "+rs1.getString(2)+" ");   
							
						}
					}	
					out.println("</td></tr>");
					//out.println("<tr><td width='*%' class=txt-body style='{font:12px;text-align:left;}'><b>Authorized Signatory</td></tr>");
					out.println("<tr><td width='*%' class=txt-body style='{font:12px;text-align:left;}'><b>"+m_designation+"</td></tr>");
					out.println("</table>");
					//out.println("</td></tr>");
					//out.println("</table>");
					out.println("</font></p></blockquote>");
					out.println("</form>");
					out.println("</body>");
					out.println("</html>");
					
					
				}
				/*
				if(!m_st.equals("0")){
				
				out.println("<HTML><HEAD>");
				out.println("<SCRIPT language='JavaScript'>");
				out.println("function add_button(){");
				out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
				out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
				out.println("m_writedata+'</table>';");
				out.println("}");
					
				out.println("function save_data(){");			
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_Indicative_Quatation_Letter?chksql=view_letter&data_val="+m_qutation+"&ac_status=Y\";");
				out.println(" window.location.href=m_url;");		
				out.println("m_table.innerHTML=\"\" ");		
				out.println("window.print();");		
				out.println("}");
				
				out.println("function displaymsg() {");
				out.println("}</SCRIPT></HEAD>");
				out.println("<body onload='displaymsg(),add_button();'>");
				
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>");
					
				out.println("</body>");
				out.println("</html>");
				}				
			*/
			}catch(Exception e)
			{
				ByteArrayOutputStream ostrtest = new ByteArrayOutputStream();
				e.printStackTrace(new PrintStream(ostrtest));
				out.println(ostrtest.toString());
			}
		}
		//--------------------------------------------------------------------------------------			
		
		else if (m_chksql.trim().equals("m_price")) {
			String m_qutation = req.getParameter("data_val").trim();
			String m_px = req.getParameter("price_no1").trim();
			
			out.println("<html><head><font 10pt arial><title>Pricing Details</title></head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function price(){");
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_Price?chksql=main_page&pricing_no="+m_px+"';"); 
			
			out.println("window.open(m_url,'displayWindow3','left=0,top=133,width=900,height=700,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
			out.println("}</SCRIPT>");
			
			out.println("<body leftmargin='0' topmargin='0' class=body>");
			out.println("<br>");
			out.println("<br>");
			out.println("<div align='center' width='180%' class='txt-body' style='{font: bold;text-align:center;}'>Pricing Details</div>");
			out.println("<form name='form1'>");
			out.println("<table border='1' width='100%' bgcolor='white' style='{color: black; font: bold 10px;}'>");
			out.println("</table>");			
			out.println("<HR width='100%'>");
			out.println("<br>");
			
			
			rs=stmt.executeQuery("SELECT A.QUOTATION_NO,C.INQUIRY_NO,A.PRICING_NO,RATE,A.PERIOD "+
				"FROM "+m_schema_name+".AF_MK_PRO_QUOTATION_DET A,"+m_schema_name+".AF_MK_PRO_PRICING B,LAKDL.AF_MK_PRO_QUOTATION C "+
				"WHERE A.QUOTATION_NO=C.QUOTATION_NO AND A.PRICING_NO=B.PRICING_NO AND A.QUOTATION_NO =UPPER('"+ m_qutation +"') ");
			
			boolean more=rs.next();	
			
			
			while(more)
			{
				m_price=rs.getString(1);
				m_pricing=rs.getString(3);
				
				
				out.println("<table border='1' width='100%' class='table'>");
				out.println("<tr><td width='50%' class='txt-body' style='{text-align:center;}'><b>Pricing No </td><td width='30%' class='txt-body' style='{text-align:center;}'><b>Rate </td><td width='30%' class='txt-body' style='{text-align:center;}'><b>Period </td></tr>");
				out.println("</table>");
				
				while(rs.getString(1).trim().equals(m_price)){
					out.println("<table border='1' width='100%' class='table'>");
					
					out.println("<tr>");
					out.println("<td width='50%' class='txt-body' style='{text-align:center;}'>"+rs.getString(3)+"</td>");
					out.println("<td width='30%' class='txt-body' style='{text-align:center;}'>"+rs.getString(4)+"</td>");
					out.println("<td width='30%' class='txt-body' style='{text-align:center;}'>"+rs.getString(5)+"</td>");
					
					out.println("</tr>");
					out.println("</table>");
					
					out.println("<table width='100%' cellpadding='1' border=\"0\">");	
					out.println("<tr><td width='20%' align='center'>");
					
					out.println("<input type='button' class='but_input' name='Exit'  value=' Pricing ' OnClick=\"price()\";></td></tr>");
					out.println("</table>");		
					
					more=rs.next();
					if (!more)
					{
						break;
					}
				}
				
				if (more)
				{
					m_price=rs.getString(1);
				}
			}
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");
		}
		
		
		
		
		//--------------------------------------------------------------------------------------			
		
		else {
			out.println("Undefined");
		}
		
		out.close();
		conn.close();
		this.destroy();
		
		
	}
	catch (Exception e) {
		try {
			conn.close();
		}catch (Exception eti) {}
		
		ByteArrayOutputStream ostr = new ByteArrayOutputStream();
		e.printStackTrace(new PrintWriter(ostr));
		
		ServletOutputStream out = res.getOutputStream();
		out.println(ostr.toString());
		out.close();
		
	}
}
}

