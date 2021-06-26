package es.taw.eventosgospring.service;

import es.taw.eventosgospring.dao.EstudioRepository;
import es.taw.eventosgospring.dao.EventoRepository;
import es.taw.eventosgospring.dao.UsuarioEventoRepository;
import es.taw.eventosgospring.dao.UsuarioRepository;
import es.taw.eventosgospring.dto.EstudioDTO;
import es.taw.eventosgospring.dto.EventoDTO;
import es.taw.eventosgospring.dto.UsuarioDTO;
import es.taw.eventosgospring.dto.UsuarioEventoDTO;
import es.taw.eventosgospring.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Service
public class EstudioService {
    
    private EstudioRepository estudioRepository;
    private UsuarioEventoRepository usuarioEventoRepository;
    private UsuarioRepository usuarioRepository;
    private EventoRepository eventoRepository;


    @Autowired
    public void setEstudioRepository(EstudioRepository estudioRepository) {
        this.estudioRepository = estudioRepository;
    }

    @Autowired
    public void setUsuarioEventoRepository(UsuarioEventoRepository usuarioEventoRepository) {
        this.usuarioEventoRepository = usuarioEventoRepository;
    }

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Autowired
    public void setEventoRepository(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public List<EstudioDTO> getEstudioDTOList(Integer usuarioId) {
        List<EstudioDTO> estudioList = new ArrayList<>();
        Usuario u = this.usuarioRepository.findById(usuarioId).orElse(null);
        for(Estudio e : this.estudioRepository.getEstudioListByUser(u)) {
            estudioList.add(e.getDTO());
        }
        return estudioList;
    }

    public UsuarioDTO deleteEstudioById(Integer id, Integer usuarioId) {
        Estudio estudio = this.estudioRepository.findById(id).orElse(null);
        Usuario usuario = this.usuarioRepository.findById(usuarioId).orElse(null);
        if(estudio != null) {
            usuario.getEstudiosById().remove(estudio);
            this.usuarioRepository.save(usuario);
            this.estudioRepository.delete(estudio);
        }
        return usuario.getDTO();
    }

    public List<UsuarioEventoDTO> getResultadoEstudio(String resEstudio) {

        List<UsuarioEventoDTO> resDTO = new ArrayList<>();
        List<UsuarioEvento> res = new ArrayList<>();
        List<UsuarioEvento> aux = this.usuarioEventoRepository.findAll();

        int edadMinima;
        int edadMaxima;
        String ciudad;
        int anio;
        int masculino;
        int femenino;
        int otro;


        // Extraer valores de los filtros
        try (Scanner sc = new Scanner(resEstudio)) {
            sc.useDelimiter(";");
            edadMinima = (sc.hasNext()) ? Integer.parseInt(sc.next()) : -1;
            edadMaxima = (sc.hasNext()) ? Integer.parseInt(sc.next()) : -1;
            String auxsc = sc.next();
            ciudad = (sc.hasNext() && !auxsc.isEmpty()) ? auxsc : null;
            anio = (sc.hasNext()) ? Integer.parseInt(sc.next()) : -1;
            masculino = (sc.hasNext()) ? Integer.parseInt(sc.next()) : -1;
            femenino = (sc.hasNext()) ? Integer.parseInt(sc.next()) : -1;
            otro = (sc.hasNext()) ? Integer.parseInt(sc.next()) : -1;
        }

        List<UsuarioEvento> usuarioEventoListGenero = new ArrayList<>();
        List<UsuarioEvento> usuarioEventoListEdad = new ArrayList<>();
        List<UsuarioEvento> usuarioEventoListCiudad = new ArrayList<>();
        List<UsuarioEvento> usuarioEventoListAnio = new ArrayList<>();

        if (edadMinima <= edadMaxima) {
            // Calcular lista de usuarios que comprendan entre las edades
            usuarioEventoListEdad = this.getFiltroEdad(edadMinima, edadMaxima, aux);

            res = usuarioEventoListEdad;

            if (masculino != -1 || femenino != -1 || otro != -1) {
                // Calcular lista de usuarios de dicho genero
                usuarioEventoListGenero = this.usuarioEventoRepository.filtroSexo(masculino, femenino, otro);
                res.retainAll(usuarioEventoListGenero);

                if(ciudad != null) {
                    // Calcular lista de usuarios de dicha ciudad
                    usuarioEventoListCiudad = this.usuarioEventoRepository.filtroCiudad(ciudad);

                    res.retainAll(usuarioEventoListCiudad);
                }

                if(anio > 0) {
                    usuarioEventoListAnio = this.getFiltroAnio(anio);


                    res.retainAll(usuarioEventoListAnio);
                }

            }
            
        }

        for(UsuarioEvento usuarioEvento : res) {
            resDTO.add(usuarioEvento.getDTO());
        }

        return resDTO;
    }

    private List<UsuarioEvento> getFiltroEdad(Integer min, Integer max, List<UsuarioEvento> l) {

        List<UsuarioEvento> res = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY");
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd");
        for (UsuarioEvento u : l) {
            int x = Period.between(LocalDate.of(Integer.parseInt(sdf.format(u.getFechaNacimiento())), Integer.parseInt(sdf1.format(u.getFechaNacimiento())), Integer.parseInt(sdf2.format(u.getFechaNacimiento()))), LocalDate.now()).getYears();
            if (min <= x && x <= max) {
                res.add(u);
            }
        }

        return res;
    }

    private List<UsuarioEvento> getFiltroAnio(int anio) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<UsuarioEvento> usuarioEventoList = new ArrayList<>();
        List<Evento> eventoListAnio;
        try {
            Date dateIni = simpleDateFormat.parse(anio+"-01-01");
            Date dateFin = simpleDateFormat.parse(anio+"-12-31");
            eventoListAnio = this.eventoRepository.findEventosByAnio(dateIni,dateFin); // Obtengo los eventos que ocurrieron dicho año
            for(UsuarioEvento u : this.usuarioEventoRepository.findAll()) {
                for(Entrada e : u.getEntradasById()) {
                    if(eventoListAnio.contains(e.getEventoByIdEvento())) {
                        usuarioEventoList.add(u);
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return usuarioEventoList;
    }

    public EstudioDTO getEstudioDTOById(Integer id) {
        return this.estudioRepository.findById(id).orElse(null).getDTO();
    }

    public EstudioDTO getEstudioDTOAlmacenado(String titulo, String anio, String edad_min, String edad_max, String masc, String fem, String otro, String ciudad, Integer analistaId) {
        EstudioDTO estudioDTO = new EstudioDTO();
        estudioDTO.setTitulo(titulo);
        estudioDTO.setUsuarioIdAnalista(analistaId);
        // Transformacion del campo resultado para una lectura correcta
        StringJoiner resultado = new StringJoiner(";");
        resultado.add(edad_min);
        resultado.add(edad_max);
        resultado.add(ciudad);
        resultado.add(anio);
        resultado.add(masc);
        resultado.add(fem);
        resultado.add(otro);

        estudioDTO.setResultado(resultado.toString());

        return estudioDTO;
    }

    public void guardarEstudio(EstudioDTO estudioDTO) {

        Optional<Usuario> opt = this.usuarioRepository.findById(estudioDTO.getUsuarioIdAnalista());
        Usuario analista = opt.get();

        Estudio estudio = new Estudio();
        estudio.setTitulo(estudioDTO.getTitulo());
        estudio.setResultado(estudioDTO.getResultado());
        estudio.setUsuarioByIdAnalista(analista);

        if (estudioDTO.getId() != null && estudioDTO.getId() != -1) {
            estudio.setId(estudioDTO.getId());
            this.deleteEstudioById(estudio.getId(), estudio.getUsuarioByIdAnalista().getId());
        }

        this.estudioRepository.save(estudio);
    }
}
