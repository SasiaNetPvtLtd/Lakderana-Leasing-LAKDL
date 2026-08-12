import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
//import CLAMF_sn_methods;
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_CO_PRO_Genarate_Letter extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt1,stmt2;
	CallableStatement callstmt;
	java.text.NumberFormat nf;
	java.text.NumberFormat nf1;
    
  public ResultSet rs,rs1,rs_ind,rs2;
	public String m_chksql,m_qua,m_price,m_opt,m_url;
	public String m_st="";
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		try {
		
			//************************************************************	
			//SCREEN_METHODS m_sn_methods = new SCREEN_METHODS();
			LAKDL_AF_CO_conn_methods m_sn_methods=new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url = m_sn_methods.html_client_url;
			String m_class_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();

			//**************************************************************					
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
            
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			res.setHeader("Cache-Control", "No-Cache");
     	res.setDateHeader("Expires", 0);

			ServletOutputStream out = res.getOutputStream();
			String m_sp_name="";
			String m_pricing="";
			m_chksql=req.getParameter("chksql");
			stmt=conn.createStatement();
			stmt2=conn.createStatement();
			stmt1=conn.createStatement();
			m_url = m_class_url;

			String m_st="0";
			if (m_chksql.trim().equals("idle")) {
			out.println("idle");
			}	
			
			
			else if (m_chksql.trim().equals("view_letter")){
		
	
		String m_Client_Code = req.getParameter("data_val").trim();
		String m_Header = req.getParameter("header");
		String m_Body   = req.getParameter("body");
		String m_Signature= req.getParameter("signature");
		String m_status = req.getParameter("ac_status");
		String m_model="";
		String m_make="";
		String m_item="";
		String m_un="";
		String m_client_name="";
		String m_mk="";
		String m_title="";
		String m_sub_type="";
		String m_client_add1="", m_client_add2="", m_client_add3="";
		String m_com_name="", m_com_add1="", m_com_add2="", m_city="";
	//-----------------------------------------------------------------------------------------
	
	
		rs= stmt.executeQuery ("SELECT  	CLIENT_CATEGORY, "+
				                       " CLIENT_CODE,INITCAP(FULL_NAME), "+
															 " ADDRESS1,ADDRESS2,CITY_CODE "+
															 " FROM  LAKDL.AF_CO_MAS_CLIENT "+
															 " WHERE UPPER(CLIENT_CODE)='"+m_Client_Code+"'");

		boolean more=rs.next();	
	
		if(more)
		{
		m_qua=rs.getString(1);
		m_client_name=rs.getString(3);
		m_client_add1=rs.getString(4);
		m_client_add2=rs.getString(5);
		m_client_add3=rs.getString(6);
		}
		
		out.println("<html><head><font 10pt arial><title></title></head>");
		out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
		
		out.println("<SCRIPT language=\"JavaScript\">"); 
		
		out.println("function add_button(){");
		out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"print_letter()\"></td></tr>';"); 
		out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    out.println("m_writedata+'</table>';");
		out.println("}");
		
		
		out.println("function print_letter(){");
		out.println("m_table.innerHTML=\"\"");
		out.println("window.print();");
		out.println("}");
		out.println("</script>"); 
		
		out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");
		out.println("<br>");
    out.println("<form name='form1'>");
		
		
		
		out.println("<table border='0' width='100%' class=table>");
	  out.println("</table>");
		
		//out.println("<table border='0' width='100%' class=table style='text-align:right'>");
		//out.println("<tr class=tr_input>");
	  //out.println("<td width='70%'>"); 
		//out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"print_letter()\" ></td>"); 
		//out.println("</tr>");
		//out.println("</table>");
		
		out.println("<table align='center' width='100%' class='table'>"); 
		out.println("<tr>");  
		out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		out.println("</tr>"); 
		out.println("</table>");
		
		
	  //out.println("<blockquote><font size=2><p style='text-align:right'>");	
		out.println("<table border='0' width='100%' class=table style='text-align:left'>");
		out.println("<tr><td width='70%'>");
	  out.println("<td width='*%' class='rep-body'>"+m_client_name+"</td></tr>");
		out.println("<tr><td width='70%'>");
	  out.println("<td width='*%' class='rep-body'>"+m_client_add1+"</td></tr>");
		out.println("<tr><td width='70%'>");
	  out.println("<td width='*%' class='rep-body'>"+m_client_add2+"</td></tr>");
    out.println("<tr><td width='70%'>");
	  out.println("<td width='*%' class='rep-body'>"+m_client_add3+"</td></tr>");
		//out.println("</blockquote>");
		out.println("</table>");
    
			rs1= stmt.executeQuery (" SELECT COMPANY_NAME,ADDRESS1,ADDRESS2, CITY FROM LAKDL.AF_CO_MAS_COMPANY_DETAILS ");
		  
				boolean more1=rs1.next();	
	
		if(more1)
		{
		m_com_name=rs1.getString(1);
		m_com_add1=rs1.getString(2);
		m_com_add2=rs1.getString(3);
		m_city=rs1.getString(4);
		}
		out.println("<blockquote><font size=2><p style='text-align:left'>");	
		out.println("<table border='0' width='100%' class=table >");
		out.println("<tr>");
	  out.println("<td width='*%' class='rep-body'>"+m_com_name+",</td><td width='20%'></tr>");
		out.println("<tr>");
	  out.println("<td width='*%' class='rep-body'>"+m_com_add1+",</td><td width='20%'></tr>");
		out.println("<tr>");
	  out.println("<td width='*%' class='rep-body'>"+m_com_add2+",</td><td width='20%'></tr>");
		out.println("<tr>");
	  out.println("<td width='*%' class='rep-body'>"+m_city+".</td><td width='20%'></tr>");
		out.println("</table>");
		out.println("</blockquote>");
		
		out.println("<blockquote><font size=2><p style='text-align:left'>");	
		out.println("<table border='0' width='100%' class=table >");
		out.println("<tr>");
	  out.println("<td width='*%' class='rep-body'>Dear Sir/Madam, &nbsp&nbsp&nbsp&nbsp&nbsp</td><td width='20%'></td></tr>");
		out.println("</table>");
		//out.println("</blockquote>");
		
		out.println("<blockquote><font size=2><p style='text-align:center'>");	
		out.println("<table border='0' width='100%' class=table >");
		out.println("<tr>");
	  out.println("<td width='*%' class='rep-body'><U><B>"+m_Header+"</B></U></td><td width='14%'></td></tr>");
		out.println("</table>");
		out.println("</blockquote>");
		out.println("</blockquote>");
    //out.println("<br>");
		
		out.println("<blockquote><font size=2><p style='text-align:justify'>");	
		out.println("<table border='0' width='100%' class=table style='text-align:justify'>");
		out.println("<tr>");
	  out.println("<td width='*%' class='rep-body'><P>"+m_Body+"</P></td><td width='10%'></td></tr>");
		out.println("</table>");
		out.println("</blockquote>");
    out.println("<br>");
		
		//Authorized Signatory
		
		out.println("<blockquote><font size=2><p style='text-align:left'>");	
		out.println("<table border='0' width='100%' class=table >");
		out.println("<tr>");
	  out.println("<td width='*%' class='rep-body'>Yours faithfully,</td><td width='20%'></tr>");
		out.println("<tr>");
	  out.println("<td width='*%' class='rep-body'><B>"+m_com_name+".</B></td><td width='20%'></tr>");
		out.println("</table>");
		out.println("</blockquote>");
		out.println("<br>");
		out.println("<br>");
		
		out.println("<blockquote><font size=4><p style='text-align:left'>");	
		out.println("<table border='0' width='100%' class=table >");
		out.println("<tr>");
	  out.println("<td width='*%' class='rep-body'><B>......................................</B></td><td width='20%'></tr>");
		out.println("<tr>");
	  out.println("<td width='*%' class='rep-body'>"+m_Signature+"</td><td width='20%'></tr>");
		out.println("<tr>");
	  out.println("<td width='*%' class='rep-body'><B>Authorized Signatory</B></td><td width='20%'></tr>");
		out.println("</table>");
		out.println("</blockquote>");
    

    out.println("</body></html>");

	
			
			
		}	
			
			
			
			
			
			
			
			
		else if (m_chksql.trim().equals("view_letter1")){
		
	
		String m_Client_Code = req.getParameter("data_val").trim();
		String m_status = req.getParameter("ac_status");
		String m_model="";
		String m_make="";
		String m_item="";
		String m_un="";
		String m_client_name="";
		String m_mk="";
		String m_title="";
		String m_sub_type="";
		String m_client_add1="", m_client_add2="", m_client_add3="";
	//-----------------------------------------------------------------------------------------
	out.println("test123"+m_Client_Code);
	
		rs= stmt.executeQuery ("SELECT  	CLIENT_CATEGORY, "+
				                       " CLIENT_CODE,INITCAP(FULL_NAME), "+
															 " ADDRESS1,ADDRESS2,CITY_CODE "+
															 " FROM  LAKDL.AF_CO_MAS_CLIENT "+
															 " WHERE UPPER(CLIENT_CODE)='"+m_Client_Code+"'");

		boolean more=rs.next();	
	
		if(more)
		{
		m_qua=rs.getString(1);
		m_client_name=rs.getString(3);
		m_client_add1=rs.getString(4);
		m_client_add2=rs.getString(5);
		m_client_add3=rs.getString(6);
		
		
		out.println("test123"+m_client_name);
		

   // out.println("<html><head><font 10pt arial><title></title></head>");
		//out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
		//out.println("<body leftmargin='0' topmargin='0' class=body>");
		//out.println("<br>");
   // out.println("<form name='form1'>");
		//out.println("<table border='0' width='100%' class=table>");
	//	out.println("</table>");
	//	out.println("<blockquote><font size=2><p style='text-align:right'>");	
	//	out.println("<table border='0' width='100%' class=table >");
	//	out.println("<tr><td width='*%' class='rep-body'>"+m_client_name+"</td></tr>");

 //   out.println("</table>");

    //out.println("</body></html>");

	}
		else
		{
		out.println("test");
		
		
	

		
		out.println("<html><head><font 10pt arial><title>INDICATIVE QUOTATION</title></head>");
		out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
		out.println("<body leftmargin='0' topmargin='0' class=body>");
		out.println("<br>");
		out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo.gif\"></DIV><br><hr>");
		out.println("<br>");
		out.println("<div align='center' width='162%' class=div_input><b>INDICATIVE QUOTATION -NO ("+rs.getString(1)+") </div>");
		out.println("<form name='form1'>");
		out.println("<table border='0' width='100%' class=table>");
		out.println("</table>");
		out.println("<blockquote><font size=2><p style='text-align:right'>");	
		out.println("<table border='0' width='100%' class=table >");
		out.println("<tr><td width='*%' class='rep-body'>"+rs.getString(9)+"</td></tr>");
		out.println("<tr><td width='*%' class='rep-body' style='{ font:bold;}'><br>"+m_title+" "+rs.getString(3)+" "+m_client_name+"</td></tr>");
		out.println("<tr><td width='*%' class='rep-body' >"+rs.getString(4)+"</td></tr>");
		out.println("<tr><td width='*%' class='rep-body' >"+rs.getString(5)+"</td></tr>");
		out.println("<tr><td width='*%' class='rep-body' >"+rs.getString(7)+"</td></tr>");
		out.println("<tr ><td width='*%' class='rep-body' style='{text-align:left;}'><br>Dear Sir/Madam, </td>");
		out.println("<tr><td width='*%' class='rep-body' style='{text-align:left;}'><br>We thank you for your inquiry and are pleased to forward the following Indicative Quotation for your perusal.</td></tr>");
		out.println("<tr ><td width='*%' class='rep-body' style='{text-align:left;}'><br><b>* "+rs.getString(30)+"</td></tr>");
		out.println("</table>");
		
		out.println("</font></p></blockquote>");

		while(rs.getString(1).trim().equals(m_qua)){
		
		m_opt=rs.getString(11);
		out.println("<blockquote><font size=2><p style='text-align:right'>");	

		out.println("<table border='0' width='100%' class=table>");
		out.println("<tr>");
		out.println("<td width='36%'><b>Option  ("+rs.getString(11)+")</td>");
		out.println("</tr>");
		out.println("</table>");
		
		while(rs.getString(11).trim().equals(m_opt)){
		if(rs.getString(6)==null) {
		m_model="";
		}
		else{
		m_model=rs.getString(6);
		}
		
		if(rs.getString(19)==null) {
		m_make="";
		}
		else{
		m_make=rs.getString(19);
		}
		
		if(rs.getString(20)==null) {
		m_un="";
		}
		else{
		m_un=rs.getString(20);
		}
		
		if(rs.getString(27)==null) {
		m_item="";
		}
		else{
		m_item=rs.getString(27);
		}
		
		m_mk=rs.getString(28);
		
		out.println("<table border='0' width='100%' class='table' >");
		out.println("<tr style='{ font:bold; text-align:left;}'><td><br><u>"+m_model+"  "+m_make+"  "+m_un+ "  "+m_item+ " Costing "+nf.format(rs.getDouble(25))+"/-</td></tr>");

		out.println("</table>");
		out.println("<table border='0' width='100%' class='table'>");
		out.println("<tr><td style='{ font:bold; text-align:left;}'><br>Monthly Rental</td><td width='1'>  - </td><td width='10%' style='{ font:bold; text-align:left;}'>"+nf.format(rs.getDouble(15))+"</td><td width='*%' style='{ font:bold; text-align:left;}'> + VAT of  "+nf.format(rs.getDouble(16))+" "+"("+rs.getString(21)+"+0)"+" </td></tr>");
		out.println("<tr><td style='{ font:bold; text-align:left;}'>Period        </td><td width='1'>  - </td><td width='10%' style='{ font:bold; text-align:left;}'>"+rs.getString(18)+" months</td></tr>");
		out.println("<tr><td style='{ font:bold; text-align:left;}'>Initial Payment</td><td width='1'> - </td><td width='10%' style='{ font:bold; text-align:left;}'>"+nf.format(rs.getDouble(13))+"</td><td width='*%' style='{ font:bold; text-align:left;}'> + VAT of  "+nf.format(rs.getDouble(14))+" "+"("+rs.getString(21)+"+0)"+"</td></tr>");
		if(!rs.getString(22).equals("0")){
		out.println("<tr><td style='{ font:bold; text-align:left;}'>Residual Amount</td><td width='1'>  - </td><td width='10%' style='{ font:bold; text-align:left;}'>0.00</td></tr>");

		}
		else if(rs.getString(22).equals("0")){
		out.println("<tr><td style='{ font:bold; text-align:left;}'>Residual Amount</td><td width='1'>  - </td><td width='10%' style='{ font:bold; text-align:left;}'>"+nf.format(rs.getDouble(17))+"</td></tr>");

		}
		
		out.println("<tr><td style='{ font:bold; text-align:left;}'>NIBSM</td><td width='1'>  - </td><td width='10%' style='{ font:bold; text-align:left;}'>"+nf.format(rs.getDouble(22))+"</td></tr>");
		out.println("</table>");
		more=rs.next();
		
		if (!more)
		{
		break;
		}

		}
		out.println("</font></p></blockquote>");			
		if (more)
		{
		m_opt=rs.getString(11);
		}	
			
			
		if (!more)
		{
		break;
		}		
			
		if (more)
		{
		m_qua=rs.getString(1);
		}

	
		}
		

		
		
		}
		
		
		
		
		
		
		
		rs2 = stmt2.executeQuery("SELECT MAIN_CODE,CODE,initcap(DESCRIPTION),ACTIVE_STATUS "+
    "FROM "+m_schema_name+".AF_MK_CONDITIONS_MAIN "+
		"WHERE UPPER(MAIN_CODE)=UPPER('"+m_qua+"')");


		int f=0;
		
		boolean more2=rs2.next();	
		out.println("<blockquote><font size=2><p style='text-align:right'>");
		
		out.println("<table border='0' width='100%' class=table >");

		while(more2)
		{
		if(f==0){
		out.println("<tr><td width='30%' style='{ font:bold; text-align:left;}'><b>Subject to Following Conditions </td></tr>");
}
		out.println("<tr><td width='30%' style='{ font:bold; text-align:left;}'><b>Condition :-</td><td width='20%' > "+rs2.getString(2)+"</td><td width='20%' style='{ font:bold; text-align:left;}' ><b>Description :-</td><td width='20%' > "+rs2.getString(3)+"</td></tr>");

		more2=rs2.next();
		f=f+1;
		if (!more2)
		{
		break;
		}


		}
		out.println("</table>");
		out.println("</font></p></blockquote>");


		if(m_st.equals("0")){

		out.println("<HTML><HEAD>");
		out.println("<SCRIPT language='JavaScript'>");
		out.println("function displaymsg() {");
		out.println("window.close()");
		out.println("}</SCRIPT></HEAD>");
		out.println("<body onload='displaymsg();'></body>");
		out.println("</html>");
		}				
				if(!m_st.equals("0")){

		out.println("<blockquote><font size=2><p style='text-align:right'>");
		
		out.println("<table border='0' width='100%' class=table >");
		
		out.println("<tr ><td width='*%' class='rep-body' style='{text-align:left;}'>The stated monthly rental excludes Insurance, which needs to be placed through our Broker with an insurer of </td>");
		out.println("</tr>");
		out.println("<tr><td width='*%' class='rep-body' style='{text-align:left;}'>your choice. </td></tr>");
		out.println("<tr><td width='*%' class='rep-body' style='{text-align:left;}'><br>This quotation is valid for 14 days from today and will be subject to the approval of our credit committee.</td></tr>");
		out.println("<tr><td width='*%' class='rep-body' style='{text-align:left;}'>Should you need further clarification or a facility under the option shown in this quotation, please do not</td></tr>");
		out.println("<tr><td width='*%' class='rep-body' style='{text-align:left;}'>hesitate to contact "+m_sp_name+" on 5-577577(Direct)</td></tr>");
		out.println("<tr><td width='*%' class='rep-body' style='{text-align:left;}'><br>We look forward to meeting you shortly to complete the necessary documentation.</td></tr>");
		out.println("<tr><td width='*%' class='rep-body' style='{text-align:left;}'><br>Yours faithfully,</td></tr>");
		out.println("<tr><td width='*%' class='rep-body' style='{font:bold; text-align:left;}'>LAKDERANA  INVESTMENTS  LIMITED.</td></tr>");
		out.println("</table>");
		out.println("</font></p></blockquote>");

		
		rs1 = stmt1.executeQuery("SELECT DISTINCT MK_SUPERVISOR,NAME "+
		"FROM "+m_schema_name+".AF_MK_PRO_INQUIRY ,"+m_schema_name+".CO_CO_MAS_USER   "+
		"WHERE USER_ID=MK_SUPERVISOR ");

		out.println("<blockquote><font size=2><p style='text-align:right'>");

		out.println("<table border='0' width='100%' class=table >");
		out.println("<tr><td width='*%' class=txt-body style='{font:12px;text-align:left;}'><br><br><br>.....................................</td></tr>");
		out.println("<tr><td ><select class=\"txt_input\" type=\"text\" name=TXT_SCREEN maxlength=1 style=\"width:150px\">");  

		boolean more1=rs1.next();	
		while(more1)
		{

		if(rs1.getString(1).equals(m_mk)){
		out.println("<option value="+rs1.getString(1)+" selected>"+rs1.getString(2)+"</option>");
		}

		else if(!rs1.getString(1).equals(m_mk)){
		out.println("<option value="+rs1.getString(1)+" >"+rs1.getString(2)+"</option>");
		}

		more1=rs1.next();
		
		if (!more1)
		{
		break;
		}


		}	
		out.println("</select></td></tr>");
		out.println("<tr><td width='*%' class=txt-body style='{font:12px;text-align:left;}'><b>Authorized Signatory</td></tr>");
		out.println("</table>");
		out.println("</font></p></blockquote>");
		out.println("</form>");
		out.println("</body>");
    out.println("</html>");
		
		
		}
		if(!m_st.equals("0")){

		out.println("<HTML><HEAD>");
		out.println("<SCRIPT language='JavaScript'>");
		out.println("function displaymsg() {");
		out.println("}</SCRIPT></HEAD>");
		out.println("<body onload='displaymsg();'></body>");
		out.println("</html>");
		}				
		
		}
