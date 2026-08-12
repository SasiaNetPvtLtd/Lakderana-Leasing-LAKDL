
//--
//SCREEN NAME:Display Team Hierarchy
//CREATED BY:Disnaka Jayasuriya
//DATE:2010-04-05
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_FA_Dispaly_Team_hierarchy extends javax.servlet.http.HttpServlet { 
   
   Connection conn;
   Statement stmt,stmt1,stmt2,stmt3,stmt4,stmt5;
   public ResultSet rs,rs1,rs2,rs3,rs4,rs5;
   java.text.NumberFormat nf;
   
   public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
      
      try { 
         
         nf = java.text.NumberFormat.getInstance(Locale.US);
         nf.setMinimumFractionDigits(2);
         ServletOutputStream out = res.getOutputStream(); 
		 
		 /**
         HttpSession session = req.getSession(true);COMFAC_AF_CO_conn_methods m_init_methods = new COMFAC_AF_CO_conn_methods(); String html_client_home_url=m_init_methods.html_client_home_url.trim();
         String m_screen_url = HttpUtils.getRequestURL(req).toString();
         
         Object done = (String)session.getValue("logon.isDone");  // marker object
         if (done == null) {
            String m_target_path = m_screen_url;
            session.putValue("login.target",m_target_path);
            out.println("<html><head>");
            out.println("<script language='JavaScript'>");
            out.println("function displaymsg() {");
            out.println("alert('Your login expired or invalid login');");
            out.println("window.location.href='"+html_client_home_url+"/login.htm' ;");
            out.println("}</script></head>");
            out.println("<body onload='displaymsg();'></body>");
            out.println("</html>");
            out.flush();
            return;
         }
         COMFAC_AF_CO_conn_methods m_sn_methods = new COMFAC_AF_CO_conn_methods(session); 
         **/
		 
         LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
         String m_html_client_url=m_sn_methods.html_client_url.trim(); 
         String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
         //String m_html_client_home_url=m_sn_methods.html_client_home_url.trim(); 
         conn = m_sn_methods.met_user_validate(req); 
         String m_username=m_sn_methods.username;
         String m_client_name=m_sn_methods.client_name;
         String m_schema_name=m_sn_methods.schema_name;
				 //out.println("conn"+conn);
         
         stmt = conn.createStatement();
         stmt1= conn.createStatement();
         stmt2= conn.createStatement();
         stmt3= conn.createStatement();
         stmt4= conn.createStatement();
         stmt5= conn.createStatement();
         String m_color="#FFFFFF";
         
         res.setStatus(HttpServletResponse.SC_OK); 
         res.setContentType("text/html"); 
         //out.println("CONN"+conn);
         //out.println("m_schema_name"+m_schema_name);
         
         //ServletOutputStream out = res.getOutputStream(); 
         //out.println(""+conn);
         out.println("<HTML><HEAD><TITLE>Asset Financing System</TITLE>");
         
         
         
         
         //********************************* END ********************************
         
         
         
         out.println("<BODY size='100%' bottomMargin=0 leftMargin=0 topMargin=0 rightMargin=0>");
         out.println("<script>");
         out.println("var tmenuItems =");
         out.println("[");
         
         out.println("[\"+<span style='color:#FFFFFF'>Team Hierarchy</span>\", \"\", \"\",\"\",\"\", \"\",,\"1\"],");
         
         rs=stmt.executeQuery(" SELECT TEAM_ID,INITCAP(TEAM_DESC) "+
            " FROM   "+m_schema_name+".AF_CO_MAS_TEAMS "+
						" WHERE  ACTIVE_STATUS='Y' ");
            //" WHERE TEAM_ID NOT IN (SELECT SUB_TEAM_ID FROM "+m_schema_name+".AF_CO_MAS_SUB_TEAMS_ASSIGN) AND ACTIVE_STATUS='Y' ");
         
         while(rs.next()){
            //m_color = "#"+rs.getString(3);
            //out.println("[\"|<span style='color:red'>Dis</span>"+rs.getString(2)+"\", \"\",,, \"\",\""+rs.getString(2)+"\"],");
            out.println("[\"|+<span style='position:relative;left:0px;top:-2px;border:1px solid black;font-size:4pt;background-color:"+m_color+"'>&nbsp;&nbsp;&nbsp;</span> <span>&nbsp;</span><span style='font-weight:bold'>"+rs.getString(2)+"</span>\", \"\",\""+m_html_client_url+"/img/team.gif\",, \"\",\""+rs.getString(2)+"\"],");
            //out.println(""+rs.getString(1));
           
            rs1=stmt1.executeQuery(" SELECT DISTINCT A.SUB_TEAM_ID,'SUBTEAM' TEAM "+
               " FROM "+m_schema_name+".AF_CO_MAS_SUB_TEAMS A "+
        			 " WHERE A.ACTIVE_STATUS='Y'"+
							 " AND   A.SUB_TEAM_ID IN ( SELECT SUB_TEAM_ID FROM "+m_schema_name+".AF_CO_MAS_SUB_TEAMS_ASSIGN  WHERE TEAM_ID='"+rs.getString(1)+"' AND ACTIVE_STATUS='Y' )"+
               " ");
            while(rs1.next()){
               
               
               
               
               if (rs1.getString(2).equals("SUBTEAM")){
                  
                  
                  
                  //out.println("-"+rs1.getString(1));
                  
                  rs2=stmt2.executeQuery(" SELECT SUB_TEAM_ID,INITCAP(SUB_TEAM_DESC) FROM "+m_schema_name+".AF_CO_MAS_SUB_TEAMS "+ 
                     " WHERE SUB_TEAM_ID='"+rs1.getString(1)+"' ");
                  
                  while(rs2.next()){
                     //m_color = "#"+rs2.getString(3);
                     //out.println("----"+rs2.getString(1));
                     out.println("[\"||+<span style='position:relative;left:0px;top:-2px;border:1px solid black;font-size:4pt;background-color:"+m_color+"'>&nbsp;&nbsp;&nbsp;</span> <span>&nbsp;</span><span style='font-weight:bold'>"+rs2.getString(2)+"</span>\", \"\",\""+m_html_client_url+"/img/subteam.gif\"],");
                     //--------------------------------------
                     
                     rs3=stmt3.executeQuery( "SELECT DISTINCT "+m_schema_name+".FA_GET_EMP_NAME(USER_ID) \"EMPLOYEE NAME\" "+
                        " FROM "+m_schema_name+".CO_CO_MAS_USER "+
                        " WHERE EMP_ID  IN "+ //USER_ID
                        " (SELECT USER_ID FROM "+m_schema_name+".AF_CO_MAS_TEAM_MEMBERS "+
                        " WHERE TEAM_ID='"+rs2.getString(1)+"' AND ACTIVE_STATUS='Y')");
                     
                     while(rs3.next()){
                        //out.println("[\"|||"+rs3.getString(1)+"\", \""+rs3.getString(3)+"\",],");
                        out.println("[ \"|||<span style='color:#000000'>"+rs3.getString(1)+"</span>\", \"\",\""+m_html_client_url+"/img/user.png\"],");
                     }
                     rs3.close();
                     //----------------------------
                     
                     
                  }
                  rs2.close();
                  
                  
               }  
								
               else if (rs1.getString(2).equals("TEAM")){
                  
                  rs2=stmt2.executeQuery(" SELECT TEAM_ID,INITCAP(TEAM_DESC) FROM "+m_schema_name+".AF_CO_MAS_TEAMS "+ 
                     " WHERE TEAM_ID='"+rs1.getString(1)+"' ");
                  
                  
                  
                  while(rs2.next()){
                     //m_color = "#"+rs2.getString(3);
                     out.println("[\"||+<span style='position:relative;left:0px;top:-2px;border:1px solid black;font-size:4pt;background-color:"+m_color+"'>&nbsp;&nbsp;&nbsp;</span> <span>&nbsp;</span><span style='font-weight:bold'>"+rs2.getString(2)+"</span>\", \"\",\""+m_html_client_url+"/img/team.gif\"],");
                     
                     rs4=stmt4.executeQuery(" SELECT SUB_TEAM_ID,'TEAM' TEAM "+
                        " FROM "+m_schema_name+".AF_CO_MAS_SUB_TEAMS_ASSIGN "+
                        " WHERE "+
                        " TEAM_ID='"+rs1.getString(1)+"' AND "+
                        " ACTIVE_STATUS='Y' ");
                     
                     
                     while(rs4.next()){
                        //if (rs4.getString(2).equals("SUBTEAM")){
                        
                        
                        
                        
                        
                        rs5=stmt5.executeQuery(" SELECT SUB_TEAM_ID,INITCAP(SUB_TEAM_DESC) FROM "+m_schema_name+".AF_CO_MAS_SUB_TEAMS "+ 
                           " WHERE SUB_TEAM_ID='"+rs4.getString(1)+"' ");
                        
                        while(rs5.next()){
                           //m_color = "#"+rs5.getString(3);
                           out.println("[\"|||+<span style='position:relative;left:0px;top:-2px;border:1px solid black;font-size:4pt;background-color:"+m_color+"'>&nbsp;&nbsp;&nbsp;</span> <span>&nbsp;</span><span style='font-weight:bold'>"+rs5.getString(2)+"</span>\", \"\",\""+m_html_client_url+"/img/subteam.gif\"],");
                           
                           rs3=stmt3.executeQuery( "SELECT DISTINCT "+m_schema_name+".FA_GET_EMP_NAME(USER_ID) \"EMPLOYEE NAME\"  "+
                              " FROM "+m_schema_name+".CO_CO_MAS_USER "+
                              " WHERE EMP_ID IN "+ //USER_ID
                              " (SELECT USER_ID FROM "+m_schema_name+".AF_CO_MAS_TEAM_MEMBERS "+
                              " WHERE TEAM_ID='"+rs5.getString(1)+"' AND ACTIVE_STATUS='Y')");
                           
                           while(rs3.next()){
                              //out.println("[\"|||"+rs3.getString(1)+"\", \""+rs3.getString(3)+"\",],");
                              out.println("[\"||||<span style='color:#000000'>"+rs3.getString(1)+"</span>\",\"\",\""+m_html_client_url+"/img/user.png\"],");
                           }
                           rs3.close();
                           
                           
                        }
                        rs5.close();
                        
                        
                        
                        //}
                        
                        
                        
                     }      
                     rs4.close();    
                     //-------------
                     
                  }
                  rs2.close();
                  
               }
               
               
            }
            rs1.close(); 
         }
         rs.close();
         
         out.println("];");
         
         out.println("</script>");
         out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
         out.println("<SCRIPT language=\"JavaScript1.2\" src='"+m_html_client_url+"/team/menu_funton.js' type=\"text/javascript\"></SCRIPT>");
         out.println("<SCRIPT language=\"JavaScript1.2\" src='"+m_html_client_url+"/team/menu_function_main.js' type=\"text/javascript\"></SCRIPT>");
         out.println("<SCRIPT language=\"JavaScript1.2\" src='"+m_html_client_url+"/team/menu_load.js' type=\"text/javascript\"></SCRIPT>");
         out.println("<SCRIPT language=\"JavaScript1.2\" src='"+m_html_client_url+"/team/validate_v1.js' type=\"text/javascript\"></SCRIPT>");
         
         out.println("<LINK href='"+m_html_client_url+"/css/menu_style.css' type=\"text/css\" rel=\"stylesheet\">");
         out.println("</BODY></HTML>");
         
         
         stmt.close();
         stmt1.close();
         stmt2.close();
         stmt3.close();
         
         conn.close();
         out.flush();
         out.close();
         
      }
      catch (Exception e) { 
         try { 
            
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


