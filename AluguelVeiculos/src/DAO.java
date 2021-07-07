
import com.sun.istack.internal.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import sun.util.logging.PlatformLogger;

public class DAO {

    private static final String url = "jdbc:postgresql://localhost:5432/aluguelVeiculos";
    private static final String driver = "org.postgresql.Driver";
    private static final String usuario = "postgres";
    private static final String senha = "admin123";
    Connection conn;
    PreparedStatement ppst;

    public Statement getConnection() throws SQLException {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usuario, senha);
            return conn.createStatement();
        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public boolean incluirCliente(Cliente cliente) {
        try {
            getConnection();
            String sql = "INSERT INTO cliente(id_cliente,"
                    + "Nome, "
                    + "Rua, "
                    + "Numero, "
                    + "Cidade, "
                    + "Estado, "
                    + "Telefone )"
                    + "VALUES (?,?,?,?,?,?,?)";
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1,cliente.getCod());
            ppst.setString(2, cliente.getNome());
            ppst.setString(3, cliente.getRua());
            ppst.setInt(4, cliente.getNumero());
            ppst.setString(5, cliente.getCidade());
            ppst.setString(6, cliente.getEstado());
            ppst.setString(7, cliente.getTelefone());

            ppst.executeUpdate();
            ppst.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean incluirVeiculo(Veiculo veiculo) {
        try {
            getConnection();
            String sql = "INSERT INTO veiculo(id_veiculo, "
                    + "Modelo, "
                    + "Ano, "
                    + "Placa, "
                    + "Cor, "
                    + "Lugar )"
                    + "VALUES (?,?,?,?,?,?)";
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, veiculo.getCod());
            ppst.setString(2, veiculo.getModelo());
            ppst.setInt(3, veiculo.getAno());
            ppst.setString(4, veiculo.getPlaca());
            ppst.setString(5, veiculo.getCor());
            ppst.setString(6, veiculo.getLugar());

            ppst.executeUpdate();
            ppst.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public ResultSet consultarNomeCliente() {
        try {
            String sql = "SELECT * FROM cliente order by id_cliente";
            getConnection();
            ppst = conn.prepareStatement(sql);
            ResultSet rs = ppst.executeQuery();

            return rs;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ResultSet consultarNomeVeiculo() {
        try {
            String sql = "SELECT * FROM veiculo order by id_veiculo";
            getConnection();
            ppst = conn.prepareStatement(sql);
            ResultSet rs = ppst.executeQuery();

            return rs;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void encerraConexao() throws SQLException {
        ppst.close();
        ppst.close();
    }

    public boolean removeCliente(String nome) {
        try {
            String sql = "delete from cliente where nome=?";
            getConnection();
            ppst = conn.prepareStatement(sql);
            ppst.setString(1, nome);
            int r = ppst.executeUpdate();
            ppst.close();
            ppst.close();
            if (r == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean removeVeiculo(String modelo) {
        try {
            String sql = "delete from veiculo where modelo=?";
            getConnection();
            ppst = conn.prepareStatement(sql);
            ppst.setString(1, modelo);
            int r = ppst.executeUpdate();
            ppst.close();
            ppst.close();
            if (r == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean alterarCliente(Cliente cliente, int cod) {
        try {
            getConnection();
            String sql = "UPDATE cliente SET Nome=?,"
                    + "Rua=?, "
                    + "Numero=?, "
                    + "Cidade=?, "
                    + "Estado=?,"
                    + "Telefone=?"
                    + "where id_cliente=?";
            ppst = conn.prepareStatement(sql);
            ppst.setString(1, cliente.getNome());
            ppst.setString(2, cliente.getRua());
            ppst.setInt(3, cliente.getNumero());
            ppst.setString(4, cliente.getCidade());
            ppst.setString(5, cliente.getEstado());
            ppst.setString(6, cliente.getTelefone());
            ppst.setInt(7,cliente.getCod());

            ppst.executeUpdate();
            ppst.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean alterarVeiculo(Veiculo veiculo, int cod) {
        try {
            getConnection();
            String sql = "UPDATE veiculo SET Modelo=?,"
                    + "Ano=?, "
                    + "Placa=?, "
                    + "Cor=?, "
                    + "Lugar=?"
                    + "where id_veiculo=?";
            ppst = conn.prepareStatement(sql);
            ppst.setString(1, veiculo.getModelo());
            ppst.setInt(2, veiculo.getAno());
            ppst.setString(3, veiculo.getPlaca());
            ppst.setString(4, veiculo.getCor());
            ppst.setString(5, veiculo.getLugar());
            ppst.setInt(6, veiculo.getCod());
            

            ppst.executeUpdate();
            ppst.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    

}
