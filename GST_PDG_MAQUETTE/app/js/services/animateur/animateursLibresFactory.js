services.factory('AnimateursLibresFactory', function($resource) {
	//return $resource('/ng_gst_pdg/web/stagiaires/:jour', {}, { jour si
	// renseigné, liste des salles libres sinon toutes les salles
	// ici on bouchonne en renvoant une liste de salles en considerant seulement
	// le renvoi de salles libres
	return $resource('json/animateursLibresLoad.json', {}, {
	 query: { method: 'GET', isArray: true}
	 
	 })
	
});
