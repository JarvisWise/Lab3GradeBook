--drop_s
DROP SEQUENCE user_id_seq;
DROP SEQUENCE  subject_id_seq;
DROP SEQUENCE  task_id_seq;
DROP SEQUENCE group_id_seq;
DROP SEQUENCE student_subject_id_seq;

--create_c
CREATE SEQUENCE user_id_seq;
CREATE SEQUENCE subject_id_seq;
CREATE SEQUENCE task_id_seq;
CREATE SEQUENCE group_id_seq;
CREATE SEQUENCE student_subject_id_seq;

--drop_t --mb erase cascade
DROP TABLE student /*CASCADE*/;
DROP TABLE teacher /*CASCADE*/;
DROP TABLE subject /*CASCADE*/;
DROP TABLE task /*CASCADE*/;
DROP TABLE group_ /*CASCADE*/;
DROP TABLE student_subject /*CASCADE*/;
DROP TABLE student_task /*CASCADE*/;
DROP TABLE teacher_subject /*CASCADE*/;

Create table  student(
                         student_id  INTEGER NOT NULL ,
                         headman INTEGER,
                         email /*login_name*/ varchar2(30) UNIQUE,
                         first_name varchar2(30),
                         last_name varchar2(30),
                         group_id INTEGER ,
                         password varchar2(30),
                         primary key(student_id)
);


Create table  teacher(
                         teacher_id  INTEGER NOT NULL ,
                         email /*login_name*/ varchar2(30) UNIQUE,
                         first_name varchar2(30),
                         last_name varchar2(30),
                         password varchar2(30),
                         primary key(teacher_id)
);

Create table  subject(
                         subject_id INTEGER NOT NULL ,
                         subject_name varchar2(30),
                         max_grade INTEGER NOT NULL,
                         pass_proc_grade INTEGER NOT NULL,
                         subject_description varchar2(1000),
                         primary key(subject_id)
);

Create table  task(
                      task_id INTEGER NOT NULL ,
                      subject_id INTEGER NOT NULL,
                      task_name varchar2(30),
                      max_grade INTEGER NOT NULL,
                      task_description varchar2(4000),
                      primary key(task_id)
);

Create table  group_(
                        group_id INTEGER NOT NULL ,
                        group_name varchar2(30),
                        group_description varchar2(1000),
                        primary key(group_id)
);

Create table  student_subject(
                                 student_subject_id INTEGER NOT NULL , --mb no need pk
                                 total_grade INTEGER ,
                                 student_id INTEGER NOT NULL ,
                                 subject_id INTEGER NOT NULL ,
    --teacher_id INTEGER NOT NULL , --delete this field
                                 primary key(student_subject_id) --mb no need pk
);

--mb add student_id field
Create table  student_task(
                              student_subject_id INTEGER NOT NULL ,
                              task_id INTEGER NOT NULL ,
                              subject_id INTEGER NOT NULL ,
                              grade INTEGER
);

Create table teacher_subject(
                                subject_id INTEGER NOT NULL ,
                                teacher_id INTEGER NOT NULL
);

--students
INSERT INTO student VALUES(user_id_seq.nextval, null, 'login1', 'bob1', 'smith', 1, '1234');
INSERT INTO student VALUES(user_id_seq.nextval, null, 'login2', 'bob2', 'smith', 1, '1234');
INSERT INTO student VALUES(user_id_seq.nextval, null, 'login3', 'bob3', 'smith', 2, '1234');
INSERT INTO student VALUES(user_id_seq.nextval, null, 'login4', 'bob4', 'smith', 2, '1234');

--teachers
INSERT INTO teacher VALUES(user_id_seq.nextval, 'login5', 'bill1', 'smith', '1234');
INSERT INTO teacher VALUES(user_id_seq.nextval, 'login6', 'bill2', 'smith', '1234');
INSERT INTO teacher VALUES(user_id_seq.nextval, 'login7', 'bill3', 'smith', '1234');

--subjects
INSERT INTO subject VALUES(subject_id_seq.nextval, 'subject1', 100, 60, 'desc subject'); --for pass better %
INSERT INTO subject VALUES(subject_id_seq.nextval, 'subject2', 100, 60, 'desc subject'); --for pass better %
INSERT INTO subject VALUES(subject_id_seq.nextval, 'subject3', 100, 60, 'desc subject'); --for pass better %

--tasks
INSERT INTO task VALUES(task_id_seq.nextval, 1 /*sub id*/, 'task1', 7, 'desc task');
INSERT INTO task VALUES(task_id_seq.nextval, 2 /*sub id*/, 'task2', 8, 'desc task');
INSERT INTO task VALUES(task_id_seq.nextval, 3 /*sub id*/, 'task3', 9, 'desc task');

--groups
INSERT INTO group_ VALUES(group_id_seq.nextval, 'group1', 'desc group');
INSERT INTO group_ VALUES(group_id_seq.nextval, 'group2', 'desc group');
INSERT INTO group_ VALUES(group_id_seq.nextval, 'group3', 'desc group');

--student_subject
INSERT INTO student_subject VALUES(student_subject_id_seq.nextval, 70,1, 1);
INSERT INTO student_subject VALUES(student_subject_id_seq.nextval, 50,1, 2);
INSERT INTO student_subject VALUES(student_subject_id_seq.nextval, 80,1, 3);
INSERT INTO student_subject VALUES(student_subject_id_seq.nextval, 20,2, 1);
INSERT INTO student_subject VALUES(student_subject_id_seq.nextval, 40,2, 2);
INSERT INTO student_subject VALUES(student_subject_id_seq.nextval, 60,2, 3);
INSERT INTO student_subject VALUES(student_subject_id_seq.nextval, 90,3, 1);
INSERT INTO student_subject VALUES(student_subject_id_seq.nextval, 40,3, 2);
INSERT INTO student_subject VALUES(student_subject_id_seq.nextval, 60,3, 3);
INSERT INTO student_subject VALUES(student_subject_id_seq.nextval, 20,4, 1);
INSERT INTO student_subject VALUES(student_subject_id_seq.nextval, 70,4, 2);
INSERT INTO student_subject VALUES(student_subject_id_seq.nextval, 60,4, 3);

--student_task
INSERT INTO student_task VALUES(1, 1, 1, 5);
INSERT INTO student_task VALUES(2, 2, 2, 5);
INSERT INTO student_task VALUES(3, 3, 3, 5);
INSERT INTO student_task VALUES(4, 1, 1, 5);
INSERT INTO student_task VALUES(5, 2, 2, 5);
INSERT INTO student_task VALUES(6, 3, 3, 5);
INSERT INTO student_task VALUES(7, 1, 1, 5);
INSERT INTO student_task VALUES(8, 2, 2, 5);
INSERT INTO student_task VALUES(9, 3, 3, 5);
INSERT INTO student_task VALUES(10, 1, 1, 5);
INSERT INTO student_task VALUES(11, 2, 2, 5);
INSERT INTO student_task VALUES(12, 3, 3, 5);

--teacher_subject
INSERT INTO teacher_subject VALUES(1, 5);
INSERT INTO teacher_subject VALUES(1, 6);
INSERT INTO teacher_subject VALUES(1, 7);
INSERT INTO teacher_subject VALUES(2, 5);
INSERT INTO teacher_subject VALUES(2, 6);
INSERT INTO teacher_subject VALUES(2, 7);
INSERT INTO teacher_subject VALUES(3, 5);
INSERT INTO teacher_subject VALUES(3, 6);
INSERT INTO teacher_subject VALUES(3, 7);
commit;