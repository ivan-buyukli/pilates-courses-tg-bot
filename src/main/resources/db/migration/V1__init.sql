create table if not exists bot_users (
id bigserial primary key,
telegram_user_id bigint not null unique,
username varchar(255),
first_name varchar(255),
is_active boolean not null default true,
created_at timestamp not null default now(),
last_activity_at timestamp
);

create table if not exists prepared_messages (
id bigserial primary key,
code varchar(100) not null unique,
title varchar(255),
text_before text,
media_type varchar(30) not null default 'NONE',
telegram_file_id text,
caption text,
text_after text,
button_text varchar(255),
button_type varchar(30),
button_value text,
created_at timestamp not null default now()
);

create table if not exists scheduled_messages (
 id bigserial primary key,
 user_id bigint not null references bot_users(id),
 prepared_message_id bigint not null references prepared_messages(id),
 status varchar(30) not null default 'PENDING',
 scheduled_at timestamp not null,
 sent_at timestamp,
 error_message text,
 created_at timestamp not null default now()
);