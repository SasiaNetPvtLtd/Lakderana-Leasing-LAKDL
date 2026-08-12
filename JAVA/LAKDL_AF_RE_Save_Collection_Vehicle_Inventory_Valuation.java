
//--
//SCREEN NAME:SAVE MARKETING -VALUATION
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_RE_Save_Collection_Vehicle_Inventory_Valuation extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
  String reqstr;
	ServletOutputStream out = null;
	Statement stmt;
	public ResultSet rs;

	
	
	public synchronized void  service(HttpServletRequest req, HttpServletResponse res)	throws IOException{


		try {

			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
			out = res.getOutputStream();
			//out.println(reqstr);

			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			//************************************************************	
			conn =m_sn_methods.met_user_validate(req);
			//**************************************************************		
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_fschema_name=m_sn_methods.client_name.trim();

			String m_html_client_url;
			String m_class_url;
			String m_class_name_save;
			String m_save_procedure_name;
			int m_chksql;
			String m_field,number;
			String m_app_no;
			String m_valuation_no="";
			String m_valuation_no1="";
			String m_val_date="";
			String m_reg_date="";
			
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();

			
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();

			conn.setAutoCommit(false);
			
      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name");
      String m_screen_name=(String)m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");
			
			m_chksql = Integer.parseInt(req.getParameter("number"));
			m_field=req.getParameter("field");
			
			int xx=0;
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			
			 
			m_app_no=m_sn_methods.met_formdata(reqstr,"TXT_APP_NO").trim();
			
			String m_my_screen="";
			
			m_my_screen =  req.getParameter("my_screen_name");
			
			if(m_my_screen==null){
			m_my_screen="";
			
			}
				
			
			if(!m_screen_name.equals("NEW")){
			 m_valuation_no=(String)m_sn_methods.met_formdata(reqstr,"TXT_VALUATION_NO");
			}
			
			
			//callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_SAVE_INSPEC_AND_VAL_RPT(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25); END;");
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_SAVE_INSPEC_AND_VAL_RPT(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28,:29); END;");

			
		
		if(m_valuation_no.equals("")){
        callstmt.registerOutParameter(1,java.sql.Types.CHAR);	
      }
     else{
				callstmt.setString(1,m_valuation_no.trim());
			}
			
			m_val_date = m_sn_methods.met_formdata(reqstr,"HID_TXT_VALUATION_DATE");
			m_reg_date = m_sn_methods.met_formdata(reqstr,"HID_TXT_REG_DATE");
			
			if(m_val_date.equals("--")){
			 m_val_date = null;
			}
			if(m_reg_date.equals("--")){
			 m_reg_date = null;
			}
			
			callstmt.setString(2,(m_sn_methods.met_formdata(reqstr,"TXT_ASSET_ID")).trim());
			callstmt.setString(3,(m_sn_methods.met_formdata(reqstr,"TXT_SUB_MODEL_CODE")).trim());
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_REG_NO"));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_ENGINE_NO"));
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_CHASSIS_NO"));
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_COLOUR"));
			callstmt.setString(8,(m_sn_methods.met_formdata(reqstr,"TXT_MODEL_CODE")).trim());
			
			//callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_NOTES"));
			//callstmt.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_REMARKS"));
			//added by nuwan de silva on 07-11-07---------------------------------
			callstmt.setString(9,m_sn_methods.format_text_area_string(m_sn_methods.met_formdata(reqstr,"TXT_NOTES")));
			callstmt.setString(10,m_sn_methods.format_text_area_string(m_sn_methods.met_formdata(reqstr,"TXT_REMARKS")));
			
			callstmt.setString(11,m_val_date);
			callstmt.setString(12,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_VALUE")));
			callstmt.setString(13,m_sn_methods.met_formdata(reqstr,"TXT_TYPE_OF_BODY"));
			callstmt.setString(14,m_reg_date);
			callstmt.setString(15,m_sn_methods.met_formdata(reqstr,"TXT_METER_READING"));
			callstmt.setString(16,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(17,m_username);
			callstmt.setString(18,m_sn_methods.met_formdata(reqstr,"TXT_GENERAL_INDEX"));
			callstmt.setString(19,m_sn_methods.met_formdata(reqstr,"TXT_SEATING_CAPACITY"));
			callstmt.setString(20,m_sn_methods.met_formdata(reqstr,"TXT_NO_OF_CYLINDERS"));
			callstmt.setString(21,Integer.toString(xx));
			callstmt.setString(22,m_app_no);
			callstmt.setString(23,m_sn_methods.met_formdata(reqstr,"TXT_INVENTORY_NO"));
			callstmt.setString(24,"");
			//callstmt.setString(25,"");
			callstmt.setString(25,m_sn_methods.met_formdata(reqstr,"TXT_VALUER_CODE"));
			callstmt.setString(26,m_sn_methods.met_formdata(reqstr,"TXT_YEAR_OF_MANUFACTURE"));
			callstmt.setString(27,m_sn_methods.met_formdata(reqstr,"TXT_CONDITION_OF_ASSET"));
			callstmt.setString(28,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_FORCED_VALUE")));
			callstmt.setString(29,m_scr_name);

		
		callstmt.execute();
			
			
			if(m_screen_name.equals("NEW")){
	      m_valuation_no1 =callstmt.getString(1);
				m_msg = "'"+m_valuation_no1+" Valuation saved successfully.'";
			}
			else{
				m_valuation_no1=(String)m_sn_methods.met_formdata(reqstr,"TXT_VALUATION_NO");
			}
						
			for (int k=0; k<=m_chksql; k++) 
			{
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_SAVE_INS_AND_VAL_RPT1(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");
		
		  String m_status_1= m_sn_methods.met_formdata(reqstr,"TXT_STATUS_"+k);
			
			
			if(m_status_1==null || m_status_1==""){
			m_status_1="-";
			}
			
			callstmt.setString(1,m_valuation_no1);
			callstmt.setString(2,(m_sn_methods.met_formdata(reqstr,"TXT_ASSET_ID")).trim());
			callstmt.setString(3,(m_sn_methods.met_formdata(reqstr,"TXT_FILED_CODE_"+k)).trim());
			callstmt.setString(4,m_status_1);
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_REMARK_"+k));
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(7,m_username);
			callstmt.setString(8,Integer.toString(k));
			callstmt.setString(9,"AF_AD_VALUATION_DETAILS");
			
			
			callstmt.execute();
			}
			
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_SAVE_INSPEC_RPT2(:1,:2,:3,:4,:5,:6); END;");
			callstmt.setString(1,m_valuation_no1);
			callstmt.setString(2,"VAL_ENT");
			//callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(3,"NEW");
			callstmt.setString(4,m_username);
			callstmt.setString(5,"AF_AD_VALUATION_DETAILS");
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_INVENTORY_NO"));

					callstmt.execute();




							///*
							
							//-----------------------------------------------------------------------------------------------
							//--MODIFIED BY : DELANJALI----------------------------------------------------------------------
							//--DATE				: 2007-02-20---------------------------------------------------------------------
							//--PURPOSE			: TO INSERT A RECORD TO SUS PAYMENT----------------------------------------------
							
							String m_engin=m_sn_methods.met_formdata(reqstr,"TXT_ENGINE_NO");
							String m_pur="";
							String m_invoice="";
							
							
				//Commented by Dineth on 06-04-2009
				/*
							 rs = stmt.executeQuery ("SELECT "+
			   			 "INVOICE_NO,A.APPLICATION_NO,A.PURCHASE_ORDER_NO "+
			 				 "FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER B,  "+
							 ""+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_DET C "+
			 				 "WHERE A.ENGINE_NO='"+m_engin+"' "+
						   "AND C.PURCHASE_ORDER_NO=B.PURCHASE_ORDER_NO "+
						   "AND A.PURCHASE_ORDER_NO=B.PURCHASE_ORDER_NO "+
							 "AND A.APPLICATION_NO='"+m_app_no+"' "+
						   "AND INVOICE_NO=PRO_INVOICE_NO ");
				
							boolean more = rs.next();
							if(more){
								m_pur=rs.getString(3);
								m_invoice=rs.getString(1);
							}			
							*/
						//	out.println("m_pur"+m_pur);
						//			out.println("m_invoice"+m_invoice);
					/*
							callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_GEN_VENDOR_PAYMENT(:1,:2,:3,:4,:5); END;");
				
              callstmt.setString(1,m_invoice);
	            callstmt.setString(2,m_pur);
	            callstmt.setString(3,"AF_RE_COLLECTION_VEHICLE_INVENTORY");
	  					callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
              callstmt.setString(5,m_username);
							callstmt.execute();
*/
							//-----------------------------------------------------------------------------------------------
//
			
		///	conn.commit();
			//callstmt.close();
      //End by Dineth on 06-04-2009
			String m_veh	= m_sn_methods.met_formdata(reqstr,"TXT_REG_NO");
			String m_eng	=	m_sn_methods.met_formdata(reqstr,"TXT_ENGINE_NO");
			String m_chas	=	m_sn_methods.met_formdata(reqstr,"TXT_CHASSIS_NO");
			String m_inv	=	m_sn_methods.met_formdata(reqstr,"TXT_INVENTORY_NO");
		//	out.println("@@@@@"+m_inv);

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("var m_app_no='';");
			out.println("m_app_no='"+m_app_no+"';");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_RE_Collection_Vehicle_Inventory_Valuation?inv_no="+m_inv+"&vehicle_no="+m_veh+"&chassis_no="+m_chas+"&engine_no="+m_eng+"';");
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
			//if(input     !=null){try{input.close();    }catch(Exception e){}}
			//if(callstmt1 !=null){try{callstmt1.close();}catch(Exception e){}}
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}

	}
}
		
