/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import modeloDTO.AlunoDTO;
import java.sql.ResultSet;

public class AlunoDAO {

    // Criando a variável do tipo connection para poder conectar com o banco de dados
    // Criada globalmente para não precisar criar em cada uma das funções
    Connection con;

    // Criando variável que prepara o código sql para acessar o banco de dados
    // Criada globalmente para não precisar criar em cada uma das funções abaixo
    PreparedStatement pstm;

    // Criando variável necessária para pegar e trabalhar 
    // com os dados do banco de dados 
    ResultSet rs;

    //Criando variável do tipo arraylist para colocar a lista de alunos
    // extraída do banco de dados a partir do resultset
    ArrayList<AlunoDTO> lista = new ArrayList<>();

    // Criando método do tipo VOID (não retorna nada quando chamado)
    // para cadastrar aluno novo. Coloquei como parâmetro padrão do método um objeto
    // do tipo AlunoDTO que será a classe de onde as informações do aluno
    // serão puxadas.
    public void CadastrarAluno(AlunoDTO objAlunoDTO) throws ClassNotFoundException {

        // Criando variável com comando SQL necessário para
        // incluir um novo aluno na tabela
        // ATENÇÃO: necessário colocar todos os campos que
        // não possuem auto_increment, para pegar do input do usuário através da página JSP
        // e passar para as variáveis ainda não declaradas.
        String sql = "INSERT INTO aluno (nome, sobrenome, telefone, email, curso, cpf) VALUES (?, ?, ?, ?, ?, ?)";

        // Passando a função de conexão com o banco de dados criada na classe ConexaoDAO para
        // a variável con criada acima, para que a função CadastrarCliente funcione direito
        con = new ConexaoDAO().conexaoBD();

        try {

            // Atribuindo à variável pstm a conexão com sql (a partir da variável con)
            // e passando em seguida o comando sql criado acima
            pstm = con.prepareStatement(sql);

            // Passando os campos que serão inseridos no banco de dados, de acordo com o tipo
            // e sequência informada acima    
            pstm.setString(1, objAlunoDTO.getNome());
            pstm.setString(2, objAlunoDTO.getSobrenome());
            pstm.setString(3, objAlunoDTO.getTelefone());
            pstm.setString(4, objAlunoDTO.getEmail());
            pstm.setString(5, objAlunoDTO.getCurso());
            pstm.setString(6, objAlunoDTO.getCpf());

            // Executando o código preparado acima
            pstm.execute();

            // Fechando a conexão e codificação
            pstm.close();

        } catch (SQLException e) {

            System.err.println(e);

        }

    }

    // Criando método do tipo arraylist que retorna um array quando chamado
    // para listar todos os alunos 
    public ArrayList<AlunoDTO> ListarAluno() throws ClassNotFoundException {

        // Criando variável com comando SQL necessário para
        // listar um novo aluno na tabela        
        String sql = "SELECT * FROM aluno;";

        // Passando a função de conexão com o banco de dados criada na classe ConexaoDAO para
        // a variável con criada acima, para que a função funcione direito
        con = new ConexaoDAO().conexaoBD();

        try {

            // Atribuindo à variável pstm a conexão com sql (a partir da variável con)
            // e passando em seguida o comando sql criado acima
            pstm = con.prepareStatement(sql);

            // Como o comando em sql acima retorna uma seleção, é necessário
            // trabalhar com a variável do tipo ResultSet, que vai armazenar
            // os dados da seleção.
            // Abaixo estou passando o resultado do pstm para essa 
            // variável do tipo ResultSet
            rs = pstm.executeQuery(sql);

            // Criando laço de repetição para varrer o resultado
            // do select do banco de dados até pegar o último elemento            
            while (rs.next()) {

                // Criando objeto para acessar as informações do aluno
                AlunoDTO objAlunoDTO = new AlunoDTO();

                // Passando as informações do aluno extraídas do banco de dados
                // para a classe AlunoDTO 
                objAlunoDTO.setId(rs.getInt("id"));
                objAlunoDTO.setNome(rs.getString("nome"));
                objAlunoDTO.setSobrenome(rs.getString("sobrenome"));
                objAlunoDTO.setTelefone(rs.getString("telefone"));
                objAlunoDTO.setEmail(rs.getString("email"));
                objAlunoDTO.setCurso(rs.getString("curso"));
                objAlunoDTO.setCpf(rs.getString("cpf"));

                // Adicionando as informações do aluno para a variável
                // lista criada acima
                lista.add(objAlunoDTO);

            }

            // Executando o código preparado acima
            pstm.execute();

            // Fechando a conexão e codificação
            pstm.close();

        } catch (SQLException e) {
            System.out.println(e);

        }

        return lista;

    }

    // Criando método do tipo VOID (não retorna nada quando chamado)
    // para excluir um aluno. Coloquei como parâmetro padrão do método um objeto
    // do tipo AlunoDTO que será a classe de onde as informações do aluno
    // serão puxadas.
    public void ExcluirAluno(AlunoDTO objAlunoDTO) throws ClassNotFoundException {

        // Criando variável com comando SQL necessário para
        // excluir um aluno na tabela

        String sql = "DELETE FROM aluno WHERE id = ?";

        // Passando a função de conexão com o banco de dados criada na classe ConexaoDAO para
        // a variável con criada acima, para que a função funcione direito
        con = new ConexaoDAO().conexaoBD();

        try {

            // Atribuindo à variável pstm a conexão com sql (a partir da variável con)
            // e passando em seguida o comando sql criado acima
            pstm = con.prepareStatement(sql);

            // Passando o campo de id para o banco de dados filtrar
            // apenas o aluno selecionado
            pstm.setInt(1, objAlunoDTO.getId());

            // Executando o código preparado acima
            pstm.execute();

            // Fechando a conexão e codificação
            pstm.close();

        } catch (SQLException e) {

            System.err.println(e);

        }

    }
}
