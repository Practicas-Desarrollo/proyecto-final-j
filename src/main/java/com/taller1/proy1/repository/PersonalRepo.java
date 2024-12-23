package com.taller1.proy1.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.taller1.proy1.model.Personal;

public interface PersonalRepo  extends JpaRepository<Personal,Integer> {
	
	@Query("SELECT c FROM Personal c WHERE c.usuario.login = ?1 and c.usuario.clave = ?2 ")
	Personal verificaCuentasolomio(String xlogin, String xclave);

	
	///INSERT a Personal
	@Modifying
	@Query(value = 
	    "insert into Personal(nombre,appaterno,apmaterno,estado,tipoper,foto) values (:nombre,:appaterno, :apmaterno, :estado,:tipoper,:foto)",nativeQuery = true)
	public int addPersonal(@Param("nombre") String xnombre,
			         @Param("appaterno") String xap,
					 @Param("apmaterno") String xam,
					 @Param("estado") int xestado,

					 @Param("tipoper") String xtipoper,
					 @Param("foto") String xfoto);
	
	
	//Extraer codper insertado en Personal
		@Query(value = 
		    "select max(p.codper) from Personal p ",nativeQuery = true)
		public int maxPersonal();
		
		
		//INSERT a Datos
		@Modifying
		@Query(value = 
		    "insert into Datos(codper,ci) values (:codper, :ci)",nativeQuery = true)
		public int addDatos(@Param("codper") int xcodper, @Param("ci")  String xci);
		
		
		//filtro	
				@Query("SELECT c FROM Personal c WHERE (c.estado=:xest1 OR c.estado=:xest2)")
				List <Personal> listafiltro(int xest1, int xest2);
		
		
}
