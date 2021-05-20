package com.jpabuddy.kotlinentities

import org.hibernate.collection.internal.PersistentBag
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TeamRepositoryTest(@Autowired val teamRepository: TeamRepository) {


    @Test
    fun databaseIsInitialized() {
        val team = teamRepository.findById(1)
        assertTrue(team.isPresent)
    }

    @Test
    fun teamMembersArePresent() {
        val team = teamRepository.findById(1).get()
        assertFalse(team.teamMember!!.isEmpty())
    }

    @Test
    fun teamMembersAreInitializedWithAProxy() {
        val team = teamRepository.getOne(1)
        assertTrue(team.teamMember is PersistentBag)
    }

}