//--------------------------------------------------------------------------------------			
			
	else if (m_chksql.trim().equals("m_price")) {
			String m_qutation = req.getParameter("data_val").trim();
			String m_px = req.getParameter("price_no1").trim();
	
		out.println("<html><head><font 10pt arial><title>Pricing Details</title></head>");
		out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");

		out.println("<SCRIPT language='JavaScript'>");
	  out.println("function price(){");
		out.println("m_url='"+m_class_url+"/LAKDL_AF_MK_Price?chksql=main_page&pricing_no="+m_px+"';"); 
		
		out.println("window.open(m_url,'displayWindow3','left=0,top=133,width=900,height=700,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
		out.println("}</SCRIPT>");

		out.println("<body leftmargin='0' topmargin='0' class=body>");
		out.println("<br>");
		out.println("<br>");
		out.println("<div align='center' width='180%' class='txt-body' style='{font: bold;text-align:center;}'>Pricing Details</div>");
		out.println("<form name='form1'>");
		out.println("<table border='1' width='100%' bgcolor='white' style='{color: black; font: bold 10px;}'>");
		out.println("</table>");			
    out.println("<HR width='100%'>");
    out.println("<br>");
		
				
			rs=stmt.executeQuery("SELECT A.QUOTATION_NO,C.INQUIRY_NO,A.PRICING_NO,RATE,A.PERIOD "+
			"FROM "+m_schema_name+".AF_MK_PRO_QUOTATION_DET A,"+m_schema_name+".AF_MK_PRO_PRICING B,LAKDL.AF_MK_PRO_QUOTATION C "+
			"WHERE A.QUOTATION_NO=C.QUOTATION_NO AND A.PRICING_NO=B.PRICING_NO AND A.QUOTATION_NO =UPPER('"+ m_qutation +"') ");
		
		boolean more=rs.next();	
	
		
		while(more)
		{
		m_price=rs.getString(1);
		m_pricing=rs.getString(3);
		
		
		out.println("<table border='1' width='100%' class='table'>");
		out.println("<tr><td width='50%' class='txt-body' style='{text-align:center;}'><b>Pricing No </td><td width='30%' class='txt-body' style='{text-align:center;}'><b>Rate </td><td width='30%' class='txt-body' style='{text-align:center;}'><b>Period </td></tr>");
		out.println("</table>");
	
		while(rs.getString(1).trim().equals(m_price)){
		out.println("<table border='1' width='100%' class='table'>");

		out.println("<tr>");
		out.println("<td width='50%' class='txt-body' style='{text-align:center;}'>"+rs.getString(3)+"</td>");
		out.println("<td width='30%' class='txt-body' style='{text-align:center;}'>"+rs.getString(4)+"</td>");
		out.println("<td width='30%' class='txt-body' style='{text-align:center;}'>"+rs.getString(5)+"</td>");

		out.println("</tr>");
		out.println("</table>");
				
		out.println("<table width='100%' cellpadding='1' border=\"0\">");	
    out.println("<tr><td width='20%' align='center'>");

    out.println("<input type='button' class='but_input' name='Exit'  value=' Pricing ' OnClick=\"price()\";></td></tr>");
    out.println("</table>");		

		more=rs.next();
		if (!more)
		{
		break;
		}
		}

		if (more)
		{
		m_price=rs.getString(1);
		}
		}
		out.println("</form>");
		out.println("</body>");
    out.println("</html>");
	}
	
		
		
		  
//--------------------------------------------------------------------------------------			
						
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

