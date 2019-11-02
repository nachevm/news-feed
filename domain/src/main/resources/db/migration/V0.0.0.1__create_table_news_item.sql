create table news_item
(
    id             varchar(36) primary key not null,
    title          varchar(255)            not null,
    description    text                    not null,
    published_date datetime                not null,
    image_path     varchar(255)
);
