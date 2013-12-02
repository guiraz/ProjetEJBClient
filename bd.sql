drop table Route;
drop table Vehicule;
drop table Routeplan;
drop table TimeoutLog;
drop table Crisis;

create table Crisis(
    idcrisis varchar(10),
    longitude float(6) not null,
    latitude float(6) not null,
    t timestamp not null,
    statut varchar(6) not null,
    description varchar(1000),
    constraint crisisidcrisis_pk primary key(idcrisis),
    constraint statut_check check(statut = 'Active' or statut = 'Closed')
);

create table TimeoutLog(
    idcrisis varchar(10),
    d timestamp not null,
    fscreason varchar(100),
    pscreason varchar(100),
    constraint timeoutlogidcrisis_pk primary key(idcrisis),
    constraint timeoutlog_fk_1 foreign key(idcrisis) references Crisis(idcrisis) on delete cascade
);

create table Routeplan(
    idcrisis varchar(10),
    nbpolicevehicule int not null,
    nbfirevehicule int not null,
    constraint routeplanidcrisis_pk primary key(idcrisis),
    constraint routeplan_fk_1 foreign key(idcrisis) references Crisis(idcrisis) on delete cascade
);

create table Vehicule(
    idvehicule varchar(10),
    eta timestamp,
    position varchar(7) not null,
    type varchar(6) not null,
    constraint vehiculeidvehicule_pk primary key(idvehicule),
    constraint posotion_check_1 check(position = 'Station' or position = 'ERTL' or position = 'AL' or position = 'ERTS'),
    constraint type_check_2 check(type = 'Police' or type = 'Fire')
);

create table Route(
    idvehicule varchar(10),
    idcrisis varchar(10),
    nomroute varchar(15) not null,
    constraint routeidcrisis_pk primary key(idcrisis,idvehicule),
    constraint route_fk_1 foreign key(idcrisis) references Crisis(idcrisis) on delete cascade,
    constraint route_fk_2 foreign key(idvehicule) references Vehicule(idvehicule) on delete cascade
);

insert into Vehicule values ('0001AA64', NULL, 'Station', 'Police');
insert into Vehicule values ('0002AA64', NULL, 'Station', 'Fire');
insert into Vehicule values ('0003AA64', NULL, 'Station', 'Police');
insert into Vehicule values ('0004AA64', NULL, 'Station', 'Fire');
insert into Vehicule values ('0005AA64', NULL, 'Station', 'Police');
