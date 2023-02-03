# catalog-service

## Ressources web
https://learn.microsoft.com/fr-fr/windows/wsl/install

https://start.spring.io/

https://structurizr.com/dsl

https://c4model.com/

https://www.baeldung.com/linux/assign-port-docker-container

https://www.bibliotheque-partagee.fr/

## Test and build

Dans Eclipse : import existing gradle project

Pour lancer les tests : Run as > Gradle Test (ouvre ensuite le panneau Gradle Tasks et Executions)

Pour builder lancer l'app : Gradle Tasks > application > bootRun
Puis sur le navigateur : http://localhost:8080/

## Containerisation Docker

https://buildpacks.io/
Il suffit de lancer la tâche Gradle bootBuildImage !
Si le build OCI gèle, vérifier le statut du deamon Docker.
Mieux après : les images et le container apparaissent sous Docker Desktop, mais docker version ne répond plus... instabilité sous Win10 ? Non il fallait lancer Docker en mode admin.

https://stackoverflow.com/questions/62377865/docker-for-windows-will-not-start

docker run --rm --name catalog-service -p 8080:8080 catalog-service:0.0.1-SNAPSHOT

## Orchestration K8S

winget install minikube
minikube start --driver=docker
minikube dashboard

minikube image load catalog-service:0.0.1-SNAPSHOT
kubectl create deployment catalog-service --image=catalog-service:0.0.1-SNAPSHOT
kubectl get deployment
kubectl get pod
kubectl expose deployment catalog-service \ --name=catalog-service \ --port=8080
kubectl logs deployment/ catalog-service

kubectl delete service catalog -service
kubectl delete deployment catalog -service
minikube stop

## Persistence with PostgreSQL in Docker

docker run -d --name sharedlibrary-postgres -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -e POSTGRES_DB=sharedlibrarydb_catalog -p 5432:5432 postgres:14.4

## Structurizr DSL

workspace {

    model {
        reader = person "Lecteur" "Consulte (après : Recherche, Emprunte, Retourne) un ou plusieurs livres"
        owner = person "Propriétaire" "Propose un ou plusieurs livres au partage"
        softwareSystem = softwareSystem "Bibliothèque partagée" {
            edgeService = container "EdgeService" "API Gateway" "Spring Boot" {
                reader -> this "Accède par REST/HTTP"
                owner -> this "Accède par REST/HTTP"
            }
            catalogService = container "CatalogService" "Service de gestion des livres de la bibliothèque" "Spring Boot" {
                edgeService -> this "Consomme par API REST"
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