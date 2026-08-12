//--
//SCREEN NAME	:SAVE PAYMENT DETAILS - NEW
//CREATED BY	:DELANJALI
//DATE/TIME		:
//NOTES				:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_PRO_CR_save_cheque_printing_details_new extends HttpServlet {
    
    Connection conn;	
    String m_msg,m_url;
    CallableStatement callstmt;
    String reqstr;
    ServletOutputStream out = null;
    Statement stmt,stmt1,stmt2;
    public ResultSet rs,rs1,rs2;
    
    public synchronized void  service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
        
        
        try {
            
            BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
            reqstr = input.readLine();   	
            out = res.getOutputStream();
            
            LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
            //************************************************************	
            conn =m_sn_methods.met_user_validate(req);
            //**************************************************************		
            String m_schema_name = m_sn_methods.schema_name.trim();
            String m_client_name = m_sn_methods.client_name.trim();
            String m_username = m_sn_methods.username;
            String m_html_client_url;
            String m_class_url;
            String m_class_name_save;
            String m_save_procedure_name;
            m_html_client_url=m_sn_methods.html_client_url;
            m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
            
            String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
            conn.setAutoCommit(false);
            m_msg = "'Information saved successfully'";
            m_url = m_class_url;
            stmt=conn.createStatement();
            
            
            String m_fschema_name=m_sn_methods.client_name.trim();
            String m_status = req.getParameter("chksql");//APPRO2.
            String m_chksql1 = req.getParameter("chksql2");///PRINT.
            int m_no=Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_count")); 
            
            
            String m_approv="";
            String m_date="";
            String m_date_dis="";
            String m_id="";
            int sel_stage=0;
            
            
            String m_sus_ref="";
            String m_my_screen="";
            
            if (m_chksql1.equals("PRINT")){
                m_my_screen="AF_CR_PRO_PAYMENT_CHEQUE_PRINT1";
            }
            else if(m_chksql1.equals("DISBURSE")){
                m_my_screen="AF_CR_PRO_PAYMENT_CHEQUE_DISBURSE1";
            }
            else if(m_chksql1.equals("CANCEL")){
                m_my_screen="AF_CR_PRO_PAYMENT_CHEQUE_CANCEL1";
            }
            
            rs= stmt.executeQuery(" SELECT POSITION "+
                " FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
                " WHERE upper(SCREEN_NAME)=UPPER('"+m_my_screen+"') ");
            while(rs.next()){
                sel_stage=rs.getInt(1);
            }
            
            
            
            if(m_chksql1.equals("PRINT")){
                m_approv=m_chksql1;
            }
            
            else if(m_chksql1.equals("DISBURSE")){
                m_approv="DISBRS";
            }
            else if(m_chksql1.equals("CANCEL")){
                m_approv="APPRO2";
            }
            
            
            
            String m_letter_date="";
            //-----------------------------------------------------------------------------------------------------------------------------------
            if (m_chksql1.equals("PRINT") || m_chksql1.equals("CANCEL")){	
                
                Bulk_Printing_Main m_Print_cheques=new Bulk_Printing_Main();
                
                int mm_page_count=0;
                
                String[] m_temp_list=new String[100];
                String m_printer_name=m_sn_methods.met_formdata(reqstr,"TXT_PRINTER_NAME");
                
                for (int k=0; k<m_no; k++){
                    String m_app=m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO_"+k);
                    String m_pay_no=m_sn_methods.met_formdata(reqstr,"TXT_PAYMENT_NO_"+k);
                    String m_chk=m_sn_methods.met_formdata(reqstr,"CHK_APP_"+k);
                    String m_cheque_no=m_sn_methods.met_formdata(reqstr,"TXT_CHEQUE_NO_"+k);
                    
                    if(m_chk.trim().equals("Y")){
                        String m_payment_no=m_sn_methods.met_formdata(reqstr,"TXT_PAYMENT_NO_"+k);
                        
                        callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_CR_SAVE_CHEQUE_NEW(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");
                        callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_PAYMENT_NO_"+k));
                        callstmt.setInt(2,sel_stage);
                        callstmt.setString(3,m_chksql1);
                        callstmt.setString(4,m_approv);
                        callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
                        callstmt.setString(6,m_username);
                        callstmt.setString(7,m_my_screen);
                        callstmt.setString(8,Integer.toString(k));	
                        callstmt.setString(9,m_cheque_no);
                        
                        if(m_pay_no.trim().equals("")) {
                            break;
                        }
                        callstmt.execute();
                        
                        m_temp_list[mm_page_count]=m_payment_no;//m_payment_no
                        mm_page_count++;
                        
                        //int m_cknum=chq_print.printing_interface_1(m_pay_no,"FACT_PR");
                        //out.println("m_payment_no="+m_pay_no+"  m_cknum="+m_cknum);
                        //------------------------------------------------------
                    }
                }
                
                String[] m_paycount=new String[mm_page_count];
                for (int j=0;j<m_paycount.length;j++){
                    m_paycount[j]=m_temp_list[j];
                    //out.println("m_printer_name="+m_temp_list[j]);
                }
                
                //cheques printing goes here
                if(mm_page_count>0){
                    //out.println("------------------");
                    //out.println("m_printer_name="+m_printer_name);
                    //int mm=m_Print_cheques.printing_interface_8(m_paycount,m_printer_name);	
                    //out.println("mm="+mm);
                    //out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_PRO_CR_display_cheque_printing_details_new");
                    
                }
            }		
            
            //-----------------------------------------------------------------------------------------------------------------------------------
            if (m_chksql1.equals("DISBURSE")){	
                
                for (int j=0; j<m_no; j++){
                    String m_chk1=m_sn_methods.met_formdata(reqstr,"CHK_APP_"+j);
                    
                    
                    
                    if(m_chk1.trim().equals("Y")){
                        String m_pay_no1=m_sn_methods.met_formdata(reqstr,"TXT_PAYMENT_NO_"+j);
                        
                        m_date=(m_sn_methods.met_formdata(reqstr,"TXT_POST_DATE_DD_"+j)+'-'+m_sn_methods.met_formdata(reqstr,"TXT_POST_DATE_MM_"+j)+'-'+m_sn_methods.met_formdata(reqstr,"TXT_POST_DATE_YY_"+j));
                        
                        if(m_sn_methods.met_formdata(reqstr,"TXT_POST_DATE_DD_"+j).equals("") || m_sn_methods.met_formdata(reqstr,"TXT_POST_DATE_MM_"+j).equals("") || m_sn_methods.met_formdata(reqstr,"TXT_POST_DATE_YY_"+j).equals("")){
                            m_date="";
                        }
                        
                        m_date_dis=(m_sn_methods.met_formdata(reqstr,"TXT_DIS_DATE_DD_"+j)+'-'+m_sn_methods.met_formdata(reqstr,"TXT_DIS_DATE_MM_"+j)+'-'+m_sn_methods.met_formdata(reqstr,"TXT_DIS_DATE_YY_"+j));
                        
                        if(m_sn_methods.met_formdata(reqstr,"TXT_DIS_DATE_DD_"+j).equals("") || m_sn_methods.met_formdata(reqstr,"TXT_DIS_DATE_MM_"+j).equals("") || m_sn_methods.met_formdata(reqstr,"TXT_DIS_DATE_YY_"+j).equals("")){
                            m_date_dis="";
                        }	
                        
                        
                        
                        callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_PRO_SAVE_PAY_DIS_SET_NEW(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15); END;");
                        callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_PAYMENT_NO_"+j));
                        callstmt.setString(2,"DISBRS");
                        callstmt.setString(3,m_date);
                        callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_DIS_BY_"+j));
                        callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_DIS_TO_"+j));
                        callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_ID_NO_"+j));
                        callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_CHEQUE_NO_"+j));
                        callstmt.setString(8,m_username);
                        callstmt.setString(9,Integer.toString(j));			
                        callstmt.setString(10,"DISBURSE");
                        callstmt.setString(11,m_my_screen);
                        callstmt.setInt(12,sel_stage);
                        callstmt.setString(13,"Vendor Payment Auto generated");
                        callstmt.setString(14,m_sn_methods.met_formdata(reqstr,"TXT_REF_NO_"+j));
                        callstmt.setString(15,m_date_dis);
                        
                        callstmt.execute();
                        
                        if  (m_pay_no1.trim().equals("")) {
                            
                            break;
                        }
                        
                    }
                    
                }
                
            }
            //--------------------------------------------------------------------------------------------
            
            callstmt.close();
            
            conn.commit();
            
            
            
            out.println("<HTML><HEAD>");
            out.println("<SCRIPT language='JavaScript'>");
            out.println("function displaymsg() {");
            out.println("alert("+m_msg+");");
            if (m_chksql1.equals("PRINT") || m_chksql1.equals("CANCEL")){	
                out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_PRO_CR_display_cheque_printing_details_new?screen=main_page&chksql="+m_status+"&chksql2="+m_chksql1+"';");
            }
            if (m_chksql1.equals("DISBURSE")){	
                out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_PRO_CR_display_cheque_printing_details_dis_new?screen=main_page&chksql="+m_status+"&chksql2="+m_chksql1+"';");
            }
            out.println("}</SCRIPT></HEAD>");
            out.println("<body onload='displaymsg();'></body>");
            out.println("</html>");
            
            
            out.flush();
            
        }
        
        
        catch (Exception E) {
            try{conn.rollback();}catch(Exception e){}
            out.println("ERROR:"+E.toString());
            out.println("<HTML><HEAD>");
            out.println("<SCRIPT language='JavaScript'>");
            out.println("function displaymsg() {");
            out.println("alert('Error when Saving');");
            out.println("window.history.back();"); 
            out.println("}</SCRIPT></HEAD>");
            out.println("<body onload='displaymsg();'></body>");
            out.println("</html>");
            out.flush();
            
        }
        finally{
            try{conn.setAutoCommit(true);}catch(Exception e){}
            //if(input     !=null){try{input.close();    }catch(Exception e){}}
            //if(callstmt1 !=null){try{callstmt1.close();}catch(Exception e){}}
            if(conn!=null){try{conn.close(); }catch(Exception e){}}
            if(out!=null){try{out.close();  }catch(Exception e){}}
        }
        
    }
}


