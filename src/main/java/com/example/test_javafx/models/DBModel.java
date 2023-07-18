package com.example.test_javafx.models;

import org.postgresql.ds.PGSimpleDataSource;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        source.setDatabaseName("montda");
        source.setUser("postgres");
        source.setPassword("bohboq20");
        source.setCurrentSchema("public");

        try {
            con = source.getConnection();
            System.out.println("Connected to database");
            try {
                Statement statement = con.createStatement();

                // Check if the schema exists
                String checkSchemaQuery = "SELECT EXISTS (SELECT 1 FROM pg_namespace WHERE nspname = 'public');";
                ResultSet resultSet = statement.executeQuery(checkSchemaQuery);

                resultSet.next();
                boolean schemaExists = resultSet.getBoolean(1);

                if (schemaExists) {
                    System.out.println("Schema exists.");

                } else {
                    System.out.println("Schema does not exist.");


                    System.out.println("Schema created successfully.");
                    restoreDatabase("backups/");
                    source.setCurrentSchema("public");

//                    restoreDatabase("backups/");
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException ex) {
            if (ex.getSQLState().equals("3D000")) {
                String url = "jdbc:postgresql://localhost:5432/postgres";
                String username = "postgres";
                String password = "bohboq20";
                String databaseName = "montda";

                try (Connection connection = DriverManager.getConnection(url, username, password)) {
                    con =connection;
                    Statement statement = con.createStatement();

                    // Create the database
                    String createDatabaseQuery = "CREATE DATABASE " + databaseName;
                    statement.executeUpdate(createDatabaseQuery);
                    connect();
                    restoreDatabase("backups/");
                    System.out.println("Database created successfully.");
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


            }
            System.err.println(ex.getMessage());
        }

    }


    public int backupDatabase(String path) throws IOException, InterruptedException {
        try {
            if (!con.isValid(1)){
                return -2;

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String[] envp = {
                "PGHOST=localhost",
                "PGDATABASE=montda",
                "PGUSER=postgres",
                "PGPASSWORD=bohboq20",
                "PGPORT=5432",
                "path=C:\\Program Files\\PostgreSQL\\15\\bin" //PostgreSQL path
        };
        String[] cmdArray = {
                "cmd",
                "/c",
                String.format("pg_dump -f \"%s\"", path)
        };
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(cmdArray, envp);
        process.waitFor();
        return process.exitValue();
    }


    public static void restoreDatabase(String path) throws IOException, InterruptedException {
        String[] envp = {
                "PGHOST=localhost",
                "PGDATABASE=montda",
                "PGUSER=postgres",
                "PGPASSWORD=bohboq20",
                "PGPORT=5432",
                "path=C:\\Program Files\\PostgreSQL\\15\\bin"
        };
        String[] cmdArray = {
                "cmd",
                "/c",
                String.format("psql -f \"%s\"", path)
        };
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(cmdArray, envp);
        process.waitFor();
        System.out.println("restore done");
        // Check the exit value of the process
        int exitValue = process.exitValue();
        System.out.println(exitValue);
        if (exitValue != 0) {
            throw new IOException("Failed to restore database. Exit value: " + exitValue);
        }
    }


    private void closeEverything() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     public void exit() {
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
    public String getCourseRoom(String id) {
        String sql = "select room from course where course_id = ?;";

        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1,id);
            ResultSet rs =st.executeQuery();
            if(rs.next()) {
                return (rs.getString(1));
                //  System.out.println(rs.getString(1));
            }

        } catch (SQLException ex) {

            System.err.println(ex.getMessage());
            return null;
        }
return null;
    }
//    public String getTeachCourseID(String id) {
//        String sql = "select teache from teacher_assistant where id =?;";
//
//        try (PreparedStatement st = con.prepareStatement(sql) ) {
//            st.setString(1,id);
//            if (rs.next()) {
//               return (rs.getString(1));
//                //  System.out.println(rs.getString(1));
//            }
//
//        } catch (SQLException ex) {
//
//            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//
//        return null;
//    }

    public String getTeachCourseID(String id) {
        String sql = "select course_id from teacher natural join teach where id =?;";

        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
                //  System.out.println(rs.getString(1));
            }

        } catch (SQLException ex) {

            System.err.println(ex.getMessage());
            return null;
        }
        return null;
    }

    public ArrayList<Student> getTeachStu(String id) {
        String sql = "SELECT s.id, s.name,  s.majer, s.place, string_agg(pn.ph_num, '\n') " +
                "FROM students s " +
                "JOIN takes t ON s.id = t.ID " +
                "JOIN phone_num pn ON s.id = pn.s_id " +
                "WHERE t.course_id = ? " +
                "GROUP BY s.id, s.name, s.gender, s.place, s.majer";
        ArrayList<Student> students = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, id);
            ResultSet rs = st.executeQuery(); // Remove the "sql" argument here
            while (rs.next()) {
                students.add(new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
            }
            return students;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("2");
            return null;
        }
    }


    public ArrayList<String> getStuCourseIDs(String id) {
        String sql = "select course_id from takes where id = ?;";
        ArrayList<String> ids = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(sql)

        ) {
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ids.add(rs.getString(1));
                //  System.out.println(rs.getString(1));
            }
            return ids;
        } catch (SQLException ex) {

            System.err.println(ex.getMessage());
            return null;
        }

    }

    public int addAttendance(String stu_id, String lec_id) {
        String sql = "insert into attendence values(?,?); ";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, stu_id);
            st.setString(2, lec_id);
            return st.executeUpdate();
        } catch (SQLException ex) {
            return 0;
        }
    }

    public int deleteAttendence(String stu_id, String lec_id) {
        String sql = "delete from attendence where stu_id =? and lec_id = ?; ";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, stu_id);
            st.setString(2, lec_id);
            return st.executeUpdate();
        } catch (SQLException ex) {
            return 0;
        }
    }

    public int updateAttendence(String stu_id, String name, String lec_id, String cou_id, String title) {
        String sql = "UPDATE students, lecture\n" +
                "SET  students.name = ?, lecture.course_id = ?, lecture.title = ?\n" +
                "WHERE students.id = (SELECT stu_id FROM attendence WHERE stu_id = ? AND lec_id = ?)\n" +
                "    AND lecture.id = ?;\n ";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, name);
            st.setString(2, cou_id);
            st.setString(3, title);
            st.setString(4, stu_id);
            st.setString(5, lec_id);
            st.setString(6, lec_id);
            return st.executeUpdate();
        } catch (SQLException ex) {
            return 0;
        }
    }

    public ArrayList<Attendences> searchAttendence(String stu_id, String lec_id) {
        String sql = "SELECT s.id, s.name, l.id, l.course_id, l.title \n" +
                "FROM students s \n" +
                "JOIN attendence a ON s.id = a.stu_id \n" +
                "JOIN lecture l ON a.lec_id = l.id \n" +
                "WHERE s.id = ? AND l.id = ?;";

        try (PreparedStatement st = con.prepareStatement(sql)) {
            ArrayList<Attendences> attendences = new ArrayList<>();
            st.setString(1, stu_id);
            st.setString(2, lec_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                attendences.add(new Attendences(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            return attendences;
        } catch (SQLException ex) {
            return null;
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

    public ArrayList<Take> getTakes() {
        String sql = "select s.id, s.name , string_agg(t.course_id, '\n') as course_id from students as s natural join takes  as t group by s.id, s.name ;";
        try (PreparedStatement st = con.prepareStatement(sql)) {
//            st.setString(1, id);
            ArrayList<Take> takes = new ArrayList<>();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
//                System.out.println(rs.getString(1));
                takes.add(new Take(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
            return takes;
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
            return null;
        }

    }

    public ArrayList<Take> searchTakes(String id) {
        String sql = "SELECT students.id, students.name,  string_agg(takes.course_id, '\n')\n" +
                "FROM students\n" +
                "JOIN takes ON students.id = takes.ID\n" +
                "WHERE students.id = ? \n" +
                "GROUP BY students.id, students.name;";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, id);
            ArrayList<Take> takes = new ArrayList<>();
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
//                System.out.println(rs.getString(1));
                takes.add(new Take(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
            return takes;
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
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
    }

    public int delete_take(String id) {
        String sql = "DELETE FROM takes WHERE id = ? ;";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, id);
            return st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public int delete_teacher(String id) {
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
        String sql = "DELETE FROM course WHERE course_id = ? ;";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, id);
            return st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

//    public void readEXL(String path) {
//        try {
//            Workbook workbook = Workbook.getWorkbook(new File(path));
//            Sheet sheet = workbook.getSheet(0);
//            Cell cell1 = sheet.getCell(0, 0);
//            System.out.print(cell1.getContents() + ":");    // Test Count + :
//            Cell cell2 = sheet.getCell(0, 1);
//            System.out.println(cell2.getContents());        // 1
//
//            Cell cell3 = sheet.getCell(1, 0);
//            System.out.print(cell3.getContents() + ":");
//        } catch (IOException | BiffException e) {
//            e.printStackTrace();
//        }
//    }

    public ArrayList<Student> getStd() {
        String sql = "SELECT * FROM students ";
        try (PreparedStatement st = con.prepareStatement(sql)) {
//            st.setString(1, id);
            ArrayList<Student> ids = new ArrayList<>();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ids.add(new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
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

    public int addLecture(String id, String course_id, String room, String title) throws SQLException {
        String SQL = "INSERT INTO lecture(id,course_id,room,title) VALUES(?,?,?,?);";
        try (PreparedStatement pstmt = con.prepareStatement(SQL)) {

            pstmt.setString(1, id);
            pstmt.setString(2, course_id);
            pstmt.setString(3, room);
            pstmt.setString(4, title);

//            System.out.println("1");
            return pstmt.executeUpdate();
            //return "";
        } catch (SQLException e) {
            throw e;
        }
    }

    public int addCourse(String course_id, String name, String room, String monadMajor, String time) {
        String SQL = "INSERT INTO course(course_id,name,room,monad_major,time) "
                + "VALUES(?,?,?,?,?)";
        try (PreparedStatement pstmt = con.prepareStatement(SQL)) {
            pstmt.setString(1, course_id);
            pstmt.setString(2, name);
            pstmt.setString(3, room);
            pstmt.setString(4, monadMajor);
            Time sqlTime = convertToSqlTime(time);
            pstmt.setTime(5, sqlTime);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
            return 0;
        }
    }
    private Time convertToSqlTime(String time) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm"); // Assuming the time format is "HH:mm"
            java.util.Date utilDate = sdf.parse(time);
            long timeInMillis = utilDate.getTime();
            return new Time(timeInMillis);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    public int addStudent(String id, String name, String level, String place, String monadMajor, String uniMajor, String path) {
        String SQL = "INSERT INTO students(id,name,stu_level,place,montda_majer,uni_majer,path) VALUES(?,?,?,?,?,?,?)";
//        ArrayList<student> arr;
        try (PreparedStatement pstmt = con.prepareStatement(SQL)) {
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, level);
            pstmt.setString(4, place);
            pstmt.setString(5, monadMajor);
            pstmt.setString(6, uniMajor);
            pstmt.setString(7, path);


            return pstmt.executeUpdate() ;
        } catch (SQLException e) {
            System.err.println(e);
            return 0;

        }
    }

    public int addTeacher(String id, String name, String teache) {
        String sql = "insert into teacher_assistant(id,name,teache) values (?,?,?);";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, id);
            st.setString(2, name);
            st.setString(3, teache);
            return st.executeUpdate();
        } catch (SQLException ex) {
            return 0;
        }

    }

    public int addTake(String id, String course_id) {
        String sql = "insert into takes values (?,?);";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, id);
            st.setString(2, course_id);
            return st.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
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
                ids.add(new LectureTime(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            return ids;
        } catch (SQLException ex) {

            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public ArrayList<ReportLectures> reportLectures() {
        String sql = "select id ,course_id,room,title from lecture ;";
//        String sql2 = "select id ,course_id,room,title from lecture ;";

        try (PreparedStatement st = con.prepareStatement(sql)) {
//            st.setString(1, id);
            ArrayList<ReportLectures> ids = new ArrayList<>();
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String id = rs.getString(1);
                double num = getNumAttendence(id);
                double present = (((num / getAllStuCourse(id)) * 100) >= 0) ? ((num / getAllStuCourse(id)) * 100) : -1;
                ids.add(new ReportLectures(id, rs.getString(2), rs.getString(3), num, present));

            }
            return ids;
        } catch (SQLException ex) {

            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public ArrayList<ReportLectures> reportLectures(String lec_id) {
        String sql = "select id ,course_id,room,title from lecture  where id =?;";
//        String sql2 = "select id ,course_id,room,title from lecture ;";

        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, lec_id);
            ArrayList<ReportLectures> ids = new ArrayList<>();
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String id = rs.getString(1);
                double num = getNumAttendence(id);
                double present = (((num / getAllStuCourse(id)) * 100) >= 0) ? ((num / getAllStuCourse(id)) * 100) : -1;
                ids.add(new ReportLectures(id, rs.getString(2), rs.getString(3), num, present));

            }
            return ids;
        } catch (SQLException ex) {

            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public Double getCourseLectures(String course_id) {
        String SQL = "SELECT COUNT(*) FROM lecture WHERE course_id = ?";
        double numLectures = 0.0;
        try (PreparedStatement pstmt = con.prepareStatement(SQL)) {
            pstmt.setString(1, course_id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    numLectures = rs.getDouble(1);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return numLectures;
    }

    public Double getStu_Atten_Course(String course_id, String stu_id) {
        String SQL = "SELECT COUNT(*) FROM attendence JOIN lecture ON attendence.lec_id = lecture.id " +
                "WHERE lecture.course_id = ? AND attendence.stu_id = ?";
        double numLectures = 0.0;
        try (PreparedStatement pstmt = con.prepareStatement(SQL)) {
            pstmt.setString(1, course_id);
            pstmt.setString(2, stu_id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    numLectures = rs.getDouble(1);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return numLectures;
    }

    public ArrayList<StudentReport> reportStudents(String id) {
        String query = "SELECT  students.name, takes.course_id FROM students JOIN takes ON students.id = takes.ID where  students.id =?;";

        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, id);
            ArrayList<StudentReport> ids = new ArrayList<>();
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                // id = rs.getString(2);
                double precent = (getStu_Atten_Course(rs.getString(2), id) / getCourseLectures(rs.getString(2))) * 100;
                ids.add(new StudentReport(id, rs.getString(1), rs.getString(2), precent));
            }
            return ids;
        } catch (SQLException ex) {
            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Double getNumAttendence(String id) throws SQLException {
        String SQL = "SELECT COUNT(DISTINCT a.stu_id) AS student_count FROM attendence a JOIN takes t ON a.stu_id = t.ID AND a.lec_id = ?;";
        double att = 0.0;
        try (PreparedStatement pstmt = con.prepareStatement(SQL)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    att = rs.getDouble(1);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return att;
    }

    public Double getAllStuCourse(String id) {
        String SQL = "SELECT COUNT(*)  FROM takes  WHERE course_id IN (SELECT course_id FROM lecture WHERE id = ? );";
        double stuCourse = 0.0;
        try (PreparedStatement pstmt = con.prepareStatement(SQL)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    stuCourse = rs.getDouble(1);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return stuCourse;
    }





    public ArrayList<LectureTime> getLecFromCou(String course_id) {
        ArrayList<LectureTime> LecId = new ArrayList<>();
        String sql = "select * from lecture where course_id = ?;";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, course_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                LecId.add(new LectureTime(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
            return LecId;
        } catch (SQLException ex) {
            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<String> LecIds(String id, String course_id) {
        ArrayList<String> Ids = new ArrayList<>();
        String sql = "select lec_id from attendence join lecture on lec_id = lecture.id  where stu_id = ? and course_id = ?;";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, id);
            st.setString(2, course_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Ids.add(rs.getString(1));
            }
            return Ids;
        } catch (SQLException ex) {
            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }


    public ArrayList<Attendences> getAttendence(String lec_id) {
        ArrayList<Attendences> LecId = new ArrayList<>();
        String sql = "SELECT s.id, s.name, a.lec_id, l.course_id, l.title\n" +
                "FROM students s\n" +
                "JOIN attendence a ON s.id = a.stu_id\n" +
                "JOIN lecture l ON a.lec_id = l.id\n" +
                "WHERE a.lec_id = ?;";


        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, lec_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                LecId.add(new Attendences(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            return LecId;
        } catch (SQLException ex) {
            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<TeacherAssistant> getTeacherAssistant() {
        String sql = "SELECT ID, name, course_id FROM teacher natural join teach ;";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            ArrayList<TeacherAssistant> teacherAssistants = new ArrayList<>();
            ResultSet rs = st.executeQuery();
//            ResultSet advisor = ad.executeQuery();
            while (rs.next())
                teacherAssistants.add(new TeacherAssistant(rs.getString(1), rs.getString(2), rs.getString(3)));
            return teacherAssistants;
        } catch (SQLException ex) {
            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }


    public boolean login(String id, String password) {
        String sql = "select id from teacher_assistant where password=crypt(?, password) and id =?;";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, password);
            st.setString(2, id);
            ResultSet rs = st.executeQuery();
            if (rs.next())
                return rs.getString(1).equals(id);

        } catch (SQLException ex) {

            return false;

        }
        return false;
    }

    public ArrayList<String> getLecIds() {
        String sql = "select id from lecture ;";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            ArrayList<String> ids = new ArrayList<>();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
//                System.out.println(rs.getString(1));
                ids.add(rs.getString(1));
            }
            return ids;
        } catch (SQLException ex) {

            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public ArrayList<String> getTeacherLecIds(String id) {
        String sql = "SELECT lecture.id\n" +
                "FROM lecture\n" +
                "JOIN course ON lecture.course_id = course.course_id\n" +
                "JOIN teacher ON teacher.teache = course.course_id\n" +
                "WHERE teacher.ID = ?;\n";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            ArrayList<String> ids = new ArrayList<>();
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
//                System.out.println(rs.getString(1));
                ids.add(rs.getString(1));
            }
            return ids;
        } catch (SQLException ex) {

            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public ArrayList<String> getStuLecIds(String id) {
        String sql = "select lec_id from attendence  where stu_id =?;";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            ArrayList<String> ids = new ArrayList<>();
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
//                System.out.println(rs.getString(1));
                ids.add(rs.getString(1));
            }
            return ids;
        } catch (SQLException ex) {

            System.err.println(ex.getMessage());
            return null;
        }

    }

    public ArrayList<Absents> getAbsents() {
        String sql = "SELECT s.id AS student_id, s.name AS student_name, pn.phone_numbers AS phone_numbers, t.course_id AS course_id,\n" +
                "       (COUNT(a.stu_id) * 1.0 / COUNT(l.id)) * 100 AS attendance_percentage\n" +
                "FROM students s\n" +
                "JOIN takes t ON s.id = t.ID\n" +
                "JOIN (\n" +
                "    SELECT s_id, string_agg(ph_num, '\n') AS phone_numbers\n" +
                "    FROM phone_num\n" +
                "    GROUP BY s_id\n" +
                ") pn ON s.id = pn.s_id\n" +
                "JOIN lecture l ON t.course_id = l.course_id\n" +
                "LEFT JOIN attendence a ON l.id = a.lec_id AND t.ID = a.stu_id\n" +
                "GROUP BY s.id, s.name, pn.phone_numbers, t.course_id\n" +
                "HAVING (COUNT(a.stu_id) * 1.0 / COUNT(l.id)) * 100 < 25;";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            ArrayList<Absents> absents = new ArrayList<>();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                absents.add(new Absents(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            return absents;
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
            return null;
        }

    }

    public ArrayList<Absents> searchAbsents(String id) {
        String sql = "SELECT s.id AS student_id, s.name AS student_name, pn.ph_num AS phone_number, t.course_id AS course_id,\n" +
                "       (COUNT(a.stu_id) * 1.0 / COUNT(l.id)) * 100 AS attendance_percentage\n" +
                "FROM students s\n" +
                "JOIN takes t ON s.id = t.ID\n" +
                "JOIN phone_num pn ON s.id = pn.s_id\n" +
                "JOIN lecture l ON t.course_id = l.course_id\n" +
                "LEFT JOIN attendence a ON l.id = a.lec_id AND t.ID = a.stu_id\n" +
                "WHERE s.id = ? \n" +
                "GROUP BY s.id, s.name, pn.ph_num, t.course_id\n" +
                "HAVING (COUNT(a.stu_id) * 1.0 / COUNT(l.id)) * 100 < 25;";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            ArrayList<Absents> absents = new ArrayList<>();
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                absents.add(new Absents(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            return absents;
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
            return null;
        }

    }


    public ArrayList<String> getStdIds() {
        ArrayList<String> sems = new ArrayList<>();
        String sql = "select  id from students ;";
        try (PreparedStatement st = con.prepareStatement(sql)) {

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

//    public int getNumAtt(String lec_id) {
//        ArrayList<String> sems = new ArrayList<>();
//        String sql = "SELECT COUNT(DISTINCT stu_id) AS attendance_count FROM attendence WHERE lec_id = ?;";
//        try (PreparedStatement st = con.prepareStatement(sql)) {
//            st.setString(1, lec_id);
//            ResultSet rs = st.executeQuery();
//            return rs.getInt(1);
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//            return 0;
//        }
//    }


    public ArrayList<Student> searchStudent(String id) {
        String sql = "select * from students where id = ? ;";
        ArrayList<Student> student = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                student.add(new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
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
                Lecture.add(new LectureTime(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
            return Lecture;
        } catch (SQLException ex) {
            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<TeacherAssistant> searchTeacher(String id) {
        String sql = "select * from teacher where id = ? ;";
        ArrayList<TeacherAssistant> TA = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                TA.add(new TeacherAssistant(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
            return TA;
        } catch (SQLException ex) {
            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }


    public int UpdateStudent(String id, String name, String gender, String major, String place, String phone_num) {
        String SQL = "UPDATE students SET name = ?, gender = ?, place = ?, majer = ? WHERE id = ?";
        String SQL2 = "UPDATE phone_num SET ph_num = ? WHERE s_id = ?";
//        ArrayList<student> arr;
        try (PreparedStatement pstmt = con.prepareStatement(SQL); PreparedStatement pstmt2 = con.prepareStatement(SQL2)) {
            pstmt.setString(1, name);
            pstmt.setString(2, gender);
            pstmt.setString(3, place);
            pstmt.setString(4, major);
            pstmt.setString(5, id);
            pstmt2.setString(1, phone_num);
            pstmt2.setString(2, id);
            return pstmt.executeUpdate() + pstmt2.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }


    public int updateCourse(String course_id, String name, String teacher_name, String room_number, String monadMajor, String time) {
        String SQL = "UPDATE course SET name = ?, teacher_name = ?, room = ?, monadMajor = ? ,WHERE course_id = ?";
//        ArrayList<student> arr;
        Timestamp timestamp=Timestamp.valueOf(time);
        try (PreparedStatement pstmt = con.prepareStatement(SQL)) {
            pstmt.setString(1, name);
            pstmt.setString(2, teacher_name);
            pstmt.setString(3, room_number);
            pstmt.setString(4, monadMajor);
            pstmt.setTimestamp(5, timestamp);
            pstmt.setString(6, course_id);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }

    public int UpdateLecture(String id, String course_id, String room, String title) {
        String SQL = "UPDATE lecture SET course_id = ?, room = ?, title = ? WHERE id = ?;";
//        ArrayList<student> arr;
        try (PreparedStatement pstmt = con.prepareStatement(SQL)) {
            pstmt.setString(1, course_id);
            pstmt.setString(2, room);
            pstmt.setString(3, title);
            pstmt.setString(4, id);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }

    public int UpdateTeacher_Assist(String id, String name, String teach, String password) {
        String SQL = "UPDATE teacher_assistant SET name = ?, teache = ?, password = crypt(?, gen_salt('bf')) WHERE id = ?;";
//        ArrayList<student> arr;
        try (PreparedStatement pstmt = con.prepareStatement(SQL)) {
            pstmt.setString(1, name);
            pstmt.setString(2, teach);
            pstmt.setString(3, password);
            pstmt.setString(4, id);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }

}
