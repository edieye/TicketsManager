CREATE SCHEMA `ticketmanager`;
use ticketmanager;
#SET foreign_key_checks = 0;


## COMMENT THIS OUT
#drop table employee_accessibility cascade;
#drop table customer cascade;
#drop table customer_info cascade;
#drop table attend cascade;
#drop table event_create cascade;
#drop table ticket cascade;
#drop table has_ticket cascade;
#drop table venue cascade;
#drop table venue_specs cascade;
#drop table venue_detailed cascade;
#drop table producer cascade;
#drop table producer_type cascade;
#drop table works_for cascade;
#drop table schedules cascade;
#drop table oversee cascade;
#drop table employee cascade ;
#drop table userNames;


#SET foreign_key_checks = 1;


CREATE TABLE employee(
	eid integer AUTO_INCREMENT NOT NULL,
	name char(20),
	empType char(20),
    -- need index keys apparently to do foreign keys
  key (eid),
  primary key(eid)
);

 CREATE TABLE employee_accessibility(
 	empType char(20),
 	accessibility boolean,
  eid integer AUTO_INCREMENT NOT NULL PRIMARY KEY,
 	foreign key (eid) references employee(eid)
		ON DELETE cascade
    ON UPDATE cascade
 );

 CREATE TABLE customer(
	  custID integer not null,
    name char(20),
    attended boolean,
    key (custID),
    primary key(custID)
);


CREATE TABLE customer_info(
	custID integer not null,
    phoneNum integer(30),
    addr char(50),
    name char(20),
	foreign key (custID) references customer(custID)
		ON DELETE cascade
        ON UPDATE cascade
 );

CREATE TABLE ticket (
	ticketNum integer not null,
  seatNum integer,
  ticType char(20),
  section char(20),
    custID integer NOT NULL,
    confirmNum integer,
    ticsPurchased integer,
    ticPrice integer,
    key (ticketNum),
    primary key(ticketNum),
    foreign key (custID) references customer(custID)
		on delete cascade
        on update cascade
);

CREATE TABLE venue (
	city char(20),
  street char(20),
  postalCode char(20) not null,
  key (postalCode),
  primary key (postalCode)
);

CREATE TABLE venue_specs(
	stageCap integer,
  eventType char(20)
);

CREATE TABLE venue_detailed (
	vID integer not null,
    name char(20),
    postalCode char(20) not null,
    seatCap integer,
    stageCap integer,
    key (vID),
    primary key (vID),
    foreign key (postalCode) references venue(postalCode)
		on delete cascade
        on update cascade
);

CREATE TABLE event_create(
	eventID integer not null,
    title char(20),
    capacity integer,
    dateTime char(20),
    ticSold integer not null,
    vipPrice integer,
    gaPrice integer,
    vID integer NOT NULL,
    key (eventID, ticSold),
    primary key(eventID, ticSold),
    foreign key (vID) references venue_detailed(vID)
		on delete cascade
        on update cascade
);

 CREATE TABLE attend (
	eid integer not null,
    eventID integer not null,
    ticketNum integer not null,
    custID integer not null,
    ticSold integer not null,
    primary key (eventID, ticketNum, custID, ticSold),
    foreign key (ticketNum) references ticket(ticketNum)
		    on delete cascade
        on update cascade,
    foreign key (eid) references employee(eid)
		    on delete cascade
        on update cascade,
    foreign key (custID) references customer(custID)
		  on delete cascade
        on update cascade,
    foreign key (eventID, ticSold) references event_create(eventID, ticSold)
		  on delete cascade
      on update cascade
);





CREATE TABLE has_ticket (
	ticketNum integer not null,
    seatNum integer,
    ticType char(20),
    section char(20),
    eventID integer not null,
    ticSold integer not null,
    key (ticketNum),
    primary key (ticketNum),
    foreign key (ticketNum) references ticket(ticketNum)
		  on delete cascade
      on update cascade,
    foreign key (eventID, ticSold) references event_create(eventID, ticSold)
		  on delete cascade
      on update cascade
);


CREATE TABLE producer (
	name char(20),
    pID integer not null,
    company char(20),
    primary key (pID)
);

CREATE TABLE producer_type (
	company char(20),
    prodType char(20)
);

CREATE TABLE works_for (
	vID integer not null,
    eID integer not null,
    postalCode char(20) not null,
    primary key (vID, eID, postalCode),
    foreign key (vID) references venue_detailed(vID)
		on delete cascade
        on update cascade,
    foreign key (postalCode) references venue(postalCode)
		on delete cascade
        on update cascade,
    foreign key (eID) references employee(eID)
		on delete cascade
        on update cascade
);

CREATE TABLE schedules (
	vID integer not null,
    pID integer not null,
    primary key (vID, pID),
    foreign key (vID) references venue_detailed(vID),
    foreign key (pID) references producer(pID)
);


