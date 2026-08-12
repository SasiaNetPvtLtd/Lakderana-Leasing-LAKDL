
//--
//SCREEN NAME:SAVE APPLICATION PROCESS
//CREATED BY:THAMALI JAYATUNGA
//DATE/TIME:2010.02.22
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MK_Save_App_Process_Delete_Securities extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
  String reqstr;
	Statement stmt;
	public ResultSet rs;
	ServletOutputStream out = null;
	String m_app_no;
	
	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
	synchronized(this){ 

		try {

			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   
			out = res.getOutputStream();
		
			PrintStream out = new PrintStream(res.getOutputStream());
	 //   out.println(reqstr);
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
			String m_screen_name="";
			//String m_app_no="";
			String m_status="";
      m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			
      
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			//conn.setAutoCommit(false); 	
			String m_chksql = req.getParameter("chksql");
			m_app_no = req.getParameter("app_no");
			
			
			String m_vehicle_no  = "NULL";
			String m_mortage_no  = "NULL";
			String m_fix_depo_no = "NULL";
			String m_run_case_no = "NULL";
			String m_sec_type    = "NULL";
			String m_pro_hadagasma_no    = "NULL";
			
			if(m_chksql.equals("vehicle")){
				m_vehicle_no = m_sn_methods.met_formdata(reqstr,"TXT_VEHICLE");
				m_sec_type = "V";
			}
			
			if(m_chksql.equals("land")){
				m_mortage_no = m_sn_methods.met_formdata(reqstr,"TXT_CAV_NO");
				m_sec_type = "L";
			}
			
			if(m_chksql.equals("fixed_deposit")){
				m_fix_depo_no = m_sn_methods.met_formdata(reqstr,"TXT_AC_NO");
				m_sec_type = "F";
			}
			
			if(m_chksql.equals("run_contract")){
				m_run_case_no = m_sn_methods.met_formdata(reqstr,"TXT_FIN_NO");
				m_sec_type = "R";
			}

			if(m_chksql.equals("product_hadagasma")){
				m_pro_hadagasma_no = m_sn_methods.met_formdata(reqstr,"TXT_ITEM_SUB_CAT");
				m_sec_type = "H";
			}
			
			String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"); 

			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MK_DELETE_SECURITIES(:1,:2,:3,:4,:5,:6,:7,:8); END;");
			callstmt.setString(1,m_app_no);
			callstmt.setString(2,m_vehicle_no);
			callstmt.setString(3,m_mortage_no);
			callstmt.setString(4,m_fix_depo_no);
			callstmt.setString(5,m_run_case_no);
			callstmt.setString(6,m_pro_hadagasma_no);
			callstmt.setString(7,m_scr_name);
			callstmt.setString(8,m_sec_type);
			callstmt.execute();
			
			/*

			if(m_chksql.equals("vehicle")){

			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MK_SAVE_APP_SECU_VEHICLE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28); END;");
			
			callstmt.setString(1,m_app_no);
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_VEHICLE"));
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT"));
			
			String reg_dd =m_sn_methods.met_formdata(reqstr,"TXT_REG_DD");
			String reg_mm =m_sn_methods.met_formdata(reqstr,"TXT_REG_MM");
			String reg_yy =m_sn_methods.met_formdata(reqstr,"TXT_REG_YY");
			String m_reg_date=reg_dd+"-"+reg_mm+"-"+reg_yy;
			callstmt.setString(4,m_reg_date);

			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_VEHICLE_TYPE"));
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_VEHICLE_MAKE"));
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_MANUFAC"));
			callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_CAPACITY"));
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_CHASIS"));
			callstmt.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_ENGINE"));
			callstmt.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_EXTEND"));
			callstmt.setString(12,m_sn_methods.met_formdata(reqstr,"TXT_FUEL"));
			callstmt.setString(13,m_sn_methods.met_formdata(reqstr,"TXT_ADD"));
			
			String cr_dd =m_sn_methods.met_formdata(reqstr,"TXT_CR_DD");
			String cr_mm =m_sn_methods.met_formdata(reqstr,"TXT_CR_MM");
			String cr_yy =m_sn_methods.met_formdata(reqstr,"TXT_CR_YY");
			String m_cr_date=cr_dd+"-"+cr_mm+"-"+cr_yy;
			callstmt.setString(14,m_cr_date);

			callstmt.setString(15,m_sn_methods.met_formdata(reqstr,"TXT_PROVINCE"));
			callstmt.setString(16,m_sn_methods.met_formdata(reqstr,"TXT_CR"));
			callstmt.setString(17,m_sn_methods.met_formdata(reqstr,"TXT_VALUER"));
			
			String val_dd =m_sn_methods.met_formdata(reqstr,"TXT_VAL_DD");
			String val_mm =m_sn_methods.met_formdata(reqstr,"TXT_VAL_MM");
			String val_yy =m_sn_methods.met_formdata(reqstr,"TXT_VAL_YY");
			String m_val_date=val_dd+"-"+val_mm+"-"+val_yy;
			callstmt.setString(18,m_val_date);

			callstmt.setString(19,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_VALUE")));
			callstmt.setString(20,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_SALES_VALUE")));
			callstmt.setString(21,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_USAGE")));
			callstmt.setString(22,m_sn_methods.met_formdata(reqstr,"TXT_CONDITION"));
			callstmt.setString(23,m_sn_methods.met_formdata(reqstr,"TXT_NOTES"));
			callstmt.setString(24,m_sn_methods.met_formdata(reqstr,"TXT_REMARKS"));
			callstmt.setString(25,m_sn_methods.met_formdata(reqstr,"TXT_BODY"));
			callstmt.setString(26,"Y");
			callstmt.setString(27,m_username);
			callstmt.setString(28,m_scr_name);
			callstmt.execute();
			}	
			
			
			
			if(m_chksql.equals("land")){

			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MK_SAVE_APP_SECU_LAND(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12); END;");
			
			callstmt.setString(1,m_app_no);
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_CAV_NO"));
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_MORTGAGE"));
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_DEED_NO"));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_LAND_ADD"));
			callstmt.setString(6,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_LAND_VALUE")));
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_VAL_NAME"));
			String val_dd =m_sn_methods.met_formdata(reqstr,"TXT_VAL_DD");
			String val_mm =m_sn_methods.met_formdata(reqstr,"TXT_VAL_MM");
			String val_yy =m_sn_methods.met_formdata(reqstr,"TXT_VAL_YY");
			String m_val_date=val_dd+"-"+val_mm+"-"+val_yy;
			callstmt.setString(8,m_val_date);
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_REMARKS"));
			callstmt.setString(10,"Y");
			callstmt.setString(11,m_username);
			callstmt.setString(12,m_scr_name);
			callstmt.execute();
			}	
			
			if(m_chksql.equals("fixed_deposit")){

			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MK_SAVE_APP_SECU_FIXED_DEP(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12); END;");
			
			callstmt.setString(1,m_app_no);
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_AC_NO"));
			callstmt.setString(3,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_DEP_AMT")));
			String st_dd =m_sn_methods.met_formdata(reqstr,"TXT_ST_DD");
			String st_mm =m_sn_methods.met_formdata(reqstr,"TXT_ST_MM");
			String st_yy =m_sn_methods.met_formdata(reqstr,"TXT_ST_YY");
			String m_st_date=st_dd+"-"+st_mm+"-"+st_yy;
			callstmt.setString(4,m_st_date);
			String matu_dd =m_sn_methods.met_formdata(reqstr,"TXT_MATU_DD");
			String matu_mm =m_sn_methods.met_formdata(reqstr,"TXT_MATU_MM");
			String matu_yy =m_sn_methods.met_formdata(reqstr,"TXT_MATU_YY");
			String m_matu_date=matu_dd+"-"+matu_mm+"-"+matu_yy;
			callstmt.setString(5,m_matu_date);
			String inter_dd =m_sn_methods.met_formdata(reqstr,"TXT_INTER_DD");
			String inter_mm =m_sn_methods.met_formdata(reqstr,"TXT_INTER_MM");
			String inter_yy =m_sn_methods.met_formdata(reqstr,"TXT_INTER_YY");
			String m_inter_date=inter_dd+"-"+inter_mm+"-"+inter_yy;
			callstmt.setString(6,m_inter_date);
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_INTER_PAY"));
			callstmt.setString(8,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_PERIOD")));
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_REMARKS"));
			callstmt.setString(10,"Y");
			callstmt.setString(11,m_username);
			callstmt.setString(12,m_scr_name);
			callstmt.execute();
			}	
			
			
			//run_contract
			// added by udara 19-02-2014
			
			if(m_chksql.equals("run_contract")){

			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".SAVE_AF_ASSET_RUN_CONTRACTS(:1,:2,:3,:4); END;");
			
			callstmt.setString(1,m_app_no);
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_FIN_NO"));
			callstmt.setString(3,m_scr_name);
			callstmt.setString(4,m_username);
			callstmt.execute();
			}
			
			// end by udara 19-02-2014
			*/
			

		  callstmt.close();
			conn.commit(); 

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("m_scr_name='"+m_screen_name+"'");
			//out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_MK_Application_Process_Additional_Securities?APP_NO="+m_app_no+"';");
		  out.println("window.close();");	
			
			//out.println("alert(check this); ");
			out.println("window.opener.chk_totals();"); // added by udara 03-12-2014
			
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();
      out.close();
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
}
