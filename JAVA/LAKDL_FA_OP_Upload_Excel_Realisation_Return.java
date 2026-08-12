// DEVELOP BY : UDARA FOR OFSCL FACTORING    DATE:26-01-2012
         

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
   

public class LAKDL_FA_OP_Upload_Excel_Realisation_Return extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public ResultSet rs,rs1;
	
	Connection conn;
	Statement stmt,stmt1;
	CallableStatement callstmt1;
	public PreparedStatement pstmt;
	java.text.NumberFormat nf;

	public String m_chksql;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			String m_schema_name=m_sn_methods.schema_name.trim();
			//String m_username = m_sn_methods.username;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			conn=m_sn_methods.met_user_validate(req); 
			
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			
			// ========================== Uploading Section Start ============================================================
			
				String m_receipt_no = "";
				String m_comments   = "";
				String m_date       = "";
				String m_date_comp  = "";
				String m_type       = ""; 

				
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ=D:\\SasiaNet_Products\\NetAsset\\OFSCL\\UPLOAD\\Return_Realizations\\RETURN_REALIZATIONS.xls" );
	
                if(con==null){
					out.println("Connection Not Created");				
				}

				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery( "Select * from [PRICE$]" );
				ResultSetMetaData rsmd = rs.getMetaData();
				int numberOfColumns = rsmd.getColumnCount();
				
				callstmt1= conn.prepareCall( "BEGIN "+m_schema_name+".FA_OP_UPLOAD_RET_REALIZE_PRO(:1,:2,:3,:4,:5); END;");
				
				while (rs.next()) {

					for (int i = 1; i <= numberOfColumns; i++) {
						
						if (i == 1 )
							m_receipt_no = rs.getString(i);
						else if (i == 2 )
							m_comments = rs.getString(i);
						else if (i == 3 )
							m_date = rs.getString(i);
						else if (i == 4)
							m_date_comp = rs.getString(i);
						else if (i == 5 )
							m_type = rs.getString(i);
							
					}
						
					callstmt1.setString(1,m_receipt_no);		
					callstmt1.setString(2,m_comments);				
					callstmt1.setString(3,m_date);
					callstmt1.setString(4,m_date_comp);
					callstmt1.setString(5,m_type); 
					
					callstmt1.execute();


				}
				
				/*
					try{
					if(callstmt1!=null)callstmt1.close();
					}catch(Exception e){
					out.println("Callstmt1--"+e.toString());
					}
				*/
				
			// ========================== End Uploading Section ================================================================

			// ========================== Report Section Start =================================================================			
		
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE> Uploaded Data From Excel </TITLE>"); 
			out.println("</HEAD>"); 
			
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
			out.println("<BODY class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
			
			out.println("<br>");
			
			out.println("<table border=0 align=center >");
			out.println("<tr align=center >");
		    out.println("<td  align=center ><b> Uploaded Data From Excel </b></td>");
		    out.println("</tr>");
			out.println("</table>");

			out.println("<br>");
			
		
			rs1= stmt1.executeQuery(" "+
				
					" SELECT  DECODE(D.STATUS,'RETURN','','REALIZED','REA') REF_NO, "+ // 1
				        " A.DEPOSIT_NO, "+ // 2
				        " B.RECEIPT_NO,  "+ // 3
				        " NVL(B.DEPOSIT_AMOUNT,0), "+ // 4
				        " NVL(B.CHEQUE_NO,'-'), "+ // 5
						" TO_CHAR(D.RET_REALIZE_DATE,'DD-MM-YYYY') RET_REALIZE_DATE, "+ // 6
						" DECODE(D.STATUS,'RETURN','RET','REALIZED','REA') STATUS, "+ // 7
						" A.ACC_NO, "+ // 8
						" A.BRANCH_CODE, "+ // 9
						" 'NEW', "+ // 10
						" 'user name', "+ // 11
						" D.COMMENTS, "+ // 12 
						" TO_CHAR(D.RET_REALIZE_DATE_COMP,'DD-MM-YYYY') RET_REALIZE_DATE_COMP "+ // 13
				  			 " FROM "+m_schema_name+".FA_OP_PRO_DEPOSIT A,"+m_schema_name+".FA_OP_PRO_DEPOSIT_DETAILS B,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT C, "+m_schema_name+".FA_OP_UPLOAD_RET_REALIZE D "+
				  			 " WHERE A.DEPOSIT_NO=B.DEPOSIT_NO "+
				             " AND B.RECEIPT_NO=C.RECEIPT_NO "+
				             " AND C.RECEIPT_NO = D.RECEIPT_NO  "+
				             " AND A.STATUS='Y' "+
				             " AND C.REC_STATUS='B' "+
							 " ORDER BY A.DEPOSIT_DATE "+	 
				" ");

			
			out.println("<table align='center' width='100%' class='table' >");
			out.println("<tr class=pdn_txtpos2 >");
			
			// out.println("<td width='10%' class='div_input'><b>Ref No</b></td>");
			out.println("<td width='12%' class='div_input'><b>Deposit No</b></td>");
			out.println("<td width='12%' class='div_input'><b>Receipt No</b></td>");
			out.println("<td width='10%' class='div_input'><b>Deposit Amount</b></td>");
			out.println("<td width='10%' class='div_input'><b>Cheque No</b></td>");
			out.println("<td width='10%' class='div_input'><b>Return/Realize Date</b></td>");
			out.println("<td width='8%' class='div_input'><b>Status</b></td>");
			out.println("<td width='8%' class='div_input'><b>Account No</b></td>");
			out.println("<td width='10%' class='div_input'><b>Branch Code</b></td>");
			// out.println("<td width='10%' class='div_input'><b>Screen Name</b></td>");
			// out.println("<td width='10%' class='div_input'><b>User Name</b></td>");
			out.println("<td width='10%' class='div_input'><b>Comments</b></td>");
			out.println("<td width='10%' class='div_input'><b>Return/Realize Date Company</b></td>");
			out.println("</tr>");
			
			
			int j=1;
			while (rs1.next()) {
				
				if(j==0){
					out.println("<tr bgcolor=\"#FFFFFF\">");
					j=1;
				}
				else{
					out.println("<tr bgcolor=\"#C0C0C0\" >");
					j=0;
				}
				
				
				// out.println("<td> "+rs1.getString(1)+" </td>");
				out.println("<td> "+rs1.getString(2)+" </td>");
				out.println("<td> "+rs1.getString(3)+" </td>");
				out.println("<td align=right > "+nf.format(rs1.getDouble(4))+" </td>");
				out.println("<td> "+rs1.getString(5)+" </td>");
				out.println("<td> "+rs1.getString(6)+" </td>");
				out.println("<td> "+rs1.getString(7)+" </td>");
				out.println("<td> "+rs1.getString(8)+" </td>");
				out.println("<td> "+rs1.getString(9)+" </td>");
				// out.println("<td> "+rs1.getString(10)+" </td>");
				// out.println("<td> "+rs1.getString(11)+" </td>");
				out.println("<td> "+rs1.getString(12)+" </td>");
				out.println("<td> "+rs1.getString(13)+" </td>");
				
				out.println("</tr>");
				
			}
			
			
			out.println("</table>");
			out.println("</body>");
			out.println("</html>");

			// ========================== Report Section End ===================================================================
			String m_username = m_sn_methods.username;
			//out.println("Username ::::::::::::::::::::::::: " + m_username);
			// ========================== Update Start =========================================================================
			
			rs1= stmt1.executeQuery(" "+
				
					" SELECT  DECODE(D.STATUS,'RETURN','','REALIZED','REA') REF_NO, "+ // 1
				        " A.DEPOSIT_NO, "+ // 2
				        " B.RECEIPT_NO,  "+ // 3
				        " NVL(B.DEPOSIT_AMOUNT,0), "+ // 4
				        " NVL(B.CHEQUE_NO,'-'), "+ // 5
						" TO_CHAR(D.RET_REALIZE_DATE,'DD-MM-YYYY') RET_REALIZE_DATE, "+ // 6
						" DECODE(D.STATUS,'RETURN','RET','REALIZED','REA') STATUS, "+ // 7
						" A.ACC_NO, "+ // 8
						" A.BRANCH_CODE, "+ // 9
						" 'NEW', "+ // 10
						" 'user name', "+ // 11
						" D.COMMENTS, "+ // 12 
						" TO_CHAR(D.RET_REALIZE_DATE_COMP,'DD-MM-YYYY') RET_REALIZE_DATE_COMP "+ // 13
				  			 " FROM "+m_schema_name+".FA_OP_PRO_DEPOSIT A,"+m_schema_name+".FA_OP_PRO_DEPOSIT_DETAILS B,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT C, "+m_schema_name+".FA_OP_UPLOAD_RET_REALIZE D "+
				  			 " WHERE A.DEPOSIT_NO=B.DEPOSIT_NO "+
				             " AND B.RECEIPT_NO=C.RECEIPT_NO "+
				             " AND C.RECEIPT_NO = D.RECEIPT_NO  "+
				             " AND A.STATUS='Y' "+
				             " AND C.REC_STATUS='B' "+
							 " ORDER BY A.DEPOSIT_DATE "+	 
				" ");
			
			callstmt1= conn.prepareCall( "BEGIN "+m_schema_name+".FA_CR_SAVE_REALIZE_RETURN(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13); END;");
			
			while (rs1.next()) {
				/*
				out.println(rs1.getString(2));
				out.println(rs1.getString(3));
				out.println(rs1.getString(4));
				out.println(rs1.getString(5));
				out.println(rs1.getString(6));
				out.println(rs1.getString(7));
				out.println(rs1.getString(8));
				out.println(rs1.getString(9));
				out.println(rs1.getString(12));
				out.println(rs1.getString(13));
				*/
				
				callstmt1.setString(1,rs1.getString(1));		
				callstmt1.setString(2,rs1.getString(2));				
				callstmt1.setString(3,rs1.getString(3));
				callstmt1.setString(4,rs1.getString(4));
				callstmt1.setString(5,rs1.getString(5)); 
				callstmt1.setString(6,rs1.getString(6)); 
				callstmt1.setString(7,rs1.getString(7)); 
				callstmt1.setString(8,rs1.getString(8)); 
				callstmt1.setString(9,rs1.getString(9)); 
				callstmt1.setString(10,rs1.getString(10)); 
				callstmt1.setString(11,m_username); 
				callstmt1.setString(12,rs1.getString(12)); 
				callstmt1.setString(13,rs1.getString(13)); 
					
				callstmt1.execute();
		
				
			}
			
			// ========================== Update End ===========================================================================
			
			try{
					if(callstmt1!=null)callstmt1.close();
				}catch(Exception e){
					out.println("Callstmt1--"+e.toString());
				}
		
			try{
				if(st!=null)st.close();
			}catch(Exception e){
				out.println("St--"+e.toString());
			}
			
			try{
				if(con!=null)con.close();
			}catch(Exception e){
				out.println("Con--"+e.toString());
			}	

		
		
		
		
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());
			}catch(Exception e){}
		}
		finally{
				if(out!=null){
				try{out.close();  
				}catch(Exception e){}
				}
		}
	}
}
