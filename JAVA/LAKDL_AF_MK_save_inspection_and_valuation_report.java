//--
//SCREEN NAME:SAVE MARKETING -INSPECTION AND VALUATION
//CREATED BY :delanjali
//DATE/TIME  :
//NOTES      :

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MK_save_inspection_and_valuation_report extends HttpServlet {
		
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
			//out.println(reqstr);

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
			int m_chksql;
			String m_field,number;
			String m_app_no;
			String m_valuation_no="";
			String m_valuation_no1="";
			String m_val_date="";
			String m_reg_date="";
			
			
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
			
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_SAVE_INSPEC_AND_VAL_RPT(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28,:29); END;");
			

     if(m_valuation_no.equals("")){
        callstmt.registerOutParameter(1,java.sql.Types.CHAR);	
      }
     else{
				callstmt.setString(1,m_valuation_no.trim());
			}
			
			//m_val_date = m_sn_methods.met_formdata(reqstr,"HID_TXT_VALUATION_DATE");
			//m_reg_date = m_sn_methods.met_formdata(reqstr,"HID_TXT_REG_DATE");
			
			String m_val_date_dd = m_sn_methods.met_formdata(reqstr,"TXT_VALUATION_DATE_DD");
			String m_val_date_mm = m_sn_methods.met_formdata(reqstr,"TXT_VALUATION_DATE_MM");
			String m_val_date_yy = m_sn_methods.met_formdata(reqstr,"TXT_VALUATION_DATE_YY");
			
			
			
			if(m_val_date_dd.equals("") && m_val_date_mm.equals("") &&  m_val_date_yy.equals("") )
			{
			m_val_date=m_val_date_dd+m_val_date_mm+m_val_date_yy; 
			}
			else
			{
			m_val_date=m_val_date_dd+"-"+m_val_date_mm+"-"+m_val_date_yy;
			}
			
			
			
			String m_reg_date_dd = m_sn_methods.met_formdata(reqstr,"TXT_REG_DATE_DD");
			String m_reg_date_mm = m_sn_methods.met_formdata(reqstr,"TXT_REG_DATE_MM");
			String m_reg_date_yy = m_sn_methods.met_formdata(reqstr,"TXT_REG_DATE_YY");
			
			
			
							
		//	m_reg_date=m_reg_date_dd+'-'+m_reg_date_mm+'-'+m_reg_date_yy;
			
		//	if(m_reg_date_dd==null || m_reg_date_mm==null || m_reg_date_yy==null){
		//	m_reg_date="";
		//	}
			
				
			if(m_reg_date_dd.equals("") && m_reg_date_mm.equals("") &&  m_reg_date_yy.equals("") )
			{
			m_reg_date=m_reg_date_dd+m_reg_date_mm+m_reg_date_yy; 
			}
			else
			{
			m_reg_date=m_reg_date_dd+"-"+m_reg_date_mm+"-"+m_reg_date_yy;
			}
			
						
			
		//	if(m_val_date.equals("--")){
		//	 m_val_date = null;
			//}
			
			
			
			
			callstmt.setString(2,(m_sn_methods.met_formdata(reqstr,"TXT_ASSET_ID")).trim());
			callstmt.setString(3,(m_sn_methods.met_formdata(reqstr,"TXT_SUB_MODEL_CODE")).trim());
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_REG_NO"));
			
			//callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_ENGINE_NO"));
			
			String m_eng_no = m_sn_methods.met_formdata(reqstr,"TXT_ENGINE_NO");
			
			if(m_eng_no.equals("")){
			callstmt.setString(5,"-");
			}
			else
			{
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_ENGINE_NO"));
			}
			
			
			//callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_CHASSIS_NO"));
			
		  String	m_chassis_no = m_sn_methods.met_formdata(reqstr,"TXT_CHASSIS_NO");
			
			
			if(m_chassis_no.equals("")){
			callstmt.setString(6,"-");
			}
			else
			{
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_CHASSIS_NO"));
			}
			
			
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_COLOUR"));
			callstmt.setString(8,(m_sn_methods.met_formdata(reqstr,"TXT_MODEL_CODE")).trim());
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_NOTES"));
			callstmt.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_REMARKS"));
			callstmt.setString(11,m_val_date);
			callstmt.setString(12,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_VALUE")));
			callstmt.setString(13,m_sn_methods.met_formdata(reqstr,"TXT_TYPE_OF_BODY"));
			callstmt.setString(14,m_reg_date);
			//callstmt.setString(15,m_sn_methods.met_formdata(reqstr,"TXT_METER_READING"));
			callstmt.setString(15,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_METER_READING")));

			callstmt.setString(16,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(17,m_username);
			callstmt.setString(18,m_sn_methods.met_formdata(reqstr,"TXT_GENERAL_INDEX"));
			callstmt.setString(19,m_sn_methods.met_formdata(reqstr,"TXT_SEATING_CAPACITY"));
			callstmt.setString(20,m_sn_methods.met_formdata(reqstr,"TXT_NO_OF_CYLINDERS"));
			callstmt.setString(21,Integer.toString(xx));
			callstmt.setString(22,m_app_no);
			callstmt.setString(23,"");
			callstmt.setString(24,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_NO"));
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
						
			/*for (int k=0; k<m_chksql; k++) 
			{
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_SAVE_INS_AND_VAL_RPT1(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");
			String m_status_1= m_sn_methods.met_formdata(reqstr,"TXT_STATUS_"+k);
			String m_field_code=m_sn_methods.met_formdata(reqstr,"TXT_FILED_CODE_"+k).trim();
			
			
			if(m_status_1==null || m_status_1==""){
			m_status_1="-";
			}
			if(m_field_code.equals("") || m_field_code.equals("-") || m_field_code==null){
			m_field_code="-";
			}
			callstmt.setString(1,m_valuation_no1);
			callstmt.setString(2,(m_sn_methods.met_formdata(reqstr,"TXT_ASSET_ID")).trim());
			callstmt.setString(3,m_field_code);
			
			//callstmt.setString(3,(m_sn_methods.met_formdata(reqstr,"TXT_FILED_CODE_"+k)).trim());
			callstmt.setString(4,m_status_1);
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_REMARK_"+k));
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(7,m_username);
			callstmt.setString(8,Integer.toString(k));
			callstmt.setString(9,"AF_AD_VALUATION_DETAILS");
			
			
			callstmt.execute();
			conn.commit();
			}
			*/
			callstmt.close();


	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("var m_app_no='';");
			out.println("m_app_no='"+m_app_no+"';");
			out.println("alert("+m_msg+");");
			
			out.println("window.close();");
			
			out.println("if('"+m_my_screen+"'==''){");
			//out.println("window.opener.chk_totals()");
			
			out.println("		if(confirm(\"Are you sure you want to add a new valuation ?\")){ "); 
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_MK_display_inspection_and_valuation_report?APP_NO="+m_app_no+"';");
			out.println("	}");
			
			out.println("	else	if(confirm(\"Are you sure you want to add a Documents ?\")){ "); 
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_MK_Application_Process_Document_Required?chksql=main_page&Hid_scr_name=AF_MK_APPLICATION_PROCESS&APP_NO="+m_app_no+"&CLIENT_CODE='+window.opener.document.Form1.TXT_APPLICANT_CODE.value+'&CORE_APP_CODE='+window.opener.document.Form1.TXT_CORE_APPLICANT_CODE.value+'&ac_status=Y&hid_records='+window.opener.document.Form1.hid_no_rec.value+'';"); 
		  //out.println("window.opener.document.Form1.TXT_APPLICANT_CODE.value=\""+client_code+"\"");
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
			//if(input     !=null){try{input.close();    }catch(Exception e){}}
			//if(callstmt1 !=null){try{callstmt1.close();}catch(Exception e){}}
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}

	}
}
		
