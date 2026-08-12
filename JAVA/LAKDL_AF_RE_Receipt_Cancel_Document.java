import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_RE_Receipt_Cancel_Document extends javax.servlet.http.HttpServlet { 

ServletOutputStream out = null;
Connection conn;
Statement stmt,stmt1,stmt2,stmt3,stmt4;
CallableStatement callstmt1;
java.text.NumberFormat nf;
	
public ResultSet rs;
public ResultSet rs1,rs2,rs3,rs4;
public String m_chksql,m_html_client_url,reqstr,m_Letter_date;
public String m_c_code,m_add1,m_add2,m_name,m_city_desc,m_due_date;
public String m_receipt_no,m_client_no,m_no_of_due_date,m_finance_no;
public String m_print,m_inv_type,m_inv_no,m_vat_reg_no,m_vat_reg_date,m_value_date;
public double m_amount_due;
public double m_gross_rent;
public String m_LAKDL_vat_no="",m_email="";
public String m_vat_precentage="";
	
	
public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
try { 
		 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			int m_count=0;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
		  nf.setMinimumFractionDigits(2);
		  nf.setMaximumFractionDigits(2);
			
			String m_orient_name="";
			String m_orient_add1="";
			String m_orient_add2="";
			String m_orient_city_name="";
			String m_orient_tel_no="";
			String m_orient_fax_no="";
			String m_orient_vat_rate="";
			String m_comments="",m_payer_branch="",m_cheque_cash_type="";
			
			m_chksql = req.getParameter("chksql");
			m_receipt_no = req.getParameter("receipt_no");		
			//m_client_no = req.getParameter("client_no");		
			m_print = req.getParameter("print");
			
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();
			stmt3 = conn.createStatement();
			stmt4 = conn.createStatement();
			
			//modified by nuwan de silva ---------23-07-07----------------------
			 rs = stmt.executeQuery(" SELECT "+
					    " UPPER(NVL(COMPANY_NAME,' ')), "+
					    " INITCAP(NVL(ADDRESS1,' ')), "+
					    " INITCAP(NVL(ADDRESS2,' ')), "+
					    " INITCAP(NVL(CITY,' ')), "+
					    " NVL(TEL_NO,' '), "+
					    " NVL(FAX_NO,' '),  "+
							" NVL(VAT_RATE,0), "+
							" NVL(VAT_REG_NO,' '), "+
							" NVL(EMAIL, ' ') "+
							" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ");
							boolean  more = rs.next();		
											
											if(more)
											{
											m_orient_name=rs.getString(1);
											m_orient_add1=rs.getString(2);
											m_orient_add2=rs.getString(3);
											m_orient_city_name=rs.getString(4);
											m_orient_tel_no=rs.getString(5);
											m_orient_fax_no=rs.getString(6);
											m_vat_precentage=rs.getString(7);			
											m_LAKDL_vat_no=rs.getString(8);			
											m_email=rs.getString(9);			
											}
		
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}					
			else if (m_chksql.trim().equals("main_page")) {

			out.println("<html><head>"); 
			out.println("<title>Receipt Document</title></head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");			
			out.println("<script>");
			out.println("function save_data(){");
			out.println("m_table.innerHTML=\"\" ");
			//out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_RE_Save_Collection_Receipt_Document?chksql=save_page&receipt_no="+m_receipt_no+"&client_no="+m_client_no+"&scr_name=AF_RE_SETTELMENT\";"); 
		  //out.println(" window.location.href=m_url;"); 
			out.println("window.print();");
			out.println("}");
			out.println("function add_button(){");
			if (m_print.trim().equals("FALSE")) {
			out.println("m_table.innerHTML=\"\" ");
			}
			else
			{
			out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_writedata+'</table>';");
			}
		  out.println("}");
			out.println("</script>");
				
			out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");	
			out.println("<body bgcolor='white'>");	
			out.println("<form name='Form1'>");				
			String m_third_party_name="",m_third_party_add="",m_sys_date="";
			double m_rec_amount=0;

		    
				String sub_rec_no = "";
				
				rs = stmt.executeQuery ("SELECT TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),NVL(comments,'-') ,NVL(rec_amount,0) , client_code "+
				                        " ,NVL(THIRD_PARTY_NAME,'-'), NVL(THIRD_PARTY_ADDRESS,'-') ,TO_CHAR(SYSDATE,'DD-MM-YYYY'), NVL(A.SUB_REC_NO,A.REC_NO) "+ // 
										" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A, "+m_schema_name+".af_co_pro_receipt_cancel B"+
								                " WHERE A.REC_NO=B.REC_NO AND A.REC_NO=UPPER('"+m_receipt_no+"') ");							
				more = rs.next();
				
				if(more){
				m_Letter_date=rs.getString(1);
				m_comments=rs.getString(2);
				m_rec_amount=rs.getDouble(3);
				m_client_no=rs.getString(4);
				m_third_party_name=rs.getString(5);
			  m_third_party_add =rs.getString(6);
				m_sys_date =rs.getString(7);
				sub_rec_no = rs.getString(8); // added by udara on 10-07-2013

				}

				rs1 = stmt1.executeQuery (	" SELECT "+
  		  " CLIENT_CODE, "+
  		  " UPPER(FULL_NAME), "+
  		  " NVL(UPPER(ADDRESS1),'ADD1'), "+
  		  " NVL(UPPER(ADDRESS2),'ADD2'), "+
  		  " NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),'CITY') CITY_NAME ,"+
				" NVL(VAT_REG_NO,'-') VAT_REG_NO "+
				" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
  		  " WHERE CLIENT_CODE='"+m_client_no+"' ");
				
				more = rs1.next();
				if(more){
				m_c_code=rs1.getString(1);
				m_name=rs1.getString(2);
				m_add1=rs1.getString(3);
				m_add2=rs1.getString(4);
				m_city_desc=rs1.getString(5);
				m_vat_reg_no=rs1.getString(6);
				}
				String m_address="";
				
				if(m_add1.equals("ADD1")){
				m_address="";
				}
				else{
				m_address=m_add1;
				}
				if(!m_add2.equals("ADD2")){
				m_address=m_address+","+m_add2;
				}
							
				
				/*out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='15%'class='rep-body'>&nbsp;</td><td width='50%'class='rep-body'>"+m_receipt_no+"</td><td width='10%' class='rep-body' align='right'>&nbsp;</td><td width='20%' class='rep-body' align='right'>"+m_Letter_date+"</td></tr>");
				out.println("</table>");
				*/
			/*	out.println("<table border='0' width='100%' class='table'>");		
				
				if(!m_third_party_name.equals("-")){
				out.println("<tr><td width='20%' class='rep-body' >&nbsp;</td><td width='*%' class='rep-body' >"+m_third_party_name+"</td></tr>");	
				}
				else{
				out.println("<tr><td width='20%' class='rep-body' >&nbsp;</td><td width='*%' class='rep-body' >"+m_name+"</td></tr>");	
				}

			out.println("</TABLE>");			
			*/
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
			out.println("</tr>"); 
			out.println("</table>");
			
			out.println("<table border='0' width='100%' class='table'>"); 
			out.println("<tr><td width='*%' class='rep-body' >Date : "+m_sys_date+"</td></tr>");
			//out.println("<tr><td width='*%' class='rep-body' >Rec No : "+m_receipt_no+"</td></tr>"); // commented by udara on 10-07-2013
			out.println("<tr><td width='*%' class='rep-body' >Rec No : "+sub_rec_no+"</td></tr>"); // added by udara on 10-07-2013
			out.println("</table>");
			out.println("<br><br>");	

			out.println("<table border='0' width='100%' class='table'>"); 
			if(!m_third_party_name.equals("-")){
			out.println("<tr><td width='*%' class='rep-body' >"+m_third_party_name+"</td></tr>");
			}
			else{
			out.println("<tr><td width='*%' class='rep-body' >"+m_name+"</td></tr>");
			}
			if(!m_third_party_name.equals("-")){
			out.println("<tr></td><td width='*%' class='rep-body' >"+m_third_party_add+"</td></tr>");
			}
			else{
			out.println("<tr><td width='*%' class='rep-body' >"+m_address+"</td></tr>");
			}
			if(m_third_party_name.equals("-")){
			if(m_city_desc.equals("CITY")){ //Added by Chandana on 11/06/2007
			}
			else{			
			out.println("<tr><td width='*%' class='rep-body' >"+m_city_desc+".</td></tr>");
			}
			}
			out.println("</table>");
			
			out.println("<br><br><br>");	
			
			out.println("<table border='1' bordercolor='black' cellspacing='0' width='100%' class='table' >");
			out.println("<tr><td width='80%' class='rep-body'>Description</td><td width='20%' class='rep-body' align='right' >Total</td></tr>");
			out.println("<tr height='360px' valign='top'><td width='80%' class='rep-body'>"+m_comments+"</td><td width='20%' class='rep-body' align='right'>"+nf.format(m_rec_amount)+"</td></tr>");
			out.println("<tr                ><td width='80%' class='rep-body'>Total</td><td width='20%' class='rep-body' align='right' >"+nf.format(m_rec_amount)+"</td></tr>");
			out.println("</table>");

			out.println("</form></body></html>");
			}//main page end
		
			
			out.flush();
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
		    if(rs!=null){try{rs.close();  }catch(Exception e){}}
				if(stmt!=null){try{stmt.close();  }catch(Exception e){}}
				if(rs1!=null){try{rs1.close();  }catch(Exception e){}}
				if(stmt1!=null){try{stmt1.close();  }catch(Exception e){}}
				if(rs2!=null){try{rs2.close();  }catch(Exception e){}}
				if(stmt2!=null){try{stmt2.close();  }catch(Exception e){}}
				if(conn!=null){try{conn.close();  }catch(Exception e){}}
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
