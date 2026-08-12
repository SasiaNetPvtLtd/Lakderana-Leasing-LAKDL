import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.Locale;

class Debtor_Statement_bulk_print implements Printable {
    
    public String m_facility_no;
    public String m_client_no;
    public String m_date_from;
    public String m_date_to;
    public String m_debtor_no;
    NumberFormat nf;
    
    public Debtor_Statement_bulk_print(String s, String s1, String s2, String s3, String s4) {
        nf = NumberFormat.getInstance(Locale.US);
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);
        m_facility_no = s;
        m_client_no = s1;
        m_date_from = s2;
        m_date_to = s3;
        m_debtor_no = s4;
    }
    
    public Debtor_Statement_bulk_print() {}
    
    public int print(Graphics g, PageFormat pageformat, int i) {
        
        Graphics2D graphics2d = (Graphics2D)g;
        graphics2d.translate(pageformat.getImageableX(), pageformat.getImageableY());
        try
        {
            LAKDL_print_methods LAKDL_print_methods = new LAKDL_print_methods();
            String s = LAKDL_print_methods.schema_name.trim();
            Connection connection = LAKDL_print_methods.get_print_connection();
            Statement statement = connection.createStatement();
            Statement statement1 = connection.createStatement();
            Statement statement2 = connection.createStatement();
            Statement statement3 = connection.createStatement();
            graphics2d.setPaint(Color.black);
            graphics2d.setStroke(new BasicStroke(5F));
            FontMetrics fontmetrics = graphics2d.getFontMetrics();
            double d = pageformat.getImageableWidth() / 2D;
            double d1 = 36D;
            Font font = new Font("Tahoma", 0, 8);
            graphics2d.setFont(font);
            String s1 = "";
            ResultSet resultset = statement1.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY HH24:MI:SS') FROM DUAL ");
            if(resultset.next())
                s1 = resultset.getString(1);
            resultset.close();
            ResultSet resultset1 = statement3.executeQuery("SELECT CLIENT_CODE  FROM(  SELECT    A.CLIENT_CODE CLIENT_CODE   FROM " + s + ".FA_OP_PRO_SETTLE_ALLO A," + s + ".FA_OP_PRO_SETTL_RECEIPT B " + " WHERE A.RECEIPT_NO=B.RECEIPT_NO " + " AND " + s + ".FA_GET_INVOICE_DEBTOR(A.INVOICE_NO)='" + m_debtor_no + "' " + " AND TO_DATE(TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('" + m_date_from + "','DD-MM-YYYY') " + " AND TO_DATE(TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('" + m_date_to + "','DD-MM-YYYY') " + " AND A.ALLOCATED_AMOUNT>0 " + " AND A.FACILITY_NO='" + m_facility_no + "' " + " AND A.CLIENT_CODE='" + m_client_no + "' " + " UNION ALL " + " SELECT  " + " B.CLIENT_CODE CLIENT_CODE " + " FROM " + s + ".FA_CR_PRO_INVOICE_DETAIL A," + s + ".FA_CR_PRO_INVOICE B " + " WHERE A.BATCH_NO=B.BATCH_NO " + " AND A.SETTLE_AMOUNT=0 " + " AND TO_DATE(TO_CHAR(B.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('" + m_date_from + "','DD-MM-YYYY') " + " AND TO_DATE(TO_CHAR(B.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('" + m_date_to + "','DD-MM-YYYY') " + " AND B.FACILITY_NO='" + m_facility_no + "' " + " AND B.CLIENT_CODE='" + m_client_no + "' " + " AND A.DEBTOR_CODE='" + m_debtor_no + "' " + " ) ");
            if(resultset1.next())
            {
                int j;
                for(ResultSet resultset2 = statement1.executeQuery(" SELECT  NVL(A.CLIENT_CODE,'-'),  NVL(UPPER(A.FULL_NAME),'-'),  NVL(UPPER(A.REGISTERED_ADDRESS1),'-'),  NVL(UPPER(A.REGISTERED_ADDRESS2),'-'),  UPPER(NVL(" + s + ".AF_CO_GET_CITY_NAME(A.CITY_CODE),'-')), " + " NVL(UPPER(B.CONTACT_PERSON),'-')," + " NVL(UPPER(B.DESIGNATION_PAYMENT),' '), " + " A.CLIENT_TYPE " + " FROM " + s + ".FA_CO_MAS_CLIENT A," + s + ".FA_CR_PRO_CLIENT_DEBTOR B " + " WHERE B.DEBTOR_CODE=A.CLIENT_CODE " + " AND B.FACILITY_NO='" + m_facility_no + "' " + " AND B.CLIENT_CODE='" + m_client_no + "' " + " AND B.DEBTOR_CODE='" + m_debtor_no + "' "); resultset2.next(); graphics2d.drawString("Factoring Division of Lakderana Investments Limited", 42, j))
                {
                    graphics2d.drawString("Co.Reg.No. PB75", 42, 108);
                    graphics2d.drawString(s1, 42, 118);
                    if(resultset2.getString(8).equals("C"))
                        graphics2d.drawString(resultset2.getString(7), 42, 128);
                    graphics2d.drawString(resultset2.getString(2), 42, 138);
                    graphics2d.drawString(resultset2.getString(3), 42, 148);
                    graphics2d.drawString(resultset2.getString(4), 42, 158);
                    graphics2d.drawString(resultset2.getString(5), 42, 168);
                    graphics2d.drawString("DEBTOR CODE:" + resultset2.getString(1), 42, 198);
                    graphics2d.drawString("REPORT PERIOD: " + m_date_from + " - " + m_date_to, 42, 208);
                    graphics2d.drawString("INVOICE AND SETTLEMENT REPORT", 42, 228);
                    graphics2d.drawString("PRINT DATE/TIME " + s1, 42, 238);
                    Font font1 = new Font("Tahoma", 0, 7);
                    graphics2d.setFont(font1);
                    graphics2d.drawString("Dear Sir", 42, 260);
                    graphics2d.drawString("We inform to you that the following invoice amounts were settled against your payments.", 42, 270);
                    graphics2d.drawString("Date", 42, 290);
                    graphics2d.drawString("Supplier Name", 83, 290);
                    graphics2d.drawString("Cheque No", 183, 290);
                    graphics2d.drawString("Branch Code", 253, 290);
                    graphics2d.drawString("Cheque Amount", 313, 290);
                    graphics2d.drawString("Invoice No", 383, 290);
                    graphics2d.drawString("Invoice Amount", 427, 290);
                    graphics2d.drawString("Settle Amount", 483, 290);
                    graphics2d.drawString("Bal Amount", 533, 290);
                    ResultSet resultset3 = statement.executeQuery("SELECT CLIENT_CODE,  SUBSTR(CNAME, 1, 20),  FACILITY_NO,  CHEQUE_NO,  CHQ_AMT,  NVL(BRANCH_NAME,'-'),  INVOICE_NO,  INV_AMOUNT,  ALL_AMT,  BAL_AMT,  TO_CHAR(ALLO_DATE,'DD-MM-YYYY')  FROM(  SELECT    A.CLIENT_CODE CLIENT_CODE,   " + s + ".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE) CNAME, " + " A.FACILITY_NO FACILITY_NO, " + " DECODE(B.SETTLE_MODE,'CHEQUE',B.CHEQUE_NO,B.SETTLE_MODE) CHEQUE_NO, " + " B.REC_AMOUNT CHQ_AMT, " + " B.PAYER_BRANCH_CODE BRANCH_NAME, " + " " + s + ".FA_GET_INVOICE_NO(A.INVOICE_NO) INVOICE_NO, " + " A.INVOICED_AMOUNT INV_AMOUNT,  " + " A.ALLOCATED_AMOUNT ALL_AMT, " + " (" + s + ".FA_CLIENT_PRE_INV_BAL('" + m_client_no + "','" + m_facility_no + "',A.INVOICE_NO,TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'))-A.ALLOCATED_AMOUNT) BAL_AMT,  " + " A.ALLOCATED_DATE ALLO_DATE " + " FROM " + s + ".FA_OP_PRO_SETTLE_ALLO A," + s + ".FA_OP_PRO_SETTL_RECEIPT B " + " WHERE A.RECEIPT_NO=B.RECEIPT_NO " + " AND " + s + ".FA_GET_INVOICE_DEBTOR(A.INVOICE_NO)='" + m_debtor_no + "' " + " AND TO_DATE(TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('" + m_date_from + "','DD-MM-YYYY') " + " AND TO_DATE(TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('" + m_date_to + "','DD-MM-YYYY') " + " AND A.ALLOCATED_AMOUNT>0 " + " AND A.FACILITY_NO='" + m_facility_no + "' " + " AND A.CLIENT_CODE='" + m_client_no + "' " + " UNION ALL " + " SELECT  " + " B.CLIENT_CODE CLIENT_CODE, " + " " + s + ".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE) CNAME, " + " B.FACILITY_NO FACILITY_NO, " + " '-' CHEQUE_NO, " + " 0 CHQ_AMT, " + " '-' BRANCH_NAME, " + " A.INVOICE_NO INVOICE_NO, " + " A.INVOICE_AMOUNT INV_AMOUNT, " + " A.SETTLE_AMOUNT ALL_AMT, " + " A.BALANCE_AMOUNT BAL_AMT, " + " B.INVOICE_BATCH_DATE ALLO_DATE " + " FROM " + s + ".FA_CR_PRO_INVOICE_DETAIL A," + s + ".FA_CR_PRO_INVOICE B " + " WHERE A.BATCH_NO=B.BATCH_NO " + " AND A.SETTLE_AMOUNT=0 " + " AND TO_DATE(TO_CHAR(B.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('" + m_date_from + "','DD-MM-YYYY') " + " AND TO_DATE(TO_CHAR(B.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('" + m_date_to + "','DD-MM-YYYY') " + " AND B.FACILITY_NO='" + m_facility_no + "' " + " AND B.CLIENT_CODE='" + m_client_no + "' " + " AND A.DEBTOR_CODE='" + m_debtor_no + "' " + " ) ORDER BY ALLO_DATE ");
                    j = 300;
                    String s2 = "";
                    boolean flag = false;
                    boolean flag1 = false;
                    while(resultset3.next()) 
                    {
                        graphics2d.drawString(resultset3.getString(11), 42, j);
                        graphics2d.drawString(resultset3.getString(2), 83, j);
                        graphics2d.drawString(resultset3.getString(4), 183, j);
                        graphics2d.drawString(resultset3.getString(6), 253, j);
                        int k = 0;
                        int l = 0;
                        String s3;
                        for(s3 = nf.format(resultset3.getDouble(5)); l < s3.length(); l++)
                            k += fontmetrics.charWidth(s3.charAt(l));
                        
                        graphics2d.drawString(s3, 313, j);
                        graphics2d.drawString(resultset3.getString(7), 383, j);
                        k = 0;
                        l = 0;
                        for(s3 = nf.format(resultset3.getDouble(8)); l < s3.length(); l++)
                            k += fontmetrics.charWidth(s3.charAt(l));
                        
                        graphics2d.drawString(s3, 427, j);
                        k = 0;
                        l = 0;
                        for(s3 = nf.format(resultset3.getDouble(9)); l < s3.length(); l++)
                            k += fontmetrics.charWidth(s3.charAt(l));
                        
                        graphics2d.drawString(s3, 483, j);
                        k = 0;
                        l = 0;
                        for(s3 = nf.format(resultset3.getDouble(10)); l < s3.length(); l++)
                            k += fontmetrics.charWidth(s3.charAt(l));
                        
                        graphics2d.drawString(s3, 533, j);
                        j += 10;
                    }
                    resultset3.close();
                    j += 10;
                    graphics2d.drawString("Thanking you", 42, j);
                    j += 10;
                    graphics2d.drawString("Yours faithfully", 42, j);
                    j += 10;
                }
                
            }
        }
        catch(Exception exception) {
            exception.printStackTrace();
        }
        graphics2d.dispose();
        System.gc();
        return 0;
        
    }
    
}
