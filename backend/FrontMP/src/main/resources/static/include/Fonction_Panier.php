<?php
	function New_Panier()
	{
		if(!isset($_SESSION['panier']))
		{
			$_SESSION['panier']= array();
			$_SESSION['panier']['Trajet'] = array();
			$_SESSION['panier']['ID_Voyage'] = array();
			$_SESSION['panier']['NbVoyage'] = array();
			$_SESSION['panier']['PrixVoyage'] = array();
			$_SESSION['panier']['TAXE'] = array();
			$_SESSION['panier']['Place_Voyage'] = array();
			$_SESSION['panier']['Valider'] = false;
			
		}
		return isset($_SESSION['panier']);
	}
	function Ajout_Produit($Trajet,$NbVoyage,$PrixVoyage,$ID,$TAXE,$Place)
	{
		if(isset($_SESSION['panier']) && !Valider())
		{
			$IndexIDProduit= array_search($ID,$_SESSION['panier']['ID_Voyage']);
			$first=0;
			if(isset($_SESSION['panier']['ID_Voyage'][0])){
				$first=($_SESSION['panier']['ID_Voyage'][0]==$ID);
			}
			if($IndexIDProduit || $first)
			{
				if(($_SESSION['panier']['NbVoyage'][$IndexIDProduit]+$NbVoyage) < $Place){
					$_SESSION['panier']['NbVoyage'][$IndexIDProduit]+= $NbVoyage;
				}
			}
			else
			{
				$_SESSION['panier']['Trajet'][]= $Trajet;
				$_SESSION['panier']['NbVoyage'][]= $NbVoyage;
				$_SESSION['panier']['PrixVoyage'][]= $PrixVoyage;
				$_SESSION['panier']['ID_Voyage'][]= $ID;
				$_SESSION['panier']['TAXE'][] = $TAXE;
				$_SESSION['panier']['Place_Voyage'][] = $Place;
			}
		}
	}
	function Modif_NbVoyage($ID,$NbProduit)
	{
		if(New_Panier())
		{
			if($NbProduit){
				$IndexIDProduit= array_search($ID,$_SESSION['panier']['ID_Voyage']);
				if($IndexIDProduit|$IndexIDProduit==0){
					$Place=$_SESSION['panier']['Place_Voyage'][$IndexIDProduit];
					$NbProduit=$_SESSION['panier']['NbVoyage'][$IndexIDProduit]+$NbProduit;
					if( $NbProduit>0){
						if($Place>=$NbProduit){
							$_SESSION['panier']['NbVoyage'][$IndexIDProduit]= $NbProduit;
						}else{
							echo'Impossible de d\'augmenter la quantité en raison de Place insufisant';
						}
					}else{
						echo'Vous devez commander au moins un produit';
					}
				}
				
			}
		}
	}
	function Prix_Panier(){
		$Prix=0;
		$lenght= count($_SESSION['panier']['PrixVoyage']);
		$i=0;
		reset($_SESSION['panier']['PrixVoyage']);
		reset($_SESSION['panier']['NbVoyage']);
		for($i; $i<$lenght;$i++){
			$Prixprod = current($_SESSION['panier']['PrixVoyage']);
			$nbprod= current($_SESSION['panier']['NbVoyage']);
			$Prix += ($Prixprod * $nbprod);
			next($_SESSION['panier']['PrixVoyage']);
			next($_SESSION['panier']['NbVoyage']);
		}
		return $Prix + Prix_TAXE();
	}
	function Prix_TAXE(){
		$Prix=0;
		$lenght= count($_SESSION['panier']['TAXE']);
		$i=0;
		reset($_SESSION['panier']['TAXE']);
		reset($_SESSION['panier']['NbVoyage']);
		for($i; $i<$lenght;$i++){
			$TAXEprod = current($_SESSION['panier']['TAXE']);
			$nbprod= current($_SESSION['panier']['NbVoyage']);
			$Prix += ($TAXEprod * $nbprod);
			next($_SESSION['panier']['TAXE']);
			next($_SESSION['panier']['NbVoyage']);
		}
		return $Prix;
	}
	function Supp_Voyage($ID)
	{
		if(New_Panier())
		{
			$newtab=array();
			$newtab['Trajet']=array();
			$newtab['ID_Voyage']=array();
			$newtab['NbVoyage']=array();
			$newtab['PrixVoyage']=array();
			$newtab['TAXE']=array();
			$newtab['Place_Voyage']=array();
			$newtab['Valider']=false;
			
			$lenght= count($_SESSION['panier']['ID_Voyage']);
			$i=0;
			for($i; $i<$lenght;$i++){
				
				if($_SESSION['panier']['ID_Voyage'][$i]!=$ID){
					array_push($newtab['Trajet'], $_SESSION['panier']['Trajet'][$i]);
					array_push($newtab['ID_Voyage'], $_SESSION['panier']['ID_Voyage'][$i]);
					array_push($newtab['NbVoyage'], $_SESSION['panier']['NbVoyage'][$i]);
					array_push($newtab['PrixVoyage'], $_SESSION['panier']['PrixVoyage'][$i]);
					array_push($newtab['TAXE'],$_SESSION['panier']['TAXE'][$i]);
					array_push($newtab['Place_Voyage'],$_SESSION['panier']['Place_Voyage'][$i]);
				}
			}
			$_SESSION['panier']= $newtab;
			unset($newtab);
			
		}
	}
	function Supp_Panier(){
		if(isset($_SESSION['panier'])){
			unset($_SESSION['panier']);
		}
	}
	function Valider()
	{
		return ($_SESSION['panier'] && $_SESSION['panier']['Valider']);
	}
	function GetValider()
	{
		return $_SESSION['panier']['Valider'];
	}
	function Verouiller(){
		$_SESSION['panier']['Valider']=true;
	}
	function Deverouiller(){
		$_SESSION['panier']['Valider']=false;
	}
	function Nombre_Produit()
	{
		return count($_SESSION['panier']['ID_Voyage']);
	}
?>
