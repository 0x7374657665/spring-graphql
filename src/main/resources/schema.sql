create table if not exists application (
  id bigint not null,
  name varchar(100) not null,
  display_name varchar(100) not null,
  description varchar(100),
  provisioner_role_id bigint,
  is_deleted boolean,
  created timestamp,
  updated timestamp
  );

create table if not exists entitlement (
  id bigint not null,
  name varchar(100) not null,
  display_name varchar(100) not null,
  description varchar(100),
  business_unit varchar(100) not null,
  restricted boolean not null,
  parent_application_id bigint not null,
  is_deleted boolean,
  created timestamp,
  updated timestamp
  );

create table if not exists role (
  id bigint not null,
  name varchar(100) not null,
  display_name varchar(100) not null,
  description varchar(100),
  is_deleted boolean,
  is_requestable boolean,
  created timestamp,
  updated timestamp
  );

alter table application
  add foreign key (provisioner_role_id) references role(id);

alter table entitlement
  add foreign key (parent_application_id) references application(id);

create table if not exists policy (
  id bigint not null,
  role_id bigint not null,
  entitlement_id bigint not null,
  created timestamp 
  );

alter table policy
  add foreign key (role_id) references role(id);

alter table policy
  add foreign key (entitlement_id) references entitlement(id);

create table if not exists person (
  id bigint not null,
  first_name varchar(100),
  last_name varchar(100),
  login varchar(100) not null,
  enabled boolean,
  created timestamp,
  updated timestamp
  );

create table if not exists access (
  id bigint not null,
  person_id bigint not null,
  role_id bigint not null,
  enabled boolean,
  created timestamp
  );

alter table access
  add foreign key (person_id) references person(id);

alter table access
  add foreign key (role_id) references role(id);

create table if not exists account (
  id bigint not null,
  person_id bigint,
  application_id bigint not null,
  account_name varchar(100) not null,
  created timestamp
  );

alter table account
  add foreign key (person_id) references person(id);

alter table account
  add foreign key (application_id) references application(id);

