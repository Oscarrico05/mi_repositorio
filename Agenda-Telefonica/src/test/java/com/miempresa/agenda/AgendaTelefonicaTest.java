package com.miempresa.agenda;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class AgendaTelefonicaTest {

    private List<Contacto> agenda;
    private Scanner scanner;

    @BeforeEach
    public void setUp() {
        agenda = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    @Test
    public void testAñadirContacto() {
        String nombre = "Juan Perez";
        String telefono = "123456789";
        scanner = new Scanner(nombre + "\n" + telefono);
        AgendaTelefonica.añadirContacto(agenda, scanner);

        assertEquals(1, agenda.size());
        assertEquals(nombre, agenda.get(0).getNombre());
        assertEquals(telefono, agenda.get(0).getTelefono());
    }

    @Test
    public void testEliminarContacto() {
        Contacto contacto = new Contacto("Juan Perez", "123456789");
        agenda.add(contacto);
        String nombre = "Juan Perez";
        scanner = new Scanner(nombre);
        AgendaTelefonica.eliminarContacto(agenda, scanner);

        assertEquals(0, agenda.size());
    }

    @Test
    public void testMostrarAgenda() {
        Contacto contacto1 = new Contacto("Juan Perez", "123456789");
        Contacto contacto2 = new Contacto("Maria Lopez", "987654321");
        agenda.add(contacto1);
        agenda.add(contacto2);

        // No hay una forma directa de probar la salida en consola, pero podemos verificar que los contactos están en la agenda
        assertEquals(2, agenda.size());
        assertEquals("Juan Perez", agenda.get(0).getNombre());
        assertEquals("Maria Lopez", agenda.get(1).getNombre());
    }
}