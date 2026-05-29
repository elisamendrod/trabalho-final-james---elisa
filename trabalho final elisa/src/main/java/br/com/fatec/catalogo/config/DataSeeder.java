package br.com.fatec.catalogo.config;

import br.com.fatec.catalogo.models.UsuarioModel;
import br.com.fatec.catalogo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.findByUsername("admin").isEmpty()) {
            UsuarioModel admin = new UsuarioModel();
            admin.setUsername("admin");
            
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ROLE_ADMIN");

            usuarioRepository.save(admin);
            System.out.println(">>> Usuário ADMIN criado com sucesso (admin / admin123)");
        }

        if (usuarioRepository.findByUsername("usuario").isEmpty()) {
            UsuarioModel usuario = new UsuarioModel();
            usuario.setUsername("usuario");
            usuario.setPassword(passwordEncoder.encode("usuario123"));
            usuario.setRole("ROLE_USER");

            usuarioRepository.save(usuario);
            System.out.println(">>> Usuário comum criado com sucesso (usuario / usuario123)");
        }
    }
}
