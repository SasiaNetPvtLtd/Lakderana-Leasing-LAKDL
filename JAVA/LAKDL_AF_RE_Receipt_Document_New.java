/*    */ import java.io.IOException;
/*    */ import java.sql.CallableStatement;
/*    */ import java.sql.Connection;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.Statement;
/*    */ import java.text.NumberFormat;
/*    */ import java.util.Locale;
/*    */ import javax.servlet.ServletOutputStream;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ 
/*    */ public class LAKDL_AF_RE_Receipt_Document_New extends HttpServlet
/*    */ {

			// commented below by udara 03-02-2017
			/*
			ServletOutputStream out = null;
		   Connection conn;
		   Statement stmt;
		   Statement stmt1;
		   Statement stmt2;
		   Statement stmt3;
		   Statement stmt4;
		   Statement stmt5;
		   CallableStatement callstmt1;
		   NumberFormat nf;
		   public ResultSet rs;
		   public ResultSet rs1;
		   public ResultSet rs2;
		   public ResultSet rs3;
		   public ResultSet rs4;
		   public ResultSet rs5;
		   public ResultSet rs6;
		   public String m_chksql;
		   public String m_html_client_url;
		   public String reqstr;
		   public String m_Letter_date;
		   public String m_c_code;
		   public String m_add1;
		   public String m_add2;
		   public String m_name;
		   public String m_city_desc;
		   public String m_due_date;
		   public String m_receipt_no;
		   public String m_client_no;
		   public String m_no_of_due_date;
		   public String m_finance_no;
		   public String m_print;
		   public String m_inv_type;
		   public String m_inv_no;
		   public String m_vat_reg_no;
		   public String m_vat_reg_date;
		   public String m_value_date;
		   public double m_amount_due;
		   public double m_gross_rent;
		   public String m_LAKDL_vat_no = ""; public String m_email = "";
		   public String m_vat_precentage = "";
		   String m_third_party_name = ""; String m_third_party_add = ""; String m_words_amt = ""; String m_Cheque_no = ""; String m_group_no = ""; String m_amount_str = ""; String userdetails = ""; String m_arreas_str = "";
		   */
 
/*    */   public synchronized void service(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
/*    */     throws IOException
/*    */   {


			   // added by udara 03-02-2017
			   ServletOutputStream out = null;
			   Connection conn = null;
			   Statement stmt = null;
			   Statement stmt1 = null;
			   Statement stmt2 = null;
			   Statement stmt3 = null;
			   Statement stmt4 = null;
			   Statement stmt5 = null;
			   CallableStatement callstmt1 = null;
			   NumberFormat nf = null;
			   ResultSet rs = null;
			   ResultSet rs1 = null;
			   ResultSet rs2 = null;
			   ResultSet rs3 = null;
			   ResultSet rs4 = null;
			   ResultSet rs5 = null;
			   ResultSet rs6 = null;
			   String m_chksql = null;
			   String m_html_client_url = null;
			   String reqstr = null;
			   String m_Letter_date = null;
			   String m_c_code = null;
			   String m_add1 = null;
			   String m_add2 = null;
			   String m_name = null;
			   String m_city_desc = null;
			   String m_due_date = null;
			   String m_receipt_no = null;
			   String m_client_no = null;
			   String m_no_of_due_date = null;
			   String m_finance_no = null;
			   String m_print = null;
			   String m_inv_type = null;
			   String m_inv_no = null;
			   String m_vat_reg_no = null;
			   String m_vat_reg_date = null;
			   String m_value_date = null;
			   double m_amount_due = 0;
			   double m_gross_rent = 0;
			   String m_LAKDL_vat_no = ""; String m_email = "";
			   String m_vat_precentage = "";
			   String m_third_party_name = ""; String m_third_party_add = ""; String m_words_amt = ""; String m_Cheque_no = ""; String m_group_no = ""; String m_amount_str = ""; String userdetails = ""; String m_arreas_str = "";
			   // end by udara 03-02-2017


/*    */     try
/*    */     {

/* 35 */       LAKDL_AF_CO_conn_methods localLAKDL_AF_CO_conn_methods = new LAKDL_AF_CO_conn_methods();
/* 36 */       conn = localLAKDL_AF_CO_conn_methods.met_user_validate(paramHttpServletRequest);
/* 37 */       String str1 = localLAKDL_AF_CO_conn_methods.html_client_url.trim();
/* 38 */       String str2 = localLAKDL_AF_CO_conn_methods.servlet_client_url.trim() + ":" + localLAKDL_AF_CO_conn_methods.client_t3_port.trim();
/* 39 */       String str3 = localLAKDL_AF_CO_conn_methods.client_name.trim();
/* 40 */       String str4 = localLAKDL_AF_CO_conn_methods.schema_name.trim();
/* 41 */       String str5 = localLAKDL_AF_CO_conn_methods.username.trim();
/* 42 */       int i = 0;
/* 43 */       paramHttpServletResponse.setStatus(200);
/* 44 */       paramHttpServletResponse.setContentType("text/html");
/* 45 */       out = paramHttpServletResponse.getOutputStream();
/*    */ 
/* 47 */       nf = NumberFormat.getInstance(Locale.US);
/* 48 */       nf.setMinimumFractionDigits(2);
/* 49 */       nf.setMaximumFractionDigits(2);
/*    */ 
/* 51 */       String str6 = "";
/* 52 */       String str7 = "";
/* 53 */       String str8 = "";
/* 54 */       String str9 = "";
/* 55 */       String str10 = "";
/* 56 */       String str11 = "";
/* 57 */       String str12 = "";
/* 58 */       String str13 = "";
/* 59 */       String str14 = "";
/* 60 */       String str15 = "";
/* 61 */       double d1 = 0.0D;
/* 62 */       String str16 = "";
/* 63 */       String str17 = "";
/* 64 */       double d2 = 0.0D;
/* 65 */       String str18 = "";
/* 66 */       String str19 = ""; String str20 = ""; String str21 = "";
/*    */ 
/* 68 */       m_chksql = paramHttpServletRequest.getParameter("chksql");
/* 69 */       m_receipt_no = paramHttpServletRequest.getParameter("receipt_no");
/* 70 */       m_client_no = paramHttpServletRequest.getParameter("client_no");
/* 71 */       m_print = paramHttpServletRequest.getParameter("print");
/*    */ 
/* 73 */       stmt = conn.createStatement();
/* 74 */       stmt1 = conn.createStatement();
/* 75 */       stmt2 = conn.createStatement();
/* 76 */       stmt3 = conn.createStatement();
/* 77 */       stmt4 = conn.createStatement();
/* 78 */       stmt5 = conn.createStatement();
/*    */ 
/* 81 */       rs = stmt.executeQuery(" SELECT  UPPER(NVL(COMPANY_NAME,' ')),  INITCAP(NVL(ADDRESS1,' ')),  INITCAP(NVL(ADDRESS2,' ')),  INITCAP(NVL(CITY,' ')),  NVL(TEL_NO,' '),  NVL(FAX_NO,' '),   NVL(VAT_RATE,0),  NVL(VAT_REG_NO,' '),  NVL(EMAIL, ' ')  FROM " + str4 + ".AF_CO_MAS_COMPANY_DETAILS ");
/*    */ 
/* 92 */       boolean bool = rs.next();
/*    */ 
/* 94 */       if (bool)
/*    */       {
/* 96 */         str6 = rs.getString(1);
/* 97 */         str7 = rs.getString(2);
/* 98 */         str8 = rs.getString(3);
/* 99 */         str9 = rs.getString(4);
/* 100 */         str10 = rs.getString(5);
/* 101 */         str11 = rs.getString(6);
/* 102 */         m_vat_precentage = rs.getString(7);
/* 103 */         m_LAKDL_vat_no = rs.getString(8);
/* 104 */         m_email = rs.getString(9);
/*    */       }
/*    */ 
/* 107 */       if (m_chksql.trim().equals("idle")) {
/* 108 */         out.println("idle");
/*    */       }
/* 110 */       if (m_chksql.trim().equals("print_rec"))
/*    */       {
/* 112 */         str13 = paramHttpServletRequest.getParameter("m_loc_receipt_no");
/* 113 */         m_Letter_date = paramHttpServletRequest.getParameter("m_Letter_date");
/* 114 */         m_finance_no = paramHttpServletRequest.getParameter("m_finance_no");
/* 115 */         m_third_party_name = paramHttpServletRequest.getParameter("m_third_party_name");
/* 116 */         m_words_amt = paramHttpServletRequest.getParameter("m_words_amt");
/* 117 */         m_Cheque_no = paramHttpServletRequest.getParameter("m_Cheque_no");
/* 118 */         m_group_no = paramHttpServletRequest.getParameter("m_group_no");
/* 119 */         m_amount_str = paramHttpServletRequest.getParameter("m_amount_str");
/* 120 */         m_arreas_str = paramHttpServletRequest.getParameter("m_arreas_str");
/* 121 */         str18 = paramHttpServletRequest.getParameter("m_sysdate");
/* 122 */         userdetails = paramHttpServletRequest.getParameter("userdetails");
/*    */ 
/* 126 */         int j = 0;
/*    */ 
/* 128 */         out.println("Printing status   -  " + j);
/*    */       }
/* 131 */       else if (m_chksql.trim().equals("main_page"))
/*    */       {
/* 133 */         out.println("<html><head>");
/* 134 */         out.println("<title>Receipt Document</title></head>");
/* 135 */         out.println("<link href=\"" + str1 + "/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
/* 136 */         out.println("<script>");
/*    */ 
/* 139 */         out.println("function print_rec(){");
/* 140 */         out.println("m_url = \"" + str2 + "/" + str3 + "AF_RE_Receipt_Document_New?chksql=print_rec&m_loc_receipt_no=" + str13 + "&m_Letter_date=" + m_Letter_date + "&m_finance_no=" + m_finance_no + "&m_third_party_name=" + m_third_party_name + "&m_words_amt=" + m_words_amt + "&m_Cheque_no=" + m_Cheque_no + "&m_group_no=" + m_group_no + "&m_amount_str=" + m_amount_str + "&m_arreas_str=" + m_arreas_str + "&m_sysdate=" + str18 + "&userdetails=" + userdetails + "\";");
/*    */ 
/* 143 */         out.println("popupwin=window.open(m_url,'displayWindow2','left=110,top=110,width=620,height=800,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
/* 144 */         out.println("}");
/*    */ 
/* 146 */         out.println("function save_data(){");
/* 147 */         out.println("m_table.innerHTML=\"\" ");
/* 148 */         out.println("\tm_url = \"" + str2 + "/" + str3 + "AF_RE_Save_Collection_Receipt_Document?chksql=save_page&receipt_no=" + m_receipt_no + "&client_no=" + m_client_no + "&scr_name=AF_RE_SETTELMENT\";");
/* 149 */         out.println(" window.location.href=m_url;");
/*    */ 
/* 151 */         out.println("window.print();");
/*    */ 
/* 153 */         out.println("}");
/* 154 */         out.println("function add_button(){");
/* 155 */         if (m_print.trim().equals("FALSE")) {
/* 156 */           out.println("m_table.innerHTML=\"\" ");
/*    */         }
/*    */         else
/*    */         {
/* 160 */           out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';");
/* 161 */           out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
/* 162 */           out.println("m_writedata+'</table>';");
/*    */         }
/* 164 */         out.println("}");
/* 165 */         out.println("</script>");
/*    */ 
/* 167 */         out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");
/* 168 */         out.println("<body bgcolor='white'>");
/* 169 */         out.println("<form name='Form1'>");
/*    */ 
///* 175 */         rs = stmt.executeQuery("SELECT TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),NVL(A.OTH_COMMENTS,'-') ,NVL(A.CHEQUE_NO,'CASH'),NVL(A.PAYER_BRANCH_CODE,'-') ,NVL(A.THIRD_PARTY_NAME,'-'),NVL(A.THIRD_PARTY_ADDRESS,'-'),A.SUB_REC_NO , C.REG_NO,B.FINANCE_NO,C.APPLICATION_NO FROM " + str4 + ".AF_CO_PRO_SETTL_RECEIPT A, " + str4 + ".AF_CO_PRO_SETTL_REC_APP_BAL B," + str4 + ".AF_CO_PRO_APP_INVOICE_DETAILS C " + " WHERE A.CLIENT_CODE LIKE UPPER('" + m_client_no + "%') AND  A.REC_NO = B.REC_NO  AND  A.REC_NO LIKE UPPER('" + m_receipt_no + "%') AND C.APPLICATION_NO = " + str4 + ".AF_CO_GET_APPLICATION_NO(B.FINANCE_NO) ");
				  boolean isClientRec = false;
				  rs = stmt.executeQuery(" SELECT CASE WHEN SUS_REF_NO IS NOT NULL THEN 'YES' ELSE 'NO' END SUS_REF_STATUS "+
					" FROM " + str4 + ".AF_CO_PRO_SETTL_RECEIPT "+
					" WHERE REC_NO = '" + m_receipt_no + "' ");
				  if(rs.next()){
						if(rs.getString(1)!=null && rs.getString(1).equals("YES") ){
							isClientRec = true;
						}
				  }
				if(isClientRec){
					rs = stmt.executeQuery("SELECT TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),NVL(A.OTH_COMMENTS,'-') ,NVL(A.CHEQUE_NO,'CASH'),NVL(A.PAYER_BRANCH_CODE,'-') ,NVL(A.THIRD_PARTY_NAME,'-'),NVL(A.THIRD_PARTY_ADDRESS,'-'),A.SUB_REC_NO , NVL("+str4+".AF_CO_GET_ALL_REG_NUMBERS_2(B.FINANCE_NO),C.REG_NO),B.FINANCE_NO,C.APPLICATION_NO FROM " + str4 + ".AF_CO_PRO_SETTL_RECEIPT A, " + str4 + ".AF_CO_PRO_SETTL_REC_APP_BAL B," + str4 + ".AF_CO_PRO_APP_INVOICE_DETAILS C " + " WHERE A.CLIENT_CODE LIKE UPPER('" + m_client_no + "%') AND  A.REC_NO = B.REC_NO  AND  A.REC_NO LIKE UPPER('" + m_receipt_no + "%') AND C.APPLICATION_NO = " + str4 + ".AF_CO_GET_APPLICATION_NO(B.FINANCE_NO) ");
				}else{
					//rs = stmt.executeQuery("SELECT TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),NVL(A.OTH_COMMENTS,'-') ,NVL(A.CHEQUE_NO,'CASH'),NVL(A.PAYER_BRANCH_CODE,'-') ,NVL(A.THIRD_PARTY_NAME,'-'),NVL(A.THIRD_PARTY_ADDRESS,'-'),A.SUB_REC_NO , ' ' REG_NUMBERS,  ' ' FINANCE_NO,  ' ' APPLICATION_NO                                          FROM " + str4 + ".AF_CO_PRO_SETTL_RECEIPT A  WHERE A.CLIENT_CODE LIKE UPPER('" + m_client_no + "%') AND A.REC_NO LIKE UPPER('" + m_receipt_no + "%') ");
					rs = stmt.executeQuery("SELECT TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),NVL(A.OTH_COMMENTS,'-') ,NVL(A.CHEQUE_NO,'CASH'),NVL(A.PAYER_BRANCH_CODE,'-') ,NVL(A.THIRD_PARTY_NAME,'-'),NVL(A.THIRD_PARTY_ADDRESS,'-'),A.SUB_REC_NO , ' ' REG_NUMBERS,  NVL(" + str4 + ".AF_CO_GET_CLIENT_NIC(A.CLIENT_CODE),'_') FINANCE_NO,  ' ' APPLICATION_NO                                          FROM " + str4 + ".AF_CO_PRO_SETTL_RECEIPT A  WHERE A.CLIENT_CODE LIKE UPPER('" + m_client_no + "%') AND A.REC_NO LIKE UPPER('" + m_receipt_no + "%') ");
				}	
/* 175 */         

/*    */ 
/* 186 */         bool = rs.next();
/* 187 */         if (bool) {
/* 188 */           m_Letter_date = rs.getString(1);
/* 189 */           str19 = rs.getString(2);
/* 190 */           str21 = rs.getString(3);
/* 191 */           str20 = rs.getString(4);
/* 192 */           m_third_party_name = rs.getString(5);
/* 193 */           m_third_party_add = rs.getString(6);
/* 194 */           str13 = rs.getString(7);
/* 195 */           str14 = rs.getString(8);
/* 196 */           m_finance_no = rs.getString(9);
/* 197 */           str15 = rs.getString(10);
/*    */         }
/*    */ 
/* 200 */         rs1 = stmt1.executeQuery(" SELECT  CLIENT_CODE,  UPPER(FULL_NAME),  NVL(UPPER(ADDRESS1),'ADD1'),  NVL(UPPER(ADDRESS2),'ADD2'),  NVL(UPPER(" + str4 + ".AF_CO_GET_CITY_NAME(CITY_CODE)),'CITY') CITY_NAME ," + " NVL(VAT_REG_NO,'-') VAT_REG_NO " + " FROM " + str4 + ".AF_CO_MAS_CLIENT " + " WHERE CLIENT_CODE='" + m_client_no + "' ");
/*    */ 
/* 211 */         bool = rs1.next();
/* 212 */         if (bool) {
/* 213 */           m_c_code = rs1.getString(1);
/* 214 */           m_name = rs1.getString(2);
/* 215 */           m_add1 = rs1.getString(3);
/* 216 */           m_add2 = rs1.getString(4);
/* 217 */           m_city_desc = rs1.getString(5);
/* 218 */           m_vat_reg_no = rs1.getString(6);
/*    */         }
/* 220 */         String str22 = "";
/*    */ 
/* 222 */         if (m_add1.equals("ADD1")) {
/* 223 */           str22 = "";
/*    */         }
/*    */         else {
/* 226 */           str22 = m_add1;
/*    */         }
/* 228 */         if (!m_add2.equals("ADD2")) {
/* 229 */           str22 = str22 + "," + m_add2;
/*    */         }
/*    */ 
/* 237 */         rs3 = stmt1.executeQuery(" SELECT  NVL(" + str4 + ".AF_CO_GET_ARREAS_2(" + str4 + ".AF_CO_GET_FINANCE_NO('" + str15 + "')),0), " + " A.INSTALLMENT_NO," + " NVL(B.PERIOD,0)," + " NVL(A.NET_RENTAL_AMOUNT,0)" + " FROM " + str4 + ".AF_CO_PRO_APP_INSTALLMENT A ," + str4 + ".AF_CO_PRO_APP_PRICING B " + " WHERE A.APPLICATION_NO = UPPER('" + str15 + "') AND B.PRO_INVOICE_NO = A.PRO_INVOICE_NO ");
/*    */ 
/* 248 */         bool = rs3.next();
/* 249 */         if (bool) {
/* 250 */           d1 = rs3.getDouble(1);
/* 251 */           str16 = rs3.getString(2);
/* 252 */           str17 = rs3.getString(3);
/* 253 */           d2 = rs3.getDouble(4);
/*    */         }
/*    */ 
/* 261 */         int k = 0;
/* 262 */         String str23 = "";
				  int rent_ins =0;
				
				  rs3 = stmt1.executeQuery("   SELECT COUNT(A.REC_NO)  FROM " + str4 + ".AF_CO_PRO_SETTL_RECEIPT A, " + str4 + ".AF_CO_PRO_INVOICE_DETAILS B, " + str4 + ".AF_CO_PRO_INVOICE C " + " WHERE A.REC_NO = '" + m_receipt_no + "' " + " AND A.INSURANCE > 0 AND A.rental_oter_invoice >0 " + " AND  A.REC_NO = B.RECEIPT_NO " + " AND B.INVOICE_NO = C.INVOICE_NO  " + "  ");
				  if(rs3.next()){
						rent_ins = rs3.getInt(1);
				  }
				  if(rent_ins > 0){// if there is rental/insurance balance amount should show
						k=0;
				  }else{
/*    */ 
/* 275 */         		rs3 = stmt1.executeQuery("   SELECT COUNT(A.REC_NO)  FROM " + str4 + ".AF_CO_PRO_SETTL_RECEIPT A, " + str4 + ".AF_CO_PRO_INVOICE_DETAILS B, " + str4 + ".AF_CO_PRO_INVOICE C " + " WHERE A.REC_NO = '" + m_receipt_no + "' " + " AND A.INSURANCE > 0 " + " AND  A.REC_NO = B.RECEIPT_NO " + " AND B.INVOICE_NO = C.INVOICE_NO  " + "  ");
/*    */ 
/* 284 */         		bool = rs3.next();
/*    */ 
/* 286 */         		if (bool) {
/* 287 */           		k = rs3.getInt(1);
/*    */         		}
/*    */ 		  }
					
/* 290 */         if (k > 0) {
/* 291 */           str23 = "  SELECT SUM(C.BALANCE_TO_BE_RECEIVED)  FROM " + str4 + ".AF_CO_PRO_SETTL_RECEIPT A, " + str4 + ".AF_CO_PRO_INVOICE_DETAILS B, " + str4 + ".AF_CO_PRO_INVOICE C " + " WHERE A.REC_NO = '" + m_receipt_no + "' " + " AND A.INSURANCE > 0 " + " AND  A.REC_NO = B.RECEIPT_NO " + " AND B.INVOICE_NO = C.INVOICE_NO  " + " ";
/*    */         }
/*    */         else
/*    */         {
/* 312 */           str23 = "  SELECT  NVL(" + str4 + ".AF_CO_GET_ARREAS_2(" + str4 + ".AF_CO_GET_FINANCE_NO('" + str15 + "')),0) " + " FROM DUAL " + " ";
/*    */         }
/*    */ 
/* 321 */         rs3 = stmt1.executeQuery(str23);
/*    */ 
/* 324 */         if (rs3.next()) {
/* 325 */           d1 = rs3.getDouble(1);
/*    */         }
/*    */ 
/* 336 */         rs5 = stmt1.executeQuery(" SELECT  TO_CHAR(SYSDATE,'DD-Month-YYYY HH:MI:SS AM')  FROM DUAL ");
/*    */ 
/* 343 */         bool = rs5.next();
/* 344 */         if (bool) {
/* 345 */           str18 = rs5.getString(1);
/*    */         }
/*    */ 
/* 350 */         out.println("<table align='center' width='100%' class='table'>");
/* 351 */         out.println("<tr>");
/* 352 */         out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
/* 353 */         out.println("</tr>");
/* 354 */         out.println("</table>");
/*    */ 
/* 357 */         String str24 = "\tSELECT C.FINANCE_NO, NVL(CHEQUE_NO,'CASH'),NVL(PAYER_BRANCH_CODE,'-'),'Rental Charges Invoice No.'||B.INVOICE_NO DESCREPTION,SETTELED_AMOUNT  FROM " + str4 + ".AF_CO_PRO_SETTL_RECEIPT A, " + str4 + ".AF_CO_PRO_INVOICE_DETAILS B, " + str4 + ".AF_CO_PRO_INVOICE C " + " WHERE A.REC_NO = B.RECEIPT_NO AND " + " A.CLIENT_CODE LIKE UPPER('" + m_client_no + "%') AND A.REC_NO LIKE UPPER('" + m_receipt_no + "%') " + " AND B.INVOICE_NO = C.INVOICE_NO " + " UNION ALL " + " SELECT '&nbsp',NVL(CHEQUE_NO,'CASH'),NVL(PAYER_BRANCH_CODE,'-'), DESCREPTION ,CHARGE SETTELED_AMOUNT " + " FROM " + " (SELECT REC_NO,PAYER_BRANCH_CODE,CHEQUE_NO,INSURANCE CHARGE,'Insurance' DESCREPTION " + " FROM " + str4 + ".AF_CO_PRO_SETTL_RECEIPT " + " WHERE REC_NO ='" + m_receipt_no + "' " + " UNION ALL " + " SELECT REC_NO,PAYER_BRANCH_CODE,CHEQUE_NO,LUXURY_TAX CHARGE,'Luxury Tax' DESCREPTION " + " FROM " + str4 + ".AF_CO_PRO_SETTL_RECEIPT " + " WHERE REC_NO ='" + m_receipt_no + "' " + " UNION ALL " + " SELECT REC_NO,PAYER_BRANCH_CODE,CHEQUE_NO,REVANUE_LICENCE CHARGE,'Revanue Licence' DESCREPTION " + " FROM " + str4 + ".AF_CO_PRO_SETTL_RECEIPT " + " WHERE REC_NO ='" + m_receipt_no + "' " + " UNION ALL " + " SELECT REC_NO,PAYER_BRANCH_CODE,CHEQUE_NO,RMV_CHARGES CHARGE,'RMV Charges' DESCREPTION " + " FROM " + str4 + ".AF_CO_PRO_SETTL_RECEIPT " + " WHERE REC_NO ='" + m_receipt_no + "')A " + " WHERE A.REC_NO IN (SELECT REC_NO FROM " + str4 + ".AF_CO_PRO_INVOICE_DETAILS WHERE REC_NO ='" + m_receipt_no + "') ";
/*    */ 
/* 384 */         String str25 = " SELECT  NVL(CHEQUE_NO,'CASH'),NVL(PAYER_BRANCH_CODE,'-'),'Receipt Amount' DESCREPTION,REC_AMOUNT SETTELED_AMOUNT  FROM " + str4 + ".AF_CO_PRO_SETTL_RECEIPT " + " WHERE REC_NO ='" + m_receipt_no + "'  AND REC_AMOUNT = RENTAL_OTER_INVOICE " + " UNION ALL " + " SELECT  NVL(CHEQUE_NO,'CASH'),NVL(PAYER_BRANCH_CODE,'-'),'Rental Charges' DESCREPTION,RENTAL_OTER_INVOICE SETTELED_AMOUNT " + " FROM " + str4 + ".AF_CO_PRO_SETTL_RECEIPT A " + " WHERE A.REC_NO ='" + m_receipt_no + "' AND REC_AMOUNT <> RENTAL_OTER_INVOICE " + " UNION ALL " + " SELECT NVL(CHEQUE_NO,'CASH'),NVL(PAYER_BRANCH_CODE,'-'), DESCREPTION ,CHARGE SETTELED_AMOUNT " + " FROM " + " (SELECT REC_NO,PAYER_BRANCH_CODE,CHEQUE_NO,INSURANCE CHARGE,'Insurance' DESCREPTION " + " FROM " + str4 + ".AF_CO_PRO_SETTL_RECEIPT " + " WHERE REC_NO ='" + m_receipt_no + "' AND REC_AMOUNT <> RENTAL_OTER_INVOICE " + " UNION ALL " + " SELECT REC_NO,PAYER_BRANCH_CODE,CHEQUE_NO,LUXURY_TAX CHARGE,'Luxury Tax' DESCREPTION " + " FROM " + str4 + ".AF_CO_PRO_SETTL_RECEIPT " + " WHERE REC_NO ='" + m_receipt_no + "' AND REC_AMOUNT <> RENTAL_OTER_INVOICE " + " UNION ALL " + " SELECT REC_NO,PAYER_BRANCH_CODE,CHEQUE_NO,REVANUE_LICENCE CHARGE,'Revanue Licence' DESCREPTION " + " FROM " + str4 + ".AF_CO_PRO_SETTL_RECEIPT " + " WHERE REC_NO ='" + m_receipt_no + "' AND REC_AMOUNT <> RENTAL_OTER_INVOICE " + " UNION ALL " + " SELECT REC_NO,PAYER_BRANCH_CODE,CHEQUE_NO,RMV_CHARGES CHARGE,'RMV Charges' DESCREPTION " + " FROM " + str4 + ".AF_CO_PRO_SETTL_RECEIPT " + " WHERE REC_NO ='" + m_receipt_no + "' AND REC_AMOUNT <> RENTAL_OTER_INVOICE ) ";
/*    */ 
/* 424 */         String str26 = " SELECT FINANCE_NO,CHEQUE_NO,/*PAYER_BRANCH_CODE,DESCREPTION, */ NVL(" + str4 + ".AF_CO_GET_BANK_NAME(PAYER_BRANCH_CODE),'')||' - '|| NVL(" + str4 + ".AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE),'-'), " + " DESCREPTION , " + " SETTELED_AMOUNT,ENT_DATE " + " FROM " + " ( SELECT C.FINANCE_NO,NVL(CHEQUE_NO,'CASH') CHEQUE_NO,NVL(PAYER_BRANCH_CODE,'-') PAYER_BRANCH_CODE, " + " NVL(" + str4 + ".AF_CO_GET_SUB_CHARG_DESC(C.INVOICE_TYPE)," + str4 + ".AF_CO_GET_INVOICE_DESCR(C.INVOICE_TYPE))||'  '||B.INVOICE_NO DESCREPTION, " + " SETTELED_AMOUNT,C.ENT_DATE " + " FROM " + str4 + ".AF_CO_PRO_SETTL_RECEIPT A, " + " " + str4 + ".AF_CO_PRO_INVOICE_DETAILS B, " + " " + str4 + ".AF_CO_PRO_INVOICE C " + " WHERE A.REC_NO = B.RECEIPT_NO AND " + " A.CLIENT_CODE LIKE UPPER('" + m_client_no + "%') AND A.REC_NO LIKE UPPER('" + m_receipt_no + "%') " + " AND B.INVOICE_NO = C.INVOICE_NO " + " UNION ALL " + " SELECT D.FINANCE_NO,NVL(CHEQUE_NO,'CASH') CHEQUE_NO,NVL(PAYER_BRANCH_CODE,'-') PAYER_BRANCH_CODE, " + " 'OD Interest' ," + " SUM(SETTELED_AMOUNT),NULL ENT_DATE  " + " FROM " + str4 + ".AF_CO_PRO_SETTL_RECEIPT A,  " + " " + str4 + ".AF_CO_PRO_INVOICE_DETAILS B,  " + " " + str4 + ".AF_CO_PRO_OD_INTEREST_MONTHLY C, " + " " + str4 + ".AF_CO_PRO_INVOICE D  " + " WHERE A.REC_NO = B.RECEIPT_NO AND  " + " A.CLIENT_CODE LIKE UPPER('" + m_client_no + "%') AND " + " A.REC_NO LIKE UPPER('" + m_receipt_no + "%') AND " + " B.INVOICE_NO = C.ODI_REF_NO AND " + " C.INVOICE_NO = D.INVOICE_NO  " + " GROUP BY D.FINANCE_NO,CHEQUE_NO,PAYER_BRANCH_CODE  " + " ) " + " WHERE SETTELED_AMOUNT <>0 " + " ORDER BY ENT_DATE ";
/*    */ 
/* 476 */         String str27 = "Y";
/*    */ 
/* 478 */         rs2 = stmt2.executeQuery(" select REC_AA ,SUM(SETTELED_AMOUNT)+NVL(" + str4 + ".AF_CO_GET_REC_UNALLO_AMOUNT_2('" + m_receipt_no + "'),0)/*,INSTALLMENT*/\t  " + " from (\t " + " SELECT REC_AA,SETTELED_AMOUNT/*,INSTALLMENT*/ " + " FROM  " + " ( " + " SELECT  " + " A.REC_AMOUNT REC_AA,SETTELED_AMOUNT/*, " + " CASE  " + " WHEN (C.INVOICE_TYPE <> 'INSURANCE' AND C.REMARKS <> 'CHARGES - INSURANCE' )  THEN " + str4 + ".AF_CO_GET_INSTALLMENT_NO(C.INVOICE_NO)  " + " END INSTALLMENT  */" + " FROM " + str4 + ".AF_CO_PRO_SETTL_RECEIPT A,  " + " " + str4 + ".AF_CO_PRO_INVOICE_DETAILS B, " + " " + str4 + ".AF_CO_PRO_INVOICE C " + " WHERE A.REC_NO = B.RECEIPT_NO AND " + " A.CLIENT_CODE LIKE UPPER('" + m_client_no + "%') AND A.REC_NO LIKE UPPER('" + m_receipt_no + "%')  " + " AND B.INVOICE_NO = C.INVOICE_NO  " + " UNION ALL " + " SELECT REC_AA,SUM(SETTELED_AMOUNT) SETTELED_AMOUNT/*,INSTALLMENT*/ " + " FROM (" + " SELECT  " + " A.REC_AMOUNT REC_AA,SETTELED_AMOUNT/*, " + " CASE  " + " WHEN (D.INVOICE_TYPE <> 'INSURANCE' AND D.REMARKS <> 'CHARGES - INSURANCE' )  THEN " + str4 + ".AF_CO_GET_INSTALLMENT_NO(D.INVOICE_NO)  " + " END INSTALLMENT */ " + " FROM " + str4 + ".AF_CO_PRO_SETTL_RECEIPT A,   " + " " + str4 + ".AF_CO_PRO_INVOICE_DETAILS B,   " + " " + str4 + ".AF_CO_PRO_OD_INTEREST_MONTHLY C,   " + " " + str4 + ".AF_CO_PRO_INVOICE D   " + " WHERE A.REC_NO = B.RECEIPT_NO AND   " + " A.CLIENT_CODE LIKE UPPER('" + m_client_no + "%') AND  " + " A.REC_NO LIKE UPPER('" + m_receipt_no + "%') AND  " + " B.INVOICE_NO = C.ODI_REF_NO AND  " + " C.INVOICE_NO = D.INVOICE_NO )  " + " GROUP BY REC_AA/*,INSTALLMENT*/ " + " ) " + " WHERE SETTELED_AMOUNT <>0 \t" + " ) group by REC_AA/*,INSTALLMENT*/  ");
/*    */ 
/* 539 */         if ((rs2.next()) && 
/* 540 */           (rs2.getDouble(1) < rs2.getDouble(2))) {
/* 541 */           str27 = "N";
/*    */         }
/*    */ 
/* 566 */         rs2 = stmt2.executeQuery("  SELECT NVL(TO_CHAR(TO_DATE(" + str4 + ".AF_CO_GET_LAST_RENTAL_DATE(" + str4 + ".AF_CO_GET_APPLICATION_NO(A.SUS_REF_NO)),'DD-MM-YYYY'),'DD'),' ')  " + " FROM " + str4 + ".AF_CO_PRO_SETTL_RECEIPT A " + " WHERE A.REC_NO = '" + m_receipt_no + "' " + " ");
/*    */ 
/* 572 */         if (rs2.next()) {
/* 573 */           str16 = rs2.getString(1);
/*    */         }
/*    */ 
/* 580 */         rs2 = stmt2.executeQuery(" SELECT  NVL(REC_NO,'-'),  DECODE(SETTLE_MODE,'CASH','Cash',CHEQUE_NO),  NVL(PAYER_BRANCH_CODE,'-'),  NVL(" + str4 + ".AF_CO_GET_BANK_NAME(PAYER_BRANCH_CODE),'')||' - '|| NVL(" + str4 + ".AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE),'-'), " + " NVL(REC_AMOUNT,0), " + " DECODE(SETTLE_MODE,'CHEQUE','Cheque','CASH','Cash','DIR_DEP','Derect Deposit','STD_ORD','Standing Order'), " + " NVL(PAYER_ACC_NO,'-'), " + " TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'), " + " CLIENT_CODE, " + 
								
								// commented by udara 04-03-2014
								/*
								" CASE  " + 
								" WHEN RENTAL_OTER_INVOICE >0  THEN 'Rental'   " +
								" WHEN INSURANCE >0  THEN 'Insurance'   " +
								" END DESCRIPTION  " +
								*/
								
								// added by udara 04-03-2014
								" CASE  "+
								" WHEN " + str4 + ".AF_CO_IS_INITIAL_RECIEPT_C(CLIENT_CODE) ='Y' AND SUS_REF_NO IS NULL THEN 'Client Receipt' "+
								" WHEN " + str4 + ".AF_CO_IS_INITIAL_RECIEPT(SUS_REF_NO) ='Y'  THEN 'Initial Receipt' "+	
								
								" WHEN RENTAL_OTER_INVOICE >0 AND NVL(CLOSING_FLAG,'N')='Y'  THEN 'Closing'   "+
								" WHEN RENTAL_OTER_INVOICE >0 AND NVL(CLOSING_FLAG,'N')='I'  THEN 'Documentation Charges'   "+ // added by udara 14-07-2015
								" WHEN RENTAL_OTER_INVOICE >0 AND NVL(CLOSING_FLAG,'N')='S'  THEN 'Stamp Duty'   "+ // added by udara 14-07-2015
								" WHEN RENTAL_OTER_INVOICE >0 AND NVL(CLOSING_FLAG,'N')='R'  THEN 'Refinance'   "+ // added by udara 17-10-2016
								
								" WHEN RENTAL_OTER_INVOICE >0 AND INSURANCE >0 THEN 'Rental / Insurance' "+
								" WHEN RENTAL_OTER_INVOICE >0 AND NVL(CLOSING_FLAG,'N')='N'  THEN 'Rental'   "+
								
								" WHEN INSURANCE >0  THEN 'Insurance'   "+
								" END DESCRIPTION,  "+ 
								" NVL(OTH_COMMENTS,' ') " +  // ADDED BY SAJITH MENDIS ON 09-04-2014
								
								" FROM " + str4 + ".AF_CO_PRO_SETTL_RECEIPT " + " WHERE CLIENT_CODE LIKE UPPER('" + m_client_no + "%') AND  REC_NO LIKE UPPER('" + m_receipt_no + "%') ");
/*    */ 
/* 596 */         String str28 = "";
/* 597 */         String str29 = "";
/* 598 */         String str30 = "";
/* 599 */         String str31 = "";
/* 600 */         double d3 = 0.0D;
/* 601 */         String str32 = "";
/* 602 */         String str33 = "***";
				  String remarks = "";
					
					double receipt_amount = 0; // added by udara 29-01-2019
/*    */ 
/* 604 */         bool = rs2.next();
/*    */ 
/* 606 */         if (bool) {
/* 607 */           str28 = rs2.getString(2);
/* 608 */           str29 = rs2.getString(4);
/* 609 */           d3 = rs2.getDouble(5);
/* 610 */           str32 = rs2.getString(5);
/* 611 */           str31 = rs2.getString(6);
/* 612 */           str30 = rs2.getString(2);
/* 613 */           str33 = rs2.getString(10);	//description
					remarks = rs2.getString(11); // ADDED BY SAJITH MENDIS ON 09-04-2014
					
					receipt_amount = rs2.getDouble(5); // added by udara 29-01-2019
					
/*    */         }
/*    */ 
/* 620 */         double d4 = 0.0D;
/*    */ 
/* 622 */         rs2 = stmt2.executeQuery("SELECT NVL(" + str4 + ".AF_CO_GET_REC_UNALLO_AMOUNT_2('" + m_receipt_no + "'),0) FROM DUAL ");
/*    */ 
/* 625 */         bool = rs2.next();
/* 626 */         if (bool) {
/* 627 */           d4 = rs2.getDouble(1);
/*    */         }
/*    */ 
/* 631 */         rs2 = stmt2.executeQuery(str26);
/*    */ 
/* 633 */         bool = rs2.next();
/*    */ 
/* 635 */         String str34 = "A";

					String str35 = "";

/* 636 */         int m = 1;
/* 637 */         double d5 = 0.0D;
/* 638 */         int n = 0;
/*    */ 
/* 642 */         if (str27.equals("N")) {
/* 643 */           out.println("");
/*    */         }
/*    */         else
/*    */         {
/* 648 */           out.println("<table border='0' bordercolor='black' cellspacing='0' width='100%' class='table' height='140px' >");
/* 649 */           out.println("<tr><td width='100%' class='rep-body' valign='top'>");
/* 650 */           out.println("<table border='0' bordercolor='black' cellspacing='0' width='100%' class='table' >");
/*    */ 
/* 652 */           if (bool) {
/* 653 */             while (bool)
/*    */             {
/* 655 */               m += 1;
/* 656 */               d5 += rs2.getDouble(5);
/* 657 */               n += rs2.getInt(5);
/* 658 */               bool = rs2.next();
/*    */             }
/*    */ 
/* 661 */             if (d4 > 0.0D) {
/* 662 */               d5 += d4;
/*    */             }
/*    */ 
/*    */           }
/* 667 */           else if (str34.equals("A"))
/*    */           {
/* 669 */             str35 = "Initial Payment / Document Charges / Insurance / Advance Monthly Instruments / RMV Charges  / Semi luxury / Luxury Tax / Governement Levy / Insurance Claims / Lease Receivable - Settlement / ODI / Sale Price ";
/*    */ 
/* 672 */             m += 1;
/* 673 */             d5 += d3;
/*    */           }
/*    */ 
/* 676 */           //String str35 = "";
/* 677 */           String str36 = "";
/* 678 */           String str37 = "";
/*    */ 
/* 680 */           rs6 = stmt5.executeQuery("SELECT NAME," + str4 + ".AF_CO_GET_LOCATION_DESC(LOCATION_CODE) FROM " + str4 + ".CO_CO_MAS_USER WHERE USER_ID='" + str5 + "' ");
/* 681 */           if (rs6.next()) {
/* 682 */             str35 = rs6.getString(1);
/* 683 */             str36 = rs6.getString(2);
/*    */           }
/*    */ 
/* 686 */           rs6 = stmt5.executeQuery("SELECT SUBSTR('" + str35 + "',0,INSTR('" + str35 + "',' ',1,1)-1) FROM DUAL");
/* 687 */           if (rs6.next()) {
/* 688 */             str37 = rs6.getString(1);
/*    */           }
/*    */ 
/* 696 */           out.println("<table height='30px' border='0' bordercolor='black' cellspacing='0' width='100%' class='table'>");
/* 697 */           out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
/* 698 */           out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
/* 699 */           out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
/* 700 */           out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
/*    */ 
/* 702 */           out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>" + str13 + "<input type=\"hidden\" name=\"loc_rec_no\" value='" + str13 + "'></td></tr>");
/*    */ 
/* 704 */           out.println("<tr  height='30px'><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
/* 705 */           out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
/* 706 */           out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
/* 707 */           out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
/* 708 */           out.println("<td width='20%' align='right' class='rep-body' style='text-align:right' VALIGN='bottom'>" + m_Letter_date + "<input type=\"hidden\" name=\"m_Letter_date\" value='" + m_Letter_date + "'></td></tr>");
/*    */ 
/* 710 */           out.println("<tr  height='30px'><td colspan='2' align='left' class='rep-body' style='text-align:left' VALIGN='bottom'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + str14 + "</td>");
/*    */ 
/* 713 */           out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
/* 714 */           out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
/* 715 */           out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>" + m_finance_no + "<input type=\"hidden\" name=\"m_finance_no\" value='" + m_finance_no + "'></td></tr>");
/*    */ 
/* 717 */           out.println("<tr  height='35px'><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
/* 718 */           out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
/* 719 */           if (!m_third_party_name.equals("-")) {
/* 720 */             out.println("<td colspan='3'  align='left' class='rep-body' style='text-align:left' >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + m_third_party_name + "</td>");
/*    */ 
/* 722 */             out.println("<input type=\"hidden\" name=\"m_third_party_name\" value='" + m_third_party_name + "'>");
/*    */           }
/*    */           else {
/* 725 */             out.println("<td colspan='3' align='left' class='rep-body' style='text-align:left' >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + m_name + "</td>");
/*    */ 
/* 727 */             m_third_party_name = m_name;
/* 728 */             out.println("<input type=\"hidden\" name=\"m_third_party_name\" value='" + m_third_party_name + "'>");
/*    */           }
/*    */ 
/* 735 */           out.println("<tr  height='35px'><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
/* 736 */           out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
/* 737 */           out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
/* 738 */           out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
/* 739 */           out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>&nbsp;</td></tr>");
/*    */ 
/* 742 */           out.println("<tr  height='12px'><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
/*    */
					// added by udara 29-01-2019
					String string_receipt_amount = String.valueOf(receipt_amount);
					if(receipt_amount < 1){
						
						//out.println("receipt_amount  - " + string_receipt_amount);
						
						String[] parts = string_receipt_amount.split("\\.");
						
				        out.println("<td colspan='4'  align='left' class='rep-body' style='text-align:left' VALIGN='bottom' >" + localLAKDL_AF_CO_conn_methods.numbersToChar(parts[1]).toUpperCase() + " CENTS ONLY</td>");
				
				        m_words_amt = localLAKDL_AF_CO_conn_methods.numbersToChar(parts[1]).toUpperCase();
				        out.println("<input type=\"hidden\" name=\"m_words_amt\" value='" + m_words_amt + "'>");
					}
					else{
						out.println("<td colspan='4'  align='left' class='rep-body' style='text-align:left' VALIGN='bottom' >" + localLAKDL_AF_CO_conn_methods.numbersToChar(str32).toUpperCase() + " ONLY</td>");
				
				        m_words_amt = localLAKDL_AF_CO_conn_methods.numbersToChar(str32).toUpperCase();
				        out.println("<input type=\"hidden\" name=\"m_words_amt\" value='" + m_words_amt + "'>");
					}
					// end by udara 29-01-2019

			/*    */ 
/* 753 */           out.println("<tr height='25px'><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
/* 754 */           out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
/* 755 */           out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
/* 756 */           out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
/* 757 */           out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>&nbsp;</td></tr>");
/*    */ 
/* 759 */           out.println("<tr height='20px'><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
/* 760 */           out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
/* 761 */           out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
/* 762 */           out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
/* 763 */           out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>&nbsp;</td></tr>");
/*    */ 
/* 765 */           out.println("<tr height='10px'><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
/* 766 */           out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >" + str28 + "</td>");
/*    */ 
/* 768 */           out.println("<input type=\"hidden\" name=\"m_Cheque_no\" value='" + str28 + "'>");
/*    */ 
/* 771 */           out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
/* 772 */           out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
/* 773 */           out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>&nbsp;</td></tr>");
/*    */ 
/* 775 */           out.println("<tr  height='35px' ><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
/* 776 */           //out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");     // COMMENTED BY SAJITH MENDIS ON 09-04-2014
					out.println("<td  colspan='3' align='left' class='rep-body' style='text-align:left' name ='remarks' >"+remarks+"</td>"); // ADDED BY SAJITH MENDIS ON 09-04-2014
/* 777 */          // out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");     // COMMENTED BY SAJITH MENDIS ON 09-04-2014
/* 778 */          // out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");     // COMMENTED BY SAJITH MENDIS ON 09-04-2014
/* 779 */           out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>&nbsp;</td></tr>");
/*    */ 
/* 781 */           out.println("<tr height='20px' ><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
/* 782 */           out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
/* 783 */           out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
/*    */ 
/* 787 */           String str38 = "-";
/*    */ 			if(str17!=null && str16!=null ){
							out.println("<td colspan='2' align='left' class='rep-body' style='text-align:left' VALIGN='TOP' >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + str33 + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; " + str17 + "/" + str16 + "</td></tr>");							
					}else{
							out.println("<td colspan='2' align='left' class='rep-body' style='text-align:left' VALIGN='TOP' name='description' >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + str33 + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td></tr>");					
					}
					
/* 789 */           
/*    */ 
/* 793 */           str38 = "" + str33 + "  /" + str17 + "/" + str16;
/* 794 */           out.println("<input type=\"hidden\" name=\"m_group_no\" value='" + str38 + "'>");
/*    */ 
/* 801 */           out.println("<tr height='58px'><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
/* 802 */           out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
/* 803 */           out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
/* 804 */           out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
/* 805 */           out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>&nbsp;</td></tr>");
/*    */ 
/* 807 */           out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
/* 808 */           out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
/* 809 */           out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
/* 810 */           out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
/* 811 */           out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>&nbsp;</td></tr>");
/*    */ 
/* 813 */           String str39 = nf.format(d3);
/* 814 */           out.println("<input type=\"hidden\" name=\"m_amount_str\" value='" + str39 + "'>");
/*    */ 
/* 816 */           out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
/* 817 */           out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
/* 818 */           out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >" + nf.format(d3) + "</td>");
/* 819 */           out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					if(str33!= null && str33.equals("Initial Receipt")){
						out.println("<td width='20%' align='right' class='rep-body' style='text-align:right' VALIGN='TOP'></td></tr>");
					}else{
/* 820 */           	out.println("<td width='20%' align='right' class='rep-body' style='text-align:right' VALIGN='TOP'>" + nf.format(d1) + "</td></tr>");
					}
/*    */ 
/* 822 */           String str40 = nf.format(d1);
/* 823 */           out.println("<input type=\"hidden\" name=\"m_arreas_st\" value='" + str40 + "'>");
/*    */ 
/* 826 */           out.println("<tr height='20px'><td width='15%' align='left' class='rep-body' style='text-align:left'>" + str18 + "</td>");
/* 827 */           out.println("<input type=\"hidden\" name=\"m_sysdate\" value='" + str18 + "'>");
/*    */ 
/* 829 */           out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
/* 830 */           out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
/* 831 */           out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >-" + str37 + "-" + str36 + "</td>");
/* 832 */           out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>&nbsp;</td></tr>");
/*    */ 
/* 834 */           out.println("</table>");
/* 835 */           String str41 = str37 + "-" + str36;
/* 836 */           out.println("<input type=\"hidden\" name=\"userdetails\" value='" + str41 + "'>");
/*    */ 
/* 841 */           out.println("");
/*    */ 
/* 844 */           out.println("<BR><BR>");
/*    */ 
/* 849 */           out.println("<br><br><br><br><br><br><br><br><br><br><br><br><br>");
/* 850 */           out.println("<font size=2><p style='text-align:left'>");
/* 851 */           out.println("</font></p>");
/* 852 */           out.println("</font></p>");
/* 853 */           out.println("</form></body></html>");
/*    */         }
/*    */ 
/*    */       }
/*    */ 
/* 860 */       out.flush();
/*    */     } catch (Exception localException17) {
/*    */       try {
/* 863 */         //out.println("Error:" + localException9.toString()); } catch (Exception localException18) {
					out.println("Error:" + localException17.toString()); } catch (Exception localException18) {
/*    */       }
/*    */     } finally {
/* 866 */       if (rs != null) try { rs.close(); } catch (Exception localException19) {
/*    */         } if (stmt != null) try { stmt.close(); } catch (Exception localException20) {
/*    */         } if (rs1 != null) try { rs1.close(); } catch (Exception localException21) {
/*    */         } if (stmt1 != null) try { stmt1.close(); } catch (Exception localException22) {
/*    */         } if (rs2 != null) try { rs2.close(); } catch (Exception localException23) {
/*    */         } if (stmt2 != null) try { stmt2.close(); } catch (Exception localException24) {
/*    */         } if (conn != null) try { conn.close(); } catch (Exception localException25) {
/*    */         } if (out != null) try { out.close();
/*    */         }
/*    */         catch (Exception localException26)
/*    */         {
/*    */         }
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\thamalij\Desktop\
 * Qualified Name:     LAKDL_AF_RE_Receipt_Document_New
 * JD-Core Version:    0.6.0
 */