//--
//SCREEN NAME:
//CREATED BY: SANDUN 
//DATE/TIME:02/10/2008 
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MISF_Save_Asset_Insurance_Detail_sec extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt,callstmt1,callstmt2; // mod by udara 17-02-2014
  	String reqstr;
	String m_client_name;
	ServletOutputStream out = null;


	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
	synchronized(this){ 

		try {

			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
			out = res.getOutputStream();
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			//************************************************************	
			conn =m_sn_methods.met_user_validate(req);
			conn.setAutoCommit(false); 

			//**************************************************************		
			String m_schema_name = m_sn_methods.schema_name.trim();
			m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url;
			String m_class_url;
			String m_screen_name="";
			
			
      m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			//out.println("conn"+conn);
			//out.println(reqstr);
			
      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
	  String m_hid_get_flag_status=(String)m_sn_methods.met_formdata(reqstr,"hid_get_flag_status"); // added by udara 20-07-2018
			
			m_msg = "'Information saved successfully.'";
			m_url = m_class_url;				
			
			String m_start_date=(String)m_sn_methods.met_formdata(reqstr,"start_dd")+"-"+
													(String)m_sn_methods.met_formdata(reqstr,"start_mm")+"-"+
													(String)m_sn_methods.met_formdata(reqstr,"start_yy");
			
			String m_end_date= (String)m_sn_methods.met_formdata(reqstr,"end_dd")+"-"+
													(String)m_sn_methods.met_formdata(reqstr,"end_mm")+"-"+
													(String)m_sn_methods.met_formdata(reqstr,"end_yy");
													
			
			
			/*if(m_hid_get_flag_status.equals("Y")){
				
				    // start temp level
					callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_SAVE_INIT_INSURANCE_SEC(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27); END;");
					
					callstmt.setString(1,(String)m_sn_methods.met_formdata(reqstr,"hid_finance_no"));
					callstmt.setString(2,(String)m_sn_methods.met_formdata(reqstr,"txt_policy_no"));
					callstmt.setString(3,m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"txt_sum_in")));
					callstmt.setString(4,m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"txt_premium")));
					callstmt.setString(5,m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"txt_rcc"))); //added by prabash 23/05/2011
					callstmt.setString(6,m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"txt_tc"))); //added by prabash 23/05/2011
					callstmt.setString(7,m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"txt_rcc_tc"))); //added by prabash 23/05/2011
			        callstmt.setString(8,m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"txt_bas_pre"))); //added by prabash 23/05/2011
					callstmt.setString(9,m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"txt_comm_rate"))); //added by prabash 23/05/2011
					callstmt.setString(10,m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"txt_bas_com"))); //added by prabash 23/05/2011
					callstmt.setString(11,m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"txt_rcc_comm_rate"))); //added by prabash 23/05/2011
					callstmt.setString(12,m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"txt_rcc_comm"))); //added by prabash 23/05/2011
					callstmt.setString(13,m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"txt_tax_total"))); //added by prabash 02/06/2011
					callstmt.setString(14,m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"txt_vat_total_com"))); //added by prabash 23/05/2011
					callstmt.setString(15,(String)m_sn_methods.met_formdata(reqstr,"'Insured By'"));
					callstmt.setString(16,(String)m_sn_methods.met_formdata(reqstr,"txt_in_company_code"));
					callstmt.setString(17,(String)m_sn_methods.met_formdata(reqstr,"hid_asset_deta"));			
					callstmt.setString(18,(String)m_sn_methods.met_formdata(reqstr,"hid_invo_no"));
					callstmt.setString(19,m_start_date);			
					callstmt.setString(20,m_end_date);
					callstmt.setString(21,m_username);
					callstmt.setString(22,(String)m_sn_methods.met_formdata(reqstr,"TXT_REMARK"));//Added By Sandun on 09-01-2009
					callstmt.setString(23,(String)m_sn_methods.met_formdata(reqstr,"txt_Debit_Note_no"));
					callstmt.setString(24,(String)m_sn_methods.met_formdata(reqstr,"txt_tax"));
					callstmt.setString(25,(String)m_sn_methods.met_formdata(reqstr,"hid_business_type"));
					callstmt.setString(26,(String)m_sn_methods.met_formdata(reqstr,"TXT_ITEM_SUB_CAT")); // added by udara 13-03-2014
					callstmt.setString(27,(String)m_sn_methods.met_formdata(reqstr,"txt_payable_premium")); // added by udara 06-05-2014
					callstmt.execute();	*/
					
					// end temp level
				
			/*}
			else{*/
				
				   
					
					
					// start general save
			
					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".WD_save_aset_tax_deta(?,?,?,?);END;");
						int i1 = Integer.parseInt(m_sn_methods.met_formdata(reqstr, "hid_tax_num"));
						for (int j = 1; j <i1; j++) {
							callstmt1.setString(1, (String)m_sn_methods.met_formdata(reqstr,"txt_policy_no"));
							callstmt1.setString(2,m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr, "txt_tax_code"+(Integer.toString(j))).trim()));
							callstmt1.setString(3,m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"txt_tax_mut"+(Integer.toString(j))).trim()));	
							callstmt1.setString(4,(String)m_sn_methods.met_formdata(reqstr,"txt_Debit_Note_no"));
		
							callstmt1.execute();
						}
					
		     		//callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_IS_PRO_SAVE_ASET_INSUR_DETA(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25); END;");
					//callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_IS_PRO_SAVE_ASET_INSUR_DETA(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26); END;");
					callstmt=conn.prepareCall("BEGIN "+m_schema_name+".af_is_pro_save_aset_insur_sec(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28); END;");
					
					callstmt.setString(1,(String)m_sn_methods.met_formdata(reqstr,"hid_finance_no"));
					callstmt.setString(2,(String)m_sn_methods.met_formdata(reqstr,"txt_policy_no"));
					callstmt.setString(3,m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"txt_sum_in")));
					callstmt.setString(4,m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"txt_premium")));
					callstmt.setString(5,m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"txt_rcc"))); //added by prabash 23/05/2011
					callstmt.setString(6,m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"txt_tc"))); //added by prabash 23/05/2011
					callstmt.setString(7,m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"txt_rcc_tc"))); //added by prabash 23/05/2011
			        callstmt.setString(8,m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"txt_bas_pre"))); //added by prabash 23/05/2011
					callstmt.setString(9,m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"txt_comm_rate"))); //added by prabash 23/05/2011
					callstmt.setString(10,m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"txt_bas_com"))); //added by prabash 23/05/2011
					callstmt.setString(11,m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"txt_rcc_comm_rate"))); //added by prabash 23/05/2011
					callstmt.setString(12,m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"txt_rcc_comm"))); //added by prabash 23/05/2011
					callstmt.setString(13,m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"txt_tax_total"))); //added by prabash 02/06/2011
					callstmt.setString(14,m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"txt_vat_total_com"))); //added by prabash 23/05/2011
					callstmt.setString(15,(String)m_sn_methods.met_formdata(reqstr,"'Insured By'"));
					callstmt.setString(16,(String)m_sn_methods.met_formdata(reqstr,"txt_in_company_code"));
					callstmt.setString(17,(String)m_sn_methods.met_formdata(reqstr,"hid_asset_deta"));			
					callstmt.setString(18,(String)m_sn_methods.met_formdata(reqstr,"hid_invo_no"));
					callstmt.setString(19,m_start_date);			
					callstmt.setString(20,m_end_date);
					callstmt.setString(21,m_username);
					callstmt.setString(22,(String)m_sn_methods.met_formdata(reqstr,"TXT_REMARK"));//Added By Sandun on 09-01-2009
					callstmt.setString(23,(String)m_sn_methods.met_formdata(reqstr,"txt_Debit_Note_no"));
					callstmt.setString(24,(String)m_sn_methods.met_formdata(reqstr,"txt_tax"));
					callstmt.setString(25,(String)m_sn_methods.met_formdata(reqstr,"hid_business_type"));
					callstmt.setString(26,(String)m_sn_methods.met_formdata(reqstr,"TXT_ITEM_SUB_CAT")); // added by udara 13-03-2014
					callstmt.setString(27,(String)m_sn_methods.met_formdata(reqstr,"txt_payable_premium")); // added by udara 06-05-2014
					callstmt.setString(28,(String)m_sn_methods.met_formdata(reqstr,"hid_ins_done_by"));
					callstmt.execute();	
					
					// end general save
					
					
					
				
			//}

			

			
			conn.commit(); 
			conn.close();
				
		
			

	   	    out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg(),window.close()'></body>");
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
