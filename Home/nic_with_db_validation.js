
// ===================================================from lakdl ===========================================


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

//Old Function to validate NIC
function old_NIC_validation(object){
if (object.value != '') { 
		if(val_nic1(object) && val_nic2(object)){	// (new_val_nic1(object) && new_val_nic2(object))
			return true ; 
		}  else {  	
				//return false ; //comment by nuwan de silva for the entering old data 06-06-07 (to the requst) 	
			return true ;  
		} 
}
//new Function to validate NIC
function new_NIC_validation(object){
if (object.value != '') { 
		if(new_val_nic1(object) && val_nic2(object)){	
			return true ; 
		} else {  	
			//return false ;	
			return true ;  
		}  
}else{
		return true; 
	}

}


// Modified By Samitha Kulatilaka On 2011-12-07
function validate_NIC(object){      
m_objval = object.value;
m_length = m_objval.toString().length;
if(m_length>10){
		alert('You are entering new formatted NIC number');		
	//	new_NIC_validation(object);
			
	} else{
		alert('You are entering old formatted NIC number');
	//	old_NIC_validation(object);
	}	
       		
    } 
	


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

// ===================================================from lakdl ===========================================






// new nic validattion========================================================================================


function new_val_nic1(object)
{ 
 	
  m_objval = object.value;
    m_length = m_objval.toString().length;
   if(m_objval != '') {
    if(m_length<12)
    { 
      alert(' NIC length should be 12'); 
      object.select();
      return false; 
    } 
    else 
    {  
    for(var i = 0; i<m_length; i++) 
     { 
       m_char = m_objval.charAt(i); 
       if(i>=0 && i<=11) 
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



function new_val_nic2(object)
{  
    var m_num_days = 0;		
    m_gender = document.Form1.TXT_GENDER.value;	
    m_objval = object.value;
	
	m_year = m_objval.substring(0,4); // Get first 4 digit from the NIC
	
    m_days = parseFloat(m_objval.substring(4,7)); // Get first 5,6,7 digit from the NIC
	//alert(m_days);
    if(document.Form1.TXT_DATE_OF_BIRTH_DD.value!='' && document.Form1.TXT_DATE_OF_BIRTH_MM.value!='' && document.Form1.TXT_DATE_OF_BIRTH_YY.value!=''  ) {
    m_yyyy = document.Form1.TXT_DATE_OF_BIRTH_YY.value; 	
    m_month = parseFloat(document.Form1.TXT_DATE_OF_BIRTH_MM.value);  // DOB Month
	m_date = parseFloat(document.Form1.TXT_DATE_OF_BIRTH_DD.value); 	// DOB Date		
    m_yy = m_yyyy.substring(0,4)  // DOB Year

/*alert(m_yy);*/
		
    if( m_year != 	m_yy) { 
    	alert(' Invalid NIC No  ') ;
		document.Form1.TXT_DATE_OF_BIRTH_DD.value=""; 
		document.Form1.TXT_DATE_OF_BIRTH_MM.value="";
		document.Form1.TXT_DATE_OF_BIRTH_YY.value="";
//		document.Form1.TXT_S03_I_IDENTIFICATION.select();
		//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
		document.getElementById('DIV_TXT_DATE_OF_BIRTH').style.color = 'red';
		
        // object.value='';	 	
        // object.focus();	 			
   	 } 		
    else if( m_month == 1	){ 	

    	 if( m_gender == 'MALE' && m_days != m_date ){  	
    		alert(' Invalid NIC No  ') ;
			document.Form1.TXT_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_DATE_OF_BIRTH_YY.value="";
//			document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_TXT_DATE_OF_BIRTH').style.color = 'red';
          // object.value="";	 	
          // object.focus();	 			
           return false;	 
   	   } 	
		else if( m_gender == 'FEMALE' && m_days != (m_date+500) ){  	
    		alert(' Invalid NIC No  ') ;
			document.Form1.TXT_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_DATE_OF_BIRTH_YY.value="";
//			document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_TXT_DATE_OF_BIRTH').style.color = 'red';
          // object.value="";	 	
          // object.focus();	 			
           return false;	
   	   } 		
    	 else{  		
           return true;	
   	   } 			 
    } 	
    else if( m_month == 2	){ 		
 
      m_num_days = 31+m_date;			
    	 if( m_gender == 'MALE' && m_days != m_num_days ){  	
    		alert(' Invalid NIC No  ') ;
			document.Form1.TXT_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_DATE_OF_BIRTH_YY.value="";
	//		document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_TXT_DATE_OF_BIRTH').style.color = 'red';
          // object.value="";	 	
          // object.focus();	 			
           return false;		
   	   } 		
		else if( m_gender == 'FEMALE' && m_days != (m_num_days+500) ){  	
    		alert(' Invalid NIC No  ') ;
			document.Form1.TXT_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_DATE_OF_BIRTH_YY.value="";
		//	document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_TXT_DATE_OF_BIRTH').style.color = 'red';
          // object.value="";	 	
          // object.focus();	 			
           return false;	
   	   } 			
    	 else{  	
           return true;	
   	   } 			 	
    } 		
    else if( m_month == 3	){ 		
      m_num_days = 60+m_date;			
    	 if( m_gender == 'MALE' && m_days != m_num_days ){  	
    		alert(' Invalid NIC No  ') ;
			document.Form1.TXT_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_DATE_OF_BIRTH_YY.value="";
		//	document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_TXT_DATE_OF_BIRTH').style.color = 'red';
           //object.value="";	 	
          //object.focus();	 			
           return false;	
   	   } 		
		else if( m_gender == 'FEMALE' && m_days != (m_num_days+500) ){  	
    		alert(' Invalid NIC No  ') ;
			document.Form1.TXT_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_DATE_OF_BIRTH_YY.value="";
	//		document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_TXT_DATE_OF_BIRTH').style.color = 'red';
           //object.value="";	 	
           //object.focus();	 		
           return false;	
   	   } 			
    	 else{  	
           return true;	
   	   } 			 	
    } 			
    else if( m_month == 4	){ 		
 
    m_num_days = 91+m_date;				
    	 if( m_gender == 'MALE' && m_days != m_num_days ){  	
    		alert(' Invalid NIC No  ') ;
			document.Form1.TXT_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_DATE_OF_BIRTH_YY.value="";
	//		document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_TXT_DATE_OF_BIRTH').style.color = 'red';
           //object.value="";	 	
          // object.focus();	 		
           return false;	
   	   } 		
		else if( m_gender == 'FEMALE' && m_days != (m_num_days+500) ){  	
    		alert(' Invalid NIC No  ') ;
			document.Form1.TXT_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_DATE_OF_BIRTH_YY.value="";
	//		document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_TXT_DATE_OF_BIRTH').style.color = 'red';
          // object.value="";	 	
          // object.focus();	 			
           return false;	
   	   } 			
    	 else{  	
           return true;	
   	   } 			 	
    } 		
    else if( m_month == 5	){ 	
 
     m_num_days = 121+m_date;				
    	 if( m_gender == 'MALE' && m_days != m_num_days ){  	
    		alert(' Invalid NIC No  ') ;
			document.Form1.TXT_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_DATE_OF_BIRTH_YY.value="";
	//		document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_TXT_DATE_OF_BIRTH').style.color = 'red';
          // object.value="";	 	
          // object.focus();	 			
           return false;	
   	   } 		
		else if( m_gender == 'FEMALE' && m_days != (m_num_days+500) ){  	
    		alert(' Invalid NIC No  ') ;
			document.Form1.TXT_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_DATE_OF_BIRTH_YY.value="";
	//		document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_TXT_DATE_OF_BIRTH').style.color = 'red';
          // object.value="";	 	
           //object.focus();	 		
           return false;	
   	   } 			
    	 else{  	
           return true;	
   	   } 			 	
    } 		
    else if( m_month == 6	){ 	
 
    m_num_days = 152+m_date;				
    	 if( m_gender == 'MALE' && m_days != m_num_days ){  	
    		alert(' Invalid NIC No  ') ;
			document.Form1.TXT_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_DATE_OF_BIRTH_YY.value="";
	//		document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_TXT_DATE_OF_BIRTH').style.color = 'red';
           //object.value="";	 	
           //object.focus();	 			
           return false;	
     	 } 		
		else if( m_gender == 'FEMALE' && m_days != (m_num_days+500) ){  	
    		alert(' Invalid NIC No  ') ;
			document.Form1.TXT_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_DATE_OF_BIRTH_YY.value="";
	//		document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_TXT_DATE_OF_BIRTH').style.color = 'red';
           //object.value="";	 	
           //object.focus();	 			
           return false;	
    	 } 			
    	 else{  	
           return true;	
   	   } 			 	
    } 		
    else if( m_month == 7	){ 	
 
     m_num_days = 182+m_date;				
    	 if( m_gender == 'MALE' && m_days != m_num_days ){  	
    		alert(' Invalid NIC No  ') ;
			document.Form1.TXT_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_DATE_OF_BIRTH_YY.value="";
			document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_TXT_DATE_OF_BIRTH').style.color = 'red';
           //object.value="";	 	
           //object.focus();	 			
           return false;	
    	 } 		
		else if( m_gender == 'FEMALE' && m_days != (m_num_days+500) ){  	
    		alert(' Invalid NIC No  ') ;
			document.Form1.TXT_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_DATE_OF_BIRTH_YY.value="";
			document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_TXT_DATE_OF_BIRTH').style.color = 'red';
           //object.value="";	 	
           //object.focus();	 		
           return false;	
   	   } 			
    	 else{  	
           return true;	
   	   } 			 	
    } 		
    else if( m_month == 8	){ 
 
     m_num_days = 213+m_date;				
    	 if( m_gender == 'MALE' && m_days != m_num_days ){  	
    		alert(' Invalid NIC No  ') ;
			//document.Form1.TXT_DATE_OF_BIRTH_DD.value="";
			//document.Form1.TXT_DATE_OF_BIRTH_MM.value="";
			//document.Form1.TXT_DATE_OF_BIRTH_YY.value="";
			//document.Form1.TXT_S03_I_IDENTIFICATION.select();
			document.Form1.DIV_TXT_DATE_OF_BIRTH.value="";
           //object.value="";	 	
           //object.focus();	 		
           return false;	
    	 } 		
		else if( m_gender == 'FEMALE' && m_days != (m_num_days+500) ){  	
    		alert(' Invalid NIC No  ') ;
			//document.Form1.TXT_DATE_OF_BIRTH_DD.value="";
			//document.Form1.TXT_DATE_OF_BIRTH_MM.value="";
			//document.Form1.TXT_DATE_OF_BIRTH_YY.value="";
			//document.Form1.TXT_S03_I_IDENTIFICATION.select();
			document.Form1.DIV_TXT_DATE_OF_BIRTH.value="";
           //object.value="";	 	
           //object.focus();	 			
           return false;	
    	 } 			
    	 else{  	
           return true;	
   	   } 			 	
    } 		
    else if( m_month == 9	){ 
    m_num_days = 244+m_date;	
 
    	 if( m_gender == 'MALE' && m_days != m_num_days ){  	
    		alert(' Invalid NIC No  ') ;
			document.Form1.TXT_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_DATE_OF_BIRTH_YY.value="";
			document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_TXT_DATE_OF_BIRTH').style.color = 'red';
           //object.value="";	 	
           //object.focus();	 		
           return false;	
    	 } 		
		else if( m_gender == 'FEMALE' && m_days != (m_num_days+500) ){  	
    		alert(' Invalid NIC No  ') ;
			document.Form1.TXT_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_DATE_OF_BIRTH_YY.value="";
			document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_TXT_DATE_OF_BIRTH').style.color = 'red';
           //object.value="";	 	
           //object.focus();	 			
           return false;	
    	 } 			
    	 else{  	
           return true;	
    	 } 			 	
    } 		
    else if( m_month == 10	){ 
 
    m_num_days = 274+m_date;				
    	 if( m_gender == 'MALE' && m_days != m_num_days ){  	
    		alert(' Invalid NIC No  ') ;
			document.Form1.TXT_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_DATE_OF_BIRTH_YY.value="";
			document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_TXT_DATE_OF_BIRTH').style.color = 'red';
           //object.value="";	 	
           //object.focus();	 		
           return false;	
    	 } 		
		else if( m_gender == 'FEMALE' && m_days != (m_num_days+500) ){  	
    		alert(' Invalid NIC No  ') ;
			document.Form1.TXT_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_DATE_OF_BIRTH_YY.value="";
			document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_TXT_DATE_OF_BIRTH').style.color = 'red';
          // object.value="";	 	
          // object.focus();	 		
           return false;	
    	 } 			
    	 else{  	
           return true;	
   	  } 			 	
    } 		
    else if( m_month == 11	){ 	
 
    m_num_days = 305+m_date;				
    	 if( m_gender == 'MALE' && m_days != m_num_days ){  	
    		alert(' Invalid NIC No  ') ;
			document.Form1.TXT_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_DATE_OF_BIRTH_YY.value="";
			document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_TXT_DATE_OF_BIRTH').style.color = 'red';
           //object.value="";	 	
          // object.focus();	 			
           return false;	
    	 } 		
		else if( m_gender == 'FEMALE' && m_days != (m_num_days+500) ){  	
    		alert(' Invalid NIC No  ') ;
			document.Form1.TXT_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_DATE_OF_BIRTH_YY.value="";
			document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_TXT_DATE_OF_BIRTH').style.color = 'red';
           //object.value="";	 	
           //object.focus();	 			
           return false;	
    	 } 			
    	 else{  	
           return true;	
    	 } 			 	
    } 			
    else if( m_month == 12	){ 		
 
    m_num_days = 335+m_date;
    	 if( m_gender == 'MALE' && m_days != m_num_days ){  	
    		alert(' Invalid NIC No  ') ;
			document.Form1.TXT_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_DATE_OF_BIRTH_YY.value="";
			document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_TXT_DATE_OF_BIRTH').style.color = 'red';
           //object.value="";	 	
           //object.focus();	 		
           return false;	
    	 } 		
		else if( m_gender == 'FEMALE' && m_days != (m_num_days+500) ){  	
    		 (' Invalid NIC No  ') ;
			document.Form1.TXT_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_DATE_OF_BIRTH_YY.value="";
			document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_TXT_DATE_OF_BIRTH').style.color = 'red';
           //object.value="";	 	
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





