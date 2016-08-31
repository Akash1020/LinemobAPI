create user linemob identified by "f110987*";
grant connect, resource, create session, dba to linemob;

create sequence sq_linemob
increment by 1
start with 1
nocache;

create table linemob_usuario
(id number primary key,
usuario varchar2(4000));

create table linemob_conta(
  id number primary key, 
  nome varchar2(4000),
  datafundacao date,
  valorsaldoinicial number);
  
create table linemob_pessoa(
 id number primary key,
 nome varchar2(4000),
 apelido varchar2(4000));  
 
create table linemob_categoria(
 id number primary key,
 nome varchar2(4000),
 nomesubcategoria varchar2(4000)); 
 
create table linemob_cartao(
 id number primary key,
 nome varchar2(4000),
 diavencimento number,
 diafechamento number,
 valorlimite number,
 idconta number references linemob_conta(id)); 
 
create table linemob_movimento(
 id  number primary key,
 valor number,
 natureza char(1),
 descricao varchar2(4000),
 idconta number references linemob_conta(id),
 idcategoria number references linemob_categoria(id),
 idpessoa number references linemob_pessoa(id),
 idcartao number references linemob_cartao(id)); 
 
 
