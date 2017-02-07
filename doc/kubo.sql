/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/11/27 19:12:03                          */
/*==============================================================*/


drop table if exists ku_user;

/*==============================================================*/
/* Table: ku_user                                               */
/*==============================================================*/
create table ku_user
(
   id                   varchar(32) not null,
   username             varchar(50),
   usernamecn           varchar(50),
   password             varchar(50),
   primary key (id)
);

