package main.java.com.johnlab.inventarysystem.service;

import javafx.collections.ObservableList;
import main.java.com.johnlab.inventarysystem.model.Aspirante;
import main.java.com.johnlab.inventarysystem.repository.AspiranteRepository;

public class InventarioService {

    private AspiranteRepository aspiranteRepository;

    public InventarioService(AspiranteRepository aspiranteRepository) {
        this.aspiranteRepository = aspiranteRepository;
    }

    public ObservableList<Aspirante> listarAspirantes() {
        return aspiranteRepository.findAll();
    }

    public boolean crearAspirante(Aspirante aspirante) {
        return aspiranteRepository.createAspirante(aspirante);

    }

    public boolean actualizarAspirante(Aspirante aspirante) {
        return aspiranteRepository.updateAspirante(aspirante);
    }

    public boolean eliminarAspirante(Aspirante aspirante) {
        return aspiranteRepository.deleteAspirante(aspirante);

    }

}
