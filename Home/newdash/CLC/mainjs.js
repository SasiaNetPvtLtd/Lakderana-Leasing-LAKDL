/****************************************
* Copyright 2006 Ex-designz.net & Myasp-net.com
* JavaScript written by: Dexter Zafra
****************************************/

//--------------------------------------------------------//
// Handle PopUp Window
function openWinReqPassword(url) 
 {
 popupWin = window.open(url,'new_page','width=430,height=230,toolbar=no,menubar=no,location=no,scrollbars=no,resizable=no')
}
//--------------------------------------------------------//

//--------------------------------------------------------//
// Multiple Functions to handle element OnFocus event on Login form
function UnameFocus() 
 {
   document.getElementById('username').style.backgroundColor = '#FFF9EC'; //Make the textbox color lightyellow
   document.getElementById('lbluname').style.color = '#000000'; //Make the label text color black
   document.getElementById('lbluname').innerHTML = 'Username:'; //On error raised textbox focus, assign label default text 
}
function CompanyFocus() 
 {
   document.getElementById('company').style.backgroundColor = '#FFF9EC'; //Make the textbox color lightyellow
   document.getElementById('lblcompany').style.color = '#000000'; //Make the label text color black
   document.getElementById('lblcompany').innerHTML = 'Company:'; //On error raised textbox focus, assign label default text 
}
function PassWordFocus() 
 {
   document.getElementById('password').style.backgroundColor = '#FFF9EC'; 
   document.getElementById('lblpass').style.color = '#000000';
   document.getElementById('lblpass').innerHTML = 'Password:';
}
// Handle Login Form Validation
function LoginValidate(LogForm) 
 {
   var LoginPass = LogForm.elements['password'].value;
   var LoginName = LogForm.elements['username'].value;
if (LoginName == "" && LoginPass == "") 
  {
     // Change the background color of the textbox,border as well as label text
     document.getElementById('username').style.backgroundColor='#FFF4F4'; 
     document.getElementById('username').style.border = '1px solid #CC0000';
     document.getElementById('lbluname').style.color = '#CC0000';
     document.getElementById('lbluname').innerHTML = 'Username:';
     document.getElementById('password').style.backgroundColor='#FFF4F4'; 
     document.getElementById('password').style.border = '1px solid #CC0000';
     document.getElementById('lblpass').style.color = '#CC0000';
     document.getElementById('lblpass').innerHTML = 'Password:';
     alert("Please fill in the empty fields.\n- Username\n- Password");
     return false;
}
// Check Username login
 if (LoginName == "") // if is empty/blank alert
  {
     alert("Please enter your username");
     document.getElementById('username').style.backgroundColor='#FFF4F4'; //Change the background color of the textbox
     document.getElementById('username').style.border = '1px solid #CC0000';
     document.getElementById('lbluname').style.color = '#CC0000';
     //document.getElementById('lbluname').innerHTML = 'Empty Field:';
     return false;
  }
// Login Password
 if (LoginPass == "")
  {
     alert("Please enter your password");   
     document.getElementById('password').style.backgroundColor='#FFF4F4'; 
     document.getElementById('password').style.border = '1px solid #CC0000';
     document.getElementById('lblpass').style.color = '#CC0000';
     //document.getElementById('lblpass').innerHTML = 'Empty Field:';
     return false;
  }
return true;
}

// Handle Request Password Form Validation
function ReqPassVal(ReqPass) 
 {
 // Email
var UserReqEMail = ReqPass.elements['email'].value;
 if (UserReqEMail == "")
  {
     alert("You must enter an email address");
     ReqPass.elements['email'].style.backgroundColor='#FFF9EC';
     return false;
  }
else if (!(UserReqEMail.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1))
 {
    alert("E-mail address is not valid.\n Please enter a valid email address.");
    ReqPass.elements['email'].style.backgroundColor='#FFF9EC';
    return false ;
}
return true;
}

