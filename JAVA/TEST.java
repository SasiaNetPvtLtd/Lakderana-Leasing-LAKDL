import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.Locale;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LAKDL_AF_CR_Termination_calculation_rpt
  extends HttpServlet
{
  Connection conn;
  Statement stmt;
  Statement stmt1;
  NumberFormat nf;
  NumberFormat nf1;
  public ResultSet rs;
  public ResultSet rs1;
  public String m_chksql;
  ServletOutputStream out = null;
  
  public synchronized void service(HttpServletRequest req, HttpServletResponse res)
  {
    try
    {
      LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
      this.conn = con_method.met_user_validate(req);
      String m_html_client_url = con_method.html_client_url;
      
      String m_schema_name = con_method.schema_name;
      String m_servlet_client_url = con_method.servlet_client_url;
      String m_client_name = con_method.client_name;
      String m_client_t3_port = con_method.client_t3_port;
      String m_username = con_method.username;
      String header_name = con_method.header_name;
      String m_class_url = con_method.servlet_client_url.trim() + ":" + con_method.client_t3_port.trim();
      String m_fschema_name = con_method.client_name.trim();
      
      this.out = res.getOutputStream();
      CallableStatement callstmt1 = null;
      



      this.nf = NumberFormat.getInstance(Locale.US);
      

      this.nf.setMinimumFractionDigits(2);
      this.nf.setMaximumFractionDigits(2);
      
      this.stmt = this.conn.createStatement();
      this.stmt1 = this.conn.createStatement();
      
      res.setStatus(200);
      res.setContentType("text/html");
      String m_st = "NEW";
      String m_st_hid = "New";
      String m_app_no = "";
      int m_rental_count = 0;
      double m_tot_agree = 0.0D;
      double m_capitl = 0.0D;
      double m_interest = 0.0D;
      double m_vat = 0.0D;
      String rental_date = "";
      double tot_cash_coll = 0.0D;
      double tot_cash_coll_6months = 0.0D;
      
      double m_down_payment = 0.0D;
      
      this.m_chksql = req.getParameter("chksql");
      if (this.m_chksql.trim().equals("idle"))
      {
        this.out.println("idle");
      }
      else if (this.m_chksql.equals("CALCULATION_REPORT"))
      {
        String m_finance_no = req.getParameter("finance_no");
        String m_closing_rate = req.getParameter("closing_rate");
        String m_date = req.getParameter("termi_date");
        

        String m_odi_net = req.getParameter("odi_net");
        
        double m_odi_net_value = 0.0D;
        if (m_odi_net != null) {
          m_odi_net_value = Double.parseDouble(m_odi_net);
        }
        String company_name = "";
        
        String client_name = "";
        String reg_no = "";
        
        String contract_status = "";
        
        double arrears_amount = 0.0D;
        double ceasing_charges = 0.0D;
        double insurance_charges = 0.0D;
        double visiting_charges = 0.0D;
        double total_arrears = 0.0D;
        double rental_amount = 0.0D;
        double m_repossess_charge = 0.0D;
        
        int no_of_future_rentals = 0;
        
        double closing_rate = 0.0D;
        
        double normal_closing = 0.0D;
        double closing_amount = 0.0D;
        
        double final_closing_amount = 0.0D;
        
        double rebate_amount = 0.0D;
        
        double future_capital = 0.0D;
        double balance_capital = 0.0D;
        double interest_for_capital = 0.0D;
        
        double total_amount = 0.0D;
        
        double other_charges = 0.0D;
        
        double arrears_excess = 0.0D;
        
        String m_date_show = "";
        
        String client_code = "";
        
        int m_security_count = 0;
        try
        {
          this.rs = this.stmt.executeQuery("  SELECT COMPANY_NAME, TO_CHAR(SYSDATE,'DD-MM-YYYY')  \tFROM " + m_schema_name + ".AF_CO_MAS_COMPANY_DETAILS " + " ");
          if (this.rs.next())
          {
            company_name = this.rs.getString(1);
            m_date_show = this.rs.getString(2);
          }
          this.rs = this.stmt.executeQuery("SELECT   A.APPLICATION_NO   ,DECODE(" + m_schema_name + ".AF_CO_GET_APP_STATUS(APPLICATION_NO),'Normal Termination','Normal Termination Pending'," + m_schema_name + ".AF_CO_GET_APP_STATUS(APPLICATION_NO)) " + "FROM   LAKDL.AF_CO_PRO_APPLICATION_DETAILS A , LAKDL.AF_CO_MAS_CLIENT B,LAKDL.AF_CO_MAS_TRANSACTION_TYPE C   " + "WHERE A.CLIENT_CODE = B.CLIENT_CODE   " + "AND   A.TRANSACTION_TYPE=C.TRAN_CODE   " + "AND A.FINANCE_NO = '" + m_finance_no + "' ");
          if (this.rs.next())
          {
            m_app_no = this.rs.getString(1);
            contract_status = this.rs.getString(2);
          }
          this.rs = this.stmt.executeQuery("  SELECT SUM(SECURITY_COUNT) FROM (  SELECT COUNT(*) SECURITY_COUNT FROM AF_MK_APP_SECURITY_VEHICLE WHERE APPLICATION_NO='" + m_app_no + "' " + " UNION ALL SELECT COUNT(*) SECURITY_COUNT FROM AF_MK_APP_SECURITY_LAND WHERE APPLICATION_NO='" + m_app_no + "' " + " UNION ALL SELECT COUNT(*) SECURITY_COUNT FROM AF_MK_APP_SECURITY_FIXED_DEP WHERE APPLICATION_NO='" + m_app_no + "' " + " UNION ALL SELECT COUNT(*) SECURITY_COUNT FROM AF_ASSET_RUN_CONTRACTS WHERE UPPER(APP_NO)=UPPER('" + m_app_no + "') " + " UNION ALL SELECT COUNT(*) SECURITY_COUNT FROM AF_ASSET_RUN_CONTRACTS WHERE UPPER(APP_NO)=UPPER('" + m_app_no + "') " + " UNION ALL SELECT COUNT(*) SECURITY_COUNT FROM AF_ASSET_RUN_CONTRACTS  WHERE ASSIGNED_FINANCE_NO ='" + m_finance_no + "' AND " + m_schema_name + ".AF_CO_GET_FINANCE_NO(APP_NO) IS NOT NULL  " + " \t) " + " ");
          if (this.rs.next()) {
            m_security_count = this.rs.getInt(1);
          }
          this.rs = this.stmt.executeQuery(" SELECT  SUM(A.CAPITAL_AMOUNT),  SUM(A.INTEREST_AMOUNT),  0 ,  COUNT(*)  FROM " + m_schema_name + ".AF_CO_PRO_APP_INSTALLMENT A," + m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS B " + " WHERE A.APPLICATION_NO = '" + m_app_no + "'  " + " AND A.APPLICATION_NO   = B.APPLICATION_NO " + " AND A.PRICING_NO       = B.PRICING_NO " + " AND A.PRO_INVOICE_NO   = B.INVOICE_NO " + " AND B.ACTIVE_STATUS    IN ('T','Y')" + " AND A.INSTALLMENT_NO <> 0 " + "  ");
          if (this.rs.next())
          {
            m_rental_count = this.rs.getInt(4);
            
            m_capitl = this.rs.getDouble(1);
            m_interest = this.rs.getDouble(2);
            m_vat = this.rs.getDouble(3);
            m_tot_agree = m_capitl + m_interest + m_vat;
          }
          this.rs = this.stmt.executeQuery("SELECT TO_CHAR(MAX(A.RENTAL_DATE),'DD') DUE_DATE FROM " + m_schema_name + ".AF_CO_PRO_APP_INSTALLMENT A  " + "WHERE A.APPLICATION_NO = '" + m_app_no + "' ");
          if (this.rs.next()) {
            rental_date = this.rs.getString(1);
          }
          this.rs = this.stmt.executeQuery("SELECT SUM(CAPITAL_AMOUNT) FROM " + m_schema_name + ".AF_CO_PRO_APP_INSTALLMENT A  " + "WHERE A.APPLICATION_NO = '" + m_app_no + "' " + "AND A.INSTALLMENT_NO = 0  " + " ");
          if (this.rs.next()) {
            m_down_payment = this.rs.getDouble(1);
          }
          this.rs = this.stmt.executeQuery("SELECT DISTINCT C.FINANCE_NO, SUM(A.RENTAL_OTER_INVOICE) FROM " + m_schema_name + ".AF_CO_PRO_SETTL_RECEIPT  A , " + m_schema_name + ".AF_CO_PRO_SETTL_REC_APP_BAL B, " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS C  " + "WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  " + "AND NVL(A.RENTAL_OTER_INVOICE,0) > 0 " + "AND A.CLOSING_FLAG NOT IN ('I','S')" + "AND C.FINANCE_NO = '" + m_finance_no + "' " + " AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<= SYSDATE " + "GROUP BY C.FINANCE_NO ");
          if (this.rs.next()) {
            tot_cash_coll = this.rs.getDouble(2);
          }
          this.rs = this.stmt.executeQuery("SELECT DISTINCT C.FINANCE_NO, SUM(A.RENTAL_OTER_INVOICE) FROM " + m_schema_name + ".AF_CO_PRO_SETTL_RECEIPT  A , " + m_schema_name + ".AF_CO_PRO_SETTL_REC_APP_BAL B, " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS C  " + "WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  " + "AND NVL(A.RENTAL_OTER_INVOICE,0) > 0 " + "AND A.CLOSING_FLAG NOT IN ('I','S')" + "AND C.FINANCE_NO = '" + m_finance_no + "' " + "AND A.EFF_VALDATE >= TO_DATE(TO_CHAR(ADD_MONTHS(SYSDATE ,-6),'DD-MM-YYYY'),'DD-MM-YYYY') " + "AND A.EFF_VALDATE <= SYSDATE   " + "GROUP BY C.FINANCE_NO ");
          if (this.rs.next()) {
            tot_cash_coll_6months = this.rs.getDouble(2);
          }
          this.rs = this.stmt.executeQuery("  SELECT  " + m_schema_name + ".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE), " + " NVL((SELECT REG_NO FROM " + m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS WHERE APPLICATION_NO = A.APPLICATION_NO AND REG_NO IS NOT NULL AND ACTIVE_STATUS <> 'C' AND ROWNUM=1 ),'-') REG_NO, " + " " + m_schema_name + ".AF_CO_GET_CONTRACT_BAL(FINANCE_NO,CLIENT_CODE,'" + m_date + "',NULL) DUE_AMOUNT, " + " DECODE(A.PRE_APPLICATION_NO,NULL," + m_schema_name + ".AF_CO_GET_INSTALMENT_AMT(A.APPLICATION_NO)," + m_schema_name + ".AF_CO_GET_INSTALMENT_AMT(A.PRE_APPLICATION_NO)), " + " " + m_schema_name + ".AF_CR_GET_NO_FUTURE_RENTAL(A.APPLICATION_NO), " + " " + m_schema_name + ".AF_CO_CLOSING_RATE(A.APPLICATION_NO), " + " ( SELECT  SUM(NVL(CAPITAL_AMOUNT,0)) " + " FROM    " + m_schema_name + ".AF_CO_PRO_APP_INSTALLMENT " + " WHERE   (PRO_INVOICE_NO,PRICING_NO) IN (SELECT INVOICE_NO,PRICING_NO " + " FROM   " + m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS " + " WHERE  APPLICATION_NO =   A.APPLICATION_NO  " + " AND       ACTIVE_STATUS  =   'Y' " + " ) " + " AND INVOICE_NO IS NULL  " + " AND APPLICATION_NO = A.APPLICATION_NO  " + " AND INSTALLMENT_NO <> 0 " + " ) OUTS_CAPITAL, " + " ( " + " SELECT " + " SUM(BALANCE_TO_BE_RECEIVED) " + " FROM " + m_schema_name + ".AF_CO_PRO_INVOICE A " + " WHERE     ACTIVE_STATUS='Y' " + " AND  A.FINANCE_NO = '" + m_finance_no + "' " + " AND  VALUE_DATE > TO_DATE('" + m_date + "','DD-MM-YYYY') " + " AND FINANCE_NO IN " + " (SELECT " + " FINANCE_NO " + " FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS " + " WHERE  UPPER(APPLICATION_NO)=UPPER(A.APPLICATION_NO) " + " AND APPLICATION_STATUS<>'CANCEL') " + " ) DUE_AMOUNT_INV, " + " ( " + " SELECT  SUM(ODI_BAL_AMOUNT) FROM ( " + " SELECT  SUM(ODI_BAL_AMOUNT)  ODI_BAL_AMOUNT " + " FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS A, " + " " + m_schema_name + ".AF_CO_PRO_INVOICE B,  " + " " + m_schema_name + ".AF_CO_PRO_OD_INTEREST_MONTHLY C " + " WHERE A.FINANCE_NO ='" + m_finance_no + "' AND  " + " A.FINANCE_NO = B.FINANCE_NO AND  " + " B.ACTIVE_STATUS='Y' AND  " + " B.INVOICE_NO = C.INVOICE_NO   " + " ) " + " ) ODI, " + " (\t" + " SELECT  " + " SUM(BALANCE_TO_BE_RECEIVED) " + " FROM " + m_schema_name + ".AF_CO_PRO_INVOICE A " + " WHERE     ACTIVE_STATUS='Y' " + " AND  A.FINANCE_NO =  '" + m_finance_no + "' " + " AND (INVOICE_TYPE = 'INSURANCE' OR REMARKS = 'CHARGES - INSURANCE') " + " ) INSURANCE_CHARGE, " + " (\t" + " SELECT  " + " SUM(BALANCE_TO_BE_RECEIVED) " + " FROM " + m_schema_name + ".AF_CO_PRO_INVOICE A " + " WHERE     ACTIVE_STATUS='Y' " + " AND  A.FINANCE_NO =  '" + m_finance_no + "' " + " AND INVOICE_TYPE = 'VISIT'  " + " ) VISIT_CHARGES, " + " (\t" + " SELECT  " + " SUM(BALANCE_TO_BE_RECEIVED) " + " FROM " + m_schema_name + ".AF_CO_PRO_INVOICE A " + " WHERE     ACTIVE_STATUS='Y' " + " AND  A.FINANCE_NO =  '" + m_finance_no + "' " + " AND INVOICE_TYPE = 'CEASEINGC'  " + " ) CEASEINGC, " + " " + m_schema_name + ".AF_CO_NEW_CON_BAL_ARR('" + m_finance_no + "'," + m_schema_name + ".AF_CO_GET_CLIENT_CODE(AF_CO_GET_APPLICATION_NO('" + m_finance_no + "')),'" + m_date + "','" + m_username + "') EXCESS_AMNT, " + " (SELECT  SUM(TOTAL_AMOUNT) " + " FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS A, " + " " + m_schema_name + ".AF_CO_PRO_INVOICE B " + " WHERE A.FINANCE_NO ='" + m_finance_no + "' AND " + " A.FINANCE_NO = B.FINANCE_NO AND " + " B.VALUE_DATE > TO_DATE('" + m_date + "','DD-MM-YYYY')  AND " + " B.ACTIVE_STATUS='Y' AND " + " B.INVOICE_TYPE = 'INV_GENER') NEXT_DUE, " + " NVL(" + m_schema_name + ".AF_CO_INS_EXCESS_NEW('" + m_finance_no + "',NULL),0) INS_EXCESS, " + " A.CLIENT_CODE  CLIENT_CODE, " + " (SELECT  " + " SUM(BALANCE_TO_BE_RECEIVED) " + " FROM " + m_schema_name + ".AF_CO_PRO_INVOICE A " + " WHERE     ACTIVE_STATUS='Y' " + " AND  A.FINANCE_NO =  '" + m_finance_no + "' " + " AND INVOICE_TYPE  = 'REPOSSESS'  " + ")REPOSSESS " + " FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS A " + " WHERE A.FINANCE_NO = '" + m_finance_no + "' " + " ");
          if (this.rs.next())
          {
            client_code = this.rs.getString("CLIENT_CODE");
            


            client_name = this.rs.getString(1);
            reg_no = this.rs.getString(2);
            arrears_amount = this.rs.getDouble("DUE_AMOUNT") + this.rs.getDouble("DUE_AMOUNT_INV") + this.rs.getDouble("ODI");
            
            rental_amount = this.rs.getDouble(4);
            no_of_future_rentals = this.rs.getInt(5);
            
            future_capital = this.rs.getDouble(7);
            





            arrears_excess = this.rs.getDouble("EXCESS_AMNT") + this.rs.getDouble("ODI") + this.rs.getDouble("NEXT_DUE") - this.rs.getDouble("REPOSSESS") - this.rs.getDouble("CEASEINGC") - this.rs.getDouble("VISIT_CHARGES");
            


            ceasing_charges = this.rs.getDouble("CEASEINGC");
            visiting_charges = this.rs.getDouble("VISIT_CHARGES");
            insurance_charges = this.rs.getDouble("INSURANCE_CHARGE") - this.rs.getDouble("INS_EXCESS");
            m_repossess_charge = this.rs.getDouble("REPOSSESS");
          }
          closing_rate = Double.parseDouble(m_closing_rate);
          




          total_arrears = arrears_excess + m_repossess_charge + ceasing_charges + insurance_charges + visiting_charges;
          


          normal_closing = no_of_future_rentals * rental_amount + total_arrears;
          
          closing_amount = rental_amount * closing_rate + total_arrears;
          
          final_closing_amount = closing_amount;
          
          rebate_amount = normal_closing - closing_amount;
          


          balance_capital = future_capital;
          
          interest_for_capital = future_capital * 0.05D;
          
          total_amount = future_capital + interest_for_capital + total_arrears;
        }
        catch (Exception ee)
        {
          this.out.println(ee.toString());
        }
        this.out.println("<HTML><HEAD><TITLE>Closing Report</TITLE></HEAD>");
        this.out.println("<link REL='STYLESHEET' HREF='" + m_html_client_url + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
        
        this.out.println("<SCRIPT language=\"JavaScript\">");
        
        this.out.println("function print_window(){");
        this.out.println("    m_table.innerHTML=\"\" ");
        this.out.println("    window.print();");
        this.out.println("}");
        
        this.out.println("function add_button(){");
        this.out.println("   m_writedata='<tr><td width=\"*%\" align=\"left\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"print_window()\"></td></tr>';");
        this.out.println("   m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
        this.out.println("   m_writedata+'</table>';");
        this.out.println("}");
        

        this.out.println("function show_transaction_history_new(val,val2){ ");
        this.out.println("m_url='" + m_class_url + "/" + m_fschema_name + "AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val2+'&client_code='+val;");
        this.out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');");
        this.out.println("}");
        
        this.out.println("</script>");
        

        this.out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0' onload='add_button();' >");
        this.out.println("<FORM NAME='Form1' method='post'>");
        
        this.out.println("<table align='center' width='100%' class='table'>");
        this.out.println("<tr>");
        this.out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
        this.out.println("</tr>");
        this.out.println("</table>");
        
        this.out.println("<br>");
        
        this.out.println("<TABLE  WIDTH='90%' >");
        this.out.println("<TR><TD><CENTER><B> " + company_name + " </B></TD></TR>");
        this.out.println("</TABLE>");
        
        this.out.println("<TABLE  WIDTH='90%' >");
        this.out.println("<TR><TD><CENTER><B>Closing Report</B></TD></TR>");
        this.out.println("</TABLE>");
        
        this.out.println("<BR><BR>");
        
        this.out.println("<TABLE  WIDTH='90%' align=center border=0 >");
        

        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='20%' ><B> Date </B></TD>");
        this.out.println("      <TD WIDTH='30%' > " + m_date_show + " </TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        

        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='20%' ><B> Customer Name </B></TD>");
        this.out.println("      <TD WIDTH='30%' > " + client_name + " </TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        

        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='20%' ><B> Contract Status </B></TD>");
        this.out.println("      <TD WIDTH='30%' > " + contract_status + " </TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        

        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><B> Con-Number </B></TD>");
        this.out.println("      <TD WIDTH='20%' style=cursor:hand onClick=\"show_transaction_history_new('" + client_code + "','" + m_finance_no + "')\" ><u> " + m_finance_no + " </u></TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        


        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><B> Security Details </B></TD>");
        if (m_security_count > 0) {
          this.out.println("      <TD WIDTH='20%' style=cursor:hand onClick=\"show_run_con_det('" + m_app_no + "')\" ><u> Yes </u></TD>");
        } else {
          this.out.println("      <TD WIDTH='20%' style=cursor:hand onClick=\"show_run_con_det('" + m_app_no + "')\" ><u> No </u></TD>");
        }
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        



        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><B> Vehicle No </B></TD>");
        this.out.println("      <TD WIDTH='20%' > " + reg_no + " </TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        

        this.out.println("<tr>");
        this.out.println("      <TD WIDTH='20%' ><b> Rental Date </b></TD>");
        this.out.println("      <TD WIDTH='15%' > " + rental_date + " </TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("</tr>");
        
        this.out.println("<tr>");
        this.out.println("      <TD WIDTH='20%' ><b> No of Rental </b></TD>");
        this.out.println("      <TD WIDTH='15%' > " + m_rental_count + " </TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("</tr>");
        
        this.out.println("<tr>");
        this.out.println("      <TD WIDTH='20%' ><b> Capital </b></TD>");
        this.out.println("      <TD WIDTH='15%' > " + this.nf.format(m_capitl) + " </TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("</tr>");
        

        tot_cash_coll -= m_down_payment;
        tot_cash_coll_6months -= m_down_payment;
        
        this.out.println("<tr>");
        this.out.println("      <TD WIDTH='20%' ><b> Cash Collection Without Insuarance </b></TD>");
        this.out.println("      <TD WIDTH='15%' > " + this.nf.format(tot_cash_coll) + " </TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("</tr>");
        
        this.out.println("<tr>");
        this.out.println("      <TD WIDTH='20%' ><b> Last Six Months Rental Payments Without Insuarance </b></TD>");
        this.out.println("      <TD WIDTH='15%' > " + this.nf.format(tot_cash_coll_6months) + " </TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("</tr>");
        
        this.out.println("<tr>");
        this.out.println("      <TD WIDTH='20%' ><b>  </b></TD>");
        this.out.println("      <TD WIDTH='15%' ></TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("</tr>");
        

        this.out.println("</TABLE>");
        



        this.out.println("<BR><BR>");
        
        this.out.println("<TABLE  WIDTH='90%' align=center border=0 >");
        



        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='20%' ><b> Arrears/(Excess) </b></TD>");
        this.out.println("      <TD WIDTH='15%' > &nbsp; </TD>");
        
        this.out.println("      <TD WIDTH='15%' align=right ><b> " + this.nf.format(arrears_excess) + " </b></TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        








        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><b> Ceasing  Charges </b></TD>");
        this.out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='10%' align=right ><b> " + this.nf.format(m_repossess_charge + ceasing_charges) + " </b></TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        

        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><b> Insurance </b></TD>");
        this.out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='10%' align=right ><b> " + this.nf.format(insurance_charges) + " </b></TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><b> Visiting Chargers </b></TD>");
        this.out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='10%' align=right ><b> " + this.nf.format(visiting_charges) + " </b></TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        


        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><b> Total Arrears </b></TD>");
        this.out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
        


        this.out.println("      <TD WIDTH='10%' align=right ><B> " + this.nf.format(arrears_excess + m_repossess_charge + ceasing_charges + insurance_charges + visiting_charges) + " </B></TD>");
        
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        











        this.out.println("   <TR><TD colspan=4 height=10px > &nbsp; </TD></TR>");
        
        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><B> Balance Period </B></TD>");
        this.out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='10%' align=right ><B> " + no_of_future_rentals + " </B></TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><B> Rental </B></TD>");
        this.out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='10%' align=right ><B> " + this.nf.format(rental_amount) + " </B></TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><B> Closing Rate </B></TD>");
        this.out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='10%' align=right ><B> " + this.nf.format(closing_rate) + " </B></TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        
        this.out.println("   <TR><TD colspan=4 height=10px > &nbsp; </TD></TR>");
        

        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><b> Option 1 </b></TD>");
        this.out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='10%' align=right > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        

        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><b> Normal Closing </b></TD>");
        this.out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='10%' align=right color=\"#006600\" ><B> " + this.nf.format(normal_closing) + " </B></TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        





















        this.out.println("   <TR><TD colspan=4 height=10px > &nbsp; </TD></TR>");
        





























        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><B> Future Capital </B></TD>");
        this.out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='10%' align=right ><B> " + this.nf.format(future_capital) + " </B></TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        
        this.out.println("   <TR><TD colspan=4 height=10px > &nbsp; </TD></TR>");
        
        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><B> Balance capital </B></TD>");
        this.out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='10%' align=right ><B> " + this.nf.format(balance_capital) + " </B></TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><B> Interest for capital </B></TD>");
        this.out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='10%' align=right ><B> " + this.nf.format(interest_for_capital) + " </B></TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><B> Arrears /<B></TD>");
        this.out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='10%' align=right ><B> " + this.nf.format(total_arrears) + " </B></TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><B> Total </B></TD>");
        this.out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='10%' align=right color=\"#006600\" ><B> " + this.nf.format(total_amount) + " </B></TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        
        this.out.println("   <TR><TD colspan=4 height=10px > &nbsp; </TD></TR>");
        this.out.println("   <TR><TD colspan=4 height=10px > &nbsp; </TD></TR>");
        
        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='10%' > ............................ </TD>");
        this.out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='10%' > ............................ </TD>");
        this.out.println("      <TD WIDTH='10%'  > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='10%' > ............................ </TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='10%' > Prepared By </TD>");
        this.out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='10%' > Authorized Signature 1 </TD>");
        this.out.println("      <TD WIDTH='10%'  > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='10%' > Authorized Signature 2 </TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        this.out.println("</TABLE>");
        

        this.out.println("</form>");
        this.out.println("<SCRIPT language1.2='JavaScript' src='" + m_html_client_url + "/leasing_drill_down.js'></SCRIPT>");
        this.out.println("</BODY></HTML>");
      }
      else if (this.m_chksql.equals("CALCULATION_REPORT2"))
      {
        String m_finance_no = req.getParameter("finance_no");
        String m_closing_rate = req.getParameter("closing_rate");
        String m_date = req.getParameter("termi_date");
        

        String m_odi_net = req.getParameter("odi_net");
        
        double m_odi_net_value = 0.0D;
        if (m_odi_net != null) {
          m_odi_net_value = Double.parseDouble(m_odi_net);
        }
        String company_name = "";
        
        String client_name = "";
        String reg_no = "";
        
        String contract_status = "";
        
        double arrears_amount = 0.0D;
        double ceasing_charges = 0.0D;
        double insurance_charges = 0.0D;
        double visiting_charges = 0.0D;
        double total_arrears = 0.0D;
        double rental_amount = 0.0D;
        double m_repossess_charge = 0.0D;
        
        int no_of_future_rentals = 0;
        
        double closing_rate = 0.0D;
        
        double normal_closing = 0.0D;
        double closing_amount = 0.0D;
        
        double final_closing_amount = 0.0D;
        
        double rebate_amount = 0.0D;
        
        double future_capital = 0.0D;
        double balance_capital = 0.0D;
        double interest_for_capital = 0.0D;
        
        double total_amount = 0.0D;
        
        double other_charges = 0.0D;
        
        double arrears_excess = 0.0D;
        
        String m_date_show = "";
        
        String client_code = "";
        
        int m_security_count = 0;
        try
        {
          this.rs = this.stmt.executeQuery("  SELECT COMPANY_NAME, TO_CHAR(SYSDATE,'DD-MM-YYYY')  \tFROM " + m_schema_name + ".AF_CO_MAS_COMPANY_DETAILS " + " ");
          if (this.rs.next())
          {
            company_name = this.rs.getString(1);
            m_date_show = this.rs.getString(2);
          }
          this.rs = this.stmt.executeQuery("SELECT  A.APPLICATION_NO   ,DECODE(" + m_schema_name + ".AF_CO_GET_APP_STATUS(APPLICATION_NO),'Normal Termination','Normal Termination Pending'," + m_schema_name + ".AF_CO_GET_APP_STATUS(APPLICATION_NO)) " + "FROM   LAKDL.AF_CO_PRO_APPLICATION_DETAILS A , LAKDL.AF_CO_MAS_CLIENT B,LAKDL.AF_CO_MAS_TRANSACTION_TYPE C   " + "WHERE A.CLIENT_CODE = B.CLIENT_CODE   " + "AND   A.TRANSACTION_TYPE=C.TRAN_CODE   " + "AND A.FINANCE_NO = '" + m_finance_no + "' ");
          if (this.rs.next())
          {
            m_app_no = this.rs.getString(1);
            contract_status = this.rs.getString(2);
          }
          this.rs = this.stmt.executeQuery("  SELECT SUM(SECURITY_COUNT) FROM (  SELECT COUNT(*) SECURITY_COUNT FROM AF_MK_APP_SECURITY_VEHICLE WHERE APPLICATION_NO='" + m_app_no + "' " + " UNION ALL SELECT COUNT(*) SECURITY_COUNT FROM AF_MK_APP_SECURITY_LAND WHERE APPLICATION_NO='" + m_app_no + "' " + " UNION ALL SELECT COUNT(*) SECURITY_COUNT FROM AF_MK_APP_SECURITY_FIXED_DEP WHERE APPLICATION_NO='" + m_app_no + "' " + " UNION ALL SELECT COUNT(*) SECURITY_COUNT FROM AF_ASSET_RUN_CONTRACTS WHERE UPPER(APP_NO)=UPPER('" + m_app_no + "') " + " UNION ALL SELECT COUNT(*) SECURITY_COUNT FROM AF_ASSET_RUN_CONTRACTS WHERE UPPER(APP_NO)=UPPER('" + m_app_no + "') " + " UNION ALL SELECT COUNT(*) SECURITY_COUNT FROM AF_ASSET_RUN_CONTRACTS  WHERE ASSIGNED_FINANCE_NO ='" + m_finance_no + "' AND " + m_schema_name + ".AF_CO_GET_FINANCE_NO(APP_NO) IS NOT NULL  " + " \t) " + " ");
          if (this.rs.next()) {
            m_security_count = this.rs.getInt(1);
          }
          this.rs = this.stmt.executeQuery(" SELECT  SUM(A.CAPITAL_AMOUNT),  SUM(A.INTEREST_AMOUNT),  0 ,  COUNT(*)  FROM " + m_schema_name + ".AF_CO_PRO_APP_INSTALLMENT A," + m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS B " + " WHERE A.APPLICATION_NO = '" + m_app_no + "'  " + " AND A.APPLICATION_NO   = B.APPLICATION_NO " + " AND A.PRICING_NO       = B.PRICING_NO " + " AND A.PRO_INVOICE_NO   = B.INVOICE_NO " + " AND B.ACTIVE_STATUS    IN ('T','Y') " + " AND A.INSTALLMENT_NO <> 0 " + " ");
          if (this.rs.next())
          {
            m_rental_count = this.rs.getInt(4);
            
            m_capitl = this.rs.getDouble(1);
            m_interest = this.rs.getDouble(2);
            m_vat = this.rs.getDouble(3);
            m_tot_agree = m_capitl + m_interest + m_vat;
          }
          this.rs = this.stmt.executeQuery("SELECT TO_CHAR(MAX(A.RENTAL_DATE),'DD') DUE_DATE FROM LAKDL.AF_CO_PRO_APP_INSTALLMENT A  WHERE A.APPLICATION_NO = '" + m_app_no + "' ");
          if (this.rs.next()) {
            rental_date = this.rs.getString(1);
          }
          this.rs = this.stmt.executeQuery("SELECT SUM(CAPITAL_AMOUNT) FROM " + m_schema_name + ".AF_CO_PRO_APP_INSTALLMENT A  " + "WHERE A.APPLICATION_NO = '" + m_app_no + "' " + "AND A.INSTALLMENT_NO = 0  " + " ");
          if (this.rs.next()) {
            m_down_payment = this.rs.getDouble(1);
          }
          this.rs = this.stmt.executeQuery("SELECT DISTINCT C.FINANCE_NO, SUM(A.RENTAL_OTER_INVOICE) FROM " + m_schema_name + ".AF_CO_PRO_SETTL_RECEIPT  A , " + m_schema_name + ".AF_CO_PRO_SETTL_REC_APP_BAL B, " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS C  " + "WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  " + "AND NVL(A.RENTAL_OTER_INVOICE,0) > 0 " + "AND A.CLOSING_FLAG NOT IN ('I','S')" + "AND C.FINANCE_NO = '" + m_finance_no + "' " + " AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<= SYSDATE " + "GROUP BY C.FINANCE_NO ");
          if (this.rs.next()) {
            tot_cash_coll = this.rs.getDouble(2);
          }
          this.rs = this.stmt.executeQuery("SELECT DISTINCT C.FINANCE_NO, SUM(A.RENTAL_OTER_INVOICE) FROM " + m_schema_name + ".AF_CO_PRO_SETTL_RECEIPT  A , " + m_schema_name + ".AF_CO_PRO_SETTL_REC_APP_BAL B, " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS C  " + "WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  " + "AND NVL(A.RENTAL_OTER_INVOICE,0) > 0 " + "AND A.CLOSING_FLAG NOT IN ('I','S')" + "AND C.FINANCE_NO = '" + m_finance_no + "' " + "AND A.EFF_VALDATE >= TO_DATE(TO_CHAR(ADD_MONTHS(SYSDATE ,-6),'DD-MM-YYYY'),'DD-MM-YYYY') " + "AND A.EFF_VALDATE <= SYSDATE   " + "GROUP BY C.FINANCE_NO ");
          if (this.rs.next()) {
            tot_cash_coll_6months = this.rs.getDouble(2);
          }
          this.rs = this.stmt.executeQuery("  SELECT  " + m_schema_name + ".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE), " + " NVL((SELECT REG_NO FROM " + m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS WHERE APPLICATION_NO = A.APPLICATION_NO AND REG_NO IS NOT NULL AND ACTIVE_STATUS <> 'C' AND ROWNUM=1 ),'-') REG_NO, " + " " + m_schema_name + ".AF_CO_GET_CONTRACT_BAL(FINANCE_NO,CLIENT_CODE,'" + m_date + "',NULL) DUE_AMOUNT, " + " DECODE(A.PRE_APPLICATION_NO,NULL," + m_schema_name + ".AF_CO_GET_INSTALMENT_AMT(A.APPLICATION_NO)," + m_schema_name + ".AF_CO_GET_INSTALMENT_AMT(A.PRE_APPLICATION_NO)), " + " " + m_schema_name + ".AF_CR_GET_NO_FUTURE_RENTAL(A.APPLICATION_NO), " + " " + m_schema_name + ".AF_CO_CLOSING_RATE(A.APPLICATION_NO), " + " ( SELECT  SUM(NVL(CAPITAL_AMOUNT,0)) " + " FROM    " + m_schema_name + ".AF_CO_PRO_APP_INSTALLMENT " + " WHERE   (PRO_INVOICE_NO,PRICING_NO) IN (SELECT INVOICE_NO,PRICING_NO " + " FROM   " + m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS " + " WHERE  APPLICATION_NO =   A.APPLICATION_NO  " + " AND       ACTIVE_STATUS  =   'Y' " + " ) " + " AND INVOICE_NO IS NULL  " + " AND APPLICATION_NO = A.APPLICATION_NO  " + " AND INSTALLMENT_NO <> 0 " + " ) OUTS_CAPITAL, " + " ( " + " SELECT " + " SUM(BALANCE_TO_BE_RECEIVED) " + " FROM " + m_schema_name + ".AF_CO_PRO_INVOICE A " + " WHERE     ACTIVE_STATUS='Y' " + " AND  A.FINANCE_NO = '" + m_finance_no + "' " + " AND  VALUE_DATE > TO_DATE('" + m_date + "','DD-MM-YYYY') " + " AND FINANCE_NO IN " + " (SELECT " + " FINANCE_NO " + " FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS " + " WHERE  UPPER(APPLICATION_NO)=UPPER(A.APPLICATION_NO) " + " AND APPLICATION_STATUS<>'CANCEL') " + " ) DUE_AMOUNT_INV, " + " ( " + " SELECT  SUM(ODI_BAL_AMOUNT) FROM ( " + " SELECT  SUM(ODI_BAL_AMOUNT)  ODI_BAL_AMOUNT " + " FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS A, " + " " + m_schema_name + ".AF_CO_PRO_INVOICE B,  " + " " + m_schema_name + ".AF_CO_PRO_OD_INTEREST_MONTHLY C " + " WHERE A.FINANCE_NO ='" + m_finance_no + "' AND  " + " A.FINANCE_NO = B.FINANCE_NO AND  " + " B.ACTIVE_STATUS='Y' AND  " + " B.INVOICE_NO = C.INVOICE_NO   " + " ) " + " ) ODI, " + " (\t" + " SELECT  " + " SUM(BALANCE_TO_BE_RECEIVED) " + " FROM " + m_schema_name + ".AF_CO_PRO_INVOICE A " + " WHERE     ACTIVE_STATUS='Y' " + " AND  A.FINANCE_NO =  '" + m_finance_no + "' " + " AND (INVOICE_TYPE = 'INSURANCE' OR REMARKS = 'CHARGES - INSURANCE') " + " ) INSURANCE_CHARGE, " + " (\t" + " SELECT  " + " SUM(BALANCE_TO_BE_RECEIVED) " + " FROM " + m_schema_name + ".AF_CO_PRO_INVOICE A " + " WHERE     ACTIVE_STATUS='Y' " + " AND  A.FINANCE_NO =  '" + m_finance_no + "' " + " AND INVOICE_TYPE = 'VISIT'  " + " ) VISIT_CHARGES, " + " (\t" + " SELECT  " + " SUM(BALANCE_TO_BE_RECEIVED) " + " FROM " + m_schema_name + ".AF_CO_PRO_INVOICE A " + " WHERE     ACTIVE_STATUS='Y' " + " AND  A.FINANCE_NO =  '" + m_finance_no + "' " + " AND INVOICE_TYPE = 'CEASEINGC'  " + " ) CEASEINGC, " + " " + m_schema_name + ".AF_CO_NEW_CON_BAL_ARR('" + m_finance_no + "'," + m_schema_name + ".AF_CO_GET_CLIENT_CODE(AF_CO_GET_APPLICATION_NO('" + m_finance_no + "')),'" + m_date + "','" + m_username + "') EXCESS_AMNT, " + " (SELECT  SUM(TOTAL_AMOUNT) " + " FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS A, " + " " + m_schema_name + ".AF_CO_PRO_INVOICE B " + " WHERE A.FINANCE_NO ='" + m_finance_no + "' AND " + " A.FINANCE_NO = B.FINANCE_NO AND " + " B.VALUE_DATE > TO_DATE('" + m_date + "','DD-MM-YYYY')  AND " + " B.ACTIVE_STATUS='Y' AND " + " B.INVOICE_TYPE = 'INV_GENER') NEXT_DUE, " + " NVL(" + m_schema_name + ".AF_CO_INS_EXCESS_NEW('" + m_finance_no + "',NULL),0) INS_EXCESS, " + " A.CLIENT_CODE  CLIENT_CODE, " + " (SELECT  " + " SUM(BALANCE_TO_BE_RECEIVED) " + " FROM " + m_schema_name + ".AF_CO_PRO_INVOICE A " + " WHERE     ACTIVE_STATUS='Y' " + " AND  A.FINANCE_NO =  '" + m_finance_no + "' " + " AND INVOICE_TYPE  = 'N1'  " + ")REPOSSESS " + " FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS A " + " WHERE A.FINANCE_NO = '" + m_finance_no + "' " + " ");
          if (this.rs.next())
          {
            client_code = this.rs.getString("CLIENT_CODE");
            
            client_name = this.rs.getString(1);
            reg_no = this.rs.getString(2);
            arrears_amount = this.rs.getDouble("DUE_AMOUNT") + this.rs.getDouble("DUE_AMOUNT_INV") + this.rs.getDouble("ODI");
            
            rental_amount = this.rs.getDouble(4);
            no_of_future_rentals = this.rs.getInt(5);
            
            future_capital = this.rs.getDouble(7);
            





            arrears_excess = this.rs.getDouble("EXCESS_AMNT") + this.rs.getDouble("ODI") + this.rs.getDouble("NEXT_DUE") - this.rs.getDouble("REPOSSESS") - this.rs.getDouble("CEASEINGC") - this.rs.getDouble("VISIT_CHARGES");
            


            ceasing_charges = this.rs.getDouble("CEASEINGC");
            visiting_charges = this.rs.getDouble("VISIT_CHARGES");
            insurance_charges = this.rs.getDouble("INSURANCE_CHARGE") - this.rs.getDouble("INS_EXCESS");
            m_repossess_charge = this.rs.getDouble("REPOSSESS");
          }
          closing_rate = Double.parseDouble(m_closing_rate);
          




          total_arrears = arrears_excess + m_repossess_charge + ceasing_charges + insurance_charges + visiting_charges;
          


          normal_closing = no_of_future_rentals * rental_amount + total_arrears;
          
          closing_amount = rental_amount * closing_rate + total_arrears;
          
          final_closing_amount = closing_amount;
          
          rebate_amount = normal_closing - closing_amount;
          


          balance_capital = future_capital;
          
          interest_for_capital = future_capital * 0.05D;
          
          total_amount = future_capital + interest_for_capital + total_arrears;
        }
        catch (Exception ee)
        {
          this.out.println(ee.toString());
        }
        this.out.println("<HTML><HEAD><TITLE>Closing Report</TITLE></HEAD>");
        this.out.println("<link REL='STYLESHEET' HREF='" + m_html_client_url + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
        
        this.out.println("<SCRIPT language=\"JavaScript\">");
        
        this.out.println("function print_window(){");
        this.out.println("    m_table.innerHTML=\"\" ");
        this.out.println("    window.print();");
        this.out.println("}");
        
        this.out.println("function add_button(){");
        this.out.println("   m_writedata='<tr><td width=\"*%\" align=\"left\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"print_window()\"></td></tr>';");
        this.out.println("   m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
        this.out.println("   m_writedata+'</table>';");
        this.out.println("}");
        


        this.out.println("function show_transaction_history_new(val,val2){ ");
        this.out.println("m_url='" + m_class_url + "/" + m_fschema_name + "AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val2+'&client_code='+val;");
        this.out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');");
        this.out.println("}");
        


        this.out.println("</script>");
        

        this.out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0' onload='add_button();' >");
        this.out.println("<FORM NAME='Form1' method='post'>");
        
        this.out.println("<table align='center' width='100%' class='table'>");
        this.out.println("<tr>");
        this.out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
        this.out.println("</tr>");
        this.out.println("</table>");
        
        this.out.println("<br>");
        
        this.out.println("<TABLE  WIDTH='90%' >");
        this.out.println("<TR><TD><CENTER><B> " + company_name + " </B></TD></TR>");
        this.out.println("</TABLE>");
        
        this.out.println("<TABLE  WIDTH='90%' >");
        this.out.println("<TR><TD><CENTER><B>Closing Report</B></TD></TR>");
        this.out.println("</TABLE>");
        
        this.out.println("<BR><BR>");
        
        this.out.println("<TABLE  WIDTH='90%' align=center border=0 >");
        

        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='20%' ><B> Date </B></TD>");
        this.out.println("      <TD WIDTH='30%' > " + m_date_show + " </TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        

        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='20%' ><B> Customer Name </B></TD>");
        this.out.println("      <TD WIDTH='30%' > " + client_name + " </TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        

        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='20%' ><B> Contract Status </B></TD>");
        this.out.println("      <TD WIDTH='30%' > " + contract_status + " </TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        

        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><B> Con-Number </B></TD>");
        this.out.println("      <TD WIDTH='20%' style=cursor:hand onClick=\"show_transaction_history_new('" + client_code + "','" + m_finance_no + "')\" ><u> " + m_finance_no + " </u></TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        


        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><B> Security Details </B></TD>");
        if (m_security_count > 0) {
          this.out.println("      <TD WIDTH='20%' style=cursor:hand onClick=\"show_run_con_det('" + m_app_no + "')\" ><u> Yes </u></TD>");
        } else {
          this.out.println("      <TD WIDTH='20%' style=cursor:hand onClick=\"show_run_con_det('" + m_app_no + "')\" ><u> No </u></TD>");
        }
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        




        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><B> Vehicle No </B></TD>");
        this.out.println("      <TD WIDTH='20%' > " + reg_no + " </TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        
        this.out.println("<tr>");
        this.out.println("      <TD WIDTH='20%' ><b> Rental Date </b></TD>");
        this.out.println("      <TD WIDTH='15%' > " + rental_date + " </TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("</tr>");
        
        this.out.println("<tr>");
        this.out.println("      <TD WIDTH='20%' ><b> No of Rental </b></TD>");
        this.out.println("      <TD WIDTH='15%' > " + m_rental_count + " </TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("</tr>");
        
        this.out.println("<tr>");
        this.out.println("      <TD WIDTH='20%' ><b> Capital </b></TD>");
        this.out.println("      <TD WIDTH='15%' > " + this.nf.format(m_capitl) + " </TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("</tr>");
        
        tot_cash_coll -= m_down_payment;
        tot_cash_coll_6months -= m_down_payment;
        
        this.out.println("<tr>");
        this.out.println("      <TD WIDTH='20%' ><b> Cash Collection Without Insuarance </b></TD>");
        this.out.println("      <TD WIDTH='15%' > " + this.nf.format(tot_cash_coll) + " </TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("</tr>");
        
        this.out.println("<tr>");
        this.out.println("      <TD WIDTH='20%' ><b> Last Six Months Rental Payments Without Insuarance </b></TD>");
        this.out.println("      <TD WIDTH='15%' > " + this.nf.format(tot_cash_coll_6months) + " </TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("</tr>");
        
        this.out.println("<tr>");
        this.out.println("      <TD WIDTH='20%' ><b>  </b></TD>");
        this.out.println("      <TD WIDTH='15%' ></TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("</tr>");
        

        this.out.println("</TABLE>");
        
        this.out.println("<BR><BR>");
        
        this.out.println("<TABLE  WIDTH='90%' align=center border=0 >");
        

        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='20%' ><b> Arrears/(Excess) </b></TD>");
        this.out.println("      <TD WIDTH='15%' > &nbsp; </TD>");
        
        this.out.println("      <TD WIDTH='15%' align=right ><b> " + this.nf.format(arrears_excess) + " </b></TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        







        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><b> Ceasing  Charges </b></TD>");
        this.out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='10%' align=right ><b> " + this.nf.format(m_repossess_charge + ceasing_charges) + " </b></TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        
        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><b> Insurance </b></TD>");
        this.out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='10%' align=right ><b> " + this.nf.format(insurance_charges) + " </b></TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><b> Visiting Chargers </b></TD>");
        this.out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='10%' align=right ><b> " + this.nf.format(visiting_charges) + " </b></TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        


        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><b> Total Arrears </b></TD>");
        this.out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
        


        this.out.println("      <TD WIDTH='10%' align=right ><B> " + this.nf.format(arrears_excess + m_repossess_charge + ceasing_charges + insurance_charges + visiting_charges) + " </B></TD>");
        

        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        











        this.out.println("   <TR><TD colspan=4 height=10px > &nbsp; </TD></TR>");
        
        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><B> Balance Period </B></TD>");
        this.out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='10%' align=right ><B> " + no_of_future_rentals + " </B></TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><B> Rental </B></TD>");
        this.out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='10%' align=right ><B> " + this.nf.format(rental_amount) + " </B></TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><B> Closing Rate </B></TD>");
        this.out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='10%' align=right ><B> " + this.nf.format(closing_rate) + " </B></TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        
        this.out.println("   <TR><TD colspan=4 height=10px > &nbsp; </TD></TR>");
        

        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><b> Option 1 </b></TD>");
        this.out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='10%' align=right > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        

        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><b> Normal Closing </b></TD>");
        this.out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='10%' align=right color=\"#006600\" ><B> " + this.nf.format(normal_closing) + " </B></TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><B> Closing </B></TD>");
        this.out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='10%' align=right ><B> " + this.nf.format(closing_amount) + " </B></TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><b> Rebate </b></TD>");
        this.out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
        if (rebate_amount < 0.0D) {
          this.out.println("      <TD WIDTH='10%' align=right ><B> (" + this.nf.format(rebate_amount * -1.0D) + ") </B></TD>");
        } else {
          this.out.println("      <TD WIDTH='10%' align=right ><B> " + this.nf.format(rebate_amount) + " </B></TD>");
        }
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        
        this.out.println("   <TR><TD colspan=4 height=10px > &nbsp; </TD></TR>");
        

        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><b> Option 2 </b></TD>");
        this.out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='10%' align=right > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        
        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><B> Final Closing Amount </B></TD>");
        this.out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='10%' align=right color=\"#006600\" ><B> " + this.nf.format(final_closing_amount) + " </B></TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        
        this.out.println("   <TR><TD colspan=4 height=10px > &nbsp; </TD></TR>");
        


        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><b> Option 3 </b></TD>");
        this.out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='10%' align=right > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        
        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><B> Future Capital </B></TD>");
        this.out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='10%' align=right ><B> " + this.nf.format(future_capital) + " </B></TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        
        this.out.println("   <TR><TD colspan=4 height=10px > &nbsp; </TD></TR>");
        
        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><B> Balance capital </B></TD>");
        this.out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='10%' align=right ><B> " + this.nf.format(balance_capital) + " </B></TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><B> Interest for capital </B></TD>");
        this.out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='10%' align=right ><B> " + this.nf.format(interest_for_capital) + " </B></TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><B> Arrears /<B></TD>");
        this.out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='10%' align=right ><B> " + this.nf.format(total_arrears) + " </B></TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='15%' ><B> Total </B></TD>");
        this.out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='10%' align=right color=\"#006600\" ><B> " + this.nf.format(total_amount) + " </B></TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        
        this.out.println("   <TR><TD colspan=4 height=10px > &nbsp; </TD></TR>");
        this.out.println("   <TR><TD colspan=4 height=10px > &nbsp; </TD></TR>");
        
        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='10%' > ............................ </TD>");
        this.out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='10%' > ............................ </TD>");
        this.out.println("      <TD WIDTH='10%'  > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='10%' > ............................ </TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        this.out.println("   <TR>");
        this.out.println("      <TD WIDTH='10%' > Prepared By </TD>");
        this.out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='10%' > Authorized Signature 1 </TD>");
        this.out.println("      <TD WIDTH='10%'  > &nbsp; </TD>");
        this.out.println("      <TD WIDTH='10%' > Authorized Signature 2 </TD>");
        this.out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
        this.out.println("   </TR>");
        this.out.println("</TABLE>");
        

        this.out.println("</form>");
        this.out.println("<SCRIPT language1.2='JavaScript' src='" + m_html_client_url + "/leasing_drill_down.js'></SCRIPT>");
        this.out.println("</BODY></HTML>");
      }
      return;
    }
    catch (Exception e)
    {
      try
      {
        this.out.println(e.toString());
      }
      catch (Exception e1) {}
    }
    finally
    {
      if (this.rs != null) {
        try
        {
          this.rs.close();
        }
        catch (Exception e) {}
      }
      if (this.stmt != null) {
        try
        {
          this.stmt.close();
        }
        catch (Exception e) {}
      }
      if (this.conn != null) {
        try
        {
          this.conn.close();
        }
        catch (Exception e) {}
      }
      if (this.out != null) {
        try
        {
          this.out.close();
        }
        catch (Exception e) {}
      }
    }
  }
}
