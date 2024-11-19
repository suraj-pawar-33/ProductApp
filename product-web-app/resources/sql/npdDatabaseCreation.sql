
CREATE TABLE status (
     status_key TINYINT NOT NULL AUTO_INCREMENT,
	 status_name CHAR(30),
	 status_descr CHAR(30),
	 creation_time timestamp default '1995-03-03 04:15:00', 
	 last_modified_time timestamp default now() on update now(), 
     PRIMARY KEY (status_key),
	 UNIQUE (status_name)
);

alter table status AUTO_INCREMENT=20;

INSERT INTO status (creation_time, status_name, status_descr)
VALUES ( NOW(), 'PAID', 'Order amount is fully paid');

INSERT INTO status (creation_time, status_name, status_descr)
VALUES ( NOW(), 'PA PAID', 'Order amount is partially paid');

INSERT INTO status (creation_time, status_name, status_descr)
VALUES (NOW(), 'NOT PAID', 'Order amount is not paid');

INSERT INTO status (creation_time, status_name, status_descr)
VALUES (NOW(),'SUBSCRIBED', 'Subscription for month');

INSERT INTO status (creation_time, status_name, status_descr)
VALUES (NOW(),'UNSUBSCRIBED', 'Subscription period ended');

INSERT INTO status (creation_time, status_name, status_descr)
VALUES (NOW(),'DELIVERED', 'Order delivered');

INSERT INTO status (creation_time, status_name, status_descr)
VALUES (NOW(),'NEW ORDER', 'New order');


CREATE TABLE order_type (
     ord_type_key TINYINT NOT NULL AUTO_INCREMENT,
     ord_type_name CHAR(30) NOT NULL,
	 short_name CHAR(30),
	 ord_type_details CHAR(30),
	 creation_time timestamp default '1995-03-03 04:15:00', 
	 last_modified_time timestamp default now() on update now(), 
     PRIMARY KEY (ord_type_key),
	 UNIQUE (ord_type_name)
);

alter table order_type AUTO_INCREMENT=40;

INSERT INTO order_type (creation_time, ord_type_name, short_name, ord_type_details)
VALUES (NOW(), 'ONE TIME', 'OT', 'Order is one time purchase');

INSERT INTO order_type (creation_time, ord_type_name, short_name, ord_type_details)
VALUES (NOW(), 'MONTHLY SUBSCRIPTION', 'MS','Monthly subscription');

INSERT INTO order_type (creation_time, ord_type_name, short_name, ord_type_details)
VALUES (NOW(), 'YEARLY SUBSCRIPTION', 'YT', 'Yearly subscription');


CREATE TABLE product_type (
     pro_type_key MEDIUMINT NOT NULL AUTO_INCREMENT,
     pro_type_name CHAR(30) NOT NULL,
	 short_name CHAR(30),
	 pro_type_details CHAR(60),
	 creation_time timestamp default '1995-03-03 04:15:00', 
	 last_modified_time timestamp default now() on update now(), 
     PRIMARY KEY (pro_type_key),
	 UNIQUE (pro_type_name)
);

alter table product_type AUTO_INCREMENT=1212;

INSERT INTO product_type (creation_time, pro_type_name, short_name, pro_type_details)
VALUES (NOW(), 'Plant', 'plant', 'Indoor plants');

INSERT INTO product_type (creation_time, pro_type_name, short_name, pro_type_details)
VALUES (NOW(), 'Sanitizer', 'sanitizer', 'Hand sanitizers');

INSERT INTO product_type (creation_time, pro_type_name, short_name, pro_type_details)
VALUES (NOW(), 'Gloves', 'gloves', 'Hand gloves');

INSERT INTO product_type (creation_time, pro_type_name, short_name, pro_type_details)
VALUES (NOW(), 'Tea', 'tea', 'Tea packages');

INSERT INTO product_type (creation_time, pro_type_name, short_name, pro_type_details)
VALUES (NOW(), 'Coffee', 'coffee', 'Coffee packages');


