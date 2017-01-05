create table m_book(
	isbn varchar(15) not null primary key,
	book_name varchar(200) not null,
    author varchar(200) not null,
    publish_date date not null,
    publisher varchar(200) not null,
    content varchar(200) not null,
    regist_emp_num integer(5),
    update_emp_num integer(5),
    regist_time date,
    update_time date
) CHARACTER SET utf8 COLLATE utf8_general_ci;