# Lecoincoin_et_API_REST

Contexte  Lecoincoin est une société familiale qui existe depuis 1940, elle est gérée par Mr Gerard Lecoincoin et Mme Mathilde Lecoincoin.

Lecoincoin est une entreprise de dépôt vente touchant à tout ce qui peut se vendre ou s’acheter.

Lecoincoin a raté le tournant du numérique, il y a quelques décennies et aimerait vraiment pouvoir avoir une présence en ligne pour diffuser ses annonces. Acteurs Le projet sera exploité par Mr Gérard Lecoincoin qui aura la lourde tâche d’administrer les annonces sur la plateforme qui sera créée.

Mme Mathilde Lecoincoin n’était pas à l’aise avec l’outil informatique, n’aura qu’un rôle de modérateur pour ne pas risquer de causer trop de dégâts.  Dates importantes Lecoincoin fêtera ses 80 ans le dimanche suivant la fin de votre cours (à minuit) ! Il faut donc impérativement que tous les éléments demandés soient livrés pour cette date ! Objectifs Réaliser une plateforme complète avec plusieurs modes de consultation :
● Un backend devra être réalisé pour permettre à Mr Lecoincoin d’administrer les annonces, les modifier, les supprimer et même en créer !
● Un frontend devra être réalisé pour permettre à monsieur tout le monde de profiter de ces annonces exceptionnelles  
● Une API REST devra être réalisée, car Mr Lecoincoin est ambitieux et il aimerait vraiment avoir une application mobile dans un futur proche.

Gestion de projet La méthode de gestion de projet sera une méthode agile informelle ou s’approchant de quelque chose comme de ​l’Extreme Programming (XP)​.


Le product owner sera toujours disponible pour répondre à vos questions et vous pouvez lui poser autant de questions que nécessaire.

Vous travaillerez en binôme, sur des itérations courtes en validant systématiquement les fonctionnalités à deux.

Aucun document relatif à la gestion de projet n’est attendu. Expression détaillée du besoin Besoins fonctionnels Backend
● Une couche de sécurité sera ajoutée afin d’empêcher l’accès à la partie backend sans identifiants
● Les comptes utilisateurs pourront avoir 3 rôles différents  ○ ADMIN ○ MODERATOR ○ CLIENT
● L’administrateur et le modérateur pourront tous deux accéder au backend
● Le rôle CLIENT sera réservé aux utilisateurs de la partie frontend qui se sont enregistrés
● Au delà des utilisateurs (User) qui seront liés à des rôles (Role) pour la sécurité, votre modèle de donnée permettra de gérer des annonces (SaleAd) et leurs illustrations (Illustration)
● Toutes les opérations de CRUD seront disponible sur les entités représentant  ○ Les utilisateurs ○ Les annonces  ○ Les illustrations n’auront pas de gestion indépendante, elles seront simplement modifiable dans les annonces

Pour résumer, les actions réalisable pour la partie backend sont les suivantes :  
● S’identifier / Se déconnecter
● Créer / Visualiser / Mettre à jour / Supprimer  ○ Une annonce ○ Un utilisateur

Frontend Le frontend sera accessible à tout le monde sur une autre URL que celle du backend, exemple :

Le frontend sera composé 3 éléments :
● Un formulaire de login
● Un listing de toutes les annonces ○ Pour l’affichage de la liste des annonces, vous pouvez alternativement mettre en place une pagination ou un chargement Ajax ○ Vous ne devrez pas charger plus de 10 annonces au chargement de la page ○ Une annonce dans la liste est présentée avec les informations suivantes  ■ Titre ■ Description courte ■ Prix ■ Date de création ■ Image d’illustration
● Une vue détaillée d’annonce ○ Le détail d’une annonce devra présenter les informations suivantes ■ Titre ■ Description courte ■ Description longue (texte pouvant contenir des balises de mise en forme) ■ Prix ■ Date de création ■ Image d’illustration 
 
