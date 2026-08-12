// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
     
public class LAKDL_FA_OP_Bulk_Print_reminder_print extends HttpServlet {
  
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt1;
	Statement stmt;
  public ResultSet rs;
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
			
			stmt=conn.createStatement();
			
			m_msg = "'Invoice Reminder Print Completed'";

			String m_print_type=(String)m_sn_methods.met_formdata(reqstr,"TXT_LETTER_TYPE");
			String m_print_bulk_type=(String)m_sn_methods.met_formdata(reqstr,"hid_print_status");
			String m_print_date=(String)m_sn_methods.met_formdata(reqstr,"TXT_TO_DATE_DD")+"-"+
													(String)m_sn_methods.met_formdata(reqstr,"TXT_TO_DATE_MM")+"-"+
													(String)m_sn_methods.met_formdata(reqstr,"TXT_TO_DATE_YY");
													
			if(m_print_bulk_type.equals("NORMAL")){
			
				if(m_print_type.equals("PR")){
					Bulk_Printing_Main m_bulk_print=new Bulk_Printing_Main();
					
					rs=stmt.executeQuery ("SELECT CLIENT_CODE,FACILITY_NO,DEBTOR_CODE "+
					" FROM "+m_schema_name+".FA_OP_PRO_INVOICE_LETTER "+
					" WHERE LETTER_NAME='PRE_INVOICE_REMINDER' "+
					" AND PRINT_STATUS='N' "+
					" GROUP BY CLIENT_CODE,FACILITY_NO,DEBTOR_CODE");
					
					while(rs.next()){
						int m_num=m_bulk_print.printing_interface_5(rs.getString(2),rs.getString(1),rs.getString(3),m_print_date,"N");
						//out.println("m_num="+m_num);
						System.gc();
					}
				}
				else if(m_print_type.equals("1R")){
					Bulk_Printing_Main m_bulk_print=new Bulk_Printing_Main();
					
					rs=stmt.executeQuery ("SELECT CLIENT_CODE,FACILITY_NO,DEBTOR_CODE,INVOICE_SEQ_NO "+
					" FROM "+m_schema_name+".FA_OP_PRO_INVOICE_LETTER "+
					" WHERE LETTER_NAME='FIRST_INVOICE_REMINDER' "+
					" AND PRINT_STATUS='N' "+
					" ORDER BY CLIENT_CODE,FACILITY_NO,DEBTOR_CODE,INVOICE_SEQ_NO");
					
					while(rs.next()){
						int m_num=m_bulk_print.printing_interface_6(rs.getString(2),rs.getString(1),rs.getString(3),rs.getString(4));
						System.gc();
					}
				}
				else if(m_print_type.equals("2R")){
					Bulk_Printing_Main m_bulk_print=new Bulk_Printing_Main();
					
					rs=stmt.executeQuery ("SELECT CLIENT_CODE,FACILITY_NO,DEBTOR_CODE,INVOICE_SEQ_NO "+
					" FROM "+m_schema_name+".FA_OP_PRO_INVOICE_LETTER "+
					" WHERE LETTER_NAME='SECOND_INVOICE_REMINDER' "+
					" AND PRINT_STATUS='N' "+
					" GROUP BY CLIENT_CODE,FACILITY_NO,DEBTOR_CODE,INVOICE_SEQ_NO");
					
					while(rs.next()){
						int m_num=m_bulk_print.printing_interface_7(rs.getString(2),rs.getString(1),rs.getString(3),rs.getString(4));
						System.gc();
					}
				}
				else if(m_print_type.equals("FR")){
					/*Bulk_Printing_Main m_bulk_print=new Bulk_Printing_Main();
					
					rs=stmt.executeQuery ("SELECT CLIENT_CODE,FACILITY_NO,DEBTOR_CODE,INVOICE_SEQ_NO "+
					" FROM "+m_schema_name+".FA_OP_PRO_INVOICE_LETTER "+
					" WHERE LETTER_NAME='FINAL_INVOICE_REMINDER' "+
					" AND PRINT_STATUS='N' "+
					" GROUP BY CLIENT_CODE,FACILITY_NO,DEBTOR_CODE,INVOICE_SEQ_NO");
					
					while(rs.next()){
						int m_num=m_bulk_print.printing_interface_6(rs.getString(2),rs.getString(1),rs.getString(3));
						System.gc();
					}*/
				}
			}
			else{
					
					if(m_print_type.equals("PR")){
					Bulk_Printing_Main m_bulk_print=new Bulk_Printing_Main();
					
					rs=stmt.executeQuery ("SELECT A.CLIENT_CODE,A.FACILITY_NO,A.DEBTOR_CODE "+
					" FROM "+m_schema_name+".FA_OP_PRO_INVOICE_LETTER A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
					" WHERE A.LETTER_NAME='PRE_INVOICE_REMINDER' "+
					" AND B.INVOICE_SEQ_NO=A.INVOICE_SEQ_NO "+
					" AND TO_DATE(TO_CHAR(A.LETTER_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')=TO_DATE('"+m_print_date+"','DD-MM-YYYY') "+
					" AND B.BALANCE_AMOUNT>0 "+
					" GROUP BY A.CLIENT_CODE,A.FACILITY_NO,A.DEBTOR_CODE");
					//out.println("m_num=*********************************************");
					while(rs.next()){
						int m_num=m_bulk_print.printing_interface_5(rs.getString(2),rs.getString(1),rs.getString(3),m_print_date,"Y");
						//out.println("m_num="+m_num);
						System.gc();
					}
				}
				else if(m_print_type.equals("1R")){
					Bulk_Printing_Main m_bulk_print=new Bulk_Printing_Main();
					
					rs=stmt.executeQuery ("SELECT A.CLIENT_CODE,A.FACILITY_NO,A.DEBTOR_CODE,B.INVOICE_SEQ_NO "+
					" FROM "+m_schema_name+".FA_OP_PRO_INVOICE_LETTER A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
					" WHERE A.LETTER_NAME='FIRST_INVOICE_REMINDER' "+
					" AND B.INVOICE_SEQ_NO=A.INVOICE_SEQ_NO "+
					" AND TO_DATE(TO_CHAR(A.LETTER_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')=TO_DATE('"+m_print_date+"','DD-MM-YYYY') "+
					" AND B.BALANCE_AMOUNT>0 "+
					" ORDER BY A.CLIENT_CODE,A.FACILITY_NO,A.DEBTOR_CODE");
					
					while(rs.next()){
						int m_num=m_bulk_print.printing_interface_6(rs.getString(2),rs.getString(1),rs.getString(3),rs.getString(4));
						System.gc();
					}
				}
				else if(m_print_type.equals("2R")){
					Bulk_Printing_Main m_bulk_print=new Bulk_Printing_Main();
					
					rs=stmt.executeQuery("SELECT A.CLIENT_CODE,A.FACILITY_NO,A.DEBTOR_CODE,B.INVOICE_SEQ_NO "+
					" FROM "+m_schema_name+".FA_OP_PRO_INVOICE_LETTER A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
					" WHERE A.LETTER_NAME='SECOND_INVOICE_REMINDER' "+
					" AND B.INVOICE_SEQ_NO=A.INVOICE_SEQ_NO "+
					" AND TO_DATE(TO_CHAR(A.LETTER_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')=TO_DATE('"+m_print_date+"','DD-MM-YYYY') "+
					" AND B.BALANCE_AMOUNT>0 "+
					" GROUP BY A.CLIENT_CODE,A.FACILITY_NO,A.DEBTOR_CODE");
	
					while(rs.next()){
						int m_num=m_bulk_print.printing_interface_7(rs.getString(2),rs.getString(1),rs.getString(3),rs.getString(4));
						System.gc();
					}
				}
				else if(m_print_type.equals("FR")){
					/*Bulk_Printing_Main m_bulk_print=new Bulk_Printing_Main();
					
					rs=stmt.executeQuery ("SELECT CLIENT_CODE,FACILITY_NO,DEBTOR_CODE,INVOICE_SEQ_NO "+
					" FROM "+m_schema_name+".FA_OP_PRO_INVOICE_LETTER "+
					" WHERE LETTER_NAME='FINAL_INVOICE_REMINDER' "+
					" AND TO_DATE(TO_CHAR(LETTER_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')=TO_DATE('"+m_print_date+"','DD-MM-YYYY') "+
					" GROUP BY CLIENT_CODE,FACILITY_NO,DEBTOR_CODE,INVOICE_SEQ_NO");
					
					while(rs.next()){
						int m_num=m_bulk_print.printing_interface_6(rs.getString(2),rs.getString(1),rs.getString(3));
						System.gc();
					}*/
				}
			}
			
			callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_OP_SAVE_INV_LETTER_BULK(:1,:2,:3,:4); END;");
			callstmt1.setString(1,m_print_type);
			callstmt1.setString(2,m_username);
			callstmt1.setString(3,m_print_date);
			callstmt1.setString(4,m_print_bulk_type);
			callstmt1.execute();
			callstmt1.close();
			conn.commit();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_invoice_reminder';");
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

