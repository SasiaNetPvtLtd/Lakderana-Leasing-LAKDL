//ID         :
//SCREEN NAME:Document Printing - Asset Replacement Document 1
//CREATED BY :Sandun Jayathilake	
//DATE/TIME  : 19-11-2008
//NOTES:

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_CR_PRO_Document_Asset_Replace_2 extends javax.servlet.http.HttpServlet { 

 ServletOutputStream out = null;
	
	
	Connection conn;
	Statement stmt,stmt2,stmt3,stmt4,stmt1;
	java.text.NumberFormat nf;
	
  public ResultSet rs,rs2,rs3,rs4,rs_partner;
 	

	public String m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_due_date,m_no_of_due_date,m_finance_no,m_print;
	public double m_amount_due;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
		
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
		  nf.setMinimumFractionDigits(2);
		  nf.setMaximumFractionDigits(2);
						
			String m_full_name="";
			String m_add1="";
			String m_add2="";
			String m_city_name="";
			String m_title="";
			String m_client_type=""; 
			String m_start_date="";
			String m_partner_name [];
			int m_data_count=0;
		  String m_status ="";
			String m_nic = "";
			String m_client_catogery ="";
			
			
			String m_orient_name="";
			String m_orient_add1="";
			String m_orient_add2="";
			String m_orient_city_name="";
			String m_orient_tel_no="";
			String m_orient_fax_no="";
			String m_orient_vat_rate="";
			
			String m_master_no = "";
			String m_eng_no="";
			String m_cha_no="";
			String m_reg_no="";
			String m_discrp="";
			
			String m_prv_eng_no="";
			String m_prv_cha_no="";
			String m_prv_reg_no="";
			String m_prv_discrp="";
			String m_prv_act_date  ="";
			
						
		 String m_chksql = req.getParameter("chksql");
			
  	 if(m_chksql.trim().equals("main_page")){
							
			stmt = conn.createStatement ();
			stmt1 = conn.createStatement ();
			stmt2 = conn.createStatement ();
			stmt3 = conn.createStatement ();
			stmt4 = conn.createStatement ();
									
			String m_application_no = req.getParameter("application_no");
			String m_client_code	  =req.getParameter("client_code");		
			String m_document_code	=req.getParameter("document_code");	
			String m_print=req.getParameter("print");
			
			
			rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'fmddth') || ' ' || TO_CHAR(SYSDATE, 'Month')|| TO_CHAR(SYSDATE, 'YYYY') LETTER_DATE FROM DUAL ");
				            
			boolean more = rs.next();
			if(more){
			m_Letter_date=rs.getString(1);
			}
				
		if(req.getParameter("status")==null){
							
		 rs=stmt.executeQuery (" SELECT "+
		" COUNT(DOCUMENT_CODE) "+
		" FROM "+m_schema_name+".AF_CR_PRO_DOCUMENT_STATUS "+
		" WHERE APPLICATION_NO=UPPER('"+m_application_no+"') AND CLIENT_CODE=UPPER('"+m_client_code+"') AND DOCUMENT_CODE=UPPER('"+m_document_code+"') ");

    		more = rs.next();
				if(more){
			  m_data_count=rs.getInt(1);
			  }
				
				if(m_data_count==0){
				m_status="ORIGINAL";
				}
				else{
				m_status="COPY";
				}
			
			}
			else
			{
			m_status=req.getParameter("status");
			}
			
			
					 rs = stmt.executeQuery(" SELECT "+
					    " NVL(UPPER(COMPANY_NAME),' '), "+
					    " NVL(UPPER(ADDRESS1),' '), "+
					    " NVL(UPPER(ADDRESS2),' '), "+
					    " NVL(UPPER(CITY),' '), "+
					    " NVL(TEL_NO,' '), "+
					    " NVL(FAX_NO,' '),  "+
							" NVL(VAT_RATE,0) "+
							" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ");

							  more = rs.next();		
											
											if(more)
											{
											m_orient_name=rs.getString(1);
											m_orient_add1=rs.getString(2);
											m_orient_add2=rs.getString(3);
											m_orient_city_name=rs.getString(4);
											m_orient_tel_no=rs.getString(5);
											m_orient_fax_no=rs.getString(6);
											m_orient_vat_rate=rs.getString(7);			
											}
				
		
					
					
					  rs = stmt.executeQuery(" SELECT "+
        												" NVL(UPPER(TITLE),''), "+
																" DECODE(CLIENT_TYPE,'I',NVL(UPPER(TITLE),' ')||' '||NVL(UPPER(FULL_NAME),' '),'C',NVL(UPPER(FULL_NAME),' ')), "+ 
																" REPLACE(REPLACE(NVL(UPPER(ADDRESS1),' '),'-',' '),'null',' '),"+
																" REPLACE(REPLACE(NVL(UPPER(ADDRESS2),' '),'-',' '),'null',' '),"+
                                " NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),' '), "+
																" NVL(NVL(NIC_NO,BUSINESS_CERTIFICATE_NO),'-'),CLIENT_TYPE,CLIENT_CATEGORY"+
																" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
                                " WHERE CLIENT_CODE = "+
                                " (SELECT "+
		                            " CLIENT_CODE "+
                                " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
                                " WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"'))");
    
			 more = rs.next();		
				
				if(more){
				m_title=rs.getString(1);
				m_full_name=rs.getString(2);
				m_add1=rs.getString(3);
				m_add2=rs.getString(4);
				m_city_name=rs.getString(5);							
				m_nic = rs.getString(6);
				m_client_type = rs.getString(7);
				m_client_catogery = rs.getString(8);
				}
				
				if(!m_full_name.equals(" "))
				{
				m_full_name=m_full_name+",";
				}
				
				if(!m_add1.equals(" "))
				{
				m_add1=m_add1+",";
				}
				if(!m_add2.equals(" "))
				{
				m_add2=m_add2+",";
				}		
				
				 rs=stmt.executeQuery (" SELECT "+
        "  NVL(FINANCE_NO,'-'), "+
				"  TO_CHAR(ACTIVATED_DATE, 'fmddth') || ' ' || TO_CHAR(ACTIVATED_DATE, 'Month')|| TO_CHAR(ACTIVATED_DATE, 'YYYY') START_DATE ,"+
				"  NVL(MASTER_AGREEMENT_NO ,'-') "+
				"  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
        "  WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ");
				
				more = rs.next();
			
			if(more){
			  m_finance_no=rs.getString(1);
				m_start_date=rs.getString(2);
			  m_master_no = rs.getString(3);
			  }
				
				
				  rs_partner=stmt1.executeQuery (" SELECT A.NAME,count(*) "+
															  " FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DIRECTORS A "+
															  " WHERE CLIENT_CODE = "+
                                " (SELECT "+
		                            " CLIENT_CODE "+
                                " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
                                " WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"')) group by A.NAME");
				 
					
					if(m_client_catogery.equals("PARTNERS")){
					
			  out.println("<html><head>"); 
				out.println("<title>Asset Replacement Letter</title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  
			
  		
		   out.println("<script>");
			
			out.println("function save_data(){");			  
			out.println("m_table.innerHTML=\"\" ");		
			out.println("window.print();");
			out.println("}");		
		
			out.println("function add_button(){");
			//out.println("alert(document.Form1.hid_client_type.value);");
			if (m_print.trim().equals("FALSE")) {
			out.println("m_table.innerHTML=\"\" ");
			out.println("if(document.Form1.hid_client_type.value=='C'){");
			out.println("m_letter='<tr><td width=\"*%\" align=\"center\" class=\"rep-body1\" ><b>To be typed on company letter head</b></td></tr>';"); 
	  	out.println("		}else{");
			out.println("m_letter=\"\" ");
			out.println("		}");
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_letter+'</table>';");
			}
			else
			{
			out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';");
			out.println("if(document.Form1.hid_client_type.value=='C'){");
			out.println("m_letter='<tr><td width=\"*%\" align=\"center\" class=\"rep-body1\" ><b>To be typed on company letter head</b></td></tr>';"); 
	  	out.println("		}else{");
			out.println("m_letter=\"\" ");
			out.println("		}");
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_writedata+m_letter+'</table>';");
			}
			out.println("}");
			
			out.println("</script>");
			out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");
			out.println("<body bgcolor='white'><br>");
							
			out.println("<form name='Form1'>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_client_type' VALUE=\""+m_client_type+"\">");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		  out.println("</tr>"); 
			out.println("</table>");			
			
			
																
					//get proposed Asset											
					rs=stmt.executeQuery (" SELECT "+
					        " nvl(B.ENGINE_NO,'-'), "+
									" nvl(B.CHASSIS_NO,'-'), "+
		              " NVL(B.REG_NO,'-'), "+								 
									" INITCAP(C.MAKE_DESC||' '||D.DESCRIPTION ||' '||H.DESCRIPTION) MODEL_DESC, "+ 
								  " NVL(D.YEAR_OF_MANUFACTURE,''), "+
								  " NVL(B.SUB_MODEL_CODE,' '), "+ 
								  " INITCAP(H.DESCRIPTION) ITEM_SUB_CAT_DESC, "+
								  " C.MAKE_CODE, "+
								  " F.ITEM_SUB_CAT,"+
								  " UPPER(E.VENDOR_CODE),"+
								  " UPPER(E.BRANCH), "+
								  " INITCAP(G.NAME) "+
								  " FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS A, "+
								  " "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+
								  " "+m_schema_name+".AF_CO_MAS_MAKE C, "+
								  " "+m_schema_name+".AF_CO_MAS_SUB_MODLE D, "+
								  " "+m_schema_name+".AF_CO_MAS_VENDOR_LOCATION E, "+
								  " "+m_schema_name+".AF_CO_MAS_MODEL F , "+
								  " "+m_schema_name+".AF_CO_MAS_VENDORS G , "+
								  " "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY H "+
								  " WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
								  " A.ACTIVE_STATUS='Y' AND "+
								  " B.ACTIVE_STATUS='Y' AND "+
								  " A.APPLICATION_NO=UPPER('"+m_application_no+"') AND "+
								  " A.ASSET_ID=B.ASSET_ID AND "+
								  " C.MAKE_CODE=(SELECT "+
								  " MAKE_CODE "+
								  " FROM "+m_schema_name+".AF_CO_MAS_MODEL "+
								  " WHERE "+
								  " MODEL_CODE IN ( SELECT "+
								  " MODEL_CODE "+
								  " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
								  " WHERE INVOICE_NO=B.INVOICE_NO AND B.ACTIVE_STATUS='Y' "+
								  " )) AND "+
								  " D.SUB_CODE=B.SUB_MODEL_CODE AND "+
								  " UPPER(E.VENDOR_CODE) =UPPER(B.VENDOR_CODE) AND "+
								  " UPPER(G.VENDOR_CODE) =UPPER(B.VENDOR_CODE) AND "+
								  " UPPER(F.ITEM_SUB_CAT) =UPPER(H.ITEM_SUB_CAT) AND "+
								  " UPPER(E.BRANCH)=UPPER(B.BRANCH_ID) AND "+
								  " B.MODEL_CODE=F.MODEL_CODE "+
									" AND B.ENT_DATE IN (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
							    " GROUP BY APPLICATION_NO) ");
																				
				if(rs.next()){ 
				  m_eng_no=rs.getString(1);
					m_cha_no=rs.getString(2);
					m_reg_no=rs.getString(3);
				  m_discrp=rs.getString(4);
				  }
				
		//get previouse Asset		
				   rs3=stmt3.executeQuery (" SELECT   nvl(B.ENGINE_NO,'-'), "+
															   " nvl(B.CHASSIS_NO,'-'), "+
									               " NVL(B.REG_NO,'-'), "+ 								 
															   " INITCAP(C.MAKE_DESC||' '||D.DESCRIPTION ||' '||H.DESCRIPTION) MODEL_DESC,  "+
															   " NVL(D.YEAR_OF_MANUFACTURE,''), "+
															   " NVL(B.SUB_MODEL_CODE,' '),  "+
															   " INITCAP(H.DESCRIPTION) ITEM_SUB_CAT_DESC, "+
															   " C.MAKE_CODE, "+
															   " F.ITEM_SUB_CAT, "+
															   " UPPER(E.VENDOR_CODE), "+
															   " UPPER(E.BRANCH), "+
															   " INITCAP(G.NAME) "+
															   " FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS A, "+ 
															   " "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DET_BK B, "+
															   " "+m_schema_name+".AF_CO_MAS_MAKE C, "+
															   " "+m_schema_name+".AF_CO_MAS_SUB_MODLE D, "+
															   " "+m_schema_name+".AF_CO_MAS_VENDOR_LOCATION E, "+
															   " "+m_schema_name+".AF_CO_MAS_MODEL F , "+
															   " "+m_schema_name+".AF_CO_MAS_VENDORS G , "+
															   " "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY H "+
															   " WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
															   " A.ACTIVE_STATUS='Y' AND "+
															   " B.ACTIVE_STATUS='Y' AND "+
															   " A.APPLICATION_NO=UPPER('"+m_application_no+"') AND "+
															   " A.ASSET_ID=B.ASSET_ID AND "+
															   " C.MAKE_CODE=(SELECT "+
															   " MAKE_CODE "+
															   " FROM "+m_schema_name+".AF_CO_MAS_MODEL "+
															   " WHERE "+
															   " MODEL_CODE IN ( SELECT "+
															   " MODEL_CODE "+
															   " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DET_BK "+
															   " WHERE INVOICE_NO=B.INVOICE_NO AND B.ACTIVE_STATUS='Y' "+
															   " )) AND "+
															   " D.SUB_CODE=B.SUB_MODEL_CODE AND "+
															   " UPPER(E.VENDOR_CODE) =UPPER(B.VENDOR_CODE) AND "+
															   " UPPER(G.VENDOR_CODE) =UPPER(B.VENDOR_CODE) AND "+
															   " UPPER(F.ITEM_SUB_CAT) =UPPER(H.ITEM_SUB_CAT) AND "+
															   " UPPER(E.BRANCH)=UPPER(B.BRANCH_ID) AND "+
															   " B.MODEL_CODE=F.MODEL_CODE "+
							                   " AND 	B.ENT_DATE IN (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DET_BK "+
							                   " GROUP BY APPLICATION_NO) ");
				
				
				if(rs3.next()){ 
				  m_prv_eng_no=rs3.getString(1);
					m_prv_cha_no=rs3.getString(2);
					m_prv_reg_no=rs3.getString(3);
				  m_prv_discrp=rs3.getString(4);
				  }
				
				//get previouse activated date
					rs4=stmt4.executeQuery (" SELECT A.APPLICATION_NO,NVL(TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY'),'-') "+
											        		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAI_BK A "+
																	//" WHERE UPPER(A.APPLICATION_NO) =UPPER('"+m_application_no+"') "+
																	" WHERE A.APPLICATION_NO =UPPER('"+m_application_no+"') "+
																	" AND A.MOD_DATE=(SELECT MAX(A.MOD_DATE) "+
																	" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAI_BK A "+
																	//" WHERE UPPER(A.APPLICATION_NO) =UPPER('"+m_application_no+"') ) " );
																	" WHERE A.APPLICATION_NO =UPPER('"+m_application_no+"') ) " );
				
				int j=1;
				if(rs4.next()){
				m_prv_act_date = rs4.getString(2);
				}
				
				boolean more_gur = rs_partner.next();
				
      while(more_gur){
			
			
			out.println("<blockquote><font size=3><p style='text-align:left'>");
			
				if(rs_partner.getInt(2)>j && j>1){
			out.println("   <p style=\"page-break-after:always\"></p>");	
			
			}
/*
		  if(m_client_type.equals("I")){
			out.println("<table border='0' width='90%' class='table' align='center'>"); 		
			out.println("<tr><td width='*%'>&nbsp;</td><td width='35%' class='rep-body1' >"+m_full_name+"</td></tr>");
			out.println("<tr><td width='*%'>&nbsp;</td><td width='35%' class='rep-body1' >"+m_add1+"</td></tr>");
			out.println("<tr><td width='*%'>&nbsp;</td><td width='35%' class='rep-body1' >"+m_add2+"</td></tr>");
			out.println("<tr><td width='*%'>&nbsp;</td><td width='35%' class='rep-body1' >"+m_city_name+".</td></tr>");
			out.println("</table>");
			}else{
			*/
			out.println("<br>");
			out.println("<br>");
			out.println("<br>");	
			out.println("<br>");
			out.println("<br>");
			out.println("<br>");
			
			
			
						
			out.println("<table border='0' width='90%' class='table' align='center'>"); 		
			out.println("<tr><td width='30%' class='rep-body1' >Date: "+m_Letter_date+"</td><td width='*%'>&nbsp;</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table' align='center'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' >Lakderana  Investments  Limited</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' >No. 100,Buthgamuwa Road,</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' >Rajagiriya</td></tr>");
			out.println("</table>");
												
			out.println("<br>");
			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' >Dear Sirs,</td></tr>");
			out.println("</table>");
						
			out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table' align='center'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' >VEHICLE NO : "+m_reg_no+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' ><U>LEASE AGREEMENT SCHEDULE NO : "+m_finance_no+" DATED : "+m_start_date+"</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' >As notified to you previously the above vehicle bearing <b>Engine No. "+m_prv_eng_no+", Chassis No. "+m_prv_cha_no+"</b> & <b>Reg. No: "+m_prv_reg_no+"</b>(which was "+
									"leased out to us/me by you under and in terms of the Lease Agreement Schedule No: "+m_finance_no+" dated "+m_prv_act_date+") is not in good working condition and needs replacement. "+
									"I/We now seek your formal approval in terms of Article 16 (2) (b) of the said Master Lease Agreement No: "+m_master_no+" to replace  the said vehicle with the under mentioned vehicle in good repair, condition "+
									"and working order.</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table' align='center'>"); 		
			out.println("<tr><td width='20%' class='rep-body1' ><b>MAKE & MODLE</b></td><td width='5%' class='rep-body1' >:</td><td width='*%' class='rep-body1' >"+m_discrp+"</td></tr>");
			out.println("<tr><td width='20%' class='rep-body1' ><b>REG NO</td><td width='5%' class='rep-body1' >:</td><td width='*%' class='rep-body1' >"+m_reg_no+"</td></tr>");
			out.println("<tr><td width='20%' class='rep-body1' ><b>ENGINE NO</td><td width='5%' class='rep-body1' >:</td><td width='*%' class='rep-body1' >"+m_eng_no+"</td></tr>");
			out.println("<tr><td width='20%' class='rep-body1' ><b>CHASSIS NO</td><td width='5%' class='rep-body1' >:</td><td width='*%' class='rep-body1' >"+m_cha_no+"</td></tr>");
			out.println("</table>");		
			
			out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' >I/We confirm that should the above replacement be acceptable to you, the Lease Agreement will, in terms of Article 16(4),continue "+
		             	"without any amendments and in particular the rentals stipulated under the Master Lease Agreement No: "+m_master_no+" shall continue to be payable.</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' >We further agree and undertake that the remaining clauses of the Lease Agreement will be binding & operative with respect to the said "+
		             	"vehicle so replaced and I/We further agree & undertake to indemnify & save you harmless from any claim arising due to the replacement of said vehicle in terms of clause 16 2(b).</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' >If the foregoing is acceptable to you, please return the duplicate copy of this letter duly signed to signify your approval. "+
		             	"</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' >Yours faithfully,</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' >&nbsp;</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' >..........................................................</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' >"+rs_partner.getString(1)+"</td></tr>");
			out.println("</table>");
			
			/*out.println("<br>");			
			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' >NIC NO : "+m_nic+"</td></tr>");
			out.println("</table>");	
			*/
			out.println("<br>");	
			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' >We agree.</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' >&nbsp;</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' >....................................&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;....................................</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table' align='center'>"); 		
			out.println("<tr><td width='50%' class='rep-body1' >Lakderana  Investments  Limited</td><td width='*%'>&nbsp;</td></tr>");
			out.println("</table>");
			
			//out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table' align='center'>"); 		
			out.println("<tr><td width='30%' class='rep-body1' >Date: "+m_Letter_date+"</td><td width='*%'>&nbsp;</td></tr>");
			out.println("</table>");
    		
		  out.println("</font></p></blockquote>");				
			
		
			j=j+1;
			more_gur = rs_partner.next();
		}
			out.println("</form>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>");
		  out.println("</body></html>");
			
			}
			
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
			else{
			
			out.println("<html><head>"); 
				out.println("<title>Asset Replacement Letter</title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  
			
  		
		   out.println("<script>");
			
			out.println("function save_data(){");			  
			out.println("m_table.innerHTML=\"\" ");		
			out.println("window.print();");
			out.println("}");		
		
			out.println("function add_button(){");
			if (m_print.trim().equals("FALSE")) {
			out.println("m_table.innerHTML=\"\" ");
			out.println("if(document.Form1.hid_client_type.value=='C'){");
			out.println("m_letter='<tr><td width=\"*%\" align=\"center\" class=\"rep-body1\" ><b>To be typed on company letter head</b></td></tr>';"); 
	  	out.println("		}else{");
			out.println("m_letter=\"\" ");
			out.println("		}");
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_letter+'</table>';");
			}
			else
			{
			out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';");
			out.println("if(document.Form1.hid_client_type.value=='C'){");
			out.println("m_letter='<tr><td width=\"*%\" align=\"center\" class=\"rep-body1\" ><b>To be typed on company letter head</b></td></tr>';"); 
	  	out.println("		}else{");
			out.println("m_letter=\"\" ");
			out.println("		}");
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_writedata+m_letter+'</table>';");
			}
			out.println("}");
			
			out.println("</script>");
			out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");
			out.println("<body bgcolor='white'><br>");
							
			out.println("<form name='Form1'>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_client_type' VALUE=\""+m_client_type+"\">");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		  out.println("</tr>"); 
			out.println("</table>");			
			
			
																
					//get proposed Asset											
					rs=stmt.executeQuery (" SELECT "+
					        " nvl(B.ENGINE_NO,'-'), "+
									" nvl(B.CHASSIS_NO,'-'), "+
		              " NVL(B.REG_NO,'-'), "+								 
									" INITCAP(C.MAKE_DESC||' '||D.DESCRIPTION ||' '||H.DESCRIPTION) MODEL_DESC, "+ 
								  " NVL(D.YEAR_OF_MANUFACTURE,''), "+
								  " NVL(B.SUB_MODEL_CODE,' '), "+ 
								  " INITCAP(H.DESCRIPTION) ITEM_SUB_CAT_DESC, "+
								  " C.MAKE_CODE, "+
								  " F.ITEM_SUB_CAT,"+
								  " UPPER(E.VENDOR_CODE),"+
								  " UPPER(E.BRANCH), "+
								  " INITCAP(G.NAME) "+
								  " FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS A, "+
								  " "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+
								  " "+m_schema_name+".AF_CO_MAS_MAKE C, "+
								  " "+m_schema_name+".AF_CO_MAS_SUB_MODLE D, "+
								  " "+m_schema_name+".AF_CO_MAS_VENDOR_LOCATION E, "+
								  " "+m_schema_name+".AF_CO_MAS_MODEL F , "+
								  " "+m_schema_name+".AF_CO_MAS_VENDORS G , "+
								  " "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY H "+
								  " WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
								  " A.ACTIVE_STATUS='Y' AND "+
								  " B.ACTIVE_STATUS='Y' AND "+
								  " A.APPLICATION_NO=UPPER('"+m_application_no+"') AND "+
								  " A.ASSET_ID=B.ASSET_ID AND "+
								  " C.MAKE_CODE=(SELECT "+
								  " MAKE_CODE "+
								  " FROM "+m_schema_name+".AF_CO_MAS_MODEL "+
								  " WHERE "+
								  " MODEL_CODE IN ( SELECT "+
								  " MODEL_CODE "+
								  " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
								  " WHERE INVOICE_NO=B.INVOICE_NO AND B.ACTIVE_STATUS='Y' "+
								  " )) AND "+
								  " D.SUB_CODE=B.SUB_MODEL_CODE AND "+
								  " UPPER(E.VENDOR_CODE) =UPPER(B.VENDOR_CODE) AND "+
								  " UPPER(G.VENDOR_CODE) =UPPER(B.VENDOR_CODE) AND "+
								  " UPPER(F.ITEM_SUB_CAT) =UPPER(H.ITEM_SUB_CAT) AND "+
								  " UPPER(E.BRANCH)=UPPER(B.BRANCH_ID) AND "+
								  " B.MODEL_CODE=F.MODEL_CODE "+
									" AND B.ENT_DATE IN (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
							    " GROUP BY APPLICATION_NO) ");
																				
				if(rs.next()){ 
				  m_eng_no=rs.getString(1);
					m_cha_no=rs.getString(2);
					m_reg_no=rs.getString(3);
				  m_discrp=rs.getString(4);
				  }
				
		//get previouse Asset		
				   rs3=stmt3.executeQuery (" SELECT   nvl(B.ENGINE_NO,'-'), "+
															   " nvl(B.CHASSIS_NO,'-'), "+
									               " NVL(B.REG_NO,'-'), "+ 								 
															   " INITCAP(C.MAKE_DESC||' '||D.DESCRIPTION ||' '||H.DESCRIPTION) MODEL_DESC,  "+
															   " NVL(D.YEAR_OF_MANUFACTURE,''), "+
															   " NVL(B.SUB_MODEL_CODE,' '),  "+
															   " INITCAP(H.DESCRIPTION) ITEM_SUB_CAT_DESC, "+
															   " C.MAKE_CODE, "+
															   " F.ITEM_SUB_CAT, "+
															   " UPPER(E.VENDOR_CODE), "+
															   " UPPER(E.BRANCH), "+
															   " INITCAP(G.NAME) "+
															   " FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS A, "+ 
															   " "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DET_BK B, "+
															   " "+m_schema_name+".AF_CO_MAS_MAKE C, "+
															   " "+m_schema_name+".AF_CO_MAS_SUB_MODLE D, "+
															   " "+m_schema_name+".AF_CO_MAS_VENDOR_LOCATION E, "+
															   " "+m_schema_name+".AF_CO_MAS_MODEL F , "+
															   " "+m_schema_name+".AF_CO_MAS_VENDORS G , "+
															   " "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY H "+
															   " WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
															   " A.ACTIVE_STATUS='Y' AND "+
															   " B.ACTIVE_STATUS='Y' AND "+
															   " A.APPLICATION_NO=UPPER('"+m_application_no+"') AND "+
															   " A.ASSET_ID=B.ASSET_ID AND "+
															   " C.MAKE_CODE=(SELECT "+
															   " MAKE_CODE "+
															   " FROM "+m_schema_name+".AF_CO_MAS_MODEL "+
															   " WHERE "+
															   " MODEL_CODE IN ( SELECT "+
															   " MODEL_CODE "+
															   " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DET_BK "+
															   " WHERE INVOICE_NO=B.INVOICE_NO AND B.ACTIVE_STATUS='Y' "+
															   " )) AND "+
															   " D.SUB_CODE=B.SUB_MODEL_CODE AND "+
															   " UPPER(E.VENDOR_CODE) =UPPER(B.VENDOR_CODE) AND "+
															   " UPPER(G.VENDOR_CODE) =UPPER(B.VENDOR_CODE) AND "+
															   " UPPER(F.ITEM_SUB_CAT) =UPPER(H.ITEM_SUB_CAT) AND "+
															   " UPPER(E.BRANCH)=UPPER(B.BRANCH_ID) AND "+
															   " B.MODEL_CODE=F.MODEL_CODE "+
							                   " AND 	B.ENT_DATE IN (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DET_BK "+
							                   " GROUP BY APPLICATION_NO) ");
				
				
				if(rs3.next()){ 
				  m_prv_eng_no=rs3.getString(1);
					m_prv_cha_no=rs3.getString(2);
					m_prv_reg_no=rs3.getString(3);
				  m_prv_discrp=rs3.getString(4);
				  }
				
				//get previouse activated date
					rs4=stmt4.executeQuery (" SELECT A.APPLICATION_NO,NVL(TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY'),'-') "+
											        		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAI_BK A "+
																	//" WHERE UPPER(A.APPLICATION_NO) =UPPER('"+m_application_no+"') "+
																	" WHERE A.APPLICATION_NO =UPPER('"+m_application_no+"') "+
																	" AND A.MOD_DATE=(SELECT MAX(A.MOD_DATE) "+
																	" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAI_BK A "+
																	//" WHERE UPPER(A.APPLICATION_NO) =UPPER('"+m_application_no+"') ) " );
																	" WHERE A.APPLICATION_NO =UPPER('"+m_application_no+"') ) " );
				
				int j=1;
				if(rs4.next()){
				m_prv_act_date = rs4.getString(2);
				}
								
			
			out.println("<blockquote><font size=3><p style='text-align:left'>");					

		  if(m_client_type.equals("I")){
			out.println("<table border='0' width='90%' class='table' align='center'>"); 		
			out.println("<tr><td width='*%'>&nbsp;</td><td width='35%' class='rep-body1' >"+m_full_name+"</td></tr>");
			out.println("<tr><td width='*%'>&nbsp;</td><td width='35%' class='rep-body1' >"+m_add1+"</td></tr>");
			out.println("<tr><td width='*%'>&nbsp;</td><td width='35%' class='rep-body1' >"+m_add2+"</td></tr>");
			out.println("<tr><td width='*%'>&nbsp;</td><td width='35%' class='rep-body1' >"+m_city_name+".</td></tr>");
			out.println("</table>");
			}else{
			out.println("<br>");
			out.println("<br>");
			out.println("<br>");
			}
			out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table' align='center'>"); 		
			out.println("<tr><td width='30%' class='rep-body1' >Date: "+m_Letter_date+"</td><td width='*%'>&nbsp;</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table' align='center'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' >Lakderana  Investments  Limited</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' >No. 100,Buthgamuwa Road,</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' >Rajagiriya</td></tr>");
			out.println("</table>");
												
			out.println("<br>");
			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' >Dear Sirs,</td></tr>");
			out.println("</table>");
						
			out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table' align='center'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' >VEHICLE NO : "+m_reg_no+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' ><U>LEASE AGREEMENT SCHEDULE NO : "+m_finance_no+" DATED : "+m_start_date+"</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' >As notified to you previously the above vehicle bearing <b>Engine No. "+m_prv_eng_no+", Chassis No. "+m_prv_cha_no+"</b> & <b>Reg. No: "+m_prv_reg_no+"</b>(which was "+
									"leased out to us/me by you under and in terms of the Lease Agreement Schedule No: "+m_finance_no+" dated "+m_prv_act_date+") is not in good working condition and needs replacement. "+
									"I/We now seek your formal approval in terms of Article 16 (2) (b) of the said Master Lease Agreement No: "+m_master_no+" to replace  the said vehicle with the under mentioned vehicle in good repair, condition "+
									"and working order.</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table' align='center'>"); 		
			out.println("<tr><td width='20%' class='rep-body1' ><b>MAKE & MODLE</b></td><td width='5%' class='rep-body1' >:</td><td width='*%' class='rep-body1' >"+m_discrp+"</td></tr>");
			out.println("<tr><td width='20%' class='rep-body1' ><b>REG NO</td><td width='5%' class='rep-body1' >:</td><td width='*%' class='rep-body1' >"+m_reg_no+"</td></tr>");
			out.println("<tr><td width='20%' class='rep-body1' ><b>ENGINE NO</td><td width='5%' class='rep-body1' >:</td><td width='*%' class='rep-body1' >"+m_eng_no+"</td></tr>");
			out.println("<tr><td width='20%' class='rep-body1' ><b>CHASSIS NO</td><td width='5%' class='rep-body1' >:</td><td width='*%' class='rep-body1' >"+m_cha_no+"</td></tr>");
			out.println("</table>");		
			
			out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' >I/We confirm that should the above replacement be acceptable to you, the Lease Agreement will, in terms of Article 16(4),continue "+
		             	"without any amendments and in particular the rentals stipulated under the Master Lease Agreement No: "+m_master_no+" shall continue to be payable.</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' >We further agree and undertake that the remaining clauses of the Lease Agreement will be binding & operative with respect to the said "+
		             	"vehicle so replaced and I/We further agree & undertake to indemnify & save you harmless from any claim arising due to the replacement of said vehicle in terms of clause 16 2(b).</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' >If the foregoing is acceptable to you, please return the duplicate copy of this letter duly signed to signify your approval. "+
		             	"</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' >Yours faithfully,</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' >&nbsp;</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' >..........................................................</td></tr>");
			if(m_client_type.equals("I")){
			out.println("<tr><td width='*%' class='rep-body1' >"+m_full_name+"</td></tr>");
			}
			out.println("</table>");
			
			/*out.println("<br>");			
			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' >NIC NO : "+m_nic+"</td></tr>");
			out.println("</table>");	
			*/
			out.println("<br>");	
			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' >We agree.</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' >&nbsp;</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' >....................................&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;....................................</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table' align='center'>"); 		
			out.println("<tr><td width='50%' class='rep-body1' >Lakderana Investments Limited</td><td width='*%'>&nbsp;</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table' align='center'>"); 		
			out.println("<tr><td width='30%' class='rep-body1' >Date: "+m_Letter_date+"</td><td width='*%'>&nbsp;</td></tr>");
			out.println("</table>");
    		
		  out.println("</font></p></blockquote>");				
			
	
			out.println("</form>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>");
		  out.println("</body></html>");		
			
			
			}
			
			out.flush();
			}
			
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
