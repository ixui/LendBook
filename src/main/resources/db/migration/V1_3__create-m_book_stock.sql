create table m_book_stock(
	book_stock_id integer(5) not null primary key,
	isbn varchar(15) not null,
    owner_emp_num integer(5) not null default 9999,
    regist_emp_num integer(5),
    update_emp_num integer(5),
    regist_time date,
    update_time date
) CHARACTER SET utf8 COLLATE utf8_general_ci;