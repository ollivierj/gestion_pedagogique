angular.module('schemaForm').config(
       ['schemaFormProvider','schemaFormDecoratorsProvider',
function(schemaFormProvider,  schemaFormDecoratorsProvider){
  var base = 'lib/angular-1.2.16/plugins/angular-schema-form/directives/decorators/bootstrap/';

  var password = function(name,schema,options) {
    if (schema.type === 'string' && schema.format == "password") {
      var f = schemaFormProvider.stdFormObj(schema,options);
      f.key  = options.path;
      f.type = 'password';
      options.lookup[options.path] = f;
      return f;
    }
  };

  schemaFormProvider.defaults.string.unshift(password);

  //Add to the bootstrap directive
  schemaFormDecoratorsProvider.addMapping('bootstrapDecorator','password',base+'password/password.html');
  schemaFormDecoratorsProvider.createDirective('password',base+'password.html');

}]);