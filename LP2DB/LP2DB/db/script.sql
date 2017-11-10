drop table userinfo;
drop table comment;
drop table post;
drop table userlp2;


-- criar tabela usuário
create table userlp2(
    id_userlp2 bigint not null primary key 
               generated always as identity
               (start with 1, increment by 1),
    username varchar(20) not null,
    password varchar(32) not null
);

-- Informações do usuário
create table userinfo(
    id_userinfo bigint not null primary key,
    firstname varchar(50),
    lastname varchar(50),
    email varchar(100),
    birthday date,
    avatar blob
);

-- Tabela de posts
create table post(
    id_post bigint not null primary key
            generated always as identity
            (start with 1, increment by 1),
    posttext varchar(140) not null,
    postdate timestamp not null,
    image blob,
    fk_userlp2 bigint not null
);

-- Tabela de comentários
create table comment(
    id_comment bigint not null primary key
               generated always as identity
               (start with 1, increment by 1),
    commenttext varchar(140) not null,
    commentdate timestamp not null,
    fk_userlp2 bigint not null,
    fk_post bigint not null
);

-- chaves estrangeiras
-- 1 para 1
alter table userinfo
add foreign key(id_userinfo)
references userlp2(id_userlp2)
on delete cascade;

-- 1 para muitos
alter table post
add foreign key(fk_userlp2)
references userlp2(id_userlp2)
on delete cascade;

-- muitos para muitos
alter table comment
add foreign key(fk_userlp2)
references userlp2(id_userlp2)
on delete cascade;

alter table comment
add foreign key(fk_post)
references post(id_post)
on delete cascade;

insert into userlp2(username, password) values('camila','123');

insert into userinfo values(1,'Camila',
            'Camila','capereira@gmail.com','1992-10-08',null);

delete from userlp2 where username='camila';

select * from userlp2 inner join userinfo
on id_userlp2 = id_userinfo where username='camila';

-- select * from userlp2;