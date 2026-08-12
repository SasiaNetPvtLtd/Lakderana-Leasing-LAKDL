//ID         :
//SCREEN NAME:Document Printing - Schedule
//CREATED BY :Chandana 	
//DATE/TIME  : 11-06-2007
//NOTES:

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_CR_PRO_Document_hirepurchase_shedule extends HttpServlet {
    
    ServletOutputStream out = null;
    
    
    Connection conn;
    Statement stmt,stmt_invoice,stmt_insu,stmt1,stmt_partner,stmt_rental,stmt2;
    //	CallableStatement callstmt1;
    java.text.NumberFormat nf;
    
    public ResultSet rs,rs1,rs_invoice,rs_insu,rs3,rs_partner,rs_rental,rs2;
    
    
    public String m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_due_date,m_no_of_due_date,m_finance_no,m_print,m_letter_date;
    public String m_Reg_no,m_Make_modle,m_Eng_no,m_Chas_no,m_Manu_year,m_amnt_word;
    public double m_amount_due,m_amount;
    public String m_master_lease_no;
    
    public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
        
        try { 
            
            LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
            Method    Obj_method = new Method();//added by sn on 02-09-2010
            conn = m_sn_methods.met_user_validate(req); 
            String m_html_client_url=m_sn_methods.html_client_url.trim(); 
            String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
            String m_fschema_name=m_sn_methods.client_name.trim();
            String m_schema_name = m_sn_methods.schema_name.trim();
            //	String m_chksql;
            res.setStatus(HttpServletResponse.SC_OK); 
            res.setContentType("text/html"); 
            out = res.getOutputStream(); 
            
            nf = java.text.NumberFormat.getInstance(Locale.US);
            nf.setMinimumFractionDigits(2);
            nf.setMaximumFractionDigits(2);
            
            // out.println("conn"+conn);
            int m_data_count=0;
            String m_status ="";
            
            String m_orient_name="";
            String m_orient_add1="";
            String m_orient_add2="";
            String m_orient_city_name="";
            String m_orient_tel_no="";
            String m_orient_fax_no="";
            String m_orient_vat_rate="";
            String m_partner_name [];
            
            
            
            String m_chksql = req.getParameter("chksql");
            
            if(m_chksql.trim().equals("main_page")){
                
                
                
                stmt = conn.createStatement ();
                stmt_invoice = conn.createStatement ();
                stmt_partner= conn.createStatement ();
                stmt_rental = conn.createStatement ();
                stmt2 = conn.createStatement ();
                
                String m_application_no = req.getParameter("application_no");
                String m_client_code	  =req.getParameter("client_code");	
                String m_document_code	=req.getParameter("document_code");	
                String m_print=req.getParameter("print");
                
                
                
                if(req.getParameter("status")==null){
                    
                    rs=stmt.executeQuery (" SELECT "+
                        " COUNT(DOCUMENT_CODE) "+
                        " FROM "+m_schema_name+".AF_CR_PRO_DOCUMENT_STATUS "+
                        " WHERE APPLICATION_NO=UPPER('"+m_application_no+"') AND CLIENT_CODE=UPPER('"+m_client_code+"') AND DOCUMENT_CODE=UPPER('"+m_document_code+"') ");
                    
                    boolean	more = rs.next();
                    if(more){
                        m_data_count=rs.getInt(1);
                        
                    }
                    
                    if(m_data_count==0){
                        m_status="ORIGINAL";
                    }
                    else{
                        m_status="COPY";
                    }
                    
                }
                else
                {
                    m_status=req.getParameter("status");
                }
                rs=stmt.executeQuery (" SELECT TO_CHAR(SYSDATE, 'fmddth') ||'  '||TO_CHAR(SYSDATE, 'Month')||TO_CHAR(SYSDATE, 'YYYY') LETTER_DATE "+
                    " FROM DUAL ");
                
                boolean	more = rs.next();
                if(more){
                    m_letter_date=rs.getString(1);
                }
                
                rs.close();	
                
                rs = stmt.executeQuery(" SELECT "+
                    " NVL(UPPER(COMPANY_NAME),''), "+
                    " NVL(UPPER(ADDRESS1),''), "+
                    " NVL(UPPER(ADDRESS2),''), "+
                    " NVL(UPPER(CITY),''), "+
                    " NVL(TEL_NO,''), "+
                    " NVL(FAX_NO,''),  "+
                    " NVL(VAT_RATE,0) "+
                    " FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ");
                
                more = rs.next();		
                
                if(more)
                {
                    m_orient_name=rs.getString(1);
                    m_orient_add1=rs.getString(2);
                    m_orient_add2=rs.getString(3);
                    m_orient_city_name=rs.getString(4);
                    m_orient_tel_no=rs.getString(5);
                    m_orient_fax_no=rs.getString(6);
                    m_orient_vat_rate=rs.getString(7);			
                }
                
                rs = stmt.executeQuery("	SELECT A.APPLICATION_NO, "+
                    " A.TOTAL_AMOUNT, "+
                    " NVL(A.REG_NO,'-'), "+
                    " NVL(B.MAKE_CODE,'')||' '||NVL(B.ITEM_SUB_CAT,'')||' '||NVL(C.DESCRIPTION,''), "+
                    " A.ENGINE_NO, "+
                    " A.CHASSIS_NO, "+
                    " C.YEAR_OF_MANUFACTURE "+
                    " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_MAS_MODEL B,"+m_schema_name+".AF_CO_MAS_SUB_MODLE C "+
                    " WHERE   A.MODEL_CODE=B.MODEL_CODE AND "+
                    " A.MODEL_CODE=C.MODEL_CODE AND "+
                    " APPLICATION_NO=UPPER('"+m_application_no+"') ");
                
                more = rs.next(); 
                
                if(more){
                    m_amnt_word=rs.getString(2);
                    m_amount=rs.getDouble(2);
                    m_Reg_no =rs.getString(3);
                    m_Make_modle =rs.getString(4);
                    m_Eng_no =rs.getString(5);
                    m_Chas_no=rs.getString(6);
                    m_Manu_year=rs.getString(7);		
                }		
                
                rs.close();
                stmt.close();
                
                out.println("<html><head>"); 
                out.println("<title>Acceptance Receipt </title></head>");
                out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
                out.println("<script>");
                
                out.println("function save_data(){");
                out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Documents_Status?chksql=save_page&scr_name=AF_MK_APP_STATUS_APPROVE_3&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&document_code="+m_document_code+"\";"); 
                
                //out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Leasepurchase_shedule?chksql=shedule1&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&document_code="+m_document_code+"&print=FALSE\";"); 
                
                //LAKDL_AF_CR_PRO_Document_Leasepurchase_shedule?chksql=schedule1&application_no=AP20070608-0723&document_code=ACCEPT_REC&print=TRUE&client_code=0000000366
                //LAKDL_AF_CR_PRO_Document_cash_price_of_goods?chksql=main_page&application_no=AP20070608-0723&document_code=ACCEPT_REC&print=TRUE&client_code=0000000366
                
                out.println(" window.location.href=m_url;"); 
                
                out.println("m_table.innerHTML=\"\" ");
                
                
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
                
                out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");//add_button()
                
                //out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo.gif\"></DIV><br><hr>");
                
                out.println("<body bgcolor='white'><br>");
                out.println("<form name='Form1'>");
                out.println("<table align='center' width='100%' class='table'>"); 
                out.println("<tr>");  
                out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
                out.println("</tr>"); 
                out.println("</table>");
                
                out.println("<blockquote><font size=3><p style='text-align:left'>");					
                out.println("<table align='center' width='100%' class='table'>"); 
                out.println("<tr><td width=\"100%\" class='rep-body1'><b></b></td></tr>"); //"+m_status+"
                out.println("</table>");
                out.println("</font></p></blockquote>");	
                
                String m_client_name="";
                String m_client_add="";
                String m_client_nic="";
                String m_act_date="";
                double m_hp_amount=0;	
                String m_str_hp_amount="";
                double m_rate=0;	
                String m_finance_no="";
                String m_dicision_maker="";
                
                stmt = conn.createStatement ();
                
                rs = stmt.executeQuery(" SELECT NVL(TO_CHAR(A.ACTIVATED_DATE,'DD-MON-YYYY'),'-'), "+ //1
                    // " NVL(UPPER(B.FULL_NAME),'-'), "+ //2
                    //	" NVL(UPPER(B.ADDRESS1),'-'), "+ //3
                    //	" NVL(UPPER(B.ADDRESS2),'-'), "+ //4
                    " UPPER(DECODE(B.CLIENT_TYPE,'I',UPPER(B.TITLE) || '. ' || B.FULL_NAME,'C','MESS' || '. '  || B.FULL_NAME)), "+ //2
                    " UPPER(NVL(DECODE(B.CLIENT_TYPE,'I',B.ADDRESS1,'C',B.REGISTERED_ADDRESS1),'-')), "+//3
                    " UPPER(NVL(DECODE(B.CLIENT_TYPE,'I',B.ADDRESS2,'C',B.REGISTERED_ADDRESS2),'-')), "+//4
                    " NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(B.CITY_CODE)),'-'), "+ //5
                    //" NVL(NIC_NO,'-'), "+ //6
                    " DECODE(B.CLIENT_TYPE,'I',NVL(DECODE(NATIONALITY,'SRILANKAN',B.NIC_NO,B.PASSPORT_NO),'-'),'C',B.BUSINESS_CERTIFICATE_NO) ,"+
                    
                    
                    //" NVL(UPPER(B.ADDRESS1),'')||' '||NVL(UPPER(B.ADDRESS2),'')||' '||NVL(UPPER(B.CITY_CODE),''), "+ //7 //" UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)) ,"+
                    " NVL(UPPER(B.ADDRESS1),'') || ' ' || NVL(UPPER(B.ADDRESS2) || '' || NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(B.CITY_CODE)),'') ,''), "+
                    " A.TOTAL_FINANCE_AMOUNT, "+//8
                    " C.RATE, "+ //9
                    " A.FINANCE_NO, "+ //10
                    //" NVL(TO_CHAR(A.AGREEMENT_DATE,'DD-MON-YYYY'),'-'), "+ //11
                    " NVL(TO_CHAR(A.AGREEMENT_DATE, 'fmddth'),'-') AGREEMENT_DATE, "+ //ADDED BY NUWAN DE SILVA 04-09-07 //11
                    " NVL(TO_CHAR(A.AGREEMENT_DATE, 'Month YYYY'),'-') AGREEMENT_DATE2, "+ //ADDED BY NUWAN DE SILVA 04-09-07 //12
                    " NVL(TO_CHAR(A.AGREEMENT_DATE, 'fmddth Month YYYY'),'-') AGREEMENT_DATE3, "+//13
                    " nvl(A.TER_TYPE,'-') ,"+//14
                    " NVL(B.KEY_DECISION_MAKER,'-') "+//15
                    " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B, "+
                    " "+m_schema_name+".AF_CO_PRO_APP_PRICING C "+
                    " WHERE A.CLIENT_CODE=B.CLIENT_CODE AND "+
                    " A.APPLICATION_NO=C.APPLICATION_NO AND "+
                    " A.APPLICATION_NO=UPPER('"+m_application_no+"') ");
                more=rs.next();
                
                String m_full_name="";
                String m_add1="";
                String m_add2="";
                String m_city_name="";
                String m_address="";
                String m_agr_day="";
                String m_agr_month="";
                String m_ter_type="";
                
                
                if(more){
                    m_act_date=rs.getString(1);
                    m_client_name=rs.getString(2);
                    m_full_name=rs.getString(2);//
                    m_add1=rs.getString(3); //
                    m_add2=rs.getString(4); //
                    m_city_name=rs.getString(5); //
                    m_client_add=rs.getString(7);
                    m_client_nic=rs.getString(6);
                    m_hp_amount=rs.getDouble(8);	
                    //	 m_rate=rs.getString(9);	
                    m_finance_no=rs.getString(10);	
                    m_str_hp_amount=rs.getString(8);	 //added by nuwan de silva 10-07-07
                    m_agr_day=rs.getString(11);				//added by nuwan de silva on 04-09-07		
                    m_agr_month=rs.getString(12);	   			//added by nuwan de silva 04 05-09-07		
                    m_letter_date=rs.getString(13);
                    m_ter_type = rs.getString(14);//Added By Sandun on 14-07-2009
                    m_dicision_maker = rs.getString(15);//Added By Sandun on 14-07-2009
                }
                
                if(m_ter_type.equals("RESCHEDULE")){
                    
                    rs = stmt.executeQuery(" SELECT NVL(A.FINANCED_AMOUNT,0)+NVL(A.CHARGES,0)+NVL(A.MAINTENANCE,0) "+
                        " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A "+
                        " WHERE A.APPLICATION_NO='"+m_application_no+"' "+
                        " AND ACTIVE_STATUS='T' ");												 
                    
                    
                    if(rs.next()){
                        m_hp_amount     = rs.getDouble(1);
                        m_str_hp_amount = rs.getString(1);
                    }
                    
                    
                    
                }
                
                rs.close();
                //stmt.close();				
                
                if(!m_add1.equals("-") && !m_add2.equals("-") && !m_city_name.equals("-") )
                {
                    m_address=m_add1+","+m_add2+","+m_city_name;
                }
                
                else if(!m_add1.equals("-") && !m_add2.equals("-") && m_city_name.equals("-") )
                {
                    m_address=m_add1+","+m_add2;
                }
                
                else if(!m_add1.equals("-") && m_add2.equals("-") && !m_city_name.equals("-") )
                {
                    m_address=m_add1+","+m_city_name;
                }
                else if(m_add1.equals("-") && m_add2.equals("-") && !m_city_name.equals("-") )
                {
                    m_address=m_city_name;
                }
                
                
                //	stmt = conn.createStatement ();		
                rs=stmt.executeQuery (" SELECT "+ 	 
                    " RATE "+
                    " FROM "+m_schema_name+".AF_CO_MAS_OD_INTEREST_RATE "+
                    " WHERE ACTIVE_STATUS='Y' ");
                more=rs.next();
                if(more){
                    m_rate=rs.getDouble(1);
                }
                
                rs.close();
                //	stmt.close();			
                
                String m_to_be_delver="";
                String m_del_add="";
                String m_city="";
                
                //	stmt = conn.createStatement ();		
                /*rs1 = stmt.executeQuery(" SELECT NVL(UPPER(TO_BE_DELIVERD_TO),' '),NVL(UPPER(ADDRESS),' '),NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),' ') "+
                                        " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
                                                                " WHERE ENT_DATE=(SELECT DISTINCT MAX(ENT_DATE) "+
                                                                " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
                                                                " WHERE APPLICATION_NO=UPPER('"+m_application_no+"')) ");
                */
                
                //Mod By Sandun on 14-07-2009
                rs1 = stmt.executeQuery(" SELECT NVL(UPPER(TO_BE_DELIVERD_TO),' '),NVL(UPPER(ADDRESS),' '),NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),' ') "+
                    " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
                    " WHERE APPLICATION_NO=UPPER('"+m_application_no+"') "+
                    " AND ACTIVE_STATUS = 'Y' ");
                
                
                more=rs1.next();
                if(more){
                    m_to_be_delver =rs1.getString(1);
                    m_del_add      =rs1.getString(2);
                    m_city         =rs1.getString(3);
                }
                
                rs.close();
                //	stmt.close();	
                
                String m_entity_code="";
                String m_description="";
                
                
                
                rs= stmt.executeQuery (" SELECT ENTITY_CODE,DESCRIPTION "+
                    " FROM   "+m_schema_name+".AF_CO_MAS_LEGAL_ENTITY "+
                    " WHERE  ENTITY_CODE=(SELECT CLIENT_CATEGORY "+
                    " FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
                    " WHERE UPPER(CLIENT_CODE)= '"+m_client_code+"' ) ");
                
                more=rs.next();	
                
                if(more)
                {
                    m_entity_code=rs.getString(1);
                    m_description=rs.getString(2);			 
                }
                rs.close();
                
                
                
                String data="";
                String sql=" SELECT "+
                    " UPPER(NAME) "+
                    " FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DIRECTORS "+
                    " WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') ";
                
                
                rs_partner=stmt_partner.executeQuery(sql);
                int count_part=0;
                int x=0;
                
                while(rs_partner.next()){
                    count_part=count_part+1;
                }
                
                m_partner_name = new String[count_part];
                
                rs_partner=stmt_partner.executeQuery (sql);
                rs_partner.next();
                while(x<count_part){
                    m_partner_name[x]=rs_partner.getString(1);
                    x=x+1;
                    rs_partner.next();
                }
                
                rs_partner=stmt_partner.executeQuery (sql);
                String m_contact_people="";
                int m_pos=count_part-1;
                int j_part=1;
                x=0;
                
                
                while(x<count_part){
                    
                    if(j_part==m_pos)		{
                        m_contact_people=m_contact_people+m_partner_name[x]+" and"+" ";
                    }
                    else if(x==m_pos){
                        m_contact_people=m_contact_people+m_partner_name[x];
                    }
                    
                    else{
                        m_contact_people=m_contact_people+m_partner_name[x]+","+" ";
                    }
                    
                    x=x+1;
                    j_part=j_part+1;
                }
                
                
                
                out.println("<font size=3><p style='text-align:center'>");
                //------- Added by Chandana on 03/08/2007 -----------------//
                out.println("<table align='center' width='100%' class='table'><tr>"); 
                out.println("<td width=\"50%\" class='rep-body1' style='text-align:left'><b>Agreement No: "+m_finance_no+"</b></td>"); 
                out.println("<td width=\"50%\" class='rep-body1' style='text-align:right'><b>1 of 4</b></td>");
                out.println("</tr></table>");
                out.println("<br><br>");
                //---------End on 03/08/2007 -----------------//
                
                out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                out.println("<tr><td width=\"*%\" class='rep-body1' align='center' ><H5><b>Lease Purchase Agreement</b></td></tr>");
                out.println("</table>");
                out.println("<br>");
                
                
                out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                out.println("<tr><td width=\"*%\" class='rep-body1' align='center' ><b>Agreement No:</b></td></tr>");
                out.println("<tr><td width=\"*%\" class='rep-body1' align='center' ><b>"+m_finance_no+"</b></td></tr>");
                out.println("</table>");
                out.println("<br>");
                out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                out.println("<tr><td width=\"*%\" class='rep-body1' align='center' ><b>SCHEDULE I</b></td></tr>");
                out.println("</table>");
                out.println("<br>");
                out.println("<br>");
                out.println("<br>");
                //out.println("<br>");
                out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='1' bordercolor='black'>"); 
                out.println("<tr><td width=\"45%\" class='rep-body1' valign='top' >");
                out.println("</font>");
                
                out.println("<font size=3><p style='text-align:left'>");
                
                out.println("<table align='center' width='100%' class='table' cellspacing='0' cellpadding='0'>");
                out.println("<tr><td width=\"3%\"><td width=\"2%\">1.</td><td width=\"*%\"     class='rep-body1' align='left' >Date :&nbsp"+m_letter_date+"</td></tr>"); //m_act_date
                out.println("<tr><td width=\"3%\"><td width=\"2%\">&nbsp;</td><td width=\"*%\" class='rep-body1' align='left' >&nbsp&nbsp</td></tr>");
                out.println("<tr><td width=\"3%\"><td width=\"2%\">&nbsp;</td><td width=\"*%\" class='rep-body1' align='left' >&nbsp</td></tr>");
                out.println("<tr><td width=\"3%\"><td width=\"2%\">&nbsp;</td><td width=\"*%\" class='rep-body1' align='left' >&nbsp</td></tr>");
                out.println("</table>");
                out.println("</td>");
                
                
                
                out.println("<td width=\"45%\" class='rep-body1' valign='top' >");
                
                
                if(m_entity_code.equals("PARTNERS")){
                    data=""+m_contact_people+" CARRING ON BUSINESS IN PARTNERSHIP UNDER THE NAME STYLE AND FIRM OF " +m_client_name+" "+m_address+" ";
                    out.println("<table align='center' width='100%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"3%\"></td><td width=\"2%\">2.</td><td width=\"*%\" class='rep-body1' align='left' >Name/ Address, NIC Number of the <b>Lease Purchaser</b></td></tr>");
                    out.println("<tr><td width=\"3%\"></td><td width=\"2%\">&nbsp;</td><td width=\"*%\" class='rep-body1' align='left' >"+data+"</td></tr>");
                    //out.println("<tr><td width=\"3%\"></td><td width=\"2%\">&nbsp;</td><td width=\"*%\" class='rep-body1' align='left' >"+m_address+"</td></tr>");
                    out.println("<tr><td width=\"3%\"></td><td width=\"2%\">&nbsp;</td><td width=\"*%\" class='rep-body1' align='left' >NIC/REG NO:&nbsp;"+m_client_nic+"</td></tr>");
                    out.println("</table>");
                }
                if(m_entity_code.equals("SOLEPROPRI")){//Added By Sandun on 25-06-2009			
                    out.println("<table align='center' width='100%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"3%\"></td><td width=\"2%\">2.</td><td width=\"*%\" class='rep-body1' align='left' >Name/ Address, NIC Number of the <b>Lease Purchaser</b></td></tr>");
                    out.println("<tr><td width=\"3%\"></td><td width=\"2%\">&nbsp;</td><td width=\"*%\" class='rep-body1' align='left' >"+m_dicision_maker.toUpperCase()+" OF</td></tr>");
                    out.println("<tr><td width=\"3%\"></td><td width=\"2%\">&nbsp;</td><td width=\"*%\" class='rep-body1' align='left' >"+m_address.toUpperCase()+" PROPRIETOR OF "+m_client_name.toUpperCase()+" <br> "+m_address.toUpperCase()+"</td></tr>");
                    out.println("<tr><td width=\"3%\"></td><td width=\"2%\">&nbsp;</td><td width=\"*%\" class='rep-body1' align='left' >NIC/REG NO:&nbsp;"+m_client_nic+"</td></tr>");
                    out.println("</table>");			
                }
                else{
                    out.println("<table align='center' width='100%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"3%\"></td><td width=\"2%\">2.</td><td width=\"*%\" class='rep-body1' align='left' >Name/ Address, NIC Number of the <b>Lease Purchaser</b></td></tr>");
                    out.println("<tr><td width=\"3%\"></td><td width=\"2%\">&nbsp;</td><td width=\"*%\" class='rep-body1' align='left' >"+m_client_name+"</td></tr>");
                    out.println("<tr><td width=\"3%\"></td><td width=\"2%\">&nbsp;</td><td width=\"*%\" class='rep-body1' align='left' >"+m_address+"</td></tr>");
                    out.println("<tr><td width=\"3%\"></td><td width=\"2%\">&nbsp;</td><td width=\"*%\" class='rep-body1' align='left' >NIC/REG NO:&nbsp;"+m_client_nic+"</td></tr>");
                    out.println("</table>");
                    
                    
                }
                //&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+m_client_name+", &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp "+m_client_add+",   &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+m_client_nic+"</td></tr>");
                
                out.println("</td></tr>");
                
                out.println("<tr><td width=\"45%\" class='rep-body1' valign='top'> ");
                //MODIIFED BY NUWAN DE SILVA 10-07-07
                
                
                //comment by ns on 12-08-2010
                /***
            //	stmt = conn.createStatement ();		
                rs = stmt.executeQuery(" SELECT "+
                                        //" NVL(UPPER(FULL_NAME),' '), "+
                                        //" NVL(UPPER(ADDRESS1),'-'), "+
                                                                //" NVL(UPPER(ADDRESS2),'-'), "+
                                                                " UPPER(DECODE(CLIENT_TYPE,'I',UPPER(TITLE) || '. ' || FULL_NAME,'C','MESS' || '. '  || FULL_NAME)), "+ //1
                                                                " UPPER(NVL(DECODE(CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-')), "+//2
                                                                " UPPER(NVL(DECODE(CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-')), "+//3
                                                                //" NVL(UPPER(CITY_CODE),' '), "+
                                                                " NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),'-') ,"+
                                                                //" NVL(NIC_NO,' ') "+
                                                                " DECODE(CLIENT_TYPE,'I',NIC_NO,'C',BUSINESS_CERTIFICATE_NO)"+
                                                                " FROM "+m_schema_name+".AF_CO_MAS_CLIENT WHERE CLIENT_CODE IN "+
                                                                " (SELECT GUARANTOR_CODE "+
                                                                " FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR "+
                                                                " WHERE APPLICATION_NO=UPPER('"+m_application_no+"'))");
                                                                
                ****/			
                
                //added by ns on 12-08-2010
                rs = stmt.executeQuery(" SELECT "+
                    " UPPER(DECODE(CLIENT_TYPE,'I',UPPER(TITLE) || '. ' || FULL_NAME,'C','MESS' || '. '  || FULL_NAME)), "+ //1
                    " UPPER(NVL(DECODE(CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-')), "+//2
                    " UPPER(NVL(DECODE(CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-')), "+//3
                    " NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),'-') ,"+
                    " DECODE(CLIENT_TYPE,'I',NVL(DECODE(NATIONALITY,'SRILANKAN',NIC_NO,PASSPORT_NO),'-'),'C',BUSINESS_CERTIFICATE_NO)"+
                    " ,B.GUAR_ID"+
                    " FROM "+m_schema_name+".AF_CO_MAS_CLIENT A, "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR B"+
                    " WHERE A.CLIENT_CODE=B.GUARANTOR_CODE "+
                    " AND   B.APPLICATION_NO=UPPER('"+m_application_no+"') ORDER BY B.GUAR_ID ");
                
                
                more=rs.next();        	
                int j=1;
                while(more){	
                    
                    m_add1=rs.getString(2);
                    m_add2=rs.getString(3);
                    m_city_name=rs.getString(4);
                    if(!m_add1.equals("-") && !m_add2.equals("-") && !m_city_name.equals("-") ){
                        m_address=m_add1+","+m_add2+","+m_city_name;
                    }else if(!m_add1.equals("-") && !m_add2.equals("-") && m_city_name.equals("-") ){
                        m_address=m_add1+","+m_add2;
                    }else if(!m_add1.equals("-") && m_add2.equals("-") && !m_city_name.equals("-") ){
                        m_address=m_add1+","+m_city_name;
                    }else if(m_add1.equals("-") && m_add2.equals("-") && !m_city_name.equals("-") ){
                        m_address=m_city_name;
                    }
                    
                    
                    if(j==1){
                        out.println("<table align='center' width='100%' class='table' cellspacing='0' cellpadding='0'>");
                        out.println("<tr><td width=\"3%\"></td><td width=\"2%\">3.</td><td width=\"*%\" class='rep-body1' align='left' >Name/ Address NIC Numbers of the Guarantors</td></tr>");
                        //out.println("<tr><td width=\"3%\"></td><td width=\"2%\">&nbsp;</td><td width=\"*%\" class='rep-body1' align='left' >"+rs.getString(1)+"</td></tr>");
                        //out.println("<tr><td width=\"3%\"></td><td width=\"2%\">&nbsp;</td><td width=\"*%\" class='rep-body1' align='left' >"+m_address+"</td></tr>");
                        //out.println("<tr><td width=\"3%\"></td><td width=\"2%\">&nbsp;</td><td width=\"*%\" class='rep-body1' align='left' >"+rs.getString(3)+"</td></tr>");
                        //out.println("<tr><td width=\"1%\"></td><td width=\"2%\">&nbsp;</td><td width=\"*%\" class='rep-body1' align='left' >&nbsp"+rs.getString(4)+"</td></tr>");
                        //out.println("<tr><td width=\"3%\"></td><td width=\"2%\">&nbsp;</td><td width=\"*%\" class='rep-body1' align='left' >NIC/REG N0:&nbsp;"+rs.getString(5)+"</td></tr>");
                        out.println("</table>");
                    }
                    
                    out.println("<table align='center' width='100%' class='table' cellspacing='0' cellpadding='0'>");
                    //out.println("<tr><td width=\"3%\"></td><td width=\"2%\">3.</td><td width=\"*%\" class='rep-body1' align='left' >Name/ Address NIC Numbers of the Guarantors</td></tr>");
                    // Line Commented And Added By Samitha Kulatilaka On 2012-01-05
                    // out.println("<tr><td width=\"3%\"></td><td width=\"2%\" valign='top'>"+j+".</td><td width=\"*%\" class='rep-body1' align='left' >"+rs.getString(1)+"</td></tr>");
                    out.println("<tr><td width=\"3%\"></td><td width=\"2%\" valign='top'>&nbsp;</td><td width=\"*%\" class='rep-body1' align='left' >"+rs.getString(1)+"</td></tr>");
                    out.println("<tr><td width=\"3%\"></td><td width=\"2%\">&nbsp;</td><td width=\"*%\" class='rep-body1' align='left' >"+m_address+"</td></tr>");
                    out.println("<tr><td width=\"3%\"></td><td width=\"2%\">&nbsp;</td><td width=\"*%\" class='rep-body1' align='left' >NIC/REG N0:&nbsp;"+rs.getString(5)+"</td></tr>");
                    out.println("</table>");
                    j=j+1;
                    more=rs.next();
                }
                
                out.println("</td>");
                
                out.println("<td width=\"45%\" class='rep-body1' valign='top'>");
                out.println("<table align='center' width='100%' class='table' cellspacing='0' cellpadding='0'>");
                out.println("<tr><td width=\"3%\"></td><td width=\"2%\">4.</td><td width=\"*%\" class='rep-body1' align='left' >Cash price of the vehicle</td></tr>");
                out.println("<tr><td width=\"3%\"></td><td width=\"2%\">&nbsp;</td><td width=\"*%\" class='rep-body1' align='left' >"+nf.format(m_hp_amount)+"</td></tr>");
                
                ///out.println("<tr><td width=\"3%\"></td><td width=\"2%\">&nbsp;</td><td width=\"*%\" class='rep-body1' align='left' >RUPEES "+m_sn_methods.numbersToChar(m_str_hp_amount).toUpperCase()+" ONLY</td></tr>");
                out.println("<tr><td width=\"3%\"></td><td width=\"2%\">&nbsp;</td><td width=\"*%\" class='rep-body1' align='left' >RUPEES "+Obj_method.numbersToChar4(m_str_hp_amount).toUpperCase()+" ONLY</td></tr>");
                //out.println("<tr><td width=\"3%\"></td><td width=\"2%\">&nbsp;</td><td width=\"*%\" class='rep-body1' align='left' >RUPEES "+m_sn_methods.numbersToChar("100000000").toUpperCase()+" ONLY</td></tr>");
                
                out.println("<tr><td width=\"3%\"></td><td width=\"2%\">&nbsp;</td><td width=\"*%\" class='rep-body1' align='left' >&nbsp</td></tr>");
                out.println("<tr><td width=\"3%\"></td><td width=\"2%\">&nbsp;</td><td width=\"*%\" class='rep-body1' align='left' >&nbsp</td></tr>");
                out.println("<tr><td width=\"3%\"></td><td width=\"2%\">&nbsp;</td><td width=\"*%\" class='rep-body1' align='left' >&nbsp</td></tr>");
                out.println("</table>");			
                out.println("</td></tr>");
                out.println("<tr>");
                out.println("<td width=\"45%\" class='rep-body1' valign='top'>");
                out.println("<table align='center' width='100%' class='table' cellspacing='0' cellpadding='0'>");
                out.println("<tr><td width=\"3%\"></td><td width=\"2%\">5.</td><td width=\"*%\" class='rep-body1' align='left' >Date of the cash price letter &nbsp"+m_letter_date+"</td></tr>");
                out.println("<tr><td width=\"3%\"></td><td width=\"2%\">&nbsp;</td><td width=\"*%\" class='rep-body1' align='left' >&nbsp&nbsp&nbsp</td></tr>");
                out.println("<tr><td width=\"3%\"></td><td width=\"2%\">&nbsp;</td><td width=\"*%\" class='rep-body1' align='left' >&nbsp&nbsp&nbsp</td></tr>");
                out.println("<tr><td width=\"3%\"></td><td width=\"2%\">&nbsp;</td><td width=\"*%\" class='rep-body1' align='left' >&nbsp&nbsp&nbsp</td></tr>");
                out.println("<tr><td width=\"3%\"></td><td width=\"2%\">&nbsp;</td><td width=\"*%\" class='rep-body1' align='left' >&nbsp</td></tr>");
                out.println("<tr><td width=\"3%\"></td><td width=\"2%\">&nbsp;</td><td width=\"*%\" class='rep-body1' align='left' >&nbsp</td></tr>");
                out.println("</table>");
                out.println("</td>");
                
                out.println("<td width=\"45%\" class='rep-body1' valign='top'>");
                out.println("<table align='center' width='100%' class='table' cellspacing='0' cellpadding='0'>");
                out.println("<tr><td width=\"3%\"></td><td width=\"2%\">6.</td><td width=\"*%\" class='rep-body1' align='left' >Address at where the will be kept</td></tr>");
                //out.println("<tr><td width=\"1%\"></td><td width=\"*%\" class='rep-body1' align='left' >&nbsp&nbsp&nbsp"+m_to_be_delver+"</td></tr>");
                out.println("<tr><td width=\"3%\"></td><td width=\"2%\">&nbsp;</td><td width=\"*%\" class='rep-body1' align='left' >"+m_del_add+"</td></tr>");
                out.println("<tr><td width=\"3%\"></td><td width=\"2%\">&nbsp;</td><td width=\"*%\" class='rep-body1' align='left' >"+m_city+"</td></tr>");
                out.println("<tr><td width=\"3%\"></td><td width=\"2%\">&nbsp;</td><td width=\"*%\" class='rep-body1' align='left' >&nbsp</td></tr>");
                out.println("<tr><td width=\"3%\"></td><td width=\"2%\">&nbsp;</td><td width=\"*%\" class='rep-body1' align='left' >&nbsp</td></tr>");
                out.println("</table>");
                out.println("</td></tr>");
                
                out.println("<tr><td width=\"45%\" class='rep-body1' valign='top' >&nbsp&nbsp7. Rate of default interest "+m_rate+" %</td>");
                out.println("<td width=\"45%\" class='rep-body1' valign='top'>");
                out.println("<table align='center' width='100%' class='table' cellspacing='0' cellpadding='0'>");
                out.println("<tr><td width=\"3%\"></td><td width=\"2%\">8.</td><td width=\"*%\" class='rep-body1' align='left' >Valuers Name</td></tr>");
                
                
                rs = stmt.executeQuery(" SELECT NVL(UPPER(B.FIRST_NAME),'')||' '||NVL(UPPER(B.LAST_NAME),'')"+
                    " FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION A,"+m_schema_name+".AF_CO_MAS_VALUERS B "+
                    " WHERE A.VALUER_CODE = B.VALUER_CODE AND "+
                    " APPLICATION_NO=UPPER('"+m_application_no+"')");
                more=rs.next();
                if(more){		
                    out.println("<tr><td width=\"3%\"></td><td width=\"2%\">&nbsp;</td><td width=\"*%\" class='rep-body1' align='left' >"+rs.getString(1)+"</td></tr>");
                    more=rs.next();
                }
                else{
                    out.println("<tr><td width=\"3%\"></td><td width=\"2%\">&nbsp;</td><td width=\"*%\" class='rep-body1' align='left' >NILL</td></tr>");
                }
                rs.close();
                stmt.close();	
                
                out.println("</table>");
                
                out.println("</td></tr>");
                out.println("</table>");
                
                //out.println("<br><br>");
                out.println("<br><br>");
                out.println("<br><br>");
                out.println("<br><br>");
                
                //===================================================SCHEDULE II==================================================
                
                out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                out.println("<tr><td width=\"*%\" class='rep-body1' align='center' ><b>SCHEDULE II</b></td></tr>");
                out.println("</table>");
                out.println("<br>");
                out.println("<br>");
                out.println("<br>");
                //out.println("<br>");
                
                String m_eng_no=""; 
                String m_cha_no="";
                String m_reg_no="";
                String m_color ="";
                String m_descrp="";
                String m_vih_status="";
                String m_item_cat="";
                String m_extra_include="";
                
                /*	rs = stmt.executeQuery("SELECT NVL(A.ENGINE_NO,'-'), "+
                                            "NVL(A.CHASSIS_NO,'-'),NVL(A.REG_NO,'-'), "+
                                                                    "NVL(A.COLOUR,'-'), "+
                                                                    "A.MODEL_CODE|| '-' ||A.SUB_MODEL_CODE||','||NVL(B.DESCRIPTION,'') "+
                                                                    "FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_MAS_SUB_MODLE B "+
                                                                    "WHERE A.SUB_MODEL_CODE=B.SUB_CODE AND "+
                                                                    "APPLICATION_NO=UPPER('"+m_application_no+"')");*/
                stmt = conn.createStatement ();															
                
                //---Added by Chandana on 16/07/07 -------------//															
                rs = stmt.executeQuery(	"	SELECT  "+
                    " nvl(B.ENGINE_NO,'-'), "+
                    " nvl(B.CHASSIS_NO,'-'), "+
                    " NVL(B.REG_NO,'-'), "+
                    "NVL(B.COLOUR,'-'), "+
                    //" INITCAP(C.MAKE_DESC||' '||F.DESCRIPTION||' '||D.DESCRIPTION ||' '||H.DESCRIPTION) MODEL_DESC, "+ //comment by nuwan de silva on 12-12-2007
                    " INITCAP(C.MAKE_DESC||' '||D.DESCRIPTION||' '||H.DESCRIPTION) MODEL_DESC, "+ //added by nuwan de silva on 12-12-2007
                    " B.MODEL_CODE,"+								
                    " NVL(D.YEAR_OF_MANUFACTURE,''), "+
                    " INITCAP(H.DESCRIPTION) ITEM_SUB_CAT_DESC, "+
                    " C.MAKE_CODE, "+
                    " F.ITEM_SUB_CAT,"+
                    " UPPER(E.VENDOR_CODE),"+
                    " UPPER(E.BRANCH), "+
                    " INITCAP(G.NAME), "+
                    " DECODE(A.STATUS,'N','Brand-New','U','Used','R','Re-Conditioned'), "+
                    " "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(B.MODEL_CODE) ITEM_CAT, "+
                    " NVL(B.EXTRAS_INCLUDED,'NIL') "+
                    " FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS A, "+
                    " "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+
                    " "+m_schema_name+".AF_CO_MAS_MAKE C, "+
                    " "+m_schema_name+".AF_CO_MAS_SUB_MODLE D, "+
                    " "+m_schema_name+".AF_CO_MAS_VENDOR_LOCATION E, "+
                    " "+m_schema_name+".AF_CO_MAS_MODEL F , "+
                    " "+m_schema_name+".AF_CO_MAS_VENDORS G , "+
                    " "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY H "+
                    " WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
                    " A.ACTIVE_STATUS='Y' AND "+
                    " B.ACTIVE_STATUS='Y' AND "+
                    " A.APPLICATION_NO=UPPER('"+m_application_no+"') AND "+
                    " A.ASSET_ID=B.ASSET_ID AND "+
                    " C.MAKE_CODE=(SELECT "+
                    " MAKE_CODE "+
                    " FROM "+m_schema_name+".AF_CO_MAS_MODEL "+
                    " WHERE "+
                    " MODEL_CODE IN ( SELECT "+
                    " MODEL_CODE "+
                    " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
                    " WHERE INVOICE_NO=B.INVOICE_NO AND B.ACTIVE_STATUS='Y' "+
                    " )) AND "+
                    " D.SUB_CODE=B.SUB_MODEL_CODE AND "+
                    " UPPER(E.VENDOR_CODE) =UPPER(B.VENDOR_CODE) AND "+
                    " UPPER(G.VENDOR_CODE) =UPPER(B.VENDOR_CODE) AND "+
                    " UPPER(F.ITEM_SUB_CAT) =UPPER(H.ITEM_SUB_CAT) AND "+
                    " UPPER(E.BRANCH)=UPPER(B.BRANCH_ID) AND "+
                    " B.MODEL_CODE=F.MODEL_CODE ");															
                
                more=rs.next();
                
                while(more){
                    m_eng_no=rs.getString(1);
                    m_cha_no=rs.getString(2);
                    m_reg_no=rs.getString(3);
                    m_color =rs.getString(4);
                    m_descrp=rs.getString(5);
                    m_vih_status=rs.getString(14);
                    m_item_cat=rs.getString(15);
                    m_extra_include=rs.getString(16);	
                    
                    
                    /*	out.println("<table align='center' width='80%' class='table' cellspacing='0' cellpadding='0'>"); 
                        out.println("<tr><td width=\"*%\" class='rep-body1' align='left'>Description of the vehicle:&nbsp&nbsp"+m_descrp+"</td></tr>");
                        out.println("</table>");
                        out.println("<table align='center' width='80%' class='table' cellspacing='0' cellpadding='0'>"); 
                        out.println("<tr><td width=\"45%\" class='rep-body1' align='left'>Type of Body:&nbsp&nbsp"+m_color+"</td><td width=\"45%\" class='rep-body1' align='left'>Registration No:&nbsp&nbsp"+m_reg_no+"</td></tr>");
                        out.println("<tr><td width=\"45%\" class='rep-body1' align='left'>Engine No:&nbsp&nbsp"+m_eng_no+"</td><td width=\"45%\" class='rep-body1' align='left'>Chassis No:&nbsp&nbsp"+m_cha_no+"</td></tr>");
                        out.println("</table>");
                        
                        out.println("<table align='center' width='80%' class='table' cellspacing='0' cellpadding='0'>"); 
                        out.println("<tr><td width=\"*%\" class='rep-body1' align='left'>Equipment and Accessories:...................................</td></tr>");
                        out.println("<tr><td width=\"*%\" class='rep-body1' align='left'>Extras included:....................................................................................................</td></tr>");
                        out.println("<tr><td width=\"*%\" class='rep-body1' align='left'>........................................................................................................................</td></tr>");
                        out.println("<tr><td width=\"*%\" class='rep-body1' align='left'>........................................................................................................................</td></tr>");
                                    
                        out.println("<tr><td width=\"*%\" class='rep-body1' align='left'>This is a brand new/ unregistered/ second hand vehicle.</td></tr>");
                        out.println("</table>");
                    out.println("<br><br>");
                    */
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='1' bordercolor='black'>"); 
                    
                    
                    
                    if(m_item_cat.equals("VEHICLE")){
                        out.println("<tr>");
                        out.println("<td width=\"45%\" class='rep-body1' valign='top'>&nbsp;&nbsp;Description of the vehicle:</td>");
                        out.println("<td width=\"45%\" class='rep-body1' valign='top'>&nbsp;&nbsp;"+m_descrp+"</td>");
                        out.println("</tr>");
                        out.println("<tr>");
                        out.println("<td width=\"45%\" class='rep-body1' valign='top'>&nbsp;&nbsp;Registration No:</td>");
                        out.println("<td width=\"45%\" class='rep-body1' valign='top'>&nbsp;&nbsp;"+m_reg_no+"</td>");
                        out.println("</tr>");
                        out.println("<tr>");
                        out.println("<td width=\"45%\" class='rep-body1' valign='top'>&nbsp;&nbsp;Engine No:</td>");
                        out.println("<td width=\"45%\" class='rep-body1' valign='top'>&nbsp;&nbsp;"+m_eng_no+"</td>");
                        out.println("</tr>");
                        out.println("<tr>");
                        out.println("<td width=\"45%\" class='rep-body1' valign='top'>&nbsp;&nbsp;Chassis No:</td>");
                        out.println("<td width=\"45%\" class='rep-body1' valign='top'>&nbsp;&nbsp;"+m_cha_no+"</td>");
                        out.println("</tr>");
                        out.println("<tr>");
                        out.println("<td width=\"45%\" class='rep-body1' valign='top'>&nbsp;&nbsp;Extras included:</td>");
                        out.println("<td width=\"45%\" class='rep-body1' valign='top'>&nbsp;&nbsp;"+m_extra_include+"</td>"); //Added by Chandana on 10/09/2007 
                        out.println("</tr>");
                        //------ Added by Chandana on 03/08/2007 --------------- // 			
                        out.println("<tr>");
                        out.println("<td width=\"45%\" class='rep-body1' valign='top'>&nbsp;&nbsp;Status:</td>");
                        out.println("<td width=\"45%\" class='rep-body1' valign='top'>&nbsp;&nbsp;"+m_vih_status+"</td>");
                        out.println("</tr>");
                        //------- End on 03/08/2007 ------------//
                        
                    }
                    else if(m_item_cat.equals("EQUIPMENT")){
                        
                        out.println("<tr>");
                        out.println("<td width=\"45%\" class='rep-body1' valign='top'>&nbsp;&nbsp;Description of the Equipment:</td>");
                        out.println("<td width=\"45%\" class='rep-body1' valign='top'>&nbsp;&nbsp;"+m_descrp+"</td>");
                        out.println("</tr>");
                        
                        out.println("<tr>");
                        out.println("<td width=\"45%\" class='rep-body1' valign='top'>&nbsp;&nbsp;Serial No:</td>");
                        out.println("<td width=\"45%\" class='rep-body1' valign='top'>&nbsp;&nbsp;"+m_cha_no+"</td>");
                        out.println("</tr>");
                        out.println("<tr>");
                        out.println("<td width=\"45%\" class='rep-body1' valign='top'>&nbsp;&nbsp;Extras included:</td>");
                        out.println("<td width=\"45%\" class='rep-body1' valign='top'>&nbsp;&nbsp;"+m_extra_include+"</td>"); //Added by Chandana on 10/09/2007 
                        out.println("</tr>");
                        //------ Added by Chandana on 03/08/2007 --------------- // 			
                        out.println("<tr>");
                        out.println("<td width=\"45%\" class='rep-body1' valign='top'>&nbsp;&nbsp;Status:</td>");
                        out.println("<td width=\"45%\" class='rep-body1' valign='top'>&nbsp;&nbsp;"+m_vih_status+"</td>");
                        out.println("</tr>");
                        //------- End on 03/08/2007 ------------//
                    }
                    
                    
                    
                    out.println("</table>");
                    
                    more=rs.next();
                }
                
                rs.close();
                stmt.close();
                
                out.println("<table align='center' width='100%' class='table'><tr>"); 
                out.println("<td width='5%'>&nbsp;</td><td width='*%' align='left'><b>Second Hand Vehicle / Machinery / Equipment</td>"); 
                out.println("</tr>"); 
                out.println("</table>");	
                out.println("<br><br>");
                
                out.println("   <p style=\"page-break-after:always\"></p>"); 
                //------- Added by Chandana on 03/08/2007 -----------------//
                out.println("<table align='center' width='100%' class='table'><tr>"); 
                out.println("<td width=\"50%\" class='rep-body1' style='text-align:left'><b>Agreement No: "+m_finance_no+"</b></td>"); 
                out.println("<td width=\"50%\" class='rep-body1' style='text-align:right'><b>2 of 4</b></td>");
                out.println("</tr></table>");
                // ----------- End 03/08/2007 ---------------//
                out.println("<br><br>");
                //out.println("<br><br>");
                //out.println("<br><br>");
                //out.println("<br><br>");
                //out.println("<br>");
                //out.println("<br><br>");
                //out.println("<br><br>");
                //out.println("<br><br>");
                //===================================================SCHEDULE III=======================================================
                
                
                //	out.println("<style type=\"text/css\"><!--.style1 {font-family: kaputadotcom}--></style>");
                
                
                //	out.println("<span class=\"style1\">");
                //	out.println("<table align='center' width='90%'  cellspacing='0' cellpadding='0' >");  //class='table'
                //	out.println("<tr><td width=\"*%\" span class=\"style1\" >test for font</span></td></tr>"); //style='{font:Webdings; text-align:justify}'
                //  out.println("</table>");  // <INPUT STYLE="{color: black; font:bold  9pt Wingdings 3;} TYPE="TEXT" NAME="firstName" VALUE="" onblur=chek_name()>
                
                
                
                out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                out.println("<tr><td width=\"*%\" class='rep-body1' align='center' ><b>SCHEDULE III</b></td></tr>");
                out.println("</table>");
                
                out.println("<br>");
                out.println("<br>");
                out.println("<br>");
                out.println("<br>");
                
                data = "Summary of the <B>Lease Purchaser's</B> financial obligations under this Agreement "+
                    "relating to the vehicle particulars whereof are contained in the Schedule II hereinbefore appearing.";
                
                out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                out.println("<tr><td width=\"*%\" class='rep-body1' align='justify'>"+data+"</td></tr>");
                out.println("</table>");
                out.println("<br>");
                out.println("<br>"); 
                
                double m_int_paymnt=0;
                double m_cash_price=0;
                int m_period    =0;
                
                /*	rs = stmt.executeQuery(" SELECT  DISTINCT A.PRICING_NO,A.GROSS_AMOUNT,B.GRENTAL_AMOUNT,(A.PERIOD-1) PERIOD "+
                                            " FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING A, "+
                                                                    " "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT B "+	
                                                                    " WHERE A.APPLICATION_NO=UPPER('"+m_application_no+"') AND "+
                                                                    " A.APPLICATION_NO=B.APPLICATION_NO AND "+
                                                                    " B.INSTALLMENT_NO='0'	"); */
                
                /*
                rs = stmt.executeQuery(" SELECT  DISTINCT A.PRICING_NO,A.GROSS_AMOUNT,B.GRENTAL_AMOUNT,(A.PERIOD-1) PERIOD "+
                                                                " FROM LAKDL.AF_CO_PRO_APP_PRICING A, "+
                                                                " LAKDL.AF_CO_PRO_APP_INSTALLMENT B "+
                                                                " WHERE A.APPLICATION_NO=UPPER('"+m_application_no+"') AND "+
                                                                " A.APPLICATION_NO=B.APPLICATION_NO AND "+
                                                                " B.INSTALLMENT_NO='0' ");	
                                                                */
                stmt = conn.createStatement ();		
                
                //comment ns 25-06-2009---------
                /*rs = stmt.executeQuery(" SELECT  SUM(GROSS_AMOUNT),SUM(GRENTAL_AMOUNT),PERIOD "+
                                        " FROM (SELECT  DISTINCT A.PRICING_NO,A.GROSS_AMOUNT,B.GRENTAL_AMOUNT,(A.PERIOD-1) PERIOD "+
                                                                " FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING A, "+
                                                                " "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT B "+
                                                                " WHERE A.APPLICATION_NO=UPPER('"+m_application_no+"') AND "+
                                                                " A.APPLICATION_NO=B.APPLICATION_NO AND "+
                                                                " B.INSTALLMENT_NO='0') "+
                                                                " GROUP BY PERIOD ");		
                */												
                
                //added by ns 25-06-2009
                rs = stmt.executeQuery(" SELECT  SUM(GROSS_AMOUNT),SUM(GRENTAL_AMOUNT),PERIOD "+
                    " FROM (SELECT  DISTINCT A.PRICING_NO,A.GROSS_AMOUNT,B.GRENTAL_AMOUNT,(A.PERIOD-1) PERIOD "+
                    " FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING A , "+
                    " "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT B "+
                    " WHERE A.APPLICATION_NO = UPPER('"+m_application_no+"')  "+
                    " AND   A.APPLICATION_NO = B.APPLICATION_NO  "+
                    " AND   B.PRO_INVOICE_NO IN "	+
                    " (SELECT INVOICE_NO  "+
                    "        FROM  "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS  "+
                    " 				WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"')  "+
                    " 				AND   ACTIVE_STATUS='Y')	 "+
                    " AND   B.INSTALLMENT_NO='0') "+
                    " GROUP BY PERIOD ");		
                
                
                
                more=rs.next();						
                if(more){
                    m_cash_price=rs.getDouble(1);
                    m_int_paymnt=rs.getDouble(2);
                    m_period    =rs.getInt(3);
                }				
                
                rs.close();
                
                String m_pric_no ="";	
                double m_1st_rent=0;
                String m_rent_dd ="";
                
                /*
                rs = stmt.executeQuery(" SELECT PRICING_NO,GRENTAL_AMOUNT,TO_CHAR(RENTAL_DATE,'fmddth') "+
                                        " FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
                                                                " WHERE PRICING_NO =(SELECT PRICING_NO "+
                                                                " FROM "+m_schema_name+".AF_MK_PRO_PRICING "+
                                                                " WHERE APP_NO=UPPER('"+m_application_no+"')) AND "+
                                                                " INSTALLMENT_NO ='0' ");
            
                            more=rs.next();						
                    if(more){
                        m_pric_no =rs.getString(1);
                        m_1st_rent=rs.getDouble(2);
                        m_rent_dd =rs.getString(3);
                        }	*/				
                
                
                double m_insu_amt=0;
                double m_reg_or_other_fees_payable_amt = 0;
                
                //comment by ns 25-06-2009
                /*
                rs = stmt.executeQuery(" SELECT SUM(AMOUNT) "+
                                                " FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING_CHARGES "+
                                                                        " WHERE  SUB_CHAGE_CODE='INSURANCE' AND "+
                                                                        " PRICING_NO IN (SELECT PRICING_NO "+
                                                                        " FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
                                                                        " WHERE PRICING_NO IN(SELECT PRICING_NO "+
                                                                        " FROM "+m_schema_name+".AF_MK_PRO_PRICING "+
                                                                        " WHERE APP_NO=UPPER('"+m_application_no+"')) AND "+
                                                                        " INSTALLMENT_NO ='0') ");
                */
                
                //added by ns 25-06-2009			
                rs = stmt.executeQuery(" SELECT SUM(AMOUNT) "+
                    " FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING_CHARGES A, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
                    " WHERE A.APPLICATION_NO = UPPER('"+m_application_no+"')   "+
                    " AND   A.APPLICATION_NO = B.APPLICATION_NO "+
                    " AND   A.PRO_INVOICE_NO = B.INVOICE_NO "+
                    " AND   A.PRICING_NO     = B.PRICING_NO "+
                    " AND   B.ACTIVE_STATUS  = 'Y' "+
                    // Line Added By Samitha Kulatilaka On 2012-01-05
                    " AND   A.SUB_CHAGE_CODE = 'INSURANCE'  " +
                    //" AND   INSTALLMENT_NO   = '0' "+
                    " /*GROUP BY A.PRICING_NO*/ ");//, RENTAL_DATE
                
                more=rs.next();						
                if(more){
                    m_insu_amt = rs.getDouble(1);
                }
                rs.close();		
                
                // Added By Samitha Kulatilaka On 2012-01-05
                rs = stmt.executeQuery(" SELECT SUM(AMOUNT) "+
                    " FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING_CHARGES A, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
                    " WHERE A.APPLICATION_NO = UPPER('"+m_application_no+"')   "+
                    " AND   A.APPLICATION_NO = B.APPLICATION_NO "+
                    " AND   A.PRO_INVOICE_NO = B.INVOICE_NO "+
                    " AND   A.PRICING_NO     = B.PRICING_NO "+
                    " AND   B.ACTIVE_STATUS  = 'Y' "+
                    " AND   A.SUB_CHAGE_CODE IN ('STAMPDUTY', 'RMV')  " +
                    //" AND   INSTALLMENT_NO   = '0' "+
                    " /*GROUP BY A.PRICING_NO*/ ");//, RENTAL_DATE
                
                more=rs.next();						
                if(more){
                    m_reg_or_other_fees_payable_amt = rs.getDouble(1);
                }
                rs.close();		
                
                //comment by ns 25-06-2009
                /*
                rs = stmt.executeQuery(" SELECT PRICING_NO, SUM(GRENTAL_AMOUNT),TO_CHAR(RENTAL_DATE,'fmddth') RENTAL_DATE, TO_CHAR(RENTAL_DATE,'MONTH')||TO_CHAR(RENTAL_DATE,'YYYY') ST_RENTAL_DATE "+
                                        " FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
                                                                " WHERE PRICING_NO IN(SELECT PRICING_NO "+
                                                                " FROM "+m_schema_name+".AF_MK_PRO_PRICING "+
                                                                " WHERE APP_NO=UPPER('"+m_application_no+"')) AND "+
                                                                " INSTALLMENT_NO ='0' "+
                                                                " GROUP BY PRICING_NO, RENTAL_DATE ");
                */
                
                //added by ns 25-06-2009			
                rs = stmt.executeQuery(" SELECT A.PRICING_NO, SUM(GRENTAL_AMOUNT),TO_CHAR(RENTAL_DATE,'fmddth') RENTAL_DATE, TO_CHAR(RENTAL_DATE,'MONTH')||TO_CHAR(RENTAL_DATE,'YYYY') ST_RENTAL_DATE "+
                    " FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
                    " WHERE A.APPLICATION_NO = UPPER('"+m_application_no+"')   "+
                    " AND   A.APPLICATION_NO = B.APPLICATION_NO "+
                    " AND   A.PRO_INVOICE_NO = B.INVOICE_NO "+
                    " AND   A.PRICING_NO     = B.PRICING_NO "+
                    " AND   B.ACTIVE_STATUS  = 'Y' "+
                    // Line Commented And Added By Samitha Kulatilaka On 2012-01-05
                    // " AND   A.INSTALLMENT_NO   = '0' "+
                    " AND   A.INSTALLMENT_NO   = '1' "+
                    " GROUP BY A.PRICING_NO, RENTAL_DATE ");
                
                
                more=rs.next();
                
                String m_rent_day=""; 
                
                while(more){			
                    m_pric_no =rs.getString(1);
                    m_1st_rent=m_1st_rent + rs.getDouble(2);
                    m_rent_dd =rs.getString(3);
                    //m_rent_day=rs.getString(4); //Comment by Chandana on 06/08/2007			
                    more=rs.next();
                }
                
                //Added by Chandana on 06/08/2007 for get the commencing date
                rs = stmt.executeQuery(" SELECT  NVL(TO_CHAR(ACTIVATED_DATE,'Month')||TO_CHAR(ACTIVATED_DATE,'YYYY') ,' ') "+
                    " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
                    " WHERE ACTIVATED_DATE IS NOT NULL AND "+
                    " APPLICATION_NO=UPPER('"+m_application_no+"') ");	
                
                more=rs.next();
                if(more){
                    m_rent_day=rs.getString(1);
                }	
                //------------End by Chandana ---------------// 
                double m_2st_rent=0;		
                String m_rent_date=""; 
                double m_tot_rent=0;
                
                /*
                    rs = stmt.executeQuery(" SELECT PRICING_NO,GRENTAL_AMOUNT,TO_CHAR(RENTAL_DATE,'MONTH')||TO_CHAR(RENTAL_DATE,'YYYY') "+
                                            " FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
                                                                    " WHERE PRICING_NO =(SELECT PRICING_NO "+
                                                                    " FROM "+m_schema_name+".AF_MK_PRO_PRICING "+
                                                                    " WHERE APP_NO=UPPER('"+m_application_no+"')) AND "+
                                                                    " INSTALLMENT_NO ='1' "); */
                
                
                //comment ns 25-06-2009
                /*rs = stmt.executeQuery(" SELECT SUM(GRENTAL_AMOUNT),TO_CHAR(RENTAL_DATE,'MONTH')||TO_CHAR(RENTAL_DATE,'YYYY') RENTAL_DATE "+
                                        " FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
                                                                " WHERE PRICING_NO IN(SELECT PRICING_NO "+
                                                                " FROM "+m_schema_name+".AF_MK_PRO_PRICING "+
                                                                " WHERE APP_NO=UPPER('"+m_application_no+"')) AND "+
                                                                " INSTALLMENT_NO ='1' "+
                                                                " GROUP BY RENTAL_DATE ");		
                */
                
                
                //added ns 25-06-2009
                rs = stmt.executeQuery("  SELECT  "+
                    " SUM(GRENTAL_AMOUNT),TO_CHAR(RENTAL_DATE,'MONTH')||TO_CHAR(RENTAL_DATE,'YYYY') RENTAL_DATE "+
                    " FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
                    " WHERE A.APPLICATION_NO = UPPER('"+m_application_no+"')   "+
                    " AND   A.APPLICATION_NO = B.APPLICATION_NO "+
                    " AND   A.PRO_INVOICE_NO = B.INVOICE_NO "+
                    " AND   A.PRICING_NO     = B.PRICING_NO "+
                    " AND   B.ACTIVE_STATUS  = 'Y' "+
                    " AND   A.INSTALLMENT_NO   = '1' "+
                    " GROUP BY RENTAL_DATE ");		
                
                more=rs.next();						
                if(more){
                    m_2st_rent  =rs.getDouble(1);
                    m_rent_date =rs.getString(2);
                }						
                rs.close();
                //	stmt.close();
                
                //m_tot_rent = m_2st_rent*(m_period+1);			
                //m_tot_rent =m_1st_rent + m_2st_rent*(m_period);			 //modified by nwuan de silva 10-07-07
                
                
                //comment ns 25-06-2009
                /*rs2 = stmt2.executeQuery(" SELECT SUM(A.GRENTAL_AMOUNT) "+ //Added By Sandun on 24-02-2009
                                                            " FROM   "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A "+
                                                            " WHERE  A.APPLICATION_NO=UPPER('"+m_application_no+"') ");
                */
                
                
                //added by ns 25-06-2009
                rs2 = stmt2.executeQuery("  SELECT  "+
                    " SUM(A.GRENTAL_AMOUNT)  "+
                    " FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
                    " WHERE A.APPLICATION_NO=UPPER('"+m_application_no+"')   "+
                    " AND   A.APPLICATION_NO=B.APPLICATION_NO "+
                    " AND   A.PRO_INVOICE_NO=B.INVOICE_NO "+
                    " AND   A.PRICING_NO=B.PRICING_NO "+
                    " AND   B.ACTIVE_STATUS='Y' ");
                
                
                
                if(rs2.next()){
                    m_tot_rent = rs2.getDouble(1);
                }											
                stmt2.close();
                out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                /*out.println("<tr><td width=\"5%\"></td><td width=\"3%\">1.</td><td width=\"40%\" class='rep-body1' align='left'> Initial payment </td><td width=\"30%\" class='rep-body1' align='left'>-&nbspRs. NIL</td></tr>");m_hp_amount
                out.println("<tr><td width=\"5%\"></td><td width=\"3%\">2.</td><td width=\"40%\" class='rep-body1' align='left'> The cash price of the vehicle is</td><td width=\"30%\" class='rep-body1' align='left'>-&nbspRs."+nf.format(m_cash_price)+"</td></tr>");
                out.println("<tr><td width=\"5%\"></td><td width=\"3%\">3.</td><td width=\"40%\" class='rep-body1' align='left'> Installation chargers</td><td width=\"30%\" class='rep-body1' align='left'>-&nbspRs. NIL</td></tr>");
                out.println("<tr><td width=\"5%\"></td><td width=\"3%\"></td><td width=\"40%\" class='rep-body1' align='right'> Total&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</td><td width=\"30%\" class='rep-body1' align='left'>-&nbspRs."+nf.format(m_cash_price)+"</td></tr>");*/
                
                out.println("<tr><td width=\"5%\"></td><td width=\"3%\">1.</td><td width=\"40%\" class='rep-body1' align='left'> Initial payment </td><td width=\"30%\" class='rep-body1' align='left'>-&nbspRs. NIL</td></tr>");
                out.println("<tr><td width=\"5%\"></td><td width=\"3%\">2.</td><td width=\"40%\" class='rep-body1' align='left'> The cash price of the vehicle is</td><td width=\"30%\" class='rep-body1' align='left'>-&nbspRs."+nf.format(m_hp_amount)+"</td></tr>");
                out.println("<tr><td width=\"5%\"></td><td width=\"3%\">3.</td><td width=\"40%\" class='rep-body1' align='left'> Installation chargers</td><td width=\"30%\" class='rep-body1' align='left'>-&nbspRs. NIL</td></tr>");
                out.println("<tr><td width=\"5%\"></td><td width=\"3%\"></td><td width=\"40%\" class='rep-body1' align='right'> Total&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</td><td width=\"30%\" class='rep-body1' align='left'>-&nbspRs."+nf.format(m_hp_amount)+"</td></tr>");
                
                out.println("<tr><td width=\"5%\"></td><td width=\"3%\" valign='top'>4.</td><td width=\"40%\" class='rep-body1' align='left'> The Lease purchase price including statutory charges and deposit of RS is</td><td width=\"30%\" class='rep-body1' align='left'>-&nbspRs. "+nf.format(m_tot_rent)+"</td></tr>");
                out.println("<tr><td width=\"5%\"></td><td width=\"3%\" valign='top'>5.</td><td width=\"40%\" class='rep-body1' align='left'> The following amounts have been included in the Lease Purchase price</td><td width=\"30%\" class='rep-body1' align='left'>&nbsp</td></tr>");
                out.println("</table>");
                out.println("<br>");
                out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>");
                out.println("<tr><td width=\"5%\"></td><td width=\"3%\"></td><td width=\"3%\" valign='top'>a)</td><td width=\"36%\" class='rep-body1' align='left'>Freight chargers (expenses of delivery of the vehicle or any of them to or to the order of the <B>Lease Purchaser</B>)</td><td width=\"30%\" class='rep-body1' align='left' valign='bottom'>-&nbspRs. NIL</td></tr>");
                
                // If Clause Added By Samitha Kulatilaka On 2012-01-05
                // out.println("<tr><td width=\"5%\"></td><td width=\"3%\"></td><td width=\"3%\" valign='top'>b)</td><td width=\"36%\" class='rep-body1' align='left'>Registration or other fees payable under any law</td><td width=\"30%\" class='rep-body1' align='left'>-&nbspRs. NIL</td></tr>");
                if(m_reg_or_other_fees_payable_amt==0){
                    out.println("<tr><td width=\"5%\"></td><td width=\"3%\"></td><td width=\"3%\" valign='top'>b)</td><td width=\"36%\" class='rep-body1' align='left'>Registration or other fees payable under any law</td><td width=\"30%\" class='rep-body1' align='left'>-&nbspRs. NIL</td></tr>");
                }else{
                    out.println("<tr><td width=\"5%\"></td><td width=\"3%\"></td><td width=\"3%\" valign='top'>b)</td><td width=\"36%\" class='rep-body1' align='left'>Registration or other fees payable under any law</td><td width=\"30%\" class='rep-body1' align='left'>-&nbspRs. "+nf.format(m_reg_or_other_fees_payable_amt)+"</td></tr>");
                }
                
                if(m_insu_amt==0){
                    out.println("<tr><td width=\"5%\"></td><td width=\"3%\"></td><td width=\"3%\" valign='top'>c)</td><td width=\"36%\" class='rep-body1' align='left'>Insurance</td><td width=\"30%\" class='rep-body1' align='left'>-&nbspRs. NIL</td></tr>");
                }else{
                    out.println("<tr><td width=\"5%\"></td><td width=\"3%\"></td><td width=\"3%\" valign='top'>c)</td><td width=\"36%\" class='rep-body1' align='left'>Insurance</td><td width=\"30%\" class='rep-body1' align='left'>-&nbspRs. "+nf.format(m_insu_amt)+"</td></tr>");
                }
                
                out.println("<tr><td width=\"5%\"></td><td width=\"3%\"></td><td width=\"3%\" valign='top'>d)</td><td width=\"36%\" class='rep-body1' align='left'>Installation charges</td><td width=\"30%\" class='rep-body1' align='left'>-&nbspRs. NIL</td></tr>");
                out.println("<tr><td width=\"5%\"></td><td width=\"3%\"></td><td width=\"3%\" valign='top'>e)</td><td width=\"36%\" class='rep-body1' align='left'>Value Added Tax or other taxes</td><td width=\"30%\" class='rep-body1' align='left'>-&nbspRs. NIL</td></tr>");
                
                out.println("</table>");
                
                out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>");
                out.println("<tr><td width=\"5%\"></td><td width=\"3%\" valign='top'>6.</td><td width=\"70%\" class='rep-body1' align='left'>The installment payable and the interval at which such installments are payable are as follows:- </td></tr>");
                
                data="First monthly hiring rental of Rs "+nf.format(m_1st_rent)+" and the balance "+m_period+" monthly "+
                    "hiring rentals of Rs "+nf.format(m_2st_rent)+" each on or before the  "+m_rent_dd+" day of each month, "+
                    "commencing from the month of  "+m_rent_day+" aggregating to Rs"+nf.format(m_tot_rent)+""; 
                // m_rent_date
                out.println("<br>");
                
                //	out.println("<tr><td width=\"5%\"></td><td width=\"3%\" valign='top'></td><td width=\"70%\" class='rep-body1' align='left'>"+data+"</td></tr>");
                
                
                //===================================================================================================================
                
                //comment by ns 25-06-2009
                /*
                String sql_rent_new="   SELECT  "+
                                    " TO_NUMBER(INSTALLMENT_NO)+1 , "+//1
                                    " SUM(NET_RENTAL_AMOUNT),  "+//2
                                    " SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,  "+//3
                                    " SUM(GRENTAL_AMOUNT), "+//4
                                    " TO_CHAR(RENTAL_DATE,'MON-YYYY') "+//5
                                    " FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A  "+
                                    " WHERE  A.APPLICATION_NO=UPPER('"+m_application_no+"')  "+
                                    " GROUP BY   TO_NUMBER(INSTALLMENT_NO),RENTAL_DATE "+ //,RENTAL_DATE //A.PRICING_NO
                                    " ORDER BY TO_NUMBER(INSTALLMENT_NO) ";
                */						
                
                //added by ns 25-06-2009
                String	sql_rent_new="   SELECT  "+
                    " TO_NUMBER(INSTALLMENT_NO) +1 ,  "+
                    " SUM(NET_RENTAL_AMOUNT),   "+
                    " SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,   "+
                    " SUM(GRENTAL_AMOUNT)  "+
                    " ,TO_CHAR(RENTAL_DATE,'MON-YYYY') "+//5
                    " FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
                    " WHERE A.APPLICATION_NO=UPPER('"+m_application_no+"')   "+
                    " AND   A.APPLICATION_NO=B.APPLICATION_NO "+
                    " AND   A.PRO_INVOICE_NO=B.INVOICE_NO "+
                    " AND   A.PRICING_NO=B.PRICING_NO "+
                    " AND   B.ACTIVE_STATUS='Y' "+
                    " GROUP BY   TO_NUMBER(INSTALLMENT_NO) ,RENTAL_DATE "+
                    " ORDER BY TO_NUMBER(INSTALLMENT_NO)  ";
                
                
                int end=0;
                int start=0;
                String m_ins="";
                double m_rental_new=0;
                double m_vat_new=0;
                double m_gross_new=0;
                int count_period=0;
                String rental_start_date="";
                String rental_end_date="",m_no_of_mon="",m_no_of="";double m_tot = 0.0;
                
                rs_rental = stmt_rental.executeQuery(sql_rent_new);
                
                boolean more3 =rs_rental.next();
                int 	m_varialble=0;
                StringBuffer rental_data = new StringBuffer();
                if(more3)
                {
                    out.println("<table border='1' cellspacing='0' align='center' width='60%' bordercolor='black' class='table'>"); 		
                    out.println("<tr>");
                    out.println("    <td width='20%' class='rep-body1' ><b>Period</td>");
                    out.println("    <td width='20%' class='rep-body1' ><b>No of Rentals</td>");
                    out.println("    <td width='20%' class='rep-body1' ><b>Rental Amount</td>");
                    out.println("</tr>");
                    
                    
                    
                    m_rental_new=rs_rental.getDouble(2);
                    start=rs_rental.getInt(1);
                    rental_start_date=rs_rental.getString(5);
                    m_vat_new=rs_rental.getDouble(3);
                    m_gross_new=rs_rental.getDouble(4);
                    
                    while(more3) //START INSTALLMENT LOOP
                    {
                        if(m_rental_new!=rs_rental.getDouble(2))
                        {
                            //out.println("<table border='1' cellspacing='0' align='center' width='50%' bordercolor='black' class='table'>"); 		
                            //out.println("<tr>");
                            //out.println("    <td width='35%' class='rep-body1' ><b>&nbsp;</b></td>");
                            if(start==end){
                                //out.println("    <td width='20%' class='rep-body1' ><b>"+count_period+"  Months "+m_no_of_mon+"</b></td>");
                                //out.println("    <td width='*%' class='rep-body1' ><b>&nbsp;&nbsp;Rs."+nf.format(m_gross_new)+" - ("+rental_start_date+" - "+rental_start_date+")  </b></td>");
                                //rental_data.append("First monthly hiting rental of Rs. "+m_rental_new+" and the balance");
                                //m_varialble=1;
                                //out.println("<tr></tr>");
                                
                                out.println("<tr>");
                                out.println("    <td width='20%' class='rep-body1' ><b>"+rental_start_date+" - "+rental_start_date+"</td>");
                                out.println("    <td width='20%' class='rep-body1' ><b>"+count_period+"</td>");
                                out.println("    <td width='20%' class='rep-body1' ><b>Rs."+nf.format(m_gross_new)+"</td>");
                                out.println("</tr>");
                                
                            }
                            else{
                                //out.println("    <td width='20%' class='rep-body1' >"+count_period+" Months "+m_no_of_mon+"</b></td>");
                                //out.println("    <td width='*%' class='rep-body1' ><b>&nbsp;&nbsp;Rs."+nf.format(m_gross_new)+" - ("+count_period+" "+m_no_of+" ,"+rental_start_date+" - "+rental_end_date+")  </b></td>");
                                //rental_data.append(" "+count_period+" rental of Rs."+nf.format(m_gross_new)+" each on or before the  day of each month, commencing from the month of "+rental_start_date+" ");
                                
                                //out.println("<tr></tr>");
                                out.println("<tr>");
                                out.println("    <td width='20%' class='rep-body1' ><b>"+rental_start_date+" - "+rental_end_date+"</td>");
                                out.println("    <td width='20%' class='rep-body1' ><b>"+count_period+"</td>");
                                out.println("    <td width='20%' class='rep-body1' ><b>Rs.&nbsp;"+nf.format(m_gross_new)+"</td>");
                                out.println("</tr>");
                                
                            }
                            //	out.println("</table>");
                            start=rs_rental.getInt(1);		
                            rental_start_date=rs_rental.getString(5);
                            m_rental_new=rs_rental.getDouble(2);
                            m_vat_new=rs_rental.getDouble(3);
                            m_gross_new=rs_rental.getDouble(4);
                            count_period=0;
                            
                        }
                        m_tot = m_tot+m_gross_new	;	//----Added By Sandun on 24-11-2008
                        count_period=count_period+1;
                        end=rs_rental.getInt(1);
                        rental_end_date=rs_rental.getString(5);
                        
                        more3=rs_rental.next();
                        
                        if(!more3)
                        {
                            break;
                        }
                        
                    }
                    //out.println("<table border='1' cellspacing='0' align='center' width='60%'  bordercolor='black' class='table'>"); 		
                    //out.println("<tr>");
                    // out.println("    <td width='35%' class='rep-body1' ><b>&nbsp;</b></td>");
                    if(start==end){
                        //out.println("    <td width='20%' class='rep-body1' >"+count_period+" Months</b></td>");
                        //out.println("    <td width='*%' class='rep-body1' ><b>&nbsp;&nbsp;Rs."+nf.format(m_gross_new)+" - ("+count_period+" "+m_no_of_mon+" ,"+rental_start_date+" - "+rental_start_date+")  </b></td>");
                        //out.println("<tr></tr>");
                        
                        out.println("<tr>");
                        out.println("<td width='20%' class='rep-body1' ><b>"+rental_start_date+" - "+rental_start_date+"</td>");
                        out.println("<td width='20%' class='rep-body1' ><b>"+count_period+"</td>");
                        out.println("<td width='20%' class='rep-body1' ><b>Rs.&nbsp;"+nf.format(m_gross_new)+"</td>");
                        out.println("</tr>");
                        
                    }
                    else{
                        //out.println("    <td width='20%' class='rep-body1' >"+count_period+" Months</b></td>");
                        //out.println("    <td width='*%' class='rep-body1' ><b>&nbsp;&nbsp;Rs."+nf.format(m_gross_new)+" - ("+count_period+" "+m_no_of+" ,"+rental_start_date+" - "+rental_end_date+")  </b></td>");
                        //out.println("<tr></tr>");
                        out.println("<tr>");
                        out.println("    <td width=20%' class='rep-body1' ><b>"+rental_start_date+" - "+rental_end_date+"</td>");
                        out.println("    <td width='20%' class='rep-body1' ><b>"+count_period+"</td>");
                        out.println("    <td width='20%' class='rep-body1' ><b>Rs.&nbsp;"+nf.format(m_gross_new)+"</td>");
                        out.println("</tr>");
                        
                    }
                    out.println("</table>");
                    
                }
                
                data="The each Lease purchase rentals to be paid on or before  <b>"+m_rent_dd+"</b> day of each month, "+
                    "commencing from the month of  <b>"+m_rent_day+"</b> aggregating to Rs.&nbsp; <b>"+nf.format(m_tot)+"</b>"; //m_tot_rent
                
                out.println("<br>");
                out.println("<table border='0' cellspacing='0' align='center' width='80%'   class='table'>"); 		
                out.println("<tr>");
                out.println("    <td width=20%' class='rep-body1' >"+data+"</td>");
                out.println("</tr>");
                out.println("</table>");
                
                
                
                
                
                //===================================================================================================================
                
                if(m_entity_code.equals("INDIVIDUAL")){
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    
                    data="With the written consent of the owners the Lease Purchaser can assign his rights under this Agreement and the "+
                        "Owners may not unreasonably refuse their consent as provided in Section 10 of the said Act."; 
                    
                    out.println("<tr><td width=\"5%\"></td><td width=\"3%\" valign='top'>7)</td><td width=\"70%\" class='rep-body1' align='left'>"+data+"</td></tr>");
                    
                    data="The Lease Purchaser has the right at any time to complete purchase of the vehicle with a rebate in terms "+
                        "of Sec.7 of the said Act.";
                    
                    out.println("<tr><td width=\"5%\"></td><td width=\"3%\" valign='top'>8)</td><td width=\"70%\" class='rep-body1' align='left'>"+data+"</td></tr>");
                    
                    data="The Lease Purchaser has the right to terminate this Agreement at any time by returning the vehicle to the Owner "+
                        "at the Lease Purchaser's own expense subject to the provisions of Sec.8 of the said Act ";
                    
                    
                    out.println("<tr><td width=\"5%\"></td><td width=\"3%\" valign='top'>9)</td><td width=\"70%\" class='rep-body1' align='left'>"+data+"</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<br>"); 
                    
                    //data="AS WITNESS the hands of the parties the day and year aforesaid.";
                    //added by nuwan de silva on 05-09-07		
                    /*data="The agreement was signed the / common seal of the Lease Purchaser was placed and attested hereto and to another at Colombo "+
                            "on this "+m_agr_day+" day of "+m_agr_month+"    "; //modified by nuwan de silva on 22-11-2007
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"*%\" class='rep-body1' align='justify'>"+data+"</td></tr>");
                    out.println("</table>");
                out.println("<br>");
                    */
                    data="The contents of this Agreement have been explained to us and we understood by us and we have agreed to abide by its terms and conditions.";
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"*%\" class='rep-body1' align='justify'>"+data+"</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    data="ihw kW vgn~wQ sh @k`n~@qsQ @mm v`hn@y~ hQmWkr#vn~ vQsQn~ kQyv` a#py @w~r#m|kr qQ@mn~~ ap ey vth` gw~ bvtw~ ap vQsQn~ @myt ap@g~ aw~sn w#bQ@mn~ ihw s[hn~ kr#NR pQlQp#qWQmtw~, vgkWQm| x`rg#nWQmtw~ @myQn~ PkM vn~@nmE."; //
                    
                    
                    out.println("<style type=\"text/css\"><!--.style1 {font-family: kaputadotcom}--></style>");
                    out.println("<span class=\"style1\">");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' >"); 
                    out.println("<tr><td width=\"*%\" span class=\"style1\" text-align:justify><B>"+data+"</B></td></tr>"); //text-align:justify
                    out.println("</table>");
                    out.println("</span>");
                    out.println("<br>");	
                    
                    
                    out.println("<p style=\"page-break-after:always\"></p>");
                    out.println("<table align='center' width='100%' class='table'><tr>"); 
                    out.println("<td width=\"50%\" class='rep-body1' style='text-align:left'><b>Agreement No: "+m_finance_no+"</b></td>"); 
                    out.println("<td width=\"50%\" class='rep-body1' style='text-align:right'><b>3 of 4</b></td>");
                    out.println("</tr></table>");
                    out.println("<br><BR>");
                    
                    //data="The agreement was signed / the common seal of the Lease Purchaser was placed and attested hereto and to another at Colombo "+
                    //"on this "+m_agr_day+" day of "+m_agr_month+"    "; //modified by nuwan de silva on 22-11-2007
                    
                    data="The agreement was signed / the common seal of the Lease Purchaser was placed and attested hereto and to another at Colombo "+
                        "on this .................... day of ................................   "; //modified by Chandana on 29-11-2007
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"*%\" class='rep-body1' align='justify'>"+data+"</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    out.println("<br>");
                    //out.println("<br>");
                    //out.println("<br>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"20%\" class='rep-body1' align='left' valign='top'><B>Lease Purchaser's signature</B></td>");
                    out.println("<td width=\"25%\" class='rep-body1' align='left'><B>..................................................</B></td>");
                    out.println("<td width=\"20%\" class='rep-body1' align='right'><B>NIC No</B></td>");
                    out.println("<td width=\"25%\" class='rep-body1' align='left'><B>..........................................</B></td></tr>");
                    out.println("</table>");
                    out.println("<br>");	
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"*%\" class='rep-body1' align='left'>Witness to the signature of the Lease Purchaser:</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">1.</td><td width=\"41%\" class='rep-body1' align='left'>Signature ........................................................</td><td width=\"4%\">2.</td><td width=\"41%\" class='rep-body1' align='left'>Signature ........................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ...........................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ...........................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no ...........................................................</td><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no ...........................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    // out.println("   <p style=\"page-break-after:always\"></p>"); 
                    // out.println("<table align='center' width='100%' class='table'><tr>"); 
                    // out.println("<td width=\"50%\" class='rep-body1' style='text-align:left'><b>Agreement No: "+m_finance_no+"</b></td>"); 
                    // out.println("<td width=\"50%\" class='rep-body1' style='text-align:right'><b>3 of 3</b></td>");
                    // out.println("</tr></table>");
                    // out.println("<br>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"4%\"><b>&nbsp;</b></td><td width=\"41%\" class='rep-body1' align='left'><B>Guarantors signature ..............................</td><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'><B>NIC No .........................</b></td></tr>");
                    out.println("</table>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"*%\">Witness to the signature of the 1st Guarantor:</td></tr>");
                    out.println("</table>");
                    
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">1.</td><td width=\"41%\" class='rep-body1' align='left'>Signature .........................................................</td><td width=\"4%\">2.</td><td width=\"41%\" class='rep-body1' align='left'>Signature .........................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ............................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no .............................................................</td><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no .............................................................</td></tr>");
                    out.println("</table>");
                    
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"4%\"><b>&nbsp;</b></td><td width=\"41%\" class='rep-body1' align='left'><B>Guarantors signature ..............................</td><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'><B>NIC No .........................</b></td></tr>");
                    out.println("</table>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"*%\">Witness to the signature of the 2nd Guarantor:</td></tr>");
                    out.println("</table>");
                    
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">1.</td><td width=\"41%\" class='rep-body1' align='left'>Signature .........................................................</td><td width=\"4%\">2.</td><td width=\"41%\" class='rep-body1' align='left'>Signature .........................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ............................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no .............................................................</td><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no .............................................................</td></tr>");
                    out.println("</table>");
                    
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"4%\"><b>&nbsp;</b></td><td width=\"41%\" class='rep-body1' align='left'><B>Guarantors signature ..............................</td><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'><B>NIC No .........................</b></td></tr>");
                    out.println("</table>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"*%\">Witness to the signature of the 3rd Guarantor:</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">1.</td><td width=\"41%\" class='rep-body1' align='left'>Signature .........................................................</td><td width=\"4%\">2.</td><td width=\"41%\" class='rep-body1' align='left'>Signature .........................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ............................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no .............................................................</td><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no .............................................................</td></tr>");
                    out.println("</table>");
                    
                    out.println("   <p style=\"page-break-after:always\"></p>"); 
                    out.println("<table align='center' width='100%' class='table'><tr>"); 
                    out.println("<td width=\"50%\" class='rep-body1' style='text-align:left'><b>Agreement No: "+m_finance_no+"</b></td>"); 
                    out.println("<td width=\"50%\" class='rep-body1' style='text-align:right'><b>4 of 4</b></td>");
                    out.println("</tr></table>");
                    out.println("<br>");
                    
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"4%\">Authorized Signatory of the owner</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    
                    
                    
                    // out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"40%\" class='rep-body1' align='center'>........................................................</td><td width=\"4%\"></td><td width=\"40%\" class='rep-body1' align='center'>...................................................</td><td width=\"6%\"></td></tr>");
                    out.println("<tr><td width=\"40%\" class='rep-body1' align='center'><B>Authorized Signatory</B></td><td width=\"4%\"></td><td width=\"40%\" class='rep-body1' align='center'><B>Authorized Signatory</B></td><td width=\"6%\"></td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"*%\">Witness to the signature of the Authorized Signatory:</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">1.</td><td width=\"41%\" class='rep-body1' align='left'>Signature .........................................................</td><td width=\"4%\">2.</td><td width=\"41%\" class='rep-body1' align='left'>Signature .........................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ............................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no .............................................................</td><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no .............................................................</td></tr>");
                    out.println("</table>");
                    
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"*%\"><B>I/We acknowledge the receipt of the copy of the Lease Purchase Agreement.</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    /*
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"40%\" class='rep-body1' align='left'>Lease Purchaser &nbsp&nbsp  :..........................................</td><td width=\"4%\"></td><td width=\"40%\" class='rep-body1' align='left'>1. Guarantor ...................................</td><td width=\"6%\"></td></tr>");
                    out.println("</table>");
                out.println("<br>");
                out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"40%\" class='rep-body1' align='left'></td><td width=\"4%\"></td><td width=\"40%\" class='rep-body1' align='left'>2. Guarantor ...................................</td><td width=\"6%\"></td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"40%\" class='rep-body1' align='left'></td><td width=\"4%\"></td><td width=\"40%\" class='rep-body1' align='left'>3. Guarantor ...................................</td><td width=\"6%\"></td></tr>");
                    out.println("</table>");
                    */
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width='50%'>");
                    out.println("<table align='center' width='45%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"40%\" class='rep-body1' align='left'>Lease Purchaser </td><td width=\"6%\"></td></tr>");
                    out.println("<tr><td width=\"40%\" class='rep-body1' align='left'>&nbsp;</td><td width=\"6%\"></td></tr>");
                    out.println("<tr><td width=\"20%\" class='rep-body1' align='left'>................................................................<br> "+m_full_name+"</td><td width=\"6%\"></td></tr>");
                    out.println("</table>");
                    out.println("</td><td width='50%'>");
                    out.println("<table align='center' width='45%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"50%\" class='rep-body1' align='left'>Gurantor...................................................</td><td width=\"6%\"></td></tr>");
                    out.println("<tr><td width=\"40%\" class='rep-body1' align='left'>&nbsp;</td><td width=\"6%\"></td></tr>");
                    out.println("<tr><td width=\"40%\" class='rep-body1' align='left'>&nbsp;</td><td width=\"6%\"></td></tr>");
                    out.println("<tr><td width=\"50%\" class='rep-body1' align='left'>Gurantor...................................................</td><td width=\"6%\"></td></tr>");
                    out.println("</table>");
                    out.println("</td></tr></table>");
                    
                    
                    
                    
                }else if(m_entity_code.equals("SOLEPROPRI")){
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    data="With the written consent of the owners the Lease Purchaser can assign his rights under this Agreement and the "+
                        "Owners may not unreasonably refuse their consent as provided in Section 10 of the said Act."; 
                    
                    out.println("<tr><td width=\"5%\"></td><td width=\"3%\" valign='top'>7)</td><td width=\"70%\" class='rep-body1' align='left'>"+data+"</td></tr>");
                    
                    data="The Lease Purchaser has the right at any time to complete purchase of the vehicle with a rebate in terms "+
                        "of Sec.7 of the said Act.";
                    
                    out.println("<tr><td width=\"5%\"></td><td width=\"3%\" valign='top'>8)</td><td width=\"70%\" class='rep-body1' align='left'>"+data+"</td></tr>");
                    
                    data="The Lease Purchaser has the right to terminate this Agreement at any time by returning the vehicle to the Owner "+
                        "at the Lease Purchaser's own expense subject to the provisions of Sec.8 of the said Act ";
                    
                    
                    out.println("<tr><td width=\"5%\"></td><td width=\"3%\" valign='top'>9)</td><td width=\"70%\" class='rep-body1' align='left'>"+data+"</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<br>"); 
                    
                    //data="AS WITNESS the hands of the parties the day and year aforesaid.";
                    
                    /*			data="The agreement was signed the / common seal of the Lease Purchaser was placed and attested hereto and to another at Colombo "+
                            "on this "+m_agr_day+" day of "+m_agr_month+"    "; //modified by nuwan de silva on 22-11-2007
                                
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"*%\" class='rep-body1' align='justify'>"+data+"</td></tr>");
                    out.println("</table>");
                out.println("<br>");
                    */
                    
                    data="The contents of this Agreement have been explained to us and we understood by us and we have agreed to abide by its terms and conditions.";
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"*%\" class='rep-body1' align='justify'>"+data+"</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    //data="by; lS j.ka;s iy fldkafoais fuu jdykfha ys&ntilde;lrejka "+
                    //" &uacute;iska lshjd wemh f;re&iuml;lr &sect;fuka wm Mh jgyd .;a njg;a wm "+
                    //" &uacute;iska fuhg wmf.a w;aika ;e&icirc;fuka by; i|yka lrekq ms&lt;sme&sect;ug;a "+
                    //" j.lS&iuml;Ndr .ekSug;a fuhska tl. jkafkuq";
                    
                    
                    data="ihw kW vgn~wQ sh @k`n~@qsQ @mm v`hn@y~ hQmWkr#vn~ vQsQn~ kQyv` a#py @w~r#m|kr qQ@mn~~ ap ey vth` gw~ bvtw~ ap vQsQn~ @myt ap@g~ aw~sn w#bQ@mn~ ihw s[hn~ kr#NR pQlQp#qWQmtw~, vgkWQm| x`rg#nWQmtw~ @myQn~ PkM vn~@nmE."; //
                    
                    
                    out.println("<style type=\"text/css\"><!--.style1 {font-family: kaputadotcom}--></style>");
                    out.println("<span class=\"style1\">");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' >"); 
                    out.println("<tr><td width=\"*%\"  span class=\"style1\" text-align:justify><B>"+data+"</B></td></tr>"); //text-align:justify
                    out.println("</table>");
                    out.println("</span>");
                    out.println("<br>");	
                    
                    
                    out.println("<p style=\"page-break-after:always\"></p>");
                    out.println("<table align='center' width='100%' class='table'><tr>"); 
                    out.println("<td width=\"50%\" class='rep-body1' style='text-align:left'><b>Agreement No: "+m_finance_no+"</b></td>"); 
                    out.println("<td width=\"50%\" class='rep-body1' style='text-align:right'><b>3 of 4</b></td>");
                    out.println("</tr></table>");
                    out.println("<br><BR>");
                    
                    
                    //data="The agreement was signed / the common seal of the Lease Purchaser was placed and attested hereto and to another at Colombo "+
                    //			"on this "+m_agr_day+" day of "+m_agr_month+"    "; //modified by nuwan de silva on 22-11-2007	
                    
                    data="The agreement was signed / the common seal of the Lease Purchaser was placed and attested hereto and to another at Colombo "+
                        "on this .................... day of ................................   "; //modified by Chandana on 29-11-2007
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"*%\" class='rep-body1' align='justify'>"+data+"</td></tr>");
                    out.println("</table>");
                    
                    
                    out.println("<br>");
                    out.println("<br>");
                    out.println("<br>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"20%\" class='rep-body1' align='left' valign='top'><B>Lease Purchaser's signature</B></td>");
                    out.println("<td width=\"25%\" class='rep-body1' align='left'><B>..................................................</B></td>");
                    out.println("<td width=\"20%\" class='rep-body1' align='right'><B>NIC No</B></td>");
                    out.println("<td width=\"25%\" class='rep-body1' align='left'><B>..........................................</B></td></tr>");
                    out.println("<tr><td width=\"20%\" class='rep-body1' align='left'></td>");
                    out.println("<td width=\"25%\" class='rep-body1' align='center'><B>(Proprietor)</B></td>");
                    out.println("<td width=\"20%\" class='rep-body1' align='right'></td>");
                    out.println("<td width=\"25%\" class='rep-body1' align='left'></td></tr>");
                    out.println("</table>");
                    out.println("<br>");	
                    
                    out.println("<br>");	
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"*%\" class='rep-body1' align='left'>Witness to the signature of the Lease Purchaser:</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">1.</td><td width=\"41%\" class='rep-body1' align='left'>Signature ........................................................</td><td width=\"4%\">2.</td><td width=\"41%\" class='rep-body1' align='left'>Signature ........................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ...........................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ...........................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no ...........................................................</td><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no ...........................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    // out.println("   <p style=\"page-break-after:always\"></p>");
                    // out.println("<table align='center' width='100%' class='table'><tr>"); 
                    // out.println("<td width=\"50%\" class='rep-body1' style='text-align:left'><b>Agreement No: "+m_finance_no+"</b></td>"); 
                    // out.println("<td width=\"50%\" class='rep-body1' style='text-align:right'><b>3 of 3</b></td>");
                    // out.println("</tr></table>");
                    // out.println("<br><BR>");
                    
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"4%\"><b>&nbsp;</b></td><td width=\"41%\" class='rep-body1' align='left'><B>Guarantors signature ..............................</td><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'><B>NIC No .........................</b></td></tr>");
                    out.println("</table>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"*%\">Witness to the signature of the 1st Guarantor:</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">1.</td><td width=\"41%\" class='rep-body1' align='left'>Signature .........................................................</td><td width=\"4%\">2.</td><td width=\"41%\" class='rep-body1' align='left'>Signature .........................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ............................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no .............................................................</td><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no .............................................................</td></tr>");
                    out.println("</table>");
                    
                    
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"4%\"><b>&nbsp;</b></td><td width=\"41%\" class='rep-body1' align='left'><B>Guarantors signature ..............................</td><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'><B>NIC No .........................</b></td></tr>");
                    out.println("</table>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"*%\">Witness to the signature of the 2nd Guarantor:</td></tr>");
                    out.println("</table>");
                    
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">1.</td><td width=\"41%\" class='rep-body1' align='left'>Signature .........................................................</td><td width=\"4%\">2.</td><td width=\"41%\" class='rep-body1' align='left'>Signature .........................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ............................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no .............................................................</td><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no .............................................................</td></tr>");
                    out.println("</table>");
                    
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"4%\"><b>&nbsp;</b></td><td width=\"41%\" class='rep-body1' align='left'><B>Guarantors signature ..............................</td><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'><B>NIC No .........................</b></td></tr>");
                    out.println("</table>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"*%\">Witness to the signature of the 3rd Guarantor:</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">1.</td><td width=\"41%\" class='rep-body1' align='left'>Signature .........................................................</td><td width=\"4%\">2.</td><td width=\"41%\" class='rep-body1' align='left'>Signature .........................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ............................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no .............................................................</td><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no .............................................................</td></tr>");
                    out.println("</table>");
                    
                    out.println("<br>");
                    //out.println("<br>");
                    
                    out.println("   <p style=\"page-break-after:always\"></p>"); 
                    out.println("<table align='center' width='100%' class='table'><tr>"); 
                    out.println("<td width=\"50%\" class='rep-body1' style='text-align:left'><b>Agreement No: "+m_finance_no+"</b></td>"); 
                    out.println("<td width=\"50%\" class='rep-body1' style='text-align:right'><b>4 of 4</b></td>");
                    out.println("</tr></table>");
                    out.println("<br>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"40%\" class='rep-body1' align='center'>........................................................</td><td width=\"4%\"></td><td width=\"40%\" class='rep-body1' align='center'>...................................................</td><td width=\"6%\"></td></tr>");
                    out.println("<tr><td width=\"40%\" class='rep-body1' align='center'><B>Authorized Signatory</B></td><td width=\"4%\"></td><td width=\"40%\" class='rep-body1' align='center'><B>Authorized Signatory</B></td><td width=\"6%\"></td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"*%\">Witness to the signature of the Authorized Signatory:</td></tr>");
                    out.println("</table>");
                    
                    out.println("<br>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">1.</td><td width=\"41%\" class='rep-body1' align='left'>Signature .........................................................</td><td width=\"4%\">2.</td><td width=\"41%\" class='rep-body1' align='left'>Signature .........................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ............................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no .............................................................</td><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no .............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<br>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"*%\"><B>I/We acknowledge the receipt of the copy of the Lease Purchase Agreement.</td></tr>");
                    out.println("</table>");
                    
                    out.println("<br>");
                    out.println("<br>");
                    /*
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"40%\" class='rep-body1' align='left'>Lease Purchaser &nbsp&nbsp  :...............................................................</td><td width=\"4%\"></td><td width=\"40%\" class='rep-body1' align='left'>1. Guarantor ........................................................</td><td width=\"6%\"></td></tr>");
                    out.println("</table>");
                out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"40%\" class='rep-body1' align='center'>(Proprietor)</td><td width=\"4%\"></td><td width=\"40%\" class='rep-body1' align='left'></td><td width=\"6%\"></td></tr>");
                    out.println("</table>");
                out.println("<br>");			
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"40%\" class='rep-body1' align='left'></td><td width=\"4%\"></td><td width=\"40%\" class='rep-body1' align='left'>2. Guarantor ...................................</td><td width=\"6%\"></td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"40%\" class='rep-body1' align='left'></td><td width=\"4%\"></td><td width=\"40%\" class='rep-body1' align='left'>3. Guarantor ...................................</td><td width=\"6%\"></td></tr>");
                    out.println("</table>");
                    */
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width='50%'>");
                    out.println("<table align='center' width='45%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"40%\" class='rep-body1' align='left'>Lease Purchaser </td><td width=\"6%\"></td></tr>");
                    out.println("<tr><td width=\"40%\" class='rep-body1' align='left'>&nbsp;</td><td width=\"6%\"></td></tr>");
                    out.println("<tr><td width=\"20%\" class='rep-body1' align='left'>................................................................<br> "+m_full_name+"</td><td width=\"6%\"></td></tr>");
                    out.println("</table>");
                    out.println("</td><td width='50%'>");
                    out.println("<table align='center' width='45%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"50%\" class='rep-body1' align='left'>Gurantor...................................................</td><td width=\"6%\"></td></tr>");
                    out.println("<tr><td width=\"40%\" class='rep-body1' align='left'>&nbsp;</td><td width=\"6%\"></td></tr>");
                    out.println("<tr><td width=\"40%\" class='rep-body1' align='left'>&nbsp;</td><td width=\"6%\"></td></tr>");
                    out.println("<tr><td width=\"50%\" class='rep-body1' align='left'>Gurantor...................................................</td><td width=\"6%\"></td></tr>");
                    out.println("</table>");
                    out.println("</td></tr></table>");
                    
                    
                }else if(m_entity_code.equals("PARTNERS")){			
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    
                    data="With the written consent of the Owners the <b>Lease Purchaser</b> can assign  his rights under this Agreement and the "+
                        "Owners may not unreasonably refuse their consent as provided in Section 10 of the said Act. "; //"+m_entity_code+"
                    
                    out.println("<tr><td width=\"5%\"></td><td width=\"3%\" valign='top'>7)</td><td width=\"70%\" class='rep-body1' align='left'>"+data+"</td></tr>");
                    
                    data="The <b>Lease Purchaser</b> has the right at any time to complete purchase of the vehicle with a rebate in terms "+
                        "of Sec.7 of the said Act.";
                    
                    out.println("<tr><td width=\"5%\"></td><td width=\"3%\" valign='top'>8)</td><td width=\"70%\" class='rep-body1' align='left'>"+data+"</td></tr>");
                    
                    data="The <b>Lease Purchaser</b> has the right to terminate this Agreement at any time by returning the vehicle to the Owner "+
                        "at the <b>Lease Purchaser's</b> own expense subject to the provisions of Sec.8 of the said Act</b> ";
                    
                    
                    out.println("<tr><td width=\"5%\"></td><td width=\"3%\" valign='top'>9)</td><td width=\"70%\" class='rep-body1' align='left'>"+data+"</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<br>"); 
                    
                    //data="AS WITNESS the hands of the parties the day and year aforesaid.";
                    /*data="The agreement was signed the / common seal of the Lease Purchaser was placed and attested hereto and to another at Colombo "+
                            "on this "+m_agr_day+" day of "+m_agr_month+"    "; //modified by nuwan de silva on 22-11-2007
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"4%\"></td><td width=\"*%\" class='rep-body1' align='justify'>"+data+"</td></tr>");
                    out.println("</table>");
                out.println("<br>");
                    */
                    
                    data="The contents of this Agreement have been explained to us and we understood by us and we have agreed to abide by its terms and conditions.";
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"4%\"></td><td width=\"*%\" class='rep-body1' align='justify'>"+data+"</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    data="ihw kW vgn~wQ sh @k`n~@qsQ @mm v`hn@y~ hQmWkr#vn~ vQsQn~ kQyv` a#py @w~r#m|kr qQ@mn~~ ap ey vth` gw~ bvtw~ ap vQsQn~ @myt ap@g~ aw~sn w#bQ@mn~ ihw s[hn~ kr#NR pQlQp#qWQmtw~, vgkWQm| x`rg#nWQmtw~ @myQn~ PkM vn~@nmE."; //
                    
                    
                    
                    
                    out.println("<style type=\"text/css\"><!--.style1 {font-family: kaputadotcom}--></style>");
                    out.println("<span class=\"style1\">");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' >"); 
                    out.println("<tr><td width=\"*%\"  span class=\"style1\" text-align:justify><B>"+data+"</B></td></tr>"); //text-align:justify
                    out.println("</table>");
                    out.println("</span>");
                    out.println("<br>");	
                    
                    
                    out.println("<p style=\"page-break-after:always\"></p>");
                    out.println("<table align='center' width='100%' class='table'><tr>"); 
                    out.println("<td width=\"50%\" class='rep-body1' style='text-align:left'><b>Agreement No: "+m_finance_no+"</b></td>"); 
                    out.println("<td width=\"50%\" class='rep-body1' style='text-align:right'><b>3 of 4</b></td>");
                    out.println("</tr></table>");
                    out.println("<br><BR>");
                    
                    //data="The agreement was signed / the common seal of the Lease Purchaser was placed and attested hereto and to another at Colombo "+
                    //"on this "+m_agr_day+" day of "+m_agr_month+"    "; //modified by nuwan de silva on 22-11-2007	
                    
                    data="The agreement was signed / the common seal of the Lease Purchaser was placed and attested hereto and to another at Colombo "+
                        "on this .................... day of ................................   "; //modified by Chandana on 29-11-2007
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"*%\" class='rep-body1' align='justify'>"+data+"</td></tr>");
                    out.println("</table>");
                    
                    out.println("<br>");
                    out.println("<br>");
                    
                    
                    data="The common seal of Lease Purchaser's/ was affixed hereto in the presence of:  ";
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"*%\" class='rep-body1' align='justify'>"+data+"</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"20%\" class='rep-body1' align='left' valign='top'><B>Lease Purchaser's signature</B></td>");
                    out.println("<td width=\"25%\" class='rep-body1' align='left'><B>..................................................</B></td>");
                    out.println("<td width=\"20%\" class='rep-body1' align='left' valign='top'><B>Lease Purchaser's signature</B></td>");
                    out.println("<td width=\"25%\" class='rep-body1' align='left'><B>..........................................</B></td></tr>");
                    
                    out.println("<tr><td width=\"20%\" class='rep-body1' align='left'><B></B></td>");
                    out.println("<td width=\"25%\" class='rep-body1' align='center'><B>(Partner)</B></td>");
                    out.println("<td width=\"20%\" class='rep-body1' align='right'><B></B></td>");
                    out.println("<td width=\"25%\" class='rep-body1' align='center'><B>(Partner)</B></td></tr>");
                    out.println("</table>");
                    out.println("<br>");	
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"20%\" class='rep-body1' align='left'><B>Name</B></td>");
                    out.println("<td width=\"25%\" class='rep-body1' align='left'><B>..................................................</B></td>");
                    out.println("<td width=\"20%\" class='rep-body1' align='left'><B>Name</B></td>");
                    out.println("<td width=\"25%\" class='rep-body1' align='left'><B>..........................................</B></td></tr>");
                    out.println("</table>");
                    out.println("<br>");	
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"20%\" class='rep-body1' align='left'><B>NIC No</B></td>");
                    out.println("<td width=\"25%\" class='rep-body1' align='left'><B>..................................................</B></td>");
                    out.println("<td width=\"20%\" class='rep-body1' align='left'><B>NIC No</B></td>");
                    out.println("<td width=\"25%\" class='rep-body1' align='left'><B>..........................................</B></td></tr>");
                    out.println("</table>");
                    out.println("<br>");	
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"*%\" class='rep-body1' align='left'>Witness to the signature of the Lease Purchaser:</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">1.</td><td width=\"41%\" class='rep-body1' align='left'>Signature ........................................................</td><td width=\"4%\">2.</td><td width=\"41%\" class='rep-body1' align='left'>Signature ........................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ...........................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ...........................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no ...........................................................</td><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no ...........................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    //VVVVVVV	
                    // out.println("   <p style=\"page-break-after:always\"></p>");
                    // out.println("<table align='center' width='100%' class='table'><tr>"); 
                    // out.println("<td width=\"50%\" class='rep-body1' style='text-align:left'><b>Agreement No: "+m_finance_no+"</b></td>"); 
                    // out.println("<td width=\"50%\" class='rep-body1' style='text-align:right'><b>3 of 3</b></td>");
                    // out.println("</tr></table>");
                    // out.println("<br><BR>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"4%\"><b>&nbsp;</b></td><td width=\"41%\" class='rep-body1' align='left'><B>Guarantors signature ..............................</td><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'><B>NIC No .........................</b></td></tr>");
                    out.println("</table>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"*%\">Witness to the signature of the 1st Guarantor:</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">1.</td><td width=\"41%\" class='rep-body1' align='left'>Signature .........................................................</td><td width=\"4%\">2.</td><td width=\"41%\" class='rep-body1' align='left'>Signature .........................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ............................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no .............................................................</td><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no .............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"4%\"><b>&nbsp;</b></td><td width=\"41%\" class='rep-body1' align='left'><B>Guarantors signature ..............................</td><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'><B>NIC No .........................</b></td></tr>");
                    out.println("</table>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"*%\">Witness to the signature of the 2nd Guarantor:</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">1.</td><td width=\"41%\" class='rep-body1' align='left'>Signature .........................................................</td><td width=\"4%\">2.</td><td width=\"41%\" class='rep-body1' align='left'>Signature .........................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ............................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no .............................................................</td><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no .............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");  //bbbbbbbbbbbbbbbbbbbbbbbbbb
                    
                    out.println("<p style=\"page-break-after:always\"></p>");
                    out.println("<table align='center' width='100%' class='table'><tr>"); 
                    out.println("<td width=\"50%\" class='rep-body1' style='text-align:left'><b>Agreement No: "+m_finance_no+"</b></td>"); 
                    out.println("<td width=\"50%\" class='rep-body1' style='text-align:right'><b>4 of 4</b></td>");
                    out.println("</tr></table>");
                    out.println("<br><BR>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"4%\">Authorized Signatory of the owner</td></tr>");
                    out.println("</table>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"40%\" class='rep-body1' align='center'>........................................................</td><td width=\"4%\"></td><td width=\"40%\" class='rep-body1' align='center'>...................................................</td><td width=\"6%\"></td></tr>");
                    out.println("<tr><td width=\"40%\" class='rep-body1' align='center'><B>Authorized Signatory</B></td><td width=\"4%\"></td><td width=\"40%\" class='rep-body1' align='center'><B>Authorized Signatory</B></td><td width=\"6%\"></td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"*%\">Witness to the signature of the Authorized Signatory:</td></tr>");
                    out.println("</table>");
                    
                    out.println("<br>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">1.</td><td width=\"41%\" class='rep-body1' align='left'>Signature .........................................................</td><td width=\"4%\">2.</td><td width=\"41%\" class='rep-body1' align='left'>Signature .........................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ............................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no .............................................................</td><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no .............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<br>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"*%\"><B>I/We acknowledge the receipt of the copy of the Lease Purchase Agreement.</td></tr>");
                    out.println("</table>");
                    
                    out.println("<br>");
                    out.println("<br>");
                    /*
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"40%\" class='rep-body1' align='left'>Lease Purchaser &nbsp&nbsp  :.....................................................</td><td width=\"4%\"></td><td width=\"40%\" class='rep-body1' align='left'>1. Guarantor ................................................</td><td width=\"6%\"></td></tr>");
                out.println("<tr><td width=\"40%\" class='rep-body1' align='center'>(Partner)</td><td width=\"4%\"></td><td width=\"40%\" class='rep-body1' align='left'></td><td width=\"6%\"></td></tr>");
                    out.println("<tr><td width=\"40%\" class='rep-body1' align='center'></td><td width=\"4%\"></td><td width=\"40%\" class='rep-body1' align='left'>2. Guarantor ................................................</td><td width=\"6%\"></td></tr>");
                    out.println("</table>");
                    */
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width='50%'>");
                    out.println("<table align='center' width='45%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"40%\" class='rep-body1' align='left'>Lease Purchaser </td><td width=\"6%\"></td></tr>");
                    out.println("<tr><td width=\"40%\" class='rep-body1' align='left'>&nbsp;</td><td width=\"6%\"></td></tr>");
                    out.println("<tr><td width=\"20%\" class='rep-body1' align='left'>..................................<br> Partner</td><td width='20%'>&nbsp;</td><td width=\"20%\" class='rep-body1' align='left'>..................................<br> Partner</td><td width=\"6%\"></td></tr>");
                    out.println("</table>");
                    out.println("</td><td width='50%'>");
                    out.println("<table align='center' width='45%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"50%\" class='rep-body1' align='left'>Gurantor...................................................</td><td width=\"6%\"></td></tr>");
                    out.println("<tr><td width=\"40%\" class='rep-body1' align='left'>&nbsp;</td><td width=\"6%\"></td></tr>");
                    out.println("<tr><td width=\"40%\" class='rep-body1' align='left'>&nbsp;</td><td width=\"6%\"></td></tr>");
                    out.println("<tr><td width=\"50%\" class='rep-body1' align='left'>Gurantor...................................................</td><td width=\"6%\"></td></tr>");
                    out.println("</table>");
                    out.println("</td></tr></table>");
                    
                    
                    
                }
                
                else if((m_entity_code.equals("LIMITED"))||(m_entity_code.equals("PUBLIC"))){
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    data="With the written consent of the Owners the <b>Lease Purchaser</b> can assign  his rights under this Agreement and the "+
                        "Owners may not unreasonably refuse their consent as provided in Section 10 of the said Act."; //"+m_entity_code+"
                    
                    out.println("<tr><td width=\"5%\"></td><td width=\"3%\" valign='top'>7)</td><td width=\"70%\" class='rep-body1' align='left'>"+data+"</td></tr>");
                    
                    data="The <b>Lease Purchaser</b> has the right at any time to complete purchase of the vehicle with a rebate in terms "+
                        "of Sec.7 of the said Act.";
                    
                    out.println("<tr><td width=\"5%\"></td><td width=\"3%\" valign='top'>8)</td><td width=\"70%\" class='rep-body1' align='left'>"+data+"</td></tr>");
                    
                    data="The <b>Lease Purchaser</b> has the right to terminate this Agreement at any time by returning the vehicle to the Owner "+
                        "at the <b>Lease Purchaser's</b> own expense subject to the provisions of Sec.8 of the said Act</b> ";
                    
                    
                    out.println("<tr><td width=\"5%\"></td><td width=\"3%\" valign='top'>9)</td><td width=\"70%\" class='rep-body1' align='left'>"+data+"</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<br>"); 
                    
                    //data="AS WITNESS the hands of the parties the day and year aforesaid.";
                    /*data="The agreement was signed the / common seal of the Lease Purchaser was placed and attested hereto and to another at Colombo "+
                            "on this "+m_agr_day+" day of "+m_agr_month+"    "; //modified by nuwan de silva on 22-11-2007
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"4%\"></td><td width=\"*%\" class='rep-body1' align='justify'>"+data+"</td></tr>");
                    out.println("</table>");
                out.println("<br>");
                    */
                    
                    data="ihw kW vgn~wQ sh @k`n~@qsQ @mm v`hn@y~ hQmWkr#vn~ vQsQn~ kQyv` a#py @w~r#m|kr qQ@mn~~ ap ey vth` gw~ bvtw~ ap vQsQn~ @myt ap@g~ aw~sn w#bQ@mn~ ihw s[hn~ kr#NR pQlQp#qWQmtw~, vgkWQm| x`rg#nWQmtw~ @myQn~ PkM vn~@nmE."; //
                    
                    
                    out.println("<style type=\"text/css\"><!--.style1 {font-family: kaputadotcom}--></style>");
                    out.println("<span class=\"style1\">");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' >"); 
                    out.println("<tr><td width=\"*%\" span class=\"style1\" text-align:justify><B>"+data+"</B></td></tr>"); //text-align:justify
                    out.println("</table>");
                    out.println("</span>");
                    out.println("<br>");
                    
                    out.println("<p style=\"page-break-after:always\"></p>");
                    out.println("<table align='center' width='100%' class='table'><tr>"); 
                    out.println("<td width=\"50%\" class='rep-body1' style='text-align:left'><b>Agreement No: "+m_finance_no+"</b></td>"); 
                    out.println("<td width=\"50%\" class='rep-body1' style='text-align:right'><b>3 of 4</b></td>");
                    out.println("</tr></table>");
                    out.println("<br><BR>");
                    
                    
                    data="The contents of this Agreement have been explained to us and we understood by us and we have agreed to abide by its terms and conditions.";
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"4%\"></td><td width=\"*%\" class='rep-body1' align='justify'>"+data+"</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    /*data="ihw kW vgn~wQ sh @k`n~@qsQ @mm v`hn@y~ hQmWkr#vn~ vQsQn~ kQyv` a#py @w~r#m|kr qQ@mn~~ ap ey vth` gw~ bvtw~ ap vQsQn~ @myt ap@g~ aw~sn w#bQ@mn~ ihw s[hn~ kr#NR pQlQp#qWQmtw~, vgkWQm| x`rg#nWQmtw~ @myQn~ PkM vn~@nmE.";//
                    
                    
                    out.println("<style type=\"text/css\"><!--.style1 {font-family: kaputadotcom}--></style>");
                    out.println("<span class=\"style1\">");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' >"); 
                    out.println("<tr><td width=\"*%\"  span class=\"style1\" text-align:justify><B>"+data+"</B></td></tr>"); //text-align:justify
                    out.println("</table>");
                out.println("</span>");
                    */
                    
                    
                    
                    out.println("<br>");	
                    
                    //data="The agreement was signed / the common seal of the Lease Purchaser was placed and attested hereto and to another at Colombo "+
                    //"on this "+m_agr_day+" day of "+m_agr_month+"    "; //modified by nuwan de silva on 22-11-2007
                    
                    data="The agreement was signed / the common seal of the Lease Purchaser was placed and attested hereto and to another at Colombo "+
                        "on this .................... day of ................................   "; //modified by Chandana on 29-11-2007
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"*%\" class='rep-body1' align='justify'>"+data+"</td></tr>");
                    out.println("</table>");
                    
                    
                    out.println("<br>");
                    out.println("<br>");
                    
                    
                    data="The common seal of Lease Purchaser's/ was affixed hereto in the presence of:  ";
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"*%\" class='rep-body1' align='justify'>"+data+"</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"20%\" class='rep-body1' align='left' valign='top'><B>Lease Purchaser's signature</B></td>");
                    out.println("<td width=\"25%\" class='rep-body1' align='left'><B>..................................................</B></td>");
                    out.println("<td width=\"20%\" class='rep-body1' align='left' valign='top'><B>Lease Purchaser's signature</B></td>");
                    out.println("<td width=\"25%\" class='rep-body1' align='left'><B>..........................................</B></td></tr>");
                    
                    out.println("<tr><td width=\"20%\" class='rep-body1' align='left'><B></B></td>");
                    out.println("<td width=\"25%\" class='rep-body1' align='center'><B>(Director)</B></td>");
                    out.println("<td width=\"20%\" class='rep-body1' align='right'><B></B></td>");
                    out.println("<td width=\"25%\" class='rep-body1' align='center'><B>(Director)</B></td></tr>");
                    out.println("</table>");
                    out.println("<br>");	
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"20%\" class='rep-body1' align='left'><B>Name</B></td>");
                    out.println("<td width=\"25%\" class='rep-body1' align='left'><B>..................................................</B></td>");
                    out.println("<td width=\"20%\" class='rep-body1' align='left'><B>Name</B></td>");
                    out.println("<td width=\"25%\" class='rep-body1' align='left'><B>..........................................</B></td></tr>");
                    out.println("</table>");
                    out.println("<br>");	
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"20%\" class='rep-body1' align='left'><B>NIC No</B></td>");
                    out.println("<td width=\"25%\" class='rep-body1' align='left'><B>..................................................</B></td>");
                    out.println("<td width=\"20%\" class='rep-body1' align='left'><B>NIC No</B></td>");
                    out.println("<td width=\"25%\" class='rep-body1' align='left'><B>..........................................</B></td></tr>");
                    out.println("</table>");
                    out.println("<br>");	
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"*%\" class='rep-body1' align='left'>Witness to the signature of the Lease Purchaser:</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">1.</td><td width=\"41%\" class='rep-body1' align='left'>Signature ........................................................</td><td width=\"4%\">2.</td><td width=\"41%\" class='rep-body1' align='left'>Signature ........................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ...........................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ...........................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no ...........................................................</td><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no ...........................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    // out.println("   <p style=\"page-break-after:always\"></p>");
                    // out.println("<table align='center' width='100%' class='table'><tr>"); 
                    // out.println("<td width=\"50%\" class='rep-body1' style='text-align:left'><b>Agreement No: "+m_finance_no+"</b></td>"); 
                    // out.println("<td width=\"50%\" class='rep-body1' style='text-align:right'><b>3 of 3</b></td>");
                    // out.println("</tr></table>");
                    // out.println("<br><BR>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"4%\"><b>&nbsp;</b></td><td width=\"41%\" class='rep-body1' align='left'><B>Guarantors signature ..............................</td><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'><B>NIC No .........................</b></td></tr>");
                    out.println("</table>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"*%\">Witness to the signature of the 1st Guarantor:</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">1.</td><td width=\"41%\" class='rep-body1' align='left'>Signature .........................................................</td><td width=\"4%\">2.</td><td width=\"41%\" class='rep-body1' align='left'>Signature .........................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ............................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no .............................................................</td><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no .............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"4%\"><b>&nbsp;</b></td><td width=\"41%\" class='rep-body1' align='left'><B>Guarantors signature ..............................</td><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'><B>NIC No .........................</b></td></tr>");
                    out.println("</table>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"*%\">Witness to the signature of the 2nd Guarantor:</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">1.</td><td width=\"41%\" class='rep-body1' align='left'>Signature .........................................................</td><td width=\"4%\">2.</td><td width=\"41%\" class='rep-body1' align='left'>Signature .........................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ............................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no .............................................................</td><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no .............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");  //bbbbbbbbbbbbbbbbbbbbbbbbbb
                    
                    out.println("<p style=\"page-break-after:always\"></p>");
                    out.println("<table align='center' width='100%' class='table'><tr>"); 
                    out.println("<td width=\"50%\" class='rep-body1' style='text-align:left'><b>Agreement No: "+m_finance_no+"</b></td>"); 
                    out.println("<td width=\"50%\" class='rep-body1' style='text-align:right'><b>4 of 4</b></td>");
                    out.println("</tr></table>");
                    out.println("<br><BR>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"4%\">Authorized Signatory of the owner</td></tr>");
                    out.println("</table>");
                    
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"40%\" class='rep-body1' align='center'>........................................................</td><td width=\"4%\"></td><td width=\"40%\" class='rep-body1' align='center'>...................................................</td><td width=\"6%\"></td></tr>");
                    out.println("<tr><td width=\"40%\" class='rep-body1' align='center'><B>Authorized Signatory</B></td><td width=\"4%\"></td><td width=\"40%\" class='rep-body1' align='center'><B>Authorized Signatory</B></td><td width=\"6%\"></td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"*%\">Witness to the signature of the Authorized Signatory:</td></tr>");
                    out.println("</table>");
                    
                    out.println("<br>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">1.</td><td width=\"41%\" class='rep-body1' align='left'>Signature .........................................................</td><td width=\"4%\">2.</td><td width=\"41%\" class='rep-body1' align='left'>Signature .........................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ............................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no .............................................................</td><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no .............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<br>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"*%\"><B>I/We acknowledge the receipt of the copy of the Lease Purchase Agreement.</td></tr>");
                    out.println("</table>");
                    
                    out.println("<br>");
                    out.println("<br>");
                    /*
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"40%\" class='rep-body1' align='left'>Lease Purchaser &nbsp&nbsp  :.....................................................</td><td width=\"4%\"></td><td width=\"40%\" class='rep-body1' align='left'>1. Guarantor ................................................</td><td width=\"6%\"></td></tr>");
                out.println("<tr><td width=\"40%\" class='rep-body1' align='center'>(Director)</td><td width=\"4%\"></td><td width=\"40%\" class='rep-body1' align='left'></td><td width=\"6%\"></td></tr>");
                    out.println("<tr><td width=\"40%\" class='rep-body1' align='center'></td><td width=\"4%\"></td><td width=\"40%\" class='rep-body1' align='left'>2. Guarantor ................................................</td><td width=\"6%\"></td></tr>");
                    out.println("</table>");
                    */
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width='50%'>");
                    out.println("<table align='center' width='45%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"40%\" class='rep-body1' align='left'>Lease Purchaser </td><td width=\"6%\"></td></tr>");
                    out.println("<tr><td width=\"40%\" class='rep-body1' align='left'>&nbsp;</td><td width=\"6%\"></td></tr>");
                    out.println("<tr><td width=\"20%\" class='rep-body1' align='left'>..................................<br> Director</td><td width='20%'>&nbsp;</td><td width=\"20%\" class='rep-body1' align='left'>..................................<br> Director</td><td width=\"6%\"></td></tr>");
                    out.println("</table>");
                    out.println("</td><td width='50%'>");
                    out.println("<table align='center' width='45%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"50%\" class='rep-body1' align='left'>Gurantor...................................................</td><td width=\"6%\"></td></tr>");
                    out.println("<tr><td width=\"40%\" class='rep-body1' align='left'>&nbsp;</td><td width=\"6%\"></td></tr>");
                    out.println("<tr><td width=\"40%\" class='rep-body1' align='left'>&nbsp;</td><td width=\"6%\"></td></tr>");
                    out.println("<tr><td width=\"50%\" class='rep-body1' align='left'>Gurantor...................................................</td><td width=\"6%\"></td></tr>");
                    out.println("</table>");
                    out.println("</td></tr></table>");
                    
                    
                    
                }
                else{
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    
                    data="With the written consent of the Owners the <b>Lease Purchaser</b> can assign  his rights under this Agreement and the "+
                        "Owners may not unreasonably refuse their consent as provided in Section 10 of the said Act.";  //"+m_entity_code+"
                    
                    out.println("<tr><td width=\"5%\"></td><td width=\"3%\" valign='top'>7)</td><td width=\"70%\" class='rep-body1' align='left'>"+data+"</td></tr>");
                    
                    data="The <b>Lease Purchaser</b> has the right at any time to complete purchase of the vehicle with a rebate in terms "+
                        "of Sec.7 of the said Act.";
                    
                    out.println("<tr><td width=\"5%\"></td><td width=\"3%\" valign='top'>8)</td><td width=\"70%\" class='rep-body1' align='left'>"+data+"</td></tr>");
                    
                    data="The <b>Lease Purchaser</b> has the right to terminate this Agreement at any time by returning the vehicle to the Owner "+
                        "at the <b>Lease Purchaser's</b> own expense subject to the provisions of Sec.8 of the said Act</b> ";
                    
                    
                    out.println("<tr><td width=\"5%\"></td><td width=\"3%\" valign='top'>9)</td><td width=\"70%\" class='rep-body1' align='left'>"+data+"</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<br>"); 
                    
                    //data="AS WITNESS the hands of the parties the day and year aforesaid.";
                    /*data="The agreement was signed the / common seal of the Lease Purchaser was placed and attested hereto and to another at Colombo "+
                            "on this "+m_agr_day+" day of "+m_agr_month+"    "; //modified by nuwan de silva on 22-11-2007
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"4%\"></td><td width=\"*%\" class='rep-body1' align='justify'>"+data+"</td></tr>");
                    out.println("</table>");
                out.println("<br>");
                    */
                    
                    
                    data="The contents of this Agreement have been explained to us and we understood by us and we have agreed to abide by its terms and conditions.";
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"4%\"></td><td width=\"*%\" class='rep-body1' align='justify'>"+data+"</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    data="ihw kW vgn~wQ sh @k`n~@qsQ @mm v`hn@y~ hQmWkr#vn~ vQsQn~ kQyv` a#py @w~r#m|kr qQ@mn~~ ap ey vth` gw~ bvtw~ ap vQsQn~ @myt ap@g~ aw~sn w#bQ@mn~ ihw s[hn~ kr#NR pQlQp#qWQmtw~, vgkWQm| x`rg#nWQmtw~ @myQn~ PkM vn~@nmE."; //
                    
                    
                    
                    out.println("<style type=\"text/css\"><!--.style1 {font-family: kaputadotcom}--></style>");
                    out.println("<span class=\"style1\">");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' >"); 
                    out.println("<tr><td width=\"*%\"  span class=\"style1\" text-align:justify><B>"+data+"</B></td></tr>"); //text-align:justify
                    out.println("</table>");
                    out.println("</span>");
                    
                    
                    // out.println("<p style=\"page-break-after:always\"></p>");
                    // out.println("<table align='center' width='100%' class='table'><tr>"); 
                    // out.println("<td width=\"50%\" class='rep-body1' style='text-align:left'><b>Agreement No: "+m_finance_no+"</b></td>"); 
                    // out.println("<td width=\"50%\" class='rep-body1' style='text-align:right'><b>3 of 4</b></td>");
                    // out.println("</tr></table>");
                    // out.println("<br><BR>");
                    
                    
                    
                    out.println("<br>");	
                    
                    //data="The agreement was signed / the common seal of the Lease Purchaser was placed and attested hereto and to another at Colombo "+
                    //"on this "+m_agr_day+" day of "+m_agr_month+"    "; //modified by nuwan de silva on 22-11-2007
                    
                    data="The agreement was signed / the common seal of the Lease Purchaser was placed and attested hereto and to another at Colombo "+
                        "on this .................... day of ................................   "; //modified by Chandana on 29-11-2007
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"*%\" class='rep-body1' align='justify'>"+data+"</td></tr>");
                    out.println("</table>");
                    
                    out.println("<br>");
                    out.println("<br>");
                    
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"4%\"></td><td width=\"40%\" class='rep-body1' align='left'><b>Lease Purchaser's signature ......................................</b></td><td width=\"40%\" class='rep-body1' align='left'><b>Lease Purchaser's signature ......................................</b></td></tr>");
                    out.println("<tr><td width=\"4%\"></td><td width=\"40%\" class='rep-body1' align='left'><b>( Director )</b></td><td width=\"40%\" class='rep-body1' align='left'><b>( Director )</b></td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"4%\"></td><td width=\"40%\" class='rep-body1' align='left'><b>Name .................................................</b></td><td width=\"40%\" class='rep-body1' align='left'><b>Name .................................................</b></td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"4%\"></td><td width=\"40%\" class='rep-body1' align='left'><b>NIC No ...............................................</b></td><td width=\"40%\" class='rep-body1' align='left'><b>NIC No ...............................................</b></td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    
                    
                    
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"40%\" class='rep-body1' align='left'>Witness to the signature of the <b>Lease Purchaser's:</b></td><td width=\"40%\" class='rep-body1' align='left'><b></b> </td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">1.</td><td width=\"41%\" class='rep-body1' align='left'>Signature ........................................................</td><td width=\"4%\">2.</td><td width=\"41%\" class='rep-body1' align='left'>Signature ........................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ...........................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ...........................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no ...........................................................</td><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no ...........................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    
                    // out.println("<p style=\"page-break-after:always\"></p>");
                    // out.println("<table align='center' width='100%' class='table'><tr>"); 
                    // out.println("<td width=\"50%\" class='rep-body1' style='text-align:left'><b>Agreement No: "+m_finance_no+"</b></td>"); 
                    // out.println("<td width=\"50%\" class='rep-body1' style='text-align:right'><b>3 of 3</b></td>");
                    // out.println("</tr></table>");
                    // out.println("<br><BR>");
                    ///---------------------------------------------
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"2%\">&nbsp;</td><td width=\"40%\" class='rep-body1' align='left'>Guarantors signature  .......................................</td><td width=\"40%\" class='rep-body1' align='left'>NIC No...............................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"60%\" class='rep-body1' align='left'>Witness to the signature of 1st Guarantor:</td><td width=\"*%\" class='rep-body1' align='left'></td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">1.</td><td width=\"41%\" class='rep-body1' align='left'>Signature ........................................................</td><td width=\"4%\">2.</td><td width=\"41%\" class='rep-body1' align='left'>Signature ........................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ...........................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ...........................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no ...........................................................</td><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no ...........................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"2%\">&nbsp;</td><td width=\"40%\" class='rep-body1' align='left'>Guarantors signature  .......................................</td><td width=\"40%\" class='rep-body1' align='left'>NIC No...............................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"60%\" class='rep-body1' align='left'>Witness to the signature of 2nd Guarantor:</td><td width=\"*%\" class='rep-body1' align='left'></td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">1.</td><td width=\"41%\" class='rep-body1' align='left'>Signature ........................................................</td><td width=\"4%\">2.</td><td width=\"41%\" class='rep-body1' align='left'>Signature ........................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ...........................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ...........................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no ...........................................................</td><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no ...........................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    data="The common seal of the owners/ authorized signatory of the owner/ was affixed hereto in the presence of :"; 
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"*%\" class='rep-body1' align='left'>"+data+"</td></tr>");
                    out.println("</table>");
                    
                    out.println("<br>");
                    out.println("<br>");
                    out.println("<br>");
                    
                    //comment by nuwan de silva on 08-04-2008
                    /*out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                        out.println("<tr><td width=\"25%\" class='rep-body1' align='center'>......................................</td><td width=\"4%\"></td><td width=\"25%\" class='rep-body1' align='center'>......................................</td></tr>");
                        out.println("<tr><td width=\"25%\" class='rep-body1' align='center'>Director</td><td width=\"4%\"></td><td width=\"25%\" class='rep-body1' align='center'>Director</td></tr>");
                        out.println("</table>");
                        */
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"25%\" class='rep-body1' align='center'>......................................</td><td width=\"4%\"></td><td width=\"25%\" class='rep-body1' align='center'>......................................</td></tr>");
                    out.println("<tr><td width=\"25%\" class='rep-body1' align='center'>Authorized Signatory</td><td width=\"4%\"></td><td width=\"25%\" class='rep-body1' align='center'>Authorized Signatory</td></tr>");
                    out.println("</table>");
                    
                    out.println("<br>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"*%\" class='rep-body1' align='left'><b>Witness to the signature of the two directors</b></td></tr>");
                    out.println("</table>");
                    
                    out.println("<br>");
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">1.</td><td width=\"41%\" class='rep-body1' align='left'>Signature ........................................................</td><td width=\"4%\">2.</td><td width=\"41%\" class='rep-body1' align='left'>Signature ........................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Name ..............................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='0'>"); 
                    out.println("<tr><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ...........................................................</td><td width=\"4%\">&nbsp</td><td width=\"41%\" class='rep-body1' align='left'>Address ...........................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>");
                    out.println("<tr><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no ...........................................................</td><td width=\"4%\"></td><td width=\"41%\" class='rep-body1' align='left'>NIC no ...........................................................</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    
                    
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"*%\" class='rep-body1' align='left'>I/We acknowledge the receipt of the copy of the Lease Purchase Agreement.</td></tr>");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
                    out.println("<tr><td width=\"45%\" class='rep-body1' align='left'><b>Lease Purchaser  ...........................................................</b></td><td width=\"45%\" class='rep-body1' align='left'>1. Guarantor  ...........................................................</td></tr>");
                    out.println("<tr><td><br></td></tr>");
                    out.println("<tr><td><br></td></tr>");
                    out.println("<tr><td width=\"45%\" class='rep-body1' align='left'></td><td width=\"45%\" class='rep-body1' align='left'>2. Guarantor ...........................................................</td></tr>");
                    out.println("</table>");
                }
                
                out.println("<br>");
                out.println("<br>"); 
                out.println("<br>");
                
                
                
                out.println("</font></p>");												
                out.println("</form></body></html>");
            }else if(m_chksql.trim().equals("schedule2")){
                
            }else if(m_chksql.trim().equals("shedule3")){
                
            }
            
            
            out.flush();
        }
        catch (Exception ex) {
            try{out.println("Error:"+ex.toString());}catch(Exception e){}
        }
        finally{
            if(out!=null){try{out.close();  }catch(Exception e){}}
            if(rs!=null){try{rs.close();  }catch(Exception e){}}
            if(stmt!=null){try{stmt.close();  }catch(Exception e){}}
            if(conn!=null){try{conn.close();  }catch(Exception e){}}
        }
    }
}
