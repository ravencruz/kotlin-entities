package com.jpabuddy.kotlinentities

import javax.persistence.*

@Table(name = "client")
@Entity
data class Client(
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "name")
    var name: String? = null,

    @OneToMany(mappedBy = "client", orphanRemoval = true)
    var projects: MutableList<Project>? = mutableListOf()
)