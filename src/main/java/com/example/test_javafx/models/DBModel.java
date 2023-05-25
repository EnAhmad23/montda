package com.example.test_javafx.models;

import org.postgresql.ds.PGSimpleDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBModel {
    private static DBModel dbmodel = null;
    Connection con = null;

    //here our queries method
    private DBModel() {
        connect();
    }

    public static DBModel getModel() {
        if (dbmodel == null) {
            dbmodel = new DBModel();
        }
        return dbmodel;
    }

    public void connect() {
        PGSimpleDataSource source = new PGSimpleDataSource();
        source.setServerName("localhost");
        source.setDatabaseName("project");
        source.setUser("postgres");
        source.setPassword("2002");
        source.setCurrentSchema("uni");

        try {
            con = source.getConnection();
            System.out.println("Connected to database");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

//    public void schemaConnect(String schema) {
//        String sql = "set search_path to '" + schema + "'";
//        Statement s1 = null;
//        try {
//            connect();
//            s1 = con.createStatement();
//            s1.execute(sql);
//            System.out.println("Connected to schema " + schema);
//        } catch (SQLException ex) {
//        } finally {
//            try {
//                s1.close();
//            } catch (SQLException ex) {
//            }
//
//        }
//
//    }

    private void closeEverything() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void exit() {
        closeEverything();
        System.out.println("Exiting... \nBye!");
        System.exit(0);
    }

    public ArrayList<String> getCourseIDs() {
        String sql = "select course_id from course;";
        ArrayList<String> ids = new ArrayList<>();
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)
        ) {
            while (rs.next()) {
                ids.add(rs.getString(1));
                //  System.out.println(rs.getString(1));
            }
            return ids;
        } catch (SQLException ex) {

            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

//    public ArrayList<String> getBuildings() {
//        String sql = "select building from classroom;";
//        ArrayList<String> buildings = new ArrayList<>();
//        try (Statement st = con.createStatement();
//             ResultSet rs = st.executeQuery(sql)
//        ) {
//            while (rs.next()) {
//                buildings.add(rs.getString(1));
//            }
//            return buildings;
//        } catch (SQLException ex) {
//
//            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//
//    }

//    public ArrayList<String> getABuildingRooms(String building) {
//        String sql = "select room_number from classroom where building = ? ;";
//        ArrayList<String> rooms = new ArrayList<>();
//        try (PreparedStatement st = con.prepareStatement(sql)) {
//            st.setString(1, building);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                rooms.add(rs.getString(1));
//            }
//            return rooms;
//        } catch (SQLException ex) {
//
//            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//
//    }

    public String getcourseName(String id) {
        String sql = "select title from course where course_id = ? ;";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString(1));
                return rs.getString(1);
            }
            return null;
        } catch (SQLException ex) {

            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public boolean isClassCross(String b, String r, String s, int y, String t) {
        String sql = "select count(sec_id) from section " +
                "where building=? and room_number =? and semester=? and "
                + "year=? and time_slot_id= ? "
                + " group by building, room_number,semester,year,time_slot_id;";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, b);
            st.setString(2, r);
            st.setString(3, s);
            st.setInt(4, y);
            st.setString(5, t);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getInt(1));
                return rs.getInt(1) > 0;
            }
            return false;

        } catch (SQLException ex) {

            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public int maxSecID(String c, String s, int y) {
        String sql = "select max(sec_id) from section " +
                "where course_id=? and semester=? and "
                + "year=? ;";
        int max;
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, c);
            st.setString(2, s);
            st.setInt(3, y);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                max = rs.getInt(1);
                if (max > 0) return max;
                else return 0;
            } else return 0;
        } catch (SQLException ex) {

            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }


    public String insertSection(String c, String b, String r, String s, int y, String t) {
        String sql = "insert into section (course_id,sec_id,building,room_number,semester,"
                + "year,time_slot_id) values (?,?,?,?,?,?,?);";

        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, c);
            st.setString(2, String.valueOf(maxSecID(c, s, y) + 1));
            st.setString(3, b);
            st.setString(4, r);
            st.setString(5, s);
            st.setInt(6, y);
            st.setString(7, t);


            if (st.executeUpdate() > 0) {
                System.out.println("\tsection added successfully\n\tsec_id = " + maxSecID(c, s, y) + 1);
                return " section added successfully\n sec_id = " + maxSecID(c, s, y) + 1;

            } else return "";

        } catch (SQLException ex) {

            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
    public ArrayList<String> availableCourse() {
        String sql = "SELECT C.course_id FROM course AS C LEFT JOIN teacher_assistant AS TA ON C.course_id = TA.teache WHERE TA.teache IS NULL;";
        ArrayList<String> ids = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(sql)) {
//            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ids.add(rs.getString(1));
            }
            return ids;
        } catch (SQLException ex) {
            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String getStdName(String id) {
        String sql = "select name from students where id = ? ;";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString(1));
                return rs.getString(1);
            }
            return null;
        } catch (SQLException ex) {

            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
    public int delete_Student(String id) {
        String sql = "DELETE FROM students WHERE id = ? ;";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, id);
            return st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    public int delete_lecture(String id) {
        String sql = "DELETE FROM lecture WHERE id = ? ;";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, id);
            return st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    } public int delete_teacher_assistant(String id) {
        String sql = "DELETE FROM teacher_assistant WHERE id = ? ;";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, id);
            return st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    public int delete_Course(String id) {
        String sql = "DELETE FROM course WHERE id = ? ;";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, id);
            return st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public ArrayList<Student> getStd() {
        String sql = "select id,name,gender,majer,place,ph_num from students join phone_num on id =s_id;";
        try (PreparedStatement st = con.prepareStatement(sql)) {
//            st.setString(1, id);
            ArrayList<Student> ids = new ArrayList<>();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ids.add(new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
            return ids;
        } catch (SQLException ex) {

            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public ArrayList<Course> getCou() {
        String sql = "select * from course ;";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            ArrayList<Course> ids = new ArrayList<>();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ids.add(new Course(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            return ids;
        } catch (SQLException ex) {

            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
    public int addLecture(String id, String course_id, String l_time, String room, String title) throws SQLException {
        String SQL = "INSERT INTO lecture(id,course_id,l_time,room,title) VALUES(?,?,?,?,?);";
        try (PreparedStatement pstmt = con.prepareStatement(SQL)) {
            Timestamp ts = Timestamp.valueOf(l_time);
            pstmt.setString(1, id);
            pstmt.setString(2, course_id);
            pstmt.setTimestamp(3, ts);
            pstmt.setString(4, room);
            pstmt.setString(5, title);
            System.out.println("1");
             return pstmt.executeUpdate();
            //return "";
        } catch (SQLException e) {
            throw e;
        }
    }
    public int addCourse(String course_id, String book_name, String teacher_name, String room, String subject) {
        String SQL = "INSERT INTO course(course_id,book_name,teacher_name,room,subject) "
                + "VALUES(?,?,?,?,?)";
        try (PreparedStatement pstmt = con.prepareStatement(SQL)) {
            pstmt.setString(1, course_id);
            pstmt.setString(2, book_name);
            pstmt.setString(3, teacher_name);
            pstmt.setString(4, room);
            pstmt.setString(5, subject);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }
    public int addStudent(String id, String name, String gender,  String major, String place)  {
        String SQL = "INSERT INTO students(id,name,gender,majer,place) VALUES(?,?,?,?,?)";
//        ArrayList<student> arr;
        try (PreparedStatement pstmt = con.prepareStatement(SQL)) {
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, gender);
            pstmt.setString(4, major);
            pstmt.setString(5, place);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            return 0;

        }
    }

    public int addTeacher(String id, String name, String teache, String password) {
        String sql = "insert into teacher_assistant(id,name,teache,password) values (?,?,?,crypt(?, gen_salt('bf')));";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, id);
            st.setString(2, name);
            st.setString(3, teache);
            st.setString(4, password);
            return st.executeUpdate();
        } catch (SQLException ex) {
            return 0;
        }

    }

    public ArrayList<LectureTime> getLectures() {
        String sql = "select * from lecture ;";
        try (PreparedStatement st = con.prepareStatement(sql)) {
//            st.setString(1, id);
            ArrayList<LectureTime> ids = new ArrayList<>();
            ResultSet rs = st.executeQuery();
            while (rs.next())
                ids.add(new LectureTime(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            return ids;
        } catch (SQLException ex) {

            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public ArrayList<TeacherAssistant> getTeacherAssistant() {
        String sql = "SELECT ID, name, teache FROM teacher_assistant ;";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            ArrayList<TeacherAssistant> teacherAssistants = new ArrayList<>();
            ResultSet rs = st.executeQuery();
//            ResultSet advisor = ad.executeQuery();
            while (rs.next() )
                teacherAssistants.add(new TeacherAssistant(rs.getString(1), rs.getString(2), rs.getString(3)));
            return teacherAssistants;
        } catch (SQLException ex) {
            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }


    public boolean login(String id, String password) {
        String sql = "select id from teacher_ass where password=crypt(?, password) and id =?;";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, password);
            st.setString(2, id);
            ResultSet rs = st.executeQuery();
            return (rs.next());
        } catch (SQLException ex) {

            return false;

        }

    }

    public String getStdDept(String id) {
        String sql = "select dept_name from student where id = ? ;";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString(1));
                return rs.getString(1);
            }
            return null;
        } catch (SQLException ex) {

            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public String getInstructorName(String id) {
        String sql = "select name from instructor where id = ? ;";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString(1));
                return rs.getString(1);
            }
            return null;
        } catch (SQLException ex) {

            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public String getInstructorDept(String id) {
        String sql = "select dept_name from instructor where id = ? ;";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString(1));
                return rs.getString(1);
            }
            return null;
        } catch (SQLException ex) {

            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public ArrayList<String> getStdYears(String id) {
        ArrayList<String> years = new ArrayList<>();
        String sql = "select distinct year from takes where id = ? ;";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                years.add(rs.getInt(1) + "");
            }
            return years;
        } catch (SQLException ex) {

            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<String> getStdSems(String id, int year) {
        ArrayList<String> sems = new ArrayList<>();
        String sql = "select distinct semester from takes where id = ? and year = ?;";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, id);
            st.setInt(2, year);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                sems.add(rs.getString(1) + "");
            }
            return sems;
        } catch (SQLException ex) {

            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<String> getInstructorYears(String id) {
        ArrayList<String> years = new ArrayList<>();
        String sql = "select distinct year from teaches where id = ? ;";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                years.add(rs.getInt(1) + "");
            }
            return years;
        } catch (SQLException ex) {

            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<String> getInstructorSems(String id, int year) {
        ArrayList<String> sems = new ArrayList<>();
        String sql = "select distinct semester from teaches where id = ? and year = ?;";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, id);
            st.setInt(2, year);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                sems.add(rs.getString(1) + "");
            }
            return sems;
        } catch (SQLException ex) {

            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String switchDay(String day) {
        switch (day) {
            case "M":
                return "Monday";
            case "F":
                return "Friday";
            case "T":
                return "Tuesday";
            case "R":
                return "Thursday";
            case "W":
                return "Wednesday";
        }
        return "";
    }

    public ArrayList<LectureTime> getStdLectures(String id, String sem, int year) {
        ArrayList<LectureTime> lects = new ArrayList<>();
        String time = "";
        String sql = "select course_id, title, building, room_number, start_hr"
                + ", start_min, end_hr,end_min ,string_agg(day ,' , ' ) as days"
                + " from takes natural join section natural join course natural join time_slot"
                + " where id = ? and year = ? and semester = ?"
                + " group by 1,2,3,4,5,6,7,8 ;";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, id);
            st.setInt(2, year);
            st.setString(3, sem);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                time = "";
                System.out.println(rs.getArray(9).toString());
                String arr = rs.getArray(9).toString();
                String[] days = arr.split(" , ");
                //  rs.getArray(9).toString();
                /*Array arr =  rs.getArray(9);
                String [] days = (String [])(arr.getArray());*/
                for (String day : days) {
                    time += switchDay(day) + " ";
                    System.out.println(day);
                }
                time += "\n";
                time += "From " + rs.getInt(5) + " : " + rs.getInt(6) + " To " + rs.getInt(7) + " : " + rs.getInt(8);
                lects.add(new LectureTime(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), time));
            }
            return lects;
        } catch (SQLException ex) {

            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<LectureTime> getInstructorLectures(String id, String sem, int year) {
        ArrayList<LectureTime> lects = new ArrayList<>();
        String time = "";
        String sql = "select course_id, title, building, room_number, start_hr"
                + ", start_min, end_hr,end_min ,string_agg(day ,' , ' ) as days"
                + " from teaches natural join section natural join course natural join time_slot"
                + " where id = ? and year = ? and semester = ?"
                + " group by 1,2,3,4,5,6,7,8 ;";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, id);
            st.setInt(2, year);
            st.setString(3, sem);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                time = "";
                System.out.println(rs.getArray(9).toString());
                String arr = rs.getArray(9).toString();
                String[] days = arr.split(" , ");
                for (String day : days) {
                    time += switchDay(day) + " ";
                    System.out.println(day);
                }
                time += "\n";
                time += "From " + rs.getInt(5) + " : " + rs.getInt(6) + " To " + rs.getInt(7) + " : " + rs.getInt(8);
                lects.add(new LectureTime(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), time));
            }
            return lects;
        } catch (SQLException ex) {

            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<Student> searchStudent(String id) {
        String sql = "select id,name,gender,majer,place,ph_num from students join phone_num on id =s_id where id = ? ;";
        ArrayList<Student> student = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                student.add(new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
            return student;
        } catch (SQLException ex) {

            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<Course> searchCourse(String id) {
        String sql = "select * from course where course_id = ? ;";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, id);
            ArrayList<Course> ids = new ArrayList<>();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ids.add(new Course(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));

            }
            return ids;
        } catch (SQLException ex) {

            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public ArrayList<LectureTime> searchLecture(String id) {
        String sql = "select * from lecture where id = ? ;";
        ArrayList<LectureTime> Lecture = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Lecture.add(new LectureTime(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            return Lecture;
        } catch (SQLException ex) {
            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

//    public ArrayList<TeacherAssistant> searchTA(String id) {
//        String sql = "select * from teacher_assistant where id = ? ;";
//        ArrayList<TeacherAssistant> TA = new ArrayList<>();
//        try (PreparedStatement st = con.prepareStatement(sql)) {
//            st.setString(1, id);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                TA.add(new TeacherAssistant(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
//            }
//            return TA;
//        } catch (SQLException ex) {
//            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//    }


    public int UpdateStudent(String id,String name, String gender,  String major, String place)  {
        String SQL = "UPDATE students SET name = ?, gender = ?, place= ?, major = ? WHERE id = ?";
//        ArrayList<student> arr;
        try (PreparedStatement pstmt = con.prepareStatement(SQL)) {
            pstmt.setString(1, name);
            pstmt.setString(2, gender);
            pstmt.setString(3, place);
            pstmt.setString(4, major);
            pstmt.setString(5, id);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }

    public int UpdateCourse(String id,String book_name, String teach_name, String room,String subject){
        String SQL = "UPDATE course SET book_name = ?, teacher_name = ?, room = ?, subject = ? WHERE course_id = ?";
//        ArrayList<student> arr;
        try (PreparedStatement pstmt = con.prepareStatement(SQL)) {
            pstmt.setString(1, book_name);
            pstmt.setString(2, teach_name );
            pstmt.setString(3, room);
            pstmt.setString(4, subject);
            pstmt.setString(5, id);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }
    public int UpdateLecture(String id,String course_id, String l_time,  String room, String title)  {
        String SQL = "UPDATE lecture SET course_id = ?, l_time = ?, room = ?, title = ? WHERE id = ?";
//        ArrayList<student> arr;
        try (PreparedStatement pstmt = con.prepareStatement(SQL)) {
            pstmt.setString(1, course_id);
            pstmt.setString(2, l_time);
            pstmt.setString(3, room);
            pstmt.setString(4, title);
            pstmt.setString(5, id);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }
    public int UpdateTeacher_Assist(String id,String name, String teach ,String password)  {
        String SQL = "UPDATE teacher_assist SET name = ?, teache = ?, `password` = ? WHERE id = ?";
//        ArrayList<student> arr;
        try (PreparedStatement pstmt = con.prepareStatement(SQL)) {
            pstmt.setString(1, name);
            pstmt.setString(2, teach );
            pstmt.setString(3, password);
            pstmt.setString(4, id);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }
}
