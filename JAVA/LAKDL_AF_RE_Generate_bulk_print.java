
//--
//ID :1.12 Bank Creation Process
//SCREEN NAME:SYSTEM ADMINISTRATION - BANK
//CREATED BY:M.M. WICKRAMASEKARA
//DATE/TIME:2006.07.20
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_RE_Generate_bulk_print extends HttpServlet {
	
	Connection conn;	
	String m_msg,m_url;
	ResultSet rs,rs2,rs1;
	Statement stmt,stmt2,stmt1;
	CallableStatement callstmt;
	String reqstr;
	ServletOutputStream out = null;
	public synchronized void  service(HttpServletRequest req, HttpServletResponse res)	throws IOException
	{
		
		
		try 
		{
			
			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
			out = res.getOutputStream();
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			//************************************************************	
			conn =m_sn_methods.met_user_validate(req);
			conn.setAutoCommit(false);
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
			
			String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			
			
			
			
		
			
			
			
			
			String basic_upload_path="D:\\SasiaNet_Products\\NetAsset\\OFSCL\\UPLOAD";
			String file_path="";
			
			
			
			stmt=conn.createStatement();
			String sysdate="";
			rs=stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL");	
			if(rs.next())
			{
				sysdate=rs.getString(1);
			}			
			
			
			//String file_name=m_payement_no+"_"+sysdate+".pdf"; //generate PDF
			//file_path=basic_upload_path;
			//file_path=file_path+"\\LAKDL_LETTERES\\"+Letter_category+"\\"+sysdate+"\\"+file_name;
			
			
			
			
			
			int num_records=Integer.parseInt(m_sn_methods.met_formdata(reqstr,"NUM_CHKS").trim());
			String letter_category=m_sn_methods.met_formdata(reqstr,"TXT_LETETR_CATEGORY").trim();
			//out.println("letter_category"+letter_category);
			
			LAKDL_AF_Re_generater_letters obj=new LAKDL_AF_Re_generater_letters(conn,m_sn_methods);
			//not letter generation part
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_BULK_LETTERS(:1,:2,:3,:4,:5,:6,:7,:8); END;");
			if(letter_category.equals("NOT"))
			{	
				for(int i=1;i<=num_records;i++)
				{
					String finance_no=m_sn_methods.met_formdata(reqstr,"TXT_FINANCE_NO_"+i).trim();
					String txt_lease_type=m_sn_methods.met_formdata(reqstr,"TXT_LEASE_TYPE_"+i).trim(); 
					String txt_client_code=m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE_"+i).trim(); 
					String txt_facility_no=m_sn_methods.met_formdata(reqstr,"TXT_FACILITY_NO_"+i).trim(); 
					String txt_print_true=m_sn_methods.met_formdata(reqstr,"TXT_APPROVE_TYPE_"+i).trim();
					if(txt_print_true.equals("Y"))
					{
						
						obj.generate_letter_Pdf_version(finance_no,letter_category,txt_lease_type,txt_client_code); //String m_payement_no,String Letter_category,String Lease_type,String Client_code)
						String file_name=finance_no+"_"+sysdate+".pdf"; 
						file_path=basic_upload_path;
						file_path=file_path+"\\LAKDL_LETTERES\\"+letter_category+"\\"+sysdate+"\\"+file_name;
						
						callstmt.setString(1,(finance_no));
						callstmt.registerOutParameter(2,java.sql.Types.CHAR);
						callstmt.setString(3,file_path);
						callstmt.setString(4,letter_category);
						callstmt.setString(5,txt_lease_type);
						callstmt.setString(6,"NEW");
						callstmt.setString(7,m_username);
						callstmt.setString(8,txt_client_code);
						callstmt.execute();
					}
					
				}	
			}
			callstmt.close();
			
			//lot letter generation part
			if(letter_category.equals("LOT"))
			{	
			}
			//end lot generation part
			
			//other type
			///auto matically generating all the letter types under any letter category selected
			//    Run this section  for all letters 'NOT','LOT','LOT_1ST_RE','LOT_2ND_RE','LOT_3RD_RE','NOT_1ST_RE','NOT_2ND_RE','NOT_3RD_RE'
			//    check all the letter categories sysdate and the letter to be generated date if same or sysdate higher the all the leeters in that application no must be gbnerated and printed
			//end auto matically generating all the letter types under any letter category selected
			
			
			conn.commit();
			
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_class_url+"/"+m_client_name+"AF_RE_Bulk_Letter_print?chksql=main_page';");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			
			out.flush();
			
		}
		catch (Exception ex) 
		{
			try{conn.rollback();}catch(Exception e){}
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
		finally
		{
			try{conn.setAutoCommit(true);}catch(Exception e){}
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}
		
	}
}
