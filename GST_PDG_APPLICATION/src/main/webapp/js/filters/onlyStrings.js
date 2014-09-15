filters.filter('onlyStrings', function($filter) {
	  return function(array) {
	        if (!angular.isUndefined(array) && array.length > 0) {
	            var filteredAray = [];
	            angular.forEach(array, function (item) {
	                if (isNaN(item)){
	                	filteredAray.push(item);
	                }
	            });
	            return filteredAray;
	        } else {
	            return [];
	        }
	    };
});