'use strict';

controllers.controller('fichiersCtrl', function($scope, $timeout, $filter, FileUploader, fichiers, FichiersFactory) {
	$scope.title="Fichiers"
	$scope.fichiers = fichiers;
	$scope.results = fichiers;
	$scope.filterOptions = FichiersFactory.filterOptions;
	$scope.gridOptions = {
		data : 'results',
		multiSelect : false,
		columnDefs : 	[
						{
							field : 'filename',
							displayName : 'Nom'
						},
						{
							field : 'size',
							displayName : 'Taille'
						},
						{
							field : 'url',
							displayName : 'Chemin'
						}
						],
		enablePaging : false,
		showFooter : false,
		keepLastSelected: true,
		enableColumnResize: true,
		enableColumnReordering : true,
		filterOptions : $scope.filterOptions,
		useExternalSorting : true,
		showColumnMenu : true,
		i18n : 'fr'
	};
	
	$scope.$watch('filterOptions', function (newVal, oldVal) {
		if (newVal.filterText){
	        $scope.results=$filter('filter')($scope.fichiers, {filename:newVal.filterText}, false);
		}else{
			$scope.results=$scope.fichiers;
		}
    }, true);
	
	var uploader = $scope.uploader = new FileUploader({
		url : '/ng_gst_pdg/web/fichiers/deposer'
	});

	// FILTERS

	uploader.filters.push({
		name : 'customFilter',
		fn : function(item /* {File|FileLikeObject} */, options) {
			return this.queue.length < 10;
		}
	});

	// CALLBACKS

	uploader.onWhenAddingFileFailed = function(item /* {File|FileLikeObject} */,
			filter, options) {
		console.info('onWhenAddingFileFailed', item, filter, options);
	};
	uploader.onAfterAddingFile = function(fileItem) {
		console.info('onAfterAddingFile', fileItem);
	};
	uploader.onAfterAddingAll = function(addedFileItems) {
		console.info('onAfterAddingAll', addedFileItems);
	};
	uploader.onBeforeUploadItem = function(item) {
		console.info('onBeforeUploadItem', item);
	};
	uploader.onProgressItem = function(fileItem, progress) {
		console.info('onProgressItem', fileItem, progress);
	};
	uploader.onProgressAll = function(progress) {
		console.info('onProgressAll', progress);
	};
	uploader.onSuccessItem = function(fileItem, response, status, headers) {
		console.info('onSuccessItem', fileItem, response, status, headers);
	};
	uploader.onErrorItem = function(fileItem, response, status, headers) {
		console.info('onErrorItem', fileItem, response, status, headers);
	};
	uploader.onCancelItem = function(fileItem, response, status, headers) {
		console.info('onCancelItem', fileItem, response, status, headers);
	};
	uploader.onCompleteItem = function(fileItem, response, status, headers) {
		console.info('onCompleteItem', fileItem, response, status, headers);
	};
	uploader.onCompleteAll = function() {
		console.info('onCompleteAll');
	};

	console.info('uploader', uploader);
});