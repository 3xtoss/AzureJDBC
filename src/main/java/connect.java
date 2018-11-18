
//imported libaries
import java.sql.*;
import java.io.*;
import java.util.*;

/**
 * 
 * @author jared
 * @version 1.0
 * @description simple application
 */



public class connect {
	
	//getting the connection from my database
	final static Connection connection = getConnection();
	final static Scanner scan = new Scanner(System.in);
	
    public static void main(String[] args) {
      run();
  }
	
	/**
	 * 
	 */
	private static void run() {
		// TODO Auto-generated method stub
		
		int user_input;
		int choice;
		do {
			menu();
			System.out.println("");
			System.out.println("");
			System.out.println("Please choose a menu option: ");
			user_input = scan.nextInt();
			
			switch(user_input) {
				case 0:
					menu();
					break;
					
				case 1:
					System.out.println("Please Choose an Employee Type");
					System.out.println("1 = Worker" + "\t" + "2 = Quality Controller" + "\t" + "3 = Technical Staff");
					choice = scan.nextInt();
					employee_type_menu_helper(choice);
					break;
				case 2:
					
					System.out.println("Please Select the Product Type: ");
					System.out.println("1 = Product 1" + "\t" + "2 = Product 2" + "\t" + "3 = Product 3");
					choice = scan.nextInt();
					boolean proceed = product_type_menu_helper(choice);
					break;
				case 3:
					
					if(customer_menu_helper()) {
						
					}
					//failure
					else {
						
					}
					break;
				case 4:
					
					System.out.println("Please Select the Account Type: ");
					System.out.println("1 = Product1" + "\t" + "2 = Product2" + "\t" + "3 = Product3");
					choice = scan.nextInt();
					if(account_type_menu_helper(choice)) {
						
					}
					
					//failure
					else {
						
					}
					break;
				case 5:
					if (complaint_menu_helper()) {
						
					}
					
					//failure
					else {
						
					}
					break;
				case 6:
					if (accident_menu_helper()) {
						
					}
					
					//failure
					else {
						
					}
					break;
				case 7:
					break;
				case 8:
					//Ask for Employee id
					
					//view_product_Worker(id);
					
				case 9:
					// Get QA id
					
					// find QA.product_id in repairs
					
					// sum total entry in repairs
					
					break;
					
				case 10:
					//Ask for Product id
					
					//See if it exists in Product 3
					
					//Get Product 3 Account
					
					//Get Account Information
					break;
					
				case 11:
					break;
				case 12:
					break;
				case 13:
					break;
				case 14:
					break;
				case 15:
					break;
				case 16:
					break;
				case 17:
					break;
				case 18:
					break;
				case 19:
					break;
				case 20:
					System.out.println("--You have chosen to exit the program --");
					System.out.println("Goodbye!");
					System.exit(user_input);
					break;
				default:
					System.out.println("--You have entered an invalid menu option--");
					System.out.println("Please select a valid menu option");
				
					// a short pause would be helpful.
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}		
		}while(user_input!=20);
		
	}
	/**
	 * 
	 * @param args
	 * @throws NumberFormatException
	 * @throws IOException
	 */
    
