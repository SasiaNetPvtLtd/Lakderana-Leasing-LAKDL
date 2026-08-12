import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import sun.misc.BASE64Decoder; 
import java.lang.*;
import java.sql.*;


import oracle.jdbc.driver.*;



//Created by Chandana on 08/01/2008

public class LAKDL_AF_RE_Invoice_Printing extends javax.servlet.http.HttpServlet {

  public String m_ref_no,m_chksql;
  public String m_from_date,m_to_date,m_print_type,m_client_code="";
	public ResultSet rs;
	Connection conn;
  Statement stmt;
  String[]  m_inv_num = new String[2030]; 
	String[]  m_pay_ref = new String[2030];
	
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
			m_client_code = req.getParameter("client_code");
			String m_ref_no="";
			
     	//out.println(reqstr);
			stmt = conn.createStatement();	
				
			if(m_chksql.trim().equals("PRINT")) {
			
				//m_ref_no = req.getParameter("ref_no");
				
				Bulk_Printing_Main m_Print_cheques = new Bulk_Printing_Main();
				
				Bulk_Printing_Main invoce_print =new Bulk_Printing_Main();
				
				String m_chq_pay_no="";
				
				int count = 1;
				
			if(m_print_type.equals("PEN")){													
														
				rs = stmt.executeQuery (" SELECT GROUP_INV_NO, "+
				                        " FINANCE_NO, "+
				                        " CLIENT_CODE, "+
																" VALUE_DATE "+
															  " FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
																" WHERE PRINTED_STATUS IS NULL "+
																" AND TO_DATE(TO_char(VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																" AND TO_DATE(TO_char(VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																" AND CLIENT_CODE = '"+m_client_code+"'  "+
																" GROUP BY   CLIENT_CODE,FINANCE_NO,VALUE_DATE,GROUP_INV_NO "+
																" ORDER BY VALUE_DATE  "); 
																
			}else if(m_print_type.equals("ALL")){													
															
				rs = stmt.executeQuery (" SELECT GROUP_INV_NO, "+
				                        " FINANCE_NO, "+
				                        " CLIENT_CODE, "+
																" VALUE_DATE "+
																" FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
																" WHERE TO_DATE(TO_char(VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																" AND TO_DATE(TO_char(VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																" AND CLIENT_CODE = '"+m_client_code+"'  "+
																" GROUP BY CLIENT_CODE,FINANCE_NO,VALUE_DATE,GROUP_INV_NO "+
																" ORDER BY VALUE_DATE ASC ");												
																
																}
														
			  boolean	more = rs.next();
				int i=0;
				
							
				while(more){
				
				//if(i<1){
				
				out.println("---------- Inoice Number--"+i+"------"+rs.getString(1));
				//int m_ginvoice_no=invoce_print.printing_interface_9(rs.getString(1));
				//int m_ginvoice_no=invoce_print.printing_interface_9("GI20080502-1032"); ///
				//int m_ginvoice_no=invoce_print.printing_interface_invoice_individual("GI20071207-0054"); 
				int m_ginvoice_no=invoce_print.printing_interface_invoice_individual(rs.getString(1),m_from_date,m_to_date,m_client_code);
				//int m_ginvoice_no=invoce_print.printing_interface_9("GI20080306-2155");
			//out.println("----------m_ginvoice_no --------"+m_ginvoice_no);
			//	}
				
				//m_inv_num[i]=rs.getString(1);
				//out.println("Document--- "+rs.getString(1));
				//out.println("m_pay_ref[i]-"+i+"-- "+m_inv_num[i]);
				//System.out.println("--"+m_inv_num);
				i=i+1;
				more = rs.next();
				}
				
				
				
				
				//int c=m_Print_cheques.printing_interface_9(m_inv_num);
				
				//out.println("c--- "+c);
				
			//	for (int j=1;j<=count;j++){
		   		//String m_save_status=(String)m_sn_methods.met_formdata(reqstr,"Text_save"+(Integer.toString(j)));
				 	//if(m_save_status.equals("on")){
						//String m_pay_ref=m_sn_methods.met_formdata(reqstr,"H_sus_refno"+(Integer.toString(j)));
						
						
 			//	} 

				//m_Print_cheques.printing_interface_1(m_ref_no);
				out.println("Document generated. Please check ");
				System.gc();
			}	
			
			
			
			
			
			
			if(m_chksql.trim().equals("RECEIPT")){
			  String m_receipt_num ="";
				
				if(req.getParameter("receipt_num")==null){
								
				rs = stmt.executeQuery (" SELECT REC_NO "+
				" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
				" WHERE ENT_DATE > TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
        " ENT_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') ");
				
 				}else if(req.getParameter("receipt_num")!=null){
					
				m_receipt_num = req.getParameter("receipt_num");
				
				rs = stmt.executeQuery (" SELECT REC_NO "+
				" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
				" WHERE REC_NO =  '"+m_receipt_num+"' ");
				
				}
				
				Bulk_Printing_Main m_Print_cheques = new Bulk_Printing_Main();
				
				String m_chq_pay_no="";
				
				//int count =(Integer.parseInt((String)m_sn_methods.met_formdata(reqstr,"Hid_rows")));
				int count = 1;
				
				
				boolean	more = rs.next();
				int i=0;
				while(more){
				
				m_pay_ref[i]=rs.getString(1);
				out.println("Document--- "+rs.getString(1));
				out.println("m_pay_ref[i]-"+i+"-- "+m_pay_ref[i]);
				m_Print_cheques.printing_interface_10(m_pay_ref);
				System.out.println(m_pay_ref);
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
												
				out.println("client_null");								
												
				rs= stmt.executeQuery (" SELECT "+
				" DISTINCT GROUP_INV_NO,  "+//1
				" SUM(TOTAL_AMOUNT) , "+ //2
				" NVL((TO_CHAR(DUE_DATE,'DD-MM-YYYY')),'-') DUE_DATE ,  "+ //3
				" B.CLIENT_CODE "+//4
				" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
				" WHERE A.FINANCE_NO=B.FINANCE_NO "+
				" AND  INVOICE_TYPE='INV_GENER'  "+
				" AND  DUE_DATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_value_date+"','DD-MM-YYYY'),-1))+1)   "+
				" AND  DUE_DATE<=LAST_DAY(TO_DATE('"+m_value_date+"','DD-MM-YYYY')) "+
				" AND  B.APPLICATION_STATUS='ACTIVATED' "+
				" AND  PRINTED_STATUS IS NULL"+
				" GROUP BY GROUP_INV_NO,DUE_DATE,B.CLIENT_CODE ");
								
 				}else if(req.getParameter("client_num")!=null){
					
				//m_client_num = req.getParameter("client_num");
				out.println("client_ok");	
				
				
				rs= stmt.executeQuery (" SELECT DISTINCT GROUP_INV_NO, "+
				" SUM(TOTAL_AMOUNT) , "+
				" NVL((TO_CHAR(DUE_DATE,'DD-MM-YYYY')),'-') DUE_DATE ,  "+
				" B.CLIENT_CODE "+
				" FROM LAKDL.AF_CO_PRO_INVOICE A,LAKDL.AF_CO_PRO_APPLICATION_DETAILS B "+
				" WHERE A.FINANCE_NO=B.FINANCE_NO "+
				" AND  INVOICE_TYPE='INV_GENER' "+
				" AND  UPPER(B.CLIENT_CODE)=UPPER('0000034644') "+
				" AND  B.APPLICATION_STATUS='ACTIVATED' "+
				" AND  PRINTED_STATUS IS NULL "+
				" GROUP BY GROUP_INV_NO,DUE_DATE,B.CLIENT_CODE ");
						
								
				}
				
				Bulk_Printing_Main m_Print_Monthly_Statement = new Bulk_Printing_Main();
				
				String m_chq_pay_no="";
				
				//int count =(Integer.parseInt((String)m_sn_methods.met_formdata(reqstr,"Hid_rows")));
				int count = 1;
				
				
				boolean	more = rs.next();
				int i=0;
				while(more){
				
				//m_pay_ref[i]=rs.getString(1);
				out.println("Document--- "+rs.getString(1));
				//out.println("m_pay_ref[i]-"+i+"-- "+m_pay_ref[i]);
				m_Print_Monthly_Statement.printing_interface_11(rs.getString(1),m_value_date);
				//System.out.println(m_pay_ref);
				i=i+1;
				more = rs.next();
				}
				
				out.println("m_value_date-----"+m_value_date);

				//m_Print_cheques.printing_interface_1(m_ref_no);
				out.println("Document generated. Please check ");
				System.gc();
			}
			
			
			
			
			
			
			
			
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

