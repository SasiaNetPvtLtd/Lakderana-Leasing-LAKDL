//This JS file particularly used in OFSCL Client Screen & Guarantor Screen

//All variable declarations
//Individual

/*//added by nuwan de silva on 
var m_count_gur     ='' ;   
var m_count_as      ='' ;   
var m_count_pr      ='' ;   
var m_count_pi      ='' ;   
var m_count_vl      ='' ;   
var m_count_client  =''; 
var m_client_status ='';
*/
var b_client_status=0; //added by nuwan de silva for the client creation on 07-08-07
var m_client_code_value=''; //added by nuwan de silva for the client creation  07-08-07 
var m_client_type_value=''; //added by nuwan de silva for the client creation  07-08-07
var m_client_or_gur=''; //added by nuwan de silva for the client creation  07-08-07
var b_new_data=0;
var lineno=0;
var lineno_bank=0;
var lineno_credit=0;
var lineno_nonrelated=0;
var lineno_family=0;
var lineno_property=0;
var b_flag_validate=0;

var arr_size=0;
var arr_size_family=0;
var arr_size_property=0;
var arr_size_bank=0;
var arr_size_credit=0;
var arr_size_nonrelated=0;

//Corporate
var lineno_company_dir = 0;
var lineno_subsidiaries = 0;
var lineno_customer = 0;
var lineno_credit_c = 0;
var lineno_ba = 0;

var arr_size_company = 0;
var arr_size_subsidiaries = 0;
var arr_size_customer = 0;
var arr_size_credit_c=0;
var arr_size_ba=0;
var arr_size_auditors=0;
var lineno_auditors=0;


//Income Expense
var arr_size_income = 0;
var income_tot=0; 

var client_contract_count=0; 
// All Array declarations used in
// Client Screen	- OFSCL

//Auditors
var array_name_aud = new Array();
var array_add_aud = new Array();
var array_add2_aud = new Array();
var array_rela_aud = new Array();
var array_ref_aud = new Array();
var array_tel_aud = new Array();
var array_fax_aud = new Array();

//Family Member Arrays
var array_member_f = new Array();
var array_name_f = new Array();
var array_address_f = new Array();
var array_age_f = new Array();
var array_telno_f = new Array();
var array_moble_f = new Array();


//Property Arrays
var array_name_p = new Array();
var array_loc_p = new Array();
var array_make_p = new Array();
var array_model_p = new Array();
var array_company_p = new Array();
var array_val_p = new Array();
var array_reg_no = new Array();

//BA arrays
var array_cat = new Array();
var array_activity = new Array();

//Employment Arrays
var array_organization = new Array();
var array_telno = new Array();
var array_from_date_mm = new Array();
var array_from_date_dd = new Array();
var array_from_date_yy = new Array();
var array_to_date_mm = new Array();
var array_to_date_dd = new Array();
var array_to_date_yy = new Array();
var array_designation = new Array();

// Bank Arrays
var array_banker = new Array();
var array_banker_name = new Array();


var array_branch = new Array();
var array_branch_name = new Array();
var array_acc_no = new Array();
var array_reference = new Array();
var array_telno_bank = new Array();
var array_fax_bank = new Array();
var array_relationship_b = new Array();

//Credit Facilities arrays
var array_type = new Array();
var array_institute = new Array();
var array_contact_person = new Array();
var array_contract_no = new Array();
var array_security = new Array();
var array_app_amount = new Array();
var array_bal_amount = new Array();
var array_months = new Array();

//Non related referee arrays
var array_name = new Array();
var array_relationship = new Array();
var array_period = new Array();
var array_design = new Array();
var array_telno_home = new Array();
var array_telno_office = new Array();
var array_mobileno = new Array();


//Company Director arrays
var array_name_dir = new Array();
var array_add_dir = new Array(); //added by nuwan de silva on 07-09-07 for ref no:851
var array_nic_no_dir = new Array();
var array_stake = new Array();
var array_no_shares = new Array();
var array_value = new Array();
var array_position = new Array();

//Subsidiary arrays
var array_name_sub = new Array();
var array_stake_sub = new Array();
var array_value_sub = new Array();
var array_telno_sub = new Array();
var array_officer_sub = new Array();
var array_ba_sub = new Array();

//Customer arrays
var array_name_cus = new Array();
var array_type_cus = new Array();
var array_add_cus = new Array();
var array_relation_cus = new Array();
var array_contact_person_cus = new Array();
var array_telno_cus = new Array();

//Corporate Credit Facilities arrays
var array_institute_c = new Array();
var array_contact_person_c = new Array();
var array_type_c = new Array();
var array_equip_c = new Array();
var array_app_amount_c = new Array();
var array_rental = new Array();
var array_months_c = new Array();
var array_bal_amount_c = new Array();

//OFSCL - Client Screen 
//Corporate Credit Facilities arrays

var array_institute_c = new Array();
var array_contact_person_c = new Array();
var array_type_c = new Array();
var array_equip_c = new Array();
var array_app_amount_c = new Array();
var array_rental = new Array();
var array_months_c = new Array();
var array_bal_amount_c = new Array();



//_____________Added by nuwan de silva  on 23-11-2007 ________
function validate_nic_no_status(m_objval)
{  
    m_length = m_objval.toString().length;
    if(m_objval != '') {
        if(m_length<10)
        { 
            //alert(' NIC length should be 10'); 
            //object.focus(); 
            return false; 
        } 
        else 
        {  
            for(var i = 0; i<m_length; i++) 
            { 
                m_char = m_objval.charAt(i); 
                if(i==9) 
                {    
                    if(m_char != 'v' && m_char != 'V' && m_char != 'x' && m_char != 'X') 
                    { 
                        //   alert('NIC final character should be X or V '); 
                        //    object.focus(); 
                        return false; 
                    }
                }
                else if(i>=0 && i<=8) 
                { 
                    if(isNaN(m_char)) 
                    { 
                        i++; 
                        //   alert('NIC contains an invalid character at position '+i+' ?');
                        // object.focus();
                        return false;
                    }
                }
            }
        }	
        return true;		
    } else 	
    return true;	
}
//_______________________ end by nuwan de silva _______________________________

//_______________________  Added by Chandana  _________________________________
function check_blanks(obj){
    
    m_val_length = obj.value.toString().length;
    var k=0;		
    for(var i = 0; i<m_val_length; i++) 
    { 
        m_char = obj.value.charAt(i); 	
        if(m_char==' '){
            k=i+1;
            
            // alert(i);
        }
    }
    
    if(k>0){
        alert('An invalid space character at position '+k+' ...');
        obj.value="";
        obj.focus();	
    }
    
}
//_____________________ End by Chandana _______________________________

//_____________________ added by nuwan de silva ________________________________

function validate_nic_status_dob(m_objval,m_gender,m_date_of_bitrh)
{  
    var m_num_days = 0;		
    //m_gender = document.Form1.TXT_GENDER.value;	
    //m_objval = object.value;
    m_year = m_objval.substring(0,2)
    m_days = parseFloat(m_objval.substring(2,5));
    // if(document.Form1.TXT_DATE_OF_BIRTH_DD.value!='' && document.Form1.TXT_DATE_OF_BIRTH_MM.value!='' && document.Form1.TXT_DATE_OF_BIRTH_YY.value!=''  ) {
    if (m_date_of_bitrh!=''){
        m_date  = parseFloat(m_date_of_bitrh.substring(0,2))
        m_month =	parseFloat(m_date_of_bitrh.substring(3,5))
        m_yyyy	=	m_date_of_bitrh.substring(6,10)
        
        //m_yyyy = document.Form1.TXT_DATE_OF_BIRTH_YY.value; 	
        //m_month = parseFloat(document.Form1.TXT_DATE_OF_BIRTH_MM.value); 		
        //m_date = parseFloat(document.Form1.TXT_DATE_OF_BIRTH_DD.value); 			
        
        m_yy = m_yyyy.substring(2,4)
        
        
        
        if( m_year != 	m_yy) { 
            //alert(' Invalid NIC no  ') ;	
            // object.value='';	 	
            // object.focus();	 			
            return false;	 
            
        } 		
        else if( m_month == 1	){ 	
            
            if( m_gender == 'M' && m_days != m_date ){  	
                //alert(' Invalid NIC no  ') ;	
                // object.value='';	 	
                // object.focus();	 			
                return false;	 
            } 	
            else if( m_gender == 'F' && m_days != (m_date+500) ){  	
                //alert(' Invalid NIC no  ') ;	
                // object.value='';	 	
                // object.focus();	 			
                return false;	
            } 		
            else{  	
                return true;	
            } 			 
        } 	
        else if( m_month == 2	){ 		
            
            m_num_days = 31+m_date;			
            if( m_gender == 'M' && m_days != m_num_days ){  	
                //alert(' Invalid NIC no  ') ;	
                // object.value='';	 	
                // object.focus();	 			
                return false;		
            } 		
            else if( m_gender == 'F' && m_days != (m_num_days+500) ){  	
                //alert(' Invalid NIC no  ') ;	
                // object.value='';	 	
                // object.focus();	 			
                return false;	
            } 			
            else{  	
                return true;	
            } 			 	
        } 		
        else if( m_month == 3	){ 		
            m_num_days = 60+m_date;			
            if( m_gender == 'M' && m_days != m_num_days ){  	
                //alert(' Invalid NIC no  ') ;	
                //object.value='';	 	
                //object.focus();	 			
                return false;	
            } 		
            else if( m_gender == 'F' && m_days != (m_num_days+500) ){  	
                //alert(' Invalid NIC no  ') ;	
                //object.value='';	 	
                //object.focus();	 		
                return false;	
            } 			
            else{  	
                return true;	
            } 			 	
        } 			
        else if( m_month == 4	){ 		
            
            m_num_days = 91+m_date;				
            if( m_gender == 'M' && m_days != m_num_days ){  	
                //alert(' Invalid NIC no  ') ;	
                //object.value='';	 	
                // object.focus();	 		
                return false;	
            } 		
            else if( m_gender == 'F' && m_days != (m_num_days+500) ){  	
                //alert(' Invalid NIC no  ') ;	
                // object.value='';	 	
                // object.focus();	 			
                return false;	
            } 			
            else{  	
                return true;	
            } 			 	
        } 		
        else if( m_month == 5	){ 	
            
            m_num_days = 121+m_date;				
            if( m_gender == 'M' && m_days != m_num_days ){  	
                //	alert(' Invalid NIC no  ') ;	
                // object.value='';	 	
                // object.focus();	 			
                return false;	
            } 		
            else if( m_gender == 'F' && m_days != (m_num_days+500) ){  	
                //alert(' Invalid NIC no  ') ;	
                // object.value='';	 	
                //object.focus();	 		
                return false;	
            } 			
            else{  	
                return true;	
            } 			 	
        } 		
        else if( m_month == 6	){ 	
            
            m_num_days = 152+m_date;				
            if( m_gender == 'M' && m_days != m_num_days ){  	
                //alert(' Invalid NIC no  ') ;	
                //object.value='';	 	
                //object.focus();	 			
                return false;	
            } 		
            else if( m_gender == 'F' && m_days != (m_num_days+500) ){  	
                //alert(' Invalid NIC no  ') ;	
                //object.value='';	 	
                //object.focus();	 			
                return false;	
            } 			
            else{  	
                return true;	
            } 			 	
        } 		
        else if( m_month == 7	){ 	
            
            m_num_days = 182+m_date;				
            if( m_gender == 'M' && m_days != m_num_days ){  	
                //alert(' Invalid NIC no  ') ;	
                //object.value='';	 	
                //object.focus();	 			
                return false;	
            } 		
            else if( m_gender == 'F' && m_days != (m_num_days+500) ){  	
                //alert(' Invalid NIC no  ') ;	
                //object.value='';	 	
                //object.focus();	 		
                return false;	
            } 			
            else{  	
                return true;	
            } 			 	
        } 		
        else if( m_month == 8	){ 
            
            m_num_days = 213+m_date;				
            if( m_gender == 'M' && m_days != m_num_days ){  	
                //alert(' Invalid NIC no  ') ;	
                //object.value='';	 	
                //object.focus();	 		
                return false;	
            } 		
            else if( m_gender == 'F' && m_days != (m_num_days+500) ){  	
                //alert(' Invalid NIC no  ') ;	
                //object.value='';	 	
                //object.focus();	 			
                return false;	
            } 			
            else{  	
                return true;	
            } 			 	
        } 		
        else if( m_month == 9	){ 
            m_num_days = 244+m_date;	
            
            if( m_gender == 'M' && m_days != m_num_days ){  	
                //alert(' Invalid NIC no  ') ;	
                //object.value='';	 	
                //object.focus();	 		
                return false;	
            } 		
            else if( m_gender == 'F' && m_days != (m_num_days+500) ){  	
                //alert(' Invalid NIC no  ') ;	
                //object.value='';	 	
                //object.focus();	 			
                return false;	
            } 			
            else{  	
                return true;	
            } 			 	
        } 		
        else if( m_month == 10	){ 
            
            m_num_days = 274+m_date;				
            if( m_gender == 'M' && m_days != m_num_days ){  	
                //alert(' Invalid NIC no  ') ;	
                //object.value='';	 	
                //object.focus();	 		
                return false;	
            } 		
            else if( m_gender == 'F' && m_days != (m_num_days+500) ){  	
                //alert(' Invalid NIC no  ') ;	
                // object.value='';	 	
                // object.focus();	 		
                return false;	
            } 			
            else{  	
                return true;	
            } 			 	
        } 		
        else if( m_month == 11	){ 	
            
            m_num_days = 305+m_date;				
            if( m_gender == 'M' && m_days != m_num_days ){  	
                //alert(' Invalid NIC no  ') ;	
                //object.value='';	 	
                // object.focus();	 			
                return false;	
            } 		
            else if( m_gender == 'F' && m_days != (m_num_days+500) ){  	
                //alert(' Invalid NIC no  ') ;	
                //object.value='';	 	
                //object.focus();	 			
                return false;	
            } 			
            else{  	
                return true;	
            } 			 	
        } 			
        else if( m_month == 12	){ 		
            
            m_num_days = 335+m_date;				
            if( m_gender == 'M' && m_days != m_num_days ){  	
                //alert(' Invalid NIC no  ') ;	
                //object.value='';	 	
                //object.focus();	 		
                return false;	
            } 		
            else if( m_gender == 'F' && m_days != (m_num_days+500) ){  	
                //alert(' Invalid NIC no  ') ;	
                //object.value='';	 	
                //object.focus();	 			
                return false;	
            } 			
            else{  	
                return true;	
            } 			 	
        } 			
    } 	
    else{  	
        return true;	
    } 			 		
} 
// ____________________end by nuwan de silva __________________________________


// __________ added by nuwan de silva on 23-11-2007 ___________________________________________________

function validate_NIC_Return(m_objval,m_gender,m_date_of_bitrh){ 
    if(m_objval != '') {  	
        if(validate_nic_no_status(m_objval) && validate_nic_status_dob(m_objval,m_gender,m_date_of_bitrh)){	
            return true ; 
        } 		
        else {  	
            return false ; 
        } 		
    } 		
} 
//____________ End by nuwan de silva on 23-11-2007 ______________________________________________________		

