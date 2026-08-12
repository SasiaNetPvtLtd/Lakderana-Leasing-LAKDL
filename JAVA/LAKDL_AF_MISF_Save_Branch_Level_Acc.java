//Option Id is 4.0
//This File was created by SVA on 01-08-2006 
//Collection Save

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import sun.misc.BASE64Decoder;
import java.sql.*;

public class LAKDL_AF_MISF_Save_Branch_Level_Acc extends HttpServlet {
    
    Connection	conn            =null;
    ServletOutputStream out     =null;
    CallableStatement callstmt1 =null;
    CallableStatement callstmt12 =null;
    BufferedReader input        =null;
    String m_username           =null;
    String m_chksql,m_msg,m_url,m_scr_name,m_schema_name,m_pricing_no=null;
    String reqstr;
    String m_html_client_url;
    String m_servlet_client_url;
    String m_client_t3_port;
    String  m_client_code;
    String m_rec_no_1;
    String m_option_name;
    
    
    public void service(HttpServletRequest req, HttpServletResponse res)throws IOException
    {
        try {
            
            out    = res.getOutputStream();
            LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
            conn=m_sn_methods.met_user_validate(req);
            
            m_username 						= m_sn_methods.username;
            m_html_client_url 		= m_sn_methods.html_client_url;
            m_servlet_client_url	= m_sn_methods.servlet_client_url;
            m_client_t3_port			= m_sn_methods.client_t3_port; 
            m_schema_name					= m_sn_methods.schema_name.trim();
            String m_client_name  = m_sn_methods.client_name;
            String m_rec_no="";
            input  = new BufferedReader(new InputStreamReader(req.getInputStream()),20000);
            reqstr = input.readLine();
            //out.println(reqstr);
            
            out    = res.getOutputStream();
            
            conn.setAutoCommit(false);
            String m_date="";
            String m_date_to="";	
            m_scr_name = 	m_sn_methods.met_formdata(reqstr,"Hid_scr_name");
            m_date     = 	m_sn_methods.met_formdata(reqstr,"Hid_date");
            m_date_to  = 	m_sn_methods.met_formdata(reqstr,"Hid_date_to");
            //---------------------------------------------------------------------------------------------------			
            
            if (m_scr_name.trim().equals("AF_MISF_BRANCH_LEVEL_ACC")){
                
                synchronized (this){
                    
                    callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
                        "AF_CO_SAVE_BRANCH_LEVEL_ACC(:1,:2,:3);END;");
                    
                    callstmt1.setString(1,m_date);
                    callstmt1.setString(2,m_date_to);
                    callstmt1.setString(3,m_username);
                    callstmt1.execute();
                    
                    m_msg = "'Data Saved Successfully'";
                    //m_url = "AF_RE_Cancellation?chksql=main_page";
                    
                    conn.commit();
                }//synchronised
                
            }				
            
            
            out.println("<HTML><HEAD>");
            out.println("<SCRIPT language='JavaScript'>");
            out.println("function displaymsg() {");
            out.println("alert("+m_msg+");");
            //out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Receipt_Cancel_Document?chksql=main_page&receipt_no="+m_rec_no+"&print=TRUE\";"); 
            //out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=720,height=800,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=0');");
            //out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"'");
            out.println("window.close();");
            
            
            out.println("}</SCRIPT></HEAD>");
            out.println("<body onload='displaymsg()'></body>");
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
            //out.println("window.history.back();"); 
            out.println("}</SCRIPT></HEAD>");
            out.println("<body onload='displaymsg();'></body>");
            out.println("</html>");
            out.flush();
            
        }finally{
            try{conn.setAutoCommit(true);}catch(Exception e){}
            if(input     !=null){try{input.close();    }catch(Exception e){}}
            if(callstmt1 !=null){try{callstmt1.close();}catch(Exception e){}}
            if(conn      !=null){try{conn.close();     }catch(Exception e){}}
            if(out       !=null){try{out.close();      }catch(Exception e){}}
            
        }
    }
}

