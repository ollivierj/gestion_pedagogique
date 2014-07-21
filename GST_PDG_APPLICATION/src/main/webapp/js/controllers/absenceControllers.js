'use strict';
var absencesCtrl = function($scope, $modalInstance, $log, AbsencesFactory,
		StagiairesFactory) {
	$scope.absences = [];
	$scope.date = new Date();
	$scope.hstep = 1;
	$scope.mstep = 15;
	$scope.options = {
		hstep : [ 1, 2, 3 ],
		mstep : [ 1, 5, 10, 15, 25, 30 ]
	};
	$scope.ismeridian = true;
	$scope.toggleMode = function() {
		$scope.ismeridian = !$scope.ismeridian;
	};
	$scope.disabled = function(date, mode) {
		return (mode === 'day' && (date.getDay() === 0 || date.getDay() === 6));
	};

	$scope.editAbsence = function(absence) {
		absence.editing = true;
	};

	$scope.periode = 'matin';

	$scope.getStagiaires = function(search) {
		return StagiairesFactory.dataAutocomplete.getData({
			search : search
		}).$promise.then(function(data) {
			var stagiaires = [];
			angular.forEach(data, function(item) {
				stagiaires.push(item);
			});
			return stagiaires;
		});
	};

	$scope.getAbsences = function(day) {
		return AbsencesFactory.jour.getData({
			jour : day
		}).$promise.then(function(data) {
			$scope.absences = data;
		});
		;
	};

	$scope.checkModel = {
		matin : true,
		soir : false
	};

	$scope.cancelEditingAbsence = function(absence) {
		absence.editing = false;
	};

	$scope.addAbsence = function(stagiaire) {
		$scope.absences.push({
			stagiaire : stagiaire,
			date : $scope.date,
			dateArriveeMatin : null,
			dateArriveeApresMidi : null,
			dateSaisie : null,
			auteur : null,
			editing : false
		});
	};
	
	$scope.removeAbsence = function(absence) {
		return null;
	};

	$scope.saveAbsence = function(absence) {
		absence.editing = false;
	};

	$scope.open = function($event) {
		$event.preventDefault();
		$event.stopPropagation();
		$scope.opened = true;
	};

	$scope.ok = function() {
		$modalInstance.close(null);
	};

	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
};