//OFSCL - Client Screen 
//Added by Mahela for NIC validation  	
function val_nic1(object)
{  
    m_objval = object.value;
    m_length = m_objval.toString().length;
    if(m_objval != '') {
        if(m_length<10)
        { 
            alert(' NIC length should be 10'); 
            object.focus(); 
            return false; 
        } 
        else 
        {  
            for(var i = 0; i<m_length; i++) 
            { 
                m_char = m_objval.charAt(i); 
                if(i==9) 
                {    
                    if(m_char != 'v' && m_char != 'V' && m_char != 'x' && m_char != 'X') 
                    { 
                        alert('NIC final character should be X or V '); 
                        object.focus(); 
                        return false; 
                    }
                }
                else if(i>=0 && i<=8) 
                { 
                    if(isNaN(m_char)) 
                    { 
                        i++; 
                        alert('NIC contains an invalid character at position '+i+' ?');
                        object.focus();
                        return false;
                    }
                }
            }
        }	
        return true;		
    } else 	
    return true;	
}


// Modified By Samitha Kulatilaka On 2011-12-07
// commented by udara 10-12-2013	
/*
function validate_NIC(object){ 
    
    var selectedNationalityCode = document.forms[0].elements['TXT_NATIONALITY'].value;
    
    if ((selectedNationalityCode == 'SRILANKAN') && (object.value != '')) {  	
        if(val_nic1(object) && val_nic2(object)){	
            return true ; 
        } 		
        else {  	
            //return false ; //comment by nuwan de silva for the entering old data 06-06-07 (to the requst) 	
            return true ;  
        } 		
    } 		
    else if (selectedNationalityCode != 'SRILANKAN') {  	
        return true;
    }
} 
*/
	
// added by udara 10-12-2013
	function validate_NIC(object){ 
		
		var selectedNationalityCode = document.forms[0].elements['TXT_NATIONALITY'].value;
		
		//if ((selectedNationalityCode == 'SRILANKAN') && (object.value != '')) {  	// commented by udara 14-11-2013
		if (object.value != '') { 
			if(val_nic1(object) && val_nic2(object)){	
				return true ; 
			} 		
			else {  	
				//return false ; //comment by nuwan de silva for the entering old data 06-06-07 (to the requst) 	
				return true ;  
			} 		
		} 
		// commented by udara 14-11-2013
		//else if (selectedNationalityCode != 'SRILANKAN') {  	
		//    return true;
		//}
		
		// udara 14-11-2013
		else{
			return true; 
		}
		
		
	} 
	// end by udara 10-12-2013


//OFSCL - Client Screen 
//Added by Mahela for NIC validation  	
function val_nic2(object)
{  
    var m_num_days = 0;		
    m_gender = document.Form1.TXT_GENDER.value;	
    m_objval = object.value;
    m_year = m_objval.substring(0,2)
    m_days = parseFloat(m_objval.substring(2,5));
    if(document.Form1.TXT_DATE_OF_BIRTH_DD.value!='' && document.Form1.TXT_DATE_OF_BIRTH_MM.value!='' && document.Form1.TXT_DATE_OF_BIRTH_YY.value!=''  ) {
        m_yyyy = document.Form1.TXT_DATE_OF_BIRTH_YY.value; 	
        m_month = parseFloat(document.Form1.TXT_DATE_OF_BIRTH_MM.value); 		
        m_date = parseFloat(document.Form1.TXT_DATE_OF_BIRTH_DD.value); 			
        m_yy = m_yyyy.substring(2,4)
        
        
        if( m_year != 	m_yy) { 
            alert(' Invalid NIC no  ') ;	
            // object.value='';	 	
            // object.focus();	 			
        } 		
        else if( m_month == 1	){ 	
            
            if( m_gender == 'M' && m_days != m_date ){  	
                alert(' Invalid NIC no  ') ;	
                // object.value='';	 	
                // object.focus();	 			
                return false;	 
            } 	
            else if( m_gender == 'F' && m_days != (m_date+500) ){  	
                alert(' Invalid NIC no  ') ;	
                // object.value='';	 	
                // object.focus();	 			
                return false;	
            } 		
            else{  	
                return true;	
            } 			 
        } 	
        else if( m_month == 2	){ 		
            
            m_num_days = 31+m_date;			
            if( m_gender == 'M' && m_days != m_num_days ){  	
                alert(' Invalid NIC no  ') ;	
                // object.value='';	 	
                // object.focus();	 			
                return false;		
            } 		
            else if( m_gender == 'F' && m_days != (m_num_days+500) ){  	
                alert(' Invalid NIC no  ') ;	
                // object.value='';	 	
                // object.focus();	 			
                return false;	
            } 			
            else{  	
                return true;	
            } 			 	
        } 		
        else if( m_month == 3	){ 		
            m_num_days = 60+m_date;			
            if( m_gender == 'M' && m_days != m_num_days ){  	
                alert(' Invalid NIC no  ') ;	
                //object.value='';	 	
                //object.focus();	 			
                return false;	
            } 		
            else if( m_gender == 'F' && m_days != (m_num_days+500) ){  	
                alert(' Invalid NIC no  ') ;	
                //object.value='';	 	
                //object.focus();	 		
                return false;	
            } 			
            else{  	
                return true;	
            } 			 	
        } 			
        else if( m_month == 4	){ 		
            
            m_num_days = 91+m_date;				
            if( m_gender == 'M' && m_days != m_num_days ){  	
                alert(' Invalid NIC no  ') ;	
                //object.value='';	 	
                // object.focus();	 		
                return false;	
            } 		
            else if( m_gender == 'F' && m_days != (m_num_days+500) ){  	
                alert(' Invalid NIC no  ') ;	
                // object.value='';	 	
                // object.focus();	 			
                return false;	
            } 			
            else{  	
                return true;	
            } 			 	
        } 		
        else if( m_month == 5	){ 	
            
            m_num_days = 121+m_date;				
            if( m_gender == 'M' && m_days != m_num_days ){  	
                alert(' Invalid NIC no  ') ;	
                // object.value='';	 	
                // object.focus();	 			
                return false;	
            } 		
            else if( m_gender == 'F' && m_days != (m_num_days+500) ){  	
                alert(' Invalid NIC no  ') ;	
                // object.value='';	 	
                //object.focus();	 		
                return false;	
            } 			
            else{  	
                return true;	
            } 			 	
        } 		
        else if( m_month == 6	){ 	
            
            m_num_days = 152+m_date;				
            if( m_gender == 'M' && m_days != m_num_days ){  	
                alert(' Invalid NIC no  ') ;	
                //object.value='';	 	
                //object.focus();	 			
                return false;	
            } 		
            else if( m_gender == 'F' && m_days != (m_num_days+500) ){  	
                alert(' Invalid NIC no  ') ;	
                //object.value='';	 	
                //object.focus();	 			
                return false;	
            } 			
            else{  	
                return true;	
            } 			 	
        } 		
        else if( m_month == 7	){ 	
            
            m_num_days = 182+m_date;				
            if( m_gender == 'M' && m_days != m_num_days ){  	
                alert(' Invalid NIC no  ') ;	
                //object.value='';	 	
                //object.focus();	 			
                return false;	
            } 		
            else if( m_gender == 'F' && m_days != (m_num_days+500) ){  	
                alert(' Invalid NIC no  ') ;	
                //object.value='';	 	
                //object.focus();	 		
                return false;	
            } 			
            else{  	
                return true;	
            } 			 	
        } 		
        else if( m_month == 8	){ 
            
            m_num_days = 213+m_date;				
            if( m_gender == 'M' && m_days != m_num_days ){  	
                alert(' Invalid NIC no  ') ;	
                //object.value='';	 	
                //object.focus();	 		
                return false;	
            } 		
            else if( m_gender == 'F' && m_days != (m_num_days+500) ){  	
                alert(' Invalid NIC no  ') ;	
                //object.value='';	 	
                //object.focus();	 			
                return false;	
            } 			
            else{  	
                return true;	
            } 			 	
        } 		
        else if( m_month == 9	){ 
            m_num_days = 244+m_date;	
            
            if( m_gender == 'M' && m_days != m_num_days ){  	
                alert(' Invalid NIC no  ') ;	
                //object.value='';	 	
                //object.focus();	 		
                return false;	
            } 		
            else if( m_gender == 'F' && m_days != (m_num_days+500) ){  	
                alert(' Invalid NIC no  ') ;	
                //object.value='';	 	
                //object.focus();	 			
                return false;	
            } 			
            else{  	
                return true;	
            } 			 	
        } 		
        else if( m_month == 10	){ 
            
            m_num_days = 274+m_date;				
            if( m_gender == 'M' && m_days != m_num_days ){  	
                alert(' Invalid NIC no  ') ;	
                //object.value='';	 	
                //object.focus();	 		
                return false;	
            } 		
            else if( m_gender == 'F' && m_days != (m_num_days+500) ){  	
                alert(' Invalid NIC no  ') ;	
                // object.value='';	 	
                // object.focus();	 		
                return false;	
            } 			
            else{  	
                return true;	
            } 			 	
        } 		
        else if( m_month == 11	){ 	
            
            m_num_days = 305+m_date;				
            if( m_gender == 'M' && m_days != m_num_days ){  	
                alert(' Invalid NIC no  ') ;	
                //object.value='';	 	
                // object.focus();	 			
                return false;	
            } 		
            else if( m_gender == 'F' && m_days != (m_num_days+500) ){  	
                alert(' Invalid NIC no  ') ;	
                //object.value='';	 	
                //object.focus();	 			
                return false;	
            } 			
            else{  	
                return true;	
            } 			 	
        } 			
        else if( m_month == 12	){ 		
            
            m_num_days = 335+m_date;				
            if( m_gender == 'M' && m_days != m_num_days ){  	
                alert(' Invalid NIC no  ') ;	
                //object.value='';	 	
                //object.focus();	 		
                return false;	
            } 		
            else if( m_gender == 'F' && m_days != (m_num_days+500) ){  	
                alert(' Invalid NIC no  ') ;	
                //object.value='';	 	
                //object.focus();	 			
                return false;	
            } 			
            else{  	
                return true;	
            } 			 	
        } 			
    } 	
    else{  	
        return true;	
    } 			 		
} 




function clear_fields(){ 
    if(document.Form1.hid_help_type.value=='99' || document.Form1.hid_help_type.value=='999' ) {
        document.Form1.TXT_CLIENT_CODE.value =''; 
        document.Form1.TXT_CLIENT_CODE.focus(); 
    }		 
    else if(document.Form1.hid_help_type.value=='1') {
        clear_bank_code(document.Form1.hid_lineno.value); 
    }		 
    else if(document.Form1.hid_help_type.value=='2') {
        clear_branch_code(document.Form1.hid_lineno.value); 
    }		 
    else if(document.Form1.hid_help_type.value=='3') {
        document.Form1.TXT_CITY_CODE.value =''; 
        document.Form1.TXT_CITY_CODE.focus(); 
        document.Form1.TXT_CITY_DESC.value =''; 
    }		 
    else if(document.Form1.hid_help_type.value=='76') {
        document.Form1.TXT_POSTAL_CODE.value =''; 
        document.Form1.TXT_POSTAL_CODE.focus(); 
        document.Form1.TXT_POSTAL_DESC.value =''; 
    }		
    else if(document.Form1.hid_help_type.value=='77') {
        document.Form1.TXT_INQUARY_NO.value =''; 
        document.Form1.TXT_INQUARY_NO.focus(); 
    }		
    
    else if(document.Form1.hid_help_type.value=='101') {
        document.Form1.TXT_BUS_SECT.value =''; 
        document.Form1.TXT_BUS_SECT_DES.value ='';  //added by nuwan de silva on 01-10-07
        document.Form1.TXT_BUS_SECT.focus(); 
    }		
    else if(document.Form1.hid_help_type.value=='102') {
        document.Form1.TXT_BUS_SECT_MAIN.value =''; 
        document.Form1.TXT_BUS_SECT_DES_MAIN.value ='';  //added by nuwan de silva on 01-10-07
        document.Form1.TXT_BUS_SECT_MAIN.focus(); 
    }	
    
    
}		 

//delanjali(23/11/2006)
function load_lock_1(val){	
    var m_client_type=val;
    
    if (m_client_type==''){
        m_client_type='I';
    }
    
    return m_client_type;
}


function help_button_postal_code() {
    document.Form1.hid_help_type.value="76";
    m_sql ="m_help_TXT_POSTAL_CODE_sql"; 
    if(document.Form1.SCREEN_NAME.value=="NEW" ||document.Form1.SCREEN_NAME.value=="EDIT" || document.Form1.SCREEN_NAME.value=="DACT"){ 
        m_criteria = document.Form1.TXT_POSTAL_CODE.value+"@"+"Y@";
    } 
    else{
        m_criteria = document.Form1.TXT_POSTAL_CODE.value+"@"+"N@";
    } 
    HelpBox('1','10','2');
}

function assign_postal_code(val) {
    if(val=='-' || val=='null' || val=='NULL'  ){
        document.Form1.TXT_POSTAL_CODE.value='';
    }
    else{
        document.Form1.TXT_POSTAL_CODE.value=val;
    }
    
}

//Created by nuwan de silva 22-06-07 5.11pm

function assign_individual_rest_data(Obj) {
    
    
    if(oBj.valout[53]=='-' || oBj.valout[53]=='null' || oBj.valout[53]=='NULL'  ){
        document.Form1.TXT_CITY_DESC.value='';
    }
    
    else{
        document.Form1.TXT_CITY_DESC.value=oBj.valout[53];
    }
    if(oBj.valout[54]=='-' || oBj.valout[54]=='null' || oBj.valout[54]=='NULL'  ){
        document.Form1.TXT_POSTAL_DESC.value='';
    }
    
    else{
        document.Form1.TXT_POSTAL_DESC.value=oBj.valout[54];
    }
    
    document.Form1.TXT_BUS_SECT_DES_MAIN.value=oBj.valout[55];
    document.Form1.TXT_BUS_SECT_DES.value=oBj.valout[56]; 
    
}


function assign_corparate_rest_data(Obj) {
    
    if(oBj.valout[34]=='-' || oBj.valout[34]=='null' || oBj.valout[34]=='NULL'  ){
        document.Form1.TXT_BUS_SECT.value='';
    }
    
    else{
        document.Form1.TXT_BUS_SECT.value=oBj.valout[34]; 
        document.Form1.TXT_BUS_SECT_DES.value=oBj.valout[39];
    }
    if(oBj.valout[35]=='-' || oBj.valout[35]=='null' || oBj.valout[35]=='NULL'  ){
        document.Form1.TXT_BUS_SECT_MAIN.value='';
    }
    
    else{
        document.Form1.TXT_BUS_SECT_MAIN.value=oBj.valout[35];
        document.Form1.TXT_BUS_SECT_DES_MAIN.value=oBj.valout[38];
    }
    
    if(oBj.valout[36]=='-' || oBj.valout[36]=='null' || oBj.valout[36]=='NULL'  ){
        document.Form1.TXT_CITY_DESC.value='';
    }
    
    else{
        document.Form1.TXT_CITY_DESC.value=oBj.valout[36];
    }
    if(oBj.valout[37]=='-' || oBj.valout[37]=='null' || oBj.valout[37]=='NULL'  ){
        document.Form1.TXT_POSTAL_DESC.value='';
    }
    
    else{
        document.Form1.TXT_POSTAL_DESC.value=oBj.valout[37];
    }
    
    
}






