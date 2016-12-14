create table m_emp(
	emp_num integer(5) not null primary key,
	mail_address varchar(255) not null,
    emp_name varchar(50) not null,
    password varchar(80) not null,
    admin_frag varchar(1) not null,
    regsit_emp_num integer(5),
    update_emp_num integer(5),
    regist_time date,
    update_time date
) CHARACTER SET utf8 COLLATE utf8_general_ci;

insert into m_emp
values (9999, 'admin@tosyo.co.jp', '初期管理ユーザー', '$2a$10$nj9jU107JM/ol9PeZkcmVObw45bT8KtpV9DRVFYk3hJCNDvwK4FpW', '1', NULL, NULL, CURRENT_DATE(), null);
insert into m_emp
values (9998, 'general@tosyo.co.jp', '初期一般ユーザー', '$2a$10$nj9jU107JM/ol9PeZkcmVObw45bT8KtpV9DRVFYk3hJCNDvwK4FpW', '0', NULL, NULL, CURRENT_DATE(), null);