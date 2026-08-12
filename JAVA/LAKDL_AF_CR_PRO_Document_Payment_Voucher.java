/*
 * ID           :
 * SCREEN NAME  : Document Printing - NIBSM Letter
 * CREATED BY   : Nuwan De Silva
 * DATE/TIME    : 12-02-2006
 * NOTES        :
 */


import java.io.IOException;
import java.sql.Connection; 
import java.sql.ResultSet; 
import java.sql.Statement; 
import java.text.NumberFormat;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LAKDL_AF_CR_PRO_Document_Payment_Voucher extends HttpServlet { 
    
    /*
	ServletOutputStream out = null;
    
    
    Connection conn;
    Statement stmt,stmt2;
    NumberFormat nf;
    
    public ResultSet rs,rs2;
    
    
    public String m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_due_date,m_no_of_due_date,m_finance_no,m_mlease_no,m_print;
    public double m_amount_due;
	*/
    
    //public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
	public void service(HttpServletRequest req, HttpServletResponse res)  throws IOException {
		
		ServletOutputStream out = null;

	    Connection conn= null;
	    Statement stmt= null,stmt2= null;
	    NumberFormat nf= null;
	    
	    ResultSet rs= null,rs2= null;
	    
	    
	    String reqstr= null,m_Letter_date= null,m_c_code= null,m_name= null,m_city_desc= null,m_due_date= null,m_no_of_due_date= null,m_finance_no= null,m_mlease_no= null;
	    double m_amount_due= 0;
        
        try { 
            
            LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
            conn = m_sn_methods.met_user_validate(req); 
            String m_html_client_url=m_sn_methods.html_client_url.trim(); 
            String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
            String m_fschema_name=m_sn_methods.client_name.trim();
            String m_schema_name = m_sn_methods.schema_name.trim();
            //	String m_chksql;
            res.setStatus(HttpServletResponse.SC_OK); 
            res.setContentType("text/html"); 
            out = res.getOutputStream(); 
            
            nf = NumberFormat.getInstance(Locale.US);
            nf.setMinimumFractionDigits(2);
            nf.setMaximumFractionDigits(2);
            
            // out.println("conn"+conn);
            
            
            //Decaring variables
            
            String m_full_name="";
            String m_add1="";
            String m_add2="";
            String m_city_name="";
            String m_title="";
            String m_client_type="",m_nic_no=""; 
            double m_NIBSM=0;
            int m_data_count=0;
            String m_status ="",m_activated_date="";
            
            String m_orient_name="";
            String m_orient_add1="";
            String m_orient_add2="";
            String m_orient_city_name="";
            String m_orient_tel_no="";
            String m_orient_fax_no="";
            String m_orient_vat_rate="";
            
            String m_chassis_no="",m_engine_no="",m_invoice_no="",m_chassis_no_new="",m_engine_no_new="",m_asset_desc="";
            String m_payee_name="",m_word_amt="",m_comment="",m_req_user="";
            
            String m_chksql = req.getParameter("chksql");
            
            if(m_chksql.trim().equals("main_page")){
                
                stmt = conn.createStatement ();
                stmt2 = conn.createStatement ();			
                String m_payment_no = req.getParameter("payment_no");
                String m_print=req.getParameter("print");
                
                String		Sql_company_details=" SELECT "+
                    " NVL(UPPER(COMPANY_NAME),' '), "+
                    " NVL(UPPER(ADDRESS1),' '), "+
                    " NVL(UPPER(ADDRESS2),' '), "+
                    " NVL(UPPER(CITY),' '), "+
                    " NVL(TEL_NO,' '), "+
                    " NVL(FAX_NO,' '),  "+
                    " NVL(VAT_RATE,0), "+
                    " NVL(EMAIL,' ') "+
                    " FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ";
                
                rs=stmt.executeQuery(Sql_company_details);
                
                if(rs.next())
                {
                    m_orient_name=rs.getString(1);
                    m_orient_add1=rs.getString(2);
                    m_orient_add2=rs.getString(3);
                    m_orient_city_name=rs.getString(4);
                    m_orient_tel_no=rs.getString(5);
                    m_orient_fax_no=rs.getString(6);
                    m_orient_vat_rate=rs.getString(7);	
                    
                }
                
                
                
                rs = stmt.executeQuery (" SELECT  "+
                    " PAYMENT_NO, "+
                    " UPPER(PAYEE_NAME) "+
                    " FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT "+
                    " WHERE PAYMENT_NO =UPPER('"+m_payment_no+"')  ");
                
                if(rs.next()){
                    m_payee_name=rs.getString(2);
                }
                
                rs = stmt.executeQuery (" "+
					
					/*
					" SELECT  "+
                    " DISTINCT A.SUS_REF_NO, "+
                    " NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO)),' ') , "+
                    " "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO)), "+
                    " NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME("+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO))),' ') , "+
                    " INT_BAL_SETTLE_AMOUNT, "+
                    " REF_NO, "+
                    " RECEIVER,   "+
                    " NVL("+m_schema_name+".AF_CO_GET_REF_NAME(REF_NO,RECEIVER),' ') PAYEE_NAME,  "+
                    " NVL(decode(A.SUSPENSE_ENTRY_TYPE,'S','Supplier','V','Vendor','E','Seizer','L','Lawyer','A','Advertistment','R','Repossision',"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(SUSPENSE_ENTRY_TYPE)),'-') SUSPENSE_ENTRY_TYPE,    "+
                    //" NVL(C.WHT,0),"+
                    //" NVL(C.NET_AMOUNT,0), "+
                    //" NVL(C.PAY_AMOUNT,0), "+
                    //" NVL(A.TOT_SETTLE_AMOUNT,0) "+
                    "   NVL(B.WHT_AMOUNT,0), "+//10
                    "   NVL(B.SETTELED_AMOUNT,0), "+//11
                    "   NVL(C.COMMENTS,'-'), "+//12//Added By Sandun on 21-08-2009
                    "   "+m_schema_name+".AF_CO_GET_USER_NAME(C.ENT_USER), "+//13 //Added By Sandun on 21-08-2009
					"   NVL(BRAC_VOU_NO,'-'), "+ // Added by Prabash on 27-10-2014
					"   NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO)),'-') "+ // 15 added by udara 16-02-2015
                    " FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT A , "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN B, "+
                    " "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT C "+
                    " WHERE A.SUS_REF_NO = B.SUS_REF_NO  "+
                    " AND C.PAYMENT_NO = B.PAYMENT_NO "+
                    " AND B.PAYMENT_NO = UPPER('"+m_payment_no+"')  "+
                    " ORDER BY REF_NO"+
					*/
					
					" SELECT "+
							" SUS_REF_NO, "+
							" NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO(REF_REF),' ') , "+
							" "+m_schema_name+".AF_CO_GET_CLIENT_CODE(REF_REF),  "+
							" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME("+m_schema_name+".AF_CO_GET_CLIENT_CODE(REF_REF)),' ') , "+
							" INT_BAL_SETTLE_AMOUNT,  "+
							" REF_NO, "+
							" RECEIVER, "+
							" PAYEE_NAME, "+
							" SUSPENSE_ENTRY_TYPE, "+
							" WHT_AMOUNT, "+
							" SETTELED_AMOUNT, "+
							" COMMENTS, "+
							" ENT_USER, "+
							" BRAC_VOU_NO, "+
							" NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO(REF_REF),'-') "+
							
							" FROM ( "+
							
							    " SELECT   "+
							        " "+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO) REF_REF, "+
							        " A.SUS_REF_NO SUS_REF_NO, "+
							        " INT_BAL_SETTLE_AMOUNT INT_BAL_SETTLE_AMOUNT,  "+
							        " REF_NO REF_NO, "+
							        " RECEIVER RECEIVER, "+
							        " NVL("+m_schema_name+".AF_CO_GET_REF_NAME(REF_NO,RECEIVER),' ') PAYEE_NAME, "+
							        " NVL(decode(A.SUSPENSE_ENTRY_TYPE,'S','Supplier','V','Vendor','E','Seizer','L','Lawyer','A','Advertistment','R','Repossision',LAKDL.AF_CO_GET_SUB_CHARG_DESC(SUSPENSE_ENTRY_TYPE)),'-') SUSPENSE_ENTRY_TYPE, "+
							        " NVL(B.WHT_AMOUNT,0) WHT_AMOUNT, "+ 
							        " NVL(B.SETTELED_AMOUNT,0) SETTELED_AMOUNT, "+
							        " NVL(C.COMMENTS,'-') COMMENTS, "+
							        " "+m_schema_name+".AF_CO_GET_USER_NAME(C.ENT_USER) ENT_USER, "+
							        " NVL(BRAC_VOU_NO,'-')  BRAC_VOU_NO "+                  
							                 " FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT A , "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN B, "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT C "+
							                 " WHERE A.SUS_REF_NO = B.SUS_REF_NO  "+
							                 " AND C.PAYMENT_NO = B.PAYMENT_NO "+
							                 " AND B.PAYMENT_NO = UPPER('"+m_payment_no+"')  "+
							                 " ORDER BY REF_NO "+
							
							" ) "+
					
					
					" ");
                
                
                
                
                out.println("<html><head>"); 
                out.println("<title>Discrepancies Letter </title></head>");
                out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
                
                
                out.println("       <style type=\"text/css\" media=\"print\">");
                out.println("           .hide_elements {");
                out.println("               display: none;");
                out.println("           }");
                out.println("       </style>");
                
                out.println("<script>");
                
                out.println("function save_data(){");
                //out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Documents_Status?chksql=save_page&scr_name=AF_CR_PRO_DISCRE_LETTER&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&document_code="+m_document_code+"\";"); 
                out.println(" window.location.href=m_url;"); 
                
                out.println("m_table.innerHTML=\"\" ");
                out.println("m_letter='<tr><td width=\"*%\" align=\"center\" class=\"rep-body1\" ><b>To be typed on company letter head</b></td></tr>';"); 
                out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
                out.println("m_letter+'</table>';");
                out.println("window.print();");
                out.println("}");
                
                
                out.println("function add_button(){");
                if (m_print.trim().equals("FALSE")) {
                    out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
                    out.println("m_letter='<tr><td width=\"*%\" align=\"center\" class=\"rep-body1\" ><b>To be typed on company letter head</b></td></tr>';"); 
                    out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
                    out.println("m_writedata+m_letter+'</table>';");
                }
                out.println("}");
                
                
                out.println("</script>");
                
                
                out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"\">");//add_button()
                out.println("<body bgcolor='white'><br>");
                out.println("<form name='Form1'>");
                
                out.println("<table align='center' width='100%' class='table'>"); 
                out.println("<tr>");  
                out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
                out.println("</tr>"); 
                out.println("                                       <tr class=\"hide_elements\">");
                out.println("                                           <td height=\"10px\" class=\"pdn_txtpos\">");
                out.println("                                               <table class=\"table\" cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
                out.println("                                                   <tr>");
                out.println("                                                       <td><input class=\"mainbut\" type=\"button\" name=\"BUT_PRINT\" id=\"BUT_PRINT\" value=\"Print\" onclick=\"window.print();\" /></td>");
                out.println("                                                   </tr>");
                out.println("                                               </table>");
                out.println("                                           </td>");
                out.println("                                       </tr>");
                out.println("</table>");
                
                out.println("<table align='center' width='100%' class='table'>"); 
                out.println("<tr>");
                out.println("<td widht='100%' align='center'><b>"+m_orient_name+"<td>");
                out.println("</tr>"); 
                out.println("<tr>");
                out.println("<td widht='100%' align='center'><b>PAYMENT REQUISITION<td>");
                out.println("</tr>"); 
                out.println("</table>");
                
                out.println("<br><br>"); 
                out.println("<table align='center' width='100%' class='table'>"); 
                out.println("<tr>");  
                out.println("<td width=\"100%\">To : Accountant</td>");
                out.println("</tr>"); 
                out.println("</table>");
                
                out.println("<br>"); 
                out.println("<table align='center' width='100%' class='table'>"); 
                out.println("<tr>");  
                out.println("<td width=\"20%\">Payment No</td>");
                out.println("<td width=\"*%\">"+m_payment_no+"</td>");
                out.println("</tr>"); 
                out.println("<tr>");  
                out.println("<td width=\"20%\">Payee name</td>");
                out.println("<td width=\"*%\">"+m_payee_name+"</td>");
                out.println("</tr>"); 
                out.println("</table>");
                
                boolean more = rs.next();
                
                out.println("<table class='table' border='0' width='100%' cellspacing='0' >");
                
                if(more){
					//--Addedd by Prabash on 27-10-2014---**
					 out.println("<tr>");  
                	out.println("<td width=\"20%\">Branch Account VC Number</td>");
                	out.println("<td width=\"*%\">"+rs.getString(14)+"</td>");
                	out.println("</tr>"); 
				   //-------------------------------------**
					out.println("<br>"); 	
                    out.println("<tr > "); 
                    out.println("<td width='2%' ><b>No</td>"); 
                    out.println("<td width='11%' ><b>Finance No</td>");
                    out.println("<td width='11%' ><b>Invoice No</td>");//Added by Dineth on 2009-02-10
                    out.println("<td width='11%' ><b>Sus Ref No</td>"); 
                    out.println("<td width='11%' ><b>Type</td>"); 
                    out.println("<td width='15%' ><b>Client Name</td>"); 
					out.println("<td width='15%' ><b>Vehicle No.</td>");  // added by udara 16-02-2015
                    out.println("<td width='12%' align='right'><b>WHT</td>"); 
                    out.println("<td width='12%' align='right'><b>Settled Amount</td>"); 
                    out.println("<td width='12%' align='right'><b>Payment Amount</td>"); 
                    out.println("</tr>"); 
                }
                int j = 1;      					
                double sum=0;
                double wht=0;
                double net_amt=0;
                while(more){
                    
                    if(j>0 && j%2==1){
                        out.println("<tr class=tr_input >");
                    }
                    else{
                        out.println("<tr class=tr_input >");
                    }
                    out.println("<td width='2%' >"+j+"</td>"); 
                    out.println("<td width='11%' >"+rs.getString(2)+"</td>");
                    out.println("<td width='11%' >"+rs.getString(6)+"</td>");//Added by Dineth on 2009-02-10
                    out.println("<td width='11%' >"+rs.getString(1)+"</td>"); 
                    out.println("<td width='11%' >"+rs.getString(9)+"</td>"); 
                    out.println("<td width='15%' >"+rs.getString(4)+"</td>"); 
					out.println("<td width='15%' >"+rs.getString(15)+"</td>"); // added by udara 16-02-2015
                    out.println("<td width='12%' align='right' >"+nf.format(rs.getDouble(10))+"</td>"); 
                    //out.println("<td width='12%' align='right' >"+nf.format(rs.getDouble(11))+"</td>"); 
                    //out.println("<td width='12%' align='right' >"+nf.format(rs.getDouble(12))+"</td>"); 
                    out.println("<td width='12%' align='right' >"+nf.format(rs.getDouble(11)-rs.getDouble(10))+"</td>"); 
                    out.println("<td width='12%' align='right' >"+nf.format(rs.getDouble(11))+"</td>"); 
                    out.println("</tr>"); 		
                    m_comment = rs.getString(12);
                    m_req_user =rs.getString(13); 
                    //sum+=rs.getDouble(11);
                    wht+=rs.getDouble(10);
                    //net_amt+=rs.getDouble(12);
                    sum+=rs.getDouble(11);
                    net_amt+=rs.getDouble(5);
                    
                    more = rs.next();
                    j=j+1;
                }
                out.println("<tr>"); 		
                out.println("<td width='2%' >&nbsp;</td>"); 
                out.println("<td width='11%' >&nbsp;</td>"); 
                out.println("<td width='11%' >&nbsp;</td>"); 
                out.println("<td width='11%' >&nbsp;</td>"); 
                out.println("<td width='11%' >&nbsp;</td>"); 
				out.println("<td width='11%' >&nbsp;</td>");  // added by udara 16-02-2015
                out.println("<td width='15%' ><b>Total</td>");
                out.println("<td width='12%' align='right' ><b>"+nf.format(wht)+"</td>"); 
                out.println("<td width='12%' align='right' ><b>"+nf.format(sum-wht)+"</td>"); 
                out.println("<td width='12%' align='right' ><b>"+nf.format(sum)+"</td>"); 
                out.println("</tr>"); 		
                out.println("</table>");
                
                
                String sum_word="";
                rs = stmt.executeQuery (" SELECT ROUND("+sum+",2) FROM DUAL" );
                if(rs.next()){
                    sum_word=rs.getString(1);
                }
                
                //out.println("sum_word"+sum_word);			
                //m_word_amt=m_sn_methods.numbersToChar(Double.toString(sum));
                m_word_amt=m_sn_methods.numbersToChar(sum_word);
                //m_word_amt=m_word_amt.toUpperCase();
                
                //Comment By Sandun On 21-07-2009
                /*
                out.println("<br><br>"); 		
                out.println("<table align='center' width='100%' class='table'>"); 
                out.println("<tr>");  
                out.println("<td width=\"*%\">--------------------------------</td>");
                out.println("</tr>"); 
                out.println("<tr>");  
                out.println("<td width=\"*%\">(Authorised Signatory)</td>");
                out.println("</tr>"); 
                out.println("</table>");
                */
                
                out.println("<br><br>"); 		
                
                out.println("<table class='table' border='0' width='100%' cellspacing='0' >");
                out.println("<tr>"); 		
                out.println("<td width='100%'>Amount in word -&nbsp;&nbsp;Rupees "+m_word_amt+"<td>");
                out.println("</tr>");
                out.println("</table>");
                
                out.println("<br>"); 		
                
                out.println("<table class='table' border='0' width='100%' cellspacing='0' >");
                out.println("<tr>"); 		
                out.println("<td width='100%'>Discription of payment</td></tr>");
                out.println("<tr><td width='100%'>"+m_comment+"<td>");
                out.println("</tr>");
                out.println("</table>");
                out.println("<br>"); 
                out.println("<table class='table' border='0' width='100%' cellspacing='0' >");
                out.println("<tr>"); 		
                out.println("<td width='100%'>Requested By -&nbsp;&nbsp;  "+m_req_user+" <td>");
                out.println("</tr>");
                out.println("</table>");
                out.println("<br>"); 
                out.println("<table class='table' border='0' width='100%' cellspacing='0' >");
                out.println("<tr>"); 		
                out.println("<td width='12%'>Approved By - <td width='88%'>...................................................................<td></tr>");
                out.println("</tr>");
                out.println("</table>");
                out.println("<br>"); 
                out.println("<table class='table' border='0' width='100%' cellspacing='0' >");
                out.println("<tr>"); 		
                out.println("<td width='20%'>Authorized Signatory.01 - <td width='80%'>.....................................................<td></tr>");
                out.println("</tr>");
                out.println("</table>");
                out.println("<br>"); 
                out.println("<table class='table' border='0' width='100%' cellspacing='0' >");
                out.println("<tr>"); 		
                out.println("<td width='20%'>Authorized Signatory.02 - <td width='80%'>.....................................................<td></tr>");
                out.println("</tr>");
                out.println("</table>");
                
                out.println("<br>"); 
                out.println("<table class='table' border='1' width='100%' cellspacing='0' cellpadding='0' bordercolor='black'>");
                out.println("<tr>"); 		
                out.println("<td width='50%'>");
                out.println("<table class='table' border='0' width='100%' cellspacing='0' >");
                out.println("<tr style='height:30px'>"); 		
                out.println("<td width='2%'>&nbsp;</td><td width='100%' colspan=2><u>Disbursement Details</td>");
                out.println("</tr>");
                out.println("<tr style='height:30px'>"); 		
                out.println("<td width='2%'>&nbsp;</td><td width='25%'>Name</td><td width='75%'> : ............................................................</td>");
                out.println("</tr ><tr style='height:30px'>"); 
                out.println("<td width='2%'>&nbsp;</td><td width='25%'>NIC No</td><td width='75%'> : ............................................................</td>");
                out.println("</tr><tr style='height:30px'>"); 
                out.println("<td width='2%'>&nbsp;</td><td width='25%'>Date</td><td width='75%'> : ............................................................</td>");
                out.println("</tr>"); 		
                out.println("</table>");
                out.println("</td>");
                out.println("<td width='50%'>");
                out.println("<table class='table' border='0' width='100%' cellspacing='0' >");out.println("<tr>"); 		
                out.println("<tr style='height:30px'>"); 		
                out.println("<td width='2%'>&nbsp;</td><td width='98%' colspan=2 ><u>Cheque Details</td>");
                out.println("</tr>");
                out.println("<tr style='height:30px' >"); 		
                out.println("<td width='2%'>&nbsp;</td><td width='25%'>Bank</td><td width='75%'> : ............................................................</td>");
                out.println("</tr><tr style='height:30px'>"); 
                out.println("<td width='2%'>&nbsp;</td><td width='25%'>Cheque No</td><td width='75%'> : ............................................................</td>");
                out.println("</tr><tr style='height:30px'>"); 
                out.println("<td width='2%'>&nbsp;</td><td width='25%'>Date</td><td width='75%'> : ............................................................</td>");
                out.println("</tr>"); 		
                out.println("</table>");
                out.println("</td>");
                out.println("</tr>");
                out.println("</table>");
                
                out.println("</form></body></html>");
            }
            
            out.flush();
        }
        catch (Exception ex) {
            try{out.println("Error:"+ex.toString());}catch(Exception e){}
        }
        finally{
            if(out!=null){try{out.close();  }catch(Exception e){}}
        }
    }
}
