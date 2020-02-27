##РЭМД. Инструкция по разворачиванию среды разработки

 + [Адреса](#1)
+ [Общее описание](#2)
+ [Инструкция по установке](#3)
  + [1. Операции, выполняемые на всех серверах](#4)
  + [2. Настройка имен машин на всех машинах](#5)
  + [3. Установка postgresql на dev-remd-database-01](#6)
  + [4. Установка JDK на машинах remd-web-*, remd-core-*, remd-secured-*, remd-domain-01, remd-cassandra-*, remd-portal-web-*, remd-portal-core-*, remd-cassandra-*](#7)
  + [5. Установка РЭМД](#8)
    + [5.1 Установка Wildfly на remd-domain-01, remd-core-*, remd-secured-*, remd-web-*](#9)
    + [5.2 Настройка контроллера домена на remd-domain-01](#10)
    + [5.3 Настройка remd-core-*, remd-web-*, remd-secured-*](#11)
    + [5.4 Установка и настройка CryptoPro JCP на remd-secured-*](#12)  
    + [5.5 Настройка домена на контроллере домена remd-domain-01 через jboss-cli](#13)
    + [5.6 Настройка балансировки на remd-domain-01 через jboss-cli](#14)
    + [5.7 Настройка REMOTE EJB на remd-domain-01 через jboss-cli](#15)
    + [5.8 Настройка кэша Hibernate на remd-domain-01 через jboss-cli](#16)
    + [5.9 Настройка web-кэша на remd-domain-01 через jboss-cli](#17)
    + [5.10 Добавление связи с crypto facade на remd-domain-01 через jboss-cli](#18)
    + [5.11 Добавление AppEmailPool и ReportPool, настройка JMS на remd-domain-01 через jboss-cli](#19)
    + [5.12 Настройка JVM на remd-domain-01 через jboss-cli](#20)
    + [5.13 Настройка журналирования на remd-domain-01 через jboss-cli](#21)
    + [5.14 Настройка синхронизации кеша eh-cache на машинах remd-web-*](#22)
    + [5.14.2 Настройка n2o.ui.url в placeholders.properties](#23)
    + [5.15 Настройка балансировщика на remd-balancer-01](#24)
    + [5.16 Установка Cassandra на remd-cassandra-*](#25)
    + [5.17 Настройка почтового сервера на машине remd-database-01](#26)
    + [5.18 Настройка журналирования запросов в ELK (опционально)](#27)
  + [6. Установка Порталов ЭМД](#28)
    + [6.1 Установка Apache Tomcat на remd-portal-core-*](#29)
    + [6.2 Установка Apache Tomcat на remd-portal-web-* для модуля "Спецпортал ЭМД"](#30)
    + [6.3 Установка Apache Tomcat на remd-portal-web-* для модуля "Портал ЭМД"](#31)
  + [7. Установка nginx на remd-balancer-01](#32)
+ [Инструкция по настройке](#33)
  + [1. Настройка РЭМД в сервере remd-database-01](#34)
  + [3. Настройка Apache Tomcat /opt/tomcat на remd-portal-web-*](#35)
  + [4. Настройка Apache Tomcat /opt/tomcatpp на remd-portal-web-*](#36)
+ [Замечания](#37)
  + [Восстановление дампа из базы ARM](#38)
  + [Восстановление БД спец. портала](#39)

##### <a name="1"></a>
###Адреса
Администрирование РЭМД: https://remd-dev.rt-eu.ru
Портал ЭМД: https://emd-portal-dev.rt-eu.ru
Специализированный портал ЭМД:  https://emd-special-dev.rt-eu.ru

##### <a name="2"></a> 
###Общее описание
Инфраструктура площадки состоит из следующих серверов:
