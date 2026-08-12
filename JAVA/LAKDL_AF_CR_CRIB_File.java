//--
//SCREEN NAME:OFSCL CRIB DATA TRANFER
//CREATED BY:CHANDANA
//DATE/TIME:14/11/2007
//NOTES: 

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_CR_CRIB_File extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public String m_chksql;
	Connection conn;
	Statement stmt,stmt1;
	CallableStatement callstmt1 =null;
	public ResultSet rs,rs_cf,rs_cs,rs_ss,rs_gs,rs_rs;
	
	
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods  m_sn_methods = new LAKDL_AF_CO_conn_methods (); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			String m_schema_name = m_sn_methods.schema_name;
			String m_username = m_sn_methods.username;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
						
			m_chksql=req.getParameter("chksql");
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			
			if(m_chksql.equals("run_report")){ 
			
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_rpt_type=req.getParameter("rpt_type");
				
				try{
				callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".CO_SAVE_CRIB_DATA(:1,:2,:3,:4);END;");
				callstmt1.setString(1,m_from_date);
				callstmt1.setString(2,m_username);
				callstmt1.setString(3,m_username);
				callstmt1.setString(4,m_rpt_type);
				callstmt1.execute();
				out.print("OK"); 
				}
				catch(Exception ex){
				out.println("ERROR"+ex.toString()); 
				}

			}
			else if(m_chksql.equals("text_file")){
				
			
			String m_data_prov_id     = req.getParameter("data_prov_id");
			String m_data_point_id	  = req.getParameter("data_point_id"); 
			String m_data_prepar_date = req.getParameter("data_prepar_date");
			String m_data_report_date = req.getParameter("data_report_date"); 
			String m_data_report_time = req.getParameter("data_report_time"); 
			String m_data_cf_type     = req.getParameter("data_cf_type");
			
			String m_prepar_date="";
			String m_report_date="";
			rs = stmt.executeQuery (" SELECT TO_CHAR(TO_DATE('"+m_data_prepar_date+"','DD-MM-YYYY'),'DD-MON-YYYY'), "+
			                        " TO_CHAR(TO_DATE('"+m_data_report_date+"','DD-MM-YYYY'),'DD-MON-YYYY') "+
															" FROM DUAL ");
			
			boolean more = rs.next();
						
			if(more){
			m_prepar_date = rs.getString(1); 
			m_report_date = rs.getString(2);
			}
						
			if(m_data_cf_type.equals("I")){
			out.println("HDHD|"+m_data_prov_id+"|"+m_data_point_id+"|"+m_prepar_date+"|"+m_report_date+"|"+m_data_report_time+"|001"); 
      		}else if(m_data_cf_type.equals("C")){
			out.println("HDHD|"+m_data_prov_id+"|"+m_data_point_id+"|"+m_prepar_date+"|"+m_report_date+"|"+m_data_report_time+"|002"); 
			} 
			
			rs = stmt.executeQuery (
			" SELECT B.HEADER_ID, B.ID,B.FACILITY_NO, B.CRIBDETAILS CRIBDETAILS  "+
			" FROM "+m_schema_name+".DESTINITY_LE_CRIBHEADER A, "+m_schema_name+".DESTINITY_LE_CRIBDETAILS B "+
			" WHERE A.HEADER_ID =B.HEADER_ID "+
			" AND   A.FILETYPE = '"+m_data_cf_type+"' "+
			" ORDER BY B.HEADER_ID,B.ID ,B.FACILITY_NO "+
			"");
			while(rs.next()){
			out.println(rs.getString("CRIBDETAILS"));
			}
			
			rs = stmt.executeQuery (
			" SELECT TOTALCONTRACTS "+
			" FROM   "+m_schema_name+".DESTINITY_LE_CRIBHEADER "+
			" WHERE  FILETYPE='"+m_data_cf_type+"' "+
			"");
			
			if(rs.next()){
			 out.println("TLTL|"+m_data_prov_id+"|"+m_data_point_id+"|"+rs.getString("TOTALCONTRACTS")+""); 	
			}
			
		   
			
			
		    }
			
 
			
		
			else{
			
			out.println("undifind");
			}
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
