//Created by Yohan Gunarathna on 24-10-2006 at  11.40 A.M.
//Application Status Report

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_CR_PRO_Other_Payments_Account_Select extends HttpServlet {
    
    Connection conn;
    Statement stmt;
    java.text.NumberFormat nf,nf1,nf2;
    public ResultSet rs;
    public String m_chksql;
    ServletOutputStream out = null;
    
    public synchronized void service(HttpServletRequest req, HttpServletResponse res)
    {
        
        try {
            
            //************************************************************	
            LAKDL_AF_MK_CO_methods CO_methods = new LAKDL_AF_MK_CO_methods();
            
            LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
            conn = con_method.met_user_validate(req); 
            String m_html_client_url = con_method.html_client_url;
            String header_name=con_method.header_name.trim();
            String m_schema_name = con_method.schema_name;
            String m_servlet_client_url=con_method.servlet_client_url;
            String m_client_name=con_method.client_name;
            String m_client_t3_port=con_method.client_t3_port;
            String m_username 						= "AA";//m_sn_methods.username;
            out = res.getOutputStream();
            CallableStatement callstmt1 =null;
            int m_count_payment_no=0;
            
            
            //************************************************************
            
            nf = java.text.NumberFormat.getInstance(Locale.US);   
            nf.setMinimumFractionDigits(0);
            
            nf1 = java.text.NumberFormat.getInstance(Locale.US);   
            nf1.setMinimumFractionDigits(4);
            
            nf2 = java.text.NumberFormat.getInstance(Locale.US);   
            nf2.setMaximumFractionDigits(2);
            nf2.setMinimumFractionDigits(2);
            
            res.setStatus(HttpServletResponse.SC_OK);
            res.setContentType("text/html");
            
            m_chksql = req.getParameter("chksql");
            stmt = conn.createStatement ();
            if (m_chksql.trim().equals("idle")) {
                out.println("idle");
            }
            
            
            
            else if(m_chksql.trim().equals("Payment_breakup_details")){
                
                String  m_payment_no = req.getParameter("payment_no").trim();
                
                out.println("<HTML><HEAD><TITLE>Payment Break Up Details </TITLE></HEAD>");
                out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
                out.println("<SCRIPT language=\"JavaScript\">");
                
                
                
                out.println("</SCRIPT>");
                out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
                out.println("<FORM NAME='Form1' method='post'>"); 							
                out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
                out.println("</TABLE>");
                out.println("<BR>");	
                
                out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
                out.println("<TR><TD align='Center' ><B>PAYMENT BREAKUP DETAILS </B></TD></TR>");
                out.println("</TABLE>");
                
                out.println("<table class=table border='0' width='100%' >");
                out.println("<tr class=pdn_txtpos2 align='center'>");
                out.println("<td  width='12%' >Sus Ref No</td>"); //1
                out.println("<td  width='12%' >Contract No</td>"); //1
                out.println("<td  width='10%' >Effective Date</td>"); //7
                out.println("<td  width='20%' >Remarks</td>"); //2
                out.println("<td  width='10%' >Type</td>"); //2
                out.println("<td  width='10%' >WHT</td>"); //
                out.println("<td  width='10%' >Amount</td>"); //
                out.println("<td  width='10%' >Payment Amount</td>"); //4
                
                out.println("</tr>");
                
                int j = 0;   
                
                rs = stmt.executeQuery (" SELECT "+
                    " A.PAYMENT_NO, "+ //1
                    " A.SUS_REF_NO, "+ //2
                    " TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY'), "+ //3
                    " A.SETTELED_AMOUNT, "+  //4
                    " NVL(A.REMARKS,'-'), "+ //5
                    " A.ENT_USER, "+ //6
                    " A.ENT_DATE, "+ //7
                    " A.CLIENT_CODE, "+ //8
                    //" ENTRY_TYPE, "+ 
                    " NVL(DECODE(A.ENTRY_TYPE,'E','Seizer','L','Lawyer','A','Advertistment','V','Vendor','S','Supplier',"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(A.ENTRY_TYPE) ),'-'), "+ //9
                    " A.TAX_INV_NO, "+ //10
                    " A.TAX_INV_DATE, "+ //11
                    " "+m_schema_name+".AF_CO_GET_FIN_NO(B.REF_NO), "+ //12 //----Added By Sandun on 17-11-2008
                    " NVL(C.WHT,0),"+//13 ------Sandun on 24-12-2008
                    " NVL(C.NET_AMOUNT,0) "+//14 -------Sandun on 24-12-2008
                    " FROM "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN A, "+
                    "      "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B, "+
                    " "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT C "+
                    " WHERE A.SUS_REF_NO = B.SUS_REF_NO "+
                    " AND C.PAYMENT_NO   = A.PAYMENT_NO "+					
                    " AND A.PAYMENT_NO   = UPPER('"+m_payment_no+"') ");
                
                double sum=0;
                double wht=0;
                double amt=0;
                
                while(rs.next()){
                    if(j>0 && j%2==1){
                        out.println("<tr bgcolor='#8fb382' >");
                    }
                    else{
                        out.println("<tr class=tr_input >");
                    }
                    
                    
                    out.println("<td width='12%' align='center' ><u>"+rs.getString(2) +"</u></td>");
                    out.println("<td width='10%' align='center' >"+rs.getString(12)+"</td>");
                    out.println("<td width='10%' align='center' >"+rs.getString(3) +"</td>");
                    out.println("<td width='10%' align='center' >"+rs.getString(5) +"</td>");
                    out.println("<td width='10%' align='left'   >"+rs.getString(9) +"</td>");
                    out.println("<td width='10%' align='right'  >"+nf2.format(rs.getDouble(13))+"</td>");
                    out.println("<td width='10%' align='right'  >"+nf2.format(rs.getDouble(14))+"</td>");
                    out.println("<td width='10%' align='right'  >"+nf2.format(rs.getDouble(4))+"</td>");
                    out.println("</tr>");
                    sum+=rs.getDouble(4);
                    wht+=rs.getDouble(13);
                    amt+=rs.getDouble(14);
                    j=j+1;
                }
                
                out.println("<tr bgcolor='#8fb382' >");
                out.println("<td width='12%' align='center' >&nbsp;</td>");
                out.println("<td width='10%' align='center' >&nbsp;</td>");
                out.println("<td width='10%' align='center' >&nbsp;</td>");
                out.println("<td width='10%' align='right'  >&nbsp;</td>");
                out.println("<td width='10%' align='center' ><b>Total</td>");
                out.println("<td width='10%' align='right'   ><b>"+nf2.format(wht)+"</td>");
                out.println("<td width='10%' align='right'   ><b>"+nf2.format(amt)+"</td>");
                out.println("<td width='10%' align='right'   ><b>"+nf2.format(sum)+"</td>");
                out.println("</tr>");
                
                
                out.println("</table>");
                
                
                out.println("</form>"); 
                out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
                out.println("</BODY></HTML>");
                
                
            }
            else if(m_chksql.trim().equals("payment_details")){
                
                /*  String m_sort_column   = "PRIORITY";	
                    String m_order_by_type = "ASC";
                    
                    if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
                m_sort_column = req.getParameter("sort_column");
                m_order_by_type = req.getParameter("order_by_type");
                    }
            */
                String  m_payee_name = req.getParameter("payee_name").trim();
                
                out.println("<table class=table border='0' width='100%' >");
                
                out.println("<tr class=tr_input>");
                //out.println("<td colspan=8 align=right><input type=button name=Close value=Close class=mainbut onclick=close_window(); ></td>");
                out.println("<td colspan=10 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
                out.println("</tr>");
                
                
                out.println("<tr class=pdn_txtpos2 align='center'>");
                out.println("<td  width='12%' >Payment No</td>"); //1
                out.println("<td  width='5%' >Type</td>"); //2
                out.println("<td  width='5%' >Entry Type</td>"); //3
                out.println("<td  width='10%' >WHT</td>"); //
                out.println("<td  width='10%' >Amount</td>"); //Added By Sandun on 24-12-2008
                out.println("<td  width='10%' >Payment Amount</td>"); //4
                out.println("<td  width='25%' >Account Number</td>"); //5
                out.println("<td  width='15%' >Branch Name</td>"); //6
                //out.println("<td  width='15%' >Branch Name</td>"); //6
                out.println("<td  width='10%' >Effective Date</td>"); //7
                out.println("<td  width='10%' >Payee Name</td>"); //8
                //out.println("<td  width='10%' >Approve</td>"); //9
                out.println("<td  width='5%'  >Select</td>"); //10
                out.println("<td  width='5%'  >Details</td>"); //10
                out.println("</tr>");
                
                int j = 0;   
                
                
                rs = stmt.executeQuery (" SELECT "+
                    " PAYMENT_NO, "+
                    " DECODE(SETTLE_MODE,'CHQ','Cheque','Cash'), "+
                    //" ENTRY_TYPE, "+
                    " DECODE(ENTRY_TYPE,'E','Seizer','L','Lawyer','A','Advertistment','V','Vendor','S','Supplier',"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(ENTRY_TYPE) ), "+
                    " PAY_AMOUNT, "+
                    " LIC_BRANCH_CODE, "+
                    " LIC_ACC_NO, "+
                    " TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'), "+
                    " NVL(PAYEE_NAME,'-'),  "+
                    " NVL(WHT,0), "+
                    " NVL(NET_AMOUNT,0) "+
                    " FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT "+
                    " WHERE  PROCESS_STATUS='ENTER' AND  ENTRY_TYPE <> 'V'  AND UPPER(PAYEE_NAME) LIKE UPPER('"+m_payee_name+"%')  " +
                    " ORDER BY PAYMENT_NO ASC ");
                while(rs.next()){
                    if(j>0 && j%2==1){
                        out.println("<tr class=tr_input1 >");
                    }
                    else{
                        out.println("<tr class=tr_input >");
                    }
                    
                    
                    out.println("<td width='12%' align='center' style= cursor:hand;cursor-color:blue onclick=show_payment_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1) +"</u></td>");
                    out.println("<td width='5%' align='center' >"+rs.getString(2) +"</td>");
                    out.println("<td width='5%' align='center' >"+rs.getString(3) +"</td>");
                    out.println("<td width='10%' align='right'  >"+nf2.format(rs.getDouble(9))+"</td>");//Sandun on 24-12-2008
                    out.println("<td width='10%' align='right'  >"+nf2.format(rs.getDouble(10))+"</td>");//Sandun on 24-12-2008
                    out.println("<td width='10%' align='right'  >"+nf2.format(rs.getDouble(4))+"</td>");
                    
                    out.println("<td width='20%' ><input class='txt_input' type='text' name=TXT_LIC_ACC_NO"+j+" maxlength='19' size='19' onblur=\"help_licence_account_no("+j+")\">"); 
                    out.println("<input class='but_input' type='button' name=BUT_TXT_LIC_ACC_NO"+j+" value=\"Help\" onClick=\"help_licence_account_no("+j+")\"></td>"); 
                    //out.println("<td width='15%' ><input class='txt_input' type='text' name=TXT_LIC_BRANCH_CODE"+j+" maxlength='19' size='19' disabled>"); 
                    out.println("<td width='15%' ><input class='txt_input' type='text' name=TXT_LIC_BRANCH_NAME"+j+" maxlength='19' size='19' disabled>"); 
                    out.println("<td width='10%' align='left'   >"+rs.getString(7) +"</td>");
                    out.println("<td width='10%' align='left'   >"+rs.getString(8) +"</td>");
                    out.println("<TD WIDTH='5%' align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_REQUIRED"+j+" VALUE=\"\" onclick=\"change_val_req("+j+")\"  ></td>");			
                    out.println("<TD WIDTH='5%' align=\"center\"><input class='but_input' type='button' name=but_details"+j+" value=\"Details\" onClick=\"Payment_breakup('"+rs.getString(1)+"')\"></td>"); 
                    out.println("<input type=hidden name=HID_TXT_PAYMENT_NO"+j+" value="+rs.getString(1)+" >");
                    out.println("<input type=hidden name=TXT_LIC_BRANCH_CODE"+j+"  >");
                    out.println("</tr>");
                    j=j+1;
                }
                
                out.println("<input type=hidden name=hid_no_rec_count value="+j+">");
                
                out.println("<tr class=tr_input>");
                // out.println("<td colspan=8 align=right><input type=button name=Close value=Close class=mainbut onclick=close_window(); ></td>");
                out.println("<td align=right colspan=10><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
                out.println("</tr></table>");
                
                out.println("</table>");
                
                
            }
            else if(m_chksql.trim().equals("main_page")){
                
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Asset Financing System</title>    ");
                out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
                out.println("</head>");
                
                rs = stmt.executeQuery (" SELECT "+
                    " COUNT(PAYMENT_NO) "+
                    " FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT "+
                    " WHERE  PROCESS_STATUS='ENTER' AND  ENTRY_TYPE<>'V' ");
                
                boolean more=rs.next();
                if(more){
                    m_count_payment_no=rs.getInt(1);
                } 	
                
                out.println("<Script>");
                
                out.println("function befor_end(m_obj) {");
                out.println("   m_obj.focus();");
                out.println("}");
                
                out.println("function load_roll_value(m_val){"); 
                out.println("if(m_val==''){");
                out.println("help_box.innerHTML=\"Finance - Other Payments Account Selection \";"); 
                out.println("}else{");
                out.println("help_box.innerHTML=\"Finance - Other Payments Account Selection - \"+m_val;"); 
                out.println("}");
                out.println("}");
                
                out.println("function change_val_req(row_no){")	;
                out.println("m_chk_required=\"CHK_REQUIRED\"+row_no;");	
                out.println("var m_chk_acc_no=\"TXT_LIC_ACC_NO\"+row_no;");	
                //out.println("alert(document.Form1.elements[m_chk_acc_no].value);");
                out.println("if(document.Form1.elements[m_chk_required].checked==true){");//ADDED BY LALANKA ON 05-11-2009
                out.println("if(document.Form1.elements[m_chk_acc_no].value==\"\"){");
                out.println("document.Form1.elements[m_chk_required].value='off'");
                out.println("alert('Please enter the account number');");
                out.println("document.Form1.elements[m_chk_required].checked=false");
                out.println("}else {");
                out.println("document.Form1.elements[m_chk_required].value='on'");
                out.println("}");
                out.println("}else if(document.Form1.elements[m_chk_required].checked==false){");
                out.println("document.Form1.elements[m_chk_required].value='off'");
                out.println("}");	
                out.println("}");	
                
                
                out.println("function validate_data(){"); 
                out.println("return true;"); 
                out.println("}"); 
                
                out.println("function before_submit(){ "); 
                out.println("		if(validate_data()){"); 
                out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ "); 
                //out.println("   document.Form1.hid_no_rec.value="+j+";");//Added By Nuwan De Silva
                out.println("   document.Form1.hid_no_rec.value=document.Form1.hid_no_rec_count.value;");//Added By Nuwan De Silva
                
                out.println("		if(validate_data()){"); 
                out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
                out.println("document.Form1.elements[i].disabled=false;");
                out.println("}");
                out.println("		document.Form1.action='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Save_Other_Payment_Account_Select';");  
                out.println("		document.Form1.submit();	"); 
                out.println("		}"); 
                out.println("		}"); 
                out.println("		}"); 
                out.println("else{");
                out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
                out.println("} "); 
                out.println("} "); 
                
                /*  Mod By Sandun on 08-01-2009
                out.println("function Payment_breakup(val) {"); 
                out.println("m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Other_Payments_Account_Select?chksql=Payment_breakup_details&payment_no=\"+val+\"\";");
                //out.println("window.open(m_url);");
                out.println("window.open(m_url,'popupwin','left=290,top=250,width=730,height=465,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=0,resizable=1')");
                out.println("} "); 
                */
                
                out.println("function Payment_breakup(val) {"); //Added By Sandun on 08-01-2009
                out.println("m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Document_Payment_Voucher?chksql=main_page&payment_no=\"+val+\"&print=TRUE;\"");
                out.println("window.open(m_url,'popupwin','left=110,top=110,width=750,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=0')");
                out.println("} "); 	
                
                
                out.println("function help_licence_account_no(rowNo) {"); 
                out.println("    m_account = \"TXT_LIC_ACC_NO\"+rowNo;");
                out.println("    document.Form1.hid_row_no.value=rowNo;"); 
                out.println("    document.Form1.hid_help_type.value=\"5\";"); 
                out.println("    Sql = \"m_help_licence_account_no\";");
                //out.println("    Crit = document.Form1.TXT_LIC_ACC_NO.value+\"@Y@\";"); 
                out.println("    Crit = document.Form1.elements[m_account].value+\"@Y@\";"); 
                out.println("    m_sql = Sql;"); 
                out.println("    m_criteria = Crit"); 
                out.println("    HelpBox('1','10','0');"); 
                
                out.println("}"); 
                out.println(""); 
                
                out.println("function assign_licence_account_no(rowNo,oBj) {"); 
                out.println("    m_account = \"TXT_LIC_ACC_NO\"+rowNo;");
                out.println("    m_branch = \"TXT_LIC_BRANCH_CODE\"+rowNo;");
                out.println("    m_branch_name = \"TXT_LIC_BRANCH_NAME\"+rowNo;");
                out.println("    document.Form1.elements[m_account].value=oBj.valout[2];"); 
                out.println("    document.Form1.elements[m_branch].value=oBj.valout[3];"); 
                out.println("    document.Form1.elements[m_branch_name].value=oBj.valout[4];"); 
                //out.println("    document.Form1.TXT_LIC_ACC_NO.value=oBj.valout[2];"); 
                //out.println("    document.Form1.TXT_LIC_BRANCH_CODE.value=oBj.valout[3];"); 
                //out.println("    document.Form1.TXT_LIC_BRANCH_NAME.value=oBj.valout[4];");
                out.println("}"); 
                
                
                
                /* out.println("function sort_data(m_sort_col) {");
                out.println("	 m_order_by_type = 'ASC'; ");  
                out.println("	 if(m_sort_col=='"+m_sort_column+"'){");
                out.println("	   if('"+m_order_by_type+"'=='DESC'){");
                out.println("	      m_order_by_type = 'ASC'; ");  
                out.println("    }else{");
              out.println("       m_order_by_type = 'DESC'; ");
              out.println("    }");
              out.println("  }else{");
              out.println("    m_order_by_type = 'ASC'; ");
              out.println("  }");
          out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Application_Status_Report?chksql=main_page&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
              out.println(" window.location.href=m_url;"); 
                out.println("}");
                */				
                
                
                out.println("function get_payment_details(m_payee_name){");
                out.println("m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Other_Payments_Account_Select?chksql=payment_details&payee_name=\"+m_payee_name+\"\";");
                out.println("load_interface(m_url,'NORM');");
                out.println("}"); 
                
                out.println("function get_vector_normal(http_response) {");
                out.println(" m_table.innerHTML = ''; ");
                out.println(" m_table.innerHTML = http_response; ");
                out.println("}");
                
                out.println("function load_roll_out_value(){");
                out.println("help_box.innerHTML=\" Finance - Other Payments Account Selection - \"+document.Form1.hid_status.value;"); 
                out.println("}"); 
                
                out.println("function load_help_msg() {"); 
                out.println("    m_help_message = \"\";");  //m_help_msg_LAKDL_AF_PRO_CR_finance_activation
                out.println("    HelpBox_msg(m_help_message);"); 
                out.println("}"); 	
                
                out.println("function HelpBox_msg(m_help_message) {"); 
                out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
                out.println("  \"&help_message_in=\"+m_help_message);"); 
                out.println("}"); 
                
                out.println("function load_screen_status(m_val){"); 
                out.println("if(m_val==\"NEW\"){"); 
                out.println("	new_window();"); 
                out.println("}");
                out.println("else if(m_val==\"HELP\"){"); 
                out.println(" load_help_msg();");
                out.println("}"); 
                out.println("else{");
                out.println("}"); 
                out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
                out.println("if(m_val==\"NEW\"){");
                out.println("document.Form1.hid_status.value=\"New\";"); 
                out.println("}else if(m_val==\"EDIT\"){");  
                out.println("document.Form1.hid_status.value=\"Edit\";");  
                out.println("}else if(m_val==\"DELETE\"){");  
                out.println("}else if(m_val==\"RACT\"){");  
                out.println("document.Form1.hid_status.value=\"Reactivate\";");  
                out.println("}else{");  
                out.println("document.Form1.hid_status.value=\"\";");  
                out.println("}"); 
                out.println("}"); 
                
                out.println("function clear_window(){	"); 
                out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
                out.println("		window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Other_Payments_Account_Select?chksql=main_page';"); 
                out.println("		}"); 
                out.println("}"); 
                
                /*out.println("function search_client_details(m_client_name) {"); 
                out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Other_Payments_Approval?chksql=load_data_client&pre=\"+m_prev+\"&appro=\"+m_app+\"&qry=\"+m_app+\"&division_code=\"+m_division_code+\"&m_client_name=\"+m_client_name+\"&option=\"+m_option;"); 
                out.println("load_interface(m_url,'NORM');");
                out.println("}"); 
                */
                
                
                out.println("function MyDialog(){"); 
                out.println("    this.valout   = new Array(10);"); 
                out.println("}		"); 
                
                out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
                out.println("    oBj = new MyDialog();"); 
                out.println("    oBj.valout[1]  = \" \";"); 
                out.println("    oBj.valout[2]  = \" \";"); 
                out.println("    oBj.valout[3]  = \" \";"); 
                out.println("	"); 
                out.println("popupwin=window.showModalDialog('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_PRO_CR_Help_Servlet?class_in="+m_client_name+"AF_PRO_CR_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
                out.println("	if(oBj.valout[1] ==\" \"){"); 
                out.println("	clear_data(document.Form1.hid_help_type.value);");
                out.println("	}else");
                out.println("	"); 
                out.println("	if(oBj.valout[1] !=\" \"){"); 
                out.println("	if(oBj.valout[1] !=\"Close\"){"); 
                out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
                out.println("	if(oBj.valout[1]!=\"Next\"){"); 
                
                
                
                out.println("if(document.Form1.hid_help_type.value=='1'){"); 
                out.println("		payment_no_assign(oBj);"); 
                out.println("}");
                out.println("else if(document.Form1.hid_help_type.value=='2'){"); 
                out.println("		suspense_referense_assign(oBj);"); 
                out.println("}");
                out.println("else if(document.Form1.hid_help_type.value=='3'){"); 
                out.println("		help_value_assign_3(document.Form1.hid_row_no.value,oBj);"); 
                out.println("}");
                out.println("else if(document.Form1.hid_help_type.value=='4'){"); 
                out.println("		help_value_assign_guarantor(oBj);"); 
                out.println("}");		
                out.println("else if(document.Form1.hid_help_type.value=='5'){"); 
                out.println("		assign_licence_account_no(document.Form1.hid_row_no.value,oBj);"); 
                out.println("}");		
                
                
                
                out.println("	}"); //end next
                out.println("	else{"); 
                out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No);"); 
                out.println("		return false;"); 
                out.println("	} "); 
                out.println("	}"); //end prev
                out.println("	else{	"); 
                out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
                out.println("	}	"); 
                out.println("	}		"); ///close
                out.println("	else{");
                out.println("	clear_data(document.Form1.hid_help_type.value);");//Added To The Clear 
                out.println("	}");
                
                
                out.println("	}	"); //
                out.println("}"); 
                out.println(""); 
                
                out.println("function Prev(Start,End,Hid_No){"); 
                out.println("    HelpBox(Start,End,Hid_No);"); 
                out.println("}"); 
                out.println(""); 
                
                out.println("function Next (Start,End,Hid_No){"); 
                out.println("    HelpBox(Start,End,Hid_No);"); 
                out.println("}"); 
                out.println(""); 
                
                
                out.println("function clear_data() {");
                out.println("}");
                
                out.println("</Script>");
                
                
                
                
                out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' onLoad=\"\">"); //load_lock()
                out.println("<FORM NAME='Form1' method='post'>"); 
                out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
                out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
                out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
                out.println("<INPUT TYPE='Hidden' NAME='hid_row_no' VALUE=\"0\">"); 
                out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
                out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=\"0\">"); 
                out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_CR_PRO_OTHER_PAY_ACC_SEL\">"); 
                out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
                out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
                
                
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
                out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Finance - Other Payments Account Selection</td>"); 
                out.println("</tr>"); 
                out.println("<tr>"); 
                out.println("<td  height='10px' class='pdn_txtpos'>"); 
                out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
                out.println("<td width='10%'></td>");  
                //out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
                out.println("<td width='6%'></td>");  
                out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='before_submit()' value=\"Save\"></td>");  
                out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
                out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
                out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
                //out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Copy\");'  onclick='View_Letter()' value=\"Copy\"></td>"); 
                out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
                out.println("</table>");  
                
                out.println("</td></tr><tr>");  
                out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
                out.println("</tr><tr>");  
                out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
                out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%' >");  
                out.println("<tr class='tr_input'>");  
                out.println("<td width='100%'><img height='1' src='spacer.gif' width='100%' /></td>");  
                out.println("</tr>");  
                out.println("</table>");  
                
                
                out.println("<table align='center' width='100%' class='table' border='0'>"); 
                out.println("<tr class=tr_input>"); 
                out.println("<td width='20%' ><DIV id='DIV_TXT_CLIENT_NAME'  class=div_input>Payee Name </DIV></td>"); 
                out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_NAME' maxlength='15' style=\"{width:250px;}\" size='15' >");  //onblur=\"assignState('M2'),makeRequest(document.Form1.TXT_APPLICATION_NO)\"
                out.println("<input class='but_input' type='button' name='BUT_TXT_CLIENT_NAME_SEARCH' value=\"Search\" onClick=\"get_payment_details(document.Form1.TXT_CLIENT_NAME.value)\"></td>"); //m_help_TXT_APPLICATION_NO
                out.println("<td width='*%'><b>Pending Payments For Account Entering &nbsp;&nbsp;&nbsp; "+m_count_payment_no+"</td>");
                out.println("</tr>"); 
                
                
                /*out.println("<tr class=tr_input>");
                out.println("<td width='20%' >Division Code</td>");
                out.println("<td width='40%' >");
                out.println("<select name=\"TXT_DIVISION_CODE\" class=\"txt_input\" style=\"{width:200px;}\" >");
                rs = stmt.executeQuery(	"  SELECT "+
                "  DIVISION_CODE, "+
                "  DESCRIPTION "+
                "  FROM "+m_schema_name+".CO_CO_MAS_DIVISION "+
                "  WHERE ACTIVE_STATUS='Y' ");
                
                boolean more = rs.next();
                while(more){
                out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
                more = rs.next();	
                }	
                out.println("<OPTION value=\"ALL\" SELECTED >ALL</option>");
                out.println("</SELECT>");
                out.println("</td>");
                out.println("</tr>");
                */
                
                out.println("</table>"); 
                
                out.println("<table align='center' width='100%' class='table'>"); 
                
                out.println("<tr>");  
                out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
                out.println("</tr>"); 
                
                out.println("</table>");
                
                out.println("</td></tr><tr>");  
                out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
                out.println("</tr><tr>");  	
                out.println("<tr>"); 
                out.println("<td  height='10px' class='pdn_txtpos'>"); 
                out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
                //out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
                out.println("<td width='10%'></td>");  
                out.println("<td width='6%'></td>");  
                out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='before_submit()' value=\"Save\"></td>");  
                out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
                out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
                out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
                //out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Copy\");'  onclick='View_Letter()' value=\"Copy\"></td>"); 
                out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
                out.println("</table>");  
                
                out.println("</td></tr><tr>");  
                out.println("</tr><tr>");  	
                out.println("</form>");
                out.println("</body>");
                out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
                out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
                out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
                out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
                out.println("</html>");
                
                
            }
            
            //=========================================================================================================================			
            /*else {
                out.println("Undefined");
            }
            */
            //out.close();
            //conn.close();
            //this.destroy();
            
            
        }
        catch (Exception e) {
            try{out.println(e.toString());}catch(Exception e1){}
            //return null;
        }finally{
            if(rs    !=null){try{rs.close();   }catch(Exception e){}}
            if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
            if(conn  !=null){try{conn.close(); }catch(Exception e){}}
            if(out   !=null){try{out.close();  }catch(Exception e){}}
            //try{conn.setAutoCommit(true);
        }
    }
}
