import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; //ok
//import CSAMAC_wbacc_methods;
import java.util.*;
import java.text.*;
import oracle.jdbc.driver.*;


public class LAKDL_users_frame2 extends javax.servlet.http.HttpServlet {
  					
Connection conn;
Statement stmt;
java.text.NumberFormat nf;

public ResultSet rs;
public ResultSet rs1,rs2,rs3,rs5,rs4,rs6;
PreparedStatement pstmt;
CallableStatement callstmt1;
public String m_chksql;
public synchronized void service(HttpServletRequest req, HttpServletResponse res)
throws IOException
{

try {
//************************************************************	
LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
conn = con_method.met_user_validate(req); 
String m_html_client_url = con_method.html_client_url;
String m_schema_name = con_method.schema_name;
String m_servlet_client_url=con_method.servlet_client_url;
String m_client_name=con_method.client_name;
String m_client_t3_port=con_method.client_t3_port;
String m_username 						= con_method.username;

//************************************************************

res.setStatus(HttpServletResponse.SC_OK);
res.setContentType("text/html");
ServletOutputStream out = res.getOutputStream();
stmt = conn.createStatement ();
//---------------------------------------------------start--------------------------------------------------

			
			   
			int flag=0;
			int h=0;


				int no_main_opt =0;
				int p=0;

				/*pstmt = conn.prepareStatement("SELECT COUNT( DISTINCT OPTION_NAME) FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN WHERE  DISPLAY_STATUS='Y'");
				rs1 = pstmt.executeQuery();
				boolean more1 =rs1.next();
				if(more1){
					no_main_opt = rs1.getInt(1);  
				}		
				if(rs1!=null){try{rs1.close();}catch(Exception e){}}
				if(pstmt!=null){try{pstmt.close();}catch(Exception e){}}
				*/
				
				//String[] ARRAY_SET1 = new String[no_main_opt];
				String m_div_code = "";  
				String m_div_desc = "";  
				
				pstmt = conn.prepareStatement("SELECT DIVISION_CODE,DESCRIPTION "+
				                              "FROM "+m_schema_name+".CO_CO_MAS_DIVISION "+
																			"WHERE  ACTIVE_STATUS='Y' "+
																			"ORDER BY DESCRIPTION");
				rs6 = pstmt.executeQuery();
				boolean more1 =rs6.next();
				while(more1){
					//no_main_opt = rs1.getInt(1);  
				 m_div_code = rs6.getString(1);  
				 m_div_desc = rs6.getString(2);  
				
				out.println("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"2\" >");
			  out.println("<tr>");
				out.println("<td COLSPANE=4 class=tr_input><B>"+m_div_desc+"<B></TD>");
				out.println("</tr>");
        
				pstmt = conn.prepareStatement ("SELECT DISTINCT OPTION_NAME,OPTION_ID "+
				  "FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
					"WHERE  DISPLAY_STATUS='Y' AND DIVISION_CODE = '"+m_div_code+"' "+
					"ORDER BY  OPTION_NAME ");//,TO_NUMBER(OPTION_ID)
				rs2 = pstmt.executeQuery();
				boolean more2 =rs2.next();
				int i=0;
				while(more2){
				
				
				
				pstmt = conn.prepareStatement ("SELECT COUNT(OPTION_NAME) "+
				  "FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
					"WHERE  DISPLAY_STATUS='Y' AND  SUB_OPTION_STATUS = 'N' AND DIVISION_CODE = '"+m_div_code+"' AND OPTION_NAME = '"+rs2.getString(1)+"' "+
					"ORDER BY  OPTION_NAME ");
					
				rs1 = pstmt.executeQuery();
				boolean more11 =rs1.next();
				int m_option_count = 0;
				if(more11){
					m_option_count = rs1.getInt(1);
				}
				
				
				
					//ARRAY_SET1[i]=rs2.getString(1);
					//i=i+1;
					//more2 =rs2.next();
				//}	
				//if(rs2!=null){try{rs2.close();}catch(Exception e){}}
				//if(pstmt!=null){try{pstmt.close();}catch(Exception e){}}
			
				int k=0;
				int j=0;
				if(flag==0){
					flag++;            
				}
				else{
				}
				out.println("<tr>");
				out.println("<td>");   


				//while (k < no_main_opt){
					out.println("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"2\" >");
					out.println("<tr>");
					out.println("<td width=\"45%\" >&nbsp;&nbsp;</td>");
					out.println("<td width=\"10%\" >&nbsp;</td>");
					out.println("<td width=\"10%\" >&nbsp;</td>");
					out.println("<td width=\"35%\" >&nbsp;</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width=\"45%\" class=tr_input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+rs2.getString(1)+"&nbsp;&nbsp;<input class='but_input' type='button' name='CH_"+rs2.getString(1)+"_"+rs2.getString(2)+"' id = 'CH_"+rs2.getString(1)+"_"+rs2.getString(2)+"' value=\"Check All\" style=\"width:80px;\"  onClick=\"check_section_wise(this,"+m_option_count+");\"></td>"); //THAMALI 2012.02.13
				  out.println("<td width=\"10%\" class=tr_input>View</td>");
					out.println("<td width=\"10%\" class=tr_input>Execute</td>");
					out.println("<td width=\"35%\">&nbsp;</td>");
					out.println("</tr>");
					try{
						
				  /*  callstmt1= conn.prepareCall( "BEGIN "+m_schema_name+".WEBAC_SCREEN_NAME_DISPLAY(:1,:2); END;");
						callstmt1.setString(1,rs2.getString(1));
						callstmt1.registerOutParameter(2,OracleTypes.CURSOR);
						callstmt1.execute();
						rs3 = ((OracleCallableStatement)callstmt1).getCursor(2);
						callstmt1.close();*/
						
						rs3 = stmt.executeQuery (" SELECT DISPLAY_NAME,ROW_ID,SUB_OPTION_STATUS,SCREEN_NAME    "+
						                         " FROM   "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
                                     " WHERE  OPTION_NAME='"+rs2.getString(1)+"' AND SCREEN_LEVEL=1 AND "+
																		 "        DISPLAY_STATUS='Y' AND DIVISION_CODE = '"+m_div_code+"' "+
																		 " ORDER BY TO_NUMBER(ROW_ID) ");

						
						boolean more3 = rs3.next();
						int t = 0;

						while (more3){
							out.println("<tr>");
							out.println("<td width=\"45%\" class=tr_input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+rs3.getString(1)+"</td>");
							if(!rs3.getString(3).equals("Y")){
							    out.println("<td width=\"10%\" ><input type='checkbox' name='chkv_"+rs3.getString(2)+"' id='chkv_"+rs2.getString(1)+"_"+rs2.getString(2)+"_"+t+"' value='off' onclick='check_view("+rs3.getString(2)+")'></td>");//"+rs3.getString(2)+"_<input type='hidden' name='"+rs3.getString(2)+"_hid_screen_view_"+rs3.getString(2)+"_WWW' value='"+rs3.getString(4)+"'>
								out.println("<td width=\"10%\" ><input type='checkbox' name='chke_"+rs3.getString(2)+"' id='chke_"+rs2.getString(1)+"_"+rs2.getString(2)+"_"+t+"' value='off' onclick='check_exec("+rs3.getString(2)+")'><input type='hidden' name='hid_screen_exec_"+rs3.getString(2)+"' value='"+rs3.getString(4)+"'></td>");//onclick='check_exec("+rs3.getString(2)+")'
								//"+rs3.getString(2)+"_<input type='hidden' name='"+rs3.getString(2)+"_hid_screen_exec_"+rs3.getString(2)+"_WWW' value='"+rs3.getString(4)+"'>
                				out.println("<td width=\"35%\" >&nbsp;</td>");
								p=p+1;
								t=t+1;
							}else{
								out.println("<td width=\"55%\" colspan=3>&nbsp;</td>");
							}
							out.println("</tr>");
							
							if(rs3.getString(3).equals("Y")){
							pstmt = conn.prepareStatement("SELECT DISPLAY_NAME,ROW_ID,SUB_OPTION_STATUS,SCREEN_NAME "+
							                               "FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
											                       "WHERE UPPER(SUB_OPTION1)=UPPER(?) AND SCREEN_LEVEL=2 AND "+
																							"OPTION_NAME= ? AND DIVISION_CODE = '"+m_div_code+"'   "+
																							"AND DISPLAY_STATUS='Y' ORDER BY TO_NUMBER(ROW_ID)");
							pstmt.setString(1,rs3.getString(4));
							pstmt.setString(2,rs2.getString(1));
							
							rs4 =pstmt.executeQuery();											
							boolean more4 = rs4.next();
							while (more4){
									out.println("<tr>");
									out.println("<td width=\"45%\" class=tr_input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+rs4.getString(1)+"</td>");
									if(!rs4.getString(3).equals("Y")){
									  out.println("<td width=\"10%\" ><input type='checkbox' name='chkv_"+rs4.getString(2)+"'   id='chkv_"+rs2.getString(1)+"_"+rs2.getString(2)+"_"+t+"' value='off' onclick='check_view("+rs4.getString(2)+")' ></td>");//"+rs4.getString(2)+"_<input type='hidden' name='"+rs4.getString(2)+"_hid_screen_view_"+rs4.getString(2)+"_WWW' value='"+rs4.getString(4)+"'>
										out.println("<td width=\"10%\" ><input type='checkbox' name='chke_"+rs4.getString(2)+"' id='chke_"+rs2.getString(1)+"_"+rs2.getString(2)+"_"+t+"' value='off' onclick='check_exec("+rs4.getString(2)+")'><input type='hidden' name='hid_screen_exec_"+rs4.getString(2)+"' value='"+rs4.getString(4)+"' ></td>");//"+rs4.getString(2)+"_<input type='hidden' name='"+rs4.getString(2)+"_hid_screen_exec_"+rs4.getString(2)+"_WWW' value='"+rs4.getString(4)+"'> // onclick='check_exec("+rs4.getString(2)+")'
	                  					out.println("<td width=\"35%\" ></td>");
										p=p+1;
										t=t+1;
									}else{
										out.println("<td width=\"55%\"  colspan=3></td>");
									}				  
									out.println("</tr>");
									if(rs4.getString(3).equals("Y")){
									 													
										pstmt = conn.prepareStatement("SELECT DISPLAY_NAME,ROW_ID,SUB_OPTION_STATUS,SCREEN_NAME "+
										                               "FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
													                         "WHERE UPPER(SUB_OPTION2)=UPPER(?) AND  "+
																										"SCREEN_LEVEL=3  AND OPTION_NAME=? AND "+
																										"UPPER(SUB_OPTION1)=UPPER(?) AND DIVISION_CODE = '"+m_div_code+"'  AND  "+
																										"DISPLAY_STATUS='Y' ORDER BY TO_NUMBER(ROW_ID) ");
										pstmt.setString(1,rs4.getString(4));
										pstmt.setString(2,rs2.getString(1));
										pstmt.setString(3,rs3.getString(4));
										
										rs5 =pstmt.executeQuery();			  								
										boolean more5 = rs5.next();
										while (more5){																								
											out.println("<tr>");
											out.println("<td width=\"45%\" class=tr_input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+rs5.getString(1)+"</td>"); 
											if(!rs5.getString(3).equals("Y")){
											  out.println("<td width=\"10%\" ><input type='checkbox' name='chkv_"+rs5.getString(2)+"' id='chkv_"+rs2.getString(1)+"_"+rs2.getString(2)+"_"+t+"'  value='off' onclick='check_view("+rs5.getString(2)+")' ></td>");//"+rs5.getString(2)+"_<input type='hidden' name='"+rs5.getString(2)+"_hid_screen_view_"+rs5.getString(2)+"_WWW' value='"+rs5.getString(4)+"'>
												out.println("<td width=\"10%\" ><input type='checkbox' name='chke_"+rs5.getString(2)+"' id='chke_"+rs2.getString(1)+"_"+rs2.getString(2)+"_"+t+"'  value='off' onclick='check_exec("+rs5.getString(2)+")'><input type='hidden' name='hid_screen_exec_"+rs5.getString(2)+"' value='"+rs5.getString(4)+"'></td>");//"+rs5.getString(2)+"_<input type='hidden' name='"+rs5.getString(2)+"_hid_screen_exec_"+rs5.getString(2)+"_WWW' value='"+rs5.getString(4)+"'> //onclick='check_exec("+rs5.getString(2)+")'
	                     						 out.println("<td width=\"35%\" >&nbsp;</td>");
												p=p+1;
												t=t+1;

											}else{
												out.println("<td width=\"55%\" colspan=3>&nbsp;</td>");
											}				  
											out.println("</tr>");
											more5 = rs5.next();
										}
										if(rs5!=null){try{rs5.close();}catch(Exception e){}}
									  if(pstmt!=null){try{pstmt.close();}catch(Exception e){}}
									}//if
									more4 = rs4.next();
								}
								if(rs4!=null){try{rs4.close();}catch(Exception e){}}
								if(pstmt!=null){try{pstmt.close();}catch(Exception e){}}
							}//if
							more3 =rs3.next();
						}		
						if(rs3!=null){try{rs3.close();}catch(Exception e){}}
						if(callstmt1!=null){try{callstmt1.close();}catch(Exception e){}}
					}
					catch(Exception  nir){
						out.println("Exception=>"+nir.toString());
					}
					k=k+1;
					j=j+1;
					i=i+1;
					more2 =rs2.next();
					out.println("</table>");

				}//while
				out.println("</BR>");
				out.println("</BR>");
						
				 more1 =rs6.next();
				}		
				if(rs1!=null){try{rs6.close();}catch(Exception e){}}
				if(pstmt!=null){try{pstmt.close();}catch(Exception e){}}
				
				h=h+1;
			out.println("</td>");   
			out.println("</tr>");
			out.println("</table>");
			out.close();



//--------------------------------------------end-------------------------------------------------------

conn.close();
this.destroy();
}
catch (Exception e) {
try {
conn.close();
}	
catch (Exception eti) {}
ByteArrayOutputStream ostr = new ByteArrayOutputStream();
e.printStackTrace(new PrintStream(ostr));

ServletOutputStream out = res.getOutputStream();
out.println(ostr.toString());
out.close();
}
}
}
