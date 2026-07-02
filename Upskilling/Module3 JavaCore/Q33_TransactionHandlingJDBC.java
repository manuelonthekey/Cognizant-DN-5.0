import java.sql.*;
public class Q33_TransactionHandlingJDBC {
    public static void transferMoney(Connection conn, int fromAcc, int toAcc, double amount) {
        try {
            conn.setAutoCommit(false);
            PreparedStatement withdraw = conn.prepareStatement("UPDATE accounts SET balance = balance - ? WHERE id = ?");
            withdraw.setDouble(1, amount); withdraw.setInt(2, fromAcc);
            withdraw.executeUpdate();
            
            PreparedStatement deposit = conn.prepareStatement("UPDATE accounts SET balance = balance + ? WHERE id = ?");
            deposit.setDouble(1, amount); deposit.setInt(2, toAcc);
            deposit.executeUpdate();
            
            conn.commit();
            System.out.println("Transfer successful");
        } catch (SQLException e) {
            try { conn.rollback(); System.out.println("Transaction rolled back"); } catch (SQLException ex) { ex.printStackTrace(); }
        }
    }
}