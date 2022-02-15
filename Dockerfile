FROM php:8-apache

RUN a2enmod rewrite

RUN apt-get update && apt-get install -y git unzip zip


COPY ./phpweb /var/www/html
