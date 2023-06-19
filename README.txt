
                                                              In the name of Allah, The most gracious, the most merciful 


Group_name : data base groub 16.

memberes of groub : 
Ahmed Humeid          120200887
Anas Abu Al_Tarabish  120200999
Yassen Abu Zeina      120200291

Requirments to run the project you must have these libraries ↓↓

1- jxl
2- org.controlsfx


→ important things ←

all jdbc methods exist in DBModel in (model)
Navigation class have navigateTo method to redirect to new interface 
controlleres have autoComplete method and use the DBModel mehods 
DBModel consist of connection method with data base name databasegroub16  and data base password databasegroub16 
to export and import excel file , the file should be "Excel 97-2003 Workbook" = .xls
the user data is user id :120200887 uaser password :123


Description our project : 

firstly, our project contain some parts like : GUI , Java code , sql then convert it to jdbc

our project build on MVC design pattern (model, view, controller)

the main iterface to our project is login which it has two text field , one for the user name and the other for the password 
in our project we have two type of useres 
first: admin who control the project 
second: teacher assistant who has some features 

if the user entered admin in the user_name and password it will automatically convert to new interface contain his features like :

1- Students
2- lectures
3- course 
4- teacher assistant
5- takes 

if i click on any one of previous it will convert me to another new interface each one of the previous has some features like 
add , update , delete , view in tables , search

if i want to add new tuple simply i will click on add button then it will convert me to new interface that has text fields that i will fill it 
to add to DB (it does not matter if i fill any thing in the text field in the main interface)

if i want to delete tuple i should fill the text field in the main interface (example : if i want to delete a student i should click on student the i should fill the text field in the main
the click on delete)

the feature search look like delete , the fifference is it will view it in table

hint: delete, update will not convert me to new interface , only add, update will

important || if i want to update specific tuple i should fill the text field in the main iterface then click on update button will automatically convert me to new interface , id will not change in update


if the user entered in any user_name and it is password from teacher assistant table will automatically convert him to new interface contain his features like :

1- Students
2- lectures
3- attendence
4- reports

reports has three option :
Lecture Reports
Student Report
Absence

→ in the lectures and student will view informations about the entered value and has properities like add, update, delete, search 


→ in attendence (add, update, delete, search and upload) you have text field you must fill it with any information of student and combo box to choose lecture 
if i want to make student attended in the lecture i should fill text field by one of his information and choose the lecture from the combo box then click add 
warning : student must exists in takes table that confirm he take the course of the lecture 

the same thing for delete and search i should fill text field by one of his information and choose the lecture from the combo box then click delete or search 

for update you should fill text field by one of his information and choose the lecture from the combo box then click update that will automatically convert you to new interface where student id is fixed

if i want to upload attendence from specific excel file i should click on upload button it will automatically convert me to new interface has combo box for choose lectures 
and a button to choose file from local computer (using the feature of file chooser) 
you should choose the lecture and choose the file then press upload 



→ for reports 
**if you choose Student Report it will convert you to new interface containing text field and two buttons and table for view 
if you want to search you should enter any information about specific student then press search it will view his information and his attendence percent due to course id
if you want to update you should enter any information about specific student then press update it will convert you to new interface 

**if you choose Lecture Report it will convert you to new interface containing text field and one button and table for view 
it is the same as like Student report 

**if you choose Absence it will convert you to new interface containing text field and two buttons and table for view (this is for students who attended less than 25% in lectures for course)
if you want to search you should enter any information about specific student then press search it will view his information and his attendence percent due to course 
if you want to export to new excel file you must press export and it will require to choose the location for the file the will require from you to naming the file 


else it will appear an error.




Finally,

we are very proud about ourself due to complete the project with a lot of challenges and a lot of funny moment and a lot of memories

we hope to get your wonder 


AlHamdullah 


submitted to :

Dr. Ahmed Mahdi
Eng. Usama Al_zayyan














 