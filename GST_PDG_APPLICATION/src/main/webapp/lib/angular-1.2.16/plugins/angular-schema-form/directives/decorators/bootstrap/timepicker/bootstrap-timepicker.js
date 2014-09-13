angular.module('schemaForm').config(
       ['schemaFormProvider','schemaFormDecoratorsProvider', 
function(schemaFormProvider,  schemaFormDecoratorsProvider){
  var base = 'lib/angular-1.2.16/plugins/angular-schema-form/directives/decorators/bootstrap/';

  var timepicker = function(name,schema,options) {
    if (schema.type === 'string' && schema.format == "time") {
      var f = schemaFormProvider.stdFormObj(schema,options);
      f.key  = options.path;
      f.type = 'timepicker';
      options.lookup[options.path] = f;
      return f;
    }
  };

  schemaFormProvider.defaults.string.unshift(timepicker);

  //Add to the bootstrap directive
  schemaFormDecoratorsProvider.addMapping('bootstrapDecorator','timepicker',base+'timepicker/timepicker.html');
  schemaFormDecoratorsProvider.createDirective('timepicker',base+'timepicker.html');

}]);
