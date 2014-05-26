filters.filter('getById', function($filter) {
  return function(array, id) {
	var result =   $filter('filter')(array, {id: id})[0]; 
    if (result) {
      return result;
    } else {
      return 'inconnu';
    }
  };
})