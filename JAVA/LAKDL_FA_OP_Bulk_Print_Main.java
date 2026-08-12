import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import sun.misc.BASE64Decoder; 
import java.lang.*;
import java.sql.*;


import oracle.jdbc.driver.*;



/* Created by Ashini on 06-03-2008 */

public class LAKDL_FA_OP_Bulk_Print_Main extends javax.servlet.http.HttpServlet {

  public String m_ref_no,m_chksql;
  public String m_from_date,m_to_date,m_print_type;
	public ResultSet rs;
	Connection conn;
  Statement stmt;
  String[]  m_rec_ref = new String[1000];
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse		res) throws IOException	{
		
		try {
	
		  //************************************************************	
			//CLAMF_sn_methods m_sn_methods = new CLAMF_sn_methods();
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			String m_schema_name = m_sn_methods.schema_name;
			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			String reqstr = input.readLine();
		  //************************************************************
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			ServletOutputStream out = res.getOutputStream();
			conn = m_sn_methods.met_user_validate(req); 
			
			//String m_chksql=(String)m_sn_methods.met_formdata(reqstr,"chksql");
			
			//String m_chksql="";
			m_chksql = req.getParameter("chksql"); 
			m_from_date = req.getParameter("from_date");
  		m_to_date = req.getParameter("to_date"); 
			m_print_type = req.getParameter("print_type");
			String m_ref_no="";
			
     	//out.println(reqstr);
			stmt = conn.createStatement();	
				
			if(m_chksql.trim().equals("RECEIPT")) {
			
				//m_ref_no = req.getParameter("ref_no");
				
				Bulk_Printing_Main m_Print_cheques = new Bulk_Printing_Main();
				
				//String m_chq_pay_no="";
				
				//int count =(Integer.parseInt((String)m_sn_methods.met_formdata(reqstr,"Hid_rows")));
				int count = 1;
				
				

				rs = stmt.executeQuery (" SELECT RECEIPT_NO "+
															  " FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
																" WHERE RECON_STATUS ='Y' "+
																" AND TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																" AND TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																" AND RECEIPT_NO NOT IN (SELECT RECEIPT_NO FROM "+m_schema_name+".FA_OP_PRO_PRINT_RECEIPT ) "+
																" ORDER BY ENT_DATE  "); 
														
														
				boolean	more = rs.next();
				int i=0;
				while(more){
				
				if(i<1){
				m_rec_ref[i]=rs.getString(1);
			//	out.println("Document--- "+rs.getString(2));
				out.println("m_rec_ref[i]-"+i+"-- "+m_rec_ref[i]);
				//m_Print_cheques.printing_interface_4(m_rec_ref); //commented 2010-11-08
				//m_Print_cheques.printing_interface_4(rs.getString(1),m_from_date,m_to_date); //uncommented this line for testing
				m_Print_cheques.printing_interface_10(m_rec_ref);
				}
				System.out.println(m_rec_ref);
				
				i=i+1;
				more = rs.next();
				}
				
				
				
				for (int j=1;j<=count;j++){
		   		//String m_save_status=(String)m_sn_methods.met_formdata(reqstr,"Text_save"+(Integer.toString(j)));
				 	//if(m_save_status.equals("on")){
						//String m_rec_ref=m_sn_methods.met_formdata(reqstr,"H_sus_refno"+(Integer.toString(j)));
						
						
 				} 

				//m_Print_cheques.printing_interface_1(m_ref_no);
				out.println("Document generated. Please check ");
				System.gc();
			}	
			
			
			
			
			
			
		/*	if(m_chksql.trim().equals("RECEIPT")){
			  String m_receipt_num ="";
				
				if(req.getParameter("receipt_num")==null){
								
				rs = stmt.executeQuery (" SELECT REC_NO "+
				" FROM LAKDL.AF_CO_PRO_SETTL_RECEIPT "+
				" WHERE ENT_DATE > TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
        " ENT_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') ");
				
 				}else if(req.getParameter("receipt_num")!=null){
					
				m_receipt_num = req.getParameter("receipt_num");
				
				rs = stmt.executeQuery (" SELECT REC_NO "+
				" FROM LAKDL.AF_CO_PRO_SETTL_RECEIPT "+
				" WHERE REC_NO =  '"+m_receipt_num+"' ");
				
				}
				
				Bulk_Printing_Main m_Print_cheques = new Bulk_Printing_Main();
				
				String m_chq_pay_no="";
				
				//int count =(Integer.parseInt((String)m_sn_methods.met_formdata(reqstr,"Hid_rows")));
				int count = 1;
				
				
				boolean	more = rs.next();
				int i=0;
				while(more){
				
				m_rec_ref[i]=rs.getString(1);
				out.println("Document--- "+rs.getString(2));
				out.println("m_rec_ref[i]-"+i+"-- "+m_rec_ref[i]);
				m_Print_cheques.printing_interface_2(m_rec_ref);
				System.out.println(m_rec_ref);
				i=i+1;
				more = rs.next();
				}
				
				

				//m_Print_cheques.printing_interface_1(m_ref_no);
				out.println("Document generated. Please check ");
				System.gc();
			}
			
			
				if(m_chksql.trim().equals("MONTHLY_STATEMENT")){
			  String m_client_code ="";
				String m_value_date ="";
				
				m_value_date = req.getParameter("to_date"); 
				m_client_code= req.getParameter("client_num");
				
				if(req.getParameter("client_num")==null){
												
				rs= stmt.executeQuery (" SELECT "+
				" DISTINCT GROUP_INV_NO,  "+//1
				" SUM(TOTAL_AMOUNT) , "+ //2
				" NVL((TO_CHAR(DUE_DATE,'DD-MM-YYYY')),'-') DUE_DATE ,  "+ //3
				" B.CLIENT_CODE "+//4
				" FROM LAKDL.AF_CO_PRO_INVOICE A,LAKDL.AF_CO_PRO_APPLICATION_DETAILS B "+
				" WHERE A.FINANCE_NO=B.FINANCE_NO "+
				" AND  INVOICE_TYPE='INV_GENER'  "+
				" AND  UPPER(B.CLIENT_CODE)=UPPER('"+m_client_code+"') "+
				" AND  DUE_DATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_value_date+"','DD-MM-YYYY'),-1))+1)   "+
				" AND  DUE_DATE<=LAST_DAY(TO_DATE('"+m_value_date+"','DD-MM-YYYY')) "+
				" AND  B.APPLICATION_STATUS='ACTIVATED' "+
				" AND  PRINTED_STATUS IS NULL"+
				" GROUP BY GROUP_INV_NO,DUE_DATE,B.CLIENT_CODE ");
								
 				}else if(req.getParameter("client_num")!=null){
					
				//m_client_num = req.getParameter("client_num");
				
				
				rs= stmt.executeQuery (" SELECT "+
				" DISTINCT GROUP_INV_NO,  "+//1
				" SUM(TOTAL_AMOUNT) , "+ //2
				" NVL((TO_CHAR(DUE_DATE,'DD-MM-YYYY')),'-') DUE_DATE ,  "+ //3
				" B.CLIENT_CODE "+//4
				" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
				" WHERE A.FINANCE_NO=B.FINANCE_NO "+
				" AND  INVOICE_TYPE='INV_GENER'  "+
				" AND  UPPER(B.CLIENT_CODE)=UPPER('"+m_client_code+"') "+
				" AND  DUE_DATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_value_date+"','DD-MM-YYYY'),-1))+1)   "+
				" AND  DUE_DATE<=LAST_DAY(TO_DATE('"+m_value_date+"','DD-MM-YYYY')) "+
				" AND  B.APPLICATION_STATUS='ACTIVATED' "+
				" AND  PRINTED_STATUS IS NULL"+
				" GROUP BY GROUP_INV_NO,DUE_DATE,B.CLIENT_CODE ");
				
								
				}
				
				Bulk_Printing_Main m_Print_cheques = new Bulk_Printing_Main();
				
				String m_chq_pay_no="";
				
				//int count =(Integer.parseInt((String)m_sn_methods.met_formdata(reqstr,"Hid_rows")));
				int count = 1;
				
				
				boolean	more = rs.next();
				int i=0;
				while(more){
				
				m_rec_ref[i]=rs.getString(1);
				out.println("Document--- "+rs.getString(2));
				out.println("m_rec_ref[i]-"+i+"-- "+m_rec_ref[i]);
				m_Print_cheques.printing_interface_3(m_rec_ref);
				System.out.println(m_rec_ref);
				i=i+1;
				more = rs.next();
				}
				
				

				//m_Print_cheques.printing_interface_1(m_ref_no);
				out.println("Document generated. Please check ");
				System.gc();
			}
			*/
			
			
			
			
			
			
			
			else {
				out.println("Undefined123");
			}
      out.close();
			this.destroy();
		}
		catch (Exception e) {
			
			ByteArrayOutputStream ostr = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(ostr));
			
			ServletOutputStream out = res.getOutputStream();
			out.println(ostr.toString());
      out.close();
			
		}
	}
}

