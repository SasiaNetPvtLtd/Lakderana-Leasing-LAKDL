
// NEW NIC Validation 
// Modified By: Kanchana Karunarathna on 2016-01-13 
// Registration of persons department change the NIC number format

//Main function to decide old validation or new
function val_nic(object){
m_objval = object.value;
m_length = m_objval.toString().length;
if(m_length>10){
alert('You are entering new formatted NIC number');
	new_NIC_validation(object);
} else{
	alert('You are entering old formatted NIC number');
	old_NIC_validation(object);	
}
}
//-----------------------------------------------------------
//Old Function to validate NIC
function old_NIC_validation(object){
val_nic_old(object);
}
//new Function to validate NIC
function new_NIC_validation(object){
validateNIC(object);
}

function val_nic_old(object){
m_objval = object.value;
m_length = m_objval.toString().length;

if(m_length<10){
alert(" NIC length should be 10");
object.focus();
return false;
}
else{
for(var i = 0; i<m_length; i++){
m_char = m_objval.charAt(i);
if(i==9){
if(m_char != 'v' && m_char != 'V' && m_char != 'x' && m_char != 'X'){
alert("NIC Final Character should be X or V ");
object.focus();
return false;
}
}
else if(i>=0 && i<=8){
if(isNaN(m_char)){
i++;
alert("NIC contains an Invalid Character at position "+i+" ?");
object.focus();
return false;
//break;

}
}

}
}
return false;

}
//-------------------------------------------------------------------


function validateNIC(object) {
	var m_expression = new RegExp("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]");
	var m_value = object.value;
	if ((object.value.length > 0) && ((object.value.length != 12) || (m_expression.test(m_value) == false))) {
		alert('NIC is invalid.');
		var m_object_name = object.name;
		setTimeout('document.forms[0].elements[\'' + m_object_name + '\'].select()', 1);
		return false;
	}
	else {
		object.value = m_value.toUpperCase();
		return true;
	}
}



























