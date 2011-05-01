#!/bin/bash
cd fbtosemweb; \
        mvn clean package install; \
        cd ../fbtosemweb-webservice; \
        mvn keytool:genkey; \
        mvn jetty:run; \
        cd ..
