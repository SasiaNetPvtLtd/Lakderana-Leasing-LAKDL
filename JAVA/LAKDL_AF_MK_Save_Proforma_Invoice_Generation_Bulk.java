//--
//SCREEN NAME:SAVE SYSTEM ADMINISTRATION - PERFORMA INVOICE
//CREATED BY:NUWAN DE SILVA
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MK_Save_Proforma_Invoice_Generation_Bulk extends HttpServlet {
	
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt,callstmt1,callstmt2,callstmt3;
	String reqstr;
	ServletOutputStream out = null;
	Statement stmt,stmt1;
	
	public ResultSet rs;
	public synchronized void  service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
		
		try {
			
			//  out.println("test A");
			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
			out = res.getOutputStream();
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			//************************************************************	
			conn =m_sn_methods.met_user_validate(req);
			//out.println("conn="+conn)  ;
			
			//**************************************************************		
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
			String m_username = m_sn_methods.username;
			String m_html_client_url;
			String m_class_url;
			String m_class_name_save;
			String m_save_procedure_name;
			String m_price_no="";
			String m_screen_name="";
			String m_invoice_no="";
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			conn.setAutoCommit(false); //added by nuwan de silva on 03-09-07	
			String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			String m_appNo=(String)m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"); 
			//	String m_appNo = (String)m_sn_methods.met_formdata(reqstr,"hid_app_no"); 
			
			int m_maxentries     = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_no_rec"));
			
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			
			m_screen_name =	(String)m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");				
			
			String m_my_screen="";
			
			//m_my_screen =  req.getParameter("my_screen_name");
			
			//if(m_my_screen==null){
			//m_my_screen="";
			//}
			
			
			
			//callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MK_SAVE_PER_INVOICE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28,:29,:30,:31); END;");
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MK_SAVE_PER_INVOICE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28,:29,:30,:31,:32,:33,:34,:35,:36,:37,:38,:39); END;");// insert 32,33,34,35,36,37,38 by Prabash 
			callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_PRO_APP_SAVE_PRICING(:1,:2,:3,:4,:5,:6); END;");
			callstmt2=conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_PRO_APP_PR_SAVE_CHAR(:1,:2,:3,:4,:5,:6); END;");
			callstmt3=conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_PRO_APP_SAVE_INST(:1,:2,:3,:4,:5,:6); END;");
			
			for (int j = 0; j < m_maxentries; j++) {
				
				String m_inv_no =(String)m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_NO"+(Integer.toString(j)));
				
				//_____________________________________________Pro forma Invoice __________________________________________________________________
				if(m_inv_no.equals("")){
					callstmt.registerOutParameter(1,java.sql.Types.CHAR);
					
				}
				else{ 
					callstmt.setString(1 ,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_NO"+(Integer.toString(j))));
				}	
				
				callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
				///callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_ASSET_ID"));
				callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_ASSET_ID"+(Integer.toString(j))));
				callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_ENGINE_NO"+(Integer.toString(j))));
				callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_CHASSIS_NO"+(Integer.toString(j))));
				
				//callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_REG_NO"));
				callstmt.setString(6,"");
				
				/*	String m_reg_dd =m_sn_methods.met_formdata(reqstr,"TXT_REG_DATE_DD");
					String m_reg_mm =m_sn_methods.met_formdata(reqstr,"TXT_REG_DATE_MM");
					String m_reg_yy =m_sn_methods.met_formdata(reqstr,"TXT_REG_DATE_YY");
					
					String m_reg_date="";
					
					if(m_reg_dd.equals("") && m_reg_mm.equals("") &&  m_reg_yy.equals("") )
					{
					m_reg_date=m_reg_dd+m_reg_mm+m_reg_yy; 
					}
					else
					{
					m_reg_date=m_reg_dd+"-"+m_reg_mm+"-"+m_reg_yy;
					}
					
					callstmt.setString(7,m_reg_date);
					*/
				callstmt.setString(7,"");
				
				//callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_PRICING_NO"));
				callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_PRICING_NO"+(Integer.toString(j))));
				
				//callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_SUB_MODEL_NO"));
				callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_SUB_MODEL_NO"+(Integer.toString(j))));
				
				//callstmt.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_COLOUR"));
				callstmt.setString(10,"");
				
				//callstmt.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_SEATING_CAPACITY"));
				callstmt.setString(11,"");
				
				//callstmt.setString(12,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_NET_PRICE")));
				callstmt.setString(12,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_NET_PRICE"+(Integer.toString(j)))));
				//callstmt.setString(13,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_VAT")));
				callstmt.setString(13,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_VAT"+(Integer.toString(j)))));
				//callstmt.setString(14,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_TOTAL_AMOUNT")));
				callstmt.setString(14,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_TOTAL_AMOUNT"+(Integer.toString(j)))));
				
				callstmt.setString(15,m_sn_methods.met_formdata(reqstr,"TXT_TO_BE_DELIVERD_TO"));
				
				//callstmt.setString(16,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_VALUE")));
				callstmt.setString(16,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_VALUE"+(Integer.toString(j)))));
				
				//callstmt.setString(17,m_sn_methods.met_formdata(reqstr,"TXT_CURR_CODE"));
				callstmt.setString(17,m_sn_methods.met_formdata(reqstr,"TXT_CURR_CODE"+(Integer.toString(j))));
				
				//callstmt.setString(18,m_sn_methods.met_formdata(reqstr,"TXT_MODEL_NO"));
				callstmt.setString(18,m_sn_methods.met_formdata(reqstr,"TXT_MODEL_NO"+(Integer.toString(j))));
				
				//callstmt.setString(19,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_DOC_NO"));
				callstmt.setString(19,"");
				
				callstmt.setString(20,m_sn_methods.met_formdata(reqstr,"TXT_LOCATION"));
				
				callstmt.setString(21,(m_sn_methods.met_formdata(reqstr,"TXT_CITY_CODE")).trim());
				
				//callstmt.setString(22,m_sn_methods.met_formdata(reqstr,"TXT_VENDOR_CODE"));
				callstmt.setString(22,m_sn_methods.met_formdata(reqstr,"TXT_VENDOR_CODE"+(Integer.toString(j))));
				
				//callstmt.setString(23,m_sn_methods.met_formdata(reqstr,"TXT_LOCATION_CODE"));
				callstmt.setString(23,m_sn_methods.met_formdata(reqstr,"TXT_LOCATION_CODE"+(Integer.toString(j))));
				
				//callstmt.setString(24,m_sn_methods.met_formdata(reqstr,"TXT_FUEL_CONV_STS"));
				callstmt.setString(24,"");
				
				/*String m_due_dd =m_sn_methods.met_formdata(reqstr,"TXT_DUE_DATE_DD");
				String m_due_mm =m_sn_methods.met_formdata(reqstr,"TXT_DUE_DATE_MM");
				String m_due_yy =m_sn_methods.met_formdata(reqstr,"TXT_DUE_DATE_YY");
				
				String m_due_date="";
				
				if(m_due_dd.equals("") && m_due_mm.equals("") &&  m_due_yy.equals("") )
				{
				m_due_date=m_due_dd+m_due_mm+m_due_yy; 
				}
				else
				{
				m_due_date=m_due_dd+"-"+m_due_mm+"-"+m_due_yy;
				}
				*/
				
				//callstmt.setString(25,m_due_date);
				callstmt.setString(25,"");
				callstmt.setString(26,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
				callstmt.setString(27,m_username);
				callstmt.setString(28,m_sn_methods.met_formdata(reqstr,"TXT_YEAR_MANUFACTURE"));  //ADDED BY NUWAN DE SILVA ON 02-01-2008
				//callstmt.setString(28,"");
				//callstmt.setString(29,m_sn_methods.met_formdata(reqstr,"TXT_EXTRAS"));
				callstmt.setString(29,"");
				callstmt.setString(30,"");
				callstmt.setString(31,"0");
				callstmt.setString(32,"0"); // added by udara 26-06-2018 to fix inserting null to AF_CO_SUM_INSURED_DETAILS_BK  // callstmt.setString(32,"");
				callstmt.setString(33,"");
				callstmt.setString(34,"");
				callstmt.setString(35,"");
				callstmt.setString(36,"");
				callstmt.setString(37,"");
				callstmt.setString(38,"");
				callstmt.setString(39,"");
				callstmt.execute();
				
				if(m_screen_name.equals("NEW")){
					m_invoice_no =callstmt.getString(1);
				}
				
				//_____________________________________Saving Appricing __________________________________________________________________
				callstmt1.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
				
				if(m_screen_name.equals("NEW")){
					callstmt1.setString(2,m_invoice_no.trim());
					m_msg = "'"+m_invoice_no+" Proforma Invoice saved successfully.'";
				}
				else
				{
					//callstmt.setString(2,(m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_NO")).trim());
					callstmt1.setString(2 ,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_NO"+(Integer.toString(j))));
				}	
				
				//callstmt.setString(1 ,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_NO").toUpperCase());
				//callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_PRICING_NO"));
				callstmt1.setString(3 ,m_sn_methods.met_formdata(reqstr,"TXT_PRICING_NO"+(Integer.toString(j))));
				callstmt1.setString(4,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
				callstmt1.setString(5,m_username);
				callstmt1.setString(6,m_scr_name);
				callstmt1.execute();
				//__________________________________End Apppricing _______________________________________________________________________
				
				
				//___________________________________App pricing charges _________________________________________________________________
				//callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_PRICING_NO"));
				callstmt2.setString(1 ,m_sn_methods.met_formdata(reqstr,"TXT_PRICING_NO"+(Integer.toString(j))));
				callstmt2.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
				
				//callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_NO"));
				
				if(m_screen_name.equals("NEW")){
					callstmt2.setString(3,m_invoice_no.trim());
				}
				else
				{
					//callstmt.setString(3,(m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_NO")).trim());
					callstmt2.setString(3 ,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_NO"+(Integer.toString(j))));
				}	
				
				callstmt2.setString(4,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
				callstmt2.setString(5,m_username);
				callstmt2.setString(6,m_scr_name);
				callstmt2.execute();
				//______________________________________End App pricing charges __________________________________________________________
				
				
				//__________________________________________App Instalment ________________________________________________________________
				callstmt3.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
				if(m_screen_name.equals("NEW")){
					callstmt3.setString(2,m_invoice_no.trim());
				}
				else
				{
					//callstmt.setString(2,(m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_NO")).trim());
					callstmt3.setString(2 ,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_NO"+(Integer.toString(j))));
					
				}	
				
				//callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_PRICING_NO"));
				callstmt3.setString(3 ,m_sn_methods.met_formdata(reqstr,"TXT_PRICING_NO"+(Integer.toString(j))));
				callstmt3.setString(4,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
				callstmt3.setString(5,m_username);
				callstmt3.setString(6,m_scr_name);
				callstmt3.execute();
				//___________________________________________End App Instalment ___________________________________________________________
			}
			callstmt.close();
			conn.commit(); //added by nuwan de silva on 03-09-07----------------------
			
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			
			out.println("window.close();");
			out.println("if('"+m_my_screen+"'==''){");
			//added by nuwan de silva 26-06-07-------------------------------------
			out.println("		if(confirm(\"Are you sure you want to add a new proforma invoice ?\")){ "); 
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_MK_display_performa_invoice?APP_NO="+m_appNo+"';");
			out.println("	}");
			
			out.println("	else	if(confirm(\"Are you sure you want to add a valuation ?\")){ "); 
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_MK_display_inspection_and_valuation_report?APP_NO="+m_appNo+"';");
			out.println("	}");
			
			out.println("	else {");
			out.println("window.close();");			
			out.println("	}");
			
			out.println("window.opener.chk_totals()");
			
			out.println("}");
			
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();
			
		}
		
		catch (Exception E) {
			try{conn.rollback();}catch(Exception e){}
			out.println("ERROR:"+E.toString());
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert('Error when Saving');");
			out.println("window.history.back();"); 
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();
			
		}
		finally{
			try{conn.setAutoCommit(true);}catch(Exception e){}
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}
		
	}
}
