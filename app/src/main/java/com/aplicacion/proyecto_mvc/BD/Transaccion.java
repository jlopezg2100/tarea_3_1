package com.aplicacion.proyecto_mvc.BD;

public class Transaccion {

    /* tablas */
    public static final String tabla_empleados = "tbl_empleados";
    public static final String tabla_puesto = "tbl_puesto";


    /* Campos */
    public static final String id = "id";
    public static final String nombre = "nombre";
    public static final String apellido = "apellido";
    public static final String edad = "edad";
    public static final String direccion = "direccion";
    public static final String puesto = "puesto";

    /* tablas - CREATE , DROP */
    public static final String CreateTableEmpleados = "CREATE TABLE tbl_empleados( id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, apellido TEXT, edad INTEGER," +
            "direccion TEXT, puesto TEXT)";
    public static final String DropTableEmpleados = "DROP TABLE IF EXISTS tbl_empleados";



    /* Campos */
    public static final String id_p = "id_p";
    public static final String puesto_p = "puesto_p";

    public static final String CreateTablePuesto = "CREATE TABLE tbl_puesto( id_p INTEGER PRIMARY KEY AUTOINCREMENT, puesto_p TEXT)";
    public static final String DropTablePuesto = "DROP TABLE IF EXISTS tbl_puesto";



    /* Creacion del nombre de la base de datos */
    public static final String NameDataBase = "DBMVC";


}
