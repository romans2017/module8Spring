INSERT INTO roles (id, name) VALUES ('00000000-0000-0000-0000-000000000001', 'ROLE_ADMIN');
INSERT INTO roles (id, name) VALUES ('00000000-0000-0000-0000-000000000002', 'ROLE_USER');
--P@ssword?654
INSERT INTO users (id, email, first_name, password) VALUES ('00000000-0000-0000-0000-000000000003', 'admin@admin.org', 'admin', '{bcrypt}$2a$12$mJH7EcR0mLbmV6Zzf7tbX.nsTcWcELPxiPoFMwhNFC5slwJ4jP35G');
INSERT INTO users_roles (role_id, user_id) VALUES ('00000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000003');
--user
INSERT INTO users (id, email, first_name, password) VALUES ('00000000-0000-0000-0000-000000000004', 'user@user.org', 'user', '{bcrypt}$2y$10$nmqdWjbaj6ZMbSn8SdZEGuwIna0zJqjcvPMqDmtlneMat4pVZYGaa');
INSERT INTO users_roles (role_id, user_id) VALUES ('00000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000004');