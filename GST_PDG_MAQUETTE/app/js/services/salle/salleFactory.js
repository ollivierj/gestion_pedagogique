services.factory('StagiairesFactory', function ($resource) {
    return $resource('/ng_gst_pdg/web/stagiaires', {}, {
        query: { method: 'GET', isArray: true },
        create: { method: 'POST' }
    })
});