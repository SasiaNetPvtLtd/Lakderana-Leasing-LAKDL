//===========Created by Dineth Meemenage===================
//===========Date:2008-08-20
//===========Screen Name:AF_MAS_GUAR_THANK_LETTER
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

public class LAKDL_AF_MAS_display_guarantor_details extends javax.servlet.http.HttpServlet {

			ServletOutputStream out = null;
			public String m_chksql;
			Connection conn;
			Statement stmt,stmt1,stmt2,stmt3;
			java.text.NumberFormat nf,nf1;
			public ResultSet rs,rs1,rs2,rs3;
			public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 			try { 
			
							LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
							String m_html_client_url=m_sn_methods.html_client_url.trim(); 
							String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
							String m_fschema_name=m_sn_methods.client_name.trim();
							res.setStatus(HttpServletResponse.SC_OK); 
							res.setContentType("text/html"); 
							conn = m_sn_methods.met_user_validate(req); 
							stmt=conn.createStatement();
							stmt1=conn.createStatement();
							stmt2=conn.createStatement();
							stmt3=conn.createStatement();
							
							m_chksql=req.getParameter("chksql");
							out = res.getOutputStream(); 
							String m_fin_code,m_invoice;
			
							String m_schema_name = m_sn_methods.schema_name;
							
							
							if(m_chksql.equals("request_details")){
							
							
										String m_hid_val=req.getParameter("m_val");
										String m_fin_no=req.getParameter("finance_no");
										String sql1="SELECT  "+
						" COUNT(DISTINCT A.GUARANTOR_CODE) "+ 
						" FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR A, "+ 
						" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
						" WHERE UPPER(A.APPLICATION_NO)=UPPER(B.APPLICATION_NO) AND "+
						" B.FINANCE_NO= '"+m_fin_no+"'"+
						" ORDER BY A.APPLICATION_NO DESC";
						out.println(sql1);
						int val1=0;
						rs1 = stmt1.executeQuery(sql1);
						boolean more2=rs1.next();
						if(more2){
						val1=rs1.getInt(1);
						}
						
						
										
										
										
										String sql2= "SELECT  "+
						
						" NVL(DECODE(CLIENT_TYPE,'I',UPPER(TITLE) || '. ' || UPPER(FULL_NAME),'C',/*'MESS' || '. '  || */UPPER(FULL_NAME)),' ') ,   "+ //1
						" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS1),'C',UPPER(REGISTERED_ADDRESS1)),' '),  "+ //2
						" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS2),'C',UPPER(REGISTERED_ADDRESS2)),' ') , "+ //3
						" NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),' '),  "+ //4
						" NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,  'C',BUSINESS_CERTIFICATE_NO),'-'),   "+ //5
						" NVL(DECODE(CLIENT_TYPE,'I',UPPER(FULL_NAME),'C',UPPER(FULL_NAME)),' ')    "+ //1
						" FROM "+m_schema_name+".AF_CO_MAS_CLIENT  "+
						" WHERE   CLIENT_CODE IN  "+
						" (SELECT  "+
						" GUARANTOR_CODE "+ 
						" FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR A, "+ 
						" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
						" WHERE UPPER(A.APPLICATION_NO)=UPPER(B.APPLICATION_NO) AND "+
						" B.FINANCE_NO= '"+m_fin_no+"')";
						  
							
						rs2 = stmt2.executeQuery(sql2);
						boolean more=rs2.next();
						if(more){
						out.println("<table class=table border='0' width='100%' >");
						
						out.println("<tr class=pdn_txtpos2 align='left'>");
						out.println("<td  width='20%'  >Full Name</td>");
          	out.println("<td  width='20%'  >Address1</td>");
						out.println("<td  width='20%'  >Address2</td>");
						out.println("<td  width='20%'  >City</td>");
						out.println("<td  width='20%'  >Letter</td>");
						out.println("</tr>");
						int j = 0;
					
					while(more){
					
					if(j>0 && j%2==1){
	  out.println("<tr class=tr_input1 >");
		}
		else{
		out.println("<tr class=tr_input >");
		}
					
					out.println("<td width='20%'  align='left'>"+rs2.getString(1) +"</td>");
					out.println("<td width='20%'  align='left'>"+rs2.getString(2) +"</td>");
          out.println("<td width='20%'  align='left'>"+rs2.getString(3) +"</td>");
          out.println("<td width='20%'  align='left'>"+rs2.getString(4) +"</td>");
					out.println("<td width='20%'  align='center'><input type=button name=\"letter_generation_but\" value=\"Generate\"></td>");
					out.println("</tr>");
					more=rs.next();
					j=j+1;
		
		
					}
					out.println("</table>");
					out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
					out.println("<tr><td>");
					out.println("<td ><input type=hidden name=hid_no_val value=\""+val1+"\"></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
						}
						
						
						
						
						
						
			
								
							}
 					}catch (Exception ex) {
						try{out.println("Error:"+ex.toString());}catch(Exception e){}
					}
					finally{
						if(out!=null){try{out.close();  }catch(Exception e){}}
					}
			
			
			}



}

