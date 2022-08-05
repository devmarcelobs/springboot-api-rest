package br.com.alura.forum.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.alura.forum.modelo.Curso;

@RunWith(SpringRunner.class)
@DataJpaTest //utilizado para test de repository
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //não substitui as configurações do db
@ActiveProfiles("test")
public class CursoRepositoryTest {
    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TestEntityManager testEntityManager;
    
    @Test
	public void deveriaCarregarUmCursoBuscaPeloNome(){
        String nomeCurso = "HTML 5";

        Curso html5 = new Curso();
        html5.setNome(nomeCurso);
        html5.setCategoria("Programacao");
        testEntityManager.persist(html5);

        Curso curso = cursoRepository.findByNome(nomeCurso);

        assertNotNull(curso);
        assertEquals(nomeCurso, curso.getNome());
	}

    @Test
	public void naoDeveriaCarregarUmCursoBuscaPeloNome(){
        String nomeCurso = "JPA";

        Curso curso = cursoRepository.findByNome(nomeCurso);

        assertNull(curso);
	}
}
