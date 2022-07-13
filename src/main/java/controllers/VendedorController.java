package controllers;

import DB.Vendedor;

import java.util.Map;

public class VendedorController extends Vendedor {

    public VendedorController(Double salario, Double beneficio, Map<String, Double> vendas) {
        super(salario, beneficio, vendas);
    }
}
