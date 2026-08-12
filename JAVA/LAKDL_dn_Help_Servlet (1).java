import java.io.*;
import java.util.*;
import java.lang.*;


public class HNBAM_dn_help_select  {  
	
  //Enter Filds Here
	
	public Object Ret_Object = new Object();
	public String ret_str;
	
	HNBAM_sn_methods m_HNBAM_sn_methods = new HNBAM_sn_methods();
	public	String m_schema_name = m_HNBAM_sn_methods.schema_name;
	
	
	//Put the Sqlnames Bigining with m_*
	//public String m_clchelp_Header = " ";
	public String m_dealhelpsql = " "; 
	public String m_txn_inqsql = " ";
	public String m_gdatasql = " "; 
	public String m_gdata2sql = " "; 
	public String m_gdata5sql = " "; 
	public String m_gdata6sql = " "; 
	
	public String m_gdata7sql = " ";
  public String m_gdata8sql = " "; 
  public String m_gdata9sql = " "; 
	public String m_gdata10sql = " "; 
	public String m_gdata11sql = " "; 
	public String m_gdata12sql = " "; 
	public String m_gdata13sql = " "; 
	public String m_gdata14sql = " "; 
	
	public String m_bgdata7sql = " ";
  public String m_bgdata8sql = " "; 
  public String m_bgdata9sql = " "; 
	public String m_bgdata10sql = " "; 
	public String m_bgdata11sql = " "; 
	public String m_bgdata12sql = " "; 
	public String m_bgdata13sql = " "; 
	public String m_bgdata14sql = " "; 
	
	public String m_cgdatasql = " "; 
	public String m_portfoliosql = " "; 
	public String m_cgdata2sql = " "; 
		public String m_cgdata2Ssql = " "; 

	public String m_cgdata1sql = " ";
	public String m_cgdata2_p_sql = " "; 
	public String m_cgdata2_p_r_sql = " "; 

	public String m_cgdata5sql = " "; 
	public String m_custodial_helpsql = " "; 
	public String m_cgdata6sql=" ";

	public String m_currgdatasql = " "; 
	public String m_printersql = " ";
  //--Added By Gayan On 09-07-2001--
	public String m_rfullrfhelp = " ";
  public String m_rfullrrhelp = " ";
	public String m_rfullrphelp = " ";
// Put the Heading with The " _Header " Clause appended to the Sql Name
	
	//-----------DINESH---------------------------------
	//public String m_clchelp_Header = " Client Name";
	
	
	public String m_printersql_Header = "Printer Help "; 
	public String m_dealhelpsql_Header = "Account No Help ";
	public String m_je_inqsql_Header = "Journal No Inquiry Help ";
	public String m_je_moddelsql_Header = "Journal No Help ";
	public String m_banksql_Header = "Bank Code Help ";
	public String m_chequesql_Header = "Cheque No Help ";
	public String m_bankaccsql_Header = "Account No Help ";
	public String m_trannewsql_Header = "Transaction Code Help ";
	public String m_tranmdsql_Header = "Transaction Code Help ";
	
	//upul
	public String voucher_numbersql_Header = "Voucher Number Help ";
	public String voucher_viewsql_Header = "Voucher Details";
	public String m_accountssql_Header = "Accounts Details";
	public String m_accsql_Header = "Accounts Details";
  //nirmala
	public String m_txn_inqsql_Header = "Transaction Code Help ";
	
//--------------Lathika-------------------------------------
	public String m_gdatasql_Header = "General Ledger Details";
	public String m_gdata2sql_Header = "General Ledger Details";
	public String m_gdata5sql_Header = "General Ledger Details";
	public String m_gdata6sql_Header = "General Ledger Details";
	
	public String m_gdata7sql_Header = "General Ledger Details";
	public String m_gdata8sql_Header = "General Ledger Details";
	public String m_gdata9sql_Header = "General Ledger Details";
	public String m_gdata10sql_Header = "General Ledger Details";
	public String m_gdata11sql_Header = "General Ledger Details";
	public String m_gdata12sql_Header = "General Ledger Details";
	public String m_gdata13sql_Header = "General Ledger Details";
	public String m_gdata14sql_Header = "General Ledger Details";
	
	public String m_bgdata7sql_Header = "General Ledger Details";
	public String m_bgdata8sql_Header = "General Ledger Details";
	public String m_bgdata9sql_Header = "General Ledger Details";
	public String m_bgdata10sql_Header = "General Ledger Details";
	public String m_bgdata11sql_Header = "General Ledger Details";
	public String m_bgdata12sql_Header = "General Ledger Details";
	public String m_bgdata13sql_Header = "General Ledger Details";
	public String m_bgdata14sql_Header = "General Ledger Details";
	
	public String m_cgdatasql_Header = "Client Ledger Details";
	public String m_portfoliosql_Header = "Client Details";
	public String m_cgdata2sql_Header = "Client Ledger Details";
		public String m_cgdata2Ssql_Header = "Client Ledger Details";

	public String m_cgdata1sql_Header = "Portfolio Ledger Details";
	public String m_cgdata2_p_sql_Header = "Payment Details";
	public String m_cgdata2_p_r_sql_Header = "Receipt Details";
	public String m_cgdata5sql_Header = "Client Ledger Details";
	public String m_cgdata6sql_Header = "Client Ledger Details";
	public String m_currgdatasql_Header = " Details";
	//hasara
	public String m_custodial_helpsql_Header = " custodial Details";
	// Method for Sql Put Sqls Inside
	
	public String m_rfullrfhelp_Header = "Repo Fresh Help";
	public String m_rfullrrhelp_Header = "Repo Rollover Help";
	public String m_rfullrphelp_Header = "Repo Periodic Interest Help";
	
