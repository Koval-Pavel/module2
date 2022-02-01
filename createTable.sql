create table QUESTIONS
(
    ID        NUMBER        not null
        unique,
    QUESTIONS VARCHAR2(255) not null,
    ANSWER    VARCHAR2(255) not null,
    POINTS    NUMBER        not null
)
    /
create sequence QUESTIONS_ID_SEQ
    /
create trigger BI_QUESTIONS_ID
    before insert
    on QUESTIONS
    for each row
begin
    select "QUESTIONS_ID_SEQ".nextval into :NEW."ID" from dual;
end;
/

create table USERS
(
    ID               NUMBER        not null
        unique,
    NAME             VARCHAR2(255) not null,
    MAX_TOTAL_POINTS NUMBER        not null
)
    /
create sequence USERS_ID_SEQ
    /
create trigger BI_USERS_ID
    before insert
    on USERS
    for each row
begin
    select "USERS_ID_SEQ".nextval into :NEW."ID" from dual;
end;
/