function assign_postal_desc(val) {
    if(val=='-' || val=='null' || val=='NULL'  ){
        document.Form1.TXT_POSTAL_DESC.value='';
    }
    else{
        document.Form1.TXT_POSTAL_DESC.value=val;
    }
    
}


function assign_sect_desc(val){
    if(val=='-' || val=='null' || val=='NULL'  ){
        document.Form1.TXT_BUS_SECT_DES.value='';
    }
    else{
        document.Form1.TXT_BUS_SECT_DES_MAIN.value=val;
    }
}


function assign_sub_sect_desc(val){
    if(val=='-' || val=='null' || val=='NULL'  ){
        document.Form1.TXT_BUS_SECT_DES.value='';
    }
    else{
        document.Form1.TXT_BUS_SECT_DES.value=val;
    }
}

function chk_decimal(object1){	//ADDED BY LALANKA ON 22-01-2010
    m_format=object1.value;
    m_dot_count=0;
    //alert(m_format);
    if(m_format !=""){
        for (var i = 0; i < m_format.length; i++)  {
            
            var oneChar = m_format.charAt(i)
            if (oneChar== ".") 
            {
                m_dot_count=m_dot_count+1;
            }
            
            
            
        }
        if (m_dot_count>0) 
        {
            alert("You Cannot enter decimals");
            object1.focus();
        }
        //}
        if (m_format>12) 
        {
            alert("Maximum Rate is 12");
            object1.focus();
        }
        //}
    }
    
}

function format_number3(object1,size) 
{
    var comsize=20;
    m_format_1=object1.value;
    if(m_format_1 !="")
    {	
        m_dot_count=0;
        var chk_brk=false;
        var m_integer="";
        var m_decimal="";
        var m_format="";
        m_minus=false;
        for (var i = 0; i < m_format_1.length; i++) 
        {
            var oneChar = m_format_1.charAt(i)
            if (oneChar==null || oneChar == "" || oneChar == " ") 
            {
                var k=1;
            }
            else 
            {	
                m_format=m_format+oneChar; 		
            }
        }
        for (var i = 0; i < m_format.length; i++) 
        {
            var oneChar = m_format.charAt(i)
            if (oneChar== ".") 
            {
                m_dot_count=m_dot_count+1;
            }
            if (oneChar== "-") 
            {
                m_minus=true;
            }
            if (isNaN(oneChar) && oneChar != "," && oneChar != "." && oneChar != "-") 
            {
                alert("Please enter a number");
                chk_brk=true;
                i=m_format.length;
                break;			
            }
            if (!isNaN(oneChar) && m_dot_count==0 ) 
            {
                m_integer=m_integer+oneChar;
            }
            if (m_dot_count>0) 
            {
                m_decimal=m_decimal+oneChar;
            }
        }
        if (chk_brk) 
        {
            object1.value="";
            object1.focus();
            return false;
        }
        if (m_dot_count>1) 
        {
            alert("You have typed more than one decimal separator");
            object1.focus();
            return false;
        }
        m_format=m_integer; 
        
        m_length=m_integer.length;
        if (m_length > size)
        {
            alert("The length of the number cannot be more than "+size.toString());
            object1.focus();
            return false;
        }
        var m_formatted="";
        var m_new_str="";
        m_end=m_format.length;
        m_stat_pos=m_end-4;
        var m_last_pos=m_end;
        while (m_stat_pos>=0)	
        {
            m_chk_str=m_format.substr(m_stat_pos,1);
            if (m_chk_str!=null) 
            {
                m_add_str=","+m_format.substr(m_stat_pos+1,3);
                m_new_str=m_add_str+m_new_str;
            }
            m_last_pos=m_stat_pos;
            m_stat_pos=m_stat_pos-3;
        }
        
        if (m_decimal == "") 
        {
            m_decimal=".00";
        }
        else if (m_decimal == ".") 
        {
            m_decimal=".00";
        }
        else if (m_decimal == ".0") 
        {
            m_decimal=".00";
        }
        else if (m_decimal == ". 0") 
        {
            m_decimal=".00";
        }
        
        m_new_str=m_format.substr(0,m_last_pos+1)+m_new_str+m_decimal;
        if (m_minus) 
        {
            m_new_str="-"+m_new_str;
        }
        
        var m_left_str="";
        rem_len=16-m_new_str.length;
        while (rem_len>0) 
        {
            m_left_str=m_left_str+"	";
            rem_len=rem_len-1;
        }
        //object1.value=m_left_str+m_new_str;
        object1.value=m_new_str;
        //return m_new_str;
        return true;
        
    }	
}	

function check_number(obj){
    if(obj.value!='' &&  obj.value!='-'  && obj.value!='null') 
        if(isPosInteger(obj.value)){ 
            format_noobject_nodecimal1(obj) ;
        } 
    else{
        alert('please enter a number'); 
        obj.value=''; 
        obj.focus(); 
    } 
} 

function check_number_decimal(obj,size){
	//alert('obj'+obj.value+'obj size'+size)
    if(obj.value!='' && obj.value!='-'  && obj.value!='null' ) 
        if(isnumberok(obj,size)){ 
            format_number(obj,size) ;
        } 
    else{
        alert('please enter a number'); 
        obj.value=''; 
        //obj.focus(); 
    } 
} 

function close_screen() {
    if(document.Form1.close2.value=="Proceed to Next Level"){
        if(document.Form1.HID_CLOSE_STS.value=='Y' ){ 
            if(document.Form1.CHK_ACK.checked==true ){ 
                if(confirm("Are you sure you want to Proceed to Next Level?")){ 
                    
                    
                    if(m_client_status=='A'){
                        window.close();
                        //alert('sdfs'+m_client_status)
                        window.opener.document.Form1.CHK_APV_1.checked=true;
                        window.opener.document.Form1.CHK_APV_1.value='YES';
                        window.opener.check_change_A(1);
                    }
                    if(m_client_status=='B'){
                        window.close();
                        window.opener.document.Form1.CHK_APV_2.checked=true;
                        window.opener.document.Form1.CHK_APV_2.value='YES';
                        window.opener.check_change_A(2);
                    }
                    if(m_client_status=='C'){
                        window.close();
                        window.opener.document.Form1.elements["CHK_CGV_"+m_gur_row_no].checked=true;
                        window.opener.document.Form1.elements["CHK_CGV_"+m_gur_row_no].value='YES';
                        window.opener.check_change_B(m_gur_row_no);
                    }
                    
                    
                }
            }
            else {
                alert('Please check acknowledge to proceed next level');
                
            }
        }					
        else { 
            close_window();
        }
        
    }
    else	if(document.Form1.close2.value=="Close"){
        if(document.Form1.HID_CLOSE_STS.value=='Y'){ 
            if(confirm("Are you sure you want to close the screen?")){ 
                window.close();
            }
        }
        else { 
            close_window();
        }
    }
}


//comment by nuwan de silva on 22-10-07-----------------------	
/**	function close_screen() {
        if(document.Form1.close2.value=="Proceed to Next Level"){
            if(document.Form1.HID_CLOSE_STS.value=='Y' ){ 
            if(document.Form1.CHK_ACK.checked==true ){ 
            
                    if(confirm("Are you sure you want to Proceed to Next Level?")){ 
                    window.close();
                    }
                }
                else {
                    alert('Please check acknowledge to proceed next level');
                
                        }
            }					
            else { 
                    close_window();
            }
            
            }
    else	if(document.Form1.close2.value=="Close"){
            if(document.Form1.HID_CLOSE_STS.value=='Y'){ 
                    if(confirm("Are you sure you want to close the screen?")){ 
                    window.close();
                    }
                }
            else { 
                    close_window();
            }
            }
    }
    
    */	

//=============================================================================//
//===========Added By Nuwan De Silva 29-05-07=================================//

function close_screen2() {
    
    if(document.Form1.close.value=="Close"){
        if(document.Form1.HID_CLOSE_STS.value=='Y'){ 
            if(confirm("Are you sure you want to close the screen?")){ 
                window.close();
            }
        }
        else { 
            close_window();
        }
    }
}

//==========================================================================================//

function check_number_precent(obj,size){
    if(obj.value!='' && obj.value!='-' ) 
        if(isnumberok(obj,size)){ 
            
            if( parseInt(obj.value) <= 100 ){ 
                format_number(obj,size) ;
            }
            
            else
            {
                alert('Number can not exceed 100'); 
                obj.value=''; 
                obj.focus(); 
            }
            
        }
    
    else{
        alert('please enter a number'); 
        obj.value=''; 
        obj.focus(); 
    } 
}


//added by nuwan de silva 

function clear_corporate_client(){
    
    lineno_bank=0;
    arr_size_bank=0;
    arr_size_company=0; 
    lineno_company_dir=0; 
    lineno_subsidiaries=0; 
    arr_size_subsidiaries=0; 
    lineno_customer=0; 
    arr_size_customer=0; 
    lineno_auditors=0; 
    arr_size_auditors=0; 
    
    lineno_credit_c=0;
    arr_size_credit_c=0;
    e_label_credit_c.innerHTML='';  
    e_header_credit_c.innerHTML='';  
    e_txt_credit_c.innerHTML='';  
    e_add_but_credit_c.innerHTML='';  
    
    e_label_company_directors.innerHTML='';
    e_header_company_directors.innerHTML='';
    e_txt_company_directors.innerHTML='';
    e_add_but_company_directors.innerHTML='';
    e_label_subsidiaries.innerHTML='';
    e_header_subsidiaries.innerHTML='';
    e_txt_subsidiaries.innerHTML='';
    e_add_but_subsidiaries.innerHTML='';
    e_label_bank.innerHTML='';
    e_header_bank_c.innerHTML='';
    e_txt_bank_c.innerHTML='';
    e_add_but_bank_c.innerHTML='';
    e_label_customer.innerHTML='';
    e_header_customer.innerHTML='';
    e_txt_customer.innerHTML='';
    e_add_but_customer.innerHTML='';
    
    e_header_auditors.innerHTML='';
    e_txt_auditors.innerHTML='';
    e_add_but_auditors.innerHTML='';
    
    
    lineno_nonrelated=0;
    arr_size_nonrelated=0;
    e_label_nonrelated_c.innerHTML='';  
    e_header_nonrelated_c.innerHTML='';  
    e_txt_nonrelated_c.innerHTML='';  
    e_add_but_nonrelated_c.innerHTML='';  
    
    lineno_ba=0;
    arr_size_ba=0;
    e_label_ba.innerHTML='';  
    e_header_ba.innerHTML='';  
    e_txt_ba.innerHTML='';  
    e_add_but_ba.innerHTML='';  
    
}

function clear_corporate(){
    lineno_bank=0;
    arr_size_bank=0;
    arr_size_company=0; 
    lineno_company_dir=0; 
    lineno_subsidiaries=0; 
    arr_size_subsidiaries=0; 
    lineno_customer=0; 
    arr_size_customer=0; 
    
    lineno_credit_c=0;
    arr_size_credit_c=0;
    e_label_credit_c.innerHTML=''; 
    e_header_credit_c.innerHTML='';  
    e_txt_credit_c.innerHTML='';  
    e_add_but_credit_c.innerHTML='';  
    
    e_label_company_directors.innerHTML='';
    e_header_company_directors.innerHTML='';
    e_txt_company_directors.innerHTML='';
    e_add_but_company_directors.innerHTML='';
    e_label_subsidiaries.innerHTML='';
    e_header_subsidiaries.innerHTML='';
    e_txt_subsidiaries.innerHTML='';
    e_add_but_subsidiaries.innerHTML='';
    e_label_bank.innerHTML='';
    e_header_bank_c.innerHTML='';
    e_txt_bank_c.innerHTML='';
    e_add_but_bank_c.innerHTML='';
    e_label_customer.innerHTML='';
    e_header_customer.innerHTML='';
    e_txt_customer.innerHTML='';
    e_add_but_customer.innerHTML='';
    
    e_header_auditors.innerHTML=''; 
    e_txt_auditors.innerHTML=''; 
    e_add_but_auditors.innerHTML=''; 
    
    lineno_auditors=0;
    arr_size_auditors=0;
    
    lineno_property=0;
    arr_size_property=0;
    e_label_property_c.innerHTML='';  
    e_header_property_c.innerHTML='';  
    e_txt_property_c.innerHTML='';  
    e_add_but_property_c.innerHTML='';  
    
    lineno_ba=0;
    arr_size_ba=0;
    e_label_ba.innerHTML='';  
    e_header_ba.innerHTML=''; 
    e_txt_ba.innerHTML='';  
    e_add_but_ba.innerHTML='';  
    
}





//add header auditors nuwan de silva
function header_auditors(){
    
    lineno_auditor = 0; 
    e_header_auditors.innerHTML +='<table  border="0" align="center" width="100%" class="table" border="0"><TR><TD WIDTH="18%" align="left">Auditor Name </TD>'+
        '<TD WIDTH="10%" align="left">Address 1</TD>' +
        '<TD WIDTH="20%" align="left">Address 2</TD>' +
        '<TD WIDTH="10%" align="right">Relationship (Yrs/Mts)</TD>' +
        '<TD WIDTH="12%" align="left">Reference</TD>' +
        '<TD WIDTH="12%" align="left">Telephone</TD>' +
        '<TD WIDTH="*%" align="left">Fax  </TD>' +
        '</TR></table>';
}


