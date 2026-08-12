//Option Id is 2.1  
//This File was created by SVA on 17-05-2006 
//Marketing Inquiry Display
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
//import FCAM_sn_methods;
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_CO_Followup_XML extends javax.servlet.http.HttpServlet {
	
	/*
	Connection conn;
	Statement stmt;
	java.text.NumberFormat nf;
	public ResultSet rs;
	public String m_chksql;
	ServletOutputStream out = null;
	*/
	
	//public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	public void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		Connection conn=null;
		Statement stmt=null;
		java.text.NumberFormat nf=null;
		ResultSet rs=null;
		String m_chksql=null;
		ServletOutputStream out = null;
		
		try {
			
			//************************************************************	
			LAKDL_AF_MK_CO_methods CO_methods = new LAKDL_AF_MK_CO_methods();
			
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String m_schema_name = con_method.schema_name;
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
			String m_username 						= con_method.username;
			
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.40.1:1521:DN10G", "system", "sys123");
			
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
			nf.setMinimumFractionDigits(2);
			
			res.setStatus(HttpServletResponse.SC_OK);
			//httpservletresponse.setContentType("text/xml");
			res.setStatus(200);
			res.setContentType("text/xml");
			out = res.getOutputStream();
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
				
			}else if(m_chksql.trim().equals("FollowupDet")){
			
				rs = stmt.executeQuery("SELECT A.FOLLOW_UP_NO, NVL(A.ID_NO,'-'),A.ACTION_TOBE_TAKEN, "+
				                       "       A.EFF_VAL_DATE "+
															 "FROM "+m_schema_name+"..AF_CO_PRO_FOLLOW_UP A   ");
				boolean flag = rs.next();
				out.println("<Root>");
				for(; flag; flag = rs.next())
				{
					out.println("<ITEM>");
					out.println("<FNO>"    + rs.getString(1) + "</CLIENT>");
					out.println("<IDNO>"   + rs.getString(2) + "</NAME>");
					out.println("<ATBT>"   + rs.getString(3) + "</SNAME>");
					out.println("<EVDATE>" + rs.getString(4) + "</NIC>");
					out.println("</ITEM>");
				}
				
				out.println("</Root>");
				
			}else if(m_chksql.trim().equals("FollowupCat")){
			
				rs = stmt.executeQuery("SELECT A.CATEGORY_CODE, A.CATEGORY_NAME "+
															 "FROM   "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY A "+
															 "WHERE  A.ACTIVE_STATUS='Y'  ");
																
				boolean flag = rs.next();
				
				out.println("<Root>");
				for(; flag; flag = rs.next())
				{
					out.println("<ITEM>");
					out.println("<CATEGORY>" + rs.getString(1) + "</CLIENT>");
					out.println("<NAME>"     + rs.getString(2) + "</NAME>");
					out.println("</ITEM>");
				}
				
				out.println("</Root>");
				
			}else if(m_chksql.trim().equals("Division")){
			  String m_div_code     = req.getParameter("DIVISOIN");
         
				rs = stmt.executeQuery("SELECT DIVISION_CODE,DESCRIPTION "+
															 "FROM   "+m_schema_name+".CO_CO_MAS_DIVISION "+
						 									 "WHERE  ACTIVE_STATUS='Y' AND DIVISION_CODE='"+m_div_code+"' ");
																
				boolean flag = rs.next();
				
				out.println("<Root>");
				for(; flag; flag = rs.next())
				{
					out.println("<ITEM>");
					out.println("<DCODE>" + rs.getString(1) + "</DCODE>");
					out.println("<DNAME>" + rs.getString(2) + "</DNAME>");
					out.println("</ITEM>");
				}
				
				out.println("</Root>");
			}
			else if(m_chksql.trim().equals("SubDivision")){
			  String m_div_code     = req.getParameter("DIVISOIN");
        String m_sdiv_code    = req.getParameter("SUB_DIVISOIN");
         
				rs = stmt.executeQuery("SELECT SUB_DIVISION_CODE,DESCRIPTION "+
															 "FROM   "+m_schema_name+".CO_CO_MAS_SUB_DIVISION "+
						 									 "WHERE  ACTIVE_STATUS='Y' AND "+
															 "       SUB_DIVISION_CODE='"+m_sdiv_code+"' AND "+ 	
															 "       DIVISION_CODE='"+m_div_code+"' ");
																
				boolean flag = rs.next();
				
				out.println("<Root>");
				for(; flag; flag = rs.next())
				{
					out.println("<ITEM>");
					out.println("<DCODE>" + rs.getString(1) + "</DCODE>");
					out.println("<DNAME>" + rs.getString(2) + "</DNAME>");
					out.println("</ITEM>");
				}
				
				out.println("</Root>");
			}
			else if(m_chksql.trim().equals("Product")){
			  String m_pro_code     = req.getParameter("PRODUCT");
         
				rs = stmt.executeQuery("SELECT PRODUCT_CODE,DESCRIPTION "+
															 "FROM   "+m_schema_name+".AF_CO_MAS_PRODUCT "+
						 									 "WHERE  ACTIVE_STATUS='Y' AND "+
															 "       PRODUCT_CODE='"+m_pro_code+"' ");
																
				boolean flag = rs.next();
				
				out.println("<Root>");
				for(; flag; flag = rs.next())
				{
					out.println("<ITEM>");
					out.println("<PCODE>" + rs.getString(1) + "</PCODE>");
					out.println("<PNAME>" + rs.getString(2) + "</PNAME>");
					out.println("</ITEM>");
				}
				
				out.println("</Root>");
			}
			else if(m_chksql.trim().equals("Foll_Cat")){
			  String m_sdiv_code     = req.getParameter("SUB_DIVISION");
         
				rs = stmt.executeQuery( "SELECT CATEGORY_CODE, CATEGORY_NAME "+
															  "FROM   "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY "+
																"WHERE  SUB_DIVISION_CODE LIKE '"+m_sdiv_code+"%' AND "+
																"       ACTIVE_STATUS = 'Y' "+
																"ORDER  BY DEFAULT_VALUE ");
															 
				boolean flag = rs.next();
				
				out.println("<Root>");
				for(; flag; flag = rs.next())
				{
					out.println("<ITEM>");
					out.println("<PCODE>" + rs.getString(1) + "</PCODE>");
					out.println("<PNAME>" + rs.getString(2) + "</PNAME>");
					out.println("</ITEM>");
				}
				
				out.println("</Root>");
			}
			else if(m_chksql.trim().equals("chk_next_followup")){
			
			   String m_next       = req.getParameter("next");
         String m_actu       = req.getParameter("actual");
        
				rs = stmt.executeQuery (" SELECT '"+m_next+"' FROM DUAL WHERE TO_DATE('"+m_next+"','DD-MM-YYYY')>=TO_DATE('"+m_actu+"','DD-MM-YYYY') ");

	      boolean flag = rs.next();
				out.println("<Root>");
				//out.println("flag-"+flag);
				for(; flag; flag = rs.next())
				{
					out.println("<ITEM>");
					out.println("<FDATE>" + rs.getString(1) + "</FDATE>");
					out.println("</ITEM>");
				}
				
				//out.println("</DATA>");
				out.println("</Root>");
      
      
			
			}
			else if(m_chksql.trim().equals("get_followup")){
			
			       String m_value      = req.getParameter("fno");
              //Modified by Mahela on 17-05-2007  ---  AF_CO_GET_TRANSACTION_DESC function
							rs = stmt.executeQuery (" SELECT A.FOLLOW_UP_NO, NVL(A.ID_NO,'-'), CATEGORY_NAME,"+//3
																			"	       TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY'),TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'), "+//2
																			"	       A.ENT_USER,NVL(A.ENT_REMARKS,'-'),NVL(A.ACTION_TAKEN,'-'), "+//3
																			"	       A.ACTION_DATE,NVL(A.REMARKS,'-'),ACTION_SET_FOR,TO_CHAR(SYSDATE,'DD-MM-YYYY'), "+//4
																			"        DIVISION_CODE,A.SUB_DIVISION_CODE,PRODUCT_CODE,  "+//3
																			"        "+m_schema_name+".AF_CO_GET_DIVISION_DESC(DIVISION_CODE), "+//1
																			"        "+m_schema_name+".AF_CO_GET_SUB_DIVISION_DESC(A.SUB_DIVISION_CODE), "+//1
																			"        "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(PRODUCT_CODE) "+//1
																			" FROM   "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A, "+
																			"		     "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY B "+
																			"	WHERE  STATUS = 'PENDING' AND ACTION_SET_FOR='"+m_username+"' AND "+
																			"        CATEGORY_CODE=ACTION_TOBE_TAKEN AND FOLLOW_UP_NO='"+m_value.toUpperCase()+"' AND "+
																			"        EFF_VAL_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MON-YYYY'),'DD-MON-YYYY') "+
																			"	ORDER  BY PRIORITY ");

	      boolean flag = rs.next();
				out.println("<Root>");
				//out.println("flag-"+flag);
				for(; flag; flag = rs.next())
				{
					out.println("<ITEM>");
					out.println("<FOLLO>" + rs.getString(1) + "</FOLLO>");
					out.println("<ID>"    + rs.getString(2) + "</ID>");
					out.println("<CAT>"   + rs.getString(3) + "</CAT>");
					out.println("<EDATE>" + rs.getString(4) + "</EDATE>");
					out.println("<ENTD>"  + rs.getString(5) + "</ENTD>");
					out.println("<EUSER>" + rs.getString(6) + "</EUSER>");
					out.println("<EREM>"  + rs.getString(7) + "</EREM>");
					out.println("<ACTION>"+ rs.getString(8) + "</ACTION>");
					out.println("<ADATE>" + rs.getString(9) + "</ADATE>");
					out.println("<REMA>"  + rs.getString(10) + "</REMA>");
					out.println("<ASF>"   + rs.getString(11) + "</ASF>");
          out.println("<SDATE>" + rs.getString(12) + "</SDATE>");
					out.println("<DIVI>"  + rs.getString(13) + "</DIVI>");
					out.println("<SDIVI>" + rs.getString(14) + "</SDIVI>");
          out.println("<PRODU>" + rs.getString(15) + "</PRODU>");
					out.println("<DIVID>" + rs.getString(16) + "</DIVID>");
					out.println("<SDIVID>"+ rs.getString(17) + "</SDIVID>");
          out.println("<PRODUD>"+ rs.getString(18) + "</PRODUD>");
					out.println("</ITEM>");
				}
				
				//out.println("</DATA>");
				out.println("</Root>");
      
      }
			
			
			
		}
		catch (Exception e) {
			try{out.println(e.toString());}catch(Exception e1){}
			//return null;
		}finally{
			if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
			if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e){}}
			//try{conn.setAutoCommit(true);
		}
	}
}
