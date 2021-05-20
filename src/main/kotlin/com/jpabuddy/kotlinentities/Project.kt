package com.jpabuddy.kotlinentities

import javax.persistence.*

@Table(name = "project")
@Entity
data class Project (
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Id
    var id: Long? = null,

    @Column(name = "name")
    var name: String? = null,

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "project")
    var team: Team? = null,

    @JoinColumn(name = "client_id")
    @ManyToOne(fetch = FetchType.LAZY)
    var client: Client? = null,
)