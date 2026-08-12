
//--
//SCREEN NAME:SAVE PAYMENT DETAILS
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_CR_PRO_save_payment_details extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
  String reqstr;
	ServletOutputStream out = null;
	public synchronized void  service(HttpServletRequest req, HttpServletResponse res)	throws IOException{


		try {

			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
			out = res.getOutputStream();

			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			//************************************************************	
			conn =m_sn_methods.met_user_validate(req);
			//**************************************************************		
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url;
			String m_class_url;
			String m_class_name_save;
			String m_save_procedure_name;
			int m_doc,m_cr,m_oth;
			String m_st;
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			
			conn.setAutoCommit(false);
			
      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			
			m_doc = Integer.parseInt(req.getParameter("number"));
			m_cr = Integer.parseInt(req.getParameter("number1"));
			m_oth = Integer.parseInt(req.getParameter("number2"));
			
			//out.println("M_CR"+m_cr);
		//	out.println("M_DOC"+m_doc);
		//	m_st = req.getParameter("v_status");

			//String m_chk=m_sn_methods.met_formdata(reqstr,"CHK_ST"+k);
			

			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_PAYMENT_DETAILS(:1,:2,:3,:4,:5); END;");
	
		String m_pro=m_sn_methods.met_formdata(reqstr,"TXT_PURCHASE_ORDER_NO");
	
			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_PURCHASE_ORDER_NO"));
		//	callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_STAGE"));
			//callstmt.setString(2,"AF_CRO_PRO_PAYMENT_DETAILS");--ask
			out.println("TXT_STATUS"+m_sn_methods.met_formdata(reqstr,"TXT_STATUS"));

			callstmt.setInt(2,1);
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_STATUS"));
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(5,m_username);
			
			if  (!m_pro.trim().equals("")) {
					//	  break;
					callstmt.execute();
			}
		
			
			for (int j=0; j<=m_doc; j++) 
			{
	
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_PAYMENT_APP_DOC(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12); END;");

			String m_app=m_sn_methods.met_formdata(reqstr,"hid_TXT_DOC_CODE"+j);
			String m_chk=m_sn_methods.met_formdata(reqstr,"CHK_STATUS"+j);
			String m_chk_napp=m_sn_methods.met_formdata(reqstr,"CHK_NAPP"+j);

			out.println("TXT_APPLICATION_NO"+m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
			out.println("hid_TXT_DOC_CODE"+m_sn_methods.met_formdata(reqstr,"hid_TXT_DOC_CODE"+j));
			out.println("CHK_STATUS"+m_sn_methods.met_formdata(reqstr,"CHK_STATUS"+j));
			out.println("TXT_REMARK"+m_sn_methods.met_formdata(reqstr,"TXT_REMARK"+j));
			out.println("TXT_PURCHASE_ORDER_NO"+m_sn_methods.met_formdata(reqstr,"TXT_PURCHASE_ORDER_NO"));
			out.println("TXT_INVOICE_CODE"+m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_CODE"+j));
			out.println("SCREEN_NAME"+m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			out.println("m_username"+m_sn_methods.met_formdata(reqstr,"m_username"));
			out.println("j"+j);
			out.println("CHK_NAPP"+m_sn_methods.met_formdata(reqstr,"CHK_NAPP"+j));

			if(m_chk.equals("")){
			m_chk="N";
		///	out.println("CHK_STATUS@@@@@@@@"+m_sn_methods.met_formdata(reqstr,"CHK_STATUS"+j));

			}
			if(m_chk_napp.equals("")){
			m_chk_napp="N";
	//		out.println("CHK_STATUS@@@@@@@@"+m_sn_methods.met_formdata(reqstr,"CHK_STATUS"+j));

			}

			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
			callstmt.setString(2,"AF_CRO_PRO_PAYMENT_DETAILS");
		//	callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_STAGE"+j));
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"hid_TXT_DOC_CODE"+j));
		//	callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"CHK_STATUS"+j));
					callstmt.setString(4,m_chk);

			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_REMARK"+j));
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_PURCHASE_ORDER_NO"));
			callstmt.setString(7,"");
			callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_CODE"+j));
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(10,m_username);
			callstmt.setString(11,Integer.toString(j));
			callstmt.setString(12,m_chk_napp);

			//callstmt.setString(12,m_sn_methods.met_formdata(reqstr,"CHK_NAPP"+j));
			

			if  (m_app.trim().equals("")) {
						  break;
			}
			callstmt.execute();
			
			}
			
			out.println("m_cr"+m_cr);

				for (int d=0; d<=m_cr; d++) 
			{
		
			String m_pro1=m_sn_methods.met_formdata(reqstr,"TXT_PURCHASE_ORDER_NO");

			String m_inv=m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_CODE"+d);
			//out.println("purord"+m_pro1);
			//out.println("invoice"+m_inv);

			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_PAYMENT_CRBOOKS(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14); END;");
			//out.println("d"+d);
			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_PURCHASE_ORDER_NO"));

			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_ENGIN_DOCS"+d));
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_CHASI_DOCS"+d));
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_CODE"+d));

			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_VEHICLE_NO"+d));
			
			
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(7,m_username);
			callstmt.setString(8,Integer.toString(d));
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
			
			callstmt.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_INSURANCE_DATE"+d));
			callstmt.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_REVENUE_LICENSE_DATE"+d));
			callstmt.setString(12,m_sn_methods.met_formdata(reqstr,"TXT_LUXURY_TAX_DATE"+d));
			callstmt.setString(13,m_sn_methods.met_formdata(reqstr,"TXT_DISTRICT_CODE"+d));
			callstmt.setString(14,m_sn_methods.met_formdata(reqstr,"TXT_DRIVING_LICENSE_DATE"+d));

			if  (m_inv.trim().equals("")) {
						  break;
			}
			
			callstmt.execute();
			
			}
			
			
				for (int a=0; a<=m_oth; a++) 
			{
	
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_PAYMENT_APP_DOC(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12); END;");

			String m_app_oth=m_sn_methods.met_formdata(reqstr,"hid_TXT_OTH_DOC"+a);
			String m_chk1=m_sn_methods.met_formdata(reqstr,"CHK_ST"+a);
			
			String m_chk_nth=m_sn_methods.met_formdata(reqstr,"CHK_NAPP_OTH"+a);

			out.println("***m_app_oth"+m_sn_methods.met_formdata(reqstr,"hid_TXT_OTH_DOC"+a));
			out.println("***m_chk"+m_sn_methods.met_formdata(reqstr,"CHK_ST"+a));
		//	out.println("***"+m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_CODE"+a));
			

			if(m_chk1.equals("")){
			m_chk1="N";

			}
				if(m_chk_nth.equals("")){
			m_chk_nth="N";

			}
			
			
			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
			callstmt.setString(2,"AF_CRO_PRO_PAYMENT_DETAILS");
		//	callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_STAGE"+j));
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"hid_TXT_OTH_DOC"+a));
			callstmt.setString(4,m_chk1);

			//callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"CHK_ST"+a));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_OTH_REMARK"+a));
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_PURCHASE_ORDER_NO"));
			callstmt.setString(7,"");
			callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_CODE"+a));
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(10,m_username);
			callstmt.setString(11,Integer.toString(a));
		//	callstmt.setString(12,m_sn_methods.met_formdata(reqstr,"CHK_NAPP_OTH"+j));
			callstmt.setString(12,m_chk_nth);


			if  (m_app_oth.trim().equals("")) {
						  break;
			}
			callstmt.execute();
			
			}

			
			
			
			
			
		
			conn.commit();
			callstmt.close();


	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/LAKDL_AF_CR_PRO_display_payment_details';");
			//out.println("new_window();"); 
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();

		}
		/*
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert('Error When Saving Record..');");
			out.println("window.history.back();"); 
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();
      out.close();
		}
		finally{
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}

	}
}
*/
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
			//if(input     !=null){try{input.close();    }catch(Exception e){}}
			//if(callstmt1 !=null){try{callstmt1.close();}catch(Exception e){}}
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}

	}
}
		