CREATE TABLE oversee (
	eID integer,
    custID integer,
    eventID integer,
    ticSold integer,
    primary key (eID, custID, eventID, ticSold),
    foreign key(custID) references customer(custID)
		on delete cascade
        on update cascade,
    foreign key(eID) references employee(eid)
		on delete cascade
        on update cascade,
	foreign key (eventID) references event_create(eventID)
		on delete cascade
        on update cascade
);


CREATE TABLE userNames (
  username CHAR(30),
  pass CHAR(30)
);

-- woo now time to populate some tuples

-- populate employees
insert into employee values(null, "Anne Smith", "Manager");
insert into employee_accessibility values ("Manager", true, LAST_INSERT_ID(eid));
insert into employee values(null, "David Green", "Clerk");
insert into employee_accessibility values ("Clerk", false, LAST_INSERT_ID(eid));
insert into employee values(null, "Katie Scott", "Clerk");
insert into employee_accessibility values ("Clerk", false, LAST_INSERT_ID(eid));
insert into employee values(null, "John Adams", "Clerk");
insert into employee_accessibility values ("Clerk", false, LAST_INSERT_ID(eid));
insert into employee values(null, "Sam Miller", "Manager");
insert into employee_accessibility values ("Manager", true, LAST_INSERT_ID(eid));


-- populate employee_accessibility
#insert into employee_accessibility values ("Manager", true, 10001);
#insert into employee_accessibility values ("Clerk", false, 10002);
#insert into employee_accessibility values ("Clerk", false, 10003);
#insert into employee_accessibility values ("Clerk", false, 10004);
#insert into employee_accessibility values ("Manager", true, 10005);

-- populate customers
insert into customer values (1, "Bob Smith", true);
insert into customer values (2, "Bobby Li", true);
insert into customer values (3, "Jason Black", false);
insert into customer values (4, "Dennis Tran", true);
insert into customer values (5, "Jay Sean", true);
insert into customer values (6, "Naomi Morcilla", true);

-- populate ticket
insert into ticket values (50001, 4, "General Admission", "C", 1, 123456789, 2,5);
insert into ticket values (50002, 5, "General Admission", "C", 2, 234567891,2,5);
insert into ticket values (51000, 12, "VIP", "A", 4, 345678912, 5,50);
insert into ticket values (51001, 1, "General Admission", "B", 5, 456789123, 1,30);
insert into ticket values (51009, 8, "General Admission", "D", 2, 567891234, 3,30);
insert into ticket values (52000, 32, "VIP", "A", 5, 678912345, 1,1000);
insert into ticket values(51019,3,"VIP","A",3,098765432,1,1000);
insert into ticket values(23,82,"General Admission","B",3,098775432,4,500);
insert into ticket values(456,3,"VIP","A",3,098665432,8,500);
insert into ticket values (4999, 4, "General Admission","C",3, 98765789, 20,20);
insert into ticket values (4988,16,"VIP","A",3,765456767,300,2);

insert into ticket values (100, 1, "VIP", "A", 6, 918273, 100, 100);
insert into ticket values (101, 1, "VIP", "A", 6, 918274, 100, 100);
insert into ticket values (102, 1, "VIP", "A", 6, 918275, 100, 100);
insert into ticket values (103, 1, "VIP", "A", 6, 918276, 100, 100);
insert into ticket values (104, 1, "VIP", "A", 6, 918277, 100, 100);

-- populate venue
insert into venue values ("Vancouver", "Ontario Street", "V5X3E3");
insert into venue_detailed values (1000, "Roger's Arena", "V5X3E3", 15000, 50);
insert into venue values ("Vancouver", "Victoria Drive", "V5P3G4");
insert into venue_detailed values (1001, "Staples Centre", "V5P3G4", 12000, 50);
insert into venue values ("Vancouver", "Burke Street", "V4F4E4");
insert into venue_detailed values (1005, "Staples Centre", "V4F4E4", 12000, 50);
insert into venue values ("Toronto", "Oaklands Ave", "M4V1A1");
insert into venue_detailed values (1002, "Dodger's Stadium", "M4V1A1", 13000, 55);
insert into venue values ("Toronto", "Wellington Street", "M5V1E3");
insert into venue_detailed values (1003, "Diamond", "M5V1E3", 200, 4);
insert into venue values ("Edmonton", "Victoria Ave", "V5P3G2");
insert into venue_detailed values (1004, "GM Place", "V5P3G2", 50,  2);

-- populate event_create
insert into event_create values (1111, "Canucks VS Oilers", 15000,"Feb 14, 2017",1200, 10,5, 1000);
insert into event_create values (1112, "Canucks VS Leafs",12000, "Feb 29, 2017", 1000, 50, 30, 1001);
insert into event_create values (1113, "Canucks VS Kings", 13000, "Mar 25, 2017",13000, 25,20,1002);
insert into event_create values (1114, "Beyonce World Tour", 13000, "Apr 19, 2017",9244,2,1, 1002);
insert into event_create values (1115, "Hannah Montana", 200, "June 21, 2017", 199,1000,500, 1003);




