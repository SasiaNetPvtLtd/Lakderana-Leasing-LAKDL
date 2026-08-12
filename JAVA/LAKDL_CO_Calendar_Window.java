import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_CO_Calendar_Window extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt1,stmt2;
	CallableStatement callstmt1;	
	
	public ResultSet rs;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException {
		
		try {
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url = m_sn_methods.html_client_url;
			String m_servlet_client_url= m_sn_methods.servlet_client_url;
			String m_client_t3_port= m_sn_methods.client_t3_port; 
			String m_client_name= m_sn_methods.client_name; 
			String m_username = m_sn_methods.username;
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			ServletOutputStream out = res.getOutputStream();
			
			stmt1=conn.createStatement();
			
			out.println("<HTML><HEAD><TITLE>Management Window</TITLE></HEAD>");
			//out.println("<link REL=\"STYLESHEET\" HREF=\""+m_html_client_url+"/main_page_styles.css\" TYPE=\"text/css\">");
			//out.println("<link REL=\"STYLESHEET\" HREF=\""+m_html_client_url+"/calender_style.css\" TYPE=\"text/css\">");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("<SCRIPT language=\"JavaScript\">");
			
			out.println("function rollon(){");
			out.println("if (event.srcElement.className ==\"hlink\") {");
			out.println("event.srcElement.className = \"hlinkMouseOver\";");
			out.println("}}");
			
			out.println("function rolloff() {");
			out.println("if (event.srcElement.className == \"hlinkMouseOver\") {");
			out.println("event.srcElement.className = \"hlink\";	}}");
			
			out.println("function open_url(m_url) {");
			out.println("var width=\"750\", height=\"500\";	");
			out.println("	var left = (screen.width/2) - width/2;	");
			out.println("	var top = (screen.height/2) - height/2;	");
			//out.println("	var styleStr = 'toolbar=no,location=no,directories=no,status=1,menubar=no,scrollbar=1,resizable=1,copyhistory=yes,width='+width+',height='+height+',left='+left+',top='+top+',screenX='+left+',screenY='+top;	");
			out.println("	var styleStr = 'scrollbars=1,resizable=1,status=1,width='+width+',height='+height+',left='+left+',top='+top+',screenX='+left+',screenY='+top;	");
			out.println("	var msgWindow = window.open(m_url,\"\", styleStr);	");
			out.println(" msgWindow.focus();");
			out.println("}");
			
			out.println("var obj,strobj;	");
			out.println("var tdate=new Date();	");
			out.println("var smonth=tdate.getMonth()+1;//get month	");
			out.println("var syear=tdate.getYear();//get year	");
			
			out.println("var strf_date;	");
			out.println("function start(){	");
			out.println("	document.Form1.mmonth.selectedIndex=smonth-1;	");
			out.println("document.Form1.syear.value=syear;");
			out.println("document.Form1.smonth.value=smonth;");
			out.println("}	");
			
			out.println("function load_cal(){	");
			out.println("	strf_date=syear+\"/\"+smonth+\"/\"+\"1\";	");
			out.println("	load_calendar();	");
			out.println("}	");
			out.println("function load_calendar(){	");
			out.println("var fdate=new Date(strf_date);	");
			out.println("	var st_day=fdate.getDay();	");
			out.println("	smonth=fdate.getMonth()+1;//get month	");
			out.println("	var ndays=0;	");
			out.println("	if (smonth==1||smonth==3||smonth==5||smonth==7||smonth==8||smonth==10||smonth==12){	");
			out.println("		ndays = 31;	");
			out.println("	}	");
			out.println("	else if (smonth==2){	");
			out.println("		var rem = syear%4;	");
			out.println("		if (rem==0){	");
			out.println("			ndays=29;	");
			out.println("		}	");
			out.println("		else{	");
			out.println("			ndays=28;	");
			out.println("		}	");
			out.println("	}	");
			out.println("	else{	");
			out.println("		ndays=30;	");
			out.println("	}	");
				
			out.println("	var i,j;	");
			out.println("	for(i=0;i<=41;i++){	");
			out.println("		obj=eval(\"d\"+i);	");
			out.println("		obj.innerHTML=\"\";");
			out.println("	}	");
				
			out.println("	j=st_day;	");
			out.println("	for(i=1;i<=ndays;i++){	");
			out.println("		obj=eval(\"d\"+j);	");
			out.println("		obj.innerHTML=i;	");
			out.println("		j=j+1;						");
			out.println("	}	");
			out.println("}	");
			out.println("function displayevent(sday){	");
			out.println(" val=sday.innerHTML;");
			out.println(" if(val.length=='1' && document.Form1.smonth.value.length=='1'){");
			out.println("   window.opener.load_c_date('0'+sday.innerHTML+'-0'+document.Form1.smonth.value+'-'+document.Form1.syear.value);");
			out.println(" }else if(val.length=='1' ){");
			out.println("   window.opener.load_c_date('0'+sday.innerHTML+'-'+document.Form1.smonth.value+'-'+document.Form1.syear.value);");
			out.println(" }else if(document.Form1.smonth.value.length=='1' ){");
			out.println("   window.opener.load_c_date(sday.innerHTML+'-0'+document.Form1.smonth.value+'-'+document.Form1.syear.value);");
			out.println(" }else{");
			out.println("   window.opener.load_c_date(sday.innerHTML+'-'+document.Form1.smonth.value+'-'+document.Form1.syear.value);");
			out.println(" }");
			//out.println("  alert(sday.innerHTML+'-'+document.Form1.smonth.value+'-'+document.Form1.syear.value);");
			out.println("  window.close();");
			/*
			out.println(" var width=\"300\", height=\"125\";	");
			out.println("	var left = (screen.width/2) - width/2;	");
			out.println("	var top = (screen.height/2) - height/2;	");
			out.println("	var styleStr = 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbar=no,resizable=no,copyhistory=yes,width='+width+',height='+height+',left='+left+',top='+top+',screenX='+left+',screenY='+top;	");
			out.println("	var msgWindow = window.open(\"\",\"msgWindow\", styleStr);	");
			out.println("	var head = '<head><title>Event</title></head>';	");
			out.println("	var body = '<center>no events fond...<br>'+document.Form1.syear.value+'/'+document.Form1.smonth.value+'/'+sday.innerHTML+'<p><form><input type=\"button\" value=\"   Done   \" onClick=\"self.close()\"></form>';	");
			out.println("msgWindow.document.write(head+body);");
			*/
			out.println("}");
			
			out.println("function loadmonth()	");
			out.println("{	strf_date=document.Form1.myear.value+\"/\"+document.Form1.mmonth.value+\"/\"+\"1\";	");
			out.println("document.Form1.syear.value=document.Form1.myear.value;");
			out.println("document.Form1.smonth.value=document.Form1.mmonth.value;");
			out.println("load_calendar();");
			out.println("}	");
			out.println("function loadYear(){	");
			out.println("strf_date=document.Form1.myear.value+\"/\"+document.Form1.mmonth.value+\"/\"+\"1\";");	
			out.println("document.Form1.syear.value=document.Form1.myear.value;");
			out.println("document.Form1.smonth.value=document.Form1.mmonth.value;");
			out.println("load_calendar();	");
			out.println("}	");
			
			out.println("function load_lock(){	");
			out.println("document.oncontextmenu=new Function(\"return false\");}	");

			out.println("</script>");
			out.println("<BODY LEFTMARGIN='0' TOPMARGIN='0' onload=\"load_cal();start()\">");
			out.println("<FORM NAME='Form1' >");
			out.println("<input  type=\"hidden\" value=\"\" name=\"syear\"> ");
			out.println("<input  type=\"hidden\" value=\"\" name=\"smonth\"> ");
			out.println("<input  type=\"hidden\" value=\"\" name=\"sday\"> ");
			out.println("<table align=\"center\">");
			out.println("<tr>");
			//------CALENDER WINDOW
			out.println("<TD WIDTH='70%' align=\"left\">");
			out.println("<table width=\"100%\" border=\"0\" align=\"left\"> ");
			out.println("<tr><td width=\"100%\">");
			out.println("<table width=\"100%\" border=\"0\" align=\"left\"> ");
			out.println("<tr class=tr_input><td width=\"1%\" ></td> ");
			out.println("<td width=\"20%\"> ");
			out.println("<select name=\"mmonth\" class=txt_input onChange=\"loadmonth()\" > ");
			out.println("<option value=\"1\" >January</option> ");
			out.println("<option value=\"2\">February</option> ");
			out.println("<option value=\"3\">March</option> ");
			out.println("<option value=\"4\">April</option> ");
			out.println("<option value=\"5\">May</option> ");
			out.println("<option value=\"6\">June</option> ");
			out.println("<option value=\"7\">July</option> ");
			out.println("<option value=\"8\">August</option> ");
			out.println("<option value=\"9\">September</option> ");
			out.println("<option value=\"10\">October</option> ");
			out.println("<option value=\"11\">November</option> ");
			out.println("<option value=\"12\">December</option> ");
			out.println("</select> ");
			out.println("</td> ");
			out.println("<td width=\"1%\"  ></td> ");
			out.println("<td width=\"70%\"> ");
			out.println("<select name=\"myear\"  onChange=\"loadYear()\" class=txt_input> ");
			
			//rs = stmt1.executeQuery(" SELECT TO_CHAR(SYSDATE,'YYYY')-3 FROM DUAL");
			rs = stmt1.executeQuery(" SELECT TO_CHAR(SYSDATE,'YYYY')-100 FROM DUAL"); //change by waruna 2012-04-24
			int m_last_gen = 0;
			
			boolean more = rs.next();
			
			if(more){
				for(int i = 0;i<=150;i++){
				  if(i==100){
				   out.println("<option value=\""+(rs.getInt(1)+i)+"\" selected>"+(rs.getInt(1)+i)+"</option> ");
					}else{
					 out.println("<option value=\""+(rs.getInt(1)+i)+"\" >"+(rs.getInt(1)+i)+"</option> ");
					}
					
				}
			}
			/*
			out.println("<option value=\"1995\" >1995</option> ");
			out.println("<option value=\"1996\">1996</option> ");
			out.println("<option value=\"1997\">1997</option> ");
			out.println("<option value=\"1998\">1998</option> ");
			out.println("<option value=\"1999\">1999</option> ");
			out.println("<option value=\"2000\">2000</option> ");
			out.println("<option value=\"2001\">2001</option> ");
			out.println("<option value=\"2002\">2002</option> ");
			out.println("<option value=\"2003\">2003</option> ");
			out.println("<option value=\"2004\">2004</option> ");
			out.println("<option value=\"2005\">2005</option> ");
			out.println("<option value=\"2006\" selected>2006</option> ");
			out.println("<option value=\"2007\">2007</option> ");
			out.println("<option value=\"2008\">2008</option> ");
			out.println("<option value=\"2009\">2009</option> ");
			out.println("<option value=\"2010\">2010</option> ");
			out.println("<option value=\"2011\">2011</option> ");
			out.println("<option value=\"2012\">2012</option> ");
			*/
			out.println("</select> ");
			out.println("</td> ");
			out.println("</tr> ");
			out.println("</table> ");
			out.println("</td></tr>");
			out.println("<tr><td width=\"100%\">");
			out.println("<table width=\"100%\" border=\"0\" align=\"left\"> ");
			out.println("<tr class=pdn_txtpos2>  ");
			out.println("<td width=\"10%\" >Sun</td> ");
			out.println("<td width=\"10%\" >Mon</td> ");
			out.println("<td width=\"10%\" >Tue</td>");
			out.println("<td width=\"10%\" >Wed</td> ");
			out.println("<td width=\"10%\" >Thu</td> ");
			out.println("<td width=\"10%\" >Fri</td> ");
			out.println("<td width=\"10%\" >Sat</td> ");
			out.println("</tr> ");
			out.println("<tr class=tr_input> ");
			out.println("<td  id=\"d0\" onclick=\"displayevent(d0);\">&nbsp;</td> ");
			out.println("<td  id=\"d1\" onclick=\"displayevent(d1);\">&nbsp;</td> ");
			out.println("<td  id=\"d2\" onclick=\"displayevent(d2);\">&nbsp;</td> ");
			out.println("<td  id=\"d3\" onclick=\"displayevent(d3);\">&nbsp;</td> ");
			out.println("<td  id=\"d4\" onclick=\"displayevent(d4);\">&nbsp;</td> ");
			out.println("<td  id=\"d5\" onclick=\"displayevent(d5);\">&nbsp;</td> ");
			out.println("<td  id=\"d6\" onclick=\"displayevent(d6);\">&nbsp;</td> ");
			out.println("</tr> ");
			out.println("<tr class=tr_input> ");
			out.println("<td  id=\"d7\" onclick=\"displayevent(d7);\">&nbsp;</td> ");
			out.println("<td  id=\"d8\" onclick=\"displayevent(d8);\">&nbsp;</td> ");
			out.println("<td  id=\"d9\" onclick=\"displayevent(d9);\">&nbsp;</td> ");
			out.println("<td  id=\"d10\" onclick=\"displayevent(d10);\">&nbsp;</td> ");
			out.println("<td  id=\"d11\" onclick=\"displayevent(d11);\">&nbsp;</td> ");
			out.println("<td  id=\"d12\" onclick=\"displayevent(d12);\">&nbsp;</td> ");
			out.println("<td  id=\"d13\" onclick=\"displayevent(d13);\">&nbsp;</td> ");
			out.println("</tr> ");
			out.println("<tr class=tr_input> ");
			out.println("<td  id=\"d14\" onclick=\"displayevent(d14);\">&nbsp;</td> ");
			out.println("<td  id=\"d15\" onclick=\"displayevent(d15);\">&nbsp;</td> ");
			out.println("<td  id=\"d16\" onclick=\"displayevent(d16);\">&nbsp;</td> ");
			out.println("<td  id=\"d17\" onclick=\"displayevent(d17);\">&nbsp;</td> ");
			out.println("<td  id=\"d18\" onclick=\"displayevent(d18);\">&nbsp;</td> ");
			out.println("<td  id=\"d19\" onclick=\"displayevent(d19);\">&nbsp;</td> ");
			out.println("<td  id=\"d20\" onclick=\"displayevent(d20);\">&nbsp;</td> ");
			out.println("</tr> ");
			out.println("<tr class=tr_input> ");
			out.println("<td  id=\"d21\" onclick=\"displayevent(d21);\">&nbsp;</td> ");
			out.println("<td  id=\"d22\" onclick=\"displayevent(d22);\">&nbsp;</td> ");
			out.println("<td  id=\"d23\" onclick=\"displayevent(d23);\">&nbsp;</td> ");
			out.println("<td  id=\"d24\" onclick=\"displayevent(d24);\">&nbsp;</td> ");
			out.println("<td  id=\"d25\" onclick=\"displayevent(d25);\">&nbsp;</td> ");
			out.println("<td  id=\"d26\" onclick=\"displayevent(d26);\">&nbsp;</td> ");
			out.println("<td  id=\"d27\" onclick=\"displayevent(d27);\">&nbsp;</td> ");
			out.println("</tr> ");
			out.println("<tr class=tr_input> ");
			out.println("<td  id=\"d28\" onclick=\"displayevent(d28);\">&nbsp;</td> ");
			out.println("<td  id=\"d29\" onclick=\"displayevent(d29);\">&nbsp;</td> ");
			out.println("<td  id=\"d30\" onclick=\"displayevent(d30);\">&nbsp;</td> ");
			out.println("<td  id=\"d31\" onclick=\"displayevent(d31);\">&nbsp;</td> ");
			out.println("<td  id=\"d32\" onclick=\"displayevent(d32);\">&nbsp;</td> ");
			out.println("<td  id=\"d33\" onclick=\"displayevent(d33);\">&nbsp;</td> ");
			out.println("<td  id=\"d34\" onclick=\"displayevent(d34);\">&nbsp;</td> ");
			out.println("</tr> ");
			out.println("<tr class=tr_input> ");
			out.println("<td  id=\"d35\" onclick=\"displayevent(d35);\">&nbsp;</td> ");
			out.println("<td  id=\"d36\" onclick=\"displayevent(d36);\">&nbsp;</td> ");
			out.println("<td  id=\"d37\" onclick=\"displayevent(d37);\">&nbsp;</td> ");
			out.println("<td  id=\"d38\" onclick=\"displayevent(d38);\">&nbsp;</td> ");
			out.println("<td  id=\"d39\" onclick=\"displayevent(d39);\">&nbsp;</td> ");
			out.println("<td  id=\"d40\" onclick=\"displayevent(d41);\">&nbsp;</td> ");
			out.println("<td  id=\"d41\" onclick=\"displayevent(d42);\">&nbsp;</td> ");
			out.println("</tr> ");
			out.println("</table> ");	
			out.println("</td></tr>");
			out.println("</table> ");	
			out.println("</TD>");
			//------END
			out.println("</TR>");
			out.println("</TABLE>");
			out.println("<HR>");
			
			out.println("</form>");
			out.println("</body></html>");
			
			out.close();
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
