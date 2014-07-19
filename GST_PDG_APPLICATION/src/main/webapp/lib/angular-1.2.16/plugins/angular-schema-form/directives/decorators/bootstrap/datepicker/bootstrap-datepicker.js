angular.module('schemaForm').config(
       ['schemaFormProvider','schemaFormDecoratorsProvider',
function(schemaFormProvider,  schemaFormDecoratorsProvider){
  var base = 'lib/angular-1.2.16/plugins/angular-schema-form/directives/decorators/bootstrap/';

  var datepicker = function(name,schema,options) {
    if (schema.type === 'string' && schema.format == "date") {
      var f = schemaFormProvider.stdFormObj(schema,options);
      f.key  = options.path;
      f.type = 'datepicker';
      options.lookup[options.path] = f;
      return f;
    }
  };

  schemaFormProvider.defaults.string.unshift(datepicker);

  //Add to the bootstrap directive
  schemaFormDecoratorsProvider.addMapping('bootstrapDecorator','datepicker',base+'datepicker/datepicker.html');
  schemaFormDecoratorsProvider.createDirective('datepicker',base+'datepicker.html');

}]);
