package com.jpabuddy.kotlinentities

import org.hibernate.Hibernate
import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.*

@MappedSuperclass
open class BaseLongIdEntity() {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null
}

@Table(name = "project")
@Entity
class Project //(val security: String)
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @Column(name = "name", nullable = false)
    var name: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    open var client: Client? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Project

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 1545761250

}


interface ProjectRepository : JpaRepository<Project, Long>

@Table(name = "client")
@Entity
data class Client(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,

    @Column(name = "name", nullable = false)
    var name: String? = null,

    @OneToMany(mappedBy = "client", orphanRemoval = true)
    var projects: MutableSet<Project> = mutableSetOf(),

    @JoinColumn(name = "client_id")
    @OneToMany
    var contacts: MutableSet<Contact> = mutableSetOf(),
)

@Table(name = "contact")
@Entity
data class Contact(
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Id
    var id: Long? = null,

    @Column(name = "name")
    var name: String? = null
)

