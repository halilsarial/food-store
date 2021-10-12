insert into restaurant (id, version, creation_time, update_time, name, restaurant_chain_id) values (1, 0, sysdate(), null, 'STARTBUCKS', null);
insert into restaurant (id, version, creation_time, update_time, name, restaurant_chain_id) values (2, 0, sysdate(), null, 'STARTBUCKS ERYAMAN', 1);

insert into restaurant (id, version, creation_time, update_time, name, restaurant_chain_id) values (3, 0, sysdate(), null, 'NUSR-ET STEAKHOUSE', null);
insert into restaurant (id, version, creation_time, update_time, name, restaurant_chain_id) values (4, 0, sysdate(), null, 'NUSR-ET STEAKHOUSE DUBAI', 3);
insert into restaurant (id, version, creation_time, update_time, name, restaurant_chain_id) values (5, 0, sysdate(), null, 'NUSR-ET STEAKHOUSE ETILER', 3);

insert into menu (id, version, creation_time, update_time, name, restaurant_id) values (1, 0, sysdate(), null, 'HOT DRINKS', 1);
insert into menu (id, version, creation_time, update_time, name, restaurant_id) values (2, 0, sysdate(), null, 'HOT DRINKS', 2);
insert into menu (id, version, creation_time, update_time, name, restaurant_id) values (3, 0, sysdate(), null, 'ICED DRINKS', 1);
insert into menu (id, version, creation_time, update_time, name, restaurant_id) values (4, 0, sysdate(), null, 'ICED DRINKS', 2);

insert into food (id, version, creation_time, update_time, name, price, menu_id) values (1, 0, sysdate(), null, 'CAFFE LATTE', 11.50, 1);
insert into food (id, version, creation_time, update_time, name, price, menu_id) values (2, 0, sysdate(), null, 'CAFFE LATTE', 11.50, 2);
insert into food (id, version, creation_time, update_time, name, price, menu_id) values (3, 0, sysdate(), null, 'CAPPUCCINO', 11.50, 1);
insert into food (id, version, creation_time, update_time, name, price, menu_id) values (4, 0, sysdate(), null, 'CAPPUCCINO', 11.50, 2);
insert into food (id, version, creation_time, update_time, name, price, menu_id) values (5, 0, sysdate(), null, 'LATTE MACCHIATO', 14.25, 1);
insert into food (id, version, creation_time, update_time, name, price, menu_id) values (6, 0, sysdate(), null, 'LATTE MACCHIATO', 14.25, 2);
insert into food (id, version, creation_time, update_time, name, price, menu_id) values (7, 0, sysdate(), null, 'CARAMEL FRAPPUCCINO', 15.50, 3);
insert into food (id, version, creation_time, update_time, name, price, menu_id) values (8, 0, sysdate(), null, 'CARAMEL FRAPPUCCINO', 15.50, 4);
insert into food (id, version, creation_time, update_time, name, price, menu_id) values (9, 0, sysdate(), null, 'ICED CAPPUCCINO', 12.75, 3);
insert into food (id, version, creation_time, update_time, name, price, menu_id) values (10, 0, sysdate(), null, 'ICED CAPPUCCINO', 12.75, 4);
insert into food (id, version, creation_time, update_time, name, price, menu_id) values (11, 0, sysdate(), null, 'MANGO PASSION FRUIT FRAPPUCCINO', 15.00, 3);
insert into food (id, version, creation_time, update_time, name, price, menu_id) values (12, 0, sysdate(), null, 'MANGO PASSION FRUIT FRAPPUCCINO', 15.00, 4);
