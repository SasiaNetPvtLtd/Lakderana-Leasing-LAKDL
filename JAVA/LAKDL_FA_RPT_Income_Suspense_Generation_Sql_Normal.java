import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
       
// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006

public class LAKDL_FA_RPT_Income_Suspense_Generation_Sql_Normal extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt1;
	CallableStatement callstmt;
	java.text.NumberFormat nf;
    
  public ResultSet rs1;
	public String m_chksql;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		try {
		
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			//**************************************************************					
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
            
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			res.setHeader("Cache-Control", "No-Cache");
     	res.setDateHeader("Expires", 0);

			ServletOutputStream out = res.getOutputStream();

			m_chksql=req.getParameter("chksql");
			stmt1=conn.createStatement();

			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
		  else if(m_chksql.equals("LOAD_SUSPENSE_DETAILS")){
																
				String m_date=req.getParameter("DATE");
				String m_string="";		
				String m_date1="" ,m_date2="",m_date3="",m_date4="";
				String m_month1="" ,m_month2="",m_month3="",m_month4="";
				
         rs1= stmt1.executeQuery( " SELECT  TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DD-MM-YYYY'),                  "+
					                        "         TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1),'DD-MM-YYYY') ,  "+
																  "         TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-2),'DD-MM-YYYY') ,  "+
																	"         TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-3),'DD-MM-YYYY') ,  "+
																  "         TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MON') ,                        "+
																	"         TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1),'MON') ,         "+
																	"         TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-2),'MON') ,         "+
																	"         TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-3),'MON')           "+
					                       "  FROM    DUAL ");
																	
					if(rs1.next()){
					m_date1=rs1.getString(1);
					m_date2=rs1.getString(2);
					m_date3=rs1.getString(3);
					m_date4=rs1.getString(4);
					
					m_month1=rs1.getString(5);
					m_month2=rs1.getString(6);
					m_month3=rs1.getString(7);
					m_month4=rs1.getString(8);
					}	
					
					rs1= stmt1.executeQuery(" SELECT "+
					" FACILITY_NO, "+ //1
					" CLIENT_CODE, "+ //2
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE), "+ //3
					" CREDIT_LIMIT, "+ //4
					" ROUND("+m_schema_name+".FA_CLIENT_AV_LOAN_BAL(CLIENT_CODE,FACILITY_NO,'"+m_date+"'),2) CUR_BAL, "+ //5
					" TO_CHAR(FACILITY_START_DATE,'DD-MM-YYYY'), "+ //6
					" TO_CHAR(FACILITY_END_DATE,'DD-MM-YYYY') ,"+ //7
					" "+m_schema_name+".FA_CLIENT_MOVEMENT(CLIENT_CODE,FACILITY_NO,'"+m_date4+"')*100 RATE1 , "+ //8
					" "+m_schema_name+".FA_CLIENT_MOVEMENT(CLIENT_CODE,FACILITY_NO,'"+m_date3+"')*100 RATE1 , "+ //9
					" "+m_schema_name+".FA_CLIENT_MOVEMENT(CLIENT_CODE,FACILITY_NO,'"+m_date2+"')*100 RATE2 , "+ //11
					" "+m_schema_name+".FA_CLIENT_MOVEMENT(CLIENT_CODE,FACILITY_NO,'"+m_date1+"')*100 RATE3  "+ //11
					" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
					" WHERE FACILITY_STATUS='Y'  "+
					" AND   SUS_STATUS IS NULL  "+
					" ORDER BY FACILITY_NO ");



				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td rowspan=2 width='12%' ><DIV class=div_input><b>Facility No</b></DIV></td>";
				//m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Code</b></DIV></td>";
				m_string=m_string+"<td rowspan=2 width='15%' ><DIV class=div_input><b>Name</b></DIV></td>";
				m_string=m_string+"<td rowspan=2 width='10%' ><DIV class=div_input><b>Credit Limit</b></DIV></td>"; 
				m_string=m_string+"<td rowspan=2 width='10%' ><DIV class=div_input><b>Cur Ac. Balance</b></DIV></td>"; 
				m_string=m_string+"<td rowspan=2 width='10%' ><DIV class=div_input><b>Faciliti Start Date</b></DIV></td>"; 
				m_string=m_string+"<td rowspan=2 width='10%' ><DIV class=div_input><b>Faciliti End Date</b></DIV></td>"; 
				//m_string=m_string+"<td rowspan=2 width='3%' ><DIV class=div_input><b>"+m_month4+"</b></DIV></td>"; //
				//m_string=m_string+"<td rowspan=2 width='3%' ><DIV class=div_input><b>"+m_month3+"</b></DIV></td>"; //
				//m_string=m_string+"<td rowspan=2 width='3' ><DIV class=div_input><b>"+m_month2+"</b></DIV></td>";// 
				//m_string=m_string+"<td rowspan=2 width='3%' ><DIV class=div_input><b>"+m_month1+"</b></DIV></td>";// 
				m_string=m_string+"<td colspan=4 width='12%' align='center'><DIV class=div_input><b>Movement %</b></DIV></td>"; //
				m_string=m_string+"<td rowspan=2 width='15%' ><DIV class=div_input><b>Comments</b></DIV></td>";
				m_string=m_string+"<td rowspan=2 width='10%' ><DIV class=div_input><b>Eff Date [DD-MM-YYYY]</b></DIV></td>";
				m_string=m_string+"<td rowspan=2 width='5%' ><DIV class=div_input><b>Suspense</b></DIV></td>";
				m_string=m_string+"</tr>";
				
				m_string=m_string+"<tr>";
				m_string=m_string+"<td  width='3%' ><DIV class=div_input><b>"+m_month4+"</b></DIV></td>"; //
				m_string=m_string+"<td  width='3%' ><DIV class=div_input><b>"+m_month3+"</b></DIV></td>"; //
				m_string=m_string+"<td  width='3' ><DIV class=div_input><b>"+m_month2+"</b></DIV></td>";// 
				m_string=m_string+"<td  width='3%' ><DIV class=div_input><b>"+m_month1+"</b></DIV></td>";// 
				m_string=m_string+"</tr>";

				int chk_nums=0;
				
				int j=1;
								
				while(rs1.next()){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='12%' class=div_input><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FACILITY_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\">"+rs1.getString(1)+"</td>";
					//m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(2)+"</td>";
					m_string=m_string+"<td width='15%' class=div_input  onClick=\"show_client('"+rs1.getString(2)+"')\" style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(2)+"\"><U>"+rs1.getString(3)+"</U></td>";
					m_string=m_string+"<td width='10%' class=div_input>"+nf.format(rs1.getDouble(4))+"</td>";
					m_string=m_string+"<td width='10%' class=div_input>"+nf.format(rs1.getDouble(5))+"</td>";
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(6)+"</td>"; 
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(7)+"</td>"; 
					m_string=m_string+"<td width='3%' bgcolor=\"#CC66FF\" class=div_input>"+rs1.getString(8)+"</td>"; 
					m_string=m_string+"<td width='3%' bgcolor=\"#CC66FF\" class=div_input>"+rs1.getString(9)+"</td>"; 
					m_string=m_string+"<td width='3%' bgcolor=\"#CC99FF\" class=div_input>"+rs1.getString(10)+"</td>"; 
					m_string=m_string+"<td width='3%' bgcolor=\"#CCCCFF\" class=div_input>"+rs1.getString(11)+"</td>"; 
					m_string=m_string+"<td width='15%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_COMMENT_"+chk_nums+"' VALUE=\"\" maxlength=\"200\"></td>"; 
					
					m_string=m_string+"<td width='10%' ><INPUT TYPE='TEXT' class='txt_input5' NAME='TXT_DD_"+chk_nums+"' STYLE=\"{WIDTH:20PX;}\" maxlength=\"2\">"; 
					m_string=m_string+"<INPUT TYPE='TEXT' class='txt_input5' NAME='TXT_MM_"+chk_nums+"' STYLE=\"{WIDTH:20PX;}\" maxlength=\"2\">"; 
					m_string=m_string+"<INPUT TYPE='TEXT' class='txt_input5' NAME='TXT_YY_"+chk_nums+"' STYLE=\"{WIDTH:40PX;}\" maxlength=\"4\"></TD>"; 
					
					m_string=m_string+"<td width='5%'  align='center' class=div_input><INPUT TYPE='checkbox'  NAME='TXT_SUS_STATUS_"+chk_nums+"' VALUE=\"\"onClick=\"validate_suspense_status(this)\" ></td>"; //class='txt_input'
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
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