/*  //commented by kanchana
//Added By: Samith Dilshan
function validate_NIC_Days(object){
	//alert('1');
	if(document.Form1.TXT_S03_I_DATE_OF_BIRTH_DD.value!='' && document.Form1.TXT_S03_I_DATE_OF_BIRTH_MM.value!='' && document.Form1.TXT_S03_I_DATE_OF_BIRTH_YY.value!=''){
		//alert('11');
		validate_NIC(object);
	}
}


//Added By: Samith Dilshan
function validate_NIC_Months(object){
	//alert('2');
	if(document.Form1.TXT_S03_I_DATE_OF_BIRTH_DD.value!='' && document.Form1.TXT_S03_I_DATE_OF_BIRTH_MM.value!='' && document.Form1.TXT_S03_I_DATE_OF_BIRTH_YY.value!=''){
		//alert('22');
		validate_NIC(object);
	}
}

		
function validate_NIC(object){
	//alert('223344');
  //alert("fwefe");
  //alert(object.value);
  if(object.value != '') {  	
     if(val_nic1(object) && val_nic2(object)){	
     return true ; 
	   } 		
     else {  	
     //return false ; //comment by nuwan de silva for the entering old data 06-06-07 (to the requst) 	
			return true ;  
	   } 		
  } 		
} 


function val_nic1(object)
{ 
 if (document.Form1.TXT_S03_I_IDENTIFICATION_TYPE.value == 'NIC'){	
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
}


function val_nic2(object)
{  
    var m_num_days = 0;		
    m_gender = document.Form1.TXT_S03_I_GENDER.value;	
    m_objval = object.value;
	
	m_year = m_objval.substring(0,4); // Get first 4 digit from the NIC
	
    m_days = parseFloat(m_objval.substring(4,7)); // Get first 5,6,7 digit from the NIC
	//alert(m_days);
    if(document.Form1.TXT_S03_I_DATE_OF_BIRTH_DD.value!='' && document.Form1.TXT_S03_I_DATE_OF_BIRTH_MM.value!='' && document.Form1.TXT_S03_I_DATE_OF_BIRTH_YY.value!=''  ) {
    m_yyyy = document.Form1.TXT_S03_I_DATE_OF_BIRTH_YY.value; 	
    m_month = parseFloat(document.Form1.TXT_S03_I_DATE_OF_BIRTH_MM.value);  // DOB Month
	m_date = parseFloat(document.Form1.TXT_S03_I_DATE_OF_BIRTH_DD.value); 	// DOB Date		
    m_yy = m_yyyy.substring(0,4)  // DOB Year
*/ //commented by kanchana.
/*alert(m_yy);*/
/* //commented by kanchana		
    if( m_year != 	m_yy) { 
    	alert(' Invalid NIC No  ') ;
		document.Form1.TXT_S03_I_DATE_OF_BIRTH_DD.value="";
		document.Form1.TXT_S03_I_DATE_OF_BIRTH_MM.value="";
		document.Form1.TXT_S03_I_DATE_OF_BIRTH_YY.value="";
		document.Form1.TXT_S03_I_IDENTIFICATION.select();
		//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
		document.getElementById('DIV_S03_I_IDENTIFICATION').style.color = 'red';
		
        // object.value='';	 	
        // object.focus();	 			
   	 } 		
    else if( m_month == 1	){ 	

    	 if( m_gender == 'MALE' && m_days != m_date ){  	
    		alert(' Invalid NIC No  ') ;
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_YY.value="";
			document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_S03_I_IDENTIFICATION').style.color = 'red';
          // object.value="";	 	
          // object.focus();	 			
           return false;	 
   	   } 	
		else if( m_gender == 'FEMALE' && m_days != (m_date+500) ){  	
    		alert(' Invalid NIC No  ') ;
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_YY.value="";
			document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_S03_I_IDENTIFICATION').style.color = 'red';
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
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_YY.value="";
			document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_S03_I_IDENTIFICATION').style.color = 'red';
          // object.value="";	 	
          // object.focus();	 			
           return false;		
   	   } 		
		else if( m_gender == 'FEMALE' && m_days != (m_num_days+500) ){  	
    		alert(' Invalid NIC No  ') ;
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_YY.value="";
			document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_S03_I_IDENTIFICATION').style.color = 'red';
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
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_YY.value="";
			document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_S03_I_IDENTIFICATION').style.color = 'red';
           //object.value="";	 	
          //object.focus();	 			
           return false;	
   	   } 		
		else if( m_gender == 'FEMALE' && m_days != (m_num_days+500) ){  	
    		alert(' Invalid NIC No  ') ;
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_YY.value="";
			document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_S03_I_IDENTIFICATION').style.color = 'red';
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
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_YY.value="";
			document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_S03_I_IDENTIFICATION').style.color = 'red';
           //object.value="";	 	
          // object.focus();	 		
           return false;	
   	   } 		
		else if( m_gender == 'FEMALE' && m_days != (m_num_days+500) ){  	
    		alert(' Invalid NIC No  ') ;
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_YY.value="";
			document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_S03_I_IDENTIFICATION').style.color = 'red';
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
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_YY.value="";
			document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_S03_I_IDENTIFICATION').style.color = 'red';
          // object.value="";	 	
          // object.focus();	 			
           return false;	
   	   } 		
		else if( m_gender == 'FEMALE' && m_days != (m_num_days+500) ){  	
    		alert(' Invalid NIC No  ') ;
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_YY.value="";
			document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_S03_I_IDENTIFICATION').style.color = 'red';
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
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_YY.value="";
			document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_S03_I_IDENTIFICATION').style.color = 'red';
           //object.value="";	 	
           //object.focus();	 			
           return false;	
     	 } 		
		else if( m_gender == 'FEMALE' && m_days != (m_num_days+500) ){  	
    		alert(' Invalid NIC No  ') ;
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_YY.value="";
			document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_S03_I_IDENTIFICATION').style.color = 'red';
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
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_YY.value="";
			document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_S03_I_IDENTIFICATION').style.color = 'red';
           //object.value="";	 	
           //object.focus();	 			
           return false;	
    	 } 		
		else if( m_gender == 'FEMALE' && m_days != (m_num_days+500) ){  	
    		alert(' Invalid NIC No  ') ;
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_YY.value="";
			document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_S03_I_IDENTIFICATION').style.color = 'red';
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
			//document.Form1.TXT_S03_I_DATE_OF_BIRTH_DD.value="";
			//document.Form1.TXT_S03_I_DATE_OF_BIRTH_MM.value="";
			//document.Form1.TXT_S03_I_DATE_OF_BIRTH_YY.value="";
			//document.Form1.TXT_S03_I_IDENTIFICATION.select();
			document.Form1.TXT_S03_I_IDENTIFICATION.value="";
           //object.value="";	 	
           //object.focus();	 		
           return false;	
    	 } 		
		else if( m_gender == 'FEMALE' && m_days != (m_num_days+500) ){  	
    		alert(' Invalid NIC No  ') ;
			//document.Form1.TXT_S03_I_DATE_OF_BIRTH_DD.value="";
			//document.Form1.TXT_S03_I_DATE_OF_BIRTH_MM.value="";
			//document.Form1.TXT_S03_I_DATE_OF_BIRTH_YY.value="";
			//document.Form1.TXT_S03_I_IDENTIFICATION.select();
			document.Form1.TXT_S03_I_IDENTIFICATION.value="";
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
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_YY.value="";
			document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_S03_I_IDENTIFICATION').style.color = 'red';
           //object.value="";	 	
           //object.focus();	 		
           return false;	
    	 } 		
		else if( m_gender == 'FEMALE' && m_days != (m_num_days+500) ){  	
    		alert(' Invalid NIC No  ') ;
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_YY.value="";
			document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_S03_I_IDENTIFICATION').style.color = 'red';
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
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_YY.value="";
			document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_S03_I_IDENTIFICATION').style.color = 'red';
           //object.value="";	 	
           //object.focus();	 		
           return false;	
    	 } 		
		else if( m_gender == 'FEMALE' && m_days != (m_num_days+500) ){  	
    		alert(' Invalid NIC No  ') ;
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_YY.value="";
			document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_S03_I_IDENTIFICATION').style.color = 'red';
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
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_YY.value="";
			document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_S03_I_IDENTIFICATION').style.color = 'red';
           //object.value="";	 	
          // object.focus();	 			
           return false;	
    	 } 		
		else if( m_gender == 'FEMALE' && m_days != (m_num_days+500) ){  	
    		alert(' Invalid NIC No  ') ;
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_YY.value="";
			document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_S03_I_IDENTIFICATION').style.color = 'red';
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
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_YY.value="";
			document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_S03_I_IDENTIFICATION').style.color = 'red';
           //object.value="";	 	
           //object.focus();	 		
           return false;	
    	 } 		
		else if( m_gender == 'FEMALE' && m_days != (m_num_days+500) ){  	
    		 (' Invalid NIC No  ') ;
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_DD.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_MM.value="";
			document.Form1.TXT_S03_I_DATE_OF_BIRTH_YY.value="";
			document.Form1.TXT_S03_I_IDENTIFICATION.select();
			//document.Form1.TXT_S03_I_IDENTIFICATION.value="";
			document.getElementById('DIV_S03_I_IDENTIFICATION').style.color = 'red';
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

} */	 
