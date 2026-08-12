import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
//import CLAMF_sn_methods;
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_CO_REP_followup_report extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt1;
	CallableStatement callstmt;
	java.text.NumberFormat nf;
	java.text.NumberFormat nf1;
    
  public ResultSet rs,rs1;
	public String m_chksql,m_chkval,m_state;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		try {
		
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			//**************************************************************					
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
            
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			res.setHeader("Cache-Control", "No-Cache");
     	res.setDateHeader("Expires", 0);
				
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 

			ServletOutputStream out = res.getOutputStream();

			m_chksql=req.getParameter("chksql");
			m_chkval=req.getParameter("chkval");
			m_state=req.getParameter("data_val9");
			stmt=conn.createStatement();
		
			//m_prime_chk_
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			
			
			else if (m_chksql.trim().equals("M1")){	
			
			String m_id_num = req.getParameter("data_val1").trim();
		//	String m_ac_date = req.getParameter("data_val2").trim();
			String m_eff_date = req.getParameter("data_val3").trim();
			String m_eff_to_date= req.getParameter("data_val4").trim();
			String m_ac_date = req.getParameter("data_val5").trim();
			String m_ac_set = req.getParameter("data_val6").trim();
			String m_screen = req.getParameter("data_val7").trim();
			String m_div_num = req.getParameter("data_val8").trim();
			String m_status = req.getParameter("data_val9").trim();
			
			
			//out.println(m_chkval);
		  //out.println(m_eff_date);
			//out.println(m_eff_to_date);
			

		
		if (m_chkval.trim().equals("N1")){
		if (m_state.trim().equals("ALL")){
		
		rs = stmt.executeQuery(" SELECT  P.FOLLOW_UP_NO,P.ID_NO,P.CATEGORY_NAME,P.EFF_VAL_DATE,NVL(P.ACTION_TAKEN,'-')AS ACTION_TAKEN,NVL(P.ENT_REMARKS,'-') AS ENTER_REMARKS,NVL(P.REMARKS,'-')AS REMARKS,P.ENT_USER "+
		                       " FROM "+
													 " (SELECT  A.FOLLOW_UP_NO,A.ID_NO,B.CATEGORY_NAME,TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY')AS EFF_VAL_DATE,A.ACTION_TAKEN,A.ACTION_TOBE_TAKEN,A.ACTION_SET_FOR,A.SCREEN_NAME,A.DIVISION_CODE,A.STATUS,A.ENT_REMARKS,A.REMARKS,A.ENT_USER,TO_CHAR(A.ACTION_DATE,'DD-MM-YYYY')AS ACTION_DATE "+
		                       " FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A, "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY B "+
		                       " WHERE A.ID_NO=B.CATEGORY_CODE )P "+
		                       " WHERE "+
		                       " P.ID_NO LIKE('"+m_id_num+"%')"+ 
		                       " AND  P.ACTION_SET_FOR LIKE UPPER('"+m_ac_set+"%') "+
		                       " AND P.SCREEN_NAME LIKE UPPER('"+m_screen+"%') "+
													 " AND P.DIVISION_CODE LIKE('"+m_div_num+"%') "+
		                     //" AND P.STATUS LIKE('"+m_status+"%')  "+
		                     //" AND  P.ACTION_DATE = ('"+m_ac_date+"') "+
		                       " AND P.EFF_VAL_DATE =('"+m_eff_date+"')  ");
		}else
		{
		rs = stmt.executeQuery(" SELECT  P.FOLLOW_UP_NO,P.ID_NO,P.CATEGORY_NAME,P.EFF_VAL_DATE,NVL(P.ACTION_TAKEN,'-')AS ACTION_TAKEN,NVL(P.ENT_REMARKS,'-') AS ENTER_REMARKS,NVL(P.REMARKS,'-')AS REMARKS,P.ENT_USER "+
		                      " FROM "+
		                      " (SELECT  A.FOLLOW_UP_NO,A.ID_NO,B.CATEGORY_NAME,TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY')AS EFF_VAL_DATE,A.ACTION_TAKEN,A.ACTION_TOBE_TAKEN,A.ACTION_SET_FOR,A.SCREEN_NAME,A.DIVISION_CODE,A.STATUS,A.ENT_REMARKS,A.REMARKS,A.ENT_USER,TO_CHAR(A.ACTION_DATE,'DD-MM-YYYY')AS ACTION_DATE "+
		                      " FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A, "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY B "+
		                      " WHERE A.ID_NO=B.CATEGORY_CODE )P "+
	                       	" WHERE "+
		                      " P.ID_NO LIKE ('"+m_id_num+"%')"+ 
		                      " AND  P.ACTION_SET_FOR LIKE UPPER('"+m_ac_set+"%') "+
		                      " AND P.SCREEN_NAME LIKE UPPER('"+m_screen+"%') "+
													" AND P.DIVISION_CODE LIKE('"+m_div_num+"%') "+
													" AND P.STATUS LIKE('"+m_status+"%')  "+
													//" AND  P.ACTION_DATE = ('"+m_ac_date+"') "+
													" AND P.EFF_VAL_DATE =('"+m_eff_date+"')  ");
			 }
		}

	else if (m_chkval.trim().equals("N2")){
			if (m_state.trim().equals("ALL")){
		
		rs = stmt.executeQuery(" SELECT  P.FOLLOW_UP_NO,P.ID_NO,P.CATEGORY_NAME,P.EFF_VAL_DATE,NVL(P.ACTION_TAKEN,'-')AS ACTION_TAKEN,NVL(P.ENT_REMARKS,'-') AS ENTER_REMARKS,NVL(P.REMARKS,'-')AS REMARKS,P.ENT_USER "+
		                       " FROM "+
		                       " (SELECT  A.FOLLOW_UP_NO,A.ID_NO,B.CATEGORY_NAME,TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY')AS EFF_VAL_DATE,A.ACTION_TAKEN,A.ACTION_TOBE_TAKEN,A.ACTION_SET_FOR,A.SCREEN_NAME,A.DIVISION_CODE,A.STATUS,A.ENT_REMARKS,A.REMARKS,A.ENT_USER,TO_CHAR(A.ACTION_DATE,'DD-MM-YYYY')AS ACTION_DATE "+
		                       " FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A, "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY B "+
		                       " WHERE A.ID_NO=B.CATEGORY_CODE )P "+
		                       " WHERE "+
		                       " P.ID_NO LIKE('"+m_id_num+"%')"+ 
		                       " AND  P.ACTION_SET_FOR LIKE UPPER('"+m_ac_set+"%') "+
		                       " AND P.SCREEN_NAME LIKE UPPER('"+m_screen+"%') "+
													 " AND P.DIVISION_CODE LIKE('"+m_div_num+"%') "+
														" AND TO_DATE(P.EFF_VAL_DATE,'DD-MM-YYYY')<= TO_DATE('"+m_eff_to_date+"','DD-MM-YYYY') "+
	                       " AND TO_DATE(P.EFF_VAL_DATE,'DD-MM-YYYY')>= TO_DATE('"+m_eff_date+"','DD-MM-YYYY') ");		
														
														
													//	" AND P.EFF_VAL_DATE BETWEEN '"+m_eff_date+"'  AND '"+m_eff_to_date+"' ");
													//	"AND TO_DATE(TO_CHAR(P.EFF_VAL_DATE,'DD-MON-YYYY'),'DD-MON-YYYY')<= TO_DATE('"+m_eff_to_date+"','DD/MM/YYYY')"+
													//	"AND TO_DATE(TO_CHAR(P.EFF_VAL_DATE,'DD-MON-YYYY'),'DD-MON-YYYY')>= TO_DATE('"+m_eff_date+"','DD/MM/YYYY') ");	

														
														
														
													//	"	AND	(TO_DATE(TO_CHAR(P.EFF_VAL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_eff_date+"','DD-MM-YYYY') AND TO_DATE(TO_CHAR(P.EFF_VAL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<= TO_DATE('"+m_eff_to_date+"','DD-MM-YYYY'))");
													
													//	"	AND	(TO_CHAR(P.EFF_VAL_DATE,'DD-MM-YYYY'))>=(TO_CHAR('"+m_eff_date+"','DD-MM-YYYY')) AND (TO_CHAR(P.EFF_VAL_DATE,'DD-MM-YYYY'))<= (TO_CHAR('"+m_eff_to_date+"','DD-MM-YYYY'))");
	                        //	" AND P.STATUS LIKE('"+m_status+"%')  "+
                        	//	"  OR  P.ACTION_DATE = ('"+m_ac_date+"') "+
		                      // " AND P.EFF_VAL_DATE BETWEEN '"+m_eff_date+"'  AND '"+m_eff_to_date+"' ");
												//	"AND((P.EFF_VAL_DATE >='"+m_eff_date+"') AND (P.EFF_VAL_DATE <='"+m_eff_to_date+"')) ");												
				
			}else             
			{
			rs = stmt.executeQuery(" SELECT  P.FOLLOW_UP_NO,P.ID_NO,P.CATEGORY_NAME,P.EFF_VAL_DATE,NVL(P.ACTION_TAKEN,'-')AS ACTION_TAKEN,NVL(P.ENT_REMARKS,'-') AS ENTER_REMARKS,NVL(P.REMARKS,'-')AS REMARKS,P.ENT_USER "+
		                         " FROM "+
		                         " (SELECT  A.FOLLOW_UP_NO,A.ID_NO,B.CATEGORY_NAME,TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY')AS EFF_VAL_DATE,A.ACTION_TAKEN,A.ACTION_TOBE_TAKEN,A.ACTION_SET_FOR,A.SCREEN_NAME,A.DIVISION_CODE,A.STATUS,A.ENT_REMARKS,A.REMARKS,A.ENT_USER,TO_CHAR(A.ACTION_DATE,'DD-MM-YYYY')AS ACTION_DATE "+
		                         " FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A, "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY B "+
		                         " WHERE A.ID_NO=B.CATEGORY_CODE )P "+
		                         " WHERE "+
		                         " P.ID_NO LIKE('"+m_id_num+"%')"+ 
		                         " AND  P.ACTION_SET_FOR LIKE UPPER('"+m_ac_set+"%') "+
		                         " AND P.SCREEN_NAME LIKE UPPER('"+m_screen+"%') "+
														 " AND P.DIVISION_CODE LIKE('"+m_div_num+"%') "+
		                         " AND P.STATUS LIKE('"+m_status+"%')  "+
															" AND TO_DATE(P.EFF_VAL_DATE,'DD-MM-YYYY')<= TO_DATE('"+m_eff_to_date+"','DD-MM-YYYY') "+
															" AND TO_DATE(P.EFF_VAL_DATE,'DD-MM-YYYY')>= TO_DATE('"+m_eff_date+"','DD-MM-YYYY') ");	
	                        //	"  OR  P.ACTION_DATE = ('"+m_ac_date+"') "+
		                       //  " AND ( P.EFF_VAL_DATE BETWEEN '"+m_eff_date+"'  AND '"+m_eff_to_date+"' ) ");
			}
		}
			
		else if (m_chkval.trim().equals("N3")){
		 if (m_state.trim().equals("ALL")){
		
		rs = stmt.executeQuery(" SELECT  P.FOLLOW_UP_NO,P.ID_NO,P.CATEGORY_NAME,P.EFF_VAL_DATE,NVL(P.ACTION_TAKEN,'-')AS ACTION_TAKEN,NVL(P.ENT_REMARKS,'-') AS ENTER_REMARKS,NVL(P.REMARKS,'-')AS REMARKS,P.ENT_USER "+
		                       " FROM "+
		                       " (SELECT  A.FOLLOW_UP_NO,A.ID_NO,B.CATEGORY_NAME,TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY')AS EFF_VAL_DATE,A.ACTION_TAKEN,A.ACTION_TOBE_TAKEN,A.ACTION_SET_FOR,A.SCREEN_NAME,A.DIVISION_CODE,A.STATUS,A.ENT_REMARKS,A.REMARKS,A.ENT_USER,TO_CHAR(A.ACTION_DATE,'DD-MM-YYYY')AS ACTION_DATE "+
		                       " FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A, "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY B "+
		                       " WHERE A.ID_NO=B.CATEGORY_CODE )P "+
		                       " WHERE "+
		                       " P.ID_NO LIKE ('"+m_id_num+"%')"+ 
		                       " AND  P.ACTION_SET_FOR LIKE UPPER('"+m_ac_set+"%') "+
		                       " AND P.SCREEN_NAME LIKE UPPER('"+m_screen+"%') "+
													 " AND P.DIVISION_CODE LIKE('"+m_div_num+"%') "+
		                    // " AND P.STATUS LIKE('"+m_status+"%')  "+
		                       " AND  P.ACTION_DATE = ('"+m_ac_date+"') ");
	                      // " AND ( P.EFF_VAL_DATE BETWEEN '"+m_eff_date+"'  AND '"+m_eff_to_date+"' ) ");
			}else
			{
			rs = stmt.executeQuery(" SELECT  P.FOLLOW_UP_NO,P.ID_NO,P.CATEGORY_NAME,P.EFF_VAL_DATE,NVL(P.ACTION_TAKEN,'-')AS ACTION_TAKEN,NVL(P.ENT_REMARKS,'-') AS ENTER_REMARKS,NVL(P.REMARKS,'-')AS REMARKS,P.ENT_USER "+
		                         " FROM "+
		                         " (SELECT  A.FOLLOW_UP_NO,A.ID_NO,B.CATEGORY_NAME,TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY')AS EFF_VAL_DATE,A.ACTION_TAKEN,A.ACTION_TOBE_TAKEN,A.ACTION_SET_FOR,A.SCREEN_NAME,A.DIVISION_CODE,A.STATUS,A.ENT_REMARKS,A.REMARKS,A.ENT_USER,TO_CHAR(A.ACTION_DATE,'DD-MM-YYYY')AS ACTION_DATE "+
		                         " FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A, "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY B "+
		                         " WHERE A.ID_NO=B.CATEGORY_CODE )P "+
		                         " WHERE "+
		                         " P.ID_NO LIKE ('"+m_id_num+"%')"+ 
		                         " AND  P.ACTION_SET_FOR LIKE UPPER('"+m_ac_set+"%') "+
		                         " AND P.SCREEN_NAME LIKE UPPER('"+m_screen+"%') "+
													   " AND P.DIVISION_CODE LIKE('"+m_div_num+"%') "+
		                         " AND P.STATUS LIKE('"+m_status+"%')  "+
		                         " AND  P.ACTION_DATE = ('"+m_ac_date+"') ");
	                      //	 " AND ( P.EFF_VAL_DATE BETWEEN '"+m_eff_date+"'  AND '"+m_eff_to_date+"' ) ");
			}
		}			
		 else if (m_chkval.trim().equals("N4")){
			if (m_state.trim().equals("ALL")){
		
		rs = stmt.executeQuery(" SELECT  P.FOLLOW_UP_NO,P.ID_NO,P.CATEGORY_NAME,P.EFF_VAL_DATE,NVL(P.ACTION_TAKEN,'-')AS ACTION_TAKEN,NVL(P.ENT_REMARKS,'-') AS ENTER_REMARKS,NVL(P.REMARKS,'-')AS REMARKS,P.ENT_USER "+
		                       " FROM "+
		                       " (SELECT  A.FOLLOW_UP_NO,A.ID_NO,B.CATEGORY_NAME,TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY')AS EFF_VAL_DATE,A.ACTION_TAKEN,A.ACTION_TOBE_TAKEN,A.ACTION_SET_FOR,A.SCREEN_NAME,A.DIVISION_CODE,A.STATUS,A.ENT_REMARKS,A.REMARKS,A.ENT_USER,TO_CHAR(A.ACTION_DATE,'DD-MM-YYYY')AS ACTION_DATE "+
		                       " FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A, "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY B "+
		                       " WHERE A.ID_NO=B.CATEGORY_CODE )P "+
		                       " WHERE "+
		                       " P.ID_NO LIKE ('"+m_id_num+"%')"+ 
		                       " AND  P.ACTION_SET_FOR LIKE UPPER('"+m_ac_set+"%') "+
		                       " AND P.SCREEN_NAME LIKE UPPER('"+m_screen+"%') "+		
		                       " AND P.DIVISION_CODE LIKE('"+m_div_num+"%') "+
		                    // " AND P.STATUS LIKE('"+m_status+"%')  "+
		                       " OR  P.ACTION_DATE = ('"+m_ac_date+"') "+
		                       " OR ( P.EFF_VAL_DATE BETWEEN '"+m_eff_date+"'  AND '"+m_eff_to_date+"' ) ");
			}else
			{
			rs = stmt.executeQuery(" SELECT  P.FOLLOW_UP_NO,P.ID_NO,P.CATEGORY_NAME,P.EFF_VAL_DATE,NVL(P.ACTION_TAKEN,'-')AS ACTION_TAKEN,NVL(P.ENT_REMARKS,'-') AS ENTER_REMARKS,NVL(P.REMARKS,'-')AS REMARKS,P.ENT_USER "+
		                         " FROM "+
		                         " (SELECT  A.FOLLOW_UP_NO,A.ID_NO,B.CATEGORY_NAME,TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY')AS EFF_VAL_DATE,A.ACTION_TAKEN,A.ACTION_TOBE_TAKEN,A.ACTION_SET_FOR,A.SCREEN_NAME,A.DIVISION_CODE,A.STATUS,A.ENT_REMARKS,A.REMARKS,A.ENT_USER,TO_CHAR(A.ACTION_DATE,'DD-MM-YYYY')AS ACTION_DATE "+
		                         " FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A, "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY B "+
		                         " WHERE A.ID_NO=B.CATEGORY_CODE )P "+
		                         " WHERE "+
		                         " P.ID_NO LIKE ('"+m_id_num+"%')"+ 
		                         " AND  P.ACTION_SET_FOR LIKE UPPER('"+m_ac_set+"%') "+
		                         " AND P.SCREEN_NAME LIKE UPPER('"+m_screen+"%') "+		
		                         " AND P.DIVISION_CODE LIKE('"+m_div_num+"%') "+
		                         " AND P.STATUS LIKE('"+m_status+"%')  "+
		                         " OR  P.ACTION_DATE = ('"+m_ac_date+"') "+
		                         " OR ( P.EFF_VAL_DATE BETWEEN '"+m_eff_date+"'  AND '"+m_eff_to_date+"' ) ");
			}
		}
		else if (m_chkval.trim().equals("N5")){
		if (m_state.trim().equals("ALL")){
		
		rs = stmt.executeQuery(" SELECT  P.FOLLOW_UP_NO,P.ID_NO,P.CATEGORY_NAME,P.EFF_VAL_DATE,NVL(P.ACTION_TAKEN,'-')AS ACTION_TAKEN,NVL(P.ENT_REMARKS,'-') AS ENTER_REMARKS,NVL(P.REMARKS,'-')AS REMARKS,P.ENT_USER "+
		                       " FROM "+
		                       " (SELECT  A.FOLLOW_UP_NO,A.ID_NO,B.CATEGORY_NAME,TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY')AS EFF_VAL_DATE,A.ACTION_TAKEN,A.ACTION_TOBE_TAKEN,A.ACTION_SET_FOR,A.SCREEN_NAME,A.DIVISION_CODE,A.STATUS,A.ENT_REMARKS,A.REMARKS,A.ENT_USER,TO_CHAR(A.ACTION_DATE,'DD-MM-YYYY')AS ACTION_DATE "+
		                       " FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A, "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY B "+
		                       " WHERE A.ID_NO=B.CATEGORY_CODE )P "+
		                       " WHERE "+
		                       " P.ID_NO LIKE ('"+m_id_num+"%')"+ 
		                       " AND  P.ACTION_SET_FOR LIKE UPPER('"+m_ac_set+"%') "+
		                       " AND P.SCREEN_NAME LIKE UPPER('"+m_screen+"%') "+
		                       " AND P.DIVISION_CODE LIKE('"+m_div_num+"%') "+
		                     //" AND P.STATUS LIKE('"+m_status+"%')  "+
		                       " AND  P.ACTION_DATE = ('"+m_ac_date+"') ");
				}else
				{
				rs = stmt.executeQuery(" SELECT  P.FOLLOW_UP_NO,P.ID_NO,P.CATEGORY_NAME,P.EFF_VAL_DATE,NVL(P.ACTION_TAKEN,'-')AS ACTION_TAKEN,NVL(P.ENT_REMARKS,'-') AS ENTER_REMARKS,NVL(P.REMARKS,'-')AS REMARKS,P.ENT_USER "+
		                           " FROM "+
		                           " (SELECT  A.FOLLOW_UP_NO,A.ID_NO,B.CATEGORY_NAME,TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY')AS EFF_VAL_DATE,A.ACTION_TAKEN,A.ACTION_TOBE_TAKEN,A.ACTION_SET_FOR,A.SCREEN_NAME,A.DIVISION_CODE,A.STATUS,A.ENT_REMARKS,A.REMARKS,A.ENT_USER,TO_CHAR(A.ACTION_DATE,'DD-MM-YYYY')AS ACTION_DATE "+
		                           " FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A, "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY B "+
		                           " WHERE A.ID_NO=B.CATEGORY_CODE )P "+
		                           " WHERE "+
		                           " P.ID_NO LIKE('"+m_id_num+"%')"+ 
		                           " AND  P.ACTION_SET_FOR LIKE UPPER('"+m_ac_set+"%') "+
		                           " AND P.SCREEN_NAME LIKE UPPER('"+m_screen+"%') "+
		                           " AND P.DIVISION_CODE LIKE('"+m_div_num+"%') "+
		                           " AND P.STATUS LIKE('"+m_status+"%')  "+
		                           " AND  P.ACTION_DATE = ('"+m_ac_date+"') ");
				}
			}
					
      boolean more = rs.next();
         
      out.println("<HTML><HEAD><TITLE>Follow Up Details</TITLE></HEAD>");
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
      out.println("<BODY   LEFTMARGIN='0' TOPMARGIN='0'><BR>");
      
      out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'><TR><TD><CENTER><B>Follow  Up  Details</B></TD></TR></TABLE><BR><BR>");
      out.println("<TABLE WIDTH='100%'   border='1'>");
      out.println("<TR class='pdn_txtpos2' align='center'>");
			//#8fb382 class='pdn_txtpos2'
			
      out.println("<TR class='pdn_txtpos2' align='center'> "+			
            "<B>"+
            "<TD WIDTH='10%' STYLE='{text-align:left;}'><B>Follow Up Code</TD>"+
            "<TD WIDTH='10%' STYLE='{text-align:left;}'><B>Number</TD>"+
            "<TD WIDTH='20%' STYLE='{text-align:left;}'><B>Action Type</TD>"+
            "<TD WIDTH='11%' STYLE='{text-align:left;}'><B>Effective Date</TD>"+
            "<TD WIDTH='15%' STYLE='{text-align:left;}'><B>Action Taken</TD>"+
            "<TD WIDTH='15%' STYLE='{text-align:left;}'><B>Enter Remarks</TD>"+
						"<TD WIDTH='14%' STYLE='{text-align:left;}'><B>Remarks</TD>"+
					  "<TD WIDTH='12%' STYLE='{text-align:left;}'><B>Entered User</TD></TR></TABLE>");  
          
         
      while (more){
        
        
        out.println("<TABLE BORDER='0' WIDTH='100%' STYLE='{ color: green; font: 9pt arial;}'><B>");
        out.println("<TR><TD WIDTH='10%' STYLE='{color:black; font: 8pt arial; text-align:left;}' >"+rs.getString(1)+"</TD>");
        out.println("<TD WIDTH='10%' STYLE='{color:black; font: 8pt arial; text-align:left;}' >"+rs.getString(2)+"</TD>");
        out.println("<TD WIDTH='20%' STYLE='{color:black; font: 8pt arial; text-align:left;}' >"+rs.getString(3)+"</TD>");
        out.println("<TD WIDTH='11%' STYLE='{color:black; font: 8pt arial; text-align:left;}' >"+rs.getString(4)+"</TD>");
        out.println("<TD WIDTH='15%' STYLE='{color:black; font: 8pt arial; text-align:left;}' >"+rs.getString(5)+"</TD>");
        out.println("<TD WIDTH='15%' STYLE='{color:black; font: 8pt arial; text-align:left;}' >"+rs.getString(6)+"</TD>");
        out.println("<TD WIDTH='14%' STYLE='{color:black; font: 8pt arial; text-align:left;}' >"+rs.getString(7)+"</TD>");
        out.println("<TD WIDTH='12%' STYLE='{color:black; font: 8pt arial; text-align:left;}' >"+rs.getString(8)+"</TD>");
        out.println("</TR></TABLE>");
        
        more = rs.next(); 
        
        }
            
      out.println("</BODY></HTML>");
			
			}
			
		
				
			else {
			    out.println("Undefined");
			}
			
      out.close();
			conn.close();
			this.destroy();
			
			
			}
			catch (Exception e) {
				try {
						conn.close();
				}catch (Exception eti) {}
			
					ByteArrayOutputStream ostr = new ByteArrayOutputStream();
					e.printStackTrace(new PrintWriter(ostr));
			
					ServletOutputStream out = res.getOutputStream();
					out.println(ostr.toString());
      		out.close();
			
			}
	}
}

