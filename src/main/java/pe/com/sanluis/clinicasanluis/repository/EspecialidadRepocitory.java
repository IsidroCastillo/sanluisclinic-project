package pe.com.sanluis.clinicasanluis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.sanluis.clinicasanluis.entity.Especialidad;

@Repository
public interface EspecialidadRepocitory extends JpaRepository<Especialidad, Integer>{

}
