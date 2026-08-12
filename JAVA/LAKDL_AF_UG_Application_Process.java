

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

//DEVELOPED BY MAHELA FOR OFSCL user guide on 24-05-2007

public class LAKDL_AF_UG_Application_Process extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			String Geographic_Areas_header="";
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			 
						out.println("<!doctype html public '-//w3c//dtd html 4.0 transitional//en'>");
						out.println("<html>");
						out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
						out.println("<head>");
						out.println("	<STYLE TYPE='text/css'> ");
						out.println("	a:link {color:black; text-decoration:none font-size: 12px; color: #ED0306;}");//
						out.println("	a:hover {color:red; text-decoration:underline font-size: 12px; color: #ED0306;} ");
						out.println("	a:active {color:blue; text-decoration:underline font-size: 12px; color: #ED0306;}");
						out.println(" </STYLE>");
						out.println("   <meta http-equiv='Content-Type' content='text/html; charset=iso-8859-1'>");
						out.println("   <meta name='Generator' content='Microsoft FrontPage 5.0'>");
						out.println("   <meta name='GENERATOR' content='Mozilla/4.7 [en] (Win98; I) [Netscape]'>");
						out.println("   <title>MIDDLE OFFICE</title>");
						out.println("</head>");
						out.println("<body text='#000000' bgcolor='#FFFFFF' link='#000000' vlink='#000000' alink='#000000'>");
						
						//-----------------Application Process User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						//add by madhawa 2009-11-26
						out.println("<p class=ug_headin><a NAME='Application_Process'></a><u>Application Process</u> ");
						
						Geographic_Areas_header="<p align='justify' class=rep-body>Similar to Inquiry section, Application Process section also you need to specify data that need to come in drop down menus and other fields that are in the Marketing module. You will also specify default values for different parameters and you will set the business process related to application processing.";
						out.println(Geographic_Areas_header);
						//end added by madhawa 2009-11-26

						//-----------------Transaction Types
						out.println("<p class=ug_headin><a NAME='Transaction_Types'></a><u>Transaction Types</u> ");
						out.println("<p align='justify' class=rep-body>Transactions types are the services you offer. Here you will have to specify services such as Leasing, Hire Purchase, Loans, etc. ");
						//----------------- Transaction Sub Types Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Transaction_Sub_Types'>");
						out.println("</a><u>Transaction Sub Types</u>");
						out.println(" ");
						out.println("<p class=rep-body>A transaction type can be too broad to define your service. In that instance you will have to specify sub types for transactions. For example, Finance Lease and Operating Lease come under the transaction type Leasing.");
												//----------------- Item Categories User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Item_Categories'>");
						out.println("</a><u>Item Categories </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>An Item Category is a type of financial asset for which you would like to provide financing. It can be a vehicle, machinery or any type of broad asset. ");
											//----------------- Item Sub Categories User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Item_Sub_Categories'>");
						out.println("</a><u>Item Sub Categories</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>An Item Sub Category is a subset of an Item Category. For a vehicle, a car, van or a lorry can be a sub category. Here you need to specify a previously created category and create sub categories. ");
						//----------------- Makes User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Makes'>");
						out.println("</a><u>Makes</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>A make of an asset is the most abstract description of that asset or a brand. Examples are Toyota, Nissan for vehicles and HP for servers.");
						
						out.println("<p align='justify' class=ug_headin><a NAME='Models'>");
						out.println("</a><u>Models</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>A model is a type of products under a brand or a make. You can specify a make and define models under a make here. An example is Sunny N16 for the make Nissan.");
						
						out.println("<p align='justify' class=ug_headin><a NAME='Application_Status'>");
						out.println("</a><u>Application Status</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Similar to Inquiry Stages screen in Inquiry section, this screen helps you to predefine stages of the application process that a user should enter in order to report the degree to which an application is processed.");
							
						out.println("<p align='justify' class=ug_headin><a NAME='Clients'>");
						out.println("</a><u>Clients</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Clients are your business customers. They can be individual or corporate clients. You have to specify that in Client Type field. To complete Client screen you need to enter personal details, contact details, business details, references, details related to creditworthiness, income status and details about family members. However, you will be allowed to keep some of the details blank at initial stages.");
						
						
						out.println("<p align='justify' class=ug_headin><a NAME='Client_Groups'>");
						out.println("</a><u>Client Groups</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Client Groups are necessary when a number of entities appear as one single entity. For example, if multiple branches of a company obtain leasing facility from you, and the head office settles payments on behalf of each branch, you can consider each branch as a client and the head office as the Master Client. After entering details about branches in Clients screen, you can create the group and have details of the head office as the Master client with the use of this screen.");
						
						out.println("<p align='justify' class=ug_headin><a NAME='Guarantors'>");
						out.println("</a><u>Guarantors</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Guarantors are individuals or companies that would guarantee repayment of a facility, using their own assets if necessary. Required details are similar to those which are required in the Client screen.");
						
						
						out.println("<p align='justify' class=ug_headin><a NAME='Engine_Capacities'>");
						out.println("</a><u>Engine Capacities</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This screen helps you to predefine engine capacities to set to vehicles. This feature, which is commonly used in vehicle financing, appears automatically at the selection of a vehicle when required; therefore you do not need to enter it over and over again for the same type of vehicles.");
						
						out.println("<p align='justify' class=ug_headin><a NAME='Sub_Models'>");
						out.println("</a><u>Sub Models</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>You can have multiple products under one module with the use of sub models. In the case of Nissan Sunny, Nissan is the make and Sunny is the model. Under Nissan Sunny, there are multiple products such as FB14, FB15, and N16 and so on. You can define them in this screen by specifying the sub category, tax rate and engine capacity, etc.");

						out.println("<p align='justify' class=ug_headin><a NAME='Condition_of_Assets'>");
						out.println("</a><u>Condition of Assets</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>In this screen you can specify common situations of an asset that will be useful to make decisions related to performance of the asset. For a vehicle, two of many conditions can be brand new and reconditioned.");
						
						out.println("<p align='justify' class=ug_headin><a NAME='Fuel_Types'>");
						out.println("</a><u>Fuel Types</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Fuel types are the type of fuel used in the asset. Most common fuel types for a vehicle are Petrol, Diesel and LPG. Specify all fuel types of financial assets that you deal with.");
						
						out.println("<p align='justify' class=ug_headin><a NAME='Options'>");
						out.println("</a><u>Options</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Options are features of an asset. You can define options such as fog lights, dual air bags, spoiler and so on for a vehicle.  You will be able to map those features with assets at a later stage.");
						
						out.println("<p align='justify' class=ug_headin><a NAME='Mileage'>");
						out.println("</a><u>Mileage</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Mileage is the number of kilometers an asset can run with one liter of fuel. For a sub model in a particular condition, within a given number of kilometers of usage, there should be a mileage. You can specify different mileages for different usage ranges. This is applicable when vehicles are leased/hired in your business.");
						
						out.println("<p align='justify' class=ug_headin><a NAME='Asset_Usage_Type'>");
						out.println("</a><u>Asset Usage Type</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Usage type is broadly what the asset is used for. This screen lets you specify the usage of the asset. Most common types are Personal use and Commercial use.");

						
						out.println("<p align='justify' class=ug_headin><a NAME='Documents_Required'>");
						out.println("</a><u>Documents Required</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>You can specify documents that are required when servicing a client here. Documents are of two types as Client related and Asset related. After defining those documents you can make them applicable for each facility and status of them, whether you obtain or pending, can be updated. ");
						
						
					
						out.println("<p align='justify' class=ug_headin><a NAME='Documents_Applicable_to_Client_Asset'>");
						out.println("</a><u>Documents Applicable to Client/Asset</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This screen lets you map documents with the product, i.e. the service you offer. After specifying the type of document (client type or asset type), for each type of client (individual, partnership, public, etc.) you can specify the applicable document from the total document list for a specific product. ");
						
						//-----------------Vendors
						out.println("<p align='justify' class=ug_headin><a NAME='Vendors'>");
						out.println("</a><u>Vendors</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Vendors are sellers of asset that you finance. Here you can obtain a group of vendors for an asset so that you can easily pick a vendor from the list when required. You can define multiple branches for the same vendor, located even in different countries. ");
						
						//--------------Garages
						out.println("<p align='justify' class=ug_headin><a NAME='Garages'>");
						out.println("</a><u>Garages</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This screen is to enter garages and repair locations of your assets.  ");
						
						//---------------Valuation Fields
						out.println("<p align='justify' class=ug_headin><a NAME='Valuation_Fields'>");
						out.println("</a><u>Valuation Fields</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This screen lets you define parameters when assessing the value of assets that are to be financed. Valuation Officers will have to set values to these fields of valuation, i.e. parameters. ");
						//-------------Applicable Fields in Valuation
						out.println("<p align='justify' class=ug_headin><a NAME='Applicable_Fields_in_Valuation'>");
						out.println("</a><u>Applicable Fields in Valuation</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Here you can map valuation fields with item categories. In other words, you can specify the fields that are applicable for valuation for each type of asset, namely equipment, vehicle and so on.");
						//----------------Valuers
						out.println("<p align='justify' class=ug_headin><a NAME='Valuers'>");
						out.println("</a><u>Valuers</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>You can maintain a list of valuers of assets along with their contact and personal details as well as charges. This screen helps you to feed data into the system.");

						out.println("<p align='justify' class=ug_headin><a NAME='Valuers'>");
						out.println("</a><u>Valuers</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>You can maintain a list of valuers of assets along with their contact and personal details as well as charges. This screen helps you to feed data into the system.");
						
						out.println("<p align='justify' class=ug_headin><a NAME='Income_Expense_Types'>");
						out.println("</a><u>Income/Expense Types</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This screen assists you to generate income and expense accounts that are related to application process. ");
						
						out.println("<p align='justify' class=ug_headin><a NAME='CRIB_Number'>");
						out.println("</a><u>CRIB Number</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>For your clients you can generate a reference number for identification purposes. When uploading information to the Credit Information Bureau (CRIB) you can refer to your record by using this unique number.");
						
						out.println("<p align='justify' class=ug_headin><a NAME='Screen_Order'>");
						out.println("</a><u>Screen Order</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This enables the user to select the screen order by which the process order can be defined");
						//------------Approval of Reference Info
						out.println("<p align='justify' class=ug_headin><a NAME='Approval_of_Reference_Info'>");
						out.println("</a><u>Approval of Reference Info</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>When the marketing teams or the front office enters an inquiry, they are allowed to enter names for asset makes, models and sub models, which are not predefined. At that stage, you can proceed without verifying such terms, but they should be approved at later stage. This screen lets a senior user to approve creation of makes and models. If you approve they will be registered as new names for that particular type and if you disapprove, you will need to map the entered name with an existing name, assuming the difference is due to a spelling mistake.");
						//---------------Conditions
						out.println("<p align='justify' class=ug_headin><a NAME='Conditions'>");
						out.println("</a><u>Conditions</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>You can specify conditions (rules) that should be made applicable at different stages of the business process. You define such conditions here and attach them to relevant stages of your business process. For example, you can make approval of the Purchase Requisition compulsory when generating a Purchase Order.");
						//---------------Transactions - Monthly Targets
						out.println("<p align='justify' class=ug_headin><a NAME='Transactions_Monthly_Targets'>");
						out.println("</a><u>Transactions - Monthly Targets</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Here you can specify what percentage should be targeted to be collected in each month. For all 12 months of the year, collection targets should be set once a year. You need to specify what percentage should be collected out of the total outstanding due. If 60% of January collections should be achieved, for February, the total outstanding is February month's collection plus the remaining 40% of January collections. From that, say, 60% should be collected.");
						//-----------------Transactions - Business Volume Setup
						out.println("<p align='justify' class=ug_headin><a NAME='Transactions_Business_Volume_Setup'>");
						out.println("</a><u>Transactions - Business Volume Setup</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>You can set monthly sales (/business) targets for the head office and branches. You also can specify the expected (planned) breakdown of sales volume for each transaction type.");

						out.println("<p align='justify' class=ug_headin><a NAME='Transactions_Rental_Target_Setup'>");
						out.println("</a><u>Transactions - Rental Target Setup</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This screen is for you to plan collection targets. You can select a Collection Officer or a Finance number to proceed. If you select a Collection Officer, all facilities assigned to a collection officer will appear. Then you can select a facility and set up targets by adding arrears targets to the current collection amount each month. By selecting a Finance number directly, you will prepare the same collection target plan.");
						//-------------Transactions - ODI Target Setup
						out.println("<p align='justify' class=ug_headin><a NAME='Transactions_ODI_Target_Setup'>");
						out.println("</a><u>Transactions - ODI Target Setup</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>You can preset overdue interest rates for each month of the year with the use of this screen. ");

						
						out.println("</body>");
						out.println("</html>");
						out.flush();
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
