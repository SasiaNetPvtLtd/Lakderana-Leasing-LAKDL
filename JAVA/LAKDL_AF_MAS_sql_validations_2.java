/*
	*	DEVELOPED BY : INESH
	*	2018-01-08 
	*	JB02012018-02276 NetAsset system documents upload facility
*/
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_MAS_sql_validations_2 extends HttpServlet {
	

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
	Connection connection=null;
	Statement statement=null;
	Statement statement2=null;
	java.text.NumberFormat nf=null;
	java.text.NumberFormat nf1=null;
	
	ResultSet resultSet=null;	
	ResultSet resultSet2=null;	
	ResultSet rs=null;	
	String m_chksql=null;
		
		
		try {
			
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods ();
			connection = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			//**************************************************************					
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/xml");
			res.setHeader("Cache-Control", "No-Cache");
			res.setDateHeader("Expires", 0);
			
			ServletOutputStream out = res.getOutputStream();
			
			m_chksql = req.getParameter("chksql").trim();
			statement=connection.createStatement();
			statement2=connection.createStatement();
			//m_prime_chk_
			
			if (m_chksql.equals("idle")) {
				out.println("idle");
			}
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_document_types")){
				
				String m_val = req.getParameter("data_val");
				
				resultSet= statement.executeQuery (" SELECT DOC_ID FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_TYPES WHERE DOC_NAME = UPPER('"+m_val+"') ");					
					
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");										
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			else if (m_chksql.equals("m_is_document_types_used")){
				String m_val = req.getParameter("data_val");
				resultSet= statement.executeQuery (	 
					" SELECT DOC_TYPE_ID  "+
					 " FROM "+m_schema_name+".AF_MK_DOC_UPLOAD_LOG "+
					 " WHERE DOC_TYPE_ID = '"+m_val+"' "+
					 " AND ACTIVE_STATUS = 'Y' ");
				
				out.print("<DATA>");
				if(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>ALREADY_USED</R1>");										
					out.print("</ITEM>");
				}else{
					out.print("<ITEM>");
					out.print("<R1>NOT_USED</R1>");										
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			else if (m_chksql.equals("m_get_data_LAKDL_AF_MAS_document_types")){
				String m_val = req.getParameter("data_val");
				
				resultSet= statement.executeQuery (
					 " SELECT  "+
					 "   DOC_ID, "+
					 "   UPPER(DOC_NAME) DOC_NAME, "+
					 "   NVL(DOC_DESCRIPTION,'_') DOC_DESCRIPTION, "+
					 "   NVL(NUM_OF_DOC,0) NUM_OF_DOC "+
					 " FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_TYPES  "+
					 " WHERE DOC_ID = UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				if(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString("DOC_ID")+"</R1>");										
					out.print("<R2>"+resultSet.getString("DOC_NAME")+"</R2>");
					out.print("<R3>"+resultSet.getString("DOC_DESCRIPTION")+"</R3>");
					out.print("<R4>"+resultSet.getString("NUM_OF_DOC")+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");

			}
			else if (m_chksql.equals("m_getDocumentUploadTable")){
				res.setStatus(HttpServletResponse.SC_OK);
				res.setContentType("text/html");
				out = res.getOutputStream();
				
				String m_fin_no = req.getParameter("fin_no");
				int numOfDoc =0;
				int num_of_rec=0;
				int num_of_slots = 0;
				boolean isHeaderPrint = false;
				String m_subQuery ="";
				String m_query = ""+
								 " SELECT  "+
								 "   A.DOC_ID, "+
								 "   A.DOC_NAME, "+
								 "   NVL(A.DOC_DESCRIPTION,A.DOC_NAME) DOC_DESCRIPTION, "+
								 "   NVL(A.NUM_OF_DOC,0) NUM_OF_DOC, "+
								 "   A.ACTIVE_STATUS "+
								 " FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_TYPES A "+
								 " WHERE A.ACTIVE_STATUS ='Y'  "+
								 " ORDER BY DOC_DESCRIPTION ASC ";
				String m_prev_upq =  
								 " SELECT SUM(DATAC) FROM ( "+
								 "   SELECT COUNT(*) DATAC FROM "+m_schema_name+".AF_MK_DOCUMENT_UPLOAD "+
								 "   WHERE ACTIVE_STATUS = 'Y'  "+
								 "   AND FINANCE_NO ='"+m_fin_no+"' "+
								 " UNION ALL "+
								 "   SELECT COUNT(*) DATAC FROM "+m_schema_name+".AF_MK_DOCUMENT_UPLOAD_NEW "+
								 "   WHERE ACTIVE_STATUS = 'Y'  "+
								 "   AND FINANCE_NO ='"+m_fin_no+"' "+
								 " ) ";

				try{
					resultSet= statement.executeQuery (m_prev_upq);
					if(resultSet.next()){
						if(resultSet.getInt(1)>0){
							out.println("<table align='center' width='95%' border='0' border-collapse='collapse' class='table'>"); 
							out.println("<tr >"); 
							out.println(" 	<td width='5%'  align=center></td>");
							out.println(" 	<td width='25%' align=center><b>View Previous Uploads</b></td>");
							out.println(" 	<td width='15%' align=center><input type=\"button\" class='mainbut' onClick='viewPreviousUploads();' width='100%' value=\"View\" ></td>");
							out.println(" 	<td width='15%' align=center></td>");
							out.println(" 	<td width='10%' align=center></td>");
							out.println(" 	<td width='15%' align=center></td>");
							out.println(" 	<td width='15%' align=center></td>");
							out.println("</tr>");							
							out.println("</table>");
							
							out.println("<table align='center' width='95%' border='0' border-collapse='collapse' class='table'>"); 
							out.println("<tr >"); 
							out.println(" 	<td width='5%'  align=center>Maximum file size for document is 1mb</td>");
							out.println(" 	<td width='25%' align=center></td>");
							out.println(" 	<td width='15%' align=center></td>");
							out.println(" 	<td width='15%' align=center></td>");
							out.println(" 	<td width='10%' align=center></td>");
							out.println(" 	<td width='15%' align=center></td>");
							out.println(" 	<td width='15%' align=center></td>");
							out.println("</tr>");							
							out.println("</table>");
							
							out.println("<br>");
							out.println("<br>");
							
						}
					}
					
					resultSet= statement.executeQuery (m_query);	
					out.println("<table align='center' width='95%' border='1px solid black' border-collapse='collapse' class='table'>"); 
					while(resultSet.next()){
						if(!isHeaderPrint){
							isHeaderPrint= true;
								out.println("<tr class='pdn_txtpos2' >"); 
								out.println(" 	<td width='5%'  align=center><b>No</b></td>");
								out.println(" 	<td width='20%' align=center><b>Document Type</b></td>");
								out.println(" 	<td width='15%' align=center><b>File Size(KB)</b></td>");
								out.println(" 	<td width='15%' align=center><b>Date/Time</b></td>");
								out.println(" 	<td width='10%' align=center><b>User</b></td>");
								out.println(" 	<td width='15%' align=center></td>");
								out.println(" 	<td width='10%' align=center></td>");
								out.println(" 	<td width='10%' align=center></td>");
								out.println("</tr>");
						}
						numOfDoc = resultSet.getInt("NUM_OF_DOC");
						for(int i=1;i<=numOfDoc;i++){
								
						m_subQuery = " SELECT  "+
								 "   DOC_ID, "+
								 "   DOC_TYPE_ID, "+
								 "   NVL(DOC_NUM,0) DOC_NUM, "+
								 "   NVL(DOCUMENT_NAME,'_') DOCUMENT_NAME, "+
								 "   NVL(FILE_TYPE,'_') FILE_TYPE, "+
								 "   NVL(FILE_SIZE,0) FILE_SIZE, "+
								 "   NVL(ACTIVE_STATUS,'N'), "+
								 "   NVL(A.MOD_USER,A.ENT_USER) ENT_USER, "+
								 "   TO_CHAR(NVL(A.MOD_DATE,A.ENT_DATE),'YYYY-MM-DD HH:MI:AM') ENT_DATE "+
								 " FROM "+m_schema_name+".AF_MK_DOC_UPLOAD_LOG A "+
								 " WHERE a.FINANCE_NO = '"+m_fin_no+"' "+
								 " AND A.ACTIVE_STATUS ='Y' "+
								 " AND a.DOC_TYPE_ID = '"+resultSet.getString("DOC_ID")+"' "+
								 " AND a.DOC_NUM ='"+i+"' ";
						resultSet2 = statement2.executeQuery (m_subQuery);
						
							if(resultSet2.next()){
									out.println("<tr >"); 
									++num_of_rec;
									//out.println(" 	<td align=center >"+ ++num_of_rec +"</td>"); //No
									if(i==1 && numOfDoc>1){
										out.println(" 	<td align=center rowspan='"+numOfDoc+"' >"+ ++num_of_slots +"</td>"); //No
										out.println(" 	<td rowspan='"+numOfDoc+"'>"+ resultSet.getString("DOC_DESCRIPTION")+"</td>"); //Document Type
									}else if(i==1 && numOfDoc==1){
										out.println(" 	<td align=center >"+ ++num_of_slots +"</td>"); //No
										out.println(" 	<td >"+ resultSet.getString("DOC_DESCRIPTION")+"</td>"); //Document Type
									}
									out.println(" 	<td align=center >"+ resultSet2.getString("FILE_SIZE")); //File Size(KB)
							 		out.println(" 		<input  type='hidden' value='"+resultSet2.getString("DOC_TYPE_ID")+"' name='HID_DOC_TYPE_ID_"+num_of_rec+"'>"); 
									out.println(" 		<input  type='hidden' value='N' name='HID_UPLOAD_STATUS_"+num_of_rec+"'>"); 
									out.println(" 		<input  type='hidden' value='N' name='HID_UPLOAD_ENABLE_"+num_of_rec+"'>");
									out.println(" 		<input  type='hidden' value='"+resultSet2.getString("DOC_NUM")+"' name='HID_DOC_NUM_"+num_of_rec+"'></td>"); 
									out.println(" 	<td>"+ resultSet2.getString("ENT_DATE")+"</td>"); //Date/Time
									out.println(" 	<td>"+ resultSet2.getString("ENT_USER")+"</td>"); //User
									out.println(" 	<td align=center ><input type=\"button\" class='mainbut' onClick='' width='100%' value=\"Browse\" disabled></td>"); //brows
									out.println(" 	<td align=center ><input type=\"button\" class='mainbut' onClick='' width='100%' value=\"Clear\" disabled></td>"); //view
									out.println(" 	<td align=center ><input type=\"button\" class='mainbut' onClick='viewDocument(\""+resultSet2.getString("DOC_ID")+"\")' width='100%' value=\"View\"></td>"); //view
									out.println("</tr>");
							}else{						
									out.println("<tr >"); 
									++num_of_rec;
									//out.println(" 	<td align=center >"+ ++num_of_rec +"</td>"); //No
									if(i==1 && numOfDoc>1){
										out.println(" 	<td align=center rowspan='"+numOfDoc+"'>"+ ++num_of_slots +"</td>");
										out.println(" 	<td rowspan='"+numOfDoc+"'>"+ resultSet.getString("DOC_DESCRIPTION")+"</td>"); //Document Type
									}else if(i==1 && numOfDoc==1){
										out.println(" 	<td align=center >"+ ++num_of_slots +"</td>");
										out.println(" 	<td >"+ resultSet.getString("DOC_DESCRIPTION")+"</td>"); //Document Type
									}
									out.println(" 	<td >"); //File Size(KB)
									out.println(" 		<input  type='hidden' value='"+resultSet.getString("DOC_ID")+"' name='HID_DOC_TYPE_ID_"+num_of_rec+"'>");
									out.println(" 		<input  type='hidden' value='N' name='HID_UPLOAD_STATUS_"+num_of_rec+"'>"); 
									out.println(" 		<input  type='hidden' value='Y' name='HID_UPLOAD_ENABLE_"+num_of_rec+"'>");
									out.println(" 		<input  type='hidden' value='"+i+"' name='HID_DOC_NUM_"+num_of_rec+"'></td>");
									out.println(" 	<td>&nbsp;</td>"); //Date/Time
									out.println(" 	<td>&nbsp;</td>"); //User
									out.println(" 	<td align=center ><input type=\"file\"  onchange='upload_file(\""+num_of_rec+"\");' id='FILE_DOCUMENT_"+num_of_rec+"' name='FILE_DOCUMENT_"+num_of_rec+"' size=\"50\">	</td>"); //brows			  &nbsp;					
									out.println(" 	<td align=center ><input type=\"button\" width='80px' class='mainbut' onClick='clearFileUpload(\""+num_of_rec+"\");' value=\"Clear\" ></td>"); //view
									out.println(" 	<td align=center ><input type=\"button\" class='mainbut' onClick='' width='100%' value=\"View\" disabled></td>"); //view
									out.println("</tr>");
							}
						}
					}
					if(!isHeaderPrint){
						out.println("<tr>"); 
						out.println(" 	<td ><h3>No Records fuond.</h3></td>");
						out.println("</tr>");
					}
					out.println("</table>"); 
					out.println("<input  type='hidden' value='"+num_of_rec+"' name='HID_NUM_OF_REC'> ");
					
				}catch(Exception e){
					out.println(e.getMessage());
				}
				
	
			}
			//------------------------------------------------------------------------------------------			
			else {
				out.println("Undefined");
			}
			
			out.close();
			connection.close();
			this.destroy();
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			try {
				connection.close();
			}catch (Exception eti) {}
			
			ByteArrayOutputStream ostr = new ByteArrayOutputStream();
			e.printStackTrace(new PrintWriter(ostr));
			
			ServletOutputStream out = res.getOutputStream();
			out.println(ostr.toString());
			out.close();
			
		}
	}
}