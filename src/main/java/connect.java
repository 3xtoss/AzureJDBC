import java.sql.*;
import java.io.*;
import java.util.*;
public class connect {
	
	final static double mostProblems = 0.20;
	final static double secondMostProblems = 0.15;
	final static double thirdMostProblems = 0.10;
	final static double baseRaise = 0.05;
	final static Connection connection = getConnection();
	
    public static void main(String[] args) throws NumberFormatException, IOException {
        run();
  }
    
    public static void run() throws NumberFormatException, IOException {
    	//LETS USER PICK AN OPTION
    	BufferedReader user_input = new BufferedReader(new InputStreamReader(System.in));
    	Integer user_choice;
    	
    	
    	//For problem manipulation
    	int pid;
    	String pname;
    	int max_score;
    	int author_id;
    	
    	System.out.println("SAMPLE JDBC APPLICATION --- OPTIONS");
    	System.out.println("");
    	menu();
    	user_choice = Integer.parseInt(user_input.readLine());
    	while(true) {
    		switch(user_choice) {
    			case 1:
    				System.out.println("Enter pid: ");
    				pid = Integer.parseInt(user_input.readLine());
    				
    				System.out.println("Enter pname: ");
    				pname = user_input.readLine();
    				
    				
    				System.out.println("Enter max-score: ");
    				max_score = Integer.parseInt(user_input.readLine());
    				
    				System.out.println("Enter aid: ");
    				author_id = Integer.parseInt(user_input.readLine());
    				
    				try {
    					writeToProblemTable(connection, pid, pname, max_score, author_id);
    				} catch (SQLException e) {
    					System.out.println(e.getMessage());
    				}
    				
    				
    				break;
    			case 2:
    				ArrayList<Integer> sortedList = orderAuthorsByTotalProblems(connection);
    				System.out.println("Enter aid: ");
    				author_id = Integer.parseInt(user_input.readLine());
    				int i = sortedList.indexOf(author_id);
    				
    				//author has the most problems
    				if (i == 0) {
    					changeCompensation(connection, author_id, mostProblems);
    				}
    				//author has the second most problems
    				else if(i == 1) {
    					changeCompensation(connection, author_id, secondMostProblems);
    				}
    				//author has the third most problems
    				else if (i == 2) {
    					changeCompensation(connection, author_id, thirdMostProblems);
    				}
    				
    				//author is last
    				else if (i == 3) {
    					changeCompensation(connection, author_id, baseRaise);
    				}
    				break;
    				
    			case 3:
    				try {
    					selectFromTable("Author", connection);
    					selectFromTable("Problem", connection);
    				} catch (SQLException e) {
    					e.printStackTrace();
    				}
    				break;
    			case 4:
    				System.exit(-1);
    			default:
    				System.out.println("Incorrect user-input");
    				System.out.println("Retry again");
    		}
    		menu();
    		user_choice = Integer.parseInt(user_input.readLine());
    	}
    }
    
    public static void menu() {
    	System.out.println("PLEASE ENTER AN INTEGER TO SELECT A MENU OPTION");
    	System.out.println("1. Insert Problem");
    	System.out.println("2. Author Compensation");
    	System.out.println("3. Display Problem and Author Table");
    	System.out.println("4. Quit");
    }
    
    
    public static void changeCompensation(Connection connection, int author_id, double multiplier) {
    	
    	double newCompensation;
    	int compensation = 0;
    	
    	try {
    		
    	  	Statement statement = connection.createStatement();
    		String query = "SELECT aid,aname,compensation FROM Author WHERE aid=" + author_id;
    		System.out.println(query);
    		ResultSet authorSet = statement.executeQuery(query);
    		
    		while(authorSet.next())  {
    		compensation = authorSet.getInt("compensation");
    		newCompensation = compensation * multiplier;
    		compensation = (int)(newCompensation + compensation);
    	}
    		
    		//compensation still needs to be an int, cannot change to double
    		String updateQuery = "UPDATE AUTHOR " +
    							"SET COMPENSATION= " + compensation +
    							"WHERE aid=" + author_id;
    		statement.executeUpdate(updateQuery);
    		
    		
    		
    	}catch(SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }
    public static ArrayList<Integer> orderAuthorsByTotalProblems(Connection connection) {
    	
    	//we know there is only 4 authors
    	ArrayList<Integer> authorsByMaxSize = new ArrayList<Integer>(4);
    	try {
    		//Statement stmt = connection.;
    		String query = "SELECT aid, count(aid) as count\n"
    						+ "FROM Problem\n" +
    						"GROUP by aid order by count desc";
    		Statement statement = connection.createStatement();
    		ResultSet keys = statement.executeQuery(query);
    		while(keys.next()) {
    			Integer authorID = keys.getInt("aid");
    			//System.out.println("Added author ID");
    			authorsByMaxSize.add(authorID);
    		}
    		
    	}catch(SQLException e) {
    		System.out.println(e.getMessage());    	
    		}
    	return authorsByMaxSize;
    }
    
    /*public static void ChangeCompensation(Connection connection, int author_id, int compensationRaise) {
    	try {
    		
    	}catch (SQLException e) {
    		
    	}
    }*/
    
    public static void writeToProblemTable(Connection connection, int problem_id, String problem_name, int max_score, int author_id) throws SQLException {
    	//Scanner table_input = new Scanner(System.in);
    	
    	try {
    		PreparedStatement preparedStatement = connection.prepareStatement("INSERT into PROBLEM (pid,pname,max_score,aid) VALUES (?,?,?,?)",
    				Statement.RETURN_GENERATED_KEYS);
    		//Inserting the new problem into the table.
    		preparedStatement.setInt(1, problem_id);
    		preparedStatement.setString(2, problem_name);
    		preparedStatement.setInt(3, max_score);
    		preparedStatement.setInt(4, author_id);
    		preparedStatement.executeUpdate();
    		
    		ResultSet tableKeys = preparedStatement.getGeneratedKeys();
    		tableKeys.next();
    		
    
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }
    //SELECT methods
    public static void selectFromTable(String tableName, Connection connection) throws SQLException {
       Statement statement = connection.createStatement();
       String sql = "SELECT * FROM " + tableName;
       ResultSet rs = statement.executeQuery(sql);
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnsNumber = rsmd.getColumnCount();
       while (rs.next()) {
           for (int i = 1; i <= columnsNumber; i++) {
               if (i > 1) {
                   System.out.print(",  ");
               }
               String columnValue = rs.getString(i);
               System.out.print(columnValue + " " + rsmd.getColumnName(i));
           }
           System.out.println("");
       }
       System.out.println("");
   }

    public static Connection getConnection() {
    	final String connectionString = "jdbc:sqlserver://nels4696-sql-server.database.windows.net:1433;database=​cs-dsa-4513-sql-db​;user=nels4696@nels4696-sql-server;password=Sooner$wag12;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

        Connection connection = null;
        try {
            // Creating connection
            connection = DriverManager.getConnection(connectionString);
            System.out.println("Successfully got connection\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    
}