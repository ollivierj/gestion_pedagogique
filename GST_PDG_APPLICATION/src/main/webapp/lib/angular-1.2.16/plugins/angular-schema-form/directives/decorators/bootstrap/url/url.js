angular.module('schemaForm').config(
       ['schemaFormProvider','schemaFormDecoratorsProvider',
function(schemaFormProvider,  schemaFormDecoratorsProvider){
  var base = 'lib/angular-1.2.16/plugins/angular-schema-form/directives/decorators/bootstrap/';

  var url = function(name,schema,options) {
    if (schema.type === 'string' && schema.format == "url") {
      var f = schemaFormProvider.stdFormObj(schema,options);
      f.key  = options.path;
      f.type = 'url';
      options.lookup[options.path] = f;
      return f;
    }
  };

  schemaFormProvider.defaults.string.unshift(url);

  //Add to the bootstrap directive
  schemaFormDecoratorsProvider.addMapping('bootstrapDecorator','url',base+'url/url.html');
  schemaFormDecoratorsProvider.createDirective('url',base+'url.html');

}]);