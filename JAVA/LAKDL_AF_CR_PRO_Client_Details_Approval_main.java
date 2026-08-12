/*
// header - edit "Data/yourJavaHeader" to customize
// contents - edit "EventHandlers/Java file/onCreate" to customize
//
*/
// CREATED BY SANJEEWA ON 2010-07-14
// DISPLAY NAME INVOICE DETAIL REPORT
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_CR_PRO_Client_Details_Approval_main extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
    public ResultSet rs;
	public String m_chksql;
	ServletOutputStream out = null;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		try {
		
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
				String m_schema_name = m_sn_methods.schema_name.trim();
				String m_html_client_url=m_sn_methods.html_client_url.trim(); 
				String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
				String m_fschema_name=m_sn_methods.client_name.trim();
				String m_header_name=m_sn_methods.header_name.trim();
			//**************************************************************					
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);      
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			res.setHeader("Cache-Control", "No-Cache");
     	    res.setDateHeader("Expires", 0);
			String m_client_code="";
			String m_from_date="";
			String m_to_date="";
			String query="";
			//m_client_code = req.getParameter("client_code").trim();																		
			//m_from_date = req.getParameter("as_at_date").trim();																		
		   //	m_to_date = req.getParameter("as_at_date1").trim();
	        
	
			stmt=conn.createStatement();
			ServletOutputStream out = res.getOutputStream();
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Client Details Approval </TITLE>"); 
			
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			
			out.println("function load_roll_value(m_val){");
		   	out.println("help_box.innerHTML=\" System Administration - Users - \"+m_val;"); 
			out.println("}"); 
			out.println("");
			
			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" System Administration - Users - \"+document.Form1.hid_status.value;"); 
			out.println("}");
			
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Client_Details_Approval_main';"); 
			out.println("		}"); 
			out.println("}");
			
			out.println("function close_screen() {");
			out.println("		if(document.Form1.HID_CLOSE_STS.value=='Y'){ "); 
			out.println("		     if(confirm(\"Are you sure you want to close the screen?\")){ "); 
			out.println("		      window.close();"); 
			out.println("		     }"); 
			out.println("		 }"); 
			out.println("		else { "); 
			out.println("		     close_window();"); 
			out.println("		}"); 
			out.println("}");
			
			out.println("function before_submit(){ "); 
			out.println("		m_option = document.Form1.hid_status.value;"); 
			out.println("		if(m_option=='New') {");
			out.println("			m_sav_msg = 'Are you sure you want to Save?'; ");
			out.println("		}"); 
			out.println("		else if(m_option=='Edit') {");
			out.println("			m_sav_msg = 'Are you sure you want to Modify?'; ");
			out.println("		}"); 
			out.println("		else if(m_option=='Deactivate') {");
			out.println("			m_sav_msg = 'Are you sure you want to Deactivate?'; ");
			out.println("		}"); 
			out.println("		else if(m_option=='Reactivate') {");
			out.println("			m_sav_msg = 'Are you sure you want to Reactivate?'; ");
			out.println("		}"); 
			//commen bu waruna
			//out.println("		if(validate_data()){");
			out.println("		if(true){");
			out.println("     for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("      document.Form1.elements[i].disabled=false;");
			out.println("     }");
			out.println("		if(confirm(m_sav_msg)){ "); 
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Client_Details_Approval_Save';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("		else { "); 
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("		}");
			out.println("} "); 

			
			out.println("</SCRIPT> "); 
			
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' >"); 
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
			out.println("<tr> "); 
			out.println("<td height='30' class='pdn_mainHD'>"+m_header_name+"</td>"); 
			out.println("</tr>"); 
			out.println("<tr> "); 
			out.println("<td height='1'><img src='spacer.gif' width='1' height='1'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td style='height: 30px'>"); 
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' height='100%' width='100%'>   "); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Client Details Approval</td>"); 
			out.println("</tr>"); 
			out.println("<tr>");
			//*********
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Deactivate\");' onClick='load_screen_status(\"DACT\")' value=\"Deactivate\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Reactivate\"></td>");
			///out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View All\");'  onclick='View_all()' value=\"View All\"></td>");
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut'  onMouseOver='load_roll_value(\"Save\");'   value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=close_screen(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td>");
			//*********
		    out.println("</tr>");
			out.println("</table>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			
			
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='HID_CLOSE_STS' VALUE=\"N\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			
			
			
			out.println("</table>"); 
			
			
			
			String CHNG_APP_STATUS="N";
			query="SELECT A.CLIENT_CODE, A.CLIENT_TYPE, A.FULL_NAME,A.ACTIVE_STATUS, A.BUSINESS_SUB_SECTOR, "+
       " A.CLIENT_CATEGORY, NVL(A.ADDRESS1,'-'), NVL(A.ADDRESS2,'-'), NVL(A.CITY_CODE,'-'), "+
       " NVL(A.REFERENCE,'-'), NVL(A.TEL_NO,'-'), NVL(A.FAX_NO,'-'), NVL(A.EMAIL,'-'),NVL(A.OFFICE_TEL_NO,'-'), "+
       " NVL( A.MOBILE_NO,'-'),  A.TEMP_ACTIVE_STATUS, A.ENT_USER, "+
       "  A.ENT_DATE, A.MOD_USER, A.MOD_DATE, A.CAT_TYPE_CODE, A.NIC_NO, "+
       "  A.BUSINESS_CERTIFICATE_NO, A.KEY_DECISION_MAKER, A.DESIGNATION, "+
       " A.DIRECT_TEL_NO, A.CONTACT_FOR_PAYMENT, A.DESIGNATION_PAYMENT, "+
       "  A.FACTORY_ADDRESS1, A.FACTORY_ADDRESS2, A.FACTORY_STATUS, "+
       "  A.F_CONTACT_PERSON, A.REGISTERED_ADDRESS1, A.REGISTERED_ADDRESS2, "+
       "  A.REGISTERED_CITY_CODE, A.REGISTERED_STATUS, "+
       "  A.CORRESPONDENCE_STATUS, A.F_TEL_NO, A.F_FAX_NO, A.F_EMAIL, "+
       "  A.ISSUED_SHARE_CAPITAL, A.DATE_OF_INCORPORATION, A.VAT_REG_NO, "+
      " A.VAT_REG_DATE, A.TITLE, A.FIRST_NAME, A.SURNAME, A.INITIALS, "+
      " A.OTHER_NAME, A.RESIDENTIAL_STATUS, A.DURATION_AT_YEARS, "+
      " A.DURATION_AT_MONTHS, A.PASSPORT_NO, A.MARITAL_STATUS, "+
      "  A.DATE_OF_BIRTH, A.NATIONALITY, A.GENDER, "+
      " A.BA_NATURE_OF_BUSINESS, A.BA_PROFESSION, A.BA_QUALIFICATIONS,"+
      "  A.BA_DESIGNATION, A.EMP_NAME, A.EMP_ADDRESS1, A.EMP_ADDRESS2,"+
      " A.EMP_REFERENCE, A.EMP_RDESIGNATION, A.EMP_TEL_NO, A.EMP_FAX_NO,"+
      "  A.AF_CLIENT, A.AF_GUARANTORS, A.FA_CLIENT, A.FA_DEBTOR, "+
      "  A.NO_OF_CHILDREN, A.DEPENDENTS, A.TEL_NO_GEN, A.FAX_NO_GEN, "+
      " A.EMAIL_GEN, A.DRIVING_LICENSE_NO, A.POSTALCODE, A.GRIB_NO, "+
      " A.SECTOR_CODE,A.CHNG_APP_STATUS "+
      " FROM LAKDL.AF_CO_MAS_CLIENT_APP A "+
	  " WHERE A.CHNG_APP_STATUS LIKE'%"+CHNG_APP_STATUS+"%' ";
			
			
		
      // out.println(query);
			rs = stmt.executeQuery(query);
			boolean more = rs.next();
		
		if(rs!=null){
						out.println("<br>");
			            out.println("<table border=0 class='table' width='100%'>");
			            out.println("<tr class='pdn_txtpos2'>");
			            out.println("<td width='5%' align='center'>Client Code</td>");
	                    //out.println("<td width='20%' align='center'>Application No</td>");
			            //out.println("<td width='20%' align='center'>Finance No</td>");
						out.println("<td width='5%' align='center'>Client Type</td>");
						out.println("<td width='10%' align='center'>Full Name</td>");
						out.println("<td width='10%' align='center'>Address1</td>");
		                out.println("<td width='10%' align='center'>Address2</td>");
			            out.println("<td width='10%' align='center'>City_Code</td>");
						out.println("<td width='10%' align='center'>Telephone No</td>");
						out.println("<td width='10%' align='center'>Fax No</td>");
						out.println("<td width='10%' align='center'>Email</td>");
						out.println("<td width='10%' align='center'>Office Telephone No</td>");
						out.println("<td width='10%' align='center'>Mobile No</td>");
			            //out.println("<td width='20%' align='center'>Active Status </td>");
						out.println("<td width='5%' align='center'>Approve</td>");
						
					    out.println("</tr>");
         
              int j = 0;
			  int i=0;	
              while(more){
						i++;
									if(j>0 && j%2==1){
										out.println("<tr class=tr_input >");
								    }
									else{
										out.println("<tr class=tr_input1 >");
									}
								 
									//out.println("<td width='20%' style= cursor:hand;cursor-color:blue onclick=show_inv_det_rep_by_month('"+rs.getString(1)+"')><u>"+rs.getString(1)+"</u></td>"); <input class='but_input' type='button' name='BUT_TXT_CLIENT_NAME_SEARCH' value='Approve' >
									out.println("<td width='5%' style= cursor:hand;cursor-color:blue >"+rs.getString(1)+"</td>"); 
									//out.println("<td width='20%' style= cursor:hand;cursor-color:blue >"+rs.getString(2)+"</td>"); 
									//out.println("<td width='20%' align='right' style= cursor:hand;cursor-color:blue >"+rs.getString(3)+"</td>"); 
									
									out.println("<td width='5%' align='left' style= cursor:hand;cursor-color:blue >"+rs.getString(2)+"</td>"); 
									out.println("<td width='10%' align='left' style= cursor:hand;cursor-color:blue >"+rs.getString(3)+"</td>"); 
									
									out.println("<td width='10%' align='left' style= cursor:hand;cursor-color:blue >"+rs.getString(7)+"</td>"); 
									out.println("<td width='10%' align='left' style= cursor:hand;cursor-color:blue >"+rs.getString(8)+"</td>"); 
									out.println("<td width='10%' align='left' style= cursor:hand;cursor-color:blue >"+rs.getString(9)+"</td>"); 
									
									out.println("<td width='10%' align='left' style= cursor:hand;cursor-color:blue >"+rs.getString(10)+"</td>"); 
									out.println("<td width='10%' align='left' style= cursor:hand;cursor-color:blue >"+rs.getString(11)+"</td>"); 
									out.println("<td width='10%' align='left' style= cursor:hand;cursor-color:blue >"+rs.getString(12)+"</td>"); 
									out.println("<td width='10%' align='left' style= cursor:hand;cursor-color:blue >"+rs.getString(13)+"</td>"); 
									out.println("<td width='10%' align='left' style= cursor:hand;cursor-color:blue >"+rs.getString(14)+"</td>"); 
									//out.println("<td width='20%' align='Center' style= cursor:hand;cursor-color:blue >"+rs.getString(4)+"</td>"); 
									//hidden values
									out.println("<input type='hidden' name='Client_Code_"+i+"' value='"+rs.getString(1)+"'>");	
									out.println("<td width='5%' align='center' style= cursor:hand;cursor-color:blue >"+
										" <select name='Approve_"+i+"' class='txt_input' > "+
                                        " <option value='N' selected>No Action</option> "+
                                        " <option value='Y'>Approve</option> "+
                                        " <option value='D'>Disapprove</option> "+
                                        " </select> "+
										" </td>"); //onClick="help_update();"
									
									out.println("</tr>"); 		
									more = rs.next();
									j=j+1;
	              }
				
			out.println("<input type='hidden' name='noofrecodes' value='"+i+"'>");	
			}
		
		
		
		
		   
			out.println("</table>");
		  out.println("</FORM>"); 
				
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("</BODY>"); 
			out.println("</html>"); 
			out.flush();
			
			
			
			
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
			     	
			
			
			
						
			
			
			
