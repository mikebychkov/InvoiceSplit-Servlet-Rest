FROM tomcat

COPY target/InvoiceSplit-Servlet-Rest.war /usr/local/tomcat/webapps/
COPY target/InvoiceSplit-Servlet-Rest /usr/local/tomcat/webapps/InvoiceSplit-Servlet-Rest

CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]