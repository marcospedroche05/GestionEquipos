package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("unidad-equipos");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

            //INSERTA_EQUIPOS
            em.persist(new Equipo("Getafe", "EstadioGeta"));
            em.persist(new Equipo("Pepsi", "EstadioPepsi"));

            //ELIMINA_EQUIPOS
            em.remove(em.find(Equipo.class, 2));

            //MODIFICA_EQUIPOS
            Equipo equipoAModificar = em.find(Equipo.class, 1);
            equipoAModificar.setEstadio("EstadioGetafeModificado");
            em.merge(equipoAModificar);

            //LEE_EQUIPOS
            System.out.println(em.find(Equipo.class, 1));

            //-------------------------------------------------------------------------

            //INSERTA_JUGADORES
            Jugador jugador = new Jugador();
            jugador.setNombre("Cano");
            jugador.setEstatura(1.6F);
            jugador.setPeso(100F);
            jugador.setIdEquipo(em.find(Equipo.class, 1));
            em.persist(jugador);
            Jugador jugador2 = new Jugador("Jose", 1.5F, 200F, em.find(Equipo.class, 1));
            em.persist(jugador2);


            //ELIMINA_JUGADORES
            em.remove(em.find(Jugador.class, 2));

            //MODIFICA_JUGADORES
            Jugador jugadorAModificar = em.find(Jugador.class, 1);
            jugadorAModificar.setPeso(200F);
            em.merge(jugadorAModificar);

            //LEE_JUGADORES
            System.out.println(em.find(Jugador.class, 1));


        tx.commit();
    }
}
