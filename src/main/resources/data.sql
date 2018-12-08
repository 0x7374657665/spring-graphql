--Roles
insert into role (id, name, display_name, description, is_deleted, is_requestable, created, updated)
values (301, 'applicatrPrv', 'Applic 8R Provisioners', 'Provisioners for Applic 8R', false, true, current_timestamp(), current_timestamp()),
       (302, 'applicatrUser', 'Applic 8R User', 'Standard User for Applic 8R', false, true, current_timestamp(), current_timestamp()),

       (310, 'stripperPrv', 'Removes measurements', 'Strip Mtrix Provisioners', false, true, current_timestamp(), current_timestamp()),
       (311, 'admin', 'Administers Removals of measurements', 'Strip Mtrix Provisioners', false, true, current_timestamp(), current_timestamp()),

       (320, 'tecoderPrv', 'TeCoder Provisioners', 'Provisioning group for TeCoder application', false, true, current_timestamp(), current_timestamp()),

       (330, 'flexraPrv', 'FlexRA Provisioners', 'Provisioning group for FlexRA application', false, true, current_timestamp(), current_timestamp()),

       (340, 'htoolsPrv', 'Hyper Tools Provisioners', 'Provisioning group for Hyper Tools application', false, true, current_timestamp(), current_timestamp()),

       (350, 'netvpxPrv', 'Net Virtual Primary Transfer', 'Provisioning group for Net Virtual Primary Transfer application', false, true, current_timestamp(), current_timestamp());

--Applications
insert into application ( id, name, display_name, description, provisioner_role_id, is_deleted, created, updated )
values (101, 'applicatr', 'Applic 8R', 'Manages the applying of things', 301, false, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),

       (110, 'stripmetrics', 'Strip Mtrix', 'Removes Measurements', 310, false, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),

       (120, 'tecoder', 'TeCoder', 'Become Teh Coder', 320, false, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),

       (130, 'flexra', 'FlexRA', 'Flexes Rotary Ancillaries', 330, false, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),

       (140, 'htools', 'Hyper Tools', 'Hyper Tools', 340, false, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),

       (150, 'netvpx', 'Net Virtual Primary Transfer', 'Primary source for virtual transfers', 350, false, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

--Entitlements
insert into entitlement (id, name, display_name, description, business_unit, restricted, parent_application_id, is_deleted, created, updated)
values (201, 'applier', 'Applic 8R applier', 'allows applying', 'IT', false, 101, false, current_timestamp(), current_timestamp()),
       (202, 'user', 'Applic 8R user', 'standard user', 'IT', false, 101, false, current_timestamp(), current_timestamp()),
       (203, 'submitter', 'Applic 8R submitter', 'submits Applic 8R applyings', 'IT', false, 101, false, current_timestamp(), current_timestamp()),

       (210, 'stripper', 'Strip Mtrix', 'Removes measurements', 'IT', false, 110, false, current_timestamp(), current_timestamp()),
       (211, 'metrician', 'Strip Mtrix', 'Measures Removals of measurements', 'IT', false, 110, false, current_timestamp(), current_timestamp()),
       (212, 'admin', 'Strip Mtrix', 'Administers Removals of measurements', 'IT', false, 110, false, current_timestamp(), current_timestamp());




