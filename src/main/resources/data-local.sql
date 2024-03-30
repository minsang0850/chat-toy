drop table if exists members;
drop table if exists chat_room;
drop table if exists chatter;

create table members
(
    member_no   bigint not null,
    member_name varchar(255),
    member_id varchar(255),
    password varchar(255),
    primary key (member_no)
);
create table chat_room
(
    chat_room_id   bigint not null,
    chatters_count bigint not null,
    primary key (chat_room_id)
);
create table chatter
(
    chatter_id      bigint not null,
    chat_room_id    bigint not null,
    member_no       bigint not null,
    chat_room_name  varchar(255),
    read_message_no bigint not null,
    primary key (chatter_id)
);

insert into members (member_no, member_name, member_id, password)
values (1, 'min', 'min', '1234');
insert into members (member_no, member_name, member_id, password)
values (2, 'sang', 'sang', '1234');

insert into chat_room (chat_room_id, chatters_count)
values (1, 2);
insert into chat_room (chat_room_id, chatters_count)
values (2, 2);
insert into chat_room (chat_room_id, chatters_count)
values (3, 1);

insert into chatter (chatter_id, chat_room_id, member_no, chat_room_name, read_message_no)
values (1, 1, 1, "roomA", 0);

insert into chatter (chatter_id, chat_room_id, member_no, chat_room_name, read_message_no)
values (2, 2, 1, "roomB", 0);

insert into chatter (chatter_id, chat_room_id, member_no, chat_room_name, read_message_no)
values (3, 3, 1, "roomC", 0);

insert into chatter (chatter_id, chat_room_id, member_no, chat_room_name, read_message_no)
values (4, 1, 2, "방A", 0);

insert into chatter (chatter_id, chat_room_id, member_no, chat_room_name, read_message_no)
values (5, 2, 2, "방B", 0);
