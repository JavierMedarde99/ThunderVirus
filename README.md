# ThunderVirus
Proyecto final de DAW

# Instrucciones

## Depliege local
### Sin docker
  - Primero necesitarias tener java 17, si no aqui tienes como instalar java 17 (https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
  - Segundo tener una bbdd postgress, ya que es el tipo de bbdd que se usa en este proyecto, y ya una vez echo ejecutar el archivo sql.
  - Por ultimo, hacer maven clean y install para que se traiga todas las dependencia y arrancarlo en el perfil local
### Con Docker
  - Primero instalar Docker (https://docs.docker.com/get-started/get-docker/)
  - Instalar el Docker-Compose ya que ahi se generara la base de datos y el java. Solo que hay que ejecutar el sql ya que tiene datos importantes para el perfecto comportamiento de la pagina

## Despliege en perfil de produccion
 - Primero generar un .env con las credenciales de la bbdd que tiene que ser postgress
 - Segundo, o ejecutar el programa en el perfil pro o ejecutar el Dockerfile en Docker
