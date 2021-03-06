Create invoices and put bills in it by users, then run report to check full invoice and debts split to make equal payment amount between users. 

RUN:\
$ sh build.sh\
$ docker network create mynet\
$ docker run -d --rm --name db --net=mynet -e POSTGRES_PASSWORD=Zxcv1234 -e POSTGRES_DB=postgres1 -e POSTGRES_USER=postgres1 postgres:12.5\
$ docker run -d --rm -p 8080:8080 --net=mynet mikebychkov/invoice-split

USAGE:\
localhost:8080/InvoiceSplit-Servlet-Rest/api/invoice(?inv) - for add, list, remove invoices\
localhost:8080/InvoiceSplit-Servlet-Rest/api/details(?inv,usr,name,sum,id) - for add, list, remove invoice details\
localhost:8080/InvoiceSplit-Servlet-Rest/api/report(?inv,usr) - to get invoice report
