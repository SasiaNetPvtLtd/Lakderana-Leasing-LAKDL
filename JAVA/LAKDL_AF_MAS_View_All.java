import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
//import CLAMF_sn_methods;
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_MAS_View_All extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt1;
	CallableStatement callstmt;
	java.text.NumberFormat nf;
	java.text.NumberFormat nf1;
    
  public ResultSet rs,rs1;
	public String m_chksql;
	
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
			//res.setContentType("text/xml");
			res.setContentType("text/html");
			res.setHeader("Cache-Control", "No-Cache");
     	res.setDateHeader("Expires", 0);

			ServletOutputStream out = res.getOutputStream();
      String m_html_client_url=m_sn_methods.html_client_url.trim();
			m_chksql=req.getParameter("chksql");
			stmt=conn.createStatement();
		
			//m_prime_chk_
			
			 
				
				
			
			
			

			//if (m_chksql.trim().equals("idle")) {
			//	out.println("idle");
			//}	
			
			
			//	String m_val = req.getParameter("data_val").trim();
			//	String m_status = req.getParameter("ac_status");
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>System Administration - Location</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 

			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"\">"); 
			out.println("<FORM NAME='Form1'>");
			
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
			out.println("<tr> "); 
			out.println("<td height='30' class='pdn_mainHD'>Asset Financing System</td>"); 
			out.println("</tr>"); 
			out.println("<tr> "); 
			out.println("<td height='1'><img src='spacer.gif' width='1' height='1'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td style='height: 327px'>"); 
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' height='100%' width='100%'>   "); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Location</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onClick=\"window.close()\" value=\"Close\" ></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  

      if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_district")){
			 out.println("<TABLE BORDER='1' WIDTH='100%' BGCOLOR='white' STYLE='{ color: black; font: 9pt arial;}'><B>");
      
      out.println("<TR BGCOLOR='silver' ><STYLE='{text-align:left;}'>"+
            "<TD WIDTH='15%' STYLE='{text-align:left;}'><B>DISTRICT CODE</TD>"+
            "<TD WIDTH='15%' STYLE='{text-align:left;}'><B>DISTRICT DESCRIPTION</TD>"+
            "<TD WIDTH='15%' STYLE='{text-align:left;}'><B>PROVINCE CODE</TD>"+
						"<TD WIDTH='15%' STYLE='{text-align:left;}'><B>DEFAULT VALUE</TD>"+
            "<TD WIDTH='15%' STYLE='{text-align:left;}'><B>ACTIVE_STATUS</TD></TR></TABLE>");
						//"<TD WIDTH='*%' STYLE='{text-align:left;}'></TD></TR></TABLE>");




			out.println("<table align='center' width='100%' STYLE='{ color: silver; font: 10pt arial;}' class='table'>"); 

			out.println("<HR>");		
				
			rs= stmt.executeQuery ("SELECT  DISTRICT_CODE,DISTRICT_DESC,PROVINCE_CODE,DECODE(DEFAULT_VALUE,'Y','YES','N','NO '),DECODE(ACTIVE_STATUS,'Y','YES','N','NO ')  FROM LAKDL.AF_CO_MAS_DISTRICT ");
				
					
			boolean more = rs.next();
			
      while (more){
        
        
      // out.println("<TABLE BORDER=\"1\" WIDTH='100%' STYLE='{ color: silver; font: 9pt arial;}'><B>");
        out.println("<TR><TD WIDTH='15%' STYLE='{color:black; text-align:left;}'><B>"+rs.getString(1)+"</TD>");
            out.println("<TD WIDTH='15%' STYLE='{color:black; text-align:left;}' ><B>"+rs.getString(2)+"</TD>");
            out.println("<TD WIDTH='15%' STYLE='{color:black; text-align:left;}' ><B>"+rs.getString(3)+"</TD>");
            out.println("<TD WIDTH='15%' STYLE='{color:black; text-align:left;}' ><B>"+rs.getString(4)+"</TD>");
            out.println("<TD WIDTH='15%' STYLE='{color:black; text-align:left;}' ><B>"+rs.getString(5)+"</TD>");
           
        out.println("</TR>");
        
        more = rs.next(); 
        
        }
   
			
			
			
		/*	 out.println("<TABLE BORDER='1' WIDTH='100%' BGCOLOR='white' STYLE='{ color: black; font: 9pt arial;}'><B>");
      
      out.println("<TR BGCOLOR='silver' ><TD WIDTH='8%' STYLE='{text-align:left;}'>"+
            "<B>Client Code </TD>"+
            "<TD WIDTH='15%'><B>DISTRICT_CODE</TD>"+
            "<TD WIDTH='15%' STYLE='{text-align:left;}'><B>DISTRICT_DESC</TD>"+
            "<TD WIDTH='10%' STYLE='{text-align:left;}'><B>PROVINCE_CODE</TD>"+
            "<TD WIDTH='12%' STYLE='{text-align:left;}'><B>DEFAULT_VALUE</TD></TR></TABLE>");  */
          
    /*  out.println("<BR><TABLE BGCOLOR='silver' WIDTH='100%' STYLE='{ color: black; font: 10pt arial;text-align:left;}'><TR><TD><B>Active Client Details</B></TD></TR></TABLE><BR>");         
      //out.println("<TD><DIV ID='"+m_table+"'</TD>");
      
			boolean more = rs.next();
			
      while (more){
        
        
        out.println("<TABLE BORDER='0' WIDTH='100%' STYLE='{ color: silver; font: 9pt arial;}'><B>");
        out.println("<TR><TD WIDTH='8%' STYLE='{color:black; text-align:left;}' >"+rs.getString(1)+"</TD>");
        out.println("<TD WIDTH='15%' STYLE='{color:black; text-align:left;}' >"+rs.getString(2)+"</TD>");
        out.println("<TD WIDTH='15%' STYLE='{color:black; text-align:left;}' >"+rs.getString(3)+"</TD>");
        out.println("<TD WIDTH='10%' STYLE='{color:black; text-align:left;}' >"+rs.getString(4)+"</TD>");
        out.println("<TD WIDTH='15%' STYLE='{color:black; text-align:left;}' >"+rs.getString(5)+"</TD>");
        out.println("<TD WIDTH='15%' STYLE='{color:black; text-align:left;}' >"+rs.getString(6)+"</TD>");
        out.println("<TD WIDTH='10%' STYLE='{color:black; text-align:left;}' >"+rs.getString(7)+"</TD>");
        out.println("<TD WIDTH='12%' STYLE='{color:black; text-align:left;}' >"+rs.getString(8)+"</TD>");
        out.println("</TR><HR></TABLE>");
        
        more = rs.next(); 
        
        }*/
        
        }

			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		/*	  rs = stmt.executeQuery("SELECT C_CODE,C_NAME,NIC,TEL_NO,NVL(ADD_ID,'N/A'),NVL(ADD_STREET,'N/A'),NVL(ADD_CITY,'N/A'),NVL(COUNTRY,'N/A') FROM "+m_schema_name+".TRAINEE_DN_REF_CLIENT_NUWA WHERE IS_ACTIVE='Y' ORDER BY C_CODE ");
       
      boolean more = rs.next();
      
         
      out.println("<HTML><HEAD><TITLE>Client Entry Details</TITLE></HEAD>");
      out.println("<BODY LEFTMARGIN='0' TOPMARGIN='0'><BR>");
      out.println("<DIV align=center><img src='"+m_html_client_url+"/logo.gif'></DIV><br><hr>");
      out.println("<TABLE BGCOLOR='silver' WIDTH='100%' STYLE='{ color: black; font: 12pt arial;}'><TR><TD><CENTER><B>VIEW CLIENT DETAILS</B></TD></TR></TABLE><BR><BR>");
      out.println("<TABLE BORDER='1' WIDTH='100%' BGCOLOR='white' STYLE='{ color: black; font: 9pt arial;}'><B>");
      
      out.println("<TR BGCOLOR='silver' ><TD WIDTH='8%' STYLE='{text-align:left;}'>"+
            "<B>Client Code </TD>"+
            "<TD WIDTH='15%' STYLE='{text-align:left;}'><B>Client Name</TD>"+
            "<TD WIDTH='15%' STYLE='{text-align:left;}'><B>NIC</TD>"+
            "<TD WIDTH='10%' STYLE='{text-align:left;}'><B>Telephone</TD>"+
            "<TD WIDTH='15%' STYLE='{text-align:left;}'><B>Address Identification</TD>"+
            "<TD WIDTH='15%' STYLE='{text-align:left;}'><B>Address Street</TD>"+
            "<TD WIDTH='10%' STYLE='{text-align:left;}'><B>Address City</TD>"+
            "<TD WIDTH='12%' STYLE='{text-align:left;}'><B>Country</TD></TR></TABLE>");  
          
      out.println("<BR><TABLE BGCOLOR='silver' WIDTH='100%' STYLE='{ color: black; font: 10pt arial;text-align:left;}'><TR><TD><B>Active Client Details</B></TD></TR></TABLE><BR>");         
      out.println("<TD><DIV ID='"+m_table+"'</TD>");
             
      while (more){
        
        
        out.println("<TABLE BORDER='0' WIDTH='100%' STYLE='{ color: silver; font: 9pt arial;}'><B>");
        out.println("<TR><TD WIDTH='8%' STYLE='{color:black; text-align:left;}' >"+rs.getString(1)+"</TD>");
        out.println("<TD WIDTH='15%' STYLE='{color:black; text-align:left;}' >"+rs.getString(2)+"</TD>");
        out.println("<TD WIDTH='15%' STYLE='{color:black; text-align:left;}' >"+rs.getString(3)+"</TD>");
        out.println("<TD WIDTH='10%' STYLE='{color:black; text-align:left;}' >"+rs.getString(4)+"</TD>");
        out.println("<TD WIDTH='15%' STYLE='{color:black; text-align:left;}' >"+rs.getString(5)+"</TD>");
        out.println("<TD WIDTH='15%' STYLE='{color:black; text-align:left;}' >"+rs.getString(6)+"</TD>");
        out.println("<TD WIDTH='10%' STYLE='{color:black; text-align:left;}' >"+rs.getString(7)+"</TD>");
        out.println("<TD WIDTH='12%' STYLE='{color:black; text-align:left;}' >"+rs.getString(8)+"</TD>");
        out.println("</TR><HR></TABLE>");
        
        more = rs.next(); 
        
        }
        
        out.println("<HR>");
        
      rs2 = stmt2.executeQuery("SELECT C_CODE,C_NAME,NIC,TEL_NO,NVL(ADD_ID,'N/A'),NVL(ADD_STREET,'N/A'),NVL(ADD_CITY,'N/A'),NVL(COUNTRY,'N/A') FROM "+m_schema_name+".TRAINEE_DN_REF_CLIENT_NUWA WHERE IS_ACTIVE='N' ORDER BY C_CODE ");
       
        boolean more2 = rs2.next();
        
        out.println("<BR><TABLE BGCOLOR='silver' WIDTH='100%' STYLE='{ color: black; font: 10pt arial;text-align:left;}'><TR><TD><B>Deactive Client Details</B></TD></TR></TABLE><BR>"); 
        
        while (more2){
        
        out.println("<TABLE BORDER='0' WIDTH='100%' STYLE='{ color: silver; font: 9pt arial;}'><B>");
        out.println("<TR><TD WIDTH='8%' STYLE='{color:black; text-align:left;}' >"+rs2.getString(1)+"</TD>");
        out.println("<TD WIDTH='15%' STYLE='{color:black; text-align:left;}' >"+rs2.getString(2)+"</TD>");
        out.println("<TD WIDTH='15%' STYLE='{color:black; text-align:left;}' >"+rs2.getString(3)+"</TD>");
        out.println("<TD WIDTH='10%' STYLE='{color:black; text-align:left;}' >"+rs2.getString(4)+"</TD>");
        out.println("<TD WIDTH='15%' STYLE='{color:black; text-align:left;}' >"+rs2.getString(5)+"</TD>");
        out.println("<TD WIDTH='15%' STYLE='{color:black; text-align:left;}' >"+rs2.getString(6)+"</TD>");
        out.println("<TD WIDTH='10%' STYLE='{color:black; text-align:left;}' >"+rs2.getString(7)+"</TD>");
        out.println("<TD WIDTH='12%' STYLE='{color:black; text-align:left;}' >"+rs2.getString(8)+"</TD>");
        out.println("</TR><HR></TABLE>");
        
        more2 = rs2.next(); 
        
        }
      
            
      out.println("<BODY></HTML>");*/
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		/*	//---------------------ID  			:Process Stage Creation Process---------------------------------//
			//---------------------Purpose 	:Process Stage-----------------------------------
			//---------------------Name     :Nuwan---------------------------------------------------
			//---------------------Date     :02-08-06--------------------------------------------------
			
			
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_process_stage")){
		
		String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
							
			rs= stmt.executeQuery ("SELECT STAGE_CODE,DESCRIPTION,DIVISION_CODE,DEFAULT_VALUE FROM LAKDL.AF_CO_MAS_PROCESS_STAGE "+
			" WHERE UPPER(STAGE_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
			
 
    
  

			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_score_sub_category")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");	
				
				rs= stmt.executeQuery ("SELECT  SCORE_SUB_CODE,SCORE_CODE,DESCRIPTION,DISPALY_POSITION  FROM LAKDL.AF_CR_MAS_SCORE_SUB_CATEGORY "+
				" WHERE UPPER(SCORE_SUB_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_score_model_creation")){
				String m_val = req.getParameter("data_val");
				
				rs= stmt.executeQuery ("SELECT  SCORE_MODEL_CODE  FROM LAKDL.AF_CR_MAS_SCORE_MODEL "+
				" WHERE UPPER(SCORE_MODEL_CODE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
		  //---------------------ID  			:1.4  City Creation Process---------------------------------//
			//---------------------Purpose 	:To validate the City Code-----------------------------------
			//---------------------Name     :Delanjali---------------------------------------------------
			//---------------------Date     :2006 07 19--------------------------------------------------
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_city")){
				String m_val = req.getParameter("data_val").trim();
				//String m_val2 = req.getParameter("data_val2");
				String m_status = req.getParameter("ac_status");	
				 
				rs= stmt.executeQuery ("SELECT CITY_CODE,CITY_DESC,DISTRICT_CODE,DEFAULT_VALUE FROM LAKDL.AF_CO_MAS_CITY "+
				" WHERE (UPPER(CITY_CODE)=UPPER('"+m_val+"'))  "+
				"	AND ACTIVE_STATUS =UPPER('"+m_status+"')");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R1>"+rs.getString(2)+"</R1>");
					out.print("<R1>"+rs.getString(3)+"</R1>");
					out.print("<R1>"+rs.getString(4)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			//LAKDL_AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_city1&data_val2
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_city1")){
				//String m_val = req.getParameter("data_val");
				String m_val2 = req.getParameter("data_val2").trim();
				
				rs= stmt.executeQuery ("SELECT  DISTRICT_CODE FROM LAKDL.AF_CO_MAS_CITY "+
				" WHERE UPPER(DISTRICT_CODE)=UPPER('"+m_val2+"'))  ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			//---------------------ID  			:1.3 District Creation Process---------------------------------//
			//---------------------Purpose 	:To validate the District Code-----------------------------------
			//---------------------Name     :N.V.P.Chandana---------------------------------------------------
			//---------------------Date     :2006-07-20--------------------------------------------------
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_district")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT  DISTRICT_CODE,DISTRICT_DESC,PROVINCE_CODE,DEFAULT_VALUE  FROM LAKDL.AF_CO_MAS_DISTRICT ");
				//" WHERE UPPER(DISTRICT_CODE)=UPPER('"+m_val+"')AND ACTIVE_STATUS='"+m_status+"' ");
				
				
			
			out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_district1")){
				String m_val = req.getParameter("data_val").trim();
								
				rs= stmt.executeQuery ("SELECT  DISTRICT_CODE FROM LAKDL.AF_CO_MAS_DISTRICT "+
				" WHERE UPPER(DISTRICT_CODE)=UPPER('"+m_val+"')");
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
				//---------------------ID  			:1.15 Business Sector Creation Process---------------------------------//
			  //---------------------Purpose 	:To validate the Business Sector Code-----------------------------------
			 //---------------------Name     :N.V.P.Chandana---------------------------------------------------
			 //---------------------Date     :20-07-2006--------------------------------------------------
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_business_sector")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT SECTOR_CODE,DESCRIPTION,DEFAULT_VALUE  FROM LAKDL.AF_CO_MAS_BUSINESS_SECTOR "+
				" WHERE UPPER(SECTOR_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_business_sector1")){
				String m_val = req.getParameter("data_val").trim();
								
				rs= stmt.executeQuery ("SELECT SECTOR_CODE FROM LAKDL.AF_CO_MAS_BUSINESS_SECTOR "+
				" WHERE UPPER(SECTOR_CODE)=UPPER('"+m_val+"')");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			
				//---------------------ID  			:1.16 Business Sub Sector Creation Process---------------------------------//
			  //---------------------Purpose 	:To validate the Sub Business Sector Code-----------------------------------
			  //---------------------Name     :N.V.P.Chandana---------------------------------------------------
			  //---------------------Date     :21-07-2006--------------------------------------------------
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_sub_business_sectors")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT SUB_CODE,SECTOR_CODE,DESCRIPTION,DEFAULT_VALUE  FROM LAKDL.AF_CO_MAS_SUB_BUSINESS_SECTORS "+
				" WHERE UPPER(SUB_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_sub_business_sectors2")){
				String m_val = req.getParameter("data_val").trim();
								
				rs= stmt.executeQuery ("SELECT SUB_CODE FROM LAKDL.AF_CO_MAS_SUB_BUSINESS_SECTORS "+
				" WHERE UPPER(SUB_CODE)=UPPER('"+m_val+"')");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			
			//---------------------ID  			:1.17 Charges Creation Process---------------------------------//
			//---------------------Purpose 	:To validate the Type Code-----------------------------------
			//---------------------Name     :N.V.P.Chandana---------------------------------------------------
			//---------------------Date     :20-07-2006--------------------------------------------------
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_charges")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT TYPE_CODE,DESCRIPTION,DEFAULT_VALUE  FROM LAKDL.AF_CO_MAS_CHARGES "+
				" WHERE UPPER(TYPE_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_charges1")){
				String m_val = req.getParameter("data_val").trim();
								
				rs= stmt.executeQuery ("SELECT TYPE_CODE FROM LAKDL.AF_CO_MAS_CHARGES "+
				" WHERE UPPER(TYPE_CODE)=UPPER('"+m_val+"')");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			
		  //---------------------  ID  		:1.18 Applicable Charges Creation Process---------------------------------//
			//---------------------Purpose 	:To validate the Type Code-----------------------------------
			//---------------------Name     :N.V.P.Chandana---------------------------------------------------
			//---------------------Date     :20-07-2006--------------------------------------------------
			
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_applicable_charges")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT ITEM_SUB_CAT,SUB_TYPE_CODE,FUAL_TYPE_CODE,to_Char(FROM_DATE),to_Char(TO_DATE),AMOUNT,PERCENTAGE,DEFAULT_VALUE  FROM LAKDL.AF_CO_MAS_CHARGERS_APPLICABLE "+
				" WHERE UPPER(ITEM_SUB_CAT)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");
					out.print("<R6>"+rs.getString(6)+"</R6>");
					out.print("<R7>"+rs.getString(7)+"</R7>");
					out.print("<R8>"+rs.getString(8)+"</R8>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");

			}
			
			//---------------------  ID  		:1.18 Applicable Charges Creation Process---------------------------------//
			//---------------------Purpose 	:To validate the Item Sub Code-----------------------------------
			//---------------------Name     :N.V.P.Chandana---------------------------------------------------
			//---------------------Date     :21-08-2006--------------------------------------------------
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_item_sub_category1")){
			
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status");	
			
			rs= stmt.executeQuery ("SELECT A.ITEM_SUB_CAT "+
       " FROM LAKDL.AF_CO_MAS_CHARGERS_APPLICABLE A  "+
       "  WHERE UPPER(A.ITEM_SUB_CAT)=UPPER('"+m_val+"')");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
			
			
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_applicable_charges1")){
				String m_val = req.getParameter("data_val").trim();
				
				
				rs= stmt.executeQuery ("SELECT ITEM_SUB_CAT FROM LAKDL.AF_CO_MAS_CHARGERS_APPLICABLE "+
				" WHERE UPPER(ITEM_SUB_CAT)=UPPER('"+m_val+"')");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");

			}
				
			
			
			//---------------------  ID  		:1.18 Applicable Charges Creation Process---------------------------------//
			//---------------------Purpose 	:To validate the Sub Type Code-----------------------------------
			//---------------------Name     :N.V.P.Chandana---------------------------------------------------
			//---------------------Date     :20-07-2006--------------------------------------------------
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_Sub_Type_Code")){
				String m_val = req.getParameter("data_val").trim();
				
				rs= stmt.executeQuery ("SELECT SUB_TYPE_CODE  FROM LAKDL.AF_CO_MAS_SUB_CHARGES "+
				" WHERE UPPER(SUB_TYPE_CODE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");

			}
			
			//---------------------  ID  		:1.18 Applicable Charges Creation Process---------------------------------//
			//---------------------Purpose 	:To validate the Sub Type Code(Check New Valide Codes)-----------------------------------
			//---------------------Name     :N.V.P.Chandana---------------------------------------------------
			//---------------------Date     :21-08-2006--------------------------------------------------
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_applicable_charges2")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT ITEM_SUB_CAT FROM LAKDL.AF_CO_MAS_ITEM_SUB_CATEGORY "+
				" WHERE UPPER(ITEM_SUB_CAT)=UPPER('"+m_val+"')AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");

			}
			
			
			
			
			
			//---------------------  ID  		:1.18 Applicable Charges Creation Process---------------------------------//
			//---------------------Purpose 	:To validate the Fual Type Code-----------------------------------
			//---------------------Name     :N.V.P.Chandana---------------------------------------------------
			//---------------------Date     :20-07-2006--------------------------------------------------
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_fual")){
			
			String m_val = req.getParameter("data_val").trim();
				
			rs= stmt.executeQuery ("SELECT CODE FROM LAKDL.AF_CO_MAS_FUEL_TYPE"+
			" WHERE UPPER(CODE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
			
			
		
		//---------------------  ID  			:1.19 Condition of Asset Creation Process ---------------------------------//
			//---------------------Purpose 	:To validate the Asset Code-----------------------------------
			//---------------------Name     :N.V.P.Chandana---------------------------------------------------
			//---------------------Date     :24-07-2006--------------------------------------------------
				
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_condition_of_asset")){
			
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status");
				
			rs= stmt.executeQuery ("SELECT CODE,DESCRIPTION,DEFAULT_VALUE FROM LAKDL.AF_CO_MAS_CONDITION_OF_ASSET"+
			" WHERE UPPER(CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_condition_of_asset1")){
			
			String m_val = req.getParameter("data_val").trim();
							
			rs= stmt.executeQuery ("SELECT CODE FROM LAKDL.AF_CO_MAS_CONDITION_OF_ASSET"+
			" WHERE UPPER(CODE)=UPPER('"+m_val+"')");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
		
		
		  //---------------------  ID  			:1.40 Make Creation Process ---------------------------------//
			//---------------------Purpose 	:To validate the Make Code-----------------------------------
			//---------------------Name     :N.V.P.Chandana---------------------------------------------------
			//---------------------Date     :24-07-2006--------------------------------------------------
		
		
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_make_creation")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT MAKE_CODE,MAKE_DESC,ITEM_SUB_CAT,DEFAULT_VALUE  FROM LAKDL.AF_CO_MAS_MAKE "+
				" WHERE UPPER(MAKE_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");

			}
		
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_make_creation1")){
				String m_val = req.getParameter("data_val").trim();
								
				rs= stmt.executeQuery ("SELECT MAKE_CODE FROM LAKDL.AF_CO_MAS_MAKE "+
				" WHERE UPPER(MAKE_CODE)=UPPER('"+m_val+"')");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");

			}
		
		
		
		
		 //---------------------  ID  		:1.40 Make Creation Process ---------------------------------//
			//---------------------Purpose 	:To validate the Sub Category Code-----------------------------------
			//---------------------Name     :N.V.P.Chandana---------------------------------------------------
			//---------------------Date     :24-07-2006--------------------------------------------------
		
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_Sub_Cat_Code")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT ITEM_SUB_CAT  FROM LAKDL.AF_CO_MAS_ITEM_SUB_CATEGORY "+
				" WHERE UPPER(ITEM_SUB_CAT)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");

			}
		
		
		
		
		
		
		
		
		  //---------------------  ID  			:1.41 Model Creation Process ---------------------------------//
			//---------------------Purpose 	:To validate the Model Code-----------------------------------
			//---------------------Name     :N.V.P.Chandana---------------------------------------------------
			//---------------------Date     :24-07-2006--------------------------------------------------
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_model_creation")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT MODEL_CODE,DESCRIPTION,MAKE_CODE,FUEL_TYPE,TAX_RATE,TAX_FOR_LEASE,DEFAULT_VALUE  FROM LAKDL.AF_CO_MAS_MODEL "+
				" WHERE UPPER(MODEL_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");
					out.print("<R6>"+rs.getString(6)+"</R6>");
					out.print("<R7>"+rs.getString(7)+"</R7>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
		}
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_model_creation1")){
				String m_val = req.getParameter("data_val").trim();
								
				rs= stmt.executeQuery ("SELECT MODEL_CODE FROM LAKDL.AF_CO_MAS_MODEL "+
				" WHERE UPPER(MODEL_CODE)=UPPER('"+m_val+"')");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
		}
		
		//--------------------- ID  			:1.42 Vendor Creation Process ---------------------------------//
		//---------------------Purpose 	  :To validate the Vendor Code-----------------------------------
		//---------------------Name       :N.V.P.Chandana---------------------------------------------------
		//---------------------Date       :25-07-2006--------------------------------------------------
		
				
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_vendor_creation")){
				String m_val = req.getParameter("data_val").trim();
				
				rs= stmt.executeQuery ("SELECT VENDOR_CODE FROM LAKDL.AF_CO_MAS_VENDORS "+
				" WHERE UPPER(VENDOR_CODE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
		//--------------------- ID  			:1.43 Customer Category Creation Process ---------------------------------//
		//---------------------Purpose 	  :To validate the Customer Category Code-----------------------------------
		//---------------------Name       :N.V.P.Chandana---------------------------------------------------
		//---------------------Date       :25-07-2006--------------------------------------------------
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_customer_category")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT CAT_TYPE_CODE,DESCRIPTION,DEFAULT_VALUE  FROM LAKDL.AF_MK_MAS_CUSTOMER_CATOGORY "+
				" WHERE UPPER(CAT_TYPE_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
		  }
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_customer_category1")){
				String m_val = req.getParameter("data_val").trim();
							
				rs= stmt.executeQuery ("SELECT CAT_TYPE_CODE FROM LAKDL.AF_MK_MAS_CUSTOMER_CATOGORY "+
				" WHERE UPPER(CAT_TYPE_CODE)=UPPER('"+m_val+"')");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
		  }
		
		
		
			//-------------------ID  			  :1.44 Initiation Type Creation Process ---------------------------------//
		//---------------------Purpose 	  :To validate the Initiation Type Code-----------------------------------
		//---------------------Name       :N.V.P.Chandana---------------------------------------------------
		//---------------------Date       :25-07-2006--------------------------------------------------
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_initiation_type")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT INITIATION_CODE,DESCRIPTION,DEFAULT_VALUE  FROM LAKDL.AF_MK_MAS_INITIATION_TYPE "+
				" WHERE UPPER(INITIATION_CODE)=UPPER('"+m_val+"')  AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
		  }
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_initiation_type1")){
				String m_val = req.getParameter("data_val").trim();
								
				rs= stmt.executeQuery ("SELECT INITIATION_CODE FROM LAKDL.AF_MK_MAS_INITIATION_TYPE "+
				" WHERE UPPER(INITIATION_CODE)=UPPER('"+m_val+"')");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
		  }
			
				
			//-------------------ID  			  :1.50 Document Required Creation Process ---------------------------------//
		//---------------------Purpose 	  :To validate the Document Required Code-----------------------------------
		//---------------------Name       :N.V.P.Chandana---------------------------------------------------
		//---------------------Date       :25-07-2006--------------------------------------------------
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_documents_required")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT CODE,DESCRIPTION  FROM LAKDL.AF_CO_MAS_DOCUMENTS_REQUIRED "+
				" WHERE UPPER(CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
		  }
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_documents_required1")){
				String m_val = req.getParameter("data_val").trim();
								
				rs= stmt.executeQuery ("SELECT CODE FROM LAKDL.AF_CO_MAS_DOCUMENTS_REQUIRED "+
				" WHERE UPPER(CODE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
		  }
			
			
					 
			//-------------------  ID  			  :1.51 Applicable Document Type Creation Process ---------------------------------//
		  //---------------------Purpose 	  :To validate the Applicable Document Code-----------------------------------
		  //---------------------Name       :N.V.P.Chandana---------------------------------------------------
		  //---------------------Date       :25-07-2006--------------------------------------------------
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_document_applicable")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT CODE,ENTITY_TYPE,STAGE,ITEM_CAT_CODE,ENTITY_CODE FROM LAKDL.AF_CO_MAS_DOCUMENT_APPLICABLE "+
				" WHERE UPPER(CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
		  }
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_document_applicable1")){
				String m_val = req.getParameter("data_val").trim();
							
				rs= stmt.executeQuery ("SELECT CODE FROM LAKDL.AF_CO_MAS_DOCUMENT_APPLICABLE "+
				" WHERE UPPER(CODE)=UPPER('"+m_val+"')");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				 }
				out.print("</DATA>");
		  }
		
			//-------------------  ID  			  :1.51 Applicable Document Type Creation Process ---------------------------------//
		  //---------------------Purpose 	  :To validate the Entity Type-----------------------------------
		  //---------------------Name       :N.V.P.Chandana---------------------------------------------------
		  //---------------------Date       :25-07-2006--------------------------------------------------
		
		 else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_legal_entity ")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT ENTITY_CODE  FROM LAKDL.AF_CO_MAS_LEGAL_ENTITY "+
				" WHERE UPPER(ENTITY_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
		  }
			
			
			//-------------------  ID  			  :1.51 Applicable Document Type Creation Process ---------------------------------//
		  //---------------------Purpose 	  :To validate the Stage Type-----------------------------------
		  //---------------------Name       :N.V.P.Chandana---------------------------------------------------
		  //---------------------Date       :25-07-2006--------------------------------------------------
		
		 else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_stage ")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT STAGE_CODE  FROM LAKDL.AF_CO_MAS_STAGE "+
				" WHERE UPPER(STAGE_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
		  }
		
		
		
		
		  //-------------------  ID  			  :1.51 Applicable Document Type Creation Process ---------------------------------//
		  //---------------------Purpose 	  :To validate the Stage Type-----------------------------------
		  //---------------------Name       :N.V.P.Chandana---------------------------------------------------
		  //---------------------Date       :25-07-2006--------------------------------------------------
		
		 else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_stage")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT STAGE_CODE  FROM LAKDL.AF_CO_MAS_STAGE "+
				" WHERE UPPER(STAGE_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
		  }
		
		
		 //-------------------   ID  			  :1.53 Account Code Creation Process  ---------------------------------//
		  //---------------------Purpose 	  :To validate the Account Code Type-----------------------------------
		  //---------------------Name       :N.V.P.Chandana---------------------------------------------------
		  //---------------------Date       :25-07-2006--------------------------------------------------
		
		
		
		
		 else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_account_code")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT ACCOUNT_CODE,DESCRIPTION,REPORT_TYPE,STATUS  FROM LAKDL.AF_CO_ACC_ACCOUNTS_CODE "+
				" WHERE UPPER(ACCOUNT_CODE)=UPPER('"+m_val+"') AND STATUS LIKE'"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
		  }
		
		 else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_account_code1")){
				String m_val = req.getParameter("data_val").trim();
				//String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT ACCOUNT_CODE,DESCRIPTION,REPORT_TYPE,STATUS  FROM LAKDL.AF_CO_ACC_ACCOUNTS_CODE "+
				" WHERE UPPER(ACCOUNT_CODE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
		  }
		
		
		
		//---------------------------------------------------------------------------------------------
		
		 else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_app_client_doc")){
				String m_val = req.getParameter("app_num").trim();
				//String m_status = req.getParameter("ac_status");
				
								
				rs= stmt.executeQuery ("SELECT B.CODE,C.DESCRIPTION,A.REMARKS,DECODE(A.STATUS,'Y','on','N','off')"+
       "  FROM LAKDL.AF_CO_PRO_APP_CLIENT_DOCS A,LAKDL.AF_CO_MAS_DOCUMENT_APPLICABLE B,LAKDL.AF_CO_MAS_LEGAL_ENTITY C "+
       "   WHERE UPPER(A.APPLICATION_NO)=UPPER('"+m_val+"') AND UPPER(A.DOCUMENT_CODE)=UPPER(B.CODE) AND UPPER(B.ENTITY_TYPE)=UPPER(C.ENTITY_CODE)");
				
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
		  }
		
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_app_client_doc1")){
				String m_val = req.getParameter("entity_type").trim();
				String m_stage = req.getParameter("stage");
				
				rs= stmt.executeQuery ("SELECT A.CODE,B.DESCRIPTION,'Not_Enter',DECODE(A.ACTIVE_STATUS,'Y','on','N','off')  FROM LAKDL.AF_CO_MAS_DOCUMENT_APPLICABLE A, LAKDL.AF_CO_MAS_LEGAL_ENTITY B"+
				" WHERE UPPER(A.ENTITY_TYPE)=UPPER('"+m_val+"') AND STAGE='"+m_stage+"' AND UPPER(A.ENTITY_TYPE)=UPPER(B.ENTITY_CODE) ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
		  }
		
		
				
		
		
		 //-------------------  ID  			  :1.52 Client Creation Process   ---------------------------------//
		  //---------------------Purpose 	  :To validate the Client Code-----------------------------------
		  //---------------------Name       :N.V.P.Chandana---------------------------------------------------
		  //---------------------Date       :01-08-2006--------------------------------------------------
		
		
		
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_client_creation")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT CLIENT_CODE,CLIENT_TYPE,FULL_NAME,BUSINESS_SUB_SECTOR,CLIENT_CATEGORY,ADDRESS1,ADDRESS2,CITY_CODE,REFERENCE,TEL_NO,FAX_NO,EMAIL,OFFICE_TEL_NO,MOBILE_NO, "+
				 " CAT_TYPE_CODE,NIC_NO,BUSINESS_CERTIFICATE_NO,KEY_DECISION_MAKER,DESIGNATION,DIRECT_TEL_NO,CONTACT_FOR_PAYMENT,DESIGNATION_PAYMENT,FACTORY_ADDRESS1,FACTORY_ADDRESS2,FACTORY_STATUS, "+
				" F_CONTACT_PERSON,REGISTERED_ADDRESS1,REGISTERED_ADDRESS2,REGISTERED_CITY_CODE,REGISTERED_STATUS,CORRESPONDENCE_STATUS,F_TEL_NO,F_FAX_NO,F_EMAIL,ISSUED_SHARE_CAPITAL, "+
				" to_Char(DATE_OF_INCORPORATION),VAT_REG_NO,to_Char(VAT_REG_DATE),TITLE,FIRST_NAME,SURNAME,INITIALS,OTHER_NAME,RESIDENTIAL_STATUS,DURATION_AT_YEARS,DURATION_AT_MONTHS, "+
				" PASSPORT_NO, MARITAL_STATUS,to_Char(DATE_OF_BIRTH),NATIONALITY,GENDER "+
				"  FROM LAKDL.AF_CO_MAS_CLIENT  "+
				" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");
					out.print("<R6>"+rs.getString(6)+"</R6>");
					out.print("<R7>"+rs.getString(7)+"</R7>");
					out.print("<R8>"+rs.getString(8)+"</R8>");
				  out.print("<R9>"+rs.getString(9)+"</R9>");
					out.print("<R10>"+rs.getString(10)+"</R10>");
					out.print("<R11>"+rs.getString(11)+"</R11>");
					out.print("<R12>"+rs.getString(12)+"</R12>");
					out.print("<R13>"+rs.getString(13)+"</R13>");
					out.print("<R14>"+rs.getString(14)+"</R14>");
					out.print("<R15>"+rs.getString(15)+"</R15>");
					out.print("<R16>"+rs.getString(16)+"</R16>");
					out.print("<R17>"+rs.getString(17)+"</R17>");
					out.print("<R18>"+rs.getString(18)+"</R18>");
					out.print("<R19>"+rs.getString(19)+"</R19>");
					out.print("<R20>"+rs.getString(20)+"</R20>");
					out.print("<R21>"+rs.getString(21)+"</R21>");
					out.print("<R22>"+rs.getString(22)+"</R22>");
					out.print("<R23>"+rs.getString(23)+"</R23>");
					out.print("<R24>"+rs.getString(24)+"</R24>");
					out.print("<R25>"+rs.getString(25)+"</R25>");
					out.print("<R26>"+rs.getString(26)+"</R26>");
					out.print("<R27>"+rs.getString(27)+"</R27>");
					out.print("<R28>"+rs.getString(28)+"</R28>");
					out.print("<R29>"+rs.getString(29)+"</R29>");
					out.print("<R30>"+rs.getString(30)+"</R30>");
					out.print("<R31>"+rs.getString(31)+"</R31>");
					out.print("<R32>"+rs.getString(32)+"</R32>");
					out.print("<R33>"+rs.getString(33)+"</R33>");
					out.print("<R34>"+rs.getString(34)+"</R34>");
					out.print("<R35>"+rs.getString(35)+"</R35>");
					out.print("<R36>"+rs.getString(36)+"</R36>");
					out.print("<R37>"+rs.getString(37)+"</R37>");
					out.print("<R38>"+rs.getString(38)+"</R38>");
					out.print("<R39>"+rs.getString(39)+"</R39>");
					out.print("<R40>"+rs.getString(40)+"</R40>");
					out.print("<R41>"+rs.getString(41)+"</R41>");
					out.print("<R42>"+rs.getString(42)+"</R42>");
					out.print("<R43>"+rs.getString(43)+"</R43>");
					out.print("<R44>"+rs.getString(44)+"</R44>");
					out.print("<R45>"+rs.getString(45)+"</R45>");
					out.print("<R46>"+rs.getString(46)+"</R46>");
					out.print("<R47>"+rs.getString(47)+"</R47>");
					out.print("<R48>"+rs.getString(48)+"</R48>");
					out.print("<R49>"+rs.getString(49)+"</R49>");
					out.print("<R50>"+rs.getString(50)+"</R50>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
		
		
		
		
		
		
			
		//-------------------- ID : 1.14 Broker Creation Process-----------------------------//	
		//--------------------Broker Code Validation -------------------------------------------------//
		//--------------------Mahela Wickramasekara------------------------------------------------------//
		//--------------------19-07-2006---------------------------------------------------------//
		
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_broker")){
				String m_val = req.getParameter("data_val").trim();
				
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT  BROKER_CODE,TITLE,FIRST_NAME,LAST_NAME,ID_NO,ADDRESS1,ADDRESS2,"+
        " LOCATION_CODE,CITY_CODE,POSTAL_CODE,CONTACT_NO,MOBILE_NO,FAX_NO,SECTOR_CODE,COMMISSION_RATE,"+
        " COMMISSION_AMOUNT  FROM LAKDL.AF_CO_MAS_BROKER "+
				" WHERE UPPER(BROKER_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");
					out.print("<R6>"+rs.getString(6)+"</R6>");
					out.print("<R7>"+rs.getString(7)+"</R7>");
					out.print("<R8>"+rs.getString(8)+"</R8>");
					out.print("<R9>"+rs.getString(9)+"</R9>");
					out.print("<R10>"+rs.getString(10)+"</R10>");
					out.print("<R11>"+rs.getString(11)+"</R11>");
					out.print("<R12>"+rs.getString(12)+"</R12>");
					out.print("<R13>"+rs.getString(13)+"</R13>");
					out.print("<R14>"+rs.getString(14)+"</R14>");
					out.print("<R15>"+rs.getString(15)+"</R15>");
					out.print("<R16>"+rs.getString(16)+"</R16>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}	
			
			
			
			
		//-------------------- ID : 1.30 Garage Creation Process -----------------------------//	
		//--------------------Garage Code Validation -------------------------------------------------//
		//--------------------Mahela Wickramasekara------------------------------------------------------//
		//--------------------24-07-2006---------------------------------------------------------//
		
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_garage")){
				String m_val = req.getParameter("data_val").trim();
				
				String m_status = req.getParameter("ac_status");
				//AND ACTIVE_STATUS='"+m_status+"' 
				
				rs= stmt.executeQuery ("SELECT  GARAGE_CODE,NAME  FROM LAKDL.AF_CO_MAS_GARAGE "+
				" WHERE UPPER(GARAGE_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}		
			
			
			
		//-------------------- ID : 1.31 Income / Expense Type Creation Process -----------------------------//	
		//--------------------Income Expense Code Validation -------------------------------------------------//
		//--------------------Mahela Wickramasekara------------------------------------------------------//
		//--------------------24-07-2006---------------------------------------------------------//
		
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_income_expence_type")){
				String m_val = req.getParameter("data_val").trim();
				
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT  I_E_CODE,DESCRIPTION,DEFAULT_VALUE  FROM LAKDL.AF_CO_MAS_INCOME_EXPENCE_TYPE "+
				" WHERE UPPER(I_E_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}		

			
		//-------------------- ID : 1.33 Interest Type Creation Process -----------------------------//	
		//--------------------Interest Type Code Validation -------------------------------------------------//
		//--------------------Mahela Wickramasekara------------------------------------------------------//
		//--------------------24-07-2006---------------------------------------------------------//
		
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_interest_type")){
				String m_val = req.getParameter("data_val").trim();
				
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT  CODE,DESCRIPTION,DEFAULT_VALUE  FROM LAKDL.AF_CO_MAS_INTEREST_TYPE "+
				" WHERE UPPER(CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}	
			
		//-------------------- ID : 1.2 Province Creation Process-----------------------------//	
		//--------------------Province Code Validation -------------------------------------------------//
		//--------------------Mahela Wickramasekara------------------------------------------------------//
		//--------------------19-07-2006---------------------------------------------------------//
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_province")){
				String m_val = req.getParameter("data_val").trim();
				
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT  PROVINCE_CODE,PROVINCE_DESC,COUNTRY_CODE,DEFAULT_VALUE  FROM LAKDL.AF_CO_MAS_PROVINCE "+
				" WHERE UPPER(PROVINCE_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
		//--------------------User Access Rights -------------------------------------------------//	
		//--------------------Mahela Wickramasekara------------------------------------------------------//
		//--------------------28-07-2006---------------------------------------------------------//
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_user_access")){
				String m_val = req.getParameter("data_val").trim();

				
				rs= stmt.executeQuery ("SELECT B.ROW_ID,A.STATUS FROM LAKDL.CO_CO_MAS_USER_ACCESS A,LAKDL.CO_CO_MAS_USER_SCREEN B WHERE A.USER_ID='"+m_val+"'  AND A.SCREEN_NAME=B.SCREEN_NAME AND B.DISPLAY_STATUS<>'N'");
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
		//-------------------- ID : 1.34 Nationality Creation Process-----------------------------//	
		//--------------------Nationality Code Validation -------------------------------------------------//
		//--------------------Mahela Wickramasekara------------------------------------------------------//
		//--------------------24-07-2006---------------------------------------------------------//
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_nationality")){
				String m_val = req.getParameter("data_val").trim();
				
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT  NATIONALITY_CODE,DESCRIPTION  FROM LAKDL.AF_CO_MAS_NATIONALITY "+
				" WHERE UPPER(NATIONALITY_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
		//-------------------- ID : 1.45 Lead Source Creation Process-----------------------------//	
		//--------------------Lead Source Code Validation -------------------------------------------------//
		//--------------------Mahela Wickramasekara------------------------------------------------------//
		//--------------------25-07-2006---------------------------------------------------------//
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_lead_source")){
				String m_val = req.getParameter("data_val").trim();
				
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT  CODE,DESCRIPTION,DEFAULT_VALUE  FROM LAKDL.AF_MK_MAS_LEAD_SOURCE "+
				" WHERE UPPER(CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
		//-------------------- ID :1.46 Valuer Creation Process-----------------------------//	
		//--------------------Valuer Code Validation -------------------------------------------------//
		//--------------------Mahela Wickramasekara------------------------------------------------------//
		//--------------------25-07-2006---------------------------------------------------------//
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_valuer")){
				String m_val = req.getParameter("data_val").trim();
				
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT  VALUER_CODE,FIRST_NAME,LAST_NAME,ADDRESS,ADDRESS2,CITY_CODE,TEL_NO,MOBILE_NO,DEFAULT_VALUE  FROM LAKDL.AF_CO_MAS_VALUERS "+
				" WHERE UPPER(VALUER_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");
					out.print("<R6>"+rs.getString(6)+"</R6>");
					out.print("<R7>"+rs.getString(7)+"</R7>");
					out.print("<R8>"+rs.getString(8)+"</R8>");
					out.print("<R9>"+rs.getString(9)+"</R9>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
		//---------------Employee-----Id No Validation -------------------------------------------------//
		//--------------------Mahela Wickramasekara------------------------------------------------------//
		//--------------------02-08-2006---------------------------------------------------------//
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_employee_id")){
				String m_val = req.getParameter("data_val").trim();
				
				rs= stmt.executeQuery ("SELECT  ID_NO  FROM LAKDL.CO_CO_MAS_EMPLOYEE "+
				" WHERE UPPER(ID_NO)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}

			
			
			//---------------Broker-----Id No Validation -------------------------------------------------//
		//--------------------Mahela Wickramasekara------------------------------------------------------//
		//--------------------02-08-2006---------------------------------------------------------//
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_broker_idno")){
				String m_val = req.getParameter("data_val").trim();
				
				rs= stmt.executeQuery ("SELECT  ID_NO  FROM LAKDL.AF_CO_MAS_BROKER "+
				" WHERE UPPER(ID_NO)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
		//-------------------- ID :1.48 Sub Model Creation Process-----------------------------//	
		//--------------------Sub Model Code Validation -------------------------------------------------//
		//--------------------Mahela Wickramasekara------------------------------------------------------//
		//--------------------25-07-2006---------------------------------------------------------//
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_sub_model")){
				String m_val = req.getParameter("data_val").trim();
				
				String m_status = req.getParameter("ac_status");
				//&& ACTIVE_STATUS='"+m_status+"'
				
				rs= stmt.executeQuery ("SELECT SUB_CODE,MODEL_CODE,DESCRIPTION,ENGINE_CAPACITY,OPTION_TYPE,"+
				"COUNTRY_CODE,YEAR_OF_MANUFACTURE,DEFAULT_VALUE FROM LAKDL.AF_CO_MAS_SUB_MODLE "+
				" WHERE UPPER(SUB_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");
					out.print("<R6>"+rs.getString(6)+"</R6>");
					out.print("<R7>"+rs.getString(7)+"</R7>");
					out.print("<R8>"+rs.getString(8)+"</R8>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
		//-------------------- ID :1.49 Option Creation Process-----------------------------//	
		//--------------------Option Code Validation -------------------------------------------------//
		//--------------------Mahela Wickramasekara------------------------------------------------------//
		//--------------------26-07-2006---------------------------------------------------------//
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_option")){
				String m_val = req.getParameter("data_val").trim();
				
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT  OPTION_CODE,OPTION_DESC,DEFAULT_VALUE  FROM LAKDL.AF_CO_MAS_OPTION_TYPE "+
				" WHERE UPPER(OPTION_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
		
		
		//-------------------- ID :1.66 Missing Vehicle Process-----------------------------//	
		//--------------------Vehicle No Validation -------------------------------------------------//
		//--------------------Mahela Wickramasekara------------------------------------------------------//
		//--------------------26-07-2006---------------------------------------------------------//
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_missing_vehicle")){
				String m_val = req.getParameter("data_val").trim();
				
				rs= stmt.executeQuery ("SELECT  VEHICLE_NO,TO_CHAR(MISSING_DATE,'DD-MM-YYYY') AS MISSING_DATE,ENGIN_NO,CHASSISS_NO  FROM LAKDL.AF_CO_PRO_MISSING_VEHICLES "+
				" WHERE UPPER(VEHICLE_NO)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
		//-------------------- ID :1.68 Lead Source Category Process-----------------------------//	
		//--------------------Source Code Validation -------------------------------------------------//
		//--------------------Mahela Wickramasekara------------------------------------------------------//
		//--------------------26-07-2006---------------------------------------------------------//
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_lead_source_cat")){
				String m_val = req.getParameter("data_val").trim();
				
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT  SOURCE_CODE,NAME,DEFAULT_VALUE  FROM LAKDL.AF_MK_MAS_LEAD_SOURCE_CAT "+
				" WHERE UPPER(SOURCE_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
		
		//-------------------- ID :1.69 Transaction Process-----------------------------//	
		//--------------------Transaction Code Validation -------------------------------------------------//
		//--------------------Mahela Wickramasekara------------------------------------------------------//
		//--------------------26-07-2006---------------------------------------------------------//

		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_transaction")){
				String m_val = req.getParameter("data_val").trim();
				
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT  TRAN_CODE,DESCRIPTION,DEFAULT_VALUE  FROM LAKDL.AF_CO_MAS_TRANSACTION_TYPE "+
				" WHERE UPPER(TRAN_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
		
		//-------------------- ID :1.70 Transaction Sub Type Process-----------------------------//	
		//--------------------Transaction Sub Type Code Validation -------------------------------------------------//
		//--------------------Mahela Wickramasekara------------------------------------------------------//
		//--------------------26-07-2006---------------------------------------------------------//
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_transaction_sub_type")){
				String m_val = req.getParameter("data_val").trim();
				
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT  TRN_SUB_TYPE,TRN_CODE,DESCRIPTION,RATE,DEFAULT_VALUE  FROM LAKDL.AF_CO_MAS_TRANSACTION_SUB_TYPE "+
				" WHERE UPPER(TRN_SUB_TYPE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
		
		//-------------------- ID :1.47 Mileage Creation Process-----------------------------//	
		//--------------------Model Code Validation -------------------------------------------------//
		//--------------------Mahela Wickramasekara------------------------------------------------------//
		//--------------------25-07-2006---------------------------------------------------------//
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_mileage")){
				String m_val = req.getParameter("data_val").trim();
				
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT MODEL,SUB_MODEL,CONDITION_OF_ASSET,USAGE_FROM,USAGE_TO,AMOUNT FROM LAKDL.AF_CO_MAS_MILEAGE "+
				" WHERE UPPER(MODEL)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");
					out.print("<R6>"+rs.getString(6)+"</R6>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			//-------------------- ID : 1.32 Nationality Creation Process-----------------------------//	
		//--------------------Inquiry Code Validation -------------------------------------------------//
		//--------------------Mahela Wickramasekara------------------------------------------------------//
		//--------------------24-07-2006---------------------------------------------------------//
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_inquiry_status")){
				String m_val = req.getParameter("data_val").trim();
				
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT  CODE,DESCRIPTION,DEFAULT_VALUE  FROM LAKDL.AF_CO_MAS_INQUARY_STATUS "+
				" WHERE UPPER(CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"'  ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
		  //------------------ID         : 1.1 Country Creation Process-----------------------------------------
			//--------------------Purpose    :Country Code Validation ----------------------------------------------
		 // -------------------   Added By :Nuwan De Silva------------------------------------------------------
		  //--------------------  Date     :19-07-2006---------------------------------------------------------
		
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_country")){
			
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				rs= stmt.executeQuery ("SELECT COUNTRY_CODE,COUNTRY_DESC,DEFAULT_VALUE FROM LAKDL.AF_CO_MAS_COUNTRY "+
				" WHERE UPPER(COUNTRY_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			//------------------ID         : 1.2 Area Creation Process-----------------------------------------
			//--------------------Purpose    :Area Code Validation ----------------------------------------------
		  //-------------------   Added By :Nuwan De Silva------------------------------------------------------
		 // --------------------  Date     :19-07-2006---------------------------------------------------------
		
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_area")){
			
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT AREA_CODE,AREA_DESC,CITY_CODE,DEFAULT_VALUE FROM LAKDL.AF_CO_MAS_AREA "+
				" WHERE UPPER(AREA_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
		
			
			
		  //---------------------ID  			:1.20  Currency Creation Process---------------------------------//
			//---------------------Purpose 	:To validate the Currency Code-----------------------------------
			//---------------------Name     :Delanjali---------------------------------------------------
			//---------------------Date     :2006 07 19--------------------------------------------------
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_currency")){
			
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT CURR_CODE,CURR_SYMBOL,REP_CURR,TO_CHAR(TRN_DATE,'DD-MM-YYYY'),CATEGORY_CODE,DEFAULT_VALUE"+
 				" FROM LAKDL.AF_CO_MAS_CURRENCY "+
				" WHERE UPPER(CURR_CODE)=UPPER('"+m_val+"') "+
				" AND ACTIVE_STATUS=UPPER('"+m_status+"') ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R1>"+rs.getString(2)+"</R1>");
					out.print("<R1>"+rs.getString(3)+"</R1>");
					out.print("<R1>"+rs.getString(4)+"</R1>");
					out.print("<R1>"+rs.getString(5)+"</R1>");
					out.print("<R1>"+rs.getString(6)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			//------------------ID         : 1.6 Postal Code Creation Process-----------------------------------------
			//--------------------Purpose    :Postal Code Validation ----------------------------------------------
		 // -------------------   Added By :Nuwan De Silva------------------------------------------------------
		 // --------------------  Date     :19-07-2006---------------------------------------------------------
		
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_postal_codes")){
			
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status");						
			rs= stmt.executeQuery ("SELECT POSTAL_CODE,DESCRIPTION,CITY_CODE,DEFAULT_VALUE FROM LAKDL.AF_CO_MAS_POSTAL_CODES "+
			" WHERE UPPER(POSTAL_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
			
			//------------------ID         : 1.10 Designation Creation Process-----------------------------------------
			//--------------------Purpose    :Designation Code Validation ----------------------------------------------
		 // -------------------   Added By :Mahela Wickramasekara------------------------------------------------------
		  //--------------------  Date     :19-07-2006---------------------------------------------------------
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_designation")){
			
				String m_val = req.getParameter("data_val");
				
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT DESIGNATION_CODE,DESIGNATION_NAME,DESIGNATION_LEVEL,DIVISION FROM LAKDL.CO_CO_MAS_DESIGNATION "+
				" WHERE UPPER(DESIGNATION_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			//------------------ID         : 1.11 Employee Creation Process-----------------------------------------
		//	--------------------Purpose    : Emp Code Validation ----------------------------------------------
		 // -------------------   Added By : Mahela Wickramasekara------------------------------------------------------
		 // --------------------  Date     : 20-07-2006---------------------------------------------------------
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_employee")){
			
				String m_val = req.getParameter("data_val");
				
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT EMP_CODE,TITLE,FIRST_NAME,LAST_NAME,ADDRESS,LOCATION_CODE,NVL(AREA_CODE,'N/A'),"+
				" NVL(CITY_CODE,'N/A'),NVL(CONTACT_NO,'N/A'),DESIGNATION_CODE,DIVISION_CODE,NVL(EPF_NO,'N/A'),ID_NO FROM LAKDL.CO_CO_MAS_EMPLOYEE "+
				" WHERE UPPER(EMP_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");
					out.print("<R6>"+rs.getString(6)+"</R6>");
					out.print("<R7>"+rs.getString(7)+"</R7>");
					out.print("<R8>"+rs.getString(8)+"</R8>");
					out.print("<R9>"+rs.getString(9)+"</R9>");
					out.print("<R10>"+rs.getString(10)+"</R10>");
					out.print("<R11>"+rs.getString(11)+"</R11>");
					out.print("<R12>"+rs.getString(12)+"</R12>");
					out.print("<R13>"+rs.getString(13)+"</R13>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			//------------------ID         : 1.13 Bank branch Creation Process-----------------------------------------
		//	--------------------Purpose    : Branch Code Validation ----------------------------------------------
		  //-------------------   Added By : Mahela Wickramasekara------------------------------------------------------
		 // --------------------  Date     : 20-07-2006---------------------------------------------------------
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_branch")){
			
				String m_val = req.getParameter("data_val");
				
				String m_status = req.getParameter("ac_status");

				rs= stmt.executeQuery ("SELECT BRANCH_CODE,BRANCH_NAME,BANK_CODE,ADDRESS1,NVL(ADDRESS2,'N/A'),NVL(CITY_CODE,'N/A'),NVL(TEL_NO,'N/A'),NVL(FAX_NO,'N/A'),DAYS_TO_REALISE,DEFAULT_VALUE FROM LAKDL.AF_CO_MAS_BANK_BRANCH "+
				" WHERE UPPER(BRANCH_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");
					out.print("<R6>"+rs.getString(6)+"</R6>");
					out.print("<R7>"+rs.getString(7)+"</R7>");
					out.print("<R8>"+rs.getString(8)+"</R8>");
					out.print("<R9>"+rs.getString(9)+"</R9>");
					out.print("<R10>"+rs.getString(10)+"</R10>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			//------------------ID         : 1.12 Bank Creation Process-----------------------------------------
			//--------------------Purpose    : Bank Code Validation ----------------------------------------------
		  //-------------------   Added By : Mahela Wickramasekara------------------------------------------------------
		  //--------------------  Date     : 20-07-2006---------------------------------------------------------
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_bank")){
			
				String m_val = req.getParameter("data_val");
				
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT BANK_CODE,NAME,DEFAULT_VALUE FROM LAKDL.AF_CO_MAS_BANKS "+
				" WHERE UPPER(BANK_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
		  //---------------------ID  			:1.21  Leasing Process Stage Creation Process---------------------------------//
			//---------------------Purpose 	:To validate the Division Code-----------------------------------
			//---------------------Name     :Delanjali---------------------------------------------------
			//---------------------Date     :2006 07 19--------------------------------------------------

			
				else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_leasing")){
			
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT A.DIVISION,PRODUCT_ID FROM LAKDL.AF_CO_LEASE_PROCESS_STAGE A,LAKDL.CO_CO_MAS_DIVISION B "+
				" WHERE UPPER(DIVISION)=TRIM(UPPER('"+m_val+"')) "+
				" AND A.DIVISION=B.DIVISION_CODE " +
				" AND A.ACTIVE_STATUS=UPPER('"+m_status+"')" );
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R1>"+rs.getString(2)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_leasing_product_id")){
			
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");

				rs= stmt.executeQuery ("SELECT PRODUCT_CODE,DESCRIPTION,DEFAULT_VALUE FROM LAKDL.AF_CO_MAS_PRODUCT "+
				" WHERE UPPER(PRODUCT_CODE)=UPPER('"+m_val+"') "+
				" AND ACTIVE_STATUS=UPPER('"+m_status+"')" );
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R1>"+rs.getString(2)+"</R1>");
					out.print("<R1>"+rs.getString(3)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			//------------------ID         : 1.7 Location Creation Process-----------------------------------------
		//	--------------------Purpose    :Location Code Validation ----------------------------------------------
		 // -------------------   Added By :Nuwan De Silva------------------------------------------------------
		 // --------------------  Date     :20-07-2006---------------------------------------------------------
		
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_location")){
			
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status");						
									
			rs= stmt.executeQuery ("SELECT LOCATION_CODE,LOCATION_DESC,ADDRESS1,NVL(ADDRESS2,'N/A'),CITY_CODE,NVL(POSTAL_CODE,'N/A'),NVL(COUNTRY_CODE,'N/A') FROM LAKDL.AF_CO_MAS_LOCATION "+
			" WHERE UPPER(LOCATION_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");
					out.print("<R6>"+rs.getString(6)+"</R6>");
					out.print("<R7>"+rs.getString(7)+"</R7>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_location_new")){
			
			String m_val = req.getParameter("data_val").trim();
									
			rs= stmt.executeQuery ("SELECT CONTACT_PERSON,TEL_NO,FAX_NO FROM LAKDL.AF_CO_MAS_LOCATION_CONTACT  "+
			" WHERE UPPER(LOCATION_CODE)=UPPER('"+m_val+"')");
				
	 
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
			
			
			
			//------------------ID         : 1.8 Division Creation Process-----------------------------------------
			//--------------------Purpose    :Division Code Validation ----------------------------------------------
		 // -------------------   Added By :Nuwan De Silva------------------------------------------------------
		  //--------------------  Date     :20-07-2006---------------------------------------------------------
		
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_division")){
			
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status");	
									
			rs= stmt.executeQuery ("SELECT DIVISION_CODE,DESCRIPTION FROM LAKDL.CO_CO_MAS_DIVISION "+
			" WHERE UPPER(DIVISION_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
			
	  	//------------------ID         :1.25 Engine Capacity Creation Process-----------------------------------------
			//--------------------Purpose    :Engine Capacity Code Validation ----------------------------------------------
		 // -------------------   Added By :Nuwan De Silva------------------------------------------------------
		 // --------------------  Date     :20-07-2006---------------------------------------------------------
		
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_engine_capacity")){
			
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status");	
									
			rs= stmt.executeQuery ("SELECT CAPACITY_CODE,DESCRIPTION,DEFAULT_VALUE FROM LAKDL.AF_CO_MAS_ENGINE_CAPACITY "+
			" WHERE UPPER(CAPACITY_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
			
			//------------------ID         :1.9 User Creation Process-----------------------------------------
			//--------------------Purpose    :User ID Validation ----------------------------------------------
		 // -------------------   Added By :Nuwan De Silva------------------------------------------------------
		 // --------------------  Date     :20-07-2006---------------------------------------------------------
		
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_user")){
			
			String m_val = req.getParameter("data_val").trim();
			
									
			rs= stmt.executeQuery ("SELECT USER_ID,NAME,LOCATION_CODE, USER_TYPE, EMP_ID,"+
   " DIVISION_CODE, DESIGNATION_CODE FROM LAKDL.CO_CO_MAS_USER "+
			" WHERE UPPER(USER_ID)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");
					out.print("<R6>"+rs.getString(6)+"</R6>");
					out.print("<R7>"+rs.getString(7)+"</R7>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
			//------------------ID         :1.22 Item Category Creation Process-------------------------------
			//--------------------Purpose    :Item Category Code validation------------------------------------------------
		 // //------------------- Added By   :Delanjali------------------------------------------------------
		 // --------------------Date       :20-07-2006---------------------------------------------------------

			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_item_category")){
			
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status");
			
			rs= stmt.executeQuery ("SELECT ITEM_CAT_CODE,DESCRIPTION,DEFAULT_VALUE FROM LAKDL.AF_CO_MAS_ITEM_CATEGORY "+
			" WHERE UPPER(ITEM_CAT_CODE)=UPPER('"+m_val+"') "+
			" AND ACTIVE_STATUS=UPPER('"+m_status+"')");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R1>"+rs.getString(2)+"</R1>");
					out.print("<R1>"+rs.getString(3)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
			
		
						
		 	//------------------ID         :1.23 Item Sub Category Creation Process-------------------------------
		//	--------------------Purpose    :Item Sub Category Code validation------------------------------------------------
		 // ------------------- Added By   :Delanjali------------------------------------------------------
		//  --------------------Date       :20-07-2006---------------------------------------------------------

			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_item_sub_category")){
			
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status");	
			
			rs= stmt.executeQuery ("SELECT ITEM_SUB_CAT,ITEM_CAT_CODE,DESCRIPTION,DEFAULT_VALUE FROM LAKDL.AF_CO_MAS_ITEM_SUB_CATEGORY "+
			" WHERE UPPER(ITEM_SUB_CAT)=UPPER('"+m_val+"') "+
			" AND ACTIVE_STATUS=UPPER('"+m_status+"')");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R1>"+rs.getString(2)+"</R1>");
					out.print("<R1>"+rs.getString(3)+"</R1>");
					out.print("<R1>"+rs.getString(4)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
			
			//------------------ID         :1.26 Fields Creation Process-----------------------------------------
			//--------------------Purpose    :Fields Code Validation ----------------------------------------------
		//  -------------------   Added By :Nuwan De Silva------------------------------------------------------
		//  --------------------  Date     :20-07-2006---------------------------------------------------------
		
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_fields")){
			
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status");	
									
			rs= stmt.executeQuery ("SELECT  FILED_CODE,DESCRIPTION,DEFAULT_VALUE FROM LAKDL.AF_CO_MAS_FILEDS "+
			" WHERE UPPER( FILED_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
		//------------------ID         :1.28 Fuel Type Creation Process-----------------------------------------
			//--------------------Purpose    :Fuel Type Code Validation ----------------------------------------------
	//	  -------------------   Added By :Nuwan De Silva------------------------------------------------------
		 // --------------------  Date     :20-07-2006---------------------------------------------------------
		
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_fuel_type")){
			
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status");	
									
			rs= stmt.executeQuery ("SELECT CODE,DESCRIPTION,DEFAULT_VALUE FROM LAKDL.AF_CO_MAS_FUEL_TYPE "+
			" WHERE UPPER(CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
			
			/*------------------ID         :1.27 Applicable Fields Creation Process-----------------------------------------
			--------------------Purpose    :Applicable Fields Code Validation ----------------------------------------------
		  -------------------   Added By :Nuwan De Silva------------------------------------------------------
		  --------------------  Date     :20-07-2006---------------------------------------------------------*/
		
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_applicable_fields")){
			
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status");	
									
			rs= stmt.executeQuery ("SELECT FILED_CODE,ITEM_CATEGORY,PROCESS_STAGE,DEFAULT_VALUE FROM LAKDL.AF_CO_MAS_FILEDS_APPLICABLE "+
			" WHERE UPPER(FILED_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }

				
			
		/*--------------------ID         :1.24 Leagal Entry Creation Process-------------------------------
			--------------------Purpose    :Leagal Code validation------------------------------------------------
		  ------------------- Added By   :Delanjali------------------------------------------------------
		  --------------------Date       :20-07-2006---------------------------------------------------------*/

			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_legal_entity")){
			
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status");	
			
			rs= stmt.executeQuery ("SELECT ENTITY_CODE,DESCRIPTION,DEFAULT_VALUE FROM LAKDL.AF_CO_MAS_LEGAL_ENTITY "+
			" WHERE UPPER(ENTITY_CODE)=UPPER('"+m_val+"') "+
			" AND ACTIVE_STATUS=UPPER('"+m_status+"')");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R1>"+rs.getString(2)+"</R1>");
					out.print("<R1>"+rs.getString(3)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
			
			
			/*--------------------ID       :1.35  Phone Area Code Creation Process-------------------------------
			--------------------Purpose    :Phone Area Code validation------------------------------------------------
		  ------------------- Added By   :Delanjali------------------------------------------------------
		  --------------------Date       :21-07-2006---------------------------------------------------------
				
	
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_phone_area_codes")){
			
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status");	
			
			rs= stmt.executeQuery ("SELECT PHONE_AREA_CODE,DISTRICT_CODE,DEFAULT_VALUE FROM LAKDL.AF_CO_MAS_PHONE_CODES "+
			" WHERE UPPER(PHONE_AREA_CODE)=UPPER('"+m_val+"') "+
			" AND ACTIVE_STATUS=UPPER('"+m_status+"')");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R1>"+rs.getString(2)+"</R1>");
					out.print("<R1>"+rs.getString(3)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
			
			


			/*--------------------ID       :1.37 RMV Agent Creation Process-------------------------------
			--------------------Purpose    :RMV Agent Code validation------------------------------------------------
		  ------------------- Added By   :Delanjali------------------------------------------------------
		  --------------------Date       :21-07-2006---------------------------------------------------------
				
	 	else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_rmv_agents")){
			
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status");	
				
			rs= stmt.executeQuery ("SELECT RMV_AGENT_CODE,NAME,ADDRESS1,ADDRESS2,CITY_CODE,MOBILE_NO,TEL_NO,MONTHLY_FEE,FEE_FOR_CASE,DEFAULT_VALUE"+
			" FROM LAKDL.AF_CO_MAS_RMV_AGENTS "+
			" WHERE UPPER(RMV_AGENT_CODE)=UPPER('"+m_val+"') "+
			" AND ACTIVE_STATUS=UPPER('"+m_status+"')");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R1>"+rs.getString(2)+"</R1>");
					out.print("<R1>"+rs.getString(3)+"</R1>");
					out.print("<R1>"+rs.getString(4)+"</R1>");
					out.print("<R1>"+rs.getString(5)+"</R1>");
					out.print("<R1>"+rs.getString(6)+"</R1>");
					out.print("<R1>"+rs.getString(7)+"</R1>");
					out.print("<R1>"+rs.getString(8)+"</R1>");
					out.print("<R1>"+rs.getString(9)+"</R1>");
					out.print("<R1>"+rs.getString(10)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }



			/*--------------------ID       :1.38 Seizer Creation Process-------------------------------
			--------------------Purpose    :Seizer Code validation------------------------------------------------
		  ------------------- Added By   :Delanjali------------------------------------------------------
		  --------------------Date       :24-07-2006---------------------------------------------------------
				
	 	else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_seizer")){
			
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status");	
				
			rs= stmt.executeQuery ("SELECT  SEIZER_CODE,FIRST_NAME,LAST_NAME,ADDRESS1,ADDRESS2,MOBILE_NO,TEL_NO,CITY_CODE,FEE_PER_CASE,MONTHLY_FEE,"+
 			" DEFAULT_VALUE,VALIDITY_PERIOD FROM LAKDL.AF_CO_MAS_SEIZER "+
			" WHERE UPPER(SEIZER_CODE)=UPPER('"+m_val+"') "+
			" AND ACTIVE_STATUS=UPPER('"+m_status+"')");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R1>"+rs.getString(2)+"</R1>");
					out.print("<R1>"+rs.getString(3)+"</R1>");
					out.print("<R1>"+rs.getString(4)+"</R1>");
					out.print("<R1>"+rs.getString(5)+"</R1>");
					out.print("<R1>"+rs.getString(6)+"</R1>");
					out.print("<R1>"+rs.getString(7)+"</R1>");
					out.print("<R1>"+rs.getString(8)+"</R1>");
					out.print("<R1>"+rs.getString(9)+"</R1>");
					out.print("<R1>"+rs.getString(10)+"</R1>");
					out.print("<R1>"+rs.getString(11)+"</R1>");
					out.print("<R1>"+rs.getString(12)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }


			/*--------------------ID       :1.39 Yard Creation Process-------------------------------
			--------------------Purpose    :Yard Code validation------------------------------------------------
		  ------------------- Added By   :Delanjali------------------------------------------------------
		  --------------------Date       :24-07-2006---------------------------------------------------------
					
			
	 	else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_yard_creation")){
			
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status");	
				
			rs= stmt.executeQuery ("SELECT YARD_CODE,NAME,ADDRESS1,ADDRESS2,CITY_CODE,TEL_NO,FAX_NO,DEFAULT_VALUE"+
 			" FROM LAKDL.AF_CO_MAS_YARD"+
			" WHERE UPPER(YARD_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R1>"+rs.getString(2)+"</R1>");
					out.print("<R1>"+rs.getString(3)+"</R1>");
					out.print("<R1>"+rs.getString(4)+"</R1>");
					out.print("<R1>"+rs.getString(5)+"</R1>");
					out.print("<R1>"+rs.getString(6)+"</R1>");
					out.print("<R1>"+rs.getString(7)+"</R1>");
					out.print("<R1>"+rs.getString(8)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
			
	  	/*------------------ID         : 1.29 Team Creation Process-----------------------------------------
			--------------------Purpose    :Team ID Validation ----------------------------------------------
		  -------------------   Added By :Nuwan De Silva------------------------------------------------------
		  --------------------  Date     :20-07-2006--------------------------------------------------------
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_team")){
			
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status");	
				
			rs= stmt.executeQuery ("SELECT TEAM_ID,TEAM_DESC,NVL(TEAM_HEAD,'N/A'),NVL(DIVISION_CODE,'N/A'),NVL(SUB_DIVISION_CODE,'N/A') FROM LAKDL.AF_CO_MAS_TEAMS"+
			" WHERE UPPER(TEAM_ID)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
			
			/*------------------ID         : 1.29 Team Creation Process-----------------------------------------
			--------------------Purpose    :Sub Division Code Validation ----------------------------------------------
		  -------------------   Added By :Nuwan De Silva------------------------------------------------------
		  --------------------  Date     :20-07-2006---------------------------------------------------------
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_sub_division")){
			
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status");	
				
			rs= stmt.executeQuery ("SELECT SUB_DIVISION_CODE,DESCRIPTION,DIVISION_CODE FROM LAKDL.CO_CO_MAS_SUB_DIVISION"+
			" WHERE UPPER(SUB_DIVISION_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
			
			/*--------------------ID       :1.55 Repayment Interval Creation Process-------------------------------
			--------------------Purpose    :Duration validation------------------------------------------------
		  ------------------- Added By   :Delanjali------------------------------------------------------
		  --------------------Date       :24-07-2006---------------------------------------------------------
	 
	 	else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_repayment_interval")){
			
			String m_val = req.getParameter("data_val").trim();
				
			rs= stmt.executeQuery ("SELECT DURATION,DESCRIPTION,DURATION_TYPE,DEFAULT_VALUE FROM LAKDL.AF_CO_MAS_REPAYMENT_INTERVAL"+
			" WHERE UPPER(DURATION)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R1>"+rs.getString(2)+"</R1>");
					out.print("<R1>"+rs.getString(3)+"</R1>");
					out.print("<R1>"+rs.getString(4)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
			
			/*--------------------ID       :1.62 Score Category Process-------------------------------
			--------------------Purpose    :Score Category validation------------------------------------------------
		  ------------------- Added By   :Nuwan De Silva------------------------------------------------------
		  --------------------Date       :24-07-2006---------------------------------------------------------
	
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_score_category")){
			
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status");	
				
			rs= stmt.executeQuery ("SELECT SCORE_CODE,DESCRIPTION,DISPLAY_POSITION FROM LAKDL.AF_CR_MAS_SCORE_CATEGORY"+
			" WHERE UPPER(SCORE_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
			
			/*--------------------ID       :1.56 Revenue License Creation Process-------------------------------
			--------------------Purpose    :Revenue Code validation------------------------------------------------
		  ------------------- Added By   :Delanjali------------------------------------------------------
		  --------------------Date       :24-07-2006---------------------------------------------------------
	
	
	 		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_revenue_license")){
			
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status");
				
			rs= stmt.executeQuery ("SELECT CODE,TO_CHAR(FROM_DATE,'DD-MM-YYYY'),TO_CHAR(TO_DATE,'DD-MM-YYYY'),FUEL_TYPE,RATE,DEFAULT_VALUE FROM LAKDL.AF_CO_MAS_REVENUE_LICENSE"+
			" WHERE UPPER(CODE)=UPPER('"+m_val+"') ");
			//" ACTIVE_STATUS=UPPER('"+m_status+"')");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R1>"+rs.getString(2)+"</R1>");
					out.print("<R1>"+rs.getString(3)+"</R1>");
					out.print("<R1>"+rs.getString(4)+"</R1>");
					out.print("<R1>"+rs.getString(5)+"</R1>");
					out.print("<R1>"+rs.getString(6)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     	}
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_fual")){
			
			String m_val = req.getParameter("data_val").trim();
				
			rs= stmt.executeQuery ("SELECT CODE FROM LAKDL.AF_CO_MAS_FUEL_TYPE"+
			" WHERE UPPER(CODE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
			//--------------------------------------------------------------------------------------------



			/*--------------------ID       :1.57 Sub Charge Creation Process-------------------------------
			--------------------Purpose    :sub_type Code validation------------------------------------------------
		  ------------------- Added By   :Delanjali------------------------------------------------------
		  --------------------Date       :25-07-2006---------------------------------------------------------
		
	 		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_sub_charge")){
			
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status");	
			
			rs= stmt.executeQuery ("SELECT SUB_TYPE_CODE,TYPE_CODE,DESCRIPTION,DEFAULT_VALUE FROM LAKDL.AF_CO_MAS_SUB_CHARGES"+
			" WHERE UPPER(SUB_TYPE_CODE)=UPPER('"+m_val+"')  AND ACTIVE_STATUS='"+m_status+"'  ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R1>"+rs.getString(2)+"</R1>");
					out.print("<R1>"+rs.getString(3)+"</R1>");
					out.print("<R1>"+rs.getString(4)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     	}
				
			/*--------------------ID       :1.64 Score Rating Process-------------------------------
			--------------------Purpose    :Score Rating Code validation------------------------------------------------
		  ------------------- Added By   :Nuwan De Silva------------------------------------------------------
		  --------------------Date       :25-07-2006---------------------------------------------------------
		
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_score_rating")){
			
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status");	
				
			rs= stmt.executeQuery ("SELECT RATING_CODE,NVL(DESCRIPTION,'N/A'),NVL(FROM_RAGE,0),NVL(TO_RANGE,0) FROM LAKDL.AF_CR_MAS_SCORE_RATING"+
			" WHERE UPPER(RATING_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"'  ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     	}
				
				
					
			/*--------------------ID       :1.73 Authorization Limits Process-------------------------------
			--------------------Purpose    :user id validation------------------------------------------------
		  ------------------- Added By   :Nuwan De Silva------------------------------------------------------
		  --------------------Date       :25-07-2006---------------------------------------------------------
		
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_authorization_limits")){
			
			String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");	
				
			rs= stmt.executeQuery ("SELECT USER_ID,AUTHORIZATION_LEVEL,LIMIT,DEFAULT_VALUE FROM LAKDL.AF_CO_MAS_AUTHORIZATION_LIMITS"+
			" WHERE UPPER(USER_ID)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"'  ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     	}	
				
							
			/*--------------------ID       :1.75 Lawyer Process-------------------------------
			--------------------Purpose    :Lawyer id validation------------------------------------------------
		  ------------------- Added By   :Nuwan De Silva------------------------------------------------------
		  --------------------Date       :25-07-2006---------------------------------------------------------
		
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_lawyer")){
			
			String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");	
				
			rs= stmt.executeQuery ("SELECT LAWYER_CODE,FIRST_NAME,LAST_NAME,NAME_WITH_INITIALS,ADDRESS1,NVL(ADDRESS2,'N/A'),CITY_CODE,TEL_NO,OFFICE_TEL_NO,MOBILE_NO,FAX_NO,OFFICE_FAX_NO,FEE_PER_CASE,MONTHLY_FEE FROM LAKDL.AF_CO_MAS_LAWYER"+
			" WHERE UPPER(LAWYER_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");
					out.print("<R6>"+rs.getString(6)+"</R6>");
					out.print("<R7>"+rs.getString(7)+"</R7>");
					out.print("<R8>"+rs.getString(8)+"</R8>");
					out.print("<R9>"+rs.getString(9)+"</R9>");
					out.print("<R10>"+rs.getString(10)+"</R10>");
					out.print("<R11>"+rs.getString(11)+"</R11>");
					out.print("<R12>"+rs.getString(12)+"</R12>");
					out.print("<R13>"+rs.getString(13)+"</R13>");
					out.print("<R14>"+rs.getString(14)+"</R14>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     	}	
				
				
			/*--------------------ID       :1.59 Client Black List Process-------------------------------
			--------------------Purpose    :Client Code validation------------------------------------------------
		  ------------------- Added By   :Delanjali------------------------------------------------------
		  --------------------Date       :25-07-2006---------------------------------------------------------
		
	 		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_client_backlisting")){
			
			String m_val = req.getParameter("data_val").trim();
				
		rs= stmt.executeQuery ("SELECT CLIENT_CODE,ACTIVE_STATUS,CLIENT_TYPE,FULL_NAME,TITLE,FIRST_NAME,SURNAME,INITIALS,OTHER_NAME,"+
    	" TEL_NO,FAX_NO,EMAIL,OFFICE_TEL_NO,MOBILE_NO,ADDRESS1,ADDRESS2,CITY_CODE,NIC_NO,PASSPORT_NO,MARITAL_STATUS,"+
    	" TO_CHAR(DATE_OF_BIRTH,'DD-MM-YYYY'),NATIONALITY,GENDER,BUSINESS_SUB_SECTOR,CLIENT_CATEGORY,CAT_TYPE_CODE,REFERENCE,BUSINESS_CERTIFICATE_NO,"+
    	" KEY_DECISION_MAKER,DESIGNATION,DIRECT_TEL_NO,CONTACT_FOR_PAYMENT,DESIGNATION_PAYMENT,FACTORY_ADDRESS1,FACTORY_ADDRESS2,"+
    	" FACTORY_STATUS,F_CONTACT_PERSON,REGISTERED_ADDRESS1,REGISTERED_ADDRESS2,REGISTERED_CITY_CODE,REGISTERED_STATUS,"+
    	" CORRESPONDENCE_STATUS,F_TEL_NO,F_FAX_NO,F_EMAIL,ISSUED_SHARE_CAPITAL,TO_CHAR(DATE_OF_INCORPORATION,'DD-MM-YYYY'),VAT_REG_NO,TO_CHAR(VAT_REG_DATE,'DD-MM-YYYY'),RESIDENTIAL_STATUS,"+
    	" DURATION_AT_YEARS,DURATION_AT_MONTHS FROM LAKDL.AF_CO_MAS_CLIENT"+
			" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R1>"+rs.getString(2)+"</R1>");
					out.print("<R1>"+rs.getString(3)+"</R1>");
					out.print("<R1>"+rs.getString(4)+"</R1>");
					out.print("<R1>"+rs.getString(5)+"</R1>");
					out.print("<R1>"+rs.getString(6)+"</R1>");
					out.print("<R1>"+rs.getString(7)+"</R1>");
					out.print("<R1>"+rs.getString(8)+"</R1>");
					out.print("<R1>"+rs.getString(9)+"</R1>");
					out.print("<R1>"+rs.getString(10)+"</R1>");
					out.print("<R1>"+rs.getString(11)+"</R1>");
					out.print("<R1>"+rs.getString(12)+"</R1>");
					out.print("<R1>"+rs.getString(13)+"</R1>");
					out.print("<R1>"+rs.getString(14)+"</R1>");
					out.print("<R1>"+rs.getString(15)+"</R1>");
					out.print("<R1>"+rs.getString(16)+"</R1>");
					out.print("<R1>"+rs.getString(17)+"</R1>");
					out.print("<R1>"+rs.getString(18)+"</R1>");
					out.print("<R1>"+rs.getString(19)+"</R1>");
					out.print("<R1>"+rs.getString(20)+"</R1>");
					out.print("<R1>"+rs.getString(21)+"</R1>");
					out.print("<R1>"+rs.getString(22)+"</R1>");
					out.print("<R1>"+rs.getString(23)+"</R1>");
					out.print("<R1>"+rs.getString(24)+"</R1>");
					out.print("<R1>"+rs.getString(25)+"</R1>");
					out.print("<R1>"+rs.getString(26)+"</R1>");
					out.print("<R1>"+rs.getString(27)+"</R1>");
					out.print("<R1>"+rs.getString(28)+"</R1>");
					out.print("<R1>"+rs.getString(29)+"</R1>");
					out.print("<R1>"+rs.getString(30)+"</R1>");
					out.print("<R1>"+rs.getString(31)+"</R1>");
					out.print("<R1>"+rs.getString(32)+"</R1>");
					out.print("<R1>"+rs.getString(33)+"</R1>");
					out.print("<R1>"+rs.getString(34)+"</R1>");
					out.print("<R1>"+rs.getString(35)+"</R1>");
					out.print("<R1>"+rs.getString(36)+"</R1>");
					out.print("<R1>"+rs.getString(37)+"</R1>");
					out.print("<R1>"+rs.getString(38)+"</R1>");
					out.print("<R1>"+rs.getString(39)+"</R1>");
					out.print("<R1>"+rs.getString(40)+"</R1>");
					out.print("<R1>"+rs.getString(41)+"</R1>");
					out.print("<R1>"+rs.getString(42)+"</R1>");
					out.print("<R1>"+rs.getString(43)+"</R1>");
					out.print("<R1>"+rs.getString(44)+"</R1>");
					out.print("<R1>"+rs.getString(45)+"</R1>");
					out.print("<R1>"+rs.getString(46)+"</R1>");
					out.print("<R1>"+rs.getString(47)+"</R1>");
					out.print("<R1>"+rs.getString(48)+"</R1>");
					out.print("<R1>"+rs.getString(49)+"</R1>");
					out.print("<R1>"+rs.getString(50)+"</R1>");
					out.print("<R1>"+rs.getString(51)+"</R1>");
					out.print("<R1>"+rs.getString(52)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     	}	
			//--------------------Purpose    :Bussiness Sector Code validation------------------------------------------------
		  //------------------- Added By   :Delanjali------------------------------------------------------
		  //--------------------Date       :26-07-2006---------------------------------------------------------

				
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_bussniess_sectors")){
			
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status");
				
			rs= stmt.executeQuery ("SELECT SECTOR_CODE,DESCRIPTION,DEFAULT_VALUE FROM LAKDL.AF_CO_MAS_BUSINESS_SECTOR"+
			" WHERE UPPER(SECTOR_CODE)=UPPER('"+m_val+"')  ");
			
			
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     	}
				
			//--------------------ID         :1.72 Discount Marketing Team Process------------------------------------------------
			//--------------------Purpose    :Team Code validation------------------------------------------------
		  //------------------- Added By   :Nuwan De Silva------------------------------------------------------
		  //--------------------Date       :26-07-2006---------------------------------------------------------
				
				else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_assign_members")){
			
			String m_val = req.getParameter("data_val").trim();
			//	String m_status = req.getParameter("ac_status");	
				
			//rs= stmt.executeQuery ("SELECT TEAM_ID FROM LAKDL.AF_CO_MAS_TEAM_MEMBERS"+
			//" WHERE UPPER(TEAM_ID)=UPPER('"+m_val+"') ");
				
				
				
				
				
			rs= stmt.executeQuery (" SELECT "+
      " DISTINCT A.TEAM_ID, "+
			" B.TEAM_DESC, "+
      " B.TEAM_HEAD, "+
      " B.DIVISION_CODE, "+
      " B.SUB_DIVISION_CODE "+
      " FROM LAKDL.AF_CO_MAS_TEAM_MEMBERS A,LAKDL.AF_CO_MAS_TEAMS B "+
			" WHERE A.TEAM_ID=B.TEAM_ID AND A.TEAM_ID LIKE UPPER('"+m_val+"') ");
				
				
							
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     	}	

				
			//--------------------ID         :1.78 Follow up Category Creation Process------------------------------------------------
		  //--------------------Purpose    :Follow up Category Code validation------------------------------------------------
			//------------------- Added By   :Delanjali------------------------------------------------------
		  //--------------------Date       :26-07-2006---------------------------------------------------------
				
		
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_follow_up_action_category")){
			
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status");	
				
			rs= stmt.executeQuery ("SELECT CATEGORY_CODE,CATEGORY_NAME,DEFAULT_VALUE FROM LAKDL.AF_CO_MAS_FOLLOWUP_CATEGORY"+
			" WHERE UPPER(CATEGORY_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R1>"+rs.getString(2)+"</R1>");
					out.print("<R1>"+rs.getString(3)+"</R1>");
					//out.print("<R1>"+rs.getString(4)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     	}
				
				
			//--------------------ID         :1.77 Discount Rate Creation Process------------------------------------------------
		  //--------------------Purpose    :Rate validation------------------------------------------------
			//------------------- Added By   :Delanjali------------------------------------------------------
		  //--------------------Date       :26-07-2006---------------------------------------------------------
				
		
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_discount_rate_process")){
			
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status");	
				
			rs= stmt.executeQuery ("SELECT RATE,TO_CHAR(FROM_DATE,'DD-MM-YYYY')FROM LAKDL.AF_CO_MAS_DISCOUNT_RATE"+
			" WHERE UPPER(RATE)=UPPER('"+m_val+"')");
				//AND ACTIVE_STATUS='"+m_status+"' "
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R1>"+rs.getString(2)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     	}	
				
				
					
			//--------------------ID         :1.72 Marketing Team Process------------------------------------------------
			//--------------------Purpose    :Team Code validation------------------------------------------------
		  //------------------- Added By   :Nuwan De Silva------------------------------------------------------
		  //--------------------Date       :26-07-2006---------------------------------------------------------
				
				
				
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_assign_team")){
			
			String m_val = req.getParameter("data_val").trim();
			//String m_status = req.getParameter("ac_status");	
				
			rs= stmt.executeQuery ("SELECT A.USER_ID,B.NAME,B.EMP_ID,B.DIVISION_CODE FROM LAKDL.AF_CO_MAS_TEAM_MEMBERS A,LAKDL.CO_CO_MAS_USER B "+
			" WHERE UPPER(A.TEAM_ID)=UPPER('"+m_val+"') AND UPPER(A.USER_ID)=UPPER(B.USER_ID)");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     	}
	
				
			//---------------------ID  			:1.71 Variable Interest Base Process ---------------------------------//
			//---------------------Purpose 	:To validate the Base Code-----------------------------------
			//---------------------Name     :Delanjali---------------------------------------------------
			//---------------------Date     :2006 07 27--------------------------------------------------

			
				else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_variable_interest_base_process")){
			
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");	
				
				rs= stmt.executeQuery ("SELECT BASE_CODE,DESCRIPTION,RATE FROM LAKDL.AF_CO_MAS_INTEREST_BASE "+
				" WHERE UPPER(BASE_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R1>"+rs.getString(2)+"</R1>");
					out.print("<R1>"+rs.getString(3)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}

					
			//---------------------ID  			:1.54 Maintenance Rate Creation Process  ---------------------------------//
			//---------------------Purpose 	:To validate the Make Code-----------------------------------
			//---------------------Name     :Delanjali---------------------------------------------------
			//---------------------Date     :2006 07 27--------------------------------------------------

		
				else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_maintenance_rate")){
			
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT MAKE_CODE,SUB_MODEL_CODE,CHARGE_SUB_CODE,MILEAGE_CODE,INCREASE_DECREASE,AMOUNT,DEFAULT_VALUE"+
				" FROM LAKDL.AF_CO_MAS_MAINTENANCE_RATE "+
				" WHERE UPPER(MAKE_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R1>"+rs.getString(2)+"</R1>");
					out.print("<R1>"+rs.getString(3)+"</R1>");
					out.print("<R1>"+rs.getString(4)+"</R1>");
					out.print("<R1>"+rs.getString(5)+"</R1>");
					out.print("<R1>"+rs.getString(6)+"</R1>");
					out.print("<R1>"+rs.getString(7)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_make_creation1")){
			
				String m_val = req.getParameter("data_val").trim();
				
				rs= stmt.executeQuery ("SELECT MAKE_CODE FROM LAKDL.AF_CO_MAS_MAKE "+
				" WHERE UPPER(MAKE_CODE)=UPPER('"+m_val+"')"+
				"AND UPPER(MAKE_CODE)NOT IN (SELECT MAKE_CODE FROM LAKDL.AF_CO_MAS_MAINTENANCE_RATE)");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
				
			
			//---------------------ID  			:Holiday Process  ---------------------------------//
			//---------------------Purpose 	:Holiday_Date Validation----------------------------------
			//---------------------Name     :Delanjali---------------------------------------------------
			//---------------------Date     :2006 07 28--------------------------------------------------

		
				else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_holidays")){
			
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT TO_CHAR(HOLIDAY_DATE,'DD-MM-YYYY'),DESCRIPTION FROM LAKDL.CO_CO_MAS_HOLIDAY "+
				" WHERE TO_CHAR(HOLIDAY_DATE,'DD-MM-YYYY')='"+m_val+"' AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R1>"+rs.getString(2)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
					
		//----------------------ID      :1.74 Early termination Charge Process -----------------------------------//
		//----------------------Purpose :Early Termination  -------------------------------------------------//
		//----------------------Name    :Nuwan De Silva----------------------------------------------------------//
		//----------------------Date    :31-07-2006--------------------------------------------------------//
			
			
				else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_early_termination_charge")){
			
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");	
				
				rs= stmt.executeQuery ("SELECT TERMINATION_TYPE,DESCRIPTION,AMOUNT FROM LAKDL.AF_CO_MAS_EARLY_TERMI_CHARGE "+
				" WHERE UPPER(TERMINATION_TYPE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			//----------------------ID    : -----------------------------------//
		//----------------------Purpose :Assest Usage  -------------------------------------------------//
		//----------------------Name    :Nuwan De Silva----------------------------------------------------------//
		//----------------------Date    :31-07-2006--------------------------------------------------------//
			
			
				else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_asset_usage_type")){
			
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");	
				
				rs= stmt.executeQuery ("SELECT USAGE_TYPE,NVL(DESCRIPTION,'N/A'),DEFAULT_VALUE FROM LAKDL.AF_CO_MAS_ASSET_USAGE_TYPE "+
				" WHERE UPPER(USAGE_TYPE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			//----------------------ID    : -----------------------------------//
		//----------------------Purpose :Repament Type  -------------------------------------------------//
		//----------------------Name    :Nuwan De Silva----------------------------------------------------------//
		//----------------------Date    :31-07-2006--------------------------------------------------------//
			
			
				else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_repayment_method")){
			
				String m_val = req.getParameter("data_val").trim();
					String m_status = req.getParameter("ac_status");	
				
				rs= stmt.executeQuery ("SELECT REPAYMENT_TYPE,NVL(DESCRIPTION,'N/A'),DEFAULT_VALUE FROM LAKDL.AF_CO_MAS_REPAYMENT_METHOD "+
				" WHERE UPPER(REPAYMENT_TYPE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			//----------------------ID    : -----------------------------------//
		//----------------------Purpose :Sub Product  -------------------------------------------------//
		//----------------------Name    :Nuwan De Silva----------------------------------------------------------//
		//----------------------Date    :31-07-2006--------------------------------------------------------//
			
			
				else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_sub_product")){
			
				String m_val = req.getParameter("data_val").trim();
					String m_status = req.getParameter("ac_status");	
				
				rs= stmt.executeQuery ("SELECT SUB_PRODUCT_CODE,DESCRIPTION,PRODUCT_CODE,DEFAULT_VALUE FROM LAKDL.AF_CO_MAS_SUB_PRODUCT "+
				" WHERE UPPER(SUB_PRODUCT_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"'  ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			//----------------------ID    : -----------------------------------//
		//----------------------Purpose :Product  -------------------------------------------------//
		//----------------------Name    :Nuwan De Silva----------------------------------------------------------//
		//----------------------Date    :31-07-2006--------------------------------------------------------//
			
			
				else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_product")){
			
				String m_val = req.getParameter("data_val").trim();
					String m_status = req.getParameter("ac_status");	
				
				rs= stmt.executeQuery ("SELECT PRODUCT_CODE FROM LAKDL.AF_CO_MAS_PRODUCT "+
				" WHERE UPPER(PRODUCT_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"'  ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_pricing_default_val")){
			
			//	String m_val = req.getParameter("data_val");
				
				rs= stmt.executeQuery ("SELECT INTEREST_RATE,VAT_PER,VAT_APP,PERIOD,NVL(AMOUNT,0) FROM LAKDL.AF_CO_MAS_PRICING_DEFAULT_VAL ");
				//" WHERE UPPER(INTEREST_RATE)=UPPER('"+m_val+"') ");
				
	      	
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_termination_rate")){
			
			//	String m_val = req.getParameter("data_val");
				
				rs= stmt.executeQuery ("SELECT MIN_CHARGE,MAX_CHARGES FROM LAKDL.AF_CO_MAS_TERMNATION_RATE ");
				//" WHERE UPPER(INTEREST_RATE)=UPPER('"+m_val+"') ");
				
	      	
    
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}	
			
				
	
	
		//--------------------- ID  			:1.42 Vendor Creation Process ---------------------------------//
		//---------------------Purpose 	  :To validate the Vendor Code-----------------------------------
		//---------------------Name       :Delanjali---------------------------------------------------
		//---------------------Date       :01-08-2006--------------------------------------------------
		
				
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_vendor_creation1")){
				String m_val = req.getParameter("data_val").trim();
				//String m_stat = req.getParameter("hid_st1");
				String m_status = req.getParameter("ac_status");						

				rs= stmt.executeQuery ("SELECT VENDOR_CODE,NAME,CATEGORY,TYPE,ACTIVE_STATUS,DEFAULT_VALUE FROM LAKDL.AF_CO_MAS_VENDORS "+
				" WHERE UPPER(VENDOR_CODE)=UPPER('"+m_val+"') "+
				"AND ACTIVE_STATUS=UPPER('"+m_status+"')");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R1>"+rs.getString(2)+"</R1>");
					out.print("<R1>"+rs.getString(3)+"</R1>");
					out.print("<R1>"+rs.getString(4)+"</R1>");
					out.print("<R1>"+rs.getString(5)+"</R1>");
					out.print("<R1>"+rs.getString(6)+"</R1>");

					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}	
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_id_no")){
				String m_val = req.getParameter("data_val").trim();
				//String m_stat = req.getParameter("hid_st1");
				

				rs= stmt.executeQuery ("SELECT ID_NO FROM LAKDL.AF_CO_MAS_VENDOR_LOCATION "+
				" WHERE UPPER(ID_NO)=UPPER('"+m_val+"') ");
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}	
			
		//--------------------- ID  			:user screen Creation Process ---------------------------------//
		//---------------------Purpose 	  :row id-----------------------------------
		//---------------------Name       :nuwan de silva---------------------------------------------------
		//---------------------Date       :03-08-2006--------------------------------------------------
			
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_user_screen_row_id")){
			

				rs= stmt.executeQuery ("SELECT MAX(ROW_ID) FROM LAKDL.CO_CO_MAS_USER_SCREEN ");
				
							
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}	
		
		//--------------------- ID  			:user screen Creation Process ---------------------------------//
		//---------------------Purpose 	  :Screen Name-----------------------------------
		//---------------------Name       :nuwan de silva---------------------------------------------------
		//---------------------Date       :03-08-2006--------------------------------------------------
		
			
				else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_user_screen")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");	
				

				rs= stmt.executeQuery ("SELECT SCREEN_NAME,NVL(DIVISION_CODE,'N/A'),NVL(DIVISION_SUB_CODE,'N/A'),NVL(OPTION_NAME,'N/A'),"+
				" NVL(SUB_OPTION1,'N/A'),NVL(SUB_OPTION2,'N/A'),NVL(SUB_OPTION3,'N/A'),NVL(SUB_OPTION4,'N/A'),NVL(ROW_ID,0),NVL(SCREEN_URL,'N/A'),NVL(DISPLAY_STATUS,'N/A'),NVL(SCREEN_LEVEL,0), "+
				" NVL(SUB_OPTION_STATUS,'N/A'),NVL(DISPLAY_NAME,'N/A'),NVL(OPTION_ID,0)"+
				" FROM LAKDL.CO_CO_MAS_USER_SCREEN "+
				" WHERE UPPER(SCREEN_NAME)=UPPER('"+m_val+"')  AND DISPLAY_STATUS='"+m_status+"'  ");
				
    		out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");
					out.print("<R6>"+rs.getString(6)+"</R6>");
					out.print("<R7>"+rs.getString(7)+"</R7>");
					out.print("<R8>"+rs.getString(8)+"</R8>");
					out.print("<R9>"+rs.getString(9)+"</R9>");
					out.print("<R10>"+rs.getString(10)+"</R10>");
					out.print("<R11>"+rs.getString(11)+"</R11>");
					out.print("<R12>"+rs.getString(12)+"</R12>");
					out.print("<R13>"+rs.getString(13)+"</R13>");
					out.print("<R14>"+rs.getString(14)+"</R14>");
					out.print("<R15>"+rs.getString(15)+"</R15>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}	
			
			
						
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_performa_invoice")){
			

				rs= stmt.executeQuery ("SELECT INVOICE_NO,APPLICATION_NO,ASSET_ID,ENGINE_NO,MODEL_CODE "+
				"CHASSIS_NO,REG_NO,REG_DATE,PRICING_NO,SUB_MODEL_CODE,COLOUR,SEATING_CAPACITY,NET_PRICE, "+
				"VAT,TOTAL_AMOUNT,TO_BE_DELIVERD_TO,VALUE,CURR_CODE "+
				"FROM LAKDL.AF_CO_PRO_APP_INVOICE_DETAILS ");
	
							
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");
					out.print("<R6>"+rs.getString(6)+"</R6>");
					out.print("<R7>"+rs.getString(7)+"</R7>");
					out.print("<R8>"+rs.getString(8)+"</R8>");
					out.print("<R9>"+rs.getString(9)+"</R9>");
					out.print("<R10>"+rs.getString(10)+"</R10>");
					out.print("<R11>"+rs.getString(11)+"</R11>");
					out.print("<R12>"+rs.getString(12)+"</R12>");
					out.print("<R13>"+rs.getString(13)+"</R13>");
					out.print("<R14>"+rs.getString(14)+"</R14>");
					out.print("<R15>"+rs.getString(15)+"</R15>");
					out.print("<R16>"+rs.getString(16)+"</R16>");
					out.print("<R17>"+rs.getString(17)+"</R17>");
					out.print("<R18>"+rs.getString(18)+"</R18>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}	
			
			
			
			
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_asset_details")){
			  String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");	

		rs= stmt.executeQuery ("SELECT ASSET_ID FROM LAKDL.AF_CO_PRO_ASSET_DETAILS "+
		
			" WHERE UPPER(ASSET_ID)=UPPER('"+m_val+"')  AND STATUS='"+m_status+"'  ");
				
							
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}	
			
			
			
			//---------------------  ID  		:2.4.7 Valuation Process---------------------------------//
			//---------------------Purpose 	:fill the Fual Types according to model-----------------------------------
			//---------------------Name     :delanjli--------------------------------------------------
			//---------------------Date     :08-08-2006--------------------------------------------------
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_fual2")){
			
			String m_val = req.getParameter("data_val").trim();
			
			rs=stmt.executeQuery("SELECT A.CODE,A.DESCRIPTION "+
														"FROM LAKDL.AF_CO_MAS_FUEL_TYPE A,LAKDL.AF_CO_MAS_MODEL B "+
														"WHERE B.FUEL_TYPE=A.CODE "+
														"AND B.MODEL_CODE LIKE UPPER('"+m_val+"%')"+
														"AND B.ACTIVE_STATUS='Y'");
		
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R1>"+rs.getString(2)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }

			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_inspect1")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				
				rs= stmt.executeQuery ("SELECT A.MAKE_CODE,A.MAKE_DESC,A.ITEM_SUB_CAT,C.ITEM_CAT_CODE FROM LAKDL.AF_CO_MAS_MAKE A,LAKDL.AF_CO_MAS_ITEM_CATEGORY B,"+
				" LAKDL.AF_CO_MAS_ITEM_SUB_CATEGORY C"+
				" WHERE A.ITEM_SUB_CAT=C.ITEM_SUB_CAT"+
				" AND C.ITEM_CAT_CODE LIKE UPPER('"+m_val+"%')"+
				"	AND B.ITEM_CAT_CODE=C.ITEM_CAT_CODE");


				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R1>"+rs.getString(2)+"</R1>");
					out.print("<R1>"+rs.getString(3)+"</R1>");
					out.print("<R1>"+rs.getString(4)+"</R1>");

					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }

				else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_inspect2")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				
				rs= stmt.executeQuery ("SELECT A.FILED_CODE,B.DESCRIPTION"+
    		" FROM LAKDL.AF_CO_MAS_FILEDS_APPLICABLE A,LAKDL.AF_CO_MAS_FILEDS B"+
    		" WHERE A.FILED_CODE=B.FILED_CODE"+
				" AND A.ITEM_CATEGORY = UPPER('"+m_val+"') AND A.ACTIVE_STATUS='Y'" );
				


				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R1>"+rs.getString(2)+"</R1>");
					//out.print("<R1>"+rs.getString(3)+"</R1>");
				//	out.print("<R1>"+rs.getString(4)+"</R1>");

					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }


			
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_fuel_type1")){
			
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status");	
									
			rs= stmt.executeQuery ("SELECT CODE,DESCRIPTION FROM LAKDL.AF_CO_MAS_FUEL_TYPE "+
			" WHERE ACTIVE_STATUS='Y' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					//out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }

		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_inspect3")){
			
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				 rs= stmt.executeQuery ("SELECT  A.MODEL_CODE,A.MAKE_CODE,B.ITEM_SUB_CAT,C.ITEM_CAT_CODE "+  
 				"	FROM LAKDL.AF_CO_MAS_MODEL A,LAKDL.AF_CO_MAS_MAKE B,LAKDL.AF_CO_MAS_ITEM_SUB_CATEGORY C"+
 				"	WHERE A.MAKE_CODE=B.MAKE_CODE"+
 				"	AND B.ITEM_SUB_CAT=C.ITEM_SUB_CAT"+
				"	AND A.MODEL_CODE LIKE UPPER('"+m_val+"%')");



				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R1>"+rs.getString(2)+"</R1>");
					out.print("<R1>"+rs.getString(3)+"</R1>");
					out.print("<R1>"+rs.getString(4)+"</R1>");

					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }


			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_inspection_and_valuation_report")){
			
			String m_val = req.getParameter("data_val").trim();
			//String m_val1 = req.getParameter("data_val1").trim();
			String m_status = req.getParameter("ac_status");	
								
			rs= stmt.executeQuery ("SELECT A.VALUATION_NO,A.ASSET_ID,MODEL_CODE,SUB_MODEL_CODE,REG_NO,ENGINE_NO,CHASSIS_NO,NOTES,REMARKS,"+
    	" TYPE_OF_BODY,TO_CHAR(DATE_OF_REG,'DD-MM-YYYY'),VALUE,METER_READING,"+
    	" TO_CHAR(VALUATION_DATE,'DD-MM-YYYY'),COLOUR,FILED_CODE,B.STATUS,REMARK,GENERAL_INDEX,SEATING_CAPACITY,NO_OF_CYLINDERS	"+
 			" FROM LAKDL.AF_CO_PRO_APP_VALUATION A,LAKDL.AF_CO_PRO_APP_VALUATION_DET B"+
 			" WHERE A.VALUATION_NO=B.VALUATION_NO AND A.VALUATION_NO=UPPER('"+m_val+"')"+
 			" AND A.ASSET_ID=B.ASSET_ID AND ACTIVE_STATUS=('"+m_status+"')");
							
				
 
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R1>"+rs.getString(4)+"</R1>");
					out.print("<R2>"+rs.getString(5)+"</R2>");
					out.print("<R3>"+rs.getString(6)+"</R3>");
					out.print("<R1>"+rs.getString(7)+"</R1>");
					out.print("<R2>"+rs.getString(8)+"</R2>");
					out.print("<R3>"+rs.getString(9)+"</R3>");
					out.print("<R1>"+rs.getString(10)+"</R1>");
					out.print("<R2>"+rs.getString(11)+"</R2>");
					out.print("<R3>"+rs.getString(12)+"</R3>");
					out.print("<R1>"+rs.getString(13)+"</R1>");
					out.print("<R2>"+rs.getString(14)+"</R2>");
					out.print("<R3>"+rs.getString(15)+"</R3>");
					out.print("<R1>"+rs.getString(16)+"</R1>");
					out.print("<R2>"+rs.getString(17)+"</R2>");
					out.print("<R3>"+rs.getString(18)+"</R3>");
					out.print("<R1>"+rs.getString(19)+"</R1>");
					out.print("<R2>"+rs.getString(20)+"</R2>");
					out.print("<R3>"+rs.getString(21)+"</R3>");
					//out.print("<R3>"+rs.getString(22)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }

			
				else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_inspect4")){
			
				String m_val = req.getParameter("data_val").trim();
				String m_item = req.getParameter("data_val1").trim();
				String m_status = req.getParameter("ac_status");
				
				 rs= stmt.executeQuery ("SELECT A.FILED_CODE,REPLACE(C.DESCRIPTION,' ','-'),STATUS,REMARK "+
				" FROM LAKDL.AF_CO_PRO_APP_VALUATION_DET A,LAKDL.AF_CO_PRO_APP_VALUATION B ,LAKDL.AF_CO_MAS_FILEDS C"+
				" WHERE A.VALUATION_NO LIKE UPPER('"+m_val+"%') AND A.VALUATION_NO=B.VALUATION_NO AND A.FILED_CODE=C.FILED_CODE"+
	
 				"	UNION "+             
	 			"	SELECT DISTINCT B.FILED_CODE,REPLACE(C.DESCRIPTION,' ','-'),'xx','xx' FROM"+
        " LAKDL.AF_CO_MAS_FILEDS_APPLICABLE B,LAKDL.AF_CO_MAS_FILEDS C"+
        " WHERE (B.FILED_CODE NOT IN(SELECT A.FILED_CODE FROM LAKDL.AF_CO_PRO_APP_VALUATION_DET A WHERE A.VALUATION_NO LIKE UPPER('"+m_val+"%'))"+
        " AND B.ITEM_CATEGORY = UPPER('"+m_item+"')"+
        " AND B.FILED_CODE=C.FILED_CODE)");

				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R1>"+rs.getString(2)+"</R1>");
					out.print("<R1>"+rs.getString(3)+"</R1>");
					out.print("<R1>"+rs.getString(4)+"</R1>");
					//out.print("<R1>"+rs.getString(5)+"</R1>");

					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
			
			//////////////////////////apllcation process//////////////////////////////////////////
			
				else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_Application_Process")){
			
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				 				
				
					rs= stmt.executeQuery ("SELECT CLIENT_CODE, FULL_NAME, TEL_NO, NIC_NO "+
														"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
														"	 WHERE CLIENT_CODE =('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' "+
														" ORDER BY FULL_NAME "); 		
				



				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");

					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
			
			
					else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_Application_Process_data")){
			
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				 				
				
					rs= stmt.executeQuery ("SELECT GUARANTOR_CODE, RELATIONSHIP,PERIOD, TEL_NO"+
														"	 FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR "+
														"	 WHERE APPLICATION_NO =('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' "); 
														
														
					



				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");

					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }*/
			
			
			
				
			else {
			    out.println("Undefined");
			}
			
			
			
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
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

