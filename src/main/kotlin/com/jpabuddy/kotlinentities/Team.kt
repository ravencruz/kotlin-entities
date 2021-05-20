package com.jpabuddy.kotlinentities

import javax.persistence.*

@Entity
@Table(name = "team")
class Team (
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Id
    var id: Long,

    @Column(name = "name", nullable = false)
    var name: String,

    @JoinColumn(name = "project_id")
    @OneToOne(fetch = FetchType.LAZY)
    var project: Project,

    @ManyToMany(mappedBy = "teams")
    var teamMember: MutableList<TeamMember>? = mutableListOf()
)
