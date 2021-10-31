/*
Java Project01 JDBC 연동 (kosmo)계정
*/


create table phonebook_tb (
    idx number primary key,
    name varchar2(20),
    phone varchar2(20),
    birth varchar2(20)
);

create sequence seq_phonebook
    INCREMENT by 1
    start with 1
    minvalue 1
    nomaxvalue
    nocache
    nocycle;


select * from phonebook_tb order by idx;


commit;