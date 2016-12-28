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

insert into m_book
values ("978-4844336389", "スッキリわかるJava入門 第2版", "中山 清喬", "2014/8/7",
	"インプレス", "本書は、Javaの基礎から初学者には難しいとされるオブジェクト指向まで、膨らむ疑問にしっかり対応し、Javaプログラミングの「なぜ?」がわかる解説と約300点の豊富なイラストで、楽しく・詳しく・スッキリとマスターできる構成となっています。",
	"9999", NULL, CURRENT_DATE(), NULL)