	public Object getSql(Object reqObj1,Object reqObj2,Object reqObj3,Object reqObj4) {
		String Sql_Name  = (String) reqObj1;
		String Start_Val = (String) reqObj2;
		String End_Val   = (String) reqObj3;	
		String Criteria	=	(String) reqObj4;
		
		int m_val =(Integer.parseInt(End_Val));
		m_val++;
		End_Val = Integer.toString(m_val);
		
		Vector m_vector = new Vector();
		String m_substring="";
		int start_index, stop_index, cnt, i;
		start_index = 0;
		stop_index = 0;
		cnt = 0;
		int  m_length = Criteria.lastIndexOf("@");
		
		if (m_length!=0) {
			while (stop_index<m_length) {
		 	 try {
				  stop_index = Criteria.trim().indexOf("@",start_index);
					m_substring = Criteria.substring(start_index,stop_index);
					if (m_substring.length()>0) {
			 		 m_vector.addElement(m_substring);
					}
					else {
						m_vector.addElement("");
					}
					start_index = stop_index+1;
				}
				catch (Exception e) {
					stop_index=m_length;
				}
			}
		}
		else if (m_length==0) {
			m_vector.addElement("");
		}
		
		cnt = m_vector.size();
		
		for ( i = cnt ; i < 8 ; i++ ) {
			m_vector.addElement("");
		}
		
		/*----------------------------------------------------------------
		Purpose  : Display Settlement Payment   with from and to date
		
		Used in  : Current Account, 
		-----------------------------------------------------------------*/
		m_currgdatasql=" SELECT A.No, A.Entered,A.Value,A.Doc_Ref, "+
			          " A.Debit,A.Credit, "+
								" A.Description,A.Acc_Ref "+
								" FROM "+
								" (SELECT ROWNUM No, B.Entered,B.Value,B.Doc_Ref, "+
			          " B.Debit,B.Credit, "+
								" B.Description,B.Acc_Ref "+
								" FROM "+
								" (SELECT L.Entered,L.Value,L.Doc_Ref, "+
			          " L.Debit,L.Credit, "+
								" L.Description,L.Acc_Ref "+
								" FROM "+
								" (SELECT TO_CHAR(ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(TRNDATE,'DD/MON/YY') Value,	"+
								" DOCREFNO Doc_Ref, "+
								" TO_CHAR(NVL(DECODE(DRCR_STATUS,'DR',TRNAMOUNT),0),'FM999G999G999G999G999G990D00') Debit, "+
 								" TO_CHAR(NVL(DECODE(DRCR_STATUS,'CR',TRNAMOUNT),0),'FM999G999G999G999G999G990D00') Credit, "+
 								" PROC_DESC Description,CORR_ACC_NO Acc_Ref "+
		       			" FROM "+m_schema_name+".DN_ACC_LICENCEE_GEN_ACCOUNT  "+
					 			" WHERE PORTFOLIO_CODE='"+m_vector.elementAt(0)+"' AND ACC_TYPE_CODE='302' "+
								//" AND SUBSTR(DOCREFNO,0,2) NOT IN  ('OR','SP','SR') "+
								" AND DN_PORTFOLIO_CODE='"+m_vector.elementAt(1)+"' "+
					 			" GROUP BY TO_CHAR(ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(TRNDATE,'DD/MON/YY'), "+
								" DOCREFNO,NVL(DECODE(DRCR_STATUS,'DR',TRNAMOUNT),0) , "+
 								" NVL(DECODE(DRCR_STATUS,'CR',TRNAMOUNT),0), "+
 								" PROC_DESC ,CORR_ACC_NO )L "+
								"  )B) A "+
 								" WHERE A.No >= "+ Start_Val+" AND A.No<= "+End_Val+" "+
                " ORDER BY A.No ";

		
		m_portfoliosql =   " SELECT PORTFOLIO_CODE , PF_NAME " +
									    " FROM  "+m_schema_name+".DN_REF_PORTFOLIO " +
										  " WHERE (PORTFOLIO_CODE LIKE '"+m_vector.elementAt(0)+"%' OR "+
										  " UPPER(PF_NAME) LIKE UPPER('"+m_vector.elementAt(0)+"%')) AND "+
										  " ACTIVE_STATUS='Y' AND PF_TYPE='P' "+ 
											" ORDER BY  PF_NAME, PORTFOLIO_CODE ";
						
		
	 /*----------------------------------------------------------------
		Purpose  : Display Client Ledger  with from and to date
		
		Used in  : Client Account, 
		-----------------------------------------------------------------*/
			m_cgdatasql=" SELECT A.No, A.Entered,A.Value,A.Doc_Ref, "+
			          " A.Debit,A.Credit, "+
								" A.Description,A.Acc_Ref "+
								" FROM "+
								" (SELECT ROWNUM No, B.Entered,B.Value,B.Doc_Ref, "+
			          " B.Debit,B.Credit, "+
								" B.Description,B.Acc_Ref "+
								" FROM "+
								" (SELECT L.Entered,L.Value,L.Doc_Ref, "+
			          " L.Debit,L.Credit, "+
								" L.Description,L.Acc_Ref "+
								" FROM "+
								" (SELECT TO_CHAR(ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(TRNDATE,'DD/MON/YY') Value,	"+
								" DOCREFNO Doc_Ref, "+
								" TO_CHAR(NVL(DECODE(DRCR_STATUS,'DR',TRNAMOUNT),0),'FM999G999G999G999G999G990D00') Debit, "+
 								" TO_CHAR(NVL(DECODE(DRCR_STATUS,'CR',TRNAMOUNT),0),'FM999G999G999G999G999G990D00') Credit, "+
 								" PROC_DESC Description,CORR_ACC_NO Acc_Ref "+
		       			" FROM "+m_schema_name+".DN_ACC_COUNTP_GEN_ACCOUNT "+
					 			" WHERE PORTFOLIO_CODE='"+m_vector.elementAt(0)+"' AND ACC_TYPE_CODE IN ('302','404','409','350') "+
								" AND DN_PORTFOLIO_CODE='"+m_vector.elementAt(1)+"' "+
					 			" GROUP BY TO_CHAR(ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(TRNDATE,'DD/MON/YY'), "+
								" DOCREFNO,NVL(DECODE(DRCR_STATUS,'DR',TRNAMOUNT),0) , "+
 								" NVL(DECODE(DRCR_STATUS,'CR',TRNAMOUNT),0), "+
 								" PROC_DESC ,CORR_ACC_NO  "+
								" UNION ALL"+
								" SELECT TO_CHAR(ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(TRNDATE,'DD/MON/YY') Value,	"+
								" DOCREFNO Doc_Ref, "+
								" TO_CHAR(NVL(DECODE(DRCR_STATUS,'DR',TRNAMOUNT),0),'FM999G999G999G999G999G990D00') Debit, "+
 								" TO_CHAR(NVL(DECODE(DRCR_STATUS,'CR',TRNAMOUNT),0),'FM999G999G999G999G999G990D00') Credit, "+
 								" PROC_DESC Description,CORR_ACC_NO Acc_Ref "+
		       			" FROM "+m_schema_name+".DN_ACC_LICENCEE_GEN_ACCOUNT  "+
					 			" WHERE PORTFOLIO_CODE='"+m_vector.elementAt(0)+"' AND ACC_TYPE_CODE IN ('302') "+
								" AND DN_PORTFOLIO_CODE='"+m_vector.elementAt(1)+"' "+
								" AND SUBSTR(DOCREFNO,0,2) NOT IN  ('OR','SP','SR') "+
					 			" GROUP BY TO_CHAR(ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(TRNDATE,'DD/MON/YY'), "+
								" DOCREFNO,NVL(DECODE(DRCR_STATUS,'DR',TRNAMOUNT),0) , "+
 								" NVL(DECODE(DRCR_STATUS,'CR',TRNAMOUNT),0), "+
 								" PROC_DESC ,CORR_ACC_NO  )L "+
								"WHERE (L.Doc_Ref NOT IN (SELECT "+
                                 " A.REC_NO "+
                                 "FROM "+m_schema_name+".DN_TRN_BO_SETTL_RECEIPT A "+
                                 "WHERE A.REC_NO=L.Doc_Ref "+
                               	 "AND SUBSTR(L.Doc_Ref,0,2)='SR' "+
                                 "AND substr(A.SUS_REF_NO,0,2) IN ('RR','RF','RS') "+
                                 "AND A.SUSPENSE_ENTRY_TYPE ='D') "+
                                 "AND "+
                              	 "L.Doc_Ref NOT IN (SELECT "+
                                 "A.PAYMENT_NO "+
                                 "FROM "+m_schema_name+".DN_TRN_BO_SETTL_PAYMENT A "+
                                 "WHERE A.PAYMENT_NO=L.Doc_Ref "+
                                 "AND SUBSTR(L.Doc_Ref,0,2)='SP' "+
                                 "AND substr(A.SUS_REF_NO,0,2) IN ('RR','RF','RS') "+
                                 "AND A.ENTRY_TYPE ='D')) "+
								"  )B) A "+
 								" WHERE A.No >= "+ Start_Val+" AND A.No<= "+End_Val+" "+
                " ORDER BY A.No ";


		m_cgdata1sql=" SELECT A.No, A.Entered,A.Value,A.Doc_Ref,A.Debit,A.Credit,A.Description,A.Acc_Ref "+
								" FROM "+
								" (SELECT ROWNUM No, B.Entered,B.Value,B.Doc_Ref,B.Debit,B.Credit,B.Description,B.Acc_Ref "+
								" FROM "+
								" (SELECT L.Entered,L.Value,L.Doc_Ref,L.Debit,L.Credit,L.Description,L.Acc_Ref "+
								" FROM "+
							  " (SELECT TO_CHAR(ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(TRNDATE,'DD/MON/YY') Value,	"+
								" DOCREFNO Doc_Ref,NVL(DECODE(DRCR_STATUS,'DR',TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(DRCR_STATUS,'CR',TRNAMOUNT),0) Credit, "+
 								" PROC_DESC Description,CORR_ACC_NO Acc_Ref "+
		       			" FROM "+m_schema_name+".DN_ACC_LICENCEE_GEN_ACCOUNT "+
					 			" WHERE PORTFOLIO_CODE='"+m_vector.elementAt(0)+"' AND ACC_TYPE_CODE IN ('301') AND "+
					 			" TO_DATE(TO_CHAR(TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')>=TO_DATE('"+m_vector.elementAt(1)+"','DD/MM/YYYY') AND "+
					     	" TO_DATE(TO_CHAR(TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')<=TO_DATE('"+m_vector.elementAt(2)+"','DD/MM/YYYY') "+
								" AND DN_PORTFOLIO_CODE='"+m_vector.elementAt(3)+"' "+
								//" AND SUBSTR(DOCREFNO,0,2) NOT IN  ('OR','SP','SR') "+
		 						" GROUP BY TO_CHAR(ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(TRNDATE,'DD/MON/YY'), "+
								" DOCREFNO,NVL(DECODE(DRCR_STATUS,'DR',TRNAMOUNT),0) , "+
 								" NVL(DECODE(DRCR_STATUS,'CR',TRNAMOUNT),0), "+
 								" PROC_DESC ,CORR_ACC_NO )L "+
								" )B) A "+
 								" WHERE A.No >= "+ Start_Val+" AND A.No<= "+End_Val+" "+
                " ORDER BY A.No ";	

		m_cgdata2sql=" SELECT A.No, A.Entered,A.Value,A.Doc_Ref,A.Debit,A.Credit,A.Description,A.Acc_Ref "+
								" FROM "+
								" (SELECT ROWNUM No, B.Entered,B.Value,B.Doc_Ref,B.Debit,B.Credit,B.Description,B.Acc_Ref "+
								" FROM "+
								" (SELECT L.Entered,L.Value,L.Doc_Ref,L.Debit,L.Credit,L.Description,L.Acc_Ref "+
								" FROM "+
							  " (SELECT TO_CHAR(ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(TRNDATE,'DD/MON/YY') Value,	"+
								" DOCREFNO Doc_Ref,NVL(DECODE(DRCR_STATUS,'DR',TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(DRCR_STATUS,'CR',TRNAMOUNT),0) Credit, "+
 								" PROC_DESC Description,CORR_ACC_NO Acc_Ref "+
		       			" FROM "+m_schema_name+".DN_ACC_LICENCEE_GEN_ACCOUNT "+
					 			" WHERE PORTFOLIO_CODE='"+m_vector.elementAt(0)+"' AND ACC_TYPE_CODE IN ('302') AND "+
					 			" TO_DATE(TO_CHAR(TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')>=TO_DATE('"+m_vector.elementAt(1)+"','DD/MM/YYYY') AND "+
					     	" TO_DATE(TO_CHAR(TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')<=TO_DATE('"+m_vector.elementAt(2)+"','DD/MM/YYYY') "+
								" AND DN_PORTFOLIO_CODE='"+m_vector.elementAt(3)+"' "+
								//" AND SUBSTR(DOCREFNO,0,2) NOT IN  ('OR','SP','SR') "+
		 						" GROUP BY TO_CHAR(ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(TRNDATE,'DD/MON/YY'), "+
								" DOCREFNO,NVL(DECODE(DRCR_STATUS,'DR',TRNAMOUNT),0) , "+
 								" NVL(DECODE(DRCR_STATUS,'CR',TRNAMOUNT),0), "+
 								" PROC_DESC ,CORR_ACC_NO )L "+
								" )B) A "+
 								" WHERE A.No >= "+ Start_Val+" AND A.No<= "+End_Val+" "+
                " ORDER BY A.No ";	
								
				m_cgdata2Ssql=" SELECT A.No, A.Entered,A.Value,A.Doc_Ref,A.Debit,A.Credit,A.Description,A.Acc_Ref "+
								" FROM "+
								" (SELECT ROWNUM No, B.Entered,B.Value,B.Doc_Ref,B.Debit,B.Credit,B.Description,B.Acc_Ref "+
								" FROM "+
								" (SELECT L.Entered,L.Value,L.Doc_Ref,L.Debit,L.Credit,L.Description,L.Acc_Ref "+
								" FROM "+
							  " (SELECT TO_CHAR(ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(TRNDATE,'DD/MON/YY') Value,	"+
								" DOCREFNO Doc_Ref,NVL(DECODE(DRCR_STATUS,'DR',TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(DRCR_STATUS,'CR',TRNAMOUNT),0) Credit, "+
 								" PROC_DESC Description,CORR_ACC_NO Acc_Ref "+
		       			" FROM "+m_schema_name+".DN_ACC_LICENCEE_GEN_ACCOUNT "+
					 			" WHERE PORTFOLIO_CODE='"+m_vector.elementAt(0)+"' AND ACC_TYPE_CODE IN ('302') AND "+
					 			" TO_DATE(TO_CHAR(TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')>=TO_DATE('"+m_vector.elementAt(1)+"','DD/MM/YYYY') AND "+
					     	" TO_DATE(TO_CHAR(TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')<=TO_DATE('"+m_vector.elementAt(2)+"','DD/MM/YYYY') "+
								" AND DN_PORTFOLIO_CODE='"+m_vector.elementAt(3)+"' "+
								"AND "+m_schema_name+".DN_EXCLUDE_BLSEC(DOCREFNO)='N' "+
								//" AND SUBSTR(DOCREFNO,0,2) NOT IN  ('OR','SP','SR') "+
		 						" GROUP BY TO_CHAR(ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(TRNDATE,'DD/MON/YY'), "+
								" DOCREFNO,NVL(DECODE(DRCR_STATUS,'DR',TRNAMOUNT),0) , "+
 								" NVL(DECODE(DRCR_STATUS,'CR',TRNAMOUNT),0), "+
 								" PROC_DESC ,CORR_ACC_NO )L "+
								" )B) A "+
 								" WHERE A.No >= "+ Start_Val+" AND A.No<= "+End_Val+" "+
                " ORDER BY A.No ";	
						
								
		m_cgdata2_p_sql=" SELECT A.No, A.Entered,A.Value,A.Doc_Ref,A.Debit,A.Credit,A.Description,A.Acc_Ref "+
								" FROM "+
								" (SELECT ROWNUM No, B.Entered,B.Value,B.Doc_Ref,B.Debit,B.Credit,B.Description,B.Acc_Ref "+
								" FROM "+
								" (SELECT L.Entered,L.Value,L.Doc_Ref,L.Debit,L.Credit,L.Description,L.Acc_Ref "+
								" FROM "+
							  " (SELECT TO_CHAR(ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(TRNDATE,'DD/MON/YY') Value,	"+
								" DOCREFNO Doc_Ref,NVL(DECODE(DRCR_STATUS,'DR',TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(DRCR_STATUS,'CR',TRNAMOUNT),0) Credit, "+
 								" PROC_DESC Description,CORR_ACC_NO Acc_Ref "+
		       			" FROM "+m_schema_name+".DN_ACC_LICENCEE_GEN_ACCOUNT "+
					 			" WHERE PORTFOLIO_CODE='"+m_vector.elementAt(0)+"' AND ACC_TYPE_CODE IN ('404') AND "+
					 			" TO_DATE(TO_CHAR(TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')>=TO_DATE('"+m_vector.elementAt(1)+"','DD/MM/YYYY') AND "+
					     	" TO_DATE(TO_CHAR(TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')<=TO_DATE('"+m_vector.elementAt(2)+"','DD/MM/YYYY') "+
								" AND DN_PORTFOLIO_CODE='"+m_vector.elementAt(3)+"' "+
								//" AND SUBSTR(DOCREFNO,0,2) NOT IN  ('OR','SP','SR') "+
		 						" GROUP BY TO_CHAR(ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(TRNDATE,'DD/MON/YY'), "+
								" DOCREFNO,NVL(DECODE(DRCR_STATUS,'DR',TRNAMOUNT),0) , "+
 								" NVL(DECODE(DRCR_STATUS,'CR',TRNAMOUNT),0), "+
 								" PROC_DESC ,CORR_ACC_NO )L "+
								" )B) A "+
 								" WHERE A.No >= "+ Start_Val+" AND A.No<= "+End_Val+" "+
                " ORDER BY A.No ";				
			
			
		m_cgdata2_p_r_sql=" SELECT A.No, A.Entered,A.Value,A.Doc_Ref,A.Debit,A.Credit,A.Description,A.Acc_Ref "+
								" FROM "+
								" (SELECT ROWNUM No, B.Entered,B.Value,B.Doc_Ref,B.Debit,B.Credit,B.Description,B.Acc_Ref "+
								" FROM "+
								" (SELECT L.Entered,L.Value,L.Doc_Ref,L.Debit,L.Credit,L.Description,L.Acc_Ref "+
								" FROM "+
							  " (SELECT TO_CHAR(ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(TRNDATE,'DD/MON/YY') Value,	"+
								" DOCREFNO Doc_Ref,NVL(DECODE(DRCR_STATUS,'DR',TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(DRCR_STATUS,'CR',TRNAMOUNT),0) Credit, "+
 								" PROC_DESC Description,CORR_ACC_NO Acc_Ref "+
		       			" FROM "+m_schema_name+".DN_ACC_LICENCEE_GEN_ACCOUNT "+
					 			" WHERE PORTFOLIO_CODE='"+m_vector.elementAt(0)+"' AND ACC_TYPE_CODE IN ('409') AND "+
					 			" TO_DATE(TO_CHAR(TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')>=TO_DATE('"+m_vector.elementAt(1)+"','DD/MM/YYYY') AND "+
					     	" TO_DATE(TO_CHAR(TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')<=TO_DATE('"+m_vector.elementAt(2)+"','DD/MM/YYYY') "+
								" AND DN_PORTFOLIO_CODE='"+m_vector.elementAt(3)+"' "+
								//" AND SUBSTR(DOCREFNO,0,2) NOT IN  ('OR','SP','SR') "+
		 						" GROUP BY TO_CHAR(ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(TRNDATE,'DD/MON/YY'), "+
								" DOCREFNO,NVL(DECODE(DRCR_STATUS,'DR',TRNAMOUNT),0) , "+
 								" NVL(DECODE(DRCR_STATUS,'CR',TRNAMOUNT),0), "+
 								" PROC_DESC ,CORR_ACC_NO )L "+
								" )B) A "+
 								" WHERE A.No >= "+ Start_Val+" AND A.No<= "+End_Val+" "+
                " ORDER BY A.No ";				
			
						
			
			
			m_cgdata5sql=" SELECT A.No, A.Entered,A.Value,A.Doc_Ref,A.Debit,A.Credit,A.Description,A.Acc_Ref "+
								" FROM "+
								" (SELECT ROWNUM No, B.Entered,B.Value,B.Doc_Ref,B.Debit,B.Credit,B.Description,B.Acc_Ref "+
								" FROM "+
								" (SELECT L.Entered,L.Value,L.Doc_Ref,L.Debit,L.Credit,L.Description,L.Acc_Ref "+
								" FROM "+
							  " (SELECT TO_CHAR(ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(TRNDATE,'DD/MON/YY') Value,	"+
								" DOCREFNO Doc_Ref,NVL(DECODE(DRCR_STATUS,'DR',TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(DRCR_STATUS,'CR',TRNAMOUNT),0) Credit, "+
 								" PROC_DESC Description,CORR_ACC_NO Acc_Ref "+
		       			" FROM "+m_schema_name+".DN_ACC_COUNTP_GEN_ACCOUNT "+
					 			" WHERE PORTFOLIO_CODE='"+m_vector.elementAt(0)+"' AND ACC_TYPE_CODE IN ('302','404','409','350') AND "+
					 	   	" TO_DATE(TO_CHAR(TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')<=TO_DATE('"+m_vector.elementAt(1)+"','DD/MM/YYYY') "+
								" AND DN_PORTFOLIO_CODE='"+m_vector.elementAt(2)+"' "+
		 						" GROUP BY TO_CHAR(ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(TRNDATE,'DD/MON/YY'), "+
								" DOCREFNO,NVL(DECODE(DRCR_STATUS,'DR',TRNAMOUNT),0) , "+
 								" NVL(DECODE(DRCR_STATUS,'CR',TRNAMOUNT),0), "+
 								" PROC_DESC ,CORR_ACC_NO  "+
								" UNION ALL "+
								"SELECT TO_CHAR(ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(TRNDATE,'DD/MON/YY') Value,	"+
								" DOCREFNO Doc_Ref,NVL(DECODE(DRCR_STATUS,'DR',TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(DRCR_STATUS,'CR',TRNAMOUNT),0) Credit, "+
 								" PROC_DESC Description,CORR_ACC_NO Acc_Ref "+
		       			" FROM "+m_schema_name+".DN_ACC_LICENCEE_GEN_ACCOUNT "+
					 			" WHERE PORTFOLIO_CODE='"+m_vector.elementAt(0)+"' AND ACC_TYPE_CODE IN ('302') AND "+
					 	   	" TO_DATE(TO_CHAR(TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')<=TO_DATE('"+m_vector.elementAt(1)+"','DD/MM/YYYY') "+
								" AND DN_PORTFOLIO_CODE='"+m_vector.elementAt(2)+"' "+
								" AND SUBSTR(DOCREFNO,0,2) NOT IN  ('OR','SP','SR') "+
		 						" GROUP BY TO_CHAR(ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(TRNDATE,'DD/MON/YY'), "+
								" DOCREFNO,NVL(DECODE(DRCR_STATUS,'DR',TRNAMOUNT),0) , "+
 								" NVL(DECODE(DRCR_STATUS,'CR',TRNAMOUNT),0), "+
 								" PROC_DESC ,CORR_ACC_NO )L "+
								" WHERE (L.Doc_Ref NOT IN (SELECT "+
                                 " A.REC_NO "+
                                 "FROM "+m_schema_name+".DN_TRN_BO_SETTL_RECEIPT A "+
                                 "WHERE A.REC_NO=L.Doc_Ref "+
                               	 "AND SUBSTR(L.Doc_Ref,0,2)='SR' "+
                                 "AND substr(A.SUS_REF_NO,0,2) IN ('RR','RF','RS') "+
                                 "AND A.SUSPENSE_ENTRY_TYPE ='D') "+
                                 "AND "+
                              	 "L.Doc_Ref NOT IN (SELECT "+
                                 "A.PAYMENT_NO "+
                                 "FROM "+m_schema_name+".DN_TRN_BO_SETTL_PAYMENT A "+
                                 "WHERE A.PAYMENT_NO=L.Doc_Ref "+
                                 "AND SUBSTR(L.Doc_Ref,0,2)='SP' "+
                                 "AND substr(A.SUS_REF_NO,0,2) IN ('RR','RF','RS') "+
                                 "AND A.ENTRY_TYPE ='D')) "+
								" )B) A "+
 								" WHERE A.No >= "+ Start_Val+" AND A.No<= "+End_Val+" "+
                " ORDER BY A.No ";				
								
			m_cgdata6sql=" SELECT A.No, A.Entered,A.Value,A.Doc_Ref,A.Debit,A.Credit,A.Description,A.Acc_Ref "+
								" FROM "+
								" (SELECT ROWNUM No, B.Entered,B.Value,B.Doc_Ref,B.Debit,B.Credit,B.Description,B.Acc_Ref "+
								" FROM "+
								" (SELECT L.Entered,L.Value,L.Doc_Ref,L.Debit,L.Credit,L.Description,L.Acc_Ref "+
								" FROM "+
								" (SELECT TO_CHAR(ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(TRNDATE,'DD/MON/YY') Value,	"+
								" DOCREFNO Doc_Ref,NVL(DECODE(DRCR_STATUS,'DR',TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(DRCR_STATUS,'CR',TRNAMOUNT),0) Credit, "+
 								" PROC_DESC Description,CORR_ACC_NO Acc_Ref "+
		       			" FROM "+m_schema_name+".DN_ACC_COUNTP_GEN_ACCOUNT "+
					 			" WHERE PORTFOLIO_CODE='"+m_vector.elementAt(0)+"' AND ACC_TYPE_CODE IN ('302','404','409','350') AND "+
					 	   	" TO_DATE(TO_CHAR(TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')>=TO_DATE('"+m_vector.elementAt(1)+"','DD/MM/YYYY') "+
								" AND DN_PORTFOLIO_CODE='"+m_vector.elementAt(2)+"' "+
		 						" GROUP BY TO_CHAR(ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(TRNDATE,'DD/MON/YY'), "+
								" DOCREFNO,NVL(DECODE(DRCR_STATUS,'DR',TRNAMOUNT),0) , "+
 								" NVL(DECODE(DRCR_STATUS,'CR',TRNAMOUNT),0), "+
 								" PROC_DESC ,CORR_ACC_NO  "+
								" UNION ALL "+
								" SELECT TO_CHAR(ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(TRNDATE,'DD/MON/YY') Value,	"+
								" DOCREFNO Doc_Ref,NVL(DECODE(DRCR_STATUS,'DR',TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(DRCR_STATUS,'CR',TRNAMOUNT),0) Credit, "+
 								" PROC_DESC Description,CORR_ACC_NO Acc_Ref "+
		       			" FROM "+m_schema_name+".DN_ACC_LICENCEE_GEN_ACCOUNT "+
					 			" WHERE PORTFOLIO_CODE='"+m_vector.elementAt(0)+"' AND ACC_TYPE_CODE IN ('302') AND "+
					 	   	" TO_DATE(TO_CHAR(TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')>=TO_DATE('"+m_vector.elementAt(1)+"','DD/MM/YYYY') "+
								" AND DN_PORTFOLIO_CODE='"+m_vector.elementAt(2)+"' "+
								" AND SUBSTR(DOCREFNO,0,2) NOT IN  ('OR','SP','SR') "+
		 						" GROUP BY TO_CHAR(ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(TRNDATE,'DD/MON/YY'), "+
								" DOCREFNO,NVL(DECODE(DRCR_STATUS,'DR',TRNAMOUNT),0) , "+
 								" NVL(DECODE(DRCR_STATUS,'CR',TRNAMOUNT),0), "+
 								" PROC_DESC ,CORR_ACC_NO  )L "+
									" WHERE (L.Doc_Ref NOT IN (SELECT "+
                                 " A.REC_NO "+
                                 "FROM "+m_schema_name+".DN_TRN_BO_SETTL_RECEIPT A "+
                                 "WHERE A.REC_NO=L.Doc_Ref "+
                               	 "AND SUBSTR(L.Doc_Ref,0,2)='SR' "+
                                 "AND substr(A.SUS_REF_NO,0,2) IN ('RR','RF','RS') "+
                                 "AND A.SUSPENSE_ENTRY_TYPE ='D') "+
                                 "AND "+
                              	 "L.Doc_Ref NOT IN (SELECT "+
                                 "A.PAYMENT_NO "+
                                 "FROM "+m_schema_name+".DN_TRN_BO_SETTL_PAYMENT A "+
                                 "WHERE A.PAYMENT_NO=L.Doc_Ref "+
                                 "AND SUBSTR(L.Doc_Ref,0,2)='SP' "+
                                 "AND substr(A.SUS_REF_NO,0,2) IN ('RR','RF','RS') "+
                                 "AND A.ENTRY_TYPE ='D')) "+
								" )B) A "+
 							  " WHERE A.No >= "+ Start_Val+" AND A.NO<= "+End_Val+" "+
                " ORDER BY A.No ";		

	 /*----------------------------------------------------------------
		Purpose  : Display Balance Sheet with from and to date
		
		Used in  : General Ledger, 
		-----------------------------------------------------------------*/
		m_bgdata7sql=" SELECT A.No, A.Entered,A.Value,A.Doc_Ref,A.Debit,A.Credit,A.Description,A.Acc_Ref "+
								" FROM "+
								" (SELECT ROWNUM No, TO_CHAR(B.ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(B.TRNDATE,'DD/MON/YY') Value,	"+
								" B.DOCREFNO Doc_Ref,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0) Credit, "+
 								" B.PROC_DESC Description,B.CORR_ACC_NO Acc_Ref"+
		       			" FROM "+m_schema_name+".DN_ACC_LICEN_INST_GEN_DETAIL B,"+m_schema_name+".DN_ACC_ACCOUNT_TYPE C "+
					 			" WHERE B.ACC_TYPE_CODE= '"+m_vector.elementAt(0)+"' "+
								" AND B.ACC_TYPE_CODE=C.ACC_TYPE_CODE AND C.ACC_BS_PL='BS' AND C.ACC_NOTE='AS' "+
					 			" GROUP BY TO_CHAR(B.ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(B.TRNDATE,'DD/MON/YY'), "+
								" DOCREFNO,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) , "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0), "+
 								" B.PROC_DESC ,B.CORR_ACC_NO ,ROWNUM) A "+
 								" WHERE A.No >= "+ Start_Val+" AND A.No<= "+End_Val+" "+
                " ORDER BY A.No ";
								
