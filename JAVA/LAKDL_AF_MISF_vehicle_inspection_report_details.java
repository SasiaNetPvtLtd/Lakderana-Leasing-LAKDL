//CREATED BY	:SAJITH MENDIS
//DATE/TIME		:09-04-2014

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

import java.awt.Image; // added by udara 05-05-2015
import java.awt.image.BufferedImage; // added by udara 05-05-2015
import java.lang.Object; // added by udara 05-05-2015
import javax.imageio.ImageIO;  // added by udara 05-05-2015
import java.io.File; // added by udara 05-05-2015

public class LAKDL_AF_MISF_vehicle_inspection_report_details extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt_2,stmt,stmt_invoice,stmt_rental,stmt_pricing,stmt_charges;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
	java.lang.Math a;
	
	/*
	// public ResultSet rs1,rs_doc_charge;
	public ResultSet rs,rs2,rs3,rs_rental,rs_pricing,rs_charges;
	
	public String m_chksql;
	public String m_path_1; // udara 11-04-2014
	
	//public String img_path = "D:\\SasiaNet_Products\\NetAsset\\LAKDL\\Home\\vehicle_inspection"; // DEVELOPMENT
	public String img_path = "E:\\SasiaNet\\NetAsset\\LAKDL\\Home\\vehicle_inspection"; // LIVE
	*/
	
	//public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{

		ResultSet rs,rs2,rs3,rs_rental,rs_pricing,rs_charges;
		String m_chksql;
		String m_path_1; 
		String img_path = "E:\\SasiaNet\\NetAsset\\LAKDL\\Home\\vehicle_inspection"; // LIVE
		//String img_path = "D:\\SasiaNet_Products\\NetAsset\\LAKDL\\Home\\vehicle_inspection"; // DEVELOPMENT
		int public_height = 200;
		
		rs=rs2=rs3=rs_rental=rs_pricing=rs_charges=null;
		
		
		
		try {
			
			//************************************************************	
			//LAKDL_AF_RE_PRO_drill_downs obj =new LAKDL_AF_RE_PRO_drill_downs();
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_username = m_sn_methods.username;
			
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
			
			ServletOutputStream out = res.getOutputStream();
			m_chksql=req.getParameter("chksql");
			String m_finance_no=req.getParameter("finance_no").trim();
				String m_doc_1=req.getParameter("doc_1").trim();
				String m_doc_2=req.getParameter("doc_2").trim();
				String m_doc_3=req.getParameter("doc_3").trim();
				String m_print=req.getParameter("print").trim();
				String m_status=req.getParameter("status").trim();
			
			stmt_invoice=conn.createStatement();
			stmt_pricing=conn.createStatement();
			stmt=conn.createStatement();
			stmt_2=conn.createStatement();
			stmt_rental=conn.createStatement();
			stmt_charges = conn.createStatement ();
			
			conn.setAutoCommit(false); // added by udara 28-3-2016
			
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			else if(m_chksql.equals("main_page")){
				
				//out.println("sfsdsd");
				
			if(m_status.equals("run_report")){	
				
				
				
				String m_client_code="";
				String m_application_no="";
				String m_client_name="";
				
				synchronized(this){ // added synchronized by udara 30-12-2014
					callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_VEHICLE_INSPEC_DOCS(:1,:2,:3,:4,:5); END;");
					callstmt.setString(1,m_finance_no);
					callstmt.setString(2,m_doc_1);
					callstmt.setString(3,m_doc_2);
					callstmt.setString(4,m_doc_3);
					callstmt.setString(5,m_username);
					callstmt.execute();
					callstmt.close();
				}
				
				try{
					rs=stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY'),"+m_schema_name+".AF_CO_GET_USER_NAME('"+m_username+"') FROM DUAL ");
					rs.next();
					String m_date=rs.getString(1);
					String m_user=rs.getString(2);
				}
				catch(Exception e_test_1){
					out.println(" DOCUMENT GENERATION ERROR CODE : e_test_1 : " + e_test_1.toString());
				}
				
				// rs.close(); // commented by udara 21-01-2015
				
				/*String Sql_client_data="SELECT DISTINCT A.APPLICATION_NO, "+//1
					" A.CLIENT_CODE, "+//2
					" UPPER(DECODE(B.CLIENT_TYPE,'I',INITCAP(B.TITLE) || '. ' || B.FULL_NAME,'C','Mess' || '. '  || B.FULL_NAME)), "+ //3
					" UPPER(NVL(DECODE(B.CLIENT_TYPE,'I',B.ADDRESS1,'C',B.ADDRESS1),'-')), "+//4 //B.REGISTERED_ADDRESS1
					" UPPER(NVL(DECODE(B.CLIENT_TYPE,'I',B.ADDRESS2,'C',B.ADDRESS2),'-')), "+//5 // B.REGISTERED_ADDRESS2
					" UPPER(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(B.CITY_CODE),' ')) CITY_NAME ,"+ //6
					//" NVL(DECODE(A.APPLICATION_STATUS,'ENTERED','Entered','ENT_CON','Application Process','VERIFY1','Credit Verification','V-APP','Credit Score Approval','VERIFY-M','Credit Approval 1','VERIFY2','Credit Approval 2','VERIFYL','Entered Leasing No','ACTIVATED','Purchase Order','CANCEL','Cancel','REPOSSESS','Repossess'),'-') "+//6 comment and add by malik on 26-8-2008//6 comment and add by malik on 26-8-2008
					//"NVL(DECODE(A.APPLICATION_STATUS,'ENTERED','Entered','ENT_CON','Completed','VERIFY1','Credit Verification','V-APP','Credit Score Approval','VERIFY-M','Credit Approval 1','VERIFY2','Credit Approval 2','VERIFYL','Entered Leasing No','ACTIVATED','Activated','CANCEL','Cancel','REPOSSESS','Repossess'),'-') "+//6
					" NVL(DECODE(APPLICATION_STATUS,'ACTIVATED',DECODE("+m_schema_name+".AF_CO_GET_APP_TER_STATUS(A.APPLICATION_NO),'ACTIVATED','ACTIVATED',"+m_schema_name+".AF_CO_GET_APP_TER_STATUS(A.APPLICATION_NO)),'ENTERED','ENTERED','ENT_CON','COMPLETED','VERIFY1','CREDIT VERIFICATION','V-APP','CREDIT SCORE APPROVAL','VERIFY-M','CREDIT APPROVAL 1','VERIFY2','CREDIT APPROVAL 2','VERIFYL','ENTERED LEASING NO'),'-') "+//add by malik on 7-10-2008 Modified by Dineth on 15-06-2009
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
					" WHERE A.CLIENT_CODE=B.CLIENT_CODE AND "+
					" UPPER(A.FINANCE_NO)=UPPER('"+m_finance_no+"') ";
				*/
				
				out.println("<HTML><HEAD><TITLE>Feasibility Report</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				
				out.println("<script>");
				out.println("</script>");
				
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0' >"); //onLoad=\"add_button()\"
				out.println("<FORM NAME='Form1' method='post'>"); 
				
				
				
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				//out.println("<TR><TD align='Center' ><B> VEHICLE INSPECTION REPORT  </B></TD></TR>"); // commented by udara 07-10-2014
				out.println("<TR><TD align='Center' ><B> VEHICLE INSPECTION REPORT - "+m_finance_no+" </B></TD></TR>"); // added by udara 07-10-2014
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				
				//boolean more=rs.next();
			
				
			
				String sysdate= "SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY'),TO_CHAR(SYSDATE,'HH'),TO_CHAR(SYSDATE,'MI'),TO_CHAR(SYSDATE,'AM') FROM DUAL";
				String m_cur_date = "";
				String m_cur_month = "";
				String m_cur_year = "";
				String m_cur_hour = "";
				String m_cur_min = "";
				String m_time = "";
				
				try{
					rs=stmt.executeQuery(sysdate);
					boolean more12=rs.next();
					if(more12){
						m_cur_date = rs.getString(1);
						m_cur_month = rs.getString(2);
						m_cur_year = rs.getString(3);
						m_cur_hour = rs.getString(4);
						m_cur_min = rs.getString(5);
						m_time = rs.getString(6);
					}
				}
				catch(Exception e_test_2){
					out.println(" DOCUMENT GENERATION ERROR CODE : e_test_2 : " + e_test_2.toString());
				}
				
			
				String client_info= "SELECT "+m_schema_name+".AF_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+m_schema_name+".AF_CO_GET_CLIENT_FULL_ADDRESS(A.CLIENT_CODE) FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A WHERE A.FINANCE_NO = '"+m_finance_no+"' ";
				String m_client_name_new = "";
				String m_client_add = "";
				
				try{
				
						rs=stmt.executeQuery(client_info);
						boolean more13=rs.next();
						if(more13){
							m_client_name_new = rs.getString(1);
							m_client_add = rs.getString(2);
						}
				}
				catch(Exception e_test_3){
					out.println("  DOCUMENT GENERATION ERROR CODE : e_test_3 : " + e_test_3.toString());
				}
				
				
			String vehicle_info= "SELECT "+
					"A.ASSET_ID,  "+
					"B.MAKE_CODE, "+
					"INITCAP(B.MAKE_DESC),  "+
					"A.MODEL_CODE,  "+
					"INITCAP(C.DESCRIPTION),  "+
					"A.SUB_MODEL_CODE,  "+
					"INITCAP(D.DESCRIPTION), "+
					"A.STATUS,  "+
					"A.COST,   "+
					"A.PURPOSE,   "+
					"A.QTY,   "+
					"E.DESCRIPTION ,  "+
					"D.YEAR_OF_MANUFACTURE   "+
					"FROM  "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS A,  "+
					""+m_schema_name+".AF_CO_MAS_MAKE B,  "+
					""+m_schema_name+".AF_CO_MAS_MODEL C,  "+
					""+m_schema_name+".AF_CO_MAS_SUB_MODLE D,   "+
					""+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY E  "+
					"WHERE A.MODEL_CODE=C.MODEL_CODE AND  "+
					"D.SUB_CODE=A.SUB_MODEL_CODE AND   "+
					"C.MAKE_CODE=B.MAKE_CODE AND  "+
					//"A.APPLICATION_NO =('"+m_application_no+"') AND  "+
					"A.APPLICATION_NO = "+m_schema_name+".AF_CO_GET_APPLICATION_NO('"+m_finance_no+"') AND  "+
					"C.ITEM_SUB_CAT(+)=E.ITEM_SUB_CAT ";
				
				String m_vehicle_make = "";
				String m_vehicle_model = "";
				String m_vehicle_year = "";
				
				
				try{
						
						rs=stmt.executeQuery(vehicle_info);
						boolean more14=rs.next();
						if(more14){
							m_vehicle_make = rs.getString(3);
							m_vehicle_model = rs.getString(5);
							m_vehicle_year = rs.getString(13);
						}
						
				}
				catch(Exception e_test_4){
					out.println("  DOCUMENT GENERATION ERROR CODE : e_test_4 : " + e_test_4.toString());
				}
				
				
				
				
				String vehicle_info_new= "SELECT A.INVOICE_NO,NVL(A.APPLICATION_NO,'-'),NVL(A.ASSET_ID,'-'),NVL(A.ENGINE_NO,'-'),NVL(A.MODEL_CODE,'-'), "+
					"NVL(A.CHASSIS_NO,'-'),NVL(A.REG_NO,'-'),NVL(TO_CHAR(A.REG_DATE,'DD-MM-YYYY'),'-'),NVL(A.PRICING_NO,'-'),NVL(A.SUB_MODEL_CODE,'-'),NVL(A.COLOUR,'-'),NVL(A.SEATING_CAPACITY,0),NVL(A.NET_PRICE,0), "+
					"NVL(A.VAT,0),NVL(A.TOTAL_AMOUNT,0),NVL(A.TO_BE_DELIVERD_TO,'-'),NVL(A.VALUE,0),NVL(A.CURR_CODE,'-'),NVL(A.BRANCH_ID,'-'),replace(NVL(A.VENDOR_CODE,'-'),'&','$') ,  "+
					"(SELECT DECODE(Y.ADDRESS1||','||Y.ADDRESS2,',','-',Y.ADDRESS1||','||Y.ADDRESS2)  "+
					"FROM  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X,  "+m_schema_name+".AF_CO_MAS_CLIENT Y  "+
					"WHERE X.CLIENT_CODE = Y.CLIENT_CODE AND  "+
					"X.APPLICATION_NO = A.APPLICATION_NO),  "+
					"(SELECT NVL(Y.CITY_CODE,'-')  "+
					"FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X, "+m_schema_name+".AF_CO_MAS_CLIENT Y  "+
					"WHERE X.CLIENT_CODE = Y.CLIENT_CODE AND  "+
					"X.APPLICATION_NO = A.APPLICATION_NO),  "+
					"NVL( (SELECT replace(B.NAME,'&','$') FROM "+m_schema_name+".AF_CO_MAS_VENDORS B WHERE B.VENDOR_CODE=A.VENDOR_CODE),'N/A') VENDOR_NAME ,  "+
					"NVL(INVOICE_DOC_NO,'-'),NVL(FUEL_CONVERTION_STATUS,'-'),TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'),  "+
					"( SELECT B.DESCRIPTION FROM    "+m_schema_name+".AF_CO_MAS_MODEL B WHERE A.MODEL_CODE=B.MODEL_CODE) MODEL_DESC,    "+
					"( SELECT C.DESCRIPTION FROM    "+m_schema_name+".AF_CO_MAS_SUB_MODLE C WHERE C.SUB_CODE=A.SUB_MODEL_CODE ) SUB_MODEL_DESC,   "+
					"A.YEAR_OF_MANUFACTURE,  "+
					"A.EXTRAS_INCLUDED,  "+
					"NVL(AF_CO_GET_CITY_NAME(A.CITY_CODE),' ') CITY_NAME  "+
					",NVL("+m_schema_name+".AF_CO_GET_MAKE_DESC("+m_schema_name+".AF_CO_GET_MAKE_CODE(A.MODEL_CODE)),' ') MAKE_DESC   "+
					",NVL("+m_schema_name+".AF_CO_GET_ITEM_SUB_DESC(A.MODEL_CODE),' ') ITEM_SUB_DESC    "+
					"FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
					"WHERE A.APPLICATION_NO = B.APPLICATION_NO "+
					"AND B.APPLICATION_STATUS='ACTIVATED' "+
					//"AND B.APPLICATION_NO = '"+m_application_no+"' ";
					"AND B.APPLICATION_NO = "+m_schema_name+".AF_CO_GET_APPLICATION_NO('"+m_finance_no+"') ";
				
				String m_vehicle_reg_num = "";
				String m_vehicle_reg_date = "";
				String m_vehicle_eng_num = "";
				String m_vehicle_chasis_num = "";
				String m_vehicle_color = "";
				String m_invoice_num = "";
				
				try{
				
						rs=stmt.executeQuery(vehicle_info_new);
						//out.println(vehicle_info_new);
						boolean more15=rs.next();
						if(more15){
							m_vehicle_reg_num = rs.getString(7);
							m_vehicle_reg_date = rs.getString(8);
							m_vehicle_eng_num = rs.getString(4);
							m_vehicle_chasis_num = rs.getString(6);
							m_vehicle_color = rs.getString(11);
							m_invoice_num = rs.getString(1);
						}
						
				}		
				catch(Exception e_test_5){
					out.println("  DOCUMENT GENERATION ERROR CODE : e_test_5 : " + e_test_5.toString());
				}
				
				
				String vehicle_parked= "SELECT NVL(A.SUM_INSURED,0),AREA,POLICE,OWNER_ADDRESS,COLLECTON_SECURITY,LIC_AUTH,VEHICAL_AGA  "+
					" FROM "+m_schema_name+".AF_CO_SUM_INSURED_DETAILS A   "+
					" WHERE UPPER(A.INVOICE_NO)=UPPER('"+m_invoice_num+"') "; 
				
				String m_vehicle_parked = "";
				
				
				try{
						rs=stmt.executeQuery(vehicle_parked);
						boolean more16=rs.next();
						if(more16){
							m_vehicle_parked = rs.getString(2);
						}
				}
				catch(Exception e_test_6){
					out.println("  DOCUMENT GENERATION ERROR CODE : e_test_6 : " + e_test_6.toString());
				}
				
				
				
				String vehicle_sales= "SELECT A.VALUATION_NO,A.ASSET_ID,MODEL_CODE,SUB_MODEL_CODE,NVL(REG_NO,'-'),ENGINE_NO,CHASSIS_NO,NOTES,REMARKS, "+
					"TYPE_OF_BODY,NVL(TO_CHAR(DATE_OF_REG,'DD-MM-YYYY'),'-'),VALUE,METER_READING, "+
					"TO_CHAR(VALUATION_DATE,'DD-MM-YYYY'),COLOUR,  "+
					"'','','',  "+
					"GENERAL_INDEX,SEATING_CAPACITY,NO_OF_CYLINDERS,	 "+
					" "+m_schema_name+".AF_CO_GET_FUEL_TYPE(MODEL_CODE) FUEL_TYPE,  "+
					" "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE) ITEM_CAT_CODE,NVL(A.PRO_INVOICE_NO,'-') PRO_INVOICE_NO,NVL(A.VALUER_CODE,'-') VALUER_CODE,  "+
					"NVL(A.YEAR_OF_MANUFACTURE,0) YEAR_OF_MANUFACTURE,  "+
					"NVL(A.CONDITION_OF_ASSET,'-') CONDITION_OF_ASSET,  "+
					"NVL(A.FORCED_SALES_VALUE,0) FORCED_SALES_VALUE,  "+
					" "+m_schema_name+".AF_CO_GET_VALUER_NAME(VALUER_CODE) VALUER_NAME  "+
					",(SELECT C.DESCRIPTION FROM  "+m_schema_name+".AF_CO_MAS_SUB_MODLE C WHERE C.SUB_CODE=SUB_MODEL_CODE) SUB_MODEL_DESC  "+
					",NVL("+m_schema_name+".AF_CO_GET_MAKE_DESC("+m_schema_name+".AF_CO_GET_MAKE_CODE(MODEL_CODE)),' ') MAKE_DESC  "+
					",NVL("+m_schema_name+".AF_CO_GET_ITEM_SUB_DESC(MODEL_CODE),' ') ITEM_SUB_DESC    "+
					"FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION A  "+
					"WHERE  "+
					//"A.APPLICATION_NO=UPPER('"+m_application_no+"') "; 
					"A.APPLICATION_NO="+m_schema_name+".AF_CO_GET_APPLICATION_NO('"+m_finance_no+"') "; 
				
				
				String m_vehicle_meter_reading = "";
				String m_vehicle_sales_val = "";
				String m_vehicle_forced_sales_val = "";
				String m_vehicle_fuel_type = "";
				
				try{
						rs=stmt.executeQuery(vehicle_sales);
						boolean more17=rs.next();
						if(more17){
							m_vehicle_sales_val = rs.getString(12);
							m_vehicle_forced_sales_val = rs.getString(28);
							m_vehicle_fuel_type = rs.getString(22);
							m_vehicle_meter_reading =  rs.getString(13);
						}
						
				}		
				catch(Exception e_test_7){
					out.println("  DOCUMENT GENERATION ERROR CODE : e_test_7 : " + e_test_7.toString());
				}
				
				/*
				File index = new File(""+img_path+"");
				index.delete();
				*/
				
				
				try{
				
					//m_path_1 = "E:\\SasiaNet\\NetAsset\\LAKDL\\Home\\vehicle_inspection\\doc_1.jpg";	// LIVE
					m_path_1 = ""+img_path+"\\doc_1.jpg"; // DEVELOPMENT
					File index = new File(m_path_1);
					index.delete();
					
					//m_path_1 = "E:\\SasiaNet\\NetAsset\\LAKDL\\Home\\vehicle_inspection\\doc_2.jpg";	// LIVE
					m_path_1 = ""+img_path+"\\doc_2.jpg";// DEVELOPMENT
					index = new File(m_path_1);
					index.delete();
					
					//m_path_1 = "E:\\SasiaNet\\NetAsset\\LAKDL\\Home\\vehicle_inspection\\doc_3.jpg";	// LIVE
					m_path_1 = ""+img_path+"\\doc_3.jpg";// DEVELOPMENT
					index = new File(m_path_1);
					index.delete();
				
				}
				catch(Exception e_test_8){
					out.println("  DOCUMENT GENERATION ERROR CODE : e_test_8 : " + e_test_8.toString());
				}
				
				
				
				String inspection_documents = "SELECT DOCUMENT_1, DOCUMENT_2, DOCUMENT_3 FROM "+m_schema_name+".AF_MK_VEHICLE_INSPEC_DOC WHERE FINANCE_NO = '"+m_finance_no+"' "; 
				
				
				String m_document1 = "";
				String m_document2 = "";
				String m_document3 = "";
				
				try{
				
						rs=stmt.executeQuery(inspection_documents);
						boolean more53=rs.next();
						if(more53){
							m_document1 = rs.getString(1);
							m_document2 = rs.getString(2);
							m_document3 = rs.getString(3);
						}
						
				}
				catch(Exception e_test_9){
					out.println("  DOCUMENT GENERATION ERROR CODE : e_test_9 : " + e_test_9.toString());
				}
				
				/*
				String Sql_data=
						
						" SELECT	A.DOCUMENT, A.FILE_NAME"+
						"  FROM	"+m_schema_name+".AF_MK_DOCUMENT_UPLOAD A "+
						//"  WHERE	A.ACTIVE_STATUS = 'Y' AND A.DOCUMENT_NO = '"+m_document_no+"'";
						"  WHERE	A.ACTIVE_STATUS = 'Y' AND A.DOCUMENT_NO = 'DOC-FIN20081208-0001'";
					
					
					rs=stmt.executeQuery(Sql_data);
					//out.println(""+Sql_data+"");
					Blob document = null;
					byte[] imgData = null ;
					
					boolean more55=rs.next();
					
					if(more55){
							document = rs.getBlob(1);
							imgData = document.getBytes(1,(int)document.length());
					}
					
					*/
				
				
				Blob document = null;
				String Sql_data="";
				
				
				Sql_data=
					
					" SELECT	A.DOCUMENT, A.FILE_NAME, A.DOCUMENT_NO"+
					"  FROM	"+m_schema_name+".AF_MK_DOCUMENT_UPLOAD A "+
					//"  WHERE	A.ACTIVE_STATUS = 'Y' AND A.DOCUMENT_NO = '"+m_document_no+"'";
					"  WHERE	A.ACTIVE_STATUS = 'Y' AND A.DOCUMENT_NO = '"+m_document1+"'";
				
				
				try{
				
						rs=stmt.executeQuery(Sql_data);
						//out.println(""+Sql_data+"");
						boolean more77=rs.next();
						//res.setContentType("APPLICATION/OCTET-STREAM");
						if(more77){
							//	res.setHeader("Content-Disposition",
							//	"attachment;filename="+rs.getString(2)+"");
							
							// commented by udara 11-04-2014
							/*
							document = rs.getBlob(1);
							
							
							InputStream in = document.getBinaryStream();
							int length = (int) document.length();
							
							int bufferSize = 1024;
							byte[] buffer = new byte[bufferSize];
							
							while ((length = in.read(buffer)) != -1) {
								out.write(buffer, 0, length);
							}
							
							in.close();
							
							out.flush();
							*/
							
							// added by udara 11-04-2014
							m_path_1 = ""+img_path+"\\doc_1.jpg"; // DEVELOPMENT
							//m_path_1 = "E:\\SasiaNet\\NetAsset\\LAKDL\\Home\\vehicle_inspection\\doc_1.jpg"; // LIVE
							File image = new File(m_path_1);
							FileOutputStream fos = new FileOutputStream(image);
							
							// get the blob
							document = rs.getBlob(1);
							
							InputStream in = document.getBinaryStream();
							int length = (int) document.length();
							
							int bufferSize = 1024;
							byte[] buffer = new byte[bufferSize];
							
							while ((length = in.read(buffer)) != -1) {
								//out.write(buffer, 0, length);
								fos.write(buffer, 0, length);
							}
							
							in.close();
							
							fos.close();
							
							out.flush();
							
							
						}		
				
				}
				catch(Exception e_test_10){
					out.println("  DOCUMENT GENERATION ERROR CODE : e_test_10 : " + e_test_10.toString());
				}
				
				
				Blob document_2 = null;
				String Sql_data_2="";
				
				Sql_data_2=
					
					" SELECT	A.DOCUMENT, A.FILE_NAME"+
					"  FROM	"+m_schema_name+".AF_MK_DOCUMENT_UPLOAD A "+
					"  WHERE	A.ACTIVE_STATUS = 'Y' AND A.DOCUMENT_NO = '"+m_document2+"'";
				
				try{
				
							rs=stmt.executeQuery(Sql_data_2);
							boolean more78=rs.next();
							if(more78){
								// added by udara 11-04-2014
								m_path_1 = ""+img_path+"\\doc_2.jpg"; // DEVELOPMENT
								//m_path_1 = "E:\\SasiaNet\\NetAsset\\LAKDL\\Home\\vehicle_inspection\\doc_2.jpg";// LIVE
								File image = new File(m_path_1);
								FileOutputStream fos = new FileOutputStream(image);
								
								// get the blob
								document = rs.getBlob(1);
								
								InputStream in = document.getBinaryStream();
								int length = (int) document.length();
								
								int bufferSize = 1024;
								byte[] buffer = new byte[bufferSize];
								
								while ((length = in.read(buffer)) != -1) {
									fos.write(buffer, 0, length);
								}
								in.close();
								fos.close();
								out.flush();
							}
				
				}
				catch(Exception e_test_11){
					out.println("  DOCUMENT GENERATION ERROR CODE : e_test_11 : " + e_test_11.toString());
				}
				
				
				Blob document_3 = null;
				String Sql_data_3="";
				
				Sql_data_3=
					
					" SELECT	A.DOCUMENT, A.FILE_NAME"+
					"  FROM	"+m_schema_name+".AF_MK_DOCUMENT_UPLOAD A "+
					"  WHERE	A.ACTIVE_STATUS = 'Y' AND A.DOCUMENT_NO = '"+m_document3+"'";
				
				try{
				
						rs=stmt.executeQuery(Sql_data_3);
						boolean more79=rs.next();
						if(more79){
							// added by udara 11-04-2014
							m_path_1 = ""+img_path+"\\doc_3.jpg"; // DEVELOPMENT
							//m_path_1 = "E:\\SasiaNet\\NetAsset\\LAKDL\\Home\\vehicle_inspection\\doc_3.jpg";// LIVE
							File image = new File(m_path_1);
							FileOutputStream fos = new FileOutputStream(image);
							
							// get the blob
							document = rs.getBlob(1);
							
							InputStream in = document.getBinaryStream();
							int length = (int) document.length();
							
							int bufferSize = 1024;
							byte[] buffer = new byte[bufferSize];
							
							while ((length = in.read(buffer)) != -1) {
								fos.write(buffer, 0, length);
							}
							in.close();
							fos.close();
							out.flush();
							
							
						}
						
						
				}
				catch(Exception e_test_12){
					out.println("  DOCUMENT GENERATION ERROR CODE : e_test_12 : " + e_test_12.toString());
				}

				
				rs.close();
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Marketing - Change Proforma Invoice </TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">");
			
				
				
				
				
				
				out.println(" function selectOnlyThis(id) { ");
				//out.println(" alert(id+\"id\");  ");
				//out.println(" alert(document.Form1.ckeck_inspec_\"+i+\".value);  ");
    			out.println(" for (var i = 1;i <= 4; i++)  ");
    			out.println("{  ");
				out.println("  m_ch_ins = 'ckeck_inspec_'+i; ");
               // out.println(" document.getElementById(\"ckeck_inspec_\" + i).checked = false; ");
				//out.println("document.Form1.ckeck_inspec_\"+i+\".checked=false; ");
				out.println("  document.Form1.elements[m_ch_ins].checked=false; ");
    			out.println("}  ");
    			out.println("document.getElementById(id).checked = true;  ");
				out.println("document.getElementById(id).value = 'N';  ");
				//out.println(" alert(document.getElementById(id).value+\"*****value\");  ");
				out.println("} ");
				
				out.println(" function val_change(id,i) { ");
				out.println("  check_ok = 'check_ok_'+i; ");
				out.println("  need_att = 'need_att_'+i; ");
				//out.println(" alert(check_ok+\"*****check_ok\");  ");
				//out.println(" alert(need_att+\"*****need_att\");  ");
				
				out.println("  document.Form1.elements[check_ok].checked=false; ");
				out.println("  document.Form1.elements[need_att].checked=false; ");

			//	out.println(" alert(document.getElementById(id).id+\"*****value\");  ");
    			out.println("document.getElementById(id).checked = true;  ");
				out.println("document.getElementById(id).value = 'N';  ");
				out.println("} ");
				
				// added by udara 07-10-2014
				
				out.println(" function val_change_revenue(id) { ");
				out.println("  document.Form1.elements['RevenueLi_ok'].checked=false; ");
				out.println("  document.Form1.elements['RevenueLi_na'].checked=false; ");
    			out.println("  document.getElementById(id).checked = true;  ");
				out.println("  document.getElementById(id).value = 'N';  ");
				out.println("} ");
				
				out.println(" function val_change_insurance(id) { ");
				out.println("  document.Form1.elements['Insurance_ok'].checked=false; ");
				out.println("  document.Form1.elements['Insurance_na'].checked=false; ");
    			out.println("  document.getElementById(id).checked = true;  ");
				out.println("  document.getElementById(id).value = 'N';  ");
				out.println("} ");
				
				// end by udara 07-10-2014
				
				
				/*out.println("if(document.Form1.check_ok_\"+i+\".checked==true){");
				out.println("document.Form1.check_ok_\"+i+\".value=\"Y\"; ");
				out.println("}else {");
				out.println("document.Form1.check_ok_\"+i+\".value=\"N\"; ");
				out.println("}");
				
				out.println("if(document.Form1.need_att_\"+i+\".checked==true){");
				out.println("document.Form1.need_att_\"+i+\".value=\"Y\"; ");
				out.println("}else {");
				out.println("document.Form1.need_att_\"+i+\".value=\"N\"; ");
				out.println("}");
				*/

				
				out.println("function save_report_details(m_finance_no){");
				out.println("		         if(confirm(\"Are you sure you want to save?\")){ "); 
			    //out.println("		          document.Form1.action=\""+m_class_url+"/"+m_schema_name+"_AF_MISF_save_inspection_report_details?FIN_NO=\"+m_finance_no+\"\";");
			    out.println("		          document.Form1.action=\""+m_class_url+"/"+m_schema_name+"_AF_MISF_save_inspection_report_details?FIN_NO=\"+m_finance_no+\"&doc_1="+m_doc_1+"&doc_2="+m_doc_2+"&doc_3="+m_doc_3+"\";");
			    out.println("		          document.Form1.submit();	"); 
			    out.println("		         }"); 
				out.println("}");
				
				// added by udara 10-10-2014
				
				out.println("function validate_before_save(m_finance_no){");
				
				/*
				// Basic
				out.println("  if((document.Form1.RevenueLi_ok.checked==false) && (document.Form1.RevenueLi_na.checked==false) ){ ");
				out.println("     alert('Please select Revenue License Status'); ");
				out.println("  }");
				out.println("  else if((document.Form1.Insurance_ok.checked==false) && (document.Form1.Insurance_na.checked==false) ){ ");
				out.println("     alert('Please select Insurance Status'); ");
				out.println("  }");
				
				// Unusual Noises
				out.println("  else if((document.Form1.check_ok_1.checked==false) && (document.Form1.need_att_1.checked==false) ){ ");
				out.println("     alert('Please select Noise Status'); ");
				out.println("  }");
				
				// Gauges
				out.println("  else if((document.Form1.check_ok_2.checked==false) && (document.Form1.need_att_2.checked==false) ){ ");
				out.println("     alert('Please select Fuel Status'); ");
				out.println("  }");
				out.println("  else if((document.Form1.check_ok_3.checked==false) && (document.Form1.need_att_3.checked==false) ){ ");
				out.println("     alert('Please select Temperature Status'); ");
				out.println("  }");
				out.println("  else if((document.Form1.check_ok_4.checked==false) && (document.Form1.need_att_4.checked==false) ){ ");
				out.println("     alert('Please select Dashboard Warning Light Status'); ");
				out.println("  }");
				
				// Lights
				out.println("  else if((document.Form1.check_ok_5.checked==false) && (document.Form1.need_att_5.checked==false) ){ ");
				out.println("     alert('Please select Headlights Status'); ");
				out.println("  }");
				out.println("  else if((document.Form1.check_ok_6.checked==false) && (document.Form1.need_att_6.checked==false) ){ ");
				out.println("     alert('Please select Break Lights Status'); ");
				out.println("  }");
				out.println("  else if((document.Form1.check_ok_7.checked==false) && (document.Form1.need_att_7.checked==false) ){ ");
				out.println("     alert('Please select Turn Signals Status'); ");
				out.println("  }");
				out.println("  else if((document.Form1.check_ok_8.checked==false) && (document.Form1.need_att_8.checked==false) ){ ");
				out.println("     alert('Please select Hazard Lights Status'); ");
				out.println("  }");
				
				// Other
				out.println("  else if((document.Form1.check_ok_9.checked==false) && (document.Form1.need_att_9.checked==false) ){ ");
				out.println("     alert('Please select Windshield Wipers Status'); ");
				out.println("  }");
				out.println("  else if((document.Form1.check_ok_10.checked==false) && (document.Form1.need_att_10.checked==false) ){ ");
				out.println("     alert('Please select Engine # in Original look & feel Status'); ");
				out.println("  }");
				out.println("  else if((document.Form1.check_ok_11.checked==false) && (document.Form1.need_att_11.checked==false) ){ ");
				out.println("     alert('Please select Chassis # in Original look & feel Status'); ");
				out.println("  }");
				out.println("  else if((document.Form1.check_ok_12.checked==false) && (document.Form1.need_att_12.checked==false) ){ ");
				out.println("     alert('Please select Body and Paint Status'); ");
				out.println("  }");
				out.println("  else if((document.Form1.check_ok_13.checked==false) && (document.Form1.need_att_13.checked==false) ){ ");
				out.println("     alert('Please select Hood Status'); ");
				out.println("  }");
				out.println("  else if((document.Form1.check_ok_14.checked==false) && (document.Form1.need_att_14.checked==false) ){ ");
				out.println("     alert('Please select Registration Number with CR Status'); ");
				out.println("  }");
				out.println("  else if((document.Form1.check_ok_15.checked==false) && (document.Form1.need_att_15.checked==false) ){ ");
				out.println("     alert('Please select Any Corporate Branding Painted Status'); ");
				out.println("  }");
				
				out.println("  else if((document.Form1.check_ok_16.checked==false) && (document.Form1.need_att_16.checked==false) ){ ");
				out.println("     alert('Please select Brakes Status'); ");
				out.println("  }");
				out.println("  else if((document.Form1.check_ok_17.checked==false) && (document.Form1.need_att_17.checked==false) ){ ");
				out.println("     alert('Please select Parking Break Status'); ");
				out.println("  }");
				out.println("  else if((document.Form1.check_ok_18.checked==false) && (document.Form1.need_att_18.checked==false) ){ ");
				out.println("     alert('Please select Mirrors Status'); ");
				out.println("  }");
				out.println("  else if((document.Form1.check_ok_19.checked==false) && (document.Form1.need_att_19.checked==false) ){ ");
				out.println("     alert('Please select Horn Status'); ");
				out.println("  }");
				out.println("  else if((document.Form1.check_ok_20.checked==false) && (document.Form1.need_att_20.checked==false) ){ ");
				out.println("     alert('Please select Exhaust Status'); ");
				out.println("  }");
				out.println("  else if((document.Form1.check_ok_21.checked==false) && (document.Form1.need_att_21.checked==false) ){ ");
				out.println("     alert('Please select Seats and Interior Status'); ");
				out.println("  }");
				
				out.println("  else if((document.Form1.start_DD.value=='DD') || (document.Form1.start_MM.value=='MM') || (document.Form1.start_YY.value=='YYYY') ){ ");
				out.println("     alert('Please enter the Last Serviced Date'); ");
				out.println("  }");
				
				// Tires
				out.println("  else if((document.Form1.check_ok_22.checked==false) && (document.Form1.need_att_22.checked==false) ){ ");
				out.println("     alert('Please select Proper Inflation Status'); ");
				out.println("  }");
				out.println("  else if((document.Form1.check_ok_23.checked==false) && (document.Form1.need_att_23.checked==false) ){ ");
				out.println("     alert('Please select Adequate Tread Status'); ");
				out.println("  }");
				out.println("  else if((document.Form1.check_ok_24.checked==false) && (document.Form1.need_att_24.checked==false) ){ ");
				out.println("     alert('Please select Spare Inflated Status'); ");
				out.println("  }");
				
				// Leaks
				out.println("  else if((document.Form1.check_ok_25.checked==false) && (document.Form1.need_att_25.checked==false) ){ ");
				out.println("     alert('Please select Oil Leak Status'); ");
				out.println("  }");
				out.println("  else if((document.Form1.check_ok_26.checked==false) && (document.Form1.need_att_26.checked==false) ){ ");
				out.println("     alert('Please select Other Leak Status'); ");
				out.println("  }");
				
				// Safety Equipment
				out.println("  else if((document.Form1.check_ok_27.checked==false) && (document.Form1.need_att_27.checked==false) ){ ");
				out.println("     alert('Please select Fire Extinguisher Status'); ");
				out.println("  }");
				out.println("  else if((document.Form1.check_ok_28.checked==false) && (document.Form1.need_att_28.checked==false) ){ ");
				out.println("     alert('Please select First Aid Kit Status'); ");
				out.println("  }");
				out.println("  else if((document.Form1.check_ok_29.checked==false) && (document.Form1.need_att_29.checked==false) ){ ");
				out.println("     alert('Please select Flares Status'); ");
				out.println("  }");
				out.println("  else if((document.Form1.check_ok_30.checked==false) && (document.Form1.need_att_30.checked==false) ){ ");
				out.println("     alert('Please select Spare Bulbs/Fuses Status'); ");
				out.println("  }");
				out.println("  else if((document.Form1.check_ok_31.checked==false) && (document.Form1.need_att_31.checked==false) ){ ");
				out.println("     alert('Please select GPS Status'); ");
				out.println("  }");
				out.println("  else if((document.Form1.check_ok_32.checked==false) && (document.Form1.need_att_32.checked==false) ){ ");
				out.println("     alert('Please select Seat Belts Status'); ");
				out.println("  }");
				
				out.println("  else if((document.Form1.text_dd.value=='DD') || (document.Form1.text_mm.value=='MM') || (document.Form1.text_yy.value=='YYYY') ){ ");
				out.println("     alert('Please enter the Date'); ");
				out.println("  }");
				
				out.println("  else{");
				//out.println("     alert('Ready'); ");
				out.println("       save_report_details(m_finance_no);  ");
				out.println("  }");
				*/
				
				// added by udara 19-12-2014
				out.println("  if(isNaN(document.Form1.start_DD.value)==true){ ");
				out.println("     document.Form1.start_DD.value = ''; ");
				out.println("  }");
				out.println("  if(isNaN(document.Form1.start_MM.value)==true){ ");
				out.println("     document.Form1.start_MM.value = ''; ");
				out.println("  }");
				out.println("  if(isNaN(document.Form1.start_YY.value)==true){ ");
				out.println("     document.Form1.start_YY.value = ''; ");
				out.println("  }");
				
				out.println("  if(isNaN(document.Form1.text_dd.value)==true){ ");
				out.println("     document.Form1.text_dd.value = ''; ");
				out.println("  }");
				out.println("  if(isNaN(document.Form1.text_mm.value)==true){ ");
				out.println("     document.Form1.text_mm.value = ''; ");
				out.println("  }");
				out.println("  if(isNaN(document.Form1.text_yy.value)==true){ ");
				out.println("     document.Form1.text_yy.value = ''; ");
				out.println("  }");
				// end by udara 19-12-2014
				
				out.println("  var count = 0; ");
				
				out.println("  if((document.Form1.RevenueLi_ok.checked==false) && (document.Form1.RevenueLi_na.checked==false) ){ ");
				out.println("        count = count + 0; ");
				out.println("  }");
				out.println("  else{");
				out.println("        count = count + 1; ");
				out.println("  }");
				
				
				out.println("  if((document.Form1.Insurance_ok.checked==false) && (document.Form1.Insurance_na.checked==false) ){ ");
				out.println("        count = count + 0; ");
				out.println("  }");
				out.println("  else{");
				out.println("        count = count + 1; ");
				out.println("  }");
				
				
				out.println("  for(i=1; i<33; i++){ ");
				out.println("     if( (document.Form1.elements['check_ok_'+i].checked==false) && (document.Form1.elements['need_att_'+i].checked==false)  ){ ");
				out.println("        count = count + 0; ");
				out.println("     }");
				out.println("     else{");
				out.println("        count = count + 1; ");
				out.println("     }");
				out.println("  }");
				//out.println("  alert(count); ");
				
				out.println("  if(count>0){; ");
				out.println("       save_report_details(m_finance_no);  ");
				out.println("  }");
				out.println("  else{");
				out.println("       alert('Enter the data before save'); ");
				out.println("  }");
				
				out.println("}");
				// end by udara 10-10-2014
				
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"\">"); 
				out.println("<FORM NAME='Form1' method='post'>");
				//out.println("<input class='' type='checkbox' name='ckeck_inspec_hid'  VALUE = '' >");
				
				
				out.println("	<table frame='VOID' cols='5' rules='NONE' cellspacing='0' border='0' style='font-size:130%' >	");
				out.println("	    <colgroup>	");
				out.println("	        <col width='20%'/>	");
				out.println("	        <col width='20%'/>	");
				out.println("	        <col width='1%'/>	");
				out.println("	        <col width='15%'/>	");
				out.println("	        <col width='44%'/>	");
				out.println("	    </colgroup>	");
				out.println("	    <tbody>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20' width='20%'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT' width='20%'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT' width='1%'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT' width='15%'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT' width='44%'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Date: "+m_cur_date+"/"+m_cur_month+"/"+m_cur_year+"	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                Time: "+m_cur_hour+" : "+m_cur_min+" "+m_time+" ");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Vehicle Owner's Name s per NIC: "+m_client_name_new+" ");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Permanent Address: "+m_client_add+"	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Permanent Location the vehicle is parked: "+m_vehicle_parked+"	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                Vehicle Make: "+m_vehicle_make+"	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                Model: "+m_vehicle_model+ " Year: "+m_vehicle_year+"	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='27'>	");
				out.println("	                Odometer Reading: "+m_vehicle_meter_reading+"	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='21'>	");
				out.println("	                Registration # "+m_vehicle_reg_num+"	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                Registration Date: "+m_vehicle_reg_date+"	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='18'>	");
				out.println("	                Engine #: "+m_vehicle_eng_num+"	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                Chassis #  "+m_vehicle_chasis_num+"	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='18'>	");
				out.println("	                Fuel Type: "+m_vehicle_fuel_type+"	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                Color: "+m_vehicle_color+"	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='18'>	");
				out.println("	                Market Value: "+m_vehicle_sales_val+"	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                Forced Sale Value: "+m_vehicle_forced_sales_val+"	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Valid: Revenue License  Yes <input class='' type='checkbox' name='RevenueLi_ok' id='RevenueLi_ok'  VALUE = 'N' onclick='val_change_revenue(this.id)' > No<input class='' type='checkbox' name='RevenueLi_na' id='RevenueLi_na'  VALUE = 'N' onclick='val_change_revenue(this.id)' > Insurance   Yes<input class='' type='checkbox' name='Insurance_ok' id='Insurance_ok'  VALUE = 'N' onclick='val_change_insurance(this.id)' >  No<input class='' type='checkbox' name='Insurance_na' id='Insurance_na'  VALUE = 'N' onclick='val_change_insurance(this.id)' >	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Please check any item that needs attention and then include additional details under the comments section below.	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                Start the Engine and Test the Following: (Instructor needs to use a red pen)	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <strong><u>Unusual Noises:</u></strong>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Noises	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' id='check_ok_1' name='check_ok_1' onClick='val_change(this.id,1);' VALUE = 'N' > Needs Attention <input class='' type='checkbox' id='need_att_1'  name='need_att_1' onClick='val_change(this.id,1);' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_1'  VALUE = 'NOISES' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <strong>	");
				out.println("	                    <u>	");
				out.println("	                        <br/>	");
				out.println("	                    </u>	");
				out.println("	                </strong>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <strong>Gauges:</strong>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Fuel	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' id='check_ok_2' name='check_ok_2' onClick='val_change(this.id,2);' VALUE = 'N' > Needs Attention <input class='' type='checkbox' id='need_att_2' name='need_att_2' onClick='val_change(this.id,2);' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_2'  VALUE = 'FUEL' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Temperature	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' id='check_ok_3' name='check_ok_3' onClick='val_change(this.id,3);' VALUE = 'N' > Needs Attention <input class='' type='checkbox' id='need_att_3' name='need_att_3' onClick='val_change(this.id,3);' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_3'  VALUE = 'TEMP' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Dashboard Warning Light	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' id='check_ok_4' name='check_ok_4' onClick='val_change(this.id,4);' VALUE = 'N' > Needs Attention <input class='' type='checkbox' id='need_att_4' name='need_att_4' onClick='val_change(this.id,4);'  VALUE = 'N' ><input class='' type='hidden' name='hidd_name_4'  VALUE = 'DASHBORD' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <strong><u>Lights:</u></strong>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Headlights	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' id='check_ok_5' name='check_ok_5' onClick='val_change(this.id,5);' VALUE = 'N' > Needs Attention <input class='' type='checkbox' id='need_att_5' name='need_att_5' onClick='val_change(this.id,5);' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_5'  VALUE = 'HEADLIGHT' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Break Lights	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' id='check_ok_6' name='check_ok_6' onClick='val_change(this.id,6);' VALUE = 'N' > Needs Attention <input class='' type='checkbox' id='need_att_6' name='need_att_6' onClick='val_change(this.id,6);' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_6'  VALUE = 'BREAKELIGHT' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Turn Signals	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' id='check_ok_7' name='check_ok_7' onClick='val_change(this.id,7);' VALUE = 'N' > Needs Attention <input class='' type='checkbox' id='need_att_7'  name='need_att_7' onClick='val_change(this.id,7);' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_7'  VALUE = 'TURNSIG' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Hazard Lights	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' id='check_ok_8' name='check_ok_8' onClick='val_change(this.id,8);' VALUE = 'N'> Needs Attention <input class='' type='checkbox' id='need_att_8' name='need_att_8' onClick='val_change(this.id,8);' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_8'  VALUE = 'HAZARDLIG' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <strong><u>Other:</u></strong>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Windshield Wipers	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' id='check_ok_9' name='check_ok_9' onClick='val_change(this.id,9);' VALUE = 'N' > Needs Attention <input class='' type='checkbox' id='need_att_9' name='need_att_9' onClick='val_change(this.id,9);' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_9'  VALUE = 'WIPERS' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Engine # in Original look &amp; feel	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' id='check_ok_10' name='check_ok_10' onClick='val_change(this.id,10);' VALUE = 'N' > Needs Attention <input class='' type='checkbox' id='need_att_10'  name='need_att_10' onClick='val_change(this.id,10);' VALUE = 'N' > Photograph taken <input class='' type='checkbox' name='engin_ori_pht'  VALUE = 'N' ><input class='' type='hidden' name='hidd_name_10'  VALUE = 'ENGINNO' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Chassis # in Original look &amp; feel	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' id='check_ok_11' name='check_ok_11' onClick='val_change(this.id,11);' VALUE = 'N' > Needs Attention <input class='' type='checkbox' id='need_att_11' name='need_att_11' onClick='val_change(this.id,11);' VALUE = 'N' > Photograph Taken <input class='' type='checkbox' name='chasi_ori_pht'  VALUE = 'N' ><input class='' type='hidden' name='hidd_name_11'  VALUE = 'CHASSINO' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Body and Paint	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' id='check_ok_12'  name='check_ok_12' onClick='val_change(this.id,12);' VALUE = 'N' > Needs Attention <input class='' type='checkbox' id='need_att_12' name='need_att_12' onClick='val_change(this.id,12);' VALUE = 'N' > Photograph Taken <input class='' type='checkbox' name='body_paint_pht'  VALUE = 'N' ><input class='' type='hidden' name='hidd_name_12'  VALUE = 'BODYPAINT' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Hood	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' id='check_ok_13' name='check_ok_13' onClick='val_change(this.id,13);' VALUE = 'N' > Needs Attention <input class='' type='checkbox' id='need_att_13' name='need_att_13' onClick='val_change(this.id,13);' VALUE = 'N' > Photograph Taken <input class='' type='checkbox' name='hood_pht'  VALUE = 'N' ><input class='' type='hidden' name='hidd_name_13'  VALUE = 'HOOD' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Registration Number with CR	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' id='check_ok_14'  name='check_ok_14'  onClick='val_change(this.id,14);' VALUE = 'N' > Needs Attention <input class='' type='checkbox' id='need_att_14'  name='need_att_14' onClick='val_change(this.id,14);' VALUE = 'N' > Photograph Taken <input class='' type='checkbox' name='reg_no_pht'  VALUE = 'N' ><input class='' type='hidden' name='hidd_name_14'  VALUE = 'REGNO' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Any Corporate Branding Painted	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' id='check_ok_15' name='check_ok_15' onClick='val_change(this.id,15);' VALUE = 'N' > Needs Attention <input class='' type='checkbox' id='need_att_15' name='need_att_15' onClick='val_change(this.id,15);'  VALUE = 'N' ><input class='' type='hidden' name='hidd_name_15'  VALUE = 'COOPPAINT' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Company Name painted/stickered	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <input class='' type='text' name='company_name'  VALUE = '' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Body-kits any modifications	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                NO<input class='' type='checkbox' name='body_kit'  VALUE = 'N' > YES Specify  <input class='' type='text' name='Specify'  VALUE = '' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                Brakes	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' id='check_ok_16' name='check_ok_16' onClick='val_change(this.id,16);' VALUE = 'N' > Needs Attention <input class='' type='checkbox' id='need_att_16' name='need_att_16' onClick='val_change(this.id,16);' VALUE = 'N' >	<input class='' type='hidden' name='hidd_name_16'  VALUE = 'BREAKERS' >");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Parking Break	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' id='check_ok_17' name='check_ok_17' onClick='val_change(this.id,17);' VALUE = 'N' > Needs Attention <input class='' type='checkbox' id='need_att_17'  name='need_att_17' onClick='val_change(this.id,17);' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_17'  VALUE = 'PARKBREAK' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Mirrors	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' id='check_ok_18' name='check_ok_18' onClick='val_change(this.id,18);' VALUE = 'N' > Needs Attention <input class='' type='checkbox' id='need_att_18' name='need_att_18' onClick='val_change(this.id,18);' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_18'  VALUE = 'MIRRORS' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Horn	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' id='check_ok_19' name='check_ok_19' onClick='val_change(this.id,19);' VALUE = 'N' > Needs Attention <input class='' type='checkbox' id='need_att_19'  name='need_att_19' onClick='val_change(this.id,19);' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_19'  VALUE = 'HORN' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Exhaust	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' id='check_ok_20' name='check_ok_20' onClick='val_change(this.id,20);' VALUE = 'N' > Needs Attention <input class='' type='checkbox' id='need_att_20'  name='need_att_20' onClick='val_change(this.id,20);' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_20'  VALUE = 'EXHAUST' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Seats and Interior	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' id='check_ok_21'  name='check_ok_21' onClick='val_change(this.id,21);' VALUE = 'N' > Needs Attention <input class='' type='checkbox' id='need_att_21' name='need_att_21' onClick='val_change(this.id,21);' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_21'  VALUE = 'SEATINTERIOR' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Last Serviced date	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                 <input class='' type='checkbox' name='last_service_date'  VALUE = 'N' style={display:none;} > State date  <input class='' maxlength='2' size='2' type='text' name='start_DD'  VALUE = 'DD' >/ <input class='' maxlength='2' size='2' type='text' name='start_MM'  VALUE = 'MM' >/ <input class='' maxlength='4' size='4' type='text' name='start_YY'  VALUE = 'YYYY' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Remarks	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <input class='' type='text' name='Remarks_1'  VALUE = '' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <strong>	");
				out.println("	                    <br/>	");
				out.println("	                </strong>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <strong><u>Tires:</u></strong>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Proper Inflation	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                 <input class='' type='checkbox' id='check_ok_22' name='check_ok_22' onClick='val_change(this.id,22);' VALUE = 'N' > Needs Attention <input class='' type='checkbox' id='need_att_22' name='need_att_22'  onClick='val_change(this.id,22);' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_22'  VALUE = 'PROPINFLATION' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Adequate Tread	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' id='check_ok_23'  name='check_ok_23' onClick='val_change(this.id,23);' VALUE = 'N' > Needs Attention <input class='' type='checkbox' id='need_att_23'  name='need_att_23' onClick='val_change(this.id,23);' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_23'  VALUE = 'ADEQUATETREAD' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Spare Inflated	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' id='check_ok_24' name='check_ok_24' onClick='val_change(this.id,24);' VALUE = 'N' > Needs Attention <input class='' type='checkbox' id='need_att_24' name='need_att_24' onClick='val_change(this.id,24);' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_24'  VALUE = 'SPAREINFLATED' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <strong><u>Leaks:</u></strong>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                Oil	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' id='check_ok_25' name='check_ok_25' onClick='val_change(this.id,25);' VALUE = 'N' > Needs Attention <input class='' type='checkbox' id='need_att_25' name='need_att_25' onClick='val_change(this.id,25);' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_25'  VALUE = 'OIL' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Other	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				//out.println("	                OK <input class='' type='checkbox' id='check_ok_26'  name='check_ok_26' onClick='val_change(this.id,26);' VALUE = 'N' > Needs Attention <input class='' type='checkbox' id='need_att_26' name='need_att_26' onClick='val_change(this.id,26);' VALUE = 'N' >   Remarks <input class='' type='text' name='Remarks' VALUE = '' ><input class='' type='hidden' name='hidd_name_26'  VALUE = 'OTHER' >");
				out.println("	                OK <input class='' type='checkbox' id='check_ok_26'  name='check_ok_26' onClick='val_change(this.id,26);' VALUE = 'N' > Needs Attention <input class='' type='checkbox' id='need_att_26' name='need_att_26' onClick='val_change(this.id,26);' VALUE = 'N' >   Remarks <input class='' type='text' name='Remarks_2' VALUE = '' ><input class='' type='hidden' name='hidd_name_26'  VALUE = 'OTHER' >"); // udara 04-09-2014
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <strong><u>Safety Equipment:</u></strong>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Fire Extinguisher	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' id='check_ok_27' name='check_ok_27' onClick='val_change(this.id,27);' VALUE = 'N' > Needs Attention <input class='' type='checkbox' id='need_att_27' name='need_att_27' onClick='val_change(this.id,27);' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_27'  VALUE = 'EXHUSTER' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                First Aid Kit	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' id='check_ok_28' name='check_ok_28' onClick='val_change(this.id,28);' VALUE = 'N' > Needs Attention <input class='' type='checkbox' id='need_att_28' name='need_att_28' onClick='val_change(this.id,28);' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_28'  VALUE = 'FIRSTAIDKID' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Flares	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' id='check_ok_29' name='check_ok_29' onClick='val_change(this.id,29);' VALUE = 'N' > Needs Attention <input class='' type='checkbox' id='need_att_29' name='need_att_29' onClick='val_change(this.id,29);' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_29'  VALUE = 'FLARES' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                Spare Bulbs/Fuses	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' id='check_ok_30' name='check_ok_30' onClick='val_change(this.id,30);' VALUE = 'N' > Needs Attention <input class='' type='checkbox' id='need_att_30' name='need_att_30' onClick='val_change(this.id,30);' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_30'  VALUE = 'SPAREFUES' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                GPS	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' id='check_ok_31' name='check_ok_31' onClick='val_change(this.id,31);' VALUE = 'N' > Needs Attention <input class='' type='checkbox' id='need_att_31' name='need_att_31' onClick='val_change(this.id,31);' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_31'  VALUE = 'GPS' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                Seat Belts	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' id='check_ok_32' name='check_ok_32' onClick='val_change(this.id,32);' VALUE = 'N' > Needs Attention <input class='' type='checkbox' id='need_att_32' name='need_att_32' onClick='val_change(this.id,32);' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_32'  VALUE = 'SEATBELT' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				
				
				
				// udara start 11-04-2014
				/*
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT'> ");
				out.println("	                	<img src='https://dev-lakdl.sasianet.com/vehicle_inspection/java_1.gif' height=\"100\" width=\"100\" > "); 
				out.println("	            </td>	");
				out.println("	        </tr>	");
				*/
				out.println("	        <tr>	");
				out.println("	            <td> ");
				out.println("	                <table border=1 > ");
				
				
				//File doc_1 = new File("E:\\SasiaNet\\NetAsset\\LAKDL\\Home\\vehicle_inspection\\doc_1.jpg");//LIVE
				File doc_1 = new File(""+img_path+"\\doc_1.jpg"); //DEVELOPMENT
				
				
				
				
				if(doc_1.exists()){
					
					// added by udara 05-05-2015
					int width = 0;
					int height = 0;
					int new_width = 0;
					int new_height = public_height;
					try{
						BufferedImage bimg = ImageIO.read(doc_1);
						width          = bimg.getWidth();
						height         = bimg.getHeight();
						new_width = (width * new_height) / height;
					}
					catch (IOException e) {
						
					}
					// end by udara 05-05-2015
					
					
					out.println("	                     <td> ");					
					//out.println("	                	    <img src=\""+m_html_client_url+"/vehicle_inspection/doc_1.jpg\" height=\"200\" width=\"200\" > "); // commented by udara 05-05-2015
					out.println("	                	    <img src=\""+m_html_client_url+"/vehicle_inspection/doc_1.jpg\" height=\""+new_height+"\" width=\""+new_width+"\" > "); 
					out.println("	                     </td> ");
					
				}
				
				//File doc_2 = new File("E:\\SasiaNet\\NetAsset\\LAKDL\\Home\\vehicle_inspection\\doc_2.jpg");//LIVE
				File doc_2 = new File(""+img_path+"\\doc_2.jpg"); //DEVELOPMENT
				if(doc_2.exists()){
					
					// added by udara 05-05-2015
					int width = 0;
					int height = 0;
					int new_width = 0;
					int new_height = public_height;
					try{
						BufferedImage bimg = ImageIO.read(doc_2);
						width          = bimg.getWidth();
						height         = bimg.getHeight();
						new_width = (width * new_height) / height;
					}
					catch (IOException e) {
						
					}
					// end by udara 05-05-2015
					
					out.println("	                     <td> ");
					//out.println("	                	    <img src=\""+m_html_client_url+"/vehicle_inspection/doc_2.jpg\" height=\"200\" width=\"200\" > "); // commented by udara 05-05-2015
					out.println("	                	    <img src=\""+m_html_client_url+"/vehicle_inspection/doc_2.jpg\" height=\""+new_height+"\" width=\""+new_width+"\" > "); // added by udara 05-05-2015
					out.println("	                     </td> ");
				}
				
				//File doc_3 = new File("E:\\SasiaNet\\NetAsset\\LAKDL\\Home\\vehicle_inspection\\doc_3.jpg");//LIVE
				File doc_3 = new File(""+img_path+"\\doc_3.jpg"); //DEVELOPMENT
				if(doc_3.exists()){
					
					// added by udara 05-05-2015
					int width = 0;
					int height = 0;
					int new_width = 0;
					int new_height = public_height;
					try{
						BufferedImage bimg = ImageIO.read(doc_3);
						width          = bimg.getWidth();
						height         = bimg.getHeight();
						new_width = (width * new_height) / height;
					}
					catch (IOException e) {
						
					}
					// end by udara 05-05-2015
					
					
					out.println("	                     <td> ");
					//out.println("	                	    <img src=\""+m_html_client_url+"/vehicle_inspection/doc_3.jpg\" height=\"200\" width=\"200\" > "); // commented by udara 05-05-2015
					out.println("	                	    <img src=\""+m_html_client_url+"/vehicle_inspection/doc_3.jpg\" height=\""+new_height+"\" width=\""+new_width+"\" > "); // added by udara 05-05-2015
					out.println("	                     </td> ");
				}
				
				out.println("	                </table> ");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				// udara end 11-04-2014
				
				
				
				
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <strong><u>Comments:</u></strong>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	               <input class='' type='text' name='text_comment' VALUE = '' maxlength='100' size='100' >		");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <input class='' type='text' name='text_comment1' VALUE = '' maxlength='100' size='100' >		");
				out.println("	            </td>	");
				
				
				
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <strong><u>Condition of Vehicle Following the Inspection:</u></strong>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='18'>	");
				out.println("	                 <input class='' type='checkbox' id='ckeck_inspec_1' name='ckeck_inspec_1' VALUE = '' onclick='selectOnlyThis(this.id);' > Excellent Condition: (A+) Rating, the condition is nearly new and overall ratings are satisfactory.	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='18'>	");
				out.println("	                 <input class='' type='checkbox' id='ckeck_inspec_2'  name='ckeck_inspec_2' VALUE = '' onclick='selectOnlyThis(this.id);' >  Acceptable: (A) Rating, the vehicle is in Good Condition and is functional and can fetch a standard market value.	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='18'>	");
				out.println("	                 <input class='' type='checkbox' id='ckeck_inspec_3' name='ckeck_inspec_3' VALUE = '' onclick='selectOnlyThis(this.id);' >  Needs Attention: (A-) Rating, the roadworthiness of the vehicle is in question and may lead to loss of revenue days.	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='18'>	");
				out.println("	                 <input class='' type='checkbox' id='ckeck_inspec_4' name='ckeck_inspec_4' VALUE = '' onclick='selectOnlyThis(this.id);' >  Inspection Failed: (X) Rating, the vehicle condition is unacceptable for any facility	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                ______________________________ 	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	<input class='' type='text' name='text_dd' maxlength='2' size='2'  VALUE = 'DD' >/<input class='' type='text' name='text_mm' maxlength='2' size='2'  VALUE = 'MM' >/<input class='' type='text' name='text_yy' maxlength='4' size='4'  VALUE = 'YYYY' >");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Signature	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                Date	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                ACCOUNTABILITY STATEMENT (FILLED BY COMPANY REPRESENTATIVE)	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='JUSTIFY' height='17'>	");
				out.println("	                <strong>	");
				out.println("	                    <u>	");
				out.println("	                        <br/>	");
				out.println("	                    </u>	");
				out.println("	                </strong>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='JUSTIFY' height='17'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                I  <input class='' type='text' name='text_1' VALUE = '' maxlength='20' size='20' > (state name of Inspector) employee number  <input class='' type='text' name='text_2' VALUE = '' maxlength='20' size='20' >Take full responsibility for this	");
				out.println("	                inspection and I am aware that I am accountable and responsible for the accuracy, validity and reliability of all facts stated herein.	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <input class='' type='text' name='text_3' VALUE = '' maxlength='20' size='20' >	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Inspector's Name &amp; Signature Date and time	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <strong><u>FOR OFFICE USE:</u></strong>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='25'>	");
				out.println("	                Recommended Value: ________________________________ (1st Inspector)	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='25'>	");
				out.println("	                Recommended Value: ________________________________ (2nd Inspector)	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Remarks by Branch Manager: (Pls state any disparities and the resolution or mitigation strategies of objections/negative observations recorded	");
				out.println("	                amongst the two inspectors if any).	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	Signature and Name of the Branch Manager 	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>Date and time	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	"); 
				out.println("	    </tbody>	");
				out.println("	</table>	");
				
				out.println("<br>");
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				//out.println("<TR><TD align='right'><input class='mainbut' type='button' name='Save' onClick=\"save_report_details('"+m_finance_no+"')\" VALUE = 'Save Report' ></TD></TR>"); // commented by udara 10-10-2014
				out.println("<TR><TD align='right'><input class='mainbut' type='button' name='Save' onClick=\"validate_before_save('"+m_finance_no+"')\" VALUE = 'Save Report' ></TD></TR>"); // added by udara 10-10-2014
				out.println("</TABLE>");
				
				
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			
				out.println("</BODY></HTML>");
				
				synchronized(this){ 
					callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_VEHICLE_INSPEC_TEMP(:1,:2,:3,:4,:5); END;");
					callstmt.setString(1,m_finance_no);
					callstmt.setString(2,m_doc_1);
					callstmt.setString(3,m_doc_2);
					callstmt.setString(4,m_doc_3);
					callstmt.setString(5,m_username);
					callstmt.execute();
					callstmt.close();
				}
				
				// conn.commit(); // commented by udara 28-03-2016 // added by udara 30-12-2014
				
				
			}
			else if(m_status.equals("view_report")){	

				//String m_client_code="";
				//String m_application_no="";
				//String m_client_name="";
				
				String m_application_no = req.getParameter("finance_no").trim();
					
						
				out.println("<HTML><HEAD><TITLE>Feasibility Report</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			
				out.println("<script>");
				
				out.println("function val_change_ph(val,feild){ ");
				out.println(" alert(val); ");
				out.println(" } ");
				/*out.println("function val_change_ph(val,feild){ ");
				out.println(" alert(val); ");
				out.println(" if(val==\"N\" && feild=\"chas\"){ ");
			    out.println("  document.Form1.elements[chasi_ori_pht].checked=true; ");
			    out.println(" } ");
				out.println(" if(val=='N' && feild='body'){ ");
			    out.println("  document.Form1.elements[body_paint_pht].checked=true; ");
			    out.println(" } ");
				out.println(" if(val=='N' && feild='eng'){ ");
			    out.println("  document.Form1.elements[engin_ori_pht].checked=true; ");
			    out.println(" } ");
				out.println(" if(val=='N' && feild='hood'){ ");
			    out.println("  document.Form1.elements[hood_pht].checked=true; ");
			    out.println(" } ");
				out.println(" if(val=='N' && feild='reg'){ ");
			    out.println("  document.Form1.elements[reg_no_pht].checked=true; ");
			    out.println(" } ");
				out.println(" } ");*/
					
			out.println("function display_data(data_vec){ ");
			
			//out.println("	alert('test_alert 2'); ");
			
			out.println("m_size=33; "); // increased to 32->33 by udara 04-09-2014 for seat belts
			out.println("  k=1; ");
			out.println("  x=1; ");
			out.println("  y=2; ");
			out.println(" while(m_size>k){");
			//out.println("  alert(data_vec[0]+'--1'+data_vec[1]+'--2'+data_vec[2]+'--3'+data_vec[3]+'--4'+data_vec[4]+'--5'+data_vec[5]+'--6'); ");
			
			out.println("  m_ch_ok = data_vec[x]; ");
			out.println("  m_ch_att = data_vec[y]; ");
			
			out.println("  m_chke = 'check_ok_'+k; ");
			out.println("  m_chkv = 'need_att_'+k; ");
			
			//out.println("  alert(x+'***x'+y+'***y'); ");
			//out.println("  alert(m_chke+'m_chke'); ");
			//out.println("  alert(m_chkv+'m_chkv'); ");
			
			out.println(" if(m_ch_ok=='N'){ ");
			out.println("  document.Form1.elements[m_chke].checked=true; ");
			out.println(" } ");
			out.println(" else if(m_ch_att=='N'){ ");
			out.println("  document.Form1.elements[m_chkv].checked=true; ");
			//out.println("  document.Form1.elements[m_chke].value='on';");
			//out.println("  document.Form1.elements[m_chkv].value='on';");
			out.println(" } ");
			out.println(" else{");
			out.println("  document.Form1.elements[m_chke].checked=false; ");
			//out.println("  document.Form1.elements[m_chke].value='off';");
			out.println("  document.Form1.elements[m_chkv].checked=false; ");
			//out.println("  document.Form1.elements[m_chkv].value='on';");
			out.println(" }");
			out.println("k=k+1;");
			out.println("x=x+3;");
			out.println("y=y+3;");
			out.println(" }");
			
			
			out.println("		for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("		   document.Form1.elements[i].disabled=true;");
			out.println("		}");
			
			out.println("}");
			
			out.println("function makeRequest_rights(obj) {");
			//out.println("document.Form1.hid_chk_status.value='M6';");
     out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations2?chksql=m_prime_chk_LAKDL_AF_MAS_display_inspection_details&data_val=\"+obj+\"&ac_status=Y\";");
			//out.println("window.open(m_url)");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			
			out.println("function get_vector(data_vec) {");
			///out.println("			if(data_vec.length != 0  && document.Form1.TXT_USER_GROUP.value!=\"\" ){");
			out.println("				display_data(data_vec);");
			//out.println("			}");
			out.println("}");
			
			
			out.println("function assign_data() {");
			//out.println("	alert('test_alert 1'); ");
			///out.println("			if(data_vec.length != 0  && document.Form1.TXT_USER_GROUP.value!=\"\" ){");
			out.println("				makeRequest_rights('"+m_finance_no+"');");
			//out.println("			}");
			out.println("}");
			
			out.println("function val_change(){");
				
			//	out.println("for(int i=1;i<=32;i++){");
				/*out.println("if(document.Form1.check_ok_\"+i+\".checked==true){");
				out.println("document.Form1.check_ok_\"+i+\".value=\"Y\"; ");
				out.println("}else {");
				out.println("document.Form1.check_ok_\"+i+\".value=\"N\"; ");
				out.println("}");
				
				out.println("if(document.Form1.need_att_\"+i+\".checked==true){");
				out.println("document.Form1.need_att_\"+i+\".value=\"Y\"; ");
				out.println("}else {");
				out.println("document.Form1.need_att_\"+i+\".value=\"N\"; ");
				out.println("}");
				*/
				//out.println("}");
				
				out.println("}");
			
				out.println("</script>");
				
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'  onLoad='assign_data()' >"); //onLoad=\"add_button()\"
				out.println("<FORM NAME='Form1' method='post'>"); 


				
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				//out.println("<TR><TD align='Center' ><B> VEHICLE INSPECTION REPORT-VIEW  </B></TD></TR>"); // commented by udara 07-10-2014
				out.println("<TR><TD align='Center' ><B> VEHICLE INSPECTION REPORT - VIEW - "+m_finance_no+"  </B></TD></TR>"); 
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
			/*	rs=stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY'),"+m_schema_name+".AF_CO_GET_USER_NAME('"+m_username+"') FROM DUAL ");
				rs.next();
				String m_date=rs.getString(1);
				String m_user=rs.getString(2);
				
				rs.close();*/
				
				/*String Sql_client_data="SELECT DISTINCT A.APPLICATION_NO, "+//1
					" A.CLIENT_CODE, "+//2
					" UPPER(DECODE(B.CLIENT_TYPE,'I',INITCAP(B.TITLE) || '. ' || B.FULL_NAME,'C','Mess' || '. '  || B.FULL_NAME)), "+ //3
					" UPPER(NVL(DECODE(B.CLIENT_TYPE,'I',B.ADDRESS1,'C',B.ADDRESS1),'-')), "+//4 //B.REGISTERED_ADDRESS1
					" UPPER(NVL(DECODE(B.CLIENT_TYPE,'I',B.ADDRESS2,'C',B.ADDRESS2),'-')), "+//5 // B.REGISTERED_ADDRESS2
					" UPPER(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(B.CITY_CODE),' ')) CITY_NAME ,"+ //6
					//" NVL(DECODE(A.APPLICATION_STATUS,'ENTERED','Entered','ENT_CON','Application Process','VERIFY1','Credit Verification','V-APP','Credit Score Approval','VERIFY-M','Credit Approval 1','VERIFY2','Credit Approval 2','VERIFYL','Entered Leasing No','ACTIVATED','Purchase Order','CANCEL','Cancel','REPOSSESS','Repossess'),'-') "+//6 comment and add by malik on 26-8-2008//6 comment and add by malik on 26-8-2008
					//"NVL(DECODE(A.APPLICATION_STATUS,'ENTERED','Entered','ENT_CON','Completed','VERIFY1','Credit Verification','V-APP','Credit Score Approval','VERIFY-M','Credit Approval 1','VERIFY2','Credit Approval 2','VERIFYL','Entered Leasing No','ACTIVATED','Activated','CANCEL','Cancel','REPOSSESS','Repossess'),'-') "+//6
					" NVL(DECODE(APPLICATION_STATUS,'ACTIVATED',DECODE("+m_schema_name+".AF_CO_GET_APP_TER_STATUS(A.APPLICATION_NO),'ACTIVATED','ACTIVATED',"+m_schema_name+".AF_CO_GET_APP_TER_STATUS(A.APPLICATION_NO)),'ENTERED','ENTERED','ENT_CON','COMPLETED','VERIFY1','CREDIT VERIFICATION','V-APP','CREDIT SCORE APPROVAL','VERIFY-M','CREDIT APPROVAL 1','VERIFY2','CREDIT APPROVAL 2','VERIFYL','ENTERED LEASING NO'),'-') "+//add by malik on 7-10-2008 Modified by Dineth on 15-06-2009
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
					" WHERE A.CLIENT_CODE=B.CLIENT_CODE AND "+
					" UPPER(A.FINANCE_NO)=UPPER('"+m_finance_no+"') ";
				*/
		
				
				
			//	boolean more=rs.next();
			
				
			
				String sysdate= "SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY'),TO_CHAR(SYSDATE,'HH'),TO_CHAR(SYSDATE,'MI'),TO_CHAR(SYSDATE,'AM') FROM DUAL";
				
				// added by udara 27-02-2015
				String report_date = " "+
					" SELECT "+
						" TO_CHAR(ENT_DATE,'DD'), "+
						" TO_CHAR(ENT_DATE,'MM'), "+
						" TO_CHAR(ENT_DATE,'YYYY'), "+
						" TO_CHAR(ENT_DATE,'HH'), "+
						" TO_CHAR(ENT_DATE,'MI'), "+
						" TO_CHAR(ENT_DATE,'AM') "+
							" FROM AF_MK_VEHICLE_INSPEC_DOC "+
							" WHERE FINANCE_NO = '"+m_application_no+"' "+
					" ";
				// end by udara 27-02-2015
				
				String m_cur_date = "";
				String m_cur_month = "";
				String m_cur_year = "";
				String m_cur_hour = "";
				String m_cur_min = "";
				String m_time = "";
				
				//rs=stmt.executeQuery(sysdate); // commented by udara 27-02-2015
				rs=stmt.executeQuery(report_date); // added by udara 27-02-2015
				
				boolean more12=rs.next();
				if(more12){
					m_cur_date = rs.getString(1);
					m_cur_month = rs.getString(2);
					m_cur_year = rs.getString(3);
					m_cur_hour = rs.getString(4);
					m_cur_min = rs.getString(5);
					m_time = rs.getString(6);
				}
				
			
				
				
				String client_info= "SELECT "+m_schema_name+".AF_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+m_schema_name+".AF_CO_GET_CLIENT_FULL_ADDRESS(A.CLIENT_CODE) FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A WHERE A.FINANCE_NO = '"+m_finance_no+"' ";
				String m_client_name_new = "";
				String m_client_add = "";
				
				rs=stmt.executeQuery(client_info);
				boolean more13=rs.next();
				if(more13){
					m_client_name_new = rs.getString(1);
					m_client_add = rs.getString(2);
				}
				
			
				
				
			String vehicle_info= "SELECT "+
					"A.ASSET_ID,  "+
					"B.MAKE_CODE, "+
					"INITCAP(B.MAKE_DESC),  "+
					"A.MODEL_CODE,  "+
					"INITCAP(C.DESCRIPTION),  "+
					"A.SUB_MODEL_CODE,  "+
					"INITCAP(D.DESCRIPTION), "+
					"A.STATUS,  "+
					"A.COST,   "+
					"A.PURPOSE,   "+
					"A.QTY,   "+
					"E.DESCRIPTION ,  "+
					"D.YEAR_OF_MANUFACTURE   "+
					"FROM  "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS A,  "+
					""+m_schema_name+".AF_CO_MAS_MAKE B,  "+
					""+m_schema_name+".AF_CO_MAS_MODEL C,  "+
					""+m_schema_name+".AF_CO_MAS_SUB_MODLE D,   "+
					""+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY E  "+
					"WHERE A.MODEL_CODE=C.MODEL_CODE AND  "+
					"D.SUB_CODE=A.SUB_MODEL_CODE AND   "+
					"C.MAKE_CODE=B.MAKE_CODE AND  "+
					//"A.APPLICATION_NO =('"+m_application_no+"') AND  "+
					"A.APPLICATION_NO = "+m_schema_name+".AF_CO_GET_APPLICATION_NO('"+m_application_no+"') AND  "+
					"C.ITEM_SUB_CAT(+)=E.ITEM_SUB_CAT ";
				
				String m_vehicle_make = "";
				String m_vehicle_model = "";
				String m_vehicle_year = "";
				
				//out.println(vehicle_info);
				
				rs=stmt.executeQuery(vehicle_info);
				boolean more14=rs.next();
				if(more14){
					m_vehicle_make = rs.getString(3);
					m_vehicle_model = rs.getString(5);
					m_vehicle_year = rs.getString(13);
				}
				
				
				
				
				String vehicle_info_new= "SELECT A.INVOICE_NO,NVL(A.APPLICATION_NO,'-'),NVL(A.ASSET_ID,'-'),NVL(A.ENGINE_NO,'-'),NVL(A.MODEL_CODE,'-'), "+
					"NVL(A.CHASSIS_NO,'-'),NVL(A.REG_NO,'-'),NVL(TO_CHAR(A.REG_DATE,'DD-MM-YYYY'),'-'),NVL(A.PRICING_NO,'-'),NVL(A.SUB_MODEL_CODE,'-'),NVL(A.COLOUR,'-'),NVL(A.SEATING_CAPACITY,0),NVL(A.NET_PRICE,0), "+
					"NVL(A.VAT,0),NVL(A.TOTAL_AMOUNT,0),NVL(A.TO_BE_DELIVERD_TO,'-'),NVL(A.VALUE,0),NVL(A.CURR_CODE,'-'),NVL(A.BRANCH_ID,'-'),replace(NVL(A.VENDOR_CODE,'-'),'&','$') ,  "+
					"(SELECT DECODE(Y.ADDRESS1||','||Y.ADDRESS2,',','-',Y.ADDRESS1||','||Y.ADDRESS2)  "+
					"FROM  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X,  "+m_schema_name+".AF_CO_MAS_CLIENT Y  "+
					"WHERE X.CLIENT_CODE = Y.CLIENT_CODE AND  "+
					"X.APPLICATION_NO = A.APPLICATION_NO),  "+
					"(SELECT NVL(Y.CITY_CODE,'-')  "+
					"FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X, "+m_schema_name+".AF_CO_MAS_CLIENT Y  "+
					"WHERE X.CLIENT_CODE = Y.CLIENT_CODE AND  "+
					"X.APPLICATION_NO = A.APPLICATION_NO),  "+
					"NVL( (SELECT replace(B.NAME,'&','$') FROM "+m_schema_name+".AF_CO_MAS_VENDORS B WHERE B.VENDOR_CODE=A.VENDOR_CODE),'N/A') VENDOR_NAME ,  "+
					"NVL(INVOICE_DOC_NO,'-'),NVL(FUEL_CONVERTION_STATUS,'-'),TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'),  "+
					"( SELECT B.DESCRIPTION FROM    "+m_schema_name+".AF_CO_MAS_MODEL B WHERE A.MODEL_CODE=B.MODEL_CODE) MODEL_DESC,    "+
					"( SELECT C.DESCRIPTION FROM    "+m_schema_name+".AF_CO_MAS_SUB_MODLE C WHERE C.SUB_CODE=A.SUB_MODEL_CODE ) SUB_MODEL_DESC,   "+
					"A.YEAR_OF_MANUFACTURE,  "+
					"A.EXTRAS_INCLUDED,  "+
					"NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE),' ') CITY_NAME  "+
					",NVL("+m_schema_name+".AF_CO_GET_MAKE_DESC("+m_schema_name+".AF_CO_GET_MAKE_CODE(A.MODEL_CODE)),' ') MAKE_DESC   "+
					",NVL("+m_schema_name+".AF_CO_GET_ITEM_SUB_DESC(A.MODEL_CODE),' ') ITEM_SUB_DESC    "+
					"FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
					"WHERE A.APPLICATION_NO = B.APPLICATION_NO "+
					"AND B.APPLICATION_STATUS='ACTIVATED' "+
					//"AND B.APPLICATION_NO = '"+m_application_no+"' ";
				"AND B.APPLICATION_NO = "+m_schema_name+".AF_CO_GET_APPLICATION_NO('"+m_application_no+"') ";
				
				String m_vehicle_reg_num = "";
				String m_vehicle_reg_date = "";
				String m_vehicle_eng_num = "";
				String m_vehicle_chasis_num = "";
				String m_vehicle_color = "";
				String m_invoice_num = "";
				
				rs=stmt.executeQuery(vehicle_info_new);
				//out.println(vehicle_info_new);
				boolean more15=rs.next();
				if(more15){
					m_vehicle_reg_num = rs.getString(7);
					m_vehicle_reg_date = rs.getString(8);
					m_vehicle_eng_num = rs.getString(4);
					m_vehicle_chasis_num = rs.getString(6);
					m_vehicle_color = rs.getString(11);
					m_invoice_num = rs.getString(1);
				}
				
				
				
				String vehicle_parked= "SELECT NVL(A.SUM_INSURED,0),AREA,POLICE,OWNER_ADDRESS,COLLECTON_SECURITY,LIC_AUTH,VEHICAL_AGA  "+
					" FROM "+m_schema_name+".AF_CO_SUM_INSURED_DETAILS A   "+
					" WHERE UPPER(A.INVOICE_NO)=UPPER('"+m_invoice_num+"') "; 
				
				String m_vehicle_parked = "";
				
				rs=stmt.executeQuery(vehicle_parked);
				boolean more16=rs.next();
				if(more16){
					m_vehicle_parked = rs.getString(2);
				}
				
				
				
				String vehicle_sales= "SELECT A.VALUATION_NO,A.ASSET_ID,MODEL_CODE,SUB_MODEL_CODE,NVL(REG_NO,'-'),ENGINE_NO,CHASSIS_NO,NOTES,REMARKS, "+
					"TYPE_OF_BODY,NVL(TO_CHAR(DATE_OF_REG,'DD-MM-YYYY'),'-'),VALUE,METER_READING, "+
					"TO_CHAR(VALUATION_DATE,'DD-MM-YYYY'),COLOUR,  "+
					"'','','',  "+
					"GENERAL_INDEX,SEATING_CAPACITY,NO_OF_CYLINDERS,	 "+
					" "+m_schema_name+".AF_CO_GET_FUEL_TYPE(MODEL_CODE) FUEL_TYPE,  "+
					" "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE) ITEM_CAT_CODE,NVL(A.PRO_INVOICE_NO,'-') PRO_INVOICE_NO,NVL(A.VALUER_CODE,'-') VALUER_CODE,  "+
					"NVL(A.YEAR_OF_MANUFACTURE,0) YEAR_OF_MANUFACTURE,  "+
					"NVL(A.CONDITION_OF_ASSET,'-') CONDITION_OF_ASSET,  "+
					"NVL(A.FORCED_SALES_VALUE,0) FORCED_SALES_VALUE,  "+
					" "+m_schema_name+".AF_CO_GET_VALUER_NAME(VALUER_CODE) VALUER_NAME  "+
					",(SELECT C.DESCRIPTION FROM  "+m_schema_name+".AF_CO_MAS_SUB_MODLE C WHERE C.SUB_CODE=SUB_MODEL_CODE) SUB_MODEL_DESC  "+
					",NVL("+m_schema_name+".AF_CO_GET_MAKE_DESC("+m_schema_name+".AF_CO_GET_MAKE_CODE(MODEL_CODE)),' ') MAKE_DESC  "+
					",NVL("+m_schema_name+".AF_CO_GET_ITEM_SUB_DESC(MODEL_CODE),' ') ITEM_SUB_DESC    "+
					"FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION A  "+
					"WHERE  "+
					//"A.APPLICATION_NO=UPPER('"+m_application_no+"') "; 
					"A.APPLICATION_NO= "+m_schema_name+".AF_CO_GET_APPLICATION_NO('"+m_application_no+"') "; 
				
				
				String m_vehicle_meter_reading = "";
				String m_vehicle_sales_val = "";
				String m_vehicle_forced_sales_val = "";
				String m_vehicle_fuel_type = "";
				
				rs=stmt.executeQuery(vehicle_sales);
				boolean more17=rs.next();
				if(more17){
					m_vehicle_sales_val = rs.getString(12);
					m_vehicle_forced_sales_val = rs.getString(28);
					m_vehicle_fuel_type = rs.getString(22);
					m_vehicle_meter_reading =  rs.getString(13);
				}
				
				
				/*
				File index = new File(""+img_path+"");
				index.delete();
				*/
				
				//m_path_1 = "E:\\SasiaNet\\NetAsset\\LAKDL\\Home\\vehicle_inspection\\doc_1.jpg";	// LIVE
				m_path_1 = ""+img_path+"\\doc_1.jpg"; // DEVELOPMENT
				File index = new File(m_path_1);
				index.delete();
				
				//m_path_1 = "E:\\SasiaNet\\NetAsset\\LAKDL\\Home\\vehicle_inspection\\doc_2.jpg";	// LIVE
				m_path_1 = ""+img_path+"\\doc_2.jpg";// DEVELOPMENT
				index = new File(m_path_1);
				index.delete();
				
				//m_path_1 = "E:\\SasiaNet\\NetAsset\\LAKDL\\Home\\vehicle_inspection\\doc_3.jpg";	// LIVE
				m_path_1 = ""+img_path+"\\doc_3.jpg";// DEVELOPMENT
				index = new File(m_path_1);
				index.delete();
				
				
				
				
				String inspection_documents = "SELECT DOCUMENT_1, DOCUMENT_2, DOCUMENT_3 FROM "+m_schema_name+".AF_MK_VEHICLE_INSPEC_DOC WHERE FINANCE_NO = '"+m_finance_no+"' "; 
				
				
				String m_document1 = "";
				String m_document2 = "";
				String m_document3 = "";
				
				rs=stmt.executeQuery(inspection_documents);
				boolean more53=rs.next();
				if(more53){
					m_document1 = rs.getString(1);
					m_document2 = rs.getString(2);
					m_document3 = rs.getString(3);
				}
				
				
				
			
				
				
				Blob document = null;
				String Sql_data="";
				
				
				Sql_data=
					
					" SELECT	A.DOCUMENT, A.FILE_NAME, A.DOCUMENT_NO"+
					"  FROM	"+m_schema_name+".AF_MK_DOCUMENT_UPLOAD A "+
					//"  WHERE	A.ACTIVE_STATUS = 'Y' AND A.DOCUMENT_NO = '"+m_document_no+"'";
					"  WHERE	A.ACTIVE_STATUS = 'Y' AND A.DOCUMENT_NO = '"+m_document1+"'";
				
				rs=stmt.executeQuery(Sql_data);
				//out.println(""+Sql_data+"");
				boolean more77=rs.next();
				//res.setContentType("APPLICATION/OCTET-STREAM");
				if(more77){
					
					// added by udara 11-04-2014
					m_path_1 = ""+img_path+"\\doc_1.jpg"; // DEVELOPMENT
					//m_path_1 = "E:\\SasiaNet\\NetAsset\\LAKDL\\Home\\vehicle_inspection\\doc_1.jpg"; // LIVE
					File image = new File(m_path_1);
					FileOutputStream fos = new FileOutputStream(image);
					
					// get the blob
					document = rs.getBlob(1);
					
					InputStream in = document.getBinaryStream();
					int length = (int) document.length();
					
					int bufferSize = 1024;
					byte[] buffer = new byte[bufferSize];
					
					while ((length = in.read(buffer)) != -1) {
						//out.write(buffer, 0, length);
						fos.write(buffer, 0, length);
					}
					
					in.close();
					
					fos.close();
					
					out.flush();
					
					
				}		
				
				
				
				
				Blob document_2 = null;
				String Sql_data_2="";
				
				Sql_data_2=
					
					" SELECT	A.DOCUMENT, A.FILE_NAME"+
					"  FROM	"+m_schema_name+".AF_MK_DOCUMENT_UPLOAD A "+
					"  WHERE	A.ACTIVE_STATUS = 'Y' AND A.DOCUMENT_NO = '"+m_document2+"'";
				
				rs=stmt.executeQuery(Sql_data_2);
				boolean more78=rs.next();
				if(more78){
					// added by udara 11-04-2014
					m_path_1 = ""+img_path+"\\doc_2.jpg"; // DEVELOPMENT
					//m_path_1 = "E:\\SasiaNet\\NetAsset\\LAKDL\\Home\\vehicle_inspection\\doc_2.jpg";// LIVE
					File image = new File(m_path_1);
					FileOutputStream fos = new FileOutputStream(image);
					
					// get the blob
					document = rs.getBlob(1);
					
					InputStream in = document.getBinaryStream();
					int length = (int) document.length();
					
					int bufferSize = 1024;
					byte[] buffer = new byte[bufferSize];
					
					while ((length = in.read(buffer)) != -1) {
						fos.write(buffer, 0, length);
					}
					in.close();
					fos.close();
					out.flush();
				}
				
				
				
				Blob document_3 = null;
				String Sql_data_3="";
				
				Sql_data_3=
					
					" SELECT	A.DOCUMENT, A.FILE_NAME"+
					"  FROM	"+m_schema_name+".AF_MK_DOCUMENT_UPLOAD A "+
					"  WHERE	A.ACTIVE_STATUS = 'Y' AND A.DOCUMENT_NO = '"+m_document3+"'";
				
				rs=stmt.executeQuery(Sql_data_3);
				boolean more79=rs.next();
				if(more79){
					// added by udara 11-04-2014
					m_path_1 = ""+img_path+"\\doc_3.jpg"; // DEVELOPMENT
					//m_path_1 = "E:\\SasiaNet\\NetAsset\\LAKDL\\Home\\vehicle_inspection\\doc_3.jpg";// LIVE
					File image = new File(m_path_1);
					FileOutputStream fos = new FileOutputStream(image);
					
					// get the blob
					document = rs.getBlob(1);
					
					InputStream in = document.getBinaryStream();
					int length = (int) document.length();
					
					int bufferSize = 1024;
					byte[] buffer = new byte[bufferSize];
					
					while ((length = in.read(buffer)) != -1) {
						fos.write(buffer, 0, length);
					}
					in.close();
					fos.close();
					out.flush();
					
					
				}
				
			
				
				
				
		       String RevenueLi_ok ="";
				String RevenueLi_na  ="";
				String Insurance_ok  ="";
				String Insurance_na  ="";
				String engin_ori_pht  ="";
				String chasi_ori_pht  ="";
				String body_paint_pht  ="";
				String hood_pht    ="";
				String reg_no_pht  ="";
				String company_name  ="";
				String body_kit  ="";
				String Specify  ="";
				String Last_Serviced_date  ="";
				String Remarks_1  ="";
				String Remarks_2 ="";
				String Date_con  ="";
				String text_1  ="";
				String text_2 ="";
				String text_3  ="";
				String text_dd  ="";
				String text_mm ="";
				String text_yy  ="";
				
				String text_dd_con  ="";
				String text_mm_con ="";
				String text_yy_con  ="";
				
				String ckeck_inspec_1  ="";
				String ckeck_inspec_2  ="";
				String ckeck_inspec_3  ="";
				String ckeck_inspec_4  ="";
				
				String comments   ="";
				
				/*String view_inspection_other =  " SELECT  FINANCE_NO,NVL(REVENUE_OK, NVL(REVENUE_NA,'-'),NVL(INSURA_OK,'-'),NVL(INSURA_NA,'-'),NVL(ENGIN_ORI_PHT,'-'), NVL(CHASSI_ORI_PHT,'-'),NVL(BODY_PAINT_PHT,'-'),NVL(HOOD_PHT,'-'),NVL(REG_NO_PHT,'-'),  "+
													" COMPANY_NAME, BODY_KIT,SPECIFY,LAST_SERVICE_DATE, REMARK1, REMARK2,DATE_CON,TEXT1,TEXT2,TEXT3,TRUNC(LAST_SERVICE_DATE,'DD'),TRUNC(LAST_SERVICE_DATE,'MM'),TRUNC(LAST_SERVICE_DATE,'YYYY'),TRUNC(DATE_CON,'DD'),TRUNC(DATE_CON,'MM'),TRUNC(DATE_CON,'YYYY') "+
													" FROM  AF_MK_VEHICLE_INSPEC_DET_OTHER WHERE FINANCE_NO = '"+m_finance_no+"' "; 
				*/
				
				
				// commented by udara 10-10-2014
				/*
				String view_inspection_other =  " SELECT  FINANCE_NO,NVL(REVENUE_OK,'-'), NVL(REVENUE_NA,'-'),NVL(INSURA_OK,'-'),NVL(INSURA_NA,'-'),NVL(ENGIN_ORI_PHT,'-'), NVL(CHASSI_ORI_PHT,'-'),NVL(BODY_PAINT_PHT,'-'),NVL(HOOD_PHT,'-'),NVL(REG_NO_PHT,'-'),  "+
													" COMPANY_NAME, NVL(BODY_KIT,'-'),NVL(SPECIFY,'-'),LAST_SERVICE_DATE, REMARK1, REMARK2,DATE_CON,TEXT1,TEXT2,TEXT3,TO_CHAR(LAST_SERVICE_DATE,'DD'),TO_CHAR(LAST_SERVICE_DATE,'MM'),TO_CHAR(LAST_SERVICE_DATE,'YYYY'),TO_CHAR(DATE_CON,'DD'),TO_CHAR(DATE_CON,'MM'),TO_CHAR(DATE_CON,'YYYY'),NVL(INSPEC1,'-'),NVL(INSPEC2,'-'),NVL(INSPEC3,'-'),NVL(INSPEC4,'-'),NVL(COMMENTS,'-') "+
													" FROM  "+m_schema_name+".AF_MK_VEHICLE_INSPEC_DET_OTHER WHERE FINANCE_NO = '"+m_finance_no+"' "; 
				
				*/
				
				// added by udara 10-10-2014
				String view_inspection_other =  " SELECT  FINANCE_NO,NVL(REVENUE_OK,'-'), NVL(REVENUE_NA,'-'),NVL(INSURA_OK,'-'),NVL(INSURA_NA,'-'),NVL(ENGIN_ORI_PHT,'-'), NVL(CHASSI_ORI_PHT,'-'),NVL(BODY_PAINT_PHT,'-'),NVL(HOOD_PHT,'-'),NVL(REG_NO_PHT,'-'),  "+
													" NVL(COMPANY_NAME,'-'), "+
													" NVL(BODY_KIT,'-'),NVL(SPECIFY,'-'), "+
													" LAST_SERVICE_DATE, "+ // a date
													" NVL(REMARK1,'-'), NVL(REMARK2,'-'), "+
													" NVL(TO_CHAR(DATE_CON,'DD-MM-YYYY'),'-'), "+ // a date
													" NVL(TEXT1,'-'),NVL(TEXT2,'-'),NVL(TEXT3,'-'), "+
													" NVL(TO_CHAR(LAST_SERVICE_DATE,'DD'),'-'),NVL(TO_CHAR(LAST_SERVICE_DATE,'MM'),'-'),NVL(TO_CHAR(LAST_SERVICE_DATE,'YYYY'),'-'), "+ // a date
													" NVL(TO_CHAR(DATE_CON,'DD'),'-'),NVL(TO_CHAR(DATE_CON,'MM'),'-'),NVL(TO_CHAR(DATE_CON,'YYYY'),'-'), "+ // a date
													" NVL(INSPEC1,'-'),NVL(INSPEC2,'-'),NVL(INSPEC3,'-'),NVL(INSPEC4,'-'),NVL(COMMENTS,'-') "+
													" FROM  "+m_schema_name+".AF_MK_VEHICLE_INSPEC_DET_OTHER WHERE FINANCE_NO = '"+m_finance_no+"' "; 
				
				/*String view_inspection_other =  " SELECT  FINANCE_NO,REVENUE_OK, REVENUE_NA,INSURA_OK,INSURA_NA,ENGIN_ORI_PHT,CHASSI_ORI_PHT,BODY_PAINT_PHT,HOOD_PHT,REG_NO_PHT,  "+
													" COMPANY_NAME, BODY_KIT,SPECIFY,LAST_SERVICE_DATE, REMARK1, REMARK2,DATE_CON,TEXT1,TEXT2,TEXT3,TO_CHAR(LAST_SERVICE_DATE,'DD'),TO_CHAR(LAST_SERVICE_DATE,'MM'),TO_CHAR(LAST_SERVICE_DATE,'YYYY'),TO_CHAR(DATE_CON,'DD'),TO_CHAR(DATE_CON,'MM'),TO_CHAR(DATE_CON,'YYYY'),NVL(INSPEC1,'-'),NVL(INSPEC2,'-'),NVL(INSPEC3,'-'),NVL(INSPEC4,'-'),COMMENTS "+
													" FROM  AF_MK_VEHICLE_INSPEC_DET_OTHER WHERE FINANCE_NO = '"+m_finance_no+"' "; 
				
				*/
				
				
				rs=stmt.executeQuery(view_inspection_other);
			
				boolean more54=rs.next();
				if(more54){
					
		       	RevenueLi_ok=rs.getString(2);
				RevenueLi_na=rs.getString(3);
				Insurance_ok=rs.getString(4);
				Insurance_na=rs.getString(5);
				engin_ori_pht=rs.getString(6);
				chasi_ori_pht=rs.getString(7);
				body_paint_pht=rs.getString(8);
				hood_pht=rs.getString(9);
				reg_no_pht=rs.getString(10);
				
				company_name=rs.getString(11);
				body_kit=rs.getString(12);
				Specify=rs.getString(13);
				Last_Serviced_date=rs.getString(14);
				Remarks_1=rs.getString(15);
				Remarks_2=rs.getString(16);
				Date_con=rs.getString(17);
				text_1=rs.getString(18);
				text_2=rs.getString(19);
				text_3=rs.getString(20);
				text_dd=rs.getString(21);
				text_mm=rs.getString(22);
				text_yy=rs.getString(23);
				text_dd_con=rs.getString(24);
					text_mm_con=rs.getString(25);
					text_yy_con=rs.getString(26);
					
					ckeck_inspec_1=rs.getString(27);
					ckeck_inspec_2=rs.getString(28);
					ckeck_inspec_3=rs.getString(29);
					ckeck_inspec_4=rs.getString(30);
					comments=rs.getString(31);
				
				}
				
				
				
				
					rs.close();
				
			/*	out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Marketing - Change Proforma Invoice </TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">");
			*/
				
				
				
				
				/*out.println("function save_report_details(m_finance_no){");
				out.println("		         if(confirm(\"Are you sure you want to save?\")){ "); 
			out.println("		          document.Form1.action=\""+m_class_url+"/"+m_schema_name+"_AF_MISF_save_inspection_report_details?FIN_NO=\"+m_finance_no+\"\";");  
			out.println("		          document.Form1.submit();	"); 
			out.println("		         }"); 
				out.println("}");*/
				
		
			
			
				
				/*
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD='assign_data("+m_finance_no+")'>"); 
				out.println("<FORM NAME='Form1' method='post'>");
				
				*/
				
				out.println("	<table frame='VOID' cols='5' rules='NONE' cellspacing='0' border='0' style='font-size:130%' >	");
				out.println("	    <colgroup>	");
				out.println("	        <col width='20%'/>	");
				out.println("	        <col width='20%'/>	");
				out.println("	        <col width='1%'/>	");
				out.println("	        <col width='15%'/>	");
				out.println("	        <col width='44%'/>	");
				out.println("	    </colgroup>	");
				out.println("	    <tbody>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20' width='20%'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT' width='20%'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT' width='1%'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT' width='15%'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT' width='44%'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Date: "+m_cur_date+"/"+m_cur_month+"/"+m_cur_year+"	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                Time: "+m_cur_hour+" : "+m_cur_min+" "+m_time+" ");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Vehicle Owner's Name s per NIC: "+m_client_name_new+" ");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Permanent Address: "+m_client_add+"	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Permanent Location the vehicle is parked: "+m_vehicle_parked+"	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                Vehicle Make: "+m_vehicle_make+"	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                Model: "+m_vehicle_model+ " Year: "+m_vehicle_year+"	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='27'>	");
				out.println("	                Odometer Reading: "+m_vehicle_meter_reading+"	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='21'>	");
				out.println("	                Registration # "+m_vehicle_reg_num+"	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                Registration Date: "+m_vehicle_reg_date+"	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='18'>	");
				out.println("	                Engine #: "+m_vehicle_eng_num+"	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                Chassis #  "+m_vehicle_chasis_num+"	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='18'>	");
				out.println("	                Fuel Type: "+m_vehicle_fuel_type+"	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                Color: "+m_vehicle_color+"	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='18'>	");
				out.println("	                Market Value: "+m_vehicle_sales_val+"	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                Forced Sale Value: "+m_vehicle_forced_sales_val+"	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				
				//out.println("	                Valid: Revenue License  Yes <input class='' type='checkbox' name='RevenueLi_ok'  VALUE = '' > No<input class='' type='checkbox' name='RevenueLi_na'  VALUE = '' >  Insurance   Yes<input class='' type='checkbox' name='Insurance_ok'  VALUE = '' >    No<input class='' type='checkbox' name='Insurance_na'  VALUE = '' >");
				
				
				
				if(RevenueLi_ok.equals("N")){
				out.println("	                Valid: Revenue License  Yes <input class='' type='checkbox' name='RevenueLi_ok' id='RevenueLi_ok' checked='true'  VALUE = '' >  ");
			    }else{
				out.println("	                Valid: Revenue License  Yes <input class='' type='checkbox' name='RevenueLi_ok' id='RevenueLi_ok'  VALUE = '' >  ");
				}
				
				
				if(RevenueLi_na.equals("N")){
				out.println("	                No<input class='' type='checkbox' name='RevenueLi_na' id='RevenueLi_na' checked='true'  VALUE = '' > 	");
			    }else{
				out.println("	                No<input class='' type='checkbox' name='RevenueLi_na' id='RevenueLi_na'  VALUE = '' > 	");
				}
				//out.println("	                No<input class='' type='checkbox' name='RevenueLi_na'  VALUE = '' > 	");
				
				
				if(Insurance_ok.equals("N")){
				out.println("	                Insurance   Yes<input class='' type='checkbox' name='Insurance_ok' id='Insurance_ok'  checked='true'  VALUE = '' >  	");
				}else{
				out.println("	                 Insurance   Yes<input class='' type='checkbox' name='Insurance_ok' id='Insurance_ok'  VALUE = '' >  	");
				}
				//out.println("	                 Insurance   Yes<input class='' type='checkbox' name='Insurance_ok'  VALUE = '' >  	");
				
				
				if(Insurance_na.equals("N")){
				out.println("	                 No<input class='' type='checkbox' name='Insurance_na' id='Insurance_na'  checked='true'  VALUE = '' >	");
				}else{
				out.println("	                  No<input class='' type='checkbox' name='Insurance_na' id='Insurance_na' VALUE = '' >	");
			   
				}
				//out.println("	                  No<input class='' type='checkbox' name='Insurance_na'  VALUE = '' >	");
			   
				
				
				
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Please check any item that needs attention and then include additional details under the comments section below.	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                Start the Engine and Test the Following: (Instructor needs to use a red pen)	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <strong><u>Unusual Noises:</u></strong>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Noises	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' name='check_ok_1'  VALUE = 'N' > Needs Attention <input class='' type='checkbox' name='need_att_1'  VALUE = 'N' ><input class='' type='hidden' name='hidd_name_1'  VALUE = 'NOISES' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <strong>	");
				out.println("	                    <u>	");
				out.println("	                        <br/>	");
				out.println("	                    </u>	");
				out.println("	                </strong>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <strong>Gauges:</strong>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Fuel	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' name='check_ok_2' onClick='val_change()' VALUE = 'N' > Needs Attention <input class='' type='checkbox' name='need_att_2' onClick='val_change()' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_2' onClick='val_change()' VALUE = 'FUEL' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Temperature	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' name='check_ok_3' onClick='val_change()' VALUE = 'N' > Needs Attention <input class='' type='checkbox' name='need_att_3' onClick='val_change()' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_3' onClick='val_change()' VALUE = 'TEMP' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Dashboard Warning Light	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' name='check_ok_4' onClick='val_change()' VALUE = 'N' > Needs Attention <input class='' type='checkbox' name='need_att_4' onClick='val_change()' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_4' onClick='val_change()' VALUE = 'DASHBORD' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <strong><u>Lights:</u></strong>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Headlights	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' name='check_ok_5' onClick='val_change()' VALUE = 'N' > Needs Attention <input class='' type='checkbox' name='need_att_5' onClick='val_change()' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_5' onClick='val_change()' VALUE = 'HEADLIGHT' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Break Lights	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' name='check_ok_6' onClick='val_change()' VALUE = 'N' > Needs Attention <input class='' type='checkbox' name='need_att_6' onClick='val_change()' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_6' onClick='val_change()' VALUE = 'BREAKELIGHT' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Turn Signals	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' name='check_ok_7' onClick='val_change()' VALUE = 'N' > Needs Attention <input class='' type='checkbox' name='need_att_7' onClick='val_change()' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_7' onClick='val_change()' VALUE = 'TURNSIG' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Hazard Lights	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' name='check_ok_8' onClick='val_change()' VALUE = 'N'> Needs Attention <input class='' type='checkbox' name='need_att_8' onClick='val_change()' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_8' onClick='val_change()' VALUE = 'HAZARDLIG' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <strong><u>Other:</u></strong>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Windshield Wipers	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' name='check_ok_9' onClick='val_change()' VALUE = 'N' > Needs Attention <input class='' type='checkbox' name='need_att_9' onClick='val_change()' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_9' onClick='val_change()' VALUE = 'WIPERS' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Engine # in Original look &amp; feel	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				
				if(engin_ori_pht.equals("N")){
				out.println("	                OK <input class='' type='checkbox' name='check_ok_10' onClick='val_change()' VALUE = 'N' > Needs Attention <input class='' type='checkbox' name='need_att_10' onClick='val_change()' VALUE = 'N' > Photograph taken <input class='' type='checkbox' name='engin_ori_pht' checked='true'  VALUE = '' ><input class='' type='hidden' name='hidd_name_10' onClick='val_change()' VALUE = 'ENGINNO' >	");
				}
				else{
				out.println("	                OK <input class='' type='checkbox' name='check_ok_10' onClick='val_change()' VALUE = 'N' > Needs Attention <input class='' type='checkbox' name='need_att_10' onClick='val_change()' VALUE = 'N' > Photograph taken <input class='' type='checkbox' name='engin_ori_pht'  VALUE = '' ><input class='' type='hidden' name='hidd_name_10' onClick='val_change()' VALUE = 'ENGINNO' >	");
				
				}
				
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Chassis # in Original look &amp; feel	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				
				if(chasi_ori_pht.equals("N")){
				
				out.println("	                OK <input class='' type='checkbox' name='check_ok_11' onClick='val_change()' VALUE = 'N' > Needs Attention <input class='' type='checkbox' name='need_att_11' onClick='val_change()' VALUE = 'N' > Photograph Taken <input class='' type='checkbox' name='chasi_ori_pht' checked='true'  VALUE = '' ><input class='' type='hidden' name='hidd_name_11' onClick='val_change()' VALUE = 'CHASSINO' >	");
				
			}else{
				out.println("	                OK <input class='' type='checkbox' name='check_ok_11' onClick='val_change()' VALUE = 'N' > Needs Attention <input class='' type='checkbox' name='need_att_11' onClick='val_change()' VALUE = 'N' > Photograph Taken <input class='' type='checkbox' name='chasi_ori_pht' onClick='val_change_ph(\""+body_paint_pht+"\",chas)' VALUE = '' ><input class='' type='hidden' name='hidd_name_11' onClick='val_change()' VALUE = 'CHASSINO' >	");
				
				}
				
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Body and Paint	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				
				if(body_paint_pht.equals("N")){
				
				out.println("	                OK <input class='' type='checkbox' name='check_ok_12' onClick='val_change()' VALUE = 'N' > Needs Attention <input class='' type='checkbox' name='need_att_12' onClick='val_change()' VALUE = 'N' > Photograph Taken <input class='' type='checkbox' name='body_paint_pht' checked='true' VALUE = '' ><input class='' type='hidden' name='hidd_name_12' onClick='val_change()' VALUE = 'BODYPAINT' >	");
			}else{
				out.println("	                OK <input class='' type='checkbox' name='check_ok_12' onClick='val_change()' VALUE = 'N' > Needs Attention <input class='' type='checkbox' name='need_att_12' onClick='val_change()' VALUE = 'N' > Photograph Taken <input class='' type='checkbox' name='body_paint_pht'  VALUE = '' ><input class='' type='hidden' name='hidd_name_12' onClick='val_change()' VALUE = 'BODYPAINT' >	");
			
				}
				
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Hood	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				
				if(hood_pht.equals("N")){
				out.println("	                OK <input class='' type='checkbox' name='check_ok_13' onClick='val_change()' VALUE = 'N' > Needs Attention <input class='' type='checkbox' name='need_att_13' onClick='val_change()' VALUE = 'N' > Photograph Taken <input class='' type='checkbox' name='hood_pht' checked='true' VALUE = '' ><input class='' type='hidden' name='hidd_name_13' onClick='val_change()' VALUE = 'HOOD' >	");
				
				}else{
				out.println("	                OK <input class='' type='checkbox' name='check_ok_13' onClick='val_change()' VALUE = 'N' > Needs Attention <input class='' type='checkbox' name='need_att_13' onClick='val_change()' VALUE = 'N' > Photograph Taken <input class='' type='checkbox' name='hood_pht'  VALUE = '' ><input class='' type='hidden' name='hidd_name_13' onClick='val_change()' VALUE = 'HOOD' >	");
				}
				
				
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Registration Number with CR	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				
				if(reg_no_pht.equals("N")){
				out.println("	                    OK <input class='' type='checkbox' name='check_ok_14'  onClick='val_change()' VALUE = 'N' > Needs Attention <input class='' type='checkbox' name='need_att_14' onClick='val_change()' VALUE = 'N' > Photograph Taken <input class='' type='checkbox' name='reg_no_pht' checked='true' VALUE = '' ><input class='' type='hidden' name='hidd_name_14' onClick='val_change()' VALUE = 'REGNO' >	");
				
				}else{
					out.println("	                OK <input class='' type='checkbox' name='check_ok_14'  onClick='val_change()' VALUE = 'N' > Needs Attention <input class='' type='checkbox' name='need_att_14' onClick='val_change()' VALUE = 'N' > Photograph Taken <input class='' type='checkbox' name='reg_no_pht' onClick='val_change()' VALUE = '' ><input class='' type='hidden' name='hidd_name_14' onClick='val_change()' VALUE = 'REGNO' >	");
				
				}
				
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Any Corporate Branding Painted	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' name='check_ok_15' onClick='val_change()' VALUE = 'N' > Needs Attention <input class='' type='checkbox' name='need_att_15' onClick='val_change()' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_15' onClick='val_change()' VALUE = 'COOPPAINT' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Company Name painted/stickered	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <input class='' type='text' name='company_name'  VALUE = '"+company_name+"' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Body-kits any modifications	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				//out.println("	                NO<input class='' type='checkbox' name='body_kit' onClick='val_change()' VALUE = 'N' > YES Specify  <input class='' type='text' name='Specify'  VALUE = '"+Specify+"' >	");
				
				if(body_kit.equals("N"))
					out.println("	                NO<input class='' type='checkbox' name='body_kit' onClick='val_change()' VALUE = 'N' checked > YES Specify  <input class='' type='text' name='Specify'  VALUE = '"+Specify+"' >	");
				else
					out.println("	                NO<input class='' type='checkbox' name='body_kit' onClick='val_change()' VALUE = 'N' > YES Specify  <input class='' type='text' name='Specify'  VALUE = '"+Specify+"' >	");
				
				
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                Brakes	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' name='check_ok_16' onClick='val_change()' VALUE = 'N' > Needs Attention <input class='' type='checkbox' name='need_att_16' onClick='val_change()' VALUE = 'N' >	<input class='' type='hidden' name='hidd_name_16' onClick='val_change()' VALUE = 'BREAKERS' >");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Parking Break	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' name='check_ok_17' onClick='val_change()' VALUE = 'N' > Needs Attention <input class='' type='checkbox' name='need_att_17' onClick='val_change()' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_17' onClick='val_change()' VALUE = 'PARKBREAK' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Mirrors	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' name='check_ok_18' onClick='val_change()' VALUE = 'N' > Needs Attention <input class='' type='checkbox' name='need_att_18' onClick='val_change()' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_18' onClick='val_change()' VALUE = 'MIRRORS' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Horn	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' name='check_ok_19' onClick='val_change()' VALUE = 'N' > Needs Attention <input class='' type='checkbox' name='need_att_19' onClick='val_change()' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_19' onClick='val_change()' VALUE = 'HORN' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Exhaust	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' name='check_ok_20' onClick='val_change()' VALUE = 'N' > Needs Attention <input class='' type='checkbox' name='need_att_20'  onClick='val_change()' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_20' onClick='val_change()' VALUE = 'EXHAUST' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Seats and Interior	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' name='check_ok_21' onClick='val_change()' VALUE = 'N' > Needs Attention <input class='' type='checkbox' name='need_att_21' onClick='val_change()' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_21' onClick='val_change()' VALUE = 'SEATINTERIOR' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Last Serviced date	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <input class='' type='checkbox' name='last_service_date' onClick='val_change()' VALUE = 'N' style={display:none;} > State date  <input class='' maxlength='2' size='2' type='text' name='start_DD'  VALUE = '"+text_dd+"' >/ <input class='' maxlength='2' size='2' type='text' name='start_MM'  VALUE = '"+text_mm+"'  >/ <input class='' maxlength='4' size='4' type='text' name='start_YY'  VALUE = '"+text_yy+"'  >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Remarks	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <input class='' type='text' name='Remarks_1'  VALUE = '"+Remarks_1+"' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <strong>	");
				out.println("	                    <br/>	");
				out.println("	                </strong>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <strong><u>Tires:</u></strong>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Proper Inflation	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' name='check_ok_22' onClick='val_change()' VALUE = 'N' > Needs Attention <input class='' type='checkbox' name='need_att_22' onClick='val_change()' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_22' onClick='val_change()' VALUE = 'PROPINFLATION' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Adequate Tread	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' name='check_ok_23' onClick='val_change()' VALUE = 'N' > Needs Attention <input class='' type='checkbox' name='need_att_23' onClick='val_change()' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_23' onClick='val_change()' VALUE = 'ADEQUATETREAD' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Spare Inflated	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' name='check_ok_24' onClick='val_change()' VALUE = 'N' > Needs Attention <input class='' type='checkbox' name='need_att_24' onClick='val_change()' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_24' onClick='val_change()' VALUE = 'SPAREINFLATED' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <strong><u>Leaks:</u></strong>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                Oil	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' name='check_ok_25' onClick='val_change()' VALUE = 'N' > Needs Attention <input class='' type='checkbox' name='need_att_25' onClick='val_change()' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_25' onClick='val_change()' VALUE = 'OIL' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Other	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				//out.println("	                OK <input class='' type='checkbox' name='check_ok_26' onClick='val_change()' VALUE = 'N' > Needs Attention <input class='' type='checkbox' name='need_att_26' onClick='val_change()' VALUE = 'N' >   Remarks <input class='' type='text' name='Remarks' VALUE = '' ><input class='' type='hidden' name='hidd_name_26' onClick='val_change()' VALUE = 'OTHER' >");
				out.println("	                OK <input class='' type='checkbox' name='check_ok_26' onClick='val_change()' VALUE = 'N' > Needs Attention <input class='' type='checkbox' name='need_att_26' onClick='val_change()' VALUE = 'N' >   Remarks <input class='' type='text' name='Remarks_2' VALUE = '"+Remarks_2+"' ><input class='' type='hidden' name='hidd_name_26' onClick='val_change()' VALUE = 'OTHER' >");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <strong><u>Safety Equipment:</u></strong>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Fire Extinguisher	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' name='check_ok_27' onClick='val_change()' VALUE = 'N' > Needs Attention <input class='' type='checkbox' name='need_att_27' onClick='val_change()' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_27' onClick='val_change()' VALUE = 'EXHUSTER' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                First Aid Kit	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' name='check_ok_28' onClick='val_change()' VALUE = 'N' > Needs Attention <input class='' type='checkbox' name='need_att_28' onClick='val_change()' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_28' onClick='val_change()' VALUE = 'FIRSTAIDKID' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Flares	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' name='check_ok_29' onClick='val_change()' VALUE = 'N' > Needs Attention <input class='' type='checkbox' name='need_att_29' onClick='val_change()' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_29' onClick='val_change()' VALUE = 'FLARES' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                Spare Bulbs/Fuses	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' name='check_ok_30' onClick='val_change()' VALUE = 'N' > Needs Attention <input class='' type='checkbox' name='need_att_30' onClick='val_change()' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_30' onClick='val_change()' VALUE = 'SPAREFUES' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                GPS	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' name='check_ok_31' onClick='val_change()' VALUE = 'N' > Needs Attention <input class='' type='checkbox' name='need_att_31' onClick='val_change()' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_31' onClick='val_change()' VALUE = 'GPS' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                Seat Belts	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                OK <input class='' type='checkbox' name='check_ok_32' onClick='val_change()' VALUE = 'N' > Needs Attention <input class='' type='checkbox' name='need_att_32' onClick='val_change()' VALUE = 'N' ><input class='' type='hidden' name='hidd_name_32' onClick='val_change()' VALUE = 'SEATBELT' >	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				
				
				
				// udara start 11-04-2014
				/*
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT'> ");
				out.println("	                	<img src='https://dev-lakdl.sasianet.com/vehicle_inspection/java_1.gif' height=\"100\" width=\"100\" > "); 
				out.println("	            </td>	");
				out.println("	        </tr>	");
				*/
				out.println("	        <tr>	");
				out.println("	            <td> ");
				out.println("	                <table border=1 > ");
				
				
				//File doc_1 = new File("E:\\SasiaNet\\NetAsset\\LAKDL\\Home\\vehicle_inspection\\doc_1.jpg");//LIVE
				File doc_1 = new File(""+img_path+"\\doc_1.jpg"); //DEVELOPMENT
				
				if(doc_1.exists()){
					
					// added by udara 05-05-2015
					int width = 0;
					int height = 0;
					int new_width = 0;
					int new_height = public_height;
					try{
						BufferedImage bimg = ImageIO.read(doc_1);
						width          = bimg.getWidth();
						height         = bimg.getHeight();
						new_width = (width * new_height) / height;
					}
					catch (IOException e) {
						
					}
					// end by udara 05-05-2015
					

					out.println("	                     <td> ");
					//out.println("	                	    <img src=\""+m_html_client_url+"/vehicle_inspection/doc_1.jpg\" height=\"200\" width=\"200\" > "); // commented by udara 05-05-2015
					out.println("	                	    <img src=\""+m_html_client_url+"/vehicle_inspection/doc_1.jpg\" height=\""+new_height+"\" width=\""+new_width+"\" > "); 
					out.println("	                     </td> ");
				}
				
				//File doc_2 = new File("E:\\SasiaNet\\NetAsset\\LAKDL\\Home\\vehicle_inspection\\doc_2.jpg");//LIVE
				File doc_2 = new File(""+img_path+"\\doc_2.jpg"); //DEVELOPMENT
				if(doc_2.exists()){
					
					// added by udara 05-05-2015
					int width = 0;
					int height = 0;
					int new_width = 0;
					int new_height = public_height;
					try{
						BufferedImage bimg = ImageIO.read(doc_2);
						width          = bimg.getWidth();
						height         = bimg.getHeight();
						new_width = (width * new_height) / height;
					}
					catch (IOException e) {
						
					}
					// end by udara 05-05-2015
					
					out.println("	                     <td> ");
					//out.println("	                	    <img src=\""+m_html_client_url+"/vehicle_inspection/doc_2.jpg\" height=\"200\" width=\"200\" > "); // commented by udara 05-05-2015
					out.println("	                	    <img src=\""+m_html_client_url+"/vehicle_inspection/doc_2.jpg\" height=\""+new_height+"\" width=\""+new_width+"\" > ");  // added by udara 05-05-2015
					out.println("	                     </td> ");
				}
				
				//File doc_3 = new File("E:\\SasiaNet\\NetAsset\\LAKDL\\Home\\vehicle_inspection\\doc_3.jpg");//LIVE
				File doc_3 = new File(""+img_path+"\\doc_3.jpg"); //DEVELOPMENT
				if(doc_3.exists()){
					
					
					// added by udara 05-05-2015
					int width = 0;
					int height = 0;
					int new_width = 0;
					int new_height = public_height;
					try{
						BufferedImage bimg = ImageIO.read(doc_3);
						width          = bimg.getWidth();
						height         = bimg.getHeight();
						new_width = (width * new_height) / height;
					}
					catch (IOException e) {
						
					}
					// end by udara 05-05-2015
					
					
					out.println("	                     <td> ");
					//out.println("	                	    <img src=\""+m_html_client_url+"/vehicle_inspection/doc_3.jpg\" height=\"200\" width=\"200\" > "); // commented by udara 05-05-2015
					out.println("	                	    <img src=\""+m_html_client_url+"/vehicle_inspection/doc_3.jpg\" height=\""+new_height+"\" width=\""+new_width+"\" > "); // added by udara 05-05-2015
					out.println("	                     </td> ");
				}
				
				out.println("	                </table> ");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				// udara end 11-04-2014
				
				
				
				
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <strong><u>Comments:</u></strong>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	               <input class='' type='text' name='text_comment' maxlength='100' size='100'  VALUE = '"+comments+"' >	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				//out.println("	                 <input class='' type='text' name='text_comment1' maxlength='100' size='100'  VALUE = '' >");
				out.println("	            </td>	");
				
				
				
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <strong><u>Condition of Vehicle Following the Inspection:</u></strong>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='18'>	");
				
				if(ckeck_inspec_1.equals("N")){
				out.println("	                <input class='' type='checkbox' id='ckeck_inspec_1' VALUE = '' onclick='selectOnlyThis(this.id);' checked='true' > Excellent Condition: (A+) Rating, the condition is nearly new and overall ratings are satisfactory.	");
				}else{
				out.println("	                <input class='' type='checkbox' id='ckeck_inspec_1' VALUE = '' onclick='selectOnlyThis(this.id);' > Excellent Condition: (A+) Rating, the condition is nearly new and overall ratings are satisfactory.	");
				}
				
				
				//out.println("	                <input class='' type='checkbox' id='ckeck_inspec_1' VALUE = '' onclick='selectOnlyThis(this.id);' > Excellent Condition: (A+) Rating, the condition is nearly new and overall ratings are satisfactory.	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='18'>	");
				
				if(ckeck_inspec_2.equals("N")){
				out.println("	                <input class='' type='checkbox' id='ckeck_inspec_2' VALUE = '' onclick='selectOnlyThis(this.id);' checked='true' > Acceptable: (A) Rating, the vehicle is in Good Condition and is functional and can fetch a standard market value.	");
				}else{
				out.println("	                <input class='' type='checkbox' id='ckeck_inspec_2' VALUE = '' onclick='selectOnlyThis(this.id);' > Acceptable: (A) Rating, the vehicle is in Good Condition and is functional and can fetch a standard market value.	");
				}
				
				//out.println("	                <input class='' type='checkbox' id='ckeck_inspec_1' VALUE = '' onclick='selectOnlyThis(this.id);' > Acceptable: (A) Rating, the vehicle is in Good Condition and is functional and can fetch a standard market value.	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='18'>	");
				
				if(ckeck_inspec_3.equals("N")){
				out.println("	                <input class='' type='checkbox' id='ckeck_inspec_3' VALUE = '' onclick='selectOnlyThis(this.id);'  checked='true' > Needs Attention: (A-) Rating, the roadworthiness of the vehicle is in question and may lead to loss of revenue days.	");
				}else{
				out.println("	                <input class='' type='checkbox' id='ckeck_inspec_3' VALUE = '' onclick='selectOnlyThis(this.id);' > Needs Attention: (A-) Rating, the roadworthiness of the vehicle is in question and may lead to loss of revenue days.	");
				}
				
				
				//out.println("	                <input class='' type='checkbox' id='ckeck_inspec_1' VALUE = '' onclick='selectOnlyThis(this.id);' > Needs Attention: (A-) Rating, the roadworthiness of the vehicle is in question and may lead to loss of revenue days.	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='18'>	");
				
				if(ckeck_inspec_4.equals("N")){
				out.println("	                <input class='' type='checkbox' id='ckeck_inspec_4' VALUE = '' onclick='selectOnlyThis(this.id);' checked='true'> Inspection Failed: (X) Rating, the vehicle condition is unacceptable for any facility	");
				}else{
				out.println("	                <input class='' type='checkbox' id='ckeck_inspec_4' VALUE = '' onclick='selectOnlyThis(this.id);' > Inspection Failed: (X) Rating, the vehicle condition is unacceptable for any facility	");
				}
				
				
				//out.println("	                <input class='' type='checkbox' id='ckeck_inspec_1' VALUE = '' onclick='selectOnlyThis(this.id);' > Inspection Failed: (X) Rating, the vehicle condition is unacceptable for any facility	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                ______________________________ 	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	<input class='' type='text' name='text_dd' maxlength='2' size='2' VALUE = '"+text_dd_con+"' >/<input class='' type='text' name='text_mm' maxlength='2' size='2' VALUE = '"+text_mm_con+"' >/<input class='' type='text' maxlength='4' size='4' name='text_yy' VALUE = '"+text_yy_con+"' > ");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Signature	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                Date	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                ACCOUNTABILITY STATEMENT (FILLED BY COMPANY REPRESENTATIVE)	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='JUSTIFY' height='17'>	");
				out.println("	                <strong>	");
				out.println("	                    <u>	");
				out.println("	                        <br/>	");
				out.println("	                    </u>	");
				out.println("	                </strong>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='JUSTIFY' height='17'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                I <input class='' type='text' name='text_1' VALUE = '"+text_1+"' maxlength='50' size='50' > (state name of Inspector) employee number <input class='' type='text' name='text_2' maxlength='50' size='50' VALUE = '"+text_2+"' > Take full responsibility for this	");
				out.println("	                inspection and I am aware that I am accountable and responsible for the accuracy, validity and reliability of all facts stated herein.	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <input class='' type='text' name='text_3' VALUE = '"+text_3+"' maxlength='20' size='20' >	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Inspector's Name &amp; Signature Date and time	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <strong><u>FOR OFFICE USE:</u></strong>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='25'>	");
				out.println("	                Recommended Value: ________________________________ (1st Inspector)	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='25'>	");
				out.println("	                Recommended Value: ________________________________ (2nd Inspector)	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                Remarks by Branch Manager: (Pls state any disparities and the resolution or mitigation strategies of objections/negative observations recorded	");
				out.println("	                amongst the two inspectors if any).	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='20'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	");
				out.println("	        <tr>	");
				out.println("	            <td align='LEFT' height='17'>	");
				out.println("	Signature and Name of the Branch Manager 	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	            <td align='LEFT'>Date and time	");
				out.println("	                <br/>	");
				out.println("	            </td>	");
				out.println("	        </tr>	"); 
				out.println("	    </tbody>	");
				out.println("	</table>	");
				
				out.println("<br>");
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD align='right'></TD></TR>");
				out.println("</TABLE>");
				
				
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			    out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validatepassword.js'></SCRIPT>"); 
			    out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				
				
			}
				
				
		}
			else {
				out.println("Undefined");
			}
			
			out.close();
			conn.close();
			this.destroy();
			
			
		}
		catch (Exception e) {
			try {
				conn.close();
			}catch (Exception eti) {}
			
			ByteArrayOutputStream ostr = new ByteArrayOutputStream();
			e.printStackTrace(new PrintWriter(ostr));
			
			ServletOutputStream out = res.getOutputStream();
			out.println(ostr.toString());
			out.close();
			
		}
		
		// added by udara 28-03-2016
		finally{

				try{conn.setAutoCommit(true); conn.commit(); }catch(Exception e){}
				if(conn!=null){try{conn.close(); }catch(Exception e){}}
				//if(out!=null){try{out.close();  }catch(Exception e){}}
			
		}
		// end by udara 28-03-2016
		
		
		
	}
}