//add row auditors nuwan de silva
function add_row_auditors(){ 
    
    var b_flag=0;
    
    if(lineno_auditors!=0){ 
        count=lineno_auditors-1;
        
        m_auditor_name="TXT_AUDITOR_NAME"+count;
        m_add="TXT_AUDITOR_ADDRESS_1"+count;
        m_add2="TXT_AUDITOR_ADDRESS_2"+count;
        m_relation="TXT_AUDITOR_RELATIONSHIP"+count;
        
        if(document.Form1.elements[m_auditor_name].value=="") {
            alert('Auditor name cannot be null ');
            b_flag=1;
        }
        
        //else if(document.Form1.elements[m_add].value=="") {
        //	alert('Adress cannot be null');
        //	b_flag=1;
        //	}
        
        //	else if(document.Form1.elements[m_relation].value=="") {
        //	alert('Relationship cannot be null');
        //	b_flag=1;
        //	}
        
        else{
            b_count=0;
            tmp_auditor_name=document.Form1.elements[m_auditor_name].value;
            
            for(var i=0;i<lineno_auditors-1;i++){
                m_tmp_name="TXT_AUDITOR_NAME"+i;
                if(lineno_auditors>=2){
                    
                    if(document.Form1.elements[m_tmp_name].value.toUpperCase()==tmp_auditor_name.toUpperCase() ){
                        alert('Auditor name can not be duplicated ! ')
                        document.Form1.elements[m_auditor_name].value='';
                        //document.Form1.elements[m_tmp_name].focus();
                        b_flag=1;
                        b_count=1};
                    
                    if(b_count==1){
                        break;}
                    
                } 
                
            }
            
        }
        
        
        
    }
    
    if(b_flag==0){
        
        e_txt_auditors.innerHTML +='<table  border="0" align="center"   width="100%" class="table" border="0" ><tr>'+									
            '<TD WIDTH="18%" align="left"><input class="txt_input" type="text" name=TXT_AUDITOR_NAME'+lineno_auditors+'  style=\"width: 170px\" maxlength="200" size="55" ></TD>'+
            '<TD WIDTH="10%" align="left"><input class="txt_input" type="text" name=TXT_AUDITOR_ADDRESS_1'+lineno_auditors+' style=\"width: 85px\" maxlength="100" size="20"></TD>'+
            '<TD WIDTH="20%" align="left"><input class="txt_input" type="text" name=TXT_AUDITOR_ADDRESS_2'+lineno_auditors+' style=\"width: 170px\" maxlength="100" size="20"></TD>'+
            '<TD WIDTH="10%" align="right"><input class="txt_input" type="text" name=TXT_AUDITOR_RELATIONSHIP'+lineno_auditors+' onblur=\"check_number(this)\"  STYLE=\"{text-align:right; width:50px;}\" maxlength="3" size="30"></TD>'+
            '<TD WIDTH="12%" align="left"><input class="txt_input" type="text" name=TXT_AUDITOR_REFERENCE'+lineno_auditors+'  STYLE=\"{width:100px;}\" maxlength="50" size="30" ></TD>'+
            '<TD WIDTH="12%" align="left"><input class="txt_input" type="text" name=TXT_AUDITOR_TEL_NO'+lineno_auditors+'  style=\"width: 95px\" maxlength="60" onBlur=\"Validate_Telephone_Number(this,1)\" size="22" ></TD>'+	
            '<TD WIDTH="12%" align="left"><input class="txt_input" type="text" name=TXT_AUDITOR_FAX_NO'+lineno_auditors+'  style=\"width: 95px\" onBlur=\"Validate_Telephone_Number(this,2)\" maxlength="60" size="22"></TD>'+
            '<TD WIDTH="6%" ><input class="but_input" type="button" name=BUT_AUDITORS_DEL'+lineno_auditors+' value="Delete" onClick=del_row_auditors('+lineno_auditors+')></TD>'+
            '</tr></table>';
        
        lineno_auditors=lineno_auditors+1;
        
        arr_size_auditors=arr_size_auditors+1;
    }
    add_button_auditors();
    
}



function del_row_auditors(rowNo){
    //	alert(rowNo);
    
    var j=0;
    for(var i=0;i<arr_size_auditors;i++){
        
        m_auditor_name="TXT_AUDITOR_NAME"+i;
        m_add="TXT_AUDITOR_ADDRESS_1"+i;
        m_add2="TXT_AUDITOR_ADDRESS_2"+i;
        m_relation="TXT_AUDITOR_RELATIONSHIP"+i;
        m_reference="TXT_AUDITOR_REFERENCE"+i;
        m_tel="TXT_AUDITOR_TEL_NO"+i;
        m_fax="TXT_AUDITOR_FAX_NO"+i;
        
        if(i==rowNo)
            continue;
        
        
        array_name_aud[j]=document.Form1.elements[m_auditor_name].value;
        array_add_aud[j]=document.Form1.elements[m_add].value;
        array_add2_aud[j]=document.Form1.elements[m_add2].value;
        array_rela_aud[j]=document.Form1.elements[m_relation].value;
        array_ref_aud[j]=document.Form1.elements[m_reference].value;
        array_tel_aud[j]=document.Form1.elements[m_tel].value;
        array_fax_aud[j]=document.Form1.elements[m_fax].value;
        
        j=j+1;
    }
    
    m_auditor_name="TXT_AUDITOR_NAME"+rowNo;
    m_add="TXT_AUDITOR_ADDRESS_1"+rowNo;
    m_add2="TXT_AUDITOR_ADDRESS_2"+rowNo;
    m_relation="TXT_AUDITOR_RELATIONSHIP"+rowNo;
    m_reference="TXT_AUDITOR_REFERENCE"+rowNo;
    m_tel="TXT_AUDITOR_TEL_NO"+rowNo;
    m_fax="TXT_AUDITOR_FAX_NO"+rowNo;
    
    if(document.Form1.elements[m_auditor_name].value != '' || document.Form1.elements[m_add].value != '' || document.Form1.elements[m_add2].value != '' || document.Form1.elements[m_relation].value != '' || document.Form1.elements[m_tel].value != '' || document.Form1.elements[m_fax].value != ''  || document.Form1.elements[m_reference].value != '' ) { 
        if(confirm('Are you sure you want to delete ?')){ 
            lineno_auditors = lineno_auditors-1;
            arr_size_auditors = lineno_auditors;
            write_data_auditors(arr_size_auditors);
        }
    }
    else {
        lineno_auditors = lineno_auditors-1;
        arr_size_auditors = lineno_auditors;
        write_data_auditors(arr_size_auditors);
    }
    
}


function disable_rows_auditors(no)
{
    for(var i=0;i<no;i++)
    {	
        
        m_auditor_name="TXT_AUDITOR_NAME"+i;
        m_add="TXT_AUDITOR_ADDRESS_1"+i;
        m_add2="TXT_AUDITOR_ADDRESS_2"+i;
        m_relation="TXT_AUDITOR_RELATIONSHIP"+i;
        m_reference="TXT_AUDITOR_REFERENCE"+i;
        m_tel="TXT_AUDITOR_TEL_NO"+i;
        m_fax="TXT_AUDITOR_FAX_NO"+i;
        m_but_aud_del="BUT_AUDITORS_DEL"+i;	
        
        document.Form1.elements[m_auditor_name].disabled=true;
        document.Form1.elements[m_add].disabled=true;
        document.Form1.elements[m_relation].disabled=true;
        document.Form1.elements[m_reference].disabled=true;
        document.Form1.elements[m_tel].disabled=true;
        document.Form1.elements[m_fax].disabled=true;
        document.Form1.elements[m_but_aud_del].disabled=true;
        
        
    }	
    document.Form1.BUT_ADD_AUDITORS.disabled=true;
} 



function write_data_auditors(size){
    
    e_txt_auditors.innerHTML="";
    
    for(var j=0;j<size;j++){
        
        
        if(array_name_aud[j]=="" && array_add_aud[j]=="" && array_add2_aud[j]=="" && array_rela_aud[j]=="" && array_ref_aud[j]=="" && array_tel_aud[j]=="" && array_fax_aud[j]==""){
            
            
            e_txt_auditors.innerHTML +='<table  align="center"   width="100%" class="table" border="0" ><tr>'+									
                '<TD WIDTH="18%" align="left"><input class="txt_input" type="text" name=TXT_AUDITOR_NAME'+j+'  style=\"width: 170px\" maxlength="200" size="55"></TD>'+
                '<TD WIDTH="10%" align="left"><input class="txt_input" type="text" name=TXT_AUDITOR_ADDRESS_1'+j+' style=\"width: 85px\" maxlength="100" size="20"></TD>'+
                '<TD WIDTH="20%" align="left"><input class="txt_input" type="text" name=TXT_AUDITOR_ADDRESS_2'+j+' style=\"width: 170px\" maxlength="100" size="20"></TD>'+
                '<TD WIDTH="10%" align="right"><input class="txt_input" type="text" name=TXT_AUDITOR_RELATIONSHIP'+j+' onblur=\"check_number(this)\"  STYLE=\"{text-align:right; width:50px;}\" maxlength="3" size="30"></TD>'+
                '<TD WIDTH="12%" align="left"><input class="txt_input" type="text" name=TXT_AUDITOR_REFERENCE'+j+'  STYLE=\"{width:100px;}\" maxlength="50" size="30" ></TD>'+
                '<TD WIDTH="12%" align="left"><input class="txt_input" type="text" name=TXT_AUDITOR_TEL_NO'+j+'  style=\"width: 95px\" onBlur=\"Validate_Telephone_Number(this,1)\" maxlength="60" size="22" ></TD>'+	
                '<TD WIDTH="12%" align="left"><input class="txt_input" type="text" name=TXT_AUDITOR_FAX_NO'+j+'  style=\"width: 95px\" onBlur=\"Validate_Telephone_Number(this,2)\" maxlength="60" size="22"></TD>'+
                '<TD WIDTH="6%" ><input class="but_input" type="button" name=BUT_AUDITORS_DEL'+j+' value="Delete" onClick=del_row_auditors('+j+')></TD>'+
                '</tr></table>';
            
            continue;
        }
        
        
        e_txt_auditors.innerHTML +='<table  align="center"   width="100%" class="table" border="0" ><tr>'+									
            '<TD WIDTH="18%" align="left"><input class="txt_input" type="text" name=TXT_AUDITOR_NAME'+j+'  value="'+array_name_aud[j]+'" style=\"width: 170px\" maxlength="200" size="55"></TD>'+
        '<TD WIDTH="10%" align="left"><input class="txt_input" type="text" name=TXT_AUDITOR_ADDRESS_1'+j+'  value="'+array_add_aud[j]+'" style=\"width: 85px\" maxlength="100" size="20"></TD>'+
        '<TD WIDTH="20%" align="left"><input class="txt_input" type="text" name=TXT_AUDITOR_ADDRESS_2'+j+'  value="'+array_add2_aud[j]+'" style=\"width: 170px\" maxlength="100" size="20"></TD>'+
        '<TD WIDTH="10%" align="right"><input class="txt_input" type="text" name=TXT_AUDITOR_RELATIONSHIP'+j+' value="'+array_rela_aud[j]+'" onblur=\"check_number(this)\"  STYLE=\"{text-align:right; width:50px;}\" maxlength="3" size="30"></TD>'+
        '<TD WIDTH="12%" align="left"><input class="txt_input" type="text" name=TXT_AUDITOR_REFERENCE'+j+'  value="'+array_ref_aud[j]+'" STYLE=\"{width:100px;}\" maxlength="50" size="30" ></TD>'+
        '<TD WIDTH="12%" align="left"><input class="txt_input" type="text" name=TXT_AUDITOR_TEL_NO'+j+'  value="'+array_tel_aud[j]+'" style=\"width: 95px\" onBlur=\"Validate_Telephone_Number(this,1)\" maxlength="60" size="22" ></TD>'+	
        '<TD WIDTH="12%" align="left"><input class="txt_input" type="text" name=TXT_AUDITOR_FAX_NO'+j+'  value="'+array_fax_aud[j]+'" style=\"width: 95px\" onBlur=\"Validate_Telephone_Number(this,2)\" maxlength="60" size="22"></TD>'+
        '<TD WIDTH="6%" ><input class="but_input" type="button" name=BUT_AUDITORS_DEL'+j+' value="Delete" onClick=del_row_auditors('+j+')></TD>'+
            '</tr></table>';
        
    }
    lineno_auditors = j;
}





function assign_data_auditors(data_vec){ 
    vec_pos=0; 
    i=0; 
    m_size=0; 
    m_size=data_vec.length; 
    if(m_size==0) { 
        array_name_aud[i] = '';
        array_add_aud[i] = '';
        array_add2_aud[i] = '';
        array_rela_aud[i] = '';
        array_ref_aud[i] = '';
        array_tel_aud[i] = '';
        array_fax_aud[i] = '';
        arr_size_auditors=i+1;
        write_data_auditors(i+1); 	
        document.Form1.hid_count_aud.value=i+1; 
        if(document.Form1.SCREEN_NAME.value =='DACT' || document.Form1.SCREEN_NAME.value =='RACT' ){ 
            disable_rows_auditors(i+1);
        }
        assign_help_status('H_credit'); 
        makeRequest(document.Form1.TXT_CLIENT_CODE); 
    }
    else {
        
        while(vec_pos<m_size){  
            
            if(data_vec[vec_pos]=='null' || data_vec[vec_pos]==''){ 
                array_name_aud[i] = '';
            }
            else {
                array_name_aud[i] = data_vec[vec_pos];
            }
            
            if(data_vec[vec_pos+1]=='null' || data_vec[vec_pos+1]==''){ 
                array_add_aud[i] = '';
            }
            else {
                array_add_aud[i] = data_vec[vec_pos+1];
            }
            
            if(data_vec[vec_pos+2]=='null' || data_vec[vec_pos+2]==''){ 
                array_rela_aud[i] = '';
            }
            else {
                array_rela_aud[i] = data_vec[vec_pos+2];
            }
            
            if(data_vec[vec_pos+3]=='null' || data_vec[vec_pos+3]==''){ 
                array_ref_aud[i] = '';
            }
            
            else {
                array_ref_aud[i] = data_vec[vec_pos+3];
            }
            
            if(data_vec[vec_pos+4]=='null' || data_vec[vec_pos+4]==''){ 
                array_tel_aud[i] = '';
            }
            else {
                array_tel_aud[i] = data_vec[vec_pos+4];
            }
            
            if(data_vec[vec_pos+5]=='null' || data_vec[vec_pos+5]==''){ 
                array_fax_aud[i] = '';
            }
            else {
                array_fax_aud[i] = data_vec[vec_pos+5];
            }
            
            if(data_vec[vec_pos+6]=='null' || data_vec[vec_pos+6]==''){ 
                array_add2_aud[i] = '';
            }
            else {
                array_add2_aud[i] = data_vec[vec_pos+6];
            }
            
            vec_pos=vec_pos+7;	 
            i=i+1; 
            
        }
        arr_size_auditors=i;
        write_data_auditors(i); 	
        document.Form1.hid_count_aud.value=i; 
        if(document.Form1.SCREEN_NAME.value =='DACT' || document.Form1.SCREEN_NAME.value =='RACT' ){ 
            disable_rows_auditors(i);
        }
        assign_help_status('H_credit'); 
        makeRequest(document.Form1.TXT_CLIENT_CODE); 
    }
}



function double_check(type){ 
    //var flag=0;
    
    if(!validate_auditors()){
        return false;
    }
    
    else if(!validate_directors()){
        return false;
    }
    
    else if(!validate_subsidiaries()){
        return false;
    }
    
    
    else if(!validate_bank(type)){
        return false;
    }
    
    
    else if(!validate_credit(type)){
        return false;
    }
    
    else if(!validate_customer()){
        return false;
    }
    
    else if(!validate_refree(type)){
        return false;
    }
    
    else if(!validate_family()){
        return false;
    }
    
    else if(!validate_count_contracts()){ //added by nuwan de silva on 16-10-2008
        return false;
    }
    
    
    else{
        return true;
    }
    
    
    
    
}
//added by nuwan de silva 06-08-07---------------------------
function validate_client_dob(obj_dd,obj_mm,obj_yy){
    if(obj_dd.value!="" && obj_mm.value!="" && obj_yy.value!="" ){
        checkMonthLength(obj_dd,obj_mm,obj_yy)
    }
}
//-------------------------------------------------------------

