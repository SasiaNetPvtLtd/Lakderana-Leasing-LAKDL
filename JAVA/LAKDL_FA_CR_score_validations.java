// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

  
public class LAKDL_FA_CR_score_validations extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt3;
	CallableStatement callstmt;
	java.text.NumberFormat nf; 
    
  public ResultSet rs,rs1,rs3,rs4,rs5,rs6;
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

			ServletOutputStream out = res.getOutputStream();

			m_chksql=req.getParameter("chksql");
			
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			stmt3=conn.createStatement();
			stmt2=conn.createStatement();

			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
		
			else if(m_chksql.equals("SCORE_DETAILS")){
				
				String m_score_model_code=req.getParameter("model");
				
				String m_string="";
				
				if(!m_score_model_code.equals("")){
				
				m_string="<table align='center' width='100%' class='table'>";
				
				rs= stmt.executeQuery ("SELECT   SCORE_CODE,UPPER(DESCRIPTION) FROM "+m_schema_name+".AF_CR_MAS_SCORE_CATEGORY "+
	 					" WHERE ACTIVE_STATUS='Y' ORDER BY DISPLAY_POSITION ");
				
				int chk_nums=0;
				
				while(rs.next()){
					m_string=m_string+"<tr >";
					m_string=m_string+"<td width='1%'></td>"; 
					m_string=m_string+"<td width='30%' ><DIV class=div_input><b>"+rs.getString(2)+"</b></DIV></td>";
					m_string=m_string+"<td width='2%' ></td>";
					m_string=m_string+"<td width='10%' ></td>"; 
					m_string=m_string+"<td width='10%' ><DIV class=div_input>MIN VALUE</DIV></td>"; 
					m_string=m_string+"<td width='10%' ><DIV class=div_input>MAX VALUE</DIV></td>"; 
					m_string=m_string+"<td width='*%'></td>";
					m_string=m_string+"</tr>";
					
					rs1= stmt1.executeQuery (" SELECT  SCORE_SUB_CODE,DESCRIPTION,MIN_VALUE,MAX_VALUE,DISPALY_POSITION,DIS_STATUS "+
					" FROM ( "+
					" SELECT   SCORE_SUB_CODE,INITCAP(DESCRIPTION) DESCRIPTION,0 MIN_VALUE,0 MAX_VALUE,DISPALY_POSITION,'N' DIS_STATUS  "+
					" FROM "+m_schema_name+".AF_CR_MAS_SCORE_SUB_CATEGORY "+
					" WHERE SCORE_CODE='"+rs.getString(1)+"' AND ACTIVE_STATUS='Y' AND "+
					" SCORE_SUB_CODE NOT IN ( SELECT SCORE_SUB_CODE FROM "+m_schema_name+".AF_CR_MAS_SCORE_MODEL_DETAIL WHERE UPPER(SCORE_MODEL_CODE)=UPPER('"+m_score_model_code+"')) "+
					" UNION "+
					" SELECT  A.SCORE_SUB_CODE,INITCAP(A.DESCRIPTION) DESCRIPTION,NVL(B.MIN_VALUE,0) MIN_VALUE,NVL(B.MAX_VALUE,0) MAX_VALUE,A.DISPALY_POSITION,'Y' DIS_STATUS  "+
					" FROM "+m_schema_name+".AF_CR_MAS_SCORE_SUB_CATEGORY A,"+m_schema_name+".AF_CR_MAS_SCORE_MODEL_DETAIL B "+
					" WHERE A.SCORE_CODE='"+rs.getString(1)+"' AND "+
					" B.SCORE_SUB_CODE=A.SCORE_SUB_CODE AND UPPER(B.SCORE_MODEL_CODE)=UPPER('"+m_score_model_code+"') "+	
					" ) "+
					" ORDER BY DISPALY_POSITION");

					int i=1;
					while(rs1.next()){

						m_string=m_string+"<tr >"; 
						m_string=m_string+"<td width='1%'></td>"; 
						m_string=m_string+"<td width='30%'><DIV class=div_input>"+i+".  "+rs1.getString(2)+"</DIV></td>"; 
						m_string=m_string+"<td width='2%'></td>"; 
						if(rs1.getString(6).equals("Y")){
						m_string=m_string+"<td width='10%'><INPUT TYPE='CHECKBOX' NAME='CHK_"+chk_nums+"' checked><INPUT TYPE='HIDDEN' NAME='HID_"+chk_nums+"' VALUE="+rs1.getString(1)+"></td>"; 
						}
						else{
						m_string=m_string+"<td width='10%'><INPUT TYPE='CHECKBOX' NAME='CHK_"+chk_nums+"'><INPUT TYPE='HIDDEN' NAME='HID_"+chk_nums+"' VALUE="+rs1.getString(1)+"></td>"; 
						}
						m_string=m_string+"<td width='10%'><INPUT TYPE='TEXT' NAME='MIN_"+chk_nums+"' VALUE="+rs1.getString(3)+" onblur='val_num(this)' maxlength='3' SIZE='3' STYLE=\"{text-align:right;}\"  ></td>"; 
						m_string=m_string+"<td width='10%'><INPUT TYPE='TEXT' NAME='MAX_"+chk_nums+"' VALUE="+rs1.getString(4)+" onblur='val_num(this)' maxlength='3' SIZE='3' STYLE=\"{text-align:right;}\" ></td>"; 
						m_string=m_string+"<td width='*%'></td>"; 
						m_string=m_string+"</tr>";
						chk_nums++;
						i++;
					}
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			rs.close();
			rs1.close();
			stmt.close();
			stmt1.close();
				
			}
			
			else if(m_chksql.equals("SCORE_DETAILS_ENTER")){
				
				String m_score_model_code=req.getParameter("model");
				
				String m_string="";
				
				String m_rate_box="";
				String m_rate_vals="";
				if(!m_score_model_code.equals("")){

				rs= stmt.executeQuery (" SELECT  RATING_CODE,DESCRIPTION,(FROM_RAGE+TO_RANGE)/2 FROM "+m_schema_name+".AF_CR_MAS_SCORE_RATING WHERE ACTIVE_STATUS='Y'");
				while(rs.next()){
				m_rate_box=m_rate_box+"<OPTION VALUE=\""+rs.getString(1)+"\">"+rs.getString(2)+"</OPTION>";
				m_rate_vals=m_rate_vals+"<INPUT TYPE=\"HIDDEN\" NAME=\"RATE_VAL_"+rs.getString(1)+"\" VALUE=\""+rs.getString(3)+"\">";
				}
				
				m_string=m_string+m_rate_vals;
				m_string=m_string+"<table align='center' width='100%' class='table'>";
				
				rs= stmt.executeQuery (" SELECT   SCORE_CODE,UPPER(DESCRIPTION) FROM "+m_schema_name+".AF_CR_MAS_SCORE_CATEGORY "+
	 					" WHERE ACTIVE_STATUS='Y' AND "+
						" SCORE_CODE IN(SELECT SCORE_CODE FROM "+m_schema_name+".AF_CR_MAS_SCORE_SUB_CATEGORY "+
						" WHERE ACTIVE_STATUS='Y' AND "+
						" SCORE_SUB_CODE IN ( SELECT SCORE_SUB_CODE FROM "+m_schema_name+".AF_CR_MAS_SCORE_MODEL_DETAIL WHERE UPPER(SCORE_MODEL_CODE)=UPPER('"+m_score_model_code+"'))) "+
						" ORDER BY DISPLAY_POSITION ");
				
				int chk_nums=0;
				int j=1;
				
				while(rs.next()){
					m_string=m_string+"<BR>";
					m_string=m_string+"<tr >";
					m_string=m_string+"<td width='1%'></td>"; 
					m_string=m_string+"<td width='30%' ><DIV class=div_input><b>"+rs.getString(2)+"</b></DIV></td>";
					m_string=m_string+"<td width='10%' ></td>"; 
					m_string=m_string+"<td width='2%' ></td>";
					m_string=m_string+"<td width='10%' ></td>"; 
					m_string=m_string+"<td width='10%' ></td>"; 
					m_string=m_string+"<td width='*%'></td>";
					m_string=m_string+"</tr>";
					
					rs1= stmt1.executeQuery (" SELECT  SCORE_SUB_CODE,DESCRIPTION,MIN_VALUE,MAX_VALUE,DISPALY_POSITION,DIS_STATUS,CAT_STATUS "+
					" FROM ( "+
					" SELECT   SCORE_SUB_CODE,INITCAP(DESCRIPTION) DESCRIPTION,0 MIN_VALUE,0 MAX_VALUE,DISPALY_POSITION,'N' DIS_STATUS,CAT_STATUS  "+
					" FROM "+m_schema_name+".AF_CR_MAS_SCORE_SUB_CATEGORY "+
					" WHERE SCORE_CODE='"+rs.getString(1)+"' AND ACTIVE_STATUS='Y' AND "+
					" SCORE_SUB_CODE NOT IN ( SELECT SCORE_SUB_CODE FROM "+m_schema_name+".AF_CR_MAS_SCORE_MODEL_DETAIL WHERE UPPER(SCORE_MODEL_CODE)=UPPER('"+m_score_model_code+"')) "+
					" UNION "+
					" SELECT  A.SCORE_SUB_CODE,INITCAP(A.DESCRIPTION) DESCRIPTION,NVL(B.MIN_VALUE,0) MIN_VALUE,NVL(B.MAX_VALUE,0) MAX_VALUE,A.DISPALY_POSITION,'Y' DIS_STATUS,A.CAT_STATUS  "+
					" FROM "+m_schema_name+".AF_CR_MAS_SCORE_SUB_CATEGORY A,"+m_schema_name+".AF_CR_MAS_SCORE_MODEL_DETAIL B "+
					" WHERE A.SCORE_CODE='"+rs.getString(1)+"' AND "+
					" B.SCORE_SUB_CODE=A.SCORE_SUB_CODE AND UPPER(B.SCORE_MODEL_CODE)=UPPER('"+m_score_model_code+"') "+	
					" ) "+
					" ORDER BY DISPALY_POSITION");

					int i=1;
					while(rs1.next()){
						if(rs1.getString(6).equals("Y")){
						m_string=m_string+"<tr >"; 
						m_string=m_string+"<td width='1%'></td>"; 
						if(rs1.getString(7).equals("Y")){
						m_string=m_string+"<td width='30%'><DIV class=div_input>"+i+".  "+rs1.getString(2)+"</DIV><INPUT TYPE=\"HIDDEN\" VALUE='"+rs1.getString(1)+"' NAME=\"SUB_CAT_"+j+"\"><INPUT TYPE=\"HIDDEN\" VALUE='"+rs1.getString(4)+"' NAME=\"MAX_"+j+"\"></td>"; 
						m_string=m_string+"<td width='10%'>("+rs1.getString(3)+"-"+rs1.getString(4)+")</td>"; 
						m_string=m_string+"<td width='2%'></td>"; 
						m_string=m_string+"<td width='10%'><DIV class=div_input><SELECT NAME=\"RATE_"+j+"\" class=div_input onchange=\"load_default_score('"+j+"')\">"+m_rate_box+"</SELECT></DIV></td>"; 
						m_string=m_string+"<td width='10%'><DIV id='DIV_TXT_SCORE_"+j+"' class=div_input><INPUT TYPE=\"TEXT\" VALUE=0 NAME=\"SCORE_"+j+"\" maxlength='5' SIZE='5' onblur=\"valdate_values('"+j+"','"+rs1.getString(3)+"','"+rs1.getString(4)+"')\"></DIV></td>"; 
						m_string=m_string+"<td width='*%'><TEXTAREA NAME=\"COMMENT_"+j+"\" class=div_input  maxlength='500'></TEXTAREA></td>"; 
						m_string=m_string+"</tr>";
						chk_nums++;
						j++;
						}
						else{
						m_string=m_string+"<td width='30%'><DIV class=div_input>"+i+".  "+rs1.getString(2)+"</DIV></td>"; 
						m_string=m_string+"<td width='10%'></td>"; 
						m_string=m_string+"<td width='2%'></td>"; 
						m_string=m_string+"<td width='10%'></td>"; 
						m_string=m_string+"<td width='10%'></td>"; 
						m_string=m_string+"<td width='*%'></td>"; 
						m_string=m_string+"</tr>";
						}
						i++;
						}
					}
				}
				m_string=m_string+"</table>";
				m_string=m_string+"<INPUT TYPE=\"HIDDEN\" VALUE='"+j+"' NAME=\"NUM_COLS\">";
				out.println(m_string);
				}
				rs.close();
				rs1.close();
				stmt.close();
				stmt1.close();
			}
			else if(m_chksql.equals("SCORE_DETAILS_EDIT")){
			
				String m_score_model_code=req.getParameter("model");
				String m_facility_no=req.getParameter("facility_no");
				
				String m_string="";
				String m_rate_box="";
				String m_rate_vals="";
				
				if(!m_score_model_code.equals("")){

				rs= stmt.executeQuery (" SELECT  RATING_CODE,DESCRIPTION,(FROM_RAGE+TO_RANGE)/2 FROM "+m_schema_name+".AF_CR_MAS_SCORE_RATING WHERE ACTIVE_STATUS='Y'");
				while(rs.next()){
				m_rate_box=m_rate_box+"<OPTION VALUE=\""+rs.getString(1)+"\">"+rs.getString(2)+"</OPTION>";
				m_rate_vals=m_rate_vals+"<INPUT TYPE=\"HIDDEN\" NAME=\"RATE_VAL_"+rs.getString(1)+"\" VALUE=\""+rs.getString(3)+"\">";
				}
				
				m_string=m_string+m_rate_vals;
				m_string=m_string+"<table align='center' width='100%' class='table'>";
				
				rs= stmt.executeQuery (" SELECT   SCORE_CODE,UPPER(DESCRIPTION) FROM "+m_schema_name+".AF_CR_MAS_SCORE_CATEGORY "+
	 					" WHERE ACTIVE_STATUS='Y' AND "+
						" SCORE_CODE IN(SELECT SCORE_CODE FROM "+m_schema_name+".AF_CR_MAS_SCORE_SUB_CATEGORY "+
						" WHERE ACTIVE_STATUS='Y' AND "+
						" SCORE_SUB_CODE IN ( SELECT SCORE_SUB_CODE FROM "+m_schema_name+".AF_CR_MAS_SCORE_MODEL_DETAIL WHERE UPPER(SCORE_MODEL_CODE)=UPPER('"+m_score_model_code+"'))) "+
						" ORDER BY DISPLAY_POSITION ");
				
				int chk_nums=0;
				int j=1;
				
				while(rs.next()){
					m_string=m_string+"<BR>";
					m_string=m_string+"<tr >";
					m_string=m_string+"<td width='1%'></td>"; 
					m_string=m_string+"<td width='30%' ><DIV class=div_input><b>"+rs.getString(2)+"</b></DIV></td>";
					m_string=m_string+"<td width='10%' ></td>"; 
					m_string=m_string+"<td width='2%' ></td>";
					m_string=m_string+"<td width='10%' ></td>"; 
					m_string=m_string+"<td width='10%' ></td>"; 
					m_string=m_string+"<td width='*%'></td>";
					m_string=m_string+"</tr>";

					rs1= stmt1.executeQuery ("SELECT SCORE_SUB_CODE,DESCRIPTION,MIN_VALUE,MAX_VALUE,"+//4
					" DISPALY_POSITION,DIS_STATUS,CAT_STATUS,RATING_CODE,"+//8
					" SCORE,REMARKS "+//10
					" FROM ( "+
					" SELECT   SCORE_SUB_CODE,INITCAP(DESCRIPTION) DESCRIPTION,0 MIN_VALUE,0 MAX_VALUE,"+
					" DISPALY_POSITION,'N' DIS_STATUS,CAT_STATUS,' ' RATING_CODE,0 SCORE,'-' REMARKS   "+
					" FROM "+m_schema_name+".AF_CR_MAS_SCORE_SUB_CATEGORY "+
					" WHERE SCORE_CODE='"+rs.getString(1)+"' AND ACTIVE_STATUS='Y' AND "+
					" SCORE_SUB_CODE NOT IN ( SELECT SCORE_SUB_CODE FROM "+m_schema_name+".AF_CR_MAS_SCORE_MODEL_DETAIL WHERE UPPER(SCORE_MODEL_CODE)=UPPER('"+m_score_model_code+"')) "+
					" UNION "+
					" SELECT  A.SCORE_SUB_CODE,INITCAP(A.DESCRIPTION) DESCRIPTION,NVL(B.MIN_VALUE,0) MIN_VALUE,NVL(B.MAX_VALUE,0) MAX_VALUE,"+
					" A.DISPALY_POSITION,'Y' DIS_STATUS,A.CAT_STATUS,C.RATING_CODE,C.SCORE,NVL(C.REMARKS,'-') REMARKS  "+
					" FROM "+m_schema_name+".AF_CR_MAS_SCORE_SUB_CATEGORY A,"+
					" "+m_schema_name+".AF_CR_MAS_SCORE_MODEL_DETAIL B, "+
					" "+m_schema_name+".FA_CR_PRO_CRSCORE_DETAIL C "+
					" WHERE A.SCORE_CODE='"+rs.getString(1)+"' "+
					" AND B.SCORE_SUB_CODE=A.SCORE_SUB_CODE "+
					" AND B.SCORE_SUB_CODE=C.SCORE_SUB_CODE "+
					" AND UPPER(B.SCORE_MODEL_CODE)=UPPER('"+m_score_model_code+"') "+	
					" AND FACILITY_NO='"+m_facility_no+"' "+
					" ) "+
					" ORDER BY DISPALY_POSITION");

					int i=1;
					while(rs1.next()){
						if(rs1.getString(6).equals("Y")){
							m_string=m_string+"<tr >"; 
							m_string=m_string+"<td width='1%'></td>"; 
							if(rs1.getString(7).equals("Y")){
								m_string=m_string+"<td width='30%'><DIV class=div_input>"+i+".  "+rs1.getString(2)+"</DIV><INPUT TYPE=\"HIDDEN\" VALUE='"+rs1.getString(1)+"' NAME=\"SUB_CAT_"+j+"\"><INPUT TYPE=\"HIDDEN\" VALUE='"+rs1.getString(4)+"' NAME=\"MAX_"+j+"\"></td>"; 
								m_string=m_string+"<td width='10%'>("+rs1.getString(3)+"-"+rs1.getString(4)+")</td>"; 
								m_string=m_string+"<td width='2%'></td>"; 
								m_string=m_string+"<td width='10%'><DIV class=div_input><SELECT NAME=\"RATE_"+j+"\" class=div_input onchange=\"load_default_score('"+j+"')\">"+m_rate_box+"</SELECT></DIV></td>"; 
								m_string=m_string+"<td width='10%'><DIV id='DIV_TXT_SCORE_"+j+"' class=div_input><INPUT TYPE=\"TEXT\" VALUE='"+rs1.getDouble(9)+"' NAME=\"SCORE_"+j+"\" maxlength='5' SIZE='5' onblur=\"valdate_values('"+j+"','"+rs1.getString(3)+"','"+rs1.getString(4)+"')\"></DIV></td>"; 
								m_string=m_string+"<td width='*%'><TEXTAREA NAME=\"COMMENT_"+j+"\" class=div_input  maxlength='500'>"+rs1.getString(10)+"</TEXTAREA></td>"; 
								m_string=m_string+"</tr>";
								chk_nums++;
								j++;
							}
							else{
								m_string=m_string+"<td width='30%'><DIV class=div_input>"+i+".  "+rs1.getString(2)+"</DIV></td>"; 
								m_string=m_string+"<td width='10%'></td>"; 
								m_string=m_string+"<td width='2%'></td>"; 
								m_string=m_string+"<td width='10%'></td>"; 
								m_string=m_string+"<td width='10%'></td>"; 
								m_string=m_string+"<td width='*%'></td>"; 
								m_string=m_string+"</tr>";
							}
							i++;
						}
					}
				}
				m_string=m_string+"</table>";
				m_string=m_string+"<INPUT TYPE=\"HIDDEN\" VALUE='"+j+"' NAME=\"NUM_COLS\">";
				out.println(m_string);
				}
				rs.close();
				rs1.close();
				stmt.close();
				stmt1.close();
			}
			else if(m_chksql.equals("SCORE_DETAILS_VIEW")){
				String m_score_model_code="";
				//String m_score_model_code=req.getParameter("model");
				String m_facility_no=req.getParameter("facility_no");
				
				String m_string="";
				if(!m_facility_no.equals("")){
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Credit Process - Credit Score Evaluation </TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
				out.println("<br>");	
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>   "); 
				out.println("<tr>"); 
				out.println("<td align='left' class='div_input' width='5%'></td>");
				out.println("<td align='left' class='div_input' width='95%'><B>CREDIT SCORE EVALUATION FOR FACILITY NO: "+m_facility_no+"</B></td>"); 
				out.println("</tr>"); 
				out.println("</table>");
				out.println("<br>");	
				
				rs= stmt.executeQuery (" SELECT  FACILITY_NO,"+
						" CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE) FULL_NAME,"+
						" SCORE_MODEL_CODE,"+
						" NVL(EVAL_USER,'-'), "+
  					" NVL(FINAL_APP_SCORE,0),"+
						" NVL(MODEL_SCORE,0),"+
						" NVL(COMMENTS,'-'),"+
						" NVL(ENT_USER,'-'),"+
						" TO_CHAR(ENT_DATE,'DD-MM-YYYY HH24:MI'),"+
						" APP_STATUS,"+
						" NVL(APP_USER,'-'),"+
						" TO_CHAR(APP_DATE,'DD-MM-YYYY HH24:MI'),"+
						" NVL(APP_COMMENT,'-') "+
						" FROM "+m_schema_name+".FA_CR_PRO_CRSCORE "+
						" WHERE FACILITY_NO='"+m_facility_no+"'");
				if(rs.next()){
					out.println("<table align='center' width='100%' border=0>"); 
					out.println("<tr >"); 
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' ><DIV class=div_input>FACILITY NO </DIV></td>"); 
					out.println("<td width='69%' ><DIV class=div_input>"+rs.getString(1)+"</DIV></td>"); 
					out.println("</tr >"); 
					out.println("<tr >"); 
					out.println("<tr >"); 
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' ><DIV class=div_input>CLIENT CODE</DIV></td>"); 
					out.println("<td width='69%' ><DIV class=div_input>"+rs.getString(2)+"</DIV></td>"); 
					out.println("</tr >"); 
					out.println("<tr >"); 
					out.println("<tr >"); 
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' ><DIV class=div_input>CLIENT NAME</DIV></td>"); 
					out.println("<td width='69%' ><DIV class=div_input>"+rs.getString(3)+"</DIV></td>"); 
					out.println("</tr >"); 
					out.println("<tr >"); 
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' ><DIV class=div_input>SCORE MODEL CODE </DIV></td>"); 
					out.println("<td width='69%' ><DIV class=div_input>"+rs.getString(4)+"</DIV></td>"); 
					out.println("</tr >"); 
					out.println("<tr >"); 
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' ><DIV class=div_input>EVALUVATOR NAME/DATE </DIV></td>"); 
					out.println("<td width='69%' ><DIV class=div_input>"+rs.getString(5)+" / "+rs.getString(10)+"</DIV></td>"); 
					out.println("</tr >"); 
					out.println("<tr >"); 
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' ><DIV class=div_input>SCORE FOR APPLICATION</DIV></td>"); 
					out.println("<td width='69%' ><DIV class=div_input><b>"+nf.format(rs.getDouble(6))+"</b></DIV></td>"); 
					out.println("</tr >"); 
					out.println("<tr >"); 
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' ><DIV class=div_input>SCORE FOR MODEL</DIV></td>"); 
					out.println("<td width='69%' ><DIV class=div_input><b>"+nf.format(rs.getDouble(7))+"</b></DIV></td>"); 
					out.println("</tr >");
					
					double m_act_total=(rs.getDouble(6)/rs.getDouble(7))*100;
					String m_classification="WATCH";
					
					if(m_act_total>80){
					m_classification="EXCELLENT";
					}
					else if(m_act_total>70){
					m_classification="VERY GOOD";
					}
					else if(m_act_total>60){
					m_classification="GOOD";
					}
					else if(m_act_total>50){
					m_classification="SATISFACTORY";
					}
					else if(m_act_total>45){
					m_classification="ADEQUATE";
					}
					else if(m_act_total>40){
					m_classification="ACCEPTABLE";
					}
					else{
					m_classification="WATCH";
					}
					
					out.println("<tr >"); 
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' ><DIV class=div_input>TOTAL</DIV></td>"); 
					out.println("<td width='69%' ><DIV class=div_input><b>"+nf.format(m_act_total)+"</b></DIV></td>"); 
					out.println("</tr >");
					out.println("<tr >"); 
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' ><DIV class=div_input>GRADE</DIV></td>"); 
					out.println("<td width='69%' ><DIV class=div_input><b>"+m_classification+"</b></DIV></td>"); 
					out.println("</tr >");
					out.println("<tr >"); 
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' ><DIV class=div_input>STATUS</DIV></td>"); 
					out.println("<td width='69%' ><DIV class=div_input>"+rs.getString(11)+"</DIV></td>"); 
					out.println("</tr >");
					out.println("<tr >"); 
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' ><DIV class=div_input>COMMENTS </DIV></td>"); 
					out.println("<td width='69%' ><DIV class=div_input>"+rs.getString(8)+"</DIV></td>"); 
					out.println("</tr >"); 
					if(!rs.getString(10).equals("-")){
						out.println("<tr >"); 
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' ><DIV class=div_input>APPROVED NAME/DATE </DIV></td>"); 
						out.println("<td width='69%' ><DIV class=div_input>"+rs.getString(11)+" / "+rs.getString(12)+"</DIV></td>"); 
						out.println("</tr >"); 
						out.println("<tr >"); 
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' ><DIV class=div_input>COMMENTS </DIV></td>"); 
						out.println("<td width='69%' ><DIV class=div_input>"+rs.getString(13)+"</DIV></td>"); 
						out.println("</tr >"); 
					}
					out.println("</table>");
				}
				
				rs= stmt.executeQuery (" SELECT   SCORE_CODE,UPPER(DESCRIPTION) FROM "+m_schema_name+".AF_CR_MAS_SCORE_CATEGORY "+
				" WHERE ACTIVE_STATUS='Y' AND "+
				" SCORE_CODE IN(SELECT SCORE_CODE FROM "+m_schema_name+".AF_CR_MAS_SCORE_SUB_CATEGORY "+
				" WHERE ACTIVE_STATUS='Y' AND "+
				" SCORE_SUB_CODE IN ( SELECT SCORE_SUB_CODE FROM "+m_schema_name+".AF_CR_MAS_SCORE_MODEL_DETAIL WHERE UPPER(SCORE_MODEL_CODE)=UPPER('"+m_score_model_code+"'))) "+
				" ORDER BY DISPLAY_POSITION ");
				
				out.println("<table align='center' width='100%' border=0>");
							
				int chk_nums=0;
				int j=1;
				
				while(rs.next()){
					out.println("<table align='center' width='100%' border=0>");
					out.println("<tr >");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='40%' ><DIV class=div_input><b>"+rs.getString(2)+"</b></DIV></td>");
					out.println("<td width='2%' ></td>");
					out.println("<td width='10%' ></td>"); 
					out.println("<td width='10%' ></td>");
					out.println("<td width='47%' ></td>");
					out.println("</tr>");
					out.println("<br>");
					
					rs1= stmt1.executeQuery (" SELECT  SCORE_SUB_CODE,DESCRIPTION,MIN_VALUE,MAX_VALUE,DISPALY_POSITION,DIS_STATUS,RATING_CODE,SCORE,REMARKS "+
					" FROM ( "+
					" SELECT   SCORE_SUB_CODE,INITCAP(DESCRIPTION) DESCRIPTION,0 MIN_VALUE,0 MAX_VALUE,DISPALY_POSITION,'N' DIS_STATUS,  "+
					" '-' RATING_CODE,0 SCORE,'-' REMARKS "+
					" FROM "+m_schema_name+".AF_CR_MAS_SCORE_SUB_CATEGORY "+
					" WHERE SCORE_CODE='"+rs.getString(1)+"' AND ACTIVE_STATUS='Y' AND "+
					" SCORE_SUB_CODE NOT IN ( SELECT SCORE_SUB_CODE FROM "+m_schema_name+".AF_CR_MAS_SCORE_MODEL_DETAIL WHERE UPPER(SCORE_MODEL_CODE)=UPPER('"+m_score_model_code+"')) "+
					" UNION "+
					" SELECT  A.SCORE_SUB_CODE,INITCAP(A.DESCRIPTION) DESCRIPTION,NVL(B.MIN_VALUE,0) MIN_VALUE,"+
					" NVL(B.MAX_VALUE,0) MAX_VALUE,A.DISPALY_POSITION,'Y' DIS_STATUS,  "+
					" NVL(D.DESCRIPTION,'-'), "+
					" NVL(C.SCORE,0), "+
					" NVL(C.REMARKS,'-') "+
					" FROM "+m_schema_name+".AF_CR_MAS_SCORE_SUB_CATEGORY A,"+m_schema_name+".AF_CR_MAS_SCORE_MODEL_DETAIL B,"+m_schema_name+".FA_CR_PRO_CRSCORE_DETAIL C, "+
					" "+m_schema_name+".AF_CR_MAS_SCORE_RATING D "+
					" WHERE A.SCORE_CODE='"+rs.getString(1)+"' AND "+
					" C.FACILITY_NO='"+m_facility_no+"' AND "+
					" C.SCORE_SUB_CODE=A.SCORE_SUB_CODE AND "+
					" C.RATING_CODE=D.RATING_CODE AND "+
					" B.SCORE_SUB_CODE=A.SCORE_SUB_CODE AND UPPER(B.SCORE_MODEL_CODE)=UPPER('"+m_score_model_code+"') "+	
					" ) "+
					" ORDER BY DISPALY_POSITION");

					int i=1;
					
					String m_rate_box_val="";
					
					while(rs1.next()){
						if(rs1.getString(6).equals("Y")){			
						out.println("<tr >"); 
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%'><DIV class=div_input>"+i+".  "+rs1.getString(2)+"</DIV></td>"); 
						out.println("<td width='2%'></td>"); 
						out.println("<td width='10%'><DIV class=div_input>"+rs1.getString(7)+"</DIV></td>"); 
						//out.println("<td width='20%'><DIV class=div_input>"+rs1.getString(8)+" ( MIN="+rs1.getString(3)+", MAX="+rs1.getString(4)+")</DIV></td>"); 
						out.println("<td width='10%'><DIV class=div_input><b>"+rs1.getString(8)+" / "+rs1.getString(4)+"</b></DIV></td>"); 
						out.println("<td width='47%'><DIV class=div_input>"+rs1.getString(9)+"</DIV></td>"); 
						out.println("</tr>");
						chk_nums++;
						i++;
						j++;
						}
					}
					out.println("</table>");
				}
				out.println("</table>");
			}
			rs.close();
			rs1.close();
			stmt.close();
			stmt1.close();
			}
			
			else if(m_chksql.equals("SCORE_APPROVAL")){
				
				String m_string="";				
				String m_dir_tot = "";
				String m_prod_tot ="";
				String  m_debt_tot = "";
				double m_tot = 0.0;
				double m_double_debt_tot =0.0;
				double m_double_m_prod_tot=0.0;
				double m_double_m_dir_tot=0.0;
				String m_grade="";
				
				rs=stmt.executeQuery (" SELECT FACILITY_NO,SCORE_MODEL_CODE,EVAL_USER,NVL(FINAL_APP_SCORE,0),"+
								              " NVL(MODEL_SCORE,0),"+
								              " NVL(COMMENTS,'-'), "+
								              " "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)  FULL_NAME,CLIENT_CODE "+
								              " FROM "+m_schema_name+".FA_CR_PRO_CRSCORE "+
								              " WHERE APP_STATUS = 'EVAL' ");				
				int i=0;
				int j=0;
			  
			  	m_string="<table align='center' width='100%' class='table' border=\"0\" >";

				m_string=m_string+"<tr class=pdn_txtpos2 >";
				m_string=m_string+"<td width='10%' align='left'><b>Client Code</b></td>";
				m_string=m_string+"<td width='20%' align='left'><b>Client Name</b></td>";
				//m_string=m_string+"<td width='10%' align='left'><b>Credit Score Model</b></td>";
				m_string=m_string+"<td width='10%' align='left'><b>Eval User</b></td>"; 
				m_string=m_string+"<td width='10%' align='right'><b>Application Score</b></td>"; 
				m_string=m_string+"<td width='10%' align='left'><b>Application Garde</b></td>";
				//m_string=m_string+"<td width='10%' align='left'><b>6Model Score</b></td>"; 
				m_string=m_string+"<td width='*%' align='left'><b>Comments</b></td>"; 
				m_string=m_string+"<td width='5%' align='left'><b>&nbsp;</b></td>"; 
				m_string=m_string+"</tr>";
	
				while(rs.next()){		
				
					rs4 = stmt1.executeQuery ("  SELECT "+
															      " (A.DBT_VALUE+A.TOP_DBT_VALUE+A.TRACK_REC_VALUE+A.REFERENCE_VALUE) TOTAL "+
												            "  FROM "+m_schema_name+".FA_CR_PRO_CRSCORE_DBT_DETAIL A "+
															      " WHERE A.CLIENT_CODE = '"+rs.getString(8)+"' ");
					
					
				  rs5 = stmt2.executeQuery (" SELECT "+
																		" (A.PRODUCT_VALUE+A.SECTOR_VALUE+A.RESON_VALUE+A.OPERATION_TYPE1_VALUE+A.OPERATION_TYPE2_VALUE+ "+
																    " A.OPERATION_TYPE3_VALUE+A.OPERATION_TYPE4_VALUE+A.BANK_REC1_VALUE+A.BANK_REC2_VALUE+A.BANK_REC3_VALUE+ "+
				 												    " A.BANK_REC4_VALUE+A.SUPPL_STATUS_VALUE+A.FINANCE_TYPE_VALUE) TOTAL   "+
																    " FROM "+m_schema_name+".FA_CR_PRO_CRSCORE_PRODUCT_DET A "+
																	  " WHERE A.CLIENT_CODE = '"+rs.getString(8)+"' ");
					
					rs6 = stmt3.executeQuery (" SELECT  "+
																		" (OWNER_VALUE+REPUTATION_VALUE+REF_BANK_VALUE+REF_TRADE_VALUE+CAPACITY_VALUE) "+
																		" FROM "+m_schema_name+".FA_CR_PRO_CRSCORE_COM_DETAIL "+
																		" WHERE CLIENT_CODE = '"+rs.getString(8)+"' "); 
					
					
					if(rs4.next()){
					m_debt_tot = rs4.getString(1);
					}
					if(rs5.next()){
					m_prod_tot = rs5.getString(1);
					}
					if(rs6.next()){
					m_dir_tot = rs6.getString(1);
					}
					m_double_debt_tot   = Double.parseDouble(m_debt_tot);
					m_double_m_prod_tot = Double.parseDouble(m_prod_tot);
					m_double_m_dir_tot  = Double.parseDouble(m_dir_tot);
					m_tot =(m_double_debt_tot+m_double_m_prod_tot+m_double_m_dir_tot)/3;	
					
				
					if(m_tot>=80 && m_tot <=100){  //Added By SJ on 25-11-2008
					m_grade = "1 (Excellent)";
					}else if(m_tot>=70 && m_tot <80){
					m_grade = "2 (Very Good)";
					}else if(m_tot>=60 && m_tot <70){
					m_grade = "3 (Good)";
					}else if(m_tot>=50 && m_tot <60){
					m_grade = "4 (Satifactory)";
					}else if(m_tot>=45 && m_tot <50){
					m_grade = "5 (Acceptable)";
					}else if(m_tot>=40 && m_tot <45){
					m_grade = "6 (Adequate)";
					}else if(m_tot>=35 && m_tot <40){
					m_grade = "7 (Watch)";
					}else if(m_tot <35){
					m_grade = "8 (Poor)";
					}			
					
					
					if(j==0){
					m_string=m_string+"<tr class=\"div_input\"  bgcolor=\"#C0C0C0\" >";
					m_string=m_string+"<td width='10%' align='left'><DIV class=div_input>"+rs.getString(8)+"</DIV><INPUT TYPE=\"HIDDEN\" NAME=\"FACILITY_"+i+"\" VALUE=\""+rs.getString(1)+"\"><INPUT TYPE=\"HIDDEN\" NAME=\"CLIENT_"+i+"\" VALUE=\""+rs.getString(8)+"\"></td>";
					m_string=m_string+"<td width='20%' align='left'><DIV class=div_input>"+rs.getString(7)+"</DIV></td>";
					//m_string=m_string+"<td width='10%' align='left'><DIV class=div_input>"+rs.getString(2)+"</DIV><INPUT TYPE=\"HIDDEN\" NAME=\"SCORE_"+i+"\" VALUE=\""+rs.getString(2)+"\"></td>";
					m_string=m_string+"<td width='10%' align='left'><DIV class=div_input>"+rs.getString(3)+"</DIV><INPUT TYPE=\"HIDDEN\" NAME=\"EVAL_"+i+"\" VALUE=\""+rs.getString(3)+"\"></td>"; 
					m_string=m_string+"<td width='10%' align='right'><DIV class=div_input>"+nf.format(m_tot)+"%</DIV></td>"; //rs.getString(4)
					m_string=m_string+"<td width='10%' align='left'><DIV class=div_input>"+m_grade+"</DIV></td>"; //rs.getString(4)
					//m_string=m_string+"<td width='10%' align='left'><DIV class=div_input>"+rs.getString(5)+"</DIV></td>";   // Comment By Sandun on 04-11-2008
					m_string=m_string+"<td width='*%' align='left' ><input class=\"txt_input\" type=\"text\" NAME=\"COMMENT_"+i+"\"  maxlength='100' size=\"50\" style='width=250'>&nbsp;"; 
					m_string=m_string+"<INPUT class='but_input' TYPE=\"BUTTON\" VALUE=\"View Cedit Score\" style='{width:100;}' NAME=\"VIEW\" onclick=\"display_data('"+rs.getString(8)+"')\">"; 
					//m_string=m_string+"<INPUT TYPE=\"CHECKBOX\" NAME=\"CHK_"+i+"\"></td>"; //Comment by SJ on 25-11-2008
					m_string=m_string+"<select class=\"txt_input\" type=\"text\" NAME=\"SELECT_"+i+"\" style='width:80'><option value=\"APP\">Approve</option>";
					m_string=m_string+"<option value=\"DISAPP\">Dis Approve</option>";
					m_string=m_string+"<option value=\"NOACT\">No Action</option>"; // Added By SJ on 25-11-2008
					m_string=m_string+"</select></td>";
					m_string=m_string+"<td width='5%' align='center' ><INPUT TYPE=\"CHECKBOX\" NAME=\"CHK_"+i+"\"></td>";
					m_string=m_string+"</tr>";
					j++;
					}
					else{
					m_string=m_string+"<tr class=\"div_input\"  bgcolor=\"#FFFFFF\">";
					m_string=m_string+"<td width='10%' align='left'><DIV class=div_input>"+rs.getString(8)+"</DIV><INPUT TYPE=\"HIDDEN\" NAME=\"FACILITY_"+i+"\" VALUE=\""+rs.getString(1)+"\"><INPUT TYPE=\"HIDDEN\" NAME=\"CLIENT_"+i+"\" VALUE=\""+rs.getString(8)+"\"></td>";
					m_string=m_string+"<td width='20%' align='left'><DIV class=div_input>"+rs.getString(7)+"</DIV></td>";
					//m_string=m_string+"<td width='10%' align='left'><DIV class=div_input>"+rs.getString(2)+"</DIV><INPUT TYPE=\"HIDDEN\" NAME=\"SCORE_"+i+"\" VALUE=\""+rs.getString(2)+"\"></td>";
					m_string=m_string+"<td width='10%' align='left'><DIV class=div_input>"+rs.getString(3)+"</DIV><INPUT TYPE=\"HIDDEN\" NAME=\"EVAL_"+i+"\" VALUE=\""+rs.getString(3)+"\"></td>"; 
					m_string=m_string+"<td width='10%' align='right'><DIV class=div_input>"+nf.format(m_tot)+"%</DIV></td>";
					m_string=m_string+"<td width='10%' align='left'><DIV class=div_input>"+m_grade+"</DIV></td>"; //rs.getString(4)
					//m_string=m_string+"<td width='10%' align='left'><DIV class=div_input>"+rs.getString(5)+"</DIV></td>"; // Comment By Sandun on 04-11-2008
					m_string=m_string+"<td width='*%' align='left'><input class=\"txt_input\" type=\"text\" NAME=\"COMMENT_"+i+"\"  maxlength='100' size=\"50\" style='width=250'>&nbsp;"; 
					m_string=m_string+"<INPUT class='but_input' TYPE=\"BUTTON\" VALUE=\"View Cedit Score\" style='{width:100;}' NAME=\"VIEW\" onclick=\"display_data('"+rs.getString(8)+"')\">"; 
				//	m_string=m_string+"<INPUT TYPE=\"CHECKBOX\" NAME=\"CHK_"+i+"\"></td>";  //Comment by SJ on 25-11-2008
					m_string=m_string+"<select class=\"txt_input\" type=\"text\" NAME=\"SELECT_"+i+"\" style='width:80'><option value=\"APP\">Approve</option>";
					m_string=m_string+"<option value=\"DISAPP\">Dis Approve</option>";//Added By SJ on 25-11-2008
					m_string=m_string+"<option value=\"NOACT\">No Action</option>";
					m_string=m_string+"</select></td>";
					m_string=m_string+"<td width='5%' align='center' ><INPUT TYPE=\"CHECKBOX\" NAME=\"CHK_"+i+"\"></td>";
					m_string=m_string+"</tr>";
					j=0;
					}
					i++;
				}
				m_string=m_string+"<INPUT TYPE='hidden' NAME='NUM_CHKS' VALUE="+i+">";
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

