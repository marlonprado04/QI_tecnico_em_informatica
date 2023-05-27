package modeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modeloDTO.ExemplarDTO;

public class ExemplarDAO {

    Connection con; // Variável de conexão com o banco de dados
    PreparedStatement pstm; // Variável para preparar o código SQL
    ResultSet rs; // Variável para armazenar os resultados do banco de dados
    ArrayList<ExemplarDTO> lista = new ArrayList<>(); // Lista de objetos ExemplarDTO

    // Método para cadastrar um novo exemplar
    public void CadastrarExemplar(ExemplarDTO objExemplarDTO) throws ClassNotFoundException {

        String sql = "INSERT INTO exemplar (id_livro, numero_do_exemplar, status) VALUES (?, ?, ?)";

        con = new ConexaoDAO().conexaoBD();

        try {
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, objExemplarDTO.getIdLivro());
            pstm.setInt(2, objExemplarDTO.getNumeroDoExemplar());
            pstm.setString(3, objExemplarDTO.getStatus());
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            System.err.println(e);
        }

    }

    // Método para listar os exemplares de um livro específico
    public ArrayList<ExemplarDTO> ListarExemplarPorLivro(int idLivro) throws ClassNotFoundException {

        String sql = "SELECT * FROM exemplar WHERE id_livro = ?";

        con = new ConexaoDAO().conexaoBD();

        try {
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, idLivro);
            rs = pstm.executeQuery();

            while (rs.next()) {
                ExemplarDTO objExemplarDTO = new ExemplarDTO();
                objExemplarDTO.setId(rs.getInt("id"));
                objExemplarDTO.setIdLivro(rs.getInt("id_livro"));
                objExemplarDTO.setNumeroDoExemplar(rs.getInt("numero_do_exemplar"));
                objExemplarDTO.setStatus(rs.getString("status"));
                lista.add(objExemplarDTO);
            }

            pstm.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return lista;
    }

    // Método para excluir um exemplar
    public void ExcluirExemplar(ExemplarDTO objExemplarDTO) throws ClassNotFoundException {

        String sql = "DELETE FROM exemplar WHERE id = ?";

        con = new ConexaoDAO().conexaoBD();

        try {
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, objExemplarDTO.getId());
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            System.err.println(e);
        }

    }

    // Método para alterar as informações de um exemplar
    public void AlterarExemplar(ExemplarDTO objExemplarDTO) throws ClassNotFoundException {

        String sql = "UPDATE exemplar SET id_livro = ?, numero_do_exemplar = ?, status = ? WHERE id = ?";

        con = new ConexaoDAO().conexaoBD();

        try {
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, objExemplarDTO.getIdLivro());
            pstm.setInt(2, objExemplarDTO.getNumeroDoExemplar());
            pstm.setString(3, objExemplarDTO.getStatus());
            pstm.setInt(4, objExemplarDTO.getId());
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            System.err.println(e);
        }

    }

}