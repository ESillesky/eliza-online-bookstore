
fieldset { 
    border:4px solid #6e6658;
    border-radius:10px;
        padding-top: .5em; 
        padding-bottom:.8em;
        margin-bottom:.7em;
        padding-left:1.1em;
}
#biglogo { 
    width: 600px;
    margin-left: 10em;
    margin-bottom:-3em;
  }
legend {
    font-size:20px;
    padding:.5em;
    text-align:center;
}
#customerinfo input, #addressinfo input, #paymentinfo input {
width:18em;
padding:.5em;
margin-left: 0.5em;
margin-top:.5em;
margin-bottom: .35em;
background-color: #88bdbc;
color: white;
font-family: 'Dosis';
border-color:  #112d32;
border-radius:10px;
}

#customerinfo input::placeholder, #addressinfo input::placeholder{
     color:white;
 }
#customerinfo input::-webkit-input-placeholder:hover {
    background-color: #112d32;
}
#paymentinfo input::-webkit-input-placeholder:hover {
    background-color: #112d32;
}
#addressinfo input::-webkit-input-placeholder:hover {
    background-color: #112d32;
}
#membership select:hover, #customerinfo input:hover, #customerinfo placeholder:hover {
    background-color: #112d32;
}
#addressinfo input:hover, #paymentinfo input:hover { 
    background-color: #112d32;
}
#customerinfo label, #addressinfo label, #paymentinfo label {
    float:left; 
    width:9em; 
    text-align:right; 
    margin-top:.8em;
}
#paymentinfo select { 
    width:19.5em;
    padding:.5em;
    margin-left: .5em;
    margin-top:.5em;
    margin-bottom: .35em;
    background-color: #88bdbc;
    color: white;
    font-family: 'Dosis';
    border-color:  #112d32;
    border-radius:10px;
    border-width: .2em;
}

#processinfo input {
    width:150px;
    padding: .5em;
    margin-left:2em;
    border-radius: 10px;
    background-color:#88bdbc;
    border-color:#88bdbc;
    color:white;
    font-family:'Dosis';
    font-size: 20px;
}
#processinfo #submit {
    margin-left:18em;
}

footer { 
    padding-top: 2em;
}

#cancelfoot { 
    color: #112d32;
    margin-left: 26.5em;
    font-family:'Dosis';
    font-size: 20px;
}