CREATE TABLE address (
     address_key INT NOT NULL AUTO_INCREMENT,
     line_1 CHAR(100) NOT NULL,
	 line_2 CHAR(100),
	 line_3 CHAR(100),
	 city CHAR(30) NOT NULL,
	 zip_postcode  INT NOT NULL,
	 state CHAR(30),
	 country CHAR(30),
	 other CHAR(50),
	 creation_time timestamp default '1995-03-03 04:15:00', 
	 last_modified_time timestamp default now() on update now(), 
     PRIMARY KEY (address_key)
);

alter table address AUTO_INCREMENT=5757;

CREATE TABLE bank (
     bank_key MEDIUMINT NOT NULL AUTO_INCREMENT,
     bank_name CHAR(30) NOT NULL,
	 short_name CHAR(30) NOT NULL,
	 branch_name CHAR(30) NOT NULL,
	 address_key  INT ,
	 creation_time timestamp default '1995-03-03 04:15:00', 
	 last_modified_time timestamp default now() on update now(), 
     PRIMARY KEY (bank_key),
	 FOREIGN KEY (address_key) REFERENCES address(address_key)
);

alter table bank AUTO_INCREMENT=2121;

CREATE TABLE bank_contact (
     contact_key INT NOT NULL AUTO_INCREMENT,
     contact_name CHAR(30) NOT NULL,
	 bank_key  MEDIUMINT NOT NULL,
	 contact_number CHAR(30),	 
	 address_key  INT ,
	 creation_time timestamp default '1995-03-03 04:15:00', 
	 last_modified_time timestamp default now() on update now(), 
     PRIMARY KEY (contact_key),
	 FOREIGN KEY (address_key) REFERENCES address(address_key),
	 FOREIGN KEY (bank_key) REFERENCES bank(bank_key)
);

alter table bank_contact AUTO_INCREMENT=3434;

CREATE TABLE orders (
     order_key INT NOT NULL AUTO_INCREMENT,
     bill_number INT UNIQUE,
	 date_order_placed timestamp default '1995-03-03 04:15:00',
	 date_order_paid timestamp default '1995-03-03 04:15:00' ,
	 bank_key  MEDIUMINT ,
	 ord_type_key TINYINT,
	 total_amount INT,
	 status_key TINYINT,
	 creation_time timestamp default '1995-03-03 04:15:00', 
	 last_modified_time timestamp default now() on update now(), 
     PRIMARY KEY (order_key),
	 FOREIGN KEY (status_key) REFERENCES status(status_key),
	 FOREIGN KEY (bank_key) REFERENCES bank(bank_key),
	 FOREIGN KEY (ord_type_key) REFERENCES order_type(ord_type_key)
);

alter table orders AUTO_INCREMENT=6262;


CREATE TABLE product (
     product_key INT NOT NULL AUTO_INCREMENT,
     product_name CHAR(30) NOT NULL,
     short_name CHAR(30),
     product_details CHAR(50),
     cost INT,
     subscri_cost INT,
     pro_type_key  MEDIUMINT ,
     creation_time timestamp default '1995-03-03 04:15:00', 
     last_modified_time timestamp default now() on update now(), 
     PRIMARY KEY (product_key),
     FOREIGN KEY (pro_type_key) REFERENCES product_type(pro_type_key)
);

alter table product AUTO_INCREMENT=9393;

CREATE TABLE order2product (
	 ord2pro_key INT NOT NULL AUTO_INCREMENT,
     order_key INT NOT NULL,
	 product_key INT NOT NULL,
	 quantity INT NOT NULL,
	 amount INT,
	 creation_time timestamp default '1995-03-03 04:15:00', 
	 last_modified_time timestamp default now() on update now(), 
     PRIMARY KEY (ord2pro_key),
	 FOREIGN KEY (order_key) REFERENCES orders(order_key),
	 FOREIGN KEY (product_key) REFERENCES product(product_key)
);

alter table order2product AUTO_INCREMENT=2020;
