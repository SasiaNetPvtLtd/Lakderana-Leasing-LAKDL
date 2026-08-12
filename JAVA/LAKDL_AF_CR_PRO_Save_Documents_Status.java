 
//Created by Nuwan De Silva
//Purchase Order Print Status Save

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_CR_PRO_Save_Documents_Status extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	CallableStatement callstmt;
	Statement stmt,stmt1;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1;
	public String m_chksql,m_no_of_due_days,m_sys_date;
	ServletOutputStream out = null;
	int m_appno_count=0;
	
  public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		try {
			
			//************************************************************	
			LAKDL_AF_MK_CO_methods CO_methods = new LAKDL_AF_MK_CO_methods();
			
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String header_name=con_method.header_name.trim();
			String m_schema_name = con_method.schema_name;
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
			String m_username = con_method.username;
      		String m_gur_name="";
			String m_client_type="";
			String m_purch_no="",m_vendor_code="",m_branch_code="";
			out = res.getOutputStream();
			CallableStatement callstmt1 =null;
	
					
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql         = req.getParameter("chksql");
			
			stmt = conn.createStatement ();
			stmt1 = conn.createStatement ();
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
			else if(m_chksql.trim().equals("save_page")){
			String m_repossession_no = req.getParameter("repossess_no");	   
			String m_application_no = req.getParameter("application_no");
			String m_client_code    = req.getParameter("client_code");
			String m_document_code  = req.getParameter("document_code");
			String m_scr_name = req.getParameter("scr_name");
			String m_status       = req.getParameter("status");
			
			String m_ref_no       = ""; // added by udara 13-02-2019
			
			
			if(req.getParameter("gur_name")!=null){
			m_gur_name=req.getParameter("gur_name");
			}
			
			if(req.getParameter("client_type")!=null){
			m_client_type=req.getParameter("client_type");
			}
				
            if(req.getParameter("pur_ord_no")!=null){
			m_purch_no=req.getParameter("pur_ord_no");
			}
			
			if(req.getParameter("vendor_code")!=null){
			m_vendor_code=req.getParameter("vendor_code");
			}
			
			if(req.getParameter("branch_code")!=null){
			m_branch_code=req.getParameter("branch_code");
			}
			
			// added by udara 13-02-2019
			if(req.getParameter("ref_no")!=null){
			   m_ref_no=req.getParameter("ref_no");
			}
			// end by udara 13-02-2019

	
	    	  callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_DOC_PRINT(:1,:2,:3,:4,:5); END;");
				
				  callstmt.setString(1,m_application_no);
				  callstmt.setString(2,m_client_code);
				  callstmt.setString(3,m_document_code);
				  callstmt.setString(4,m_username);
				  callstmt.setString(5,m_scr_name);
				  callstmt.execute();
					
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("m_doc_code='"+m_document_code+"'");
			
			out.println("if(m_doc_code=='ACCEPT_REC'){");
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Document_Acceptance_Receipt?chksql=main_page&application_no="+m_application_no+"&client_code="+m_client_code+"&status="+m_status+"&print=FALSE';"); 
			//out.println("window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Document_Acceptance_Receipt?chksql=main_page&application_no="+m_application_no+"&client_code="+m_client_code+"&status="+m_status+"&print=FALSE\");");
			out.println("}");
			
			out.println("else if(m_doc_code=='JOINT_GUAR'){");
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Document_Joint_Several_Guarantee?chksql=main_page&application_no="+m_application_no+"&client_code="+m_client_code+"&status="+m_status+"&print=FALSE';"); 
			out.println("}");
			
			out.println("else if(m_doc_code=='FIRST_LETT'){");
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Document_First_Letter?chksql=main_page&application_no="+m_application_no+"&client_code="+m_client_code+"&status="+m_status+"&print=FALSE';"); 
			out.println("}");
		//added by Prabash on 10-04-2012---*
			out.println("else if(m_doc_code=='RENT_LETT'){");
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Rental_letter_summery?chksql=main_page&application_no="+m_application_no+"&client_code="+m_client_code+"&status="+m_status+"&print=FALSE';"); 	
			out.println("}");
		//---------------------------------*
		
		//---------------------------------*
		
		//added by Prabash on 11-05-2012---*
			out.println("else if(m_doc_code=='BLUE_LETT'){");
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Reminder_blue_letter?chksql=main_page&application_no="+m_application_no+"&client_code="+m_client_code+"&status="+m_status+"&print=FALSE';"); 	
			out.println("}");
		
			out.println("else if(m_doc_code=='RED_LETT'){");
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Reminder_red_letter?chksql=main_page&application_no="+m_application_no+"&client_code="+m_client_code+"&status="+m_status+"&print=FALSE';"); 	
			out.println("}");
			
			out.println("else if(m_doc_code=='CEAS_ORDER'){");
			//out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Ceasing_order_letter_new?chksql=main_page&application_no="+m_application_no+"&client_code="+m_client_code+"&status="+m_status+"&print=FALSE';"); 	
			//out.println("window.location.href= '"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Ceasing_order_letter_new?chksql=main_page&repossession_no="+m_repossession_no+"&document_code=CEAS_ORDER&print=FALSE';"); 
			out.println("window.location.href= '"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Seizing_order_letter_new?chksql=main_page&repossession_no="+m_repossession_no+"&document_code=CEAS_ORDER&print=FALSE';"); 
			out.println("}");
			
			out.println("else if(m_doc_code=='DELE_LETT'){");
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Deletion_letter?chksql=main_page&application_no="+m_application_no+"&client_code="+m_client_code+"&status="+m_status+"&print=FALSE';"); 	
			out.println("}");
			

		//---------------------------------*
			out.println("else if(m_doc_code=='NIBSM_LETT'){");
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Document_NIBSM_Letter?chksql=main_page&application_no="+m_application_no+"&client_code="+m_client_code+"&status="+m_status+"&print=FALSE';"); 
			out.println("}");
			
			out.println("else if(m_doc_code=='CORATE_GUR'){");
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Document_Corporate_Guarantee?chksql=main_page&application_no="+m_application_no+"&client_code="+m_client_code+"&gur_name="+m_gur_name+"&status="+m_status+"&print=FALSE';"); 
			out.println("}");
			
			out.println("else if(m_doc_code=='RESOLU_COR'){");
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Document_Board_Resolution_Cooparate_Guarantee?chksql=main_page&application_no="+m_application_no+"&gur_code="+m_client_code+"&status="+m_status+"&print=FALSE';"); 
			out.println("}");
			
			out.println("else if(m_doc_code=='PARTN_INDE'){");
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Document_Partners_Indemnity?chksql=main_page&application_no="+m_application_no+"&gur_code="+m_client_code+"&status="+m_status+"&print=FALSE';"); 
			out.println("}");
			
			out.println("else if(m_doc_code=='LEASE_SCHE'){");
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Document_Lease_Schedule?chksql=main_page&application_no="+m_application_no+"&client_code="+m_client_code+"&client_type="+m_client_type+"&status="+m_status+"&print=FALSE';"); 
			out.println("}");
			//ADDED BY CHANDANA ON 12/06/2007
			
			out.println("else if(m_doc_code=='HP_RESIL'){");
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Document_Hirepur_Board_Resolution?chksql=main_page&application_no="+m_application_no+"&client_code="+m_client_code+"&client_type="+m_client_type+"&branch_code="+m_branch_code+"&vendor_code="+m_vendor_code+"&status="+m_status+"&print=FALSE';"); 
			//out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Documents_Status?chksql=save_page&scr_name=AF_MK_APP_STATUS_APPROVE_3&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&pur_ord_no="+m_purch_order+"&branch_code="+m_branch_code+"&vendor_code="+m_vendor_code+"&document_code="+m_document_code+"\";");			
			out.println("}");
			
      out.println("else if(m_doc_code=='HP_DELOD'){");
			//out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Document_Hirepur_ind_delivery_order?chksql=main_page&application_no="+m_application_no+"&client_code="+m_client_code+"&client_type="+m_client_type+"&status="+m_status+"&pur_ord_no="+m_purch_no+"&print=FALSE';"); 
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Document_Hirepur_ind_delivery_order?chksql=main_page&application_no="+m_application_no+"&client_code="+m_client_code+"&client_type="+m_client_type+"&branch_code="+m_branch_code+"&vendor_code="+m_vendor_code+"&status="+m_status+"&pur_ord_no="+m_purch_no+"&print=FALSE';"); 
			out.println("}");
						
			out.println("else if(m_doc_code=='CASH_PRIC'){");
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Document_cash_price_of_goods?chksql=main_page&application_no="+m_application_no+"&client_code="+m_client_code+"&client_type="+m_client_type+"&status="+m_status+"&print=FALSE';"); 
			out.println("}");

			
			out.println("else if(m_doc_code=='HP_SHEDUL'){");
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Document_hirepurchase_shedule?chksql=main_page&application_no="+m_application_no+"&client_code="+m_client_code+"&client_type="+m_client_type+"&status="+m_status+"&print=FALSE';"); 
			out.println("}");
			
			out.println("else if(m_doc_code=='HP_STAMP'){");
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Document_Hirepur_stamp_duty?chksql=main_page&application_no="+m_application_no+"&client_code="+m_client_code+"&client_type="+m_client_type+"&status="+m_status+"&print=FALSE';"); 
			out.println("}");

			
			
			
			out.println("else if(m_doc_code=='JAPP_NBJII'){");
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Document_Noobject_and_aware_LetII?chksql=main_page&application_no="+m_application_no+"&client_code="+m_client_code+"&client_type="+m_client_type+"&status="+m_status+"&print=FALSE';"); 
			out.println("}");
			
			out.println("else if(m_doc_code=='JAPP_NOOBJ'){");
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Document_Noobject_join_applicant?chksql=main_page&application_no="+m_application_no+"&client_code="+m_client_code+"&client_type="+m_client_type+"&status="+m_status+"&print=FALSE';"); 
			out.println("}");
			
			out.println("else if(m_doc_code=='IND_STAMP'){");
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Document_lease_ind_stamp_duty?chksql=main_page&application_no="+m_application_no+"&client_code="+m_client_code+"&client_type="+m_client_type+"&status="+m_status+"&print=FALSE';"); 
			out.println("}");
			
			out.println("else if(m_doc_code=='LEASE_IND'){");
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Document_lease_ind_Letter?chksql=main_page&application_no="+m_application_no+"&client_code="+m_client_code+"&client_type="+m_client_type+"&status="+m_status+"&print=FALSE';"); 
			out.println("}");
			
			out.println("else if(m_doc_code=='LEASE_VAR'){");
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Document_Vari_Lease_Schedule?chksql=main_page&application_no="+m_application_no+"&document_code=LEASE_VAR&print=FALSE&client_code="+m_client_code+"&client_type="+m_client_type+"';"); 
			out.println("}");
			
			// added by udara 09-01-2018
			out.println("else if(m_doc_code=='SIN_LETT_3'){");
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Document_sinhala_letter_3?chksql=main_page&application_no="+m_application_no+"&client_code="+m_client_code+"&status="+m_status+"&print=FALSE';"); 
			out.println("}");
			
			out.println("else if(m_doc_code=='SIN_LETT_2'){");
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Document_sinhala_letter_2?chksql=main_page&application_no="+m_application_no+"&client_code="+m_client_code+"&status="+m_status+"&print=FALSE';"); 
			out.println("}");
			// end by udara 09-01-2018
			
			// added by udara 09-01-2019
			out.println("else if(m_doc_code=='DEL_ORD'){");
			//out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_New_Delivery_order?chksql=main_page&application_no="+m_application_no+"&client_code="+m_client_code+"&status="+m_status+"&print=FALSE';"); 
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_New_Delivery_order?chksql=main_page&application_no="+m_application_no+"&client_code="+m_client_code+"&status="+m_status+"&print=FALSE&doc_code="+m_ref_no+"';"); // 
			
			out.println("}");
			// end by udara 09-01-2019
			
			
			
	    //END BY CHANDANA ON 12/06/2007
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
						
			out.println("</html>");
									
					
 
			
			
			}
	
			
		}
		catch (Exception e) {
			try{out.println(e.toString());}catch(Exception e1){}
      //return null;
		}finally{
		  if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
	    if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e){}}
			//try{conn.setAutoCommit(true);
		}
	}
}
