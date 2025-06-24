package servicio;

import modelo.Inscripcion;

public class NotificacionServicio {

    public void enviarConfirmacionInscripcion(Inscripcion inscripcion) {
        new Thread(() -> {
            try {
                System.out.println("Enviando notificación para: " + inscripcion.getAsistente().getEmail());
                Thread.sleep(2000); 
                System.out.println("Notificación enviada con éxito a " + inscripcion.getAsistente().getEmail() + " para el evento " + inscripcion.getEvento().getTitulo());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("El hilo de notificación fue interrumpido.");
            }
        }).start();
    }
}