    public static void menu() {
    	System.out.println("PLEASE ENTER AN INTEGER TO SELECT A MENU OPTION");
    	System.out.println("0." + "\t" + "Display Menu");
    	System.out.println("1." + "\t" + " Enter a new Employee: ");
    	System.out.println("2."  + "\t" +  " Enter a new Product: ");
    	System.out.println("3."  + "\t" +  " Enter a new Customer: ");
    	System.out.println("4."  + "\t" +  " Enter a new Account: ");
    	System.out.println("5."  + "\t" +  " Enter a new Complaint: ");
    	System.out.println("6."  + "\t" +  " Enter a new Accident: ");
    	System.out.println("7."  + "\t" +  " Retreieve Product production Information: ");
    	System.out.println("8."  + "\t" +  " Retreieve all Products made by a specific Worker: ");
    	System.out.println("9."  + "\t" +  " Retreieve total number of Errors by Quality Controller: ");
    	System.out.println("10."  + "\t" +  " Retreieve total costs of Product 3 Repairs: ");
    	System.out.println("11."  + "\t" +  " Retreieve Customers who bought Products with a specific Color: ");
    	System.out.println("12."  + "\t" +  " Retreieve total number of work days missed by Accident(s) caused by Repair(s): ");
    	System.out.println("12."  + "\t" +  " Retreieve total costs of the products in Product3 which were repaired at the request of a specific Quality Controller: ");
    	System.out.println("13."  + "\t" +  " Retreieve all customers who are also Workers: ");
    	System.out.println("14."  + "\t" +  " Retreieve all customers who purchased products they made or repaired: ");
    	System.out.println("15."  + "\t" +  " Retreieve the average cost of all products made in a particular year: ");
    	System.out.println("16."  + "\t" +  " Switch the position between position between Technical Staff and Quality Controller: ");
    	System.out.println("17."  + "\t" +  " Delete all Accidents within a given time frame; ");
    	System.out.println("18."  + "\t" +  " Import");
    	System.out.println("19."  + "\t" +  " Export");
    	System.out.println("20."  + "\t" +  " Quit");
    }


