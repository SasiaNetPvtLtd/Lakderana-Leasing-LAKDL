//ID         :
//SCREEN NAME:Document Printing - Individual Delivery Order
//CREATED BY :Chandana 
//DATE/TIME  : 08-06-2007
//NOTES:

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_CR_PRO_Document_Hirepur_ind_delivery_order extends javax.servlet.http.HttpServlet { 

 ServletOutputStream out = null;
	
	
	Connection conn;
	Statement stmt;
//	CallableStatement callstmt1;
	java.text.NumberFormat nf;
	
  public ResultSet rs;
 	

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
		//	String m_chksql;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
		  nf.setMinimumFractionDigits(2);
		  nf.setMaximumFractionDigits(2);
			
		// out.println("conn"+conn);
		
		
				//Decaring variables
			
			String m_full_name="";
			String m_add1="";
			String m_add2="";
			String m_city_name="";
			String m_title="";
			String m_type=""; 
			int m_data_count=0;
		  String m_status ="";
						String m_ven_name="",m_ven_add="",m_ven_city="",m_ven_type="";

			
			String m_orient_name="";
			String m_orient_add1="";
			String m_orient_add2="";
			String m_orient_city_name="";
			String m_orient_tel_no="";
			String m_orient_fax_no="";
			String m_orient_vat_rate="",m_orient_reg_no="";
			
			String m_Eng_no="";
			String m_Chas_no="";
			String m_Reg_no="";
			String m_Color="";
			String m_Descr="";
			String m_Extras="";
			double m_Net_Amt=0;
			String m_Amount_wd=""; 
         double m_Qty=0; //Added by Chandana on 26/08/2010
         double m_tot_amt=0; //Added by Chandana on 26/08/2010
			String m_Qty2=""; //Added by Chandana on 26/08/2010
			
						
		 String m_chksql = req.getParameter("chksql");
			
  	 if(m_chksql.trim().equals("main_page")){
			
			  
				
			stmt = conn.createStatement ();
						
			String m_application_no = req.getParameter("application_no");
			String m_client_code	  =req.getParameter("client_code");		
			String m_purch_order    =req.getParameter("pur_ord_no");	 
			String m_document_code	=req.getParameter("document_code");	
			String m_print=req.getParameter("print");
			String m_vendor_code	=req.getParameter("vendor_code");		
			String m_branch_code  =req.getParameter("branch_code");
         String m_sts="";
			
			
				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MON-YY') FROM DUAL ");
								
				boolean more = rs.next();
				if(more){
				m_Letter_date=rs.getString(1);
				}
				//comment By sandun on 15-06-2009
			/*	//
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
			*/
			//---------------------------------------------------------------------------
			   //Added By Sandun on 15-06-2009
					
					if(req.getParameter("status") == null){
					
			     rs=stmt.executeQuery (" SELECT PRINTED_STATUS "+
                                 " FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER "+
                                 " WHERE PURCHASE_ORDER_NO='"+m_purch_order+"' ");
					
					boolean more1 = rs.next();
					
					if(more1){
			  		m_sts=rs.getString(1);
			    }
					if(m_sts.equals("Y")){
					  m_status="COPY";
					}
					else if(m_sts.equals("N")){
						m_status="ORIGINAL";
					}
					}
					else
					{
						m_status=req.getParameter("status");
					}
					
			//----------------------------------------------------------
					
					rs = stmt.executeQuery(" SELECT "+
					    " UPPER(COMPANY_NAME), "+
					    " nvl(UPPER(ADDRESS1),' '), "+
					    " nvl(UPPER(ADDRESS2),' '), "+
					    " nvl(UPPER(CITY),''), "+
					    " TEL_NO, "+
					    " FAX_NO,  "+
							" VAT_RATE, "+
							" REG_NO "+
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
											m_orient_reg_no=rs.getString(8);			
											}
											
				
				
				 rs = stmt.executeQuery(" SELECT "+
        												" NVL(UPPER(TITLE),' '), "+
																" NVL(UPPER(FULL_NAME),' '), "+ 
																" REPLACE(REPLACE(NVL(UPPER(ADDRESS1),' '),'-',' '),'NULL',' '),"+ // REGISTERED_ADDRESS1
																" REPLACE(REPLACE(NVL(UPPER(ADDRESS2),' '),'-',' '),'NULL',' '),"+ //REGISTERED_ADDRESS2
                                " UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)) "+ // REGISTERED_CITY_CODE
																" ,CLIENT_TYPE "+
																" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
                                " WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"')");
						
					    
			 more = rs.next();		
				
				if(more){
				m_title=rs.getString(1);
				m_full_name=rs.getString(2);
				m_add1=rs.getString(3);
				m_add2=rs.getString(4);
				m_city_name=rs.getString(5);
				m_type=rs.getString(6);

				}
				
				if(m_city_name==null){
				m_city_name="";
				
				}
				if(m_add1.equals("-")){
				m_add1="";
				
				}
				if(m_add2.equals("-")){
				m_add2="";
				
				}

				if(!m_add1.equals(" ")){
				m_add1=m_add1+",";
				}
			
		

				if(!m_add2.equals(" ") && !m_city_name.equals("")){
				m_add2=m_add2+",";
				}		
				else
				{
				m_add2=m_add2;
				}		
				
				rs = stmt.executeQuery (" SELECT "+
				" NVL(UPPER(B.NAME), ' ' ), "+
				" NVL(UPPER(A.ADDRESS), ' '), "+
				//" CITY_CODE "+
				" NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE)), ' ' ) "+
								" ,NVL(UPPER(TYPE),'-') TYPE  "+

				" FROM "+m_schema_name+".AF_CO_MAS_VENDOR_LOCATION A, "+m_schema_name+".AF_CO_MAS_VENDORS B "+
				" WHERE UPPER(A.VENDOR_CODE)=UPPER(B.VENDOR_CODE) AND UPPER(A.VENDOR_CODE) =UPPER('"+m_vendor_code+"') AND UPPER(A.BRANCH)=UPPER('"+m_branch_code+"') ");
				
				more = rs.next();
				if(more){
				m_ven_name=rs.getString(1);
				m_ven_add=rs.getString(2);
				m_ven_city=rs.getString(3);
				m_ven_type=rs.getString(4); //added by nuwan de silva on 18-03-08

				}
       
				
				 rs=stmt.executeQuery (" SELECT "+
                               " NVL(FINANCE_NO,'-') "+
		                           " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
                               " WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ");
				
				more = rs.next();
			
			if(more){
			  m_finance_no=rs.getString(1);
			  }

																			
															/*	String sql=
				                        " SELECT SUB_MODEL_CODE,NVL(ENGINE_NO,'-'),NVL(CHASSIS_NO,'-'),NVL(REG_NO,'-'),QTY,X.DESCRIPTION "+
					                      " FROM (SELECT DISTINCT A.APPLICATION_NO,A.ENGINE_NO,A.CHASSIS_NO,A.REG_NO,C.QTY,C.SUB_MODEL_CODE,C.MODEL_CODE,F.MODEL_CODE||' '||F.DESCRIPTION ||'-'|| E.DESCRIPTION DESCRIPTION "+
																" FROM LAKDL.AF_CO_PRO_APP_INVOICE_DETAILS A , "+
																" LAKDL.AF_CO_PRO_APP_ASSET_DETAILS C, "+
																" LAKDL.AF_CO_MAS_MODEL D, "+
																" LAKDL.AF_CO_MAS_ITEM_SUB_CATEGORY E, "+
																" LAKDL.AF_CO_MAS_SUB_MODLE F "+	
																//" LAKDL.AF_CO_MAS_MAKE G "+
																" WHERE A.ASSET_ID=C.ASSET_ID AND "+
																" C.ACTIVE_STATUS='Y' AND "+
																" C.MODEL_CODE = D.MODEL_CODE AND "+
																" D.ITEM_SUB_CAT = E.ITEM_SUB_CAT AND "+
																" F.SUB_CODE=A.SUB_MODEL_CODE AND "+
																" A.APPLICATION_NO IN ( SELECT APPLICATION_NO "+
																" FROM LAKDL.AF_CR_PRO_PURCHASE_ORDER "+
																" WHERE  ACTIVE_STATUS = 'VERIFY' AND "+
																" PURCHASE_ORDER_NO ='"+m_purch_order+"') ) X, "+
																" LAKDL.AF_CO_MAS_SUB_MODLE Y "+
																" WHERE Y.SUB_CODE = X.SUB_MODEL_CODE ";			*/									
																
									//------------Added by Chandana on 16/07/2007 -----------------//							
																
																//comment by ns on 18-11-2010 
															/*	String sql= "  SELECT SUB_MODEL_CODE,NVL(ENGINE_NO,'-'), "+
																" NVL(CHASSIS_NO,'-'),NVL(REG_NO,'-'), "+
																" QTY,X.MODEL_DESC,X.EXTRAS,X.NET_AMOUNT "+
																" FROM (SELECT DISTINCT A.APPLICATION_NO,A.ENGINE_NO,A.CHASSIS_NO,A.REG_NO,C.QTY,C.SUB_MODEL_CODE,C.MODEL_CODE, "+
																//" INITCAP(G.MAKE_DESC||' '||D.DESCRIPTION ||' '||F.DESCRIPTION ||' '||E.DESCRIPTION) MODEL_DESC "+ //comment by nuwan de silva on 12-12-2007 at ofscl
																" INITCAP(G.MAKE_DESC||' '||F.DESCRIPTION ||' '||E.DESCRIPTION) MODEL_DESC, "+   //added by nuwan de silva on 12-12-2007 at ofscl
																" NVL(EXTRAS_INCLUDED,'NIL') EXTRAS, "+   //Added by Chandana on 11/01/2008
																" "+m_schema_name+".AF_CO_GET_ASSET_NET_AMOUNT(A.APPLICATION_NO,A.INVOICE_NO) NET_AMOUNT "+  //Added by Chandana on 11/01/2008
																" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A , "+
																" "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS C, "+
																" "+m_schema_name+".AF_CO_MAS_MODEL D, "+
																" "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY E, "+
																" "+m_schema_name+".AF_CO_MAS_SUB_MODLE F, "+
																" "+m_schema_name+".AF_CO_MAS_MAKE G "+
																" WHERE A.ASSET_ID=C.ASSET_ID AND "+
																" C.ACTIVE_STATUS='Y' AND "+
																" A.ACTIVE_STATUS='Y' AND "+
																" C.MODEL_CODE = D.MODEL_CODE AND "+
																" D.ITEM_SUB_CAT = E.ITEM_SUB_CAT AND "+
																" F.SUB_CODE=A.SUB_MODEL_CODE AND "+
																" D.MAKE_CODE=G.MAKE_CODE AND "+
																" A.APPLICATION_NO IN ( SELECT APPLICATION_NO "+
																" FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER "+
																" WHERE  ACTIVE_STATUS = 'VERIFY' AND "+
																" PURCHASE_ORDER_NO ='"+m_purch_order+"') ) X, "+
																" "+m_schema_name+".AF_CO_MAS_SUB_MODLE Y "+
																" WHERE Y.SUB_CODE = X.SUB_MODEL_CODE ";														
															*/
															
															  //modifed the above comment query by nuwan de silva on 18-11-2010
																String sql= "  SELECT SUB_MODEL_CODE,NVL(ENGINE_NO,'-'), "+
																" NVL(CHASSIS_NO,'-'),NVL(REG_NO,'-'), "+
																" QTY,X.MODEL_DESC,X.EXTRAS,SUM(X.NET_AMOUNT) "+
																" FROM (SELECT DISTINCT A.APPLICATION_NO,A.ENGINE_NO,A.CHASSIS_NO,A.REG_NO,C.QTY,C.SUB_MODEL_CODE,C.MODEL_CODE, "+
																//" INITCAP(G.MAKE_DESC||' '||D.DESCRIPTION ||' '||F.DESCRIPTION ||' '||E.DESCRIPTION) MODEL_DESC "+ //comment by nuwan de silva on 12-12-2007 at ofscl
																" INITCAP(G.MAKE_DESC||' '||F.DESCRIPTION ||' '||E.DESCRIPTION) MODEL_DESC, "+   //added by nuwan de silva on 12-12-2007 at ofscl
																" NVL(EXTRAS_INCLUDED,'NIL') EXTRAS, "+   //Added by Chandana on 11/01/2008
																" "+m_schema_name+".AF_CO_GET_ASSET_NET_AMOUNT(A.APPLICATION_NO,A.INVOICE_NO) NET_AMOUNT,INVOICE_NO "+  //Added by Chandana on 11/01/2008
																" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A , "+
																" "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS C, "+
																" "+m_schema_name+".AF_CO_MAS_MODEL D, "+
																" "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY E, "+
																" "+m_schema_name+".AF_CO_MAS_SUB_MODLE F, "+
																" "+m_schema_name+".AF_CO_MAS_MAKE G, "+
																" "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER H "+
																" WHERE A.APPLICATION_NO=H.APPLICATION_NO AND "+
																" H.VENDER_CODE    =A.VENDOR_CODE AND "+
																" A.ASSET_ID=C.ASSET_ID AND "+
																" C.ACTIVE_STATUS='Y' AND "+
																" A.ACTIVE_STATUS='Y' AND "+
																" C.MODEL_CODE = D.MODEL_CODE AND "+
																" D.ITEM_SUB_CAT = E.ITEM_SUB_CAT AND "+
																" F.SUB_CODE=A.SUB_MODEL_CODE AND "+
																" D.MAKE_CODE=G.MAKE_CODE AND "+
																" H.PURCHASE_ORDER_NO ='"+m_purch_order+"' "+
																" ) X, "+
																" "+m_schema_name+".AF_CO_MAS_SUB_MODLE Y "+
																" WHERE Y.SUB_CODE = X.SUB_MODEL_CODE "+
																" GROUP BY SUB_MODEL_CODE,NVL(ENGINE_NO,'-'),NVL(CHASSIS_NO,'-'),NVL(REG_NO,'-'), QTY,X.MODEL_DESC,X.EXTRAS ";														
															
															
																
																
																			
																			
			/*	more = rs.next();
				int i=0;
				
			 while(more){
				i=i+1;
			  String m_Eng_no_+i=rs.getString(2);
			  String m_Chas_no_+i=rs.getString(3);
				String m_Reg_no_+i=rs.getString(4);
				String m_Descr_+i=rs.getString(6);
				more = rs.next();
			  }			*/								
							
			  out.println("<html><head>"); 
				out.println("<title>Board Resolution </title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			 	out.println("<script>");
			
			  out.println("function save_data(){");
				//out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Documents_Status?chksql=save_page&scr_name=AF_MK_APP_STATUS_APPROVE_3&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&pur_ord_no="+m_purch_order+"&document_code="+m_document_code+"\";");			
				//out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Documents_Status?chksql=save_page&scr_name=AF_MK_APP_STATUS_APPROVE_3&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&pur_ord_no="+m_purch_order+"&branch_code="+m_branch_code+"&vendor_code="+m_vendor_code+"&document_code="+m_document_code+"\";");			
				
				out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Purchase_Order_View_Letter?chksql=save_page&scr_name=AF_CR_PRO_PURCHASE_ORDER&vendor_code="+m_vendor_code+"&status="+m_status+"&app_no="+m_application_no+"&print="+m_print+"&pur_ord_no="+m_purch_order+"&client_code="+m_client_code+"&scr_type=DEL_ORD&branch_code="+m_branch_code+"\";"); //Modified by Sandun on 15-06-2009
			 	
				//out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Hirepur_ind_delivery_order?chksql=main_page&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&document_code="+m_document_code+"&print=FALSE\";"); 
				//LAKDL_AF_CR_PRO_Document_Hirepur_ind_delivery_order?chksql=main_page&application_no=AP20070608-0723&document_code=BOARD_RESL&print=TRUE&client_code=0000000366
			  out.println(" window.location.href=m_url;"); 
			  out.println("m_table.innerHTML=\"\" ");
			  out.println("window.print();");
				out.println("}");
					
		    out.println("function add_button(){");
				if (m_print.trim().equals("FALSE")){
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
				out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");
				//out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo.gif\"></DIV><br><hr>");
				out.println("<body bgcolor='white'><br>");
				out.println("<form name='Form1'>");
				out.println("<input type='hidden' name='hid_purch_order' VALUE=\"\">");
											
				out.println("<table align='center' width='100%' class='table'>"); 
			  out.println("<tr>");  
			  out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		    out.println("</tr>"); 
  	    out.println("</table>");
       							
			  /*out.println("<blockquote><font size=3><p style='text-align:left'>");					
  		  out.println("<table align='center' width='100%' class='table'>"); 
			  out.println("<tr><td width=\"100%\" class='rep-body'><b>"+m_status+"</b></td></tr>");
		    out.println("</table>");
			  out.println("</font></p></blockquote>");	
			*/
			//out.println("<blockquote><font size=3><p style='text-align:center'>");
			
 			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr><td width=\"100%\" class='rep-body' align='center'><b>Delivery Order</b></td></tr>");
			out.println("<tr><td width=\"100%\" class='rep-body' align='center'><b>"+m_orient_name.toUpperCase()+"</b></td></tr>");
		  out.println("<tr><td width=\"100%\" class='rep-body' align='center'><b>"+m_orient_add1.toUpperCase()+", "+m_orient_add2.toUpperCase()+", "+m_orient_city_name+"</b></td></tr>");
		  out.println("<tr><td width=\"100%\" class='rep-body' align='center'><b>T.P."+m_orient_tel_no+" FAX "+m_orient_fax_no+" Co.Reg No "+m_orient_reg_no+"</b></td></tr>");
		  out.println("</table>");
			//out.println("</font></p></blockquote>");	
			
			out.println("<br>");
					
			//out.println("<font size=2><p style='text-align:center' class='rep-body1'>");	
			/*out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='45%' class='rep-body' style='text-align:left'>"+m_title+" "+m_full_name+"</td>");  //document.Form1.hid_purch_order.value
			out.println("<td width='45%' class='rep-body' style='text-align:left'><b>ORDER NO. :</B>"+m_finance_no+"</td></tr>");//m_purch_order //COMMENT BY NUWAN DE SILVA ON 11-02-08
			out.println("<tr><td width='45%' class='rep-body' style='text-align:left'>"+m_add1+"</td>");
			out.println("<td width='45%' class='rep-body' style='text-align:left'><b>Ref No. :</B>&nbsp"+m_finance_no+"</td></tr>");
			out.println("<tr><td width='45%' class='rep-body' style='text-align:left'>"+m_add2+"</td>");
			out.println("<td width='45%' class='rep-body' style='text-align:left'><b>Date. :</B>&nbsp&nbsp&nbsp&nbsp"+m_Letter_date+"</td></tr>");
			out.println("<tr><td width='45%' class='rep-body' style='text-align:left'>"+m_city_name+"</td>");
			out.println("<td width='45%' class='rep-body' style='text-align:left'></td></tr>");
			out.println("<tr></tr>");
			out.println("<tr></tr>");
			out.println("<tr><td width='45%' class='rep-body' style='text-align:left'><b>Dear Sir,</td>");
			out.println("<td width='45%' class='rep-body' style='text-align:left'><b></td></tr>");
			out.println("</table>");
			*/
			
												out.println("<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class='table'> "+
											" <tr> "+
											" <td width=\"60%\" class='rep-body1' align=left>"+m_ven_name+"</td> "+
											" <td width=\"12%\" class='rep-body1' align=left>Order No </td> "+
											" <td width=\"3%\"  class='rep-body1' align=center>:</td> "+
											" <td width=\"25%\" class='rep-body1' align=left>"+m_finance_no+"</td> "+
											" </tr> "+
											" <tr> "+
											" <td class='rep-body1' align=left>"+m_ven_add+"</td> "+
											" <td class='rep-body1' align=left>Ref No </td> "+
											" <td class='rep-body1' align=center>:</td> "+
											" <td class='rep-body1' align=left>"+m_finance_no+"</td> "+
											" </tr> "+
											" <tr> "+
											" <td class='rep-body1' align=left>"+m_ven_city+"</td> "+
											" <td class='rep-body1' align=left>Date</td> "+
											" <td class='rep-body1' align=center>:</td> "+
											" <td class='rep-body1' align=left>"+m_Letter_date+"</td> "+
											" </tr> "+
											" <tr> "+
											" <td class='rep-body1' align=left>&nbsp;</td> "+
											" <td class='rep-body1' align=left>&nbsp;</td> "+
											" <td class='rep-body1' align=center>&nbsp;</td> "+
											" <td class='rep-body1' align=left>&nbsp;</td> "+
											" </tr> "+
											" </table> ");

			//out.println("</font>");
			
			/////////////out.println("<br><br>");
		 	
		  //out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body1'>");	
				
			String data="We, "+m_orient_name.toUpperCase()+" hereby place our official purchase order/ supply Agreement "+
			            "to you for the Equipment/s in connection with our lease purchase arrangement with "+
									""+m_title+" "+m_full_name+"  at "+
									" "+m_add1+"&nbsp"+m_add2+"&nbsp"+m_city_name+" ";
				
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");
			
			//////////out.println("<br><br>");
			
		//	out.println("I"+i);
			
			out.println("<table border='0' width='90%' class='table'>");
			out.println("<tr><td width='5%'></td><td width='85%' class='rep-body1' style='text-align:center'><b>SCHEDULE</b></td></tr>");
			out.println("</table>");
			
			 rs=stmt.executeQuery (sql);
			
			 more = rs.next();
								
			 while(more){
				m_Eng_no=rs.getString(2);
			  m_Chas_no=rs.getString(3);
				m_Reg_no=rs.getString(4);
        m_Qty=rs.getDouble(5);
				m_Qty2=rs.getString(5);
				m_Descr=rs.getString(6);
				m_Extras=rs.getString(7);
				m_Net_Amt=rs.getDouble(8);
				//m_Amount_wd=rs.getString(8);
			
		//	while(j<=i){
			///out.println("<br>");
			
			out.println("<table border='1' width='90%' class='table' cellspacing='0' cellpadding='0' bordercolor='black'>");
			// Modified by Dineth on 2008-08-25
			out.println("<tr><td width='45%' class='rep-body1' style='text-align:left'>&nbsp Description of the vehicle/equipment </td><td width='45%' class='rep-body1' style='text-align:left'>&nbsp"+m_Descr+" &nbsp - "+m_Qty2+"</td></tr>");//&nbsp - "+m_Qty+"
			//out.println("<tr><td width='45%' class='rep-body1' style='text-align:left'>&nbsp Type of Body.</td><td width='45%' class='rep-body1' style='text-align:left'> &nbsp"+m_Color+" </td></tr>");
			out.println("<tr><td width='45%' class='rep-body1' style='text-align:left'>&nbsp Registration No. </td><td width='45%' class='rep-body1' style='text-align:left'> &nbsp"+m_Reg_no+" </td></tr>");
			out.println("<tr><td width='45%' class='rep-body1' style='text-align:left'>&nbsp Engine No. </td><td width='45%' class='rep-body1' style='text-align:left'> &nbsp"+m_Eng_no+" </td></tr>");
			// Modified by Dineth on 2008-08-25
			out.println("<tr><td width='45%' class='rep-body1' style='text-align:left'>&nbsp Chassis No./Serial No. </td><td width='45%' class='rep-body1' style='text-align:left'>&nbsp"+m_Chas_no+"</td></tr>");
			//out.println("<tr><td width='45%' class='rep-body1' style='text-align:left'>&nbsp Equipment and Accessories.</td><td width='45%' class='rep-body1' style='text-align:left'>&nbsp ................................................................................................</td></tr>");
			out.println("<tr><td width='45%' class='rep-body1' style='text-align:left'>&nbsp Extras included.</td><td width='45%' class='rep-body1' style='text-align:left'>&nbsp"+m_Extras+"</td></tr>");
			out.println("</table>");
			
			//j=j+1;
		//	}
      
             
            
         // out.println("----"+m_Net_Amt+"---"+m_Qty+"----"+m_Net_Amt*m_Qty);
          //m_tot_amt = m_tot_amt + (m_Net_Amt*m_Qty); 
					m_tot_amt = m_tot_amt + m_Net_Amt; 
          //out.println("----"+m_tot_amt);     
               
               
			  more = rs.next();
			  }						
				
				rs=stmt.executeQuery (" SELECT "+m_tot_amt+" FROM DUAL ");
				if(rs.next()){
				m_Amount_wd=rs.getString(1);
				}
			
			
			
			
			out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table'>");
			out.println("<tr><td width='90%' class='rep-body1' style='text-align:left'>Other terms and condition</td></tr>");
			out.println("<tr></tr>");
			out.println("</table>");
									
			     data="Title to the said Equipment/s shall be vested in "+m_orient_name.toUpperCase()+"  "+
								"free from any liens and encumbrances of anyone claiming by, though or under you with effect from the "+
								"date of purchase by "+m_orient_name.toUpperCase()+" of the equipment/s. ";
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='1%' class='rep-body1' style='text-align:left' valign=top >i.</td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");
			//out.println("<br>");
			
			data="You shall deliver to "+m_orient_name.toUpperCase()+" and Lease Purchaser your written warranties in substance "+
			      "and in form and required by the aforesaid purchase order/ supply agreement. By acceptance hereof you agree that all "+
			 			"warranties written or oral, express or implied are for the benefit of and may be enforced by both "+m_orient_name.toUpperCase()+" "+
		        "and Lease Purchaser or either of them. ";
		  
		  out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='1%' class='rep-body1' style='text-align:left' valign=top >ii.</td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");
	    
			data="If the equipment/described in (1) above is a vehicle, registration of the vehicle should be undertaken by you.";
			 			
		  //out.println("<br>");		
		
		  out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='1%' class='rep-body1' style='text-align:left' valign=top >iii.</td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");
		
   		data="Registered ownership should be in favour of Lease Purchaser and you should have "+m_orient_name.toUpperCase()+" "+
			     "as the Absolute Owner. ";
			 			
		  //out.println("<br>");		
		
		  out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='1%' class='rep-body1' style='text-align:left' valign=top >iv.</td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");
		    
			data="In support of above you are required to forward the following <B>RMV</B> documents "+
			     "as the Absolute Owner. ";
			 			
		  //out.println("<br>");		
		  out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='1%' class='rep-body1' style='text-align:left' valign=top ></td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");
		     
	    	data="For Motor Vehicles";
					     			 			
		  out.println("<br>");		
		  out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='10%' class='rep-body1' style='text-align:left' valign=top ></td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' ><li>"+data+"</td>");
			out.println("</tr></table>");
	
	       data="Original Certificate of Registration and vehicle Identity Card (VIC)/CMT 52 "+
					    "receipt with "+m_orient_name.toUpperCase()+" as Absolute Owner ";  
					     			 			
		  out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='10%' class='rep-body1' style='text-align:left' valign=top ></td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' ><li>"+data+"</td>");
			out.println("</tr> ");
			
			   data="Absolute Owner ";
			
			out.println("<tr><td width='10%' class='rep-body1' style='text-align:left' valign=top ></td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' ><li>"+data+"</td>");
			out.println("</tr>");
			
			   data="Original Invoice/ Tax Invoice ";
			
			out.println("<tr><td width='10%' class='rep-body1' style='text-align:left' valign=top ></td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' ><li>"+data+"</td>");
			out.println("</tr>");
						
			   data="Duplicate Key ";
			
			out.println("<tr><td width='10%' class='rep-body1' style='text-align:left' valign=top ></td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' ><li>"+data+"</td>");
			out.println("</tr>");
	      
			   data="Original Valuation Report (in case of Used or Reconditioned)  ";
			
			out.println("<tr><td width='10%' class='rep-body1' style='text-align:left' valign=top ></td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' ><li>"+data+"</td>");
			out.println("</tr>");
	
  		   data="Copy of Vat Registration Certificate ";
			
			out.println("<tr><td width='10%' class='rep-body1' style='text-align:left' valign=top ></td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' ><li>"+data+"</td>");
			out.println("</tr>");

		  	   data="Luxury Tax Paying Slips ";
			
			out.println("<tr><td width='10%' class='rep-body1' style='text-align:left' valign=top ></td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' ><li>"+data+"</td>");
			out.println("</tr>");
  			
			out.println("</table>");
			//out.println("</font></p></blockquote>");	
	            
			//out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body1'>");	
		
		  data=					"In the event the registration/ transfers cannot be executed due to irregularities/ "+
			   					  "discrepancies found in the <b>RMV</b> forms submitted by you to the Commissioner of "+
									  "Motor Traffic. You will take full responsibility to rectify same so as to conform to condition "+
									  "as stipulated in clause(iv)";
									 
		  out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");
								
			///out.println("<br><br>");
		  //////					out.println("   <p style=\"page-break-after:always\"></p>");		

			data=					"Please return the duplicate of this Delivery Order with an endorsement from the Lease Purchaser stating "+
									  "that they have received same. ";
									 
		  out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");
		  //out.println("<br><br>");
						
			data=					"The relevant payment for Rs."+nf.format(m_tot_amt)+" ( "+m_sn_methods.numbersToChar(m_Amount_wd)+" Only. )";
			//data=					"The relevant payment for Rs."+nf.format(m_Net_Amt)+" ( Ten "+m_sn_methods.numbersToChar(m_Amount_wd)+" Only. )";
												 
		  out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' ><b>"+data+"</td>");
			out.println("</tr></table>");
		  //out.println("<br><br>");
			
			data=					"This Delivery Order will be valid for 30days from the date of issue. If you accept our above Delivery Order, please "+
			              "indicate your acceptance by signing and returning same to "+m_orient_name.toUpperCase()+". The section I to be fill and signed by the Lessee "+
										"in order to confirm the both parties acceptance to the above Delivery Order.";
									 
									 
		  out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");
		  out.println("<br>");
			
		  out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+m_orient_name.toUpperCase()+"</td>");
			out.println("</tr></table>");
			//out.println("<br><br>");
			out.println("<br><br>");
			
	 /*	out.println("<table border='0' width='80%' class='table'>"); 		
		out.println("<tr><td width='40%' class='rep-body'  >..................................</td>");
		out.println("<td width='40%' class='rep-body'  >..................................</td></tr>");
			
		out.println("<tr><td width='40%' class='rep-body' >(authorized signatories)</td>");
		out.println("    <td width='40%' class='rep-body' >(authorized signatories of supplier)</td></tr>");
	  out.println("<tr><td width='40%' class='rep-body' ></td>");
		out.println("    <td width='40%' class='rep-body' >Co. rubber stamp to be placed.</td></tr>");
    out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("</table>");
		*/
		if(m_ven_type.equals("I") || m_ven_type.equals("IND") ){
		/*out.println("<table border='0' width='80%' class='table'>"); 		
		out.println("<tr><td width='40%' class='rep-body1'  >..................................</td>");
		out.println("<td width='40%' class='rep-body1'  >..................................</td></tr>");
		out.println("<tr><td width='40%' class='rep-body1' >(authorized signatories)</td>");
		out.println("    <td width='40%' class='rep-body1' >"+m_title+" "+m_full_name.toUpperCase()+"</td></tr>");
	  out.println("<tr><td width='40%' class='rep-body1' ></td>");
		out.println("    <td width='40%' class='rep-body1' >NIC No ..................................</td></tr>");
    out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("</table>");
		*/
		
				//added by nuwan de silva on 18-03-08
		out.println("<table border='0' width='80%' class='table'>"); 		
		out.println("<tr><td width='40%' class='rep-body1'  >..................................</td>");
		out.println("<td width='40%' class='rep-body1'      >..................................</td></tr>");
		out.println("<tr><td width='40%' class='rep-body1' >(authorized signatories)</td>");
		out.println("    <td width='40%' class='rep-body1' >"+m_ven_name.toUpperCase()+"</td></tr>");
		out.println("<tr><td width='40%' class='rep-body1' ></td>");
		out.println("    <td width='40%' class='rep-body1' >NIC No.......................<td></tr>");
    out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("</table>");

		
		}
		//else if(m_type.equals("C")){
		else{
		/*out.println("<table border='0' width='80%' class='table'>"); 		
		out.println("<tr><td width='40%' class='rep-body'  >..................................</td>");
		out.println("<td width='40%' class='rep-body1'  >..................................</td></tr>");
		out.println("<tr><td width='40%' class='rep-body1' >(authorized signatories)</td>");
		out.println("    <td width='40%' class='rep-body1' >"+m_title+" "+m_full_name.toUpperCase()+"</td></tr>");
	  out.println("<tr><td width='40%' class='rep-body1' ></td>");
		out.println("    <td width='40%' class='rep-body1' >Co. rubber stamp to be placed.</td></tr>");
    out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("</table>");
    */
				out.println("<table border='0' width='80%' class='table'>"); 		
		out.println("<tr><td width='40%' class='rep-body'  >..................................</td>");
		out.println("<td width='40%' class='rep-body1'  >"+m_ven_name.toUpperCase()+"</td></tr>");
		out.println("<tr><td width='40%' class='rep-body1' >(authorized signatories)</td>");
		out.println("    <td width='40%' class='rep-body1' ></td></tr>");
		out.println("</table>");
		out.println("<br><br>");
		out.println("<table border='0' width='80%' class='table'>"); 		
		out.println("<tr><td width='40%' class='rep-body1' ></td>");
		out.println("    <td width='40%' class='rep-body1' >..................................</td></tr>");
		out.println("<tr><td width='40%' class='rep-body1' ></td>");
		out.println("    <td width='40%' class='rep-body1' >(authorized signatories)</td></tr>");
		out.println("<tr><td width='40%' class='rep-body1' ></td>");
		out.println("    <td width='40%' class='rep-body1' >Co. rubber stamp to be placed.</td></tr>");
    out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("</table>");

		
		}

		
		out.println("<table border='0' width='80%' class='table'>"); 	
		out.println("<tr><td width='*%' class='rep-body1' >......................................................................................................................................................</td></tr>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("</table>");
		out.println("   <p style=\"page-break-after:always\"></p>");
		
		out.println("<table border='0' width='80%' class='table'>");
		out.println("<tr><td width='40%' class='rep-body1' >To be completed by Lease Purchaser(Section i)</td>");
		out.println("    <td width='40%' class='rep-body1' ></td></tr>");
    out.println("</table>");
		out.println("<br><br>");
									 
		out.println("<table border='0' width='90%' class='table'>"); 		
		out.println("<td width='*%' class='rep-body1' style='text-align:justify' >To: "+m_orient_name.toUpperCase()+"</td>");
		out.println("</tr></table>");
		out.println("<br><br>");
									 
		data="We have accepted the items described in schedule above, and hereby authorize you to make payments to the supplier on following basis.";								
										
		out.println("<table border='0' width='90%' class='table'>"); 		
		out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
		out.println("</tr></table>");
		out.println("<br><br>");
		
		out.println("<table border='0' width='90%' class='table'>"); 		
		out.println("<td width='20%' class='rep-body1' style='text-align:justify' ></td>");
		out.println("<td width='70%' class='rep-body1' style='text-align:justify' >Full payment of Rs .................................................................................. </td></tr>");
		out.println("<td width='20%' class='rep-body1' style='text-align:justify' ></td>");
		out.println("<td width='70%' class='rep-body1' style='text-align:justify' >Part payment of Rs ...................................... and the balance to be paid on the</td></tr>");
    out.println("<td width='20%' class='rep-body1' style='text-align:justify' ></td>");
		out.println("<td width='70%' class='rep-body1' style='text-align:justify' >receipt of our written instructions.</td></tr>");
		out.println("</table>");
  	out.println("<br><br>");
		out.println("<br><br>");
		
		/*out.println("<table border='0' width='90%' class='table'>"); 		
		out.println("<td width='60%' class='rep-body' style='text-align:justify' >...........................................................................</td>");
		out.println("<td width='30%' class='rep-body' style='text-align:justify' ></td></tr>");
		out.println("<td width='60%' class='rep-body' style='text-align:justify' >"+m_title+" "+m_full_name+"</td>");
		out.println("<td width='30%' class='rep-body' style='text-align:justify' >Date ............................</td></tr>");
		out.println("<td width='60%' class='rep-body' style='text-align:justify' >NIC No: ..........................................................</td>");
		out.println("<td width='30%' class='rep-body' style='text-align:justify' ></td></tr>");
		out.println("</table>");
 		*/
			
			
		if(m_type.equals("I")){
		out.println("<table class='table' width=\"80%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> "+
		"<tr><td width=70% class='rep-body1' align=left>.................................................................. </td><td width=10% align=center class='rep-body1' >.................................. </td></tr>"+											
		"<tr><td width=70% class='rep-body1' align=left>"+m_full_name.toUpperCase()+"</td><td width=10% class='rep-body1'  align=center>DATE</td></tr>");										
		out.println("</table>");			
		out.println("<br><br>");	
		out.println("<table class='table' width=\"80%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> "+
		"<tr><td width=70% class='rep-body1' align=left>NIC NO: .................................................................. </td></tr>");											
		out.println("</table>");		
		}
		else if(m_type.equals("C")){
		out.println("<table class='table' width=\"80%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> "+
		"<tr><td width=70% class='rep-body1' align=left> .................................................................. </td><td width=10% align=center>.................................. </td></tr>"+											
		"<tr><td width=70% class='rep-body1' align=left>"+m_full_name.toUpperCase()+"</td><td width=10%  align=center class='rep-body1' >DATE</td></tr>");										
		out.println("</table>");			
		out.println("<table class='table' width=\"80%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> "+
		"<tr><td width=70% class='rep-body1' align=left>(authorized signatories)</td></tr>"+	
		"<tr><td width=70% class='rep-body1' align=left>Co. rubber stamp to be placed.</td></tr>");											
		out.println("</table>");			
		}
			
		//out.println("</font></p></blockquote>");		
											
	  out.println("</form></body></html>");
			}
			out.flush();
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
