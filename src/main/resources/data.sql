--Roles
insert into role (id, name, display_name, description, is_deleted, is_requestable, created, updated)
values (301, 'applicatrPrv', 'Applic 8R Provisioners', 'Provisioners for Applic 8R', false, true, current_timestamp(), current_timestamp()),

       (310, 'stripperPrv', 'Removes measurements', 'Strip Mtrix Provisioners', false, true, current_timestamp(), current_timestamp()),

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

       (140, 'htools', 'Hyper Tools', 'Hyperbolic Tooling', 340, false, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),

       (150, 'netvpx', 'Net Virtual Primary Transfer', 'Primary source for virtual transfers', 350, false, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

--Entitlements
insert into entitlement (id, name, display_name, description, restricted, parent_application_id, is_deleted, created, updated)
values (200, 'applier', 'Applic 8R applier', 'allows applying', false, 101, false, current_timestamp(), current_timestamp()),
       (201, 'user', 'Applic 8R user', 'standard user', false, 101, false, current_timestamp(), current_timestamp()),
       (202, 'submitter', 'Applic 8R submitter', 'submits Applic 8R applyings', true, 101, false, current_timestamp(), current_timestamp()),

       (210, 'stripper', 'Stripper', 'Removes measurements', false, 110, false, current_timestamp(), current_timestamp()),
       (211, 'metrician', 'Metrician', 'Measures Removals of measurements', false, 110, false, current_timestamp(), current_timestamp()),
       (212, 'admin', 'Administrator', 'Administers Removals of measurements', true, 110, false, current_timestamp(), current_timestamp()),

       (220, 'technician', 'Technician', 'TeCoder Techs', false, 120, false, current_timestamp(), current_timestamp()),
       (221, 'coder', 'Teh Coder', 'TeCoder Coder', false, 120, false, current_timestamp(), current_timestamp()),
       (222, 'readonly', 'Read Only', 'TeCoder Read Only access', false, 120, false, current_timestamp(), current_timestamp()),

       (230, 'flexer', 'Flexer', 'FlexRA Flexer', false, 130, false, current_timestamp(), current_timestamp()),
       (231, 'trammeler', 'Trammeler', 'FlexRA Trammeler', false, 130, false, current_timestamp(), current_timestamp()),
       (232, 'admin', 'Administrator', 'FlexRA Administrator', true, 130, false, current_timestamp(), current_timestamp()),

       (240, 'hyperbolist', 'Hyperbole User', 'Hyperbolic Advanced Users', false, 140, false, current_timestamp(), current_timestamp()),
       (241, 'tooler', 'Tooler', 'Hyperbolic Tooling', false, 140, false, current_timestamp(), current_timestamp()),
       (242, 'user', 'User', 'Hyperbolic Users', false, 140, false, current_timestamp(), current_timestamp()),

       (250, 'vpx', 'VP Transferer', 'NetVPX Transferer', false, 150, false, current_timestamp(), current_timestamp()),
       (251, 'user', 'User', 'NetVPX User', false, 150, false, current_timestamp(), current_timestamp()),
       (252, 'admin', 'Administrator', 'NetVPX Administrator', true, 150, false, current_timestamp(), current_timestamp());



