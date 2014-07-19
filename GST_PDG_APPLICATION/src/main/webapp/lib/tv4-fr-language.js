tv4.addLanguage('fr', 
		{
	INVALID_TYPE: "Type invalide: {type} (attendu {expected})",
	ENUM_MISMATCH: "Aucune proposition ne correspond: {value}",
	ANY_OF_MISSING: "Les données ne sont conformes à aucun des schémas de \"anyOf\"",
	ONE_OF_MISSING: "La donnée ne sont conformes aucun des schémas de \"oneOf\"",
	ONE_OF_MULTIPLE: "Les données sont conformes à plusieurs schémas \"oneOf\": indices {index1} and {index2}",
	NOT_PASSED: "Les données sont conformes au schéma de \"not\"",
	// Numeric errors
	NUMBER_MULTIPLE_OF: "La valeur {value} n'est pas un multiple de {multipleOf}",
	NUMBER_MINIMUM: "La valeur {value} est inférieure au minimum {minimum}",
	NUMBER_MINIMUM_EXCLUSIVE: "La valeur {value} est égale au minimum exclusif {minimum}",
	NUMBER_MAXIMUM: "La valeur {value} est supérieure au maximum {maximum}",
	NUMBER_MAXIMUM_EXCLUSIVE: "La valeur {value} est égale au maximum exclusif {maximum}",
	// String errors
	STRING_LENGTH_SHORT: "La chaîne de caractères est trop petite ({length} chars), minimum {minimum}",
	STRING_LENGTH_LONG: "La chaîne de caractères est trop grande ({length} chars), maximum {maximum}",
	STRING_PATTERN: "La chaîne de caractère ne correpond pas au format : {pattern}",
	// Object errors
	OBJECT_PROPERTIES_MINIMUM: "Pas assez de propriétés de définies ({propertyCount}), minimum {minimum}",
	OBJECT_PROPERTIES_MAXIMUM: "Trop de propriétés de définies  ({propertyCount}), maximum {maximum}",
	OBJECT_REQUIRED: "Propriété requis manquante : {key}",
	OBJECT_ADDITIONAL_PROPERTIES: "Propriétés supplémentaires non autorisées",
	OBJECT_DEPENDENCY_KEY: "Problème de dépendance - La clé doit exister: {missing} (lié à la clé : {key})",
	// Array errors
	ARRAY_LENGTH_SHORT: "Le tableau est trop court ({length}), minimum {minimum}",
	ARRAY_LENGTH_LONG: "Le tableau est trop long ({length}), maximum {maximum}",
	ARRAY_UNIQUE: "Les éléments du tableaux ne sont pas uniques (indices {match1} et {match2})",
	ARRAY_ADDITIONAL_ITEMS: "Les éléments supplémentaires ne sont pas autorisés",
	// Format errors
	FORMAT_CUSTOM: "La validation du formulaire a échoué ({message})",
	KEYWORD_CUSTOM: "Problème de mot clé: {key} ({message})",
	// Schema structure
	CIRCULAR_REFERENCE: "Dépendance circulaire $refs: {urls}",
	// Non-standard validation options
	UNKNOWN_PROPERTY: "Propriété inconnue : absente du schéma"
}		
);
tv4.language('fr');