//Created by Ashini 
//Invoice Collection Report Follow_up 19-11-2007
  
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_RE_inv_collection_Report_Follow_up extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1;
	public String m_chksql;
	ServletOutputStream out = null;
	
  public synchronized void service(HttpServletRequest req, HttpServletResponse res){
		
		try {
			
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
		  conn = m_sn_methods.met_user_validate(req); 

			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			
			String m_schema_name = m_sn_methods.schema_name;
			String m_servlet_client_url=m_sn_methods.servlet_client_url;
			String m_client_name=m_sn_methods.client_name;
			String m_client_t3_port=m_sn_methods.client_t3_port;
			
     	String m_username=m_sn_methods.username;
			out=res.getOutputStream();
			stmt=conn.createStatement();	
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(2);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			String m_chksql=req.getParameter("chksql");
	
			if(m_chksql.equals("main_page")){ 
			
			String m_invoice_seq_no=req.getParameter("INV_SEQ_NO").trim();
			
			String m_dd = "";
			String m_mm = "";
			String m_yy = "";
			
			rs = stmt.executeQuery(" "+
					" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL "+		
			" ");
			
			if(rs.next()){
				m_dd = rs.getString(1);
				m_mm = rs.getString(2);
				m_yy = rs.getString(3);
			}
			
										
         out.println("<html>");
			out.println("<head>");
			out.println("<title>Asset Financing System</title>    ");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			out.println("</head>");
			out.println("<Script>");
						 
			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			
			out.println("function before_submit(){ "); 
			out.println("		if(confirm(\"Are you sure you want to Save?\")){ "); 
			out.println("			document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_Save_inv_Collection_Report_Follow_up';");  
			out.println("			document.Form1.submit();"); 
			out.println("		}"); 
			out.println("} "); 
			
			// commented by udara somathilake on 18-11-2010
			/*
						//-------------------ADDED BY ASHINI ON 28-02-2008--------------------------------------------------------------------
			out.println("function show_data(){	"); 
		 	out.println("document.Form1.TXT_COMMENT.value=document.Form1.TXT_REMARK_TYPE.options[document.Form1.TXT_REMARK_TYPE.selectedIndex].text;"); 
		   out.println("m_code=document.Form1.TXT_REMARK_TYPE.value;"); 
			out.println("}"); 
			//--------END MODIFICATION DONE BY ASHINI ON 28-02-2008---------------------------------------------------------------
			*/
			
			// added by udara somathilake on 18-11-2010
			out.println("function show_data(){	"); 
			out.println("		var action_date = document.Form1.TXT_ACTION_DD.value+'-'+document.Form1.TXT_ACTION_MM.value+'-'+document.Form1.TXT_ACTION_YY.value;");  // TXT_ACTION_DD
		 	out.println("		document.Form1.TXT_COMMENT.value=document.Form1.TXT_REMARK_TYPE.options[document.Form1.TXT_REMARK_TYPE.selectedIndex].text+'  '+action_date;"); 
		   out.println("		m_code=document.Form1.TXT_REMARK_TYPE.value;"); 
			out.println("}"); 
			
		
			// Added by Udara Somathilake on 16-11-2010 to show Calender
			out.println("function load_calendar() {");
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=450,top=200,width=320,height=230\");"); 
			out.println("}");
		
		   out.println("function load_c_date(val) {");
			out.println("     v_dd = val.substr(0,val.indexOf('-'))");
			out.println("     if(v_dd.length <2) ");
			out.println("     v_dd = 0+v_dd ");
			out.println("     val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("     v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("     if(v_mm.length <2) ");
			out.println("     v_mm = 0+v_mm ");
			out.println("     v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.TXT_ACTION_DD.value=v_dd;");
			out.println("     document.Form1.TXT_ACTION_MM.value=v_mm;");
			out.println("     document.Form1.TXT_ACTION_YY.value=v_yy;");
			out.println("     show_data();"); 
			out.println("}");	
		
		
         out.println("</Script>");
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' onLoad=\"\">"); //load_lock()
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<INPUT TYPE='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='hidden' value='"+m_invoice_seq_no+"' name='INVOICE_NO'> "); 
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
			out.println("<tr> "); 
			out.println("<td height='30' class='pdn_mainHD'>"+header_name+"</td>"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process - Collection Follow up</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<td width='10%'></td>");  
			out.println("<td width='10%'></td>");  
			out.println("<td width='10%'></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>"); 
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  			
			out.println("</table>");  
			out.println("</td></tr><tr>");   
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos'  valign='top'>"); 
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='90%'>"); 
			out.println("<tr >");
			out.println("<td width='30%' ><DIV id='DIV_TXT_COMMENT_TYPE' class=div_input>Comment Type</DIV></td>");
			out.println("<td width='60%' ><SELECT name=\"TXT_COMMENT_TYPE\" class=\"txt_input\" style='width:150'> ");
			out.println("<OPTION value=\"DW\" selected>Debtor wise</OPTION>");
			out.println("<OPTION value=\"IW\">Invoice wise</OPTION>");
			out.println("</SELECT>");
			out.println("</tr>");
			out.println("</table>");    
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='90%'>"); 
			
						//-------------------ADDED BY ASHINI ON 28-02-2008--------------------------------------------------------------------
			
					rs1 = stmt.executeQuery ("SELECT REMARK_CODE,REMARK_DESC "+
				" FROM "+m_schema_name+".FA_CO_MAS_REMARKS "+
				" WHERE ACTIVE_STATUS='Y' ");
				
					out.println("<tr>");
					out.println("<td width='30%' ><DIV id='DIV_TXT_REMARK_TYPE'  class=div_input>Remarks</DIV></td>"); 
					//out.println("</tr>");
					//out.println("<tr>");
					out.println("<td width='60%' ><select class=txt_input type=text name=TXT_REMARK_TYPE maxlength=1 size=1 onChange='show_data()' >");  
					out.println("<option value='NO' selected ></option>");
			   while(rs1.next()){
					out.println("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option> "); 
					}
					out.println("</select></td>");
					out.println("</tr>");
					
					
					// Added by Udara Somathilake on 04-11-2010 - Follow Up Date Fields
					out.println("<tr >");
					out.println("<td width=\"15%\"><DIV id=\"DIV_TXT_INVOICE_DATE\" class=div_input>Action Date </DIV></td>");
					out.println("<td width=\"60%\"> ");
					out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_ACTION_DD\" maxlength=\"2\" size=\"2\" value=\""+m_dd+"\" onblur=\"checkMonthLength(document.Form1.TXT_ACTION_DD,document.Form1.TXT_ACTION_MM,document.Form1.TXT_ACTION_YY),show_data()\" tabindex=\"3\">");
					out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_ACTION_MM\" maxlength=\"2\" size=\"2\" value=\""+m_mm+"\" onblur=\"checkMonthLength(document.Form1.TXT_ACTION_DD,document.Form1.TXT_ACTION_MM,document.Form1.TXT_ACTION_YY),show_data()\" tabindex=\"4\">");
					out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_ACTION_YY\" maxlength=\"4\" size=\"4\" value=\""+m_yy+"\" onblur=\"checkMonthLength(document.Form1.TXT_ACTION_DD,document.Form1.TXT_ACTION_MM,document.Form1.TXT_ACTION_YY),show_data()\" tabindex=\"5\">[DD-MM-YYYY] <a href style=\"{cursor:hand; }\" onclick=\"load_calendar()\">   Calendar</a>");
					out.println("</td>");
					out.println("<td width='*%'></td>"); 
					out.println("</tr>");
					// End by Udara Somathilake on 04-11-2010   - Follow Up Date Fields
					
					
			out.println("</table>");    
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='90%'>"); 
			
			//--------END MODIFICATION DONE BY ASHINI ON 28-02-2008---------------------------------------------------------------

			
			out.println("<tr >");
			out.println("<td width=\"30%\" class='tr_input'><b>Other Remarks</b></td>"); 
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='30%' ><TEXTAREA class='txt_input' name='TXT_COMMENT' style=\"width:350px; height:70px;\" maxlength=\"200\" size=\"200\"></TEXTAREA></td>");  //modified by nuwan de silva on 14-09-07
			out.println("</tr>");
			out.println("</table>");    
			out.println("<hr>");
			
			rs=stmt.executeQuery(" "+
			" SELECT SEQ_NO,ENT_USER,TO_CHAR(ENT_DATE,'DD-MM-YYYY HH24:MI:SS'),COMMENTS "+
			" FROM ( "+
			" SELECT INVOICE_SEQ_NO SEQ_NO, "+
			" ENT_USER, "+
			" ENT_DATE, "+
			" NVL(COMMENTS,'-') COMMENTS "+
			" FROM "+m_schema_name+".FA_CR_PRO_COLL_COMMENT "+
			" WHERE INVOICE_SEQ_NO='"+m_invoice_seq_no+"' "+
			" UNION ALL "+
			" SELECT A.DEBTOR_CODE SEQ_NO,"+
			" A.ENT_USER,"+
			" A.ENT_DATE, "+
			" NVL(A.COMMENTS,'-') COMMENTS "+
			" FROM "+m_schema_name+".FA_CR_PRO_DEBTOR_CRD_COMMENTS A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B"+
			" WHERE A.DEBTOR_CODE=B.DEBTOR_CODE "+
			" AND B.INVOICE_SEQ_NO='"+m_invoice_seq_no+"'"+
			" ) "+
			" ORDER BY ENT_DATE DESC ");
			
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
			out.println("<tr >");
			out.println("<td width=\"20%\" class='tr_input'><b>Enter User</b></td>"); 
			out.println("<td width=\"25%\" class='tr_input'><b>Enter Date/Time</b></td>"); 
			out.println("<td width=\"60%\" class='tr_input'><b>Comments</b></td>"); 
			out.println("</tr>");
			
			while(rs.next()){
			out.println("<tr >");
			out.println("<td width=\"20%\" class='tr_input'>"+rs.getString(2)+"</td>"); 
			out.println("<td width=\"25%\" class='tr_input'>"+rs.getString(3)+"</td>"); 
			out.println("<td width=\"60%\" class='tr_input'>"+rs.getString(4)+"</td>"); 
			out.println("</tr>");
			}
			out.println("</table>"); 
			
			out.println("</form>");
			out.println("</body>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("</html>");
      }			
		}
		catch (Exception e) {
			try{out.println(e.toString());}catch(Exception e1){}
		}finally{
		  if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
	    if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
