package com.jpabuddy.kotlinentities

import org.hibernate.proxy.HibernateProxy
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.platform.commons.logging.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.lang.Exception
import java.util.*

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProjectRepositoryTest(
    @Autowired val projectRepository: ProjectRepository,
) {

    companion object{
        private val LOGGER = LoggerFactory.getLogger(ProjectRepositoryTest::class.java)
    }

    /**
     * Despues de volverlo clase se tiene el mismo problema de que buscas un projecto y a pesar de que es lazy
     * te lo trae el cliente asociado en un segundo request
     * (a pesar de que pongo open en el atributo como hace y luego quita en el video)
     */
    @Test
    internal fun projectIsInitialized() {
        val project = projectRepository.findById(1)
        assertTrue(project.isPresent)
    }

    /**
     * Hibernate uses lzy references as it is expected !! ?????? WTF
     *
     */
    @Test
    internal fun lazyLoadEnabled() {
        val project = projectRepository.findById(1).get()
        val client = project.client!!
        LOGGER.info { "Class used for the client reference: ${client::class.java}" }
        assertTrue(HibernateProxy::class.java.isAssignableFrom(client::class.java))
    }

    /**
     * somehow equals was being called and equals is based an all field from primary constructor
     * so we remove that
     */
//    @Test
//    internal fun equalsIssue() {
//        val project = projectRepository.findById(1).get()
//        assertTrue(project == project.copy())
//    }

    /**
     * Pero que pasa si lo usamos como clave en un map
     * este problema es parecido a que si lo tuvieramos en loombok
     *
     * el jpa plugin es el que crea constructores vacios por defecto
     */
    @Test
    internal fun hashCodeIsConsistent() {
        val awesomeProject = Project().apply {
            name = "Awesome project"
        }
        val hashSet = hashSetOf(awesomeProject)
        LOGGER.info { awesomeProject.hashCode().toString() }

        projectRepository.save(awesomeProject)
        LOGGER.info { awesomeProject.hashCode().toString() }

        assertTrue(awesomeProject in hashSet)
    }

//    @Test
//    internal fun valForIdTest() {
//        val project = Project().apply {
//            //id = 1000L
//            name = "New project"
//        }
//        assertTrue(project.isNew())
//        projectRepository.save(project)
//
//        assertTrue(!project.isNew())
//    }

    @Test
    internal fun lateInitWorks() {
        val project = Project().apply {
            client = Client()
        }
        assertTrue(project.client!!.name != null)
    }

}