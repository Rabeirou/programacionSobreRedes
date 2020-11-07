/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author Fran
 */
public class Principal {
    
    private static File archivoProveedores = new File("proveedores.xml");
    private static File archivoStock = new File("stock.xml");
    private static File archivoClientes = new File("clientes.xml");
    private static File archivoVentas = new File("ventas.xml");
    
    /**
     * @param args the command line arguments
     * El metodo main se encarga de ejecutar el menu principal.
     */
    public static void main(String[] args) {
        IniciarArchivo iniciar = new IniciarArchivo();
        iniciar.Iniciar();
        
        int opcion = 2;
        Verificacion ver = new Verificacion();
        while(opcion <= 2 && opcion >= 1){
            opcion = ver.verificar("[MENU PRINCIPAL]\n1- Agregar datos\n2- Mostrar datos\n3- Salir\nRespuesta: ", 1, 3);
            switch(opcion){
                case 1:
                    menuDatos(1);
                    break;
                case 2:
                    menuDatos(2);
                    break;
                default:
                    System.out.println("\n\nAdeus!");
            }
        }
    }
    
    /**
     * Muestra el menu de agregar o mostrar.
     * @param n Define si se muestra el menu de agregar o el de mostrar {@link int}.
     */
    private static void menuDatos(int n){
        String tipoDeMenu;
        if(n==1) tipoDeMenu = "AGREGAR";
        else tipoDeMenu = "MOSTRAR";
        int opcion = 2;
        Verificacion ver = new Verificacion();
        while(opcion <= 4 && opcion >= 1){
            opcion = ver.verificar("[MENU "+tipoDeMenu+"]\n1- Proveedores\n2- Stock\n3- Clientes\n4- Ventas\n5- Salir\nRespuesta: ", 1, 5);
            switch(opcion){
                case 1:
                    if(n==1) agregarProveedor();
                    else mostrar(1);
                    break;
                case 2:
                    if(n==1) agregarStock();
                    else mostrar(2);
                    break;
                case 3:
                    if(n==1) agregarCliente();
                    else mostrar(3);
                    break;
                case 4:
                    if(n==1) agregarVenta();
                    else mostrar(4);
                    break;
                default:
            }
        }
    }
    
