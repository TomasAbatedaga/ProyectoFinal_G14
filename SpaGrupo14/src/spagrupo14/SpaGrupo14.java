/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package spagrupo14;

import Modelo.Cliente;
import Persistencia.ClienteData;
import Vista.MenuPrincipal;

/**
 *
 * @author abate
 */
public class SpaGrupo14 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    MenuPrincipal.main(args);
    Cliente tomas = new Cliente(43123123,"Tomas","26449128",34,"dolor lumbar",true);
    Cliente fernando = new Cliente(53218287,"Fernando","321312",43,"dolor lumbar",true);
    Cliente daniel = new Cliente(5588287,"Daniel","322131312",15,"dolor",true);
    Cliente lucas = new Cliente(4123412, "Lucas","23123132",22,"fatiga muscular",true);
    Cliente facundo = new Cliente(44234242, "Facundo", "1341313",55, "dolor de cuello",true);
    ClienteData clientedata = new ClienteData();
    
    /*clientedata.agregarCliente(daniel);
    clientedata.agregarCliente(lucas);
    clientedata.agregarCliente(tomas);
    clientedata.agregarCliente(facundo);
    clientedata.agregarCliente(fernando);*/
    
    for (Cliente c: clientedata.listarCliente()){
        System.out.println(c);
    }
    
    
    
    
    
    
    
    
    
    
    
    }
    
}
