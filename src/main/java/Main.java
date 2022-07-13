import Exceptions.ValorNaoInformadoException;
import controllers.FuncionarioController;
import DB.Gerente;
import DB.Secretario;
import DB.Vendedor;
import controllers.VendedorController;
import models.Funcionario;

import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        // Vendas mapeadas
        Map<String, Double> vendasAna = new HashMap<>();
        Map<String, Double> vendasJoao = new HashMap<>();

        // Lista de funcionários
        List<Funcionario> todosFuncionarios = new ArrayList<>();
        List<Funcionario> funcionariosBeneficio = new ArrayList<>();
        List<Funcionario> vendedores = new ArrayList<>();

        // Controlador de Funcionário
        FuncionarioController func = new FuncionarioController();

        // Vendas de Ana Silva
        vendasAna.put("12/2021", 5200.0);
        vendasAna.put("01/2022", 4000.0);
        vendasAna.put("02/2022", 4200.0);
        vendasAna.put("03/2022", 5850.0);
        vendasAna.put("04/2022", 7000.0);

        // Vendas de João Mendes
        vendasJoao.put("12/2021", 3400.0);
        vendasJoao.put("01/2022", 7700.0);
        vendasJoao.put("02/2022", 5000.0);
        vendasJoao.put("03/2022", 5900.0);
        vendasJoao.put("04/2022", 6500.0);

        // Instancias de funcionários
        Secretario s = new Secretario(7000.0, 0.2);
        Gerente g = new Gerente(10000.0, 0.0);
        Vendedor vendedoraAna = new VendedorController(12000.0, 0.3, vendasAna);
        Vendedor vendedorJoao = new VendedorController(12000.0, 0.3, vendasJoao);

        // Alimentando lista de funcionários
        todosFuncionarios.add(new Funcionario("Jorge Carvalho", s, "01/2018", s.getSalario()));
        todosFuncionarios.add(new Funcionario("Maria Souza", s, "12/2015", g.getSalario()));
        todosFuncionarios.add(new Funcionario("Ana Silva", vendedoraAna, "12/2021", vendedoraAna.getSalario()));
        todosFuncionarios.add(new Funcionario("Joao Mendes", vendedorJoao, "12/2021", vendedorJoao.getSalario()));
        todosFuncionarios.add(new Funcionario("Juliana Alves", g, "07/2017", g.getSalario()));
        todosFuncionarios.add(new Funcionario("Bento Albino", g, "03/2014", g.getSalario()));


        todosFuncionarios.stream().forEach(f -> {
            if (Objects.nonNull(f.getCargo()) && Objects.nonNull(f.getCargo().getBeneficio()))
                funcionariosBeneficio.add(f);
        });

        todosFuncionarios.stream().forEach(f -> {
            if (f.getCargo() instanceof Vendedor)
                vendedores.add(f);
        });

        try {
            System.out.println("O salario e beneficio do mes e: " + func.calcularSalarioBeneficio(todosFuncionarios, "02/2022"));
            System.out.println("O total pago somente em salarios do mes e: " + func.calcularSomenteSalario(funcionariosBeneficio, "05/2022"));
            System.out.println("O total pago somente em beneficio do mes e: " + func.calcularSomenteBeneficio(funcionariosBeneficio, "02/2022"));
            System.out.println("O salario mais alto do mes e: " + func.calcularSalarioMaisAlto(todosFuncionarios, "01/2022"));
            System.out.println("O beneficio mais alto do mes e: " + func.calcularBeneficioMaisAlto(funcionariosBeneficio, "04/2022"));
            System.out.println("A maior venda e: " + func.calcularMaiorVenda(vendedores, "04/2022"));
        } catch (ValorNaoInformadoException ex) {
            ex.toString();
        }
    }
}