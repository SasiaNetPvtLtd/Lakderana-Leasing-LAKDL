//--
//SCREEN NAME:SAVE POD CHEQUES
//CREATED BY:Delanjali
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_RE_PRO_save_pod_cheques extends HttpServlet {
		
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
			//out.println(reqstr);
			//**************************************************************		
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_fschema_name=m_sn_methods.client_name.trim();

			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url;
			String m_class_url;
			String m_class_name_save;
			String m_save_procedure_name;
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();

      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			String m_screen_name =(String)m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");		
      String m_client_code =(String)m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE");		
			String m_finance_no =	(String)m_sn_methods.met_formdata(reqstr,"TXT_FINANCE_NO");		
			
			//out.println(reqstr);
								
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			int m_chksql    = Integer.parseInt(req.getParameter("number"));
			//out.println("chksql - "+m_chksql);
			String m_pod_ref="";
			String m_chq  ="";
			String m_check_req="YES";
			String m_pod_batch_no="";
			
			if(m_sn_methods.met_formdata(reqstr,"SCREEN_NAME").equals("NEW")){
			int m_fin_count = Integer.parseInt(req.getParameter("fin_count"));//Added By Sandun on 5-2-2009
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_PRO_SAVE_POD_CHEQUES(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23); END;");
			callstmt.registerOutParameter(23,java.sql.Types.CHAR);
			int count_check=1;
			 for (int k=0; k<=m_chksql; k++)  
				{
			 m_chq=m_sn_methods.met_formdata(reqstr,"TXT_CHEQUE_NO_"+k+"");
			//callstmt.registerOutParameter(1,java.sql.Types.CHAR);
			count_check=1; 
			for (int h=0; h<m_fin_count; h++)
			{
		  m_check_req = (String)m_sn_methods.met_formdata(reqstr,"CHK_"+k+""+h+"");
		 
			if(m_check_req.equals("YES")){	
			
  		if(count_check==1){
			callstmt.registerOutParameter(1,java.sql.Types.CHAR);
			}
			else
			{
			callstmt.setString(1,m_pod_ref);
			}
			
			
			
			callstmt.registerOutParameter(1,java.sql.Types.CHAR);
			callstmt.setString(2,m_chq);
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_CHEQUE_DATE_"+k+""));
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE"));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"hid_br_code_"+k+""));  //TXT_BRANCH_  //hid_br_code_ Added by chandana on 18/06/2007
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_ACC_NO_"+k+""));
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_CURR_CODE_"+k+""));
			callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(9,m_username);
			callstmt.setString(10,Integer.toString(k));
			//callstmt.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_CHEQUE_AMOUNT_"+k+""));
			callstmt.setString(11,m_sn_methods.met_formdata(reqstr,"allo_amount_"+k+""+h+""));
			callstmt.setString(12,"-");
			callstmt.setString(13,"CHEQUE");
			//callstmt.setString(14,"-");
			callstmt.setString(14,m_sn_methods.met_formdata(reqstr,"TXT_TYPE"));//Mod By sandun on 27-07-2009
			callstmt.setInt(15,0);
			callstmt.setString(16,"-");
			//callstmt.setString(17,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_CHEQUE_AMOUNT_"+k+"")));  //Mod By Sandun On 05-02-2009
			callstmt.setString(17,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"allo_amount_"+k+""+h+"")));  
			//callstmt.setString(18,"1");
			callstmt.setInt(18,count_check);
			callstmt.setString(19,"");
			callstmt.setString(20,m_sn_methods.met_formdata(reqstr,"HID_TXT_FINANCE_NO_"+k+""+h+""));
			callstmt.setString(21,m_sn_methods.format_text_area_string(m_sn_methods.met_formdata(reqstr,"TXT_REMARKS")).trim());
      callstmt.setString(22,Integer.toString(h));		
						
			
			callstmt.setString(23,m_pod_batch_no);
			
			
			if(!m_chq.equals("")){//1
			callstmt.execute();
			}//1
			//m_pod_ref="";
				
			if(count_check==1){
			m_pod_ref=callstmt.getString(1);
			}
			count_check=2;
			
			
			
			if(k==0 && h==0){
			m_pod_batch_no=callstmt.getString(23);
			
			}
			
		
		  }//
			
			
			
			}
			}
			}
			
			else if(m_sn_methods.met_formdata(reqstr,"SCREEN_NAME").equals("EDIT")){
				
			
			/*for (int k=0; k<=m_chksql; k++)  
						{
		  m_status_edit=m_sn_methods.met_formdata(reqstr,"CHK_EDIT_"+k+"");	
				
			if(m_status_edit.equals("on")){					
	    String m_date=m_sn_methods.met_formdata(reqstr,"TXT_CH_DATE_DD"+k+"")+"-"+m_sn_methods.met_formdata(reqstr,"TXT_CH_DATE_MM"+k+"")+"-"+m_sn_methods.met_formdata(reqstr,"TXT_CH_DATE_YY"+k+"");
	   
			
			
			m_chq=m_sn_methods.met_formdata(reqstr,"TXT_CH_NO_"+k+"");
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_PRO_SAVE_POD_CHEQUES(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21); END;");
			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_PD_NO_"+k+""));
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_CH_NO_"+k+""));
			callstmt.setString(3,m_date);
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE"));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"hid_br_code_"+k+""));  //  TXT_BR_CODE_
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_AC_NO_"+k+""));
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_CUR_"+k+""));
			callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(9,m_username);
			callstmt.setString(10,Integer.toString(k));
			callstmt.setString(11,"");
			callstmt.setString(12,"-");
			callstmt.setString(13,"CHEQUE");
			callstmt.setString(14,"-");
			callstmt.setInt(15,0);
			callstmt.setString(16,"-");
			callstmt.setString(17,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_CH_AMOUNT_"+k+"")));
			callstmt.setString(18,"2");
			callstmt.setString(19,"");
		//callstmt.setString(20,m_sn_methods.met_formdata(reqstr,"TXT_FINANCE_NO"));
		  callstmt.setString(20,m_sn_methods.met_formdata(reqstr,"TXT_FIN_NO_"+k+""));
			callstmt.setString(21,m_sn_methods.format_text_area_string(m_sn_methods.met_formdata(reqstr,"TXT_REMARKS")).trim());
			
			
			if(!m_chq.equals("")){
			callstmt.execute();
			}  
			*/
			//--------------------------Mod by Sandun on 05-03-2009--------------------------------
			
			String m_status_edit ="off";
			int con_count = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"EDIT_CON"));
			//out.println(con_count);
			/*callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_PRO_SAVE_POD_CHEQUES_1(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12); END;"); 
			
			for (int k=0; k<=m_chksql; k++)  
						{
					
		  m_status_edit=m_sn_methods.met_formdata(reqstr,"CHK_EDIT_"+k+"");	
				
			if(m_status_edit.equals("on")){	
			
	    String m_date=m_sn_methods.met_formdata(reqstr,"TXT_CH_DATE_DD"+k+"")+"-"+m_sn_methods.met_formdata(reqstr,"TXT_CH_DATE_MM"+k+"")+"-"+m_sn_methods.met_formdata(reqstr,"TXT_CH_DATE_YY"+k+"");
	   				
			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_PD_NO_"+k+""));
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_CH_NO_"+k+""));
			callstmt.setString(3,m_date);
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE"));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"hid_br_code_"+k+"")); 
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_AC_NO_"+k+""));
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_CUR_"+k+""));
			callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(9,m_username);
			//callstmt.setString(10,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_CH_AMOUNT_"+k+"")));
			callstmt.setString(10,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"CHQ_AMT_"+k+"")));
			callstmt.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_FIN_NO_"+k+""));
			callstmt.setString(12,m_sn_methods.format_text_area_string(m_sn_methods.met_formdata(reqstr,"TXT_REMARKS")).trim());	
			*/
			
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_PRO_SAVE_POD_CHEQUES_1(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12); END;"); 
			
			for (int k=0; k<=m_chksql; k++)  
						{
					
		  m_status_edit=m_sn_methods.met_formdata(reqstr,"CHK_EDIT_"+k+"");	
				
			if(m_status_edit.equals("on")){	
			for(int p=0; p<con_count; p++){
			
	    String m_date=m_sn_methods.met_formdata(reqstr,"TXT_CH_DATE_DD"+k+"")+"-"+m_sn_methods.met_formdata(reqstr,"TXT_CH_DATE_MM"+k+"")+"-"+m_sn_methods.met_formdata(reqstr,"TXT_CH_DATE_YY"+k+"");
	   				
			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_PD_NO_"+k+""));
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_CH_NO_"+k+""));
			callstmt.setString(3,m_date);
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE"));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"hid_br_code_"+k+"")); 
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_AC_NO_"+k+""));
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_CUR_"+k+""));
			callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(9,m_username);
			callstmt.setString(10,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"CHQ_AMT_"+p+"")));
			callstmt.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_FIN_NO_"+p+""));
			callstmt.setString(12,m_sn_methods.format_text_area_string(m_sn_methods.met_formdata(reqstr,"TXT_REMARKS")).trim());
			
			
			
			
			
			callstmt.execute();
			}
			  }
			 }
			}
			
			
			else if(m_sn_methods.met_formdata(reqstr,"SCREEN_NAME").equals("WITHDRAW")){
			//int count  = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_no_val"));
			for (int k=0; k<=m_chksql; k++)  
						{
			m_chq=m_sn_methods.met_formdata(reqstr,"TXT_CH_NO_"+k+"");
	    String m_date=m_sn_methods.met_formdata(reqstr,"TXT_CH_DATE_DD"+k+"")+"-"+m_sn_methods.met_formdata(reqstr,"TXT_CH_DATE_MM"+k+"")+"-"+m_sn_methods.met_formdata(reqstr,"TXT_CH_DATE_YY"+k+"");
	
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_PRO_SAVE_POD_CHEQUES(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21); END;");
			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_PD_NO_"+k+""));
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_CH_NO_"+k+""));
			callstmt.setString(3,m_date);
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE"));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_BR_CODE_"+k+""));
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_AC_NO_"+k+""));
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_CUR_"+k+""));
			callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(9,m_username);
			callstmt.setString(10,Integer.toString(k));
			callstmt.setString(11,"");
			callstmt.setString(12,"-");
			callstmt.setString(13,"CHEQUE");
			callstmt.setString(14,"-");
			callstmt.setInt(15,0);
			callstmt.setString(16,"-");
			callstmt.setString(17,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_CH_AMOUNT_"+k+"")));
			callstmt.setString(18,"2");
			
			String m_status_withdraw=m_sn_methods.met_formdata(reqstr,"CHK_WITHDRAW_"+k+"");
//			out.println("val"+m_sn_methods.met_formdata(reqstr,"CHK_WITHDRAW_"+k+""));
			if(m_status_withdraw.equals("on")){
		//				out.println("val"+m_sn_methods.met_formdata(reqstr,"CHK_WITHDRAW_"+k+""));

			callstmt.setString(19,"Y");
			}
			else
			{
	//					out.println("val"+m_sn_methods.met_formdata(reqstr,"CHK_WITHDRAW_"+k+""));

			callstmt.setString(19,"N");
			}
			
			
		// callstmt.setString(20,m_sn_methods.met_formdata(reqstr,"TXT_FINANCE_NO"));
		callstmt.setString(20,m_sn_methods.met_formdata(reqstr,"TXT_FIN_NO_"+k+""));
		callstmt.setString(21,m_sn_methods.format_text_area_string(m_sn_methods.met_formdata(reqstr,"TXT_REMARKS")).trim());
			
			if(!m_chq.equals("")){
			callstmt.execute();
			}
			}
			}
			

			callstmt.close();
			conn.commit();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("m_scr_name='"+m_screen_name+"'");
			
			out.println("if(m_scr_name=='NEW' ){"); 
			
			out.println("popupwin = window.open('"+m_url+"/"+m_fschema_name+"AF_RE_Post_Dated_Acknowledgement?chksql=print_ack&client_code="+m_client_code+"&finance_no="+m_finance_no+"&ack=new','displayWindow1','left=110,top=110,width=650,height=400,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');	");				
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_RE_PRO_display_pod_cheques?chksql=main_page';");
			
			out.println("}");
			
			out.println("else if(m_scr_name=='WITHDRAW'){");
			
			out.println("popupwin = window.open('"+m_url+"/"+m_fschema_name+"AF_RE_Post_Dated_Acknowledgement?chksql=print_ack&client_code="+m_client_code+"&finance_no="+m_finance_no+"&ack=withdraw','displayWindow1','left=110,top=110,width=650,height=400,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');	");							
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_RE_PRO_display_pod_cheques?chksql=main_page';");
		  out.println("}");
			
			out.println("else");
			out.println("{");
			//out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_RE_PRO_display_pod_cheques?chksql=main_page';");
			out.println("popupwin = window.open('"+m_url+"/"+m_fschema_name+"AF_RE_Post_Dated_Acknowledgement?chksql=print_ack&client_code="+m_client_code+"&finance_no="+m_finance_no+"&ack=new','displayWindow1','left=110,top=110,width=650,height=400,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');	");				
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_RE_PRO_display_pod_cheques?chksql=main_page';");

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
		


