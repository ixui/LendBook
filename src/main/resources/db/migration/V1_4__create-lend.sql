create table lend(
	lend_id integer(5) not null AUTO_INCREMENT,
	book_stock_id integer(5) not null,
	emp_num integer(5),
    owner_emp_num integer(5),
    lend_date date,
    return_due_date date,
    return_date date,
    regist_emp_num integer(5),
    update_emp_num integer(5),
    regist_time date,
    update_time date,
    primary key (lend_id)
) CHARACTER SET utf8 COLLATE utf8_general_ci;