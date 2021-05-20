package com.jpabuddy.kotlinentities

import javax.persistence.*

@Table(name = "project")
@Entity
class Project (
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Id
    var id: Long,

    @Column(name = "name", nullable = false)
    var name: String,

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "project")
    var team: Team?,

    @JoinColumn(name = "client_id")
    @ManyToOne(fetch = FetchType.LAZY)
    var client: Client,
)