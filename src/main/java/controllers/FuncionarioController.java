package controllers;

import DB.Vendedor;
import models.Funcionario;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class FuncionarioController {
    public Double calcularSalarioBeneficio(List<Funcionario> funcionarios, String data) {
        AtomicReference<Double> salarioTotal = new AtomicReference<>(0.0);
        funcionarios.stream().forEach(f -> {
            salarioTotal.updateAndGet(v -> v + f.calcularSalario(data));
        });
        return salarioTotal.get();
    }

    public Double calcularSomenteSalario(List<Funcionario> funcionarios, String data) {
        AtomicReference<Double> salarioTotal = new AtomicReference<>(0.0);
        funcionarios.stream().forEach(f -> {
            salarioTotal.updateAndGet(v -> v + f.calcularSalarioSemBeneficio(data));
        });
        return salarioTotal.get();
    }

    public Double calcularSomenteBeneficio(List<Funcionario> funcionarios, String data) {
        AtomicReference<Double> salarioTotal = new AtomicReference<>(0.0);
        funcionarios.stream().forEach(f -> {
            salarioTotal.updateAndGet(v -> v + f.calcularBeneficio(data));
        });
        return salarioTotal.get();
    }

    public Double calcularSalarioMaisAlto(List<Funcionario> funcionarios, String data) {
        Double salarioMaisAlto = 0.0;
        for (Funcionario f : funcionarios) {
            if (salarioMaisAlto == 0.0)
                salarioMaisAlto = f.calcularSalario(data);
            else if (f.calcularSalario(data) > salarioMaisAlto)
                salarioMaisAlto = f.calcularSalario(data);
        }
        return salarioMaisAlto;
    }

    public Double calcularBeneficioMaisAlto(List<Funcionario> funcionarios, String data) {
        Double salarioMaisAlto = 0.0;
        for (Funcionario f : funcionarios) {
            if (salarioMaisAlto == 0.0)
                salarioMaisAlto = f.calcularBeneficio(data);
            else if (f.calcularBeneficio(data) > salarioMaisAlto)
                salarioMaisAlto = f.calcularBeneficio(data);
        }
        return salarioMaisAlto;
    }

    public String calcularMaiorVenda(List<Funcionario> funcionarios, String data) {
        Double salarioTotal = 0.0;
        String maiorVendedor = "";
        for (Funcionario f : funcionarios) {
            Map<String, Double> vendas = ((Vendedor)f.getCargo()).getVendas();
            if (salarioTotal == 0.0) {
                salarioTotal = vendas.get(data);
                maiorVendedor = f.getNome();
            } else if (salarioTotal < vendas.get(data)) {
                salarioTotal = vendas.get(data);
                maiorVendedor = f.getNome();
            }
        }
        return maiorVendedor;
    }
}
