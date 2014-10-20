'use strict';

services.factory('FichiersFactory', function ($resource, $http) {
	var filterOptions = {
        filterText: "",
        useExternalFilter: true
	};
	return {
		filterOptions : filterOptions,
		fichiers : $resource('/ng_gst_pdg/web/fichiers/charger/:entite_type/:entite_id', {}, {
			getData : { method: 'GET', isArray: true, params: {entite_type: '@entite_type', entite_id: '@entite_id'}}
		}),
		telecharger : $resource('/ng_gst_pdg/web/fichiers/telecharger/:entite_type/:entite_id/:filename', {}, {
			getData : { method: 'GET', params: {entite_type: '@entite_type', entite_id: '@entite_id', filename : '@filename'}}
		}),
		delete :  $resource('/ng_gst_pdg/web/fichiers/supprimer/:entite_type/:entite_id/:filename', {}, {
			doAction : { method: 'DELETE', params: {entite_type: '@entite_type', entite_id: '@entite_id', filename : '@filename' }}
		}),
		exporter : function(url, pagingOptions, filterOptions, sortOptions){
			$http({
			    url: url,
			    method: 'POST',
			    responseType: 'arraybuffer',
			    data: {
					pagingOptions : pagingOptions,
					filterOptions : filterOptions,
					sortOptions : 	sortOptions
					}, 
			    headers: {
			        'Content-type': 'application/json',
			        'Accept': 'application/octet-stream'
			    }
			})   .success( function(data, status, headers) {

		        var octetStreamMime = 'application/octet-stream';
		        var success = false;
		        headers = headers();

		        // Get the filename from the x-filename header or default to "download.bin"
		        var filename = headers['x-filename'] || 'export.csv';

		        // Determine the content type from the header or default to "application/octet-stream"
		        var contentType = headers['content-type'] || octetStreamMime;

		        try
		        {
		            // Try using msSaveBlob if supported
		            console.log("Trying saveBlob method ...");
		            var blob = new Blob([data], { type: contentType });
		            if(navigator.msSaveBlob)
		                navigator.msSaveBlob(blob, filename);
		            else {
		                // Try using other saveBlob implementations, if available
		                var saveBlob = navigator.webkitSaveBlob || navigator.mozSaveBlob || navigator.saveBlob;
		                if(saveBlob === undefined) throw "Not supported";
		                saveBlob(blob, filename);
		            }
		            console.log("saveBlob succeeded");
		            success = true;
		        } catch(ex)
		        {
		            console.log("saveBlob method failed with the following exception:");
		            console.log(ex);
		        }

		        if(!success)
		        {
		            // Get the blob url creator
		            var urlCreator = window.URL || window.webkitURL || window.mozURL || window.msURL;
		            if(urlCreator)
		            {
		                // Try to use a download link
		                var link = document.createElement('a');
		                if('download' in link)
		                {
		                    // Try to simulate a click
		                    try
		                    {
		                        // Prepare a blob URL
		                        console.log("Trying download link method with simulated click ...");
		                        var blob = new Blob([data], { type: contentType });
		                        var url = urlCreator.createObjectURL(blob);
		                        link.setAttribute('href', url);

		                        // Set the download attribute (Supported in Chrome 14+ / Firefox 20+)
		                        link.setAttribute("download", filename);

		                        // Simulate clicking the download link
		                        var event = document.createEvent('MouseEvents');
		                        event.initMouseEvent('click', true, true, window, 1, 0, 0, 0, 0, false, false, false, false, 0, null);
		                        link.dispatchEvent(event);
		                        console.log("Download link method with simulated click succeeded");
		                        success = true;

		                    } catch(ex) {
		                        console.log("Download link method with simulated click failed with the following exception:");
		                        console.log(ex);
		                    }
		                }

		                if(!success)
		                {
		                    // Fallback to window.location method
		                    try
		                    {
		                        // Prepare a blob URL
		                        // Use application/octet-stream when using window.location to force download
		                        console.log("Trying download link method with window.location ...");
		                        var blob = new Blob([data], { type: octetStreamMime });
		                        var url = urlCreator.createObjectURL(blob);
		                        window.location = url;
		                        console.log("Download link method with window.location succeeded");
		                        success = true;
		                    } catch(ex) {
		                        console.log("Download link method with window.location failed with the following exception:");
		                        console.log(ex);
		                    }
		                }

		            }
		        }

		    })
		    .error(function(data, status) {
		        console.log("Request failed with status: " + status);

		        // Optionally write the error out to scope
		        $scope.errorDetails = "Request failed with status: " + status;
		    });
		}
	};
});





