# catalog-service

## Ressources web
https://learn.microsoft.com/fr-fr/windows/wsl/install

https://start.spring.io/

https://structurizr.com/dsl

https://c4model.com/

https://www.baeldung.com/linux/assign-port-docker-container

https://www.bibliotheque-partagee.fr/

Dans Eclipse : import existing gradle project

Pour lancer les tests : Run as > Gradle Test (ouvre ensuite le panneau Gradle Tasks et Executions)

Pour builder lancer l'app : Gradle Tasks > application > bootRun
Puis sur le navigateur : http://localhost:8080/

## Structurizr DSL

workspace {

    model {
        reader = person "Lecteur"
        owner = person "Propriétaire"
        softwareSystem = softwareSystem "Bibliothèque partagée" {
            catalogService = container "CatalogService" "Service de gestion des livres de la bibliothèque" "Spring Boot" {
                reader -> this "Consulte (après : Recherche, Emprunte, Retourne) un ou plusieurs livres"
                owner -> this "Propose un ou plusieurs livres au partage"
            }
            database = container "Database" {
                catalogService -> this "Requête et persiste"
            }
        }
    }

    views {
        systemContext softwareSystem {
            include *
            autolayout lr
        }

        container softwareSystem {
            include *
            autolayout lr
        }

        theme default
    }

}