    /**
     * Agrega un proveedor al archivo proveedoresxml.
     */
    private static void agregarProveedor(){
        String id = "", nombre_empresa = "", materiales = "", precio = "", descripcion = "";
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        try {
            System.out.println("Ingrese la id del proveedor: ");
            id = br.readLine();
            System.out.println("Ingrese el nombre de la empresa del proveedor: ");
            nombre_empresa = br.readLine();
            System.out.println("Ingrese los materiales que vende el proveedor: ");
            materiales = br.readLine();
            System.out.println("Ingrese el precio al que vende los materiales el proveedor: ");
            precio = br.readLine();
            System.out.println("Ingrese una descripcion del producto del proveedor: ");
            descripcion = br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        SAXBuilder constructor = new SAXBuilder();
        Document doc;
        try {
            doc = (Document) constructor.build(archivoProveedores);
            
            Element proveedor = new Element("Proveedor");
            proveedor.setAttribute(new Attribute("id", id));
            proveedor.addContent(new Element("nombre_de_la_empresa").setText(nombre_empresa));
            proveedor.addContent(new Element("materiales").setText(materiales));
            proveedor.addContent(new Element("precio").setText(precio));
            proveedor.addContent(new Element("descripcion_del_producto").setText(descripcion));

            doc.getRootElement().addContent(proveedor);

            XMLOutputter salidaXML = new XMLOutputter();
            salidaXML.setFormat(Format.getPrettyFormat());
            salidaXML.output(doc, new FileWriter(archivoProveedores));
            
        } catch (JDOMException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Agrega un producto al archivo stockxml.
     */
    private static void agregarStock(){
        String id = "", id_prov = "", nombre_prod = "", desc_prod = "", precio_venta = "", precio_compra="", stock="";
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        try {
            System.out.println("Ingrese la id del producto: ");
            id = br.readLine();
            System.out.println("Ingrese la id del proveedor: ");
            id_prov = br.readLine();
            System.out.println("Ingrese el nombre del producto: ");
            nombre_prod = br.readLine();
            System.out.println("Ingrese la descripcion del producto: ");
            desc_prod = br.readLine();
            System.out.println("Ingrese el precio de venta del producto: ");
            precio_venta = br.readLine();
            System.out.println("Ingrese el precio de compra del producto: ");
            precio_compra = br.readLine();
            System.out.println("Ingrese el stock del producto: ");
            stock = br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        SAXBuilder constructor = new SAXBuilder();
        Document doc;
        try {
            doc = (Document) constructor.build(archivoStock);
            
            Element producto = new Element("Producto");
            producto.setAttribute(new Attribute("id", id));
            producto.addContent(new Element("id_proveedor").setText(id_prov));
            producto.addContent(new Element("nombre_del_producto").setText(nombre_prod));
            producto.addContent(new Element("descripcion").setText(desc_prod));
            producto.addContent(new Element("precio_venta").setText(precio_venta));
            producto.addContent(new Element("precio_compra").setText(precio_compra));
            producto.addContent(new Element("stock").setText(stock));

            doc.getRootElement().addContent(producto);

            XMLOutputter salidaXML = new XMLOutputter();
            salidaXML.setFormat(Format.getPrettyFormat());
            salidaXML.output(doc, new FileWriter(archivoStock));
            
        } catch (JDOMException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Agrega un cliente al archivo clientesxml.
     */
    private static void agregarCliente(){
        String id = "", nombre_y_ap = "", tipo_consumidor = "", cuil = "", ultima_compra = "";
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        try {
            System.out.println("Ingrese la id del cliente: ");
            id = br.readLine();
            System.out.println("Ingrese el nombre y apellido del cliente: ");
            nombre_y_ap = br.readLine();
            System.out.println("Ingrese el tipo de consumidor: ");
            tipo_consumidor = br.readLine();
            System.out.println("Ingrese el cuil del cliente: ");
            cuil = br.readLine();
            System.out.println("Ingrese la ultima compra: ");
            ultima_compra = br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        SAXBuilder constructor = new SAXBuilder();
        Document doc;
        try {
            doc = (Document) constructor.build(archivoClientes);
            
            Element cliente = new Element("Cliente");
            cliente.setAttribute(new Attribute("id", id));
            cliente.addContent(new Element("nombre_y_apellido").setText(nombre_y_ap));
            cliente.addContent(new Element("tipo_de_consumidor").setText(tipo_consumidor));
            cliente.addContent(new Element("cuil_o_cuit").setText(cuil));
            cliente.addContent(new Element("ultima_compra").setText(ultima_compra));

            doc.getRootElement().addContent(cliente);

            XMLOutputter salidaXML = new XMLOutputter();
            salidaXML.setFormat(Format.getPrettyFormat());
            salidaXML.output(doc, new FileWriter(archivoClientes));
            
        } catch (JDOMException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Agrega una venta al archivo ventasxml.
     */
    private static void agregarVenta(){
        String id = "", id_cliente = "", id_prod = "", nomb_prod = "", precio = "", cant="";
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        try {
            System.out.println("Ingrese la id de la venta: ");
            id = br.readLine();
            System.out.println("Ingrese la id del cliente: ");
            id_cliente = br.readLine();
            System.out.println("Ingrese la id del producto: ");
            id_prod = br.readLine();
            System.out.println("Ingrese el nombre del producto: ");
            nomb_prod = br.readLine();
            System.out.println("Ingrese el precio del producto: ");
            precio = br.readLine();
            System.out.println("Ingrese la cantidad de productos: ");
            cant = br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        SAXBuilder constructor = new SAXBuilder();
        Document doc;
        try {
            doc = (Document) constructor.build(archivoVentas);
            
            Element venta = new Element("Venta");
            venta.setAttribute(new Attribute("id", id));
            venta.addContent(new Element("id_del_cliente").setText(id_cliente));
            venta.addContent(new Element("id_del_producto").setText(id_prod));
            venta.addContent(new Element("nombre_del_producto").setText(nomb_prod));
            venta.addContent(new Element("precio_del_producto").setText(precio));
            venta.addContent(new Element("cantidad_de_productos").setText(cant));

            doc.getRootElement().addContent(venta);

            XMLOutputter salidaXML = new XMLOutputter();
            salidaXML.setFormat(Format.getPrettyFormat());
            salidaXML.output(doc, new FileWriter(archivoVentas));
            
        } catch (JDOMException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Muestra la lista del archivo elegido previamente en el menu mostrar.
     * @param n Define cual de los cuatro archivos mostrar{@link int}
     */
    private static void mostrar(int n){
        File archivo;
        String nombre;
        int cont = 0;
        if(n==1){
            archivo = new File("proveedores.xml");
            nombre = "Proveedor";
        }else if(n==2){
            archivo = new File("stock.xml");
            nombre = "Producto";
        }else if(n==3){
            archivo = new File("clientes.xml");
            nombre = "Cliente";
        }else{
            archivo = new File("ventas.xml");
            nombre = "Venta";
        }
        
        SAXBuilder constructor = new SAXBuilder();
        try {
            Document doc = (Document) constructor.build(archivo);
            Element root = doc.getRootElement();
            List<Element> elementos = root.getChildren(nombre);
            
            for(Element item : elementos){
                
                cont++;
                
                System.out.println("\n["+nombre+"]");
                
                if(!item.getAttributes().isEmpty()){
                    for(Attribute a : item.getAttributes()){
                        System.out.println("\t"+a.getName()+": "+a.getValue()+" (atributo)");
                    }
                }
                
                for(Element subItem : item.getChildren()){
                    System.out.println("\t"+subItem.getName()+": "+subItem.getValue());
                }
                        
            }
            
            System.out.println("\nCantidad de elementos: "+cont+"\n");
        } catch (JDOMException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}