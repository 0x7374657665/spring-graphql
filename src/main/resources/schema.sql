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