//validate auditors
function validate_auditors(){ 
    
    var count=0;
    var b_count=0;
    var b_flag=0;
    
    if(lineno_auditors>=2){
        
        count =lineno_auditors-1;
        //alert(count);
        for(var i=count;i>=0;i--){
            m_name="TXT_AUDITOR_NAME"+i;
            
            if(document.Form1.elements[m_name].value!=""){
                tmp_name=document.Form1.elements[m_name].value;
                break;
            }
            
        }
    }
    
    //alert(tmp_name);
    for(var i=0;i<lineno_auditors;i++){
        
        m_auditor_name="TXT_AUDITOR_NAME"+i;
        m_add="TXT_AUDITOR_ADDRESS_1"+i;
        m_add2="TXT_AUDITOR_ADDRESS_2"+i;
        m_relation="TXT_AUDITOR_RELATIONSHIP"+i;
        m_ref="TXT_AUDITOR_REFERENCE"+i;
        m_tel="TXT_AUDITOR_TEL_NO"+i;
        m_fax="TXT_AUDITOR_FAX_NO"+i;
        
        if(document.Form1.elements[m_auditor_name].value!="" || document.Form1.elements[m_add].value!="" || document.Form1.elements[m_add2].value!="" || document.Form1.elements[m_relation].value!="" || document.Form1.elements[m_tel].value!="" || document.Form1.elements[m_fax].value!="" || document.Form1.elements[m_ref].value!="" ) {
            
            if(document.Form1.elements[m_auditor_name].value ==""){
                alert('Auditor name cannot be null ');
                b_flag=1;
                break;
                //return false;
            }
            
            if(lineno_auditors>=2){
                
                
                if(document.Form1.elements[m_auditor_name].value.toUpperCase()==tmp_name.toUpperCase() ){
                    b_count=b_count+1;
                    
                }
            }			
            
        }
        
    }
    
    
    if(b_count>=2){
        alert('Auditor name can not be duplicated ! ');
        //	document.Form1.elements[tmp_name].value='';
        //return false;
        b_flag=1;
    }
    
    
    
    if(b_flag==0){
        return true;
    }
    else{
        return false;
    }
    
}

//-------------------------Validate Directors--------------------------------------------------------------------------------------------------//



//validate auditors
function validate_directors(){ 
    
    var count=0;
    var b_count=0;
    var b_count2=0;
    var flag=0;
    
    if(lineno_company_dir>=2){
        
        count =lineno_company_dir-1;
        
        for(var i=count;i>=0;i--){
            m_name="TXT_NAME_DIR"+i;
            m_nic="TXT_NIC_NO_DIR"+i;
            
            if(document.Form1.elements[m_name].value!=""){
                tmp_name=document.Form1.elements[m_name].value;
                tmp_nic=document.Form1.elements[m_nic].value;
                //flag=1;
                break;
            }
            
        }
    }
    
    //alert(tmp_name);
    for(var i=0;i<lineno_company_dir;i++){
        
        m_name="TXT_NAME_DIR"+i;
        m_nic="TXT_NIC_NO_DIR"+i;
        m_stake="TXT_STAKE"+i;
        m_shares="TXT_NO_OF_SHARES"+i;
        m_value="TXT_VALUE"+i;
        m_pos="TXT_POSITION"+i;
        
        
        if(document.Form1.elements[m_name].value!="" || document.Form1.elements[m_nic].value!="" || document.Form1.elements[m_stake].value!="" || document.Form1.elements[m_shares].value!="" || document.Form1.elements[m_value].value!="" || document.Form1.elements[m_pos].value!=""  ) {
            
            if(document.Form1.elements[m_name].value ==""){
                alert('Director name cannot be null ');
                flag=1;
                break;
                //	return false;
            }
            
            else if(document.Form1.elements[m_nic].value ==""){
                alert('NIC number cannot be null ');
                flag=1;
                break;
                //	return false;
            }
            
            else if(document.Form1.elements[m_stake].value ==""){
                alert('Stake cannot be null ');
                flag=1;
                break;
                //	return false;
            }
            
            else if(document.Form1.elements[m_shares].value ==""){
                alert('No of shares cannot be null ');
                flag=1;
                break;
                //	return false;
            }
            
            else if(document.Form1.elements[m_value].value ==""){
                alert('Value cannot be null ');
                flag=1;
                break;
                //	return false;
            }
            
            else if(document.Form1.elements[m_pos].value ==""){
                alert('Position cannot be null ');
                flag=1;
                break;
                //	return false;
            }
            
            if(lineno_company_dir>=2){
                
                
                if(document.Form1.elements[m_name].value.toUpperCase()==tmp_name.toUpperCase() ){
                    b_count=b_count+1;
                }
                
                if(document.Form1.elements[m_nic].value.toUpperCase()==tmp_nic.toUpperCase() ){
                    b_count2=b_count2+1;
                    
                }
            }			
            
        }
        
    }
    
    
    if(b_count>=2){
        alert('Name cannot be duplicated ! ');
        //	document.Form1.elements[tmp_name].value='';
        //	return false;
        
        flag=1;		
    }
    
    if(b_count2>=2){
        alert('NIC No cannot be duplicated ! ');
        //	document.Form1.elements[tmp_name].value='';
        //	return false;
        flag=1;
        
    }
    
    if (flag==0){
        return true;
    }
    else
    {
        return false;
    }
    
}


//----------------------------------------------------------------------------------------------------------------------------------------------------//

//--validate sub sidaries ------------------------------------------------------------------------------------------//



function validate_subsidiaries(){ 
    
    var count=0;
    var b_count=0;
    var flag=0;
    
    
    if(lineno_ba>=2){
        
        count =lineno_subsidiaries-1;
        //alert(count);
        for(var i=count;i>=0;i--){
            m_name="TXT_NAME_SUB"+i;
            
            if(document.Form1.elements[m_name].value!=""){
                tmp_name=document.Form1.elements[m_name].value;
                break;
            }
            
        }
    }
    
    //alert(tmp_name);
    for(var i=0;i<lineno_subsidiaries;i++){
        
        m_name="TXT_NAME_SUB"+i;
        m_stake="TXT_STAKE_SUB"+i;
        m_value="TXT_VALUE_SUB"+i;
        m_tel="TXT_TEL_NO_SUB"+i;
        m_officer="TXT_OFFICER_SUB"+i;
        m_act="TXT_ACTIVITIES_SUB"+i;
        
        
        if(document.Form1.elements[m_name].value!="" || document.Form1.elements[m_stake].value!="" || document.Form1.elements[m_value].value!="" || document.Form1.elements[m_tel].value!="" || document.Form1.elements[m_officer].value!="" || document.Form1.elements[m_act].value!=""  ) {
            
            if(document.Form1.elements[m_name].value ==""){
                alert('Subsidiary name cannot be null ');
                flag=1;
                break;
                //return false;
            }
            
            if(document.Form1.elements[m_stake].value ==""){
                alert('Stake cannot be null ');
                flag=1;
                break;
                //return false;
            }
            
            if(document.Form1.elements[m_value].value ==""){
                alert('Value cannot be null ');
                flag=1;
                break;
                //return false;
            }
            
            if(document.Form1.elements[m_act].value ==""){
                alert('Business activities cannot be null ');
                flag=1;
                break;
                //return false;
            }
            
            
            
            if(lineno_subsidiaries>=2){
                
                
                if(document.Form1.elements[m_name].value.toUpperCase()==tmp_name.toUpperCase() ){
                    b_count=b_count+1;
                    
                }
            }			
            
        }
        
    }
    
    
    if(b_count>=2){
        alert('Subsidiary name cannot be duplicated ! ');
        //	document.Form1.elements[tmp_name].value='';
        flag=1;
        //	return false;
    }
    
    
    
    
    
    
    if (flag==0){
        return true;
    }
    else
    {
        return false;
    }
}

//------------------------------------------------------------------------------------------------------------------//




//-------------------------Validate bank--------------------------------------------------------------------------------------------------//



//validate bank
function validate_bank(type){ 
    
    var count=0;
    var b_count=0;
    var b_count2=0;
    var b_flag=0;
    
    
    if(lineno_bank>=2){
        
        count =lineno_bank-1;
        
        for(var i=count;i>=0;i--){
            m_bank_code="TXT_BANK_CODE"+i;
            m_branch_code="TXT_BRANCH_CODE"+i;
            m_ac_no="TXT_ACCOUNT_NO"+i;
            
            if(document.Form1.elements[m_bank_code].value!=""){
                
                tmp_bank_code=document.Form1.elements[m_bank_code].value;
                tmp_branch_code=document.Form1.elements[m_branch_code].value;
                tmp_ac_no=document.Form1.elements[m_ac_no].value;
                
                break;
            }
            
        }
    }
    
    //alert(tmp_name);
    for(var i=0;i<lineno_bank;i++){
        
        m_bank_code="TXT_BANK_CODE"+i;
        m_branch_code="TXT_BRANCH_CODE"+i;
        m_ac_no="TXT_ACCOUNT_NO"+i;
        m_ref="TXT_REFERENCE"+i;
        m_tel="TXT_TEL_NO2"+i;
        m_fax="TXT_FAX_NO"+i;
        m_rela="TXT_RELATIONSHIP2"+i;
        
        
        
        if(document.Form1.elements[m_bank_code].value!="" || document.Form1.elements[m_branch_code].value!="" || document.Form1.elements[m_ac_no].value!="" || document.Form1.elements[m_ref].value!="" || document.Form1.elements[m_tel].value!="" || document.Form1.elements[m_fax].value!=""  || document.Form1.elements[m_rela].value!=""  ) {
            
            if(document.Form1.elements[m_bank_code].value ==""){
                alert('Banker cannot be null ');
                b_flag=1;
                break;
                
                //return false;
            }
            
            if(document.Form1.elements[m_branch_code].value ==""){
                alert('Branch cannot be null ');
                b_flag=1;
                break;
                
                //return false;
            }
            
            if(document.Form1.elements[m_ac_no].value ==""){
                alert('Account No cannot be null ');
                b_flag=1;
                break;
                
                //return false;
            }
            
            if(document.Form1.elements[m_ref].value ==""){
                alert('Reference cannot be null ');
                b_flag=1;
                break;
                //return false;
            }
            
            if(document.Form1.elements[m_tel].value ==""){
                alert('Tel No cannot be null ');
                b_flag=1; 
                break;
                //return false;
            }
            
            if(document.Form1.elements[m_fax].value ==""){
                alert('Fax No cannot be null ');
                b_flag=1;
                break;
                //return false;
            }
            
            if(lineno_bank>=2){
                
                
                if(document.Form1.elements[m_bank_code].value.toUpperCase()==tmp_bank_code.toUpperCase() && document.Form1.elements[m_branch_code].value.toUpperCase()==tmp_branch_code.toUpperCase() && document.Form1.elements[m_ac_no].value.toUpperCase()==tmp_ac_no.toUpperCase() ){
                    b_count=b_count+1;
                }
                
                
            }			
            
        }
        
        //	if(type=='I'){
        //	break;
        //return true;
        //	}
        
    }
    
    
    if(b_count>=2){
        alert('Account No cannot be duplicated in the same branch ! ');
        //	document.Form1.elements[tmp_name].value='';
        b_flag=1;
        //return false;
        
    }
    
    
    
    if (	b_flag==0 || type=='I'){
        return true;
    }
    else
    {
        return false;
    }
    
    
    
    //	return true;
}


//----------------------------------------------------------------------------------------------------------------------------------------------------//



//-------------------------Validate credit--------------------------------------------------------------------------------------------------//



//validate credit
function validate_credit(type){ 
    
    var count=0;
    var b_count=0;
    var b_count2=0;
    var size=0;
    var b_flag=0;
    
    if(type=='C'){
        size=lineno_credit_c;
    }
    else
        if(type='I'){
            size=lineno_credit;
        }
    
    if(size>=2){
        
        count =size-1;
        
        for(var i=count;i>=0;i--){
            m_type="TXT_TYPE_OF_FACILITY"+i;
            m_name="TXT_INSTITUTION"+i;
            
            
            if(document.Form1.elements[m_name].value!=""){
                
                tmp_name=document.Form1.elements[m_name].value;
                tmp_type=document.Form1.elements[m_type].value;
                
                break;
            }
            
        }
    }
    
    //alert(tmp_name);
    for(var i=0;i<size;i++){
        
        m_type="TXT_TYPE_OF_FACILITY"+i;
        m_name="TXT_INSTITUTION"+i;
        m_contact="TXT_CONTACT_PERSON"+i;
        m_equip="TXT_EQUIPMENT"+i;
        m_rental="TXT_MONTHLY_RENTAL"+i;
        m_appr="TXT_APPROVED_AMOUNT"+i;
        m_balance="TXT_BALANCE_AMOUNT"+i;
        m_months="TXT_MONTHS"+i;
        m_security="TXT_SECURITY"+i;
        
        
        if(document.Form1.elements[m_name].value!="" || document.Form1.elements[m_contact].value!=""   ) { //|| document.Form1.elements[m_appr].value!="" || document.Form1.elements[m_balance].value!=""  || document.Form1.elements[m_months].value!="" 
            
            if(document.Form1.elements[m_name].value ==""){
                alert('Institution name cannot be null ');
                b_flag=1;
                break;
                //return false;
            }
            
            if(document.Form1.elements[m_contact].value ==""){
                alert('Contact person cannot be null ');
                b_flag=1;
                break;
                //return false;
            }
            
            if(document.Form1.elements[m_appr].value ==""){
                alert('Approved amount  cannot be null ');
                b_flag=1;
                break;
                //	return false;
            }
            
            if(type=='I'){
                if(document.Form1.elements[m_security].value =="" ){
                    alert('Security  cannot be null ');
                    b_flag=1;
                    break;
                    //return false;
                }
            }
            
            if(type=='C'){
                if(document.Form1.elements[m_rental].value ==""){
                    alert('Monthly rental cannot be null ');
                    b_flag=1;
                    break;
                    //return false;
                }
            }
            
            if(document.Form1.elements[m_months].value ==""){
                alert('Period  cannot be null ');
                b_flag=1;
                break;
                //return false;
            }
            
            if(document.Form1.elements[m_balance].value ==""){
                alert('Balance amount  cannot be null ');
                //	break;
                return false;
            }
            
            if(size>=2){
                
                
                if(document.Form1.elements[m_name].value.toUpperCase()==tmp_name.toUpperCase() && document.Form1.elements[m_type].value.toUpperCase()==tmp_type.toUpperCase()  ){
                    b_count=b_count+1;
                }
                
                
            }			
            
        }
        
    }
    
    
    if(b_count>=2){
        alert('Institute name cannot be duplicated with the same type ! ');
        //	document.Form1.elements[tmp_name].value='';
        b_flag=1;
        //return false;
        
    }
    
    if (b_flag==0){
        return true;
    }
    else
    {
        return false;
    }
    
    
    
}


//----------------------------------------------------------------------------------------------------------------------------------------------------//




//--validate auditors--------------------------------------------------------------------------------------------------------------------------------------------------//

