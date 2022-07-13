package models;

import DB.Gerente;
import DB.Secretario;
import DB.Vendedor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Funcionario {
    public Funcionario(String nome, Cargo cargo, String dataContratacao, Double salarioAtual){
        setNome(nome);
        setCargo(cargo);
        setDataContratacao(dataContratacao);
       setSalarioAtual(salarioAtual);
    }
    private String nome;
    private Cargo cargo;
    private String dataContratacao;
    private Double salarioAtual;

    public Double getSalarioAtual() {
        return salarioAtual;
    }

    public void setSalarioAtual(Double salarioAtual) {
        this.salarioAtual = salarioAtual;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public String getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(String dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    private int calcularTempoDeTrabalho(String data) {

            String[] dataContratacao = getDataContratacao().split("/");
            int mesContratacao = Integer.parseInt(dataContratacao[0]);
            int anoContratacao = Integer.parseInt(dataContratacao[1]);
            if (data == null || data.isEmpty()) {
                Date dataHoraAtual = new Date();
                data = new SimpleDateFormat("MM/yyyy").format(dataHoraAtual);
            }
            String[] dataAtual = data.split("/");
            int mesAtual = Integer.parseInt(dataAtual[0]);
            int anoAtual = Integer.parseInt(dataAtual[1]);
            int anoDeTrabalho = anoAtual - anoContratacao;
            int mesDeTrabalho = mesAtual - mesContratacao;

            if (mesDeTrabalho < 0) {
                anoDeTrabalho--;
            }
        return anoDeTrabalho;
    }

    public Double calcularSalario(String data) {
        Double salario = 0.0;
        if (getCargo() instanceof Secretario) {
            Double salarioInicial = getCargo().getSalario();
            if (getCargo().getBeneficio() == null){
                getCargo().setBeneficio(0.0);
            }
            Double beneficio = getCargo().getBeneficio();
            salario = salarioInicial + calcularTempoDeTrabalho(data) * beneficio;
            salario += salario * beneficio;

        } else if (getCargo() instanceof Vendedor) {
            Double salarioInicial = getCargo().getSalario();
            Double beneficio = getCargo().getBeneficio();
            salario = salarioInicial + calcularTempoDeTrabalho(data) * beneficio;
            salario += ((Vendedor) getCargo()).getVendas().get(data) * beneficio;

        } else if (getCargo() instanceof Gerente) {
            Double salarioInicial = getCargo().getSalario();
            Double beneficio = getCargo().getBeneficio();
            salario = salarioInicial + calcularTempoDeTrabalho(data) * beneficio;
            salario += getSalarioAtual() * beneficio;
        }
        return salario;
    }

    public Double calcularSalarioSemBeneficio(String data) {
        Double salario = 0.0;
        if (getCargo() instanceof Secretario) {
            Double salarioInicial = getCargo().getSalario();
            Double beneficio = getCargo().getBeneficio();
            salario = salarioInicial + calcularTempoDeTrabalho(data) * beneficio;

        } else if (getCargo() instanceof Vendedor) {
            Double salarioInicial = getCargo().getSalario();
            Double beneficio = getCargo().getBeneficio();
            salario = salarioInicial + calcularTempoDeTrabalho(data) * beneficio;

        } else if (this.getCargo() instanceof Gerente) {
            Double salarioInicial = getCargo().getSalario();
            Double beneficio = getCargo().getBeneficio();
            salario = salarioInicial + calcularTempoDeTrabalho(data) * beneficio;
        }
        return salario;
    }

    public Double calcularBeneficio(String data) {
        Double salario = 0.0;
        if (getCargo() instanceof Secretario) {
            Double salarioInicial = getCargo().getSalario();
            Double beneficio = getCargo().getBeneficio();
            salario = salarioInicial + calcularTempoDeTrabalho(data) * beneficio;

            salario = salario * beneficio;
        } else if (this.getCargo() instanceof Vendedor) {
            Double salarioInicial = getCargo().getSalario();
            Double beneficio = getCargo().getBeneficio();
            salario = salarioInicial + calcularTempoDeTrabalho(data) * beneficio;

            salario = ((Vendedor) getCargo()).getVendas().get(data) * beneficio;
        } else if (this.getCargo() instanceof Gerente) {
            Double salarioInicial = getCargo().getSalario();
            Double beneficio = getCargo().getBeneficio();
            salario = salarioInicial + calcularTempoDeTrabalho(data) * beneficio;
            salario = getSalarioAtual() * beneficio;
        }
        return salario;
    }
}