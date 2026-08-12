// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006

  
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_FA_MAS_save_client_creation extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt,callstmt1,callstmt2;
  String reqstr;
	ServletOutputStream out = null;
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
			//out.println("reqstr="+reqstr);
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url=m_sn_methods.html_client_url;
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			
      String m_client_code="";
			String m_date="";
			int m_num=0;
			int m_tnum=0;
			
			m_msg = "'Information saved successfully'";

			//------------------------------------------------------------------------------------------
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".FA_CO_MAS_CLIENT_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28,:29,:30,:31,:32,:33,:34,:35,:36,:37,:38,:39,:40,:41,:42,:43,:44,:45,:46,:47,:48,:49,:50,:51,:52,:53,:54,:55,:56); END;");
			
			m_client_code=m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE");
			
			if (m_client_code.equals("")){
				callstmt.registerOutParameter(1,java.sql.Types.CHAR);
			}
			else {
				callstmt.setString(1,m_client_code);
      }

			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_TYPE"));
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_RELATIONSHIP"));
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_FULL_NAME"));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_EXPOSURE_SECTOR"));
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_EXPOSURE_CAT"));
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_GROUP"));
			callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CRIB"));
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CRIB_COMMENT"));
			callstmt.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_HOME_STATUS"));
			callstmt.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_HOME_ADDRESS1"));
			callstmt.setString(12,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_HOME_ADDRESS2"));
			callstmt.setString(13,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CITY"));
			callstmt.setString(14,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_COLLECTION_AREA"));
			callstmt.setString(15,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_REFERENCE"));
			callstmt.setString(16,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_HOME_TELEPHONE"));
			callstmt.setString(17,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_FAXNO"));
			callstmt.setString(18,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_OFFICE_TELEPHONE"));
			callstmt.setString(19,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_EMAIL"));
			callstmt.setString(20,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_MOBILE"));
			callstmt.setString(21,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_KEY_DECISION_MAKER"));
			callstmt.setString(22,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CONT_PERSON"));		
			callstmt.setString(23,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_DESIG_PAYMENT"));
			callstmt.setString(24,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_FACTORY_STATUS"));
			callstmt.setString(25,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_FACTORY_ADDRESS1"));
			callstmt.setString(26,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_FACTORY_ADDRESS2"));
			callstmt.setString(27,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_FACTORY_CONPERSON"));
			callstmt.setString(28,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_FACTORY_TELEPHONE"));
			callstmt.setString(29,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_FACTORY_FAX"));
			callstmt.setString(30,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_FACTORY_EMAIL"));
			callstmt.setString(31,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CORESSS_STATUS"));
			callstmt.setString(32,m_sn_methods.met_formdata(reqstr,"TXT_CORESSS_HOMEADDRESS1"));
			callstmt.setString(33,m_sn_methods.met_formdata(reqstr,"TXT_CORESSS_HOMEADDRESS2"));
			callstmt.setString(34,m_sn_methods.met_formdata(reqstr,"TXT_CORESSS_CITY"));
			callstmt.setString(35,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CORESSS_TELEPHONE"));
			callstmt.setString(36,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CORESSS_FAX"));
			callstmt.setString(37,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CORESSS_EMAIL"));
			callstmt.setString(38,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_LEGAL_ST_BUSINESS"));
			callstmt.setString(39,m_sn_methods.met_formdata(reqstr,"TXT_BUSINESS_CERTIFICATE_NO"));
			callstmt.setString(40,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_ISSUED_SHARE_CAPITAL")));
			m_date=m_sn_methods.met_formdata(reqstr,"TXT_DATE_OF_INCORPORATION_DD")+"-"+
										m_sn_methods.met_formdata(reqstr,"TXT_DATE_OF_INCORPORATION_MM")+"-"+
										m_sn_methods.met_formdata(reqstr,"TXT_DATE_OF_INCORPORATION_YY");
			callstmt.setString(41,m_date);
			callstmt.setString(42,m_sn_methods.met_formdata(reqstr,"TXT_VAT_REG_NO"));
			m_date=m_sn_methods.met_formdata(reqstr,"TXT_VAT_REG_DATE_DD")+"-"+
										m_sn_methods.met_formdata(reqstr,"TXT_VAT_REG_DATE_MM")+"-"+
										m_sn_methods.met_formdata(reqstr,"TXT_VAT_REG_DATE_YY");
			callstmt.setString(43,m_date);
			callstmt.setString(44,m_sn_methods.met_formdata(reqstr,"TXT_WITH_HOLDING_TAX"));
			callstmt.setString(45,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_IDNO"));
			callstmt.setString(46,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_PASSPORT"));
			callstmt.setString(47,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_TITLE"));
			callstmt.setString(48,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_FNAME"));
			callstmt.setString(49,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_SNAME"));
			callstmt.setString(50,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_INITIALS").toUpperCase());
			callstmt.setString(51,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_ONAME"));
			m_date=m_sn_methods.met_formdata(reqstr,"TXT_DATE_OF_BIRTH_DD")+"-"+
										m_sn_methods.met_formdata(reqstr,"TXT_DATE_OF_BIRTH_MM")+"-"+
										m_sn_methods.met_formdata(reqstr,"TXT_DATE_OF_BIRTH_YY");
			callstmt.setString(52,m_date);
			callstmt.setString(53,m_sn_methods.met_formdata(reqstr,"TXT_GENDER"));
			callstmt.setString(54,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(55,m_username);
			callstmt.setString(56,m_sn_methods.met_formdata(reqstr,"TXT_3RD_PARTY_DEBTOR_DET"));
			callstmt.execute();
			
			if (m_client_code.equals("")){
				m_client_code=callstmt.getString(1);
			}
			
			callstmt.close();
			//-------------------------------------------------------------------------------------------
			callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_CO_MAS_CLIENT_DEBT_DET_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15); END;");

			callstmt1.setString(1,m_client_code);
			callstmt1.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_NO_OF_DEBTORS"));
			callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_BASIS_OF_CREDIT_GRANTED"));
			callstmt1.setString(4,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_AVG_CREDIT_PERIOD_EXTEND")));
			callstmt1.setString(5,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_AVG_CHEQUE_RETURN")));
			callstmt1.setString(6,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_AVG_DEPOSITS_BANK")));
			callstmt1.setString(7,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_AVG_GOOD_RETURN")));
			callstmt1.setString(8,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_AVG_BILLING_CYCLE")));
			callstmt1.setString(9,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_AVG_SALES_PER_MONTH")));
			callstmt1.setString(10,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_AVG_SALES_PER_DEBTOR")));
			callstmt1.setString(11,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_AVG_INVOICE_SIZE")));
			callstmt1.setString(12,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt1.setString(13,m_username);
			callstmt1.setString(14,m_sn_methods.met_formdata(reqstr,"TXT_CREDIT_GRANTED_SECURE_DETAILS"));
			callstmt1.setString(15,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_AVG_TOLERANCE_PERIOD")));
			callstmt1.execute();

			callstmt1.close();
			//-------------------------------------------------------------------------------------------
			callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_CO_MAS_CLIENT_COMP_DIR_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13); END;");
					
			m_num=Integer.parseInt((String)m_sn_methods.met_formdata(reqstr,"hid_director_count"));
			
			m_tnum=0;
			
			if(m_num>0){
				for(int i=1;i<=m_num;i++){
					String m_director_name=m_sn_methods.met_formdata(reqstr,"TXT_DIRECTOR_NAME_"+i);
					if(!m_director_name.equals("")){
						callstmt1.setString(1,m_client_code);
						callstmt1.setString(2,m_director_name);
						callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_DIRECTOR_NIC_"+i));
						callstmt1.setString(4,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_DIRECTOR_STAKE_"+i)));
						callstmt1.setString(5,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_DIRECTOR_NO_SHARES_"+i)));
						callstmt1.setString(6,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_DIRECTOR_VALUE_"+i)));
						callstmt1.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_DIRECTOR_POSITION_"+i));
						callstmt1.setString(8,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
						callstmt1.setString(9,m_username);
						callstmt1.setInt(10,m_tnum);
						callstmt1.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_GUARANTOR_STATUS_"+i));
						callstmt1.setString(12,m_sn_methods.met_formdata(reqstr,"TXT_DIRECTOR_ADDRESS_"+i));
						callstmt1.setString(13,m_sn_methods.met_formdata(reqstr,"TXT_DIR_COMMENT_"+i));
						callstmt1.execute();
						m_tnum++;
					}
				}
				callstmt1.close();
			}
			else{
				callstmt2=conn.prepareCall("BEGIN "+m_schema_name+".FA_CO_MAS_CLIENT_DELETION(:1,:2,:3); END;");
				callstmt2.setString(1,m_client_code);
				callstmt2.setString(2,"DIRECTOR_DATA");
				callstmt2.setString(3,m_username);
				callstmt2.execute();
				callstmt2.close();
			}
			//---------------  3rd Party Guarantors  ----- Added By Mahela On 13-12-2006  -----------------------------------------------------------------------
			callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_CO_MAS_CLIENT_GUARANT_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");
			
			m_num=Integer.parseInt((String)m_sn_methods.met_formdata(reqstr,"hid_guarantor_count"));
			
			m_tnum=0;
			
			if(m_num>0){
				for(int i=1;i<=m_num;i++){
					String m_guarantor_name=m_sn_methods.met_formdata(reqstr,"TXT_GUA_NAME_"+i);
					if(!m_guarantor_name.equals("")){
						callstmt1.setString(1,m_client_code);
						callstmt1.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_GUA_NAME_"+i));
						callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_GUA_NIC_"+i));
						callstmt1.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_GUA_ADDRESS_"+i));
						callstmt1.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_GUA_CONTACT_NO_"+i));
						callstmt1.setString(6,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
						callstmt1.setString(7,m_username);
						callstmt1.setInt(8,m_tnum);
						callstmt1.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_GUA_COMMENT_"+i));
						callstmt1.execute();
						m_tnum++;
					}
				}
				callstmt1.close();
			}
			else{
				callstmt2=conn.prepareCall("BEGIN "+m_schema_name+".FA_CO_MAS_CLIENT_DELETION(:1,:2,:3); END;");
				callstmt2.setString(1,m_client_code);
				callstmt2.setString(2,"GUARANTOR_DATA");
				callstmt2.setString(3,m_username);
				callstmt2.execute();
				callstmt2.close();
			}
			
			//---------------  Subsidiaries & Associated Companies  ---- Added By Mahela On 13-12-2006  ------------------------------------------------------------------------
			callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_CO_MAS_CLIENT_SUBSIDIA_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11); END;");
			
			m_num=Integer.parseInt((String)m_sn_methods.met_formdata(reqstr,"hid_associated_comp_count"));
			
			m_tnum=0;
			
			if(m_num>0){
				for(int i=1;i<=m_num;i++){
					String m_company_name=m_sn_methods.met_formdata(reqstr,"TXT_COMPANY_NAME_"+i);
					String m_stake=m_sn_methods.met_formdata(reqstr,"TXT_COMPANY_STAKE_"+i);
					String m_value=m_sn_methods.met_formdata(reqstr,"TXT_COMPANY_VALUE_"+i);
					String m_ba=m_sn_methods.met_formdata(reqstr,"TXT_COMPANY_BA_"+i);
					
					if(!m_company_name.equals("") && !m_stake.equals("") && !m_value.equals("") && !m_ba.equals("")  ){
						callstmt1.setString(1,m_client_code);
						callstmt1.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_COMPANY_NAME_"+i));
						callstmt1.setString(3,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_COMPANY_STAKE_"+i)));
						callstmt1.setString(4,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_COMPANY_VALUE_"+i)));
						callstmt1.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_COMPANY_TEL_NO_"+i));
						callstmt1.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_COMPANY_OFFICER_"+i));
						callstmt1.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_COMPANY_BA_"+i));
						callstmt1.setString(8,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
						callstmt1.setString(9,m_username);
						callstmt1.setInt(10,m_tnum);
						callstmt1.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_COMPANY_COMMENT_"+i));
						callstmt1.execute();
						m_tnum++;
					}
				}
				callstmt1.close();
			}
			else{
				callstmt2=conn.prepareCall("BEGIN "+m_schema_name+".FA_CO_MAS_CLIENT_DELETION(:1,:2,:3); END;");
				callstmt2.setString(1,m_client_code);
				callstmt2.setString(2,"SUBSIDIA_DATA");
				callstmt2.setString(3,m_username);
				callstmt2.execute();
				callstmt2.close();
			}
			
			
			
			//-------------------------------------------------------------------------------------------
			callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_CO_MAS_CLIENT_BANKS_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13); END;");
			
			m_num=Integer.parseInt((String)m_sn_methods.met_formdata(reqstr,"hid_bank_accounts_count"));
			
			m_tnum=0;
			
			if(m_num>0){
				for(int i=1;i<=m_num;i++){
					String m_bank_code=m_sn_methods.met_formdata(reqstr,"TXT_BANK_NAME_"+i);
					if(!m_bank_code.equals("")){
						callstmt1.setString(1,m_client_code);
						callstmt1.setString(2,m_bank_code);
						callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_BANK_BRANCH_"+i));
						callstmt1.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_BANK_ACCOUNT_"+i));
						callstmt1.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_BANK_FROMDATE_"+i));
						callstmt1.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_BANK_REFERENCE_"+i));
						callstmt1.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_BANK_TELEPNO_"+i));
						callstmt1.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_BANK_FAXNO_"+i));
						callstmt1.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_BANK_RELATION_"+i));
						callstmt1.setString(10,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
						callstmt1.setString(11,m_username);
						callstmt1.setInt(12,m_tnum);
						callstmt1.setString(13,m_sn_methods.met_formdata(reqstr,"TXT_BANK_COMMENT_"+i));
						callstmt1.execute();
						m_tnum++;
					}
				}
				callstmt1.close();
			}
			else{
				callstmt2=conn.prepareCall("BEGIN "+m_schema_name+".FA_CO_MAS_CLIENT_DELETION(:1,:2,:3); END;");
				callstmt2.setString(1,m_client_code);
				callstmt2.setString(2,"BANK_DATA");
				callstmt2.setString(3,m_username);
				callstmt2.execute();
				callstmt2.close();
			}
			
			//---------------  Auditors  ---- Added By Mahela On 13-12-2006  ------------------------------------------------------------------------
			callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_CO_MAS_CLIENT_AUDITOR_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11); END;");
			
			m_num=Integer.parseInt((String)m_sn_methods.met_formdata(reqstr,"hid_auditor_count"));
			
			m_tnum=0;
			
			if(m_num>0){
				for(int i=1;i<=m_num;i++){
					String m_company_name=m_sn_methods.met_formdata(reqstr,"TXT_AUDITOR_NAME_"+i);
					String m_add=m_sn_methods.met_formdata(reqstr,"TXT_AUDITOR_ADDRESS_"+i);
					
					if(!m_company_name.equals("") && !m_add.equals("") ){
						callstmt1.setString(1,m_client_code);
						callstmt1.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_AUDITOR_NAME_"+i));
						callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_AUDITOR_ADDRESS_"+i));
						callstmt1.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_AUDITOR_REFERENCE_"+i));
						callstmt1.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_AUDITOR_TEL_NO_"+i));
						callstmt1.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_AUDITOR_FAX_NO_"+i));
						callstmt1.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_AUDITOR_RELATION_"+i));
						callstmt1.setString(8,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
						callstmt1.setString(9,m_username);
						callstmt1.setInt(10,m_tnum);
						callstmt1.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_AUDITOR_COMMENT_"+i));
						callstmt1.execute();
						m_tnum++;
					}
				}
				callstmt1.close();
			}
			else{
				callstmt2=conn.prepareCall("BEGIN "+m_schema_name+".FA_CO_MAS_CLIENT_DELETION(:1,:2,:3); END;");
				callstmt2.setString(1,m_client_code);
				callstmt2.setString(2,"AUDITOR_DATA");
				callstmt2.setString(3,m_username);
				callstmt2.execute();
				callstmt2.close();
			}
			
			//--------------- Credit Facilities  ---  Added By Mahela On 13-12-2006 -------------------------------------------------------------------------
			callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_CO_MAS_CLIENT_CREDIT_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14); END;");
			
			m_num=Integer.parseInt((String)m_sn_methods.met_formdata(reqstr,"hid_credit_facility_count"));
			
			m_tnum=0;
			
			if(m_num>0){
				for(int i=1;i<=m_num;i++){
					String m_institute_name=m_sn_methods.met_formdata(reqstr,"TXT_CREDIT_NAME_"+i);
					
					if(!m_institute_name.equals("")){
						callstmt1.setString(1,m_client_code);
						callstmt1.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_CREDIT_NAME_"+i));
						callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_CREDIT_CONTACT_PERSON_"+i));
						callstmt1.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_CREDIT_TYPE_"+i));
						callstmt1.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_CREDIT_SECURITY_"+i));
						callstmt1.setString(6,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_CREDIT_APP_AMOUNT_"+i)));
						callstmt1.setString(7,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_CREDIT_MONTHLY_RENT_"+i)));
						callstmt1.setString(8,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_CREDIT_PERIOD_"+i)));
						callstmt1.setString(9,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_CREDIT__BAL_PAYABLE_"+i)));
						callstmt1.setString(10,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_CREDIT_BAL_OUT_"+i)));
						callstmt1.setString(11,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
						callstmt1.setString(12,m_username);
						callstmt1.setInt(13,m_tnum);
						callstmt1.setString(14,m_sn_methods.met_formdata(reqstr,"TXT_CREDIT_COMMENT_"+i));
						callstmt1.execute();
						m_tnum++;
					}
				}
				callstmt1.close();
			}
			else{
				callstmt2=conn.prepareCall("BEGIN "+m_schema_name+".FA_CO_MAS_CLIENT_DELETION(:1,:2,:3); END;");
				callstmt2.setString(1,m_client_code);
				callstmt2.setString(2,"CREDIT_FACILITY_DATA");
				callstmt2.setString(3,m_username);
				callstmt2.execute();
				callstmt2.close();
			}
			
			//--------------- Proposed Security -----  Added By Mahela On 13-12-2006  -----------------------------------------------------------------------
			callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_CO_MAS_CLIENT_PROP_SEC_SAVE(:1,:2,:3,:4,:5,:6,:7,:8); END;");
			
			m_num=Integer.parseInt((String)m_sn_methods.met_formdata(reqstr,"hid_security_count"));
			
			m_tnum=0;
			
			if(m_num>0){
				for(int i=1;i<=m_num;i++){
					String m_ownership=m_sn_methods.met_formdata(reqstr,"TXT_SECURITY_OWNERSHIP_"+i);
					
					if(!m_ownership.equals("")){
						callstmt1.setString(1,m_client_code);
						callstmt1.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_SECURITY_OWNERSHIP_"+i));
						callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_SECURITY_TYPE_"+i));
						callstmt1.setString(4,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_SECURITY_VALUE_"+i)));
						callstmt1.setString(5,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
						callstmt1.setString(6,m_username);
						callstmt1.setInt(7,m_tnum);
						callstmt1.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_SECURITY_COMMENT_"+i));
						callstmt1.execute();
						m_tnum++;
					}
				}
				callstmt1.close();
			}
			else{
				callstmt2=conn.prepareCall("BEGIN "+m_schema_name+".FA_CO_MAS_CLIENT_DELETION(:1,:2,:3); END;");
				callstmt2.setString(1,m_client_code);
				callstmt2.setString(2,"PROP_SECURITY_DATA");
				callstmt2.setString(3,m_username);
				callstmt2.execute();
				callstmt2.close();
			}
			
			//-------------------------------------------------------------------------------------------
			callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_CO_MAS_CLIENT_CUST_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11); END;");
				
			m_num=Integer.parseInt((String)m_sn_methods.met_formdata(reqstr,"hid_other_suppliers_count"));
			
			m_tnum=0;
			if(m_num>0){
				for(int i=1;i<=m_num;i++){
					String m_supplier_name=m_sn_methods.met_formdata(reqstr,"TXT_OTHER_SUPPLIER_NAME_"+i);
					if(!m_supplier_name.equals("")){
						callstmt1.setString(1,m_client_code);
						callstmt1.setString(2,m_supplier_name);
						callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_OTHER_SUPPLIER_ADDRESS_"+i));
						callstmt1.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_OTHER_SUPPLIER_RELATION_"+i));
						callstmt1.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_OTHER_SUPPLIER_CON_PERSON_"+i));
						callstmt1.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_OTHER_SUPPLIER_TELENO_"+i));
						callstmt1.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_OTHER_SUPPLIER_TYPE_"+i));
						callstmt1.setString(8,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
						callstmt1.setString(9,m_username);
						callstmt1.setInt(10,m_tnum);
						callstmt1.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_OTHER_SUPPLIER_COMMENT_"+i));
						callstmt1.execute();
						m_tnum++;
					}
				}
				callstmt1.close();
			}
			else{
				callstmt2=conn.prepareCall("BEGIN "+m_schema_name+".FA_CO_MAS_CLIENT_DELETION(:1,:2,:3); END;");
				callstmt2.setString(1,m_client_code);
				callstmt2.setString(2,"CUSTOMER_DATA");
				callstmt2.setString(3,m_username);
				callstmt2.execute();
				callstmt2.close();
			}
			//-------------------------------------------------------------------------------------------
			callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_CO_MAS_CLIENT_PROD_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17); END;");
				
			m_num=Integer.parseInt((String)m_sn_methods.met_formdata(reqstr,"hid_product_count"));
			
			m_tnum=0;
			
			if(m_num>0){
				for(int i=1;i<=m_num;i++){
					String m_product_name=m_sn_methods.met_formdata(reqstr,"TXT_PRODUCT_NAME_"+i);
					if(!m_product_name.equals("")){
						callstmt1.setString(1,m_client_code);
						callstmt1.setString(2,m_product_name);
						callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_NATURE_PRODUCT_"+i));
						callstmt1.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_PRODUCT_DESC_"+i));
						callstmt1.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_ESTABLISH_MARKET_"+i));
						callstmt1.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_ESTABLISH_MARKET_COMMENTS_"+i));
						callstmt1.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_PROTENTIAL_TO_GROUTH_"+i));
						callstmt1.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_PROTENTIAL_TO_GROUTH_COMMENTS_"+i));
						callstmt1.setString(9,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_PRESENT_MARKET_SHARE_"+i)));
						callstmt1.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_SALES_TREAND_"+i));
						callstmt1.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_PRODUCT_SUB_SECTOR_"+i));
						callstmt1.setString(12,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
						callstmt1.setString(13,m_username);
						callstmt1.setInt(14,m_tnum);
						callstmt1.setString(15,m_sn_methods.met_formdata(reqstr,"TXT_PRODUCT_CAT_"+i));
						callstmt1.setString(16,m_sn_methods.met_formdata(reqstr,"TXT_PRESENT_MARKET_SHARE_DETAILS"+i));
						callstmt1.setString(17,m_sn_methods.met_formdata(reqstr,"TXT_PRODUCT_COMMENT_"+i));
						callstmt1.execute();
						m_tnum++;
					}
				}
				callstmt1.close();
			}
			else{
				callstmt2=conn.prepareCall("BEGIN "+m_schema_name+".FA_CO_MAS_CLIENT_DELETION(:1,:2,:3); END;");
				callstmt2.setString(1,m_client_code);
				callstmt2.setString(2,"PRODUCT_DATA");
				callstmt2.setString(3,m_username);
				callstmt2.execute();
				callstmt2.close();
			}
			//-------------------------------------------------------------------------------------------
			callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_CO_MAS_CLIENT_TOP_DEBT_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12); END;");
				
			m_num=Integer.parseInt((String)m_sn_methods.met_formdata(reqstr,"hid_top_debtors_count"));
				
			m_tnum=0;
			
			if(m_num>0){
				for(int i=1;i<=m_num;i++){
					String m_debtor_name=m_sn_methods.met_formdata(reqstr,"TXT_DEBTOR_NAME_"+i);
					if(!m_debtor_name.equals("")){
						callstmt1.setString(1,m_client_code);
						callstmt1.setString(2,m_debtor_name);
						callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_DEBTOR_ADDRESS_"+i));
						callstmt1.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_DEBTOR_TELENO_"+i));
						callstmt1.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_DEBTOR_FAX_"+i));
						callstmt1.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_DEBTOR_CON_PERSON_"+i));
						callstmt1.setString(7,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_DEBTOR_AVG_SALES_VALUE_"+i)));
						callstmt1.setString(8,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_DEBTOR_AVG_INVOICE_"+i)));
						callstmt1.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_DEBTOR_COMMNTS_"+i));
						callstmt1.setString(10,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
						callstmt1.setString(11,m_username);
						callstmt1.setInt(12,m_tnum);
						callstmt1.execute();
						m_tnum++;
					}
				}
				callstmt1.close();
			}
			else{
				callstmt2=conn.prepareCall("BEGIN "+m_schema_name+".FA_CO_MAS_CLIENT_DELETION(:1,:2,:3); END;");
				callstmt2.setString(1,m_client_code);
				callstmt2.setString(2,"TOP_DEBTOR_DATA");
				callstmt2.setString(3,m_username);
				callstmt2.execute();
				callstmt2.close();
			}
			
			//-------------------------------------------------------------------------------------------------------------
			
			callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_CO_MAS_CLIENT_INQUIRY_SAVE(:1,:2,:3,:4); END;");
			callstmt1.setString(1,m_client_code);
			callstmt1.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_INQUERY_NO"));
			callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt1.setString(4,m_username);
			callstmt1.execute();
			callstmt1.close();

			//-------------------------------------------------------------------------------------------------------------
			m_msg = "'Information saved successfully, Client/Debtor Code "+m_client_code+"'";
			
			     
			conn.commit();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_class_url+"/"+m_schema_name+"_FA_MAS_display_client_creation';");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
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
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}

	}
}

