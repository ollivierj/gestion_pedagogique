/**
 * Controller de la page detail stagiaire
 */
controllers.controller('detailFichierCtrl', function($scope, $rootScope, $modal, $filter, $modal, FileUploader, StagiaireFactory, FichiersFactory, fichiers) {
	$scope.fichiers = fichiers;
	$scope.results = fichiers;
	$scope.affFichiers=true;
	$scope.canEdit=StagiaireFactory.canEdit;
	$scope.canView=StagiaireFactory.canView;
	$scope.affTelech=!StagiaireFactory.readonly;
	$scope.fichiersFilterOptions = {
			filterText: ''
		};
	$scope.fichiersGridOptions = {
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
							displayName : 'Actions',
							cellTemplate : 'partials/templates/ng-grid_view_remove_action.html'
						}
						],
		enablePaging : false,
		showFooter : false,
		keepLastSelected: true,
		enableColumnResize: true,
		enableColumnReordering : true,
		filterOptions : $scope.fichiersFilterOptions,
		useExternalSorting : true,
		showColumnMenu : true,
		i18n : 'fr'
	};
	
	$scope.downloadFile = function(fichier) {
		var downloadPath = '/ng_gst_pdg/web/fichiers/telecharger/Stagiaire/'+StagiaireFactory.stagiaire.id+'/'+fichier.filename;
		window.open(downloadPath,'_blank');  
	};
	
	$scope.removeFile = function(fichier) {
		var modalDelete = $modal
		.open({
			templateUrl : 'partials/templates/dialog.html',
			controller : modalConfirmationDeleteEvaluationCtrl,
			resolve : {
				id : function() {return fichier.filename;},
				title : function() {return "Suppression d'un fichier";},
				message : function() {return "Etes-vous sur de vouloir supprimer ce fichier ?";},
				ok : function () { return function(id) { return FichiersFactory.delete.doAction({entite_type: 'Stagiaire', entite_id: StagiaireFactory.stagiaire.id, filename : id });}}
			}
		});	
		modalDelete.result.then(function(selectedItem) {
			FichiersFactory.fichiers.getData({entite_type : "Stagiaire", entite_id : StagiaireFactory.stagiaire.id})
				.$promise.then(function(data){
					$scope.fichiers = data;
					$scope.results = data;
				}
			);
		}, function() {
			$log.info('Modal dismissed at: ' + new Date());
		});
	}
		
	var uploader = $scope.uploader = new FileUploader({
		url : '/ng_gst_pdg/web/fichiers/deposer'
	});

	uploader.filters.push({
		name : 'customFilter',
		fn : function(item, options) {
			return this.queue.length < 10;
		}
	});

	uploader.onBeforeUploadItem = function(item) {
		item.formData.push({entite_type : "Stagiaire"});
		item.formData.push({entite_id : StagiaireFactory.stagiaire.id});
	};
	uploader.onCompleteAll = function() {
		FichiersFactory.fichiers.getData({entite_type : "Stagiaire", entite_id : StagiaireFactory.stagiaire.id})
		.$promise.then(function(data){
			$scope.fichiers = data;
			$scope.results = data;
		});
	};
    
});