function validate_customer(){ 
    
    var count=0;
    var b_count=0;
    var b_flag=0;
    
    if(lineno_customer>=2){
        
        count =lineno_customer-1;
        //alert(count);
        for(var i=count;i>=0;i--){
            m_name="TXT_CUSTOMER_NAME"+i;
            m_type="TXT_TYPE_C"+i;
            if(document.Form1.elements[m_name].value!=""){
                tmp_name=document.Form1.elements[m_name].value;
                tmp_type=document.Form1.elements[m_type].value;
                break;
            }
            
        }
    }
    
    //alert(tmp_name);
    for(var i=0;i<lineno_customer;i++){
        
        m_name="TXT_CUSTOMER_NAME"+i;
        m_type="TXT_TYPE_C"+i;
        m_add="TXT_ADDRESS_CUS"+i;
        //m_rela_aud="TXT_AUDITOR_RELATIONSHIP"+i;
        m_rela_cus="TXT_RELATIONSHIP_CUS"+i;
        m_contact="TXT_CONTACT_PERSON_CUS"+i;
        m_tel="TXT_TEL_NO_CUS"+i;
        
        if(document.Form1.elements[m_name].value!="" || document.Form1.elements[m_add].value!=""  || document.Form1.elements[m_rela_cus].value!="" || document.Form1.elements[m_contact].value!="" || document.Form1.elements[m_tel].value!=""  ) {
            
            if(document.Form1.elements[m_name].value ==""){
                alert('Customer name cannot be null ');
                b_flag=1;
                break;
                //return false;
            }
            
            if(document.Form1.elements[m_add].value ==""){
                alert('Address cannot be null ');
                b_flag=1;
                break;
                //return false;
            }
            
            if(document.Form1.elements[m_rela_cus].value ==""){
                alert('Relation cannot be null ');
                b_flag=1;
                break;
                //return false;
            }
            
            if(document.Form1.elements[m_contact].value ==""){
                alert('Contact person cannot be null ');
                b_flag=1;
                break;
                //return false;
            }
            
            if(document.Form1.elements[m_tel].value ==""){
                alert('Tel No cannot be null ');
                b_flag=1;
                break;
                //return false;
            }
            
            if(lineno_customer>=2){
                
                
                if(document.Form1.elements[m_name].value.toUpperCase()==tmp_name.toUpperCase()  && document.Form1.elements[m_type].value.toUpperCase()==tmp_type.toUpperCase()){
                    b_count=b_count+1;
                    
                }
            }			
            
        }
        
    }
    
    
    if(b_count>=2){
        alert('Name cannot be duplicated ! ');
        //	document.Form1.elements[tmp_name].value='';
        b_flag=1;
        //	return false;
    }
    
    //return true;
    
    if (b_flag==0){
        return true;
    }
    else
    {
        return false;
    }
    
    
}





//--validate non related refree--------------------------------------------------------------------------------------------------------------------------------------------------//

function validate_refree(type){ 
    
    if(type=='I'){
        
        var count=0;
        var b_count=0;
        
        if(lineno_nonrelated>=2){
            
            count =lineno_nonrelated-1;
            //alert(count);
            for(var i=count;i>=0;i--){
                m_name="TXT_NAME_REFEREE"+i;
                m_rela="TXT_RELATIONSHIP3"+i;
                if(document.Form1.elements[m_name].value!=""){
                    tmp_name=document.Form1.elements[m_name].value;
                    tmp_rela=document.Form1.elements[m_rela].value;
                    break;
                }
                
            }
        }
        
        //alert(tmp_name);
        for(var i=0;i<lineno_nonrelated;i++){
            
            m_name="TXT_NAME_REFEREE"+i;
            m_rela="TXT_RELATIONSHIP3"+i;
            m_period="TXT_PERIOD"+i;
            m_desg="TXT_DESIGNATION3"+i;
            m_home_tel="TXT_HOME_TEL_NO"+i;
            m_off_tel="TXT_OFFICE_TEL_NO"+i;
            m_mob="TXT_MOBILE_NO"+i;
            
            
            if(document.Form1.elements[m_name].value!="" || document.Form1.elements[m_rela].value!=""  || document.Form1.elements[m_period].value!="" || document.Form1.elements[m_desg].value!="" || document.Form1.elements[m_home_tel].value!=""  || document.Form1.elements[m_off_tel].value!="" || document.Form1.elements[m_mob].value!=""  ) {
                
                if(document.Form1.elements[m_name].value ==""){
                    alert('Referee name cannot be null ');
                    break;
                    return false;
                }
                
                if(document.Form1.elements[m_rela].value ==""){
                    alert('Relationship cannot be null ');
                    break;
                    return false;
                }
                
                if(document.Form1.elements[m_desg].value ==""){
                    alert('Designation cannot be null ');
                    break;
                    return false;
                }
                
                
                
                if(lineno_nonrelated>=2){
                    
                    
                    if(document.Form1.elements[m_name].value.toUpperCase()==tmp_name.toUpperCase()  && document.Form1.elements[m_rela].value.toUpperCase()==tmp_rela.toUpperCase()){
                        b_count=b_count+1;
                        
                    }
                }			
                
            }
            
        }
        
        
        if(b_count>=2){
            alert('Referee name and relationship cannot be duplicated ! ');
            //	document.Form1.elements[tmp_name].value='';
            return false;
        }
        
        return true;
    }
    
    else{
        //alert('test');
        return true;
    }
    
    
}





//--validate family members--------------------------------------------------------------------------------------------------------------------------------------------------//

function validate_family(){ 
    
    var count=0;
    var b_count=0;
    var b_flag=0;
    
    
    if(lineno_family>=2){
        
        count =lineno_family-1;
        //alert(count);
        for(var i=count;i>=0;i--){
            
            m_name="TXT_NAME_F"+i;
            if(document.Form1.elements[m_name].value!=""){
                tmp_name=document.Form1.elements[m_name].value;
                
                break;
            }
            
        }
    }
    
    //alert(tmp_name);
    for(var i=0;i<lineno_family;i++){
        
        m_mem="TXT_MEMBER"+i;
        m_name="TXT_NAME_F"+i;
        m_add="TXT_ADDRESS1_F"+i;
        m_age="TXT_AGE_F"+i;
        m_tel="TXT_TELEPHONE_NO_F"+i;
        m_mob="TXT_MOBILE_NO_F"+i;
        
        
        if(document.Form1.elements[m_name].value!="" || document.Form1.elements[m_add].value!=""  || document.Form1.elements[m_age].value!="" || document.Form1.elements[m_tel].value!=""  || document.Form1.elements[m_mob].value!=""  ) {
            
            if(document.Form1.elements[m_name].value ==""){
                alert('Name cannot be null ');
                b_flag=1;
                break;
                //return false;
            }
            
            if(document.Form1.elements[m_add].value ==""){ //modified by nuwan de silva 02-08-07
                alert('Address cannot be null ');
                b_flag=1;
                break;
                //return false;
            }
            
            if(document.Form1.elements[m_age].value ==""){ //modified by nuwan de silva 02-08-07
                alert('Age cannot be null ');
                b_flag=1;
                break;
                //return false;
            }
            
            
            
            if(lineno_family>=2){
                
                
                if(document.Form1.elements[m_name].value.toUpperCase()==tmp_name.toUpperCase()  ){
                    b_count=b_count+1;
                    
                }
            }			
            
        }
        
    }
    
    
    if(b_count>=2){
        alert('Member name cannot be duplicated ! ');
        //	document.Form1.elements[tmp_name].value='';
        //	return false;
        b_flag=1;
    }
    
    if (b_flag==0){
        return true;
        
    }
    else
    {
        return false;
    }
    
    
    //return true;
}



//Added By Nuwan De Silva 26-04-2007---------------
//Purpose Validate Telephone number

function set_Telephone_Numbers(obj) {
    
    var array_Tel=new Array();
    
    var m_tel_no=''; 
    var i=0; 
    var m_val='';
    
    if(obj.value!='' && obj.value!='-'){
        
        m_tel_no=obj.value;
        
        m_tel_no=m_tel_no.toUpperCase();
        
        m_tel_no=m_tel_no.replace(' ',',');		
        
        array_Tel=m_tel_no.split(',',m_tel_no.length);
        while(i<array_Tel.length){
            
            m_val=array_Tel[i];
            
            if(m_val.length!=10){
                return false;
                
            }
            i=i+1;
        }
        
    }
    
    return true;
    
}


//Added By Nuwan De Silva 06-06-2007---------------
//Purpose set the corespondece address1
function set_Address1_Cor(obj){
    
    document.Form1.TXT_ADDRESS1_COR.value=obj.value;
}


//Added By Nuwan De Silva 06-06-2007---------------
//Purpose set the corespondece address1
function set_Address2_Cor(obj){
    document.Form1.TXT_ADDRESS2_COR.value=obj.value;
    
}






//Added By Nuwan De Silva 26-04-2007---------------
//Purpose Validate Telephone number

function Validate_Telephone_Number(obj,val){
    var msg='';
    //alert(obj.value);
    
    
    if(set_Telephone_Numbers(obj)){
        return true;
    }
    else
    {
        
        if(val=='1'){
            msg=' Telephone number should be 10 digit number';
        }
        else if(val=='2'){
            msg=' Fax number should be 10 digit number';
        }
        else if(val=='3'){
            msg=' Mobile number should be 10 digit number';
        }
        
        alert(msg);
        
        obj.value='-';
        return false;
    }
    
}

//-----------------------------------------------------------------------------------
//--modified by : delanjali----------------------------------------------------------
//--date				: 2007-06-19---------------------------------------------------------


function disable_rows_emp(no)
{
    
    for(var i=0;i<no;i++)
    {	
        m_organization='TXT_ORGANIZATION'+i
            m_tel_no='TXT_TEL_NO'+i
            m_from_date_dd='TXT_FROM_DATE_DD'+i
            m_from_date_mm='TXT_FROM_DATE_MM'+i
            m_from_date_yy='TXT_FROM_DATE_YY'+i
            
            m_to_date_dd='TXT_TO_DATE_DD'+i
            m_to_date_mm='TXT_TO_DATE_MM'+i
            m_to_date_yy='TXT_TO_DATE_YY'+i	
            
            m_designation='TXT_DESIGNATION2'+i
            m_del_but ='BUT_EMP_DEL'+i	
            document.Form1.elements[m_organization].disabled=true;                           
        document.Form1.elements[m_tel_no].disabled=true;
        document.Form1.elements[m_from_date_dd].disabled=true;
        document.Form1.elements[m_from_date_mm].disabled=true;
        document.Form1.elements[m_from_date_yy].disabled=true;
        document.Form1.elements[m_to_date_dd].disabled=true;
        document.Form1.elements[m_to_date_mm].disabled=true;
        document.Form1.elements[m_to_date_yy].disabled=true;	
        document.Form1.elements[m_designation].disabled=true;
        document.Form1.elements[m_del_but].disabled=true;	
    }	
    document.Form1.BUT_ADD_EMP.disabled=true;	
} 


function disable_rows_cus(no)
{
    for(var i=0;i<no;i++)
    {	
        
        m_name='TXT_CUSTOMER_NAME'+i
            m_type='TXT_TYPE_C'+i
            m_add='TXT_ADDRESS_CUS'+i
            m_relation='TXT_RELATIONSHIP_CUS'+i
            m_contact_p='TXT_CONTACT_PERSON_CUS'+i
            m_telno='TXT_TEL_NO_CUS'+i	
            m_but_cus='BUT_CUS_DEL'+i		
            
            document.Form1.elements[m_name].disabled=true;                           
        document.Form1.elements[m_type].disabled=true;	
        document.Form1.elements[m_add].disabled=true;
        document.Form1.elements[m_relation].disabled=true;	
        document.Form1.elements[m_contact_p].disabled=true;
        document.Form1.elements[m_telno].disabled=true;
        document.Form1.elements[m_but_cus].disabled=true;	
        
    }	
    document.Form1.BUT_ADD_CUS.disabled=true;	
} 

function header_credit(){
    lineno_credit=0; 
    e_header_credit.innerHTML +="<table  align='center' border='0' width='100%' class='table'><TR><TD WIDTH='12%' align='left'>Type </TD>"+
        "<TD WIDTH='15%' align='left'>Name Of Institution</TD>" +
        "<TD WIDTH='12%' align='left'>Contact Person</TD>" +
        "<TD WIDTH='12%' align='left'>Contract No </TD>" +
        "<TD WIDTH='12%' align='left'>Security </TD>" +
        "<TD WIDTH='12%' align='left'>Approved Amount  </TD>" +
        "<TD WIDTH='12%' align='left'>Balance Amount  </TD>" +
        "<TD WIDTH='*%' align='left'>No of Months</TD>" +
        "</TR></table>";
}	

function help_button_sub_sec() {
    document.Form1.hid_help_type.value='101'; 
    m_sql = 'm_help_TXT_SUB_CODE_sql_new'; 
    m_criteria = document.Form1.TXT_BUS_SECT.value+'@'+document.Form1.TXT_BUS_SECT_MAIN.value+'@'+'Y@'; 
    HelpBox('1','10','0'); 
} 

function help_button_bus_sec() {
    document.Form1.hid_help_type.value='102'; 
    m_sql = 'm_help_TXT_SECTOR_CODE_sql'; 
    m_criteria = document.Form1.TXT_BUS_SECT_MAIN.value+'@'+'Y@'; 
    HelpBox('1','10','0'); 
} 
function check_change(m_row) {			
    //alert('sdf'+m_row);
    var hidchk="hid_chk1_"+m_row;
    if(document.Form1.CHK_ACK.checked==false){
        document.Form1.CHK_ACK.value='0'			}
    else if(document.Form1.CHK_ACK.checked==true){
        document.Form1.CHK_ACK.value='1'
    }
    if(document.Form1.CHK_ACK.value=='1'){
        window.opener.document.Form1.elements[hidchk].checked=true
        window.opener.document.Form1.elements[hidchk].value='1'
    }
    else if(document.Form1.CHK_ACK.value=='0'){
        window.opener.document.Form1.elements[hidchk].checked=false
        window.opener.document.Form1.elements[hidchk].value='0'
    }
}

function help_button_inq() {
    document.Form1.hid_help_type.value='77'; 
    m_sql = 'm_help_TXT_INQUARY_NO'; 
    m_criteria = document.Form1.TXT_INQUARY_NO.value+'@'+'Y@'; 
    HelpBox('1','10','2');
} 

