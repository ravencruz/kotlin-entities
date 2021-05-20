package com.jpabuddy.kotlinentities

import javax.persistence.*


@Entity
@Table(name = "team_member")
data class TeamMember(
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Id
    var id: Long? = null,

    @Column(name = "name")
    var name: String? = null,

    @JoinTable(
        name = "team_member_team_link",
        joinColumns = [JoinColumn(name = "team_member_id")],
        inverseJoinColumns = [JoinColumn(name = "team_id")]
    )
    @ManyToMany
    var teams: MutableList<Team>? = mutableListOf()

)