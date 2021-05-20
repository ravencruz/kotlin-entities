package com.jpabuddy.kotlinentities

import javax.persistence.*

@Table(name = "client")
@Entity
class Client(
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column(name = "name", nullable = false)
    var name: String?,

    @OneToMany(mappedBy = "client", orphanRemoval = true)
    var projects: MutableList<Project> = mutableListOf()
)