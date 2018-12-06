--Roles
insert into role (id, name, display_name, description, is_deleted, is_requestable, created, updated)
values (301, 'applicatrPrv', 'Applic 8R Provisioners', 'Provisioners for Applic 8R', false, true, current_timestamp(), current_timestamp()),
       (302, 'applicatrUser', 'Applic 8R User', 'Standard User for Applic 8R', false, true, current_timestamp(), current_timestamp()),

       (310, 'stripper', 'Removes measurements', 'Strip Mtrix Provisioners', false, true, current_timestamp(), current_timestamp()),
       (311, 'admin', 'Administers Removals of measurements', 'Strip Mtrix Provisioners', false, true, current_timestamp(), current_timestamp());

--Applications
insert into application ( id, name, display_name, description, provisioner_role_id, is_deleted, created, updated )
values (101, 'applicatr', 'Applic 8R', 'Manages the applying of things', 301, false, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),

       (110, 'stripmetrics', 'Strip Mtrix', 'Removes Measurements', 310, false, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

--Entitlements
insert into entitlement (id, name, display_name, description, business_unit, compliance_type, parent_application_id, is_deleted, created, updated)
values (201, 'applier', 'Applic 8R applier', 'allows applying', 'IT', 'NONE', 101, false, current_timestamp(), current_timestamp()),
       (202, 'user', 'Applic 8R user', 'standard user', 'IT', 'NONE', 101, false, current_timestamp(), current_timestamp()),
       (203, 'submitter', 'Applic 8R submitter', 'submits Applic 8R applyings', 'IT', 'NONE', 101, false, current_timestamp(), current_timestamp()),

       (210, 'stripper', 'Strip Mtrix', 'Removes measurements', 'IT', 'NONE', 110, false, current_timestamp(), current_timestamp()),
       (211, 'metrician', 'Strip Mtrix', 'Measures Removals of measurements', 'IT', 'NONE', 110, false, current_timestamp(), current_timestamp()),
       (212, 'admin', 'Strip Mtrix', 'Administers Removals of measurements', 'IT', 'NONE', 110, false, current_timestamp(), current_timestamp());




