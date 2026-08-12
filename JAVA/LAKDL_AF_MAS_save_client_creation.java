//--
//SCREEN NAME:SAVE SYSTEM ADMINISTRATION - GUARANTOR
//CREATED BY: MAHELA  WICKRAMASEKARA
//DATE/TIME: 24 :11 :2006 
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MAS_save_client_creation extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt,callstmt1,callstmt2,callstmt3,callstmt4,callstmt5,callstmt6;
	CallableStatement callstmt7,callstmt8,callstmt9,callstmt10,callstmt11,callstmt12,callstmt13;
  String reqstr;
	ServletOutputStream out = null;
	int icountemp,icountbank,icountcredit,icountnon,icountfamily,icountcomp,icountba,icountsub,icountbankc,icountcreditc,icountcus,icountnonc,iarr_size_in_ex,icountaudit;
	String m_countemp,m_countbank,m_countcredit,m_countnon,m_countfamily,m_countcomp,m_countba,m_countsub,m_countbankc,m_countcreditc,m_countcus,m_countnonc,m_arr_size_in_ex,m_countaudit;
	public synchronized void  service(HttpServletRequest req, HttpServletResponse res)	throws IOException{


		try {

			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
			out = res.getOutputStream();

			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			//************************************************************	
			conn =m_sn_methods.met_user_validate(req);
			conn.setAutoCommit(false);
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
			
			String m_app_doc_lineno=(String)m_sn_methods.met_formdata(reqstr,"hid_app_doc_lineno"); 	
			int app_doc_lineno = Integer.parseInt(m_app_doc_lineno);
			
			String client_full_name="";
			
      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			String m_client_type=(String)m_sn_methods.met_formdata(reqstr,"hid_client_type"); 
			String m_screen_name =	(String)m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");				
			String m_client_cat = "INDIVIDUAL";
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			String m_fschema_name=m_sn_methods.client_name.trim();
		
			m_countemp = req.getParameter("countemp");
			icountemp = Integer.parseInt(m_countemp);
			m_countbank = req.getParameter("countbank");
			icountbank = Integer.parseInt(m_countbank);
			m_countcredit = req.getParameter("countcredit");
			icountcredit = Integer.parseInt(m_countcredit);
			m_countnon = req.getParameter("countnon");
			icountnon = Integer.parseInt(m_countnon);
			m_countfamily = req.getParameter("countfamily");
			icountfamily = Integer.parseInt(m_countfamily);
			m_countcomp = req.getParameter("countcomp");
			icountcomp = Integer.parseInt(m_countcomp);
			m_countba = req.getParameter("countba");
			icountba = Integer.parseInt(m_countba);
			m_countsub = req.getParameter("countsub");
			icountsub = Integer.parseInt(m_countsub);
			m_countbankc = req.getParameter("countbankc");
			icountbankc = Integer.parseInt(m_countbankc);
			m_countcreditc = req.getParameter("countcreditc");
			icountcreditc = Integer.parseInt(m_countcreditc);
			m_countcus = req.getParameter("countcus");
			icountcus = Integer.parseInt(m_countcus);
			m_countnonc = req.getParameter("countnonc");
			icountnonc = Integer.parseInt(m_countnonc);
			
			m_countaudit = req.getParameter("countaudit");
			icountaudit = Integer.parseInt(m_countaudit);

			m_arr_size_in_ex = req.getParameter("arr_size_in_ex");
			iarr_size_in_ex = Integer.parseInt(m_arr_size_in_ex);
			
			String client_type = m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_TYPE");
			String m_client_code = "";	
			String client_code = m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE");
			
			String m_inq_no = m_sn_methods.met_formdata(reqstr,"TXT_INQUARY_NO");
			
			
			
			String m_close="";
			String m_save_close="";
			String m_client_code_update="";
			String m_APP_NO="";
			//int m_no_of_rec=0;
			String m_no_of_rec="";
			m_close = req.getParameter("m_screen");
			//m_save_close = req.getParameter("save_close");
			
			if(req.getParameter("APP_NO")!=null){ //added by nuwan de silva 26-06-07
			//m_APP_NO=req.getParameter("APP_NO");
			m_save_close = req.getParameter("save_close");
			}
			
			
			if(req.getParameter("APP_NO")!=null){ //added by nuwan de silva 26-06-07
			m_APP_NO=req.getParameter("APP_NO");
			}
			if(req.getParameter("no_of_rec")!=null){ //added by nuwan de silva 27-06-07
			//m_no_of_rec=Integer.parseInt(req.getParameter("no_of_rec"));
			m_no_of_rec=req.getParameter("no_of_rec");
			}
			
				if(client_type.equals("I"))
			{
			
				client_full_name = m_sn_methods.met_formdata(reqstr,"TXT_FULL_NAME_I");
			}
			else if(client_type.equals("C"))
			{
			 
				client_full_name = m_sn_methods.met_formdata(reqstr,"TXT_FULL_NAME_C");
			}
			
			
			
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_CLIENT_DEL_TMP(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28,:29,:30,:31,:32,:33,:34,:35,:36,:37,:38,:39,:40,:41,:42,:43,:44,:45,:46,:47,:48,:49,:50,:51,:52,:53,:54,:55,:56,:57,:58,:59,:60,:61,:62,:63,:64,:65,:66,:67,:68,:69,:70,:71,:72,:73,:74,:75,:76,:77,:78,:79,:80); END;");
			
		  if(client_code.equals("")){
           callstmt.registerOutParameter(1,java.sql.Types.CHAR);	
          }
			else
					{
						callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE")).trim());			
					}
		
			//callstmt.setString(1,"");
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_TYPE"));
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_TITLE"));
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_FIRST_NAME"));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_SURNAME"));
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_INITIALS").toUpperCase());
			if(client_type.equals("I"))
			{
			 callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_FULL_NAME_I"));
			}
			else if(client_type.equals("C"))
			{
			 callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_FULL_NAME_C"));
			}
			callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_OTHER_NAME"));
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_RESIDENTIAL_STATUS"));
			callstmt.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_TEL_NO"));
			if(client_type.equals("I"))
			{
			  callstmt.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_ADDRESS1_HOME"));
			  callstmt.setString(12,m_sn_methods.met_formdata(reqstr,"TXT_ADDRESS2_HOME"));
			}	
			else if(client_type.equals("C"))
			{
			  callstmt.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_ADDRESS1_COR"));
			  callstmt.setString(12,m_sn_methods.met_formdata(reqstr,"TXT_ADDRESS2_COR"));
			}
			callstmt.setString(13,m_sn_methods.met_formdata(reqstr,"TXT_OFFICE_TEL_NO"));
			callstmt.setString(14,m_sn_methods.met_formdata(reqstr,"TXT_FAX_NO"));
			callstmt.setString(15,m_sn_methods.met_formdata(reqstr,"TXT_MOBILE_NO"));
			callstmt.setString(16,m_sn_methods.met_formdata(reqstr,"TXT_EMAIL"));
			
			//callstmt.setString(17,m_sn_methods.met_formdata(reqstr,"TXT_DURATION_AT_YEARS"));
			
			String m_du_year = m_sn_methods.met_formdata(reqstr,"TXT_DURATION_AT_YEARS");
			if(m_du_year.equals("-")){
			callstmt.setString(17,"");
			}
			else
			{
			callstmt.setString(17,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_DURATION_AT_YEARS")));
			}
			
			//callstmt.setString(18,m_sn_methods.met_formdata(reqstr,"TXT_DURATION_AT_MONTHS"));
			
			String m_du_month = m_sn_methods.met_formdata(reqstr,"TXT_DURATION_AT_MONTHS");
			if(m_du_month.equals("-")){
			callstmt.setString(18,"");
			}
			else
			{
			callstmt.setString(18,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_DURATION_AT_MONTHS")));
			}
			
			
			callstmt.setString(19,m_sn_methods.met_formdata(reqstr,"TXT_EMP_NAME"));
			callstmt.setString(20,m_sn_methods.met_formdata(reqstr,"TXT_EMP_ADDRESS1"));
			callstmt.setString(21,m_sn_methods.met_formdata(reqstr,"TXT_EMP_ADDRESS2"));
			callstmt.setString(22,m_sn_methods.met_formdata(reqstr,"TXT_EMP_REFERENCE"));
			callstmt.setString(23,m_sn_methods.met_formdata(reqstr,"TXT_EMP_RDESIGNATION"));
			callstmt.setString(24,m_sn_methods.met_formdata(reqstr,"TXT_EMP_TEL_NO"));
			callstmt.setString(25,m_sn_methods.met_formdata(reqstr,"TXT_EMP_FAX_NO"));
			callstmt.setString(26,m_sn_methods.met_formdata(reqstr,"TXT_NAME_REL"));
			callstmt.setString(27,m_sn_methods.met_formdata(reqstr,"TXT_ADDRESS1_REL"));
			callstmt.setString(28,m_sn_methods.met_formdata(reqstr,"TXT_ADDRESS2_REL"));
			callstmt.setString(29,m_sn_methods.met_formdata(reqstr,"TXT_RELATIONSHIP"));
			callstmt.setString(30,m_sn_methods.met_formdata(reqstr,"TXT_HOME_TEL_NO"));
			callstmt.setString(31,m_sn_methods.met_formdata(reqstr,"TXT_OFFICE_TEL_NO_REL")); 
			callstmt.setString(32,m_sn_methods.met_formdata(reqstr,"TXT_MOBILE_NO_REL"));
			callstmt.setString(33,m_sn_methods.met_formdata(reqstr,"TXT_NIC_NO").toUpperCase());
			
			String m_dob_dd = m_sn_methods.met_formdata(reqstr,"TXT_DATE_OF_BIRTH_DD");
			String m_dob_mm = m_sn_methods.met_formdata(reqstr,"TXT_DATE_OF_BIRTH_MM");
			String m_dob_yy = m_sn_methods.met_formdata(reqstr,"TXT_DATE_OF_BIRTH_YY");
			
			String m_dob="";
			
			if(m_dob_dd.equals("") && m_dob_mm.equals("") &&  m_dob_yy.equals("") )
			{
			m_dob=m_dob_dd+m_dob_mm+m_dob_yy; 
			}
			else
			{
			m_dob=m_dob_dd+"-"+m_dob_mm+"-"+m_dob_yy;
			}
			
			callstmt.setString(34,m_dob);
			
			callstmt.setString(35,m_sn_methods.met_formdata(reqstr,"TXT_PASSPORT_NO"));
			callstmt.setString(36,m_sn_methods.met_formdata(reqstr,"TXT_NATIONALITY"));
			callstmt.setString(37,m_sn_methods.met_formdata(reqstr,"TXT_MARITAL_STATUS"));
			callstmt.setString(38,m_sn_methods.met_formdata(reqstr,"TXT_GENDER"));
			callstmt.setString(39,m_sn_methods.met_formdata(reqstr,"TXT_BA_NATURE_OF_BUSINESS"));
			callstmt.setString(40,m_sn_methods.met_formdata(reqstr,"TXT_BA_PROFESSION"));
			callstmt.setString(41,m_sn_methods.met_formdata(reqstr,"TXT_BA_QUALIFICATIONS"));
			callstmt.setString(42,m_sn_methods.met_formdata(reqstr,"TXT_BA_DESIGNATION"));
			callstmt.setString(43,m_sn_methods.met_formdata(reqstr,"TXT_REG_OFFICE"));
			callstmt.setString(44,m_sn_methods.met_formdata(reqstr,"TXT_KEY_DECISION_MAKER"));
			callstmt.setString(45,m_sn_methods.met_formdata(reqstr,"TXT_DESIGNATION1"));
			callstmt.setString(46,m_sn_methods.met_formdata(reqstr,"TXT_DIRECT_TEL_NO"));
			callstmt.setString(47,m_sn_methods.met_formdata(reqstr,"TXT_CONTACT_FOR_PAYMENT"));
			callstmt.setString(48,m_sn_methods.met_formdata(reqstr,"TXT_CORRESPONDENCE_OFF"));
			callstmt.setString(49,m_sn_methods.met_formdata(reqstr,"TXT_DESIGNATION2"));
			callstmt.setString(50,m_sn_methods.met_formdata(reqstr,"TXT_GEN_TEL_NO"));
			callstmt.setString(51,m_sn_methods.met_formdata(reqstr,"TXT_GEN_FAX"));
			callstmt.setString(52,m_sn_methods.met_formdata(reqstr,"TXT_GEN_EMAIL"));
			callstmt.setString(53,m_sn_methods.met_formdata(reqstr,"TXT_FACTORY_STATUS"));
			callstmt.setString(54,m_sn_methods.met_formdata(reqstr,"TXT_F_CONTACT_PERSON"));
			callstmt.setString(55,m_sn_methods.met_formdata(reqstr,"TXT_F_TEL_NO"));
			callstmt.setString(56,m_sn_methods.met_formdata(reqstr,"TXT_F_FAX_NO"));
			callstmt.setString(57,m_sn_methods.met_formdata(reqstr,"TXT_F_EMAIL"));
			
			String m_capital = m_sn_methods.met_formdata(reqstr,"TXT_ISSUED_SHARE_CAPITAL");
			
			if(m_capital.equals("-")){
			callstmt.setString(58,"");
			}
			else
			{
			callstmt.setString(58,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_ISSUED_SHARE_CAPITAL")));
			}
			
			//callstmt.setString(58,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_ISSUED_SHARE_CAPITAL")));
			
			
			callstmt.setString(59,m_sn_methods.met_formdata(reqstr,"TXT_BUSINESS_CERTIFICATE_NO"));
			
			String m_doi_dd = m_sn_methods.met_formdata(reqstr,"TXT_DATE_OF_INCORPORATION_DD");
			String m_doi_mm = m_sn_methods.met_formdata(reqstr,"TXT_DATE_OF_INCORPORATION_MM");
			String m_doi_yy = m_sn_methods.met_formdata(reqstr,"TXT_DATE_OF_INCORPORATION_YY");

			String  m_doi="";
			
			if(m_doi_dd.equals("") && m_doi_mm.equals("") &&  m_doi_yy.equals("") )
			{
			m_doi=m_doi_dd+m_doi_mm+m_doi_yy; 
			}
			else
			{
			m_doi=m_doi_dd+"-"+m_doi_mm+"-"+m_doi_yy;
			}
			//String m_doi = m_doi_dd+m_doi_mm+m_doi_yy;
			callstmt.setString(60,m_doi);
			
			
			callstmt.setString(61,m_sn_methods.met_formdata(reqstr,"TXT_VAT_REG_NO"));
			
			String m_vrd_dd = m_sn_methods.met_formdata(reqstr,"TXT_VAT_REG_DATE_DD");
			String m_vrd_mm = m_sn_methods.met_formdata(reqstr,"TXT_VAT_REG_DATE_MM");
			String m_vrd_yy = m_sn_methods.met_formdata(reqstr,"TXT_VAT_REG_DATE_YY");
			String m_vrd="";
			
			if(m_vrd_dd.equals("") && m_vrd_mm.equals("") &&  m_vrd_yy.equals("") )
			{
			m_vrd=m_vrd_dd+m_vrd_mm+m_vrd_yy; 
			}
			else
			{
			m_vrd=m_vrd_dd+"-"+m_vrd_mm+"-"+m_vrd_yy;
			}
			callstmt.setString(62,m_vrd);
			
			//callstmt.setString(63,m_sn_methods.met_formdata(reqstr,"TXT_NO_CHILD"));
			
			
			String m_child = m_sn_methods.met_formdata(reqstr,"TXT_NO_CHILD");
			if(m_child.equals("-")){
			callstmt.setString(63,"");
			}
			else
			{
			callstmt.setString(63,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_NO_CHILD")));
			}
			
			
		//	callstmt.setString(64,m_sn_methods.met_formdata(reqstr,"TXT_TOT_DEP"));
			
			
			String m_depend = m_sn_methods.met_formdata(reqstr,"TXT_TOT_DEP");
			if(m_depend.equals("-")){
			callstmt.setString(64,"");
			}
			else
			{
			callstmt.setString(64,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_TOT_DEP")));
			}
			
			
			
			if(client_type.equals("I"))
			{
			 callstmt.setString(65,m_client_cat);
			}
			else if(client_type.equals("C"))
			{
			  callstmt.setString(65,m_sn_methods.met_formdata(reqstr,"TXT_LEGAL_STATUS"));
			}
			
			callstmt.setString(66,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(67,m_username);
			callstmt.setString(68,m_sn_methods.met_formdata(reqstr,"TXT_ADDRESS1_REG"));
			callstmt.setString(69,m_sn_methods.met_formdata(reqstr,"TXT_ADDRESS2_REG"));
			callstmt.setString(70,m_sn_methods.met_formdata(reqstr,"TXT_ADDRESS1_FAC"));
			callstmt.setString(71,m_sn_methods.met_formdata(reqstr,"TXT_ADDRESS2_FAC"));
			callstmt.setString(72,m_sn_methods.met_formdata(reqstr,"hid_temp_status"));
			callstmt.setString(73,m_sn_methods.met_formdata(reqstr,"TXT_CITY_CODE"));
			callstmt.setString(74,m_sn_methods.met_formdata(reqstr,"TXT_DRIVING_LICENSE_NO"));
			callstmt.setString(75,m_sn_methods.met_formdata(reqstr,"TXT_POSTAL_CODE"));
			callstmt.setString(76,m_sn_methods.met_formdata(reqstr,"TXT_GRIB_NO"));//Added By Nuwan De Silva 01-03-2007
			callstmt.setString(77,m_scr_name);
			callstmt.setString(78,m_sn_methods.met_formdata(reqstr,"TXT_BUS_SECT"));
			callstmt.setString(79,m_sn_methods.met_formdata(reqstr,"TXT_BUS_SECT_MAIN"));
			callstmt.setString(80, m_sn_methods.met_formdata(this.reqstr, "TXT_NIC_NO_OLD")); //Added by Kanchana on 2016-08-19
	
			
			
			callstmt.execute();
			//callstmt.getString(1);
			
			if(m_screen_name.equals("NEW")){
         m_client_code =callstmt.getString(1);
				 m_client_code_update=callstmt.getString(1);
       }
			else
				{
				m_client_code_update = m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE");
				}
				
			//	out.println("client code"+m_client_code_update);
			
			callstmt.close();
			
			
		//individual
		  if(client_type.equals("I"))
			{
			
				if(icountemp == 0)
				{
					callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_DEL_CLIENT_ALL(:1,:2,:3,:4); END;");		
					callstmt1.setString(1,client_code);
					callstmt1.setString(2,"EMP");
					callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
					callstmt1.setString(4,m_username);
					callstmt1.execute();
					callstmt1.close();
				}
	
				for(int i=0;i<icountemp;i++) 
					  {
								//String num = Integer.toString(i);
   							String org = m_sn_methods.met_formdata(reqstr,"TXT_ORGANIZATION"+i+"");
								String telno = m_sn_methods.met_formdata(reqstr,"TXT_TEL_NO"+i+"");
								String to_date_dd = m_sn_methods.met_formdata(reqstr,"TXT_TO_DATE_DD"+i+"");
								String to_date_mm = m_sn_methods.met_formdata(reqstr,"TXT_TO_DATE_MM"+i+"");
								String to_date_yy = m_sn_methods.met_formdata(reqstr,"TXT_TO_DATE_YY"+i+"");
								String m_to_date="";

								if(to_date_dd.equals("") && to_date_mm.equals("") &&  to_date_yy.equals("") )
								{
									m_to_date=to_date_dd+to_date_mm+to_date_yy; 
								}
								else
								{
									m_to_date=to_date_dd+"-"+to_date_mm+"-"+to_date_yy;
								}
								
								String from_date_dd = m_sn_methods.met_formdata(reqstr,"TXT_FROM_DATE_DD"+i+"");
								String from_date_mm = m_sn_methods.met_formdata(reqstr,"TXT_FROM_DATE_MM"+i+"");
								String from_date_yy = m_sn_methods.met_formdata(reqstr,"TXT_FROM_DATE_YY"+i+"");
								String m_from_date="";
								
								if(from_date_dd.equals("") && from_date_mm.equals("") && from_date_yy.equals("") ){
								 m_from_date = from_date_dd+from_date_mm+from_date_yy;
								}
								else {
								 m_from_date = from_date_dd+"-"+from_date_mm+"-"+from_date_yy;
								}
								
								callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_EMPLOYMENT(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10); END;");
								
									if(m_screen_name.equals("NEW")){
             				callstmt1.setString(1,m_client_code.trim());
             				}
									else
					  			 {
										callstmt1.setString(1,client_code);
				     			 }
										
								callstmt1.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_ORGANIZATION"+i+""));
								callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_TEL_NO"+i+""));
								callstmt1.setString(4,m_from_date);
								callstmt1.setString(5,m_to_date);
								callstmt1.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_DESIGNATION2"+i+""));
								callstmt1.setString(7,""+i);	
								callstmt1.setString(8,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
								callstmt1.setString(9,m_username);
								callstmt1.setString(10,m_sn_methods.met_formdata(reqstr,"hid_temp_status"));
								if(!org.equals("") && !telno.equals("") && !m_from_date.equals(""))
								{
								   callstmt1.execute();
								}		
								callstmt1.close();
				 
						}
						
						
				if(icountbank == 0)
				{
					callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_DEL_CLIENT_ALL(:1,:2,:3,:4); END;");
					callstmt1.setString(1,client_code);
					callstmt1.setString(2,"BNK");
					callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
					callstmt1.setString(4,m_username);
					callstmt1.execute();
					callstmt1.close();
				}
				
				callstmt2=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_CLIENT_BANK(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12); END;");
				
				
										
			
			
			//out.println("count bank "+icountbank);
				for(int i=0;i<icountbank;i++)
					  {
								//String num = Integer.toString(i);
								String bnk_code = m_sn_methods.met_formdata(reqstr,"TXT_BANK_CODE"+i+"");
								String br_code = m_sn_methods.met_formdata(reqstr,"TXT_BRANCH_CODE"+i+"");
								String acc_no = m_sn_methods.met_formdata(reqstr,"TXT_ACCOUNT_NO"+i+"");
								String ref = m_sn_methods.met_formdata(reqstr,"TXT_REFERENCE"+i+"");
								String telno = m_sn_methods.met_formdata(reqstr,"TXT_TEL_NO2"+i+"");
								String faxno = m_sn_methods.met_formdata(reqstr,"TXT_FAX_NO"+i+"");
								String m_rela = m_sn_methods.met_formdata(reqstr,"TXT_RELATIONSHIP2"+i+"");
               // out.println("client code bank "+m_client_code_update);
								
							
							  if(m_screen_name.equals("NEW")){
             				callstmt2.setString(1,m_client_code.trim());
             				}
									else
					  			 {
										callstmt2.setString(1,client_code);
				     			 }
								//callstmt2.setString(1,m_client_code_update);
								callstmt2.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_BANK_CODE"+i+""));
								callstmt2.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_BRANCH_CODE"+i+""));
								callstmt2.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_ACCOUNT_NO"+i+""));
								callstmt2.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_REFERENCE"+i+""));
								callstmt2.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_TEL_NO2"+i+""));
								callstmt2.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_FAX_NO"+i+""));
								
								//String m_rela = m_sn_methods.met_formdata(reqstr,"TXT_RELATIONSHIP2");
								
								if(m_rela.equals("-")){
								callstmt2.setString(8,"");
								}
								else
								{
								callstmt2.setString(8,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_RELATIONSHIP2"+i+"")));
								}
			
								//callstmt2.setString(8,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_RELATIONSHIP2"+i+"")));
								callstmt2.setString(9,""+i);	
								callstmt2.setString(10,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
								callstmt2.setString(11,m_username);
								callstmt2.setString(12,m_sn_methods.met_formdata(reqstr,"hid_temp_status"));
								
								if(!bnk_code.equals("") && !br_code.equals("") && !acc_no.equals("") && !ref.equals("") && !telno.equals("") && !faxno.equals("") )
								{
								  callstmt2.execute();
								}	
								
							
				 
						}
						
							callstmt2.close();
					if(icountcredit == 0)
					{
							callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_DEL_CLIENT_ALL(:1,:2,:3,:4); END;");
							callstmt1.setString(1,client_code);
							callstmt1.setString(2,"CRE");
							callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
							callstmt1.setString(4,m_username);
							callstmt1.execute();
							callstmt1.close();
					}
				
				for(int i=0;i<icountcredit;i++)
					  {
								String institute = m_sn_methods.met_formdata(reqstr,"TXT_INSTITUTION"+i+"");
								String con_person = m_sn_methods.met_formdata(reqstr,"TXT_CONTACT_PERSON"+i+"");
								String app_amount = m_sn_methods.met_formdata(reqstr,"TXT_APPROVED_AMOUNT"+i+"");
								String bal_amount = m_sn_methods.met_formdata(reqstr,"TXT_BALANCE_AMOUNT"+i+"");
								String months = m_sn_methods.met_formdata(reqstr,"TXT_MONTHS"+i+"");
   
								callstmt3=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_CREDIT_FACILITY(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13); END;");
								 if(m_screen_name.equals("NEW")){
             				callstmt3.setString(1,m_client_code.trim());
             				}
									else
					  			 {
										callstmt3.setString(1,client_code);
				     			 }
								//callstmt3.setString(1,client_code);
								callstmt3.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_TYPE_OF_FACILITY"+i+""));
								callstmt3.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_INSTITUTION"+i+""));
								callstmt3.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_CONTACT_PERSON"+i+""));
								callstmt3.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_CONTRACT_NO"+i+""));
								callstmt3.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_SECURITY"+i+""));
								
								if(app_amount.equals("-")){
								callstmt3.setString(7,"");
								}
								else
								{
								callstmt3.setString(7,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_APPROVED_AMOUNT"+i+"")));
								}
								
								//callstmt3.setString(7,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_APPROVED_AMOUNT"+i+"")));
								
								
								if(bal_amount.equals("-")){
								callstmt3.setString(8,"");
								}
								else
								{
								callstmt3.setString(8,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_BALANCE_AMOUNT"+i+"")));
								}
								
								//callstmt3.setString(8,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_BALANCE_AMOUNT"+i+"")));
								
								if(months.equals("-")){
								callstmt3.setString(9,"");
								}
								else
								{
								//llstmt3.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_MONTHS"+i+"")); //comment by nuwan de silva on 01-10-07
								callstmt3.setString(9,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_MONTHS"+i+""))); //added by nuwan de silva on 01-10-07
								}
								
								//callstmt3.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_MONTHS"+i+""));
								
								
								
								
								callstmt3.setString(10,""+i);	
								callstmt3.setString(11,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
								callstmt3.setString(12,m_username);
								callstmt3.setString(13,m_sn_methods.met_formdata(reqstr,"hid_temp_status"));
								if(!institute.equals("") && !con_person.equals("") && !app_amount.equals("") && !bal_amount.equals("") && !months.equals("") )
								{
								 callstmt3.execute();
								}
								callstmt3.close();
				 
						}
						
						
					if(icountnon == 0)
					{
							callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_DEL_CLIENT_ALL(:1,:2,:3,:4); END;");
							callstmt1.setString(1,client_code);
							callstmt1.setString(2,"NON");
							callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
							callstmt1.setString(4,m_username);
							callstmt1.execute();
							callstmt1.close();
					}
						
							
					for(int i=0;i<icountnon;i++){
								String name = m_sn_methods.met_formdata(reqstr,"TXT_NAME_REFEREE"+i+"");
								String rel = m_sn_methods.met_formdata(reqstr,"TXT_RELATIONSHIP3"+i+"");
								String desig = m_sn_methods.met_formdata(reqstr,"TXT_DESIGNATION3"+i+"");
								String telno = m_sn_methods.met_formdata(reqstr,"TXT_HOME_TEL_NO"+i+"");
                String period = m_sn_methods.met_formdata(reqstr,"TXT_PERIOD"+i+"");
								
								callstmt4=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_NON_RELATED(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12); END;");
								if(m_screen_name.equals("NEW")){
             				callstmt4.setString(1,m_client_code.trim());
             				}
									else
					  			 {
										callstmt4.setString(1,client_code);
				     			 }
								//callstmt4.setString(1,client_code);
								callstmt4.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_NAME_REFEREE"+i+""));
								callstmt4.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_RELATIONSHIP3"+i+""));
								
								if(period.equals("-")){
								callstmt4.setString(4,"0");
								}
								else
								{
								callstmt4.setString(4,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_PERIOD"+i+"")));
								}
								
								//callstmt4.setString(4,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_PERIOD"+i+"")));
								callstmt4.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_DESIGNATION3"+i+""));
								callstmt4.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_HOME_TEL_NO"+i+""));
								callstmt4.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_OFFICE_TEL_NO"+i+""));
								callstmt4.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_MOBILE_NO"+i+""));
								callstmt4.setString(9,""+i);	
								callstmt4.setString(10,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
								callstmt4.setString(11,m_username);
								callstmt4.setString(12,m_sn_methods.met_formdata(reqstr,"hid_temp_status"));
								if(!name.equals("") && !rel.equals("") && !desig.equals("") && !telno.equals("") )
								{
								  callstmt4.execute();
								}
								callstmt4.close();
				 
						}
						
						
					for(int i=1;i<=iarr_size_in_ex;i++)
					  {
								//String num = Integer.toString(i);
   							String income = m_sn_methods.met_formdata(reqstr,"TXT_INCOME"+i+"");
									
								callstmt13=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_INCOME_EXPENSE(:1,:2,:3,:4,:5,:6,:7,:8); END;");
								if(m_screen_name.equals("NEW")){
             				callstmt13.setString(1,m_client_code.trim());
             				}
									else
					  			 {
										callstmt13.setString(1,client_code);
				     			 }
								//callstmt13.setString(1,client_code);
								callstmt13.setString(2,m_sn_methods.met_formdata(reqstr,"hid_iecode"+i+""));
								
								if(income.equals("-")){
								callstmt13.setString(3,"0");
								}
								else
								{
								callstmt13.setString(3,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_INCOME"+i+"")));
								}
								
								//callstmt13.setString(3,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_INCOME"+i+"")));
								callstmt13.setString(4,m_sn_methods.met_formdata(reqstr,"hid_type"+i+""));
								callstmt13.setString(5,""+i);	
								callstmt13.setString(6,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
								callstmt13.setString(7,m_username);
								callstmt13.setString(8,m_sn_methods.met_formdata(reqstr,"hid_temp_status"));
								
								if(!income.equals(""))
								{
									callstmt13.execute();
								}
								callstmt13.close();
				 
						}	
					
					
					if(icountfamily == 0)
					{
							callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_DEL_CLIENT_ALL(:1,:2,:3,:4); END;");
							callstmt1.setString(1,client_code);
							callstmt1.setString(2,"FAM");
							callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
							callstmt1.setString(4,m_username);
							callstmt1.execute();
							callstmt1.close();
					}

					for(int i=0;i<icountfamily;i++)
					  {
								String name = m_sn_methods.met_formdata(reqstr,"TXT_NAME_F"+i+"");
								String add = m_sn_methods.met_formdata(reqstr,"TXT_ADDRESS1_F"+i+"");
								String age = m_sn_methods.met_formdata(reqstr,"TXT_AGE_F"+i+"");
   
								callstmt5=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_FAMILY_MEMBERS(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11); END;");
								if(m_screen_name.equals("NEW")){
             				callstmt5.setString(1,m_client_code.trim());
             				}
									else
					  			 {
										callstmt5.setString(1,client_code);
				     			 }
								//callstmt5.setString(1,client_code);
								callstmt5.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_MEMBER"+i+""));
								callstmt5.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_NAME_F"+i+""));
								callstmt5.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_ADDRESS1_F"+i+""));
								//callstmt5.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_AGE_F"+i+""));
								
								if(age.equals("-")){
								callstmt5.setString(5,"0"); //modified by nuwan de silva 06-08-07
								}
								else
								{
								callstmt5.setString(5,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_AGE_F"+i+""))); //modified by nuwan de silva 06-08-07 
								}
								
								callstmt5.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_TELEPHONE_NO_F"+i+""));
								callstmt5.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_MOBILE_NO_F"+i+""));
								callstmt5.setString(8,""+i);	
								callstmt5.setString(9,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
								callstmt5.setString(10,m_username);
								callstmt5.setString(11,m_sn_methods.met_formdata(reqstr,"hid_temp_status"));
								if(!name.equals("") && !add.equals("") && !age.equals(""))
								{
								  callstmt5.execute();
								}
								callstmt5.close();
								
				 
						}
						
						/*for(int i=0;i<app_doc_lineno;i++)
					  {
								//String num = Integer.toString(i);				
   							String received = m_sn_methods.met_formdata(reqstr,"CHK_RECEIVED_DOC"+i+"");
								//out.println(m_sn_methods.met_formdata(reqstr,"hid_doc_code"+i+""));	
								callstmt13=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_CLIENT_APP_DOC(:1,:2,:3,:4,:5,:6,:7,:8); END;");
								if(m_screen_name.equals("NEW")){
             				callstmt13.setString(1,m_client_code.trim());
             				}
									else
					  			 {
										callstmt13.setString(1,client_code);
				     			 }
								//callstmt13.setString(1,client_code);
								callstmt13.setString(2,m_sn_methods.met_formdata(reqstr,"hid_doc_code"+i+""));
								if(received.equals("on"))
								{
									callstmt13.setString(3,"Y");
								}
							  else 
								{
									callstmt13.setString(3,"N");
								}
								callstmt13.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_REMARK_DOC"+i+""));
								callstmt13.setString(5,""+i);
								callstmt13.setString(6,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
								callstmt13.setString(7,m_username);
								callstmt13.setString(8,m_sn_methods.met_formdata(reqstr,"hid_temp_status"));
								callstmt13.execute();
								conn.commit();
				 
						}	*/
						
			}
			
			else if(client_type.equals("C"))
			{
			
					 if(icountcomp == 0)
						{
							callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_DEL_CLIENT_ALL(:1,:2,:3,:4); END;");
							callstmt1.setString(1,client_code);
							callstmt1.setString(2,"DIR");
							callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
							callstmt1.setString(4,m_username);
							callstmt1.execute();
							callstmt1.close();
						}
			
			
						for(int i=0;i<icountcomp;i++)
					  {
								String name = m_sn_methods.met_formdata(reqstr,"TXT_NAME_DIR"+i+"");
								String nic = m_sn_methods.met_formdata(reqstr,"TXT_NIC_NO_DIR"+i+"");
								String stake = m_sn_methods.met_formdata(reqstr,"TXT_STAKE"+i+"");
								String no_shares = m_sn_methods.met_formdata(reqstr,"TXT_NO_OF_SHARES"+i+"");
								String value = m_sn_methods.met_formdata(reqstr,"TXT_VALUE"+i+"");
								String pos = m_sn_methods.met_formdata(reqstr,"TXT_POSITION"+i+"");
        
								
								callstmt6=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_COMPANY_DIRECTORS(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12); END;");
								if(m_screen_name.equals("NEW")){
             				callstmt6.setString(1,m_client_code.trim());
             				}
									else
					  			 {
										callstmt6.setString(1,client_code);
				     			 }
								//callstmt6.setString(1,client_code);
								callstmt6.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_NAME_DIR"+i+""));
								callstmt6.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_NIC_NO_DIR"+i+"").toUpperCase());
								//callstmt6.setString(4,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_STAKE"+i+"")));
								
								if(stake.equals("-")){
								callstmt6.setString(4,"0");
								}
								else
								{
								callstmt6.setString(4,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_STAKE"+i+"")));
								}
								
								//callstmt6.setString(5,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_NO_OF_SHARES"+i+"")));
								
								if(no_shares.equals("-")){
								callstmt6.setString(5,"0");
								}
								else
								{
								callstmt6.setString(5,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_NO_OF_SHARES"+i+"")));
								}
								
								
								//callstmt6.setString(6,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_VALUE"+i+"")));
								
								if(value.equals("-")){
								callstmt6.setString(6,"0");
								}
								else
								{
								callstmt6.setString(6,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_VALUE"+i+"")));
								}
								
								callstmt6.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_POSITION"+i+""));
								callstmt6.setString(8,""+i);	
								callstmt6.setString(9,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
								callstmt6.setString(10,m_username);
								callstmt6.setString(11,m_sn_methods.met_formdata(reqstr,"hid_temp_status"));
								
								callstmt6.setString(12,m_sn_methods.met_formdata(reqstr,"TXT_ADDRESS_DIR"+i+"")); //added by nuwan de silva on 07-09-06
								
								if(!name.equals("") && !nic.equals("") && !stake.equals("") && !no_shares.equals("") && !value.equals("")&& !pos.equals("") )
								{
								 callstmt6.execute();
								}	
								callstmt6.close();
				 
						}
						
						if(icountba == 0)
						{
							callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_DEL_CLIENT_ALL(:1,:2,:3,:4); END;");
							callstmt1.setString(1,client_code);
							callstmt1.setString(2,"BA");
							callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
							callstmt1.setString(4,m_username);
							callstmt1.execute();
							callstmt1.close();
						}
						
					 for(int i=0;i<icountba;i++)
					  {
								
								String act = m_sn_methods.met_formdata(reqstr,"TXT_ACTIVITY_BA"+i+"");
   							
								callstmt7=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_CLIENT_BA(:1,:2,:3,:4,:5,:6,:7,:8); END;");
								if(m_screen_name.equals("NEW")){
             				callstmt7.setString(1,m_client_code.trim());
             				}
								else
					  		 {
								 	callstmt7.setString(1,client_code);
				     		 }
								//callstmt7.setString(1,client_code);
								callstmt7.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_CAT_TYPE_CODE_BA"+i+""));
								callstmt7.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_ACTIVITY_BA"+i+""));
								callstmt7.setString(4,m_username);	
								callstmt7.setString(5,""+i);	
								callstmt7.setString(6,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
								callstmt7.setString(7,m_username);
								callstmt7.setString(8,m_sn_methods.met_formdata(reqstr,"hid_temp_status"));
								if(!act.equals(""))
								{
								  callstmt7.execute();
								}
								callstmt7.close();
				 				//conn.commit();
						}
						
						if(icountsub == 0)
						{
							callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_DEL_CLIENT_ALL(:1,:2,:3,:4); END;");
							callstmt1.setString(1,client_code);
							callstmt1.setString(2,"SUB");
							callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
							callstmt1.setString(4,m_username);
							callstmt1.execute();
							callstmt1.close();
						}

						for(int i=0;i<icountsub;i++)
					  {
								String name = m_sn_methods.met_formdata(reqstr,"TXT_NAME_SUB"+i+"");
								String stake = m_sn_methods.met_formdata(reqstr,"TXT_NAME_SUB"+i+"");
								String value = m_sn_methods.met_formdata(reqstr,"TXT_VALUE_SUB"+i+"");
								String act = m_sn_methods.met_formdata(reqstr,"TXT_ACTIVITIES_SUB"+i+"");
								String stake_sub = m_sn_methods.met_formdata(reqstr,"TXT_STAKE_SUB"+i+"");
   
								callstmt8=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_SUBSIDIARIES(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11); END;");
								if(m_screen_name.equals("NEW")){
             				callstmt8.setString(1,m_client_code.trim());
             				}
									else
					  			 {
										callstmt8.setString(1,client_code);
				     			 }
								//callstmt8.setString(1,client_code);
								callstmt8.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_NAME_SUB"+i+""));
								
								if(stake_sub.equals("-")){
								callstmt8.setString(3,"0");
								}
								else
								{
								callstmt8.setString(3,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_STAKE_SUB"+i+"")));
								}
								
								//callstmt8.setString(3,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_STAKE_SUB"+i+"")));
								
								if(value.equals("-")){
								callstmt8.setString(4,"0");
								}
								else
								{
								callstmt8.setString(4,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_VALUE_SUB"+i+"")));
								}
								
								//callstmt8.setString(4,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_VALUE_SUB"+i+"")));
								callstmt8.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_TEL_NO_SUB"+i+""));
								callstmt8.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_OFFICER_SUB"+i+""));
								callstmt8.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_ACTIVITIES_SUB"+i+""));
								callstmt8.setString(8,""+i);	
								callstmt8.setString(9,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
								callstmt8.setString(10,m_username);
								callstmt8.setString(11,m_sn_methods.met_formdata(reqstr,"hid_temp_status"));
								if(!name.equals("") && !stake.equals("") && !value.equals("") && !act.equals(""))
								{
								  callstmt8.execute();
								}
								callstmt8.close();
				 
						}
						
						if(icountbankc == 0)
						{
							callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_DEL_CLIENT_ALL(:1,:2,:3,:4); END;");
							callstmt1.setString(1,client_code);
							callstmt1.setString(2,"BNK");
							callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
							callstmt1.setString(4,m_username);
							callstmt1.execute();
							callstmt1.close();
						}
						
						
						for(int i=0;i<icountbankc;i++)
					  {
								//String num = Integer.toString(i);
								String bnk_code = m_sn_methods.met_formdata(reqstr,"TXT_BANK_CODE"+i+"");
								String br_code = m_sn_methods.met_formdata(reqstr,"TXT_BRANCH_CODE"+i+"");
								String acc_no = m_sn_methods.met_formdata(reqstr,"TXT_ACCOUNT_NO"+i+"");
								String ref = m_sn_methods.met_formdata(reqstr,"TXT_REFERENCE"+i+"");
								String telno = m_sn_methods.met_formdata(reqstr,"TXT_TEL_NO2"+i+"");
								String faxno = m_sn_methods.met_formdata(reqstr,"TXT_FAX_NO"+i+"");
                String relation = m_sn_methods.met_formdata(reqstr,"TXT_RELATIONSHIP2"+i+"");
								
								callstmt9=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_CLIENT_BANK(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12); END;");
								if(m_screen_name.equals("NEW")){
             				callstmt9.setString(1,m_client_code.trim());
             				}
									else
					  			 {
										callstmt9.setString(1,client_code);
				     			 }
							//	callstmt9.setString(1,client_code);
								callstmt9.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_BANK_CODE"+i+""));
								callstmt9.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_BRANCH_CODE"+i+""));
								callstmt9.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_ACCOUNT_NO"+i+""));
								callstmt9.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_REFERENCE"+i+""));
								callstmt9.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_TEL_NO2"+i+""));
								callstmt9.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_FAX_NO"+i+""));
								//callstmt9.setString(8,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_RELATIONSHIP2"+i+"")));
								
								if(relation.equals("-")){
								callstmt9.setString(8,"0");
								}
								else
								{
								callstmt9.setString(8,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_RELATIONSHIP2"+i+"")));
								}
								
								callstmt9.setString(9,""+i);	
								callstmt9.setString(10,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
								callstmt9.setString(11,m_username);
								callstmt9.setString(12,m_sn_methods.met_formdata(reqstr,"hid_temp_status"));
								if(!bnk_code.equals("") && !br_code.equals("") && !acc_no.equals("") && !ref.equals("") && !telno.equals("") && !faxno.equals("") )
								{
								  callstmt9.execute();
								}
								
				 
						}
						
						callstmt9.close();
						if(icountcreditc == 0)
						{
							callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_DEL_CLIENT_ALL(:1,:2,:3,:4); END;");
							callstmt1.setString(1,client_code);
							callstmt1.setString(2,"CRE_C");
							callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
							callstmt1.setString(4,m_username);
							callstmt1.execute();
							callstmt1.close();
						}

						for(int i=0;i<icountcreditc;i++)
					  {
								//String num = Integer.toString(i);
								String institute = m_sn_methods.met_formdata(reqstr,"TXT_INSTITUTION"+i+"");
								String con_person = m_sn_methods.met_formdata(reqstr,"TXT_CONTACT_PERSON"+i+"");
								String app_amount = m_sn_methods.met_formdata(reqstr,"TXT_APPROVED_AMOUNT"+i+"");
								String months = m_sn_methods.met_formdata(reqstr,"TXT_MONTHS"+i+"");
								String bal_amount = m_sn_methods.met_formdata(reqstr,"TXT_BALANCE_AMOUNT"+i+"");
								String rental = m_sn_methods.met_formdata(reqstr,"TXT_MONTHLY_RENTAL"+i+"");
   
								callstmt10=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_CREDIT_FACI_COR(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13); END;");
								if(m_screen_name.equals("NEW")){
             				callstmt10.setString(1,m_client_code.trim());
             				}
									else
					  			 {
										callstmt10.setString(1,client_code);
				     			 }
								//callstmt10.setString(1,client_code);
								callstmt10.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_INSTITUTION"+i+""));
								callstmt10.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_CONTACT_PERSON"+i+""));
								callstmt10.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_TYPE_OF_FACILITY"+i+""));
								callstmt10.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_EQUIPMENT"+i+""));
								//callstmt10.setString(6,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_APPROVED_AMOUNT"+i+"")));
								
									if(app_amount.equals("-")){
								callstmt10.setString(6,"");
								}
								else
								{
								callstmt10.setString(6,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_APPROVED_AMOUNT"+i+"")));
								}
								
								//callstmt10.setString(7,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_MONTHLY_RENTAL"+i+"")));
								
								if(rental.equals("-")){
								callstmt10.setString(7,"");
								}
								else
								{
								callstmt10.setString(7,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_MONTHLY_RENTAL"+i+"")));
								}
								
								//callstmt10.setString(8,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_MONTHS"+i+"")));
								if(months.equals("-")){
								callstmt10.setString(8,"");
								}
								else
								{
								callstmt10.setString(8,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_MONTHS"+i+"")));
								}
								
								
								//callstmt10.setString(9,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_BALANCE_AMOUNT"+i+"")));
								
								if(bal_amount.equals("-")){
								callstmt10.setString(9,"");
								}
								else
								{
								callstmt10.setString(9,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_BALANCE_AMOUNT"+i+"")));
								}
								
								callstmt10.setString(10,""+i);	
								callstmt10.setString(11,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
								callstmt10.setString(12,m_username);
								callstmt10.setString(13,m_sn_methods.met_formdata(reqstr,"hid_temp_status"));
								if(!institute.equals("") && !con_person.equals("") && !app_amount.equals("") && !bal_amount.equals("") && !months.equals("") && !rental.equals("") )
								{
								  callstmt10.execute();
								}
							
				 
						}
							callstmt10.close();
						
						if(icountcus == 0)
						{
							callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_DEL_CLIENT_ALL(:1,:2,:3,:4); END;");
							callstmt1.setString(1,client_code);
							callstmt1.setString(2,"CUS");
							callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
							callstmt1.setString(4,m_username);
							callstmt1.execute();
							callstmt1.close();
						}
						
						for(int i=0;i<icountcus;i++)
					  {
								String name = m_sn_methods.met_formdata(reqstr,"TXT_CUSTOMER_NAME"+i+"");
								String add = m_sn_methods.met_formdata(reqstr,"TXT_ADDRESS_CUS"+i+"");
								String rel = m_sn_methods.met_formdata(reqstr,"TXT_RELATIONSHIP_CUS"+i+"");
								String con_person = m_sn_methods.met_formdata(reqstr,"TXT_CONTACT_PERSON_CUS"+i+"");
								String telno = m_sn_methods.met_formdata(reqstr,"TXT_TEL_NO_CUS"+i+"");
   
								callstmt11=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_CUSTOMERS(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11); END;");
								if(m_screen_name.equals("NEW")){
             				callstmt11.setString(1,m_client_code.trim());
             				}
									else
					  			 {
										callstmt11.setString(1,client_code);
				     			 }
								//callstmt11.setString(1,client_code);
								callstmt11.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_CUSTOMER_NAME"+i+""));
								callstmt11.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_TYPE_C"+i+""));
								callstmt11.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_ADDRESS_CUS"+i+""));
								//callstmt11.setString(5,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_RELATIONSHIP_CUS"+i+"")));
								
								if(rel.equals("-")){
								callstmt11.setString(5,"0");
								}
								else
								{
								callstmt11.setString(5,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_RELATIONSHIP_CUS"+i+"")));
								}
								
								callstmt11.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_CONTACT_PERSON_CUS"+i+""));
								callstmt11.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_TEL_NO_CUS"+i+""));
								callstmt11.setString(8,""+i);	
								callstmt11.setString(9,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
								callstmt11.setString(10,m_username);
								callstmt11.setString(11,m_sn_methods.met_formdata(reqstr,"hid_temp_status"));
								if(!name.equals("") && !add.equals("") && !rel.equals("") && !con_person.equals("") && !telno.equals("") )
								{
								  callstmt11.execute();
								}
							
				 
						}
							callstmt11.close();
						
						if(icountnonc == 0)
						{
							callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_DEL_CLIENT_ALL(:1,:2,:3,:4); END;");
							callstmt1.setString(1,client_code);
							callstmt1.setString(2,"NON");
							callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
							callstmt1.setString(4,m_username);
							callstmt1.execute();
							callstmt1.close();
						}
						
						for(int i=0;i<icountnonc;i++)
					  {
								//String num = Integer.toString(i);
								String name = m_sn_methods.met_formdata(reqstr,"TXT_NAME_REFEREE"+i+"");
								String rel = m_sn_methods.met_formdata(reqstr,"TXT_RELATIONSHIP3"+i+"");
								String desig = m_sn_methods.met_formdata(reqstr,"TXT_DESIGNATION3"+i+"");
								String telno = m_sn_methods.met_formdata(reqstr,"TXT_HOME_TEL_NO"+i+"");
                String period = m_sn_methods.met_formdata(reqstr,"TXT_PERIOD"+i+"");
								
								callstmt4=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_NON_RELATED(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12); END;");
								if(m_screen_name.equals("NEW")){
             				callstmt4.setString(1,m_client_code.trim());
             				}
									else
					  			 {
										callstmt4.setString(1,client_code);
				     			 }
								//callstmt4.setString(1,client_code);
								callstmt4.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_NAME_REFEREE"+i+""));
								callstmt4.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_RELATIONSHIP3"+i+""));
								//callstmt4.setString(4,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_PERIOD"+i+"")));
								
								if(period.equals("-")){
								callstmt4.setString(4,"0");
								}
								else
								{
								callstmt4.setString(4,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_PERIOD"+i+"")));
								}
								
								callstmt4.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_DESIGNATION3"+i+""));
								
								callstmt4.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_HOME_TEL_NO"+i+""));
								
								//if(telno.equals("")){
								//callstmt4.setString(6,"");
								//}
								//else
								//{
								//callstmt4.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_HOME_TEL_NO"+i+""));
								//}
								
								callstmt4.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_OFFICE_TEL_NO"+i+""));
								
								callstmt4.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_MOBILE_NO"+i+""));
								callstmt4.setString(9,""+i);	
								callstmt4.setString(10,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
								callstmt4.setString(11,m_username);
								callstmt4.setString(12,m_sn_methods.met_formdata(reqstr,"hid_temp_status"));
								if(!name.equals("") && !rel.equals("") && !desig.equals("")  )
								{
								  callstmt4.execute();
								}
							
				 
						}
							callstmt4.close();
							
							
							
							
							
							
							
							
							//ADDED BY NUWAN DE SILVA
							
								if(icountaudit == 0)
						{
							callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_DEL_CLIENT_ALL(:1,:2,:3,:4); END;");
							callstmt1.setString(1,client_code);
							callstmt1.setString(2,"AUD");
							callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
							callstmt1.setString(4,m_username);
							callstmt1.execute();
							callstmt1.close();
						}
						
						for(int i=0;i<icountaudit;i++)
					  {
								
								String name = m_sn_methods.met_formdata(reqstr,"TXT_AUDITOR_NAME"+i+"");
								String rela = m_sn_methods.met_formdata(reqstr,"TXT_AUDITOR_RELATIONSHIP"+i+"");
								callstmt4=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_AUDIT(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13); END;");
								
								if(m_screen_name.equals("NEW")){
             				callstmt4.setString(1,m_client_code.trim());
             				}
									else
					  			 {
										callstmt4.setString(1,client_code);
				     			 }
										
								
								callstmt4.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_AUDITOR_NAME"+i+""));
								callstmt4.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_AUDITOR_ADDRESS_1"+i+""));
								callstmt4.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_AUDITOR_ADDRESS_2"+i+""));
								
								//callstmt4.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_AUDITOR_RELATIONSHIP"+i+""));
								
								
								if(rela.equals("-")){
								callstmt4.setString(5,"0");
								}
								else
								{
								callstmt4.setString(5,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_AUDITOR_RELATIONSHIP"+i+"")));
								}
								
								
								callstmt4.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_AUDITOR_REFERENCE"+i+""));
								callstmt4.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_AUDITOR_TEL_NO"+i+""));
								callstmt4.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_AUDITOR_FAX_NO"+i+""));
								callstmt4.setString(9,""+i);	
								callstmt4.setString(10,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
								callstmt4.setString(11,m_username);
								callstmt4.setString(12,m_sn_methods.met_formdata(reqstr,"hid_temp_status"));
								callstmt4.setString(13,m_scr_name);
								if(!name.equals("")  )
								{
								  callstmt4.execute();
								}
							
				 
						}
							callstmt4.close();
							
							
						
					/*	for(int i=0;i<app_doc_lineno;i++)
					  {
								//String num = Integer.toString(i);				
   							String received = m_sn_methods.met_formdata(reqstr,"CHK_RECEIVED_DOC"+i+"");
								//out.println(m_sn_methods.met_formdata(reqstr,"hid_doc_code"+i+""));	
								callstmt13=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_CLIENT_APP_DOC(:1,:2,:3,:4,:5,:6,:7,:8); END;");
								if(m_screen_name.equals("NEW")){
             				callstmt13.setString(1,m_client_code.trim());
             				}
								else
					  			 {
										callstmt13.setString(1,client_code);
				     			 }
								//callstmt13.setString(1,client_code);
								callstmt13.setString(2,m_sn_methods.met_formdata(reqstr,"hid_doc_code"+i+""));
								if(received.equals("on"))
								{
									callstmt13.setString(3,"Y");
								}
							  else 
								{
									callstmt13.setString(3,"N");
								}
								callstmt13.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_REMARK_DOC"+i+""));
								callstmt13.setString(5,""+i);
								callstmt13.setString(6,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
								callstmt13.setString(7,m_username);
								callstmt13.setString(8,m_sn_methods.met_formdata(reqstr,"hid_temp_status"));
								callstmt13.execute();
				 				conn.commit();
						}	*/
						
								
			}

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("	var b_flag=0");
			
			out.println("function validate_client() {");
			
			if(m_save_close.equals("A") || m_save_close.equals("B")){
			
			out.println("var 	no_of_rec="+m_no_of_rec+"");
			out.println("var 	m_screen_name='"+m_screen_name+"'");
			out.println("b_flag=0");
			
			if(m_screen_name.equals("NEW")){
			//comment by nuwan de silva on 01-10-07
			/*out.println("if(window.opener.document.Form1.TXT_APPLICANT_CODE.value==\""+m_client_code+"\"){");
			out.println("	b_flag=1");
			out.println("	}");
			
			out.println("else if(window.opener.document.Form1.TXT_CORE_APPLICANT_CODE.value==\""+m_client_code+"\"){");
			out.println("	b_flag=1");
			out.println("	}");
			*/
			//added by nuwan de silva on 01-10-07
			if(m_save_close.equals("A")){
			out.println("if(window.opener.document.Form1.TXT_CORE_APPLICANT_CODE.value==\""+m_client_code+"\" ){");
			out.println("	b_flag=1");
			out.println("	}");
			}
			
			else if(m_save_close.equals("B")){
			out.println("if(window.opener.document.Form1.TXT_APPLICANT_CODE.value==\""+m_client_code+"\" ){");
			out.println("	b_flag=1");
			out.println("	}");
			}
			
			}
			
			else if(m_screen_name.equals("EDIT")){
			//added by nuwan de silva on 01-10-07 			
			if(m_save_close.equals("A")){
			out.println("if(window.opener.document.Form1.TXT_CORE_APPLICANT_CODE.value==\""+client_code+"\" ){");
			out.println("	b_flag=1");
			out.println("	}");
			}
			
			else if(m_save_close.equals("B")){
			out.println("if(window.opener.document.Form1.TXT_APPLICANT_CODE.value==\""+client_code+"\" ){");
			out.println("	b_flag=1");
			out.println("	}");
			}
     //comment by nuwan de silva on 01-10-07
		/*	out.println("if(window.opener.document.Form1.TXT_APPLICANT_CODE.value==\""+client_code+"\"  ){");
			out.println("	b_flag=1");
			out.println("	}");
			
			out.println("else if(window.opener.document.Form1.TXT_CORE_APPLICANT_CODE.value==\""+client_code+"\"  ){");
			out.println("	b_flag=1");
			out.println("	}");
			
			*/
			
			//out.println("	alert('b_flag'+b_flag);");
			}
			
			out.println("		for (var i=0; i < parseInt(no_of_rec); i++ ) {");
			
			out.println("if(m_screen_name=='NEW' && window.opener.document.Form1.elements[\"TXT_GAURANTOR_CODE\"+i].value==\""+m_client_code+"\"){");
			out.println("	b_flag=1");
			out.println("		}");
			out.println("else if( m_screen_name=='EDIT' && window.opener.document.Form1.elements[\"TXT_GAURANTOR_CODE\"+i].value==\""+client_code+"\"){");
			out.println("	b_flag=1");
			out.println("		}");
			
			
			out.println("		}");
			
			
			}
			
			out.println("	}");
			
			
			out.println("function displaymsg() {");
			out.println("var close_status=0;"); //added by nuwan de silva on 29-10-07
			out.println("alert("+m_msg+");");
			//out.println("alert("+m_no_of_rec+");");
			
		
			
			if(m_save_close.equals("A")){
			
			out.println("if(b_flag==0) {");
						
			out.println("		if(confirm(\"Are you sure you want to create the Co Applicant now?\")){ "); 
			//out.println("window.opener.document.Form1.TXT_GAURANTOR_CODE0.focus()");
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_MAS_display_client_creation?client_type="+client_type+"&inquiry_no="+m_inq_no+"&no_of_rec="+m_no_of_rec+"&save_close=B&close_status=Y';"); 
			out.println("	}");
			
			out.println("else	if(confirm(\"Are you sure you want to create the Gaurantor now?\")){ "); 
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_MK_display_application_guarantors?chksql=main_page&ROW=0&APP_NO="+m_APP_NO+"';");
			out.println("	}");
			
			out.println("else {");
			out.println("close_status=1;");
			out.println("window.close();");
			out.println("}");
			
			out.println("}");
			
			out.println("else {");
			out.println("alert('Client code Can not be duplicated');");
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_MAS_display_client_creation?client_type="+client_type+"&inquiry_no="+m_inq_no+"&no_of_rec="+m_no_of_rec+"&save_close=B&close_status=Y';"); 
		  out.println("}");
			
			
			if(m_screen_name.equals("NEW")){
			//out.println("if(window.opener.document.Form1.TXT_CORE_APPLICANT_CODE.value!=\""+m_client_code+"\"){");
			out.println("window.opener.document.Form1.TXT_APPLICANT_CODE.value=\""+m_client_code+"\"");
			out.println("window.opener.document.Form1.TXT_APPLICANT_NAME.value=\""+client_full_name+"\"");
						
		 }
			else if(m_screen_name.equals("EDIT")){
			//out.println("if(window.opener.document.Form1.TXT_CORE_APPLICANT_CODE.value!=\""+client_code+"\"){");
			out.println("window.opener.document.Form1.TXT_APPLICANT_CODE.value=\""+client_code+"\"");
			out.println("window.opener.document.Form1.TXT_APPLICANT_NAME.value=\""+client_full_name+"\"");
			}
			
			}
			
			else if(m_save_close.equals("B")){
			
			out.println("if(b_flag==0) {");
			
			out.println("		if(confirm(\"Are you sure you want to create the Gaurantor now?\")){ "); 
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_MK_display_application_guarantors?chksql=main_page&ROW=0&APP_NO="+m_APP_NO+"';");
			out.println("	}");
			
			out.println("else {");
			out.println("close_status=1;");
			out.println("window.close();");
			out.println("}");


			
			//out.println("window.close();");
			if(m_screen_name.equals("NEW")){
			//out.println("if(window.opener.document.Form1.TXT_APPLICANT_CODE.value!=\""+m_client_code+"\"){");
			out.println("window.opener.document.Form1.TXT_CORE_APPLICANT_CODE.value=\""+m_client_code+"\"");
			out.println("window.opener.document.Form1.TXT_CORE_APPLICANT_NAME.value=\""+client_full_name+"\"");
			}
			else if(m_screen_name.equals("EDIT")){
			//out.println("if(window.opener.document.Form1.TXT_APPLICANT_CODE.value!=\""+client_code+"\"){");
			out.println("window.opener.document.Form1.TXT_CORE_APPLICANT_CODE.value=\""+client_code+"\"");
			out.println("window.opener.document.Form1.TXT_CORE_APPLICANT_NAME.value=\""+client_full_name+"\"");
			}
			
			out.println("}");
			
			out.println("else {");
			out.println("alert('Client code can not be duplicated');");
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_MAS_display_client_creation?client_type="+client_type+"&inquiry_no="+m_inq_no+"&no_of_rec="+m_no_of_rec+"&save_close=B&close_status=Y';"); 
		  	out.println("}");
						
			
			}
			
		
			
			
			else if(!m_close.equals("G")){
			//out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_MAS_display_client_creation';");
     		out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_MAS_display_client_creation?screen=N';");//sandun on 11-5-2009
}
			else if(m_close.equals("G")){
			out.println("window.close()");
			}
			
			
			out.println("if(close_status==1){");
			//out.println("alert(window.opener.location());");
			//out.println("window.opener.before_submit();");//[Commented by milinda for unwanted alerts display in client creation level in application proccess]
			out.println("}");
			
			
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='validate_client(),displaymsg();'></body>");
			out.println("</html>");

			out.flush();

		}
		catch (Exception ex) {
			try{conn.rollback();}catch(Exception e){}
			out.println("Error:"+ex.toString());
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert('Error When Saving Record..');");
			out.println("window.history.back();"); 
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();
      out.close();
		}
		finally{
			try{conn.setAutoCommit(true);}catch(Exception e){}
			if(out!=null){try{out.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
		}

	}
}

