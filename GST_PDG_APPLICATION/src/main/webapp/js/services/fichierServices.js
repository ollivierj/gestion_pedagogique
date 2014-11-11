'use strict';

services.factory('FichiersFactory', function ($resource, $http, $rootScope, toaster) {
	var filterOptions = {
        filterText: "",
        useExternalFilter: true
	};
	return {
		filterOptions : filterOptions,
		fichiers : $resource('/ng_gst_pdg/web/fichiers/charger/:entite_type/:entite_id', {}, {
			getData : { method: 'GET', headers: {'Authorization':'Basic ' + $rootScope.authtoken}, isArray: true, params: {entite_type: '@entite_type', entite_id: '@entite_id'}}
		}),
		delete :  $resource('/ng_gst_pdg/web/fichiers/supprimer/:entite_type/:entite_id/:filename', {}, {
			doAction : { method: 'DELETE', params: {entite_type: '@entite_type', entite_id: '@entite_id', filename : '@filename' }}
		}),
		telecharger : function(entite_type,entite_id,filename){
			$http({
			    url: '/ng_gst_pdg/web/fichiers/telecharger/'+entite_type+'/'+entite_id+'/'+filename,
			    method: 'GET',
			    responseType: 'arraybuffer',
			    headers: {
			        'Content-type': 'application/json',
			        'Accept': 'application/octet-stream'
			    }
			})   .success( function(data, status, headers) {
		        var octetStreamMime = 'application/octet-stream';
		        var success = false;
		        headers = headers();
		        var filename = headers['x-filename'] || 'export.csv';
		        var contentType = headers['content-type'] || octetStreamMime;
		        try
		        {
		            var blob = new Blob([data], { type: contentType });
		            if(navigator.msSaveBlob)
		                navigator.msSaveBlob(blob, filename);
		            else {
		                var saveBlob = navigator.webkitSaveBlob || navigator.mozSaveBlob || navigator.saveBlob;
		                if(saveBlob === undefined) throw "Not supported";
		                saveBlob(blob, filename);
		            }
		            success = true;
		        } catch(ex)
		        {
		        }

		        if(!success)
		        {
		            var urlCreator = window.URL || window.webkitURL || window.mozURL || window.msURL;
		            if(urlCreator)
		            {
		                var link = document.createElement('a');
		                if('download' in link)
		                {
		                    try
		                    {
		                        var blob = new Blob([data], { type: contentType });
		                        var url = urlCreator.createObjectURL(blob);
		                        link.setAttribute('href', url);
		                        link.setAttribute("download", filename);
		                        var event = document.createEvent('MouseEvents');
		                        event.initMouseEvent('click', true, true, window, 1, 0, 0, 0, 0, false, false, false, false, 0, null);
		                        link.dispatchEvent(event);
		                        success = true;
		                    } catch(ex) {
		                    }
		                }
		                if(!success)
		                {
		                    try
		                    {
		                        var blob = new Blob([data], { type: octetStreamMime });
		                        var url = urlCreator.createObjectURL(blob);
		                        window.location = url;
		                        success = true;
		                    } catch(ex) {
		                    }
		                }

		            }
		        }
		        if (success){
		        	toaster.pop('success', null, "Fichier téléchargé");
		        }else{
		        	toaster.pop('error', null, "Erreur lors du téléchargement");
		        }

		    })
		    .error(function(data, status) {
		    	toaster.pop('error', null, "Erreur lors du téléchargement");
		    });
		},
		exporter : function(url, pagingOptions, filterOptions, sortOptions){
			$http({
			    url: url,
			    method: 'POST',
			    responseType: 'arraybuffer',
			    data: {
					pagingOptions : pagingOptions,
					filterOptions : filterOptions,
					sortOptions : 	sortOptions,
					connectedUser : $rootScope.utilisateurConnecte
					}, 
			    headers: {
			        'Content-type': 'application/json',
			        'Accept': 'application/octet-stream'
			    }
			})   .success( function(data, status, headers) {

		        var octetStreamMime = 'application/octet-stream';
		        var success = false;
		        headers = headers();
		        var filename = headers['x-filename'] || 'export.csv';
		        var contentType = headers['content-type'] || octetStreamMime;
		        try
		        {
		            var blob = new Blob([data], { type: contentType });
		            if(navigator.msSaveBlob)
		                navigator.msSaveBlob(blob, filename);
		            else {
		                var saveBlob = navigator.webkitSaveBlob || navigator.mozSaveBlob || navigator.saveBlob;
		                if(saveBlob === undefined) throw "Not supported";
		                saveBlob(blob, filename);
		            }
		            success = true;
		        } catch(ex)
		        {
		        }

		        if(!success)
		        {
		            var urlCreator = window.URL || window.webkitURL || window.mozURL || window.msURL;
		            if(urlCreator)
		            {
		                var link = document.createElement('a');
		                if('download' in link)
		                {
		                    try
		                    {
		                        var blob = new Blob([data], { type: contentType });
		                        var url = urlCreator.createObjectURL(blob);
		                        link.setAttribute('href', url);
		                        link.setAttribute("download", filename);
		                        var event = document.createEvent('MouseEvents');
		                        event.initMouseEvent('click', true, true, window, 1, 0, 0, 0, 0, false, false, false, false, 0, null);
		                        link.dispatchEvent(event);
		                        success = true;
		                    } catch(ex) {
		                    }
		                }
		                if(!success)
		                {
		                    try
		                    {
		                        var blob = new Blob([data], { type: octetStreamMime });
		                        var url = urlCreator.createObjectURL(blob);
		                        window.location = url;
		                        success = true;
		                    } catch(ex) {
		                    }
		                }

		            }
		        }
		        if (success){
		        	toaster.pop('success', null, "Fichier exporté");
		        }else{
		        	toaster.pop('error', null, "Erreur lors de l'export");
		        }

		    })
		    .error(function(data, status) {
		    	toaster.pop('error', null, "Erreur lors de l'export");
		    });
		}
	};
});





