


import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MISF_save_inspection_report_details extends HttpServlet {
	
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
			//**************************************************************		
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
			String m_username = m_sn_methods.username;
			String m_html_client_url;
			String m_class_url;
			String m_class_name_save;
			String m_save_procedure_name;
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			
			
			String m_finance_no=req.getParameter("FIN_NO").trim();
			
			String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			
			String m_noise=(String)m_sn_methods.met_formdata(reqstr,"Noises_ok"); 
			
			conn.setAutoCommit(false); // added by udara 28-3-2016
			
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			
			// added by udara 29-08-2014
			String m_doc_1=req.getParameter("doc_1").trim();
			String m_doc_2=req.getParameter("doc_2").trim();
			String m_doc_3=req.getParameter("doc_3").trim();
			
			//callstmt2=conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_VEHICLE_INSPEC_DOCS(:1,:2,:3,:4,:5); END;");
			callstmt2=conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_VEHICLE_INSPEC_GEN(:1,:2,:3,:4,:5); END;"); // mod by udara 04-09-2014
			callstmt2.setString(1,m_finance_no);
			callstmt2.setString(2,m_doc_1);
			callstmt2.setString(3,m_doc_2);
			callstmt2.setString(4,m_doc_3);
			callstmt2.setString(5,m_username);
			callstmt2.execute();
			callstmt2.close();
			
			// end by udara 29-08-2014
			
			
			for(int i=1;i<=32;i++){
			
				
				//out.println("aaaaaaaaaa="+m_sn_methods.met_formdata(reqstr,"need_att_"+i));
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_VEL_INSP_DET_RPT_R(:1,:2,:3,:4,:5); END;");
			
			    callstmt.setString(1,m_finance_no);
				callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"hidd_name_"+i));
				callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"check_ok_"+i));
				callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"need_att_"+i));
				
				callstmt.setString(5,m_username);
				callstmt.execute();
				
				
				}
			
			
				callstmt.close();
				
				
				
				callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_VEL_INSP_DET_OTHER(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26); END;");
			
				
				    String start_DD= m_sn_methods.met_formdata(reqstr,"start_DD");
					String start_MM=m_sn_methods.met_formdata(reqstr,"start_MM");
					String start_YY=m_sn_methods.met_formdata(reqstr,"start_YY");
					
					String  text_dd=m_sn_methods.met_formdata(reqstr,"text_dd");
					String	text_mm=m_sn_methods.met_formdata(reqstr,"text_mm");
					String	text_yy=m_sn_methods.met_formdata(reqstr,"text_yy");
					
					String Last_Serviced_date =start_DD+"-"+start_MM+"-"+start_YY;
					String Date_con=text_dd+"-"+text_mm+"-"+text_yy;
					
					
					if(Last_Serviced_date.equals("--")){
					Last_Serviced_date="";
					}
					if(Date_con.equals("--")){
					Date_con="";
					}
					
					String	text_comment=m_sn_methods.met_formdata(reqstr,"text_comment");
					String	text_comment1=m_sn_methods.met_formdata(reqstr,"text_comment1");
					String comment =text_comment+ " "+ text_comment1;
					 
					//out.println("RevenueLi_ok="+m_sn_methods.met_formdata(reqstr,"ckeck_inspec_1"));
				/*out.println("m_finance_no="+m_finance_no);
		       out.println("RevenueLi_ok="+m_sn_methods.met_formdata(reqstr,"ckeck_inspec_1"));
				out.println("RevenueLi_na="+m_sn_methods.met_formdata(reqstr,"RevenueLi_na"));
				out.println("Insurance_ok="+m_sn_methods.met_formdata(reqstr,"Insurance_ok"));
				out.println("Insurance_na="+m_sn_methods.met_formdata(reqstr,"Insurance_na"));
				out.println("engin_ori_pht="+m_sn_methods.met_formdata(reqstr,"engin_ori_pht"));
				out.println("chasi_ori_pht="+m_sn_methods.met_formdata(reqstr,"chasi_ori_pht"));
				out.println("body_paint_pht="+m_sn_methods.met_formdata(reqstr,"body_paint_pht"));
				out.println("hood_pht="+m_sn_methods.met_formdata(reqstr,"hood_pht"));
				out.println("reg_no_pht="+m_sn_methods.met_formdata(reqstr,"reg_no_pht"));
				out.println("company_name="+m_sn_methods.met_formdata(reqstr,"company_name"));
				out.println("body_kit="+m_sn_methods.met_formdata(reqstr,"body_kit"));
				out.println("Specify="+m_sn_methods.met_formdata(reqstr,"Specify"));
				out.println("Last_Serviced_date="+Last_Serviced_date);
				out.println("Remarks_1="+m_sn_methods.met_formdata(reqstr,"Remarks_1"));
				out.println("Remarks_2="+m_sn_methods.met_formdata(reqstr,"Remarks_2"));
				out.println("Date_con="+Date_con);
				out.println("text_1="+m_sn_methods.met_formdata(reqstr,"text_1"));
				out.println("text_2="+m_sn_methods.met_formdata(reqstr,"text_2"));
				out.println("text_3="+m_sn_methods.met_formdata(reqstr,"text_3"));
				out.println("m_username="+m_username);*/
					
					
					
					
					
				
			    callstmt1.setString(1,m_finance_no);
		        callstmt1.setString(2,m_sn_methods.met_formdata(reqstr,"RevenueLi_ok"));
				callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"RevenueLi_na"));
				callstmt1.setString(4,m_sn_methods.met_formdata(reqstr,"Insurance_ok"));
				callstmt1.setString(5,m_sn_methods.met_formdata(reqstr,"Insurance_na"));
				callstmt1.setString(6,m_sn_methods.met_formdata(reqstr,"engin_ori_pht"));
				callstmt1.setString(7,m_sn_methods.met_formdata(reqstr,"chasi_ori_pht"));
				callstmt1.setString(8,m_sn_methods.met_formdata(reqstr,"body_paint_pht"));
				callstmt1.setString(9,m_sn_methods.met_formdata(reqstr,"hood_pht"));
				callstmt1.setString(10,m_sn_methods.met_formdata(reqstr,"reg_no_pht"));
				callstmt1.setString(11,m_sn_methods.met_formdata(reqstr,"company_name"));
				callstmt1.setString(12,m_sn_methods.met_formdata(reqstr,"body_kit"));
				callstmt1.setString(13,m_sn_methods.met_formdata(reqstr,"Specify"));
				callstmt1.setString(14,Last_Serviced_date);
				callstmt1.setString(15,m_sn_methods.met_formdata(reqstr,"Remarks_1"));
				callstmt1.setString(16,m_sn_methods.met_formdata(reqstr,"Remarks_2"));
				callstmt1.setString(17,Date_con);
				callstmt1.setString(18,m_sn_methods.met_formdata(reqstr,"text_1"));
				callstmt1.setString(19,m_sn_methods.met_formdata(reqstr,"text_2"));
				callstmt1.setString(20,m_sn_methods.met_formdata(reqstr,"text_3"));
				callstmt1.setString(21,comment);
				callstmt1.setString(22,m_sn_methods.met_formdata(reqstr,"ckeck_inspec_1"));
				callstmt1.setString(23,m_sn_methods.met_formdata(reqstr,"ckeck_inspec_2"));
				callstmt1.setString(24,m_sn_methods.met_formdata(reqstr,"ckeck_inspec_3"));
				callstmt1.setString(25,m_sn_methods.met_formdata(reqstr,"ckeck_inspec_4"));
				
				callstmt1.setString(26,m_username);
				callstmt1.execute();
				
				callstmt1.close();
				
				out.println("<HTML><HEAD>");
				out.println("<SCRIPT language='JavaScript'>");
				out.println("function displaymsg() {");
				out.println("alert("+m_msg+");");
				//out.println("window.location.href='"+m_url+"/LAKDL_AF_MISF_vehicle_inspection_report_details';");
				out.println("window.location.href='"+m_url+"/LAKDL_AF_MISF_vehicle_inspection_report_details?chksql=main_page&print=TRUE&finance_no="+m_finance_no+"&doc_1="+m_doc_1+"&doc_2="+m_doc_2+"&doc_3="+m_doc_3+"&status=view_report';");
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_vehicle_inspection_report_details?chksql=main_page&print=TRUE&finance_no=\"+document.Form1.TXT_FINANCE_NO_1.value+\"&doc_1=\"+document.Form1.TXT_DOC_UPLOAD_1.value+\"&doc_2=\"+document.Form1.TXT_DOC_UPLOAD_2.value+\"&doc_3=\"+document.Form1.TXT_DOC_UPLOAD_3.value+\"&status=view_report\";");
				out.println("}</SCRIPT></HEAD>");
				out.println("<body onload='displaymsg();'></body>");
				out.println("</html>");
				
				out.flush();
				
			}
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
			
			// commented by udara 28-03-2016
			/*
			finally{
				if(conn!=null){try{conn.close(); }catch(Exception e){}}
				if(out!=null){try{out.close();  }catch(Exception e){}}
			}
			*/
			
			
			// added by udara 28-03-2016
			finally{

				try{conn.setAutoCommit(true); conn.commit(); }catch(Exception e){}
				if(conn!=null){try{conn.close(); }catch(Exception e){}}
				if(out!=null){try{out.close();  }catch(Exception e){}}
			
			}
			// end by udara 28-03-2016
			
			
		}
	}