function help_value_assign_inq() { 			
    document.Form1.TXT_INQUARY_NO.value=oBj.valout[2]; 
    if(oBj.valout[9]=='INDIVIDUAL'  && document.Form1.SCREEN_NAME.value =='NEW' && document.Form1.hid_client_type.value=='I'  ){ 
        if(oBj.valout[3] != '-') 
            document.Form1.TXT_OTHER_NAME.value=oBj.valout[3];  //Modified Nuwan De Silva
        if(oBj.valout[4] != '-') 
            document.Form1.TXT_TEL_NO.value=oBj.valout[4]; 
        if(oBj.valout[5] != '-') 
            document.Form1.TXT_MOBILE_NO.value=oBj.valout[5]; 
        if(oBj.valout[6] != '-') 
            document.Form1.TXT_FAX_NO.value=oBj.valout[6]; 
        if(oBj.valout[7] != '-') 
            document.Form1.TXT_ADDRESS1_HOME.value=oBj.valout[7]; 
        if(oBj.valout[8] != '-') 
            document.Form1.TXT_CITY_CODE.value=oBj.valout[8]; 
        if(oBj.valout[19] != '-') 
            document.Form1.TXT_ADDRESS2_HOME.value=oBj.valout[19]; 
        if(data_vec[27] != '-') 
            document.Form1.TXT_SURNAME.value=oBj.valout[27];
        if(oBj.valout[16] != '-') 
            document.Form1.TXT_NIC_NO.value=oBj.valout[16]; 
        document.Form1.TXT_NIC_NO.focus(); 
    } 
    else if( oBj.valout[9]!='INDIVIDUAL'   && document.Form1.SCREEN_NAME.value =='NEW' && document.Form1.hid_client_type.value!='I') { 
        if(oBj.valout[3] != '-') 
            document.Form1.TXT_FULL_NAME_C.value=oBj.valout[3]; 
        if(oBj.valout[4] != '-') 
            document.Form1.TXT_DIRECT_TEL_NO.value=oBj.valout[4]; 
        if(oBj.valout[7] != '-') 
            document.Form1.TXT_ADDRESS1_REG.value=oBj.valout[7]; 
        if(oBj.valout[8] != '-') 
            document.Form1.TXT_CITY_CODE.value=oBj.valout[8]; 
        if(oBj.valout[9] != '-') 
            document.Form1.TXT_LEGAL_STATUS.value=oBj.valout[9]; 
        if(oBj.valout[19] != '-') 
            document.Form1.TXT_ADDRESS2_REG.value=oBj.valout[19]; 
        if(oBj.valout[16] != '-') 
            document.Form1.TXT_BUSINESS_CERTIFICATE_NO.value=oBj.valout[16];  //added by nuwan de silva 18-05-07
    } 
    document.Form1.INQUARY_LINK_BUT.disabled =false ; 
} 





function help_button_city() {
    document.Form1.hid_help_type.value='3'; 
    m_sql = 'm_help_TXT_CITY_CODE_sql'; 
    if(document.Form1.SCREEN_NAME.value=='NEW' ||document.Form1.SCREEN_NAME.value=='EDIT' || document.Form1.SCREEN_NAME.value=='DACT'){ 
        m_criteria = document.Form1.TXT_CITY_CODE.value+'@'+'Y@'; 
    } 
    else{
        m_criteria = document.Form1.TXT_CITY_CODE.value+'@'+'N@'; 
    } 
    HelpBox('1','10','2'); 
} 



function help_button_bank(lineno) { 
    // alert('lineno ' +lineno); 
    m_bank='TXT_BANK_CODE'+lineno
        document.Form1.hid_lineno.value=lineno; 
    document.Form1.hid_help_type.value='1'; 
    m_sql = 'm_help_TXT_BANK_CODE_sql'; 
    m_criteria = document.Form1.elements[m_bank].value+'@Y@'; 
    HelpBox('1','10','0'); 
}


function Prev(Start,End,Hid_No){ 
    HelpBox(Start,End,Hid_No); 
} 

function Next (Start,End,Hid_No){ 
    HelpBox(Start,End,Hid_No); 
} 

function help_button_branch(lineno) { 
    m_bank='TXT_BANK_CODE'+lineno
        m_branch='TXT_BRANCH_CODE'+lineno
        m_bank_val = document.Form1.elements[m_bank].value
    m_branch_val = document.Form1.elements[m_branch].value
    document.Form1.hid_lineno.value=lineno; 
    document.Form1.hid_help_type.value='2'; 
    m_sql = 'm_help_TXT_BRANCH_CODE_sql'; 
    m_criteria =document.Form1.elements[m_branch].value+'@'+document.Form1.elements[m_bank].value+'@Y@'; 
    HelpBox('1','10','8'); 
}


function help_value_assign_bank(lineno) { 
    m_bank='TXT_BANK_CODE'+lineno
        document.Form1.elements[m_bank].value=oBj.valout[2]; 
    document.Form1.elements['TXT_BANK_NAME'+lineno].value=oBj.valout[3]; 
    
} 			

function help_value_assign_branch(lineno) { 			
    m_branch='TXT_BRANCH_CODE'+lineno
        document.Form1.elements[m_branch].value=oBj.valout[2]; 
    document.Form1.elements['TXT_BRANCH_NAME'+lineno].value=oBj.valout[3]; 
}

function help_button_4() { 
    document.Form1.hid_help_type.value='4'; 
    m_sql = 'm_help_TXT_REGISTERED_CITY_CODE_sql'; 
    m_criteria = document.Form1.TXT_REGISTERED_CITY_CODE.value+'@Y@'; 
    HelpBox('1','10','0'); 
} 

function help_value_assign_4() { 
    document.Form1.TXT_REGISTERED_CITY_CODE.value=oBj.valout[2]; 
} 


function help_value_assign_postal_code() {
    document.Form1.TXT_POSTAL_CODE.value=oBj.valout[2];
    document.Form1.TXT_POSTAL_DESC.value=oBj.valout[3];
    
}

function help_value_assign_city() { 
    document.Form1.TXT_CITY_CODE.value=oBj.valout[2]; 
    document.Form1.TXT_CITY_DESC.value=oBj.valout[3]; 
} 

function clear_bank_code(lineno) { 
    m_bank='TXT_BANK_CODE'+lineno
        m_bank_name='TXT_BANK_NAME'+lineno
        document.Form1.elements[m_bank].value=''; 
    // document.Form1.elements[m_bank].focus(); 
    document.Form1.elements[m_bank_name].value=''; 
}

function clear_branch_code(lineno) { 
    m_branch='TXT_BRANCH_CODE'+lineno
        m_branch_name='TXT_BRANCH_NAME'+lineno
        
        document.Form1.elements[m_branch].value=''; 
    // document.Form1.elements[m_branch].focus(); 
    document.Form1.elements[m_branch_name].value=''; 
}

//added by nuwan de silva 07-08-2007===========
function validate_client_code(){ 
    //alert('tmp_status'+document.Form1.hid_temp_status.value);
    assign_help_status('Client_Code'); 
    if(document.Form1.SCREEN_NAME.value!="RACT"  ){
        m_url=""+servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_MAS_sql_validations1?chksql=m_prime_LAKDL_client_creation_client_code_validate&client_code="+document.Form1.TXT_CLIENT_CODE.value+"&tmp_help_status="+document.Form1.hid_temp_status.value+"&ac_status=Y";
    }
    else{
        //m_url=""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_LAKDL_client_creation_client_code_validate="+document.Form1.TXT_CLIENT_CODE.value+"&ac_status=N";
        m_url=""+servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_MAS_sql_validations1?chksql=m_prime_LAKDL_client_creation_client_code_validate&client_code="+document.Form1.TXT_CLIENT_CODE.value+"&tmp_help_status="+document.Form1.hid_temp_status.value+"&ac_status=N";
    }
    load_interface(m_url,'XML'); 
    //window.open(m_url);
} 

//To Choose Corporate or indivdual
//added by nuwan de silva 07-08-2007===========
function assign_data_values(){
	
    //alert('ad'+document.Form1.hid_client_type.value);
    

    if(document.Form1.hid_client_type.value=='I'){ 
        //document.Form1.TXT_TOT_INCOME.value =''; 
        //document.Form1.TXT_TOT_EXPENSE.value ='';
        //document.Form1.TXT_NET_INCOME.value ='';
        assign_help_status('H_ind'); 
		//alert('ccc');
        makeRequest(document.Form1.TXT_CLIENT_CODE); 
    }
    else
	{ 
			
        assign_help_status('H_cor'); 
        makeRequest(document.Form1.TXT_CLIENT_CODE); 
    }
    
}

//added by nuwan de silva 07-08-2007===========
function fill_rest_get_vector(data_vec,val){
    //alert('not ok');
    m_client_or_gur=val;
    b_client_status=0;
    m_client_code_value=data_vec[0];
    m_client_type_value=data_vec[1];
    document.Form1.TXT_CLIENT_TYPE.value =data_vec[1]; 
    document.Form1.hid_client_type.value=data_vec[1];
    
    if(data_vec[1]=='C'){
        b_client_status=1;
        if(m_client_or_gur=='C'){
            clear_corporate_client();
        }
        else{
            clear_corporate();
        }
        add_corporate();
    }	
    else if(data_vec[1]=='I'){
        b_client_status=1;
        clear_individual();
        add_individual();
    }	
    
}

//added by nuwan de silva 08-08-07==================	
function view_client_status(){
    //alert('b_client_status'+b_client_status);
    if(b_client_status==1 ){
        
        document.Form1.TXT_CLIENT_TYPE.value=m_client_type_value; 
        document.Form1.TXT_CLIENT_CODE.value=m_client_code_value; 
        document.Form1.hid_client_type.value=m_client_type_value; 
        
        if(document.Form1.hid_temp_status.value=="N"){
            //load_screen_status("EDIT");//commented by delanjali for ref no 830
        }
        else{
            load_screen_status("TEMP");
        }
        
        //if(b_new_data==0){
        assign_data_values();
        //}
        
    }
}

//added by nuwan de silva 08-08-07==================	
function help_client_code_validate(){
    document.Form1.hid_temp_status.value="N";
    document.Form1.hid_help_type.value="121"; 
    m_sql = "Client_code_help_client_creation"; 
    if(document.Form1.SCREEN_NAME.value=="EDIT" || document.Form1.SCREEN_NAME.value=="DACT"){ 
        m_criteria = document.Form1.TXT_CLIENT_CODE.value+"@"+"Y@"; 
    } 
    else{	m_criteria = document.Form1.TXT_CLIENT_CODE.value+"@"+"N@";} 		
    HelpBox('1','10','0'); 
}
//added by nuwan de silva 08-08-07==================	 
function help_client_code_validate_temp(){
    document.Form1.hid_temp_status.value="Y";
    document.Form1.hid_help_type.value="121"; 
    m_sql = "Client_code_help_client_creation_temp"; 
    if(document.Form1.SCREEN_NAME.value=="EDIT" || document.Form1.SCREEN_NAME.value=="DACT"){ 
        m_criteria = document.Form1.TXT_CLIENT_CODE.value+"@"+"Y@"; 
    } 
    else{	m_criteria = document.Form1.TXT_CLIENT_CODE.value+"@"+"N@";} 		
    HelpBox('1','10','0'); 
}

//added by nuwan de silva 08-08-07==================	
function help_value_assign_client_code_creation(val){
    m_client_or_gur=val;
    b_client_status=0;
    m_client_code_value=oBj.valout[2];
    m_client_type_value=oBj.valout[3];
    document.Form1.TXT_CLIENT_TYPE.value =oBj.valout[3]; 
    document.Form1.hid_client_type.value=oBj.valout[3];
    if(oBj.valout[3]=='C'){
        b_client_status=1;
        if(m_client_or_gur=='C'){
            clear_corporate_client();
        }
        else{
            clear_corporate();
        }
        add_corporate();
    }	
    else if(oBj.valout[3]=='I'){
        b_client_status=1;
        clear_individual();
        add_individual();
    }	
}


//added by nuwan de silva on 09-07-07------------
function header_company_dir(){
    lineno_company_dir = 0; 
    e_header_company_directors.innerHTML +='<table  align=\"center\" border=\"0\" width=\"100%\" class=\"table\"><TR>'+
        '<TD WIDTH=\"20%\" align=\"left\">Name </TD>'+
        '<TD WIDTH=\"25%\" align=\"left\">Address</TD>' +
        '<TD WIDTH=\"10%\" align=\"left\">NIC No</TD>' +
        '<TD WIDTH=\"6%\" align=\"left\">Stake(%)</TD>' +
        '<TD WIDTH=\"10%\" align=\"left\">No of Shares </TD>' +
        '<TD WIDTH=\"10%\" align=\"left\">Value(Rs.) </TD>' +
        '<TD WIDTH=\"13%\" align=\"left\">Position  </TD>' +
        '<TD WIDTH=\"6%\" align=\"left\">&nbsp;</TD>' +
        '</TR></table>';
}


//added by nuwan de silva on 07-09-07
function add_row_company_dir(){ 
    var b_flag=0;
    if(lineno_company_dir!=0){ 
        count=lineno_company_dir-1;
        m_name="TXT_NAME_DIR"+count;
        m_dir_add="TXT_ADDRESS_DIR"+count;
        m_nic_noo="TXT_NIC_NO_DIR"+count;
        m_stake="TXT_STAKE"+count;
        m_no_shares="TXT_NO_OF_SHARES"+count;
        m_value="TXT_VALUE"+count;
        m_position="TXT_POSITION"+count;
        
        if(document.Form1.elements[m_name].value=="") {
            alert('Director name cannot be null');
            b_flag=1;
        }
        if(document.Form1.elements[m_dir_add].value=="") {
            alert('Director address cannot be null');
            b_flag=1;
        }
        
        else if(document.Form1.elements[m_nic_noo].value=="") {
            alert('NIC number cannot be null');
            b_flag=1;
        }
        
        else if(document.Form1.elements[m_stake].value=="") {
            alert('Stake cannot be null');
            b_flag=1;
        }
        
        else if(document.Form1.elements[m_no_shares].value=="") {
            alert('No of shares cannot be null');
            b_flag=1;
        }
        
        else if(document.Form1.elements[m_value].value=="") {
            alert('Value cannot be null');
            b_flag=1;
        }
        
        else if(document.Form1.elements[m_position].value=="") {
            alert('Position cannot be null');
            b_flag=1;
        }
        
        else if(!validate_NIC_dir()) {
            b_flag=1;
        }
        
        
        else{
            b_count=0;
            
            tmp_dir_name = document.Form1.elements[m_name].value;
            tmp_nic = document.Form1.elements[m_nic_noo].value;
            
            for(var i=0;i<lineno_company_dir-1;i++){
                m_tmp_name="TXT_NAME_DIR"+i;
                m_tmp_nic="TXT_NIC_NO_DIR"+i;
                
                if(lineno_company_dir>=2){
                    if(document.Form1.elements[m_tmp_name].value!=tmp_dir_name && document.Form1.elements[m_tmp_nic].value==tmp_nic ){
                        alert('NIC No cannot be duplicated !');
                        b_flag=1;
                        b_count=1};
                    else if(document.Form1.elements[m_tmp_name].value==tmp_dir_name ){
                        alert(' Name cannot be duplicated !');
                        b_flag=1;
                        b_count=1};
                    
                    if(b_count==1){
                        break;}
                    
                } 
            }
            
        }
    }
    
    if(b_flag==0){
        e_txt_company_directors.innerHTML +='<table  border=\"0\"  align=\"center\" width=\"100%\" class=\"table\"><tr>'+									
            '<TD WIDTH=\"20%\"><input class=\"txt_input3\" type=\"text\" name=TXT_NAME_DIR'+lineno_company_dir+' maxlength=\"100\"  size=\"40\" style=\"width: 200px\" ></TD>'+
            '<TD WIDTH=\"25%\"><input class=\"txt_input3\" type=\"text\" name=TXT_ADDRESS_DIR'+lineno_company_dir+' maxlength=\"100\"  size=\"40\" style=\"width: 260px\" ></TD>'+
            '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_NIC_NO_DIR'+lineno_company_dir+' onblur=\"\"  maxlength=\"10\" size=\"12\" style=\"width: 80px\"></TD>'+
            '<TD WIDTH=\"6%\"><input class=\"txt_input5\" type=\"text\" name=TXT_STAKE'+lineno_company_dir+' onblur=\"check_number_precent(this,6)\"  maxlength=\"6\" size=\"6\" style=\"width: 50px\"></TD>'+
            '<TD WIDTH=\"10%\"><input class=\"txt_input5\" type=\"text\" name=TXT_NO_OF_SHARES'+lineno_company_dir+' onblur=\"check_number(this)\" maxlength=\"20\" size=\"15\" style=\"width: 80px\"></TD>'+
            '<TD WIDTH=\"10%\"><input class=\"txt_input5\" type=\"text\" name=TXT_VALUE'+lineno_company_dir+' onblur=\"check_number_decimal(this,20)\" maxlength=\"20\" size=\"30\" style=\"width: 100px\"></TD>'+
            '<TD WIDTH=\"13%\"><input class=\"txt_input3\" type=\"text\" name=TXT_POSITION'+lineno_company_dir+' maxlength=\"50\" size=\"34\" style=\"width: 125px\"></TD>'+
            '<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_COMP_DEL'+lineno_company_dir+' value=\"Delete\" onClick=\"del_row_company_dir('+lineno_company_dir+')\">'+
            '</td></tr></table>';
        
        lineno_company_dir = lineno_company_dir+1;
        arr_size_company = arr_size_company+1;
    }
    add_button_company_dir();
}

