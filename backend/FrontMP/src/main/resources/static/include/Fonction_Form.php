<?php
/* Pour inclure ce fichier:
	require_once('/include/Fonction_Form.php');
*/

function open_form($action="",$type=NULL) {
	$mot='<br><br><form ';
	if($action!=""){
		$mot = $mot.'action='.$action;
	}	
	if($type==NULL){
		$mot = $mot.' method="POST" align="center">';
	}else{
		$mot = $mot.' method="POST" align="center" enctype="multipart/form-data">';
	}
	echo $mot;
}
function close_form (){
	echo'</form>';
}
function champ_texte ($name, $val,$max=350,$value=NULL){	//Un champ de texte avec bootstrap
	if($value==NULL){
		echo '<div class="col-sm-4">';
      		echo '<input type="text" class="form-control" placeholder='.$name.' name= '.$val.' maxlength= '.$max.' /><BR>';
    		echo'</div>';
		
	}else{	
		echo '<div class="col-sm-4">';
		echo '<input type="text" class="form-control" placeholder='.$name.' name= '.$val.' value='.$value.' maxlength= '.$max.' /><BR>';
		echo'</div>';
	}
}
function multi_line($name, $val, $value=NULL){
	if($value==NULL){
		echo '<div class="col-sm-4">';
			echo ' : <textarea class="form-control" placeholder='.$name.' rows="3" name='.$val.'></textarea><br/>';
		echo'</div>';
	}else{
		echo '<div class="col-sm-4">';
			echo ' : <textarea class="form-control" placeholder='.$name.' rows="3" name='.$val.' value='.$value.' ></textarea><br/>';
		echo'</div>';
	}
}
function champ_pass ($name, $val, $value=NULL){//Un champ password avec bootstratp.
	if($value==NULL){
		echo '<div class="col-sm-4">';
		echo '<input type="password" class="form-control" placeholder='.$name.' name= "'.$val.'" /><BR>';
		echo'</div>';
	}else{
		echo '<div class="col-sm-4">';
		echo '<input type="password" class="form-control" placeholder='.$name.' name='.$val.' value='.$value.'/><BR>';
		echo'</div>';
	}
}
	
function open_select($name,$val){
	echo '<div class="form-group col-sm-4">';
	echo '<select class="form-control"  name='.$val.'>';
}
function select_option($name) {
	echo '<option>'.$name.'</option>';
}
function select_time($name,$val){
	$mot= $name.' : <select name='.$val.'>';
	for($i=0; $i<24;$i++)
	{
		$mot= $mot.'<option> '.$i.':00 </option>';
		$mot= $mot.'<option> '.$i.':15 </option>';
		$mot= $mot.'<option> '.$i.':30 </option>';
		$mot= $mot.'<option> '.$i.':45 </option>';
	}
	$mot= $mot.'</select><BR>';
	echo $mot;
}
function select_day(){
	$mot="";
	for($i=1; $i<=31;$i++)
	{
		if ($i < 10){           
			$mot= $mot."<option>0".$i."<BR></option>";
		}
		else {
			$mot= $mot."<option>".$i."<BR></option>";
		}
	}
	echo $mot;
}
function select_month(){
	$mot="";
	for($d=1; $d<=12;$d++)
	{
		
		if ($d < 10){           
			$mot= $mot."<option>0".$d."<BR></option>";
		}
		else {
			$mot= $mot."<option>".$d."<BR></option>";
		}
	}
	echo $mot;
}
function select_year(){
	$mot="";
	$date = date('Y');
	for ($y=date('Y')+10; $y>=$date; $y--) {
		if($y==$date){		
			$mot= $mot.'<option selected="selected"><br>'.$y.'<BR></option>';
		}else{
			$mot= $mot.'<option><br>'.$y.'<BR></option>';
		} 
	}
	echo $mot;
}
function select_date($name,$JJ,$MM,$AA){
	echo $name.' : <select name='.$JJ.'>';
		select_day();
		echo '</select>';
	echo ' <select name='.$MM.'>';
		select_month();
		echo '</select>';
	echo ' <select name='.$AA.'>';
		select_year();
		echo '</select>';
	echo ' <BR>';
}
function close_select() {
	echo '</select>';
	echo '</div>';
}	
function case_option($name,$val, $choix = 2) {
	if($choix == 1){
		echo $name .' : <input type="button" name='.$val.' value='.$val.' /><BR>';
	}else{
		echo '<input type="radio" name='.$val.' value='.$name.'/>'.$name;
	}
}
function checkbox($val,$name,$valeur=NULL) {
	if($valeur==NULL){
		echo'<div class="form-check-inline">';
	  		echo'<label class="form-check-label">';
				echo '<input class="form-check-input" type="checkbox" name='.$name.'>'.$val;
			echo'</label>';
		echo'</div>';
	}else{
		echo'<div class="form-check-inline">';
	  		echo'<label class="form-check-label">';
				echo '<input class="form-check-input" type="checkbox" name='.$name.' value='.$valeur.' >'.$val;
			echo'</label>';
		echo'</div>';
	}
}
function use_file($name=" "){
	echo $name.' : <input type="file" name="image" /><BR>';
}
function sub_and_res() {	//Boutons de validation et de reinitialisation
	echo'<BR><input type="submit" class="btn btn-default" value="Valider" name="Valider">';
	echo'<input type="reset" class="btn btn-default" value="Reset" name="Reset">';
}
function Réprimande(){
	echo"<h3 align = 'center'><br/>Veuillez remplir tout les champs</h3>";
}
