create table Product
(
    id          int auto_increment
        primary key,
    name        varchar(255) null,
    prise       double       null,
    category_id int          null
);

create table Category
(
    id   int auto_increment
        primary key,
    name varchar(255) null
);


alter table Product
    add constraint Product_Category_id_fk
        foreign key (category_id) references Category (id);

INSERT INTO `jdbc-example`.`Category` (`name`) VALUES ('Category1')

INSERT INTO `jdbc-example`.Product (id, name, prise, category_id) VALUES (5, 'SP', 11.5, 1);
INSERT INTO `jdbc-example`.Product (id, name, prise, category_id) VALUES (4, 'FP', 12.5, 1);
INSERT INTO `jdbc-example`.Product (id, name, prise, category_id) VALUES (3, 'TP', 2.5, 1);
INSERT INTO `jdbc-example`.Product (id, name, prise, category_id) VALUES (2, 'SP', 12.5, 1);
INSERT INTO `jdbc-example`.Product (id, name, prise, category_id) VALUES (1, 'FirstP', 5.5, 1);