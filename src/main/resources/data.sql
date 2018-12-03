insert into role (id, name, display_name, description, approvers, is_deleted, is_requestable, created, updated)
values (301, 'applicatrAprvs', 'Applic 8R Approvers', 'Approvers for Applic 8R', 301, false, true, current_timestamp(), current_timestamp()),
       (302, 'applicatrUser', 'Applic 8R User', 'Standard User for Applic 8R', 301, false, true, current_timestamp(), current_timestamp());

insert into application ( id, name, display_name, description, provisioners, is_deleted, created, updated )
values (101, 'applicatr', 'Applic 8R', 'Manages the applying of things', 301, false, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into entitlement (id, name, display_name, description, business_unit, compliance_type, parent_application, is_deleted, created, updated)
values (201, 'applier', 'Applic 8R applier', 'allows applying', 'IT', 'NONE', 101, false, current_timestamp(), current_timestamp()),
       (202, 'user', 'Applic 8R user', 'standard user', 'IT', 'NONE', 101, false, current_timestamp(), current_timestamp()),
       (203, 'submitter', 'Applic 8R submitter', 'submits Applic 8R applyings', 'IT', 'NONE', 101, false, current_timestamp(), current_timestamp());

