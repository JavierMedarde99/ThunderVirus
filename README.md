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
  - Instalar el Docker-Compose ya que ahi se generara la base de datos local
  - por ultimo costruir el Docker con el Dockerfile

## Despliege en perfil de produccion
 - Primero generar un .env con las credenciales de la bbdd que tiene que ser postgress
 - Segundo, o ejecutar el programa en el perfil pro o ejecutar el Dockerfile en Docker
