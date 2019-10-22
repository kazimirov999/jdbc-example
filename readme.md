Start MySql server in Docker container:

docker run -d --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=secret -e MYSQL_DATABASE=jdbc-example mysql:5

#port - 3306
#user - root
#password - secret

Connect to DB
docker exec -it mysql1 mysql -uroot -p

Program arguments - jdbc:mysql://localhost/jdbc-example root secret