    public static Connection getConnection() {
    	final String connectionString = "jdbc:sqlserver://mulh8377-sql-server-2.database.windows.net:1433;database=​cs-dsa-4513-sql-db​;user=mulh8377@mulh8377-sql-server-2;password=Jared2876!;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30";
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
    
			private static boolean employee_type_menu_helper(int choice) {
				
				
				boolean helper = false;
				
				if (choice == 1) {
					System.out.println("You have chosen to enter a Worker");
					System.out.println("");
					
					System.out.print("Enter max production: ");
					final int max_production = scan.nextInt();
					
					
					System.out.print("Please Enter a Name: ");
					final String name = scan.nextLine();
					
					System.out.print("Please Enter an address: ");
					final String address = scan.nextLine();
					 
					/**
					 * insert the employee
					 */
					
					
					/**
					 * insert the product
					 */
					
					
					/**
					 * insert the worker 
					*/
				}
				else if (choice == 2) {
					System.out.print("Please Enter a Name: ");
					final String name = scan.nextLine();
					
					System.out.print("Please Enter an address: ");
					final String address = scan.nextLine();
					
					/**
					 * insert the Employee
					 */

					/**
					 * insert the Product
					 */
					
					/**
					 * insert the Quality Controller
					 */
				}
				
				else if (choice == 3) {
					System.out.print("Please Enter a Name: ");
					final String name = scan.nextLine();
					
					System.out.print("Please Enter an address: ");
					final String address = scan.nextLine();
					
					
					/**
					 * insert the employee
					 */
					
					
					
					/**
					 * insert the product
					 */
					
					
					/**
					 * insert the Technical staff
					 * 
					 */
					
				}
				//fail condition, will make the user restart the menu.
				else {
					System.out.println("--- Invalid Employee Type Choice ---");
				}
				
				return helper;
		
			}
    		
    		
			
			/**
			 * 
			 * @param choice
			 * @return
			 *////@TODOS FINISH product_type_menu_helper
			 
    		private static boolean product_type_menu_helper(int choice) {
    			
    			
    			// required product fields.
    			String product_date;
    			int days_in_production;
    			
    			Scanner temp = new Scanner(System.in);
				if (choice == 1) {
					
					System.out.println("You have chosen to enter a Product1 Type");
					System.out.println("");
					System.out.print("");
					
					System.out.println("0 = small"+ "\t" + "1 = medium" + "\t" + "2 = large");
					
					System.out.println("Please enter the size: ");
					int size = (int) temp.nextInt();
					
					System.out.println("Please enter the software name: ");
					String software_name = (String) temp.next();
					
					System.out.println("Please enter the date the product was produced: ");
					product_date = (String) temp.next();
					
					System.out.println("Please enter days in production: ");
					days_in_production = (int) temp.nextInt();
					
					
					
					
				}
				else if (choice == 2) {
					
					System.out.println("You have chosen to enter a Product2 Type");
					System.out.println("");
					System.out.print("");
					System.out.println("0 = small"+ "\t" + "1 = medium" + "\t" + "2 = large");
					
					System.out.println("Please enter the size: ");
					int size = temp.nextInt();
					
					System.out.println("Please enter the color name: ");
					String color = temp.next();
					
					System.out.println("Please enter the date the product was produced: ");
					product_date = temp.next();
					
					System.out.println("Please enter days in production: ");
					days_in_production = temp.nextInt();
				}
				
				else if (choice == 3) {
					System.out.println("You have chosen to enter a Product3 Type");
					System.out.println("");
					System.out.print("");
					System.out.println("0 = small"+ "\t" + "1 = medium" + "\t" + "2 = large");
					
					System.out.println("Please enter the size: ");
					int size = temp.nextInt();
					
					System.out.println("Please enter the weight: ");
					
					double weight = temp.nextDouble();
					
					System.out.println("Please enter the date the product was produced: ");
					product_date = temp.next();
					
					System.out.println("Please enter days in production: ");
					days_in_production = temp.nextInt();
					
				}
				//fail condition, will make the user restart the menu.
				else {
					System.out.println("--- Invalid Product Type Choice ---");
				}
				
				return true;
				
    		}
    		
    		private static boolean account_type_menu_helper(int choice) {

    			// required product fields.
    			Scanner temp = new Scanner(System.in);
    			String account_date;
    			double product_cost;
    			
				if (choice == 1) {
					
					System.out.println("You have chosen to enter a Account1 Type");
					System.out.println("");
					System.out.print("");
					
					System.out.println("Please enter the size: ");
					int size = (int) temp.nextInt();
					
					System.out.println("Please enter the software name: ");
					String software_name = (String) temp.next();
					
					System.out.println("Please enter the date the account was created: ");
					account_date = (String) temp.next();
					
					System.out.println("Please enter days in production: ");
					product_cost = (double) temp.nextDouble();
					
				}
				else if (choice == 2) {
					
					System.out.println("You have chosen to enter a Account2 Type");
					System.out.println("");
					System.out.print("");
					
					System.out.println("Please enter the size: ");
					int size = temp.nextInt();
					
					System.out.println("Please enter the color name: ");
					String color = temp.next();
					
					System.out.println("Please enter the date the account was created: ");
					account_date = (String) temp.next();
					
					System.out.println("Please enter days in production: ");
					product_cost = (double) temp.nextDouble();
				}
				
				else if (choice == 3) {
					System.out.println("You have chosen to enter a Product3 Type");
					System.out.println("");
					System.out.print("");
					System.out.println("0 = small"+ "\t" + "1 = medium" + "\t" + "2 = large");
					
					System.out.println("Please enter the size: ");
					int size = temp.nextInt();
					
					System.out.println("Please enter the weight: ");
					
					double weight = temp.nextDouble();
					
					System.out.println("Please enter the date the account was created: ");
					account_date = (String) temp.next();
					
					System.out.println("Please enter days in production: ");
					product_cost = (double) temp.nextDouble();
					
				}
				//fail condition, will make the user restart the menu.
				else {
					System.out.println("--- Invalid Product Type Choice ---");
				}
				
				return true;
				
    		}
    		
    		private static boolean complaint_menu_helper() {
    			Scanner temp = new Scanner(System.in);
    			
    			String complaint_date;
    			String description;
    			int treatment;
    			int customer_id;
    			
    			System.out.println("Please enter your customer_id");
    			customer_id = scan.nextInt();
    			
    			/**
    			 * check if customer exists
    			 */
    			if(false) {
    				return false;
    			}
    			
    			
    			System.out.println("Please Enter the Date: ");
    			complaint_date = temp.next();
    			
    			System.out.println("Please Enter the Complaint Description: ");
    			description = temp.next();
    			
    			
    			System.out.println("Please Enter the treatment type: ");
    			treatment = temp.nextInt();
    			
    			// money back
    			if (treatment == 0) {
    				
    			}
    			
    			// request repair
    			else if (treatment == 1) {
    				
    			}
    			else {
    				System.out.println("--Wrong Treatment Type Entered----");
    				return false;
    			}
    			
    			return true;
    		}
    		
    		private static boolean customer_menu_helper() {
    			
    			Scanner temp = new Scanner(System.in);
    			String name;
    			String address;
    			int product_id;
    			
    			
    			System.out.println("Please Enter the Product ID: ");
    			product_id = temp.nextInt();
    			
    			/**
    			 * search if product_id is in the table
    			 * 
    			 */
    			
    			/**
    			 * ask if they would like to add another product.
    			 * 
    			 */
    			
    			/**
    			 * enter customer information
    			 */
    			
    			System.out.println("Please enter your name: ");
    			name = temp.next();
    			
    			System.out.println("Please enter your address: ");
    			address = temp.next();
    			
    			/**
    			 * add customer to database.
    			 */
    			return true;
    		}
    		
    		private static boolean accident_menu_helper() {
    			Scanner temp = new Scanner(System.in);
    			String accident_date;
    			int days_missed;
    			
    			boolean helper;
    			
    			/**
    			 *  check if employee id exists
    			 */
    			
    			System.out.println("Please Enter the Accident Date: ");
    			accident_date = temp.next();
    			
    			
    			System.out.println("Please Enter the total days missed: ");
    			days_missed = temp.nextInt();
    			return true;
    		}
    		
    		
    
    		
		    ////////////////////////////////////////////////////////////////////////////////////////////////////////
		    //					Employee Functions for Modifying Database
		    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    		
    		/**
    		 * 
    		 * @param name
    		 * @param address
    		 * @return
    		 */
		    private static boolean add_Employee(String name, String address) {
		    	
		    	return true;
		    }
		    
		    /**
		     * 
		     * @param max_production
		     * @param employe_id
		     * @param product_id
		     * @return
		     */
		    private static boolean add_Worker(int max_production, int employe_id, int product_id) {
		    	return true;
		    }
		    
		    
		    /**
		     * 
		     * @param request_repair
		     * @param employee_id
		     * @param product_id
		     * @return
		     */
		    private static boolean add_Quality_Controller(boolean request_repair, int employee_id, int product_id) {
		    	return true;
		    }
		    
		    
		    /**
		     * 
		     * @param education_record
		     * @param position
		     * @param employee_id
		     * @param product_id
		     * @return
		     */
		    private static boolean add_Technical_Staff(String education_record, String position, int employee_id, int product_id) {
		    	return true;
		    }
		    
		    ////////////////////////////////////////////////////////////////////////////////////////////////////////
		    //					Employee Functions ------- Finished
		    ////////////////////////////////////////////////////////////////////////////////////////////////////////
		    
		    
		    
		    ////////////////////////////////////////////////////////////////////////////////////////////////////////
		    //					Product Functions ------- Modify
		    ////////////////////////////////////////////////////////////////////////////////////////////////////////
		    /**
		     * 
		     * @param production_date
		     * @param days_in_production
		     * @return
		     */
		    private static boolean add_Product(String production_date, int days_in_production) {
		    	return true;
		    }
		    
		    /**
		     * 
		     * @param size
		     * @param software_name
		     * @param product_id
		     * @return
		     */
		    private static boolean add_Product_1(int size, int software_name, int product_id) {
		    	return true;
		    }
		    
		    /**
		     * 
		     * @param size
		     * @param color
		     * @param product_id
		     * @return
		     */
		    private static boolean add_Product_2(int size, String color, int product_id) {
		    	return true;
		    }
		    
		    
		    /**
		     * 
		     * @param size
		     * @param weight
		     * @param product_id
		     * @return
		     */
		    private static boolean add_Product_3(int size, double weight, int product_id) {
		    	return true;
		    }
		    
		    ////////////////////////////////////////////////////////////////////////////////////////////////////////
		    //					Product Modifiers ------- Finished
		    ////////////////////////////////////////////////////////////////////////////////////////////////////////
		    
		    
		    
		    ////////////////////////////////////////////////////////////////////////////////////////////////////////
		    //					Customer Functions ------- Modify
		    ////////////////////////////////////////////////////////////////////////////////////////////////////////
		    /**
		     * 
		     * @param name
		     * @param address
		     * @param product_id
		     * @return
		     */
		    private static boolean add_Customer(String name, String address, int product_id) {
		    	return true;
		    }
		    
		    /**
		     * 
		     * @param complaint_date
		     * @param description
		     * @param treatment
		     * @return
		     */
		    private static boolean add_Complaint(String complaint_date, String description, int treatment) {
		    	return true;
		    }
		    
		    
		    ////////////////////////////////////////////////////////////////////////////////////////////////////////
		    //					Customer Modifiers ------- Finished
		    ////////////////////////////////////////////////////////////////////////////////////////////////////////
		    
		    
		    
		    
		    ////////////////////////////////////////////////////////////////////////////////////////////////////////
		    //					Account Functions ------- Modify
		    ////////////////////////////////////////////////////////////////////////////////////////////////////////
		    
		    
		    /**
		     * 
		     * @param account_creation
		     * @param cost
		     * @return
		     */
		    private static boolean add_Account(String account_creation, double cost) {
		    	return true;
		    }
		    
		    
		    /**
		     * 
		     * @param account_id
		     * @param product1_id
		     * @return
		     */
		    private static boolean add_Account_Product_1(int account_id, int product1_id) {
		    	return true;
		    }
		    
		    /**
		     * 
		     * @param account_id
		     * @param product2_id
		     * @return
		     */
		    private static boolean add_Account_Product_2(int account_id, int product2_id) {
		    	return true;
		    }
		    
		    /**
		     * 
		     * @param account_id
		     * @param product3_id
		     * @return
		     */
		    private static boolean add_Account_Product_3(int account_id, int product3_id) {
		    	return true;
		    }
		    
		    
		    ////////////////////////////////////////////////////////////////////////////////////////////////////////
		    //					Account Modifiers ------- Finished
		    ////////////////////////////////////////////////////////////////////////////////////////////////////////
		    
		    
		    
		    
		    ////////////////////////////////////////////////////////////////////////////////////////////////////////
		    //					Accident and Repair Functions ------- Modify
		    ////////////////////////////////////////////////////////////////////////////////////////////////////////
		    /**
		     * 
		     * @param accident_date
		     * @param days_missed
		     * @param employee_id
		     * @return
		     */
		    private static boolean add_Accident(String accident_date, int days_missed, int employee_id) {
		    	
		    	return true;
		    }
		    
		    ////////////////////////////////////////////////////////////////////////////////////////////////////////
		    //					Accident and Repair Modifiers ------- Finished
		    ////////////////////////////////////////////////////////////////////////////////////////////////////////
		    
		    
		    private static void view_employee_Worker() {
		    	
		    }
		    
		    private static void view_product_Worker() {
		    	
		    }
		    
		    private static void view_employee_QA() {
		    	
		    }
		    
		    private static void view_product_QA() {
		    	
		    }
		    
		    private static void view_employee_Tech() {
		    	
		    }
		    
		    private static void view_product_Tech() {
		    	
		    }
		    
		    private static void view_Product1() {
		    	
		    }
		    
		    private static void view_Product1_Cost() {
		    	
		    }
		    
		    private static void view_Product2() {
		    	
		    }
		    
		    private static void view_Product2_Cost() {
		    	
		    }
		    
		    private static void view_Product3() {
		    	
		    }
		    
		    private static void view_Product3_Cost() {
		    	
		    }
		    
		    private static void view_Customer_Purchase() {
		    	
		    }
		    
}