-- populate customer info
insert into customer_info values (1, 5555555, "66 West Georgia, Vancouver, BC", "Bob Smith");
insert into attend values (2, 1111, 50001, 1, 1200);
insert into customer_info values (2, 6666666, "535 Pender St, Vancouver, BC", "Bobby Li");
insert into attend values (2, 1111, 50002, 2, 1200);
insert into attend values (2, 1112, 51009, 2, 1000);
insert into attend values (3, 1113, 51019, 2, 13000);
insert into attend values (2, 1114,23,2,9244);
insert into attend values (3, 1115, 456, 2, 199);
insert into customer_info values (3, 7777777, "23 Venables St, East Vancouver, BC", "Jason Black");
insert into attend values (3, 1112, 51000, 4, 1000);
insert into customer_info values (4, 8888888, "31 6th Ave, Vancouver, BC", "Dennis Tran");
insert into customer_info values (5, 9999999, "3443 Clyde Ave, Vancouver, BC", "Jay Sean");
insert into attend values (4, 1112, 51001, 5, 1000);

insert into customer_info values (6, 9999991, "3443 Clyde Ave, Vancouver, BC", "Naomi Morcilla");
insert into attend values (4, 1111, 100, 6, 1200);
insert into attend values (4, 1112, 101, 6, 1000);
insert into attend values (4, 1113, 102, 6, 13000);
insert into attend values (4, 1114, 103, 6, 9244);
insert into attend values (4, 1115, 104, 6, 199);


-- populate venue_specs
insert into venue_specs values (50, "Hockey Game");
insert into venue_specs values (2, "Concert");
insert into venue_specs values (30, "Orchestra");



-- populate has_ticket
insert into has_ticket values (4988, 16, "VIP","A",1114,9244);
insert into has_ticket values (4999, 4, "General Admission","C",1113,13000);
insert into has_ticket values (50001, 4, "General Admission", "C", 1111, 1200);
insert into has_ticket values (50002, 5, "General Admission", "C",  1111, 1200);
insert into has_ticket values (51000, 12, "VIP", "A", 1112, 1000);
insert into has_ticket values (51001, 1, "General Admission", "B",1112, 1000);
insert into has_ticket values (51009, 8, "General Admission", "D",1112, 1000);
insert into has_ticket values (52000, 32, "VIP", "A", 1115, 199);
insert into has_ticket values (51019,3,"VIP","A",1115,199);
insert into has_ticket values (23,82,"General Admission","B", 1115,199);
insert into has_ticket values (456,3,"VIP","A",1115,199);

insert into has_ticket values (100, 1, "VIP", "A", 1111, 1200);
insert into has_ticket values (101, 1, "VIP", "A", 1112, 1000);
insert into has_ticket values (102, 1, "VIP", "A", 1113, 13000);
insert into has_ticket values (103, 1, "VIP", "A", 1114, 9244);
insert into has_ticket values (104, 1, "VIP", "A", 1115, 199);


-- populate producer
insert into producer values ("Wilson Lee", 0010, "Blueprint");
insert into producer values ("Amanda Bynes", 0011, "Diamond Events");
insert into producer values ("DJ Trouble", 0012, "Blueprint");
insert into producer values ("Rachel Bilson", 0013, "Vector Sports");
insert into producer values ("Tom Brady", 0014, "Vector Sports");

-- populate producer_type
insert into producer_type values ("Blueprint", "Music Producer");
insert into producer_type values ("Diamond Events", "Music Producer");
insert into producer_type values ("Vector Sports", "Sports Manager");

-- populate works_for
insert into works_for values (1000, 1, "V5X3E3");
insert into works_for values (1001, 2, "V5P3G4");
insert into works_for values (1002, 3, "M4V1A1");
insert into works_for values (1003, 4, "M5V1E3");
insert into works_for values (1004, 5, "V5P3G2");
insert into works_for VALUES (1005,5,"V4F4E4");

-- populate schedules
insert into schedules values (1000 , 0014);
insert into schedules values (1001, 0011);
insert into schedules values (1002, 0012);
insert into schedules values (1003, 0013);
insert into schedules values (1004, 0014);
insert into schedules values (1005, 0014);

insert into oversee values (1, 1, 1111, 1200);
insert into oversee values (2, 2, 1112,  1000);
insert into oversee values (3, 3, 1113, 6700);
insert into oversee values (4, 4, 1114, 9244);
insert into oversee values (5, 5, 1115, 199);

insert into userNames values ("manager", "abcdef");
insert into userNames values ("clerk", "abcdef");
insert into userNames values ("user", "abcdef");