		m_bgdata8sql=" SELECT A.No, A.Entered,A.Value,A.Doc_Ref,A.Debit,A.Credit,A.Description,A.Acc_Ref "+
								" FROM "+
								" (SELECT ROWNUM No, TO_CHAR(B.ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(B.TRNDATE,'DD/MON/YY') Value,	"+
								" B.DOCREFNO Doc_Ref,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0) Credit, "+
 								" B.PROC_DESC Description,B.CORR_ACC_NO Acc_Ref"+
		       			" FROM "+m_schema_name+".DN_ACC_LICEN_INST_GEN_DETAIL B,"+m_schema_name+".DN_ACC_ACCOUNT_TYPE C "+
					 			" WHERE B.ACC_TYPE_CODE= '"+m_vector.elementAt(0)+"' "+
								" AND B.ACC_TYPE_CODE=C.ACC_TYPE_CODE AND C.ACC_BS_PL='BS' AND C.ACC_NOTE='LB' "+
					 			" GROUP BY TO_CHAR(B.ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(B.TRNDATE,'DD/MON/YY'), "+
								" DOCREFNO,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) , "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0), "+
 								" B.PROC_DESC ,B.CORR_ACC_NO ,ROWNUM) A "+
 								" WHERE A.No >= "+ Start_Val+" AND A.No<= "+End_Val+" "+
                " ORDER BY A.No ";			
		
		m_bgdata9sql=" SELECT A.No, A.Entered,A.Value,A.Doc_Ref,A.Debit,A.Credit,A.Description,A.Acc_Ref "+
								" FROM "+
							  " (SELECT ROWNUM No, TO_CHAR(B.ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(B.TRNDATE,'DD/MON/YY') Value,	"+
								" B.DOCREFNO Doc_Ref,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0) Credit, "+
 								" B.PROC_DESC Description,B.CORR_ACC_NO Acc_Ref"+
		       			" FROM "+m_schema_name+".DN_ACC_LICEN_INST_GEN_DETAIL B,"+m_schema_name+".DN_ACC_ACCOUNT_TYPE C  "+
					 			" WHERE B.ACC_TYPE_CODE=C.ACC_TYPE_CODE AND C.ACC_BS_PL='BS' AND C.ACC_NOTE='AS' "+
								" AND B.ACC_TYPE_CODE= '"+m_vector.elementAt(0)+"' AND "+
					 			" TO_DATE(TO_CHAR(B.TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')>=TO_DATE('"+m_vector.elementAt(1)+"','DD/MM/YYYY') AND "+
					     	" TO_DATE(TO_CHAR(B.TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')<=TO_DATE('"+m_vector.elementAt(2)+"','DD/MM/YYYY') "+
		 						" GROUP BY TO_CHAR(B.ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(B.TRNDATE,'DD/MON/YY'), "+
								" B.DOCREFNO,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) , "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0), "+
 								" B.PROC_DESC ,B.CORR_ACC_NO ,ROWNUM "+
							  " UNION ALL "+
								" SELECT ROWNUM No, TO_CHAR(B.ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(B.TRNDATE,'DD/MON/YY') Value,	"+
								" B.DOCREFNO Doc_Ref,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0) Credit, "+
 								" B.PROC_DESC Description,B.CORR_ACC_NO Acc_Ref"+
		       			" FROM "+m_schema_name+".DN_ACC_LICEN_DETAIL_HIS B,"+m_schema_name+".DN_ACC_ACCOUNT_TYPE C "+
					 			" WHERE B.ACC_TYPE_CODE=C.ACC_TYPE_CODE AND C.ACC_BS_PL='BS' AND C.ACC_NOTE='AS' "+
								" AND B.ACC_TYPE_CODE= '"+m_vector.elementAt(0)+"' AND "+
					 			" TO_DATE(TO_CHAR(B.TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')>=TO_DATE('"+m_vector.elementAt(1)+"','DD/MM/YYYY') AND "+
					     	" TO_DATE(TO_CHAR(B.TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')<=TO_DATE('"+m_vector.elementAt(2)+"','DD/MM/YYYY') "+
		 						" GROUP BY TO_CHAR(B.ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(B.TRNDATE,'DD/MON/YY'), "+
								" B.DOCREFNO,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) , "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0), "+
 								" B.PROC_DESC ,B.CORR_ACC_NO ,ROWNUM ) A "+
 								" WHERE A.No >= "+ Start_Val+" AND A.No<= "+End_Val+" "+
                " ORDER BY A.No ";	

	m_bgdata10sql=" SELECT A.No, A.Entered,A.Value,A.Doc_Ref,A.Debit,A.Credit,A.Description,A.Acc_Ref "+
								" FROM "+
							  " (SELECT ROWNUM No, TO_CHAR(B.ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(B.TRNDATE,'DD/MON/YY') Value,	"+
								" B.DOCREFNO Doc_Ref,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0) Credit, "+
 								" B.PROC_DESC Description,B.CORR_ACC_NO Acc_Ref"+
		       			" FROM "+m_schema_name+".DN_ACC_LICEN_INST_GEN_DETAIL B,"+m_schema_name+".DN_ACC_ACCOUNT_TYPE C  "+
					 			" WHERE B.ACC_TYPE_CODE=C.ACC_TYPE_CODE AND C.ACC_BS_PL='BS' AND C.ACC_NOTE='LB' "+
								" AND B.ACC_TYPE_CODE= '"+m_vector.elementAt(0)+"' AND "+
					 			" TO_DATE(TO_CHAR(B.TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')>=TO_DATE('"+m_vector.elementAt(1)+"','DD/MM/YYYY') AND "+
					     	" TO_DATE(TO_CHAR(B.TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')<=TO_DATE('"+m_vector.elementAt(2)+"','DD/MM/YYYY') "+
		 						" GROUP BY TO_CHAR(B.ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(B.TRNDATE,'DD/MON/YY'), "+
								" B.DOCREFNO,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) , "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0), "+
 								" B.PROC_DESC ,B.CORR_ACC_NO ,ROWNUM "+
							  " UNION ALL "+
								" SELECT ROWNUM No, TO_CHAR(B.ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(B.TRNDATE,'DD/MON/YY') Value,	"+
								" B.DOCREFNO Doc_Ref,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0) Credit, "+
 								" B.PROC_DESC Description,B.CORR_ACC_NO Acc_Ref"+
		       			" FROM "+m_schema_name+".DN_ACC_LICEN_DETAIL_HIS B,"+m_schema_name+".DN_ACC_ACCOUNT_TYPE C "+
					 			" WHERE B.ACC_TYPE_CODE=C.ACC_TYPE_CODE AND C.ACC_BS_PL='BS' AND C.ACC_NOTE='LB' "+
								" AND B.ACC_TYPE_CODE= '"+m_vector.elementAt(0)+"' AND "+
					 			" TO_DATE(TO_CHAR(B.TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')>=TO_DATE('"+m_vector.elementAt(1)+"','DD/MM/YYYY') AND "+
					     	" TO_DATE(TO_CHAR(B.TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')<=TO_DATE('"+m_vector.elementAt(2)+"','DD/MM/YYYY') "+
		 						" GROUP BY TO_CHAR(B.ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(B.TRNDATE,'DD/MON/YY'), "+
								" B.DOCREFNO,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) , "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0), "+
 								" B.PROC_DESC ,B.CORR_ACC_NO ,ROWNUM ) A "+
 								" WHERE A.No >= "+ Start_Val+" AND A.No<= "+End_Val+" "+
                " ORDER BY A.No ";	

 m_bgdata11sql=" SELECT A.No, A.Entered,A.Value,A.Doc_Ref,A.Debit,A.Credit,A.Description,A.Acc_Ref "+
								" FROM "+
							  " (SELECT ROWNUM No, TO_CHAR(B.ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(B.TRNDATE,'DD/MON/YY') Value,	"+
								" B.DOCREFNO Doc_Ref,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0) Credit, "+
 								" B.PROC_DESC Description,B.CORR_ACC_NO Acc_Ref"+
		       			" FROM "+m_schema_name+".DN_ACC_LICEN_INST_GEN_DETAIL B,"+m_schema_name+".DN_ACC_ACCOUNT_TYPE C  "+
					 			" WHERE B.ACC_TYPE_CODE=C.ACC_TYPE_CODE AND C.ACC_BS_PL='BS' AND C.ACC_NOTE='AS' "+
								" AND B.ACC_TYPE_CODE= '"+m_vector.elementAt(0)+"' AND "+
					 	   	" TO_DATE(TO_CHAR(B.TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')<=TO_DATE('"+m_vector.elementAt(1)+"','DD/MM/YYYY') "+
		 						" GROUP BY TO_CHAR(B.ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(B.TRNDATE,'DD/MON/YY'), "+
								" B.DOCREFNO,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) , "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0), "+
 								" B.PROC_DESC ,B.CORR_ACC_NO ,B.ROWNUM) A "+
 								" WHERE A.No >= "+ Start_Val+" AND A.No<= "+End_Val+" "+
                " ORDER BY A.No ";		
								
	m_bgdata12sql=" SELECT A.No, A.Entered,A.Value,A.Doc_Ref,A.Debit,A.Credit,A.Description,A.Acc_Ref "+
								" FROM "+
							  " (SELECT ROWNUM No, TO_CHAR(B.ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(B.TRNDATE,'DD/MON/YY') Value,	"+
								" B.DOCREFNO Doc_Ref,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0) Credit, "+
 								" B.PROC_DESC Description,B.CORR_ACC_NO Acc_Ref"+
		       			" FROM "+m_schema_name+".DN_ACC_LICEN_INST_GEN_DETAIL B,"+m_schema_name+".DN_ACC_ACCOUNT_TYPE C  "+
					 			" WHERE B.ACC_TYPE_CODE=C.ACC_TYPE_CODE AND C.ACC_BS_PL='BS' AND C.ACC_NOTE='LB' "+
								" AND B.ACC_TYPE_CODE= '"+m_vector.elementAt(0)+"' AND "+
					 	   	" TO_DATE(TO_CHAR(B.TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')<=TO_DATE('"+m_vector.elementAt(1)+"','DD/MM/YYYY') "+
		 						" GROUP BY TO_CHAR(B.ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(B.TRNDATE,'DD/MON/YY'), "+
								" B.DOCREFNO,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) , "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0), "+
 								" B.PROC_DESC ,B.CORR_ACC_NO ,B.ROWNUM) A "+
 								" WHERE A.No >= "+ Start_Val+" AND A.No<= "+End_Val+" "+
                " ORDER BY A.No ";		
								
	 m_bgdata13sql=" SELECT A.No, A.Entered,A.Value,A.Doc_Ref,A.Debit,A.Credit,A.Description,A.Acc_Ref "+
								" FROM "+
								" (SELECT ROWNUM No, TO_CHAR(B.ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(B.TRNDATE,'DD/MON/YY') Value,	"+
								" B.DOCREFNO Doc_Ref,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0) Credit, "+
 								" B.PROC_DESC Description,B.CORR_ACC_NO Acc_Ref"+
		       			" FROM "+m_schema_name+".DN_ACC_LICEN_INST_GEN_DETAIL B,"+m_schema_name+".DN_ACC_ACCOUNT_TYPE C"+
					 			" WHERE B.ACC_TYPE_CODE=C.ACC_TYPE_CODE AND C.ACC_BS_PL='BS' AND C.ACC_NOTE='AS' "+
								" AND B.ACC_TYPE_CODE= '"+m_vector.elementAt(0)+"' AND "+
					 	   	" TO_DATE(TO_CHAR(B.TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')>=TO_DATE('"+m_vector.elementAt(1)+"','DD/MM/YYYY') "+
		 						" GROUP BY TO_CHAR(B.ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(B.TRNDATE,'DD/MON/YY'), "+
								" B.DOCREFNO,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) , "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0), "+
 								" B.PROC_DESC ,B.CORR_ACC_NO ,ROWNUM "+
								" UNION ALL "+
								" SELECT ROWNUM No, TO_CHAR(B.ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(B.TRNDATE,'DD/MON/YY') Value,	"+
								" B.DOCREFNO Doc_Ref,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0) Credit, "+
 								" B.PROC_DESC Description,B.CORR_ACC_NO Acc_Ref"+
		       			" FROM "+m_schema_name+".DN_ACC_LICEN_DETAIL_HIS B,"+m_schema_name+".DN_ACC_ACCOUNT_TYPE C"+
					 			" WHERE B.ACC_TYPE_CODE=C.ACC_TYPE_CODE AND C.ACC_BS_PL='BS' AND C.ACC_NOTE='AS' "+
								" AND B.ACC_TYPE_CODE= '"+m_vector.elementAt(0)+"' AND "+
					 	   	" TO_DATE(TO_CHAR(B.TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')>=TO_DATE('"+m_vector.elementAt(1)+"','DD/MM/YYYY') "+
		 						" GROUP BY TO_CHAR(B.ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(B.TRNDATE,'DD/MON/YY'), "+
								" B.DOCREFNO,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) , "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0), "+
 								" B.PROC_DESC ,B.CORR_ACC_NO ,ROWNUM ) A "+
 							  " WHERE A.No >= "+ Start_Val+" AND A.No<= "+End_Val+" "+
                " ORDER BY A.No ";		
								
	m_bgdata14sql=" SELECT A.No, A.Entered,A.Value,A.Doc_Ref,A.Debit,A.Credit,A.Description,A.Acc_Ref "+
								" FROM "+
								" (SELECT ROWNUM No, TO_CHAR(B.ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(B.TRNDATE,'DD/MON/YY') Value,	"+
								" B.DOCREFNO Doc_Ref,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0) Credit, "+
 								" B.PROC_DESC Description,B.CORR_ACC_NO Acc_Ref"+
		       			" FROM "+m_schema_name+".DN_ACC_LICEN_INST_GEN_DETAIL B,"+m_schema_name+".DN_ACC_ACCOUNT_TYPE C"+
					 			" WHERE B.ACC_TYPE_CODE=C.ACC_TYPE_CODE AND C.ACC_BS_PL='BS' AND C.ACC_NOTE='LB' "+
								" AND B.ACC_TYPE_CODE= '"+m_vector.elementAt(0)+"' AND "+
					 	   	" TO_DATE(TO_CHAR(B.TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')>=TO_DATE('"+m_vector.elementAt(1)+"','DD/MM/YYYY') "+
		 						" GROUP BY TO_CHAR(B.ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(B.TRNDATE,'DD/MON/YY'), "+
								" B.DOCREFNO,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) , "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0), "+
 								" B.PROC_DESC ,B.CORR_ACC_NO ,ROWNUM "+
								" UNION ALL "+
								" SELECT ROWNUM No, TO_CHAR(B.ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(B.TRNDATE,'DD/MON/YY') Value,	"+
								" B.DOCREFNO Doc_Ref,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0) Credit, "+
 								" B.PROC_DESC Description,B.CORR_ACC_NO Acc_Ref"+
		       			" FROM "+m_schema_name+".DN_ACC_LICEN_DETAIL_HIS B,"+m_schema_name+".DN_ACC_ACCOUNT_TYPE C"+
					 			" WHERE B.ACC_TYPE_CODE=C.ACC_TYPE_CODE AND C.ACC_BS_PL='BS' AND C.ACC_NOTE='LB' "+
								" AND B.ACC_TYPE_CODE= '"+m_vector.elementAt(0)+"' AND "+
					 	   	" TO_DATE(TO_CHAR(B.TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')>=TO_DATE('"+m_vector.elementAt(1)+"','DD/MM/YYYY') "+
		 						" GROUP BY TO_CHAR(B.ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(B.TRNDATE,'DD/MON/YY'), "+
								" B.DOCREFNO,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) , "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0), "+
 								" B.PROC_DESC ,B.CORR_ACC_NO ,ROWNUM ) A "+
 							  " WHERE A.No >= "+ Start_Val+" AND A.No<= "+End_Val+" "+
                " ORDER BY A.No ";	

	  /*----------------------------------------------------------------
		Purpose  : Display Profit And Loss with from and to date
		
		Used in  : General Ledger, 
		-----------------------------------------------------------------*/	
		m_gdata7sql=" SELECT A.No, A.Entered,A.Value,A.Doc_Ref,A.Debit,A.Credit,A.Description,A.Acc_Ref "+
								" FROM "+
								" (SELECT ROWNUM No, TO_CHAR(B.ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(B.TRNDATE,'DD/MON/YY') Value,	"+
								" B.DOCREFNO Doc_Ref,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0) Credit, "+
 								" B.PROC_DESC Description,B.CORR_ACC_NO Acc_Ref"+
		       			" FROM "+m_schema_name+".DN_ACC_LICEN_INST_GEN_DETAIL B,"+m_schema_name+".DN_ACC_ACCOUNT_TYPE C "+
					 			" WHERE B.ACC_TYPE_CODE= '"+m_vector.elementAt(0)+"' "+
								" AND B.ACC_TYPE_CODE=C.ACC_TYPE_CODE AND C.ACC_BS_PL='PL' AND C.ACC_NOTE='IN' "+
					 			" GROUP BY TO_CHAR(B.ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(B.TRNDATE,'DD/MON/YY'), "+
								" DOCREFNO,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) , "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0), "+
 								" B.PROC_DESC ,B.CORR_ACC_NO ,ROWNUM) A "+
 								" WHERE A.No >= "+ Start_Val+" AND A.No<= "+End_Val+" "+
                " ORDER BY A.No ";
								
		m_gdata8sql=" SELECT A.No, A.Entered,A.Value,A.Doc_Ref,A.Debit,A.Credit,A.Description,A.Acc_Ref "+
								" FROM "+
								" (SELECT ROWNUM No, TO_CHAR(B.ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(B.TRNDATE,'DD/MON/YY') Value,	"+
								" B.DOCREFNO Doc_Ref,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0) Credit, "+
 								" B.PROC_DESC Description,B.CORR_ACC_NO Acc_Ref"+
		       			" FROM "+m_schema_name+".DN_ACC_LICEN_INST_GEN_DETAIL B,"+m_schema_name+".DN_ACC_ACCOUNT_TYPE C "+
					 			" WHERE B.ACC_TYPE_CODE= '"+m_vector.elementAt(0)+"' "+
								" AND B.ACC_TYPE_CODE=C.ACC_TYPE_CODE AND C.ACC_BS_PL='PL' AND C.ACC_NOTE='EX' "+
					 			" GROUP BY TO_CHAR(B.ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(B.TRNDATE,'DD/MON/YY'), "+
								" DOCREFNO,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) , "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0), "+
 								" B.PROC_DESC ,B.CORR_ACC_NO ,ROWNUM) A "+
 								" WHERE A.No >= "+ Start_Val+" AND A.No<= "+End_Val+" "+
                " ORDER BY A.No ";			
		
		m_gdata9sql=" SELECT A.No, A.Entered,A.Value,A.Doc_Ref,A.Debit,A.Credit,A.Description,A.Acc_Ref "+
								" FROM "+
							  " (SELECT ROWNUM No, TO_CHAR(B.ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(B.TRNDATE,'DD/MON/YY') Value,	"+
								" B.DOCREFNO Doc_Ref,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0) Credit, "+
 								" B.PROC_DESC Description,B.CORR_ACC_NO Acc_Ref"+
		       			" FROM "+m_schema_name+".DN_ACC_LICEN_INST_GEN_DETAIL B,"+m_schema_name+".DN_ACC_ACCOUNT_TYPE C  "+
					 			" WHERE B.ACC_TYPE_CODE=C.ACC_TYPE_CODE AND C.ACC_BS_PL='PL' AND C.ACC_NOTE='IN' "+
								" AND B.ACC_TYPE_CODE= '"+m_vector.elementAt(0)+"' AND "+
					 			" TO_DATE(TO_CHAR(B.TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')>=TO_DATE('"+m_vector.elementAt(1)+"','DD/MM/YYYY') AND "+
					     	" TO_DATE(TO_CHAR(B.TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')<=TO_DATE('"+m_vector.elementAt(2)+"','DD/MM/YYYY') "+
		 						" GROUP BY TO_CHAR(B.ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(B.TRNDATE,'DD/MON/YY'), "+
								" B.DOCREFNO,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) , "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0), "+
 								" B.PROC_DESC ,B.CORR_ACC_NO ,ROWNUM "+
							  " UNION ALL "+
								" SELECT ROWNUM No, TO_CHAR(B.ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(B.TRNDATE,'DD/MON/YY') Value,	"+
								" B.DOCREFNO Doc_Ref,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0) Credit, "+
 								" B.PROC_DESC Description,B.CORR_ACC_NO Acc_Ref"+
		       			" FROM "+m_schema_name+".DN_ACC_LICEN_DETAIL_HIS B,"+m_schema_name+".DN_ACC_ACCOUNT_TYPE C "+
					 			" WHERE B.ACC_TYPE_CODE=C.ACC_TYPE_CODE AND C.ACC_BS_PL='PL' AND C.ACC_NOTE='IN' "+
								" AND B.ACC_TYPE_CODE= '"+m_vector.elementAt(0)+"' AND "+
					 			" TO_DATE(TO_CHAR(B.TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')>=TO_DATE('"+m_vector.elementAt(1)+"','DD/MM/YYYY') AND "+
					     	" TO_DATE(TO_CHAR(B.TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')<=TO_DATE('"+m_vector.elementAt(2)+"','DD/MM/YYYY') "+
		 						" GROUP BY TO_CHAR(B.ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(B.TRNDATE,'DD/MON/YY'), "+
								" B.DOCREFNO,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) , "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0), "+
 								" B.PROC_DESC ,B.CORR_ACC_NO ,ROWNUM ) A "+
 								" WHERE A.No >= "+ Start_Val+" AND A.No<= "+End_Val+" "+
                " ORDER BY A.No ";	

	m_gdata10sql=" SELECT A.No, A.Entered,A.Value,A.Doc_Ref,A.Debit,A.Credit,A.Description,A.Acc_Ref "+
								" FROM "+
							  " (SELECT ROWNUM No, TO_CHAR(B.ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(B.TRNDATE,'DD/MON/YY') Value,	"+
								" B.DOCREFNO Doc_Ref,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0) Credit, "+
 								" B.PROC_DESC Description,B.CORR_ACC_NO Acc_Ref"+
		       			" FROM "+m_schema_name+".DN_ACC_LICEN_INST_GEN_DETAIL B,"+m_schema_name+".DN_ACC_ACCOUNT_TYPE C  "+
					 			" WHERE B.ACC_TYPE_CODE=C.ACC_TYPE_CODE AND C.ACC_BS_PL='PL' AND C.ACC_NOTE='EX' "+
								" AND B.ACC_TYPE_CODE= '"+m_vector.elementAt(0)+"' AND "+
					 			" TO_DATE(TO_CHAR(B.TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')>=TO_DATE('"+m_vector.elementAt(1)+"','DD/MM/YYYY') AND "+
					     	" TO_DATE(TO_CHAR(B.TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')<=TO_DATE('"+m_vector.elementAt(2)+"','DD/MM/YYYY') "+
		 						" GROUP BY TO_CHAR(B.ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(B.TRNDATE,'DD/MON/YY'), "+
								" B.DOCREFNO,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) , "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0), "+
 								" B.PROC_DESC ,B.CORR_ACC_NO ,ROWNUM "+
							  " UNION ALL "+
								" SELECT ROWNUM No, TO_CHAR(B.ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(B.TRNDATE,'DD/MON/YY') Value,	"+
								" B.DOCREFNO Doc_Ref,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0) Credit, "+
 								" B.PROC_DESC Description,B.CORR_ACC_NO Acc_Ref"+
		       			" FROM "+m_schema_name+".DN_ACC_LICEN_DETAIL_HIS B,"+m_schema_name+".DN_ACC_ACCOUNT_TYPE C "+
					 			" WHERE B.ACC_TYPE_CODE=C.ACC_TYPE_CODE AND C.ACC_BS_PL='PL' AND C.ACC_NOTE='EX' "+
								" AND B.ACC_TYPE_CODE= '"+m_vector.elementAt(0)+"' AND "+
					 			" TO_DATE(TO_CHAR(B.TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')>=TO_DATE('"+m_vector.elementAt(1)+"','DD/MM/YYYY') AND "+
					     	" TO_DATE(TO_CHAR(B.TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')<=TO_DATE('"+m_vector.elementAt(2)+"','DD/MM/YYYY') "+
		 						" GROUP BY TO_CHAR(B.ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(B.TRNDATE,'DD/MON/YY'), "+
								" B.DOCREFNO,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) , "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0), "+
 								" B.PROC_DESC ,B.CORR_ACC_NO ,ROWNUM ) A "+
 								" WHERE A.No >= "+ Start_Val+" AND A.No<= "+End_Val+" "+
                " ORDER BY A.No ";	

 m_gdata11sql=" SELECT A.No, A.Entered,A.Value,A.Doc_Ref,A.Debit,A.Credit,A.Description,A.Acc_Ref "+
								" FROM "+
							  " (SELECT ROWNUM No, TO_CHAR(B.ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(B.TRNDATE,'DD/MON/YY') Value,	"+
								" B.DOCREFNO Doc_Ref,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0) Credit, "+
 								" B.PROC_DESC Description,B.CORR_ACC_NO Acc_Ref"+
		       			" FROM "+m_schema_name+".DN_ACC_LICEN_INST_GEN_DETAIL B,"+m_schema_name+".DN_ACC_ACCOUNT_TYPE C  "+
					 			" WHERE B.ACC_TYPE_CODE=C.ACC_TYPE_CODE AND C.ACC_BS_PL='PL' AND C.ACC_NOTE='IN' "+
								" AND B.ACC_TYPE_CODE= '"+m_vector.elementAt(0)+"' AND "+
					 	   	" TO_DATE(TO_CHAR(B.TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')<=TO_DATE('"+m_vector.elementAt(1)+"','DD/MM/YYYY') "+
		 						" GROUP BY TO_CHAR(B.ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(B.TRNDATE,'DD/MON/YY'), "+
								" B.DOCREFNO,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) , "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0), "+
 								" B.PROC_DESC ,B.CORR_ACC_NO ,B.ROWNUM) A "+
 								" WHERE A.No >= "+ Start_Val+" AND A.No<= "+End_Val+" "+
                " ORDER BY A.No ";		
								
	m_gdata12sql=" SELECT A.No, A.Entered,A.Value,A.Doc_Ref,A.Debit,A.Credit,A.Description,A.Acc_Ref "+
								" FROM "+
							  " (SELECT ROWNUM No, TO_CHAR(B.ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(B.TRNDATE,'DD/MON/YY') Value,	"+
								" B.DOCREFNO Doc_Ref,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0) Credit, "+
 								" B.PROC_DESC Description,B.CORR_ACC_NO Acc_Ref"+
		       			" FROM "+m_schema_name+".DN_ACC_LICEN_INST_GEN_DETAIL B,"+m_schema_name+".DN_ACC_ACCOUNT_TYPE C  "+
					 			" WHERE B.ACC_TYPE_CODE=C.ACC_TYPE_CODE AND C.ACC_BS_PL='PL' AND C.ACC_NOTE='EX' "+
								" AND B.ACC_TYPE_CODE= '"+m_vector.elementAt(0)+"' AND "+
					 	   	" TO_DATE(TO_CHAR(B.TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')<=TO_DATE('"+m_vector.elementAt(1)+"','DD/MM/YYYY') "+
		 						" GROUP BY TO_CHAR(B.ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(B.TRNDATE,'DD/MON/YY'), "+
								" B.DOCREFNO,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) , "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0), "+
 								" B.PROC_DESC ,B.CORR_ACC_NO ,B.ROWNUM) A "+
 								" WHERE A.No >= "+ Start_Val+" AND A.No<= "+End_Val+" "+
                " ORDER BY A.No ";		
								
	 m_gdata13sql=" SELECT A.No, A.Entered,A.Value,A.Doc_Ref,A.Debit,A.Credit,A.Description,A.Acc_Ref "+
								" FROM "+
								" (SELECT ROWNUM No, TO_CHAR(B.ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(B.TRNDATE,'DD/MON/YY') Value,	"+
								" B.DOCREFNO Doc_Ref,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0) Credit, "+
 								" B.PROC_DESC Description,B.CORR_ACC_NO Acc_Ref"+
		       			" FROM "+m_schema_name+".DN_ACC_LICEN_INST_GEN_DETAIL B,"+m_schema_name+".DN_ACC_ACCOUNT_TYPE C"+
					 			" WHERE B.ACC_TYPE_CODE=C.ACC_TYPE_CODE AND C.ACC_BS_PL='PL' AND C.ACC_NOTE='IN' "+
								" AND B.ACC_TYPE_CODE= '"+m_vector.elementAt(0)+"' AND "+
					 	   	" TO_DATE(TO_CHAR(B.TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')>=TO_DATE('"+m_vector.elementAt(1)+"','DD/MM/YYYY') "+
		 						" GROUP BY TO_CHAR(B.ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(B.TRNDATE,'DD/MON/YY'), "+
								" B.DOCREFNO,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) , "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0), "+
 								" B.PROC_DESC ,B.CORR_ACC_NO ,ROWNUM "+
								" UNION ALL "+
								" SELECT ROWNUM No, TO_CHAR(B.ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(B.TRNDATE,'DD/MON/YY') Value,	"+
								" B.DOCREFNO Doc_Ref,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0) Credit, "+
 								" B.PROC_DESC Description,B.CORR_ACC_NO Acc_Ref"+
		       			" FROM "+m_schema_name+".DN_ACC_LICEN_DETAIL_HIS B,"+m_schema_name+".DN_ACC_ACCOUNT_TYPE C"+
					 			" WHERE B.ACC_TYPE_CODE=C.ACC_TYPE_CODE AND C.ACC_BS_PL='PL' AND C.ACC_NOTE='IN' "+
								" AND B.ACC_TYPE_CODE= '"+m_vector.elementAt(0)+"' AND "+
					 	   	" TO_DATE(TO_CHAR(B.TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')>=TO_DATE('"+m_vector.elementAt(1)+"','DD/MM/YYYY') "+
		 						" GROUP BY TO_CHAR(B.ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(B.TRNDATE,'DD/MON/YY'), "+
								" B.DOCREFNO,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) , "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0), "+
 								" B.PROC_DESC ,B.CORR_ACC_NO ,ROWNUM ) A "+
 							  " WHERE A.No >= "+ Start_Val+" AND A.No<= "+End_Val+" "+
                " ORDER BY A.No ";		
								
	m_gdata14sql=" SELECT A.No, A.Entered,A.Value,A.Doc_Ref,A.Debit,A.Credit,A.Description,A.Acc_Ref "+
								" FROM "+
								" (SELECT ROWNUM No, TO_CHAR(B.ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(B.TRNDATE,'DD/MON/YY') Value,	"+
								" B.DOCREFNO Doc_Ref,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0) Credit, "+
 								" B.PROC_DESC Description,B.CORR_ACC_NO Acc_Ref"+
		       			" FROM "+m_schema_name+".DN_ACC_LICEN_INST_GEN_DETAIL B,"+m_schema_name+".DN_ACC_ACCOUNT_TYPE C"+
					 			" WHERE B.ACC_TYPE_CODE=C.ACC_TYPE_CODE AND C.ACC_BS_PL='PL' AND C.ACC_NOTE='EX' "+
								" AND B.ACC_TYPE_CODE= '"+m_vector.elementAt(0)+"' AND "+
					 	   	" TO_DATE(TO_CHAR(B.TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')>=TO_DATE('"+m_vector.elementAt(1)+"','DD/MM/YYYY') "+
		 						" GROUP BY TO_CHAR(B.ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(B.TRNDATE,'DD/MON/YY'), "+
								" B.DOCREFNO,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) , "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0), "+
 								" B.PROC_DESC ,B.CORR_ACC_NO ,ROWNUM "+
								" UNION ALL "+
								" SELECT ROWNUM No, TO_CHAR(B.ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(B.TRNDATE,'DD/MON/YY') Value,	"+
								" B.DOCREFNO Doc_Ref,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0) Credit, "+
 								" B.PROC_DESC Description,B.CORR_ACC_NO Acc_Ref"+
		       			" FROM "+m_schema_name+".DN_ACC_LICEN_DETAIL_HIS B,"+m_schema_name+".DN_ACC_ACCOUNT_TYPE C"+
					 			" WHERE B.ACC_TYPE_CODE=C.ACC_TYPE_CODE AND C.ACC_BS_PL='PL' AND C.ACC_NOTE='EX' "+
								" AND B.ACC_TYPE_CODE= '"+m_vector.elementAt(0)+"' AND "+
					 	   	" TO_DATE(TO_CHAR(B.TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')>=TO_DATE('"+m_vector.elementAt(1)+"','DD/MM/YYYY') "+
		 						" GROUP BY TO_CHAR(B.ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(B.TRNDATE,'DD/MON/YY'), "+
								" B.DOCREFNO,NVL(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT),0) , "+
 								" NVL(DECODE(B.DRCR_STATUS,'CR',B.TRNAMOUNT),0), "+
 								" B.PROC_DESC ,B.CORR_ACC_NO ,ROWNUM ) A "+
 							  " WHERE A.No >= "+ Start_Val+" AND A.No<= "+End_Val+" "+
                " ORDER BY A.No ";	
		/*----------------------------------------------------------------
		Purpose  : Display Trail Balance details with from and to date
		
		Used in  : General Ledger, 
		-----------------------------------------------------------------*/	
		m_gdatasql=" SELECT A.No, A.Entered,A.Value,A.Doc_Ref,A.Debit,A.Credit,A.Description,A.Acc_Ref "+
								" FROM "+
								" (SELECT ROWNUM No, TO_CHAR(ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(TRNDATE,'DD/MON/YY') Value,	"+
								" DOCREFNO Doc_Ref,NVL(DECODE(DRCR_STATUS,'DR',TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(DRCR_STATUS,'CR',TRNAMOUNT),0) Credit, "+
 								" PROC_DESC Description,CORR_ACC_NO Acc_Ref"+
		       			" FROM "+m_schema_name+".DN_ACC_LICEN_INST_GEN_DETAIL "+
					 			" WHERE PORTFOLIO_CODE='"+m_vector.elementAt(0)+"' AND ACC_TYPE_CODE= '"+m_vector.elementAt(1)+"' "+
					 			" GROUP BY TO_CHAR(ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(TRNDATE,'DD/MON/YY'), "+
								" DOCREFNO,NVL(DECODE(DRCR_STATUS,'DR',TRNAMOUNT),0) , "+
 								" NVL(DECODE(DRCR_STATUS,'CR',TRNAMOUNT),0), "+
 								" PROC_DESC ,CORR_ACC_NO ,ROWNUM) A "+
 								" WHERE A.No >= "+ Start_Val+" AND A.No<= "+End_Val+" "+
                " ORDER BY A.No ";
								
		m_gdata2sql=" SELECT A.No, A.Entered,A.Value,A.Doc_Ref,A.Debit,A.Credit,A.Description,A.Acc_Ref "+
								" FROM "+
							  " (SELECT ROWNUM No, TO_CHAR(ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(TRNDATE,'DD/MON/YY') Value,	"+
								" DOCREFNO Doc_Ref,NVL(DECODE(DRCR_STATUS,'DR',TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(DRCR_STATUS,'CR',TRNAMOUNT),0) Credit, "+
 								" PROC_DESC Description,CORR_ACC_NO Acc_Ref"+
		       			" FROM "+m_schema_name+".DN_ACC_LICEN_INST_GEN_DETAIL "+
					 			" WHERE PORTFOLIO_CODE='"+m_vector.elementAt(0)+"' AND ACC_TYPE_CODE= '"+m_vector.elementAt(1)+"' AND "+
					 			" TO_DATE(TO_CHAR(TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')>=TO_DATE('"+m_vector.elementAt(2)+"','DD/MM/YYYY') AND "+
					     	" TO_DATE(TO_CHAR(TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')<=TO_DATE('"+m_vector.elementAt(3)+"','DD/MM/YYYY') "+
		 						" GROUP BY TO_CHAR(ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(TRNDATE,'DD/MON/YY'), "+
								" DOCREFNO,NVL(DECODE(DRCR_STATUS,'DR',TRNAMOUNT),0) , "+
 								" NVL(DECODE(DRCR_STATUS,'CR',TRNAMOUNT),0), "+
 								" PROC_DESC ,CORR_ACC_NO ,ROWNUM "+
							  " UNION ALL "+
								" SELECT ROWNUM No, TO_CHAR(ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(TRNDATE,'DD/MON/YY') Value,	"+
								" DOCREFNO Doc_Ref,NVL(DECODE(DRCR_STATUS,'DR',TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(DRCR_STATUS,'CR',TRNAMOUNT),0) Credit, "+
 								" PROC_DESC Description,CORR_ACC_NO Acc_Ref"+
		       			" FROM "+m_schema_name+".DN_ACC_LICEN_DETAIL_HIS "+
					 			" WHERE PORTFOLIO_CODE='"+m_vector.elementAt(0)+"' AND ACC_TYPE_CODE= '"+m_vector.elementAt(1)+"' AND "+
					 			" TO_DATE(TO_CHAR(TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')>=TO_DATE('"+m_vector.elementAt(2)+"','DD/MM/YYYY') AND "+
					     	" TO_DATE(TO_CHAR(TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')<=TO_DATE('"+m_vector.elementAt(3)+"','DD/MM/YYYY') "+
		 						" GROUP BY TO_CHAR(ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(TRNDATE,'DD/MON/YY'), "+
								" DOCREFNO,NVL(DECODE(DRCR_STATUS,'DR',TRNAMOUNT),0) , "+
 								" NVL(DECODE(DRCR_STATUS,'CR',TRNAMOUNT),0), "+
 								" PROC_DESC ,CORR_ACC_NO ,ROWNUM ) A "+
 								" WHERE A.No >= "+ Start_Val+" AND A.No<= "+End_Val+" "+
                " ORDER BY A.No ";	
								
			m_gdata5sql=" SELECT A.No, A.Entered,A.Value,A.Doc_Ref,A.Debit,A.Credit,A.Description,A.Acc_Ref "+
								" FROM "+
							  " (SELECT ROWNUM No, TO_CHAR(ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(TRNDATE,'DD/MON/YY') Value,	"+
								" DOCREFNO Doc_Ref,NVL(DECODE(DRCR_STATUS,'DR',TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(DRCR_STATUS,'CR',TRNAMOUNT),0) Credit, "+
 								" PROC_DESC Description,CORR_ACC_NO Acc_Ref"+
		       			" FROM "+m_schema_name+".DN_ACC_LICEN_INST_GEN_DETAIL "+
					 			" WHERE PORTFOLIO_CODE='"+m_vector.elementAt(0)+"' AND ACC_TYPE_CODE= '"+m_vector.elementAt(1)+"' AND "+
					 	   	" TO_DATE(TO_CHAR(TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')<=TO_DATE('"+m_vector.elementAt(2)+"','DD/MM/YYYY') "+
		 						" GROUP BY TO_CHAR(ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(TRNDATE,'DD/MON/YY'), "+
								" DOCREFNO,NVL(DECODE(DRCR_STATUS,'DR',TRNAMOUNT),0) , "+
 								" NVL(DECODE(DRCR_STATUS,'CR',TRNAMOUNT),0), "+
 								" PROC_DESC ,CORR_ACC_NO ,ROWNUM) A "+
 								" WHERE A.No >= "+ Start_Val+" AND A.No<= "+End_Val+" "+
                " ORDER BY A.No ";				
								
			m_gdata6sql=" SELECT A.No, A.Entered,A.Value,A.Doc_Ref,A.Debit,A.Credit,A.Description,A.Acc_Ref "+
								" FROM "+
								" (SELECT ROWNUM No, TO_CHAR(ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(TRNDATE,'DD/MON/YY') Value,	"+
								" DOCREFNO Doc_Ref,NVL(DECODE(DRCR_STATUS,'DR',TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(DRCR_STATUS,'CR',TRNAMOUNT),0) Credit, "+
 								" PROC_DESC Description,CORR_ACC_NO Acc_Ref"+
		       			" FROM "+m_schema_name+".DN_ACC_LICEN_INST_GEN_DETAIL "+
					 			" WHERE PORTFOLIO_CODE='"+m_vector.elementAt(0)+"' AND ACC_TYPE_CODE= '"+m_vector.elementAt(1)+"' AND "+
					 	   	" TO_DATE(TO_CHAR(TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')>=TO_DATE('"+m_vector.elementAt(2)+"','DD/MM/YYYY') "+
		 						" GROUP BY TO_CHAR(ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(TRNDATE,'DD/MON/YY'), "+
								" DOCREFNO,NVL(DECODE(DRCR_STATUS,'DR',TRNAMOUNT),0) , "+
 								" NVL(DECODE(DRCR_STATUS,'CR',TRNAMOUNT),0), "+
 								" PROC_DESC ,CORR_ACC_NO ,ROWNUM "+
								" UNION ALL "+
								" SELECT ROWNUM No, TO_CHAR(ENTDATE,'DD/MON/YY') Entered, "+
								"	TO_CHAR(TRNDATE,'DD/MON/YY') Value,	"+
								" DOCREFNO Doc_Ref,NVL(DECODE(DRCR_STATUS,'DR',TRNAMOUNT),0) Debit, "+
 								" NVL(DECODE(DRCR_STATUS,'CR',TRNAMOUNT),0) Credit, "+
 								" PROC_DESC Description,CORR_ACC_NO Acc_Ref"+
		       			" FROM "+m_schema_name+".DN_ACC_LICEN_DETAIL_HIS "+
					 			" WHERE PORTFOLIO_CODE='"+m_vector.elementAt(0)+"' AND ACC_TYPE_CODE= '"+m_vector.elementAt(1)+"' AND "+
					 	   	" TO_DATE(TO_CHAR(TRNDATE,'DD/MM/YYYY'),'DD/MM/YYYY')>=TO_DATE('"+m_vector.elementAt(2)+"','DD/MM/YYYY') "+
		 						" GROUP BY TO_CHAR(ENTDATE,'DD/MON/YY'), "+
								" TO_CHAR(TRNDATE,'DD/MON/YY'), "+
								" DOCREFNO,NVL(DECODE(DRCR_STATUS,'DR',TRNAMOUNT),0) , "+
 								" NVL(DECODE(DRCR_STATUS,'CR',TRNAMOUNT),0), "+
 								" PROC_DESC ,CORR_ACC_NO ,ROWNUM ) A "+
 							  " WHERE A.No >= "+ Start_Val+" AND A.No<= "+End_Val+" "+
                " ORDER BY A.No ";		
		/*----------------------------------------------------------------
		Purpose  : get deals
		
		Used in  : Repo Full settlement, 
		-----------------------------------------------------------------*/											
		m_dealhelpsql ="SELECT P.NO, P.DEAL_NO Deal_No,P.BORR_PORTFOLIO_CODE Borrower,P.LEND_PORTFOLIO_CODE Lender,P.LEG2_VALUE Value,TO_CHAR(P.ZZ,'DD-MON-YYYY') Maturity_Date "+  
			 "FROM "+  
			 "(SELECT ROWNUM NO, Z.DEAL_NO,Z.BORR_PORTFOLIO_CODE,Z.LEND_PORTFOLIO_CODE,Z.LEG2_VALUE,Z.XX ZZ "+
			 "FROM "+   
			 "(SELECT A.DEAL_NO,A.BORR_PORTFOLIO_CODE,A.LEND_PORTFOLIO_CODE,A.LEG2_VALUE,TO_DATE(TO_CHAR(A.MATURITY_DATE,'DD-MON-YYYY'),'DD-MON-YYYY') XX FROM "+m_schema_name+".DN_TRN_REPO_FRESH A "+   
			 "WHERE APP_STATUS='CONF' AND A.DEAL_NO NOT IN "+
			 "(SELECT B.PRIOR_DEAL_NO FROM "+m_schema_name+".DN_TRN_REPO_SETTLEMENT B WHERE B.APP_STATUS<>'CANCEL') "+
			 "AND "+
			 "A.DEAL_NO NOT IN "+
			 "(SELECT C.PRIOR_DEAL_NO FROM "+m_schema_name+".DN_TRN_REPO_ROLLOVER C WHERE C.APP_STATUS<>'CANCEL') "+
			 "UNION ALL "+
			 "SELECT A.DEAL_NO,A.BORR_PORTFOLIO_CODE,A.LEND_PORTFOLIO_CODE,A.LEG2_VALUE,TO_DATE(TO_CHAR(A.MATURITY_DATE,'DD-MON-YYYY'),'DD-MON-YYYY') XX FROM "+m_schema_name+".DN_TRN_REPO_ROLLOVER A "+
			 "WHERE APP_STATUS='CONF' AND A.DEAL_NO NOT IN "+
			 "(SELECT B.PRIOR_DEAL_NO FROM "+m_schema_name+".DN_TRN_REPO_SETTLEMENT B WHERE B.APP_STATUS<>'CANCEL') "+
			 "AND "+
			 "A.DEAL_NO NOT IN "+
			 "(SELECT C.PRIOR_DEAL_NO FROM "+m_schema_name+".DN_TRN_REPO_ROLLOVER C WHERE C.APP_STATUS<>'CANCEL') "+
			 "GROUP BY MATURITY_DATE,DEAL_NO,BORR_PORTFOLIO_CODE,LEND_PORTFOLIO_CODE,LEG2_VALUE) Z ) P "+
  		 "WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" "+									
	    "ORDER BY TO_DATE(TO_CHAR(P.ZZ,'DD-MON-YYYY'),'DD-MON-YYYY')";
			
			/*----------------------------------------------------------------------
			Purpose : get client confirmation 
			
			Used in : Client Confirmation from MBFSL back-office report.
			------------------------------------------------------------------------
			m_clchelp_Header = "SELECT  "+
					"	 FROM "+m_schema_name+".DN_TRN_OUTP_SALE D, "+
					"	 "+m_schema_name+".DN_REF_PORTFOLIO K "+
					"	 WHERE "+
					"	 A.CLIENT_NO=B.CLIENT_NO "+
					"	 AND "+
					"	 upper(A.MAS_DEBTOR_NAME) LIKE upper('"+m_client_data+"%') ORDER BY ");*/
					
	////////////////////////////////////////////////////////////////////////////////////				
					
					
					
  //	m_custodial_helpsql = " SELECT CUSTODIAL_NO,P.CUSTODIAL_CODE ,P.CUSTODIAL_NAME " +
									   // " FROM "+
		//									"(SELECT ROWNUM NO,A.CUSTODIAL_CODE,A.CUSTODIAL_NAME  "+
			//								" FROM "+
				//							"(SELECT CUSTODIAL_CODE,CUSTODIAL_NAME  "+
						////			" FROM "+m_schema_name+".DN_INV_CUST_DEAL_SERIAL_NO " +
					//	//				  " WHERE (CUSTODIAL_CODE '"+m_vector.elementAt(0)+"%' OR "+
								//		  " UPPER(CUSTODIAL_NAME) LIKE UPPER('"+m_vector.elementAt(0)+"%')) AND "+
										  //" ACTIVE_STATUS='Y' " +
									//	  " GROUP BY  CUSTODIAL_NAME, CUSTODIAL_CODE ) A ) P "+
										//// WHERE P.NO >=  "+ Start_Val+"  AND P.NO <=  "+End_Val+" ";	
											
		////////////////////////////////////////////////////////////////////////////////////	
		
	 /*	m_custodial_helpsql =" SELECT  A.NO,A.CODE "+
                       " FROM "+
											 " (SELECT  CUSTODIAL_CODE CODE,ROWNUM NO   "+
                       "  FROM "+m_schema_name+".DN_INV_CUST_DEAL_SERIAL_NO )A  "+
											 "  WHERE A.No >= "+ Start_Val+" AND A.No <= "+End_Val+" " ;
												
												
												
												*/
												
												
												
												/*m_custodial_helpsql="	SELECT P.NO,P.CUSTODIAL_CODE,P.PF_NAME "+
                                             " FROM "+(												
                                             " SELECT ROWNUM NO,A.CUSTODIAL_CODE,A.PF_NAME "+
                                             "  FROM "+(
                                              "  SELECT D.CUSTODIAL_CODE,G.PF_NAME "+
                                             " FROM   "+m_schema_name+".DN_INV_CUST_DEAL_SERIAL_NO D, "+
                                              " "+m_schema_name+".DN_REF_PORTFOLIO G "+
                                          " WHERE  D.CUSTODIAL_CODE=G.PORTFOLIO_CODE AND "+
                                           " D.CUSTODIAL_CODE='CUST1' "+
                                               " GROUP BY  D.CUSTODIAL_CODE,G.PF_NAME )A)P "+
                                               "  WHERE P.NO>="+ Start_Val+" AND P.NO<="+End_Val+" " ;
												
												*/
												
												
								/*				m_custodial_helpsql="	SELECT P.NO,P.CUSTODIAL_CODE,P.PF_NAME "+
                                             " FROM "+(												
                                             " SELECT ROWNUM NO,A.CUSTODIAL_CODE CODE,A.PF_NAME "+
                                             "  FROM "+(
                                              "  SELECT D.CUSTODIAL_CODE,G.PF_NAME "+
                                             " FROM   "+m_schema_name+".DN_INV_CUST_DEAL_SERIAL_NO D, "+
                                              " "+m_schema_name+".DN_REF_PORTFOLIO G "+
                                          " WHERE (D.CUSTODIAL_CODE=G.PORTFOLIO_CODE AND "+
                                           " D.CUSTODIAL_CODE='CUST1' "+
                                               " GROUP BY  D.CUSTODIAL_CODE,G.PF_NAME )A)P "+
                                             //  "  WHERE P.NO>="+ Start_Val +" AND P.NO<="+ End_Val +" " ;
													
																							 "  WHERE P.No >= "+ Start_Val+" AND P.No <= "+End_Val+" ") );
												*/
												
 			m_custodial_helpsql="SELECT P.NO,P.CUSTODIAL_CODE,P.NAME "+
														"FROM ("+
                             "SELECT ROWNUM NO,A.CUSTODIAL_CODE,A.NAME  "+
                             "FROM ( "+
                            " SELECT D.CUSTODIAL_CODE,G.PF_NAME  NAME"+ 
                             " FROM   "+m_schema_name+".DN_INV_CUST_DEAL_SERIAL_NO D, "+
                              "                 "+m_schema_name+".DN_REF_PORTFOLIO G  "+
                               "            WHERE (D.CUSTODIAL_CODE)=G.PORTFOLIO_CODE  "+
                               // "            D.CUSTODIAL_CODE='CUST1' "+
                                 "               GROUP BY  D.CUSTODIAL_CODE,G.PF_NAME )A)P "+
                                 "  WHERE P.No >= "+ Start_Val+" AND P.No <= "+End_Val+" " ; 													
										
												
	
 /*	m_custodial_helpsql =" SELECT  A.NO,A.CODE "+
                       " FROM "+
											 " (SELECT  CUSTODIAL_CODE ,ROWNUM NO   "+
                       "  FROM "+m_schema_name+".DN_INV_CUST_DEAL_SERIAL_NO )A  "+
											 "  WHERE A.No >= "+ Start_Val+" AND A.No <= "+End_Val+" " ;
													
  		// " WHERE A.No >= "+ Start_Val+" AND A.No <=  "+End_Val+" ";
				
			/*	//----------------------------------
				
				 m_portsql   ="SELECT NO, DESCRIPTION, CODE FROM "+
	 								 	"(SELECT ROWNUM NO, DESCRIPTION, PORT_CODE CODE "+
										"FROM (SELECT  PORT_CODE, DESCRIPTION "+
										"FROM "+m_schema_name+".REF_PORT "+
							 	 	  "WHERE ACTIVE_STATUS = 'Y' AND PORT_TYPE = "+m_vector.elementAt(1)+" "+
										"AND UPPER(COUNTRY_CODE) = UPPER('"+m_vector.elementAt(0)+"') ) A) P "+
										"WHERE P.NO>= "+Start_Val+" AND P.NO<= "+End_Val+" ";	
				
				//-----------------------------------*/
				
	/*---------------------------------------------------------------
	   Purpose  : get printer details
			
		 Used in  : Printer Screen
			
	------------------------------------------------------------------*/
	
   m_printersql = " SELECT P.NO,P.NAME ,P.NORMAL,P.SERVER,P.TYPE,P.ACTIVE FROM ("+
		              " SELECT ROWNUM NO,NAME,NORMAL,SERVER,TYPE,ACTIVE FROM( "+
		              " SELECT P_NAME NAME,REPLACE(NVL(NORMAL_SHARED_NAME,'-'),'/','\\\\') NORMAL, "+
									" NVL(SERVER_SHARED_NAME,'-') SERVER,P_TYPE TYPE,ACTIVE_STATUS ACTIVE "+
			  	        " FROM "+m_schema_name+".DN_REF_PRINTER "+
									" WHERE P_NAME LIKE '"+m_vector.elementAt(0)+"%' "+
									" ) A ) P WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";					
	
	
	//--ADDED BY GAYAN ON 10-07-2001 FOR REPOS-FULL SETTLEMENT REPO FRESH HELP
	m_rfullrfhelp = " SELECT H.NO,H.Deal_Number,H.Borrower_Code,H.Lender_Code,TO_CHAR(H.Leg2_Value,'FM999G999G999G999G999G990D00')Leg2_Value,TO_CHAR(H.Maturity_Date,'DD-MON-YYYY')Maturity_Date,TO_CHAR(H.Value_Date,'DD-MON-YYYY')Value_Date,H.On_Call FROM "+
                 " ( "+
	               " SELECT ROWNUM NO,P.Deal_Number,P.Borrower_Code,P.Lender_Code,P.Leg2_Value,P.Maturity_Date,P.Value_Date,P.On_Call FROM "+
                 " (SELECT Deal_Number,Borrower_Code,Lender_Code,Leg2_Value,Maturity_Date,Value_Date,On_Call FROM "+
	               " (SELECT "+
								 " A.DEAL_NO  Deal_Number,"+
						 		 " A.BORR_PORTFOLIO_CODE Borrower_Code,"+
						 		 " A.LEND_PORTFOLIO_CODE Lender_Code,"+
								 " A.LEG2_VALUE  Leg2_Value,"+
								 " A.MATURITY_DATE Maturity_Date,"+
						 		 " A.EFF_VALDATE Value_Date,"+ 
								 " ON_CALL_DEAL On_Call"+	
								 " FROM "+
								 " "+m_schema_name+".DN_TRN_REPO_FRESH A,"+m_schema_name+".DN_REF_PORTFOLIO B,"+m_schema_name+".DN_REF_PORTFOLIO B1 "+
								 " WHERE "+
						 		 " A.APP_STATUS='CONF' AND  "+
								  "A.DEAL_NO LIKE '"+m_vector.elementAt(0)+"%'  AND "+
								 //""+m_schema_name+".DN_CHK_DEALNO_RF_RR(A.DEAL_NO)='N' "+
                  "B.BRANCH LIKE ("+m_schema_name+".DN_GET_USER_LOCATION('"+m_vector.elementAt(1)+"')) AND  (A.BORR_PORTFOLIO_CODE=B.PORTFOLIO_CODE ) AND  "+
									"B1.BRANCH LIKE ("+m_schema_name+".DN_GET_USER_LOCATION('"+m_vector.elementAt(1)+"')) AND  (A.LEND_PORTFOLIO_CODE=B1.PORTFOLIO_CODE ) AND  "+

									" A.DEAL_NO NOT IN (SELECT B.PRIOR_DEAL_NO FROM "+m_schema_name+".DN_TRN_REPO_SETTLEMENT B WHERE B.APP_STATUS<>'CANCEL') AND "+
								 " A.DEAL_NO NOT IN (SELECT /*+INDEX (DN_TRN_REPO_ROLLOVER  IDX_TRN_REPO_ROLLOVER)*/  C.PRIOR_DEAL_NO FROM "+m_schema_name+".DN_TRN_REPO_ROLLOVER C WHERE C.APP_STATUS<>'CANCEL') "+
	             //  " ORDER BY A.MATURITY_DATE DESC "+
									") B  "+
								
									" ORDER BY Maturity_Date ASC "+
									
									") P ) H  "+
									"WHERE H.NO>= "+ Start_Val+" AND H.NO<= "+End_Val+" "; 			
                               
																
	m_rfullrrhelp =" SELECT H.NO,H.Deal_Number,H.Borrower_Code,H.Lender_Code,TO_CHAR(H.Leg2_Value,'FM999G999G999G999G999G990D00')Leg2_Value,TO_CHAR(H.Maturity_Date,'DD-MON-YYYY')Maturity_Date,TO_CHAR(H.Value_Date,'DD-MON-YYYY')Value_Date ,H.On_Call FROM "+
                 " (  "+
	               " SELECT ROWNUM NO,P.Deal_Number,P.Borrower_Code,P.Lender_Code,P.Leg2_Value,P.Maturity_Date,P.Value_Date,P.On_Call FROM "+
                 " (SELECT Deal_Number,Borrower_Code,Lender_Code,Leg2_Value,Maturity_Date,Value_Date,On_Call FROM "+
	               " (SELECT "+
						 
	               " A.DEAL_NO Deal_Number, "+
					 			 " A.BORR_PORTFOLIO_CODE Borrower_Code, "+
								 " A.LEND_PORTFOLIO_CODE Lender_Code, "+
								 " A.LEG2_VALUE Leg2_Value,"+
								 " A.MATURITY_DATE Maturity_Date, "+
								 " A.EFF_VALDATE Value_Date, "+
								 " A.ON_CALL_DEAL On_Call "+
						 		 " FROM "+m_schema_name+".DN_TRN_REPO_ROLLOVER A,"+m_schema_name+".DN_REF_PORTFOLIO B ,"+m_schema_name+".DN_REF_PORTFOLIO B1 "+
								 " WHERE A.APP_STATUS='CONF' AND "+
								 "A.DEAL_NO LIKE '"+m_vector.elementAt(0)+"%'  AND "+
								// ""+m_schema_name+".DN_CHK_DEALNO_RF_RR(A.DEAL_NO)='N' "+
                  "B.BRANCH LIKE ("+m_schema_name+".DN_GET_USER_LOCATION('"+m_vector.elementAt(1)+"')) AND  (A.BORR_PORTFOLIO_CODE=B.PORTFOLIO_CODE ) AND  "+
                  "B1.BRANCH LIKE ("+m_schema_name+".DN_GET_USER_LOCATION('"+m_vector.elementAt(1)+"')) AND  (A.LEND_PORTFOLIO_CODE=B1.PORTFOLIO_CODE ) AND  "+

								" A.DEAL_NO NOT IN "+
								" (SELECT B.PRIOR_DEAL_NO FROM "+m_schema_name+".DN_TRN_REPO_SETTLEMENT B WHERE B.APP_STATUS<>'CANCEL')"+
								 " AND "+
								 " A.DEAL_NO NOT IN "+
						 		 " (SELECT /*+INDEX (DN_TRN_REPO_ROLLOVER  IDX_TRN_REPO_ROLLOVER)*/ C.PRIOR_DEAL_NO FROM "+m_schema_name+".DN_TRN_REPO_ROLLOVER C WHERE C.APP_STATUS<>'CANCEL')  "+
								 //" ORDER BY A.MATURITY_DATE DESC  "+
									") B "+
									" ORDER BY Maturity_Date ASC "+ 

									") P)H  "+
									"WHERE H.NO>= "+ Start_Val+" AND H.NO<= "+End_Val+" "; 			
	
	m_rfullrphelp =" SELECT H.NO,H.Deal_Number,H.Borrower_Code,H.Lender_Code,TO_CHAR(H.Leg2_Value,'FM999G999G999G999G999G990D00')Leg2_Value,TO_CHAR(H.Maturity_Date,'DD-MON-YYYY')Maturity_Date,TO_CHAR(H.Value_Date,'DD-MON-YYYY')Value_Date ,H.On_Call FROM "+
                 " (  "+
	               " SELECT ROWNUM NO ,P.Deal_Number,P.Borrower_Code,P.Lender_Code,P.Leg2_Value,P.Maturity_Date,P.Value_Date,P.On_Call FROM "+
                 " (SELECT Deal_Number,Borrower_Code,Lender_Code,Leg2_Value,Maturity_Date,Value_Date,On_Call FROM "+
	               " (SELECT "+
						 		
								 " A.DEAL_NO Deal_Number, "+
								 " A.BORR_PORTFOLIO_CODE Borrower_Code, "+
								 " A.LEND_PORTFOLIO_CODE Lender_Code, "+
								 " A.LEG2_VALUE Leg2_Value, "+
								 " A.MATURITY_DATE Maturity_Date, "+
								 " A.EFF_VALDATE Value_Date, "+
								 " 'N' On_Call"+
								 " FROM "+
								 " "+m_schema_name+".DN_TRN_REPO_PERIOD_INT A,"+m_schema_name+".DN_REF_PORTFOLIO B,"+m_schema_name+".DN_REF_PORTFOLIO B1  "+
								 " WHERE "+
								 " A.APP_STATUS='CONF' AND  "+
    						 "A.DEAL_NO LIKE '"+m_vector.elementAt(0)+"%'  AND "+
		  					// ""+m_schema_name+".DN_CHK_DEALNO_RF_RR(A.DEAL_NO)='N' "+
                  "B.BRANCH LIKE ("+m_schema_name+".DN_GET_USER_LOCATION('"+m_vector.elementAt(1)+"')) AND  (A.BORR_PORTFOLIO_CODE=B.PORTFOLIO_CODE) AND  "+
                  "B1.BRANCH LIKE ("+m_schema_name+".DN_GET_USER_LOCATION('"+m_vector.elementAt(1)+"')) AND  (A.LEND_PORTFOLIO_CODE=B1.PORTFOLIO_CODE ) AND  "+
									"A.DEAL_NO NOT IN "+
								 " (SELECT B.PRIOR_DEAL_NO FROM "+m_schema_name+".DN_TRN_REPO_SETTLEMENT B WHERE B.APP_STATUS<>'CANCEL') "+
								 " AND "+
								 " A.DEAL_NO NOT IN "+
								 " (SELECT /*+INDEX (DN_TRN_REPO_ROLLOVER  IDX_TRN_REPO_ROLLOVER)*/ C.PRIOR_DEAL_NO FROM "+m_schema_name+".DN_TRN_REPO_ROLLOVER C WHERE C.APP_STATUS<>'CANCEL') "+ 
								// " ORDER BY A.MATURITY_DATE DESC  "+
									") B  "+
									" ORDER BY  Maturity_Date ASC  "+
									") P)H  "+
									"WHERE H.NO>= "+ Start_Val+" AND H.NO<= "+End_Val+" "; 			
	
	
	// ****Do not Alter The Parts Below
		Ret_Object= (Object)Sql_Name;
		return Ret_Object;
	  
	} 
}