//added by nuwan de silva on 07-09-07---------------
function write_data_company_dir(size){
    e_txt_company_directors.innerHTML="";
    
    for(var j=0;j<size;j++){
        if(array_name_dir[j]=="" && array_nic_no_dir[j]=="" && array_stake[j]=="" && array_no_shares[j]=="" && array_value[j]=="" && array_position[j]=="" && array_add_dir[j]==""){
            e_txt_company_directors.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+									
                '<TD WIDTH=\"20%\"><input class=\"txt_input3\" type=\"text\" name=TXT_NAME_DIR'+j+' value=\"\"  maxlength=\"100\" size=\"40\" style=\"width: 200px\" ></TD>'+
                '<TD WIDTH=\"25%\"><input class=\"txt_input3\" type=\"text\" name=TXT_ADDRESS_DIR'+j+' maxlength=\"100\"  size=\"40\" style=\"width: 260px\" ></TD>'+
                '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_NIC_NO_DIR'+j+' value=\"\"  maxlength=\"10\" size=\"12\" style=\"width: 80px\"></TD>'+
                '<TD WIDTH=\"6%\"><input class=\"txt_input5\" type=\"text\" name=TXT_STAKE'+j+' value=\"\" onblur=\"check_number_precent(this,6)\"  maxlength=\"6\" size=\"6\" style=\"width: 50px\"></TD>'+
                '<TD WIDTH=\"10%\"><input class=\"txt_input5\" type=\"text\" name=TXT_NO_OF_SHARES'+j+' value=\"\" onblur=\"check_number(this)\"   maxlength=\"20\" size=\"15\" style=\"width: 80px\" ></TD>'+
                '<TD WIDTH=\"10%\"><input class=\"txt_input5\" type=\"text\" name=TXT_VALUE'+j+' value=\"\"  onblur=\"check_number_decimal(this,20)\"  maxlength=\"20\" size=\"30\" style=\"width: 100px\" ></TD>'+
                '<TD WIDTH=\"13%\"><input class=\"txt_input3\" type=\"text\" name=TXT_POSITION'+j+' value=\"\"  maxlength=\"50\" size=\"34\" style=\"width: 125px\" ></TD>'+
                '<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_COMP_DEL'+j+' value=\"Delete\" onClick=\"del_row_company_dir('+j+')\">'+
                '</td></tr></table>';
            continue;
        }
        e_txt_company_directors.innerHTML +='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+									
            '<TD WIDTH=\"20%\"><input class=\"txt_input3\" type=\"text\" name=TXT_NAME_DIR'+j+' value=\"'+array_name_dir[j]+'\"  maxlength=\"100\" size=\"40\" style=\"width: 200px\"></TD>'+
        '<TD WIDTH=\"25%\"><input class=\"txt_input3\" type=\"text\" name=TXT_ADDRESS_DIR'+j+' value=\"'+array_add_dir[j]+'\" maxlength=\"100\"  size=\"40\" style=\"width: 260px\" ></TD>'+
        '<TD WIDTH=\"10%\"><input class=\"txt_input3\" type=\"text\" name=TXT_NIC_NO_DIR'+j+' value=\"'+array_nic_no_dir[j]+'\"  maxlength=\"10\" size=\"12\" style=\"width: 80px\"></TD>'+
        '<TD WIDTH=\"6%\"><input class=\"txt_input5\" type=\"text\" name=TXT_STAKE'+j+' value=\"'+array_stake[j]+'\" onblur=\"check_number_precent(this,6)\"  maxlength=\"6\" size=\"6\" style=\"width: 50px\" ></TD>'+
        '<TD WIDTH=\"10%\"><input class=\"txt_input5\" type=\"text\" name=TXT_NO_OF_SHARES'+j+' value=\"'+array_no_shares[j]+'\" onblur=\"check_number(this)\" maxlength=\"20\" size=\"15\" style=\"width: 80px\" ></TD>'+
        '<TD WIDTH=\"10%\"><input class=\"txt_input5\" type=\"text\" name=TXT_VALUE'+j+' value=\"'+array_value[j]+'\" onblur=\"check_number_decimal(this,20)\" maxlength=\"20\" size=\"30\" style=\"width: 100px\"></TD>'+
        '<TD WIDTH=\"13%\"><input class=\"txt_input3\" type=\"text\" name=TXT_POSITION'+j+' value=\"'+array_position[j]+'\" maxlength=\"50\" size=\"34\" style=\"width: 125px\" ></TD>'+
        '<TD WIDTH=\"6%\"><input class=\"but_input\" type=\"button\" name=BUT_COMP_DEL'+j+' value=\"Delete\" onClick=\"del_row_company_dir('+j+')\">'+
            '</td></tr></table>';
    }
    lineno_company_dir=j; 
}




// added by nuwan de silva on  16-10-2008
function count_contracts(m_active_status){ 
    //alert('aaaaaaaaaaaa'+m_active_status);
    if (m_active_status=="N") {
        assign_help_status('client_count'); 
        client_contract_count=0;
        m_url=""+servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_MAS_sql_validations2?chksql=m_prime_LAKDL_count_client_contracts&client_code="+document.Form1.TXT_CLIENT_CODE.value+"";
        load_interface(m_url,'XML'); 
        //window.open(m_url);
    } 
} 

function assign_client_count(data_vec){ 
    //alert('bbbbbbbbbbbbb');
    client_contract_count=data_vec[0];
}

function validate_count_contracts(){ 
    //alert('Client Details Cant change');
    if (parseInt(client_contract_count) >=1 ){
        //alert('Client has been assigned to a activated contract,You can not change the details');
        return false;
    }else{
        return true;
    }
}

function assign_date_values_from(dval,no){
    array_from_date_dd[no]=dval.substring(0,2)
    array_from_date_mm[no]=dval.substring(3,5)
    array_from_date_yy[no]=dval.substring(6,10)
}

function assign_date_values_to(dval,no){
    array_to_date_dd[no]=dval.substring(0,2)
    array_to_date_mm[no]=dval.substring(3,5)
    array_to_date_yy[no]=dval.substring(6,10)
}



function validate_NIC_dir(){ 
    if(e_txt_company_directors.innerHTML !="") {
        count=lineno_company_dir-1;  
        m_nic_noo="TXT_NIC_NO_DIR"+count;
        if(document.Form1[m_nic_noo].value != "") { 
            if(val_nic1(document.Form1[m_nic_noo])) 
                return true ; 
            else 
                return false ; 
        }		
        else 
            return true ; 
    }
    return true ; 
} 

function disable_client_data_ind() {//added by nuwan de silva
    document.Form1.TXT_SURNAME.disabled =true; 
    document.Form1.TXT_INITIALS.disabled =true; 
    document.Form1.TXT_FULL_NAME_I.disabled =true; 
    document.Form1.TXT_OTHER_NAME.disabled =true; 
    document.Form1.TXT_TITLE.disabled =true; 
    document.Form1.TXT_NIC_NO.disabled =true;
    document.Form1.TXT_DATE_OF_BIRTH_DD.disabled =true;
    document.Form1.TXT_DATE_OF_BIRTH_MM.disabled =true;
    document.Form1.TXT_DATE_OF_BIRTH_YY.disabled =true;
    //document.Form1.TXT_CITY_CODE.disabled =true;
    //document.Form1.TXT_ADDRESS1_HOME.disabled =true;
    //document.Form1.TXT_ADDRESS2_HOME.disabled =true;
    //document.Form1.TXT_EMP_ADDRESS1.disabled =true;
    //document.Form1.TXT_EMP_ADDRESS2.disabled =true;
    document.Form1.edit.disabled =true;
    document.Form1.edit2.disabled =true;
    document.Form1.TXT_FIRST_NAME.disabled =true; 
    document.Form1.TXT_GENDER.disabled =true;
}

function disable_client_data_cor() {//added by nuwan de silva
    document.Form1.TXT_FULL_NAME_C.disabled =true; 
    //document.Form1.TXT_ADDRESS1_REG.disabled =true; 
    //document.Form1.TXT_ADDRESS2_REG.disabled =true; 
    //document.Form1.TXT_CITY_CODE.disabled =true; 
    //document.Form1.TXT_REG_OFFICE.disabled =true; 
    document.Form1.TXT_BUSINESS_CERTIFICATE_NO.disabled =true; 
    document.Form1.TXT_DATE_OF_INCORPORATION_DD.disabled =true; 
    document.Form1.TXT_DATE_OF_INCORPORATION_MM.disabled =true; 
    document.Form1.TXT_DATE_OF_INCORPORATION_YY.disabled =true; 
    document.Form1.edit.disabled =true;
    document.Form1.edit2.disabled =true;
}


function enable_client_data_ind() {//added by nuwan de silva
    document.Form1.TXT_SURNAME.disabled =false; 
    document.Form1.TXT_INITIALS.disabled =false; 
    document.Form1.TXT_FULL_NAME_I.disabled =false; 
    document.Form1.TXT_OTHER_NAME.disabled =false; 
    document.Form1.TXT_TITLE.disabled =false; 
    document.Form1.TXT_NIC_NO.disabled =false;
    document.Form1.TXT_DATE_OF_BIRTH_DD.disabled =false;
    document.Form1.TXT_DATE_OF_BIRTH_MM.disabled =false;
    document.Form1.TXT_DATE_OF_BIRTH_YY.disabled =false;
    //document.Form1.TXT_CITY_CODE.disabled =false;
    //document.Form1.TXT_ADDRESS1_HOME.disabled =false;
    //document.Form1.TXT_ADDRESS2_HOME.disabled =false;
    //document.Form1.TXT_EMP_ADDRESS1.disabled =false;
    //document.Form1.TXT_EMP_ADDRESS2.disabled =false;
    document.Form1.edit.disabled =false;
    document.Form1.edit2.disabled =false;
    document.Form1.TXT_FIRST_NAME.disabled =false;
    document.Form1.TXT_GENDER.disabled =false;
}

function enable_client_data_cor() {//added by nuwan de silva
    document.Form1.TXT_FULL_NAME_C.disabled =false; 
    //document.Form1.TXT_ADDRESS1_REG.disabled =false; 
    //document.Form1.TXT_ADDRESS2_REG.disabled =false; 
    //document.Form1.TXT_CITY_CODE.disabled =false; 
    //document.Form1.TXT_REG_OFFICE.disabled =false; 
    document.Form1.TXT_BUSINESS_CERTIFICATE_NO.disabled =false; 
    document.Form1.TXT_DATE_OF_INCORPORATION_DD.disabled =false; 
    document.Form1.TXT_DATE_OF_INCORPORATION_MM.disabled =false; 
    document.Form1.TXT_DATE_OF_INCORPORATION_YY.disabled =false; 
    document.Form1.edit.disabled =false;
    document.Form1.edit2.disabled =false;
}

// commented by udara 25-07-2016
/*
//added by ns on 03-06-2010
function enable_disable_client_data_ind(m_active_status,from_screen) {//added by nuwan de silva
//    if(from_screen=='chg_Cln_details'){
 if(from_screen=='chg_Cln_details' || from_screen=='client_verification'){
        if(m_active_status=='Y'){
            enable_client_data_ind();
        }
        else{
            disable_client_data_ind();
        }
    }
    else{
        if(m_active_status=='Y'){
            disable_client_data_ind();
        }
        else{
            enable_client_data_ind();
        }
    }
    
}
*/


// added by udara 25-07-2016

function enable_disable_client_data_ind(m_active_status,from_screen) {//added by nuwan de silva
    if(from_screen=='chg_Cln_details'|| from_screen=='client_verification'){
        if(m_active_status=='Y'){
            enable_client_data_ind();
        }
        else{
            disable_client_data_ind();
        }
    }
    else{
        if(m_active_status=='Y'){
            disable_client_data_ind();
        }
        else{
            enable_client_data_ind();
        }
    }
	
	//Added by Kanchana on 2016-07-25
	 if(from_screen=='client_verification'){
       
            set_client_help_disable();                
        }  //Endded by Kanchana on 2016-07-25 
}

// end by udara 25-07-2016

//added by ns on 03-06-2010
function enable_disable_client_data_cor(m_active_status,from_screen) {//added by nuwan de silva
    if(from_screen=='chg_Cln_details'){
        if(m_active_status=='Y'){
            enable_client_data_cor();
        }
        else{
            disable_client_data_cor();
        }
    }
    else{
        if(m_active_status=='Y'){
            disable_client_data_cor();
        }
        else{
            enable_client_data_cor();
        }
    }
    
}


// Added By Samitha Kulatilaka On 2011-12-07
function onChangeNationalityFunction() {
    
    var selectedNationalityCode = document.forms[0].elements['TXT_NATIONALITY'].value;
    
    if (selectedNationalityCode == 'SRILANKAN') {
        document.getElementById('DIV_TXT_NIC_NO').innerHTML = 'NIC No *';
        document.getElementById('DIV_TXT_PASSPORT_NO').innerHTML = 'Passport No';
    }
    else {
        document.getElementById('DIV_TXT_NIC_NO').innerHTML = 'NIC No';
        document.getElementById('DIV_TXT_PASSPORT_NO').innerHTML = 'Passport No *';
    }
    
}