//--------------------------------------------------------//
// Multiple Functions to handle element OnFocus event on Contact form
function UnameContactFocus() 
 {
   document.getElementById('fname').style.backgroundColor = '#FFF9EC'; 
   document.getElementById('lblconuname').style.color = '#000000';
   document.getElementById('lblconuname').innerHTML = 'Your name:'; 
}
function UemailContactFocus() 
 {
   document.getElementById('email').style.backgroundColor = '#FFF9EC'; 
   document.getElementById('lblconemail').style.color = '#000000';
   document.getElementById('lblconemail').innerHTML = 'Email:';
}
function UcommentContactFocus() 
 {
   document.getElementById('comments').style.backgroundColor = '#FFF9EC'; 
   document.getElementById('lblcomment').style.color = '#000000';
   document.getElementById('lblcomment').innerHTML = 'Comments:';
}
// Handle Contact Form Validation
function ContactVal(ConForm) 
 {
 var FirstName = ConForm.elements['fname'].value;
 var UserEMail = ConForm.elements['email'].value;
 var ConComments = ConForm.elements['comments'].value;
if (FirstName == "" && UserEMail == "" && ConComments =="") 
  {
     // Change the background color of the textbox,border as well as label text
     document.getElementById('fname').style.backgroundColor='#FFF4F4'; 
     document.getElementById('fname').style.border = '1px solid #CC0000';
     document.getElementById('lblconuname').style.color = '#CC0000';
     document.getElementById('lblconuname').innerHTML = 'Empty Field:';
     document.getElementById('email').style.backgroundColor='#FFF4F4'; 
     document.getElementById('email').style.border = '1px solid #CC0000';
     document.getElementById('lblconemail').style.color = '#CC0000';
     document.getElementById('lblconemail').innerHTML = 'Empty Field:';
     document.getElementById('comments').style.backgroundColor='#FFF4F4'; 
     document.getElementById('comments').style.border = '1px solid #CC0000';
     document.getElementById('lblcomment').style.color = '#CC0000';
     document.getElementById('lblcomment').innerHTML = 'Empty Field:';
     alert("Please fill in the empty fields.\n- Your name\n- Email\n- Comments");
     return false;
}
// Contact First name
 if (FirstName == "")
  {
     alert("You must enter your name");
     document.getElementById('fname').style.backgroundColor='#FFF4F4'; 
     document.getElementById('fname').style.border = '1px solid #CC0000';
     document.getElementById('lblconuname').style.color = '#CC0000';
     document.getElementById('lblconuname').innerHTML = 'Empty Field:';
     return false;
  }
// Allow only letters 
else if (FirstName.search(/^[A-Z ]+$/i) == -1) 
  {
     alert ("Your name contained numbers. Only alphabetic characters are allowed..\n Please try again.");
     document.getElementById('fname').style.backgroundColor='#FFF4F4'; 
     document.getElementById('fname').style.border = '1px solid #CC0000';
     document.getElementById('lblconuname').style.color = '#CC0000';
     document.getElementById('lblconuname').innerHTML = 'Illegal Char:';
     return false;
} 
 // Contact Email
 if (UserEMail == "")
  {
     alert("You must enter an email address");
     document.getElementById('email').style.backgroundColor='#FFF4F4'; 
     document.getElementById('email').style.border = '1px solid #CC0000';
     document.getElementById('lblconemail').style.color = '#CC0000';
     document.getElementById('lblconemail').innerHTML = 'Empty Field:';
     return false;
  }
else if (!(UserEMail.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1))
 {
    alert("E-mail address is not valid.\n Please enter a valid email address.");
    document.getElementById('email').style.backgroundColor='#FFF4F4'; 
    document.getElementById('email').style.border = '1px solid #CC0000';
    document.getElementById('lblconemail').style.color = '#CC0000';
    document.getElementById('lblconemail').innerHTML = 'Invalid Email:';
    return false ;
}
 // Contact Comments Field
 if (ConComments == "")
  {
     alert("You must a comments or questions");
     document.getElementById('comments').style.backgroundColor='#FFF4F4'; 
     document.getElementById('comments').style.border = '1px solid #CC0000';
     document.getElementById('lblcomment').style.color = '#CC0000';
     document.getElementById('lblcomment').innerHTML = 'Empty Field:';
     return false;
  }
return true;
}

//--------------------------------------------------------//
// Handle Article Submission Form Validation
function ArticleSubmissionVal(ArtForm) 
 {
// Article Title
 var ATitle = ArtForm.elements['arttitle'].value;
 if (ATitle == "")
  {
     alert("You must enter an article title");
     ArtForm.elements['arttitle'].style.backgroundColor='#FFF9EC';
     return false;
}
 // Main Content
var AContent = ArtForm.elements['maincontent'].value;
 if (AContent == "")
  {
     alert("You must enter a main content");
     ArtForm.elements['maincontent'].style.backgroundColor='#FFF9EC';
     return false;
}
 // Summary
var ASummary = ArtForm.elements['summary'].value;
 if (ASummary == "")
  {
     alert("You must enter a summary");
     ArtForm.elements['summary'].style.backgroundColor='#FFF9EC';
     return false;
}
 // Email
var ArtEMail = ArtForm.elements['email'].value;
 if (ArtEMail == "")
  {
     alert("You must enter an email address");
     ArtForm.elements['email'].style.backgroundColor='#FFF9EC';
     return false;
  }
else if (!(ArtEMail.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1))
 {
    alert("E-mail address is not valid.\n Please enter a valid email address.");
    ArtForm.elements['email'].style.backgroundColor='#FFF9EC';
    return false ;
}
 // Author
var Aauthor = ArtForm.elements['author'].value;
 if (Aauthor == "")
  {
     alert("You must enter an author/publisher name");
     ArtForm.elements['author'].style.backgroundColor='#FFF9EC';
     return false;
}
return true;
}