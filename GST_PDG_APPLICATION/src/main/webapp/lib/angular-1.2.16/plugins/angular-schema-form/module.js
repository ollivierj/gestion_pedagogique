var deps = [];
try {
  //This throws an expection if module does not exist.
  angular.module('ngSanitize');
  deps.push('ngSanitize');
} catch (e) {}

try {
  //This throws an expection if module does not exist.
  angular.module('ui.sortable');
  deps.push('ui.sortable');
} catch (e) {}

try {
	  //This throws an expection if module does not exist.
	  angular.module('ui.bootstrap.datetimepicker');
	  deps.push('ui.bootstrap.datetimepicker');
	} catch (e) {}

angular.module('schemaForm',deps);
