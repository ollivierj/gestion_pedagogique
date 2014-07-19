filters.filter('onlyNumbers', function($filter) {
	  return function(array) {
	        if (!angular.isUndefined(array) && array.length > 0) {
	            var filteredAray = [];
	            angular.forEach(array, function (item) {
	                if (!isNaN(item)){
	                	filteredAray.push(parseInt(item));
	                }
	            });
	            return filteredAray;
	        } else {
	            return [];
	        }
	    };
});