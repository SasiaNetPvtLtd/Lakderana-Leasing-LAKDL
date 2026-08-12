import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
//import CLAMF_sn_methods;
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_CR_SCORE_validations extends javax.servlet.http.HttpServlet {
	
	/*
	Connection conn;
	Statement stmt,stmt1,stmt3;
	CallableStatement callstmt;
	java.text.NumberFormat nf;
    
    public ResultSet rs,rs1,rs3;
	public String m_chksql;
	*/
	
	//public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		Connection conn=null;
	Statement stmt=null,stmt1=null,stmt3=null;
	CallableStatement callstmt=null;
	java.text.NumberFormat nf=null;
    
     ResultSet rs=null,rs1=null,rs3=null;
	 String m_chksql=null;
		
		try {
		
			//************************************************************	
		//	SCREEN_METHODS m_sn_methods = new SCREEN_METHODS();
		//	conn = m_sn_methods.met_user_validate(req); 
		//	String m_schema_name = m_sn_methods.schema_name.trim();
		//	String m_html_client_url=m_sn_methods.html_client_url.trim(); 

		
		      LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
							conn = m_sn_methods.met_user_validate(req); 

		
		
		String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();


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
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			stmt3=conn.createStatement();
			
			
			//!!modified(2006/11/14)----------------------------------------------
			String m_applicaton_no        = req.getParameter("app_no");
			String m_sort_column   = "APPLICATION_CODE";	
			String m_order_by_type = "ASC";
			String m_pre_stage1=req.getParameter("pre_stage1");
			
		
							if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
			          m_sort_column = req.getParameter("sort_column");
			          m_order_by_type = req.getParameter("order_by_type");
								}

			//!!------------------------------------------------------------------
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
		
			else if(m_chksql.equals("SCORE_DETAILS")){
				
				String m_score_model_code=req.getParameter("model");
				
				String m_string="";
				
				if(!m_score_model_code.equals("")){
				
				m_string="<table align='center' width='100%' class='table'>";
				
				rs= stmt.executeQuery (" SELECT   SCORE_CODE,UPPER(DESCRIPTION) FROM LAKDL.AF_CR_MAS_SCORE_CATEGORY "+
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
					" FROM LAKDL.AF_CR_MAS_SCORE_SUB_CATEGORY "+
					" WHERE SCORE_CODE='"+rs.getString(1)+"' AND ACTIVE_STATUS='Y' AND "+
					" SCORE_SUB_CODE NOT IN ( SELECT SCORE_SUB_CODE FROM LAKDL.AF_CR_MAS_SCORE_MODEL_DETAIL WHERE UPPER(SCORE_MODEL_CODE)=UPPER('"+m_score_model_code+"')) "+
					" UNION "+
					" SELECT  A.SCORE_SUB_CODE,INITCAP(A.DESCRIPTION) DESCRIPTION,NVL(B.MIN_VALUE,0) MIN_VALUE,NVL(B.MAX_VALUE,0) MAX_VALUE,A.DISPALY_POSITION,'Y' DIS_STATUS  "+
					" FROM LAKDL.AF_CR_MAS_SCORE_SUB_CATEGORY A,LAKDL.AF_CR_MAS_SCORE_MODEL_DETAIL B "+
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
						m_string=m_string+"<td width='10%'><INPUT TYPE='CHECKBOX'  NAME='CHK_"+chk_nums+"' checked><INPUT TYPE='HIDDEN' NAME='HID_"+chk_nums+"' VALUE="+rs1.getString(1)+"></td>"; 
						}
						else{
						m_string=m_string+"<td width='10%'><INPUT TYPE='CHECKBOX'  NAME='CHK_"+chk_nums+"'><INPUT TYPE='HIDDEN' NAME='HID_"+chk_nums+"' VALUE="+rs1.getString(1)+"></td>"; 
						}
						m_string=m_string+"<td width='10%'><INPUT TYPE='TEXT' NAME='MIN_"+chk_nums+"' class='txt_input' VALUE="+rs1.getString(3)+" onblur='val_num(this)' maxlength='3' SIZE='3' STYLE=\"{text-align:right;}\"  ></td>"; 
						m_string=m_string+"<td width='10%'><INPUT TYPE='TEXT' NAME='MAX_"+chk_nums+"' class='txt_input' VALUE="+rs1.getString(4)+" onblur='val_num(this)' maxlength='3' SIZE='3' STYLE=\"{text-align:right;}\" ></td>"; 
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
			
		 	   	else if(m_chksql.equals("SCORE_DETAILS_ENTER_EDIT")){//add by Amila
				
				String m_score_model_code=req.getParameter("model");
				String m_app_code=req.getParameter("application");
				String m_string="";
				
				String m_rate_box="";
				String m_rate_vals="";
				if(!m_score_model_code.equals("")){

				rs= stmt.executeQuery(" SELECT  RATING_CODE,DESCRIPTION,(FROM_RAGE+TO_RANGE)/2 FROM LAKDL.AF_CR_MAS_SCORE_RATING WHERE ACTIVE_STATUS='Y'");
				while(rs.next()){
				m_rate_box=m_rate_box+"<OPTION VALUE=\""+rs.getString(1)+"\">"+rs.getString(2)+"</OPTION>";
				m_rate_vals=m_rate_vals+"<INPUT TYPE=\"HIDDEN\" NAME=\"RATE_VAL_"+rs.getString(1)+"\" VALUE=\""+rs.getString(3)+"\">";
				}
				
				
				
				m_string=m_string+m_rate_vals;
				m_string=m_string+"<table align='center' width='100%' class='table'>";
				
			/*	rs= stmt.executeQuery(" SELECT   SCORE_CODE,UPPER(DESCRIPTION) FROM "+m_schema_name+".AF_CR_MAS_SCORE_CATEGORY "+
	 					" WHERE ACTIVE_STATUS='Y' AND "+
						" SCORE_CODE IN(SELECT SCORE_CODE FROM "+m_schema_name+".AF_CR_MAS_SCORE_SUB_CATEGORY "+
						" WHERE ACTIVE_STATUS='Y' AND "+
						" SCORE_SUB_CODE IN ( SELECT SCORE_SUB_CODE FROM "+m_schema_name+".AF_CR_MAS_SCORE_MODEL_DETAIL WHERE UPPER(SCORE_MODEL_CODE)=UPPER('"+m_score_model_code+"'))) "+
						" ORDER BY DISPLAY_POSITION "); */ //10.09
			
			    rs= stmt.executeQuery("SELECT a.SCORE_CODE,UPPER(DESCRIPTION),aa.comments "+
															"FROM "+m_schema_name+".AF_CR_MAS_SCORE_CATEGORY a,(select nvl(comments,' ') comments,SCORE_CODE "+
															"from "+m_schema_name+".AF_CR_PRO_SCORE_DETAIL where application_code='"+m_app_code+"') aa "+
															"WHERE ACTIVE_STATUS='Y' and aa.SCORE_CODE= a.SCORE_CODE  "+
															"AND a.SCORE_CODE IN(SELECT SCORE_CODE FROM "+m_schema_name+".AF_CR_MAS_SCORE_SUB_CATEGORY "+
															"WHERE ACTIVE_STATUS='Y' "+
															"AND SCORE_SUB_CODE IN ( SELECT SCORE_SUB_CODE FROM "+m_schema_name+".AF_CR_MAS_SCORE_MODEL_DETAIL "+
															"WHERE UPPER(SCORE_MODEL_CODE)=UPPER('"+m_score_model_code+"'))) ORDER BY DISPLAY_POSITION  ");
				
				int chk_nums=0;
				int j=1;
				int d=0;
				
				//txt_input2
				while(rs.next()){
					m_string=m_string+"<tr >";
					m_string=m_string+"<td width='1%'></td>"; 
					m_string=m_string+"<td width='30%' ><DIV class=div_input><b>"+rs.getString(2)+"</b></DIV></td>";
					m_string=m_string+"<td width='2%' ></td>";
					m_string=m_string+"<td width='10%' ></td>"; 
					m_string=m_string+"<td width='10%' ></td>"; 
					m_string=m_string+"<td width='*%'></td>";
					
					m_string=m_string+"<td width='*%'><TEXTAREA NAME=\"COMMENT_"+d+"\" style=\"width:420px; height:100px;\" maxlength='2000' class='txt_input' onKeypress=\"chk_comment_length(this)\" onKeyDown=\"count_length(this)\" onKeyUp=\"count_length(this)\"disabled >"+rs.getString(3)+"</TEXTAREA><INPUT TYPE=\"hidden\" NAME=\"SC_CODE_"+d+"\" VALUE=\""+rs.getString(1)+"\"></td>"; 

					m_string=m_string+"</tr>";
					
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
					" NVL(RATING_CODE,1), "+
					" NVL(SCORE,0), "+
					" NVL(REMARKS,'-') "+
					" FROM "+m_schema_name+".AF_CR_MAS_SCORE_SUB_CATEGORY A,"+m_schema_name+".AF_CR_MAS_SCORE_MODEL_DETAIL B,"+m_schema_name+".AF_CR_PRO_CRSCORE_DETAIL C "+
					" WHERE A.SCORE_CODE='"+rs.getString(1)+"' AND "+
					" C.APPLICATION_CODE='"+m_app_code+"' AND "+
					" C.SCORE_SUB_CODE=A.SCORE_SUB_CODE AND "+
					" B.SCORE_SUB_CODE=A.SCORE_SUB_CODE AND UPPER(B.SCORE_MODEL_CODE)=UPPER('"+m_score_model_code+"') "+	
					" ) "+
					" ORDER BY DISPALY_POSITION");

					int i=1;
					
					String m_rate_box_val="";
					
					while(rs1.next()){
						if(rs1.getString(6).equals("Y")){
						m_rate_box_val="";
						rs3= stmt3.executeQuery (" SELECT  RATING_CODE,DESCRIPTION,(FROM_RAGE+TO_RANGE)/2 FROM LAKDL.AF_CR_MAS_SCORE_RATING WHERE ACTIVE_STATUS='Y'");
						while(rs3.next()){
							if(rs1.getString(7).equals(rs3.getString(1))){
								m_rate_box_val=m_rate_box_val+"<OPTION VALUE=\""+rs3.getString(1)+"\" selected>"+rs3.getString(2)+"</OPTION>";
							}
							else{
								m_rate_box_val=m_rate_box_val+"<OPTION VALUE=\""+rs3.getString(1)+"\" >"+rs3.getString(2)+"</OPTION>"; 
							}
						}
						rs3.close();
				
						m_string=m_string+"<tr valign='top' >"; 
						m_string=m_string+"<td width='1%'></td>"; 
						m_string=m_string+"<td width='30%'><DIV class=div_input>"+i+".  "+rs1.getString(2)+"</DIV><INPUT TYPE=\"HIDDEN\" VALUE='"+rs1.getString(1)+"' NAME=\"SUB_CAT_"+j+"\"><INPUT TYPE=\"HIDDEN\" VALUE='"+rs1.getString(4)+"' NAME=\"MAX_"+j+"\"></td>"; 
						m_string=m_string+"<td width='2%'></td>"; 

						m_string=m_string+"<td width='10%'><DIV class=div_input><SELECT NAME=\"RATE_"+j+"\" class='txt_input' onchange=\"load_default_score('"+j+"')\"disabled>"+m_rate_box_val+"</SELECT></DIV></td>"; 


						m_string=m_string+"<td width='10%'><DIV id='DIV_TXT_SCORE_"+j+"' class=div_input><INPUT TYPE=\"TEXT\" class='txt_input' VALUE="+rs1.getString(8)+" NAME=\"SCORE_"+j+"\" maxlength='5' SIZE='5' onblur=\"valdate_values('"+j+"','"+rs1.getString(3)+"','"+rs1.getString(4)+"')\"disabled></DIV></td>"; 
		//				m_string=m_string+"<td width='*%'><TEXTAREA NAME=\"COMMENT_"+j+"\" class='txt_input' style=\"width:420px; height:35px;\"  maxlength='2000' onKeypress=\"chk_comment_length(this)\" onKeyDown=\"count_length(this)\" onKeyUp=\"count_length(this)\"  >"+rs1.getString(9)+"</TEXTAREA></td>"; 
						m_string=m_string+"</tr>";
						chk_nums++;
						i++;
						j++;
						}
					}
					
					d=d+1;
					
				}
				m_string=m_string+"</table>";
				m_string=m_string+"<INPUT TYPE=\"HIDDEN\" VALUE='"+j+"' NAME=\"NUM_COLS\">";
				m_string=m_string+"<INPUT TYPE=\"HIDDEN\" VALUE="+d+" NAME=\"SCORE_NUM\">";

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

				rs= stmt.executeQuery(" SELECT  RATING_CODE,DESCRIPTION,(FROM_RAGE+TO_RANGE)/2 FROM LAKDL.AF_CR_MAS_SCORE_RATING WHERE ACTIVE_STATUS='Y'");
				while(rs.next()){
				m_rate_box=m_rate_box+"<OPTION VALUE=\""+rs.getString(1)+"\">"+rs.getString(2)+"</OPTION>";
				m_rate_vals=m_rate_vals+"<INPUT TYPE=\"HIDDEN\" NAME=\"RATE_VAL_"+rs.getString(1)+"\" VALUE=\""+rs.getString(3)+"\">";
				}
				
				
				
				m_string=m_string+m_rate_vals;
				m_string=m_string+"<table align='center' width='100%' class='table'>";
				
				rs= stmt.executeQuery(" SELECT   SCORE_CODE,UPPER(DESCRIPTION) FROM "+m_schema_name+".AF_CR_MAS_SCORE_CATEGORY "+
	 					" WHERE ACTIVE_STATUS='Y' AND "+
						" SCORE_CODE IN(SELECT SCORE_CODE FROM "+m_schema_name+".AF_CR_MAS_SCORE_SUB_CATEGORY "+
						" WHERE ACTIVE_STATUS='Y' AND "+
						" SCORE_SUB_CODE IN ( SELECT SCORE_SUB_CODE FROM "+m_schema_name+".AF_CR_MAS_SCORE_MODEL_DETAIL WHERE UPPER(SCORE_MODEL_CODE)=UPPER('"+m_score_model_code+"'))) "+
						" ORDER BY DISPLAY_POSITION ");
				
				int chk_nums=0;
				int j=1;
				int d=0;
				
				//txt_input2
				while(rs.next()){
					m_string=m_string+"<tr >";
					m_string=m_string+"<td width='1%'></td>"; 
					m_string=m_string+"<td width='30%' ><DIV class=div_input><b>"+rs.getString(2)+"</b></DIV></td>";
					m_string=m_string+"<td width='2%' ></td>";
					m_string=m_string+"<td width='10%' ></td>"; 
					m_string=m_string+"<td width='10%' ></td>"; 
					m_string=m_string+"<td width='*%'></td>";
					
					m_string=m_string+"<td width='*%'><TEXTAREA NAME=\"COMMENT_"+d+"\" style=\"width:420px; height:100px;\" maxlength='2000' class='txt_input' onKeypress=\"chk_comment_length(this)\" onKeyDown=\"count_length(this)\" onKeyUp=\"count_length(this)\" ></TEXTAREA><INPUT TYPE=\"hidden\" NAME=\"SC_CODE_"+d+"\" VALUE=\""+rs.getString(1)+"\" ></td>"; 

					m_string=m_string+"</tr>";
					
					rs1= stmt1.executeQuery(" SELECT  SCORE_SUB_CODE,DESCRIPTION,MIN_VALUE,MAX_VALUE,DISPALY_POSITION,DIS_STATUS "+
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
						if(rs1.getString(6).equals("Y")){
						m_string=m_string+"<tr valign='top' >"; 
						m_string=m_string+"<td width='1%'></td>"; 
						m_string=m_string+"<td width='30%'><DIV class=div_input>"+i+".  "+rs1.getString(2)+"</DIV><INPUT TYPE=\"HIDDEN\" VALUE='"+rs1.getString(1)+"' NAME=\"SUB_CAT_"+j+"\"><INPUT TYPE=\"HIDDEN\" VALUE='"+rs1.getString(4)+"' NAME=\"MAX_"+j+"\"></td>"; 
						m_string=m_string+"<td width='2%'></td>"; 
					//	txt_input2
						//m_string=m_string+"<td width='10%'><DIV class=div_input><SELECT NAME=\"RATE_"+j+"\" class=div_input onchange=\"load_default_score('"+j+"')\">"+m_rate_box+"</SELECT></DIV></td>"; 
						m_string=m_string+"<td width='10%'><SELECT NAME=\"RATE_"+j+"\"  class='txt_input' onchange=\"load_default_score('"+j+"')\">"+m_rate_box+"</SELECT></td>"; 

						m_string=m_string+"<td width='10%'><DIV id='DIV_TXT_SCORE_"+j+"' class=div_input ><INPUT TYPE=\"TEXT\" class='txt_input' VALUE=0 NAME=\"SCORE_"+j+"\" maxlength='5' SIZE='5' onblur=\"valdate_values('"+j+"','"+rs1.getString(3)+"','"+rs1.getString(4)+"')\"></DIV></td>"; 
				//	m_string=m_string+"<td width='*%'><TEXTAREA NAME=\"COMMENT_"+j+"\" style=\"width:420px; height:35px;\" maxlength='2000' class='txt_input' onKeypress=\"chk_comment_length(this)\" onKeyDown=\"count_length(this)\" onKeyUp=\"count_length(this)\" ></TEXTAREA></td>"; 
						m_string=m_string+"</tr>";
						chk_nums++;
						i++;
						j++;
						}
					}
					
					d=d+1;
					
				}
				m_string=m_string+"</table>";
				m_string=m_string+"<INPUT TYPE=\"HIDDEN\" VALUE='"+j+"' NAME=\"NUM_COLS\">";
				m_string=m_string+"<INPUT TYPE=\"HIDDEN\" VALUE="+d+" NAME=\"SCORE_NUM\">";

				out.println(m_string);
			}
			rs.close();
			rs1.close();
			stmt.close();
			stmt1.close();
				
			}
			else if(m_chksql.equals("SCORE_DETAILS_EDIT")){
				
				String m_score_model_code=req.getParameter("model");
				String m_app_code=req.getParameter("application");
				
				String m_string="";
				
				String m_rate_box="";
				String m_rate_vals="";
				
				if(!m_score_model_code.equals("")){

				rs= stmt.executeQuery(" SELECT  RATING_CODE,DESCRIPTION,(FROM_RAGE+TO_RANGE)/2 FROM LAKDL.AF_CR_MAS_SCORE_RATING WHERE ACTIVE_STATUS='Y'");
				while(rs.next()){
				m_rate_box=m_rate_box+"<OPTION VALUE=\""+rs.getString(1)+"\">"+rs.getString(2)+"</OPTION>";
				m_rate_vals=m_rate_vals+"<INPUT TYPE=\"HIDDEN\" NAME=\"RATE_VAL_"+rs.getString(1)+"\" VALUE=\""+rs.getString(3)+"\">";
				}
				
				m_string=m_string+m_rate_vals;
				m_string=m_string+"<table align='center' width='100%' class='table'>";
						
				/*rs= stmt.executeQuery (" SELECT   SCORE_CODE,UPPER(DESCRIPTION) FROM "+m_schema_name+".AF_CR_MAS_SCORE_CATEGORY "+
	 					" WHERE ACTIVE_STATUS='Y' AND "+
						" SCORE_CODE IN(SELECT SCORE_CODE FROM "+m_schema_name+".AF_CR_MAS_SCORE_SUB_CATEGORY "+
						" WHERE ACTIVE_STATUS='Y' AND "+
						" SCORE_SUB_CODE IN ( SELECT SCORE_SUB_CODE FROM "+m_schema_name+".AF_CR_MAS_SCORE_MODEL_DETAIL WHERE UPPER(SCORE_MODEL_CODE)=UPPER('"+m_score_model_code+"'))) "+
						" ORDER BY DISPLAY_POSITION ");
				*/
				rs= stmt.executeQuery("SELECT a.SCORE_CODE,UPPER(DESCRIPTION),aa.comments "+
															"FROM "+m_schema_name+".AF_CR_MAS_SCORE_CATEGORY a,(select nvl(comments,' ') comments,SCORE_CODE "+
															"from "+m_schema_name+".AF_CR_PRO_SCORE_DETAIL where application_code='"+m_app_code+"') aa "+
															"WHERE ACTIVE_STATUS='Y' and aa.SCORE_CODE= a.SCORE_CODE  "+
															"AND a.SCORE_CODE IN(SELECT SCORE_CODE FROM "+m_schema_name+".AF_CR_MAS_SCORE_SUB_CATEGORY "+
															"WHERE ACTIVE_STATUS='Y' "+
															"AND SCORE_SUB_CODE IN ( SELECT SCORE_SUB_CODE FROM "+m_schema_name+".AF_CR_MAS_SCORE_MODEL_DETAIL "+
															"WHERE UPPER(SCORE_MODEL_CODE)=UPPER('"+m_score_model_code+"'))) ORDER BY DISPLAY_POSITION  ");

				int chk_nums=0;
				int j=1;
				int d=0;
				
				while(rs.next()){
					m_string=m_string+"<tr >";
					m_string=m_string+"<td width='1%'></td>"; 
					m_string=m_string+"<td width='30%' ><DIV class=div_input><b>"+rs.getString(2)+"</b></DIV></td>";
					m_string=m_string+"<td width='2%' ></td>";
					m_string=m_string+"<td width='10%' ></td>"; 
					m_string=m_string+"<td width='10%' ></td>"; 
					m_string=m_string+"<td width='*%'></td>";
				//	m_string=m_string+"<td width='*%'><TEXTAREA NAME=\"COMMENT_"+j+"\" class='txt_input' style=\"width:420px; height:35px;\"  maxlength='2000' onKeypress=\"chk_comment_length(this)\" onKeyDown=\"count_length(this)\" onKeyUp=\"count_length(this)\"  >"+rs1.getString(9)+"</TEXTAREA></td>"; 
					m_string=m_string+"<td width='*%'><TEXTAREA NAME=\"COMMENT_"+d+"\" style=\"width:420px; height:100px;\" maxlength='2000' class='txt_input' onKeypress=\"chk_comment_length(this)\" onKeyDown=\"count_length(this)\" onKeyUp=\"count_length(this)\" >"+rs.getString(3)+"</TEXTAREA><INPUT TYPE=\"hidden\" NAME=\"SC_CODE_"+d+"\" VALUE=\""+rs.getString(1)+"\" ></td>"; 

					m_string=m_string+"</tr>";
					
					rs1= stmt1.executeQuery(" SELECT  SCORE_SUB_CODE,DESCRIPTION,MIN_VALUE,MAX_VALUE,DISPALY_POSITION,DIS_STATUS,RATING_CODE,SCORE,REMARKS "+
					" FROM ( "+
					" SELECT   SCORE_SUB_CODE,INITCAP(DESCRIPTION) DESCRIPTION,0 MIN_VALUE,0 MAX_VALUE,DISPALY_POSITION,'N' DIS_STATUS,  "+
					" '-' RATING_CODE,0 SCORE,'-' REMARKS "+
					" FROM "+m_schema_name+".AF_CR_MAS_SCORE_SUB_CATEGORY "+
					" WHERE SCORE_CODE='"+rs.getString(1)+"' AND ACTIVE_STATUS='Y' AND "+
					" SCORE_SUB_CODE NOT IN ( SELECT SCORE_SUB_CODE FROM "+m_schema_name+".AF_CR_MAS_SCORE_MODEL_DETAIL WHERE UPPER(SCORE_MODEL_CODE)=UPPER('"+m_score_model_code+"')) "+
					" UNION "+
					" SELECT  A.SCORE_SUB_CODE,INITCAP(A.DESCRIPTION) DESCRIPTION,NVL(B.MIN_VALUE,0) MIN_VALUE,"+
					" NVL(B.MAX_VALUE,0) MAX_VALUE,A.DISPALY_POSITION,'Y' DIS_STATUS,  "+
					" NVL(RATING_CODE,1), "+
					" NVL(SCORE,0), "+
					" NVL(REMARKS,'-') "+
					" FROM "+m_schema_name+".AF_CR_MAS_SCORE_SUB_CATEGORY A,"+m_schema_name+".AF_CR_MAS_SCORE_MODEL_DETAIL B,"+m_schema_name+".AF_CR_PRO_CRSCORE_DETAIL C "+
					" WHERE A.SCORE_CODE='"+rs.getString(1)+"' AND "+
					" C.APPLICATION_CODE='"+m_app_code+"' AND "+
					" C.SCORE_SUB_CODE=A.SCORE_SUB_CODE AND "+
					" B.SCORE_SUB_CODE=A.SCORE_SUB_CODE AND UPPER(B.SCORE_MODEL_CODE)=UPPER('"+m_score_model_code+"') "+	
					" ) "+
					" ORDER BY DISPALY_POSITION");

					int i=1;
					
					String m_rate_box_val="";
					
					while(rs1.next()){
						if(rs1.getString(6).equals("Y")){
						m_rate_box_val="";
						rs3= stmt3.executeQuery(" SELECT  RATING_CODE,DESCRIPTION,(FROM_RAGE+TO_RANGE)/2 FROM LAKDL.AF_CR_MAS_SCORE_RATING WHERE ACTIVE_STATUS='Y'");
						while(rs3.next()){
							if(rs1.getString(7).equals(rs3.getString(1))){
								m_rate_box_val=m_rate_box_val+"<OPTION VALUE=\""+rs3.getString(1)+"\" selected>"+rs3.getString(2)+"</OPTION>";
							}
							else{
								m_rate_box_val=m_rate_box_val+"<OPTION VALUE=\""+rs3.getString(1)+"\" >"+rs3.getString(2)+"</OPTION>";
							}
						}
						rs3.close();
				
						m_string=m_string+"<tr valign='top' >"; 
						m_string=m_string+"<td width='1%'></td>"; 
						m_string=m_string+"<td width='30%'><DIV class=div_input>"+i+".  "+rs1.getString(2)+"</DIV><INPUT TYPE=\"HIDDEN\" VALUE='"+rs1.getString(1)+"' NAME=\"SUB_CAT_"+j+"\"><INPUT TYPE=\"HIDDEN\" VALUE='"+rs1.getString(4)+"' NAME=\"MAX_"+j+"\"></td>"; 
						m_string=m_string+"<td width='2%'></td>"; 
						m_string=m_string+"<td width='10%'><DIV class=div_input><SELECT NAME=\"RATE_"+j+"\" class='txt_input' onchange=\"load_default_score('"+j+"')\">"+m_rate_box_val+"</SELECT></DIV></td>"; 
						m_string=m_string+"<td width='10%'><DIV id='DIV_TXT_SCORE_"+j+"' class=div_input><INPUT TYPE=\"TEXT\" class='txt_input' VALUE="+rs1.getString(8)+" NAME=\"SCORE_"+j+"\" maxlength='5' SIZE='5' onblur=\"valdate_values('"+j+"','"+rs1.getString(3)+"','"+rs1.getString(4)+"')\"></DIV></td>"; 
		//				m_string=m_string+"<td width='*%'><TEXTAREA NAME=\"COMMENT_"+j+"\" class='txt_input' style=\"width:420px; height:35px;\"  maxlength='2000' onKeypress=\"chk_comment_length(this)\" onKeyDown=\"count_length(this)\" onKeyUp=\"count_length(this)\"  >"+rs1.getString(9)+"</TEXTAREA></td>"; 
						m_string=m_string+"</tr>";
						chk_nums++;
						i++;
						j++;
						}
					}
					d=d+1;
					
				}
				m_string=m_string+"</table>";
				m_string=m_string+"<INPUT TYPE=\"HIDDEN\" VALUE='"+j+"' NAME=\"NUM_COLS\">";
				m_string=m_string+"<INPUT TYPE=\"HIDDEN\" VALUE="+d+" NAME=\"SCORE_NUM\">";

				out.println(m_string);
			}
			rs.close();
			rs1.close();
			stmt.close();
			stmt1.close();
				
			}
			
			else if(m_chksql.equals("SCORE_DETAILS_VIEW")){
				
				String m_score_model_code=req.getParameter("model");
				String m_app_code=req.getParameter("application");
				
				String m_string="";
				
				if(!m_app_code.equals("")){
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Credit Process - Credit Score Evaluation </TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
				out.println("<br>");	
				out.println("<br>");	
				out.println("<br>");	
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>   "); 
				out.println("<tr class='pdn_txtpos2'>"); 
				out.println("<td align='left'  >CREDIT SCORE EVALUATION FOR APPLICATION NO: "+m_app_code+"</td>"); 
				out.println("</tr>"); 
				out.println("</table>");
				out.println("<br>");			
				rs= stmt.executeQuery (" SELECT  APPLICATION_CODE,SCORE_MODEL_CODE,NVL(EVAL_USER,'-'), "+
  						" NVL(FINAL_APP_SCORE,0),NVL(MODEL_SCORE,0),NVL(COMMENTS,'-'),NVL(ENT_USER,'-'),"+
							" TO_CHAR(ENT_DATE,'DD-MM-YYYY HH24:MI'),APP_STATUS,NVL(APP_USER,'-'),TO_CHAR(APP_DATE,'DD-MM-YYYY HH24:MI'),"+
							" NVL(APP_COMMENT,'-') "+
							" FROM LAKDL.AF_CR_PRO_CRSCORE "+
							" WHERE APPLICATION_CODE='"+m_app_code+"'");
				if(rs.next()){
					out.println("<table align='center' width='100%' class='table' border=1>"); 
					out.println("<tr >"); 
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' ><DIV class=div_input><b>Application No </DIV></td>"); 
					out.println("<td width='69%' ><DIV class=div_input>"+rs.getString(1)+"</DIV></td>"); 
					out.println("</tr >"); 
					out.println("<tr >"); 
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' ><DIV class=div_input><b>Score Model Code </DIV></td>"); 
					out.println("<td width='69%' ><DIV class=div_input>"+rs.getString(2)+"</DIV></td>"); 
					out.println("</tr >"); 
					out.println("<tr >"); 
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' ><DIV class=div_input><b>Evaluator Name/Date </DIV></td>"); 
					out.println("<td width='69%' ><DIV class=div_input>"+rs.getString(3)+" / "+rs.getString(8)+"</DIV></td>"); 
					out.println("</tr >"); 
					out.println("<tr >"); 
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' ><DIV class=div_input><b>Score for Application</DIV></td>"); 
					out.println("<td width='69%' ><DIV class=div_input>"+nf.format(rs.getDouble(4))+"</DIV></td>"); 
					out.println("</tr >"); 
					out.println("<tr >"); 
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' ><DIV class=div_input><b>Score for Model</DIV></td>"); 
					out.println("<td width='69%' ><DIV class=div_input>"+nf.format(rs.getDouble(5))+"</DIV></td>"); 
					out.println("</tr >");
					out.println("<tr >"); 
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' ><DIV class=div_input><b>Status</DIV></td>"); 
					out.println("<td width='69%' ><DIV class=div_input>"+rs.getString(9)+"</DIV></td>"); 
					out.println("</tr >");
					out.println("<tr >"); 
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' ><DIV class=div_input><b>Comments </DIV></td>"); 
					out.println("<td width='69%' ><DIV class=div_input>"+rs.getString(6)+"</DIV></td>"); 
					out.println("</tr >"); 
					if(!rs.getString(10).equals("-")){
						out.println("<tr >"); 
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' ><DIV class=div_input><b>Approved Name/Date </DIV></td>"); 
						out.println("<td width='69%' ><DIV class=div_input>"+rs.getString(10)+" / "+rs.getString(11)+"</DIV></td>"); 
						out.println("</tr >"); 
						out.println("<tr >"); 
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' ><DIV class=div_input><b>Comments </DIV></td>"); 
						out.println("<td width='69%' ><DIV class=div_input>"+rs.getString(12)+"</DIV></td>"); 
						out.println("</tr >"); 
					}
					out.println("</table>");
				}
				out.println("<br>");	
				out.println("<br>");	
				out.println("<table align='center' width='100%' class='table' border=1>");
				rs= stmt.executeQuery (" SELECT   SCORE_CODE,UPPER(DESCRIPTION) FROM LAKDL.AF_CR_MAS_SCORE_CATEGORY "+
	 					" WHERE ACTIVE_STATUS='Y' AND "+
						" SCORE_CODE IN(SELECT SCORE_CODE FROM LAKDL.AF_CR_MAS_SCORE_SUB_CATEGORY "+
						" WHERE ACTIVE_STATUS='Y' AND "+
						" SCORE_SUB_CODE IN ( SELECT SCORE_SUB_CODE FROM LAKDL.AF_CR_MAS_SCORE_MODEL_DETAIL WHERE UPPER(SCORE_MODEL_CODE)=UPPER('"+m_score_model_code+"'))) "+
						" ORDER BY DISPLAY_POSITION ");
				
				int chk_nums=0;
				int j=1;
				
				while(rs.next()){
					out.println("<tr >");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' ><DIV class=div_input><b>"+rs.getString(2)+"</b></DIV></td>");
					out.println("<td width='2%' ></td>");
					out.println("<td width='10%' ></td>"); 
					out.println("<td width='20%' ></td>");
					out.println("<td width='47%' ></td>");
					out.println("</tr>");
					
					rs1= stmt1.executeQuery (" SELECT  SCORE_SUB_CODE,DESCRIPTION,MIN_VALUE,MAX_VALUE,DISPALY_POSITION,DIS_STATUS,RATING_CODE,SCORE,REMARKS "+
					" FROM ( "+
					" SELECT   SCORE_SUB_CODE,INITCAP(DESCRIPTION) DESCRIPTION,0 MIN_VALUE,0 MAX_VALUE,DISPALY_POSITION,'N' DIS_STATUS,  "+
					" '-' RATING_CODE,0 SCORE,'-' REMARKS "+
					" FROM LAKDL.AF_CR_MAS_SCORE_SUB_CATEGORY "+
					" WHERE SCORE_CODE='"+rs.getString(1)+"' AND ACTIVE_STATUS='Y' AND "+
					" SCORE_SUB_CODE NOT IN ( SELECT SCORE_SUB_CODE FROM LAKDL.AF_CR_MAS_SCORE_MODEL_DETAIL WHERE UPPER(SCORE_MODEL_CODE)=UPPER('"+m_score_model_code+"')) "+
					" UNION "+
					" SELECT  A.SCORE_SUB_CODE,INITCAP(A.DESCRIPTION) DESCRIPTION,NVL(B.MIN_VALUE,0) MIN_VALUE,"+
					" NVL(B.MAX_VALUE,0) MAX_VALUE,A.DISPALY_POSITION,'Y' DIS_STATUS,  "+
					" NVL(D.DESCRIPTION,'-'), "+
					" NVL(C.SCORE,0), "+
					" NVL(C.REMARKS,'-') "+
					" FROM LAKDL.AF_CR_MAS_SCORE_SUB_CATEGORY A,LAKDL.AF_CR_MAS_SCORE_MODEL_DETAIL B,LAKDL.AF_CR_PRO_CRSCORE_DETAIL C, "+
					" LAKDL.AF_CR_MAS_SCORE_RATING D "+
					" WHERE A.SCORE_CODE='"+rs.getString(1)+"' AND "+
					" C.APPLICATION_CODE='"+m_app_code+"' AND "+
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
						out.println("<td width='20%'><DIV class=div_input>( MIN="+rs1.getString(3)+", MAX="+rs1.getString(4)+")  "+nf.format(rs1.getDouble(8))+" </DIV></td>"); 
						//out.println("<td width='20%'><DIV class=div_input>"+rs1.getString(8)+" ( MIN="+rs1.getString(3)+", MAX="+rs1.getString(4)+")</DIV></td>"); 
						out.println("<td width='47%'><DIV class=div_input>"+rs1.getString(9)+"</DIV></td>"); 

						out.println("</tr>");
						chk_nums++;
						i++;
						j++;
						}
					}
					out.println("<tr >");
					out.println("<br>"); 
					out.println("</tr>");
				}
				out.println("</table>");
			}
			rs.close();
			rs1.close();
			stmt.close();
			stmt1.close();
			}
			
			//!!----modified (2006/11/12)---------------------------------------------------------------------
			
			else if(m_chksql.equals("SCORE_APPROVAL")){
				
				String m_string="";
				
				rs= stmt.executeQuery ("SELECT DISTINCT APPLICATION_CODE,SCORE_MODEL_CODE,NVL(FINAL_APP_SCORE,0),"+
								" NVL(MODEL_SCORE,0),"+
								" NVL(COMMENTS,'-') "+
								" FROM "+m_schema_name+".AF_CR_PRO_CRSCORE "+
								" WHERE APP_STATUS='"+m_pre_stage1+"' AND APPLICATION_CODE = '"+m_applicaton_no+"' ORDER BY "+m_sort_column+" "+m_order_by_type+" ");				
				int i=0;


				m_string="<table align='center' width='100%' class='table' border=\"0\" >";

				m_string=m_string+"<tr class=\"pdn_txtpos2\">";

				m_string=m_string+"<td width='15%' align='left'>Application No</td>";
				m_string=m_string+"<td width='20%' align='left'>Credit Score Model</td>";
				m_string=m_string+"<td width='15%' align='left'>Application Score</td>"; 
				m_string=m_string+"<td width='10%' align='left'>Model Score</td>"; 
				m_string=m_string+"<td width='10%' align='center'>Details</td>"; 
				m_string=m_string+"<td width='20%' align='left'>Comments</td>"; 
				m_string=m_string+"<td width='5%' align='center'>Approve</td>"; 
				m_string=m_string+"<td width='5%' align='center'>Reject</td>";
				m_string=m_string+"</tr>";
	
	
				while(rs.next()){	
					if(i>0 && i%2==1){
					m_string=m_string+"<tr class=\"tr_input1\">";
					m_string=m_string+"<td width='15%' align='left'><DIV class=div_input>"+rs.getString(1)+"</DIV><INPUT TYPE=\"HIDDEN\" NAME=\"APP_"+i+"\" VALUE=\""+rs.getString(1)+"\"></td>";
					m_string=m_string+"<td width='20%' align='left'><DIV class=div_input>"+rs.getString(2)+"</DIV><INPUT TYPE=\"HIDDEN\" NAME=\"SCORE_"+i+"\" VALUE=\""+rs.getString(2)+"\"></td>";
					m_string=m_string+"<td width='15%' align='left'><DIV class=div_input>"+nf.format(rs.getDouble(3))+"</DIV></td>"; 
					m_string=m_string+"<td width='10%' align='left'><DIV class=div_input>"+nf.format(rs.getDouble(4))+"</DIV></td>"; 
					m_string=m_string+"<td width='10%' align='center'><DIV class=div_input><INPUT class='but_input' TYPE=\"BUTTON\" VALUE=\"View\" NAME=\"VIEW\" onclick=\"display_data('"+rs.getString(1)+"','"+rs.getString(2)+"')\"></DIV></td>"; 
					//m_string=m_string+"<td width='20%' align='left' ><input class='txt_input' style=\"{width:190}\"  type=\"text\" NAME=\"COMMENT_"+i+"\"  maxlength='500' ></td>"; 
					m_string=m_string+"<td width='20%' align='left' ><TEXTAREA NAME=\"COMMENT_"+i+"\" class='txt_input' style=\"width:190px; height:50px;\"  maxlength='500'></TEXTAREA></td>";
														
					m_string=m_string+"<td width='5%' align='center'><DIV class=div_input><INPUT TYPE=\"CHECKBOX\" value=\"N\" NAME=\"CHK_"+i+"\" onclick=change("+i+")></DIV></td>"; 
					m_string=m_string+"<td width='5%' align='center'><DIV class=div_input><INPUT TYPE=\"CHECKBOX\" value=\"N\" NAME=\"CHK_REJ_"+i+"\" onclick=change_reject("+i+")></DIV></td>"; 
					
					m_string=m_string+"</tr>";
					}
					else{
					m_string=m_string+"<tr class=\"tr_input\">";
					m_string=m_string+"<td width='15%' align='left'><DIV class=div_input>"+rs.getString(1)+"</DIV><INPUT TYPE=\"HIDDEN\" NAME=\"APP_"+i+"\" VALUE=\""+rs.getString(1)+"\"></td>";
					m_string=m_string+"<td width='20%' align='left'><DIV class=div_input>"+rs.getString(2)+"</DIV><INPUT TYPE=\"HIDDEN\" NAME=\"SCORE_"+i+"\" VALUE=\""+rs.getString(2)+"\"></td>";
					m_string=m_string+"<td width='15%' align='left'><DIV class=div_input>"+nf.format(rs.getDouble(3))+"</DIV></td>"; 
					m_string=m_string+"<td width='10%' align='left'><DIV class=div_input>"+nf.format(rs.getDouble(4))+"</DIV></td>"; 
					m_string=m_string+"<td width='10%' align='center'><DIV class=div_input><INPUT class='but_input' TYPE=\"BUTTON\" VALUE=\"View\" NAME=\"VIEW\" onclick=\"display_data('"+rs.getString(1)+"','"+rs.getString(2)+"')\"></DIV></td>"; 
					//m_string=m_string+"<td width='20%' align='left'><input style=\"{width:190}\"  class='txt_input' type=\"textarea\" NAME=\"COMMENT_"+i+"\"  maxlength='500' ></td>"; 
					m_string=m_string+"<td width='20%' align='left' ><TEXTAREA NAME=\"COMMENT_"+i+"\" class='txt_input' style=\"width:190px; height:50px;\"  maxlength='500'></TEXTAREA></td>";
					m_string=m_string+"<td width='5%' align='center'><DIV class=div_input><INPUT TYPE=\"CHECKBOX\"  value=\"N\" NAME=\"CHK_"+i+"\" onclick=change("+i+")></DIV></td>"; 
					m_string=m_string+"<td width='5%' align='center'><DIV class=div_input><INPUT TYPE=\"CHECKBOX\"  value=\"N\" NAME=\"CHK_REJ_"+i+"\" onclick=change_reject("+i+")></DIV></td>"; 
					m_string=m_string+"</tr>";
					}
					
					i++;
				}
				
				m_string=m_string+"<INPUT TYPE='hidden' NAME='NUM_CHKS' VALUE="+i+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			
			//!!--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
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

