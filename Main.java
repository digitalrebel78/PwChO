import java.sql.*;
import java.util.Scanner;

public class Main {
	public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		//Ustalenie polaczenia z baza danych
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriveManager.getConnection("jdbc:mysql://10.0.10.3:3306/default", "RVoitovych", "root");
			Statement statement = connection.createStatement();
			statement.executeUpdate(
				"CREATE TABLE IF NOT EXISTS `default`.`words` ( `id` INT NOT NULL AUTO_INCREMENT , `polish` VARCHAR(64) NOT NULL , `english` VARCHAR(64) NOT NULL , `german` VARCHAR(64) NOT NULL, ENGINE = InnoDB;"
			);
			statement.close();
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("SQLException");
		}
		
		//Cialo glowne aplikacji
		while(true) {
			System.out.println("1. Add record");
			System.out.println("2. Delete record");
			System.out.println("3. Edit record");
			System.out.println(">:");
			inquire = scan.nextInt();
			scan.nextLine();

			switch(inquire) {
				case 1: {
					//Dodanie rekordu
					System.out.println("Polish:");
					String pl = scan.nextLine();
					System.out.println("English:");
					String en = scan.nextLine();
					System.out.println("German:");
					String de = scan.nextLine();
					try {
						PreparedStatement statement = connection.prepareStatement("INSERT INTO `words` ( `polish`, `english`, `german` VALUES (?, ?, ?)");
						statement.setString(1, pl);
						statement.setString(2, en);
						statement.setString(3, de);
						statement.execute();
						System.out.println("Done");
					} catch (SQLException e) {
						System.err.println("SQLException");
					}
					break;
				}
				case 2: {
					//Usuniecie rekordu
					System.out.println("Id:");
					int id = scan.nextInt();
					try {
						PreparedStatement statement = connection.preparedStatement("DELETE FROM `words` WHERE ID = ?");
						statement.setInt(1, id);
						statement.execute();
						System.out.println("Done");
					} catch (SQLException e) {
						System.err.println("SQLException");
					}
					break;
				}
				case 3: {
					//Edytowanie rekordu
					System.out.println("Id:");
					int id = scan.nextInt();
					System.out.println("Polish:");
					String pl = scan.nextLine();
					System.out.println("English:");
					String en = scan.nextLine();
					System.out.println("German:");
					String de = scan.nextLine();
					try {
						PreparedStatement statement = connection.preparedStatement("UPDATE `words` SET `polish` = ?, `english` = ?, `german` = ? WHERE ID = ?");
						statement.setString(1, pl);
						statement.setString(2, en);
						statement.setString(3, de);
						statement.setInt(4, id);
						statement.execute();
						System.out.println("Done");
					} catch (SQLException e) {
						System.err.println("SQLException");
					}
					break;
				default:
					System.err.printlin("Illegal id");
				}
			}
		}
	}
}
