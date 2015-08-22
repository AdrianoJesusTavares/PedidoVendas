package gyn.jesus.seguranca;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import gyn.jesus.model.Grupo;
import gyn.jesus.model.Usuario;
import gyn.jesus.repository.Usuarios;
import gyn.jesus.util.CDIServiceLocator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AppUserDatailService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String email)throws UsernameNotFoundException {
		Usuarios usuarios = CDIServiceLocator.getBean(Usuarios.class);
		Usuario usuario = usuarios.porEmail(email);
		UsuarioSistema user = null;
		
		if(usuario !=null){
			user = new UsuarioSistema(usuario, getGrupos(usuario));
		}
		
		return user;
	}

	private Collection<? extends GrantedAuthority> getGrupos(Usuario usuario) {
		List<SimpleGrantedAuthority> grupos = new ArrayList<>();
		
		for(Grupo grupo : usuario.getGrupos()){
			grupos.add(new SimpleGrantedAuthority(grupo.getNome().toUpperCase()));
		}
		return grupos;
	}

}
