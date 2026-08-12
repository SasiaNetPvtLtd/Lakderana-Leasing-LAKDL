//Option Id is 2.1  
//This File was created by SVA on 17-05-2006 
//Marketing Inquiry Display
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
//import FCAM_sn_methods;
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_MK_XMLFile extends javax.servlet.http.HttpServlet {
	
	// added by udara 20-05-2017
	/*
	Connection conn;
	Statement stmt;
	java.text.NumberFormat nf;
	public ResultSet rs;
	public String m_chksql;
	ServletOutputStream out = null;
	*/
	
	// added by udara 20-05-20174
	Connection conn = null;
	Statement stmt = null;
	java.text.NumberFormat nf = null;
	ResultSet rs = null;
	String m_chksql = null;
	ServletOutputStream out = null;
	// end by udara 20-05-20174
	
	//public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	public void service(HttpServletRequest req, HttpServletResponse res) // added by udara 20-05-2017
	{
		
		try {
			
			//************************************************************	
			LAKDL_AF_MK_CO_methods CO_methods = new LAKDL_AF_MK_CO_methods();
			
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String m_schema_name = con_method.schema_name;
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
			String m_username 						= con_method.username;
			
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.40.1:1521:DN10G", "system", "sys123");
			
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
			nf.setMinimumFractionDigits(0);
			nf.setMaximumFractionDigits(0);
			
			res.setStatus(HttpServletResponse.SC_OK);
			//httpservletresponse.setContentType("text/xml");
			res.setStatus(200);
			res.setContentType("text/xml");
			out = res.getOutputStream();
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
				
			}
			else if(m_chksql.trim().equals("get_price_det")){
				
				String m_pno      = req.getParameter("pno");
				
				rs = stmt.executeQuery(" SELECT PRICING_NO, INQUIRY_NO, TRANSACION_TYPE, TRN_SUB_TYPE,PERIOD,  "+						
					"    PAYMENT_INTERVAL, RATE, GROSS_AMOUNT, VAT_AMOUNT, "+
					//"    PAYMENT_INTERVAL, A.RATE, A.GROSS_AMOUNT, A.VAT_AMOUNT, "+
					"    NET_AMOUNT, NIBSM, AMI, RESIDUAL_VALUE, "+ 
					"    ITEM_CATEGORY, ITEM_SUB_CAT_CODE, "+
					"    MAKE_CODE, MODEL_CODE, SUB_MODEL_CODE, ASSET_USAGE_TYPE, "+
					"    VAT_PERCENTAGE, ENGINE_CAPACITY, FUEL_TYPE, TARE, "+
					"    MAINTENANCE_STATUS,  "+
					"    OUTFLOW_PATTERN, SUPPLIER_CREDIT,PAYMENT_MODE, "+
					"    CURRENCY_CODE, '1', SUPPLIER, "+
					"    TRAN_AMOUNT_CURRENCY, BUY_BACK, "+
					"    INTEREST_TYPE, VARIABLE_INT_BASE, INT_MARGIN, "+
					"    CONDITION_OF_ASSET,VAT_APP,VAT_ON_RENTAL,NVL(LEAD_SOURCE_NAME,'-'), "+
					"    "+m_schema_name+".AF_GET_PRICE_ALLO_STATUS(PRICING_NO), "+//ADD BY INDITHA 6-12-2007
					" NVL(LAST_RENTAL,0), "+	//	as RENTAL_AMOUNT add by warua 2012-05-09
					" NVL((SELECT NVL(FIRST_NAME || ' ' || LAST_NAME,'-') FROM "+m_schema_name+".AF_CO_MAS_BROKER WHERE BROKER_CODE=A.LEAD_SOURCE_NAME ),A.LEAD_SOURCE_NAME) BROKER_CODE,  "+ // thamali 2013.08.29
					" NVL("+m_schema_name+".AF_CO_GET_APP_STATUS(A.APP_NO),'-')   "+ // 43 added by udara 19-09-2017
					
					" FROM  "+m_schema_name+".AF_MK_PRO_PRICING A "+
					" WHERE PRICING_NO LIKE UPPER('"+m_pno+"%') "+
					//" AND   APP_NO NOT IN (SELECT APPLICATION_NO FROM AF_CO_PRO_APPLICATION_DETAILS WHERE APPLICATION_NO = A.APP_NO     AND APPLICATION_STATUS = 'ACTIVATED') "+ // added by udara 15-02-2017 // commented by udara 19-09-2017
					//" AND   APP_NO NOT IN (SELECT APPLICATION_NO FROM AF_CO_PRO_APPLICATION_DETAILS WHERE INQUIRY_NO     = A.INQUIRY_NO AND APPLICATION_STATUS = 'ACTIVATED') "+ // added by udara 15-02-2017 // commented by udara 19-09-2017
					" ORDER BY ENT_DATE DESC");
				
				boolean flag = rs.next();
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<PNO>"        + rs.getString(1)  + "</PNO>");
					out.println("<ID>"         + rs.getString(2)  + "</ID>");
					out.println("<TRNTYPE>"    + rs.getString(3)  + "</TRNTYPE>");
					out.println("<TRNSUB>"     + rs.getString(4)  + "</TRNSUB>");
					out.println("<PERIOD>"     + rs.getString(5)  + "</PERIOD>");
					out.println("<PAYINT>"     + rs.getString(6)  + "</PAYINT>");
					out.println("<RATE>"       + rs.getString(7)  + "</RATE>");
					out.println("<GAMOUNT>"    + rs.getString(8)  + "</GAMOUNT>");
					out.println("<VAMOUNT>"    + rs.getString(9)  + "</VAMOUNT>");
					out.println("<NAMOUNT>"    + rs.getString(10) + "</NAMOUNT>");
					out.println("<NIBSM>"      + rs.getString(11) + "</NIBSM>");
					out.println("<AMI>"        + rs.getString(12) + "</AMI>");
					out.println("<RES>"        + rs.getString(13) + "</RES>");
					out.println("<CAT>"        + rs.getString(14) + "</CAT>");
					out.println("<SUB>"        + rs.getString(15) + "</SUB>");
					out.println("<MAKE>"       + rs.getString(16) + "</MAKE>");
					out.println("<MODEL>"      + rs.getString(17) + "</MODEL>");
					out.println("<SUBMODEL>"   + rs.getString(18) + "</SUBMODEL>");
					out.println("<USAGETYPE>"  + rs.getString(19) + "</USAGETYPE>");
					out.println("<VATPE>"      + rs.getString(20) + "</VATPE>");
					out.println("<ENCAP>"      + rs.getString(21) + "</ENCAP>");
					out.println("<FUEL>"       + rs.getString(22) + "</FUEL>");
					out.println("<TARE>"       + rs.getString(23) + "</TARE>");
					out.println("<MAIN>"       + rs.getString(24) + "</MAIN>");
					out.println("<OUTP>"       + rs.getString(25) + "</OUTP>");
					out.println("<SUPCR>"      + rs.getString(26) + "</SUPCR>");
					out.println("<PAYMOD>"     + rs.getString(27) + "</PAYMOD>");
					//out.println("<TRNSUB>"     + rs.getString(28) + "</TRNSUB>");
					out.println("<CURR>"       + rs.getString(28) + "</CURR>");
					out.println("<EXCHA>1</EXCHA>");
					out.println("<SUPP>"       + rs.getString(30) + "</SUPP>");
					out.println("<TAMOCURR>"   + rs.getString(31) + "</TAMOCURR>");
					out.println("<BUYBACK>"    + rs.getString(32) + "</BUYBACK>");
					out.println("<INTTYPE>"    + rs.getString(33) + "</INTTYPE>");
					out.println("<INTBASE>"    + rs.getString(34) + "</INTBASE>");
					out.println("<INT_MARG>"   + rs.getString(35) + "</INT_MARG>");
					out.println("<CONASSET>"   + rs.getString(36) + "</CONASSET>");
					out.println("<VAT_APP>"    + rs.getString(37) + "</VAT_APP>");
					out.println("<VAT_REN>"    + rs.getString(38) + "</VAT_REN>");
					out.println("<LEAD_SO>"    + rs.getString(39) + "</LEAD_SO>");
					out.println("<PRISTATUS>"  + rs.getString(40) + "</PRISTATUS>");
					out.println("<RENT>"  + rs.getString(41) + "</RENT>");//add by waruna 2012-05-09
					out.println("<BROKER_NAME>"  + rs.getString(42) + "</BROKER_NAME>");//add by THAMALI 2013.09.12
					out.println("<APP_STATUS>"  + rs.getString(43) + "</APP_STATUS>"); // added by udara 19-09-2017
					
					out.println("</ITEM>");
				}
				
				//out.println("</DATA>");
				out.println("</Root>");
				
			}
			else if(m_chksql.trim().equals("get_inq_det")){
				
				String m_pno      = req.getParameter("inqno");
				
				
				rs = stmt.executeQuery(" SELECT A.INQUIRY_CODE, A.CLIENT_NAME,NVL(A.TEL_NO,'-'),"+
					"        NVL(A.MOBILE_NO,'-'),NVL(A.FAX_NO,'-'),NVL(A.ADDRESS,'-'), "+
					"      	NVL(A.ADDRESS2,'-'), A.CITY_CODE, A.LEGAL_ENTITY, "+
					"        A.STATUS,A.INITIATION_TYPE,A.CLIENT_CATEGORY, "+
					"        A.legal_entity,A.LEAD_SOURCE_CATEGORY, "+
					"        NVL(A.LEAD_SOURCE_NAME,'-'), NVL(A.INTRODUCER,'-'), "+
					"        NVL(A.ID_NO,'-'), NVL(A.INQUIRY_STATUS,'-'), "+
					"        NVL(A.SUB_PRODUCT_CODE,'-'),NVL(A.TRANSACTION_SUB_TYPE,'-'), "+
					"        NVL(A.EMAIL,'-'), A.TEAM, A.MK_OFFICER, "+
					"        A.MK_SUPERVISOR, NVL(A.CONTACT_PERSON,'-'), "+
					"        NVL(CLIENT_LAST_NAME,'-'),TITLE , "+
					"        NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE),'-'), "+
					"        NVL(BRANCH_CODE,'-'),"+m_schema_name+".AF_CO_GET_LOCATION_DESC(BRANCH_CODE) "+	
					"  FROM  "+m_schema_name+".AF_MK_PRO_INQUIRY A "+
					" WHERE  INQUIRY_CODE = UPPER('"+m_pno+"') "+
					" ORDER BY ENT_DATE DESC");
				
				boolean flag = rs.next();
				//out.println("flag="+flag);
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<CNA>"        + rs.getString(1)  + "</CNA>");
					out.println("<ID>"         + rs.getString(2)  + "</ID>");
					out.println("<TNO>"        + rs.getString(3)  + "</TNO>");
					out.println("<MNO>"        + rs.getString(4)  + "</MNO>");
					out.println("<FNO>"        + rs.getString(5)  + "</FNO>");
					out.println("<ADD>"        + rs.getString(6)  + "</ADD>");
					out.println("<AD2>"        + rs.getString(7)  + "</AD2>");
					out.println("<CIT>"        + rs.getString(8)  + "</CIT>");
					out.println("<LEG>"        + rs.getString(9)  + "</LEG>");
					out.println("<STU>"        + rs.getString(10) + "</STU>");
					out.println("<INI>"        + rs.getString(11) + "</INI>");
					out.println("<CCA>"        + rs.getString(12) + "</CCA>");
					out.println("<CTY>"        + rs.getString(13) + "</CTY>");
					out.println("<LSC>"        + rs.getString(14) + "</LSC>");
					out.println("<LSN>"        + rs.getString(15) + "</LSN>");
					out.println("<INT>"        + rs.getString(16) + "</INT>");
					out.println("<IDN>"        + rs.getString(17) + "</IDN>");
					out.println("<INS>"        + rs.getString(18) + "</INS>");
					out.println("<SPC>"        + rs.getString(19) + "</SPC>");
					out.println("<TST>"        + rs.getString(20) + "</TST>");
					out.println("<EMA>"        + rs.getString(21) + "</EMA>");
					out.println("<TEA>"        + rs.getString(22) + "</TEA>");
					out.println("<OFF>"        + rs.getString(23) + "</OFF>");
					out.println("<SUP>"        + rs.getString(24) + "</SUP>");
					out.println("<CON>"        + rs.getString(25) + "</CON>");
					out.println("<CLN>"        + rs.getString(26) + "</CLN>");
					out.println("<TIT>"        + rs.getString(27) + "</TIT>");
					out.println("<CDE>"        + rs.getString(28) + "</CDE>");
					out.println("<BCO>"        + rs.getString(29) + "</BCO>");
					out.println("<BDE>"        + rs.getString(30) + "</BDE>");
					out.println("</ITEM>");
				}
				
				//out.println("</DATA>");
				out.println("</Root>");
				
			}
			
			else if(m_chksql.trim().equals("get_ls")){
				try{
					
					String m_appno      = req.getParameter("app_no");
					
					
					rs = stmt.executeQuery(" SELECT	A.LEAD_SOURCE_NAME, "+
						" NVL((SELECT NVL(FIRST_NAME || ' ' || LAST_NAME,'-') FROM "+m_schema_name+".AF_CO_MAS_BROKER WHERE BROKER_CODE=A.LEAD_SOURCE_NAME ),A.LEAD_SOURCE_NAME) BROKER_CODE  "+ // thamali 2013.08.29
						
						"   FROM	"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
						"  WHERE	A.APPLICATION_NO = '"+m_appno+"'");
					
					boolean flag = rs.next();
					
					out.println("<Root>");
					for(; flag; flag = rs.next())				{
						out.println("<ITEM>");
						out.println("<LSN>"        + rs.getString(1)  + "</LSN>");
						out.println("<LSN>"        + rs.getString(2)  + "</LSN>");
						
						out.println("</ITEM>");
					}
					
					out.println("</Root>");
				}catch(Exception e){
					out.println(e.toString());
				}
				
			}
			
			else if(m_chksql.trim().equals("get_team")){
				
				String m_pno      = req.getParameter("team");
				
				
				rs = stmt.executeQuery(" SELECT A.TEAM_ID, A.TEAM_DESC, "+
					"        A.TEAM_HEAD "+
					" FROM   "+m_schema_name+".AF_CO_MAS_TEAMS A "+
					" WHERE  ACTIVE_STATUS='Y' AND A.TEAM_ID=UPPER('"+m_pno+"') ");
				
				boolean flag = rs.next();
				//out.println("flag="+flag);
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<TID>"        + rs.getString(1)  + "</TID>");
					out.println("<TDE>"        + rs.getString(2)  + "</TDE>");
					out.println("</ITEM>");
				}
				
			}
			else if(m_chksql.trim().equals("load_team")){
				
				String m_pno = req.getParameter("team");
				
				
				rs = stmt.executeQuery(" SELECT A.TEAM_ID, A.TEAM_DESC, "+
					"        A.TEAM_HEAD "+
					" FROM   "+m_schema_name+".AF_CO_MAS_TEAMS A "+
					" WHERE  ACTIVE_STATUS='Y' AND A.TEAM_ID=UPPER('"+m_pno+"') ");
				
				boolean flag = rs.next();
				//out.println("flag="+flag);
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<TID>"        + rs.getString(1)  + "</TID>");
					out.println("<TDE>"        + rs.getString(2)  + "</TDE>");
					out.println("</ITEM>");
				}																   
				
				out.println("</Root>");
				
			}
			
			else if(m_chksql.trim().equals("loadOfficer")){			//Added By Sandun on 06-11-2008
				String m_user = req.getParameter("user_name");
				
				rs = stmt.executeQuery(" SELECT "+
					" USER_ID,	"+	 //1
					" A.TEAM_ID, 	"+	//2	
					" A.TEAM_HEAD "+//3
					" FROM "+m_schema_name+".AF_CO_MAS_TEAMS A, "+
					"      "+m_schema_name+".AF_CO_MAS_TEAM_MEMBERS B ,"+
					"      "+m_schema_name+".AF_CO_MAS_SUB_TEAMS_ASSIGN C, "+
					"      "+m_schema_name+".AF_CO_MAS_SUB_TEAMS D, "+
					"      "+m_schema_name+".CO_CO_MAS_EMPLOYEE E"+
					" WHERE B.TEAM_ID=C.SUB_TEAM_ID "+
					" AND   A.TEAM_ID=C.TEAM_ID "+
					" AND   C.SUB_TEAM_ID=D.SUB_TEAM_ID "+
					" AND   B.USER_ID = E.EMP_CODE "+
					" AND   C.ACTIVE_STATUS='Y'  "+
					" AND   B.ACTIVE_STATUS='Y'  "+
					" AND   E.ACTIVE_STATUS='Y' "+
					" AND   E.FIRST_NAME = UPPER('"+m_user+"')" );																			
				
				
				boolean flag = rs.next();
				//out.println("flag="+flag);
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<R1>"        + rs.getString(1)  + "</R1>");	
					out.println("<R2>"        + rs.getString(2)  + "</R2>");	
					out.println("<R3>"        + rs.getString(3)  + "</R3>");	
					out.println("</ITEM>");
				}						   
				
				out.println("</Root>");
				
			}
			
			else if(m_chksql.trim().equals("get_head")){
				
				String m_pno      = req.getParameter("head");
				
				
				rs = stmt.executeQuery(" SELECT A.TEAM_ID, A.TEAM_HEAD "+
					" FROM   "+m_schema_name+".AF_CO_MAS_TEAMS A "+
					" WHERE  ACTIVE_STATUS='Y' AND A.TEAM_HEAD=UPPER('"+m_pno+"') ");
				
				boolean flag = rs.next();
				//out.println("flag="+flag);
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<TID>"        + rs.getString(1)  + "</TID>");
					out.println("<TDE>"        + rs.getString(2)  + "</TDE>");
					out.println("</ITEM>");
				}
				
				//out.println("</DATA>");
				out.println("</Root>");
				
			}		
			else if(m_chksql.trim().equals("getTeam")){
				
				String m_uid      = req.getParameter("user");
				
				
				rs = stmt.executeQuery(" SELECT TEAM_ID, TEAM_HEAD "+
					" FROM   "+m_schema_name+".AF_CO_MAS_TEAMS "+
					" WHERE ACTIVE_STATUS='Y' AND TEAM_ID IN (SELECT  TEAM_ID "+
					"                   FROM "+m_schema_name+".AF_CO_MAS_TEAM_MEMBERS  "+
					"                   WHERE USER_ID='"+m_uid+"' )");
				
				boolean flag = rs.next();
				//out.println("flag="+flag);
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<TID>"        + rs.getString(1)  + "</TID>");
					out.println("<TDE>"        + rs.getString(2)  + "</TDE>");
					out.println("</ITEM>");
				}
				
				//out.println("</DATA>");
				out.println("</Root>");
				
			}	
			
			else if(m_chksql.trim().equals("getMktTeam")){
				
				String m_uid = req.getParameter("user");
				
				
				rs = stmt.executeQuery(" SELECT TEAM_ID, TEAM_HEAD "+
					" FROM   "+m_schema_name+".AF_CO_MAS_TEAMS "+
					" WHERE ACTIVE_STATUS='Y' AND TEAM_ID IN (SELECT  TEAM_ID "+
					"                   FROM "+m_schema_name+".AF_CO_MAS_TEAM_MEMBERS  "+
					"                   WHERE USER_ID='"+m_uid+"' )");
				
				boolean flag = rs.next();
				//out.println("flag="+flag);
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<TID>"        + rs.getString(1)  + "</TID>");
					out.println("<TDE>"        + rs.getString(2)  + "</TDE>");
					out.println("</ITEM>");
				}
				
				//out.println("</DATA>");
				out.println("</Root>");
				
			}	
			
			
			else if(m_chksql.trim().equals("getPayee")){
				
				String m_PAYEE     = req.getParameter("PAYEE");
				String m_MAINTEN   = req.getParameter("MAINTEN");
				
				
				rs =  stmt.executeQuery(" SELECT SUB_TYPE_CODE, "+
					" PAYEE_CODE, "+
					" PAYEE_NAME "+
					" FROM "+m_schema_name+".AF_CR_PRO_SUB_CHAR_PAYEE_REF "+
					" WHERE UPPER(PAYEE_CODE)    = UPPER('"+m_PAYEE+"') "+
					" AND   UPPER(SUB_TYPE_CODE) = UPPER('"+m_MAINTEN+"') ");
				
				boolean flag = rs.next();
				//out.println("flag="+flag);
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<TID>"        + rs.getString(1)  + "</TID>");
					out.println("<TDE>"        + rs.getString(2)  + "</TDE>");
					out.println("</ITEM>");
				}
				
				//out.println("</DATA>");
				out.println("</Root>");
				
			}	
			else if(m_chksql.trim().equals("getOffBranch")){
				
				String m_uid      = req.getParameter("user");
				
				
				rs = stmt.executeQuery(" SELECT LOCATION_CODE,"+m_schema_name+".AF_CO_GET_LOCATION_DESC(LOCATION_CODE) "+
					" FROM   "+m_schema_name+".CO_CO_MAS_USER  "+
					" WHERE  USER_ID='"+m_uid+"' ");
				
				boolean flag = rs.next();
				//out.println("flag="+flag);
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<TID>"        + rs.getString(1)  + "</TID>");
					out.println("<TDE>"        + rs.getString(2)  + "</TDE>");
					out.println("</ITEM>");
				}
				
				//out.println("</DATA>");
				out.println("</Root>");
				
			}
			else if(m_chksql.trim().equals("getBranch")){
				
				String m_branch      = req.getParameter("BRANCH");
				
				
				rs = stmt.executeQuery(" SELECT LOCATION_CODE, LOCATION_DESC "+
					" FROM   "+m_schema_name+".AF_CO_MAS_LOCATION  "+
					" WHERE  ACTIVE_STATUS='Y'  AND "+
					"        LOCATION_CODE=UPPER('"+m_branch+"')  "+
					" ORDER  BY LOCATION_DESC  ");
				
				boolean flag = rs.next();
				//out.println("flag="+flag);
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<LC>"        + rs.getString(1)  + "</LC>");
					out.println("<LD>"        + rs.getString(2)  + "</LD>");
					out.println("</ITEM>");
				}
				//out.println("</DATA>");
				out.println("</Root>");
				
			}		
			else if(m_chksql.trim().equals("get_vendor")){
				
				String m_pno      = req.getParameter("vendor");
				
				rs = stmt.executeQuery(" SELECT A.VENDOR_CODE, A.NAME, A.CATEGORY,A.TYPE "+
					" FROM   "+m_schema_name+".AF_CO_MAS_VENDORS A "+
					" WHERE  ACTIVE_STATUS='Y' AND A.VENDOR_CODE=UPPER('"+m_pno+"') ");
				
				boolean flag = rs.next();
				//out.println("flag="+flag);
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<VEC>"        + rs.getString(1)  + "</VEC>");
					out.println("<VEN>"        + rs.getString(2)  + "</VEN>");
					out.println("</ITEM>");
				}
				
				//out.println("</DATA>");
				out.println("</Root>");
				
			}		
			
			else if(m_chksql.trim().equals("get_sub_model")){
				
				String m_pno      = req.getParameter("sub_model");
				
				rs = stmt.executeQuery(" SELECT A.MODEL_CODE, A.DESCRIPTION,A.TAX_RATE "+
					" FROM   "+m_schema_name+".AF_CO_MAS_MODEL A "+
					" WHERE  ACTIVE_STATUS='Y' AND A.MODEL_CODE=UPPER('"+m_pno+"') ");
				
				boolean flag = rs.next();
				//out.println("flag="+flag);
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<MDC>"        + rs.getString(1)  + "</MDC>");
					out.println("<MDD>"        + rs.getString(2)  + "</MDD>");
					out.println("</ITEM>");
				}
				
				//out.println("</DATA>");
				out.println("</Root>");
				
			}	
			else if(m_chksql.trim().equals("get_item")){
				
				String m_code   = req.getParameter("code");
				
				rs = stmt.executeQuery(" SELECT NVL(VAT,'0'), NVL(VAT_APP,'0') "+
					" FROM   "+m_schema_name+".AF_CO_MAS_ITEM_CATEGORY "+
					" WHERE  ITEM_CAT_CODE='"+m_code+"'  AND "+
					"        ACTIVE_STATUS='Y' ");
				
				boolean flag = rs.next();
				//out.println("flag="+flag);
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<MDC>"        + rs.getString(1)  + "</MDC>");
					out.println("<MDD>"        + rs.getString(2)  + "</MDD>");
					out.println("</ITEM>");
				}
				
				//out.println("</DATA>");
				out.println("</Root>");
				
			}	
			else if(m_chksql.trim().equals("get_sub_item_vat")){
				
				String m_code   = req.getParameter("code");
				
				rs = stmt.executeQuery(" SELECT NVL(VAT,'0'), NVL(VAT_APP,'0') "+
					" FROM   "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY "+
					" WHERE  ITEM_SUB_CAT='"+m_code+"'  AND "+
					"        ACTIVE_STATUS='Y' "+
					" ORDER  BY DEFAULT_VALUE");
				
				boolean flag = rs.next();
				//out.println("flag="+flag);
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<MDC>"        + rs.getString(1)  + "</MDC>");
					out.println("<MDD>"        + rs.getString(2)  + "</MDD>");
					out.println("</ITEM>");
				}
				
				//out.println("</DATA>");
				out.println("</Root>");
				
			}	
			else if(m_chksql.trim().equals("get_make")){
				
				String m_code   = req.getParameter("code");
				
				rs = stmt.executeQuery(" SELECT MAKE_CODE, MAKE_DESC "+
					" FROM   "+m_schema_name+".AF_CO_MAS_MAKE "+
					" WHERE  MAKE_CODE IN (SELECT MAKE_CODE "+
					"                      FROM   "+m_schema_name+".AF_CO_MAS_MODEL "+
					"                      WHERE  ITEM_SUB_CAT='"+m_code+"' ) AND "+
					"        ACTIVE_STATUS='Y' "+
					" ORDER  BY DEFAULT_VALUE DESC");
				
				boolean flag = rs.next();
				//out.println("flag="+flag);
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<MDC>"        + rs.getString(1)  + "</MDC>");
					out.println("<MDD>"        + rs.getString(2)  + "</MDD>");
					out.println("</ITEM>");
				}
				
				//out.println("</DATA>");
				out.println("</Root>");
				
			}	
			else if(m_chksql.trim().equals("get_sub_item")){
				
				String m_code   = req.getParameter("code");
				// commented by udara 25-10-2018
				/*
				
				rs = stmt.executeQuery(" SELECT ITEM_SUB_CAT, DESCRIPTION "+
					" FROM   "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY "+
					" WHERE  ITEM_CAT_CODE='"+m_code+"'  AND "+
					"        ACTIVE_STATUS='Y' "+
					" ORDER BY DEFAULT_VALUE DESC");
				*/
				
				rs = stmt.executeQuery(" SELECT ITEM_SUB_CAT \"Sub Category\",DESCRIPTION \"Sub Category Name\"  "+
			        " FROM ("+
					    " SELECT ITEM_SUB_CAT ITEM_SUB_CAT, INITCAP(DESCRIPTION) DESCRIPTION "+
                           " FROM   "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY "+
													 " WHERE  ACTIVE_STATUS ='Y' AND "+
													 "        ITEM_CAT_CODE = '"+m_code+"'  "+
														" ) "+
														" ORDER BY	DESCRIPTION "); 
				
				boolean flag = rs.next();
				//out.println("flag="+flag);
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<MDC>"        + rs.getString(1)  + "</MDC>");
					out.println("<MDD>"        + rs.getString(2)  + "</MDD>");
					out.println("</ITEM>");
				}
				
				//out.println("</DATA>");
				out.println("</Root>");
				
			}	
			else if(m_chksql.trim().equals("get_sub_cat")){
				
				String m_pno      = req.getParameter("cat_code");
				
				rs = stmt.executeQuery(" SELECT A.MODEL_CODE, A.DESCRIPTION,A.TAX_RATE "+
					" FROM   "+m_schema_name+".AF_CO_MAS_MODEL A "+
					" WHERE  ACTIVE_STATUS='Y' AND A.MODEL_CODE=UPPER('"+m_pno+"') ");
				
				boolean flag = rs.next();
				//out.println("flag="+flag);
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<MDC>"        + rs.getString(1)  + "</MDC>");
					out.println("<MDD>"        + rs.getString(2)  + "</MDD>");
					out.println("</ITEM>");
				}
				
				//out.println("</DATA>");
				out.println("</Root>");
				
			}	
			else if(m_chksql.trim().equals("get_trn_sub")){
				
				String m_pno      = req.getParameter("trn_sub");
				
				
				rs = stmt.executeQuery(" SELECT A.TRN_SUB_TYPE, A.TRN_CODE, A.DESCRIPTION "+
					" FROM   "+m_schema_name+".AF_CO_MAS_TRANSACTION_SUB_TYPE A "+
					" WHERE  ACTIVE_STATUS='Y' AND A.TRN_SUB_TYPE='"+m_pno+"' ");
				
				boolean flag = rs.next();
				//out.println("flag="+flag);
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<TST>"        + rs.getString(1)  + "</TST>");
					out.println("<TRC>"        + rs.getString(2)  + "</TRC>");
					out.println("<TSD>"        + rs.getString(2)  + "</TSD>");
					out.println("</ITEM>");
				}
				
				//out.println("</DATA>");
				out.println("</Root>");
				
			}
			
			else if(m_chksql.trim().equals("getOfficer")){
				String User_name=req.getParameter("user_name");
				
				rs = stmt.executeQuery(" SELECT A.USER_ID "+
					" FROM   "+m_schema_name+".CO_CO_MAS_USER A "+
					" WHERE  A.USER_ID='"+User_name+"' AND ACTIVE_STATUS='Y'");
				boolean flag = rs.next();
				//out.println("flag="+flag);
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<TST>"        + rs.getString(1)  + "</TST>");
					out.println("</ITEM>");
				}
				
				//out.println("</DATA>");
				out.println("</Root>");
			}
			else if(m_chksql.trim().equals("getTeam")){
				String User_name=req.getParameter("user_name");
				
				rs = stmt.executeQuery(" SELECT A.TEAM_ID, A.TEAM_HEAD "+
					" FROM   "+m_schema_name+".AF_CO_MAS_TEAMS A "+
					" WHERE  TEAM_ID =(SELECT A.TEAM_ID "+
					"                  FROM   "+m_schema_name+".AF_CO_MAS_TEAM_MEMBERS A "+
					"                  WHERE  USER_ID ='"+User_name+"' ) AND "+
					"        A.ACTIVE_STATUS='Y'");
				boolean flag = rs.next();
				//out.println("flag="+flag);
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<TST>"        + rs.getString(1)  + "</TST>");
					out.println("<TST>"        + rs.getString(2)  + "</TST>");
					out.println("</ITEM>");
				}
				
				//out.println("</DATA>");
				out.println("</Root>");   
				
			}
			else if(m_chksql.trim().equals("get_vat_net_gros")){
				
				String m_net        = req.getParameter("net_amt");
				String m_vat        = req.getParameter("vat_prt");
				String m_vat_app    = req.getParameter("vat_app");
				String m_gro        = req.getParameter("gro_amt");
				String m_typ        = req.getParameter("type");
				String m_term_amount= req.getParameter("term_amount");
				String m_term_type  = req.getParameter("term_type");
				String m_vat_amt    = req.getParameter("vat_amt");	
				String m_arr_amt    = req.getParameter("m_arr_amt");	
				
				if(m_term_amount==null){
					m_term_amount = "0";
				}else{
					if(m_term_amount.equals("")){
						m_term_amount = "0";
					}	
				}
				
				if(m_arr_amt==null){
					m_arr_amt = "0";
				}else{
					if(m_arr_amt.equals("")){
						m_arr_amt = "0";
					}	
				}
				
				if(m_term_type.equals("ENHA_DOWN")){
					if (m_typ.equals("NET")){
						
						rs = stmt.executeQuery(" SELECT '"+m_net+"',round(('"+m_net+"'-('"+m_term_amount+"'-'"+m_arr_amt+"'))*'"+m_vat+"'/100), "+
							"        '"+m_net+"'+round(('"+m_net+"'-('"+m_term_amount+"'-'"+m_arr_amt+"'))*'"+m_vat+"'/100), "+
							"        ROUND('"+m_net+"'+('"+m_net+"'-('"+m_term_amount+"'-'"+m_arr_amt+"'))*('"+m_vat+"'/100)*(("+m_vat+"-"+m_vat_app+")/100)) "+//FIN_AMOUNT	
							" FROM   DUAL ");  
					}else{	
						
						rs = stmt.executeQuery(" SELECT '"+m_gro+"'-round(('"+m_gro+"'-('"+m_term_amount+"'-'"+m_arr_amt+"'))*('"+m_vat+"'/(100+'"+m_vat+"'))), "+
							"        round(('"+m_gro+"'-('"+m_term_amount+"'-'"+m_arr_amt+"'))*'"+m_vat+"'/(100+'"+m_vat+"')),'"+m_gro+"', "+
							"        '"+m_gro+"'-round(('"+m_gro+"'-('"+m_term_amount+"'-'"+m_arr_amt+"'))*('"+m_vat+"'/(100+'"+m_vat+"')))+ROUND((('"+m_gro+"'-round(('"+m_gro+"'-('"+m_term_amount+"'-'"+m_arr_amt+"'))*('"+m_vat+"'/(100+'"+m_vat+"'))))-('"+m_term_amount+"'-'"+m_arr_amt+"'))*(('"+m_vat+"'-'"+m_vat_app+"')/100)) "+//FIN_AMOUNT	
							" FROM   DUAL ");
					}
				}else{	
					if (m_typ.equals("NET")){
						rs = stmt.executeQuery(" SELECT '"+m_net+"',round('"+m_net+"'*'"+m_vat+"'/100), "+
							"        '"+m_net+"'+round('"+m_net+"'*'"+m_vat+"'/100), "+
							"        ROUND('"+m_net+"'*(1+("+m_vat+"-"+m_vat_app+")/100)) "+//FIN_AMOUNT	
							" FROM   DUAL ");  
					}else{	
						rs = stmt.executeQuery(" SELECT '"+m_gro+"'-round('"+m_gro+"'*('"+m_vat+"'/(100+'"+m_vat+"'))), "+
							"        round('"+m_gro+"'*'"+m_vat+"'/(100+'"+m_vat+"')),'"+m_gro+"', "+
							"        ROUND(('"+m_gro+"'-round('"+m_gro+"'*('"+m_vat+"'/(100+'"+m_vat+"'))))*(1+('"+m_vat+"'-'"+m_vat_app+"')/100)) "+//FIN_AMOUNT	
							" FROM   DUAL ");
					}
				}	
				boolean flag = rs.next();
				//out.println("flag="+flag);
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<NET>"        + nf.format(rs.getDouble(1))  + "</NET>");
					out.println("<VAT>"        + nf.format(rs.getDouble(2))  + "</VAT>");
					out.println("<GRO>"        + nf.format(rs.getDouble(3))  + "</GRO>");
					out.println("<AFN>"        + nf.format(rs.getDouble(4))  + "</AFN>");
					out.println("</ITEM>");
				}
				
				//out.println("</DATA>");
				out.println("</Root>");
				
			}	else if(m_chksql.trim().equals("get_rental_amt")){ //add by waruna 2012-04-20
				
				String m_item_sub_cat        = req.getParameter("ITEM_SUB_CAT");
				String m_period_of_financing  = req.getParameter("PERIOD_OF_FINANCING");
				String m_amount_gross         = req.getParameter("AMOUNT_GROSS");	
				
				String m_rental_amount = "0";
				
				rs = stmt.executeQuery(" SELECT DECODE('"+m_period_of_financing+"','6',PERIOD_06,'12',PERIOD_12,'18',PERIOD_18, "+
					" '24',PERIOD_24,'30',PERIOD_30,'36',PERIOD_36, "+
					" '42',PERIOD_42,'48',PERIOD_48,'54',PERIOD_54, "+
					" '60',PERIOD_60) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_SHEDULE_DETAIL  A ,"+m_schema_name+".AF_CO_PRO_APP_SHEDULE B  "+
					" WHERE A.SHEDULE_REF =B.SHEDULE_REF AND "+
					" ITEM_SUB_CAT='"+m_item_sub_cat+"' AND CAPITAL='"+m_amount_gross+"'  "+
					"");
				
				boolean flag = rs.next();
				//out.println("flag="+flag);
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					
					m_rental_amount = rs.getString(1); // added by udara 09-09-2016
					
					// commented by udara 09-09-2016
					/*
					out.println("<ITEM>");
					out.println("<TID>"        + rs.getString(1)  + "</TID>");
					out.println("</ITEM>");
					*/
				}
				
				// added by udara 09-09-2016
				out.println("<ITEM>");
				out.println("<TID>"        + m_rental_amount  + "</TID>");
				out.println("</ITEM>");
				// end by udara 09-09-2016
				
				//out.println("</DATA>");
				out.println("</Root>");
				
			}
			
			// added by udara 26-10-2018
			else if(m_chksql.trim().equals("get_validate_Loan_period")){
				
				String m_application_no       = req.getParameter("application_no");
				String m_loan_period		  = req.getParameter("loan_period");
				String m_status               = "false";
				
				rs = stmt.executeQuery(" "+
					" SELECT "+m_schema_name+".AF_CO_VALIDATE_LOAN_PERIOD('"+m_application_no+"','"+m_loan_period+"') "+
					" FROM DUAL "+
					" ");
				
				if(rs.next()){
					m_status = rs.getString(1);
				}
				
				out.println("<Root>");
				out.println("<ITEM>");
				out.println("<TID> "+ m_status+" </TID>");
				out.println("</ITEM>");
				out.println("</Root>");
				
				
			}
			// end by udara 26-10-2018
			
			
			
			
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
