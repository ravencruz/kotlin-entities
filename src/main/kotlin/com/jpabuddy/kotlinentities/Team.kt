package com.jpabuddy.kotlinentities

import javax.persistence.*

@Entity
@Table(name = "team")
data class Team (
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Id
    var id: Long? = null,

    @Column(name = "name")
    var name: String? = null,

    @JoinColumn(name = "project_id")
    @OneToOne(fetch = FetchType.LAZY)
    var project: Project? = null,

    @ManyToMany(mappedBy = "teams")
    var teamMember: MutableList<TeamMember>? = mutableListOf()
)
