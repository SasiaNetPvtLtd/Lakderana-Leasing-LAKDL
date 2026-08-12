// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006

    
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
   
public class LAKDL_FA_CR_Save_client_facility_creation extends HttpServlet {

	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt,callstmt1,callstmt2;
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
			conn.setAutoCommit(false);
			//**************************************************************		
			//out.println("reqstr="+reqstr);
			String m_fschema_name = m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url=m_sn_methods.html_client_url;
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			
      String m_facility_code="";
			String m_client_code="";
			String m_date="";
			int m_num=0;
			int m_tnum=0;
			String m_scr_num="0";
			
			m_msg = "'Information saved successfully for Facility No:";

			//------------------------------------------------------------------------------------------
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_SAVE_CLIENT_FACILITY(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15); END;"); 

			m_facility_code=m_sn_methods.met_formdata(reqstr,"TXT_FACILITY_NO");
			m_client_code=m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE");
			
			if (m_facility_code.equals("")){
				callstmt.registerOutParameter(1,java.sql.Types.CHAR);
			}
			else {
				callstmt.setString(1,m_facility_code);
      }

			callstmt.setString(2,m_client_code);
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_FACILITY_MANAGER"));
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_PRODUCT_CODE"));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_FEE_CODE"));
			callstmt.setString(6,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_CREDIT_LIMIT")));
			callstmt.setString(7,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_CREDIT_PERIOD")));
			callstmt.setString(8,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_TOL_CREDIT_PERIOD")));
			callstmt.setString(9,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_RES_MARGIN")));
			callstmt.setString(10,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_INT_RATE")));
			m_date=m_sn_methods.met_formdata(reqstr,"TXT_START_DD")+"-"+
										m_sn_methods.met_formdata(reqstr,"TXT_START_MM")+"-"+
										m_sn_methods.met_formdata(reqstr,"TXT_START_YY");
			callstmt.setString(11,m_date);
			m_date=m_sn_methods.met_formdata(reqstr,"TXT_END_DD")+"-"+
										m_sn_methods.met_formdata(reqstr,"TXT_END_MM")+"-"+
										m_sn_methods.met_formdata(reqstr,"TXT_END_YY");
			callstmt.setString(12,m_date);
			callstmt.setString(13,m_username);
			callstmt.setString(14,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(15,m_sn_methods.format_text_area_string(m_sn_methods.met_formdata(reqstr,"TXT_COMMENTS")));
			callstmt.execute();
			
			if (m_facility_code.equals("")){
				m_facility_code=callstmt.getString(1);
			}
			
			callstmt.close();
			//------------------------------------------------------------------
			m_num=0;
			callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_SAVE_CLIENT_FACI_PRODUCT(:1,:2,:3,:4,:5,:6,:7); END;");
			m_scr_num=(String)m_sn_methods.met_formdata(reqstr,"hid_product_count");
			m_num=Integer.parseInt(m_scr_num);	
			
			m_tnum=0;
			
			if(m_num>0){
				for(int i=1;i<=m_num;i++){
					String m_product_code=m_sn_methods.met_formdata(reqstr,"TXT_PRODUCT_FEATURE_CODE_"+i);
					String m_status=m_sn_methods.met_formdata(reqstr,"CHK_PRODUCT_"+i);
					if(m_status.equals("on")){
						if(!m_product_code.equals("")){
							callstmt1.setString(1,m_facility_code);
							callstmt1.setString(2,m_client_code);
							callstmt1.setString(3,m_product_code);
							callstmt1.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_PRODUCT_FEATURE_PARAM_"+i));
							callstmt1.setString(5,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
							callstmt1.setString(6,m_username);
							callstmt1.setInt(7,m_tnum);
							callstmt1.execute();
							m_tnum++;
						}
					}
				}
				callstmt1.close();
			}
			//-------------------------------------------------------------------------------------------
			m_num=0;
			callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_SAVE_CLIENT_FACI_FEE(:1,:2,:3,:4,:5,:6,:7); END;");
			m_scr_num=(String)m_sn_methods.met_formdata(reqstr,"hid_fee_count");
			m_num=Integer.parseInt(m_scr_num);
			
			m_tnum=0;
			
			if(m_num>0){
				for(int i=1;i<=m_num;i++){
					String m_fee_code=m_sn_methods.met_formdata(reqstr,"TXT_FEE_CODE_"+i);
					String m_status=m_sn_methods.met_formdata(reqstr,"CHK_FEE_"+i);
					if(m_status.equals("on")){
						if(!m_fee_code.equals("")){
							callstmt1.setString(1,m_facility_code);
							callstmt1.setString(2,m_client_code);
							callstmt1.setString(3,m_fee_code);
							callstmt1.setString(4,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_FEE_VALUE_"+i)));
							callstmt1.setString(5,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
							callstmt1.setString(6,m_username);
							callstmt1.setInt(7,m_tnum);
							callstmt1.execute();
							m_tnum++;
						}
					}
				}
				callstmt1.close();
			}
		  
			//-------------------------------------------------------------------------------------------
			m_num=0;
			callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_SAVE_CLIENT_FACI_GURANT(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12); END;");
			m_scr_num=(String)m_sn_methods.met_formdata(reqstr,"hid_guarantee_count");
			m_num=Integer.parseInt(m_scr_num);
			
			m_tnum=0;
			
			if(m_num>0){
				for(int i=1;i<=m_num;i++){
					String m_gur_name=m_sn_methods.met_formdata(reqstr,"TXT_GUR_NAME"+i);
					if(!m_gur_name.equals("")){
						callstmt1.setString(1,m_facility_code);
						callstmt1.setString(2,m_client_code);
						callstmt1.setString(3,m_gur_name);
						callstmt1.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_GUR_BANK_"+i));
						callstmt1.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_GUR_CONT_PERSON_"+i));
						callstmt1.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_GUR_START_DATE_"+i));
						callstmt1.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_GUR_END_DATE_"+i));
						callstmt1.setString(8,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_GUR_VALUE_"+i)));
						callstmt1.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_GUR_COMMENT_"+i));
						callstmt1.setString(10,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
						callstmt1.setString(11,m_username);
						callstmt1.setInt(12,m_tnum);
						callstmt1.execute();
						m_tnum++;
					}
				}
				callstmt1.close();
			}
		
			//-------------------------------------------------------------------------------------------
			//added by disnaka on 2011-10-05
			
			m_num=0;
			callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_SAVE_CLIENT_FAC_GURANT(:1,:2,:3,:4,:5); END;");
			m_scr_num=(String)m_sn_methods.met_formdata(reqstr,"hid_guarantor_count");
			m_num=Integer.parseInt(m_scr_num);
			
			m_tnum=1;
			
			if(m_num>0){
				for(int i=1;i<=m_num;i++){
					String m_gurantor_code=m_sn_methods.met_formdata(reqstr,"TXT_GAURANTOR_CODE"+i);
					if(!m_gurantor_code.equals("")){
						callstmt1.setString(1,m_facility_code);
						callstmt1.setString(2,m_gurantor_code);
						callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
						callstmt1.setString(4,m_username);
						callstmt1.setInt(5,m_tnum);
						callstmt1.execute();
						m_tnum++;
					}
				}
				callstmt1.close();
			}
		
			//-------------------------------------------------------------------------------------------
			callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_SAVE_QUOT_ALLO_FACILITY(:1,:2,:3,:4); END;");
			callstmt1.setString(1,m_facility_code);
			callstmt1.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_QUOTATION_NO"));
			callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt1.setString(4,m_username);
			callstmt1.execute();
			callstmt1.close();
			//---------------------------------------------------------------------------------------------
			conn.commit();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+" "+m_facility_code+"');");
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_CR_display_client_facility';");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();

		}
		catch (Exception ex) {
			try{conn.rollback();}catch(Exception e){}
			out.println("Error:"+ex.toString());
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
			try{conn.setAutoCommit(true);}catch(Exception e){}
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}

	}
}

